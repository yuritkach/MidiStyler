package mono.android.widget;

import android.widget.AutoCompleteTextView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AutoCompleteTextView_OnDismissListenerImplementor implements IGCUserPeer, AutoCompleteTextView.OnDismissListener {
    public static final String __md_methods = "n_onDismiss:()V:GetOnDismissHandler:Android.Widget.AutoCompleteTextView/IOnDismissListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onDismiss();

    static {
        Runtime.register("Android.Widget.AutoCompleteTextView+IOnDismissListenerImplementor, Mono.Android", AutoCompleteTextView_OnDismissListenerImplementor.class, __md_methods);
    }

    public AutoCompleteTextView_OnDismissListenerImplementor() {
        if (getClass() == AutoCompleteTextView_OnDismissListenerImplementor.class) {
            TypeManager.Activate("Android.Widget.AutoCompleteTextView+IOnDismissListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onDismiss() {
        n_onDismiss();
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
