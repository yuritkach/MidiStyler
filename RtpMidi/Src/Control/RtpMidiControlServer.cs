using Android.Util;
using Java.Lang;
using Java.Net;
using Java.Nio;
using Java.Nio.FileNio;
using Java.Security;
using Java.Util;
using rtpmidi;
using rtpmidi.error;
using rtpmidi.handler;
using rtpmidi.messages;
using System;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization;
using System.Text;
using System.Threading;

namespace rtpmidi.control
{


    /**
    * The control server handles MIDI invitation requests. If there are sessions available in the session server it will
    * accept invitations and reject otherwise.
    */

    public abstract class BaseThread
    {
        private System.Threading.Thread _thread;

        protected BaseThread()
        {
            _thread = new System.Threading.Thread(new ThreadStart(this.Start));
        }

        // Thread methods / properties
        public virtual void Start() => _thread.Start();
        public virtual void Join() => _thread.Join();
        public virtual bool IsAlive => _thread.IsAlive;

        // Override in base class
       //public abstract void RunThread();
    }

    public class RtpMidiControlServer:BaseThread,IRtpMidiCommandListener {

        private static int SOCKET_TIMEOUT = 1000;

        private enum State { ACCEPT_INVITATIONS, FULL }

        private static int RECEIVE_BUFFER_LENGTH = 1024;
        private static string THREAD_SUFFIX = "ControlThread";
        public int Port { get; protected set; }
        
        private int maxNumberOfSessions;
        private bool running = true;
        
        public int Ssrc { get; protected set; }
        public string Name { get; protected set; }
        private RtpMidiCommandHandler handler;
        private DatagramSocket socket;
        private List<RtpMidiServer> acceptedServers = new List<RtpMidiServer>();
        private List<IEndSessionListener> endSessionListeners = new List<IEndSessionListener>();

        /**
        * @param name The name under which the other peers should see this server
        * @param port The control port
        */
        public RtpMidiControlServer(string name, int port):this(new RtpMidiCommandHandler(), name, port)
        {
        }

        /**
        * @param handler {@link AppleMidiCommandHandler} to handle incoming data
        * @param name    The name under which the other peers should see this server
        * @param port    The control port
        */
        RtpMidiControlServer(RtpMidiCommandHandler handler, string name, int port)
        {
            this.handler = handler;
            Port = port;
            Name = name;
            handler.RegisterListener(this);
        }


        public override void Start()
        {
            object _locker = new object();
            lock (_locker)
            {
                try
                {
                    socket = InitDatagramSocket();
                    socket.SoTimeout = SOCKET_TIMEOUT;

                    string hostName;
                    try
                    {
                        hostName = InetAddress.LocalHost.HostName;
                    }
                    catch (UnknownHostException e) 
                    {
                        hostName = "";
                    }
                    Initialize(hostName);
                }
                catch (SocketException e) 
                {
                    throw new RtpMidiControlServerRuntimeException("DatagramSocket cannot be opened", e);
                }
                base.Start();
                Log.Debug("RtpMidi","MIDI control server started");
            }
        
        }

        private void Initialize(string hostName)
        {
            Ssrc = CreateSsrc(hostName);
        }

        private int CreateSsrc(string hostName)
        {
            try
            {
                ObjectIDGenerator obGen = new ObjectIDGenerator();

                MessageDigest md = MessageDigest.GetInstance("MD5");
                md.Update(Encoding.GetEncoding("UTF-8").GetBytes(Convert.ToString(new Date().Time).ToCharArray()));
                md.Update(Encoding.GetEncoding("UTF-8").GetBytes(Convert.ToString(obGen.ToString().ToCharArray())));
                md.Update(Encoding.GetEncoding("UTF-8").GetBytes(Paths.Get("").ToAbsolutePath().Normalize().ToString().ToCharArray()));
                md.Update(Encoding.GetEncoding("UTF-8").GetBytes(hostName.ToCharArray()));
                byte[] md5 = md.Digest();
                int ssrc = 0;
                ByteBuffer byteBuffer = ByteBuffer.Wrap(md5);
                for (int i = 0; i < 3; i++)
                {
                    ssrc ^= byteBuffer.Int;
                }
                return ssrc;
            }
            catch (NoSuchAlgorithmException e) 
            {
                throw new RuntimeException("Could not get MD5 algorithm", e);
            }
        }

        protected DatagramSocket InitDatagramSocket()
        {
            return new DatagramSocket(Port);
        }

