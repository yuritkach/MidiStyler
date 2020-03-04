package md54b6cd06faa7ebb091f122a620afe8e2c;

import android.media.midi.MidiReceiver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MidiInput_Receiver extends MidiReceiver implements IGCUserPeer {
    public static final String __md_methods = "n_onSend:([BIIJ)V:GetOnSend_arrayBIIJHandler\n";
    private ArrayList refList;

    private native void n_onSend(byte[] bArr, int i, int i2, long j);

    static {
        Runtime.register("Commons.Music.Midi.AndroidExtensions.MidiInput+Receiver, Commons.Music.Midi", MidiInput_Receiver.class, __md_methods);
    }

    public MidiInput_Receiver() {
        if (getClass() == MidiInput_Receiver.class) {
            TypeManager.Activate("Commons.Music.Midi.AndroidExtensions.MidiInput+Receiver, Commons.Music.Midi", "", this, new Object[0]);
        }
    }

    public MidiInput_Receiver(int i) {
        super(i);
        if (getClass() == MidiInput_Receiver.class) {
            TypeManager.Activate("Commons.Music.Midi.AndroidExtensions.MidiInput+Receiver, Commons.Music.Midi", "System.Int32, mscorlib", this, new Object[]{Integer.valueOf(i)});
        }
    }

    public void onSend(byte[] bArr, int i, int i2, long j) {
        n_onSend(bArr, i, i2, j);
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
