namespace midi.internal_events {

    public class StreamDisconnectEvent {

        public int stream_ssrc;
        public int initiator_token;
        public Bundle rinfo;

    public StreamDisconnectEvent(int ssrc) {
            stream_ssrc = ssrc;
            initiator_token = 0;
            rinfo = null;
        }
        public StreamDisconnectEvent(int ssrc, int token) {
            stream_ssrc = ssrc;
            initiator_token = token;
            rinfo = null;
        }
        public StreamDisconnectEvent(int ssrc, Bundle b) {
            stream_ssrc = ssrc;
            initiator_token = 0;
            rinfo = b;
        }
        public StreamDisconnectEvent(int ssrc, int token, Bundle b) {
            stream_ssrc = ssrc;
            initiator_token = token;
            rinfo = b;
        }

    }
}