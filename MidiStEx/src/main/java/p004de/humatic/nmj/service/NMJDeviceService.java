package p004de.humatic.nmj.service;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.media.midi.MidiDeviceService;
import android.media.midi.MidiDeviceStatus;
import android.media.midi.MidiReceiver;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;
import p004de.humatic.nmj.NMJConfig;
import p004de.humatic.nmj.NMJSystemListener;
import p004de.humatic.nmj.NetworkMidiClient;
import p004de.humatic.nmj.NetworkMidiInput;
import p004de.humatic.nmj.NetworkMidiListener;
import p004de.humatic.nmj.NetworkMidiOutput;
import p004de.humatic.nmj.NetworkMidiSystem;

/* renamed from: de.humatic.nmj.service.NMJDeviceService */
public class NMJDeviceService extends MidiDeviceService implements NetworkMidiClient {
    public static final int AMM_INPUT = 272;
    public static final int AMM_STATE = 6;
    public static final int CHANNELS = 2;
    public static final int CONNECT = 0;
    public static final int ERROR_LOG = 7;
    public static final int LINK = 3;
    public static final int NMJ_ERROR = 17;
    public static final int NMJ_EVENT = 16;
    public static final int NMJ_INPUT = 256;
    public static final int NMJ_STATE = 5;
    public static final int PORTS = 1;
    public static final int SET_LINK = 4;

    /* renamed from: a */
    private byte f460a;

    /* renamed from: a */
    private int f461a = -1;

    /* renamed from: a */
    private SharedPreferences.Editor f462a;

    /* renamed from: a */
    private SharedPreferences f463a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Messenger f464a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public NetworkMidiSystem f465a;

    /* renamed from: a */
    private C0491b f466a;

    /* renamed from: a */
    private boolean f467a;

    /* renamed from: a */
    private int[] f468a;

    /* renamed from: a */
    private long[] f469a;

    /* renamed from: a */
    private MidiReceiver[] f470a;

    /* renamed from: a */
    private NetworkMidiInput[] f471a;

    /* renamed from: a */
    private NetworkMidiOutput[] f472a;

    /* renamed from: a */
    private Exception[] f473a;

    /* renamed from: a */
    private Vector<Bundle>[] f474a;

    /* renamed from: b */
    private byte f475b;

    /* renamed from: b */
    private int f476b = 3;

    /* renamed from: b */
    private Messenger f477b = new Messenger(new C0493d());

    /* renamed from: b */
    private boolean f478b;

    /* renamed from: b */
    private long[] f479b;

    /* renamed from: b */
    private MidiReceiver[] f480b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f481c = 0;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public boolean f482c;

    /* renamed from: d */
    private int f483d = 4;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f484d;

