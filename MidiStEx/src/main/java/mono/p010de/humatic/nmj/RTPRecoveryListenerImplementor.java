package mono.p010de.humatic.nmj;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;
import p004de.humatic.nmj.RTPRecoveryListener;

/* renamed from: mono.de.humatic.nmj.RTPRecoveryListenerImplementor */
public class RTPRecoveryListenerImplementor implements IGCUserPeer, RTPRecoveryListener {
    public static final String __md_methods = "n_journalParsed:(II[[BJ)V:GetJournalParsed_IIarrayarrayBJHandler:DE.Humatic.Nmj.IRTPRecoveryListenerInvoker, nmj\n";
    private ArrayList refList;

    private native void n_journalParsed(int i, int i2, byte[][] bArr, long j);

    static {
        Runtime.register("DE.Humatic.Nmj.IRTPRecoveryListenerImplementor, nmj", RTPRecoveryListenerImplementor.class, __md_methods);
    }

    public RTPRecoveryListenerImplementor() {
        if (getClass() == RTPRecoveryListenerImplementor.class) {
            TypeManager.Activate("DE.Humatic.Nmj.IRTPRecoveryListenerImplementor, nmj", "", this, new Object[0]);
        }
    }

    public void journalParsed(int i, int i2, byte[][] bArr, long j) {
        n_journalParsed(i, i2, bArr, j);
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
