package mono.android.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.accessibilityservice.AccessibilityService_SoftKeyboardController_OnShowModeChangedListenerImplementor */
public class C0520xd9f04bf7 implements IGCUserPeer, AccessibilityService.SoftKeyboardController.OnShowModeChangedListener {
    public static final String __md_methods = "n_onShowModeChanged:(Landroid/accessibilityservice/AccessibilityService$SoftKeyboardController;I)V:GetOnShowModeChanged_Landroid_accessibilityservice_AccessibilityService_SoftKeyboardController_IHandler:Android.AccessibilityServices.AccessibilityService/SoftKeyboardController/IOnShowModeChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onShowModeChanged(AccessibilityService.SoftKeyboardController softKeyboardController, int i);

    static {
        Runtime.register("Android.AccessibilityServices.AccessibilityService+SoftKeyboardController+IOnShowModeChangedListenerImplementor, Mono.Android", C0520xd9f04bf7.class, __md_methods);
    }

    public C0520xd9f04bf7() {
        if (getClass() == C0520xd9f04bf7.class) {
            TypeManager.Activate("Android.AccessibilityServices.AccessibilityService+SoftKeyboardController+IOnShowModeChangedListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onShowModeChanged(AccessibilityService.SoftKeyboardController softKeyboardController, int i) {
        n_onShowModeChanged(softKeyboardController, i);
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
