package md55cd2e4d03e788f7ac8ec21ef6bb1da38;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RtpMidiSessionInstantiationException extends RuntimeException implements IGCUserPeer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("rtpmidi.error.RtpMidiSessionInstantiationException, RtpMidi", RtpMidiSessionInstantiationException.class, __md_methods);
    }

    public RtpMidiSessionInstantiationException() {
        if (getClass() == RtpMidiSessionInstantiationException.class) {
            TypeManager.Activate("rtpmidi.error.RtpMidiSessionInstantiationException, RtpMidi", "", this, new Object[0]);
        }
    }

    public RtpMidiSessionInstantiationException(String str) {
        super(str);
        if (getClass() == RtpMidiSessionInstantiationException.class) {
            TypeManager.Activate("rtpmidi.error.RtpMidiSessionInstantiationException, RtpMidi", "System.String, mscorlib", this, new Object[]{str});
        }
    }

    public RtpMidiSessionInstantiationException(String str, Throwable th) {
        super(str, th);
        if (getClass() == RtpMidiSessionInstantiationException.class) {
            TypeManager.Activate("rtpmidi.error.RtpMidiSessionInstantiationException, RtpMidi", "System.String, mscorlib:Java.Lang.Throwable, Mono.Android", this, new Object[]{str, th});
        }
    }

    public RtpMidiSessionInstantiationException(Throwable th) {
        super(th);
        if (getClass() == RtpMidiSessionInstantiationException.class) {
            TypeManager.Activate("rtpmidi.error.RtpMidiSessionInstantiationException, RtpMidi", "Java.Lang.Throwable, Mono.Android", this, new Object[]{th});
        }
    }

    public void monodroidAddReference(Object obj) {
        if (this.refList == null) {
            this.refList = new ArrayList();
        }
        this.refList.add(obj);
    }

    public void monodroidClearReferences() {
        if (this.refList != null) {
            this.refList.clear();
        }
    }
}