    /* renamed from: e */
    private boolean f485e;

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT < 23) {
            m440a(-1, "NMJDeviceService requires Android 6 or larger");
            return;
        }
        this.f463a = PreferenceManager.getDefaultSharedPreferences(this);
        this.f462a = this.f463a.edit();
        this.f467a = this.f463a.getLong("firstRun", -1) == -1;
        this.f478b = this.f463a.getBoolean("reset", false);
        if (this.f467a || this.f478b) {
            try {
                Vector a = m431a();
                this.f483d = a.size();
                NMJConfig.edit(this, true);
                NMJConfig.setNumChannels(this.f483d);
                for (int i = 0; i < this.f483d; i++) {
                    if (((NMJChannel) a.get(i)).mode >= 0) {
                        NMJConfig.setMode(i, ((NMJChannel) a.get(i)).mode);
                    }
                    NMJConfig.setName(i, ((NMJChannel) a.get(i)).name);
                    NMJConfig.setIP(i, ((NMJChannel) a.get(i)).f459ip);
                    if (((NMJChannel) a.get(i)).port > 0) {
                        NMJConfig.setPort(i, ((NMJChannel) a.get(i)).port);
                    }
                    if (((NMJChannel) a.get(i)).localPort > 0) {
                        NMJConfig.setLocalPort(i, ((NMJChannel) a.get(i)).localPort);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        m431a();
        if (this.f461a > 0) {
            this.f471a = new NetworkMidiInput[this.f461a];
            this.f472a = new NetworkMidiOutput[this.f461a];
            this.f468a = new int[this.f461a];
            this.f474a = new Vector[this.f461a];
            for (int i2 = 0; i2 < this.f461a; i2++) {
                this.f474a[i2] = new Vector<>();
            }
            this.f469a = new long[this.f461a];
            this.f479b = new long[this.f461a];
            this.f473a = new Exception[(this.f461a * 2)];
            if (this.f470a == null) {
                this.f470a = new MidiReceiver[this.f461a];
                this.f480b = new MidiReceiver[this.f461a];
                for (int i3 = 0; i3 < this.f461a; i3++) {
                    this.f470a[i3] = new C0494e(this, i3, "VIO in " + (i3 + 1), (byte) 0);
                }
            }
            new AsyncTask() {
                /* access modifiers changed from: protected */
                public final Object doInBackground(Object... objArr) {
                    try {
                        NMJDeviceService.this.f465a = NetworkMidiSystem.get(this);
                        NMJConfig.setBasePort(1, NMJDeviceService.m423a(NMJDeviceService.this).getInt("rtp_base_port", 6100));
                        NMJConfig.setBasePort(6, NMJDeviceService.m423a(NMJDeviceService.this).getInt("mws_base_port", 8100));
                        NMJConfig.setBasePort(4, NMJDeviceService.m423a(NMJDeviceService.this).getInt("adb_base_port", 10100));
                        NMJConfig.setFlags(-1, NMJDeviceService.m423a(NMJDeviceService.this).getInt("global_flags", NMJDeviceService.m467c(NMJDeviceService.this)));
                        NMJConfig.addSystemListener(new C0492c(NMJDeviceService.this, (byte) 0));
                        return NMJDeviceService.m423a(NMJDeviceService.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                /* access modifiers changed from: protected */
                public final void onPostExecute(Object obj) {
                }
            }.execute(new Object[1]);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            if (this.f465a != null) {
                this.f485e = true;
                this.f465a.exit();
                this.f483d = this.f463a.getInt("defChCnt", 3);
                int[] iArr = new int[(this.f461a * 2)];
                boolean z = false;
                for (int i = 0; i < this.f461a; i++) {
                    if (this.f463a.getInt("nmj_in_" + i, 2) > this.f483d - 1) {
                        iArr[i << 1] = this.f463a.getInt("nmj_in_" + i, 2);
                        iArr[(i << 1) + 1] = this.f463a.getInt("nmj_out_" + i, 2);
                        z = true;
                    }
                }
                if (!z) {
                    NMJConfig.cleanup(this.f483d - 1, (int[]) null);
                    return;
                }
                int[] cleanup = NMJConfig.cleanup(this.f483d - 1, iArr);
                for (int i2 = 0; i2 < this.f461a; i2++) {
                    if (cleanup[i2 << 1] != -1) {
                        this.f462a.putInt("nmj_in_" + i2, cleanup[i2 << 1]);
                    }
                    if (cleanup[(i2 << 1) + 1] != -1) {
                        this.f462a.putInt("nmj_out_" + i2, cleanup[(i2 << 1) + 1]);
                    }
                }
                this.f462a.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: de.humatic.nmj.service.NMJDeviceService$c */
    class C0492c implements NMJSystemListener {
        private C0492c() {
        }

        /* synthetic */ C0492c(NMJDeviceService nMJDeviceService, byte b) {
            this();
        }

        public final void systemChanged(int i, int i2, int i3) {
            int a = NMJDeviceService.m427a(NMJDeviceService.this, i);
            if (i >= 0 && i2 == -1 && (i == NMJDeviceService.m423a(NMJDeviceService.this).getInt("nmj_in_" + a, -1) || i == NMJDeviceService.m423a(NMJDeviceService.this).getInt("nmj_out_" + a, -1))) {
                NMJDeviceService.m423a(NMJDeviceService.this).putInt("nmj_in_" + a, -1);
                NMJDeviceService.m423a(NMJDeviceService.this).putInt("nmj_out_" + a, -1);
                NMJDeviceService.m423a(NMJDeviceService.this).commit();
                NMJDeviceService.m427a(NMJDeviceService.this, a);
            }
            if (i2 == 4 && a >= 0 && a < NMJDeviceService.m423a(NMJDeviceService.this) - 1) {
                switch (i3) {
                    case 1024:
                        NMJDeviceService.m446a(NMJDeviceService.this, a, 17, new int[]{i, i3}, "Connection dropped");
                        return;
                    case 2048:
                        NMJDeviceService.m446a(NMJDeviceService.this, a, 17, new int[]{i, i3}, "Connection refused by remote session");
                        return;
                    case 4096:
                        NMJDeviceService.m446a(NMJDeviceService.this, a, 17, new int[]{i, i3}, "Remote side did not respond to invitation");
                        return;
                }
            }
            if (i2 == -1 || (i2 == 4 && i3 == 8)) {
                a = -1;
            }
            NMJDeviceService.m446a(NMJDeviceService.this, a, 16, new int[]{i, i2, i3}, (String) null);
        }

        public final void systemError(int i, int i2, String str) {
            NMJDeviceService.m440a(-1, " Error on ch " + i + ", code: " + i2 + ", desc: " + str);
            int a = NMJDeviceService.m427a(NMJDeviceService.this, i);
            NMJDeviceService.m446a(NMJDeviceService.this, a, 17, new int[]{i, i2}, str);
        }
    }

    /* renamed from: a */
    private static NMJChannel[] m458a() {
        int numChannels = NMJConfig.getNumChannels();
        Vector vector = new Vector();
        for (int i = 0; i < numChannels; i++) {
            try {
                vector.add(new NMJChannel(i));
            } catch (Exception e) {
            }
        }
        NMJChannel[] nMJChannelArr = new NMJChannel[vector.size()];
        vector.copyInto(nMJChannelArr);
        return nMJChannelArr;
    }

    /* renamed from: a */
    private static int m425a(int i, int i2) {
        int numChannels = NMJConfig.getNumChannels();
        int i3 = -1;
        for (int i4 = 0; i4 < numChannels; i4++) {
            if (NMJConfig.getIO(i4) == i2 || NMJConfig.getIO(i4) < 0) {
                i3++;
            }
            if (i3 == i) {
                return i4;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m439a(final int i, final int i2, boolean z) {
        if (this.f465a == null || this.f460a == 0) {
            if (this.f465a == null) {
                int i3 = 0;
                while (this.f465a == null) {
                    int i4 = i3 + 1;
                    if (i3 >= 50) {
                        break;
                    }
                    try {
                        Thread.currentThread();
                        Thread.sleep(10);
                        i3 = i4;
                    } catch (Exception e) {
                        i3 = i4;
                    }
                }
                if (this.f465a == null) {
                    m440a(1, "NetworkMidiSystem is and remains to be null");
                }
            }
        }
        C04892 r0 = new Runnable() {
            public final void run() {
                int a;
                int a2;
                if (Math.abs(i) == 1 && (a2 = NMJDeviceService.m428a(NMJDeviceService.this, i2, 0)) >= 0) {
                    if (NMJDeviceService.m423a(NMJDeviceService.this)[i2] != null) {
                        try {
                            NMJDeviceService.m423a(NMJDeviceService.this)[i2].close((NetworkMidiClient) null);
                        } catch (Exception e) {
                        }
                        NMJDeviceService.m423a(NMJDeviceService.this)[i2] = null;
                    }
                    try {
                        if (!((NMJDeviceService.m423a(NMJDeviceService.this) & (1 << ((i2 * 2) + 1))) == 0 || NMJDeviceService.m423a(NMJDeviceService.this)[i2] != null || a2 == -1)) {
                            NMJDeviceService.m423a(NMJDeviceService.this)[i2] = NMJDeviceService.m423a(NMJDeviceService.this).openInput(a2, new C0490a(NMJDeviceService.this, i2, (byte) 0));
                            NMJDeviceService.m440a(1, "nmj input opened: " + NMJDeviceService.m423a(NMJDeviceService.this)[i2]);
                            NMJDeviceService nMJDeviceService = NMJDeviceService.this;
                            nMJDeviceService.f481c = NMJDeviceService.m460b(nMJDeviceService) | (1 << (i2 * 2));
                            NMJDeviceService.m423a(NMJDeviceService.this)[i2] = a2;
                        }
                    } catch (Exception e2) {
                        NMJDeviceService.m423a(NMJDeviceService.this)[i2] = null;
                        if (NMJDeviceService.m460b(NMJDeviceService.this) > 0) {
                            e2.printStackTrace();
                        }
                        NMJDeviceService.m423a(NMJDeviceService.this)[i2] = e2;
                        NMJDeviceService.m446a(NMJDeviceService.this, i2, 17, new int[]{a2, -2147418111}, e2.toString());
                        return;
                    }
                }
                if (i <= 0 && (a = NMJDeviceService.m428a(NMJDeviceService.this, i2, 1)) >= 0) {
                    if (NMJDeviceService.m423a(NMJDeviceService.this)[i2] != null) {
                        try {
                            NMJDeviceService.m423a(NMJDeviceService.this)[i2].close((NetworkMidiClient) null);
                        } catch (Exception e3) {
                        }
                        NMJDeviceService.m423a(NMJDeviceService.this)[i2] = null;
                    }
                    try {
                        if ((NMJDeviceService.m423a(NMJDeviceService.this) & (1 << (i2 * 2))) != 0 && NMJDeviceService.m423a(NMJDeviceService.this)[i2] == null && a != -1) {
                            NMJDeviceService.m423a(NMJDeviceService.this)[i2] = NMJDeviceService.m423a(NMJDeviceService.this).openOutput(a, this);
                            NMJDeviceService.m440a(1, "nmj output opened: " + NMJDeviceService.m423a(NMJDeviceService.this)[i2]);
                            NMJDeviceService nMJDeviceService2 = NMJDeviceService.this;
                            nMJDeviceService2.f481c = NMJDeviceService.m460b(nMJDeviceService2) | (1 << ((i2 * 2) + 1));
                        }
                    } catch (Exception e4) {
                        NMJDeviceService.m423a(NMJDeviceService.this)[i2] = null;
                        NMJDeviceService.m423a(NMJDeviceService.this)[NMJDeviceService.m423a(NMJDeviceService.this) + i2] = e4;
                        NMJDeviceService.m446a(NMJDeviceService.this, i2, 17, new int[]{a, -2147418111}, e4.toString());
                    }
                }
            }
        };
        if (z) {
            new Thread(r0).start();
        } else {
            r0.run();
        }
    }

    public MidiReceiver[] onGetInputPortReceivers() {
        if (this.f470a == null) {
            try {
                m431a();
                if (this.f461a <= 0) {
                    return null;
                }
                this.f470a = new MidiReceiver[this.f461a];
                this.f480b = new MidiReceiver[this.f461a];
                for (int i = 0; i < this.f461a; i++) {
                    this.f470a[i] = new C0494e(this, i, "nmj in " + (i + 1), (byte) 0);
                }
            } catch (Exception e) {
            }
        }
        return this.f470a;
    }

    public void onDeviceStatusChanged(MidiDeviceStatus midiDeviceStatus) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            m441a(midiDeviceStatus);
            return;
        }
        if (this.f466a == null) {
            this.f466a = new C0491b(this, (byte) 0);
            this.f466a.start();
        }
        this.f466a.m470a(midiDeviceStatus);
    }

    /* renamed from: de.humatic.nmj.service.NMJDeviceService$b */
    class C0491b extends Thread {

        /* renamed from: a */
        private Vector<MidiDeviceStatus> f495a;

        private C0491b() {
            this.f495a = new Vector<>();
        }

        /* synthetic */ C0491b(NMJDeviceService nMJDeviceService, byte b) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public synchronized void m470a(MidiDeviceStatus midiDeviceStatus) {
            this.f495a.add(midiDeviceStatus);
            notify();
        }

        public final void run() {
            while (!NMJDeviceService.m423a(NMJDeviceService.this)) {
                synchronized (this) {
                    while (this.f495a.size() == 0) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
                try {
                    NMJDeviceService.this.m441a(this.f495a.remove(0));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m441a(MidiDeviceStatus midiDeviceStatus) {
        if (midiDeviceStatus.getDeviceInfo().getInputPortCount() > this.f461a || midiDeviceStatus.getDeviceInfo().getOutputPortCount() > this.f461a) {
            Log.e("nmj", "Invalid port count in MidiDeviceInfo");
        }
        for (int i = 0; i < this.f461a; i++) {
            byte b = this.f460a;
            int i2 = this.f481c;
            if (midiDeviceStatus.getOutputPortOpenCount(i) > 0 && (this.f460a & (1 << ((i * 2) + 1))) == 0) {
                this.f460a = (byte) (this.f460a | (1 << ((i * 2) + 1)));
                this.f480b[i] = getOutputPortReceivers()[i];
                m439a(1, i, false);
            } else if (midiDeviceStatus.getOutputPortOpenCount(i) == 0 && (this.f460a & (1 << ((i * 2) + 1))) != 0) {
                if (this.f471a[i] != null) {
                    try {
                        this.f471a[i].close((NetworkMidiClient) null);
                        this.f471a[i] = null;
                        this.f481c &= (1 << (i * 2)) ^ -1;
                    } catch (Exception e) {
                    }
                }
                this.f460a = (byte) (this.f460a & ((1 << ((i * 2) + 1)) ^ -1));
            }
            if (midiDeviceStatus.isInputPortOpen(i) && (this.f460a & (1 << (i * 2))) == 0) {
                this.f460a = (byte) (this.f460a | (1 << (i * 2)));
                m439a(0, i, false);
            } else if (!midiDeviceStatus.isInputPortOpen(i) && (this.f460a & (1 << (i * 2))) != 0) {
                if (this.f472a[i] != null) {
                    try {
                        this.f472a[i].close((NetworkMidiClient) null);
                        this.f472a[i] = null;
                        this.f481c &= (1 << ((i * 2) + 1)) ^ -1;
                    } catch (Exception e2) {
                    }
                }
                this.f460a = (byte) (this.f460a & ((1 << (i * 2)) ^ -1));
            }
            if (this.f460a != b) {
                m438a(i, 6, (int) this.f460a);
            }
            if (this.f481c != i2) {
                m438a(i, 5, this.f481c);
            }
        }
    }

    /* renamed from: de.humatic.nmj.service.NMJDeviceService$d */
    class C0493d extends Handler {
        C0493d() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: boolean} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v4 */
        /* JADX WARNING: type inference failed for: r0v5, types: [int] */
        /* JADX WARNING: type inference failed for: r0v8, types: [int] */
        /* JADX WARNING: type inference failed for: r0v24, types: [int] */
        /* JADX WARNING: type inference failed for: r0v34 */
        /* JADX WARNING: type inference failed for: r0v35 */
        /* JADX WARNING: type inference failed for: r0v36 */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0189, code lost:
            r0 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x01a2, code lost:
            r0 = null;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void handleMessage(android.os.Message r10) {
            /*
                r9 = this;
                r8 = -1
                r2 = 0
                r1 = 1
                r0 = 0
                android.os.Bundle r3 = r10.getData()
                java.lang.String r4 = "port"
                int r3 = r3.getInt(r4, r8)
                int r4 = r10.what
                switch(r4) {
                    case 0: goto L_0x0022;
                    case 1: goto L_0x0051;
                    case 2: goto L_0x0061;
                    case 3: goto L_0x0072;
                    case 4: goto L_0x0079;
                    case 5: goto L_0x0181;
                    case 6: goto L_0x019a;
                    case 7: goto L_0x0139;
                    case 256: goto L_0x01a5;
                    case 272: goto L_0x01ba;
                    default: goto L_0x0013;
                }
            L_0x0013:
                super.handleMessage(r10)
            L_0x0016:
                r0 = r2
            L_0x0017:
                if (r0 == 0) goto L_0x0021
                de.humatic.nmj.service.NMJDeviceService r1 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.os.Messenger r1 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r1)
                if (r1 != 0) goto L_0x01d0
            L_0x0021:
                return
            L_0x0022:
                de.humatic.nmj.service.NMJDeviceService r1 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.os.Messenger r2 = r10.replyTo
                r1.f464a = r2
                android.os.Message r2 = android.os.Message.obtain()
                r2.what = r0
            L_0x002f:
                de.humatic.nmj.service.NMJDeviceService r1 = p004de.humatic.nmj.service.NMJDeviceService.this
                de.humatic.nmj.NetworkMidiSystem r1 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r1)
                if (r1 != 0) goto L_0x003d
                int r1 = r0 + 1
                r3 = 50
                if (r0 < r3) goto L_0x0044
            L_0x003d:
                de.humatic.nmj.service.NMJDeviceService r0 = p004de.humatic.nmj.service.NMJDeviceService.this
                p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r0)
                r0 = r2
                goto L_0x0017
            L_0x0044:
                java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x004e }
                r4 = 10
                java.lang.Thread.sleep(r4)     // Catch:{ Exception -> 0x004e }
                r0 = r1
                goto L_0x002f
            L_0x004e:
                r0 = move-exception
                r0 = r1
                goto L_0x002f
            L_0x0051:
                java.lang.Integer r0 = new java.lang.Integer
                de.humatic.nmj.service.NMJDeviceService r2 = p004de.humatic.nmj.service.NMJDeviceService.this
                int r2 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r2)
                r0.<init>(r2)
                android.os.Message r0 = p004de.humatic.nmj.service.NMJDeviceService.createMessage(r8, r1, r0)
                goto L_0x0017
            L_0x0061:
                android.os.Message r0 = android.os.Message.obtain()
                r1 = 2
                r0.what = r1
                de.humatic.nmj.service.NMJDeviceService r1 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.os.Bundle r1 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r1)
                r0.setData(r1)
                goto L_0x0017
            L_0x0072:
                de.humatic.nmj.service.NMJDeviceService r0 = p004de.humatic.nmj.service.NMJDeviceService.this
                p004de.humatic.nmj.service.NMJDeviceService.m427a((p004de.humatic.nmj.service.NMJDeviceService) r0, (int) r3)
                r0 = r2
                goto L_0x0017
            L_0x0079:
                android.os.Bundle r4 = r10.getData()
                if (r3 >= 0) goto L_0x0088
                java.lang.String r0 = "nmj"
                java.lang.String r1 = "can't select channel for port -1"
                android.util.Log.e(r0, r1)
                r0 = r2
                goto L_0x0017
            L_0x0088:
                java.lang.String r5 = "value"
                int[] r4 = r4.getIntArray(r5)
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                de.humatic.nmj.NetworkMidiInput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)
                r5 = r5[r3]
                if (r5 == 0) goto L_0x00bd
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                de.humatic.nmj.NetworkMidiInput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)
                r5 = r5[r3]
                int r5 = r5.getChannelIndex()
                r6 = r4[r0]
                if (r5 == r6) goto L_0x00bd
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this     // Catch:{ Exception -> 0x01e4 }
                de.humatic.nmj.NetworkMidiInput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)     // Catch:{ Exception -> 0x01e4 }
                r5 = r5[r3]     // Catch:{ Exception -> 0x01e4 }
                r6 = 0
                r5.close(r6)     // Catch:{ Exception -> 0x01e4 }
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this     // Catch:{ Exception -> 0x01e4 }
                de.humatic.nmj.NetworkMidiInput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)     // Catch:{ Exception -> 0x01e4 }
                r6 = 0
                r5[r3] = r6     // Catch:{ Exception -> 0x01e4 }
            L_0x00bd:
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                de.humatic.nmj.NetworkMidiOutput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)
                r5 = r5[r3]
                if (r5 == 0) goto L_0x00ec
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                de.humatic.nmj.NetworkMidiOutput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)
                r5 = r5[r3]
                int r5 = r5.getChannelIndex()
                r6 = r4[r1]
                if (r5 == r6) goto L_0x00ec
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this     // Catch:{ Exception -> 0x01e1 }
                de.humatic.nmj.NetworkMidiOutput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)     // Catch:{ Exception -> 0x01e1 }
                r5 = r5[r3]     // Catch:{ Exception -> 0x01e1 }
                r6 = 0
                r5.close(r6)     // Catch:{ Exception -> 0x01e1 }
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this     // Catch:{ Exception -> 0x01e1 }
                de.humatic.nmj.NetworkMidiOutput[] r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)     // Catch:{ Exception -> 0x01e1 }
                r6 = 0
                r5[r3] = r6     // Catch:{ Exception -> 0x01e1 }
            L_0x00ec:
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.content.SharedPreferences$Editor r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r7 = "nmj_in_"
                r6.<init>(r7)
                java.lang.StringBuilder r6 = r6.append(r3)
                java.lang.String r6 = r6.toString()
                r7 = r4[r0]
                r5.putInt(r6, r7)
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.content.SharedPreferences$Editor r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r7 = "nmj_out_"
                r6.<init>(r7)
                java.lang.StringBuilder r6 = r6.append(r3)
                java.lang.String r6 = r6.toString()
                r7 = r4[r1]
                r5.putInt(r6, r7)
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.content.SharedPreferences$Editor r5 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r5)
                r5.commit()
                r0 = r4[r0]
                if (r0 >= 0) goto L_0x0131
                r0 = r4[r1]
                if (r0 < 0) goto L_0x0016
            L_0x0131:
                de.humatic.nmj.service.NMJDeviceService r0 = p004de.humatic.nmj.service.NMJDeviceService.this
                r0.m439a(-1, (int) r3, true)
                r0 = r2
                goto L_0x0017
            L_0x0139:
                de.humatic.nmj.service.NMJDeviceService r0 = p004de.humatic.nmj.service.NMJDeviceService.this
                r0.m462b((int) r3)
                r0 = r2
                goto L_0x0017
            L_0x0141:
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                de.humatic.nmj.NetworkMidiInput[] r3 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r3)
                r3 = r3[r0]
                if (r3 == 0) goto L_0x0159
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                int r4 = p004de.humatic.nmj.service.NMJDeviceService.m460b((p004de.humatic.nmj.service.NMJDeviceService) r3)
                int r5 = r0 << 1
                int r5 = r1 << r5
                r4 = r4 | r5
                r3.f481c = r4
            L_0x0159:
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                de.humatic.nmj.NetworkMidiOutput[] r3 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r3)
                r3 = r3[r0]
                if (r3 == 0) goto L_0x0173
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                int r4 = p004de.humatic.nmj.service.NMJDeviceService.m460b((p004de.humatic.nmj.service.NMJDeviceService) r3)
                int r5 = r0 << 1
                int r5 = r5 + 1
                int r5 = r1 << r5
                r4 = r4 | r5
                r3.f481c = r4
            L_0x0173:
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                r4 = 5
                de.humatic.nmj.service.NMJDeviceService r5 = p004de.humatic.nmj.service.NMJDeviceService.this
                int r5 = p004de.humatic.nmj.service.NMJDeviceService.m460b((p004de.humatic.nmj.service.NMJDeviceService) r5)
                r3.m438a((int) r0, (int) r4, (int) r5)
                int r0 = r0 + 1
            L_0x0181:
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                int r3 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r3)
                if (r0 < r3) goto L_0x0141
                r0 = r2
                goto L_0x0017
            L_0x018c:
                de.humatic.nmj.service.NMJDeviceService r1 = p004de.humatic.nmj.service.NMJDeviceService.this
                r3 = 6
                de.humatic.nmj.service.NMJDeviceService r4 = p004de.humatic.nmj.service.NMJDeviceService.this
                byte r4 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r4)
                r1.m438a((int) r0, (int) r3, (int) r4)
                int r0 = r0 + 1
            L_0x019a:
                de.humatic.nmj.service.NMJDeviceService r1 = p004de.humatic.nmj.service.NMJDeviceService.this
                int r1 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r1)
                if (r0 < r1) goto L_0x018c
                r0 = r2
                goto L_0x0017
            L_0x01a5:
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.os.Bundle r4 = r10.getData()
                java.lang.String r5 = "value"
                int r4 = r4.getInt(r5, r0)
                if (r4 == 0) goto L_0x01b4
                r0 = r1
            L_0x01b4:
                r3.f482c = r0
                r0 = r2
                goto L_0x0017
            L_0x01ba:
                de.humatic.nmj.service.NMJDeviceService r3 = p004de.humatic.nmj.service.NMJDeviceService.this
                android.os.Bundle r4 = r10.getData()
                java.lang.String r5 = "value"
                int r4 = r4.getInt(r5, r0)
                if (r4 == 0) goto L_0x01ce
            L_0x01c8:
                r3.f484d = r1
                r0 = r2
                goto L_0x0017
            L_0x01ce:
                r1 = r0
                goto L_0x01c8
            L_0x01d0:
                de.humatic.nmj.service.NMJDeviceService r1 = p004de.humatic.nmj.service.NMJDeviceService.this     // Catch:{ RemoteException -> 0x01db }
                android.os.Messenger r1 = p004de.humatic.nmj.service.NMJDeviceService.m423a((p004de.humatic.nmj.service.NMJDeviceService) r1)     // Catch:{ RemoteException -> 0x01db }
                r1.send(r0)     // Catch:{ RemoteException -> 0x01db }
                goto L_0x0021
            L_0x01db:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x0021
            L_0x01e1:
                r5 = move-exception
                goto L_0x00ec
            L_0x01e4:
                r5 = move-exception
                goto L_0x00bd
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.service.NMJDeviceService.C0493d.handleMessage(android.os.Message):void");
        }
    }

    public IBinder onBind(Intent intent) {
        IBinder iBinder = null;
        if (Build.VERSION.SDK_INT >= 23 && this.f461a > 0) {
            m440a(2, "MidiDeviceService, bind request: " + intent + " " + intent.getPackage() + "  " + intent.getExtras() + " " + this.f461a);
            if (intent.getAction() == null) {
                iBinder = this.f477b.getBinder();
            } else if (intent.getAction().equalsIgnoreCase("android.media.midi.MidiDeviceService")) {
                iBinder = super.onBind(intent);
            }
            m440a(2, "MidiDeviceService, binder: " + iBinder);
        }
        return iBinder;
    }

    /* renamed from: a */
    static /* synthetic */ void m446a(NMJDeviceService nMJDeviceService, int i, int i2, int[] iArr, String str) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (nMJDeviceService.f464a != null || i2 == 17) {
            Bundle bundle = new Bundle();
            if (iArr != null) {
                bundle.putIntArray("de.humatic.nmj.service.EVT", iArr);
            }
            if (str != null) {
                bundle.putString("de.humatic.nmj.service.MSG", str);
            }
            if (nMJDeviceService.f464a == null || i2 == 17) {
                bundle.putLong("de.humatic.nmj.service.TS", System.currentTimeMillis());
                bundle.putInt("port", i);
                bundle.putInt("type", 17);
                int i8 = i < 0 ? 0 : i;
                while (true) {
                    if (i8 >= (i < 0 ? nMJDeviceService.f461a : i + 1)) {
                        break;
                    }
                    if (i < 0) {
                        i5 = i8;
                    } else {
                        i5 = i;
                    }
                    if (!nMJDeviceService.m451a(i5, bundle)) {
                        Vector<Bundle>[] vectorArr = nMJDeviceService.f474a;
                        if (i < 0) {
                            i6 = i8;
                        } else {
                            i6 = i;
                        }
                        vectorArr[i6].add(bundle);
                    }
                    i8++;
                }
                if (nMJDeviceService.f464a == null) {
                    return;
                }
            }
            bundle.putInt("type", i2);
            if (i >= 0) {
                i7 = i;
            }
            while (true) {
                if (i < 0) {
                    i3 = nMJDeviceService.f461a;
                } else {
                    i3 = i + 1;
                }
                if (i7 < i3) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("value", bundle);
                    if (i < 0) {
                        i4 = i7;
                    } else {
                        i4 = i;
                    }
                    bundle2.putInt("port", i4);
                    Message obtain = Message.obtain();
                    obtain.what = i2;
                    obtain.setData(bundle2);
                    try {
                        nMJDeviceService.f464a.send(obtain);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        nMJDeviceService.f464a = null;
                    }
                    i7++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m438a(int i, int i2, int i3) {
        if (this.f464a != null && i >= 0) {
            Message obtain = Message.obtain();
            obtain.what = i2;
            Bundle bundle = new Bundle();
            bundle.putInt("port", i);
            bundle.putInt("value", (i3 >> (i * 2)) & 3);
            obtain.setData(bundle);
            try {
                this.f464a.send(obtain);
            } catch (RemoteException e) {
                e.printStackTrace();
                this.f464a = null;
            }
        }
    }

    /* renamed from: a */
    private void m437a(int i) {
        int i2;
        if (this.f464a != null) {
            if (i < 0) {
                i2 = 0;
            } else {
                i2 = i;
            }
            int i3 = i2;
            while (true) {
                if (i3 < (i < 0 ? this.f461a : i + 1)) {
                    Message obtain = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("port", i3);
                    int i4 = this.f463a.getInt("nmj_in_" + i3, -1);
                    if (i4 >= NMJConfig.getNumChannels()) {
                        this.f462a.putInt("nmj_in_" + i3, -1);
                        this.f462a.commit();
                        i4 = -1;
                    }
                    int i5 = this.f463a.getInt("nmj_out_" + i3, -1);
                    if (i5 >= NMJConfig.getNumChannels()) {
                        this.f462a.putInt("nmj_out_" + i3, -1);
                        this.f462a.commit();
                        i5 = -1;
                    }
                    bundle.putIntArray("value", new int[]{i4, i5});
                    obtain.what = 3;
                    obtain.setData(bundle);
                    try {
                        this.f464a.send(obtain);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        this.f464a = null;
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m462b(int i) {
        int i2;
        if (this.f464a != null) {
            if (i < 0) {
                i2 = 0;
            } else {
                i2 = i;
            }
            int i3 = i2;
            while (true) {
                if (i3 < (i < 0 ? this.f461a : i + 1)) {
                    Message obtain = Message.obtain();
                    obtain.what = 7;
                    if (this.f474a[i3].size() != 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("port", i3);
                        Bundle[] bundleArr = new Bundle[this.f474a[i3].size()];
                        this.f474a[i3].copyInto(bundleArr);
                        this.f474a[i3].removeAllElements();
                        bundle.putParcelableArray("value", bundleArr);
                        obtain.setData(bundle);
                        try {
                            this.f464a.send(obtain);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            this.f464a = null;
                        }
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m451a(int i, Bundle bundle) {
        for (int i2 = 0; i2 < this.f474a[i].size(); i2++) {
            Bundle bundle2 = this.f474a[i].get(i2);
            if (bundle2.containsKey("de.humatic.nmj.service.MSG") && bundle2.getString("de.humatic.nmj.service.MSG").equalsIgnoreCase(bundle.getString("de.humatic.nmj.service.MSG")) && bundle2.containsKey("de.humatic.nmj.service.EVT") && bundle.containsKey("de.humatic.nmj.service.EVT") && bundle2.getIntArray("de.humatic.nmj.service.EVT")[0] == bundle.getIntArray("de.humatic.nmj.service.EVT")[0]) {
                if (bundle.getLong("de.humatic.nmj.service.TS") - bundle2.getLong("de.humatic.nmj.service.TS") < 200) {
                    return true;
                }
                if (bundle.getLong("de.humatic.nmj.service.TS") - bundle2.getLong("de.humatic.nmj.service.TS") < 3000) {
                    bundle2.putLong("de.humatic.nmj.service.TS", bundle.getLong("de.humatic.nmj.service.TS"));
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    private Bundle m431a() {
        NMJChannel[] a = m431a();
        Bundle bundle = new Bundle();
        Bundle[] bundleArr = new Bundle[a.length];
        for (int i = 0; i < a.length; i++) {
            NMJChannel nMJChannel = a[i];
            Bundle bundle2 = new Bundle();
            bundle2.putInt("id", nMJChannel.f457id);
            bundle2.putString("name", nMJChannel.name);
            bundle2.putInt("mode", nMJChannel.mode);
            bundle2.putString("ip", nMJChannel.f459ip);
            bundle2.putInt("io", nMJChannel.f458io);
            bundle2.putInt("port", nMJChannel.port);
            bundle2.putInt("local_port", nMJChannel.localPort);
            bundle2.putInt("flags", nMJChannel.flags);
            bundle2.putInt("nwa", nMJChannel.nwa);
            bundleArr[i] = bundle2;
        }
        bundle.putParcelableArray("value", bundleArr);
        return bundle;
    }

    /* renamed from: de.humatic.nmj.service.NMJDeviceService$e */
    class C0494e extends MidiReceiver {

        /* renamed from: a */
        private int f498a;

        /* renamed from: a */
        private Vector<byte[]> f500a;

        /* renamed from: a */
        private byte[][] f501a;

        /* renamed from: b */
        private int f502b;

        /* renamed from: b */
        private byte[][] f503b;

        /* renamed from: c */
        private int f504c;

        /* renamed from: c */
        private byte[][] f505c;

        /* renamed from: d */
        private int f506d;

        /* renamed from: e */
        private int f507e;

        private C0494e(int i, String str) {
            super(1460);
            new StringBuffer();
            this.f498a = 0;
            this.f501a = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 3});
            this.f503b = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 2});
            this.f505c = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{8, 1});
            this.f498a = i;
        }

        /* synthetic */ C0494e(NMJDeviceService nMJDeviceService, int i, String str, byte b) {
            this(i, str);
        }

        public final void onSend(byte[] bArr, int i, int i2, long j) throws IOException {
            int i3;
            int i4;
            int i5;
            byte[] bArr2;
            byte[] bArr3;
            boolean z;
            if (NMJDeviceService.m423a(NMJDeviceService.this)[this.f498a] != null) {
                int i6 = i;
                while (i3 < i + i2) {
                    if ((bArr[i3] & 128) != 0) {
                        this.f507e = bArr[i3] & 255;
                    }
                    int i7 = this.f507e;
                    if ((i7 & 255) < 240) {
                        i4 = ((i7 & 255) < 192 || (i7 & 255) > 223) ? 3 : 2;
                    } else if ((i7 & 255) <= 240) {
                        int i8 = i7 & 255;
                        i4 = -1;
                    } else if ((i7 & 255) == 242) {
                        i4 = 3;
                    } else {
                        i4 = ((i7 & 255) == 241 || (i7 & 255) == 243) ? 2 : 1;
                    }
                    if (i5 == 1) {
                        byte[][] bArr4 = this.f505c;
                        int i9 = this.f506d;
                        this.f506d = i9 + 1;
                        bArr2 = bArr4[i9];
                        if (this.f506d > this.f505c.length - 1) {
                            this.f506d = 0;
                            bArr3 = bArr2;
                            z = true;
                        }
                        bArr3 = bArr2;
                        z = true;
                    } else if (i5 == 2) {
                        byte[][] bArr5 = this.f503b;
                        int i10 = this.f504c;
                        this.f504c = i10 + 1;
                        bArr2 = bArr5[i10];
                        if (this.f504c > this.f503b.length - 1) {
                            this.f504c = 0;
                            bArr3 = bArr2;
                            z = true;
                        }
                        bArr3 = bArr2;
                        z = true;
                    } else if (i5 == 3) {
                        byte[][] bArr6 = this.f501a;
                        int i11 = this.f502b;
                        this.f502b = i11 + 1;
                        bArr2 = bArr6[i11];
                        if (this.f502b > this.f501a.length - 1) {
                            this.f502b = 0;
                            bArr3 = bArr2;
                            z = true;
                        }
                        bArr3 = bArr2;
                        z = true;
                    } else {
                        int i12 = i3;
                        while (true) {
                            int i13 = i12 + 1;
                            if ((bArr[i12] & 255) == 247) {
                                i5 = i13 - i3;
                                if (this.f500a == null || this.f500a.size() <= 0) {
                                    bArr3 = new byte[i5];
                                    z = true;
                                } else {
                                    byte[] bArr7 = new byte[i5];
                                    System.arraycopy(bArr, i3, bArr7, 0, i5);
                                    this.f500a.add(bArr7);
                                    i5 = 0;
                                    for (int i14 = 0; i14 < this.f500a.size(); i14++) {
                                        i5 += this.f500a.get(i14).length;
                                    }
                                    bArr3 = new byte[i5];
                                    i3 = 0;
                                    while (this.f500a.size() > 0) {
                                        byte[] remove = this.f500a.remove(0);
                                        System.arraycopy(remove, 0, bArr3, i3, remove.length);
                                        i3 = remove.length + i3;
                                    }
                                    z = false;
                                }
                            } else if (i13 > i + i2) {
                                if (this.f500a == null) {
                                    this.f500a = new Vector<>();
                                }
                                byte[] bArr8 = new byte[((i + i2) - i3)];
                                System.arraycopy(bArr, i3, bArr8, 0, bArr8.length);
                                this.f500a.add(bArr8);
                                return;
                            } else {
                                i12 = i13;
                            }
                        }
                    }
                    if (z) {
                        System.arraycopy(bArr, i3, bArr3, 0, i5);
                    }
                    try {
                        NMJDeviceService.m423a(NMJDeviceService.this)[this.f498a].sendMidi(bArr3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    i6 = i3 + i5;
                }
            }
            if (NMJDeviceService.m460b(NMJDeviceService.this) && NMJDeviceService.m423a(NMJDeviceService.this) != null && j > NMJDeviceService.m423a(NMJDeviceService.this)[this.f498a] + 50) {
                Message obtain = Message.obtain();
                obtain.what = NMJDeviceService.AMM_INPUT;
                Bundle bundle = new Bundle();
                bundle.putInt("port", this.f498a);
                obtain.setData(bundle);
                NMJDeviceService.m423a(NMJDeviceService.this)[this.f498a] = j;
                try {
                    NMJDeviceService.m423a(NMJDeviceService.this).send(obtain);
                } catch (RemoteException e2) {
                    NMJDeviceService.this.f464a = null;
                    e2.printStackTrace();
                }
            }
        }
    }

    /* renamed from: de.humatic.nmj.service.NMJDeviceService$a */
    class C0490a implements NetworkMidiListener {

        /* renamed from: a */
        private int f492a;

        private C0490a(int i) {
            this.f492a = i;
        }

        /* synthetic */ C0490a(NMJDeviceService nMJDeviceService, int i, byte b) {
            this(i);
        }

        public final void midiReceived(int i, int i2, byte[] bArr, long j) {
            try {
                NMJDeviceService.m423a(NMJDeviceService.this)[this.f492a].send(bArr, 0, bArr.length);
                if (NMJDeviceService.m467c(NMJDeviceService.this) && NMJDeviceService.m423a(NMJDeviceService.this) != null && j > NMJDeviceService.m460b(NMJDeviceService.this)[this.f492a] + 50 && this.f492a != -1) {
                    Message obtain = Message.obtain();
                    obtain.what = 256;
                    Bundle bundle = new Bundle();
                    bundle.putInt("port", this.f492a);
                    obtain.setData(bundle);
                    NMJDeviceService.m460b(NMJDeviceService.this)[this.f492a] = j;
                    try {
                        NMJDeviceService.m423a(NMJDeviceService.this).send(obtain);
                    } catch (RemoteException e) {
                        NMJDeviceService.this.f464a = null;
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private synchronized void m436a() {
        int a;
        if (this.f461a < 0) {
            try {
                XmlResourceParser xml = getResources().getXml(getResources().getIdentifier("nmj", "xml", getPackageName()));
                String str = "";
                int eventType = xml.getEventType();
                while (eventType != 1) {
                    if (eventType != 2) {
                        if (eventType == 3) {
                            try {
                                if (xml.getName().equalsIgnoreCase("debug")) {
                                    int parseInt = Integer.parseInt(str);
                                    this.f475b = (byte) parseInt;
                                    NMJConfig.setDebugLevel(parseInt);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (eventType == 4) {
                            str = xml.getText();
                        }
                    }
                    eventType = xml.next();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                Field[] declaredFields = Class.forName(String.valueOf(getPackageName()) + ".R$xml").getDeclaredFields();
                int i = 0;
                while (true) {
                    if (i < declaredFields.length) {
                        if (!declaredFields[i].getName().equalsIgnoreCase("nmj") && declaredFields[i].getName().indexOf("$") == -1 && (a = m424a(declaredFields[i].getInt((Object) null))) > 0) {
                            this.f461a = a;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return;
    }

    /* renamed from: a */
    private int m424a(int i) {
        int i2 = 0;
        if (i <= 0) {
            return 1;
        }
        try {
            XmlResourceParser xml = getResources().getXml(i);
            int i3 = 0;
            int eventType = xml.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    try {
                        if (xml.getName().equalsIgnoreCase("device")) {
                            String attributeValue = xml.getAttributeValue((String) null, "private");
                            if ((attributeValue == null || !Boolean.parseBoolean(attributeValue)) && getPackageName().indexOf("de.humatic") != 0) {
                                m440a(-1, "NMJDeviceService subclasses must be application private. Please add a private=\"true\" attribute to your device info's <device/> node.");
                                this.f461a = 0;
                                return -1;
                            }
                        } else if (xml.getName().equalsIgnoreCase("input-port")) {
                            i3++;
                        } else if (xml.getName().equalsIgnoreCase("output-port")) {
                            i2++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                eventType = xml.next();
            }
            return Math.max(i3, i2);
        } catch (Exception e2) {
            return -1;
        }
    }

    /* renamed from: a */
    private Vector<NMJChannel> m435a() {
        boolean z;
        boolean z2;
        int parseInt;
        Vector<NMJChannel> vector = new Vector<>();
        try {
            XmlResourceParser xml = getResources().getXml(getResources().getIdentifier("nmj", "xml", getPackageName()));
            int eventType = xml.getEventType();
            NMJChannel nMJChannel = null;
            String str = "";
            int i = 0;
            int i2 = 0;
            boolean z3 = false;
            boolean z4 = false;
            while (eventType != 1) {
                if (eventType == 2) {
                    try {
                        if (xml.getName().equalsIgnoreCase("channel")) {
                            NMJChannel nMJChannel2 = new NMJChannel();
                            nMJChannel2.f457id = i;
                            vector.add(nMJChannel2);
                            i++;
                            nMJChannel = nMJChannel2;
                            z = z3;
                            z2 = true;
                        }
                        boolean z5 = z4;
                        z = z3;
                        z2 = z5;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (eventType == 3) {
                    String name = xml.getName();
                    if (z4) {
                        if (name.equalsIgnoreCase("name")) {
                            nMJChannel.name = str;
                            if (nMJChannel.name.indexOf("%DEVICE%") != -1) {
                                nMJChannel.name = String.valueOf(Build.MODEL) + nMJChannel.name.substring(nMJChannel.name.lastIndexOf("%") + 1);
                                boolean z6 = z4;
                                z = z3;
                                z2 = z6;
                            }
                            boolean z52 = z4;
                            z = z3;
                            z2 = z52;
                        } else if (name.equalsIgnoreCase("ip")) {
                            nMJChannel.f459ip = str;
                            boolean z7 = z4;
                            z = z3;
                            z2 = z7;
                        } else if (name.equalsIgnoreCase("mode")) {
                            nMJChannel.mode = Integer.parseInt(str);
                            boolean z8 = z4;
                            z = nMJChannel.mode == 2 || nMJChannel.mode == 5 || nMJChannel.mode == 7;
                            z2 = z8;
                        } else if (name.equalsIgnoreCase("port")) {
                            nMJChannel.port = Integer.parseInt(str);
                            boolean z9 = z4;
                            z = z3;
                            z2 = z9;
                        } else if (name.equalsIgnoreCase("local_port")) {
                            nMJChannel.localPort = Integer.parseInt(str);
                            boolean z10 = z4;
                            z = z3;
                            z2 = z10;
                        } else if (name.equalsIgnoreCase("id")) {
                            nMJChannel.f457id = Integer.parseInt(str);
                            boolean z11 = z4;
                            z = z3;
                            z2 = z11;
                        } else if (name.equalsIgnoreCase("io")) {
                            nMJChannel.f458io = Integer.parseInt(str);
                            boolean z12 = z4;
                            z = z3;
                            z2 = z12;
                        } else if (name.equalsIgnoreCase("flags")) {
                            nMJChannel.flags = Integer.parseInt(str);
                            boolean z13 = z4;
                            z = z3;
                            z2 = z13;
                        } else if (name.equalsIgnoreCase("nwa")) {
                            nMJChannel.nwa = Integer.parseInt(str);
                            boolean z14 = z4;
                            z = z3;
                            z2 = z14;
                        } else {
                            if (name.equalsIgnoreCase("channel")) {
                                z4 = false;
                                if (z3) {
                                    m440a(-1, "Unsupported mode, removing " + nMJChannel.toString());
                                    vector.remove(nMJChannel);
                                    i--;
                                    z = z3;
                                    z2 = false;
                                }
                            }
                            boolean z522 = z4;
                            z = z3;
                            z2 = z522;
                        }
                    } else if (name.equalsIgnoreCase("link")) {
                        this.f462a.putInt("nmj_in_" + i2, Integer.parseInt(str.split(",")[0]));
                        this.f462a.putInt("nmj_out_" + i2, Integer.parseInt(str.split(",")[1]));
                        i2++;
                        boolean z15 = z4;
                        z = z3;
                        z2 = z15;
                    } else if (name.equalsIgnoreCase("base_port")) {
                        int parseInt2 = Integer.parseInt(str);
                        this.f462a.putInt("rtp_base_port", parseInt2);
                        this.f462a.putInt("mws_base_port", parseInt2 + 2000);
                        this.f462a.putInt("adb_bas_port", parseInt2 + 4000);
                        boolean z16 = z4;
                        z = z3;
                        z2 = z16;
                    } else {
                        if (name.equalsIgnoreCase("flags") && (parseInt = Integer.parseInt(str) & -41) != this.f476b) {
                            this.f476b = parseInt;
                            this.f462a.putInt("global_flags", parseInt);
                            boolean z17 = z4;
                            z = z3;
                            z2 = z17;
                        }
                        boolean z5222 = z4;
                        z = z3;
                        z2 = z5222;
                    }
                } else {
                    if (eventType == 4) {
                        str = xml.getText();
                        boolean z18 = z4;
                        z = z3;
                        z2 = z18;
                    }
                    boolean z52222 = z4;
                    z = z3;
                    z2 = z52222;
                }
                eventType = xml.next();
                boolean z19 = z2;
                z3 = z;
                z4 = z19;
            }
            if (vector.size() == 0) {
                return vector;
            }
            if (this.f467a) {
                this.f462a.putLong("firstRun", System.currentTimeMillis());
            }
            if (this.f478b) {
                this.f462a.putBoolean("reset", false);
            }
            this.f478b = false;
            this.f467a = false;
            this.f462a.putInt("defChCnt", vector.size());
            this.f462a.commit();
            return vector;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static Message createMessage(int i, int i2, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        Bundle bundle = new Bundle();
        if (i >= 0) {
            bundle.putInt("port", i);
        }
        if (obj != null) {
            if (obj instanceof Integer) {
                bundle.putInt("value", ((Integer) obj).intValue());
            } else if (obj instanceof int[]) {
                bundle.putIntArray("value", (int[]) obj);
            } else if (obj instanceof String) {
                bundle.putString("value", obj.toString());
            } else if (obj instanceof Parcelable) {
                bundle.putParcelable("value", (Parcelable) obj);
            } else if (obj instanceof Parcelable[]) {
                bundle.putParcelableArray("value", (Parcelable[]) obj);
            }
        }
        obtain.setData(bundle);
        return obtain;
    }

    /* renamed from: a */
    static void m440a(int i, String str) {
        try {
            Method declaredMethod = Class.forName("de.humatic.nmj.p").getDeclaredMethod("logln", new Class[]{Integer.TYPE, String.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke((Object) null, new Object[]{Integer.valueOf(i), str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    static /* synthetic */ int m428a(NMJDeviceService nMJDeviceService, int i, int i2) {
        if (i2 == 0) {
            return nMJDeviceService.f463a.getInt("nmj_in_" + i, m425a(i, i2));
        }
        if (i2 == 1) {
            return nMJDeviceService.f463a.getInt("nmj_out_" + i, m425a(i, i2));
        }
        return 0;
    }
}
