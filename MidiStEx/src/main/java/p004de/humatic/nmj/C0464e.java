package p004de.humatic.nmj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;

/* renamed from: de.humatic.nmj.e */
final class C0464e extends ArrayAdapter<C0485q> {

    /* renamed from: a */
    private float f267a;

    /* renamed from: a */
    private int f268a = -1;

    /* renamed from: a */
    private NetworkMidiSystem f269a;

    /* renamed from: b */
    private int f270b = 16973892;

    /* renamed from: c */
    private int f271c;

    /* renamed from: d */
    private int f272d;

    C0464e(Context context, boolean z, NetworkMidiSystem networkMidiSystem) {
        super(context, 0);
        this.f269a = networkMidiSystem;
        try {
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            this.f271c = context.getResources().getDisplayMetrics().widthPixels;
            this.f272d = context.getResources().getDisplayMetrics().heightPixels;
            this.f267a = context.getResources().getDisplayMetrics().density;
            if (z) {
                this.f270b = 16973894;
            }
        } catch (Exception e) {
        }
        setNotifyOnChange(true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void m242a(int i) {
        this.f268a = i;
    }

    /* renamed from: a */
    private Bitmap mo8123a(int i) {
        String str = "wifi.png";
        try {
            if (NMJConfig.getMode(i) == 0) {
                str = "mc.png";
            } else if (NMJConfig.getMode(i) == 4) {
                str = "adb.png";
            } else if (NMJConfig.getMode(i) == 2) {
                str = "bt.png";
            } else if (NMJConfig.getMode(i) == 5) {
                str = "usb.png";
            } else if (NMJConfig.getMode(i) == 7) {
                str = "com.png";
            } else if (NMJConfig.getMode(i) == 6) {
                str = "ws.png";
            } else if (NMJConfig.getMode(i) == 32) {
                str = "mnet.png";
            }
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("de/humatic/nmj/img/" + str);
            if (this.f267a > 1.0f) {
                return BitmapFactory.decodeStream(resourceAsStream);
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(resourceAsStream);
            return Bitmap.createScaledBitmap(decodeStream, (int) ((((float) decodeStream.getWidth()) / 2.0f) * this.f267a), (int) ((((float) decodeStream.getHeight()) / 2.0f) * this.f267a), true);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: de.humatic.nmj.e$a */
    static class C0465a {

        /* renamed from: a */
        public ImageView f273a;

        /* renamed from: a */
        public TextView f274a;

        /* renamed from: b */
        public TextView f275b;

        /* renamed from: c */
        public TextView f276c;

        private C0465a() {
        }

        /* synthetic */ C0465a(byte b) {
            this();
        }
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        ViewGroup viewGroup2;
        C0465a aVar;
        int i2;
        if (view == null) {
            Context context = getContext();
            viewGroup2 = new LinearLayout(context);
            viewGroup2.setPadding(5, 5, 2, 5);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 0.1f);
            TextView textView = new TextView(context);
            textView.setTextAppearance(context, this.f270b);
            textView.setTextColor(-6710887);
            textView.setGravity(17);
            viewGroup2.addView(textView, layoutParams);
            viewGroup2.addView(new ImageView(context), layoutParams);
            new LinearLayout.LayoutParams(-2, -2);
            TextView textView2 = new TextView(context);
            textView2.setTextAppearance(context, this.f270b);
            textView2.setMaxLines(1);
            textView2.setSingleLine(true);
            new LinearLayout.LayoutParams(-2, -2).setMargins(10, (int) (4.0f * this.f267a), 0, (int) (4.0f * this.f267a));
            int i3 = 9;
            if (this.f267a >= 2.0f && this.f271c > 2000) {
                i3 = 10;
            } else if (((double) this.f267a) == 1.5d && this.f271c > 1300) {
                i3 = 8;
            }
            textView2.setMinEms(i3);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1, 0.65f);
            layoutParams2.setMargins(8, 0, 0, 0);
            viewGroup2.addView(textView2, layoutParams2);
            TextView textView3 = new TextView(context);
            textView3.setText("■");
            textView3.setTextColor(-14540254);
            textView3.setGravity(17);
            textView3.setTextAppearance(context, this.f270b);
            viewGroup2.addView(textView3, new LinearLayout.LayoutParams(0, -1, 0.15f));
        } else {
            viewGroup2 = view;
        }
        Object tag = viewGroup2.getTag();
        if (tag == null || !(tag instanceof C0465a)) {
            aVar = new C0465a((byte) 0);
            aVar.f274a = (TextView) ((ViewGroup) viewGroup2).getChildAt(0);
            aVar.f273a = (ImageView) ((ViewGroup) viewGroup2).getChildAt(1);
            aVar.f275b = (TextView) ((ViewGroup) viewGroup2).getChildAt(2);
            aVar.f276c = (TextView) ((ViewGroup) viewGroup2).getChildAt(3);
            viewGroup2.setTag(aVar);
        } else {
            aVar = (C0465a) tag;
        }
        C0485q qVar = (C0485q) getItem(i);
        try {
            aVar.f273a.setImageBitmap(mo8123a(i));
            if (i == this.f268a) {
                aVar.f275b.setTextColor(Build.VERSION.SDK_INT > 0 ? -13388315 : -5978567);
            } else {
                aVar.f275b.setTextColor(-2236963);
            }
            aVar.f274a.setText(String.valueOf(String.valueOf(i + 1)) + "  ");
            aVar.f275b.setText(qVar.mo8165a());
            TextView textView4 = aVar.f276c;
            if (this.f269a != null) {
                if (NMJConfig.getMode(i) == 1 || NMJConfig.getMode(i) == 2 || NMJConfig.getMode(i) == 6) {
                    switch (NMJConfig.getRTPState(i)) {
                        case 0:
                        case 512:
                        case 4096:
                            i2 = 0;
                            break;
                        case 4:
                        case 8:
                        case 16:
                        case 128:
                            if (Build.VERSION.SDK_INT <= 0) {
                                i2 = -5978567;
                                break;
                            } else {
                                i2 = -1118482;
                                break;
                            }
                        case 1024:
                            if (NMJConfig.getIP(i) == null) {
                                if (Build.VERSION.SDK_INT <= 0) {
                                    i2 = -5978567;
                                    break;
                                } else {
                                    i2 = -1118482;
                                    break;
                                }
                            } else {
                                i2 = ViewCompat.MEASURED_STATE_MASK;
                                break;
                            }
                        default:
                            if (this.f269a.isOpen(NMJConfig.getIO(i), i)) {
                                if (Build.VERSION.SDK_INT <= 0) {
                                    i2 = -3407872;
                                    break;
                                } else {
                                    i2 = -13388315;
                                    break;
                                }
                            }
                            break;
                    }
                } else {
                    i2 = this.f269a.isOpen(NMJConfig.getIO(i), i) ? Build.VERSION.SDK_INT > 0 ? -13388315 : -3407872 : 0;
                    textView4.setTextColor(i2);
                    return viewGroup2;
                }
            }
            i2 = 0;
            textView4.setTextColor(i2);
        } catch (Exception e) {
        }
        return viewGroup2;
    }
}
