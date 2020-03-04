package mono.android.support.p007v4.app;

import android.support.p000v4.app.SharedElementCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.app.SharedElementCallback_OnSharedElementsReadyListenerImplementor */
public class SharedElementCallback_OnSharedElementsReadyListenerImplementor implements IGCUserPeer, SharedElementCallback.OnSharedElementsReadyListener {
    public static final String __md_methods = "n_onSharedElementsReady:()V:GetOnSharedElementsReadyHandler:Android.Support.V4.App.SharedElementCallback/IOnSharedElementsReadyListenerInvoker, Xamarin.Android.Support.Compat\n";
    private ArrayList refList;

    private native void n_onSharedElementsReady();

    static {
        Runtime.register("Android.Support.V4.App.SharedElementCallback+IOnSharedElementsReadyListenerImplementor, Xamarin.Android.Support.Compat", SharedElementCallback_OnSharedElementsReadyListenerImplementor.class, __md_methods);
    }

    public SharedElementCallback_OnSharedElementsReadyListenerImplementor() {
        if (getClass() == SharedElementCallback_OnSharedElementsReadyListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.App.SharedElementCallback+IOnSharedElementsReadyListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]);
        }
    }

    public void onSharedElementsReady() {
        n_onSharedElementsReady();
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
