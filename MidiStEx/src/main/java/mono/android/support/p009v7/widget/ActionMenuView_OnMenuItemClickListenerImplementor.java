package mono.android.support.p009v7.widget;

import android.support.p003v7.widget.ActionMenuView;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.widget.ActionMenuView_OnMenuItemClickListenerImplementor */
public class ActionMenuView_OnMenuItemClickListenerImplementor implements IGCUserPeer, ActionMenuView.OnMenuItemClickListener {
    public static final String __md_methods = "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.ActionMenuView/IOnMenuItemClickListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
    private ArrayList refList;

    private native boolean n_onMenuItemClick(MenuItem menuItem);

    static {
        Runtime.register("Android.Support.V7.Widget.ActionMenuView+IOnMenuItemClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", ActionMenuView_OnMenuItemClickListenerImplementor.class, __md_methods);
    }

    public ActionMenuView_OnMenuItemClickListenerImplementor() {
        if (getClass() == ActionMenuView_OnMenuItemClickListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.Widget.ActionMenuView+IOnMenuItemClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
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
