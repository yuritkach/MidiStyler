package mono.android.support.p007v4.view.accessibility;

import android.support.p000v4.view.accessibility.AccessibilityManagerCompat;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.view.accessibility.AccessibilityManagerCompat_TouchExplorationStateChangeListenerImplementor */
public class C0523x8791eb33 implements IGCUserPeer, AccessibilityManagerCompat.TouchExplorationStateChangeListener {
    public static final String __md_methods = "n_onTouchExplorationStateChanged:(Z)V:GetOnTouchExplorationStateChanged_ZHandler:Android.Support.V4.View.Accessibility.AccessibilityManagerCompat/ITouchExplorationStateChangeListenerInvoker, Xamarin.Android.Support.Compat\n";
    private ArrayList refList;

    private native void n_onTouchExplorationStateChanged(boolean z);

    static {
        Runtime.register("Android.Support.V4.View.Accessibility.AccessibilityManagerCompat+ITouchExplorationStateChangeListenerImplementor, Xamarin.Android.Support.Compat", C0523x8791eb33.class, __md_methods);
    }

    public C0523x8791eb33() {
        if (getClass() == C0523x8791eb33.class) {
            TypeManager.Activate("Android.Support.V4.View.Accessibility.AccessibilityManagerCompat+ITouchExplorationStateChangeListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]);
        }
    }

    public void onTouchExplorationStateChanged(boolean z) {
        n_onTouchExplorationStateChanged(z);
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
