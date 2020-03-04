package mono.android.support.p007v4.view;

import android.support.p000v4.view.ActionProvider;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.view.ActionProvider_VisibilityListenerImplementor */
public class ActionProvider_VisibilityListenerImplementor implements IGCUserPeer, ActionProvider.VisibilityListener {
    public static final String __md_methods = "n_onActionProviderVisibilityChanged:(Z)V:GetOnActionProviderVisibilityChanged_ZHandler:Android.Support.V4.View.ActionProvider/IVisibilityListenerInvoker, Xamarin.Android.Support.Compat\n";
    private ArrayList refList;

    private native void n_onActionProviderVisibilityChanged(boolean z);

    static {
        Runtime.register("Android.Support.V4.View.ActionProvider+IVisibilityListenerImplementor, Xamarin.Android.Support.Compat", ActionProvider_VisibilityListenerImplementor.class, __md_methods);
    }

    public ActionProvider_VisibilityListenerImplementor() {
        if (getClass() == ActionProvider_VisibilityListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.View.ActionProvider+IVisibilityListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]);
        }
    }

    public void onActionProviderVisibilityChanged(boolean z) {
        n_onActionProviderVisibilityChanged(z);
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
