package mono.android.view;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnGlobalLayoutListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnGlobalLayoutListener {
    public static final String __md_methods = "n_onGlobalLayout:()V:GetOnGlobalLayoutHandler:Android.Views.ViewTreeObserver/IOnGlobalLayoutListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onGlobalLayout();

    static {
        Runtime.register("Android.Views.ViewTreeObserver+IOnGlobalLayoutListenerImplementor, Mono.Android", ViewTreeObserver_OnGlobalLayoutListenerImplementor.class, __md_methods);
    }

    public ViewTreeObserver_OnGlobalLayoutListenerImplementor() {
        if (getClass() == ViewTreeObserver_OnGlobalLayoutListenerImplementor.class) {
            TypeManager.Activate("Android.Views.ViewTreeObserver+IOnGlobalLayoutListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onGlobalLayout() {
        n_onGlobalLayout();
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
