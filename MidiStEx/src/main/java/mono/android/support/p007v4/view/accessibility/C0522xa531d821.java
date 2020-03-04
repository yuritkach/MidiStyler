package mono.android.support.p007v4.view.accessibility;

import android.support.p000v4.view.accessibility.AccessibilityManagerCompat;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.view.accessibility.AccessibilityManagerCompat_AccessibilityStateChangeListenerImplementor */
public class C0522xa531d821 implements IGCUserPeer, AccessibilityManagerCompat.AccessibilityStateChangeListener {
    public static final String __md_methods = "n_onAccessibilityStateChanged:(Z)V:GetOnAccessibilityStateChanged_ZHandler:Android.Support.V4.View.Accessibility.AccessibilityManagerCompat/IAccessibilityStateChangeListenerInvoker, Xamarin.Android.Support.Compat\n";
    private ArrayList refList;

    private native void n_onAccessibilityStateChanged(boolean z);

    static {
        Runtime.register("Android.Support.V4.View.Accessibility.AccessibilityManagerCompat+IAccessibilityStateChangeListenerImplementor, Xamarin.Android.Support.Compat", C0522xa531d821.class, __md_methods);
    }

    public C0522xa531d821() {
        if (getClass() == C0522xa531d821.class) {
            TypeManager.Activate("Android.Support.V4.View.Accessibility.AccessibilityManagerCompat+IAccessibilityStateChangeListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]);
        }
    }

    public void onAccessibilityStateChanged(boolean z) {
        n_onAccessibilityStateChanged(z);
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
