package mono.android.support.transition;

import android.support.transition.Transition;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Transition_TransitionListenerImplementor implements IGCUserPeer, Transition.TransitionListener {
    public static final String __md_methods = "n_onTransitionCancel:(Landroid/support/transition/Transition;)V:GetOnTransitionCancel_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionEnd:(Landroid/support/transition/Transition;)V:GetOnTransitionEnd_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionPause:(Landroid/support/transition/Transition;)V:GetOnTransitionPause_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionResume:(Landroid/support/transition/Transition;)V:GetOnTransitionResume_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionStart:(Landroid/support/transition/Transition;)V:GetOnTransitionStart_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\n";
    private ArrayList refList;

    private native void n_onTransitionCancel(Transition transition);

    private native void n_onTransitionEnd(Transition transition);

    private native void n_onTransitionPause(Transition transition);

    private native void n_onTransitionResume(Transition transition);

    private native void n_onTransitionStart(Transition transition);

    static {
        Runtime.register("Android.Support.Transitions.Transition+ITransitionListenerImplementor, Xamarin.Android.Support.Transition", Transition_TransitionListenerImplementor.class, __md_methods);
    }

    public Transition_TransitionListenerImplementor() {
        if (getClass() == Transition_TransitionListenerImplementor.class) {
            TypeManager.Activate("Android.Support.Transitions.Transition+ITransitionListenerImplementor, Xamarin.Android.Support.Transition", "", this, new Object[0]);
        }
    }

    public void onTransitionCancel(Transition transition) {
        n_onTransitionCancel(transition);
    }

    public void onTransitionEnd(Transition transition) {
        n_onTransitionEnd(transition);
    }

    public void onTransitionPause(Transition transition) {
        n_onTransitionPause(transition);
    }

    public void onTransitionResume(Transition transition) {
        n_onTransitionResume(transition);
    }

    public void onTransitionStart(Transition transition) {
        n_onTransitionStart(transition);
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
