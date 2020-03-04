package mono.android.support.p009v7.widget;

import android.support.p003v7.widget.Toolbar;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.widget.Toolbar_OnMenuItemClickListenerImplementor */
public class Toolbar_OnMenuItemClickListenerImplementor implements IGCUserPeer, Toolbar.OnMenuItemClickListener {
    public static final String __md_methods = "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.Toolbar/IOnMenuItemClickListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
    private ArrayList refList;

    private native boolean n_onMenuItemClick(MenuItem menuItem);

    static {
        Runtime.register("Android.Support.V7.Widget.Toolbar+IOnMenuItemClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", Toolbar_OnMenuItemClickListenerImplementor.class, __md_methods);
    }

    public Toolbar_OnMenuItemClickListenerImplementor() {
        if (getClass() == Toolbar_OnMenuItemClickListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.Widget.Toolbar+IOnMenuItemClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return n_onMenuItemClick(menuItem);
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
