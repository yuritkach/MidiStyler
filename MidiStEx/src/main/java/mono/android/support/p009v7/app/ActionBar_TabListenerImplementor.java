package mono.android.support.p009v7.app;

import android.support.p000v4.app.FragmentTransaction;
import android.support.p003v7.app.ActionBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.app.ActionBar_TabListenerImplementor */
public class ActionBar_TabListenerImplementor implements IGCUserPeer, ActionBar.TabListener {
    public static final String __md_methods = "n_onTabReselected:(Landroid/support/v7/app/ActionBar$Tab;Landroid/support/v4/app/FragmentTransaction;)V:GetOnTabReselected_Landroid_support_v7_app_ActionBar_Tab_Landroid_support_v4_app_FragmentTransaction_Handler:Android.Support.V7.App.ActionBar/ITabListenerInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onTabSelected:(Landroid/support/v7/app/ActionBar$Tab;Landroid/support/v4/app/FragmentTransaction;)V:GetOnTabSelected_Landroid_support_v7_app_ActionBar_Tab_Landroid_support_v4_app_FragmentTransaction_Handler:Android.Support.V7.App.ActionBar/ITabListenerInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onTabUnselected:(Landroid/support/v7/app/ActionBar$Tab;Landroid/support/v4/app/FragmentTransaction;)V:GetOnTabUnselected_Landroid_support_v7_app_ActionBar_Tab_Landroid_support_v4_app_FragmentTransaction_Handler:Android.Support.V7.App.ActionBar/ITabListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
    private ArrayList refList;

    private native void n_onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction);

    private native void n_onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction);

    private native void n_onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction);

    static {
        Runtime.register("Android.Support.V7.App.ActionBar+ITabListenerImplementor, Xamarin.Android.Support.v7.AppCompat", ActionBar_TabListenerImplementor.class, __md_methods);
    }

    public ActionBar_TabListenerImplementor() {
        if (getClass() == ActionBar_TabListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.App.ActionBar+ITabListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        n_onTabReselected(tab, fragmentTransaction);
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        n_onTabSelected(tab, fragmentTransaction);
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        n_onTabUnselected(tab, fragmentTransaction);
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
