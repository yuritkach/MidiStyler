using Android.Util;
using deque;
using Java.Lang;
using Java.Net;
using Java.Util.Concurrent;
using rtpmidi;
using rtpmidi.error;
using rtpmidi.handler;
using rtpmidi.messages;
using rtpmidi.model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Threading;
using Thread = Java.Lang.Thread;

namespace rtpmidi.session {


    /**
    * The session server handles MIDI invitation, clock synchronin requests, as well as the MIDI messages. In order to
    * handle MIDI messages a {@link AppleMidiSession} has to be added via {@link #addAppleMidiSession(AppleMidiSession)}.
    * Otherwise all invitation requests are rejected. The session server must be run on a port which is {@code control port
    * + 1}
    */

    public class BaseRunable:Java.Lang.Object,IRunnable
    {
        private Runnable _runable;

        protected BaseRunable()
        {
            _runable = new Runnable(Run);
        }

        // Thread methods / properties
        public virtual void Run() => _runable.Run();
    }

    public class RtpMidiSessionServer: BaseRunable, IRtpMidiCommandListener, IRtpMidiMessageListener,IRtpMidiMessageSender
    {

        private static int SOCKET_TIMEOUT = 1000;

        private enum State {
            ACCEPT_INVITATIONS, FULL
        }

        private static int RECEIVE_BUFFER_LENGTH = 1024;

        private IExecutorService executorService;
        public int Ssrc { get; protected set; }
        public string Name { get; protected set; }
        private RtpMidiCommandHandler midiCommandHandler = new RtpMidiCommandHandler();
        private RtpMidiMessageHandler midiMessageHandler = new RtpMidiMessageHandler();
        public int Port { get; protected set; }
        private bool running = true;
        private DatagramSocket socket;
        private Deque<RtpMidiSession> sessions = new Deque<RtpMidiSession>();
        private Dictionary<int, RtpMidiSessionConnection> currentSessions = new Dictionary<int, RtpMidiSessionConnection>();
        private List<ISessionChangeListener> sessionChangeListeners = new List<ISessionChangeListener>();
        private Thread thread;

        /**
        * @param name The name under which the other peers should see this server
        * @param port The session server port which must be {@code control port + 1}
        */
        public RtpMidiSessionServer(string name,int port):this(name, port, Executors.NewCachedThreadPool())
        {
        }

        public RtpMidiSessionServer(string name, int port, IExecutorService executorService) {
            Port = port;
            Ssrc = new Random().Next();
            Name = name;
            this.executorService = executorService;

            midiCommandHandler.RegisterListener(this);
            midiMessageHandler.RegisterListener(this);
        }

        Thread CreateThread(string name) {
            return new Thread(this, name + "SessionThread");
        }

        public void Start() {
            object lck = new object();
            lock (lck)
            {
                thread = CreateThread(Name);
                try
                {
                    socket = CreateSocket();
                    socket.SoTimeout = SOCKET_TIMEOUT;
                }
                catch (SocketException e)
                {
                    throw new RtpMidiSessionServerRuntimeException("DatagramSocket cannot be opened", e);
                }
                thread.Start();
                Log.Debug("RtpMidi","MIDI session server started");
            }
        }

        protected DatagramSocket CreateSocket()
        {
            return new DatagramSocket(Port);
        }

        
        public override void Run() {
            while (running) {

                try
                {
                    byte[] receiveData = new byte[RECEIVE_BUFFER_LENGTH];
                    DatagramPacket incomingPacket = new DatagramPacket(receiveData, receiveData.Length);
                    socket.Receive(incomingPacket);

                    System.Threading.Thread thread = new System.Threading.Thread(new ThreadStart(()=> {
                        if (receiveData[0] == RtpMidiCommand.MIDI_COMMAND_HEADER1)
                        {
                            midiCommandHandler.handle(receiveData,
                                new model.RtpMidiServer(incomingPacket.Address, incomingPacket.Port));
                        }
                        else
                        {
                            midiMessageHandler.Handle(receiveData,
                                new RtpMidiServer(incomingPacket.Address.HostName, incomingPacket.Port));
                        }

                    }));
                    thread.Start();
                    
                }
                catch (SocketTimeoutException ignored)
                {
                }
                catch (IOException e)
                {
                    Log.Error("RtpMidi","IOException while receiving", e);
                }
            }
            socket.Close();
        }

