package md513df95f6196c8c632261fcf18a016441;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RtpMidiSessionServer extends BaseRunable implements IGCUserPeer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("rtpmidi.session.RtpMidiSessionServer, RtpMidi", RtpMidiSessionServer.class, __md_methods);
    }

    public RtpMidiSessionServer() {
        if (getClass() == RtpMidiSessionServer.class) {
            TypeManager.Activate("rtpmidi.session.RtpMidiSessionServer, RtpMidi", "", this, new Object[0]);
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
