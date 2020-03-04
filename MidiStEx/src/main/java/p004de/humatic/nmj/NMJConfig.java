package p004de.humatic.nmj;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.p002os.EnvironmentCompat;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p000v4.view.PointerIconCompat;
import android.support.p003v7.widget.ActivityChooserView;
import java.io.File;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/* renamed from: de.humatic.nmj.NMJConfig */
public final class NMJConfig {
    public static final int ACCL = 2048;
    public static final int ADB = 4;
    public static final int ADB_QUERY = 16;
    public static final int ANNOUNCE_CLIENTS = 8192;
    public static final int BLE = 9;
    public static final int BLUETOOTH = 2;
    public static final int BT_DISCOVERABLE = 8;
    public static final int CFG_EVENT = 32;
    public static final int CH_CLOSED = 2;
    public static final int CH_CONNECTIVITY = 8;
    public static final int CH_OPENED = 1;
    public static final int CH_REMOVED = -1;
    public static final int CH_STATE = 4;
    public static final int COM = 7;
    public static final int CONNECTIVITY_ADB = 8;
    public static final int CONNECTIVITY_BLE = 2048;
    public static final int CONNECTIVITY_BLUETOOTH = 4;
    public static final int CONNECTIVITY_BTPAN = 64;
    public static final int CONNECTIVITY_MOBILE = 1024;
    public static final int CONNECTIVITY_MULTICAST = 128;
    public static final int CONNECTIVITY_P2P = 256;
    public static final int CONNECTIVITY_USB = 2;
    public static final int CONNECTIVITY_USB_HOST = 16;
    public static final int CONNECTIVITY_USB_SERIAL = 32;
    public static final int CONNECTIVITY_VIRTUAL = 512;
    public static final int CONNECTIVITY_WIFI = 1;
    public static final int DNS = 3;
    public static final int DNS_ANNOUNCE = 2;
    public static final int DNS_QUERY = 1;
    public static final int DSMI = 3;
    public static final int EV_CONNECTIVITY_CHANGED = 1;
    public static final int EV_QUERY_USB = 529;
    public static final int EV_SCAN_BT = 528;
    public static final int EV_SYSTEM_INIT = 0;
    public static final int E_ADB = -2147483640;
    public static final int E_BIND = -2147418110;
    public static final int E_BLUETOOTH = -2147483644;
    public static final int E_DEVICE_OPEN = -2147418111;
    public static final int E_DNS = -2147352576;
    public static final int E_INVALID_CH = -2147418096;
    public static final int E_INVALID_DATA = -2147418095;
    public static final int E_NETWORK = -2147483647;
    public static final int E_P2P = -2147483392;
    public static final int E_UNSPECIFIED = Integer.MIN_VALUE;
    public static final int E_USB = -2147483646;
    public static final int E_WIFI = -2147483647;
    public static final int FIXED_LOCAL_PORTS = 4096;

    /* renamed from: IN */
    public static final int f52IN = 0;

    /* renamed from: IO */
    public static final int f53IO = -1;
    public static final int LOCK_WIFI = 64;
    public static final int LOOPBACK = 4;
    public static final int MNET_CFG = 32;
    public static final int MWS = 6;
    public static final int NET_MON = 1024;
    public static final int OUT = 1;
    public static final int PROTECTED = 1;
    public static final int RAW = 0;
    public static final int RECONNECT = 65536;
    public static final int RESTART_DNS = 16777216;
    public static final int RTP = 1;
    public static final int RTPA = 1;
    public static final int RTPA_CH_CLIENT_CONNECT = 64;
    public static final int RTPA_CH_CLIENT_DISCONNECT = 256;
    public static final int RTPA_CH_CONNECTED = 32;
    public static final int RTPA_CH_DISCONNECTED = 128;
    public static final int RTPA_CH_DISCOVERED = 8;
    public static final int RTPA_CH_GONE = 512;
    public static final int RTPA_CH_LOST = 1024;
    public static final int RTPA_CH_PRESENT = 16;
    public static final int RTPA_CH_UNDEFINED = 0;
    public static final int RTPA_CH_WAITING = 4;
    public static final int RTPA_CONNECTION_REFUSED = 2048;
    public static final int RTPA_NO_RESPONSE = 4096;
    public static final int RTPA_PKT_LOSS = 8192;
    public static final int RTPA_REMOTE_ERR = 16384;
    public static final int SCAN_BT = 528;
    public static final int SCAN_DONE = 1;
    public static final int SCAN_EVENT = 16;
    public static final int SCAN_FAILED = 2;
    public static final int SCAN_START = 0;
    public static final int SCAN_WD = 530;
    public static final int USB_ATTACHMENT_LISTEN = 32;
    public static final String USB_DEVICE_ATTACHED = "de.humatic.nmj.USB_DEVICE_ATTACHED";
    public static final int USB_DEV_MASK_COM = 256;
    public static final int USB_DEV_MASK_MIDI = 128;
    public static final int USB_HOST = 5;
    public static final int VIRTUAL = 8;

    /* renamed from: a */
    static int f54a = -1;

    /* renamed from: a */
    static long f55a;

    /* renamed from: a */
    private static BroadcastReceiver f56a;

    /* renamed from: a */
    private static Context f57a = null;

    /* renamed from: a */
    private static SharedPreferences.Editor f58a;

    /* renamed from: a */
    private static SharedPreferences f59a;

    /* renamed from: a */
    private static WifiManager.WifiLock f60a;

    /* renamed from: a */
    private static C0453a f61a;

    /* renamed from: a */
    private static C0474j f62a = null;

    /* renamed from: a */
    private static Object f63a;

    /* renamed from: a */
    private static String f64a = "Network MIDI Ch.";

    /* renamed from: a */
    private static Timer f65a;

    /* renamed from: a */
    private static Vector<NMJSystemListener> f66a = new Vector<>();

    /* renamed from: a */
    protected static boolean f67a = false;

    /* renamed from: a */
    static int[] f68a = new int[16];

    /* renamed from: a */
    private static C0468h[] f69a = new C0468h[f76b.length];

    /* renamed from: b */
    private static int f70b = 6600;

    /* renamed from: b */
    private static long f71b;

    /* renamed from: b */
    private static BroadcastReceiver f72b;

    /* renamed from: b */
    private static String f73b = null;

    /* renamed from: b */
    private static Vector<String> f74b;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static boolean f75b;

    /* renamed from: b */
    protected static int[] f76b = {1, 2, 256, 64};

    /* renamed from: c */
    private static int f77c = 10000;

    /* renamed from: c */
    private static BroadcastReceiver f78c;

    /* renamed from: c */
    private static String f79c = null;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static boolean f80c;

    /* renamed from: c */
    protected static int[] f81c = {-2147352575, -2147352574, -2147352320, -2147352572};

    /* renamed from: d */
    private static int f82d = 8000;

    /* renamed from: d */
    private static String f83d = null;

    /* renamed from: d */
    private static boolean f84d;

    /* renamed from: d */
    private static int[] f85d = {1478, 3034, 1133, 1226, 1118, 2458, 3141, PointerIconCompat.TYPE_TEXT, 1452, 1423, 1501, 1643};

    /* renamed from: e */
    private static String f86e;

    /* renamed from: e */
    private static boolean f87e;

    /* renamed from: f */
    private static boolean f88f;

    /* renamed from: g */
    private static boolean f89g;

    /* renamed from: h */
    private static boolean f90h;

    /* renamed from: i */
    private static boolean f91i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static boolean f92j;

    /* renamed from: k */
    private static boolean f93k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public static boolean f94l;

    /* renamed from: m */
    private static boolean f95m = true;

    private NMJConfig() {
    }

    @Deprecated
    public static void edit(Context context) throws Exception {
        edit(context, false);
    }

