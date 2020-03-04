package md533db397aee1f7941e5b5dd6fef6b7720;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MidiStylerService extends Service implements IGCUserPeer {
    public static final String __md_methods = "n_onBind:(Landroid/content/Intent;)Landroid/os/IBinder;:GetOnBind_Landroid_content_Intent_Handler\n";
    private ArrayList refList;

    private native IBinder n_onBind(Intent intent);

    static {
        Runtime.register("MidiStyler.MidiStylerService, MidiStyler", MidiStylerService.class, __md_methods);
    }

    public MidiStylerService() {
        if (getClass() == MidiStylerService.class) {
            TypeManager.Activate("MidiStyler.MidiStylerService, MidiStyler", "", this, new Object[0]);
        }
    }

    public IBinder onBind(Intent intent) {
        return n_onBind(intent);
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
