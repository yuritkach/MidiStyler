package md53ff248140ab0f1bd7d9a921360ed480b;

import android.os.AsyncTask;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GetLocalHostNameAsyncTask extends AsyncTask implements IGCUserPeer {
    public static final String __md_methods = "n_doInBackground:([Ljava/lang/Object;)Ljava/lang/Object;:GetDoInBackground_arrayLjava_lang_Object_Handler\n";
    private ArrayList refList;

    private native Object n_doInBackground(Object[] objArr);

    static {
        Runtime.register("rtpmidi.control.GetLocalHostNameAsyncTask, RtpMidi", GetLocalHostNameAsyncTask.class, __md_methods);
    }

    public GetLocalHostNameAsyncTask() {
        if (getClass() == GetLocalHostNameAsyncTask.class) {
            TypeManager.Activate("rtpmidi.control.GetLocalHostNameAsyncTask, RtpMidi", "", this, new Object[0]);
        }
    }

    public Object doInBackground(Object[] objArr) {
        return n_doInBackground(objArr);
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
