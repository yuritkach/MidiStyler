using Java.Lang;

namespace rtpmidi.error { 

public class RtpMidiSessionServerRuntimeException:RuntimeException {

    public RtpMidiSessionServerRuntimeException():base() { }

    public RtpMidiSessionServerRuntimeException(string message):base(message) { }

    public RtpMidiSessionServerRuntimeException(string message,Throwable cause):base(message,cause) { }

    public RtpMidiSessionServerRuntimeException(Throwable cause):base(cause) { }

    }
}
