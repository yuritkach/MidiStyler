using Java.Net;

namespace rtpmidi.model {

    /**
     * A remote server representation consisting of port and {@link InetAddress}
     */

    public class RtpMidiServer {
        private InetAddress inetAddress;
        private int port;
    }
}