package mono.android.support.p007v4.app;

import android.support.p000v4.app.FragmentManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.app.FragmentManager_OnBackStackChangedListenerImplementor */
public class FragmentManager_OnBackStackChangedListenerImplementor implements IGCUserPeer, FragmentManager.OnBackStackChangedListener {
    public static final String __md_methods = "n_onBackStackChanged:()V:GetOnBackStackChangedHandler:Android.Support.V4.App.FragmentManager/IOnBackStackChangedListenerInvoker, Xamarin.Android.Support.Fragment\n";
    private ArrayList refList;

    private native void n_onBackStackChanged();

    static {
        Runtime.register("Android.Support.V4.App.FragmentManager+IOnBackStackChangedListenerImplementor, Xamarin.Android.Support.Fragment", FragmentManager_OnBackStackChangedListenerImplementor.class, __md_methods);
    }

    public FragmentManager_OnBackStackChangedListenerImplementor() {
        if (getClass() == FragmentManager_OnBackStackChangedListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.App.FragmentManager+IOnBackStackChangedListenerImplementor, Xamarin.Android.Support.Fragment", "", this, new Object[0]);
        }
    }

    public void onBackStackChanged() {
        n_onBackStackChanged();
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
