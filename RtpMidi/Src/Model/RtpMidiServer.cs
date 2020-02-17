using Java.Net;

namespace rtpmidi.model {

    /**
     * A remote server representation consisting of port and {@link InetAddress}
     */

    public class RtpMidiServer {
        public InetAddress InetAddress { get; protected set; } 
        public int Port { get; protected set; }
    }
}