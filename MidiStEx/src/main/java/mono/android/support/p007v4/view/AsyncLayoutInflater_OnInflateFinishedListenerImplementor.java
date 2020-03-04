package mono.android.support.p007v4.view;

import android.support.p000v4.view.AsyncLayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.view.AsyncLayoutInflater_OnInflateFinishedListenerImplementor */
public class AsyncLayoutInflater_OnInflateFinishedListenerImplementor implements IGCUserPeer, AsyncLayoutInflater.OnInflateFinishedListener {
    public static final String __md_methods = "n_onInflateFinished:(Landroid/view/View;ILandroid/view/ViewGroup;)V:GetOnInflateFinished_Landroid_view_View_ILandroid_view_ViewGroup_Handler:Android.Support.V4.View.AsyncLayoutInflater/IOnInflateFinishedListenerInvoker, Xamarin.Android.Support.Core.UI\n";
    private ArrayList refList;

    private native void n_onInflateFinished(View view, int i, ViewGroup viewGroup);

    static {
        Runtime.register("Android.Support.V4.View.AsyncLayoutInflater+IOnInflateFinishedListenerImplementor, Xamarin.Android.Support.Core.UI", AsyncLayoutInflater_OnInflateFinishedListenerImplementor.class, __md_methods);
    }

    public AsyncLayoutInflater_OnInflateFinishedListenerImplementor() {
        if (getClass() == AsyncLayoutInflater_OnInflateFinishedListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.View.AsyncLayoutInflater+IOnInflateFinishedListenerImplementor, Xamarin.Android.Support.Core.UI", "", this, new Object[0]);
        }
    }

    public void onInflateFinished(View view, int i, ViewGroup viewGroup) {
        n_onInflateFinished(view, i, viewGroup);
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
