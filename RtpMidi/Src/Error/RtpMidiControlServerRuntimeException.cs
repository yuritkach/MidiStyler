using Java.Lang;

namespace rtpmidi.error { 

    public class RtpMidiControlServerRuntimeException:RuntimeException {

        public RtpMidiControlServerRuntimeException():base() { }

        public RtpMidiControlServerRuntimeException(string message):base(message) { }

        public RtpMidiControlServerRuntimeException(string message, Throwable cause):base(message,cause) { }

        public RtpMidiControlServerRuntimeException(Throwable cause):base(cause) { }
    }
}