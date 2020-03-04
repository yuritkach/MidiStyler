package mono.android.support.p007v4.p008os;

import android.support.p000v4.p002os.CancellationSignal;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.os.CancellationSignal_OnCancelListenerImplementor */
public class CancellationSignal_OnCancelListenerImplementor implements IGCUserPeer, CancellationSignal.OnCancelListener {
    public static final String __md_methods = "n_onCancel:()V:GetOnCancelHandler:Android.Support.V4.OS.CancellationSignal/IOnCancelListenerInvoker, Xamarin.Android.Support.Compat\n";
    private ArrayList refList;

    private native void n_onCancel();

    static {
        Runtime.register("Android.Support.V4.OS.CancellationSignal+IOnCancelListenerImplementor, Xamarin.Android.Support.Compat", CancellationSignal_OnCancelListenerImplementor.class, __md_methods);
    }

    public CancellationSignal_OnCancelListenerImplementor() {
        if (getClass() == CancellationSignal_OnCancelListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.OS.CancellationSignal+IOnCancelListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]);
        }
    }

    public void onCancel() {
        n_onCancel();
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
