using Android.Util;
using rtpmidi;
using rtpmidi.messages;
using System;
using System.Collections.Generic;
using System.Threading;

namespace rtpmidi.control
{ 


    /**
    * The control server handles MIDI invitation requests. If there are sessions available in the session server it will
    * accept invitations and reject otherwise.
    */

    public class RtpMidiControlServer:Thread,IRtpMidiCommandListener {

        private static int SOCKET_TIMEOUT = 1000;

        private enum State {
            ACCEPT_INVITATIONS, FULL
        }

        private static int RECEIVE_BUFFER_LENGTH = 1024;
        private static string THREAD_SUFFIX = "ControlThread";
        private int port;
        
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
        RtpMidiControlServer(RtpMidiCommandHandler handler, string name, int port):base(name + THREAD_SUFFIX)
        {
            this.handler = handler;
            this.port = port;
            this.name = name;
            handler.registerListener(this);
        }


        public void Start()
        {
        private object _locker = new object();
            lock (_locker)
            {
                try
                {
                    socket = initDatagramSocket();
                    socket.setSoTimeout(SOCKET_TIMEOUT);

                    String hostName;
                    try
                    {
                        hostName = InetAddress.getLocalHost().getHostName();
                    }
                    catch (final UnknownHostException e) 
                    {
                        hostName = "";
                    }
                    Initialize(hostName);
                }
                catch (final SocketException e) 
                {
                    throw new AppleMidiControlServerRuntimeException("DatagramSocket cannot be opened", e);
                }
                base.Start();
                Log.Debug("RtpMidi","MIDI control server started");
            }
        
        }

        private void Initialize(string hostName) {
            Ssrc = CreateSsrc(hostName);
        }

        private int CreateSsrc(final String hostName) {
            try
            {
                final MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(String.valueOf(new Date().getTime()).getBytes());
                md.update(String.valueOf(System.identityHashCode(this)).getBytes());
                md.update(Paths.get("").toAbsolutePath().normalize().toString().getBytes());
                md.update(hostName.getBytes());
                final byte[] md5 = md.digest();
                int ssrc = 0;
                final ByteBuffer byteBuffer = ByteBuffer.wrap(md5);
                for (int i = 0; i < 3; i++)
                {
                    ssrc ^= byteBuffer.getInt();
                }
                return ssrc;
            }
            catch (final NoSuchAlgorithmException e) 
            {
                throw new RuntimeException("Could not get MD5 algorithm", e);
            }
        }

        protected DatagramSocket initDatagramSocket()
        {
            return new DatagramSocket(port);
        }

        /**
        * Sends a end session message to all servers and stops the main loop.
        */
        public void StopServer()
        {
            for (final AppleMidiServer server : acceptedServers) 
            {
                try
                {
                    log.info("Sending end session to {}", server);
                    send(new AppleMidiEndSession(2, getNewInitiatorToken(), ssrc), server);
                }
                catch (final IOException e) 
                {
                    log.info("Error closing session with server: {}", server, e);
                }
            }
            running = false;
            acceptedServers.clear();
            log.debug("MIDI control server stopped");
        }

        int GetNewInitiatorToken()
        {
            return new Random().Next();
        }

    
        public override void Run()
        {
            while (running)
            {
                try
                {
                    final byte[] receiveData = new byte[RECEIVE_BUFFER_LENGTH];

                    final DatagramPacket incomingPacket = initDatagramPacket(receiveData);
                    socket.receive(incomingPacket);
                    handler.handle(receiveData, new AppleMidiServer(incomingPacket.getAddress(), incomingPacket.getPort()));
                }
                catch (final SocketTimeoutException ignored) 
                {
                } 
                catch (final IOException e) 
                {
                    log.error("IOException while receiving", e);
                }
            }
            socket.close();
        }

        DatagramPacket initDatagramPacket(final byte[] receiveData)
        {
            return new DatagramPacket(receiveData, receiveData.length);
        }



    public void OnMidiInvitation(RtpMidiInvitationRequest invitation, RtpMidiServer rtpMidiServer)
    {
            Log.Info("RtpMidi","MIDI invitation from: {}", appleMidiServer);
            bool contains = acceptedServers.contains(appleMidiServer);
            if (contains)
            {
                log.info("Server {} was still in accepted servers list. Removing old entry.", appleMidiServer);
                OnEndSession(new AppleMidiEndSession(invitation.getProtocolVersion(), invitation.getInitiatorToken(),
                    invitation.getSsrc()), appleMidiServer);
            }
            if (getServerState() == State.ACCEPT_INVITATIONS) {
                sendMidiInvitationAnswer(appleMidiServer, "accept",
                    new AppleMidiInvitationAccepted(invitation.getProtocolVersion(), invitation.getInitiatorToken(),
                            ssrc, name));
                acceptedServers.add(appleMidiServer);
            }
            else
            {
                sendMidiInvitationAnswer(appleMidiServer, "decline",
                    new AppleMidiInvitationDeclined(invitation.getProtocolVersion(), invitation.getInitiatorToken(),
                            ssrc, name));
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

            socket.Send(new DatagramPacket(invitationAcceptedBytes, invitationAcceptedBytes.Length,
                rtpMidiServer.getInetAddress(), rtpMidiServer.Port));
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