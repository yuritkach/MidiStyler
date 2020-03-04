package mono.android.support.design.widget;

import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.design.widget.BottomNavigationView_OnNavigationItemReselectedListenerImplementor */
public class C0521xe7b28bc3 implements IGCUserPeer, BottomNavigationView.OnNavigationItemReselectedListener {
    public static final String __md_methods = "n_onNavigationItemReselected:(Landroid/view/MenuItem;)V:GetOnNavigationItemReselected_Landroid_view_MenuItem_Handler:Android.Support.Design.Widget.BottomNavigationView/IOnNavigationItemReselectedListenerInvoker, Xamarin.Android.Support.Design\n";
    private ArrayList refList;

    private native void n_onNavigationItemReselected(MenuItem menuItem);

    static {
        Runtime.register("Android.Support.Design.Widget.BottomNavigationView+IOnNavigationItemReselectedListenerImplementor, Xamarin.Android.Support.Design", C0521xe7b28bc3.class, __md_methods);
    }

    public C0521xe7b28bc3() {
        if (getClass() == C0521xe7b28bc3.class) {
            TypeManager.Activate("Android.Support.Design.Widget.BottomNavigationView+IOnNavigationItemReselectedListenerImplementor, Xamarin.Android.Support.Design", "", this, new Object[0]);
        }
    }

    public void onNavigationItemReselected(MenuItem menuItem) {
        n_onNavigationItemReselected(menuItem);
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
