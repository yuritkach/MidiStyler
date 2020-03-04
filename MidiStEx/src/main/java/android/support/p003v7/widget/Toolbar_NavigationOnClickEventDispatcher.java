package android.support.p003v7.widget;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: android.support.v7.widget.Toolbar_NavigationOnClickEventDispatcher */
public class Toolbar_NavigationOnClickEventDispatcher implements IGCUserPeer, View.OnClickListener {
    public static final String __md_methods = "n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onClick(View view);

    static {
        Runtime.register("Android.Support.V7.Widget.Toolbar+NavigationOnClickEventDispatcher, Xamarin.Android.Support.v7.AppCompat", Toolbar_NavigationOnClickEventDispatcher.class, __md_methods);
    }

    public Toolbar_NavigationOnClickEventDispatcher() {
        if (getClass() == Toolbar_NavigationOnClickEventDispatcher.class) {
            TypeManager.Activate("Android.Support.V7.Widget.Toolbar+NavigationOnClickEventDispatcher, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public Toolbar_NavigationOnClickEventDispatcher(Toolbar toolbar) {
        if (getClass() == Toolbar_NavigationOnClickEventDispatcher.class) {
            TypeManager.Activate("Android.Support.V7.Widget.Toolbar+NavigationOnClickEventDispatcher, Xamarin.Android.Support.v7.AppCompat", "Android.Support.V7.Widget.Toolbar, Xamarin.Android.Support.v7.AppCompat", this, new Object[]{toolbar});
        }
    }

    public void onClick(View view) {
        n_onClick(view);
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
