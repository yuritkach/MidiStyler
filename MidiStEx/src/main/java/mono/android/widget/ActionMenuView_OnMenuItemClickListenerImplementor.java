package mono.android.widget;

import android.view.MenuItem;
import android.widget.ActionMenuView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ActionMenuView_OnMenuItemClickListenerImplementor implements IGCUserPeer, ActionMenuView.OnMenuItemClickListener {
    public static final String __md_methods = "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Widget.ActionMenuView/IOnMenuItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native boolean n_onMenuItemClick(MenuItem menuItem);

    static {
        Runtime.register("Android.Widget.ActionMenuView+IOnMenuItemClickListenerImplementor, Mono.Android", ActionMenuView_OnMenuItemClickListenerImplementor.class, __md_methods);
    }

    public ActionMenuView_OnMenuItemClickListenerImplementor() {
        if (getClass() == ActionMenuView_OnMenuItemClickListenerImplementor.class) {
            TypeManager.Activate("Android.Widget.ActionMenuView+IOnMenuItemClickListenerImplementor, Mono.Android", "", this, new Object[0]);
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
