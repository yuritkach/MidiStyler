package p004de.humatic.nmj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p000v4.view.InputDeviceCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: de.humatic.nmj.NMJConfigDialog */
public final class NMJConfigDialog extends Activity implements TextWatcher, View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener, TextView.OnEditorActionListener, NMJSystemListener {

    /* renamed from: a */
    private float f100a;

    /* renamed from: a */
    private int f101a = -1;

    /* renamed from: a */
    private AlertDialog.Builder f102a;

    /* renamed from: a */
    private BitmapDrawable f103a;

    /* renamed from: a */
    private InputMethodManager f104a;

    /* renamed from: a */
    private CheckBox f105a;

    /* renamed from: a */
    private EditText f106a;

    /* renamed from: a */
    private LinearLayout f107a;

    /* renamed from: a */
    private ListView f108a;

    /* renamed from: a */
    private ScrollView f109a;

    /* renamed from: a */
    private Spinner f110a;

    /* renamed from: a */
    private TextView f111a;

    /* renamed from: a */
    private NMJInputClient f112a = new NMJInputClient();

    /* renamed from: a */
    private NMJOutputClient f113a = new NMJOutputClient(this);

    /* renamed from: a */
    private C0445a f114a;

    /* renamed from: a */
    private C0446b f115a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public NetworkMidiInput f116a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public NetworkMidiOutput f117a;

    /* renamed from: a */
    private NetworkMidiSystem f118a;

    /* renamed from: a */
    private C0464e f119a;

    /* renamed from: a */
    private C0473i f120a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f121a;

    /* renamed from: a */
    private byte[] f122a;

    /* renamed from: a */
    private int[] f123a = {63, 32, 29, 2};

    /* renamed from: a */
    private String[] f124a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public int f125b = 63;

    /* renamed from: b */
    private BitmapDrawable f126b;

    /* renamed from: b */
    private CheckBox f127b;

    /* renamed from: b */
    private EditText f128b;

    /* renamed from: b */
    private LinearLayout f129b;

    /* renamed from: b */
    private Spinner f130b;

    /* renamed from: b */
    private TextView f131b;

    /* renamed from: b */
    private boolean f132b;

    /* renamed from: b */
    private byte[] f133b;

    /* renamed from: b */
    private int[] f134b;

    /* renamed from: b */
    private String[] f135b;

    /* renamed from: c */
    private int f136c = -1;

    /* renamed from: c */
    private BitmapDrawable f137c;

    /* renamed from: c */
    private CheckBox f138c;

    /* renamed from: c */
    private EditText f139c;

    /* renamed from: c */
    private LinearLayout f140c;

    /* renamed from: c */
    private Spinner f141c;

    /* renamed from: c */
    private TextView f142c;

    /* renamed from: c */
    private boolean f143c;

    /* renamed from: c */
    private byte[] f144c;

    /* renamed from: c */
    private int[] f145c;

    /* renamed from: d */
    private int f146d;

    /* renamed from: d */
    private EditText f147d;

    /* renamed from: d */
    private LinearLayout f148d;

    /* renamed from: d */
    private TextView f149d;

    /* renamed from: d */
    private boolean f150d;

    /* renamed from: d */
    private int[] f151d;

    /* renamed from: e */
    private int f152e;

    /* renamed from: e */
    private LinearLayout f153e;

    /* renamed from: e */
    private TextView f154e;

    /* renamed from: e */
    private boolean f155e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public int f156f;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public boolean f157f;

    /* renamed from: g */
    private int f158g = 16974079;

    /* renamed from: g */
    private boolean f159g;

    /* renamed from: h */
    private int f160h;

    /* renamed from: i */
    private int f161i = 36;

    public NMJConfigDialog() {
        int[] iArr = new int[8];
        iArr[1] = 2;
        iArr[2] = 4;
        iArr[3] = 5;
        iArr[4] = 7;
        iArr[5] = 9;
        iArr[6] = 11;
        iArr[7] = 12;
        this.f134b = iArr;
        this.f145c = new int[]{1, 3, 6, 8, 10};
        this.f151d = new int[]{7, 10, 74};
        this.f124a = new String[]{"C", "D", "E", "F", "G", "A", "B", "C"};
        this.f135b = new String[]{"-", "C#", "D#", "3", "F#", "G#", "A#", "+"};
        byte[] bArr = new byte[3];
        bArr[0] = -80;
        bArr[1] = 74;
        this.f122a = bArr;
        byte[] bArr2 = new byte[3];
        bArr2[0] = -80;
        bArr2[1] = 7;
        this.f133b = bArr2;
        byte[] bArr3 = new byte[3];
        bArr3[0] = -112;
        this.f144c = bArr3;
        this.f121a = true;
    }

