package mono.android.support.design.widget;

import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class BottomNavigationView_OnNavigationItemSelectedListenerImplementor implements IGCUserPeer, BottomNavigationView.OnNavigationItemSelectedListener {
    public static final String __md_methods = "n_onNavigationItemSelected:(Landroid/view/MenuItem;)Z:GetOnNavigationItemSelected_Landroid_view_MenuItem_Handler:Android.Support.Design.Widget.BottomNavigationView/IOnNavigationItemSelectedListenerInvoker, Xamarin.Android.Support.Design\n";
    private ArrayList refList;

    private native boolean n_onNavigationItemSelected(MenuItem menuItem);

    static {
        Runtime.register("Android.Support.Design.Widget.BottomNavigationView+IOnNavigationItemSelectedListenerImplementor, Xamarin.Android.Support.Design", BottomNavigationView_OnNavigationItemSelectedListenerImplementor.class, __md_methods);
    }

    public BottomNavigationView_OnNavigationItemSelectedListenerImplementor() {
        if (getClass() == BottomNavigationView_OnNavigationItemSelectedListenerImplementor.class) {
            TypeManager.Activate("Android.Support.Design.Widget.BottomNavigationView+IOnNavigationItemSelectedListenerImplementor, Xamarin.Android.Support.Design", "", this, new Object[0]);
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return n_onNavigationItemSelected(menuItem);
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
