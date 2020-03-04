package mono.android.support.p007v4.view;

import android.support.p000v4.view.PagerAdapter;
import android.support.p000v4.view.ViewPager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.view.ViewPager_OnAdapterChangeListenerImplementor */
public class ViewPager_OnAdapterChangeListenerImplementor implements IGCUserPeer, ViewPager.OnAdapterChangeListener {
    public static final String __md_methods = "n_onAdapterChanged:(Landroid/support/v4/view/ViewPager;Landroid/support/v4/view/PagerAdapter;Landroid/support/v4/view/PagerAdapter;)V:GetOnAdapterChanged_Landroid_support_v4_view_ViewPager_Landroid_support_v4_view_PagerAdapter_Landroid_support_v4_view_PagerAdapter_Handler:Android.Support.V4.View.ViewPager/IOnAdapterChangeListenerInvoker, Xamarin.Android.Support.Core.UI\n";
    private ArrayList refList;

    private native void n_onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);

    static {
        Runtime.register("Android.Support.V4.View.ViewPager+IOnAdapterChangeListenerImplementor, Xamarin.Android.Support.Core.UI", ViewPager_OnAdapterChangeListenerImplementor.class, __md_methods);
    }

    public ViewPager_OnAdapterChangeListenerImplementor() {
        if (getClass() == ViewPager_OnAdapterChangeListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.View.ViewPager+IOnAdapterChangeListenerImplementor, Xamarin.Android.Support.Core.UI", "", this, new Object[0]);
        }
    }

    public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        n_onAdapterChanged(viewPager, pagerAdapter, pagerAdapter2);
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
