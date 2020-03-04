package mono.android.support.p009v7.widget;

import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.widget.MenuItemHoverListener;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.widget.MenuItemHoverListenerImplementor */
public class MenuItemHoverListenerImplementor implements IGCUserPeer, MenuItemHoverListener {
    public static final String __md_methods = "n_onItemHoverEnter:(Landroid/support/v7/view/menu/MenuBuilder;Landroid/view/MenuItem;)V:GetOnItemHoverEnter_Landroid_support_v7_view_menu_MenuBuilder_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.IMenuItemHoverListenerInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onItemHoverExit:(Landroid/support/v7/view/menu/MenuBuilder;Landroid/view/MenuItem;)V:GetOnItemHoverExit_Landroid_support_v7_view_menu_MenuBuilder_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.IMenuItemHoverListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
    private ArrayList refList;

    private native void n_onItemHoverEnter(MenuBuilder menuBuilder, MenuItem menuItem);

    private native void n_onItemHoverExit(MenuBuilder menuBuilder, MenuItem menuItem);

    static {
        Runtime.register("Android.Support.V7.Widget.IMenuItemHoverListenerImplementor, Xamarin.Android.Support.v7.AppCompat", MenuItemHoverListenerImplementor.class, __md_methods);
    }

    public MenuItemHoverListenerImplementor() {
        if (getClass() == MenuItemHoverListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.Widget.IMenuItemHoverListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public void onItemHoverEnter(MenuBuilder menuBuilder, MenuItem menuItem) {
        n_onItemHoverEnter(menuBuilder, menuItem);
    }

    public void onItemHoverExit(MenuBuilder menuBuilder, MenuItem menuItem) {
        n_onItemHoverExit(menuBuilder, menuItem);
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