        /**
        * Shutds down all sockets and threads
        */
        public void StopServer() {
            running = false;
            currentSessions.Clear();
            sessions.Clear();
            executorService.Shutdown();
            Log.Debug("RtpMidi","MIDI session server stopped");
        }

        private void Send(RtpMidiCommand midiCommand, model.RtpMidiServer rtpMidiServer)
        {
            Send(midiCommand.ToByteArray(), rtpMidiServer);
        }

        private void Send(byte[] data, model.RtpMidiServer rtpMidiServer)
        {
            
            Log.Debug("RtpMidi","Sending data {} to server {}", BitConverter.ToString(data).Replace("-", ""), rtpMidiServer);
            
            socket.Send(new DatagramPacket(data, data.Length, rtpMidiServer.InetAddress, rtpMidiServer.Port));
        }

        
        public void Send(RtpMidiMessage rtpMidiMessage,model.RtpMidiServer rtpMidiServer)
        { 
            Send(rtpMidiMessage.ToByteArray(), rtpMidiServer);
        }

        
        public void OnMidiInvitation(RtpMidiInvitationRequest invitation, model.RtpMidiServer rtpMidiServer)
        {
            Log.Info("RtpMidi","MIDI invitation from: {}", rtpMidiServer);
            if (GetSessionServerState() == State.ACCEPT_INVITATIONS) {
                SendMidiInvitationAnswer(rtpMidiServer, "accept",
                        new RtpMidiInvitationAccepted(invitation.ProtocolVersion, invitation.InitiatorToken,Ssrc, Name));
                RtpMidiSession rtpMidiSession = sessions.Last; // Pop()
                RtpMidiSessionConnection connection =
                        new RtpMidiSessionConnection(rtpMidiSession, rtpMidiServer, Ssrc, this);
                rtpMidiSession.Sender = connection;
                currentSessions.Add(invitation.Ssrc, connection);
                NotifyMaxNumberOfSessions();
            } else {
                SendMidiInvitationAnswer(rtpMidiServer, "decline",
                        new RtpMidiInvitationDeclined(invitation.ProtocolVersion, invitation.InitiatorToken,Ssrc, Name));
            }
        }

        /**
        * @return {@link State#FULL} if no sessions are available. {@link State#ACCEPT_INVITATIONS} otherwise
        */
        private State GetSessionServerState() {
            return sessions.IsEmpty ? State.FULL : State.ACCEPT_INVITATIONS;
        }

        private void SendMidiInvitationAnswer(model.RtpMidiServer rtpMidiServer,string type,RtpMidiInvitation midiInvitation) {
            try {
                Log.Info("RtpMidi","Sending invitation {} to: {}", type, rtpMidiServer);
                Send(midiInvitation, rtpMidiServer);
            }
            catch (IOException e) {
                Log.Error("RtpMidi","IOException while sending invitation {}", type, e);
            }
        }

        
        public void OnClockSynchronization(RtpMidiClockSynchronization clockSynchronization, model.RtpMidiServer rtpMidiServer)
        {
            if (clockSynchronization.Count == (byte) 0) {
                RtpMidiSessionConnection sessionTuple = currentSessions.GetValueOrDefault(clockSynchronization.Ssrc);
                long currentTimestamp;
                if (sessionTuple != null)
                {
                    long sessionTimestamp = sessionTuple.RtpMidiSession.GetCurrentTimestamp();
                    if (sessionTimestamp != -1)
                    {
                        currentTimestamp = sessionTimestamp;
                    }
                    else
                    {
                        currentTimestamp = GetFallbackTimestamp();
                    }
                }
                else
                {
                    currentTimestamp = GetFallbackTimestamp();
                }
                Log.Debug("RtpMidi","Answering with timestamp: {}", currentTimestamp);
                RtpMidiClockSynchronization clockSynchronizationAnswer =
                        new RtpMidiClockSynchronization(Ssrc, (byte) 1, clockSynchronization.Timestamp1,
                                currentTimestamp, 0L);
                try
                {
                    Send(clockSynchronizationAnswer, rtpMidiServer);
                }
                catch (IOException e)
                {
                    Log.Error("RtpMidi","IOException while sending clock synchronization", e);
                }
            }
            else 
            if (clockSynchronization.Count == (byte) 2)
            {
                long offsetEstimate =
                        (clockSynchronization.Timestamp3 + clockSynchronization.Timestamp1) / 2 -
                                clockSynchronization.Timestamp2;

                RtpMidiSessionConnection midiServer = currentSessions.GetValueOrDefault(clockSynchronization.Ssrc);
                if (midiServer != null) {
                    midiServer.RtpMidiSession.OffsetEstimate = offsetEstimate;
                }   
            }
        }   

