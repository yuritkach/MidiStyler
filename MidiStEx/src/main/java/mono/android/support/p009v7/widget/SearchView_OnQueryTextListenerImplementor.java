package mono.android.support.p009v7.widget;

import android.support.p003v7.widget.SearchView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

/* renamed from: mono.android.support.v7.widget.SearchView_OnQueryTextListenerImplementor */
public class SearchView_OnQueryTextListenerImplementor implements IGCUserPeer, SearchView.OnQueryTextListener {
    public static final String __md_methods = "n_onQueryTextChange:(Ljava/lang/String;)Z:GetOnQueryTextChange_Ljava_lang_String_Handler:Android.Support.V7.Widget.SearchView/IOnQueryTextListenerInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onQueryTextSubmit:(Ljava/lang/String;)Z:GetOnQueryTextSubmit_Ljava_lang_String_Handler:Android.Support.V7.Widget.SearchView/IOnQueryTextListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
    private ArrayList refList;

    private native boolean n_onQueryTextChange(String str);

    private native boolean n_onQueryTextSubmit(String str);

    static {
        Runtime.register("Android.Support.V7.Widget.SearchView+IOnQueryTextListenerImplementor, Xamarin.Android.Support.v7.AppCompat", SearchView_OnQueryTextListenerImplementor.class, __md_methods);
    }

    public SearchView_OnQueryTextListenerImplementor() {
        if (getClass() == SearchView_OnQueryTextListenerImplementor.class) {
            TypeManager.Activate("Android.Support.V7.Widget.SearchView+IOnQueryTextListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]);
        }
    }

    public boolean onQueryTextChange(String str) {
        return n_onQueryTextChange(str);
    }

    public boolean onQueryTextSubmit(String str) {
        return n_onQueryTextSubmit(str);
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
