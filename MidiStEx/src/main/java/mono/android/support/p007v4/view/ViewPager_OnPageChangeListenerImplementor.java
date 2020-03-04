package mono.android.support.p007v4.view;

import android.support.p000v4.view.ViewPager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.view.ViewPager_OnPageChangeListenerImplementor */
public class ViewPager_OnPageChangeListenerImplementor implements IGCUserPeer, ViewPager.OnPageChangeListener {
    public static final String __md_methods = "n_onPageScrollStateChanged:(I)V:GetOnPageScrollStateChanged_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPageScrolled:(IFI)V:GetOnPageScrolled_IFIHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPageSelected:(I)V:GetOnPageSelected_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Xamarin.Android.Support.Core.UI\n";
    private ArrayList refList;

    private native void n_onPageScrollStateChanged(int i);

    private native void n_onPageScrolled(int i, float f, int i2);

    private native void n_onPageSelected(int i);

    static {
        Runtime.register("Android.Support.V4.View.ViewPager+IOnPageChangeListenerImplementor, Xamarin.Android.Support.Core.UI", ViewPager_OnPageChangeListenerImplementor.class, __md_methods);
    }

    public ViewPager_OnPageChangeListenerImplementor() {
        if (getClass() == ViewPager_OnPageChangeListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.View.ViewPager+IOnPageChangeListenerImplementor, Xamarin.Android.Support.Core.UI", "", this, new Object[0]);
        }
    }

    public void onPageScrollStateChanged(int i) {
        n_onPageScrollStateChanged(i);
    }

    public void onPageScrolled(int i, float f, int i2) {
        n_onPageScrolled(i, f, i2);
    }

    public void onPageSelected(int i) {
        n_onPageSelected(i);
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
