package md533db397aee1f7941e5b5dd6fef6b7720;

import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.view.Menu;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;
import p004de.humatic.nmj.NMJSystemListener;
import p004de.humatic.nmj.NetworkMidiClient;
import p004de.humatic.nmj.NetworkMidiListener;

public class MainActivity extends AppCompatActivity implements IGCUserPeer, NetworkMidiListener, NetworkMidiClient, NMJSystemListener {
    public static final String __md_methods = "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onCreateOptionsMenu:(Landroid/view/Menu;)Z:GetOnCreateOptionsMenu_Landroid_view_Menu_Handler\nn_midiReceived:(II[BJ)V:GetMidiReceived_IIarrayBJHandler:DE.Humatic.Nmj.INetworkMidiListenerInvoker, nmj\nn_systemChanged:(III)V:GetSystemChanged_IIIHandler:DE.Humatic.Nmj.INMJSystemListenerInvoker, nmj\nn_systemError:(IILjava/lang/String;)V:GetSystemError_IILjava_lang_String_Handler:DE.Humatic.Nmj.INMJSystemListenerInvoker, nmj\n";
    private ArrayList refList;

    private native void n_midiReceived(int i, int i2, byte[] bArr, long j);

    private native void n_onCreate(Bundle bundle);

    private native boolean n_onCreateOptionsMenu(Menu menu);

    private native void n_onDestroy();

    private native void n_systemChanged(int i, int i2, int i3);

    private native void n_systemError(int i, int i2, String str);

    static {
        Runtime.register("MidiStyler.MainActivity, MidiStyler", MainActivity.class, __md_methods);
    }

    public MainActivity() {
        if (getClass() == MainActivity.class) {
            TypeManager.Activate("MidiStyler.MainActivity, MidiStyler", "", this, new Object[0]);
        }
    }

    public void onCreate(Bundle bundle) {
        n_onCreate(bundle);
    }

    public void onDestroy() {
        n_onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return n_onCreateOptionsMenu(menu);
    }

    public void midiReceived(int i, int i2, byte[] bArr, long j) {
        n_midiReceived(i, i2, bArr, j);
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