        /**
        * Sends a end session message to all servers and stops the main loop.
        */
        public void StopServer()
        {
            foreach (RtpMidiServer server in acceptedServers) 
            {
                try
                {
                    Log.Info("RtpMidi","Sending end session to {}", server);
                    Send(new RtpMidiEndSession(2, GetNewInitiatorToken(), Ssrc), server);
                }
                catch (IOException e) 
                {
                    Log.Info("RtpMidi","Error closing session with server: {}", server, e);
                }
            }
            running = false;
            acceptedServers.Clear();
            Log.Debug("RtpMidi","MIDI control server stopped");
        }

        int GetNewInitiatorToken()
        {
            return new System.Random().Next();
        }

    
        public void Run()
        {
            while (running)
            {
                try
                {
                    byte[] receiveData = new byte[RECEIVE_BUFFER_LENGTH];

                    DatagramPacket incomingPacket = InitDatagramPacket(receiveData);
                    socket.Receive(incomingPacket);
                    handler.handle(receiveData, new RtpMidiServer(incomingPacket.Address.HostAddress, incomingPacket.Port));
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

        DatagramPacket InitDatagramPacket(byte[] receiveData)
        {
            return new DatagramPacket(receiveData, receiveData.Length);
        }



        public void OnMidiInvitation(RtpMidiInvitationRequest invitation, RtpMidiServer rtpMidiServer)
        {
            Log.Info("RtpMidi","MIDI invitation from: {}", rtpMidiServer);
            bool contains = acceptedServers.Contains(rtpMidiServer);
            if (contains)
            {
                Log.Info("RtpMidi","Server {} was still in accepted servers list. Removing old entry.", rtpMidiServer);
                OnEndSession(new RtpMidiEndSession(invitation.ProtocolVersion, invitation.InitiatorToken,invitation.Ssrc), rtpMidiServer);
            }
            if (getServerState() == State.ACCEPT_INVITATIONS) {
                SendMidiInvitationAnswer(rtpMidiServer, "accept",
                    new RtpMidiInvitationAccepted(invitation.ProtocolVersion, invitation.InitiatorToken, Ssrc, Name));
                acceptedServers.Add(rtpMidiServer);
            }
            else
            {
                SendMidiInvitationAnswer(rtpMidiServer, "decline",
                    new RtpMidiInvitationDeclined(invitation.ProtocolVersion, invitation.InitiatorToken, Ssrc, Name));
            }

        }

        private void SendMidiInvitationAnswer(RtpMidiServer rtpMidiServer, string type, RtpMidiInvitation midiInvitation)
        {
            try
            {
                Log.Info("RtpMidi","Sending invitation {} to: {}", type, rtpMidiServer);
                Send(midiInvitation, rtpMidiServer);
            }
            catch (IOException e) 
            {
                Log.Error("RtpMidi","IOException while sending invitation {}", type, e);
            }
        }

        private void Send(RtpMidiCommand midiCommand, RtpMidiServer rtpMidiServer)
        {
            byte[] invitationAcceptedBytes = midiCommand.ToByteArray();

            socket.Send(new DatagramPacket(invitationAcceptedBytes, invitationAcceptedBytes.Length,rtpMidiServer.InetAddress, rtpMidiServer.Port));
        }

    
        public void OnClockSynchronization(RtpMidiClockSynchronization clockSynchronization,RtpMidiServer rtpMidiServer)
        {
        }

        private State getServerState()
        {
            return acceptedServers.size() < maxNumberOfSessions ? State.ACCEPT_INVITATIONS : State.FULL;
        }

    
        public override void OnEndSession(RtpMidiEndSession rtpMidiEndSession, RtpMidiServer rtpMidiServer)
        {
            Log.Info("RtpMidi","Session ended with: {}", rtpMidiServer);
            acceptedServers.Remove(rtpMidiServer);
            for (final EndSessionListener listener : endSessionListeners)
            {
                listener.onEndSession(appleMidiEndSession, appleMidiServer);
            }
        }

        /**
        * Registers a new {@link EndSessionListener}
        *
        * @param listener The listener to be registerd
        */
        public void RegisterEndSessionListener(EndSessionListener listener)
        {
            endSessionListeners.add(listener);
        }

        /**
        * Unregisters a {@link EndSessionListener}
        *
        * @param listener The listener to be unregisterd
        */
        public void UnRegisterEndSessionListener(EndSessionListener listener)
        {
            endSessionListeners.remove(listener);
        }

    }
}