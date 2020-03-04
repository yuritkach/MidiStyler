package android.runtime;

import java.lang.Thread;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class UncaughtExceptionHandler implements IGCUserPeer, Thread.UncaughtExceptionHandler {
    public static final String __md_methods = "n_uncaughtException:(Ljava/lang/Thread;Ljava/lang/Throwable;)V:GetUncaughtException_Ljava_lang_Thread_Ljava_lang_Throwable_Handler:Java.Lang.Thread/IUncaughtExceptionHandlerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_uncaughtException(Thread thread, Throwable th);

    static {
        Runtime.register("Android.Runtime.UncaughtExceptionHandler, Mono.Android", UncaughtExceptionHandler.class, __md_methods);
    }

    public UncaughtExceptionHandler() {
        if (getClass() == UncaughtExceptionHandler.class) {
            TypeManager.Activate("Android.Runtime.UncaughtExceptionHandler, Mono.Android", "", this, new Object[0]);
        }
    }

    public UncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (getClass() == UncaughtExceptionHandler.class) {
            TypeManager.Activate("Android.Runtime.UncaughtExceptionHandler, Mono.Android", "Java.Lang.Thread+IUncaughtExceptionHandler, Mono.Android", this, new Object[]{uncaughtExceptionHandler});
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        n_uncaughtException(thread, th);
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
