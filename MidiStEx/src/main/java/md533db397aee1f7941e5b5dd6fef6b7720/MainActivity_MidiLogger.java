package md533db397aee1f7941e5b5dd6fef6b7720;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MainActivity_MidiLogger extends Handler implements IGCUserPeer {
    public static final String __md_methods = "n_handleMessage:(Landroid/os/Message;)V:GetHandleMessage_Landroid_os_Message_Handler\n";
    private ArrayList refList;

    private native void n_handleMessage(Message message);

    static {
        Runtime.register("MidiStyler.MainActivity+MidiLogger, MidiStyler", MainActivity_MidiLogger.class, __md_methods);
    }

    public MainActivity_MidiLogger() {
        if (getClass() == MainActivity_MidiLogger.class) {
            TypeManager.Activate("MidiStyler.MainActivity+MidiLogger, MidiStyler", "", this, new Object[0]);
        }
    }

    public MainActivity_MidiLogger(Handler.Callback callback) {
        super(callback);
        if (getClass() == MainActivity_MidiLogger.class) {
            TypeManager.Activate("MidiStyler.MainActivity+MidiLogger, MidiStyler", "Android.OS.Handler+ICallback, Mono.Android", this, new Object[]{callback});
        }
    }

    public MainActivity_MidiLogger(Looper looper) {
        super(looper);
        if (getClass() == MainActivity_MidiLogger.class) {
            TypeManager.Activate("MidiStyler.MainActivity+MidiLogger, MidiStyler", "Android.OS.Looper, Mono.Android", this, new Object[]{looper});
        }
    }

    public MainActivity_MidiLogger(Looper looper, Handler.Callback callback) {
        super(looper, callback);
        if (getClass() == MainActivity_MidiLogger.class) {
            TypeManager.Activate("MidiStyler.MainActivity+MidiLogger, MidiStyler", "Android.OS.Looper, Mono.Android:Android.OS.Handler+ICallback, Mono.Android", this, new Object[]{looper, callback});
        }
    }

    public MainActivity_MidiLogger(TextView textView, Activity activity) {
        if (getClass() == MainActivity_MidiLogger.class) {
            TypeManager.Activate("MidiStyler.MainActivity+MidiLogger, MidiStyler", "Android.Widget.TextView, Mono.Android:Android.App.Activity, Mono.Android", this, new Object[]{textView, activity});
        }
    }

    public void handleMessage(Message message) {
        n_handleMessage(message);
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
