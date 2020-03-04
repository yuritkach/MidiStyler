package md54b6cd06faa7ebb091f122a620afe8e2c;

import android.media.midi.MidiDevice;
import android.media.midi.MidiManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AndroidMidiAccess_OpenDeviceListener implements IGCUserPeer, MidiManager.OnDeviceOpenedListener {
    public static final String __md_methods = "n_onDeviceOpened:(Landroid/media/midi/MidiDevice;)V:GetOnDeviceOpened_Landroid_media_midi_MidiDevice_Handler:Android.Media.Midi.MidiManager/IOnDeviceOpenedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onDeviceOpened(MidiDevice midiDevice);

    static {
        Runtime.register("Commons.Music.Midi.AndroidExtensions.AndroidMidiAccess+OpenDeviceListener, Commons.Music.Midi", AndroidMidiAccess_OpenDeviceListener.class, __md_methods);
    }

    public AndroidMidiAccess_OpenDeviceListener() {
        if (getClass() == AndroidMidiAccess_OpenDeviceListener.class) {
            TypeManager.Activate("Commons.Music.Midi.AndroidExtensions.AndroidMidiAccess+OpenDeviceListener, Commons.Music.Midi", "", this, new Object[0]);
        }
    }

    public void onDeviceOpened(MidiDevice midiDevice) {
        n_onDeviceOpened(midiDevice);
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
