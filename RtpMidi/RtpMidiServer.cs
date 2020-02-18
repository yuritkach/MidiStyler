using Android.Util;
using rtpmidi.control;
using rtpmidi.session;

namespace rtpmidi { 
    /**
    * Main class for the RTP MIDI communication. This class instantiates the {@link RtpMidiControlServer} and the {@link
    * RtpMidiSessionServer}. In order to receive midi messages a {@link RtpMidiSession} should be registerd via {@link
    * #AddRtpMidiSession(RtpMidiSession)}.
    */
    public class RtpMidiServer:ISessionChangeListener {

        private static int DEFAULT_PORT = 50004;
        private static string DEFAULT_NAME = "rtpMIDIXamarinAndroid";
        public int Port { get; protected set; }
        private RtpMidiControlServer controlServer;
        private RtpMidiSessionServer sessionServer;

        /**
        * Creates a {@link RtpMidiServer} with {@link #DEFAULT_NAME} and {@link #DEFAULT_PORT}
        */
        public RtpMidiServer():this(DEFAULT_NAME, DEFAULT_PORT)
        {
        }

        /**
        * Creates a new {@link RtpMidiServer} with the given name and port
        *
        * @param name The name under which the other peers should see this server
        * @param port The control port. A session server will be created on the {@code port + 1}
        */
        public RtpMidiServer(string name, int port) {
            Port = port;
            controlServer = new RtpMidiControlServer(name, port);
            sessionServer = new RtpMidiSessionServer(name, port + 1);
            sessionServer.RegisterSessionChangeListener(this);
            controlServer.RegisterEndSessionListener(sessionServer);
        }

        /**
        * Add a new {@link AppleMidiSession} to this server
        *
        * @param session The session to be added
        */
        public void AddRtpMidiSession(RtpMidiSession session) {
            sessionServer.AddRtpMidiSession(session);
        }

        /**
        * Remove the {@link AppleMidiSession} from this server
        *
        * @param session The session to be removed
        */
        public void RemoveRtpMidiSession(RtpMidiSession session) {
            sessionServer.RemoveRtpMidiSession(session);
        }

    
        public void OnMaxNumberOfSessionsChange(int maxNumberOfSessions) {
            controlServer.SetMaxNumberOfSessions(maxNumberOfSessions);
        }

        /**
        * Starts the control server and the session server
        */
        public void Start() {
            sessionServer.Start();
            controlServer.Start();
            Log.Info("RtpMidi","RtpMidiServer started");
        }

        /**
        * Stops the session server and the control server
        */
        public void Stop() {
            sessionServer.StopServer();
            controlServer.StopServer();
            Log.Info("RtpMidi","RtpMidiServer stopped");
        }

    }
}