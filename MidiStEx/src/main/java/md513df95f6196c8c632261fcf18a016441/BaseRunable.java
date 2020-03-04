package md513df95f6196c8c632261fcf18a016441;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class BaseRunable implements IGCUserPeer, Runnable {
    public static final String __md_methods = "n_run:()V:GetRunHandler:Java.Lang.IRunnableInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_run();

    static {
        Runtime.register("rtpmidi.session.BaseRunable, RtpMidi", BaseRunable.class, __md_methods);
    }

    public BaseRunable() {
        if (getClass() == BaseRunable.class) {
            TypeManager.Activate("rtpmidi.session.BaseRunable, RtpMidi", "", this, new Object[0]);
        }
    }

    public void run() {
        n_run();
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
