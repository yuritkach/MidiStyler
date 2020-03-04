package mono.p010de.humatic.nmj;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;
import p004de.humatic.nmj.NMJSystemListener;

/* renamed from: mono.de.humatic.nmj.NMJSystemListenerImplementor */
public class NMJSystemListenerImplementor implements IGCUserPeer, NMJSystemListener {
    public static final String __md_methods = "n_systemChanged:(III)V:GetSystemChanged_IIIHandler:DE.Humatic.Nmj.INMJSystemListenerInvoker, nmj\nn_systemError:(IILjava/lang/String;)V:GetSystemError_IILjava_lang_String_Handler:DE.Humatic.Nmj.INMJSystemListenerInvoker, nmj\n";
    private ArrayList refList;

    private native void n_systemChanged(int i, int i2, int i3);

    private native void n_systemError(int i, int i2, String str);

    static {
        Runtime.register("DE.Humatic.Nmj.INMJSystemListenerImplementor, nmj", NMJSystemListenerImplementor.class, __md_methods);
    }

    public NMJSystemListenerImplementor() {
        if (getClass() == NMJSystemListenerImplementor.class) {
            TypeManager.Activate("DE.Humatic.Nmj.INMJSystemListenerImplementor, nmj", "", this, new Object[0]);
        }
    }

    public void systemChanged(int i, int i2, int i3) {
        n_systemChanged(i, i2, i3);
    }

    public void systemError(int i, int i2, String str) {
        n_systemError(i, i2, str);
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
