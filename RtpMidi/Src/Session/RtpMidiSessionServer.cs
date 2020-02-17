using Android.Util;
using Java.Lang;
using Java.Util.Concurrent;
using rtpmidi;
using rtpmidi.messages;
using rtpmidi.model;

namespace rtpmidi.session { 


    /**
    * The session server handles MIDI invitation, clock synchronin requests, as well as the MIDI messages. In order to
    * handle MIDI messages a {@link AppleMidiSession} has to be added via {@link #addAppleMidiSession(AppleMidiSession)}.
    * Otherwise all invitation requests are rejected. The session server must be run on a port which is {@code control port
    * + 1}
    */

    public class RtpMidiSessionServer:IRtpMidiCommandListener,IRtpMidiMessageListener, Runnable,IRtpMidiMessageSender
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
        private int port;
        private bool running = true;
        private DatagramSocket socket;
        private Deque<RtpMidiSession> sessions = new ArrayDeque<>();
        private Map<Integer, RtpMidiSessionConnection> currentSessions = new HashMap<>();
        private List<SessionChangeListener> sessionChangeListeners = new ArrayList<>();
        private Thread thread;

        /**
        * @param name The name under which the other peers should see this server
        * @param port The session server port which must be {@code control port + 1}
        */
        public RtpMidiSessionServer(@Nonnull final String name, final int port) {
            this(name, port, Executors.newCachedThreadPool());
        }

        RtpMidiSessionServer(@Nonnull final String name, final int port, final ExecutorService executorService) {
            this.port = port;
            this.ssrc = new Random().nextInt();
            this.name = name;
            this.executorService = executorService;

            midiCommandHandler.registerListener(this);
            midiMessageHandler.registerListener(this);
        }

        Thread createThread(final @Nonnull String name) {
            return new Thread(this, name + "SessionThread");
        }

        public synchronized void start() {
            thread = createThread(name);
            try {
                socket = createSocket();
                socket.setSoTimeout(SOCKET_TIMEOUT);
            } catch (final SocketException e) {
                throw new AppleMidiSessionServerRuntimeException("DatagramSocket cannot be opened", e);
            }
            thread.start();
            log.debug("MIDI session server started");
        }

        DatagramSocket createSocket() throws SocketException {
            return new DatagramSocket(port);
        }

        
        public void Run() {
            while (running) {

                try {
                    final byte[] receiveData = new byte[RECEIVE_BUFFER_LENGTH];
                    final DatagramPacket incomingPacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(incomingPacket);
                    executorService.execute(new Runnable() {

                        @Override
                        public void run() {
                            if (receiveData[0] == AppleMidiCommand.MIDI_COMMAND_HEADER1) {
                                midiCommandHandler.handle(receiveData,
                                    new AppleMidiServer(incomingPacket.getAddress(), incomingPacket.getPort()));
                            } else {
                                midiMessageHandler.handle(receiveData,
                                    new AppleMidiServer(incomingPacket.getAddress(), incomingPacket.getPort()));
                            }
                        }
                    });
                } catch (final SocketTimeoutException ignored) {
                } catch (final IOException e) {
                    log.error("IOException while receiving", e);
                }
            }
            socket.close();
        }

        /**
        * Shutds down all sockets and threads
        */
        public void StopServer() {
            running = false;
            currentSessions.clear();
            sessions.clear();
            executorService.shutdown();
            log.debug("MIDI session server stopped");
        }

        private void Send(RtpMidiCommand midiCommand, RtpMidiServer rtpMidiServer)
        {
            Send(midiCommand.ToByteArray(), rtpMidiServer);
        }

        private void Send(byte[] data, RtpMidiServer rtpMidiServer)
        {
            if (Log.isTraceEnabled()) {
                Log.trace("Sending data {} to server {}", Hex.encodeHexString(data), rtpMidiServer);
            }
            socket.Send(new DatagramPacket(data, data.length, rtpMidiServer.InetAddress, rtpMidiServer.Port));
        }

        
        public void Send(RtpMidiMessage rtpMidiMessage,RtpMidiServer rtpMidiServer)
        { 
            Send(rtpMidiMessage.ToByteArray(), rtpMidiServer);
        }

        
        public void OnMidiInvitation(RtpMidiInvitationRequest invitation, RtpMidiServer appleMidiServer)
        {
            Log.Info("RtpMidi","MIDI invitation from: {}", rtpMidiServer);
            if (GetSessionServerState() == State.ACCEPT_INVITATIONS) {
                SendMidiInvitationAnswer(rtpMidiServer, "accept",
                        new RtpMidiInvitationAccepted(invitation.ProtocolVersion, invitation.InitiatorToken,Ssrc, Name));
                RtpMidiSession rtpMidiSession = sessions.pop();
                RtpMidiSessionConnection connection =
                        new RtpMidiSessionConnection(rtpMidiSession, rtpMidiServer, Ssrc, this);
                rtpMidiSession.SetSender(connection);
                currentSessions.put(invitation.Ssrc, connection);
                notifyMaxNumberOfSessions();
            } else {
                SendMidiInvitationAnswer(rtpMidiServer, "decline",
                        new RtpMidiInvitationDeclined(invitation.ProtocolVersion, invitation.InitiatorToken,Ssrc, Name));
            }
        }

        /**
        * @return {@link State#FULL} if no sessions are available. {@link State#ACCEPT_INVITATIONS} otherwise
        */
        private State GetSessionServerState() {
            return sessions.isEmpty() ? State.FULL : State.ACCEPT_INVITATIONS;
        }

