package p004de.humatic.nmj;

import android.os.Build;
import android.support.p003v7.widget.ActivityChooserView;
import android.support.p003v7.widget.helper.ItemTouchHelper;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: de.humatic.nmj.p */
class C0484p {

    /* renamed from: a */
    private static int f416a = 0;

    /* renamed from: a */
    private static PrintStream f417a = null;

    /* renamed from: a */
    private static String f418a;

    /* renamed from: a */
    private static StringBuffer f419a = new StringBuffer();

    /* renamed from: a */
    private static ExecutorService f420a;

    /* renamed from: a */
    private static boolean f421a = true;

    /* renamed from: a */
    private static final byte[] f422a;

    /* renamed from: a */
    private static int[] f423a = {128, 144, 160, 176, 192, 208, 224, 240, 248, 249, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 251, 252, 255, 256};

    /* renamed from: a */
    private static String[] f424a = {"Note off", "Note on", "poly.Aftertouch", "Controller", "Prg.Change", "Aftertouch", "Pitch", "SysEx", "", "Measure", "   Play", "  Play / Continue", "   Stop", "undefined", ""};

    /* renamed from: b */
    private static String f425b;

    /* renamed from: b */
    private static byte[] f426b = new byte[1];

    /* renamed from: c */
    private static String f427c;

    /* renamed from: d */
    private static String f428d;

    /* renamed from: e */
    private static String f429e;

    /* renamed from: f */
    private static String f430f;

    /* renamed from: g */
    private static String f431g;

    /* renamed from: h */
    private static String f432h;

    /* renamed from: i */
    private static String f433i;

    C0484p() {
    }

    static {
        new SimpleDateFormat("yy/MM/dd;HH:mm:ss.SS");
        byte[] bArr = new byte[8];
        bArr[0] = -16;
        bArr[1] = 125;
        bArr[4] = 109;
        bArr[5] = 110;
        bArr[6] = 101;
        bArr[7] = 116;
        f422a = bArr;
    }

    /* renamed from: a */
    static void m365a() {
        f418a = null;
        f425b = null;
        f427c = null;
        f428d = null;
        f429e = null;
        f430f = null;
        f431g = null;
        f432h = null;
        f433i = null;
        f420a = null;
    }

    /* renamed from: a */
    static String m353a(byte b) {
        if ((b & 255) < 16) {
            return "0" + Long.toHexString((long) (b & 255));
        }
        return Long.toHexString((long) (b & 255));
    }

    /* renamed from: a */
    static String m356a(short s) {
        if ((s & 255) < 16) {
            return "00" + Long.toHexString((long) (s & 15));
        }
        if ((s & 255) < 256) {
            return "0" + Long.toHexString((long) (s & 255));
        }
        return Long.toHexString((long) (65535 & s));
    }

    /* renamed from: a */
    static String m354a(int i) {
        if (i == -1) {
            return "-1";
        }
        if (i < 16) {
            return "0" + Long.toHexString((long) i);
        }
        return Long.toHexString((long) i);
    }

    /* renamed from: a */
    static void m383a(byte[] bArr, int i, int i2) {
        m371a(-1, "", bArr, i, i2);
    }

