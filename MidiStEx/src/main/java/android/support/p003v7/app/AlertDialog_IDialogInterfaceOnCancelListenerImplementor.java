package android.support.p003v7.app;

import android.content.DialogInterface;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: android.support.v7.app.AlertDialog_IDialogInterfaceOnCancelListenerImplementor */
public class AlertDialog_IDialogInterfaceOnCancelListenerImplementor implements IGCUserPeer, DialogInterface.OnCancelListener {
    public static final String __md_methods = "n_onCancel:(Landroid/content/DialogInterface;)V:GetOnCancel_Landroid_content_DialogInterface_Handler:Android.Content.IDialogInterfaceOnCancelListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onCancel(DialogInterface dialogInterface);

    static {
        Runtime.register("Android.Support.V7.App.AlertDialog+IDialogInterfaceOnCancelListenerImplementor, Xamarin.Android.Support.v7.AppCompat", AlertDialog_IDialogInterfaceOnCancelListenerImplementor.class, __md_methods);
    }

    public AlertDialog_IDialogInterfaceOnCancelListenerImplementor() {
        if (getClass() == AlertDialog_IDialogInterfaceOnCancelListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.App.AlertDialog+IDialogInterfaceOnCancelListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        n_onCancel(dialogInterface);
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
