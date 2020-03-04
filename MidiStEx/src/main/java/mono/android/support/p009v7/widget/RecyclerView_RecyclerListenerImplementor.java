package mono.android.support.p009v7.widget;

import android.support.p003v7.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.widget.RecyclerView_RecyclerListenerImplementor */
public class RecyclerView_RecyclerListenerImplementor implements IGCUserPeer, RecyclerView.RecyclerListener {
    public static final String __md_methods = "n_onViewRecycled:(Landroid/support/v7/widget/RecyclerView$ViewHolder;)V:GetOnViewRecycled_Landroid_support_v7_widget_RecyclerView_ViewHolder_Handler:Android.Support.V7.Widget.RecyclerView/IRecyclerListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n";
    private ArrayList refList;

    private native void n_onViewRecycled(RecyclerView.ViewHolder viewHolder);

    static {
        Runtime.register("Android.Support.V7.Widget.RecyclerView+IRecyclerListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", RecyclerView_RecyclerListenerImplementor.class, __md_methods);
    }

    public RecyclerView_RecyclerListenerImplementor() {
        if (getClass() == RecyclerView_RecyclerListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.Widget.RecyclerView+IRecyclerListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", "", this, new Object[0]);
        }
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        n_onViewRecycled(viewHolder);
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
