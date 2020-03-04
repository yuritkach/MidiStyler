package mono.android.support.p009v7.widget;

import android.graphics.Rect;
import android.support.p003v7.widget.FitWindowsViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.widget.FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor */
public class FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor implements IGCUserPeer, FitWindowsViewGroup.OnFitSystemWindowsListener {
    public static final String __md_methods = "n_onFitSystemWindows:(Landroid/graphics/Rect;)V:GetOnFitSystemWindows_Landroid_graphics_Rect_Handler:Android.Support.V7.Widget.IFitWindowsViewGroupOnFitSystemWindowsListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
    private ArrayList refList;

    private native void n_onFitSystemWindows(Rect rect);

    static {
        Runtime.register("Android.Support.V7.Widget.IFitWindowsViewGroupOnFitSystemWindowsListenerImplementor, Xamarin.Android.Support.v7.AppCompat", FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor.class, __md_methods);
    }

    public FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor() {
        if (getClass() == FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.Widget.IFitWindowsViewGroupOnFitSystemWindowsListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public void onFitSystemWindows(Rect rect) {
        n_onFitSystemWindows(rect);
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