        private void SendMidiInvitationAnswer(RtpMidiServer rtpMidiServer,string type,RtpMidiInvitation midiInvitation) {
            try {
                Log.Info("RtpMidi","Sending invitation {} to: {}", type, rtpMidiServer);
                Send(midiInvitation, rtpMidiServer);
            }
            catch (IOException e) {
                Log.Error("RtpMidi","IOException while sending invitation {}", type, e);
            }
        }

        
        public void OnClockSynchronization(RtpMidiClockSynchronization clockSynchronization, RtpMidiServer rtpMidiServer) {
            if (clockSynchronization.Count == (byte) 0) {
                RtpMidiSessionConnection sessionTuple = currentSessions.get(clockSynchronization.getSsrc());
                long currentTimestamp;
                if (sessionTuple != null) {
                    long sessionTimestamp = sessionTuple.getRtpMidiSession().CurrentTimestamp;
                    if (sessionTimestamp != -1) {
                        currentTimestamp = sessionTimestamp;
                    } else {
                        currentTimestamp = getFallbackTimestamp();
                    }
                } else {
                    currentTimestamp = getFallbackTimestamp();
                }
                Log.Debug("RtpMidi","Answering with timestamp: {}", currentTimestamp);
                RtpMidiClockSynchronization clockSynchronizationAnswer =
                        new RtpMidiClockSynchronization(ssrc, (byte) 1, clockSynchronization.getTimestamp1(),
                                currentTimestamp, 0L);
                try {
                    Send(clockSynchronizationAnswer, rtpMidiServer);
                } catch (IOException e) {
                    Log.Error("RtpMidi","IOException while sending clock synchronization", e);
                }
            } else if (clockSynchronization.getCount() == (byte) 2) {
                long offsetEstimate =
                        (clockSynchronization.getTimestamp3() + clockSynchronization.getTimestamp1()) / 2 -
                                clockSynchronization.getTimestamp2();

                RtpMidiSessionConnection midiServer = currentSessions.get(clockSynchronization.getSsrc());
                if (midiServer != null) {
                    midiServer.getRtpMidiSession().setOffsetEstimate(offsetEstimate);
                }   
            }
        }   

        private long GetFallbackTimestamp()
        {
            return Java.Lang.JavaSystem.CurrentTimeMillis() * 10;
        }

        
        public void OnEndSession(RtpMidiEndSession rtpMidiEndSession, RtpMidiServer rtpMidiServer) {
            Log.Info("RtpMidi","Session end from: {}", rtpMidiServer);
            RtpMidiSessionConnection midiServer = currentSessions.get(rtpMidiEndSession.Ssrc);
            if (midiServer != null) {
                RtpMidiSession RtpMidiSession = midiServer.getRtpMidiSession();
                rtpMidiSession.setSender(null);
                rtpMidiSession.OnEndSession(rtpMidiEndSession, rtpMidiServer);
            }
            RtpMidiSessionConnection sessionTuple = currentSessions.remove(rtpMidiEndSession.Ssrc);
            if (sessionTuple != null) {
                sessions.add(sessionTuple.getRtpMidiSession());
                notifyMaxNumberOfSessions();
            }
        }

        
        public void OnMidiMessage(MidiCommandHeader midiCommandHeader, MidiMessage message, int timestamp) {
            RtpMidiSessionConnection sessionTuple = currentSessions.get(midiCommandHeader.getRtpHeader().Ssrc);
            if (sessionTuple != null) {
                sessionTuple.getRtpMidiSession().onMidiMessage(midiCommandHeader, message, timestamp);
            } else {
                Log.Debug("RtpMidi","Could not find session for ssrc: {}", ssrc);
            }
        }

        /**
        * Add a new {@link AppleMidiSession} to this server
        *
        * @param session The session to be added
        */
        public void AddRtpMidiSession(RtpMidiSession session) {
            sessions.Add(session);
            notifyMaxNumberOfSessions();
        }

        /**
        * Remove the {@link AppleMidiSession} from this server
        *
        * @param session The session to be removed
        */
        public void RemoveRtpMidiSession(RtpMidiSession session) {
            sessions.Remove(session);
            foreach (Map.Entry<Integer, RtpMidiSessionConnection> entry in currentSessions.entrySet()) {
                if (entry.getValue().getRtpMidiSession().equals(session)) {
                    Integer ssrc = entry.getKey();
                    currentSessions.Remove(ssrc);
                    notifyMaxNumberOfSessions();
                }
            }
        }

        /**
        * Informs all {@link SessionChangeListener} about the new number of available sessions
        */
        private void NotifyMaxNumberOfSessions() {
            foreach (SessionChangeListener listener in sessionChangeListeners) {
                listener.OnMaxNumberOfSessionsChange(sessions.Size());
            }
        }

        /**
        * @return The current number of available sessions for receiving MIDI messages
        */
        public int GetNumberOfAvailableSessions() {
            return sessions.Size();
        }

        /**
        * Registers a new {@link SessionChangeListener}
        *
        * @param listener The listener to be registerd
        */
        public void RegisterSessionChangeListener(SessionChangeListener listener) {
            sessionChangeListeners.Add(listener);
        }

        /**
        * Unregisters a {@link SessionChangeListener}
        *
        * @param listener The listener to be unregisterd
        */
        public void UnregisterSessionChangeListener(SessionChangeListener listener) {
            sessionChangeListeners.Remove(listener);
        }

    }
}