package opentk_1_0.platform.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;
import opentk_1_0.GameViewBase;

public class AndroidGameView extends GameViewBase implements IGCUserPeer, SurfaceHolder.Callback {
    public static final String __md_methods = "n_surfaceChanged:(Landroid/view/SurfaceHolder;III)V:GetSurfaceChanged_Landroid_view_SurfaceHolder_IIIHandler:Android.Views.ISurfaceHolderCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_surfaceCreated:(Landroid/view/SurfaceHolder;)V:GetSurfaceCreated_Landroid_view_SurfaceHolder_Handler:Android.Views.ISurfaceHolderCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_surfaceDestroyed:(Landroid/view/SurfaceHolder;)V:GetSurfaceDestroyed_Landroid_view_SurfaceHolder_Handler:Android.Views.ISurfaceHolderCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3);

    private native void n_surfaceCreated(SurfaceHolder surfaceHolder);

    private native void n_surfaceDestroyed(SurfaceHolder surfaceHolder);

    static {
        Runtime.register("OpenTK.Platform.Android.AndroidGameView, OpenTK-1.0", AndroidGameView.class, __md_methods);
    }

    public AndroidGameView(Context context) {
        super(context);
        if (getClass() == AndroidGameView.class) {
            TypeManager.Activate("OpenTK.Platform.Android.AndroidGameView, OpenTK-1.0", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public AndroidGameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == AndroidGameView.class) {
            TypeManager.Activate("OpenTK.Platform.Android.AndroidGameView, OpenTK-1.0", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public AndroidGameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == AndroidGameView.class) {
            TypeManager.Activate("OpenTK.Platform.Android.AndroidGameView, OpenTK-1.0", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public AndroidGameView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (getClass() == AndroidGameView.class) {
            TypeManager.Activate("OpenTK.Platform.Android.AndroidGameView, OpenTK-1.0", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i), Integer.valueOf(i2)});
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        n_surfaceChanged(surfaceHolder, i, i2, i3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        n_surfaceCreated(surfaceHolder);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        n_surfaceDestroyed(surfaceHolder);
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