    static {
        new Vector();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005d A[Catch:{ Exception -> 0x00f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064 A[Catch:{ Exception -> 0x00f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ab A[Catch:{ Exception -> 0x00f3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r8) {
        /*
            r7 = this;
            r6 = 23
            r5 = 1069547520(0x3fc00000, float:1.5)
            r2 = 1
            r1 = 0
            super.onCreate(r8)
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = r7.getPackageName()     // Catch:{ Exception -> 0x00a1 }
            r4 = 0
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r3, r4)     // Catch:{ Exception -> 0x00a1 }
            if (r0 == 0) goto L_0x00a5
            int r0 = r0.targetSdkVersion     // Catch:{ Exception -> 0x00a1 }
        L_0x001a:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00f3 }
            if (r3 < r6) goto L_0x00a8
            if (r0 < r6) goto L_0x00a8
            r0 = r2
        L_0x0021:
            r7.f159g = r0     // Catch:{ Exception -> 0x00f3 }
            r0 = 1
            r7.requestWindowFeature(r0)     // Catch:{ Exception -> 0x00f3 }
            android.content.res.Resources r0 = r7.getResources()     // Catch:{ Exception -> 0x00f3 }
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()     // Catch:{ Exception -> 0x00f3 }
            float r0 = r0.density     // Catch:{ Exception -> 0x00f3 }
            r7.f100a = r0     // Catch:{ Exception -> 0x00f3 }
            android.content.res.Resources r0 = r7.getResources()     // Catch:{ Exception -> 0x00f3 }
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()     // Catch:{ Exception -> 0x00f3 }
            int r0 = r0.widthPixels     // Catch:{ Exception -> 0x00f3 }
            android.content.res.Resources r3 = r7.getResources()     // Catch:{ Exception -> 0x00f3 }
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()     // Catch:{ Exception -> 0x00f3 }
            int r3 = r3.heightPixels     // Catch:{ Exception -> 0x00f3 }
            int r0 = java.lang.Math.max(r0, r3)     // Catch:{ Exception -> 0x00f3 }
            android.content.res.Resources r3 = r7.getResources()     // Catch:{ Exception -> 0x00f3 }
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()     // Catch:{ Exception -> 0x00f3 }
            float r3 = r3.density     // Catch:{ Exception -> 0x00f3 }
            int r4 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x00ab
            r4 = 640(0x280, float:8.97E-43)
            if (r0 <= r4) goto L_0x00ab
            r0 = r1
        L_0x005e:
            r7.f132b = r0     // Catch:{ Exception -> 0x00f3 }
            boolean r0 = r7.f132b     // Catch:{ Exception -> 0x00f3 }
            if (r0 == 0) goto L_0x0069
            r0 = 16974081(0x1030101, float:2.406162E-38)
            r7.f158g = r0     // Catch:{ Exception -> 0x00f3 }
        L_0x0069:
            de.humatic.nmj.NMJConfigDialog$b r0 = new de.humatic.nmj.NMJConfigDialog$b     // Catch:{ Exception -> 0x00f3 }
            r1 = 0
            r2 = 0
            r0.<init>(r7, r1, r2)     // Catch:{ Exception -> 0x00f3 }
            r7.f115a = r0     // Catch:{ Exception -> 0x00f3 }
            de.humatic.nmj.NetworkMidiSystem r0 = p004de.humatic.nmj.NetworkMidiSystem.get(r7)     // Catch:{ SocketException -> 0x00bd, Exception -> 0x00f5 }
            r7.f118a = r0     // Catch:{ SocketException -> 0x00bd, Exception -> 0x00f5 }
        L_0x0078:
            java.lang.String r0 = "de.humatic.nmj.NMJConfigDialog"
            int r1 = r7.hashCode()     // Catch:{ Exception -> 0x00f3 }
            long r2 = (long) r1     // Catch:{ Exception -> 0x00f3 }
            p004de.humatic.nmj.NMJConfig.m68a((java.lang.String) r0, (long) r2)     // Catch:{ Exception -> 0x00f3 }
            p004de.humatic.nmj.NMJConfig.addSystemListener(r7)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = "monitor.png"
            android.graphics.drawable.BitmapDrawable r0 = r7.m138a((android.content.Context) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x00f3 }
            r7.f103a = r0     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = "keys.png"
            android.graphics.drawable.BitmapDrawable r0 = r7.m138a((android.content.Context) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x00f3 }
            r7.f126b = r0     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = "blank.png"
            android.graphics.drawable.BitmapDrawable r0 = r7.m138a((android.content.Context) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x00f3 }
            r7.f137c = r0     // Catch:{ Exception -> 0x00f3 }
            r7.m143a((android.content.Context) r7)     // Catch:{ Exception -> 0x00f3 }
        L_0x00a0:
            return
        L_0x00a1:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x00f3 }
        L_0x00a5:
            r0 = r1
            goto L_0x001a
        L_0x00a8:
            r0 = r1
            goto L_0x0021
        L_0x00ab:
            int r4 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x00b5
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r0 <= r4) goto L_0x00b5
            r0 = r1
            goto L_0x005e
        L_0x00b5:
            r0 = 1073741824(0x40000000, float:2.0)
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x00bb
        L_0x00bb:
            r0 = r2
            goto L_0x005e
        L_0x00bd:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x00f3 }
            r1 = 1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r3 = "NMJConfigDialog, on get MIDI system: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r3 = r0.toString()     // Catch:{ Exception -> 0x00f3 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00f3 }
            p004de.humatic.nmj.C0484p.logln(r1, r2)     // Catch:{ Exception -> 0x00f3 }
            r1 = -1
            r2 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r4 = "NMJConfigDialog, on get MIDI system: "
            r3.<init>(r4)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00f3 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00f3 }
            p004de.humatic.nmj.NMJConfig.m60a((int) r1, (int) r2, (java.lang.String) r0)     // Catch:{ Exception -> 0x00f3 }
            goto L_0x0078
        L_0x00f3:
            r0 = move-exception
            goto L_0x00a0
        L_0x00f5:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x00f3 }
            goto L_0x0078
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfigDialog.onCreate(android.os.Bundle):void");
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            m143a((Context) getApplication());
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0415 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0421 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0468 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x04c7 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x04fd A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0516 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x052b A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0556 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0578 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x05a3 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x05f9 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x061d A[SYNTHETIC, Splitter:B:156:0x061d] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x063d A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x064c A[Catch:{ NMJException -> 0x109f }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0660 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0687 A[SYNTHETIC, Splitter:B:175:0x0687] */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x06a7 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x06bb A[Catch:{ NMJException -> 0x1099 }] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x06cf A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x06f8 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x070a A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x073e A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x076a A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x07c5 A[Catch:{ Exception -> 0x0e81 }] */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x0970 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0992 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x09d7 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0a1e A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0a9f A[SYNTHETIC, Splitter:B:256:0x0a9f] */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0ae0 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x0b10 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x0b5c A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x0c63 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x0c70 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0c91 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x0cc0 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x0ccb A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x0d0a A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0d32 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x0d4e A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0de1 A[SYNTHETIC, Splitter:B:370:0x0de1] */
    /* JADX WARNING: Removed duplicated region for block: B:381:0x0e2b A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x0e32 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:383:0x0e36 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:384:0x0e39 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0e3d A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:387:0x0e45 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:391:0x0e53 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0e62 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:395:0x0e6f A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x0e7a  */
    /* JADX WARNING: Removed duplicated region for block: B:401:0x0e84 A[Catch:{ Exception -> 0x0e81 }] */
    /* JADX WARNING: Removed duplicated region for block: B:403:0x0ea2 A[SYNTHETIC, Splitter:B:403:0x0ea2] */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x0ea8 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:407:0x0eb6 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x0ee1 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:422:0x0efd A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:428:0x0f19 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x0f35 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:439:0x0f4c A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:452:0x0fda A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:454:0x0fe2 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:459:0x1001 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:471:0x1046 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:476:0x105d A[Catch:{ Exception -> 0x0d77 }, LOOP:5: B:328:0x0d27->B:476:0x105d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:478:0x1079 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:500:0x0cc3 A[EDGE_INSN: B:500:0x0cc3->B:308:0x0cc3 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x023b A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x029a A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x02b4 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x02dd A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0324 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0392 A[Catch:{ Exception -> 0x0d77 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x03c8 A[Catch:{ Exception -> 0x0d77 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m156a(android.content.Context r15) {
        /*
            r14 = this;
            android.content.res.Resources r0 = r15.getResources()     // Catch:{ Exception -> 0x0d77 }
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()     // Catch:{ Exception -> 0x0d77 }
            int r0 = r0.widthPixels     // Catch:{ Exception -> 0x0d77 }
            r14.f146d = r0     // Catch:{ Exception -> 0x0d77 }
            android.content.res.Resources r0 = r15.getResources()     // Catch:{ Exception -> 0x0d77 }
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()     // Catch:{ Exception -> 0x0d77 }
            int r0 = r0.heightPixels     // Catch:{ Exception -> 0x0d77 }
            r14.f152e = r0     // Catch:{ Exception -> 0x0d77 }
            int r0 = r14.f146d     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            int r0 = java.lang.Math.max(r0, r1)     // Catch:{ Exception -> 0x0d77 }
            r1 = 400(0x190, float:5.6E-43)
            if (r0 > r1) goto L_0x0028
            r0 = 1
            r14.setRequestedOrientation(r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x0028:
            int r0 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f146d     // Catch:{ Exception -> 0x0d77 }
            if (r0 <= r1) goto L_0x0d5b
            r0 = 1
        L_0x002f:
            r14.f143c = r0     // Catch:{ Exception -> 0x0d77 }
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r1 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x0d5e
            int r0 = r14.f146d     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            int r0 = java.lang.Math.max(r0, r1)     // Catch:{ Exception -> 0x0d77 }
            r1 = 480(0x1e0, float:6.73E-43)
            if (r0 > r1) goto L_0x0d5e
            r0 = 1
        L_0x0046:
            r14.f150d = r0     // Catch:{ Exception -> 0x0d77 }
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            double r0 = (double) r0     // Catch:{ Exception -> 0x0d77 }
            r2 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0d61
            int r0 = r14.f146d     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            int r0 = java.lang.Math.max(r0, r1)     // Catch:{ Exception -> 0x0d77 }
            r1 = 480(0x1e0, float:6.73E-43)
            if (r0 <= r1) goto L_0x0d61
            int r0 = r14.f146d     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            int r0 = java.lang.Math.max(r0, r1)     // Catch:{ Exception -> 0x0d77 }
            r1 = 854(0x356, float:1.197E-42)
            if (r0 > r1) goto L_0x0d61
            r0 = 1
        L_0x006a:
            r14.f155e = r0     // Catch:{ Exception -> 0x0d77 }
            java.lang.String[] r0 = r14.f135b     // Catch:{ Exception -> 0x0d77 }
            r1 = 3
            int r2 = r14.f161i     // Catch:{ Exception -> 0x0d77 }
            int r2 = r2 / 12
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0d77 }
            r0[r1] = r2     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.i r0 = new de.humatic.nmj.i     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r14.f120a = r0     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.i r0 = r14.f120a     // Catch:{ Exception -> 0x0d77 }
            r14.setContentView(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r0 = -1
            r1 = -1
            r4.<init>(r0, r1)     // Catch:{ Exception -> 0x0d77 }
            r0 = 10
            r1 = 5
            r2 = 10
            r3 = 5
            r4.setMargins(r0, r1, r2, r3)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = 1
            r1.setOrientation(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r2 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r2.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -15198184(0xffffffffff181818, float:-2.0216776E38)
            r2.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0d64
            r0 = 1
        L_0x00ae:
            r2.setOrientation(r0)     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.i r0 = r14.f120a     // Catch:{ Exception -> 0x0d77 }
            r3 = 0
            r0.addView(r2, r3, r4)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0d67
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = -1
            r5 = 0
            r6 = 1053609165(0x3ecccccd, float:0.4)
            r0.<init>(r3, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            r3 = 0
            r2.addView(r1, r3, r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x00c9:
            android.widget.LinearLayout r3 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r3.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r3.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            int r5 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r5)     // Catch:{ Exception -> 0x0d77 }
            boolean r5 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r5 == 0) goto L_0x0d7c
            r5 = 1092616192(0x41200000, float:10.0)
            r0.setTextSize(r5)     // Catch:{ Exception -> 0x0d77 }
        L_0x00e7:
            java.lang.String r5 = "Defined Channels"
            r0.setText(r5)     // Catch:{ Exception -> 0x0d77 }
            boolean r5 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r5 == 0) goto L_0x00f6
            r5 = -6710887(0xffffffffff999999, float:NaN)
            r0.setTextColor(r5)     // Catch:{ Exception -> 0x0d77 }
        L_0x00f6:
            r5 = 16
            r0.setGravity(r5)     // Catch:{ Exception -> 0x0d77 }
            boolean r5 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r5 == 0) goto L_0x0d87
            r5 = 10
            r6 = 1
            r7 = 0
            r8 = 0
            r0.setPadding(r5, r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
        L_0x0107:
            r5 = 0
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -1
            r9 = 1060991140(0x3f3d70a4, float:0.74)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r3.addView(r0, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.m172b((android.content.Context) r15)     // Catch:{ Exception -> 0x0d77 }
            r5 = 1
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -1
            r9 = 1048911544(0x3e851eb8, float:0.26)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r3.addView(r0, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0d91
            r0 = 1035489772(0x3db851ec, float:0.09)
        L_0x0132:
            r5.<init>(r6, r7, r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 0
            r1.addView(r3, r0, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ListView r0 = new android.widget.ListView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f108a = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.ListView r0 = r14.f108a     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.NMJConfigDialog$1 r3 = new de.humatic.nmj.NMJConfigDialog$1     // Catch:{ Exception -> 0x0d77 }
            r3.<init>()     // Catch:{ Exception -> 0x0d77 }
            r0.setOnItemClickListener(r3)     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.e r0 = new de.humatic.nmj.e     // Catch:{ Exception -> 0x0d77 }
            boolean r3 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.NetworkMidiSystem r5 = r14.f118a     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15, r3, r5)     // Catch:{ Exception -> 0x0d77 }
            r14.f119a = r0     // Catch:{ Exception -> 0x0d77 }
            r0 = 0
        L_0x0156:
            int r3 = p004de.humatic.nmj.NMJConfig.getNumChannels()     // Catch:{ Exception -> 0x0d77 }
            if (r0 < r3) goto L_0x0d96
            android.widget.ListView r0 = r14.f108a     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.e r3 = r14.f119a     // Catch:{ Exception -> 0x0d77 }
            r0.setAdapter(r3)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r5 = -1
            r6 = 0
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0da9
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0da4
            r0 = 1061494456(0x3f451eb8, float:0.77)
        L_0x0172:
            r3.<init>(r5, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ListView r0 = r14.f108a     // Catch:{ Exception -> 0x0d77 }
            r5 = 1
            r1.addView(r0, r5, r3)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r3 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r3.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r3.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 17
            r3.setGravity(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r5 = 0
            r6 = -1
            r7 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r5, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            r5 = 5
            r6 = 5
            r7 = 5
            r8 = 5
            r0.setMargins(r5, r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.m143a((android.content.Context) r15)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = 0
            r7 = -1
            r8 = 1065353216(0x3f800000, float:1.0)
            r5.<init>(r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            r3.addView(r0, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0dbc
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0db7
            r0 = 1040522936(0x3e051eb8, float:0.13)
        L_0x01ba:
            r5.<init>(r6, r7, r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 2
            r1.addView(r3, r0, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r3 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r3.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r3.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 1
            r3.setOrientation(r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 0
            r1 = 0
            r5 = 10
            r6 = 0
            r3.setPadding(r0, r1, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0dca
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r1 = -1
            r5 = 0
            r6 = 1058642330(0x3f19999a, float:0.6)
            r0.<init>(r1, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            r1 = 1
            r2.addView(r3, r1, r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x01ea:
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f107a = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r1 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r0.setBackgroundColor(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r1 = 1
            r0.setOrientation(r1)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x020a
            android.widget.ScrollView r0 = new android.widget.ScrollView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f109a = r0     // Catch:{ Exception -> 0x0d77 }
        L_0x020a:
            r0 = 13
            r2 = 0
            android.content.Context r1 = r15.getApplicationContext()     // Catch:{ Exception -> 0x0ddd }
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()     // Catch:{ Exception -> 0x0ddd }
            int r1 = r1.targetSdkVersion     // Catch:{ Exception -> 0x0ddd }
            java.lang.String r0 = android.os.Build.MANUFACTURER     // Catch:{ Exception -> 0x10b7 }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ Exception -> 0x10b7 }
            java.lang.String r5 = "samsung"
            int r0 = r0.indexOf(r5)     // Catch:{ Exception -> 0x10b7 }
            r2 = -1
            if (r0 == r2) goto L_0x0dda
            r0 = 18
        L_0x0228:
            r2 = r0
        L_0x0229:
            android.widget.TextView r0 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f111a = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f111a     // Catch:{ Exception -> 0x0d77 }
            int r5 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r5)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0243
            android.widget.TextView r0 = r14.f111a     // Catch:{ Exception -> 0x0d77 }
            r5 = -6710887(0xffffffffff999999, float:NaN)
            r0.setTextColor(r5)     // Catch:{ Exception -> 0x0d77 }
        L_0x0243:
            android.widget.TextView r0 = r14.f111a     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r5 = "Channel 1"
            r0.setText(r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f111a     // Catch:{ Exception -> 0x0d77 }
            r5 = 0
            r6 = 2
            r7 = 2
            r8 = 2
            r0.setPadding(r5, r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r5 = -1
            r6 = -2
            r0.<init>(r5, r6)     // Catch:{ Exception -> 0x0d77 }
            r5 = 15
            r6 = 8
            r7 = 0
            r8 = 12
            r0.setMargins(r5, r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r5 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r6 = r14.f111a     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r6, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r0 = -1
            r6 = 0
            r7 = 1065353216(0x3f800000, float:1.0)
            r5.<init>(r0, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1053609165(0x3ecccccd, float:0.4)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r7 = 15
            r8 = 1
            r9 = 15
            r10 = 1
            r6.setMargins(r7, r8, r9, r10)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r7 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r7.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            int r8 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r7.setTextAppearance(r15, r8)     // Catch:{ Exception -> 0x0d77 }
            boolean r8 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r8 == 0) goto L_0x02a0
            r8 = -6710887(0xffffffffff999999, float:NaN)
            r7.setTextColor(r8)     // Catch:{ Exception -> 0x0d77 }
        L_0x02a0:
            java.lang.String r8 = "Name: "
            r7.setText(r8)     // Catch:{ Exception -> 0x0d77 }
            r8 = 0
            r0.addView(r7, r8, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = new android.widget.EditText     // Catch:{ Exception -> 0x0d77 }
            r6.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f106a = r6     // Catch:{ Exception -> 0x0d77 }
            boolean r6 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r6 == 0) goto L_0x02bc
            android.widget.EditText r6 = r14.f106a     // Catch:{ Exception -> 0x0d77 }
            r7 = -6710887(0xffffffffff999999, float:NaN)
            r6.setTextColor(r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x02bc:
            android.widget.EditText r6 = r14.f106a     // Catch:{ Exception -> 0x0d77 }
            r7 = 524432(0x80090, float:7.34886E-40)
            r6.setInputType(r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = r14.f106a     // Catch:{ NMJException -> 0x10b4 }
            int r7 = r14.f101a     // Catch:{ NMJException -> 0x10b4 }
            java.lang.String r7 = p004de.humatic.nmj.NMJConfig.getName(r7)     // Catch:{ NMJException -> 0x10b4 }
            r6.setText(r7)     // Catch:{ NMJException -> 0x10b4 }
        L_0x02cf:
            android.widget.EditText r6 = r14.f106a     // Catch:{ Exception -> 0x0d77 }
            r6.setOnEditorActionListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = r14.f106a     // Catch:{ Exception -> 0x0d77 }
            r6.addTextChangedListener(r14)     // Catch:{ Exception -> 0x0d77 }
            boolean r6 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r6 == 0) goto L_0x02e4
            android.widget.EditText r6 = r14.f106a     // Catch:{ Exception -> 0x0d77 }
            r7 = 1097859072(0x41700000, float:15.0)
            r6.setTextSize(r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x02e4:
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1058642330(0x3f19999a, float:0.6)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r7 = r14.f106a     // Catch:{ Exception -> 0x0d77 }
            r8 = 1
            r0.addView(r7, r8, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r6 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r6.addView(r0, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1053609165(0x3ecccccd, float:0.4)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r7 = 15
            r8 = 1
            r9 = 15
            r10 = 1
            r6.setMargins(r7, r8, r9, r10)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r7 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r7.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            int r8 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r7.setTextAppearance(r15, r8)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r8 = "Mode: "
            r7.setText(r8)     // Catch:{ Exception -> 0x0d77 }
            boolean r8 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r8 == 0) goto L_0x032a
            r8 = -6710887(0xffffffffff999999, float:NaN)
            r7.setTextColor(r8)     // Catch:{ Exception -> 0x0d77 }
        L_0x032a:
            r8 = 0
            r0.addView(r7, r8, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r6 = new android.widget.Spinner     // Catch:{ Exception -> 0x0d77 }
            r6.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r14.f110a = r6     // Catch:{ Exception -> 0x0d77 }
            android.widget.ArrayAdapter r6 = new android.widget.ArrayAdapter     // Catch:{ Exception -> 0x0d77 }
            r7 = 17367048(0x1090008, float:2.5162948E-38)
            r8 = 8
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ Exception -> 0x0d77 }
            r9 = 0
            java.lang.String r10 = "RAW / Multicast"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r9 = 1
            java.lang.String r10 = "RTP"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r9 = 2
            java.lang.String r10 = "BLUETOOTH"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r9 = 3
            java.lang.String r10 = "DSMI"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r9 = 4
            java.lang.String r10 = "ADB"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r9 = 5
            java.lang.String r10 = "USB_HOST"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r9 = 6
            java.lang.String r10 = "MWS"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r9 = 7
            java.lang.String r10 = "COM"
            r8[r9] = r10     // Catch:{ Exception -> 0x0d77 }
            r6.<init>(r14, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            r7 = 17367049(0x1090009, float:2.516295E-38)
            r6.setDropDownViewResource(r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r7 = r14.f110a     // Catch:{ Exception -> 0x0d77 }
            r7.setAdapter(r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r6 = r14.f110a     // Catch:{ Exception -> 0x0d77 }
            r6.setOnItemSelectedListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r6 = r14.f110a     // Catch:{ NMJException -> 0x10b1 }
            int r7 = r14.f101a     // Catch:{ NMJException -> 0x10b1 }
            int r7 = p004de.humatic.nmj.NMJConfig.getMode(r7)     // Catch:{ NMJException -> 0x10b1 }
            r6.setSelection(r7)     // Catch:{ NMJException -> 0x10b1 }
        L_0x0384:
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1058642330(0x3f19999a, float:0.6)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r7 = 13
            if (r1 <= r7) goto L_0x0397
            r7 = 1
            r8 = 1
            r6.setMargins(r7, r2, r8, r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x0397:
            android.widget.Spinner r7 = r14.f110a     // Catch:{ Exception -> 0x0d77 }
            r8 = 1
            r0.addView(r7, r8, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r6 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r6.addView(r0, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1053609165(0x3ecccccd, float:0.4)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r7 = 15
            r8 = 1
            r9 = 15
            r10 = 1
            r6.setMargins(r7, r8, r9, r10)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r7 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r7.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            int r8 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r7.setTextAppearance(r15, r8)     // Catch:{ Exception -> 0x0d77 }
            boolean r8 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r8 == 0) goto L_0x03ce
            r8 = -6710887(0xffffffffff999999, float:NaN)
            r7.setTextColor(r8)     // Catch:{ Exception -> 0x0d77 }
        L_0x03ce:
            java.lang.String r8 = "IP: "
            r7.setText(r8)     // Catch:{ Exception -> 0x0d77 }
            r8 = 4103(0x1007, float:5.75E-42)
            r7.setId(r8)     // Catch:{ Exception -> 0x0d77 }
            r7.setOnTouchListener(r14)     // Catch:{ Exception -> 0x0d77 }
            r8 = 0
            r0.addView(r7, r8, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = new android.widget.EditText     // Catch:{ Exception -> 0x0d77 }
            r6.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f128b = r6     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = r14.f128b     // Catch:{ NMJException -> 0x10ae }
            int r7 = r14.f101a     // Catch:{ NMJException -> 0x10ae }
            java.lang.String r7 = p004de.humatic.nmj.NMJConfig.getIP(r7)     // Catch:{ NMJException -> 0x10ae }
            r6.setText(r7)     // Catch:{ NMJException -> 0x10ae }
        L_0x03f1:
            android.widget.EditText r6 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            r7 = 524432(0x80090, float:7.34886E-40)
            r6.setInputType(r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            r6.setOnEditorActionListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            r6.setOnLongClickListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            r6.addTextChangedListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r6 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            android.content.res.ColorStateList r6 = r6.getTextColors()     // Catch:{ Exception -> 0x0d77 }
            r6.getDefaultColor()     // Catch:{ Exception -> 0x0d77 }
            boolean r6 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r6 == 0) goto L_0x041d
            android.widget.EditText r6 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            r7 = -6710887(0xffffffffff999999, float:NaN)
            r6.setTextColor(r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x041d:
            boolean r6 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r6 == 0) goto L_0x0428
            android.widget.EditText r6 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            r7 = 1097859072(0x41700000, float:15.0)
            r6.setTextSize(r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x0428:
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1058642330(0x3f19999a, float:0.6)
            r6.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r7 = r14.f128b     // Catch:{ Exception -> 0x0d77 }
            r8 = 1
            r0.addView(r7, r8, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r6 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r6.addView(r0, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r6 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r6.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1053609165(0x3ecccccd, float:0.4)
            r0.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r7 = 15
            r8 = 1
            r9 = 15
            r10 = 1
            r0.setMargins(r7, r8, r9, r10)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r7 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r7.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            int r8 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r7.setTextAppearance(r15, r8)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r8 = "Interface: "
            r7.setText(r8)     // Catch:{ Exception -> 0x0d77 }
            boolean r8 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r8 == 0) goto L_0x046e
            r8 = -6710887(0xffffffffff999999, float:NaN)
            r7.setTextColor(r8)     // Catch:{ Exception -> 0x0d77 }
        L_0x046e:
            r8 = 0
            r6.addView(r7, r8, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r0 = new android.widget.Spinner     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r14.f130b = r0     // Catch:{ Exception -> 0x0d77 }
            java.util.Vector r7 = new java.util.Vector     // Catch:{ Exception -> 0x0d77 }
            r7.<init>()     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = "Auto"
            r7.add(r0)     // Catch:{ Exception -> 0x0d77 }
            java.util.Enumeration r8 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ SocketException -> 0x0df0, Exception -> 0x0e21 }
        L_0x0487:
            boolean r0 = r8.hasMoreElements()     // Catch:{ SocketException -> 0x0df0, Exception -> 0x0e21 }
            if (r0 != 0) goto L_0x0de1
        L_0x048d:
            int r0 = r7.size()     // Catch:{ Exception -> 0x0d77 }
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0d77 }
            r7.copyInto(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ArrayAdapter r7 = new android.widget.ArrayAdapter     // Catch:{ Exception -> 0x0d77 }
            r8 = 17367048(0x1090008, float:2.5162948E-38)
            r7.<init>(r14, r8, r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 17367049(0x1090009, float:2.516295E-38)
            r7.setDropDownViewResource(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r0 = r14.f130b     // Catch:{ Exception -> 0x0d77 }
            r0.setAdapter(r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r0 = r14.f130b     // Catch:{ NMJException -> 0x10ab }
            int r7 = r14.f101a     // Catch:{ NMJException -> 0x10ab }
            int r7 = p004de.humatic.nmj.NMJConfig.getNetworkAdapter(r7)     // Catch:{ NMJException -> 0x10ab }
            r0.setSelection(r7)     // Catch:{ NMJException -> 0x10ab }
        L_0x04b4:
            android.widget.Spinner r0 = r14.f130b     // Catch:{ Exception -> 0x0d77 }
            r0.setOnItemSelectedListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = 0
            r8 = -2
            r9 = 1058642330(0x3f19999a, float:0.6)
            r0.<init>(r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r7 = 13
            if (r1 <= r7) goto L_0x04cc
            r1 = 1
            r7 = 1
            r0.setMargins(r1, r2, r7, r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x04cc:
            android.widget.Spinner r1 = r14.f130b     // Catch:{ Exception -> 0x0d77 }
            r2 = 1
            r6.addView(r1, r2, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r6, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r0 = 0
            r6 = -2
            r7 = 1053609165(0x3ecccccd, float:0.4)
            r2.<init>(r0, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            r0 = 15
            r6 = 1
            r7 = 15
            r8 = 1
            r2.setMargins(r0, r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r6 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r6.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            int r0 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r6.setTextAppearance(r15, r0)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0e2b
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r7 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0e27
            java.lang.String r0 = "Port (Rem/Loc):"
        L_0x0507:
            r6.setText(r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x050a:
            r0 = 4104(0x1008, float:5.751E-42)
            r6.setId(r0)     // Catch:{ Exception -> 0x0d77 }
            r6.setOnTouchListener(r14)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x051c
            r0 = -6710887(0xffffffffff999999, float:NaN)
            r6.setTextColor(r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x051c:
            r0 = 0
            r1.addView(r6, r0, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r0 = new android.widget.EditText     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f139c = r0     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0533
            android.widget.EditText r0 = r14.f139c     // Catch:{ Exception -> 0x0d77 }
            r2 = -6710887(0xffffffffff999999, float:NaN)
            r0.setTextColor(r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x0533:
            android.widget.EditText r0 = r14.f139c     // Catch:{ Exception -> 0x0d77 }
            r2 = 2
            r0.setInputType(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r0 = r14.f139c     // Catch:{ NMJException -> 0x10a8 }
            int r2 = r14.f101a     // Catch:{ NMJException -> 0x10a8 }
            int r2 = p004de.humatic.nmj.NMJConfig.getPort(r2)     // Catch:{ NMJException -> 0x10a8 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ NMJException -> 0x10a8 }
            r0.setText(r2)     // Catch:{ NMJException -> 0x10a8 }
        L_0x0548:
            android.widget.EditText r0 = r14.f139c     // Catch:{ Exception -> 0x0d77 }
            r0.setOnEditorActionListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r0 = r14.f139c     // Catch:{ Exception -> 0x0d77 }
            r0.addTextChangedListener(r14)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x055d
            android.widget.EditText r0 = r14.f139c     // Catch:{ Exception -> 0x0d77 }
            r2 = 1097859072(0x41700000, float:15.0)
            r0.setTextSize(r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x055d:
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r2 = 0
            r6 = -2
            r7 = 1050253722(0x3e99999a, float:0.3)
            r0.<init>(r2, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r2 = r14.f139c     // Catch:{ Exception -> 0x0d77 }
            r6 = 1
            r1.addView(r2, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r0 = new android.widget.EditText     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f147d = r0     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0580
            android.widget.EditText r0 = r14.f147d     // Catch:{ Exception -> 0x0d77 }
            r2 = -6710887(0xffffffffff999999, float:NaN)
            r0.setTextColor(r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x0580:
            android.widget.EditText r0 = r14.f147d     // Catch:{ Exception -> 0x0d77 }
            r2 = 2
            r0.setInputType(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r0 = r14.f147d     // Catch:{ NMJException -> 0x10a5 }
            int r2 = r14.f101a     // Catch:{ NMJException -> 0x10a5 }
            int r2 = p004de.humatic.nmj.NMJConfig.getLocalPort(r2)     // Catch:{ NMJException -> 0x10a5 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ NMJException -> 0x10a5 }
            r0.setText(r2)     // Catch:{ NMJException -> 0x10a5 }
        L_0x0595:
            android.widget.EditText r0 = r14.f147d     // Catch:{ Exception -> 0x0d77 }
            r0.setOnEditorActionListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r0 = r14.f147d     // Catch:{ Exception -> 0x0d77 }
            r0.addTextChangedListener(r14)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x05aa
            android.widget.EditText r0 = r14.f147d     // Catch:{ Exception -> 0x0d77 }
            r2 = 1097859072(0x41700000, float:15.0)
            r0.setTextSize(r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x05aa:
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r2 = 0
            r6 = -2
            r7 = 1050253722(0x3e99999a, float:0.3)
            r0.<init>(r2, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.EditText r2 = r14.f147d     // Catch:{ Exception -> 0x0d77 }
            r6 = 2
            r1.addView(r2, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r1, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f148d = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f148d     // Catch:{ Exception -> 0x0d77 }
            r1 = 16
            r0.setGravity(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r1 = 0
            r2 = -1
            r6 = 1053609165(0x3ecccccd, float:0.4)
            r0.<init>(r1, r2, r6)     // Catch:{ Exception -> 0x0d77 }
            r1 = 15
            r2 = 1
            r6 = 15
            r7 = 1
            r0.setMargins(r1, r2, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r1 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f142c = r1     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r1 = r14.f142c     // Catch:{ Exception -> 0x0d77 }
            r2 = 16
            r1.setGravity(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r1 = r14.f142c     // Catch:{ Exception -> 0x0d77 }
            int r2 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r1.setTextAppearance(r15, r2)     // Catch:{ Exception -> 0x0d77 }
            boolean r1 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r1 == 0) goto L_0x0601
            android.widget.TextView r1 = r14.f142c     // Catch:{ Exception -> 0x0d77 }
            r2 = -6710887(0xffffffffff999999, float:NaN)
            r1.setTextColor(r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x0601:
            android.widget.TextView r1 = r14.f142c     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r2 = "IO: "
            r1.setText(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f148d     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r2 = r14.f142c     // Catch:{ Exception -> 0x0d77 }
            r6 = 0
            r1.addView(r2, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r0 = new android.widget.CheckBox     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f127b = r0     // Catch:{ Exception -> 0x0d77 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0d77 }
            r1 = 11
            if (r0 < r1) goto L_0x0630
            android.widget.CheckBox r0 = r14.f127b     // Catch:{ Exception -> 0x10a2 }
            android.content.res.Resources r1 = r14.getResources()     // Catch:{ Exception -> 0x10a2 }
            java.lang.String r2 = "btn_check_holo_dark"
            java.lang.String r6 = "drawable"
            java.lang.String r7 = "android"
            int r1 = r1.getIdentifier(r2, r6, r7)     // Catch:{ Exception -> 0x10a2 }
            r0.setButtonDrawable(r1)     // Catch:{ Exception -> 0x10a2 }
        L_0x0630:
            android.widget.CheckBox r0 = r14.f127b     // Catch:{ Exception -> 0x0d77 }
            r1 = 4098(0x1002, float:5.743E-42)
            r0.setId(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r1 = r14.f127b     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0e32
            java.lang.String r0 = " In"
        L_0x063f:
            r1.setText(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r1 = r14.f127b     // Catch:{ NMJException -> 0x109f }
            int r0 = r14.f101a     // Catch:{ NMJException -> 0x109f }
            int r0 = p004de.humatic.nmj.NMJConfig.getIO(r0)     // Catch:{ NMJException -> 0x109f }
            if (r0 > 0) goto L_0x0e36
            r0 = 1
        L_0x064d:
            r1.setChecked(r0)     // Catch:{ NMJException -> 0x109f }
        L_0x0650:
            android.widget.CheckBox r0 = r14.f127b     // Catch:{ Exception -> 0x0d77 }
            r0.setOnClickListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r0 = r14.f127b     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r1)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0668
            android.widget.CheckBox r0 = r14.f127b     // Catch:{ Exception -> 0x0d77 }
            r1 = -6710887(0xffffffffff999999, float:NaN)
            r0.setTextColor(r1)     // Catch:{ Exception -> 0x0d77 }
        L_0x0668:
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r1 = 0
            r2 = -2
            r6 = 1050253722(0x3e99999a, float:0.3)
            r0.<init>(r1, r2, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f148d     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r2 = r14.f127b     // Catch:{ Exception -> 0x0d77 }
            r6 = 1
            r1.addView(r2, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r0 = new android.widget.CheckBox     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f138c = r0     // Catch:{ Exception -> 0x0d77 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0d77 }
            r1 = 11
            if (r0 < r1) goto L_0x069a
            android.widget.CheckBox r0 = r14.f138c     // Catch:{ Exception -> 0x109c }
            android.content.res.Resources r1 = r14.getResources()     // Catch:{ Exception -> 0x109c }
            java.lang.String r2 = "btn_check_holo_dark"
            java.lang.String r6 = "drawable"
            java.lang.String r7 = "android"
            int r1 = r1.getIdentifier(r2, r6, r7)     // Catch:{ Exception -> 0x109c }
            r0.setButtonDrawable(r1)     // Catch:{ Exception -> 0x109c }
        L_0x069a:
            android.widget.CheckBox r0 = r14.f138c     // Catch:{ Exception -> 0x0d77 }
            r1 = 4099(0x1003, float:5.744E-42)
            r0.setId(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r1 = r14.f138c     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0e39
            java.lang.String r0 = " Out"
        L_0x06a9:
            r1.setText(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r1 = r14.f138c     // Catch:{ NMJException -> 0x1099 }
            int r0 = r14.f101a     // Catch:{ NMJException -> 0x1099 }
            int r0 = p004de.humatic.nmj.NMJConfig.getIO(r0)     // Catch:{ NMJException -> 0x1099 }
            int r0 = java.lang.Math.abs(r0)     // Catch:{ NMJException -> 0x1099 }
            r2 = 1
            if (r0 != r2) goto L_0x0e3d
            r0 = 1
        L_0x06bc:
            r1.setChecked(r0)     // Catch:{ NMJException -> 0x1099 }
        L_0x06bf:
            android.widget.CheckBox r0 = r14.f138c     // Catch:{ Exception -> 0x0d77 }
            r0.setOnClickListener(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r0 = r14.f138c     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r1)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f159g     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x06d7
            android.widget.CheckBox r0 = r14.f138c     // Catch:{ Exception -> 0x0d77 }
            r1 = -6710887(0xffffffffff999999, float:NaN)
            r0.setTextColor(r1)     // Catch:{ Exception -> 0x0d77 }
        L_0x06d7:
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r1 = 0
            r2 = -2
            r6 = 1050253722(0x3e99999a, float:0.3)
            r0.<init>(r1, r2, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f148d     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r2 = r14.f138c     // Catch:{ Exception -> 0x0d77 }
            r6 = 2
            r1.addView(r2, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f148d     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r1, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r1 = -1
            r5 = 0
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0e45
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r6 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x0e40
            r0 = 1060991140(0x3f3d70a4, float:0.74)
        L_0x0703:
            r2.<init>(r1, r5, r0)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0e53
            android.widget.ScrollView r0 = r14.f109a     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ScrollView r0 = r14.f109a     // Catch:{ Exception -> 0x0d77 }
            r1 = 0
            r3.addView(r0, r1, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ScrollView r0 = r14.f109a     // Catch:{ Exception -> 0x0d77 }
            r1 = 8
            r0.setVisibility(r1)     // Catch:{ Exception -> 0x0d77 }
        L_0x071e:
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f129b = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            float r1 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r5 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x074c
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            r5 = 480(0x1e0, float:6.73E-43)
            if (r1 < r5) goto L_0x074c
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            r5 = 480(0x1e0, float:6.73E-43)
            if (r1 <= r5) goto L_0x0e62
            java.lang.String r1 = "\n \n "
            r0.setText(r1)     // Catch:{ Exception -> 0x0d77 }
            r1 = 3
            r0.setLines(r1)     // Catch:{ Exception -> 0x0d77 }
        L_0x0747:
            android.widget.LinearLayout r1 = r14.f129b     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x074c:
            android.widget.LinearLayout r0 = r14.f129b     // Catch:{ Exception -> 0x0d77 }
            r1 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r0.setBackgroundColor(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f129b     // Catch:{ Exception -> 0x0d77 }
            r1 = 1
            r0.setOrientation(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f129b     // Catch:{ Exception -> 0x0d77 }
            r1 = 17
            r0.setGravity(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ImageView r1 = new android.widget.ImageView     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0e6f
            java.lang.String r0 = "hm2e.png"
            android.graphics.Bitmap r5 = r14.m136a((java.lang.String) r0)     // Catch:{ Exception -> 0x0d77 }
            int r6 = r5.getWidth()     // Catch:{ Exception -> 0x0d77 }
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r7 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0e69
            r0 = 4
        L_0x077d:
            int r6 = r6 / r0
            int r7 = r5.getHeight()     // Catch:{ Exception -> 0x0d77 }
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r8 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x0e6c
            r0 = 4
        L_0x078b:
            int r0 = r7 / r0
            r7 = 1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r5, r6, r0, r7)     // Catch:{ Exception -> 0x0d77 }
            r1.setImageBitmap(r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x0795:
            r0 = 0
            r5 = 10
            r6 = 0
            r7 = 10
            r1.setPadding(r0, r5, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f129b     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r5 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r5.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = 4
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r1 = "nmj "
            r6.<init>(r1)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r1 = p004de.humatic.nmj.NMJConfig.getVersionInfo()     // Catch:{ Exception -> 0x0e81 }
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            float r1 = r14.f100a     // Catch:{ Exception -> 0x0e81 }
            r7 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 < 0) goto L_0x0e7a
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0e81 }
            r7 = 480(0x1e0, float:6.73E-43)
            if (r1 <= r7) goto L_0x0e7a
            java.lang.String r1 = "\n\n"
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
        L_0x07ca:
            java.lang.String[] r7 = p004de.humatic.nmj.NMJConfig.m38a()     // Catch:{ Exception -> 0x0e81 }
            r1 = 0
        L_0x07cf:
            int r8 = r7.length     // Catch:{ Exception -> 0x0e81 }
            if (r1 < r8) goto L_0x0e84
            int r0 = r0 + 6
            float r1 = r14.f100a     // Catch:{ Exception -> 0x0e81 }
            r7 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 < 0) goto L_0x07e7
            int r1 = r14.f152e     // Catch:{ Exception -> 0x0e81 }
            r7 = 480(0x1e0, float:6.73E-43)
            if (r1 <= r7) goto L_0x07e7
            java.lang.String r1 = "\n"
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
        L_0x07e7:
            java.lang.String r1 = "os.name"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{ Exception -> 0x0e81 }
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = " - "
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = "os.version"
            java.lang.String r7 = java.lang.System.getProperty(r7)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x0e81 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = "\n"
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0e81 }
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = "Android: "
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = android.os.Build.VERSION.RELEASE     // Catch:{ Exception -> 0x0e81 }
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = "\n"
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = android.os.Build.MODEL     // Catch:{ Exception -> 0x0e81 }
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = " ("
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = android.os.Build.BOARD     // Catch:{ Exception -> 0x0e81 }
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = ")\n"
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = android.os.Build.MANUFACTURER     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x0e81 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = " "
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = android.os.Build.DISPLAY     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r7 = "\n"
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0e81 }
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
        L_0x0858:
            r5.setLines(r0)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x0d77 }
            r5.setText(r0)     // Catch:{ Exception -> 0x0d77 }
            int r0 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r5.setTextAppearance(r15, r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = -6710887(0xffffffffff999999, float:NaN)
            r5.setTextColor(r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 17
            r5.setGravity(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f129b     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = -2
            r1.<init>(r6, r7)     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r5, r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f129b     // Catch:{ Exception -> 0x0d77 }
            r1 = 1
            r3.addView(r0, r1, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f140c = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f140c     // Catch:{ Exception -> 0x0d77 }
            r1 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r0.setBackgroundColor(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f140c     // Catch:{ Exception -> 0x0d77 }
            r1 = 1
            r0.setOrientation(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r1 = 17
            r0.setGravity(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r5 = 1
            r1.setOrientation(r5)     // Catch:{ Exception -> 0x0d77 }
            r5 = 15
            r6 = 15
            r7 = 15
            r8 = 15
            r1.setPadding(r5, r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            r5 = 17
            r1.setGravity(r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ImageView r5 = new android.widget.ImageView     // Catch:{ Exception -> 0x0d77 }
            r5.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r6 = "scan_bluetooth.png"
            android.graphics.Bitmap r6 = r14.m136a((java.lang.String) r6)     // Catch:{ Exception -> 0x0d77 }
            r5.setImageBitmap(r6)     // Catch:{ Exception -> 0x0d77 }
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 10
            r5.setPadding(r6, r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r5)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r5 = "Start Scan"
            r6 = 4101(0x1005, float:5.747E-42)
            android.widget.Button r5 = r14.m139a((android.content.Context) r15, (java.lang.String) r5, (int) r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r7 = -2
            r8 = -2
            r6.<init>(r7, r8)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r5, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = 0
            r7 = -1
            r8 = 1065353216(0x3f800000, float:1.0)
            r5.<init>(r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r1, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f140c     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            r8 = 1065353216(0x3f800000, float:1.0)
            r5.<init>(r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r0, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f153e = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f153e     // Catch:{ Exception -> 0x0d77 }
            r1 = 17
            r0.setGravity(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ProgressBar r0 = new android.widget.ProgressBar     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f153e     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f153e     // Catch:{ Exception -> 0x0d77 }
            r1 = 4
            r0.setVisibility(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f140c     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = r14.f153e     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            r8 = 1065353216(0x3f800000, float:1.0)
            r5.<init>(r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            r0.addView(r1, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f140c     // Catch:{ Exception -> 0x0d77 }
            r1 = 2
            r3.addView(r0, r1, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f140c     // Catch:{ Exception -> 0x0d77 }
            r1 = 8
            r0.setVisibility(r1)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r1.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f131b = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f131b     // Catch:{ Exception -> 0x0d77 }
            int r2 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f131b     // Catch:{ Exception -> 0x0d77 }
            r2 = 17
            r0.setGravity(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r2 = -1
            r5 = -1
            r0.<init>(r2, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r2 = r14.f131b     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r5 = -1
            r6 = 0
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0ea2
            r0 = 1035489772(0x3db851ec, float:0.09)
        L_0x0973:
            r2.<init>(r5, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 3
            r3.addView(r1, r0, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -13948117(0xffffffffff2b2b2b, float:-2.2752213E38)
            r1.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r2 = 0
            r5 = -1
            r6 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r2, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            boolean r2 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r2 == 0) goto L_0x0ea8
            r2 = 5
            r5 = 1
            r6 = 5
            r7 = 1
            r0.setMargins(r2, r5, r6, r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x0999:
            android.graphics.drawable.BitmapDrawable r2 = r14.f137c     // Catch:{ Exception -> 0x0d77 }
            r5 = 2048(0x800, float:2.87E-42)
            r6 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            android.widget.ImageButton r2 = r14.m142a((android.content.Context) r15, (android.graphics.drawable.BitmapDrawable) r2, (int) r5, (int) r6)     // Catch:{ Exception -> 0x0d77 }
            r5 = 0
            r1.addView(r2, r5, r0)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r2 = "↻"
            r5 = 2049(0x801, float:2.871E-42)
            android.widget.Button r2 = r14.m139a((android.content.Context) r15, (java.lang.String) r2, (int) r5)     // Catch:{ Exception -> 0x0d77 }
            r5 = 1
            r1.addView(r2, r5, r0)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r2 = "+"
            r5 = 2050(0x802, float:2.873E-42)
            android.widget.Button r2 = r14.m139a((android.content.Context) r15, (java.lang.String) r2, (int) r5)     // Catch:{ Exception -> 0x0d77 }
            r5 = 2
            r1.addView(r2, r5, r0)     // Catch:{ Exception -> 0x0d77 }
            android.graphics.drawable.BitmapDrawable r2 = r14.f137c     // Catch:{ Exception -> 0x0d77 }
            r5 = 2051(0x803, float:2.874E-42)
            r6 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            android.widget.ImageButton r2 = r14.m142a((android.content.Context) r15, (android.graphics.drawable.BitmapDrawable) r2, (int) r5, (int) r6)     // Catch:{ Exception -> 0x0d77 }
            r5 = 3
            r1.addView(r2, r5, r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r5 = -1
            r6 = 0
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0eb6
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r7 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0eb1
            r0 = 1041194025(0x3e0f5c29, float:0.14)
        L_0x09e2:
            r2.<init>(r5, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 4
            r3.addView(r1, r0, r2)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = "input_method"
            java.lang.Object r0 = r15.getSystemService(r0)     // Catch:{ Exception -> 0x0d77 }
            android.view.inputmethod.InputMethodManager r0 = (android.view.inputmethod.InputMethodManager) r0     // Catch:{ Exception -> 0x0d77 }
            r14.f104a = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r0 = 1
            r1.setOrientation(r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r1.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r2 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r2.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r2.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r0 = 0
            r5 = -1
            r6 = 1065353216(0x3f800000, float:1.0)
            r3.<init>(r0, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            float r0 = r14.f100a     // Catch:{ Exception -> 0x0d77 }
            r5 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 < 0) goto L_0x0a2a
            boolean r0 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0ec4
            r0 = 5
            r5 = 8
            r6 = 5
            r7 = 0
            r3.setMargins(r0, r5, r6, r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x0a2a:
            android.widget.TextView r0 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f149d = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f149d     // Catch:{ NMJException -> 0x1096 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ NMJException -> 0x1096 }
            java.lang.String r6 = "1 - "
            r5.<init>(r6)     // Catch:{ NMJException -> 0x1096 }
            r6 = 0
            java.lang.String r6 = p004de.humatic.nmj.NMJConfig.getName(r6)     // Catch:{ NMJException -> 0x1096 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ NMJException -> 0x1096 }
            java.lang.String r5 = r5.toString()     // Catch:{ NMJException -> 0x1096 }
            r0.setText(r5)     // Catch:{ NMJException -> 0x1096 }
        L_0x0a4a:
            android.widget.TextView r0 = r14.f149d     // Catch:{ Exception -> 0x0d77 }
            int r5 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f149d     // Catch:{ Exception -> 0x0d77 }
            r5 = 16
            r0.setGravity(r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f149d     // Catch:{ Exception -> 0x0d77 }
            r2.addView(r0, r3)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r0 = new android.widget.Spinner     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            android.widget.ArrayAdapter r5 = new android.widget.ArrayAdapter     // Catch:{ Exception -> 0x0d77 }
            r6 = 17367048(0x1090008, float:2.5162948E-38)
            r7 = 4
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ Exception -> 0x0d77 }
            r8 = 0
            java.lang.String r9 = "All datatypes"
            r7[r8] = r9     // Catch:{ Exception -> 0x0d77 }
            r8 = 1
            java.lang.String r9 = "System common"
            r7[r8] = r9     // Catch:{ Exception -> 0x0d77 }
            r8 = 2
            java.lang.String r9 = "System realtime"
            r7[r8] = r9     // Catch:{ Exception -> 0x0d77 }
            r8 = 3
            java.lang.String r9 = "System exclusive"
            r7[r8] = r9     // Catch:{ Exception -> 0x0d77 }
            r5.<init>(r14, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            r6 = 17367049(0x1090009, float:2.516295E-38)
            r5.setDropDownViewResource(r6)     // Catch:{ Exception -> 0x0d77 }
            r0.setAdapter(r5)     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.NMJConfigDialog$5 r5 = new de.humatic.nmj.NMJConfigDialog$5     // Catch:{ Exception -> 0x0d77 }
            r5.<init>()     // Catch:{ Exception -> 0x0d77 }
            r0.setOnItemSelectedListener(r5)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r5 = new android.widget.CheckBox     // Catch:{ Exception -> 0x0d77 }
            r5.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r14.f105a = r5     // Catch:{ Exception -> 0x0d77 }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0d77 }
            r6 = 11
            if (r5 < r6) goto L_0x0ab2
            android.widget.CheckBox r5 = r14.f105a     // Catch:{ Exception -> 0x1093 }
            android.content.res.Resources r6 = r14.getResources()     // Catch:{ Exception -> 0x1093 }
            java.lang.String r7 = "btn_check_holo_dark"
            java.lang.String r8 = "drawable"
            java.lang.String r9 = "android"
            int r6 = r6.getIdentifier(r7, r8, r9)     // Catch:{ Exception -> 0x1093 }
            r5.setButtonDrawable(r6)     // Catch:{ Exception -> 0x1093 }
        L_0x0ab2:
            android.widget.CheckBox r5 = r14.f105a     // Catch:{ Exception -> 0x0d77 }
            r6 = 1
            r5.setChecked(r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r5 = r14.f105a     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r6 = " hex"
            r5.setText(r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r5 = r14.f105a     // Catch:{ Exception -> 0x0d77 }
            int r6 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r5.setTextAppearance(r15, r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r5 = r14.f105a     // Catch:{ Exception -> 0x0d77 }
            r6 = 30
            r7 = 0
            r8 = 0
            r9 = 0
            r5.setPadding(r6, r7, r8, r9)     // Catch:{ Exception -> 0x0d77 }
            r2.addView(r0, r3)     // Catch:{ Exception -> 0x0d77 }
            android.widget.CheckBox r0 = r14.f105a     // Catch:{ Exception -> 0x0d77 }
            r2.addView(r0, r3)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0ee1
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0ee1
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0edc
            r0 = 1032805417(0x3d8f5c29, float:0.07)
        L_0x0aeb:
            r5.<init>(r6, r7, r0)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r5)     // Catch:{ Exception -> 0x0d77 }
        L_0x0af1:
            android.widget.TextView r2 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r2.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            int r0 = r14.f152e     // Catch:{ Exception -> 0x0d77 }
            float r0 = (float) r0     // Catch:{ Exception -> 0x0d77 }
            float r5 = r2.getTextSize()     // Catch:{ Exception -> 0x0d77 }
            r6 = 1086324736(0x40c00000, float:6.0)
            float r5 = r5 + r6
            float r0 = r0 / r5
            r5 = 1086324736(0x40c00000, float:6.0)
            float r0 = r0 - r5
            int r0 = (int) r0     // Catch:{ Exception -> 0x0d77 }
            r2.setMaxLines(r0)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0efd
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0efd
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0ef8
            r0 = 1063004406(0x3f5c28f6, float:0.86)
        L_0x0b1b:
            r5.<init>(r6, r7, r0)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r5)     // Catch:{ Exception -> 0x0d77 }
        L_0x0b21:
            de.humatic.nmj.NMJConfigDialog$a r0 = new de.humatic.nmj.NMJConfigDialog$a     // Catch:{ Exception -> 0x0d77 }
            r5 = 0
            r0.<init>(r14, r2, r5)     // Catch:{ Exception -> 0x0d77 }
            r14.f114a = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r2 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r2.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r2.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = ""
            r5 = 259(0x103, float:3.63E-43)
            android.widget.Button r0 = r14.m139a((android.content.Context) r15, (java.lang.String) r0, (int) r5)     // Catch:{ Exception -> 0x0d77 }
            r2.addView(r0, r3)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = "Clear"
            r5 = 257(0x101, float:3.6E-43)
            android.widget.Button r0 = r14.m139a((android.content.Context) r15, (java.lang.String) r0, (int) r5)     // Catch:{ Exception -> 0x0d77 }
            r2.addView(r0, r3)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = ""
            r5 = -1
            android.widget.Button r0 = r14.m139a((android.content.Context) r15, (java.lang.String) r0, (int) r5)     // Catch:{ Exception -> 0x0d77 }
            r2.addView(r0, r3)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0f19
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0f19
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r5 = -1
            r6 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0f14
            r0 = 1032805417(0x3d8f5c29, float:0.07)
        L_0x0b67:
            r3.<init>(r5, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r3)     // Catch:{ Exception -> 0x0d77 }
        L_0x0b6d:
            de.humatic.nmj.i r0 = r14.f120a     // Catch:{ Exception -> 0x0d77 }
            r2 = 1
            r0.addView(r1, r2, r4)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r5 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r5.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r0 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r5.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 1
            r5.setOrientation(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r1.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r14.f154e = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f154e     // Catch:{ NMJException -> 0x1090 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ NMJException -> 0x1090 }
            java.lang.String r3 = "  1 - "
            r2.<init>(r3)     // Catch:{ NMJException -> 0x1090 }
            r3 = 0
            java.lang.String r3 = p004de.humatic.nmj.NMJConfig.getName(r3)     // Catch:{ NMJException -> 0x1090 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ NMJException -> 0x1090 }
            java.lang.String r2 = r2.toString()     // Catch:{ NMJException -> 0x1090 }
            r0.setText(r2)     // Catch:{ NMJException -> 0x1090 }
        L_0x0bad:
            android.widget.TextView r0 = r14.f154e     // Catch:{ Exception -> 0x0d77 }
            int r2 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f154e     // Catch:{ Exception -> 0x0d77 }
            r2 = 16
            r0.setGravity(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = r14.f154e     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = 0
            r6 = -1
            r7 = 1065353216(0x3f800000, float:1.0)
            r2.<init>(r3, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r0, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.TextView r0 = new android.widget.TextView     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r2 = "MIDI Ch.:   "
            r0.setText(r2)     // Catch:{ Exception -> 0x0d77 }
            int r2 = r14.f158g     // Catch:{ Exception -> 0x0d77 }
            r0.setTextAppearance(r15, r2)     // Catch:{ Exception -> 0x0d77 }
            r2 = 21
            r0.setGravity(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = 0
            r6 = -1
            r7 = 1065353216(0x3f800000, float:1.0)
            r2.<init>(r3, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r0, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r0 = new android.widget.Spinner     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r14)     // Catch:{ Exception -> 0x0d77 }
            r14.f141c = r0     // Catch:{ Exception -> 0x0d77 }
            android.widget.ArrayAdapter r0 = new android.widget.ArrayAdapter     // Catch:{ Exception -> 0x0d77 }
            r2 = 17367048(0x1090008, float:2.5162948E-38)
            r3 = 16
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0d77 }
            r6 = 0
            java.lang.String r7 = "  1"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 1
            java.lang.String r7 = "  2"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 2
            java.lang.String r7 = "  3"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 3
            java.lang.String r7 = "  4"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 4
            java.lang.String r7 = "  5"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 5
            java.lang.String r7 = "  6"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 6
            java.lang.String r7 = "  7"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 7
            java.lang.String r7 = "  8"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 8
            java.lang.String r7 = "  9"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 9
            java.lang.String r7 = "  10"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 10
            java.lang.String r7 = "  11"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 11
            java.lang.String r7 = "  12"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 12
            java.lang.String r7 = "  13"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 13
            java.lang.String r7 = "  14"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 14
            java.lang.String r7 = "  15"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r6 = 15
            java.lang.String r7 = "  16"
            r3[r6] = r7     // Catch:{ Exception -> 0x0d77 }
            r0.<init>(r14, r2, r3)     // Catch:{ Exception -> 0x0d77 }
            r2 = 17367049(0x1090009, float:2.516295E-38)
            r0.setDropDownViewResource(r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.Spinner r2 = r14.f141c     // Catch:{ Exception -> 0x0d77 }
            r2.setAdapter(r0)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0c6c
            android.widget.Spinner r0 = r14.f141c     // Catch:{ Exception -> 0x0d77 }
            r2 = 0
            r3 = 5
            r6 = 0
            r7 = 5
            r0.setPadding(r2, r3, r6, r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x0c6c:
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0c7b
            android.widget.Spinner r0 = r14.f141c     // Catch:{ Exception -> 0x0d77 }
            r2 = 0
            r3 = 8
            r6 = 0
            r7 = 8
            r0.setPadding(r2, r3, r6, r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x0c7b:
            android.widget.Spinner r0 = r14.f141c     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = 0
            r6 = -1
            r7 = 1065353216(0x3f800000, float:1.0)
            r2.<init>(r3, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r0, r2)     // Catch:{ Exception -> 0x0d77 }
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0f35
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0f35
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = -1
            r6 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0f30
            r0 = 1032805417(0x3d8f5c29, float:0.07)
        L_0x0c9c:
            r2.<init>(r3, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r1, r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x0ca2:
            android.widget.LinearLayout r6 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r6.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = 1
            r6.setOrientation(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r7 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r0 = -1
            r1 = -2
            r7.<init>(r0, r1)     // Catch:{ Exception -> 0x0d77 }
            r2 = 560(0x230, float:7.85E-43)
            r1 = 0
            r0 = 0
            r3 = r0
            r0 = r1
        L_0x0cb8:
            boolean r1 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r1 != 0) goto L_0x0cc0
            boolean r1 = r14.f132b     // Catch:{ Exception -> 0x0d77 }
            if (r1 != 0) goto L_0x0fda
        L_0x0cc0:
            r1 = 4
        L_0x0cc1:
            if (r3 < r1) goto L_0x0f4c
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0fe2
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x0fe2
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r2 = -1
            r3 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0fdd
            r0 = 1061494456(0x3f451eb8, float:0.77)
        L_0x0cd6:
            r1.<init>(r2, r3, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r6, r1)     // Catch:{ Exception -> 0x0d77 }
        L_0x0cdc:
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r0 = 0
            r1 = -1
            r2 = 1065353216(0x3f800000, float:1.0)
            r6.<init>(r0, r1, r2)     // Catch:{ Exception -> 0x0d77 }
            r0 = 8
            r1 = 8
            r2 = 8
            r3 = 8
            r6.setMargins(r0, r1, r2, r3)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r7 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r7.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r7.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            r2 = 0
            r1 = 0
            r0 = 0
        L_0x0cfe:
            r3 = 8
            if (r0 < r3) goto L_0x1001
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x1046
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x1046
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r2 = -1
            r3 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x1041
            r0 = 1031127695(0x3d75c28f, float:0.06)
        L_0x0d15:
            r1.<init>(r2, r3, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r7, r1)     // Catch:{ Exception -> 0x0d77 }
        L_0x0d1b:
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r0 = -14145496(0xffffffffff282828, float:-2.235188E38)
            r1.setBackgroundColor(r0)     // Catch:{ Exception -> 0x0d77 }
            r0 = 0
        L_0x0d27:
            r2 = 7
            if (r0 < r2) goto L_0x105d
            boolean r0 = r14.f150d     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x1079
            boolean r0 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r0 != 0) goto L_0x1079
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = -1
            r6 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x1074
            r0 = 1031127695(0x3d75c28f, float:0.06)
        L_0x0d3d:
            r2.<init>(r3, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r1, r2)     // Catch:{ Exception -> 0x0d77 }
        L_0x0d43:
            de.humatic.nmj.i r0 = r14.f120a     // Catch:{ Exception -> 0x0d77 }
            r1 = 2
            r0.addView(r5, r1, r4)     // Catch:{ Exception -> 0x0d77 }
            int r0 = r14.f101a     // Catch:{ Exception -> 0x0d77 }
            r1 = -1
            if (r0 == r1) goto L_0x0d53
            int r0 = r14.f101a     // Catch:{ Exception -> 0x0d77 }
            r14.m155a((int) r0)     // Catch:{ Exception -> 0x0d77 }
        L_0x0d53:
            de.humatic.nmj.i r0 = r14.f120a     // Catch:{ Exception -> 0x0d77 }
            int r1 = r14.f160h     // Catch:{ Exception -> 0x0d77 }
            r0.setDisplayedChild(r1)     // Catch:{ Exception -> 0x0d77 }
        L_0x0d5a:
            return
        L_0x0d5b:
            r0 = 0
            goto L_0x002f
        L_0x0d5e:
            r0 = 0
            goto L_0x0046
        L_0x0d61:
            r0 = 0
            goto L_0x006a
        L_0x0d64:
            r0 = 0
            goto L_0x00ae
        L_0x0d67:
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = 0
            r5 = -1
            r6 = 1053609165(0x3ecccccd, float:0.4)
            r0.<init>(r3, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            r3 = 0
            r2.addView(r1, r3, r0)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x00c9
        L_0x0d77:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0d5a
        L_0x0d7c:
            boolean r5 = r14.f155e     // Catch:{ Exception -> 0x0d77 }
            if (r5 == 0) goto L_0x00e7
            r5 = 1093664768(0x41300000, float:11.0)
            r0.setTextSize(r5)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x00e7
        L_0x0d87:
            r5 = 10
            r6 = 3
            r7 = 3
            r8 = 3
            r0.setPadding(r5, r6, r7, r8)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0107
        L_0x0d91:
            r0 = 1031127695(0x3d75c28f, float:0.06)
            goto L_0x0132
        L_0x0d96:
            de.humatic.nmj.e r3 = r14.f119a     // Catch:{ Exception -> 0x0d77 }
            de.humatic.nmj.q r5 = new de.humatic.nmj.q     // Catch:{ Exception -> 0x0d77 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0d77 }
            r3.add(r5)     // Catch:{ Exception -> 0x0d77 }
            int r0 = r0 + 1
            goto L_0x0156
        L_0x0da4:
            r0 = 1062501089(0x3f547ae1, float:0.83)
            goto L_0x0172
        L_0x0da9:
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0db2
            r0 = 1061830001(0x3f4a3d71, float:0.79)
            goto L_0x0172
        L_0x0db2:
            r0 = 1062836634(0x3f59999a, float:0.85)
            goto L_0x0172
        L_0x0db7:
            r0 = 1038174126(0x3de147ae, float:0.11)
            goto L_0x01ba
        L_0x0dbc:
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0dc5
            r0 = 1040522936(0x3e051eb8, float:0.13)
            goto L_0x01ba
        L_0x0dc5:
            r0 = 1035489772(0x3db851ec, float:0.09)
            goto L_0x01ba
        L_0x0dca:
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r1 = 0
            r5 = -1
            r6 = 1058642330(0x3f19999a, float:0.6)
            r0.<init>(r1, r5, r6)     // Catch:{ Exception -> 0x0d77 }
            r1 = 1
            r2.addView(r3, r1, r0)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x01ea
        L_0x0dda:
            r0 = 0
            goto L_0x0228
        L_0x0ddd:
            r1 = move-exception
        L_0x0dde:
            r1 = r0
            goto L_0x0229
        L_0x0de1:
            java.lang.Object r0 = r8.nextElement()     // Catch:{ SocketException -> 0x0df0, Exception -> 0x0e21 }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ SocketException -> 0x0df0, Exception -> 0x0e21 }
            java.lang.String r0 = r0.getName()     // Catch:{ SocketException -> 0x0df0, Exception -> 0x0e21 }
            r7.add(r0)     // Catch:{ SocketException -> 0x0df0, Exception -> 0x0e21 }
            goto L_0x0487
        L_0x0df0:
            r0 = move-exception
            java.lang.String r8 = r0.toString()     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r9 = "unkown error"
            int r8 = r8.indexOf(r9)     // Catch:{ Exception -> 0x0d77 }
            r9 = -1
            if (r8 == r9) goto L_0x0e1c
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ Exception -> 0x0d77 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0d77 }
            r9.<init>(r0)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = "\nDid you grant the required network permission?"
            java.lang.StringBuilder r0 = r9.append(r0)     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0d77 }
            r8.println(r0)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x048d
        L_0x0e1c:
            r0.printStackTrace()     // Catch:{ Exception -> 0x0d77 }
            goto L_0x048d
        L_0x0e21:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x0d77 }
            goto L_0x048d
        L_0x0e27:
            java.lang.String r0 = "Port (Rem./Loc.):"
            goto L_0x0507
        L_0x0e2b:
            java.lang.String r0 = "Port (Remote / Local): "
            r6.setText(r0)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x050a
        L_0x0e32:
            java.lang.String r0 = " Input"
            goto L_0x063f
        L_0x0e36:
            r0 = 0
            goto L_0x064d
        L_0x0e39:
            java.lang.String r0 = " Output"
            goto L_0x06a9
        L_0x0e3d:
            r0 = 0
            goto L_0x06bc
        L_0x0e40:
            r0 = 1061662228(0x3f47ae14, float:0.78)
            goto L_0x0703
        L_0x0e45:
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0e4e
            r0 = 1059145646(0x3f2147ae, float:0.63)
            goto L_0x0703
        L_0x0e4e:
            r0 = 1059481190(0x3f266666, float:0.65)
            goto L_0x0703
        L_0x0e53:
            android.widget.LinearLayout r0 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r1 = 0
            r3.addView(r0, r1, r2)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout r0 = r14.f107a     // Catch:{ Exception -> 0x0d77 }
            r1 = 8
            r0.setVisibility(r1)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x071e
        L_0x0e62:
            java.lang.String r1 = "\n"
            r0.setText(r1)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0747
        L_0x0e69:
            r0 = 2
            goto L_0x077d
        L_0x0e6c:
            r0 = 2
            goto L_0x078b
        L_0x0e6f:
            java.lang.String r0 = "hm2e.png"
            android.graphics.Bitmap r0 = r14.m136a((java.lang.String) r0)     // Catch:{ Exception -> 0x0d77 }
            r1.setImageBitmap(r0)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0795
        L_0x0e7a:
            java.lang.String r1 = "\n"
            r6.append(r1)     // Catch:{ Exception -> 0x0e81 }
            goto L_0x07ca
        L_0x0e81:
            r1 = move-exception
            goto L_0x0858
        L_0x0e84:
            int r0 = r0 + 1
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0e81 }
            r9 = r7[r1]     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0e81 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r9 = "\n"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0e81 }
            r6.append(r8)     // Catch:{ Exception -> 0x0e81 }
            int r1 = r1 + 1
            goto L_0x07cf
        L_0x0ea2:
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            r0 = 1048576000(0x3e800000, float:0.25)
            goto L_0x0973
        L_0x0ea8:
            r2 = 5
            r5 = 5
            r6 = 5
            r7 = 5
            r0.setMargins(r2, r5, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0999
        L_0x0eb1:
            r0 = 1038174126(0x3de147ae, float:0.11)
            goto L_0x09e2
        L_0x0eb6:
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0ebf
            r0 = 1036831949(0x3dcccccd, float:0.1)
            goto L_0x09e2
        L_0x0ebf:
            r0 = 1035489772(0x3db851ec, float:0.09)
            goto L_0x09e2
        L_0x0ec4:
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0ed3
            r0 = 15
            r5 = 10
            r6 = 5
            r7 = 5
            r3.setMargins(r0, r5, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0a2a
        L_0x0ed3:
            r0 = 5
            r5 = 5
            r6 = 5
            r7 = 5
            r3.setMargins(r0, r5, r6, r7)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0a2a
        L_0x0edc:
            r0 = 1039516303(0x3df5c28f, float:0.12)
            goto L_0x0aeb
        L_0x0ee1:
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0ef4
            r0 = 1039516303(0x3df5c28f, float:0.12)
        L_0x0eec:
            r5.<init>(r6, r7, r0)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r5)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0af1
        L_0x0ef4:
            r0 = 1043207291(0x3e2e147b, float:0.17)
            goto L_0x0eec
        L_0x0ef8:
            r0 = 1061326684(0x3f428f5c, float:0.76)
            goto L_0x0b1b
        L_0x0efd:
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r6 = -1
            r7 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0f10
            r0 = 1061326684(0x3f428f5c, float:0.76)
        L_0x0f08:
            r5.<init>(r6, r7, r0)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r5)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0b21
        L_0x0f10:
            r0 = 1059648963(0x3f28f5c3, float:0.66)
            goto L_0x0f08
        L_0x0f14:
            r0 = 1039516303(0x3df5c28f, float:0.12)
            goto L_0x0b67
        L_0x0f19:
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r5 = -1
            r6 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0f2c
            r0 = 1039516303(0x3df5c28f, float:0.12)
        L_0x0f24:
            r3.<init>(r5, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r3)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0b6d
        L_0x0f2c:
            r0 = 1043207291(0x3e2e147b, float:0.17)
            goto L_0x0f24
        L_0x0f30:
            r0 = 1036831949(0x3dcccccd, float:0.1)
            goto L_0x0c9c
        L_0x0f35:
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = -1
            r6 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0f48
            r0 = 1039516303(0x3df5c28f, float:0.12)
        L_0x0f40:
            r2.<init>(r3, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r1, r2)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0ca2
        L_0x0f48:
            r0 = 1041865114(0x3e19999a, float:0.15)
            goto L_0x0f40
        L_0x0f4c:
            android.widget.LinearLayout r8 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x0d77 }
            r8.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r1 = 0
            r9 = 20
            r10 = 0
            r11 = 20
            r8.setPadding(r1, r9, r10, r11)     // Catch:{ Exception -> 0x0d77 }
            android.widget.SeekBar r9 = new android.widget.SeekBar     // Catch:{ Exception -> 0x0d77 }
            r9.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r1 = 1000(0x3e8, float:1.401E-42)
            r9.setMax(r1)     // Catch:{ Exception -> 0x0d77 }
            r9.setOnSeekBarChangeListener(r14)     // Catch:{ Exception -> 0x0d77 }
            int r10 = r2 + 1
            r9.setId(r2)     // Catch:{ Exception -> 0x0d77 }
            if (r3 != 0) goto L_0x0f99
            r1 = 20
            r2 = 15
            r11 = 20
            r12 = 15
            r9.setPadding(r1, r2, r11, r12)     // Catch:{ Exception -> 0x0d77 }
        L_0x0f79:
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r11 = 0
            r12 = -2
            if (r3 != 0) goto L_0x0fa3
            r1 = 1065353216(0x3f800000, float:1.0)
        L_0x0f81:
            r2.<init>(r11, r12, r1)     // Catch:{ Exception -> 0x0d77 }
            r8.addView(r9, r2)     // Catch:{ Exception -> 0x0d77 }
            if (r3 != 0) goto L_0x0fa7
            int r1 = r10 + 1
            r2 = 500(0x1f4, float:7.0E-43)
            r9.setProgress(r2)     // Catch:{ Exception -> 0x0d77 }
            r6.addView(r8, r7)     // Catch:{ Exception -> 0x0d77 }
        L_0x0f93:
            int r2 = r3 + 1
            r3 = r2
            r2 = r1
            goto L_0x0cb8
        L_0x0f99:
            r1 = 20
            r2 = 0
            r11 = 20
            r12 = 0
            r9.setPadding(r1, r2, r11, r12)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0f79
        L_0x0fa3:
            r1 = 1062836634(0x3f59999a, float:0.85)
            goto L_0x0f81
        L_0x0fa7:
            android.widget.EditText r9 = new android.widget.EditText     // Catch:{ Exception -> 0x0d77 }
            r9.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r1 = 2
            r9.setInputType(r1)     // Catch:{ Exception -> 0x0d77 }
            int r2 = r10 + 1
            r9.setId(r10)     // Catch:{ Exception -> 0x0d77 }
            r1 = 17
            r9.setGravity(r1)     // Catch:{ Exception -> 0x0d77 }
            int[] r10 = r14.f151d     // Catch:{ Exception -> 0x0d77 }
            int r1 = r0 + 1
            r0 = r10[r0]     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0d77 }
            r9.setText(r0)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r10 = 0
            r11 = -2
            r12 = 1041865114(0x3e19999a, float:0.15)
            r0.<init>(r10, r11, r12)     // Catch:{ Exception -> 0x0d77 }
            r8.addView(r9, r0)     // Catch:{ Exception -> 0x0d77 }
            r6.addView(r8, r7)     // Catch:{ Exception -> 0x0d77 }
            r0 = r1
            r1 = r2
            goto L_0x0f93
        L_0x0fda:
            r1 = 3
            goto L_0x0cc1
        L_0x0fdd:
            r0 = 1059313418(0x3f23d70a, float:0.64)
            goto L_0x0cd6
        L_0x0fe2:
            android.widget.ScrollView r1 = new android.widget.ScrollView     // Catch:{ Exception -> 0x0d77 }
            r1.<init>(r15)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r6)     // Catch:{ Exception -> 0x0d77 }
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = -1
            r6 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x0ffd
            r0 = 1059816735(0x3f2b851f, float:0.67)
        L_0x0ff5:
            r2.<init>(r3, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r1, r2)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0cdc
        L_0x0ffd:
            r0 = 1057635697(0x3f0a3d71, float:0.54)
            goto L_0x0ff5
        L_0x1001:
            if (r0 == 0) goto L_0x1031
            r3 = 3
            if (r0 == r3) goto L_0x1031
            r3 = 7
            if (r0 == r3) goto L_0x1031
            java.lang.String[] r3 = r14.f135b     // Catch:{ Exception -> 0x0d77 }
            r8 = r3[r0]     // Catch:{ Exception -> 0x0d77 }
            int r3 = r2 + 1
            int r2 = r2 + 512
            android.widget.Button r2 = r14.m170b(r15, r8, r2)     // Catch:{ Exception -> 0x0d77 }
            r13 = r3
            r3 = r2
            r2 = r13
        L_0x1018:
            java.lang.String[] r8 = r14.f135b     // Catch:{ Exception -> 0x0d77 }
            r8 = r8[r0]     // Catch:{ Exception -> 0x0d77 }
            java.lang.String r9 = "#"
            int r8 = r8.indexOf(r9)     // Catch:{ Exception -> 0x0d77 }
            r9 = -1
            if (r8 == r9) goto L_0x102a
            r8 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3.setBackgroundColor(r8)     // Catch:{ Exception -> 0x0d77 }
        L_0x102a:
            r7.addView(r3, r6)     // Catch:{ Exception -> 0x0d77 }
            int r0 = r0 + 1
            goto L_0x0cfe
        L_0x1031:
            java.lang.String[] r3 = r14.f135b     // Catch:{ Exception -> 0x0d77 }
            r8 = r3[r0]     // Catch:{ Exception -> 0x0d77 }
            int r3 = r1 + 1
            int r1 = r1 + 544
            android.widget.Button r1 = r14.m139a((android.content.Context) r15, (java.lang.String) r8, (int) r1)     // Catch:{ Exception -> 0x0d77 }
            r13 = r3
            r3 = r1
            r1 = r13
            goto L_0x1018
        L_0x1041:
            r0 = 1038174126(0x3de147ae, float:0.11)
            goto L_0x0d15
        L_0x1046:
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r2 = -1
            r3 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x1059
            r0 = 1034818683(0x3dae147b, float:0.085)
        L_0x1051:
            r1.<init>(r2, r3, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r7, r1)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0d1b
        L_0x1059:
            r0 = 1040858481(0x3e0a3d71, float:0.135)
            goto L_0x1051
        L_0x105d:
            java.lang.String[] r2 = r14.f124a     // Catch:{ Exception -> 0x0d77 }
            r2 = r2[r0]     // Catch:{ Exception -> 0x0d77 }
            int r3 = r0 + 528
            android.widget.Button r2 = r14.m170b(r15, r2, r3)     // Catch:{ Exception -> 0x0d77 }
            r3 = -1118482(0xffffffffffeeeeee, float:NaN)
            r2.setBackgroundColor(r3)     // Catch:{ Exception -> 0x0d77 }
            r1.addView(r2, r6)     // Catch:{ Exception -> 0x0d77 }
            int r0 = r0 + 1
            goto L_0x0d27
        L_0x1074:
            r0 = 1038174126(0x3de147ae, float:0.11)
            goto L_0x0d3d
        L_0x1079:
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x0d77 }
            r3 = -1
            r6 = 0
            boolean r0 = r14.f143c     // Catch:{ Exception -> 0x0d77 }
            if (r0 == 0) goto L_0x108c
            r0 = 1034818683(0x3dae147b, float:0.085)
        L_0x1084:
            r2.<init>(r3, r6, r0)     // Catch:{ Exception -> 0x0d77 }
            r5.addView(r1, r2)     // Catch:{ Exception -> 0x0d77 }
            goto L_0x0d43
        L_0x108c:
            r0 = 1040858481(0x3e0a3d71, float:0.135)
            goto L_0x1084
        L_0x1090:
            r0 = move-exception
            goto L_0x0bad
        L_0x1093:
            r5 = move-exception
            goto L_0x0ab2
        L_0x1096:
            r0 = move-exception
            goto L_0x0a4a
        L_0x1099:
            r0 = move-exception
            goto L_0x06bf
        L_0x109c:
            r0 = move-exception
            goto L_0x069a
        L_0x109f:
            r0 = move-exception
            goto L_0x0650
        L_0x10a2:
            r0 = move-exception
            goto L_0x0630
        L_0x10a5:
            r0 = move-exception
            goto L_0x0595
        L_0x10a8:
            r0 = move-exception
            goto L_0x0548
        L_0x10ab:
            r0 = move-exception
            goto L_0x04b4
        L_0x10ae:
            r6 = move-exception
            goto L_0x03f1
        L_0x10b1:
            r6 = move-exception
            goto L_0x0384
        L_0x10b4:
            r6 = move-exception
            goto L_0x02cf
        L_0x10b7:
            r0 = move-exception
            r0 = r1
            goto L_0x0dde
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfigDialog.m156a(android.content.Context):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0329 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0374 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0377 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x037a A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x037d A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0380 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0384 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f0 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x010f A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012e A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0136 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0148 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x018c A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01a5 A[Catch:{ Exception -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0269 A[Catch:{ Exception -> 0x0219 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m155a(int r12) {
        /*
            r11 = this;
            r10 = 7
            r9 = 6
            r8 = 2
            r2 = 1
            r0 = 0
            r1 = 1
            r11.f121a = r1     // Catch:{ Exception -> 0x0219 }
            if (r12 < 0) goto L_0x0010
            int r1 = p004de.humatic.nmj.NMJConfig.getNumChannels()     // Catch:{ Exception -> 0x0219 }
            if (r12 < r1) goto L_0x0011
        L_0x0010:
            return
        L_0x0011:
            int r1 = p004de.humatic.nmj.NMJConfig.getMode(r12)     // Catch:{ Exception -> 0x0219 }
            if (r1 > r10) goto L_0x0010
            de.humatic.nmj.NetworkMidiSystem r1 = p004de.humatic.nmj.NetworkMidiSystem.get()     // Catch:{ Exception -> 0x021f }
            int r3 = p004de.humatic.nmj.NMJConfig.getIO(r12)     // Catch:{ Exception -> 0x021f }
            boolean r1 = r1.isOpen(r3, r12)     // Catch:{ Exception -> 0x021f }
            r4 = r1
        L_0x0024:
            android.widget.TextView r1 = r11.f131b     // Catch:{ Exception -> 0x0219 }
            java.lang.String r3 = ""
            r1.setText(r3)     // Catch:{ Exception -> 0x0219 }
            android.widget.LinearLayout r1 = r11.f129b     // Catch:{ Exception -> 0x0219 }
            r3 = 8
            r1.setVisibility(r3)     // Catch:{ Exception -> 0x0219 }
            android.widget.LinearLayout r1 = r11.f140c     // Catch:{ Exception -> 0x0219 }
            r3 = 8
            r1.setVisibility(r3)     // Catch:{ Exception -> 0x0219 }
            boolean r1 = r11.f132b     // Catch:{ Exception -> 0x0219 }
            if (r1 == 0) goto L_0x0223
            android.widget.ScrollView r1 = r11.f109a     // Catch:{ Exception -> 0x0219 }
            r3 = 0
            r1.setVisibility(r3)     // Catch:{ Exception -> 0x0219 }
        L_0x0043:
            r11.f101a = r12     // Catch:{ Exception -> 0x0219 }
            if (r4 != 0) goto L_0x022b
            android.widget.TextView r1 = r11.f111a     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r5 = "Channel "
            r3.<init>(r5)     // Catch:{ Exception -> 0x0219 }
            int r5 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r5 = r5 + 1
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0219 }
            r1.setText(r3)     // Catch:{ Exception -> 0x0219 }
        L_0x005f:
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r3 = p004de.humatic.nmj.NMJConfig.getMode(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f106a     // Catch:{ Exception -> 0x0219 }
            int r5 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            java.lang.String r5 = p004de.humatic.nmj.NMJConfig.getName(r5)     // Catch:{ Exception -> 0x0219 }
            r1.setText(r5)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r5 = r11.f106a     // Catch:{ Exception -> 0x0219 }
            if (r4 != 0) goto L_0x024b
            if (r3 == r2) goto L_0x0078
            if (r3 != r9) goto L_0x0080
        L_0x0078:
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            java.lang.String r1 = p004de.humatic.nmj.NMJConfig.getIP(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 != 0) goto L_0x024b
        L_0x0080:
            r1 = r2
        L_0x0081:
            r5.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.Spinner r1 = r11.f110a     // Catch:{ Exception -> 0x0219 }
            int r5 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r5 = p004de.humatic.nmj.NMJConfig.getMode(r5)     // Catch:{ Exception -> 0x0219 }
            r1.setSelection(r5)     // Catch:{ Exception -> 0x0219 }
            android.widget.Spinner r5 = r11.f110a     // Catch:{ Exception -> 0x0219 }
            if (r4 != 0) goto L_0x0260
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r6 = p004de.humatic.nmj.NMJConfig.getMode(r1)     // Catch:{ Exception -> 0x0219 }
            r7 = 5
            if (r6 == r7) goto L_0x009e
            if (r6 != r10) goto L_0x024e
        L_0x009e:
            r1 = r0
        L_0x009f:
            if (r1 == 0) goto L_0x0260
            r1 = r2
        L_0x00a2:
            r5.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f139c     // Catch:{ Exception -> 0x0219 }
            int r5 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r5 = p004de.humatic.nmj.NMJConfig.getPort(r5)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0219 }
            r1.setText(r5)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f147d     // Catch:{ Exception -> 0x0219 }
            int r5 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r5 = p004de.humatic.nmj.NMJConfig.getLocalPort(r5)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0219 }
            r1.setText(r5)     // Catch:{ Exception -> 0x0219 }
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r1 = p004de.humatic.nmj.NMJConfig.getNetworkAdapter(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.Spinner r5 = r11.f130b     // Catch:{ Exception -> 0x0219 }
            r5.setSelection(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r5 = r11.f147d     // Catch:{ Exception -> 0x0219 }
            if (r4 != 0) goto L_0x0263
            if (r3 == r2) goto L_0x00d6
            if (r3 != r9) goto L_0x0263
        L_0x00d6:
            r1 = r2
        L_0x00d7:
            r5.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.Spinner r5 = r11.f130b     // Catch:{ Exception -> 0x0219 }
            if (r4 != 0) goto L_0x0266
            if (r3 == r8) goto L_0x0266
            r1 = 4
            if (r3 == r1) goto L_0x0266
            r1 = 5
            if (r3 == r1) goto L_0x0266
            if (r3 == r10) goto L_0x0266
            r1 = r2
        L_0x00e9:
            r5.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.LinearLayout r5 = r11.f148d     // Catch:{ Exception -> 0x0219 }
            if (r3 != 0) goto L_0x0269
            r1 = r0
        L_0x00f1:
            r5.setVisibility(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r5 = r11.f128b     // Catch:{ Exception -> 0x0219 }
            if (r4 != 0) goto L_0x026d
            if (r3 == r2) goto L_0x00fe
            if (r3 == 0) goto L_0x00fe
            if (r3 != r9) goto L_0x026d
        L_0x00fe:
            r1 = r2
        L_0x00ff:
            r5.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f128b     // Catch:{ Exception -> 0x0219 }
            int r5 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            java.lang.String r5 = p004de.humatic.nmj.NMJConfig.getIP(r5)     // Catch:{ Exception -> 0x0219 }
            r1.setText(r5)     // Catch:{ Exception -> 0x0219 }
            if (r3 != r2) goto L_0x0329
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            java.lang.String r1 = p004de.humatic.nmj.NMJConfig.getIP(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 == 0) goto L_0x0270
            android.widget.EditText r1 = r11.f128b     // Catch:{ Exception -> 0x0219 }
            r3 = 0
            r1.setEnabled(r3)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f139c     // Catch:{ Exception -> 0x0219 }
            r3 = r1
            r1 = r0
        L_0x0121:
            r3.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
        L_0x0124:
            android.widget.CheckBox r3 = r11.f127b     // Catch:{ Exception -> 0x0219 }
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r1 = p004de.humatic.nmj.NMJConfig.getIO(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 > 0) goto L_0x0374
            r1 = r2
        L_0x012f:
            r3.setChecked(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.CheckBox r3 = r11.f127b     // Catch:{ Exception -> 0x0219 }
            if (r4 == 0) goto L_0x0377
            r1 = r0
        L_0x0137:
            r3.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.CheckBox r3 = r11.f138c     // Catch:{ Exception -> 0x0219 }
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r1 = p004de.humatic.nmj.NMJConfig.getIO(r1)     // Catch:{ Exception -> 0x0219 }
            int r1 = java.lang.Math.abs(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 != r2) goto L_0x037a
            r1 = r2
        L_0x0149:
            r3.setChecked(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.CheckBox r1 = r11.f138c     // Catch:{ Exception -> 0x0219 }
            if (r4 == 0) goto L_0x037d
        L_0x0150:
            r1.setEnabled(r0)     // Catch:{ Exception -> 0x0219 }
            de.humatic.nmj.NMJConfigDialog$6 r0 = new de.humatic.nmj.NMJConfigDialog$6     // Catch:{ Exception -> 0x0219 }
            r0.<init>()     // Catch:{ Exception -> 0x0219 }
            android.widget.CheckBox r1 = r11.f127b     // Catch:{ Exception -> 0x0219 }
            r1.setOnCheckedChangeListener(r0)     // Catch:{ Exception -> 0x0219 }
            android.widget.CheckBox r1 = r11.f138c     // Catch:{ Exception -> 0x0219 }
            r1.setOnCheckedChangeListener(r0)     // Catch:{ Exception -> 0x0219 }
            r0 = 2049(0x801, float:2.871E-42)
            android.view.View r0 = r11.findViewById(r0)     // Catch:{ Exception -> 0x0219 }
            android.widget.Button r0 = (android.widget.Button) r0     // Catch:{ Exception -> 0x0219 }
            java.lang.String r1 = "-"
            r0.setText(r1)     // Catch:{ Exception -> 0x0219 }
            r0 = 2050(0x802, float:2.873E-42)
            android.view.View r0 = r11.findViewById(r0)     // Catch:{ Exception -> 0x0219 }
            android.widget.Button r0 = (android.widget.Button) r0     // Catch:{ Exception -> 0x0219 }
            java.lang.String r1 = "+"
            r0.setText(r1)     // Catch:{ Exception -> 0x0219 }
            r0 = 2048(0x800, float:2.87E-42)
            android.view.View r0 = r11.findViewById(r0)     // Catch:{ Exception -> 0x0219 }
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0     // Catch:{ Exception -> 0x0219 }
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r1 = p004de.humatic.nmj.NMJConfig.getIO(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 > 0) goto L_0x0380
            android.graphics.drawable.BitmapDrawable r1 = r11.f103a     // Catch:{ Exception -> 0x0219 }
        L_0x018e:
            r0.setImageDrawable(r1)     // Catch:{ Exception -> 0x0219 }
            r0 = 2051(0x803, float:2.874E-42)
            android.view.View r0 = r11.findViewById(r0)     // Catch:{ Exception -> 0x0219 }
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0     // Catch:{ Exception -> 0x0219 }
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r1 = p004de.humatic.nmj.NMJConfig.getIO(r1)     // Catch:{ Exception -> 0x0219 }
            int r1 = java.lang.Math.abs(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 != r2) goto L_0x0384
            android.graphics.drawable.BitmapDrawable r1 = r11.f126b     // Catch:{ Exception -> 0x0219 }
        L_0x01a7:
            r0.setImageDrawable(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.TextView r0 = r11.f149d     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            int r2 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r2 = r2 + 1
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0219 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r2 = " - "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0219 }
            int r2 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            java.lang.String r2 = p004de.humatic.nmj.NMJConfig.getName(r2)     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0219 }
            r0.setText(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.TextView r0 = r11.f154e     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r2 = "  "
            r1.<init>(r2)     // Catch:{ Exception -> 0x0219 }
            int r2 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r2 = r2 + 1
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r2 = " - "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0219 }
            int r2 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            java.lang.String r2 = p004de.humatic.nmj.NMJConfig.getName(r2)     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0219 }
            r0.setText(r1)     // Catch:{ Exception -> 0x0219 }
            r0 = 4105(0x1009, float:5.752E-42)
            r11.f136c = r0     // Catch:{ Exception -> 0x0219 }
            de.humatic.nmj.e r0 = r11.f119a     // Catch:{ Exception -> 0x0219 }
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            r0.mo8123a((int) r1)     // Catch:{ Exception -> 0x0219 }
            de.humatic.nmj.e r0 = r11.f119a     // Catch:{ Exception -> 0x0219 }
            r0.notifyDataSetChanged()     // Catch:{ Exception -> 0x0219 }
            android.os.Message r0 = android.os.Message.obtain()     // Catch:{ Exception -> 0x0219 }
            r1 = 256(0x100, float:3.59E-43)
            r0.what = r1     // Catch:{ Exception -> 0x0219 }
            de.humatic.nmj.NMJConfigDialog$b r1 = r11.f115a     // Catch:{ Exception -> 0x0219 }
            r2 = 500(0x1f4, double:2.47E-321)
            r1.sendMessageDelayed(r0, r2)     // Catch:{ Exception -> 0x0219 }
            goto L_0x0010
        L_0x0219:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0010
        L_0x021f:
            r1 = move-exception
            r4 = r0
            goto L_0x0024
        L_0x0223:
            android.widget.LinearLayout r1 = r11.f107a     // Catch:{ Exception -> 0x0219 }
            r3 = 0
            r1.setVisibility(r3)     // Catch:{ Exception -> 0x0219 }
            goto L_0x0043
        L_0x022b:
            android.widget.TextView r1 = r11.f111a     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r5 = "Channel "
            r3.<init>(r5)     // Catch:{ Exception -> 0x0219 }
            int r5 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            int r5 = r5 + 1
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r5 = " - currently open, can't edit"
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0219 }
            r1.setText(r3)     // Catch:{ Exception -> 0x0219 }
            goto L_0x005f
        L_0x024b:
            r1 = r0
            goto L_0x0081
        L_0x024e:
            if (r6 == r2) goto L_0x0254
            if (r6 == r8) goto L_0x0254
            if (r6 != r9) goto L_0x025d
        L_0x0254:
            java.lang.String r1 = p004de.humatic.nmj.NMJConfig.getIP(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 == 0) goto L_0x025d
            r1 = r0
            goto L_0x009f
        L_0x025d:
            r1 = r2
            goto L_0x009f
        L_0x0260:
            r1 = r0
            goto L_0x00a2
        L_0x0263:
            r1 = r0
            goto L_0x00d7
        L_0x0266:
            r1 = r0
            goto L_0x00e9
        L_0x0269:
            r1 = 8
            goto L_0x00f1
        L_0x026d:
            r1 = r0
            goto L_0x00ff
        L_0x0270:
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            de.humatic.nmj.v[] r3 = p004de.humatic.nmj.NMJConfig.m39a((int) r1)     // Catch:{ Exception -> 0x0219 }
            if (r3 == 0) goto L_0x0293
            int r1 = r3.length     // Catch:{ Exception -> 0x0219 }
            if (r1 <= 0) goto L_0x0293
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0219 }
            r5.<init>()     // Catch:{ Exception -> 0x0219 }
            r1 = r0
        L_0x0281:
            int r6 = r3.length     // Catch:{ Exception -> 0x0219 }
            if (r1 < r6) goto L_0x0299
            android.widget.EditText r1 = r11.f128b     // Catch:{ Exception -> 0x0219 }
            java.lang.String r3 = r5.toString()     // Catch:{ Exception -> 0x0219 }
            r1.setText(r3)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f128b     // Catch:{ Exception -> 0x0219 }
            r3 = 1
            r1.setEnabled(r3)     // Catch:{ Exception -> 0x0219 }
        L_0x0293:
            android.widget.EditText r1 = r11.f139c     // Catch:{ Exception -> 0x0219 }
            r3 = r1
            r1 = r0
            goto L_0x0121
        L_0x0299:
            r6 = r3[r1]     // Catch:{ Exception -> 0x0219 }
            r7 = 2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = "ServiceRecord id "
            r8.<init>(r9)     // Catch:{ Exception -> 0x0219 }
            int r9 = r6.f562a     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0219 }
            p004de.humatic.nmj.C0484p.logln(r7, r8)     // Catch:{ Exception -> 0x0219 }
            r7 = 2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = "name "
            r8.<init>(r9)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = r6.f563a     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0219 }
            p004de.humatic.nmj.C0484p.logln(r7, r8)     // Catch:{ Exception -> 0x0219 }
            r7 = 2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = "mode "
            r8.<init>(r9)     // Catch:{ Exception -> 0x0219 }
            int r9 = r6.f564b     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0219 }
            p004de.humatic.nmj.C0484p.logln(r7, r8)     // Catch:{ Exception -> 0x0219 }
            r7 = 2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = "ip "
            r8.<init>(r9)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = r6.f565b     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0219 }
            p004de.humatic.nmj.C0484p.logln(r7, r8)     // Catch:{ Exception -> 0x0219 }
            r7 = 2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = "port "
            r8.<init>(r9)     // Catch:{ Exception -> 0x0219 }
            int r9 = r6.f566c     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0219 }
            p004de.humatic.nmj.C0484p.logln(r7, r8)     // Catch:{ Exception -> 0x0219 }
            r7 = 2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0219 }
            java.lang.String r9 = "local port "
            r8.<init>(r9)     // Catch:{ Exception -> 0x0219 }
            int r6 = r6.f568d     // Catch:{ Exception -> 0x0219 }
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0219 }
            p004de.humatic.nmj.C0484p.logln(r7, r6)     // Catch:{ Exception -> 0x0219 }
            r6 = r3[r1]     // Catch:{ Exception -> 0x0219 }
            java.lang.String r6 = r6.f563a     // Catch:{ Exception -> 0x0219 }
            r5.append(r6)     // Catch:{ Exception -> 0x0219 }
            java.lang.String r6 = ", "
            r5.append(r6)     // Catch:{ Exception -> 0x0219 }
            int r1 = r1 + 1
            goto L_0x0281
        L_0x0329:
            if (r3 != 0) goto L_0x033b
            android.widget.EditText r3 = r11.f139c     // Catch:{ Exception -> 0x0219 }
            if (r4 == 0) goto L_0x0339
            r1 = r0
        L_0x0330:
            r3.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f147d     // Catch:{ Exception -> 0x0219 }
            r3 = r1
            r1 = r0
            goto L_0x0121
        L_0x0339:
            r1 = r2
            goto L_0x0330
        L_0x033b:
            if (r3 != r9) goto L_0x0368
            int r1 = r11.f101a     // Catch:{ Exception -> 0x0219 }
            java.lang.String r1 = p004de.humatic.nmj.NMJConfig.getIP(r1)     // Catch:{ Exception -> 0x0219 }
            if (r1 == 0) goto L_0x035c
            android.widget.EditText r1 = r11.f128b     // Catch:{ Exception -> 0x0219 }
            r3 = 0
            r1.setEnabled(r3)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r1 = r11.f139c     // Catch:{ Exception -> 0x0219 }
            r3 = r1
            r1 = r0
        L_0x034f:
            r3.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            android.widget.EditText r3 = r11.f147d     // Catch:{ Exception -> 0x0219 }
            if (r4 == 0) goto L_0x0366
            r1 = r0
        L_0x0357:
            r3.setEnabled(r1)     // Catch:{ Exception -> 0x0219 }
            goto L_0x0124
        L_0x035c:
            android.widget.EditText r1 = r11.f139c     // Catch:{ Exception -> 0x0219 }
            if (r4 == 0) goto L_0x0363
            r3 = r1
            r1 = r0
            goto L_0x034f
        L_0x0363:
            r3 = r1
            r1 = r2
            goto L_0x034f
        L_0x0366:
            r1 = r2
            goto L_0x0357
        L_0x0368:
            android.widget.EditText r1 = r11.f139c     // Catch:{ Exception -> 0x0219 }
            if (r4 == 0) goto L_0x0370
            r3 = r1
            r1 = r0
            goto L_0x0121
        L_0x0370:
            r3 = r1
            r1 = r2
            goto L_0x0121
        L_0x0374:
            r1 = r0
            goto L_0x012f
        L_0x0377:
            r1 = r2
            goto L_0x0137
        L_0x037a:
            r1 = r0
            goto L_0x0149
        L_0x037d:
            r0 = r2
            goto L_0x0150
        L_0x0380:
            android.graphics.drawable.BitmapDrawable r1 = r11.f137c     // Catch:{ Exception -> 0x0219 }
            goto L_0x018e
        L_0x0384:
            android.graphics.drawable.BitmapDrawable r1 = r11.f137c     // Catch:{ Exception -> 0x0219 }
            goto L_0x01a7
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfigDialog.m155a(int):void");
    }

    /* renamed from: a */
    private void m154a() {
        this.f119a.clear();
        int numChannels = NMJConfig.getNumChannels();
        for (int i = 0; i < numChannels; i++) {
            this.f119a.add(new C0485q(i));
        }
        this.f108a.setAdapter(this.f119a);
    }

    /* renamed from: a */
    private ImageButton m142a(Context context, BitmapDrawable bitmapDrawable, int i, int i2) {
        ImageButton imageButton = new ImageButton(context);
        imageButton.setImageDrawable(bitmapDrawable);
        imageButton.setBackgroundColor(i2);
        imageButton.setId(i);
        imageButton.setOnClickListener(this);
        return imageButton;
    }

    /* renamed from: a */
    private Button m139a(Context context, String str, int i) {
        Button button = new Button(context);
        button.setText(str);
        if (this.f132b) {
            if (str.length() > 1) {
                button.setTextAppearance(context, this.f158g);
            } else {
                button.setTextAppearance(context, 16973892);
            }
            if (this.f150d) {
                button.setTextSize(10.0f);
            } else if (this.f155e) {
                button.setTextSize(11.0f);
            }
            if (this.f100a > 2.0f) {
                button.setTextSize(11.0f);
            }
        } else {
            button.setTextAppearance(context, 16973890);
        }
        button.setTextColor(-6710887);
        button.setBackgroundColor(-13421773);
        button.setId(i);
        button.setOnClickListener(this);
        return button;
    }

    /* renamed from: b */
    private Button m170b(Context context, String str, int i) {
        Button button = new Button(context);
        button.setText(str);
        if (this.f132b) {
            if (str.length() > 1) {
                button.setTextAppearance(context, this.f158g);
            } else {
                button.setTextAppearance(context, 16973892);
            }
            if (this.f100a > 2.0f) {
                button.setTextSize(11.0f);
            }
        } else {
            button.setTextAppearance(context, 16973890);
        }
        button.setTextColor(-6710887);
        button.setBackgroundColor(-13421773);
        button.setId(i);
        button.setOnTouchListener(this);
        return button;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v73, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v74, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v98, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v99, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v100, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v101, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v165, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        /*
            r7 = this;
            r5 = 2048(0x800, float:2.87E-42)
            r3 = 1
            r2 = 0
            boolean r1 = r8 instanceof android.widget.Button     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x0027
            r0 = r8
            android.widget.Button r0 = (android.widget.Button) r0     // Catch:{ Exception -> 0x009f }
            r1 = r0
            java.lang.CharSequence r1 = r1.getText()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x009f }
            int r1 = r1.length()     // Catch:{ Exception -> 0x009f }
            if (r1 != 0) goto L_0x0027
            int r1 = r8.getId()     // Catch:{ Exception -> 0x009f }
            r4 = 259(0x103, float:3.63E-43)
            if (r1 == r4) goto L_0x0027
        L_0x0026:
            return
        L_0x0027:
            boolean r1 = r8 instanceof android.widget.ImageButton     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x00a4
            r0 = r8
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0     // Catch:{ Exception -> 0x009f }
            r1 = r0
            android.graphics.drawable.Drawable r1 = r1.getDrawable()     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r4 = r7.f137c     // Catch:{ Exception -> 0x009f }
            boolean r1 = r1.equals(r4)     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x00a4
            int r1 = r8.getId()     // Catch:{ Exception -> 0x009f }
            if (r1 != r5) goto L_0x0026
            android.app.AlertDialog$Builder r1 = new android.app.AlertDialog$Builder     // Catch:{ Exception -> 0x009f }
            r1.<init>(r7)     // Catch:{ Exception -> 0x009f }
            r7.f102a = r1     // Catch:{ Exception -> 0x009f }
            android.app.AlertDialog$Builder r1 = r7.f102a     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "USB Blacklist"
            r1.setTitle(r2)     // Catch:{ Exception -> 0x009f }
            android.widget.LinearLayout r1 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x009f }
            r1.<init>(r7)     // Catch:{ Exception -> 0x009f }
            r2 = 10
            r3 = 10
            r4 = 10
            r5 = 10
            r1.setPadding(r2, r3, r4, r5)     // Catch:{ Exception -> 0x009f }
            android.widget.EditText r2 = new android.widget.EditText     // Catch:{ Exception -> 0x009f }
            r2.<init>(r7)     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = "VendorID(:ProductID,...)"
            r2.setHint(r3)     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = "usb_blacklist"
            java.lang.String r4 = ""
            java.lang.String r3 = p004de.humatic.nmj.NMJConfig.getProperty(r3, r4)     // Catch:{ Exception -> 0x009f }
            r2.setText(r3)     // Catch:{ Exception -> 0x009f }
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams     // Catch:{ Exception -> 0x009f }
            r4 = -2
            r5 = -2
            r6 = 1065353216(0x3f800000, float:1.0)
            r3.<init>(r4, r5, r6)     // Catch:{ Exception -> 0x009f }
            r1.addView(r2, r3)     // Catch:{ Exception -> 0x009f }
            android.app.AlertDialog$Builder r3 = r7.f102a     // Catch:{ Exception -> 0x009f }
            r3.setView(r1)     // Catch:{ Exception -> 0x009f }
            android.app.AlertDialog$Builder r1 = r7.f102a     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = "Ok"
            de.humatic.nmj.NMJConfigDialog$2 r4 = new de.humatic.nmj.NMJConfigDialog$2     // Catch:{ Exception -> 0x009f }
            r4.<init>(r7, r2)     // Catch:{ Exception -> 0x009f }
            r1.setPositiveButton(r3, r4)     // Catch:{ Exception -> 0x009f }
            android.app.AlertDialog$Builder r1 = r7.f102a     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Cancel"
            r3 = 0
            r1.setNegativeButton(r2, r3)     // Catch:{ Exception -> 0x009f }
            android.app.AlertDialog$Builder r1 = r7.f102a     // Catch:{ Exception -> 0x009f }
            r1.show()     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x009f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0026
        L_0x00a4:
            int r1 = r8.getId()     // Catch:{ Exception -> 0x009f }
            switch(r1) {
                case 257: goto L_0x00ad;
                case 258: goto L_0x05e2;
                case 259: goto L_0x05e7;
                case 544: goto L_0x05ef;
                case 546: goto L_0x060e;
                case 2048: goto L_0x056c;
                case 2049: goto L_0x00b8;
                case 2050: goto L_0x02c3;
                case 2051: goto L_0x05a9;
                case 4098: goto L_0x04d1;
                case 4099: goto L_0x051e;
                case 4101: goto L_0x04c5;
                case 4102: goto L_0x0026;
                case 12288: goto L_0x062f;
                case 12289: goto L_0x063c;
                case 12290: goto L_0x0649;
                case 12291: goto L_0x0656;
                case 12304: goto L_0x0671;
                case 12305: goto L_0x06ac;
                case 12306: goto L_0x0713;
                case 12307: goto L_0x074e;
                default: goto L_0x00ab;
            }     // Catch:{ Exception -> 0x009f }
        L_0x00ab:
            goto L_0x0026
        L_0x00ad:
            de.humatic.nmj.NMJConfigDialog$a r1 = r7.f114a     // Catch:{ Exception -> 0x009f }
            android.os.Message r2 = android.os.Message.obtain()     // Catch:{ Exception -> 0x009f }
            r1.sendMessage(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x00b8:
            android.widget.Button r8 = (android.widget.Button) r8     // Catch:{ Exception -> 0x009f }
            java.lang.CharSequence r1 = r8.getText()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "-"
            boolean r2 = r1.equals(r2)     // Catch:{ Exception -> 0x009f }
            if (r2 == 0) goto L_0x010f
            android.widget.TextView r1 = r7.f131b     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Delete selected channel?"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Cancel"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "OK"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 4096(0x1000, float:5.74E-42)
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x010f:
            java.lang.String r2 = "Cancel"
            boolean r2 = r1.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x009f }
            if (r2 == 0) goto L_0x015d
            int r2 = r7.f136c     // Catch:{ Exception -> 0x009f }
            r3 = 4096(0x1000, float:5.74E-42)
            if (r2 != r3) goto L_0x015d
            android.widget.TextView r1 = r7.f131b     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = ""
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "-"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "+"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f103a     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f126b     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = -1
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x015d:
            java.lang.String r2 = "Manually"
            boolean r2 = r1.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x009f }
            if (r2 == 0) goto L_0x01ee
            int r2 = r7.f136c     // Catch:{ Exception -> 0x009f }
            r3 = 4097(0x1001, float:5.741E-42)
            if (r2 != r3) goto L_0x01ee
            android.widget.TextView r1 = r7.f131b     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = ""
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            de.humatic.nmj.e r1 = r7.f119a     // Catch:{ Exception -> 0x009f }
            r2 = -1
            r1.mo8123a((int) r2)     // Catch:{ Exception -> 0x009f }
            int r1 = p004de.humatic.nmj.NMJConfig.getNumChannels()     // Catch:{ Exception -> 0x009f }
            int r1 = r1 + 1
            p004de.humatic.nmj.NMJConfig.setNumChannels(r1)     // Catch:{ Exception -> 0x009f }
            int r1 = p004de.humatic.nmj.NMJConfig.getNumChannels()     // Catch:{ Exception -> 0x009f }
            int r1 = r1 + -1
            r7.m155a((int) r1)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Discard"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Save"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            android.widget.EditText r1 = r7.f106a     // Catch:{ Exception -> 0x009f }
            r2 = 1
            r7.m157a((android.widget.TextView) r1, (boolean) r2)     // Catch:{ Exception -> 0x009f }
            android.widget.EditText r1 = r7.f128b     // Catch:{ Exception -> 0x009f }
            r2 = 1
            r7.m157a((android.widget.TextView) r1, (boolean) r2)     // Catch:{ Exception -> 0x009f }
            android.widget.EditText r1 = r7.f139c     // Catch:{ Exception -> 0x009f }
            r2 = 1
            r7.m157a((android.widget.TextView) r1, (boolean) r2)     // Catch:{ Exception -> 0x009f }
            android.widget.EditText r1 = r7.f147d     // Catch:{ Exception -> 0x009f }
            r2 = 1
            r7.m157a((android.widget.TextView) r1, (boolean) r2)     // Catch:{ Exception -> 0x009f }
            android.widget.LinearLayout r1 = r7.f148d     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
            android.widget.Spinner r1 = r7.f130b     // Catch:{ Exception -> 0x009f }
            r2 = 1
            r1.setEnabled(r2)     // Catch:{ Exception -> 0x009f }
            android.widget.Spinner r1 = r7.f110a     // Catch:{ Exception -> 0x009f }
            r2 = 1
            r1.setEnabled(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 4100(0x1004, float:5.745E-42)
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x01ee:
            java.lang.String r2 = "Discard"
            boolean r2 = r1.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x009f }
            if (r2 == 0) goto L_0x024d
            int r1 = r7.f136c     // Catch:{ Exception -> 0x009f }
            r2 = 4100(0x1004, float:5.745E-42)
            if (r1 != r2) goto L_0x0247
            int r1 = p004de.humatic.nmj.NMJConfig.getNumChannels()     // Catch:{ Exception -> 0x009f }
            int r1 = r1 + -1
            p004de.humatic.nmj.NMJConfig.setNumChannels(r1)     // Catch:{ Exception -> 0x009f }
            int r1 = p004de.humatic.nmj.NMJConfig.getNumChannels()     // Catch:{ Exception -> 0x009f }
            int r1 = r1 + -1
            r7.m155a((int) r1)     // Catch:{ Exception -> 0x009f }
        L_0x020e:
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "-"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "+"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f103a     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f126b     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = -1
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0247:
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            r7.m155a((int) r1)     // Catch:{ Exception -> 0x009f }
            goto L_0x020e
        L_0x024d:
            java.lang.String r2 = "Cancel"
            boolean r2 = r1.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x009f }
            if (r2 == 0) goto L_0x02b3
            int r2 = r7.f136c     // Catch:{ Exception -> 0x009f }
            r3 = 4101(0x1005, float:5.747E-42)
            if (r2 != r3) goto L_0x02b3
            de.humatic.nmj.e r1 = r7.f119a     // Catch:{ Exception -> 0x009f }
            int r2 = r7.f101a     // Catch:{ Exception -> 0x009f }
            r1.mo8123a((int) r2)     // Catch:{ Exception -> 0x009f }
            android.widget.LinearLayout r1 = r7.f140c     // Catch:{ Exception -> 0x009f }
            r2 = 8
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
            boolean r1 = r7.f132b     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x02ac
            android.widget.ScrollView r1 = r7.f109a     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
        L_0x0273:
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "-"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "+"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f103a     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f126b     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = -1
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x02ac:
            android.widget.LinearLayout r1 = r7.f107a     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0273
        L_0x02b3:
            java.lang.String r2 = "↻"
            boolean r1 = r1.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x0026
            r1 = -1
            r2 = 16777216(0x1000000, float:2.3509887E-38)
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x02c3:
            android.widget.Button r8 = (android.widget.Button) r8     // Catch:{ Exception -> 0x009f }
            java.lang.CharSequence r1 = r8.getText()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x009f }
            java.lang.String r4 = "+"
            boolean r4 = r1.equals(r4)     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0300
            android.widget.TextView r1 = r7.f131b     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Configure manually or perform scan?"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Manually"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Scan"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 4097(0x1001, float:5.741E-42)
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0300:
            java.lang.String r4 = "OK"
            boolean r4 = r1.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0364
            int r4 = r7.f136c     // Catch:{ Exception -> 0x009f }
            r5 = 4096(0x1000, float:5.74E-42)
            if (r4 != r5) goto L_0x0364
            android.widget.TextView r1 = r7.f131b     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = ""
            r1.setText(r3)     // Catch:{ Exception -> 0x009f }
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            p004de.humatic.nmj.NMJConfig.deleteChannel(r1)     // Catch:{ Exception -> 0x009f }
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            int r3 = p004de.humatic.nmj.NMJConfig.getNumChannels()     // Catch:{ Exception -> 0x009f }
            if (r1 != r3) goto L_0x0361
        L_0x0322:
            r7.m155a((int) r2)     // Catch:{ Exception -> 0x009f }
            r7.m154a()     // Catch:{ Exception -> 0x009f }
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "-"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "+"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f103a     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f126b     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = -1
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0361:
            int r2 = r7.f101a     // Catch:{ Exception -> 0x009f }
            goto L_0x0322
        L_0x0364:
            java.lang.String r4 = "Save"
            boolean r4 = r1.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0453
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            android.widget.Spinner r4 = r7.f110a     // Catch:{ Exception -> 0x009f }
            int r4 = r4.getSelectedItemPosition()     // Catch:{ Exception -> 0x009f }
            p004de.humatic.nmj.NMJConfig.setMode(r1, r4)     // Catch:{ Exception -> 0x009f }
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            android.widget.EditText r4 = r7.f106a     // Catch:{ Exception -> 0x009f }
            android.text.Editable r4 = r4.getText()     // Catch:{ Exception -> 0x009f }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x009f }
            p004de.humatic.nmj.NMJConfig.setName(r1, r4)     // Catch:{ Exception -> 0x009f }
            android.widget.EditText r1 = r7.f128b     // Catch:{ Exception -> 0x009f }
            android.text.Editable r1 = r1.getText()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x009f }
            int r4 = r7.f101a     // Catch:{ Exception -> 0x009f }
            int r5 = r1.length()     // Catch:{ Exception -> 0x009f }
            if (r5 != 0) goto L_0x039d
            r1 = 0
        L_0x039d:
            p004de.humatic.nmj.NMJConfig.setIP(r4, r1)     // Catch:{ Exception -> 0x009f }
            int r1 = r7.f101a     // Catch:{ Exception -> 0x0434 }
            android.widget.EditText r4 = r7.f139c     // Catch:{ Exception -> 0x0434 }
            android.text.Editable r4 = r4.getText()     // Catch:{ Exception -> 0x0434 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0434 }
            java.lang.String r4 = r4.trim()     // Catch:{ Exception -> 0x0434 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0434 }
            p004de.humatic.nmj.NMJConfig.setPort(r1, r4)     // Catch:{ Exception -> 0x0434 }
        L_0x03b7:
            int r1 = r7.f101a     // Catch:{ Exception -> 0x0440 }
            android.widget.EditText r4 = r7.f147d     // Catch:{ Exception -> 0x0440 }
            android.text.Editable r4 = r4.getText()     // Catch:{ Exception -> 0x0440 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0440 }
            java.lang.String r4 = r4.trim()     // Catch:{ Exception -> 0x0440 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0440 }
            p004de.humatic.nmj.NMJConfig.setLocalPort(r1, r4)     // Catch:{ Exception -> 0x0440 }
        L_0x03ce:
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            android.widget.Spinner r4 = r7.f130b     // Catch:{ Exception -> 0x009f }
            int r4 = r4.getSelectedItemPosition()     // Catch:{ Exception -> 0x009f }
            p004de.humatic.nmj.NMJConfig.setNetworkAdapter(r1, r4)     // Catch:{ Exception -> 0x009f }
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            int r1 = p004de.humatic.nmj.NMJConfig.getMode(r1)     // Catch:{ Exception -> 0x009f }
            if (r1 != 0) goto L_0x044c
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            android.widget.CheckBox r4 = r7.f127b     // Catch:{ Exception -> 0x009f }
            boolean r4 = r4.isChecked()     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x044a
        L_0x03eb:
            p004de.humatic.nmj.NMJConfig.setIO(r1, r2)     // Catch:{ Exception -> 0x009f }
        L_0x03ee:
            de.humatic.nmj.e r1 = r7.f119a     // Catch:{ Exception -> 0x009f }
            r1.notifyDataSetChanged()     // Catch:{ Exception -> 0x009f }
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            r7.m155a((int) r1)     // Catch:{ Exception -> 0x009f }
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "-"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "+"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f103a     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f126b     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r7.m154a()     // Catch:{ Exception -> 0x009f }
            r1 = -1
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0434:
            r1 = move-exception
            int r1 = r7.f101a     // Catch:{ Exception -> 0x043d }
            r4 = -1
            p004de.humatic.nmj.NMJConfig.setPort(r1, r4)     // Catch:{ Exception -> 0x043d }
            goto L_0x03b7
        L_0x043d:
            r1 = move-exception
            goto L_0x03b7
        L_0x0440:
            r1 = move-exception
            int r1 = r7.f101a     // Catch:{ Exception -> 0x0448 }
            r4 = -1
            p004de.humatic.nmj.NMJConfig.setLocalPort(r1, r4)     // Catch:{ Exception -> 0x0448 }
            goto L_0x03ce
        L_0x0448:
            r1 = move-exception
            goto L_0x03ce
        L_0x044a:
            r2 = r3
            goto L_0x03eb
        L_0x044c:
            int r1 = r7.f101a     // Catch:{ Exception -> 0x009f }
            r2 = -1
            p004de.humatic.nmj.NMJConfig.setIO(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x03ee
        L_0x0453:
            java.lang.String r2 = "Scan"
            boolean r1 = r1.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x0026
            de.humatic.nmj.e r1 = r7.f119a     // Catch:{ Exception -> 0x009f }
            r2 = -1
            r1.mo8123a((int) r2)     // Catch:{ Exception -> 0x009f }
            android.widget.TextView r1 = r7.f131b     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = ""
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            boolean r1 = r7.f132b     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x04bd
            android.widget.ScrollView r1 = r7.f109a     // Catch:{ Exception -> 0x009f }
            r2 = 8
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
        L_0x0473:
            android.widget.LinearLayout r1 = r7.f129b     // Catch:{ Exception -> 0x009f }
            r2 = 8
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
            android.widget.LinearLayout r1 = r7.f140c     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 4101(0x1005, float:5.747E-42)
            r7.f136c = r1     // Catch:{ Exception -> 0x009f }
            r1 = 2049(0x801, float:2.871E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "Cancel"
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2050(0x802, float:2.873E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = ""
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r7.m154a()     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x04bd:
            android.widget.LinearLayout r1 = r7.f107a     // Catch:{ Exception -> 0x009f }
            r2 = 8
            r1.setVisibility(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0473
        L_0x04c5:
            android.widget.TextView r1 = r7.f131b     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = ""
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            p004de.humatic.nmj.NMJConfig.m82b()     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x04d1:
            android.widget.Spinner r1 = r7.f110a     // Catch:{ Exception -> 0x009f }
            int r1 = r1.getSelectedItemPosition()     // Catch:{ Exception -> 0x009f }
            if (r1 != 0) goto L_0x0026
            android.widget.EditText r1 = r7.f128b     // Catch:{ Exception -> 0x009f }
            android.text.Editable r1 = r1.getText()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x009f }
            boolean r1 = p004de.humatic.nmj.C0484p.m362a((java.lang.String) r1)     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x0026
            android.widget.CheckBox r8 = (android.widget.CheckBox) r8     // Catch:{ Exception -> 0x009f }
            boolean r4 = r8.isChecked()     // Catch:{ Exception -> 0x009f }
            android.widget.CheckBox r1 = r7.f138c     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0516
        L_0x04f3:
            r1.setChecked(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0518
            android.graphics.drawable.BitmapDrawable r2 = r7.f103a     // Catch:{ Exception -> 0x009f }
        L_0x0502:
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x051b
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
        L_0x0511:
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0516:
            r2 = r3
            goto L_0x04f3
        L_0x0518:
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            goto L_0x0502
        L_0x051b:
            android.graphics.drawable.BitmapDrawable r2 = r7.f126b     // Catch:{ Exception -> 0x009f }
            goto L_0x0511
        L_0x051e:
            android.widget.Spinner r1 = r7.f110a     // Catch:{ Exception -> 0x009f }
            int r1 = r1.getSelectedItemPosition()     // Catch:{ Exception -> 0x009f }
            if (r1 != 0) goto L_0x0026
            android.widget.EditText r1 = r7.f128b     // Catch:{ Exception -> 0x009f }
            android.text.Editable r1 = r1.getText()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x009f }
            boolean r1 = p004de.humatic.nmj.C0484p.m362a((java.lang.String) r1)     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x0026
            android.widget.CheckBox r8 = (android.widget.CheckBox) r8     // Catch:{ Exception -> 0x009f }
            boolean r4 = r8.isChecked()     // Catch:{ Exception -> 0x009f }
            android.widget.CheckBox r5 = r7.f127b     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0564
            r1 = r2
        L_0x0541:
            r5.setChecked(r1)     // Catch:{ Exception -> 0x009f }
            r1 = 2048(0x800, float:2.87E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0566
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
        L_0x0550:
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            r1 = 2051(0x803, float:2.874E-42)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0569
            android.graphics.drawable.BitmapDrawable r2 = r7.f126b     // Catch:{ Exception -> 0x009f }
        L_0x055f:
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0564:
            r1 = r3
            goto L_0x0541
        L_0x0566:
            android.graphics.drawable.BitmapDrawable r2 = r7.f103a     // Catch:{ Exception -> 0x009f }
            goto L_0x0550
        L_0x0569:
            android.graphics.drawable.BitmapDrawable r2 = r7.f137c     // Catch:{ Exception -> 0x009f }
            goto L_0x055f
        L_0x056c:
            int r1 = r7.f101a     // Catch:{ Exception -> 0x058a }
            de.humatic.nmj.NetworkMidiInput r2 = r7.f116a     // Catch:{ Exception -> 0x058a }
            if (r2 != 0) goto L_0x057f
            de.humatic.nmj.NMJConfigDialog$4 r2 = new de.humatic.nmj.NMJConfigDialog$4     // Catch:{ Exception -> 0x058a }
            r2.<init>(r1)     // Catch:{ Exception -> 0x058a }
            java.lang.Thread r1 = new java.lang.Thread     // Catch:{ Exception -> 0x058a }
            r1.<init>(r2)     // Catch:{ Exception -> 0x058a }
            r1.start()     // Catch:{ Exception -> 0x058a }
        L_0x057f:
            de.humatic.nmj.i r1 = r7.f120a     // Catch:{ Exception -> 0x058a }
            r2 = 1
            r1.setDisplayedChild(r2)     // Catch:{ Exception -> 0x058a }
            r1 = 1
            r7.f160h = r1     // Catch:{ Exception -> 0x058a }
            goto L_0x0026
        L_0x058a:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = "Failed to open "
            r2.<init>(r3)     // Catch:{ Exception -> 0x009f }
            int r3 = r7.f101a     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = p004de.humatic.nmj.NMJConfig.getName(r3)     // Catch:{ Exception -> 0x009f }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.getMessage()     // Catch:{ Exception -> 0x009f }
            r7.m164a((java.lang.String) r2, (java.lang.String) r1)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x05a9:
            int r1 = r7.f101a     // Catch:{ Exception -> 0x05c3 }
            de.humatic.nmj.NMJConfigDialog$3 r2 = new de.humatic.nmj.NMJConfigDialog$3     // Catch:{ Exception -> 0x05c3 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x05c3 }
            java.lang.Thread r1 = new java.lang.Thread     // Catch:{ Exception -> 0x05c3 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x05c3 }
            r1.start()     // Catch:{ Exception -> 0x05c3 }
            de.humatic.nmj.i r1 = r7.f120a     // Catch:{ Exception -> 0x05c3 }
            r2 = 2
            r1.setDisplayedChild(r2)     // Catch:{ Exception -> 0x05c3 }
            r1 = 2
            r7.f160h = r1     // Catch:{ Exception -> 0x05c3 }
            goto L_0x0026
        L_0x05c3:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = "Failed to open "
            r2.<init>(r3)     // Catch:{ Exception -> 0x009f }
            int r3 = r7.f101a     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = p004de.humatic.nmj.NMJConfig.getName(r3)     // Catch:{ Exception -> 0x009f }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = r1.getMessage()     // Catch:{ Exception -> 0x009f }
            r7.m164a((java.lang.String) r2, (java.lang.String) r1)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x05e2:
            r7.m173b()     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x05e7:
            r1 = -1
            r2 = 16777216(0x1000000, float:2.3509887E-38)
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x05ef:
            int r1 = r7.f161i     // Catch:{ Exception -> 0x009f }
            if (r1 <= 0) goto L_0x05f9
            int r1 = r7.f161i     // Catch:{ Exception -> 0x009f }
            int r1 = r1 + -12
            r7.f161i = r1     // Catch:{ Exception -> 0x009f }
        L_0x05f9:
            r1 = 545(0x221, float:7.64E-43)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            int r2 = r7.f161i     // Catch:{ Exception -> 0x009f }
            int r2 = r2 / 12
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x009f }
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x060e:
            int r1 = r7.f161i     // Catch:{ Exception -> 0x009f }
            r2 = 120(0x78, float:1.68E-43)
            if (r1 >= r2) goto L_0x061a
            int r1 = r7.f161i     // Catch:{ Exception -> 0x009f }
            int r1 = r1 + 12
            r7.f161i = r1     // Catch:{ Exception -> 0x009f }
        L_0x061a:
            r1 = 545(0x221, float:7.64E-43)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.Button r1 = (android.widget.Button) r1     // Catch:{ Exception -> 0x009f }
            int r2 = r7.f161i     // Catch:{ Exception -> 0x009f }
            int r2 = r2 / 12
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x009f }
            r1.setText(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x062f:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "android.settings.WIFI_SETTINGS"
            r1.<init>(r2)     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r7.startActivityForResult(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x063c:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "android.settings.WIRELESS_SETTINGS"
            r1.<init>(r2)     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r7.startActivityForResult(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0649:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "android.settings.BLUETOOTH_SETTINGS"
            r1.<init>(r2)     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r7.startActivityForResult(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0656:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ ActivityNotFoundException -> 0x0663 }
            java.lang.String r2 = "android.settings.APPLICATION_DEVELOPMENT_SETTINGS"
            r1.<init>(r2)     // Catch:{ ActivityNotFoundException -> 0x0663 }
            r2 = 0
            r7.startActivityForResult(r1, r2)     // Catch:{ ActivityNotFoundException -> 0x0663 }
            goto L_0x0026
        L_0x0663:
            r1 = move-exception
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x009f }
            java.lang.String r2 = "com.android.settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS"
            r1.<init>(r2)     // Catch:{ Exception -> 0x009f }
            r2 = 0
            r7.startActivityForResult(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0671:
            r1 = -1
            int r1 = p004de.humatic.nmj.NMJConfig.getFlags(r1)     // Catch:{ Exception -> 0x009f }
            r1 = r1 & 3
            if (r1 == 0) goto L_0x06a0
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 & -4
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
        L_0x0685:
            r1 = 12304(0x3010, float:1.7242E-41)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.app.Application r2 = r7.getApplication()     // Catch:{ Exception -> 0x009f }
            r3 = 0
            r4 = -1
            int r4 = p004de.humatic.nmj.NMJConfig.getFlags(r4)     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.m137a((android.content.Context) r2, (int) r3, (int) r4)     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x06a0:
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 | 3
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0685
        L_0x06ac:
            r1 = -1
            int r1 = p004de.humatic.nmj.NMJConfig.getFlags(r1)     // Catch:{ Exception -> 0x009f }
            r1 = r1 & 8
            if (r1 == 0) goto L_0x06db
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 & -9
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
        L_0x06c0:
            r1 = 12305(0x3011, float:1.7243E-41)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.app.Application r2 = r7.getApplication()     // Catch:{ Exception -> 0x009f }
            r3 = 1
            r4 = -1
            int r4 = p004de.humatic.nmj.NMJConfig.getFlags(r4)     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.m137a((android.content.Context) r2, (int) r3, (int) r4)     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x06db:
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 | 8
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
            android.bluetooth.BluetoothAdapter r1 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()     // Catch:{ Exception -> 0x0711 }
            if (r1 == 0) goto L_0x0026
            boolean r2 = r1.isEnabled()     // Catch:{ Exception -> 0x0711 }
            if (r2 == 0) goto L_0x0026
            int r1 = r1.getScanMode()     // Catch:{ Exception -> 0x0711 }
            r2 = 23
            if (r1 == r2) goto L_0x06c0
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x0711 }
            java.lang.String r2 = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0711 }
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            r1.addFlags(r2)     // Catch:{ Exception -> 0x0711 }
            java.lang.String r2 = "android.bluetooth.adapter.extra.DISCOVERABLE_DURATION"
            r3 = 300(0x12c, float:4.2E-43)
            r1.putExtra(r2, r3)     // Catch:{ Exception -> 0x0711 }
            r7.startActivity(r1)     // Catch:{ Exception -> 0x0711 }
            goto L_0x06c0
        L_0x0711:
            r1 = move-exception
            goto L_0x06c0
        L_0x0713:
            r1 = -1
            int r1 = p004de.humatic.nmj.NMJConfig.getFlags(r1)     // Catch:{ Exception -> 0x009f }
            r1 = r1 & 16
            if (r1 == 0) goto L_0x0742
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 & -17
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
        L_0x0727:
            r1 = 12306(0x3012, float:1.7244E-41)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.app.Application r2 = r7.getApplication()     // Catch:{ Exception -> 0x009f }
            r3 = 2
            r4 = -1
            int r4 = p004de.humatic.nmj.NMJConfig.getFlags(r4)     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.m137a((android.content.Context) r2, (int) r3, (int) r4)     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x0742:
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 | 16
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0727
        L_0x074e:
            r1 = -1
            int r1 = p004de.humatic.nmj.NMJConfig.getFlags(r1)     // Catch:{ Exception -> 0x009f }
            r1 = r1 & 32
            if (r1 == 0) goto L_0x077d
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 & -33
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
        L_0x0762:
            r1 = 12307(0x3013, float:1.7246E-41)
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ Exception -> 0x009f }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x009f }
            android.app.Application r2 = r7.getApplication()     // Catch:{ Exception -> 0x009f }
            r3 = 3
            r4 = -1
            int r4 = p004de.humatic.nmj.NMJConfig.getFlags(r4)     // Catch:{ Exception -> 0x009f }
            android.graphics.drawable.BitmapDrawable r2 = r7.m137a((android.content.Context) r2, (int) r3, (int) r4)     // Catch:{ Exception -> 0x009f }
            r1.setImageDrawable(r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0026
        L_0x077d:
            r1 = -1
            r2 = -1
            int r2 = p004de.humatic.nmj.NMJConfig.getFlags(r2)     // Catch:{ Exception -> 0x009f }
            r2 = r2 | 32
            p004de.humatic.nmj.NMJConfig.setFlags(r1, r2)     // Catch:{ Exception -> 0x009f }
            goto L_0x0762
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfigDialog.onClick(android.view.View):void");
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        int i = 100;
        boolean z2 = motionEvent.getAction() == 0;
        if (motionEvent.getAction() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (!z2 && !z) {
            return true;
        }
        if (view.getId() >= 528 && view.getId() <= 535) {
            this.f144c[0] = (byte) (this.f141c.getSelectedItemPosition() | 144);
            this.f144c[1] = m133a(view.getId() - 528, true);
            this.f144c[2] = (byte) (z2 ? 100 : 0);
            m165a(this.f144c);
        } else if (view.getId() >= 512 && view.getId() <= 517) {
            this.f144c[0] = (byte) (this.f141c.getSelectedItemPosition() | 144);
            this.f144c[1] = m133a(view.getId() - 512, false);
            byte[] bArr = this.f144c;
            if (!z2) {
                i = 0;
            }
            bArr[2] = (byte) i;
            m165a(this.f144c);
        } else if (view.getId() == 4103) {
            m157a((TextView) this.f128b, true);
        } else if (view.getId() == 4104) {
            m157a((TextView) this.f139c, true);
            m157a((TextView) this.f147d, true);
        }
        return false;
    }

    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z;
        if (this.f159g) {
            try {
                ((TextView) adapterView.getChildAt(0)).setTextColor(-6710887);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!this.f121a) {
            try {
                if (adapterView.equals(this.f110a)) {
                    if (i == 0) {
                        m157a((TextView) this.f147d, false);
                        this.f148d.setVisibility(0);
                        this.f127b.setChecked(NMJConfig.getIO(this.f101a) <= 0);
                        CheckBox checkBox = this.f138c;
                        if (Math.abs(NMJConfig.getIO(this.f101a)) == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        checkBox.setChecked(z);
                        this.f130b.setEnabled(true);
                    } else if (i == 1) {
                        m157a((TextView) this.f128b, true);
                        m157a((TextView) this.f139c, true);
                        m157a((TextView) this.f147d, true);
                        this.f148d.setVisibility(4);
                        this.f130b.setEnabled(true);
                        NMJConfig.setIO(this.f101a, -1);
                    } else if (i == 2) {
                        this.f128b.setText("");
                        m157a((TextView) this.f128b, false);
                        this.f139c.setText("0");
                        m157a((TextView) this.f139c, true);
                        this.f147d.setText("");
                        m157a((TextView) this.f147d, false);
                        this.f148d.setVisibility(4);
                        this.f130b.setEnabled(false);
                        NMJConfig.setIP(this.f101a, (String) null);
                        NMJConfig.setPort(this.f101a, 0);
                        NMJConfig.setIO(this.f101a, -1);
                    } else if (i == 3) {
                        m157a((TextView) this.f128b, false);
                        m157a((TextView) this.f139c, false);
                        m157a((TextView) this.f147d, false);
                        this.f148d.setVisibility(4);
                        NMJConfig.setIO(this.f101a, -1);
                    } else if (i == 4) {
                        NMJConfig.setPort(this.f101a, -1);
                        NMJConfig.setLocalPort(this.f101a, -1);
                        this.f139c.setText("");
                        this.f147d.setText("");
                        this.f128b.setText("");
                        m157a((TextView) this.f128b, false);
                        m157a((TextView) this.f139c, false);
                        m157a((TextView) this.f147d, false);
                        this.f148d.setVisibility(4);
                        NMJConfig.setIO(this.f101a, -1);
                    } else if (i == 5 || i == 7) {
                        C0484p.logln(2, "Hardware dependent mode, not setable");
                        this.f110a.setSelection(NMJConfig.getMode(this.f101a));
                        return;
                    }
                    onManualEdit();
                } else if (adapterView.equals(this.f130b)) {
                    onManualEdit();
                }
            } catch (Exception e2) {
                C0484p.m377a(e2, "CfgDlg, onItemSelected");
            }
        }
    }

    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if ((keyEvent != null || i != 6) && (i != 0 || keyEvent.getAction() != 0)) {
            return false;
        }
        this.f104a.hideSoftInputFromWindow(((EditText) textView).getWindowToken(), 0);
        return true;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!NetworkMidiSystem.get().isOpen(-1, this.f101a)) {
            onManualEdit();
        }
    }

    public final void onManualEdit() {
        if (!this.f121a && !((Button) findViewById(2050)).getText().toString().equalsIgnoreCase("Save")) {
            ((ImageButton) findViewById(2048)).setImageDrawable(this.f137c);
            ((ImageButton) findViewById(2051)).setImageDrawable(this.f137c);
            ((Button) findViewById(2049)).setText("Discard");
            ((Button) findViewById(2050)).setText("Save");
        }
    }

    public final boolean onLongClick(View view) {
        if (!view.equals(this.f128b)) {
            return false;
        }
        if (NMJConfig.getMode(this.f101a) == 1 && NMJConfig.getIP(this.f101a) == null) {
            C0502v[] a = NMJConfig.m39a(this.f101a);
            if (a == null || a.length == 0) {
                return true;
            }
            final int i = this.f101a;
            this.f102a = new AlertDialog.Builder(this);
            this.f102a.setTitle("Ch " + i + " Clients");
            String[] strArr = new String[a.length];
            for (int i2 = 0; i2 < a.length; i2++) {
                strArr[i2] = String.valueOf(a[i2].f563a) + " (" + a[i2].f565b + ")";
            }
            this.f156f = -1;
            this.f102a.setSingleChoiceItems(strArr, this.f156f, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NMJConfigDialog.this.f156f = i;
                }
            });
            this.f102a.setPositiveButton("Disconnect Selected", new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        if (NMJConfigDialog.this.f156f >= 0) {
                            NMJConfig.m104c(i, NMJConfigDialog.this.f156f);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.f102a.setNegativeButton("Cancel", new DialogInterface.OnClickListener(this) {
                public final void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            this.f102a.show();
        }
        return true;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            if (seekBar.getId() == 560) {
                this.f133b[0] = (byte) (this.f141c.getSelectedItemPosition() | 224);
                int max = (int) (16384.0f * (((float) i) / ((float) seekBar.getMax())));
                this.f133b[1] = (byte) (max & 127);
                this.f133b[2] = (byte) (max >> 7);
                m165a(this.f133b);
                return;
            }
            int parseInt = Integer.parseInt(((TextView) findViewById(seekBar.getId() + 1)).getText().toString().trim());
            int max2 = (int) (127.0f * (((float) i) / ((float) seekBar.getMax())));
            if (this.f122a[1] != parseInt || this.f122a[2] != max2) {
                this.f122a[0] = (byte) (this.f141c.getSelectedItemPosition() | 176);
                this.f122a[1] = (byte) parseInt;
                this.f122a[2] = (byte) max2;
                m165a(this.f122a);
            }
        }
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == 560) {
            seekBar.setProgress(seekBar.getMax() / 2);
            this.f133b[0] = (byte) (this.f141c.getSelectedItemPosition() | 224);
            this.f133b[1] = 0;
            this.f133b[2] = 64;
            m165a(this.f133b);
        }
    }

    /* renamed from: b */
    private void m173b() {
        this.f121a = true;
        if (this.f116a != null) {
            try {
                ((NetworkMidiInput) this.f118a.mo8102a(0, this.f101a)).close(this.f112a);
            } catch (Exception e) {
            }
            this.f116a = null;
        }
        if (this.f117a != null) {
            this.f117a.close(this.f113a);
            this.f117a = null;
        }
        this.f120a.setDisplayedChild(0);
        this.f160h = 0;
        Message obtain = Message.obtain();
        obtain.what = 256;
        this.f115a.sendMessageDelayed(obtain, 500);
    }

    /* renamed from: a */
    private void m157a(TextView textView, boolean z) {
        textView.setEnabled(z);
        textView.setFocusableInTouchMode(z);
        textView.setFocusable(z);
        if (!z) {
            textView.clearFocus();
            this.f111a.requestFocus();
        }
    }

    /* renamed from: a */
    private LinearLayout m143a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        int connectivity = NMJConfig.getConnectivity(context);
        linearLayout.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        layoutParams.setMargins(0, this.f150d ? 1 : 5, 0, 0);
        for (int i = 0; i < 4; i++) {
            boolean z = ((1 << i) & connectivity) != 0;
            String str = "wifi.png";
            if (i == 1) {
                str = "usb.png";
            } else if (i == 2) {
                str = "bt.png";
            } else if (i == 3) {
                str = "adb.png";
            }
            Bitmap a = m136a(str);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(a, (int) ((((float) a.getWidth()) / 2.0f) * this.f100a), (int) ((((float) a.getHeight()) / 2.0f) * this.f100a), true));
            if (!z) {
                bitmapDrawable.setAlpha(100);
            }
            linearLayout.addView(m142a(context, bitmapDrawable, i + 12288, 0), i, layoutParams);
        }
        return linearLayout;
    }

    /* renamed from: b */
    private LinearLayout m172b(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        int flags = NMJConfig.getFlags(-1);
        linearLayout.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        layoutParams.setMargins(0, this.f150d ? 0 : 5, 0, 0);
        for (int i = 0; i < 4; i++) {
            try {
                linearLayout.addView(m142a(context, m137a(context, i, flags), i + 12304, 0), i, layoutParams);
            } catch (Exception e) {
            }
        }
        return linearLayout;
    }

    /* renamed from: a */
    private BitmapDrawable m137a(Context context, int i, int i2) {
        boolean z;
        String str;
        boolean z2 = false;
        if (i == 0) {
            if ((i2 & 3) != 0) {
                z2 = true;
            }
            z = z2;
            str = "wifi.png";
        } else if (i == 1) {
            if ((i2 & 8) != 0) {
                z2 = true;
            }
            z = z2;
            str = "bt.png";
        } else if (i == 2) {
            if ((i2 & 16) != 0) {
                z2 = true;
            }
            z = z2;
            str = "adb.png";
        } else if (i == 3) {
            if ((i2 & 32) != 0) {
                z2 = true;
            }
            z = z2;
            str = "usb.png";
        } else {
            z = false;
            str = "";
        }
        Bitmap a = m136a(str);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(a, (int) ((((float) a.getWidth()) / 2.3f) * this.f100a), (int) ((((float) a.getHeight()) / 2.3f) * this.f100a), true));
        if (!z) {
            bitmapDrawable.setAlpha(80);
        }
        return bitmapDrawable;
    }

    /* renamed from: a */
    private BitmapDrawable m138a(Context context, String str) {
        Bitmap a = m136a(str);
        return new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(a, (int) ((((float) a.getWidth()) / 2.0f) * this.f100a), (int) ((((float) a.getHeight()) / 2.0f) * this.f100a), true));
    }

    /* renamed from: a */
    private boolean m166a() {
        try {
            if (this.f130b.getCount() != NMJConfig.m38a() + 1) {
                Vector vector = new Vector();
                vector.add("Auto");
                try {
                    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    while (networkInterfaces.hasMoreElements()) {
                        vector.add(networkInterfaces.nextElement().getName());
                    }
                } catch (SocketException e) {
                    if (e.toString().indexOf("unkown error") != -1) {
                        System.out.println(String.valueOf(e.toString()) + "\nDid you grant the required network permission?");
                    } else {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String[] strArr = new String[vector.size()];
                vector.copyInto(strArr);
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, strArr);
                arrayAdapter.setDropDownViewResource(17367049);
                this.f130b.setAdapter(arrayAdapter);
                return true;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m164a(String str, String str2) {
        if (!this.f157f) {
            this.f102a = new AlertDialog.Builder(this);
            this.f102a.setTitle(str);
            this.f102a.setMessage(str2);
            this.f102a.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NMJConfigDialog.m169b(NMJConfigDialog.this);
                    NMJConfigDialog.this.f157f = false;
                }
            });
            this.f157f = true;
            try {
                if (Thread.currentThread().equals(Looper.getMainLooper())) {
                    this.f102a.show();
                    return;
                }
                this.f115a.post(new Runnable() {
                    public final void run() {
                        NMJConfigDialog.m134a(NMJConfigDialog.this).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        if (this.f116a != null) {
            try {
                this.f116a.removeMidiListener(this.f112a);
            } catch (Exception e) {
            }
        }
        if (this.f117a != null) {
            try {
                this.f117a.close(this.f113a);
            } catch (Exception e2) {
            }
        }
        this.f116a = null;
        this.f117a = null;
        try {
            NMJConfig.removeSystemListener(this);
        } catch (Exception e3) {
        }
    }

    public final void onResume() {
        super.onResume();
        try {
            NMJConfig.addSystemListener(this);
        } catch (Exception e) {
        }
        m154a();
    }

    /* renamed from: a */
    private void m165a(byte[] bArr) {
        try {
            this.f117a.sendMidiOnThread(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private byte m133a(int i, boolean z) {
        return (byte) ((z ? this.f134b[i] : this.f145c[i]) + this.f161i);
    }

    /* renamed from: de.humatic.nmj.NMJConfigDialog$a */
    class C0445a extends Handler {

        /* renamed from: a */
        private int f178a;

        /* renamed from: a */
        private TextView f179a;

        /* renamed from: a */
        private StringBuffer f181a;

        private C0445a(TextView textView) {
            this.f181a = new StringBuffer();
            this.f179a = textView;
        }

        /* synthetic */ C0445a(NMJConfigDialog nMJConfigDialog, TextView textView, byte b) {
            this(textView);
        }

        public final void handleMessage(Message message) {
            int i = 0;
            try {
                Bundle data = message.getData();
                byte[] byteArray = data.getByteArray("MIDI");
                if (byteArray == null || byteArray.length == 0) {
                    this.f181a.delete(0, this.f181a.length());
                    this.f179a.setText(this.f181a.toString());
                    return;
                }
                int length = this.f181a.length();
                this.f181a.append("nmjCh: ");
                this.f181a.append(data.getInt("CH") + 1);
                this.f181a.append(" - ");
                if ((byteArray[0] & 255) != 240 || byteArray.length < 5) {
                    this.f181a.append(C0484p.m355a((int) byteArray[0] & 255, -1, -1));
                } else {
                    this.f181a.append(C0484p.m355a((int) byteArray[0] & 255, (int) byteArray[3] & 255, (int) byteArray[4] & 255));
                    this.f181a.append(" (" + byteArray.length + " bytes)");
                }
                this.f181a.append(": ");
                if (NMJConfigDialog.m134a(NMJConfigDialog.this).isChecked()) {
                    while (i < byteArray.length) {
                        this.f181a.append(String.valueOf(C0484p.m353a(byteArray[i])) + " ");
                        i++;
                    }
                } else {
                    while (i < byteArray.length) {
                        this.f181a.append(String.valueOf(byteArray[i] & 255) + " ");
                        i++;
                    }
                }
                this.f181a.append("\n");
                this.f178a++;
                if (this.f179a.getLineCount() < ((int) ((((float) this.f179a.getHeight()) / this.f179a.getTextSize()) - 6.0f))) {
                    this.f179a.setText(this.f181a.toString());
                } else {
                    this.f181a.delete(0, length);
                    this.f179a.setText(this.f181a.toString());
                }
                data.clear();
            } catch (Exception e) {
            }
        }
    }

    public final void systemChanged(int i, int i2, int i3) {
        if (i < 0) {
            switch (i2) {
                case 528:
                    Message obtain = Message.obtain();
                    obtain.what = 528;
                    Bundle bundle = new Bundle();
                    bundle.putInt("SCAN_EVENT", i3);
                    obtain.setData(bundle);
                    this.f115a.sendMessage(obtain);
                    return;
                default:
                    return;
            }
        } else {
            C0484p.m369a(4, "Cfg dlg - System changed ", i, i2, i3);
            Message obtain2 = Message.obtain();
            obtain2.what = i2;
            Bundle bundle2 = new Bundle();
            bundle2.putIntArray("CON_STATE", new int[]{i, i2, i3});
            obtain2.setData(bundle2);
            this.f115a.sendMessage(obtain2);
        }
    }

    public final void systemError(int i, int i2, String str) {
        String str2 = "System error: " + i + " " + i2 + " " + str;
        Message obtain = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("NMJ_ERR", String.valueOf(str2.toString()) + "\n");
        obtain.setData(bundle);
        C0484p.logln(1, "Cfg dlg - System error on ch: " + i + ", code: " + i2 + ", desc: " + str);
    }

    /* renamed from: de.humatic.nmj.NMJConfigDialog$b */
    class C0446b extends Handler {

        /* renamed from: a */
        private int f182a;

        private C0446b(TextView textView) {
            new StringBuffer();
        }

        /* synthetic */ C0446b(NMJConfigDialog nMJConfigDialog, TextView textView, byte b) {
            this((TextView) null);
        }

        public final void handleMessage(Message message) {
            int[] intArray;
            try {
                switch (message.what) {
                    case 4:
                        Bundle data = message.getData();
                        if (data != null && (intArray = data.getIntArray("CON_STATE")) != null && intArray.length >= 3) {
                            if (intArray[2] == 512) {
                                if (NMJConfigDialog.m134a(NMJConfigDialog.this).hasMessages(4096)) {
                                    NMJConfigDialog.m134a(NMJConfigDialog.this).removeMessages(4096);
                                }
                                Message obtain = Message.obtain();
                                obtain.what = 4096;
                                NMJConfigDialog.m134a(NMJConfigDialog.this).sendMessageDelayed(obtain, 1000);
                                return;
                            } else if (intArray[2] == 8 || intArray[2] == 16 || intArray[2] == 4) {
                                NMJConfigDialog.m134a(NMJConfigDialog.this);
                                return;
                            } else if (intArray[2] == 4096) {
                                NMJConfigDialog.this.m164a("Channel " + (intArray[0] + 1), String.valueOf(NMJConfig.getIP(intArray[0])) + " did not respond to innvitation!");
                                return;
                            } else if (intArray[2] == 1024) {
                                NMJConfigDialog.m176b(NMJConfigDialog.this, intArray[0], "nmj Ch." + (intArray[0] + 1), NMJConfig.getIP(NMJConfigDialog.m134a(NMJConfigDialog.this)) == null ? NMJConfig.m40a(intArray[0], 0) : NMJConfig.getIP(intArray[0]));
                                return;
                            } else if (intArray[2] == 2048) {
                                NMJConfigDialog.this.m164a("Channel " + (intArray[0] + 1), String.valueOf(NMJConfig.getIP(intArray[0])) + " rejected connection attempt!");
                                return;
                            } else if (intArray[2] == 32) {
                                NMJConfigDialog.m134a(NMJConfigDialog.this);
                                return;
                            } else if (intArray[2] == 64 || intArray[2] == 128) {
                                NMJConfigDialog.m134a(NMJConfigDialog.this);
                                if (intArray[0] == NMJConfigDialog.m134a(NMJConfigDialog.this)) {
                                    NMJConfigDialog.this.f121a = true;
                                    C0502v[] a = NMJConfig.m39a(NMJConfigDialog.m134a(NMJConfigDialog.this));
                                    StringBuffer stringBuffer = new StringBuffer();
                                    if (a != null) {
                                        for (C0502v vVar : a) {
                                            stringBuffer.append(vVar.f563a);
                                            stringBuffer.append(", ");
                                        }
                                    }
                                    NMJConfigDialog.m134a(NMJConfigDialog.this).setText(stringBuffer.toString());
                                    NMJConfigDialog.this.f121a = false;
                                    return;
                                }
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    case 256:
                        NMJConfigDialog.this.f121a = false;
                        return;
                    case InputDeviceCompat.SOURCE_KEYBOARD:
                        NMJConfigDialog.m134a(NMJConfigDialog.this).setText("");
                        return;
                    case 528:
                        int i = message.getData().getInt("SCAN_EVENT");
                        if (i == 0) {
                            this.f182a = NMJConfig.getNumChannels();
                            NMJConfigDialog.m134a(NMJConfigDialog.this).setVisibility(0);
                            NMJConfigDialog.m134a(NMJConfigDialog.this).setText("Scaning for Bluetooth MIDI servers");
                            return;
                        } else if (i == 1) {
                            NMJConfigDialog.m134a(NMJConfigDialog.this).setVisibility(4);
                            if (NMJConfig.getNumChannels() != this.f182a) {
                                NMJConfigDialog.m134a(NMJConfigDialog.this).setText("Found " + (NMJConfig.getNumChannels() - this.f182a) + " Bluetooth server(s)");
                                NMJConfigDialog.m134a(NMJConfigDialog.this).setSelection(NMJConfigDialog.m134a(NMJConfigDialog.this).getCount() - 1);
                                NMJConfigDialog.m134a(NMJConfigDialog.this).mo8123a(NMJConfigDialog.m134a(NMJConfigDialog.this).getCount() - 1);
                            } else {
                                NMJConfigDialog.m134a(NMJConfigDialog.this).setText("No servers found");
                            }
                            Message obtain2 = Message.obtain();
                            obtain2.what = InputDeviceCompat.SOURCE_KEYBOARD;
                            sendMessageDelayed(obtain2, 3000);
                            return;
                        } else {
                            return;
                        }
                    case 4096:
                        NMJConfigDialog.m134a(NMJConfigDialog.this);
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private Bitmap m136a(String str) {
        try {
            return BitmapFactory.decodeStream(getClass().getClassLoader().getResourceAsStream("de/humatic/nmj/img/" + str));
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: de.humatic.nmj.NMJConfigDialog$NMJInputClient */
    class NMJInputClient implements NetworkMidiListener {
        NMJInputClient() {
        }

        public void midiReceived(int i, int i2, byte[] bArr, long j) {
            if (NMJConfigDialog.m134a(NMJConfigDialog.this).getDisplayedChild() == 1 && C0484p.m357a((int) bArr[0] & 255, NMJConfigDialog.m169b(NMJConfigDialog.this))) {
                Message obtain = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putByteArray("MIDI", bArr);
                bundle.putInt("CH", i);
                obtain.setData(bundle);
                NMJConfigDialog.m134a(NMJConfigDialog.this).sendMessage(obtain);
            }
        }
    }

    /* renamed from: de.humatic.nmj.NMJConfigDialog$NMJOutputClient */
    class NMJOutputClient implements NetworkMidiClient {
        NMJOutputClient(NMJConfigDialog nMJConfigDialog) {
        }
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || this.f160h <= 0 || keyEvent.getAction() != 0) {
            return super.dispatchKeyEvent(keyEvent);
        }
        m173b();
        return true;
    }

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* renamed from: b */
    static /* synthetic */ void m176b(NMJConfigDialog nMJConfigDialog, final int i, String str, String str2) {
        nMJConfigDialog.f102a = new AlertDialog.Builder(nMJConfigDialog);
        nMJConfigDialog.f102a.setTitle(str);
        AlertDialog.Builder builder = nMJConfigDialog.f102a;
        StringBuilder sb = new StringBuilder("Connection to ");
        if (str2 == null) {
            str2 = "client";
        }
        builder.setMessage(sb.append(str2).append(" lost!").toString());
        nMJConfigDialog.f102a.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                NMJConfigDialog.m169b(NMJConfigDialog.this);
                if (NMJConfig.getIP(i) == null) {
                    NMJConfigDialog.m134a(NMJConfigDialog.this).setText("");
                    NMJConfigDialog.m169b(NMJConfigDialog.this).setText("");
                }
            }
        });
        nMJConfigDialog.f102a.show();
    }
}
