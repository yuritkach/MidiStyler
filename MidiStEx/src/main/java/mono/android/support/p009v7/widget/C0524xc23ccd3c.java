package mono.android.support.p009v7.widget;

import android.support.p003v7.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.widget.RecyclerView_ItemAnimator_ItemAnimatorFinishedListenerImplementor */
public class C0524xc23ccd3c implements IGCUserPeer, RecyclerView.ItemAnimator.ItemAnimatorFinishedListener {
    public static final String __md_methods = "n_onAnimationsFinished:()V:GetOnAnimationsFinishedHandler:Android.Support.V7.Widget.RecyclerView/ItemAnimator/IItemAnimatorFinishedListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n";
    private ArrayList refList;

    private native void n_onAnimationsFinished();

    static {
        Runtime.register("Android.Support.V7.Widget.RecyclerView+ItemAnimator+IItemAnimatorFinishedListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", C0524xc23ccd3c.class, __md_methods);
    }

    public C0524xc23ccd3c() {
        if (getClass() == C0524xc23ccd3c.class) {
            TypeManager.Activate("Android.Support.V7.Widget.RecyclerView+ItemAnimator+IItemAnimatorFinishedListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", "", this, new Object[0]);
        }
    }

    public void onAnimationsFinished() {
        n_onAnimationsFinished();
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