    /* renamed from: a */
    static void m380a(String str, byte[] bArr) {
        m371a(-1, str, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    static void m381a(String str, byte[] bArr, int i, int i2) {
        m371a(-1, str, bArr, i, i2);
    }

    /* renamed from: a */
    static void m370a(int i, String str, byte[] bArr) {
        m371a(i, str, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    static synchronized void m371a(int i, String str, byte[] bArr, int i2, int i3) {
        int i4;
        synchronized (C0484p.class) {
            if (i <= f416a) {
                if (str.length() > 0) {
                    m368a(-1, str);
                    if (str.length() >= 8) {
                        m362a("");
                    } else {
                        m398b("  ");
                    }
                }
                if (i2 < 0) {
                    i4 = 0;
                } else {
                    i4 = i2;
                }
                if (i3 <= i4) {
                    i3 += i4;
                }
                int min = Math.min(i3, bArr.length);
                int i5 = 0;
                int i6 = i4;
                boolean z = false;
                while (i6 < min) {
                    z = false;
                    try {
                        String a = m353a(bArr[i6]);
                        m368a(-1, String.valueOf(a.length() < 2 ? "0" : "") + a + " ");
                        if ((i5 + 1) % 8 != 0 || (i5 + 1) % 16 == 0) {
                            if ((i5 + 1) % 16 == 0) {
                                z = true;
                                m398b("  ");
                                int i7 = i6 - 15;
                                while (i7 <= i6) {
                                    try {
                                        if ((bArr[i7] & 255) <= 32 || (bArr[i7] & 255) >= 128) {
                                            m398b(".");
                                            i7++;
                                        } else {
                                            m353a(bArr[i7]);
                                            i7++;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                m362a("");
                            }
                            i5++;
                            i6++;
                        } else {
                            m368a(-1, "  ");
                            i5++;
                            i6++;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (!z) {
                    for (int i8 = (min - i4) % 16; i8 < 15; i8++) {
                        m398b("   ");
                    }
                    if ((min - i4) % 16 > 8) {
                        m398b("  ");
                    }
                    m398b(" ");
                    m398b("  ");
                    int i9 = min - ((min - i4) % 16);
                    while (i9 < min) {
                        try {
                            if (bArr[i9] <= 32 || (bArr[i9] & 255) >= 128) {
                                m398b(".");
                                i9++;
                            } else {
                                m353a(bArr[i9]);
                                i9++;
                            }
                        } catch (Exception e3) {
                        }
                    }
                    m362a("");
                }
                if ((min - i4) % 16 != 0) {
                    m362a("");
                }
            }
        }
    }

    /* renamed from: a */
    static short m364a(byte[] bArr, int i) {
        return (short) m349a(bArr, i, 2);
    }

    /* renamed from: a */
    static int m348a(byte[] bArr, int i) {
        return m349a(bArr, i, 4);
    }

    /* renamed from: a */
    static int m349a(byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        int i4 = 0;
        int i5 = i;
        while (i5 < i + i2) {
            i4 |= (bArr[i5] & 255) << (i3 << 3);
            i5++;
            i3--;
        }
        return i4;
    }

    /* renamed from: a */
    static void m382a(short s, byte[] bArr, int i) {
        for (int i2 = 0; i2 < 2; i2++) {
            bArr[i + i2] = (byte) (s >> ((1 - i2) << 3));
        }
    }

    /* renamed from: a */
    static void m372a(int i, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[i2 + i3] = (byte) (i >> ((3 - i3) << 3));
        }
    }

    /* renamed from: a */
    static void m373a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < 2; i4++) {
            bArr[i2 + i4] = (byte) (i >> ((1 - i4) << 3));
        }
    }

    /* renamed from: a */
    static void m374a(long j, byte[] bArr, int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i + i2] = (byte) ((int) ((j >> ((7 - i2) << 3)) & 255));
        }
    }

    /* renamed from: a */
    static int m350a(byte[] bArr, int i, int i2, byte[] bArr2) {
        int i3 = i;
        while (i3 < i2) {
            int i4 = 0;
            while (i4 < bArr2.length && bArr[i3 + i4] == bArr2[i4]) {
                try {
                    if (i4 == bArr2.length - 1) {
                        return i3;
                    }
                    i4++;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            i3++;
        }
        return -1;
    }

    /* renamed from: a */
    static boolean m386a(String str) {
        try {
            return Integer.valueOf(str.substring(0, str.indexOf("."))).intValue() >= 223;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    static boolean m399b(String str) {
        if (str == null) {
            return false;
        }
        if (str.indexOf("eth") == 0 || str.indexOf("wlan") == 0 || str.indexOf("mlan") == 0 || str.indexOf("tiwlan") == 0 || str.indexOf("ra") == 0 || str.indexOf("wl0") == 0 || str.indexOf("arc") == 0 || str.indexOf("ap0") == 0 || str.indexOf("wifi") == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    static NetworkInterface m361a(int i) throws Exception {
        if (i == 1) {
            return m346a();
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (i == 2) {
                    if (nextElement.getName().indexOf("usb") != -1 && nextElement.getName().indexOf("rmnet") == -1) {
                        return nextElement;
                    }
                    if (nextElement.getName().toLowerCase().indexOf("rndis") != -1) {
                        return nextElement;
                    }
                } else if (i == 256) {
                    if (nextElement.getName().indexOf("p2p-") != -1) {
                        return nextElement;
                    }
                } else if (i == 64 && !(nextElement.getName().indexOf("bt-pan") == -1 && nextElement.getName().indexOf("bnep") == -1)) {
                    return nextElement;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: a */
    static int m346a() {
        try {
            NetworkInterface a = m346a();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i = -1;
            while (networkInterfaces.hasMoreElements()) {
                i++;
                try {
                    if (networkInterfaces.nextElement().getName().equalsIgnoreCase(a.getName())) {
                        return i;
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
        return -1;
    }

    /* renamed from: a */
    static InetAddress m359a(boolean z) {
        try {
            try {
                Enumeration<InetAddress> inetAddresses = m346a().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!z && (nextElement instanceof Inet4Address)) {
                        return nextElement;
                    }
                    if (z && (nextElement instanceof Inet6Address)) {
                        return nextElement;
                    }
                }
            } catch (Exception e) {
            }
            return InetAddress.getLocalHost();
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: a */
    public static NetworkInterface m360a() {
        int i = 0;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                int i2 = i + 1;
                try {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    try {
                        String name = nextElement.getName();
                        if (name.indexOf("eth") != 0 && name.indexOf("wlan") != 0 && name.indexOf("tiwlan") != 0 && name.indexOf("mlan") != 0 && name.indexOf("wifi") != 0 && name.indexOf("wl0") != 0 && name.indexOf("ap0") != 0 && name.indexOf("arc") != 0 && name.indexOf("ra") != 0) {
                            i = i2;
                        } else if (name.indexOf("eth") == -1) {
                            return m363a(nextElement);
                        } else {
                            while (networkInterfaces.hasMoreElements()) {
                                NetworkInterface nextElement2 = networkInterfaces.nextElement();
                                try {
                                    String name2 = nextElement2.getName();
                                    if (name2.indexOf("wlan") == 0 || name2.indexOf("mlan") == 0 || name2.indexOf("tiwlan") == 0 || name2.indexOf("wifi") == 0 || name2.indexOf("ra") == 0) {
                                        return nextElement2;
                                    }
                                } catch (Exception e) {
                                }
                            }
                            return m363a(nextElement);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        i = i2;
                    }
                } catch (Exception e3) {
                    i = i2;
                }
            }
        } catch (Exception e4) {
        }
        if (i == 0) {
            logln(5, "Oops, interface list is empty");
        }
        return null;
    }

    /* renamed from: a */
    static boolean m388a(NetworkInterface networkInterface) {
        try {
            String name = networkInterface.getName();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                String name2 = networkInterfaces.nextElement().getName();
                if (!name2.equalsIgnoreCase(name) && name.substring(0, name.length() - 1).equalsIgnoreCase(name2.substring(0, name2.length() - 1))) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /* renamed from: a */
    private static NetworkInterface m363a(NetworkInterface networkInterface) {
        try {
            String name = networkInterface.getName();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                String name2 = nextElement.getName();
                if (!name2.equalsIgnoreCase(name) && name.substring(0, name.length() - 1).equalsIgnoreCase(name2.substring(0, name2.length() - 1))) {
                    if (m400b(networkInterface)) {
                        return networkInterface;
                    }
                    if (m400b(nextElement)) {
                        return nextElement;
                    }
                }
            }
            return networkInterface;
        } catch (Exception e) {
            e.printStackTrace();
            return networkInterface;
        }
    }

    /* renamed from: b */
    static boolean m400b(NetworkInterface networkInterface) {
        try {
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                if (inetAddresses.nextElement() instanceof Inet4Address) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: a */
    private static NetworkInterface m362a(String str) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    String name = nextElement.getName();
                    if (!name.equalsIgnoreCase(str) && (name.indexOf("eth") == 0 || name.indexOf("wlan") == 0 || name.indexOf("tiwlan") == 0 || name.indexOf("mlan") == 0 || name.indexOf("wifi") == 0 || name.indexOf("wl0") == 0 || name.indexOf("ap0") == 0 || name.indexOf("arc") == 0 || name.indexOf("ra") == 0)) {
                        return nextElement;
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
        return null;
    }

    /* renamed from: a */
    static boolean m387a(InetAddress inetAddress, int i) throws Exception {
        switch (i) {
            case 1:
                if (!(inetAddress instanceof Inet6Address)) {
                    if (f418a == null) {
                        f418a = NMJConfig.m54a(false);
                    }
                    if (((Inet4Address) inetAddress).isAnyLocalAddress()) {
                        return true;
                    }
                    if (((Inet4Address) inetAddress).isLoopbackAddress()) {
                        return true;
                    }
                    if (f425b == null) {
                        f425b = m359a(false).toString();
                    }
                    if (f425b.indexOf("/") != -1) {
                        f425b = f425b.substring(f425b.indexOf("/") + 1);
                    }
                    if (inetAddress.toString().equalsIgnoreCase(f425b) || inetAddress.toString().equalsIgnoreCase(InetAddress.getLocalHost().getHostAddress()) || inetAddress.toString().equalsIgnoreCase("127.0.0.1") || inetAddress.toString().equalsIgnoreCase(f418a)) {
                        return true;
                    }
                } else if (((Inet6Address) inetAddress).isAnyLocalAddress()) {
                    return true;
                } else {
                    if (((Inet6Address) inetAddress).isLoopbackAddress()) {
                        return true;
                    }
                    if (f427c == null) {
                        f427c = m359a(true).toString();
                    }
                    if (f427c.indexOf("/") != -1) {
                        f427c = f427c.substring(f427c.indexOf("/") + 1);
                    }
                    if (inetAddress.toString().equalsIgnoreCase(f427c) || inetAddress.toString().equalsIgnoreCase(Inet6Address.getLocalHost().getHostAddress())) {
                        return true;
                    }
                }
            case 2:
                if (!(inetAddress instanceof Inet6Address)) {
                    if (f428d == null) {
                        Object[] b = m401b(false);
                        if (b != null) {
                            try {
                                f428d = b[1].toString();
                                return inetAddress.toString().equalsIgnoreCase(f428d);
                            } catch (Exception e) {
                                break;
                            }
                        }
                    } else {
                        return inetAddress.toString().equalsIgnoreCase(f428d);
                    }
                } else if (f429e == null) {
                    Object[] b2 = m401b(true);
                    if (b2 != null) {
                        try {
                            f429e = b2[1].toString();
                            return inetAddress.toString().equalsIgnoreCase(f429e);
                        } catch (Exception e2) {
                            break;
                        }
                    }
                } else {
                    return inetAddress.toString().equalsIgnoreCase(f429e);
                }
                break;
            case 64:
                if (!(inetAddress instanceof Inet6Address)) {
                    if (f432h == null) {
                        Object[] a = m359a(false);
                        if (a != null) {
                            try {
                                f432h = a[1].toString();
                                return inetAddress.toString().equalsIgnoreCase(f432h);
                            } catch (Exception e3) {
                                break;
                            }
                        }
                    } else {
                        return inetAddress.toString().equalsIgnoreCase(f432h);
                    }
                } else if (f433i == null) {
                    Object[] c = m404c(true);
                    if (c != null) {
                        try {
                            f433i = c[1].toString();
                            return inetAddress.toString().equalsIgnoreCase(f433i);
                        } catch (Exception e4) {
                            break;
                        }
                    }
                } else {
                    return inetAddress.toString().equalsIgnoreCase(f433i);
                }
                break;
            case 256:
                if (!(inetAddress instanceof Inet6Address)) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        if (f430f == null) {
                            Object[] a2 = m359a(false);
                            if (a2 != null) {
                                try {
                                    f430f = a2[1].toString();
                                    return inetAddress.toString().equalsIgnoreCase(f430f);
                                } catch (Exception e5) {
                                    break;
                                }
                            }
                        } else {
                            return inetAddress.toString().equalsIgnoreCase(f430f);
                        }
                    }
                } else if (Build.VERSION.SDK_INT >= 14) {
                    if (f431g == null) {
                        Object[] a3 = m359a(true);
                        if (a3 != null) {
                            try {
                                f431g = a3[1].toString();
                                return inetAddress.toString().equalsIgnoreCase(f431g);
                            } catch (Exception e6) {
                                break;
                            }
                        }
                    } else {
                        return inetAddress.toString().equalsIgnoreCase(f431g);
                    }
                }
                break;
        }
        return false;
    }

    /* renamed from: a */
    static Object[] m392a(boolean z) {
        if (Build.VERSION.SDK_INT < 14) {
            return null;
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    if (nextElement.getName().indexOf("p2p") != -1) {
                        try {
                            Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                            while (inetAddresses.hasMoreElements()) {
                                InetAddress nextElement2 = inetAddresses.nextElement();
                                if ((nextElement2 instanceof Inet6Address) && z) {
                                    return new Object[]{nextElement, nextElement2};
                                } else if ((nextElement2 instanceof Inet4Address) && !z) {
                                    return new Object[]{nextElement, nextElement2};
                                }
                            }
                            continue;
                        } catch (Exception e) {
                        }
                    } else {
                        continue;
                    }
                } catch (Exception e2) {
                }
            }
        } catch (Exception e3) {
        }
        return null;
    }

    /* renamed from: b */
    private static Object[] m401b(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    String name = nextElement.getName();
                    if (name.indexOf("usb") == 0 || name.indexOf("rndis") != -1) {
                        try {
                            Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                            while (inetAddresses.hasMoreElements()) {
                                InetAddress nextElement2 = inetAddresses.nextElement();
                                if ((nextElement2 instanceof Inet6Address) && z) {
                                    return new Object[]{nextElement, nextElement2};
                                } else if ((nextElement2 instanceof Inet4Address) && !z) {
                                    return new Object[]{nextElement, nextElement2};
                                }
                            }
                            continue;
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                }
            }
        } catch (Exception e3) {
        }
        return null;
    }

    /* renamed from: c */
    private static Object[] m404c(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    String name = nextElement.getName();
                    if (name.indexOf("bt-pan") != -1 || name.indexOf("bnep") != -1) {
                        try {
                            Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                            while (inetAddresses.hasMoreElements()) {
                                InetAddress nextElement2 = inetAddresses.nextElement();
                                if (nextElement2 instanceof Inet6Address) {
                                    return new Object[]{nextElement, nextElement2};
                                }
                            }
                            continue;
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                }
            }
        } catch (Exception e3) {
        }
        return null;
    }

    /* renamed from: a */
    static DatagramSocket m357a(int i, int i2) throws Exception {
        NetworkInterface a;
        Enumeration<InetAddress> enumeration = null;
        if (i2 < 0 || ((i2 > 0 && i2 < 1024) || i2 > 65535)) {
            i2 = (int) (10000.0d + (Math.random() * 1000.0d));
        }
        if (i > 0) {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i3 = 0;
            NetworkInterface networkInterface = null;
            while (true) {
                if (!networkInterfaces.hasMoreElements()) {
                    a = networkInterface;
                    break;
                } else if (i3 >= i) {
                    a = networkInterface;
                    break;
                } else {
                    networkInterface = networkInterfaces.nextElement();
                    i3++;
                }
            }
        } else {
            a = m346a();
        }
        try {
            Enumeration<InetAddress> inetAddresses = a.getInetAddresses();
            InetAddress inetAddress = null;
            while (inetAddresses.hasMoreElements()) {
                try {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof Inet4Address) {
                        break;
                    }
                } catch (NoSuchElementException e) {
                    enumeration = inetAddresses;
                    logln(2, "create ipv4 socket, NoSuchElementException on " + enumeration);
                    return new DatagramSocket(i2);
                }
            }
            InetAddress inetAddress2 = inetAddress;
            if (inetAddress2 != null && (inetAddress2 == null || (inetAddress2 instanceof Inet4Address))) {
                return new DatagramSocket(i2, inetAddress2);
            }
            if (a.getName().indexOf("eth") == 0) {
                NetworkInterface a2 = m346a();
                if (a2 != null && a2.getName().indexOf("eth") < 0) {
                    try {
                        return m358a(a2, i2);
                    } catch (Exception e2) {
                    }
                }
            } else if (m398b(a.getName())) {
                logln(5, "No address on " + a + ", looking for secondary WiFi interface");
                NetworkInterface a3 = m362a(a.getName());
                if (a3 != null) {
                    try {
                        return m358a(a3, i2);
                    } catch (Exception e3) {
                    }
                }
            }
            throw new IOException("Invalid address on " + a + ": " + inetAddress2);
        } catch (NoSuchElementException e4) {
            logln(2, "create ipv4 socket, NoSuchElementException on " + enumeration);
            return new DatagramSocket(i2);
        }
    }

    /* renamed from: a */
    private static DatagramSocket m358a(NetworkInterface networkInterface, int i) throws Exception {
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
            InetAddress nextElement = inetAddresses.nextElement();
            if (nextElement instanceof Inet4Address) {
                return new DatagramSocket(i, nextElement);
            }
        }
        throw new NoSuchElementException();
    }

    /* renamed from: b */
    static void m397b() {
        if (f416a >= 2) {
            try {
                m362a("Network interfaces:");
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    m362a("   " + nextElement.toString());
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        m362a("      " + inetAddresses.nextElement().toString());
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    static String m355a(int i, int i2, int i3) {
        switch (i) {
            case 240:
                String str = "SysEx";
                if (i2 == 1 && i3 == 1) {
                    str = String.valueOf(str) + ": std. full frame";
                }
                if (i2 == 6 && i3 == 68) {
                    str = String.valueOf(str) + ": MMC position";
                }
                if (i2 == 6 && i3 == 1) {
                    str = String.valueOf(str) + ": MMC Stop";
                }
                if (i2 == 6 && i3 == 2) {
                    str = String.valueOf(str) + ": MMC Play";
                }
                if (i2 == 6 && i3 == 3) {
                    str = String.valueOf(str) + ": MMC Deferred Play";
                }
                if (i2 == 6 && i3 == 4) {
                    str = String.valueOf(str) + ": MMC Fast Forward";
                }
                if (i2 == 6 && i3 == 5) {
                    str = String.valueOf(str) + ": MMC Rewind";
                }
                if (i2 == 6 && i3 == 6) {
                    str = String.valueOf(str) + ": MMC Record Strobe (Punch In)";
                }
                if (i2 == 6 && i3 == 7) {
                    str = String.valueOf(str) + ": MMC Record Exit (Punch out)";
                }
                if (i2 == 6 && i3 == 9) {
                    return String.valueOf(str) + ": MMC Pause";
                }
                return str;
            case 241:
                return "MIDI Timecode";
            case 242:
                return "Song Position Pointer";
            case 248:
                return "MIDI Clock";
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /*250*/:
                return "MIDI start";
            case 251:
                return "MIDI continue";
            case 252:
                return "MIDI stop";
            default:
                return m396b(i);
        }
    }

    /* renamed from: b */
    private static String m396b(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 14) {
                return new String("  unknown statusbyte");
            }
            if (i >= f423a[i3] && i < f423a[i3 + 1]) {
                return new String("  " + f424a[i3] + "  Ch." + new Integer((i - f423a[i3]) + 1).toString());
            }
            i2 = i3 + 1;
        }
    }

    /* renamed from: a */
    static boolean m385a(int i, int i2) {
        switch (i) {
            case 240:
                if ((i2 & 2) == 0) {
                    return false;
                }
                return true;
            case 241:
                if ((i2 & 1) == 0) {
                    return false;
                }
                return true;
            case 242:
                if ((i2 & 16) == 0) {
                    return false;
                }
                return true;
            case 248:
                if ((i2 & 8) == 0) {
                    return false;
                }
                return true;
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /*250*/:
            case 251:
            case 252:
                if ((i2 & 4) == 0) {
                    return false;
                }
                return true;
            default:
                if ((i2 & 32) != 0) {
                    return true;
                }
                return false;
        }
    }

    /* renamed from: b */
    static int m395b(byte[] bArr, int i) {
        byte b = bArr[i];
        if ((b & 255) < 240) {
            if ((b & 255) < 192 || (b & 255) >= 224) {
                return 2;
            }
            return 1;
        } else if ((b & 255) > 240) {
            if ((b & 255) == 242) {
                return 2;
            }
            if ((b & 255) == 241 || (b & 255) == 243) {
                return 1;
            }
            return 0;
        } else if ((b & 255) != 240) {
            return -1;
        } else {
            int i2 = i + 1;
            while (i2 < bArr.length && (bArr[i2] & 255) != 247) {
                i2++;
            }
            if (i2 != bArr.length || (bArr[bArr.length - 1] & 255) == 247) {
                return i2 - i;
            }
            return -1;
        }
    }

    /* renamed from: a */
    protected static void m384a(byte[] bArr, Vector<byte[]> vector) {
        int i = 0;
        byte b = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            if ((bArr[i2] & 128) != 0) {
                b = bArr[i2];
                i = m395b(bArr, i2);
                i2++;
            }
            if (i > 0) {
                byte[] bArr2 = new byte[(i + 1)];
                bArr2[0] = (byte) b;
                System.arraycopy(bArr, i2, bArr2, 1, i);
                vector.add(bArr2);
                i2 += i;
            } else if (i == 0) {
                vector.add(new byte[]{(byte) b});
            } else {
                byte[] bArr3 = new byte[(bArr.length - i2)];
                bArr3[0] = (byte) b;
                System.arraycopy(bArr, i2, bArr3, 1, bArr.length - i2);
                vector.add(bArr3);
                return;
            }
        }
    }

    /* renamed from: a */
    static byte[] m391a(int i, int i2) {
        switch (i) {
            case 0:
                byte[] bArr = new byte[11];
                bArr[0] = -16;
                bArr[1] = 125;
                bArr[4] = 109;
                bArr[5] = 110;
                bArr[6] = 101;
                bArr[7] = 116;
                bArr[8] = (byte) i;
                bArr[9] = (byte) i2;
                bArr[10] = -9;
                return bArr;
            case 1:
                byte[] bArr2 = new byte[i2];
                System.arraycopy(f422a, 0, bArr2, 0, 8);
                bArr2[8] = (byte) i;
                bArr2[i2 - 1] = -9;
                return bArr2;
            default:
                return null;
        }
    }

    /* renamed from: a */
    static boolean m389a(Vector<Byte> vector) {
        if (vector.size() < 7) {
            return false;
        }
        int i = 4;
        int i2 = 24;
        int i3 = 0;
        while (true) {
            int i4 = i;
            if (i4 >= Math.min(vector.size(), 8)) {
                return false;
            }
            i3 |= vector.get(i4).byteValue() << i2;
            i2 -= 8;
            if (i4 == 6 && i3 == 7236970) {
                return true;
            }
            if (i4 == 7 && i3 == 1835951476) {
                return true;
            }
            i = i4 + 1;
        }
    }

    /* renamed from: a */
    static boolean m390a(byte[] bArr) {
        if (bArr.length > 8 && m349a(bArr, 4, 4) == 1835951476) {
            return true;
        }
        if (bArr.length < 7 || m349a(bArr, 4, 3) != 7236970) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    static int m347a(byte[] bArr) {
        if (bArr.length > 8 && m349a(bArr, 4, 4) == 1835951476) {
            return (bArr[8] << 8) | bArr[9];
        }
        if (bArr.length < 7 || m349a(bArr, 4, 3) != 7236970) {
            return -1;
        }
        return 0;
    }

    /* renamed from: b */
    static int m394b(byte[] bArr) {
        if (bArr.length > 8 && m349a(bArr, 4, 4) == 1835951476) {
            switch (bArr[8]) {
                case 0:
                    return bArr[9] & 255;
                case 1:
                    return (bArr[10] << 7) | bArr[11];
            }
        } else if (bArr.length >= 7 && m349a(bArr, 4, 3) == 7236970) {
            return bArr[6] & 255;
        }
        return -1;
    }

    /* renamed from: a */
    static String m352a() {
        if (Build.VERSION.SDK_INT < 8) {
            return "NO_BASE64_IN_API<8";
        }
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) ((int) (Math.random() * 256.0d));
        }
        return Base64.encodeToString(bArr, 0).trim();
    }

    /* renamed from: a */
    private static void m366a(byte b) {
        f426b[0] = b;
        if (f417a != null) {
            f417a.print(new String(f426b));
        } else {
            f419a.append(new String(f426b));
        }
        f421a = false;
    }

    /* renamed from: b */
    private static void m398b(String str) {
        f421a = str.indexOf("\n") != -1;
        if (f417a != null) {
            f417a.print(str);
            return;
        }
        f419a.append(str);
        if (f421a) {
            Log.i("nmj", f419a.toString());
            f419a.setLength(0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0007, code lost:
        if (f416a >= r2) goto L_0x0009;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void m368a(int r2, java.lang.String r3) {
        /*
            java.lang.Class<de.humatic.nmj.p> r1 = p004de.humatic.nmj.C0484p.class
            monitor-enter(r1)
            if (r2 < 0) goto L_0x0009
            int r0 = f416a     // Catch:{ all -> 0x000e }
            if (r0 < r2) goto L_0x000c
        L_0x0009:
            m398b((java.lang.String) r3)     // Catch:{ all -> 0x000e }
        L_0x000c:
            monitor-exit(r1)
            return
        L_0x000e:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0484p.m368a(int, java.lang.String):void");
    }

    /* renamed from: a */
    public static synchronized void m379a(String str) {
        synchronized (C0484p.class) {
            if (f417a != null) {
                f417a.println(str);
            } else if (f419a.length() > 0) {
                f419a.append(str);
                Log.i("nmj", f419a.toString());
                f419a.setLength(0);
            } else {
                Log.i("nmj", str);
            }
            f421a = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0007, code lost:
        if (f416a >= r2) goto L_0x0009;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized void logln(int r2, java.lang.String r3) {
        /*
            java.lang.Class<de.humatic.nmj.p> r1 = p004de.humatic.nmj.C0484p.class
            monitor-enter(r1)
            if (r2 < 0) goto L_0x0009
            int r0 = f416a     // Catch:{ all -> 0x000e }
            if (r0 < r2) goto L_0x000c
        L_0x0009:
            m362a((java.lang.String) r3)     // Catch:{ all -> 0x000e }
        L_0x000c:
            monitor-exit(r1)
            return
        L_0x000e:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0484p.logln(int, java.lang.String):void");
    }

    /* renamed from: c */
    public static boolean m403c(String str) {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement className : stackTrace) {
                if (className.getClassName().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static void m377a(Exception exc, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(exc.toString());
        stringBuffer.append("\n");
        StackTraceElement[] stackTrace = exc.getStackTrace();
        int[] iArr = new int[stackTrace.length];
        for (int i = 0; i < Math.min(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, stackTrace.length); i++) {
            stringBuffer.append("at: ");
            stringBuffer.append(stackTrace[i].getClassName());
            stringBuffer.append(".");
            stringBuffer.append(stackTrace[i].getMethodName());
            stringBuffer.append(", line ");
            stringBuffer.append(stackTrace[i].getLineNumber());
            stringBuffer.append("\n");
            iArr[i] = stringBuffer.length();
        }
        Throwable cause = exc.getCause();
        if (cause != null) {
            stringBuffer.setLength(iArr[2]);
            stringBuffer.append("Caused by: ");
            stringBuffer.append(cause.toString());
            stringBuffer.append("\n");
            StackTraceElement[] stackTrace2 = cause.getStackTrace();
            for (int i2 = 0; i2 < Math.min(2147483645, stackTrace2.length); i2++) {
                stringBuffer.append("at: ");
                stringBuffer.append(stackTrace2[i2].getClassName());
                stringBuffer.append(".");
                stringBuffer.append(stackTrace2[i2].getMethodName());
                stringBuffer.append(", line ");
                stringBuffer.append(stackTrace2[i2].getLineNumber());
                stringBuffer.append("\n");
            }
        }
        m362a(stringBuffer.toString());
    }

    /* renamed from: a */
    static void m376a(PrintStream printStream) {
        f417a = printStream;
    }

    /* renamed from: a */
    static void m375a(File file) {
        try {
            if (!new File(file.getParent()).exists()) {
                new File(file.getParent()).mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            f417a = new PrintStream(new FileOutputStream(file, false), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    static void m367a(int i) {
        f416a = i;
    }

    /* renamed from: b */
    static int m393b() {
        return f416a;
    }

    /* renamed from: a */
    static void m378a(Runnable runnable) {
        try {
            if (f420a == null) {
                f420a = Executors.newCachedThreadPool();
            }
            f420a.execute(runnable);
        } catch (Exception e) {
        }
    }

    /* renamed from: c */
    static void m402c() {
        if (f420a != null) {
            try {
                f420a.shutdown();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    static long m351a(byte[] bArr, int i) {
        long j = 0;
        int i2 = 7;
        int i3 = i;
        while (i3 < i + 8) {
            j |= ((long) (bArr[i3] & 255)) << (i2 << 3);
            i3++;
            i2--;
        }
        return j;
    }

    /* renamed from: a */
    public static void m369a(int i, String str, int i2, int i3, int i4) {
        if (f416a >= 4) {
            m398b(str);
            switch (i3) {
                case -1:
                    m362a("ch " + i2 + " REMOVED");
                    return;
                case 1:
                    m362a("ch " + i2 + " OPENED");
                    return;
                case 2:
                    m362a("ch " + i2 + " CLOSED");
                    return;
                case 4:
                    switch (i4) {
                        case 4:
                            m362a("ch " + i2 + " WAITING");
                            return;
                        case 8:
                            m362a("ch " + i2 + " DISCOVERED");
                            return;
                        case 16:
                            m362a("ch " + i2 + " PRESENT");
                            return;
                        case 32:
                            m362a("ch " + i2 + " CONNECTED");
                            return;
                        case 64:
                            m362a("ch " + i2 + " CLIENT_CONNECTED");
                            return;
                        case 128:
                            m362a("ch " + i2 + " DISCONNECTED");
                            return;
                        case 256:
                            m362a("ch " + i2 + " CLIENT_DISCONNECTED");
                            return;
                        case 512:
                            m362a("ch " + i2 + " CH_GONE");
                            return;
                        case 1024:
                            m362a("ch " + i2 + " CH_LOST");
                            return;
                        case 2048:
                            m362a("ch " + i2 + " CONNECTION_REFUSED");
                            return;
                        case 4096:
                            m362a("ch " + i2 + " NO_RESPONSE");
                            return;
                        case 8192:
                            m362a("ch " + i2 + " PKT_LOSS");
                            return;
                        case 16384:
                            m362a("ch " + i2 + " REMOTE_ERR");
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }
}