        private long GetFallbackTimestamp()
        {
            return Java.Lang.JavaSystem.CurrentTimeMillis() * 10;
        }

        
        public void OnEndSession(RtpMidiEndSession rtpMidiEndSession, model.RtpMidiServer rtpMidiServer) {
            Log.Info("RtpMidi","Session end from: {}", rtpMidiServer);
            RtpMidiSessionConnection midiServer = currentSessions.GetValueOrDefault(rtpMidiEndSession.Ssrc);
            if (midiServer != null) {
                RtpMidiSession RtpMidiSession = midiServer.RtpMidiSession;
                RtpMidiSession.Sender=null;
                RtpMidiSession.OnEndSession(rtpMidiEndSession, rtpMidiServer);
            }
            currentSessions.Remove(rtpMidiEndSession.Ssrc);
            RtpMidiSessionConnection sessionTuple = currentSessions.GetValueOrDefault(rtpMidiEndSession.Ssrc);
            if (sessionTuple != null) {
                sessions.AddLast(sessionTuple.RtpMidiSession);
                NotifyMaxNumberOfSessions();
            }
        }

        
        public void OnMidiMessage(MidiCommandHeader midiCommandHeader, MidiMessage message, int timestamp) {
            RtpMidiSessionConnection sessionTuple = currentSessions.GetValueOrDefault(midiCommandHeader.RtpHeader.Ssrc);
            if (sessionTuple != null) {
                sessionTuple.RtpMidiSession.OnMidiMessage(midiCommandHeader, message, timestamp);
            } else {
                Log.Debug("RtpMidi","Could not find session for ssrc: {}", Ssrc);
            }
        }

        /**
        * Add a new {@link AppleMidiSession} to this server
        *
        * @param session The session to be added
        */
        public void AddRtpMidiSession(RtpMidiSession session) {
            sessions.AddLast(session);
            NotifyMaxNumberOfSessions();
        }

        /**
        * Remove the {@link AppleMidiSession} from this server
        *
        * @param session The session to be removed
        */
        public void RemoveRtpMidiSession(RtpMidiSession session) {
            sessions.RemoveLast(/*session*/);
            foreach (var entry in currentSessions) {
                if (entry.Value.RtpMidiSession.Equals(session)) {
                    int ssrc = entry.Key;
                    currentSessions.Remove(ssrc);
                    NotifyMaxNumberOfSessions();
                }
            }
        }

        /**
        * Informs all {@link SessionChangeListener} about the new number of available sessions
        */
        private void NotifyMaxNumberOfSessions() {
            foreach (ISessionChangeListener listener in sessionChangeListeners) {
                listener.OnMaxNumberOfSessionsChange(sessions.Count);
            }
        }

        /**
        * @return The current number of available sessions for receiving MIDI messages
        */
        public int GetNumberOfAvailableSessions() {
            return sessions.Count;
        }

        /**
        * Registers a new {@link SessionChangeListener}
        *
        * @param listener The listener to be registerd
        */
        public void RegisterSessionChangeListener(ISessionChangeListener listener) {
            sessionChangeListeners.Add(listener);
        }

        /**
        * Unregisters a {@link SessionChangeListener}
        *
        * @param listener The listener to be unregisterd
        */
        public void UnregisterSessionChangeListener(ISessionChangeListener listener) {
            sessionChangeListeners.Remove(listener);
        }

    }
}