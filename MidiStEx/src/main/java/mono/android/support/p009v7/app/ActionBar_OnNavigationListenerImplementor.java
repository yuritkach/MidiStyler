package mono.android.support.p009v7.app;

import android.support.p003v7.app.ActionBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.app.ActionBar_OnNavigationListenerImplementor */
public class ActionBar_OnNavigationListenerImplementor implements IGCUserPeer, ActionBar.OnNavigationListener {
    public static final String __md_methods = "n_onNavigationItemSelected:(IJ)Z:GetOnNavigationItemSelected_IJHandler:Android.Support.V7.App.ActionBar/IOnNavigationListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
    private ArrayList refList;

    private native boolean n_onNavigationItemSelected(int i, long j);

    static {
        Runtime.register("Android.Support.V7.App.ActionBar+IOnNavigationListenerImplementor, Xamarin.Android.Support.v7.AppCompat", ActionBar_OnNavigationListenerImplementor.class, __md_methods);
    }

    public ActionBar_OnNavigationListenerImplementor() {
        if (getClass() == ActionBar_OnNavigationListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.App.ActionBar+IOnNavigationListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public boolean onNavigationItemSelected(int i, long j) {
        return n_onNavigationItemSelected(i, j);
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
