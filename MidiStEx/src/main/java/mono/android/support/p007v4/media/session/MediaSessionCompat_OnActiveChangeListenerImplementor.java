package mono.android.support.p007v4.media.session;

import android.support.p000v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v4.media.session.MediaSessionCompat_OnActiveChangeListenerImplementor */
public class MediaSessionCompat_OnActiveChangeListenerImplementor implements IGCUserPeer, MediaSessionCompat.OnActiveChangeListener {
    public static final String __md_methods = "n_onActiveChanged:()V:GetOnActiveChangedHandler:Android.Support.V4.Media.Session.MediaSessionCompat/IOnActiveChangeListenerInvoker, Xamarin.Android.Support.Media.Compat\n";
    private ArrayList refList;

    private native void n_onActiveChanged();

    static {
        Runtime.register("Android.Support.V4.Media.Session.MediaSessionCompat+IOnActiveChangeListenerImplementor, Xamarin.Android.Support.Media.Compat", MediaSessionCompat_OnActiveChangeListenerImplementor.class, __md_methods);
    }

    public MediaSessionCompat_OnActiveChangeListenerImplementor() {
        if (getClass() == MediaSessionCompat_OnActiveChangeListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V4.Media.Session.MediaSessionCompat+IOnActiveChangeListenerImplementor, Xamarin.Android.Support.Media.Compat", "", this, new Object[0]);
        }
    }

    public void onActiveChanged() {
        n_onActiveChanged();
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
