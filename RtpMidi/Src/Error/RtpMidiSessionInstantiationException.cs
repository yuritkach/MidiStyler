using Java.Lang;

namespace rtpmidi.error { 

    public class RtpMidiSessionInstantiationException:RuntimeException {

        public RtpMidiSessionInstantiationException():base() { }

        public RtpMidiSessionInstantiationException(string message):base(message) { }

        public RtpMidiSessionInstantiationException(string message, Throwable cause):base(message,cause) { }

        public RtpMidiSessionInstantiationException(Throwable cause):base(cause) { }

    }
}