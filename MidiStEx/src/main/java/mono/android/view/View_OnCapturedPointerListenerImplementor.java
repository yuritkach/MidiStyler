package mono.android.view;

import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnCapturedPointerListenerImplementor implements IGCUserPeer, View.OnCapturedPointerListener {
    public static final String __md_methods = "n_onCapturedPointer:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnCapturedPointer_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnCapturedPointerListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native boolean n_onCapturedPointer(View view, MotionEvent motionEvent);

    static {
        Runtime.register("Android.Views.View+IOnCapturedPointerListenerImplementor, Mono.Android", View_OnCapturedPointerListenerImplementor.class, __md_methods);
    }

    public View_OnCapturedPointerListenerImplementor() {
        if (getClass() == View_OnCapturedPointerListenerImplementor.class) {
            TypeManager.Activate("Android.Views.View+IOnCapturedPointerListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public boolean onCapturedPointer(View view, MotionEvent motionEvent) {
        return n_onCapturedPointer(view, motionEvent);
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
