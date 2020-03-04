package p004de.humatic.nmj;

import android.content.Context;
import android.widget.ViewFlipper;

/* renamed from: de.humatic.nmj.i */
final class C0473i extends ViewFlipper {
    C0473i(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            stopFlipping();
        }
    }
}