    public static void edit(Context context, boolean z) throws Exception {
        boolean z2 = true;
        f87e = true;
        f57a = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("nmj", 0);
        f59a = sharedPreferences;
        f58a = sharedPreferences.edit();
        if (z) {
            f58a.clear();
            f58a.commit();
            if (C0484p.m393b() >= 6) {
                m128g();
            }
        }
        try {
            if (f57a.getPackageManager().checkPermission("android.permission.BLUETOOTH", f57a.getPackageName()) != 0) {
                z2 = false;
            }
            f90h = z2;
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    static void m66a(Context context) throws Exception {
        boolean z = false;
        f87e = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("nmj", 0);
        f59a = sharedPreferences;
        f58a = sharedPreferences.edit();
        f57a = context;
        f78c = null;
        f65a = null;
        f61a = null;
        f60a = null;
        f86e = null;
        f89g = false;
        f88f = false;
        f75b = false;
        f80c = false;
        f91i = false;
        f92j = false;
        f93k = false;
        f94l = false;
        f84d = false;
        f73b = null;
        f79c = null;
        f83d = null;
        f55a = System.currentTimeMillis();
        f54a = -1;
        f71b = 0;
        try {
            C0504x.m562e();
            C0510y.m592e();
        } catch (Exception e) {
        }
        C0484p.m346a();
        for (int i = 0; i < getNumChannels(); i++) {
            try {
                m64a(i, "rtpState", false);
            } catch (Exception e2) {
            }
        }
        f70b = m41a(-1, "rtp_bp", 6600);
        f77c = m41a(-1, "adb_bp", 10000);
        f82d = m41a(-1, "mws_bp", 8000);
        f62a = new C0474j();
        try {
            if (f57a.getPackageManager().checkPermission("android.permission.BLUETOOTH", f57a.getPackageName()) == 0) {
                z = true;
            }
            f90h = z;
        } catch (Exception e3) {
        }
        new Thread(new Runnable() {
            public final void run() {
                String str;
                final String trim;
                int i = 0;
                while (!NMJConfig.m82b()) {
                    int i2 = i + 1;
                    if (i >= 5) {
                        break;
                    }
                    try {
                        Thread.sleep(10);
                        i = i2;
                    } catch (InterruptedException e) {
                        i = i2;
                    }
                }
                int connectivity = NMJConfig.getConnectivity(NMJConfig.m82b());
                int flags = NMJConfig.getFlags(-1);
                StringBuilder append = new StringBuilder("nmj, on setParentContext:\nConnectivity: ").append(C0484p.m354a(connectivity)).append("\nGlobal flags: ").append(C0484p.m354a(NMJConfig.getFlags(-1))).append("\nAccesspoint: ").append(NMJConfig.m98c());
                if ((flags & 16) == 0) {
                    str = "";
                } else {
                    str = "\nUsb connected: " + NMJConfig.m108d() + (NMJConfig.m116e() ? " (Android 6 MIDI)" : "");
                }
                C0484p.logln(2, append.append(str).toString());
                if ((flags & 3) != 0) {
                    for (final int i3 = 0; i3 < NMJConfig.f76b.length; i3++) {
                        if ((NMJConfig.f76b[i3] & connectivity) != 0) {
                            try {
                                NMJConfig.m112d(1, NMJConfig.f76b[i3]);
                            } catch (Exception e2) {
                                if (e2.toString().indexOf("Exception") < 0) {
                                    trim = e2.toString();
                                } else {
                                    trim = e2.toString().substring(e2.toString().indexOf("Exception") + 10).trim();
                                }
                                new Thread(new Runnable(this) {
                                    public final void run() {
                                        NMJConfig.m60a(-1, NMJConfig.f81c[i3], trim);
                                    }
                                }).start();
                            }
                        }
                    }
                }
                if (!((connectivity & 8) == 0 || (flags & 16) == 0)) {
                    NMJConfig.m71a(false, false);
                }
                if (Build.VERSION.SDK_INT >= 12 && (flags & 32) != 0) {
                    if (NMJConfig.m82b() > 0) {
                        C0484p.m362a("USB blacklist: " + NMJConfig.getProperty("usb_blacklist", "-"));
                    }
                    if ((flags & 128) == 0) {
                        NMJConfig.m54a(true);
                    } else if ((flags & 256) == 0) {
                        NMJConfig.m88b(true);
                    }
                    NMJConfig.m108d();
                }
                if ((flags & 1024) != 0) {
                    NMJConfig.m116e();
                }
                try {
                    int numChannels = NMJConfig.getNumChannels();
                    for (int i4 = 0; i4 < numChannels; i4++) {
                        if (NMJConfig.m39a(NMJConfig.getMode(i4)) && !NetworkMidiSystem.get().isOpen(-1, i4)) {
                            NMJConfig.m84b(i4, -1);
                        }
                    }
                } catch (Exception e3) {
                }
                NMJConfig.m59a(-1, 0, 0);
            }
        }).start();
    }

    /* renamed from: b */
    private static int m83b(int i) {
        for (int i2 = 0; i2 < f76b.length; i2++) {
            if (f76b[i2] == i) {
                return i2;
            }
        }
        return 0;
    }

    /* renamed from: a */
    private static int m44a(String str) {
        try {
            String lowerCase = str.toLowerCase();
            if (lowerCase.indexOf("usb") == 0 || lowerCase.indexOf("rndis") != -1) {
                return 1;
            }
            if (lowerCase.indexOf("p2p-") != -1) {
                return 2;
            }
            if (lowerCase.indexOf("bt-pan") == 0 || lowerCase.indexOf("bnep") == 0) {
                return 3;
            }
            return 0;
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    protected static void m57a(int i) {
        try {
            f69a[m83b(i)] = null;
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    protected static Context m46a() {
        return f57a;
    }

    /* renamed from: a */
    private static boolean m77a(boolean z) {
        if (z) {
            try {
                if (f60a != null && f60a.isHeld()) {
                    return true;
                }
                WifiManager.WifiLock createWifiLock = ((WifiManager) f57a.getSystemService("wifi")).createWifiLock(Build.VERSION.SDK_INT <= 10 ? 1 : 3, "nmj_wifi_lock");
                f60a = createWifiLock;
                createWifiLock.acquire();
                C0484p.logln(1, "WifiLock aquired");
                return true;
            } catch (Exception e) {
                C0484p.logln(1, "Failed to aquire WifiLock: " + e.toString());
            }
        } else {
            try {
                if (f60a != null && f60a.isHeld()) {
                    f60a.release();
                    return true;
                }
            } catch (Exception e2) {
            }
            return false;
        }
    }

    public static void setProperty(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            f58a.remove(str);
        } else {
            f58a.putString(str, str2);
        }
        f58a.commit();
    }

    public static String getProperty(String str, String str2) {
        return f59a.getString(str, str2);
    }

    public static void setNumChannels(int i) {
        setNumChannels(i, true);
    }

    public static void setNumChannels(int i, boolean z) {
        if (i >= 0 && f59a != null) {
            int numChannels = getNumChannels();
            if (i < numChannels) {
                for (int i2 = i; i2 < numChannels; i2++) {
                    try {
                        m117e(i2);
                        if (z) {
                            m129g(i2);
                        }
                        m124f(i2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (f58a != null) {
                f58a.putInt("numCh", i);
                f58a.commit();
            }
        }
    }

    public static int addChannel() {
        int numChannels = getNumChannels();
        setNumChannels(numChannels + 1, false);
        return numChannels;
    }

    public static int getNumChannels() {
        if (f59a == null) {
            return 0;
        }
        return f59a.getInt("numCh", 3);
    }

    /* renamed from: a */
    private static void m65a(int i, boolean z) throws NMJException {
        if (i < 0 || i > getNumChannels() - 1) {
            throw new NMJException("Invalid channel: " + i, E_INVALID_CH);
        } else if (z && NetworkMidiSystem.get().isOpen(-1, i)) {
            throw new NMJException("Channel " + i + " is open. Can't change settings", E_DEVICE_OPEN);
        }
    }

    public static void setIP(int i, String str) throws NMJException {
        m65a(i, true);
        if (getMode(i) != 3 && getMode(i) != 4) {
            if (str == null || str.length() <= 0 || str.equalsIgnoreCase("null")) {
                f58a.remove("ip_" + i);
            } else {
                f58a.putString("ip_" + i, str);
            }
            f58a.commit();
        }
    }

    public static String getIP(int i) throws NMJException {
        m65a(i, false);
        if (getMode(i) == 0) {
            return f59a.getString("ip_" + i, "225.0.0.37");
        }
        if (getMode(i) == 1 || getMode(i) == 2 || getMode(i) == 6) {
            return f59a.getString("ip_" + i, (String) null);
        }
        if (getMode(i) == 3) {
            if (f83d == null) {
                f83d = m82b();
            }
            return f83d;
        } else if (getMode(i) == 4) {
            return f59a.getString("ip_" + i, "0.0.0.0");
        } else {
            if (getMode(i) == 5 || getMode(i) == 7) {
                return f59a.getString("ip_" + i, "dev/bus/usb/001/000");
            }
            return f59a.getString("ip_" + i, "unknown IP");
        }
    }

    public static void setPort(int i, int i2) throws NMJException {
        m65a(i, getMode(i) != 7);
        if (i2 == -1) {
            f58a.remove("port_" + i);
            f58a.commit();
        } else if (getMode(i) != 3) {
            f58a.putInt("port_" + i, i2);
            f58a.commit();
        }
    }

    public static int getPort(int i) throws NMJException {
        m65a(i, false);
        if (getMode(i) == 3) {
            return 9000;
        }
        int i2 = f59a.getInt("port_" + i, m99c(i));
        if (i2 != 0 || getMode(i) == 2) {
            return i2;
        }
        return m99c(i);
    }

    /* renamed from: c */
    private static int m99c(int i) {
        int i2 = 0;
        try {
            if (getMode(i) == 2) {
                return m40a(i, 2);
            }
            if (getMode(i) == 4) {
                return f77c + m40a(i, 4);
            }
            if (getMode(i) == 6) {
                return f82d + (m40a(i, 6) << 1);
            }
            if (getMode(i) == 5 || getMode(i) == 7) {
                return 0;
            }
            if (C0484p.m362a(getIP(i))) {
                for (int i3 = 0; i3 < getNumChannels(); i3++) {
                    if (getMode(i3) == 0) {
                        if (i3 >= i) {
                            break;
                        }
                        i2++;
                    }
                }
                return i2 + 21928;
            }
            return (i * 2) + 5004;
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    static int m40a(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            if (getMode(i4) == i2) {
                i3++;
            }
        }
        return i3;
    }

    public static void setLocalPort(int i, int i2) throws NMJException {
        m65a(i, true);
        if (i2 == -1) {
            f58a.remove("localPort_" + i);
            f58a.commit();
            return;
        }
        f58a.putInt("portLocal_" + i, i2);
        f58a.commit();
    }

    public static int getLocalPort(int i) throws NMJException {
        int i2;
        m65a(i, false);
        if (getMode(i) == 3) {
            return 20000;
        }
        if (getMode(i) == 2) {
            return 0;
        }
        if (getMode(i) == 4) {
            int i3 = f59a.getInt("portLocal_" + i, f77c + m40a(i, 4));
            if (i3 != 0) {
                return i3;
            }
            return m40a(i, 4) + f77c;
        } else if (getMode(i) == 5 || getMode(i) == 7) {
            return f59a.getInt("portLocal_" + i, 0);
        } else {
            if (getMode(i) == 6) {
                int i4 = f59a.getInt("portLocal_" + i, f82d + (i << 1));
                if (i4 != 0) {
                    return i4;
                }
                return (i << 1) + f82d;
            }
            SharedPreferences sharedPreferences = f59a;
            String str = "portLocal_" + i;
            if (getMode(i) == 1) {
                i2 = f70b + (i << 1);
            } else {
                i2 = 0;
            }
            int i5 = sharedPreferences.getInt(str, i2);
            if (i5 >= 0 && ((i5 <= 0 || i5 >= 1024) && i5 <= 65535)) {
                return i5;
            }
            if (getMode(i) != 1) {
                return 0;
            }
            return (i << 1) + f70b;
        }
    }

    public static void setBasePort(int i, int i2) {
        if (i == 1) {
            if (i2 != f70b) {
                f70b = i2;
                m41a(-1, "rtp_bp", i2);
            }
        } else if (i == 4) {
            if (i2 != f77c) {
                f77c = i2;
                m41a(-1, "adb_bp", i2);
            }
        } else if (i == 6 && i2 != f82d) {
            f82d = i2;
            m41a(-1, "mws_bp", i2);
        }
    }

    public static void setMode(int i, int i2) throws NMJException, IllegalArgumentException, SecurityException {
        m65a(i, true);
        if (i2 != getMode(i)) {
            if (i2 == 5 && Build.VERSION.SDK_INT < 12) {
                throw new IllegalArgumentException("USB hostmode requires Android 3.1 or greater");
            } else if (i2 != 2 || f90h) {
                if (!(i2 == 5 || i2 == 7)) {
                    setLocalPort(i, -1);
                    setPort(i, -1);
                }
                f58a.putInt("mode_" + i, i2);
                f58a.commit();
                if (i2 == 1 || i2 == 6) {
                    setIP(i, (String) null);
                    setPort(i, -1);
                } else if (getPort(i) < 0) {
                    f58a.remove("port_" + i);
                    f58a.commit();
                }
            } else {
                throw new SecurityException("No Bluetooth permission in Manifest");
            }
        }
    }

    /* renamed from: a */
    static void m58a(int i, int i2) {
        if (i >= 0 && i <= getNumChannels() - 1) {
            f58a.putInt("mode_" + i, i2);
            f58a.commit();
        }
    }

    public static int getRTPState(int i) {
        if (i < 0 || i > getNumChannels() - 1) {
            return -1;
        }
        if (getMode(i) != 0) {
            return f59a.getInt("rtpState_" + i, 0);
        }
        if (NetworkMidiSystem.get().isOpen(-1, i)) {
            return 32;
        }
        return -1;
    }

    /* renamed from: b */
    static void m91b(int i, int i2) {
        if (i >= 0 && i <= getNumChannels() - 1 && getMode(i) != 0) {
            if (i2 < 0) {
                f58a.remove("rtpState_" + i);
                f58a.commit();
            } else if (i2 != 16 || (getRTPState(i) & 96) == 0) {
                f58a.putInt("rtpState_" + i, i2);
                f58a.commit();
            }
        }
    }

    public static int getMode(int i) throws NMJException {
        int i2 = 0;
        m65a(i, false);
        SharedPreferences sharedPreferences = f59a;
        String str = "mode_" + i;
        if (i > 0 && (i + 1) % 3 == 0) {
            i2 = 1;
        }
        return sharedPreferences.getInt(str, i2);
    }

    public static void setNetworkAdapter(int i, int i2) throws NMJException {
        m65a(i, true);
        f83d = null;
        if (getNetworkAdapter(i) != i2) {
            int f = m123f();
            if (i2 <= 0) {
                i2 = f;
            }
            if (i2 == f) {
                f58a.remove("nwa_" + i);
            } else {
                f58a.putInt("nwa_" + i, i2);
            }
            f58a.commit();
            String e = m117e(i2 - 1);
            m92b(i, e);
            if ((getFlags(-1) & 3) != 0 && getIP(i) == null) {
                int a = m44a(e);
                if (!m109d(f76b[a])) {
                    return;
                }
                if (f69a[a] == null) {
                    try {
                        m112d(1, f76b[a]);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    setFlags(-1, 16777216);
                }
            }
        } else if (!m99c(i).equalsIgnoreCase(m117e(i2 - 1))) {
            m92b(i, m117e(i2 - 1));
        }
    }

    public static int getNetworkAdapter(int i) throws NMJException {
        m65a(i, false);
        if (getMode(i) == 2) {
            return 0;
        }
        String c = m99c(i);
        if (c == null) {
            return f59a.getInt("nwa_" + i, m123f());
        }
        if (c.indexOf("lo") != -1) {
            int f = m123f();
            f58a.putInt("nwa_" + i, f);
            f58a.putString("nwaName_" + i, m98c());
            f58a.commit();
            return f;
        }
        int a = m45a(c, true);
        if (a >= 0) {
            return a + 1;
        }
        int f2 = m123f();
        f58a.putInt("nwa_" + i, f2);
        f58a.putString("nwaName_" + i, m98c());
        f58a.commit();
        return f2;
    }

    /* renamed from: b */
    private static void m92b(int i, String str) {
        if (i >= 0 && i <= getNumChannels()) {
            f58a.putString("nwaName_" + i, str);
            f58a.commit();
        }
    }

    /* renamed from: c */
    private static String m101c(int i) {
        if (getMode(i) == 2) {
            return "";
        }
        String string = f59a.getString("nwaName_" + i, m98c());
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                String name = networkInterfaces.nextElement().getName();
                if (name.indexOf("p2p-") == -1 || string.indexOf("p2p-") == -1) {
                    if (name.equalsIgnoreCase(string)) {
                        return string;
                    }
                } else if (name.indexOf(string.substring(0, string.lastIndexOf("-"))) != -1) {
                    return string;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m98c();
    }

    public static boolean canOpen(int i) {
        boolean z;
        boolean z2 = true;
        if (f57a == null) {
            return false;
        }
        int connectivity = getConnectivity(f57a);
        switch (getMode(i)) {
            case 0:
                if ((connectivity & 1) == 0) {
                    return false;
                }
                if ((connectivity & 128) != 0) {
                    return true;
                }
                if (getIO(i) > 0) {
                    return true;
                }
                return false;
            case 1:
                if (!m83b(i)) {
                    String c = m99c(i);
                    if (c == null || (c.indexOf("bt-pan") == -1 && c.indexOf("bnep") == -1)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (z) {
                        if ((connectivity & 64) == 0) {
                            z2 = false;
                        }
                    } else if ((connectivity & 2) == 0) {
                        z2 = false;
                    }
                } else if ((connectivity & 1) == 0) {
                    z2 = false;
                }
                return z2;
            case 2:
                if ((connectivity & 4) != 0) {
                    return true;
                }
                return false;
            case 3:
                if ((connectivity & 1) != 0) {
                    return true;
                }
                return false;
            case 4:
                if ((connectivity & 8) != 0) {
                    return true;
                }
                return false;
            case 5:
                if ((connectivity & 16) != 0) {
                    return true;
                }
                return false;
            case 6:
                if ((connectivity & InputDeviceCompat.SOURCE_GAMEPAD) != 0) {
                    return true;
                }
                return false;
            case 7:
                if ((connectivity & 32) != 0) {
                    return true;
                }
                return false;
            default:
                C0484p.logln(1, "can't open " + i + " " + getMode(i) + " " + connectivity + " " + m83b(i));
                return false;
        }
    }

    /* renamed from: a */
    static boolean m73a(int i) {
        return i == 1 || i == 2 || i == 4 || i == 6;
    }

    /* renamed from: b */
    private static boolean m96b(int i) {
        String c = m99c(i);
        if (c == null) {
            return false;
        }
        if (c.indexOf("wlan") != -1) {
            return true;
        }
        if (c.indexOf("eth") == 0) {
            return true;
        }
        if (c.indexOf("ra") != -1) {
            return true;
        }
        if (c.indexOf("arc") != -1) {
            return true;
        }
        if (c.indexOf("wifi") != -1) {
            return true;
        }
        if (c.indexOf("wl0") != -1) {
            return true;
        }
        if (c.indexOf("ap0") != -1) {
            return true;
        }
        if (c.indexOf("mlan") != -1) {
            return true;
        }
        if (c.indexOf("p2p-") != -1) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static int m42a(int i, boolean z, boolean z2) {
        boolean z3;
        int mode = getMode(i);
        if (mode <= 1 || mode == 3 || mode == 6) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            String c = m99c(i);
            if (z2) {
                c = f59a.getString("nwaName_" + i, m98c());
            }
            if (c == null || c.indexOf("eth") == 0 || c.indexOf("wlan") == 0 || c.indexOf("arc") == 0 || c.indexOf("mlan") == 0 || c.indexOf("tiwlan") == 0 || c.indexOf("arc") == 0 || c.indexOf("ra") == 0 || c.indexOf("wl0") == 0 || c.indexOf("ap0") == 0 || ((z && c.indexOf("p2p-") != -1) || c.indexOf("wifi") != -1)) {
                return 1;
            }
            if ((c.indexOf("usb") != -1 && c.indexOf("rmnet") == -1) || c.indexOf("rndis") != -1) {
                return 2;
            }
            if (c.indexOf("p2p-") != -1) {
                return 256;
            }
            return (c.indexOf("bt-pan") == -1 && c.indexOf("bnep") == -1) ? -1 : 64;
        } else if (mode == 4) {
            return 8;
        } else {
            if (mode == 2) {
                return 4;
            }
            if (mode == 5) {
                return 16;
            }
            if (mode == 7) {
                return 32;
            }
            return mode == 8 ? 512 : -1;
        }
    }

    /* renamed from: d */
    private static void m111d(int i) {
        if (f57a != null) {
            try {
                int numChannels = getNumChannels();
                for (int i2 = 0; i2 < numChannels; i2++) {
                    if (NetworkMidiSystem.get().isOpen(-1, i2) && m42a(i2, false, true) == i) {
                        m59a(i2, 8, 0);
                        if ((getFlags(-1) & 2048) != 0) {
                            C0484p.logln(3, "Closing ch." + i2 + " on connectivity loss");
                            try {
                                NetworkMidiSystem.get().mo8102a(0, i2).close((NetworkMidiClient) null);
                            } catch (Exception e) {
                            }
                            try {
                                NetworkMidiSystem.get().mo8102a(1, i2).close((NetworkMidiClient) null);
                            } catch (Exception e2) {
                            }
                        }
                    }
                }
            } catch (Exception e3) {
            }
        }
    }

    public static void setIO(int i, int i2) throws NMJException {
        if (getMode(i) == 0) {
            m65a(i, true);
            f58a.putInt("io_" + i, i2);
            f58a.commit();
        }
    }

    public static int getIO(int i) throws NMJException {
        int i2 = -1;
        m65a(i, false);
        if (getMode(i) != 0) {
            return -1;
        }
        if ((i <= 0 || i + 1 != 0) && getMode(i) == 0) {
            i2 = (i + 1) % 2;
        }
        return f59a.getInt("io_" + i, i2);
    }

    public static void setFlags(int i, int i2) {
        boolean z;
        if (i <= getNumChannels() && f57a != null) {
            if (i >= 0) {
                f58a.putInt("flags_" + i, i2);
                f58a.commit();
            } else if (f87e || (-16777216 & i2) == 0 || (16777215 & i2) != 0) {
                f84d = true;
                int flags = getFlags(-1);
                f58a.putInt("flags", i2);
                f58a.commit();
                if (!f87e) {
                    if (flags != i2) {
                        C0484p.logln(2, "Changing global flags, was " + C0484p.m354a(flags) + " now " + C0484p.m354a(i2));
                    }
                    if ((flags & 3) != (i2 & 3)) {
                        if ((i2 & 3) != 0) {
                            for (int i3 = 0; i3 < f76b.length; i3++) {
                                if (m109d(f76b[i3])) {
                                    try {
                                        m112d(1, f76b[i3]);
                                    } catch (Exception e) {
                                        C0484p.logln(-1, "on setFlags/runServiceDiscovery: " + e.toString());
                                    }
                                }
                            }
                        } else {
                            for (int i4 = 0; i4 < f76b.length; i4++) {
                                try {
                                    f69a[i4].mo8135c();
                                    f69a[i4] = null;
                                } catch (Exception e2) {
                                }
                            }
                        }
                    }
                    if ((flags & 16) != (i2 & 16)) {
                        if ((i2 & 16) != 0 && f61a == null) {
                            m71a(false, false);
                        } else if ((i2 & 16) == 0 && f61a != null) {
                            f61a.mo8111a();
                            f61a = null;
                        }
                    }
                    if ((flags & 32) != (i2 & 32)) {
                        if ((i2 & 32) != 0 && f56a == null) {
                            m131i();
                            if ((i2 & 128) == 0) {
                                m54a(true);
                            } else if ((i2 & 256) == 0) {
                                m88b(true);
                            }
                        } else if ((i2 & 32) == 0 && f56a != null) {
                            try {
                                f57a.unregisterReceiver(f56a);
                            } catch (Exception e3) {
                            }
                            f56a = null;
                        }
                    }
                    if ((flags & 64) != (i2 & 64)) {
                        if ((i2 & 64) != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        m54a(z);
                    }
                    if ((flags & 1024) != (i2 & 1024)) {
                        if ((i2 & 1024) != 0) {
                            m132j();
                        } else if (f78c != null) {
                            try {
                                f57a.unregisterReceiver(f78c);
                            } catch (Exception e4) {
                            }
                            f78c = null;
                        }
                    }
                    if ((flags & 65536) != (i2 & 65536)) {
                        int numChannels = getNumChannels();
                        for (int i5 = 0; i5 < numChannels; i5++) {
                            try {
                                if (NetworkMidiSystem.get().isOpen(0, i5)) {
                                    ((NetworkMidiInput) NetworkMidiSystem.get().mo8101a(i5)).f185a.mo8158b();
                                }
                            } catch (Exception e5) {
                            }
                        }
                    }
                }
            } else if ((getFlags(-1) & 3) != 0 && (16777216 & i2) != 0) {
                m123f();
            }
        }
    }

    public static int getFlags(int i) {
        if (f59a == null || i > getNumChannels()) {
            return -1;
        }
        if (i >= 0) {
            return f59a.getInt("flags_" + i, 0);
        }
        return f59a.getInt("flags", Build.VERSION.SDK_INT < 23 ? 315 : 19);
    }

    public static void setName(int i, String str) throws NMJException {
        m65a(i, false);
        if (str == null) {
            f58a.remove("name_" + i);
        } else {
            f58a.putString("name_" + i, str);
        }
        f58a.commit();
    }

    public static String getName(int i) throws NMJException {
        m65a(i, false);
        String string = f59a.getString("name_" + i, (String) null);
        if (!(string == null || string.indexOf(EnvironmentCompat.MEDIA_UNKNOWN) == -1)) {
            try {
                string = string.replace("unknown IP,", m88b(false).replace(".", "_"));
                setName(i, string);
            } catch (Exception e) {
            }
        }
        return string == null ? m109d(i) : string;
    }

    /* renamed from: d */
    private static String m109d(int i) {
        int i2;
        int i3;
        int i4 = 0;
        if (i >= 0 && getMode(i) == 0) {
            return String.valueOf(f64a) + (i + 1);
        }
        if (i >= 0 && getMode(i) == 2 && f90h) {
            try {
                return String.valueOf(BluetoothAdapter.getDefaultAdapter().getName() == null ? "" : String.valueOf(BluetoothAdapter.getDefaultAdapter().getName()) + " - ") + "BT-MIDI " + (m40a(i, 2) + 1);
            } catch (Exception e) {
                return "nmj BT MIDI " + (m40a(i, 2) + 1);
            }
        } else if (i >= 0 && getMode(i) == 3) {
            return "DSMI";
        } else {
            if (getMode(i) == 4) {
                return "ADB " + (m40a(i, 4) + 1) + " - " + Build.MODEL;
            }
            if (getMode(i) == 6) {
                return "WebSockets " + (m40a(i, 6) + 1);
            }
            if (f73b == null) {
                try {
                    f73b = C0484p.m359a(false).toString();
                } catch (Exception e2) {
                }
                if (f73b == null || f73b.indexOf("127.0.0.1") != -1) {
                    f73b = m124f(getNetworkAdapter(i));
                }
                if (f73b.equalsIgnoreCase("localhost")) {
                    String f = m124f(getNetworkAdapter(i));
                    f73b = f;
                    if (f.indexOf(".") != -1) {
                        f73b = "nmj (and_" + f73b.substring(f73b.lastIndexOf(".") + 1) + ")";
                    }
                }
                if (f73b.indexOf("/") != -1) {
                    if (f73b.indexOf("/") == 0) {
                        f73b = f73b.substring(1);
                    } else {
                        f73b = f73b.substring(0, f73b.indexOf("/"));
                    }
                }
                if (f73b.indexOf(".local") != -1) {
                    f73b = f73b.substring(0, f73b.indexOf(".loc"));
                }
                if (f73b.length() == 0) {
                    try {
                        f73b = InetAddress.getLocalHost().getHostName();
                    } catch (Exception e3) {
                    }
                }
                try {
                    f73b = f73b.replace(".", "_");
                } catch (Exception e4) {
                }
                if (i < 0) {
                    return f73b;
                }
                if (m99c(i)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                int[] a = m59a(1, i2, m42a(i, true, false));
                while (i4 < a.length) {
                    if (a[i4] == i) {
                        return String.valueOf(f73b) + ", RTP " + (i4 + 1);
                    }
                    i4++;
                }
                return String.valueOf(f73b) + ", RTP " + a.length;
            } else if (i < 0) {
                return f73b;
            } else {
                if (m99c(i)) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                int[] a2 = m59a(1, i3, m42a(i, true, false));
                while (i4 < a2.length) {
                    if (a2[i4] == i) {
                        return String.valueOf(f73b) + ", RTP " + (i4 + 1);
                    }
                    i4++;
                }
                return String.valueOf(f73b) + ", RTP " + a2.length;
            }
        }
    }

    /* renamed from: a */
    static C0502v[] m80a(int i) {
        if (getMode(i) != 1 && getMode(i) != 2 && getMode(i) != 6) {
            return null;
        }
        if (getIP(i) != null) {
            return null;
        }
        try {
            if (!NetworkMidiSystem.get().isOpen(0, i)) {
                return null;
            }
            C0479n nVar = ((NetworkMidiInput) NetworkMidiSystem.get().mo8101a(i)).f185a;
            if (getMode(i) == 1) {
                return ((C0495t) nVar.mo8151a()).mo8031a();
            }
            if (getMode(i) == 2) {
                return ((C0456c) nVar.mo8151a()).mo8031a();
            }
            if (getMode(i) == 6) {
                return ((C0420A) nVar.mo8151a()).mo8031a();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    static void m104c(int i, int i2) {
        if ((getMode(i) == 1 || getMode(i) == 2) && getIP(i) == null) {
            try {
                if (NetworkMidiSystem.get().isOpen(0, i)) {
                    ((NetworkMidiInput) NetworkMidiSystem.get().mo8101a(i)).f185a.mo8151a().mo8197b(i2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    static String m52a(int i, int i2) {
        C0502v[] a;
        C0502v vVar = null;
        if ((getMode(i) == 1 || getMode(i) == 6 || getMode(i) == 2) && (a = m39a(i)) != null && 0 <= a.length) {
            vVar = a[0];
        }
        if (vVar == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return vVar.f563a;
    }

    public static void setLocalClientName(int i, String str) {
        m53a(i, "localClientName", str);
    }

    public static void setLocalClientPrefix(String str) {
        m53a(0, "localClientPrefix", str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x013d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String m51a(int r7) {
        /*
            r5 = -1
            java.lang.String r1 = m38a()
            if (r7 < 0) goto L_0x0017
            java.lang.String r0 = "localClientName"
            r2 = 0
            java.lang.String r0 = m53a((int) r7, (java.lang.String) r0, (java.lang.String) r2)
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = "localClientName"
            java.lang.String r0 = m53a((int) r7, (java.lang.String) r0, (java.lang.String) r1)
        L_0x0016:
            return r0
        L_0x0017:
            r0 = 0
            java.lang.String r2 = "localClientPrefix"
            java.lang.String r3 = m38a()     // Catch:{ Exception -> 0x0128 }
            java.lang.String r0 = m53a((int) r0, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ Exception -> 0x0128 }
            int r2 = getNetworkAdapter(r7)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r2 = m124f((int) r2)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = "10.0.2.15"
            boolean r3 = r2.equals(r3)     // Catch:{ Exception -> 0x0106 }
            if (r3 == 0) goto L_0x00b1
            java.lang.String r2 = android.os.Build.MODEL     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = "GT-I9300"
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0106 }
            if (r2 != 0) goto L_0x0054
            java.lang.String r2 = android.os.Build.MODEL     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = "GT-N7100"
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0106 }
            if (r2 != 0) goto L_0x0054
            r2 = 7937(0x1f01, float:1.1122E-41)
            java.lang.String r2 = android.opengl.GLES20.glGetString(r2)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = "BlueStacks"
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0106 }
            if (r2 == 0) goto L_0x009d
        L_0x0054:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0106 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = " (BlueStacks)"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0106 }
        L_0x0067:
            if (r7 < 0) goto L_0x0016
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0145 }
            java.lang.String r2 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0145 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0145 }
            java.lang.String r2 = " Ch_"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0145 }
            int r2 = r7 + 1
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0145 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0145 }
        L_0x0082:
            if (r7 >= 0) goto L_0x013d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "nmj ("
            r0.<init>(r1)
            java.lang.String r1 = android.os.Build.MODEL
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = ")"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            goto L_0x0016
        L_0x009d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0106 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = " (Emulator)"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0106 }
            goto L_0x0067
        L_0x00b1:
            if (r2 == 0) goto L_0x00bb
            java.lang.String r3 = "unknown"
            int r3 = r2.indexOf(r3)     // Catch:{ Exception -> 0x0106 }
            if (r3 == r5) goto L_0x00db
        L_0x00bb:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0106 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = " ("
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = android.os.Build.MODEL     // Catch:{ Exception -> 0x0106 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0106 }
            goto L_0x0067
        L_0x00db:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106 }
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0106 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r4 = " (and_"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r4 = "."
            int r4 = r2.lastIndexOf(r4)     // Catch:{ Exception -> 0x0106 }
            int r4 = r4 + 1
            java.lang.String r2 = r2.substring(r4)     // Catch:{ Exception -> 0x0106 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0106 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0106 }
            goto L_0x0067
        L_0x0106:
            r2 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0128 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0128 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0128 }
            java.lang.String r0 = " ("
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x0128 }
            java.lang.String r2 = android.os.Build.MODEL     // Catch:{ Exception -> 0x0128 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Exception -> 0x0128 }
            java.lang.String r2 = ")"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Exception -> 0x0128 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0128 }
            goto L_0x0067
        L_0x0128:
            r0 = move-exception
        L_0x0129:
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "NetworkOnMainThread"
            int r0 = r0.indexOf(r2)
            if (r0 == r5) goto L_0x0082
            r0 = 1
            java.lang.String r2 = "NetworkOnMainThread error in getLocalClientName"
            p004de.humatic.nmj.C0484p.logln(r0, r2)
            goto L_0x0082
        L_0x013d:
            java.lang.String r0 = "localClientName"
            java.lang.String r0 = m53a((int) r7, (java.lang.String) r0, (java.lang.String) r1)
            goto L_0x0016
        L_0x0145:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0129
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.m51a(int):java.lang.String");
    }

    /* renamed from: a */
    static String m50a() {
        try {
            return f57a.getApplicationInfo().loadLabel(f57a.getPackageManager()).toString();
        } catch (Exception e) {
            return "nmj_android";
        }
    }

    /* renamed from: a */
    private static void m62a(int i, String str, int i2) {
        if (i2 == -1) {
            m64a(-1, str, false);
            return;
        }
        f58a.putInt(str, i2);
        f58a.commit();
    }

    /* renamed from: a */
    private static void m63a(int i, String str, String str2) {
        if (str2 == null) {
            m64a(i, str, true);
            return;
        }
        if (i == -1) {
            f58a.putString(str, str2);
        } else {
            f58a.putString(String.valueOf(str) + "_" + i, str2);
        }
        f58a.commit();
    }

    /* renamed from: a */
    private static int m41a(int i, String str, int i2) {
        if (-1 > getNumChannels() - 1) {
            return -1;
        }
        return f59a.getInt(str, i2);
    }

    /* renamed from: a */
    static String m53a(int i, String str, String str2) {
        if (i < -1 || i > getNumChannels() - 1) {
            return str2;
        }
        if (i == -1) {
            return f59a.getString(str, str2);
        }
        return f59a.getString(String.valueOf(str) + "_" + i, str2);
    }

    /* renamed from: a */
    private static void m64a(int i, String str, boolean z) {
        if (i >= -1 && i <= getNumChannels() - 1) {
            if (i == -1) {
                f58a.remove(str);
            } else {
                f58a.remove(String.valueOf(str) + "_" + i);
            }
            if (z) {
                f58a.commit();
            }
        }
    }

    /* renamed from: a */
    static void m59a(int i, int i2, int i3) {
        boolean z = true;
        int i4 = 0;
        try {
            if (!f89g) {
                if (i >= 0 && i2 == 4) {
                    m84b(i, i3);
                    if ((getMode(i) == 1 || getMode(i) == 6) && i3 < 8192) {
                        if (i3 == 4096 && getIP(i) == null) {
                            m84b(i, 4);
                        }
                        if (i3 > 4096) {
                            if (i >= f68a.length) {
                                int[] iArr = new int[f68a.length];
                                System.arraycopy(f68a, 0, iArr, 0, iArr.length);
                                f68a = new int[(iArr.length << 1)];
                                System.arraycopy(iArr, 0, f68a, 0, iArr.length);
                            }
                            if (f68a[i] != i3) {
                                f68a[i] = i3;
                                z = false;
                            }
                            if (z) {
                                return;
                            }
                        }
                        if (i3 == 512 && getIP(i) != null && !NetworkMidiSystem.get().isOpen(-1, i)) {
                            C0484p.logln(1, "CH_GONE: " + i + ", not currently open. Deleting");
                            for (int i5 = 0; i5 < f66a.size(); i5++) {
                                f66a.get(i5).systemChanged(i, i2, i3);
                            }
                            m93b(i, true);
                            return;
                        }
                    } else if (getMode(i) == 32 && i3 == 512) {
                        C0484p.logln(4, "CH_GONE: " + i + ", deleting mnet_cfg");
                        for (int i6 = 0; i6 < f66a.size(); i6++) {
                            f66a.get(i6).systemChanged(i, i2, i3);
                        }
                        deleteChannel(i);
                        return;
                    }
                }
                while (true) {
                    int i7 = i4;
                    if (i7 < f66a.size()) {
                        f66a.get(i7).systemChanged(i, i2, i3);
                        i4 = i7 + 1;
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            C0484p.logln(-1, "in postSystemEvent " + e.toString());
        }
    }

    /* renamed from: a */
    static void m60a(int i, int i2, String str) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < f66a.size()) {
                f66a.get(i4).systemError(i, i2, str);
                i3 = i4 + 1;
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    static int m84b(int i, int i2) {
        int i3;
        m84b(i, i2 == 32 ? 64 : 256);
        for (int i4 = 0; i4 < f66a.size(); i4++) {
            NMJSystemListener nMJSystemListener = f66a.get(i4);
            if (i2 == 32) {
                i3 = 64;
            } else {
                i3 = 256;
            }
            nMJSystemListener.systemChanged(i, 4, i3);
        }
        return 0;
    }

    /* renamed from: a */
    private static C0502v m48a(int i) {
        return new C0502v(i);
    }

    public static void addSystemListener(NMJSystemListener nMJSystemListener) {
        if (!f66a.contains(nMJSystemListener)) {
            f66a.add(nMJSystemListener);
        }
    }

    public static void removeSystemListener(NMJSystemListener nMJSystemListener) {
        if (f66a.contains(nMJSystemListener)) {
            f66a.remove(nMJSystemListener);
        }
    }

    /* renamed from: a */
    static void m68a(String str, long j) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < f66a.size()) {
                if (f66a.get(i2).getClass().getName().equalsIgnoreCase(str) && ((long) f66a.get(i2).hashCode()) != j) {
                    f66a.remove(i2);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private static boolean m75a(Context context, int i) {
        if (context == null) {
            return false;
        }
        String packageName = context.getPackageName();
        if ((i & 3) != 0) {
            if (context.getPackageManager().checkPermission("android.permission.INTERNET", packageName) != 0 || context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", packageName) != 0) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 23 || context.getPackageManager().checkPermission("android.permission.CHANGE_NETWORK_STATE", packageName) == 0) && context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", packageName) == 0) {
                return true;
            }
            return false;
        } else if ((i & 128) != 0) {
            if (context.getPackageManager().checkPermission("android.permission.CHANGE_WIFI_MULTICAST_STATE", packageName) == 0) {
                return true;
            }
            return false;
        } else if ((i & 4) == 0) {
            return false;
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                return true;
            }
            if (context.getPackageManager().checkPermission("android.permission.BLUETOOTH", packageName) == 0 && context.getPackageManager().checkPermission("android.permission.BLUETOOTH_ADMIN", packageName) == 0) {
                return true;
            }
            return false;
        }
    }

    public static int getConnectivity(Context context) {
        return m43a(context, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:145:0x0212 A[SYNTHETIC, Splitter:B:145:0x0212] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0050 A[EDGE_INSN: B:224:0x0050->B:23:0x0050 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:238:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0125  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int m43a(android.content.Context r11, boolean r12) {
        /*
            if (r11 != 0) goto L_0x0004
            r0 = -1
        L_0x0003:
            return r0
        L_0x0004:
            r2 = 0
            r1 = 0
            r0 = 0
            f92j = r0
            r0 = 0
            f91i = r0
            r0 = -1
            int r4 = getFlags(r0)
            r0 = 3
            boolean r0 = m75a((android.content.Context) r11, (int) r0)
            if (r0 == 0) goto L_0x035e
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r11.getSystemService(r0)     // Catch:{ Exception -> 0x020d }
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch:{ Exception -> 0x020d }
            android.net.NetworkInfo[] r3 = r0.getAllNetworkInfo()     // Catch:{ Exception -> 0x020d }
            int r5 = r3.length     // Catch:{ Exception -> 0x020d }
            r0 = 0
            r10 = r0
            r0 = r2
            r2 = r10
        L_0x0029:
            if (r2 < r5) goto L_0x01a2
            r2 = r0
        L_0x002c:
            r0 = 0
            java.lang.Object[] r0 = p004de.humatic.nmj.C0484p.m359a((boolean) r0)     // Catch:{ Exception -> 0x034b }
            if (r0 == 0) goto L_0x003a
            r0 = 1
            r1 = r2 | 1
            r1 = r1 | 256(0x100, float:3.59E-43)
            r2 = r1
            r1 = r0
        L_0x003a:
            r3 = 0
            java.lang.String r0 = "wifi"
            java.lang.Object r0 = r11.getSystemService(r0)     // Catch:{ Exception -> 0x0352 }
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch:{ Exception -> 0x0352 }
            r3 = r0
        L_0x0044:
            java.lang.Class r0 = r3.getClass()     // Catch:{ Exception -> 0x0242 }
            java.lang.reflect.Method[] r5 = r0.getDeclaredMethods()     // Catch:{ Exception -> 0x0242 }
            int r6 = r5.length     // Catch:{ Exception -> 0x0242 }
            r0 = 0
        L_0x004e:
            if (r0 < r6) goto L_0x0212
        L_0x0050:
            boolean r0 = f91i
            if (r0 != 0) goto L_0x0067
            r0 = 0
            java.lang.String r0 = m88b((boolean) r0)
            java.lang.String r3 = ".168.43.1"
            int r0 = r0.indexOf(r3)
            r3 = -1
            if (r0 == r3) goto L_0x0067
            r0 = 1
            f91i = r0
            r2 = r2 | 1
        L_0x0067:
            int r0 = m98c()
            r3 = -1
            if (r0 == r3) goto L_0x035b
            r0 = r2 | 2
        L_0x0070:
            r2 = r0 & 64
            if (r2 != 0) goto L_0x007d
            int r2 = m116e()
            r3 = -1
            if (r2 == r3) goto L_0x007d
            r0 = r0 | 64
        L_0x007d:
            r2 = r0 & 1
            if (r2 == 0) goto L_0x008b
            r2 = 128(0x80, float:1.794E-43)
            boolean r2 = m75a((android.content.Context) r11, (int) r2)
            if (r2 == 0) goto L_0x008b
            r0 = r0 | 128(0x80, float:1.794E-43)
        L_0x008b:
            r2 = 4
            boolean r2 = m75a((android.content.Context) r11, (int) r2)
            if (r2 == 0) goto L_0x00a8
            r2 = r4 & 8
            if (r2 == 0) goto L_0x00a8
            android.bluetooth.BluetoothAdapter r2 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()     // Catch:{ Exception -> 0x0248 }
            if (r2 == 0) goto L_0x00a8
            android.bluetooth.BluetoothAdapter r2 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()     // Catch:{ Exception -> 0x0248 }
            boolean r2 = r2.isEnabled()     // Catch:{ Exception -> 0x0248 }
            if (r2 == 0) goto L_0x00a8
            r0 = r0 | 4
        L_0x00a8:
            r2 = r4 & 16
            if (r2 == 0) goto L_0x00ff
            r2 = 0
            f93k = r2
            r2 = 0
            android.content.IntentFilter r3 = new android.content.IntentFilter     // Catch:{ Exception -> 0x02b2 }
            java.lang.String r5 = "android.hardware.usb.action.USB_STATE"
            r3.<init>(r5)     // Catch:{ Exception -> 0x02b2 }
            android.content.Intent r2 = r11.registerReceiver(r2, r3)     // Catch:{ Exception -> 0x02b2 }
            android.os.Bundle r3 = r2.getExtras()     // Catch:{ Exception -> 0x02b2 }
            java.lang.String r5 = "connected"
            boolean r3 = r3.getBoolean(r5)     // Catch:{ Exception -> 0x02b2 }
            f92j = r3     // Catch:{ Exception -> 0x02b2 }
            if (r3 == 0) goto L_0x0297
            android.os.Bundle r3 = r2.getExtras()     // Catch:{ Exception -> 0x02b2 }
            java.util.Set r2 = r3.keySet()     // Catch:{ Exception -> 0x02b2 }
            java.util.Iterator r5 = r2.iterator()     // Catch:{ Exception -> 0x02b2 }
            r2 = r0
        L_0x00d6:
            boolean r0 = r5.hasNext()     // Catch:{ Exception -> 0x034e }
            if (r0 != 0) goto L_0x0253
            r0 = r2
        L_0x00dd:
            boolean r2 = f92j
            if (r2 == 0) goto L_0x00ff
            boolean r2 = f93k
            if (r2 != 0) goto L_0x00ff
            r2 = r0 & 8
            if (r2 != 0) goto L_0x00ff
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 17
            if (r2 >= r3) goto L_0x02ba
            android.content.ContentResolver r2 = r11.getContentResolver()
            java.lang.String r3 = "adb_enabled"
            r5 = 0
            int r2 = android.provider.Settings.Secure.getInt(r2, r3, r5)
            r3 = 1
            if (r2 != r3) goto L_0x00ff
            r0 = r0 | 8
        L_0x00ff:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 12
            if (r2 < r3) goto L_0x0121
            r2 = r4 & 32
            if (r2 == 0) goto L_0x0121
            r2 = r4 & 128(0x80, float:1.794E-43)
            if (r2 != 0) goto L_0x0115
            android.hardware.usb.UsbDevice r2 = p004de.humatic.nmj.C0504x.mo8031a()
            if (r2 == 0) goto L_0x0309
            r0 = r0 | 16
        L_0x0115:
            r2 = r4 & 256(0x100, float:3.59E-43)
            if (r2 != 0) goto L_0x0121
            android.hardware.usb.UsbDevice r2 = p004de.humatic.nmj.C0510y.mo8031a()
            if (r2 == 0) goto L_0x0321
            r0 = r0 | 32
        L_0x0121:
            int r2 = f54a
            if (r0 == r2) goto L_0x0003
            int r2 = f54a
            r3 = -1
            if (r2 == r3) goto L_0x019e
            r2 = 3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Connectivity changed: "
            r3.<init>(r4)
            int r4 = f54a
            java.lang.String r4 = p004de.humatic.nmj.C0484p.m354a((int) r4)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = " -> "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = p004de.humatic.nmj.C0484p.m354a((int) r0)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            p004de.humatic.nmj.C0484p.logln(r2, r3)
            if (r12 == 0) goto L_0x0158
            r2 = -1
            r3 = 1
            m59a((int) r2, (int) r3, (int) r0)
        L_0x0158:
            r2 = 0
        L_0x0159:
            r3 = 11
            if (r2 < r3) goto L_0x0339
            r2 = -1
            int r2 = getFlags(r2)
            r2 = r2 & 3
            if (r2 == 0) goto L_0x019e
            r2 = 0
            r3 = r0 & 1
            int r4 = f54a
            r4 = r4 & 1
            if (r3 != r4) goto L_0x0187
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 14
            if (r3 < r4) goto L_0x0358
            de.humatic.nmj.h[] r3 = f69a
            r4 = 2
            r3 = r3[r4]
            if (r3 != 0) goto L_0x017e
            if (r1 != 0) goto L_0x0187
        L_0x017e:
            de.humatic.nmj.h[] r3 = f69a
            r4 = 2
            r3 = r3[r4]
            if (r3 == 0) goto L_0x0358
            if (r1 != 0) goto L_0x0358
        L_0x0187:
            r1 = 1
        L_0x0188:
            r2 = r0 & 2
            int r3 = f54a
            r3 = r3 & 2
            if (r2 == r3) goto L_0x0191
            r1 = 1
        L_0x0191:
            f54a = r0
            if (r1 == 0) goto L_0x019e
            r1 = 3
            java.lang.String r2 = "Updating DNS"
            p004de.humatic.nmj.C0484p.logln(r1, r2)
            m123f()
        L_0x019e:
            f54a = r0
            goto L_0x0003
        L_0x01a2:
            r6 = r3[r2]     // Catch:{ Exception -> 0x0355 }
            java.lang.String r7 = r6.getTypeName()     // Catch:{ Exception -> 0x0355 }
            java.lang.String r8 = "WIFI"
            boolean r7 = r7.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x0355 }
            if (r7 == 0) goto L_0x01b8
            boolean r7 = r6.isConnected()     // Catch:{ Exception -> 0x0355 }
            if (r7 == 0) goto L_0x01d0
            r0 = r0 | 1
        L_0x01b8:
            boolean r7 = r6.isConnected()     // Catch:{ Exception -> 0x0355 }
            if (r7 == 0) goto L_0x01cc
            java.lang.String r7 = r6.getTypeName()     // Catch:{ Exception -> 0x0355 }
            java.lang.String r8 = "MOBILE"
            boolean r7 = r7.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x0355 }
            if (r7 == 0) goto L_0x01e0
            r0 = r0 | 1024(0x400, float:1.435E-42)
        L_0x01cc:
            int r2 = r2 + 1
            goto L_0x0029
        L_0x01d0:
            r7 = 0
            java.lang.String r7 = m88b((boolean) r7)     // Catch:{ Exception -> 0x0355 }
            java.lang.String r8 = "unknown"
            int r7 = r7.indexOf(r8)     // Catch:{ Exception -> 0x0355 }
            if (r7 >= 0) goto L_0x01b8
            r0 = r0 | 1
            goto L_0x01b8
        L_0x01e0:
            java.lang.String r7 = r6.getTypeName()     // Catch:{ Exception -> 0x0355 }
            java.lang.String r8 = "ETHERNET"
            boolean r7 = r7.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x0355 }
            if (r7 == 0) goto L_0x01ef
            r0 = r0 | 1
            goto L_0x01cc
        L_0x01ef:
            java.lang.String r7 = r6.getTypeName()     // Catch:{ Exception -> 0x0355 }
            java.lang.String r8 = "BLUETOOTH"
            boolean r7 = r7.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x0355 }
            if (r7 == 0) goto L_0x01fe
            r0 = r0 | 64
            goto L_0x01cc
        L_0x01fe:
            java.lang.String r6 = r6.getTypeName()     // Catch:{ Exception -> 0x0355 }
            java.lang.String r7 = "WIFI_P2P"
            boolean r6 = r6.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x0355 }
            if (r6 == 0) goto L_0x01cc
            r0 = r0 | 256(0x100, float:3.59E-43)
            goto L_0x01cc
        L_0x020d:
            r0 = move-exception
            r0 = r2
        L_0x020f:
            r2 = r0
            goto L_0x002c
        L_0x0212:
            r7 = r5[r0]     // Catch:{ Exception -> 0x0242 }
            java.lang.String r8 = r7.getName()     // Catch:{ Exception -> 0x0242 }
            java.lang.String r9 = "isWifiApEnabled"
            boolean r8 = r8.equals(r9)     // Catch:{ Exception -> 0x0242 }
            if (r8 == 0) goto L_0x023e
            r0 = 0
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x023b }
            java.lang.Object r0 = r7.invoke(r3, r0)     // Catch:{ Exception -> 0x023b }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Exception -> 0x023b }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x023b }
            if (r0 == 0) goto L_0x0236
            r0 = 1
            f91i = r0     // Catch:{ Exception -> 0x023b }
            r2 = r2 | 1
            goto L_0x0050
        L_0x0236:
            r0 = 0
            f91i = r0     // Catch:{ Exception -> 0x023b }
            goto L_0x0050
        L_0x023b:
            r0 = move-exception
            goto L_0x0050
        L_0x023e:
            int r0 = r0 + 1
            goto L_0x004e
        L_0x0242:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0050
        L_0x0248:
            r2 = move-exception
            r3 = 3
            java.lang.String r2 = r2.toString()
            p004de.humatic.nmj.C0484p.logln(r3, r2)
            goto L_0x00a8
        L_0x0253:
            java.lang.Object r0 = r5.next()     // Catch:{ Exception -> 0x034e }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x034e }
            java.lang.Object r6 = r3.get(r0)     // Catch:{ Exception -> 0x034e }
            if (r6 == 0) goto L_0x00d6
            java.lang.String r7 = "adb"
            boolean r7 = r0.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x034e }
            if (r7 == 0) goto L_0x027d
            boolean r0 = f93k     // Catch:{ Exception -> 0x034e }
            if (r0 != 0) goto L_0x00d6
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x034e }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x034e }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x034e }
            if (r0 == 0) goto L_0x00d6
            r2 = r2 | 8
            goto L_0x00d6
        L_0x027d:
            java.lang.String r7 = "midi"
            boolean r0 = r0.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x034e }
            if (r0 == 0) goto L_0x00d6
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x034e }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x034e }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x034e }
            f93k = r0     // Catch:{ Exception -> 0x034e }
            r2 = r2 & -9
            goto L_0x00d6
        L_0x0297:
            r2 = 0
            android.content.IntentFilter r3 = new android.content.IntentFilter     // Catch:{ Exception -> 0x02b2 }
            java.lang.String r5 = "android.intent.action.BATTERY_CHANGED"
            r3.<init>(r5)     // Catch:{ Exception -> 0x02b2 }
            android.content.Intent r2 = r11.registerReceiver(r2, r3)     // Catch:{ Exception -> 0x02b2 }
            java.lang.String r3 = "plugged"
            r5 = -1
            int r2 = r2.getIntExtra(r3, r5)     // Catch:{ Exception -> 0x02b2 }
            r3 = 2
            if (r2 != r3) goto L_0x02b8
            r2 = 1
        L_0x02ae:
            f92j = r2     // Catch:{ Exception -> 0x02b2 }
            goto L_0x00dd
        L_0x02b2:
            r2 = move-exception
        L_0x02b3:
            r2 = 1
            f92j = r2
            goto L_0x00dd
        L_0x02b8:
            r2 = 0
            goto L_0x02ae
        L_0x02ba:
            java.lang.String r2 = "android.provider.Settings$Global"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x02f6 }
            java.lang.String r3 = "getInt"
            r5 = 2
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ Exception -> 0x02f6 }
            r6 = 0
            java.lang.Class<android.content.ContentResolver> r7 = android.content.ContentResolver.class
            r5[r6] = r7     // Catch:{ Exception -> 0x02f6 }
            r6 = 1
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ Exception -> 0x02f6 }
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r3, r5)     // Catch:{ Exception -> 0x02f6 }
            r3 = 0
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x02f6 }
            r6 = 0
            android.content.ContentResolver r7 = r11.getContentResolver()     // Catch:{ Exception -> 0x02f6 }
            r5[r6] = r7     // Catch:{ Exception -> 0x02f6 }
            r6 = 1
            java.lang.String r7 = "adb_enabled"
            r5[r6] = r7     // Catch:{ Exception -> 0x02f6 }
            java.lang.Object r2 = r2.invoke(r3, r5)     // Catch:{ Exception -> 0x02f6 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x02f6 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Exception -> 0x02f6 }
            r3 = 1
            if (r2 != r3) goto L_0x00ff
            r0 = r0 | 8
            goto L_0x00ff
        L_0x02f6:
            r2 = move-exception
            android.content.ContentResolver r2 = r11.getContentResolver()
            java.lang.String r3 = "adb_enabled"
            r5 = 0
            int r2 = android.provider.Settings.Secure.getInt(r2, r3, r5)
            r3 = 1
            if (r2 != r3) goto L_0x00ff
            r0 = r0 | 8
            goto L_0x00ff
        L_0x0309:
            long r2 = java.lang.System.currentTimeMillis()
            long r6 = f55a
            long r2 = r2 - r6
            r6 = 2000(0x7d0, double:9.88E-321)
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x0115
            r2 = 5
            boolean r2 = m117e((int) r2)
            if (r2 == 0) goto L_0x0115
            r0 = r0 | 16
            goto L_0x0115
        L_0x0321:
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = f55a
            long r2 = r2 - r4
            r4 = 2000(0x7d0, double:9.88E-321)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0121
            r2 = 7
            boolean r2 = m117e((int) r2)
            if (r2 == 0) goto L_0x0121
            r0 = r0 | 32
            goto L_0x0121
        L_0x0339:
            r3 = 1
            int r3 = r3 << r2
            r4 = r0 & r3
            if (r4 != 0) goto L_0x0347
            int r4 = f54a
            r4 = r4 & r3
            if (r4 == 0) goto L_0x0347
            m109d((int) r3)
        L_0x0347:
            int r2 = r2 + 1
            goto L_0x0159
        L_0x034b:
            r0 = move-exception
            goto L_0x003a
        L_0x034e:
            r0 = move-exception
            r0 = r2
            goto L_0x02b3
        L_0x0352:
            r0 = move-exception
            goto L_0x0044
        L_0x0355:
            r2 = move-exception
            goto L_0x020f
        L_0x0358:
            r1 = r2
            goto L_0x0188
        L_0x035b:
            r0 = r2
            goto L_0x0070
        L_0x035e:
            r0 = r2
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.m43a(android.content.Context, boolean):int");
    }

    /* renamed from: c */
    private static int m98c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i = 1;
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if ((nextElement.getName().toLowerCase().indexOf("usb") == 0 || nextElement.getName().toLowerCase().indexOf("rndis") != -1) && m124f(i).indexOf(EnvironmentCompat.MEDIA_UNKNOWN) < 0) {
                    return i;
                }
                i++;
            }
        } catch (Exception e) {
        }
        return -1;
    }

    /* renamed from: d */
    private static int m108d() {
        if (Build.VERSION.SDK_INT < 14) {
            return -1;
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i = 1;
            while (networkInterfaces.hasMoreElements()) {
                if (networkInterfaces.nextElement().getName().toLowerCase().indexOf("p2p-") != -1) {
                    return i;
                }
                i++;
            }
        } catch (Exception e) {
        }
        return -1;
    }

    /* renamed from: e */
    private static int m116e() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i = 1;
            while (networkInterfaces.hasMoreElements()) {
                String lowerCase = networkInterfaces.nextElement().getName().toLowerCase();
                if (lowerCase.indexOf("bt-pan") != -1 || lowerCase.indexOf("bnep") != -1) {
                    return i;
                }
                i++;
            }
        } catch (Exception e) {
        }
        return -1;
    }

    /* renamed from: a */
    static String m54a(boolean z) {
        return m88b(false);
    }

    /* renamed from: b */
    static String m88b(boolean z) {
        String str;
        String str2 = "unknown ip";
        try {
            NetworkInterface a = C0484p.m346a();
            if (a == null && z) {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    a = networkInterfaces.nextElement();
                    String name = a.getName();
                    if (!((name.indexOf("usb") == -1 && name.indexOf("rndis") == -1) || name.indexOf("usb") == 0)) {
                        name.indexOf("rndis");
                    }
                }
            } else if (a == null) {
                return str2;
            }
            a.getName();
            Enumeration<InetAddress> inetAddresses = a.getInetAddresses();
            while (true) {
                if (!inetAddresses.hasMoreElements()) {
                    str = str2;
                    break;
                }
                InetAddress nextElement = inetAddresses.nextElement();
                if (!(nextElement instanceof Inet6Address)) {
                    str = nextElement.toString();
                    if (str.indexOf("127.0.0.1") < 0) {
                        break;
                    }
                    str2 = str;
                }
            }
            if (str.indexOf("/") != -1) {
                return str.substring(str.indexOf("/") + 1);
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "unknown IP";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d6 A[SYNTHETIC, Splitter:B:45:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e8 A[SYNTHETIC, Splitter:B:52:0x00e8] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String[] m81a() {
        /*
            r5 = 1
            r1 = 0
            r3 = 0
            r9 = -1
            java.util.Vector r6 = new java.util.Vector
            r6.<init>()
            java.net.NetworkInterface r0 = p004de.humatic.nmj.C0484p.m346a()     // Catch:{ Exception -> 0x02d0 }
            if (r0 != 0) goto L_0x0169
            java.util.Enumeration r7 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x02d0 }
            r4 = r3
            r2 = r1
        L_0x0015:
            boolean r8 = r7.hasMoreElements()     // Catch:{ Exception -> 0x01b9 }
            if (r8 != 0) goto L_0x012b
        L_0x001b:
            java.util.Enumeration r5 = r0.getInetAddresses()     // Catch:{ Exception -> 0x01b9 }
            r0 = r3
        L_0x0020:
            boolean r7 = r5.hasMoreElements()     // Catch:{ Exception -> 0x01b9 }
            if (r7 != 0) goto L_0x0171
        L_0x0026:
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = "/"
            int r5 = r0.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == r9) goto L_0x003e
            java.lang.String r5 = "/"
            int r5 = r0.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            int r5 = r5 + 1
            java.lang.String r0 = r0.substring(r5)     // Catch:{ Exception -> 0x01b9 }
        L_0x003e:
            java.lang.String r5 = "eth"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "wlan"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "tiwlan"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "mlan"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "arc"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "ra"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "wl0"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "ap0"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 == 0) goto L_0x0086
            java.lang.String r5 = "wifi"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 != 0) goto L_0x018d
        L_0x0086:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = "WIFI: "
            r4.<init>(r5)     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01b9 }
            r6.add(r0)     // Catch:{ Exception -> 0x01b9 }
        L_0x0098:
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 14
            if (r0 < r4) goto L_0x00d4
            r0 = 0
            java.lang.Object[] r0 = p004de.humatic.nmj.C0484p.m359a((boolean) r0)     // Catch:{ Exception -> 0x02cd }
            if (r0 == 0) goto L_0x00d4
            r4 = 1
            r0 = r0[r4]     // Catch:{ Exception -> 0x02cd }
            java.net.InetAddress r0 = (java.net.InetAddress) r0     // Catch:{ Exception -> 0x02cd }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02cd }
            java.lang.String r4 = "/"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x02cd }
            if (r4 == r9) goto L_0x00c2
            java.lang.String r4 = "/"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x02cd }
            int r4 = r4 + 1
            java.lang.String r0 = r0.substring(r4)     // Catch:{ Exception -> 0x02cd }
        L_0x00c2:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02cd }
            java.lang.String r5 = "P2P: "
            r4.<init>(r5)     // Catch:{ Exception -> 0x02cd }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Exception -> 0x02cd }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02cd }
            r6.add(r0)     // Catch:{ Exception -> 0x02cd }
        L_0x00d4:
            if (r2 != 0) goto L_0x00e6
            int r0 = m98c()     // Catch:{ Exception -> 0x0235 }
            if (r0 == r9) goto L_0x00e6
            java.util.Enumeration r2 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x0235 }
        L_0x00e0:
            boolean r0 = r2.hasMoreElements()     // Catch:{ Exception -> 0x0235 }
            if (r0 != 0) goto L_0x01dc
        L_0x00e6:
            if (r1 != 0) goto L_0x00f8
            int r0 = m116e()     // Catch:{ Exception -> 0x02ac }
            if (r0 == r9) goto L_0x00f8
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x02ac }
        L_0x00f2:
            boolean r0 = r1.hasMoreElements()     // Catch:{ Exception -> 0x02ac }
            if (r0 != 0) goto L_0x0253
        L_0x00f8:
            boolean r0 = f90h     // Catch:{ Exception -> 0x02ca }
            if (r0 == 0) goto L_0x011e
            android.bluetooth.BluetoothAdapter r0 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()     // Catch:{ Exception -> 0x02ca }
            if (r0 == 0) goto L_0x011e
            boolean r1 = r0.isEnabled()     // Catch:{ Exception -> 0x02ca }
            if (r1 == 0) goto L_0x011e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02ca }
            java.lang.String r2 = "Bluetooth: "
            r1.<init>(r2)     // Catch:{ Exception -> 0x02ca }
            java.lang.String r0 = r0.getAddress()     // Catch:{ Exception -> 0x02ca }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Exception -> 0x02ca }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02ca }
            r6.add(r0)     // Catch:{ Exception -> 0x02ca }
        L_0x011e:
            int r0 = r6.size()
            java.lang.String[] r0 = new java.lang.String[r0]
            r6.copyInto(r0)
            r6.removeAllElements()
            return r0
        L_0x012b:
            java.lang.Object r0 = r7.nextElement()     // Catch:{ Exception -> 0x01b9 }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r4 = r0.getName()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r8 = "usb"
            int r8 = r4.indexOf(r8)     // Catch:{ Exception -> 0x01b9 }
            if (r8 != r9) goto L_0x0145
            java.lang.String r8 = "rndis"
            int r8 = r4.indexOf(r8)     // Catch:{ Exception -> 0x01b9 }
            if (r8 == r9) goto L_0x0156
        L_0x0145:
            java.lang.String r8 = "usb"
            int r8 = r4.indexOf(r8)     // Catch:{ Exception -> 0x01b9 }
            if (r8 == 0) goto L_0x0155
            java.lang.String r8 = "rndis"
            int r8 = r4.indexOf(r8)     // Catch:{ Exception -> 0x01b9 }
            if (r8 < 0) goto L_0x0015
        L_0x0155:
            r2 = r5
        L_0x0156:
            java.lang.String r8 = "bt-pan"
            int r8 = r4.indexOf(r8)     // Catch:{ Exception -> 0x01b9 }
            if (r8 >= 0) goto L_0x0166
            java.lang.String r8 = "bnep"
            int r8 = r4.indexOf(r8)     // Catch:{ Exception -> 0x01b9 }
            if (r8 < 0) goto L_0x0015
        L_0x0166:
            r1 = r5
            goto L_0x0015
        L_0x0169:
            java.lang.String r2 = r0.getName()     // Catch:{ Exception -> 0x02d0 }
            r4 = r2
            r2 = r1
            goto L_0x001b
        L_0x0171:
            java.lang.Object r0 = r5.nextElement()     // Catch:{ Exception -> 0x01b9 }
            java.net.InetAddress r0 = (java.net.InetAddress) r0     // Catch:{ Exception -> 0x01b9 }
            boolean r7 = f95m     // Catch:{ Exception -> 0x01b9 }
            if (r7 == 0) goto L_0x0020
            boolean r7 = r0 instanceof java.net.Inet4Address     // Catch:{ Exception -> 0x01b9 }
            if (r7 == 0) goto L_0x0020
            java.lang.String r7 = r0.toString()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r8 = "127.0.0.1"
            int r7 = r7.indexOf(r8)     // Catch:{ Exception -> 0x01b9 }
            if (r7 < 0) goto L_0x0026
            goto L_0x0020
        L_0x018d:
            java.lang.String r5 = "bt-pan"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r5 != r9) goto L_0x019d
            java.lang.String r5 = "bnep"
            int r4 = r4.indexOf(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r4 == r9) goto L_0x01c0
        L_0x019d:
            java.lang.String r4 = "127.0.0.1"
            boolean r4 = r0.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x01b9 }
            if (r4 != 0) goto L_0x0098
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = "BT-Pan: "
            r4.<init>(r5)     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01b9 }
            r6.add(r0)     // Catch:{ Exception -> 0x01b9 }
            goto L_0x0098
        L_0x01b9:
            r0 = move-exception
            r0 = r1
            r1 = r2
        L_0x01bc:
            r2 = r1
            r1 = r0
            goto L_0x0098
        L_0x01c0:
            java.lang.String r4 = "127.0.0.1"
            boolean r4 = r0.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x01b9 }
            if (r4 != 0) goto L_0x0098
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = "USB: "
            r4.<init>(r5)     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01b9 }
            r6.add(r0)     // Catch:{ Exception -> 0x01b9 }
            goto L_0x0098
        L_0x01dc:
            java.lang.Object r0 = r2.nextElement()     // Catch:{ Exception -> 0x0235 }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ Exception -> 0x0235 }
            java.lang.String r4 = r0.getName()     // Catch:{ Exception -> 0x0235 }
            java.lang.String r5 = "usb"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x0235 }
            if (r5 == 0) goto L_0x01f6
            java.lang.String r5 = "rndis"
            int r4 = r4.indexOf(r5)     // Catch:{ Exception -> 0x0235 }
            if (r4 == r9) goto L_0x00e0
        L_0x01f6:
            java.util.Enumeration r4 = r0.getInetAddresses()     // Catch:{ Exception -> 0x0235 }
            r0 = r3
        L_0x01fb:
            boolean r5 = r4.hasMoreElements()     // Catch:{ Exception -> 0x0235 }
            if (r5 != 0) goto L_0x0238
        L_0x0201:
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0235 }
            java.lang.String r4 = "/"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x0235 }
            if (r4 == r9) goto L_0x0219
            java.lang.String r4 = "/"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x0235 }
            int r4 = r4 + 1
            java.lang.String r0 = r0.substring(r4)     // Catch:{ Exception -> 0x0235 }
        L_0x0219:
            java.lang.String r4 = "127.0.0.1"
            boolean r4 = r0.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x0235 }
            if (r4 != 0) goto L_0x00e0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0235 }
            java.lang.String r5 = "USB: "
            r4.<init>(r5)     // Catch:{ Exception -> 0x0235 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Exception -> 0x0235 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0235 }
            r6.add(r0)     // Catch:{ Exception -> 0x0235 }
            goto L_0x00e0
        L_0x0235:
            r0 = move-exception
            goto L_0x00e6
        L_0x0238:
            java.lang.Object r0 = r4.nextElement()     // Catch:{ Exception -> 0x0235 }
            java.net.InetAddress r0 = (java.net.InetAddress) r0     // Catch:{ Exception -> 0x0235 }
            boolean r5 = f95m     // Catch:{ Exception -> 0x0235 }
            if (r5 == 0) goto L_0x01fb
            boolean r5 = r0 instanceof java.net.Inet4Address     // Catch:{ Exception -> 0x0235 }
            if (r5 == 0) goto L_0x01fb
            java.lang.String r5 = r0.toString()     // Catch:{ Exception -> 0x0235 }
            java.lang.String r7 = "127.0.0.1"
            int r5 = r5.indexOf(r7)     // Catch:{ Exception -> 0x0235 }
            if (r5 < 0) goto L_0x0201
            goto L_0x01fb
        L_0x0253:
            java.lang.Object r0 = r1.nextElement()     // Catch:{ Exception -> 0x02ac }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ Exception -> 0x02ac }
            java.lang.String r2 = r0.getName()     // Catch:{ Exception -> 0x02ac }
            java.lang.String r4 = "bt-pan"
            int r4 = r2.indexOf(r4)     // Catch:{ Exception -> 0x02ac }
            if (r4 != r9) goto L_0x026d
            java.lang.String r4 = "bnep"
            int r2 = r2.indexOf(r4)     // Catch:{ Exception -> 0x02ac }
            if (r2 == r9) goto L_0x00f2
        L_0x026d:
            java.util.Enumeration r2 = r0.getInetAddresses()     // Catch:{ Exception -> 0x02ac }
            r0 = r3
        L_0x0272:
            boolean r4 = r2.hasMoreElements()     // Catch:{ Exception -> 0x02ac }
            if (r4 != 0) goto L_0x02af
        L_0x0278:
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02ac }
            java.lang.String r2 = "/"
            int r2 = r0.indexOf(r2)     // Catch:{ Exception -> 0x02ac }
            if (r2 == r9) goto L_0x0290
            java.lang.String r2 = "/"
            int r2 = r0.indexOf(r2)     // Catch:{ Exception -> 0x02ac }
            int r2 = r2 + 1
            java.lang.String r0 = r0.substring(r2)     // Catch:{ Exception -> 0x02ac }
        L_0x0290:
            java.lang.String r2 = "127.0.0.1"
            boolean r2 = r0.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x02ac }
            if (r2 != 0) goto L_0x00f2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02ac }
            java.lang.String r4 = "BT-Pan: "
            r2.<init>(r4)     // Catch:{ Exception -> 0x02ac }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x02ac }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02ac }
            r6.add(r0)     // Catch:{ Exception -> 0x02ac }
            goto L_0x00f2
        L_0x02ac:
            r0 = move-exception
            goto L_0x00f8
        L_0x02af:
            java.lang.Object r0 = r2.nextElement()     // Catch:{ Exception -> 0x02ac }
            java.net.InetAddress r0 = (java.net.InetAddress) r0     // Catch:{ Exception -> 0x02ac }
            boolean r4 = f95m     // Catch:{ Exception -> 0x02ac }
            if (r4 == 0) goto L_0x0272
            boolean r4 = r0 instanceof java.net.Inet4Address     // Catch:{ Exception -> 0x02ac }
            if (r4 == 0) goto L_0x0272
            java.lang.String r4 = r0.toString()     // Catch:{ Exception -> 0x02ac }
            java.lang.String r5 = "127.0.0.1"
            int r4 = r4.indexOf(r5)     // Catch:{ Exception -> 0x02ac }
            if (r4 < 0) goto L_0x0278
            goto L_0x0272
        L_0x02ca:
            r0 = move-exception
            goto L_0x011e
        L_0x02cd:
            r0 = move-exception
            goto L_0x00d4
        L_0x02d0:
            r0 = move-exception
            r0 = r1
            goto L_0x01bc
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.m81a():java.lang.String[]");
    }

    /* renamed from: a */
    private static int m45a(String str, boolean z) {
        int i = 0;
        if (str.indexOf("rmnet") != -1) {
            return -1;
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (str.indexOf("p2p-") != -1) {
                    if (nextElement.getName().indexOf(str.substring(0, str.lastIndexOf("-"))) != -1) {
                        return i;
                    }
                } else if (nextElement.getName().equalsIgnoreCase(str)) {
                    return i;
                }
                i++;
            }
        } catch (Exception e) {
        }
        return -1;
    }

    /* renamed from: e */
    private static String m117e(int i) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (!networkInterfaces.hasMoreElements()) {
                    break;
                }
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (i3 == i) {
                    return nextElement.getName();
                }
                i2 = i3 + 1;
            }
        } catch (Exception e) {
        }
        return "Auto";
    }

    /* renamed from: a */
    static int m38a() {
        int i = 0;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                networkInterfaces.nextElement();
                i++;
            }
        } catch (Exception e) {
        }
        return i;
    }

    /* renamed from: f */
    private static String m124f(int i) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i2 = 0;
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                i2++;
                if (i2 == i) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (nextElement2 instanceof Inet4Address) {
                            String inetAddress = nextElement2.toString();
                            if (inetAddress.indexOf("/") != -1) {
                                return inetAddress.substring(inetAddress.indexOf("/") + 1);
                            }
                            return inetAddress;
                        }
                    }
                    continue;
                }
            }
        } catch (Exception e) {
        }
        return "unknown IP";
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00bf  */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m123f() {
        /*
            r6 = -1
            r2 = 0
            java.util.Enumeration r3 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x00e9 }
            r1 = r2
        L_0x0007:
            boolean r0 = r3.hasMoreElements()     // Catch:{ Exception -> 0x00e9 }
            if (r0 != 0) goto L_0x001a
        L_0x000d:
            java.util.Enumeration r3 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x00e6 }
            r1 = r2
        L_0x0012:
            boolean r0 = r3.hasMoreElements()     // Catch:{ Exception -> 0x00e6 }
            if (r0 != 0) goto L_0x00bf
        L_0x0018:
            r0 = r2
        L_0x0019:
            return r0
        L_0x001a:
            int r1 = r1 + 1
            java.lang.Object r0 = r3.nextElement()     // Catch:{ Exception -> 0x00e9 }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ Exception -> 0x00e9 }
            java.lang.String r4 = r0.getName()     // Catch:{ Exception -> 0x00e9 }
            java.lang.String r5 = "eth"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "wlan"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "mlan"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "wifi"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "wl0"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "ap0"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "arc"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = "ra"
            int r5 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r5 != 0) goto L_0x0007
        L_0x0066:
            java.lang.String r5 = "eth"
            int r4 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e9 }
            if (r4 == r6) goto L_0x00ad
        L_0x006e:
            boolean r0 = r3.hasMoreElements()     // Catch:{ Exception -> 0x00e9 }
            if (r0 != 0) goto L_0x0076
            r0 = r1
            goto L_0x0019
        L_0x0076:
            java.lang.Object r0 = r3.nextElement()     // Catch:{ Exception -> 0x00e9 }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ Exception -> 0x00e9 }
            int r1 = r1 + 1
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x00ec }
            java.lang.String r4 = "wlan"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x00ec }
            if (r4 == 0) goto L_0x00aa
            java.lang.String r4 = "mlan"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x00ec }
            if (r4 == 0) goto L_0x00aa
            java.lang.String r4 = "tiwlan"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x00ec }
            if (r4 == 0) goto L_0x00aa
            java.lang.String r4 = "wifi"
            int r4 = r0.indexOf(r4)     // Catch:{ Exception -> 0x00ec }
            if (r4 == 0) goto L_0x00aa
            java.lang.String r4 = "ra"
            int r0 = r0.indexOf(r4)     // Catch:{ Exception -> 0x00ec }
            if (r0 != 0) goto L_0x006e
        L_0x00aa:
            r0 = r1
            goto L_0x0019
        L_0x00ad:
            boolean r4 = p004de.humatic.nmj.C0484p.m363a((java.net.NetworkInterface) r0)     // Catch:{ Exception -> 0x00e9 }
            if (r4 == 0) goto L_0x00b6
            r0 = r1
            goto L_0x0019
        L_0x00b6:
            boolean r0 = p004de.humatic.nmj.C0484p.m400b((java.net.NetworkInterface) r0)     // Catch:{ Exception -> 0x00e9 }
            if (r0 == 0) goto L_0x0007
            r0 = r1
            goto L_0x0019
        L_0x00bf:
            int r1 = r1 + 1
            java.lang.Object r0 = r3.nextElement()     // Catch:{ Exception -> 0x00e6 }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ Exception -> 0x00e6 }
            java.lang.String r4 = r0.getName()     // Catch:{ Exception -> 0x00e6 }
            java.lang.String r5 = "usb"
            int r4 = r4.indexOf(r5)     // Catch:{ Exception -> 0x00e6 }
            if (r4 == 0) goto L_0x00e3
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x00e6 }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ Exception -> 0x00e6 }
            java.lang.String r4 = "rndis"
            int r0 = r0.indexOf(r4)     // Catch:{ Exception -> 0x00e6 }
            if (r0 == r6) goto L_0x0012
        L_0x00e3:
            r0 = r1
            goto L_0x0019
        L_0x00e6:
            r0 = move-exception
            goto L_0x0018
        L_0x00e9:
            r0 = move-exception
            goto L_0x000d
        L_0x00ec:
            r0 = move-exception
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.m123f():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[Catch:{ Exception -> 0x0043 }] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m100c() {
        /*
            java.lang.String r0 = f79c
            if (r0 == 0) goto L_0x0007
            java.lang.String r0 = f79c
        L_0x0006:
            return r0
        L_0x0007:
            java.net.NetworkInterface r0 = p004de.humatic.nmj.C0484p.m346a()     // Catch:{ Exception -> 0x0012 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x0012 }
            f79c = r0     // Catch:{ Exception -> 0x0012 }
            goto L_0x0006
        L_0x0012:
            r0 = move-exception
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x0043 }
        L_0x0017:
            boolean r0 = r1.hasMoreElements()     // Catch:{ Exception -> 0x0043 }
            if (r0 != 0) goto L_0x001f
        L_0x001d:
            r0 = 0
            goto L_0x0006
        L_0x001f:
            java.lang.Object r0 = r1.nextElement()     // Catch:{ Exception -> 0x0043 }
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch:{ Exception -> 0x0043 }
            java.lang.String r2 = r0.getName()     // Catch:{ Exception -> 0x0043 }
            java.lang.String r3 = "usb"
            int r2 = r2.indexOf(r3)     // Catch:{ Exception -> 0x0043 }
            if (r2 == 0) goto L_0x003e
            java.lang.String r2 = r0.getName()     // Catch:{ Exception -> 0x0043 }
            java.lang.String r3 = "rndis"
            int r2 = r2.indexOf(r3)     // Catch:{ Exception -> 0x0043 }
            r3 = -1
            if (r2 == r3) goto L_0x0017
        L_0x003e:
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x0043 }
            goto L_0x0006
        L_0x0043:
            r0 = move-exception
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.m100c():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e0, code lost:
        if (r1 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e6, code lost:
        if (r1.size() > 0) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f0, code lost:
        r0 = (p004de.humatic.nmj.C0502v) r1.remove(0);
        r8 = addChannel();
        setMode(r8, r0.f564b);
        setName(r8, r0.f563a);
        setIP(r8, r0.f565b);
        setPort(r8, r0.f566c);
        setLocalPort(r8, r0.f568d);
        setFlags(r8, r0.f569e);
        m84b(r8, r0.f570f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x011f, code lost:
        if (r0.f571g <= 0) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0127, code lost:
        if (r0.f571g == m123f()) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0129, code lost:
        setNetworkAdapter(r8, r0.f571g);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0130, code lost:
        if (r0.f564b != 1) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0134, code lost:
        if (r0.f565b == null) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0138, code lost:
        if (r0.f567c == null) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x013a, code lost:
        setLocalClientName(r8, r0.f567c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013f, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0141, code lost:
        if (r5 >= r12.length) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0147, code lost:
        if (r0.f562a != r12[r5]) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0149, code lost:
        r6[r5] = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x014b, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x014e, code lost:
        setNetworkAdapter(r8, -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0152, code lost:
        setLocalClientName(r8, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] cleanup(int r11, int[] r12) {
        /*
            r7 = 0
            r9 = 2
            r3 = -1
            r2 = 1
            r4 = 0
            int r0 = getNumChannels()
            if (r12 != 0) goto L_0x000d
            int[] r12 = new int[r4]
        L_0x000d:
            int r0 = r0 + -1
            r1 = r7
            r6 = r0
        L_0x0011:
            if (r6 >= r11) goto L_0x001b
        L_0x0013:
            int r0 = r12.length
            int[] r6 = new int[r0]
            int r0 = r6.length
            if (r0 != 0) goto L_0x00d9
            r0 = r6
        L_0x001a:
            return r0
        L_0x001b:
            int r0 = getMode(r6)
            r5 = 4
            if (r0 == r5) goto L_0x0045
            if (r0 != r2) goto L_0x002a
            java.lang.String r5 = getIP(r6)
            if (r5 != 0) goto L_0x0045
        L_0x002a:
            r5 = 6
            if (r0 != r5) goto L_0x0033
            java.lang.String r5 = getIP(r6)
            if (r5 != 0) goto L_0x0045
        L_0x0033:
            if (r0 != r9) goto L_0x003b
            java.lang.String r5 = getIP(r6)
            if (r5 != 0) goto L_0x0045
        L_0x003b:
            r5 = 5
            if (r0 == r5) goto L_0x0045
            r5 = 7
            if (r0 == r5) goto L_0x0045
            r5 = 32
            if (r0 != r5) goto L_0x009f
        L_0x0045:
            int r0 = getFlags(r6)
            r0 = r0 & 1
            if (r0 == 0) goto L_0x0159
            int r0 = r11 + 1
            if (r6 <= r0) goto L_0x00a4
            r0 = r2
        L_0x0052:
            r5 = r4
        L_0x0053:
            int r8 = r12.length
            if (r5 < r8) goto L_0x00a6
        L_0x0056:
            if (r0 != r2) goto L_0x00b7
            if (r6 == r11) goto L_0x0013
            if (r1 != 0) goto L_0x0156
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
        L_0x0061:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r5 = "relocating remote channel "
            r1.<init>(r5)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r5 = " "
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r5 = getName(r6)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r1 = r1.toString()
            p004de.humatic.nmj.C0484p.logln(r9, r1)
            int r1 = r0.size()
            if (r1 != 0) goto L_0x00af
            de.humatic.nmj.v r1 = m39a((int) r6)
            r0.add(r1)
        L_0x008e:
            int r1 = getNumChannels()
            int r5 = r1 + -1
            if (r6 == r5) goto L_0x0099
            m129g(r6)
        L_0x0099:
            int r1 = r1 + -1
            setNumChannels(r1, r2)
            r1 = r0
        L_0x009f:
            int r0 = r6 + -1
            r6 = r0
            goto L_0x0011
        L_0x00a4:
            r0 = r3
            goto L_0x0052
        L_0x00a6:
            r8 = r12[r5]
            if (r6 != r8) goto L_0x00ac
            r0 = r2
            goto L_0x0056
        L_0x00ac:
            int r5 = r5 + 1
            goto L_0x0053
        L_0x00af:
            de.humatic.nmj.v r1 = m39a((int) r6)
            r0.insertElementAt(r1, r4)
            goto L_0x008e
        L_0x00b7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r5 = "removing unused remote channel "
            r0.<init>(r5)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r5 = " "
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r5 = getName(r6)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r0 = r0.toString()
            p004de.humatic.nmj.C0484p.logln(r9, r0)
            r0 = r1
            goto L_0x008e
        L_0x00d9:
            r0 = r4
        L_0x00da:
            int r5 = r6.length
            if (r0 < r5) goto L_0x00eb
            getNumChannels()
            if (r1 == 0) goto L_0x00e8
        L_0x00e2:
            int r0 = r1.size()
            if (r0 > 0) goto L_0x00f0
        L_0x00e8:
            r0 = r6
            goto L_0x001a
        L_0x00eb:
            r6[r0] = r3
            int r0 = r0 + 1
            goto L_0x00da
        L_0x00f0:
            java.lang.Object r0 = r1.remove(r4)
            de.humatic.nmj.v r0 = (p004de.humatic.nmj.C0502v) r0
            int r8 = addChannel()
            int r5 = r0.f564b
            setMode(r8, r5)
            java.lang.String r5 = r0.f563a
            setName(r8, r5)
            java.lang.String r5 = r0.f565b
            setIP(r8, r5)
            int r5 = r0.f566c
            setPort(r8, r5)
            int r5 = r0.f568d
            setLocalPort(r8, r5)
            int r5 = r0.f569e
            setFlags(r8, r5)
            int r5 = r0.f570f
            m84b((int) r8, (int) r5)
            int r5 = r0.f571g
            if (r5 <= 0) goto L_0x014e
            int r5 = r0.f571g
            int r9 = m123f()
            if (r5 == r9) goto L_0x014e
            int r5 = r0.f571g
            setNetworkAdapter(r8, r5)
        L_0x012e:
            int r5 = r0.f564b
            if (r5 != r2) goto L_0x0152
            java.lang.String r5 = r0.f565b
            if (r5 == 0) goto L_0x0152
            java.lang.String r5 = r0.f567c
            if (r5 == 0) goto L_0x0152
            java.lang.String r5 = r0.f567c
            setLocalClientName(r8, r5)
        L_0x013f:
            r5 = r4
        L_0x0140:
            int r9 = r12.length
            if (r5 >= r9) goto L_0x00e2
            int r9 = r0.f562a
            r10 = r12[r5]
            if (r9 != r10) goto L_0x014b
            r6[r5] = r8
        L_0x014b:
            int r5 = r5 + 1
            goto L_0x0140
        L_0x014e:
            setNetworkAdapter(r8, r3)
            goto L_0x012e
        L_0x0152:
            setLocalClientName(r8, r7)
            goto L_0x013f
        L_0x0156:
            r0 = r1
            goto L_0x0061
        L_0x0159:
            r0 = r4
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.cleanup(int, int[]):int[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
        p004de.humatic.nmj.C0484p.logln(1, "DNS (" + r5 + ") - channel gone " + r7 + " " + getMode(r0));
        m59a(r0, 4, 512);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized void m67a(java.lang.String r5, int r6, java.lang.String r7) {
        /*
            java.lang.Class<de.humatic.nmj.NMJConfig> r1 = p004de.humatic.nmj.NMJConfig.class
            monitor-enter(r1)
            int r0 = getNumChannels()     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            int r0 = r0 + -1
        L_0x0009:
            if (r0 > 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)
            return
        L_0x000d:
            java.lang.String r2 = getIP(r0)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            if (r2 == 0) goto L_0x0076
            int r2 = getMode(r0)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            if (r2 != r6) goto L_0x0076
            java.lang.String r2 = getName(r0)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            boolean r2 = r2.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            if (r2 != 0) goto L_0x0042
            java.lang.String r2 = getName(r0)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.String r3 = "."
            int r2 = r2.indexOf(r3)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            r3 = -1
            if (r2 == r3) goto L_0x0076
            java.lang.String r2 = getName(r0)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.String r3 = "."
            java.lang.String r4 = "_"
            java.lang.String r2 = r2.replace(r3, r4)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            boolean r2 = r2.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            if (r2 == 0) goto L_0x0076
        L_0x0042:
            r2 = 1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.String r4 = "DNS ("
            r3.<init>(r4)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.String r4 = ") - channel gone "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.String r4 = " "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            int r4 = getMode(r0)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            p004de.humatic.nmj.C0484p.logln(r2, r3)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            r2 = 4
            r3 = 512(0x200, float:7.175E-43)
            m59a((int) r0, (int) r2, (int) r3)     // Catch:{ Exception -> 0x0074, all -> 0x0079 }
            goto L_0x000b
        L_0x0074:
            r0 = move-exception
            goto L_0x000b
        L_0x0076:
            int r0 = r0 + -1
            goto L_0x0009
        L_0x0079:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.m67a(java.lang.String, int, java.lang.String):void");
    }

    public static void deleteChannel(int i) {
        m93b(i, false);
    }

    /* renamed from: b */
    private static void m93b(int i, boolean z) {
        if (i >= 0 && i <= getNumChannels() - 1) {
            m117e(i);
            if (!z || (getFlags(i) & 1) == 0) {
                m129g(i);
                m124f(i);
                if (f58a != null) {
                    f58a.putInt("numCh", getNumChannels() - 1);
                    f58a.commit();
                }
                m59a(i, -1, 0);
                return;
            }
            C0484p.logln(2, "Channel is protected");
        }
    }

    /* renamed from: e */
    private static void m119e(int i) {
        char c = 1;
        try {
            if (getMode(i) == 1 && getIP(i) == null) {
                if ((getFlags(-1) & 3) != 0) {
                    for (int i2 = 0; i2 < f76b[i2]; i2++) {
                        if (!(!m109d(f76b[i2]) || f69a[i2] == null || getRTPState(i) == -1)) {
                            f69a[i2].mo8130a(i);
                        }
                    }
                }
                m84b(i, -1);
            }
            if (!NetworkMidiSystem.get().isOpen(getIO(i), i)) {
                c = 0;
            }
            if (c > 0) {
                try {
                    if (getIO(i) <= 0) {
                        NetworkMidiSystem.get().mo8101a(i).close((NetworkMidiClient) null);
                    } else {
                        NetworkMidiSystem.get().mo8101a(i).close((NetworkMidiClient) null);
                    }
                } catch (Exception e) {
                    C0484p.logln(2, "Failed to close channel " + i);
                }
            }
        } catch (Exception e2) {
        }
    }

    /* renamed from: f */
    private static void m126f(int i) {
        NetworkMidiSystem.get().mo8101a(i);
    }

    public static void copyChannel(int i, int i2) {
        setIP(i2, getIP(i));
        setPort(i2, getPort(i));
        setLocalPort(i2, getLocalPort(i));
        m40a(i2, getMode(i));
        setNetworkAdapter(i2, getNetworkAdapter(i));
        setIO(i2, getIO(i));
        setFlags(i2, getFlags(i));
        setName(i2, getName(i).indexOf(f64a) != -1 ? String.valueOf(f64a) + (i2 + 1) : getName(i));
        if (getMode(i) == 1) {
            m84b(i2, getRTPState(i));
        }
    }

    /* renamed from: g */
    private static void m129g(int i) {
        boolean z;
        int i2;
        char c;
        String str;
        int i3;
        try {
            if (getMode(i) == 1) {
                f68a[i] = 0;
                setLocalClientName(i, (String) null);
            }
        } catch (Exception e) {
        }
        if (f74b == null) {
            f74b = new Vector<>();
        } else {
            f74b.removeAllElements();
        }
        if (i == getNumChannels() - 1) {
            z = true;
        } else {
            z = false;
        }
        for (Map.Entry<String, ?> obj : f59a.getAll().entrySet()) {
            String obj2 = obj.toString();
            try {
                int parseInt = Integer.parseInt(obj2.substring(obj2.indexOf("_") + 1, obj2.indexOf("=")));
                if (parseInt == i) {
                    f58a.remove(obj2.substring(0, obj2.indexOf("=")));
                } else if (!z && parseInt > i) {
                    f74b.add(obj2);
                }
            } catch (NumberFormatException e2) {
            }
        }
        f58a.commit();
        if (!z) {
            int i4 = 0;
            while (true) {
                if (f74b.size() <= 0) {
                    i2 = i4;
                    break;
                }
                i2 = i4 + 1;
                if (i4 >= 1000) {
                    break;
                }
                int i5 = 0;
                int i6 = Integer.MAX_VALUE;
                while (i5 < f74b.size()) {
                    int min = Math.min(i6, Integer.parseInt(f74b.get(i5).substring(f74b.get(i5).indexOf("_") + 1, f74b.get(i5).indexOf("="))));
                    i5++;
                    i6 = min;
                }
                for (int size = f74b.size() - 1; size >= 0; size--) {
                    int parseInt2 = Integer.parseInt(f74b.get(size).substring(f74b.get(size).indexOf("_") + 1, f74b.get(size).indexOf("=")));
                    if (parseInt2 == i6) {
                        String substring = f74b.get(size).substring(0, f74b.get(size).indexOf("="));
                        try {
                            str = null;
                            i3 = f59a.getInt(substring, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                            c = 0;
                        } catch (ClassCastException e3) {
                            try {
                                str = f59a.getString(substring, (String) null);
                                i3 = 0;
                                c = 1;
                            } catch (ClassCastException e4) {
                                c = 2;
                                str = null;
                                i3 = 0;
                            }
                        }
                        f58a.remove(substring);
                        if (c < 2) {
                            String replace = substring.replace(String.valueOf(parseInt2), String.valueOf(parseInt2 - 1));
                            if (c == 0 && i3 != Integer.MAX_VALUE) {
                                f58a.putInt(replace, i3);
                            } else if (str != null) {
                                f58a.putString(replace, str);
                            }
                        }
                        f58a.commit();
                        try {
                            f74b.remove(size);
                        } catch (Exception e5) {
                            i4 = i2;
                        }
                    }
                }
                i4 = i2;
            }
            if (i2 > 1000) {
                C0484p.m362a("timed out, prefs unchanged " + f74b.size());
            }
        }
    }

    /* renamed from: b */
    static void m90b(int i) {
        try {
            if (getMode(i) == 1 || getMode(i) == 6) {
                int a = m44a(m99c(i));
                if (f69a[a] == null) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    if (f69a[a] == null) {
                        m123f();
                    }
                }
                if (getIP(i) == null) {
                    f69a[a].mo8136d();
                } else if ((getFlags(-1) & 8192) != 0) {
                    f69a[a].mo8132a(i, false);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: c */
    static void m103c(int i) {
        try {
            NetworkMidiSystem.get();
            if (!NetworkMidiSystem.f213a) {
                if (getMode(i) != 1 && getMode(i) != 6) {
                    return;
                }
                if (getIP(i) == null) {
                    f69a[m44a(m99c(i))].mo8134b(i);
                } else if ((getFlags(-1) & 8192) != 0) {
                    f69a[m44a(m99c(i))].mo8132a(i, true);
                }
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    static int[] m78a(int i) {
        return m59a(-1, 0, 1);
    }

    /* renamed from: a */
    static int[] m79a(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (i4 < getNumChannels()) {
            try {
                try {
                    if ((getMode(i4) == i || (i < 0 && (getMode(i4) == 1 || getMode(i4) == 6))) && m42a(i4, false, false) == i3) {
                        if (i2 < 0) {
                            i5++;
                        } else if ((i2 & 1) == 0 || getIP(i4) != null) {
                            if ((i2 & 1) == 0 && getIP(i4) != null) {
                                if ((i2 & 2) == 0) {
                                    i5++;
                                } else if (NetworkMidiSystem.get().isOpen(-1, i4)) {
                                    i5++;
                                }
                            }
                        } else if ((i2 & 2) == 0) {
                            i5++;
                        } else if (NetworkMidiSystem.get().isOpen(-1, i4)) {
                            i5++;
                        }
                    }
                    i4++;
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                return null;
            }
        }
        if (i5 == 0) {
            return new int[0];
        }
        int[] iArr = new int[i5];
        int i6 = 0;
        int i7 = 0;
        while (i6 < getNumChannels()) {
            try {
                if ((getMode(i6) == i || (i < 0 && (getMode(i6) == 1 || getMode(i6) == 6))) && m42a(i6, false, false) == i3) {
                    if (i2 < 0) {
                        iArr[i7] = i6;
                        i7++;
                    } else if ((i2 & 1) == 0 || getIP(i6) != null) {
                        if ((i2 & 1) == 0 && getIP(i6) != null) {
                            if ((i2 & 2) == 0) {
                                iArr[i7] = i6;
                                i7++;
                            } else if (NetworkMidiSystem.get().isOpen(-1, i6)) {
                                int i8 = i7 + 1;
                                iArr[i7] = i6;
                                i7 = i8;
                            }
                        }
                    } else if ((i2 & 2) == 0) {
                        iArr[i7] = i6;
                        i7++;
                    } else if (NetworkMidiSystem.get().isOpen(-1, i6)) {
                        iArr[i7] = i6;
                        i7++;
                    }
                }
                i6++;
            } catch (Exception e3) {
            }
        }
        return iArr;
    }

    /* renamed from: a */
    static boolean m76a(String str) {
        String name;
        for (int i = 0; i < getNumChannels(); i++) {
            if (getMode(i) == 1 && getIP(i) == null && (name = getName(i)) != null && str.indexOf(name) != -1) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    private static boolean m107c(int i) {
        return getMode(i) == 1 && getIP(i) == null;
    }

    /* renamed from: d */
    static synchronized void m112d(final int i, final int i2) throws Exception {
        synchronized (NMJConfig.class) {
            if (!f89g && (getFlags(-1) & 3) != 0) {
                if (!C0484p.m403c("de.humatic.nmj.DNS_SDImpl")) {
                    try {
                        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                            new Thread(new Runnable() {
                                public final void run() {
                                    try {
                                        NMJConfig.m112d(i, i2);
                                    } catch (Exception e) {
                                        try {
                                            NMJConfig.m60a(-1, NMJConfig.f81c[NMJConfig.m39a(i2)], e.getMessage());
                                        } catch (Exception e2) {
                                        }
                                    }
                                }
                            }).start();
                        }
                    } catch (Exception e) {
                    }
                    if (m109d(i2)) {
                        f88f = false;
                        int b = m83b(i2);
                        if (f69a[b] == null) {
                            if (f62a == null) {
                                f62a = new C0474j();
                            }
                            f69a[b] = new C0468h(f62a, i2);
                        }
                        switch (i) {
                            case -1:
                                f69a[b].mo8133b();
                                break;
                            case 1:
                                f69a[b].mo8129a();
                                break;
                        }
                    }
                } else {
                    C0484p.logln(-1, "========= Oops, called from DNS =========");
                }
            }
        }
    }

    /* renamed from: d */
    private static boolean m115d(int i) {
        switch (i) {
            case 1:
                int a = C0484p.m346a();
                if (a != -1) {
                    if (m124f(a + 1).indexOf(EnvironmentCompat.MEDIA_UNKNOWN) >= 0) {
                        return false;
                    }
                    return true;
                } else if (a == -1) {
                    return false;
                } else {
                    return true;
                }
            case 2:
                if (m98c() == -1) {
                    return false;
                }
                return true;
            case 64:
                if (m116e() == -1) {
                    return false;
                }
                return true;
            case 256:
                if (Build.VERSION.SDK_INT < 14) {
                    return false;
                }
                return C0484p.m359a(false) != null;
            default:
                return false;
        }
    }

    /* renamed from: f */
    private static void m125f() {
        int i = 0;
        if ((getFlags(-1) & 3) != 0) {
            while (i < f76b.length) {
                try {
                    if (m109d(f76b[i])) {
                        if (f69a[i] != null) {
                            f69a[i].mo8131a(0, 0);
                        } else {
                            m112d(1, f76b[i]);
                        }
                        i++;
                    } else {
                        if (f69a[i] != null) {
                            f69a[i].mo8135c();
                            f69a[i] = null;
                        }
                        i++;
                    }
                } catch (Exception e) {
                    C0484p.m362a(e.toString());
                }
            }
        }
    }

    /* renamed from: a */
    static void m71a(boolean z, boolean z2) {
        if (f61a == null || z2) {
            if (z2 && f61a != null) {
                f61a.mo8111a();
            }
            f61a = new C0453a();
        } else if (z && f61a.f226a) {
            f61a.mo8111a();
        }
    }

    public static void resetAll() {
        try {
            if (f58a != null) {
                f58a.clear();
                f58a.commit();
            }
            setNumChannels(3);
            if (getMode(2) != 1) {
                try {
                    setMode(2, 1);
                } catch (NMJException e) {
                }
            }
            NetworkMidiSystem.m198a();
            m123f();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    static void m56a() {
        if (!f89g) {
            f88f = true;
            for (int i = 0; i < f69a.length; i++) {
                try {
                    if (f69a[i] != null) {
                        f69a[i].mo8135c();
                        f69a[i] = null;
                    }
                } catch (Exception e) {
                }
            }
            try {
                if (f65a != null) {
                    f65a.cancel();
                }
            } catch (Exception e2) {
            }
            try {
                if (f61a != null) {
                    f61a.mo8111a();
                }
            } catch (Exception e3) {
            }
            try {
                if (f60a != null) {
                    f60a.release();
                }
            } catch (Exception e4) {
            }
            try {
                f58a.putLong("lastClosed", System.currentTimeMillis());
                f58a.commit();
            } catch (Exception e5) {
            }
            f55a = 0;
            try {
                f66a.removeAllElements();
            } catch (Exception e6) {
            }
            if (Build.VERSION.SDK_INT >= 12) {
                try {
                    f57a.unregisterReceiver(f56a);
                } catch (Exception e7) {
                }
                try {
                    f57a.unregisterReceiver(f72b);
                } catch (Exception e8) {
                }
                try {
                    C0504x.m559c();
                } catch (Exception e9) {
                }
                try {
                    C0510y.m589c();
                } catch (Exception e10) {
                }
            }
            try {
                if (f78c != null) {
                    C0484p.logln(2, "Unregistering NetworkMonitor");
                    f57a.unregisterReceiver(f78c);
                }
            } catch (Exception e11) {
                C0484p.m377a(e11, "");
            }
            f89g = true;
            try {
                int numChannels = getNumChannels();
                for (int i2 = 0; i2 < numChannels; i2++) {
                    f58a.remove("rtpState_" + i2);
                }
                f58a.commit();
            } catch (Exception e12) {
            }
            f54a = -1;
            f57a = null;
            C0484p.m402c();
            C0484p.logln(2, "Config - exited");
        }
    }

    /* renamed from: b */
    static int m82b() {
        return C0484p.m393b();
    }

    public static void setDebugLevel(int i) {
        C0484p.m354a(i);
        if (C0484p.m393b() > 6 && f59a != null) {
            m128g();
        }
    }

    /* renamed from: g */
    private static void m128g() {
        C0484p.m362a(f59a.toString());
        for (Map.Entry<String, ?> obj : f59a.getAll().entrySet()) {
            C0484p.logln(3, "pref " + obj.toString());
        }
    }

    public static void setLogFile(File file) {
        C0484p.m375a(file);
    }

    public static void setLogStream(PrintStream printStream) {
        C0484p.m376a(printStream);
    }

    public static String getVersionInfo() {
        return "0.92";
    }

    /* renamed from: a */
    static short m55a(int i) {
        if (i != 0) {
            return Short.valueOf("0.92".substring("0.92".indexOf("."))).shortValue();
        }
        try {
            return Short.valueOf("0.92".substring(0, "0.92".indexOf("."))).shortValue();
        } catch (Exception e) {
            return -1;
        }
    }

    public static String getSystemInfo() {
        StringBuffer stringBuffer = new StringBuffer("nmj ");
        try {
            stringBuffer.append(getVersionInfo());
            stringBuffer.append("\n\n");
            String[] a = m38a();
            for (int i = 0; i < a.length; i++) {
                stringBuffer.append(String.valueOf(a[i]) + "\n");
            }
            stringBuffer.append("\n");
            stringBuffer.append(System.getProperty("os.name"));
            stringBuffer.append(" - ");
            stringBuffer.append(String.valueOf(System.getProperty("os.version")) + "\n");
            stringBuffer.append("Android: ");
            stringBuffer.append(Build.VERSION.RELEASE);
            stringBuffer.append("\n");
            stringBuffer.append(Build.MODEL);
            stringBuffer.append(" (");
            stringBuffer.append(Build.BOARD);
            stringBuffer.append(")\n");
            stringBuffer.append(String.valueOf(Build.MANUFACTURER) + " " + Build.DISPLAY + "\n");
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    static void m69a(TimerTask timerTask, int i, int i2) {
        if (!f88f) {
            if (f65a == null) {
                f65a = new Timer();
            }
            if (i2 > 0) {
                try {
                    f65a.scheduleAtFixedRate(timerTask, (long) (i >= 0 ? i : 0), (long) i2);
                } catch (IllegalStateException e) {
                    C0484p.logln(7, "on schedule " + e.toString());
                    f65a = null;
                    if (f71b == -1 || System.currentTimeMillis() - f71b >= 100) {
                        f71b = System.currentTimeMillis();
                        m69a(timerTask, i, i2);
                    }
                } catch (Exception e2) {
                    C0484p.logln(2, e2.toString());
                }
            } else {
                f65a.schedule(timerTask, (long) i);
            }
        }
    }

    /* renamed from: b */
    static void m89b() {
        C0456c.m225a(f57a, f62a);
    }

    /* renamed from: e */
    private static boolean m122e(int i) {
        try {
            if (Build.VERSION.SDK_INT < 12) {
                return false;
            }
            f86e = null;
            HashMap<String, UsbDevice> deviceList = ((UsbManager) f57a.getSystemService("usb")).getDeviceList();
            if (deviceList.isEmpty()) {
                return false;
            }
            int flags = getFlags(-1);
            for (UsbDevice next : deviceList.values()) {
                C0484p.logln(3, String.format("Found USB device %x:%x", new Object[]{Integer.valueOf(next.getVendorId()), Integer.valueOf(next.getProductId())}));
                if (m40a(next.getVendorId(), next.getProductId())) {
                    return false;
                }
                for (int numChannels = getNumChannels() - 1; numChannels > 0; numChannels--) {
                    if (getMode(numChannels) == i) {
                        int localPort = (getLocalPort(numChannels) >> 16) & SupportMenu.USER_MASK;
                        int localPort2 = getLocalPort(numChannels) & SupportMenu.USER_MASK;
                        if (next.getVendorId() == localPort && next.getProductId() == localPort2) {
                            return true;
                        }
                    }
                }
                if (i == 5 && (flags & 128) == 0) {
                    if (next.getDeviceClass() == 1 && next.getDeviceSubclass() == 3) {
                        return true;
                    }
                    if (next.getDeviceClass() == 0 && next.getDeviceSubclass() == 0) {
                        if (next.getInterfaceCount() < 2) {
                            C0484p.logln(2, "only 1 interface on " + next);
                        } else {
                            UsbManager usbManager = (UsbManager) f57a.getSystemService("usb");
                            if (!usbManager.hasPermission(next)) {
                                C0484p.logln(2, "qc host devices, no permission for " + next);
                            } else {
                                UsbDeviceConnection openDevice = usbManager.openDevice(next);
                                if (!openDevice.claimInterface(next.getInterface(1), true)) {
                                    C0484p.logln(2, "USBMidi - failed to claim interface for " + next);
                                    openDevice.close();
                                } else {
                                    UsbInterface usbInterface = next.getInterface(1);
                                    if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                                        openDevice.close();
                                        return true;
                                    }
                                    openDevice.close();
                                }
                            }
                        }
                    }
                } else if (i == 7 && (flags & 256) == 0) {
                    if ((next.getDeviceClass() == 255 && next.getDeviceSubclass() == 255) || ((next.getDeviceClass() == 2 && next.getDeviceSubclass() == 0) || (next.getDeviceClass() == 0 && next.getDeviceSubclass() == 0))) {
                        int interfaceClass = next.getInterface(0).getInterfaceClass();
                        if (interfaceClass == 255 || interfaceClass == 2) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    static void m94b(boolean z) {
        boolean z2;
        if (z) {
            try {
                UsbManager usbManager = (UsbManager) f57a.getSystemService("usb");
                HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
                if (deviceList.isEmpty()) {
                    C0484p.logln(3, "No Usb Interfaces present");
                    for (int numChannels = getNumChannels() - 1; numChannels > 0; numChannels--) {
                        if (getMode(numChannels) == 7) {
                            deleteChannel(numChannels);
                        }
                        if (getMode(numChannels) == 5) {
                            deleteChannel(numChannels);
                        }
                    }
                    return;
                }
                for (int numChannels2 = getNumChannels() - 1; numChannels2 > 0; numChannels2--) {
                    if (getMode(numChannels2) == 7) {
                        int localPort = (getLocalPort(numChannels2) >> 16) & SupportMenu.USER_MASK;
                        int localPort2 = getLocalPort(numChannels2) & SupportMenu.USER_MASK;
                        Iterator<UsbDevice> it = deviceList.values().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                UsbDevice next = it.next();
                                if (!m40a(next.getVendorId(), next.getProductId()) && next.getVendorId() == localPort && next.getProductId() == localPort2) {
                                    C0484p.logln(3, "Previously known Usb Serial Interface is present: " + next);
                                    C0484p.logln(3, "Permission: " + usbManager.hasPermission(next));
                                    m130h();
                                    z2 = true;
                                    break;
                                }
                            } else {
                                z2 = false;
                                break;
                            }
                        }
                        if (!z2) {
                            deleteChannel(numChannels2);
                        }
                    }
                }
            } catch (Exception e) {
                return;
            }
        }
        C0510y.m582a(f62a);
    }

    /* renamed from: h */
    private static void m130h() {
        if (!f75b) {
            try {
                IntentFilter intentFilter = new IntentFilter("android.hardware.usb.action.USB_DEVICE_DETACHED");
                f72b = new BroadcastReceiver() {
                    public final void onReceive(Context context, Intent intent) {
                        if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
                            synchronized (this) {
                                C0484p.logln(3, "USB_DEVICE_DETACHED " + intent);
                                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                                for (int numChannels = NMJConfig.getNumChannels() - 1; numChannels > 0; numChannels--) {
                                    if (NMJConfig.getMode(numChannels) == 5 || NMJConfig.getMode(numChannels) == 7) {
                                        int localPort = (NMJConfig.getLocalPort(numChannels) >> 16) & SupportMenu.USER_MASK;
                                        int localPort2 = NMJConfig.getLocalPort(numChannels) & SupportMenu.USER_MASK;
                                        if (usbDevice.getVendorId() == localPort && usbDevice.getProductId() == localPort2) {
                                            C0484p.logln(2, "Removing USB_HOST channel " + NMJConfig.getName(numChannels));
                                            if (NetworkMidiSystem.get().isOpen(0, numChannels)) {
                                                NetworkMidiSystem.get().mo8102a(0, numChannels).close((NetworkMidiClient) null);
                                            }
                                            if (NetworkMidiSystem.get().isOpen(1, numChannels)) {
                                                NetworkMidiSystem.get().mo8102a(1, numChannels).close((NetworkMidiClient) null);
                                            }
                                            if (NMJConfig.getMode(numChannels) == 5) {
                                                C0504x.m562e();
                                            } else {
                                                C0510y.m592e();
                                            }
                                            NMJConfig.deleteChannel(numChannels);
                                        }
                                    }
                                }
                                try {
                                    C0504x.m562e();
                                } catch (Exception e) {
                                    try {
                                        C0510y.m592e();
                                    } catch (Exception e2) {
                                    }
                                }
                                if ((NMJConfig.getFlags(-1) & 32) != 0) {
                                    NMJConfig.m108d();
                                }
                                int i = 0;
                                for (int i2 = 0; i2 < NMJConfig.getNumChannels(); i2++) {
                                    if (NMJConfig.getMode(i2) == 5) {
                                        i++;
                                    }
                                }
                                if (i == 0) {
                                    NMJConfig.m82b().unregisterReceiver(this);
                                }
                                NMJConfig.f75b = false;
                            }
                        }
                    }
                };
                f57a.registerReceiver(f72b, intentFilter);
                C0484p.logln(3, "USB detachment receiver registered");
                f75b = true;
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: i */
    private static void m131i() {
        if (!f80c) {
            try {
                f56a = new BroadcastReceiver() {
                    public final void onReceive(Context context, Intent intent) {
                        if (NMJConfig.USB_DEVICE_ATTACHED.equals(intent.getAction())) {
                            synchronized (this) {
                                try {
                                    C0484p.logln(3, "DEVICE_ATTACHED " + intent);
                                    if ((NMJConfig.getFlags(-1) & 128) == 0) {
                                        NMJConfig.m54a(true);
                                    } else if ((NMJConfig.getFlags(-1) & 256) == 0) {
                                        NMJConfig.m88b(true);
                                    }
                                    NMJConfig.m82b().unregisterReceiver(this);
                                    NMJConfig.f80c = false;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                };
                f57a.registerReceiver(f56a, new IntentFilter(USB_DEVICE_ATTACHED));
                f80c = true;
                C0484p.logln(3, "USB attachment receiver running ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    static boolean m74a(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        while (i6 < f85d.length) {
            try {
                if (i == f85d[i6]) {
                    C0484p.logln(2, String.format("Skipping USB device 0x%x:0x%x", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
                    return true;
                }
                i6++;
            } catch (Exception e) {
            }
        }
        String property = getProperty("usb_blacklist", "");
        if (property.length() == 0) {
            return false;
        }
        String[] split = property.split(",");
        if (split.length == 0) {
            return false;
        }
        for (int i7 = 0; i7 < split.length; i7++) {
            String str = split[i7].split(":")[0];
            if (split[i7].split(":").length > 1) {
                String str2 = split[i7].split(":")[1];
                i3 = Integer.parseInt(str2.substring(str2.indexOf("0x") != -1 ? 2 : 0), str2.indexOf("0x") != -1 ? 16 : 10);
            } else {
                i3 = -1;
            }
            if (str.indexOf("0x") != -1) {
                i4 = 2;
            } else {
                i4 = 0;
            }
            String substring = str.substring(i4);
            if (str.indexOf("0x") != -1) {
                i5 = 16;
            } else {
                i5 = 10;
            }
            int parseInt = Integer.parseInt(substring, i5);
            if ((parseInt == -1 || parseInt == i) && ((i3 == -1 || i3 == i2) && ((parseInt >= 0 || i3 >= 0) && ((parseInt < 0 || i == parseInt) && (i3 < 0 || i3 == i2))))) {
                C0484p.logln(2, String.format("Skipping USB device 0x%x:0x%x", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    static void m61a(int i, String str) {
        if (i == 5) {
            if ((getFlags(-1) & 256) == 0) {
                f86e = str;
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(getProperty("usb_blacklist", ""));
            if (stringBuffer.length() > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(str);
            setProperty("usb_blacklist", stringBuffer.toString());
            C0484p.m362a("added to blacklist " + i + " " + str);
        } else if (i != 7) {
        } else {
            if ((getFlags(-1) & 128) != 0 || f86e != null) {
                StringBuffer stringBuffer2 = new StringBuffer(getProperty("usb_blacklist", ""));
                if (stringBuffer2.length() > 0) {
                    stringBuffer2.append(",");
                }
                stringBuffer2.append(str);
                setProperty("usb_blacklist", stringBuffer2.toString());
                C0484p.m362a("added to blacklist " + i + " " + str);
            }
        }
    }

    /* renamed from: j */
    private static void m132j() {
        try {
            f57a.unregisterReceiver(f78c);
        } catch (Exception e) {
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            if (Build.VERSION.SDK_INT >= 14) {
                intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
            }
            intentFilter.addAction("android.net.conn.TETHER_STATE_CHANGED");
            if ((getFlags(-1) & 16) != 0) {
                intentFilter.addAction("android.hardware.usb.action.USB_STATE");
            }
            f78c = new BroadcastReceiver() {
                /* JADX WARNING: Code restructure failed: missing block: B:132:0x01df, code lost:
                    if (r2 != r3) goto L_0x01e1;
                 */
                /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void onReceive(android.content.Context r13, android.content.Intent r14) {
                    /*
                        r12 = this;
                        r4 = 0
                        r5 = 1
                        android.content.Context r2 = p004de.humatic.nmj.NMJConfig.m82b()     // Catch:{ Exception -> 0x0074 }
                        boolean r2 = r13.equals(r2)     // Catch:{ Exception -> 0x0074 }
                        if (r2 == 0) goto L_0x0019
                        long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0074 }
                        long r6 = p004de.humatic.nmj.NMJConfig.f55a     // Catch:{ Exception -> 0x0074 }
                        long r2 = r2 - r6
                        r6 = 2000(0x7d0, double:9.88E-321)
                        int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                        if (r2 >= 0) goto L_0x001a
                    L_0x0019:
                        return
                    L_0x001a:
                        java.lang.String r2 = r14.getAction()     // Catch:{ Exception -> 0x0074 }
                        java.lang.String r3 = "android.net.conn.CONNECTIVITY_CHANGE"
                        boolean r3 = r2.equals(r3)     // Catch:{ Exception -> 0x0074 }
                        if (r3 == 0) goto L_0x008a
                        java.lang.String r2 = "noConnectivity"
                        r3 = 0
                        boolean r3 = r14.getBooleanExtra(r2, r3)     // Catch:{ Exception -> 0x0074 }
                        java.lang.String r2 = "networkInfo"
                        android.os.Parcelable r2 = r14.getParcelableExtra(r2)     // Catch:{ Exception -> 0x0074 }
                        android.net.NetworkInfo r2 = (android.net.NetworkInfo) r2     // Catch:{ Exception -> 0x0074 }
                        int r6 = r2.getType()     // Catch:{ Exception -> 0x0074 }
                        if (r6 == r5) goto L_0x0043
                        int r6 = r2.getType()     // Catch:{ Exception -> 0x0074 }
                        r7 = 9
                        if (r6 != r7) goto L_0x005b
                    L_0x0043:
                        if (r3 != 0) goto L_0x004d
                        android.net.NetworkInfo$State r3 = r2.getState()     // Catch:{ Exception -> 0x0074 }
                        android.net.NetworkInfo$State r6 = android.net.NetworkInfo.State.DISCONNECTED     // Catch:{ Exception -> 0x0074 }
                        if (r3 != r6) goto L_0x0078
                    L_0x004d:
                        int r2 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r2 = r2 & 1
                        if (r2 != 0) goto L_0x0076
                        boolean r2 = p004de.humatic.nmj.NMJConfig.m98c()     // Catch:{ Exception -> 0x0074 }
                        if (r2 != 0) goto L_0x0076
                        r2 = r4
                    L_0x005a:
                        r4 = r2
                    L_0x005b:
                        if (r4 == 0) goto L_0x0019
                        java.lang.Object r3 = p004de.humatic.nmj.NMJConfig.m38a()     // Catch:{ Exception -> 0x0074 }
                        monitor-enter(r3)     // Catch:{ Exception -> 0x0074 }
                        de.humatic.nmj.NMJConfig$5$1 r2 = new de.humatic.nmj.NMJConfig$5$1     // Catch:{ all -> 0x0071 }
                        r2.<init>(r12)     // Catch:{ all -> 0x0071 }
                        java.lang.Thread r4 = new java.lang.Thread     // Catch:{ all -> 0x0071 }
                        r4.<init>(r2)     // Catch:{ all -> 0x0071 }
                        r4.start()     // Catch:{ all -> 0x0071 }
                        monitor-exit(r3)     // Catch:{ all -> 0x0071 }
                        goto L_0x0019
                    L_0x0071:
                        r2 = move-exception
                        monitor-exit(r3)     // Catch:{ all -> 0x0071 }
                        throw r2     // Catch:{ Exception -> 0x0074 }
                    L_0x0074:
                        r2 = move-exception
                        goto L_0x0019
                    L_0x0076:
                        r2 = r5
                        goto L_0x005a
                    L_0x0078:
                        android.net.NetworkInfo$State r2 = r2.getState()     // Catch:{ Exception -> 0x0074 }
                        android.net.NetworkInfo$State r3 = android.net.NetworkInfo.State.CONNECTED     // Catch:{ Exception -> 0x0074 }
                        if (r2 != r3) goto L_0x005b
                        int r2 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r2 = r2 & 1
                        if (r2 != 0) goto L_0x0088
                    L_0x0086:
                        r4 = r5
                        goto L_0x005b
                    L_0x0088:
                        r5 = r4
                        goto L_0x0086
                    L_0x008a:
                        int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0074 }
                        r6 = 14
                        if (r3 < r6) goto L_0x00c4
                        java.lang.String r3 = "android.net.wifi.p2p.CONNECTION_STATE_CHANGE"
                        boolean r3 = r3.equals(r2)     // Catch:{ Exception -> 0x0074 }
                        if (r3 == 0) goto L_0x00c4
                        java.lang.String r2 = "networkInfo"
                        android.os.Parcelable r2 = r14.getParcelableExtra(r2)     // Catch:{ Exception -> 0x0074 }
                        android.net.NetworkInfo r2 = (android.net.NetworkInfo) r2     // Catch:{ Exception -> 0x0074 }
                        android.net.NetworkInfo$State r3 = r2.getState()     // Catch:{ Exception -> 0x0074 }
                        android.net.NetworkInfo$State r6 = android.net.NetworkInfo.State.DISCONNECTED     // Catch:{ Exception -> 0x0074 }
                        if (r3 != r6) goto L_0x00b2
                        int r2 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r2 = r2 & 256(0x100, float:3.59E-43)
                        if (r2 == 0) goto L_0x00b0
                    L_0x00ae:
                        r4 = r5
                        goto L_0x005b
                    L_0x00b0:
                        r5 = r4
                        goto L_0x00ae
                    L_0x00b2:
                        android.net.NetworkInfo$State r2 = r2.getState()     // Catch:{ Exception -> 0x0074 }
                        android.net.NetworkInfo$State r3 = android.net.NetworkInfo.State.CONNECTED     // Catch:{ Exception -> 0x0074 }
                        if (r2 != r3) goto L_0x005b
                        int r2 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r2 = r2 & 256(0x100, float:3.59E-43)
                        if (r2 != 0) goto L_0x00c2
                    L_0x00c0:
                        r4 = r5
                        goto L_0x005b
                    L_0x00c2:
                        r5 = r4
                        goto L_0x00c0
                    L_0x00c4:
                        java.lang.String r3 = "android.net.conn.TETHER_STATE_CHANGED"
                        boolean r3 = r2.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0074 }
                        if (r3 == 0) goto L_0x018c
                        android.os.Bundle r6 = r14.getExtras()     // Catch:{ Exception -> 0x0074 }
                        java.util.Set r2 = r6.keySet()     // Catch:{ Exception -> 0x0074 }
                        java.util.Iterator r7 = r2.iterator()     // Catch:{ Exception -> 0x0074 }
                    L_0x00d8:
                        boolean r2 = r7.hasNext()     // Catch:{ Exception -> 0x0074 }
                        if (r2 == 0) goto L_0x005b
                        java.lang.Object r2 = r7.next()     // Catch:{ Exception -> 0x0074 }
                        r0 = r2
                        java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0074 }
                        r3 = r0
                        java.lang.Object r2 = r6.get(r3)     // Catch:{ Exception -> 0x0074 }
                        if (r2 == 0) goto L_0x00d8
                        java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0074 }
                        java.lang.String r8 = "activeArray"
                        boolean r3 = r3.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x0074 }
                        if (r3 == 0) goto L_0x00d8
                        java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ Exception -> 0x0074 }
                        int r3 = r2.size()     // Catch:{ Exception -> 0x0074 }
                        if (r3 != 0) goto L_0x0200
                        int r3 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r3 = r3 & 66
                        if (r3 != 0) goto L_0x010c
                        boolean r3 = p004de.humatic.nmj.NMJConfig.m98c()     // Catch:{ Exception -> 0x0074 }
                        if (r3 == 0) goto L_0x005b
                    L_0x010c:
                        r3 = r5
                    L_0x010d:
                        r8 = r4
                        r6 = r4
                        r7 = r4
                    L_0x0110:
                        int r9 = r2.size()     // Catch:{ Exception -> 0x0074 }
                        if (r8 < r9) goto L_0x0133
                        if (r7 != 0) goto L_0x01f6
                        int r2 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r2 = r2 & 2
                        if (r2 == 0) goto L_0x01f6
                        r2 = r5
                    L_0x011f:
                        if (r6 != 0) goto L_0x0128
                        int r3 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r3 = r3 & 64
                        if (r3 == 0) goto L_0x0128
                        r2 = r5
                    L_0x0128:
                        if (r4 != 0) goto L_0x01f3
                        boolean r3 = p004de.humatic.nmj.NMJConfig.m98c()     // Catch:{ Exception -> 0x0074 }
                        if (r3 == 0) goto L_0x01f3
                        r4 = r5
                        goto L_0x005b
                    L_0x0133:
                        java.lang.Object r9 = r2.get(r8)     // Catch:{ Exception -> 0x0074 }
                        java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0074 }
                        java.lang.String r10 = "usb"
                        int r10 = r9.indexOf(r10)     // Catch:{ Exception -> 0x0074 }
                        if (r10 == 0) goto L_0x014b
                        java.lang.String r10 = "rndis"
                        int r10 = r9.indexOf(r10)     // Catch:{ Exception -> 0x0074 }
                        if (r10 != 0) goto L_0x015e
                    L_0x014b:
                        int r7 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r7 = r7 & 2
                        if (r7 != 0) goto L_0x0152
                        r3 = r5
                    L_0x0152:
                        r7 = r3
                        r3 = r4
                        r4 = r6
                        r6 = r5
                    L_0x0156:
                        int r8 = r8 + 1
                        r11 = r3
                        r3 = r7
                        r7 = r6
                        r6 = r4
                        r4 = r11
                        goto L_0x0110
                    L_0x015e:
                        java.lang.String r10 = "bt-pan"
                        int r10 = r9.indexOf(r10)     // Catch:{ Exception -> 0x0074 }
                        if (r10 == 0) goto L_0x016e
                        java.lang.String r10 = "bnep"
                        int r10 = r9.indexOf(r10)     // Catch:{ Exception -> 0x0074 }
                        if (r10 != 0) goto L_0x017a
                    L_0x016e:
                        int r6 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x0074 }
                        r6 = r6 & 64
                        if (r6 != 0) goto L_0x0175
                        r3 = r5
                    L_0x0175:
                        r6 = r7
                        r7 = r3
                        r3 = r4
                        r4 = r5
                        goto L_0x0156
                    L_0x017a:
                        boolean r9 = p004de.humatic.nmj.C0484p.m398b((java.lang.String) r9)     // Catch:{ Exception -> 0x0074 }
                        if (r9 == 0) goto L_0x01f9
                        boolean r4 = p004de.humatic.nmj.NMJConfig.m98c()     // Catch:{ Exception -> 0x0074 }
                        if (r4 != 0) goto L_0x0187
                        r3 = r5
                    L_0x0187:
                        r4 = r6
                        r6 = r7
                        r7 = r3
                        r3 = r5
                        goto L_0x0156
                    L_0x018c:
                        java.lang.String r3 = "android.hardware.usb.action.USB_STATE"
                        boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0074 }
                        if (r2 == 0) goto L_0x005b
                        android.os.Bundle r2 = r14.getExtras()     // Catch:{ Exception -> 0x01ec }
                        java.lang.String r3 = "connected"
                        boolean r2 = r2.getBoolean(r3)     // Catch:{ Exception -> 0x01ec }
                        p004de.humatic.nmj.NMJConfig.f92j = r2     // Catch:{ Exception -> 0x01ec }
                        int r2 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x01ec }
                        r2 = r2 & 8
                        if (r2 == 0) goto L_0x01e4
                        r2 = r5
                    L_0x01a8:
                        boolean r3 = p004de.humatic.nmj.NMJConfig.m108d()     // Catch:{ Exception -> 0x01ec }
                        r2 = r2 ^ r3
                        boolean r3 = p004de.humatic.nmj.NMJConfig.m108d()     // Catch:{ Exception -> 0x01ef }
                        if (r3 == 0) goto L_0x01f3
                        android.os.Bundle r3 = r14.getExtras()     // Catch:{ Exception -> 0x01ef }
                        java.lang.String r6 = "configured"
                        boolean r6 = r3.getBoolean(r6)     // Catch:{ Exception -> 0x01ef }
                        android.os.Bundle r3 = r14.getExtras()     // Catch:{ Exception -> 0x01ef }
                        java.lang.String r7 = "adb"
                        boolean r3 = r3.getBoolean(r7)     // Catch:{ Exception -> 0x01ef }
                        if (r3 == 0) goto L_0x01e6
                        android.os.Bundle r3 = r14.getExtras()     // Catch:{ Exception -> 0x01ef }
                        java.lang.String r7 = "midi"
                        boolean r3 = r3.getBoolean(r7)     // Catch:{ Exception -> 0x01ef }
                        if (r3 != 0) goto L_0x01e6
                        r3 = r5
                    L_0x01d6:
                        if (r6 == 0) goto L_0x01ea
                        int r2 = p004de.humatic.nmj.NMJConfig.f54a     // Catch:{ Exception -> 0x01ef }
                        r2 = r2 & 8
                        if (r2 == 0) goto L_0x01e8
                        r2 = r5
                    L_0x01df:
                        if (r2 == r3) goto L_0x01ea
                    L_0x01e1:
                        r4 = r5
                        goto L_0x005b
                    L_0x01e4:
                        r2 = r4
                        goto L_0x01a8
                    L_0x01e6:
                        r3 = r4
                        goto L_0x01d6
                    L_0x01e8:
                        r2 = r4
                        goto L_0x01df
                    L_0x01ea:
                        r5 = r4
                        goto L_0x01e1
                    L_0x01ec:
                        r2 = move-exception
                        goto L_0x005b
                    L_0x01ef:
                        r3 = move-exception
                        r4 = r2
                        goto L_0x005b
                    L_0x01f3:
                        r4 = r2
                        goto L_0x005b
                    L_0x01f6:
                        r2 = r3
                        goto L_0x011f
                    L_0x01f9:
                        r11 = r4
                        r4 = r6
                        r6 = r7
                        r7 = r3
                        r3 = r11
                        goto L_0x0156
                    L_0x0200:
                        r3 = r4
                        goto L_0x010d
                    */
                    throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NMJConfig.C04315.onReceive(android.content.Context, android.content.Intent):void");
                }
            };
            C0484p.logln(2, "Registering NetworkMonitor");
            f57a.registerReceiver(f78c, intentFilter);
        } catch (Exception e2) {
        }
    }

    /* renamed from: a */
    static Object m49a() {
        if (f63a == null) {
            f63a = new Object();
        }
        return f63a;
    }

    /* renamed from: b */
    private static String m86b() {
        try {
            String b = m88b(false);
            return String.valueOf(b.substring(0, b.lastIndexOf(".") + 1)) + "255";
        } catch (Exception e) {
            return "192.168.0.255";
        }
    }

    /* renamed from: a */
    static void m70a(boolean z) {
        boolean z2;
        try {
            UsbManager usbManager = (UsbManager) f57a.getSystemService("usb");
            HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
            if (deviceList.isEmpty()) {
                C0484p.logln(3, "No Usb Interfaces present");
                for (int numChannels = getNumChannels() - 1; numChannels > 0; numChannels--) {
                    if (getMode(numChannels) == 5) {
                        deleteChannel(numChannels);
                    }
                    if (getMode(numChannels) == 7) {
                        deleteChannel(numChannels);
                    }
                }
                return;
            }
            for (int numChannels2 = getNumChannels() - 1; numChannels2 > 0; numChannels2--) {
                if (getMode(numChannels2) == 5) {
                    int localPort = (getLocalPort(numChannels2) >> 16) & SupportMenu.USER_MASK;
                    int localPort2 = getLocalPort(numChannels2) & SupportMenu.USER_MASK;
                    Iterator<UsbDevice> it = deviceList.values().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            UsbDevice next = it.next();
                            if (!m40a(next.getVendorId(), next.getProductId()) && next.getVendorId() == localPort && next.getProductId() == localPort2) {
                                C0484p.logln(3, "Previously known Usb Interface is present: " + next);
                                C0484p.logln(3, "Permission: " + usbManager.hasPermission(next));
                                m130h();
                                z2 = true;
                                break;
                            }
                        } else {
                            z2 = false;
                            break;
                        }
                    }
                    if (!z2) {
                        deleteChannel(numChannels2);
                    }
                }
            }
            C0504x.m545a(f62a);
        } catch (Exception e) {
        }
    }
}
