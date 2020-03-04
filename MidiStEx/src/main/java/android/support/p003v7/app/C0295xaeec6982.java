package android.support.p003v7.app;

import android.content.DialogInterface;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: android.support.v7.app.AlertDialog_IDialogInterfaceOnMultiChoiceClickListenerImplementor */
public class C0295xaeec6982 implements IGCUserPeer, DialogInterface.OnMultiChoiceClickListener {
    public static final String __md_methods = "n_onClick:(Landroid/content/DialogInterface;IZ)V:GetOnClick_Landroid_content_DialogInterface_IZHandler:Android.Content.IDialogInterfaceOnMultiChoiceClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onClick(DialogInterface dialogInterface, int i, boolean z);

    static {
        Runtime.register("Android.Support.V7.App.AlertDialog+IDialogInterfaceOnMultiChoiceClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", C0295xaeec6982.class, __md_methods);
    }

    public C0295xaeec6982() {
        if (getClass() == C0295xaeec6982.class) {
            TypeManager.Activate("Android.Support.V7.App.AlertDialog+IDialogInterfaceOnMultiChoiceClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public void onClick(DialogInterface dialogInterface, int i, boolean z) {
        n_onClick(dialogInterface, i, z);
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
