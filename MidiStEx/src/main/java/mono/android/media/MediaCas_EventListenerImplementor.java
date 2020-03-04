package mono.android.media;

import android.media.MediaCas;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaCas_EventListenerImplementor implements IGCUserPeer, MediaCas.EventListener {
    public static final String __md_methods = "n_onEvent:(Landroid/media/MediaCas;II[B)V:GetOnEvent_Landroid_media_MediaCas_IIarrayBHandler:Android.Media.MediaCas/IEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onEvent(MediaCas mediaCas, int i, int i2, byte[] bArr);

    static {
        Runtime.register("Android.Media.MediaCas+IEventListenerImplementor, Mono.Android", MediaCas_EventListenerImplementor.class, __md_methods);
    }

    public MediaCas_EventListenerImplementor() {
        if (getClass() == MediaCas_EventListenerImplementor.class) {
            TypeManager.Activate("Android.Media.MediaCas+IEventListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onEvent(MediaCas mediaCas, int i, int i2, byte[] bArr) {
        n_onEvent(mediaCas, i, i2, bArr);
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
