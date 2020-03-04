package p004de.humatic.nmj;

import android.net.wifi.WifiManager;
import android.os.Looper;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/* renamed from: de.humatic.nmj.n */
final class C0479n {

    /* renamed from: a */
    private int f365a;

    /* renamed from: a */
    private long f366a;

    /* renamed from: a */
    private WifiManager.MulticastLock f367a;

    /* renamed from: a */
    private C0503w f368a;

    /* renamed from: a */
    private DatagramPacket f369a;

    /* renamed from: a */
    DatagramSocket f370a;

    /* renamed from: a */
    private InetAddress f371a;

    /* renamed from: a */
    private InetSocketAddress f372a;

    /* renamed from: a */
    private MulticastSocket f373a;

    /* renamed from: a */
    private NetworkInterface f374a;

    /* renamed from: a */
    private Vector<Byte> f375a = new Vector<>();
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f376a = true;

    /* renamed from: a */
    private byte[] f377a = new byte[70];

    /* renamed from: a */
    private byte[][] f378a = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 1}));

    /* renamed from: a */
    private byte[][][] f379a = ((byte[][][]) Array.newInstance(byte[].class, new int[]{70, 10}));

    /* renamed from: b */
    private int f380b;

    /* renamed from: b */
    private DatagramPacket f381b;

    /* renamed from: b */
    private DatagramSocket f382b;

    /* renamed from: b */
    private Vector<NetworkMidiListener> f383b = new Vector<>();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f384b;

    /* renamed from: b */
    private byte[] f385b;

    /* renamed from: b */
    private byte[][] f386b = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 2}));

    /* renamed from: c */
    private int f387c;

    /* renamed from: c */
    private boolean f388c;

    /* renamed from: c */
    private byte[] f389c;

    /* renamed from: c */
    private byte[][] f390c = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{64, 3}));

    /* renamed from: d */
    private int f391d = 4031;

    /* renamed from: d */
    private boolean f392d;

    /* renamed from: d */
    private byte[] f393d = new byte[256];

    /* renamed from: e */
    private int f394e;

    /* renamed from: e */
    private boolean f395e;

    /* renamed from: f */
    private int f396f;

    /* renamed from: g */
    private int f397g;

    /* renamed from: h */
    private int f398h;

    /* renamed from: i */
    private int f399i;

    /* renamed from: j */
    private int f400j;

    C0479n(int i) throws Exception {
        C0503w a;
        boolean z = true;
        this.f365a = i;
        this.f380b = NMJConfig.getIO(this.f365a);
        this.f387c = NMJConfig.getMode(this.f365a);
        this.f388c = false;
        if (this.f387c <= 0 || this.f387c == 3) {
            String ip = NMJConfig.getIP(this.f365a);
            int port = NMJConfig.getPort(this.f365a);
            if (!(ip == null || ip.indexOf(":") == -1)) {
                port = Integer.parseInt(ip.split(":")[1]);
                ip = ip.split(":")[0];
            }
            this.f391d = NMJConfig.getLocalPort(this.f365a);
            int networkAdapter = NMJConfig.getNetworkAdapter(this.f365a);
            if (this.f387c == 0) {
                this.f384b = true;
                try {
                    this.f384b = new Integer(ip.substring(0, ip.indexOf("."))).intValue() < 223 ? false : z;
                } catch (Exception e) {
                }
                if (this.f384b) {
                    m330b(networkAdapter, port, ip);
                } else {
                    m326a(networkAdapter, port, ip);
                }
            } else if (this.f387c == 3) {
                this.f391d = 9004;
                m326a(networkAdapter, 9000, ip);
            }
        } else {
            int i2 = this.f387c;
            int i3 = this.f365a;
            switch (i2) {
                case 1:
                    a = new C0495t(i3, this);
                    break;
                case 2:
                    a = new C0456c(i3, this);
                    break;
                case 4:
                    a = new ADBMidiIO(i3, this);
                    break;
                case 5:
                    a = new C0504x(i3, this);
                    break;
                case 6:
                    a = new C0420A(i3, this);
                    break;
                case 7:
                    a = new C0510y(i3, this);
                    break;
                default:
                    a = null;
                    break;
            }
            this.f368a = a;
        }
    }

    /* renamed from: a */
    private void m326a(int i, int i2, String str) throws Exception {
        if (this.f370a == null && this.f382b == null) {
            if (this.f380b == 1) {
                this.f382b = C0484p.m357a(i, -1);
            } else if (this.f380b == 0) {
                this.f370a = C0484p.m357a(i, this.f391d);
            } else if (this.f380b < 0) {
                this.f382b = C0484p.m357a(i, -1);
                this.f370a = C0484p.m357a(i, this.f391d);
            }
            this.f369a = new DatagramPacket(new byte[1500], 1500);
            if (Math.abs(this.f380b) == 1) {
                this.f381b = new DatagramPacket(new byte[1500], 1500, InetAddress.getByName(str), i2);
            }
            if (this.f380b > 0 || this.f387c != 3) {
                C0484p.logln(1, "NMJClient " + this.f365a + " up and running on port " + this.f391d);
            } else {
                this.f370a.setBroadcast(true);
                this.f370a.connect(new InetSocketAddress(9002));
                C0484p.logln(1, "NMJClient " + this.f365a + " connected to " + this.f370a.getRemoteSocketAddress() + " broadcast enabled: " + this.f370a.getBroadcast());
            }
            C0484p.logln(2, "NMJClient " + this.f365a + " up and running on port " + this.f391d);
            if (Math.abs(this.f380b) == 1) {
                C0484p.logln(2, "NMJClient output " + this.f365a + " connected to " + InetAddress.getByName(str) + ":" + i2);
            }
            NMJConfig.m59a(this.f365a, 1, 0);
            new Thread(new C0481a(this, (byte) 0)).start();
        }
    }

    /* renamed from: b */
    private void m330b(int i, int i2, String str) throws Exception {
        C0484p.logln(2, "opening multicast " + i);
        this.f373a = new MulticastSocket(i2);
        this.f373a.setTimeToLive(255);
        if (i > 0) {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i3 = 0;
            while (networkInterfaces.hasMoreElements() && i3 < i) {
                this.f374a = networkInterfaces.nextElement();
                i3++;
            }
        } else {
            this.f374a = C0484p.m346a();
        }
        if (this.f374a == null) {
            throw new IOException("Failed to get interface " + (i - 1));
        }
        try {
            Enumeration<InetAddress> inetAddresses = this.f374a.getInetAddresses();
            InetAddress inetAddress = null;
            while (inetAddresses.hasMoreElements()) {
                inetAddress = inetAddresses.nextElement();
                boolean z = NMJConfig.f67a;
                if (inetAddress instanceof Inet4Address) {
                    break;
                }
            }
            boolean z2 = NMJConfig.f67a;
            if (inetAddress == null || !(inetAddress instanceof Inet6Address)) {
                this.f373a.setNetworkInterface(this.f374a);
                if (inetAddress == null) {
                    throw new IOException("No valid addresses on " + this.f374a);
                }
                this.f373a.setInterface(inetAddress);
                this.f371a = InetAddress.getByName(str);
                this.f367a = NetworkMidiSystem.m198a().createMulticastLock("nmj_multicast_lock_" + this.f365a);
                this.f367a.setReferenceCounted(true);
                this.f367a.acquire();
                C0484p.logln(2, "aquired multicast lock " + this.f367a);
                this.f372a = new InetSocketAddress(this.f371a, 0);
                this.f373a.joinGroup(this.f372a, this.f374a);
                C0484p.logln(2, "NMJClient " + this.f365a + " up and running on: " + this.f374a + " port " + i2 + " joined group at " + this.f371a);
                NMJConfig.m59a(this.f365a, 1, 0);
                this.f369a = new DatagramPacket(new byte[1500], 1500);
                new Thread(new C0481a(this, (byte) 0)).start();
                return;
            }
            throw new IOException("Multicast client: No valid addresses on interface.");
        } catch (NoSuchElementException e) {
            throw new IOException("No valid addresses on " + this.f374a);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo8150a(NetworkMidiListener networkMidiListener) {
        if (!this.f383b.contains(networkMidiListener)) {
            this.f383b.add(networkMidiListener);
        }
        return this.f383b.size();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo8157b(NetworkMidiListener networkMidiListener) {
        if (this.f383b.contains(networkMidiListener)) {
            this.f383b.remove(networkMidiListener);
        }
        return this.f383b.size();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8152a(int i) {
        this.f365a = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m334a() {
        try {
            if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                new Thread(new Runnable() {
                    public final void run() {
                        C0479n.this.mo8151a();
                    }
                }).start();
                return;
            }
        } catch (Exception e) {
        }
        this.f376a = false;
        if (!this.f388c) {
            try {
                if (this.f387c == 0 || this.f387c == 3) {
                    try {
                        if (this.f387c == 3 || !this.f384b) {
                            try {
                                this.f382b.close();
                            } catch (Exception e2) {
                            }
                            try {
                                this.f370a.close();
                            } catch (Exception e3) {
                            }
                            this.f382b = null;
                            this.f370a = null;
                        } else {
                            if (this.f367a != null) {
                                try {
                                    this.f367a.release();
                                    this.f367a = null;
                                } catch (Exception e4) {
                                }
                            }
                            this.f373a.leaveGroup(this.f372a, this.f374a);
                            this.f373a.close();
                        }
                    } catch (Exception e5) {
                        C0484p.logln(1, "NMJClient.close(): " + e5.getMessage());
                    }
                } else if (this.f368a != null) {
                    this.f368a.mo8031a();
                }
                this.f383b.removeAllElements();
                NMJConfig.m59a(this.f365a, 2, 0);
                this.f388c = true;
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        }
    }

    /* renamed from: de.humatic.nmj.n$a */
    class C0481a extends Thread {
        private C0481a() {
            C0479n.this.f376a = true;
        }

        /* synthetic */ C0481a(C0479n nVar, byte b) {
            this();
        }

        public final void run() {
            while (C0479n.m323a(C0479n.this)) {
                try {
                    C0479n.m323a(C0479n.this).setLength(1500);
                    if (C0479n.this.f384b) {
                        C0479n.m323a(C0479n.this).receive(C0479n.m323a(C0479n.this));
                    } else {
                        C0479n.this.f370a.receive(C0479n.m323a(C0479n.this));
                    }
                    C0479n.this.mo8155a(C0479n.m323a(C0479n.this).getData(), C0479n.m323a(C0479n.this).getLength());
                } catch (Exception e) {
                    String exc = e.toString();
                    if (exc.indexOf("closed") == -1 && exc.indexOf("system call was cancelled") == -1 && exc.indexOf("Interrupted system call") == -1 && exc.indexOf("setsockopt failed: ENODEV") == -1 && exc.indexOf("socket argument is not a valid") == -1) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8155a(byte[] bArr, int i) {
        mo8156a(bArr, 0, i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8156a(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        this.f366a = System.currentTimeMillis();
        while (i < i2) {
            if ((bArr[i] & 128) != 0) {
                if ((bArr[i] & 255) == 240) {
                    this.f392d = true;
                    this.f395e = false;
                    m329b(bArr[i], this.f366a);
                } else if ((bArr[i] & 255) == 247) {
                    if (this.f392d) {
                        m329b(bArr[i], this.f366a);
                    }
                    this.f392d = false;
                    this.f395e = false;
                } else {
                    this.f399i = C0484p.m395b(bArr, i) + 1;
                    switch (this.f399i) {
                        case 1:
                            this.f394e++;
                            if (this.f394e >= this.f378a.length) {
                                this.f394e = 0;
                            }
                            bArr2 = this.f378a[this.f394e];
                            break;
                        case 2:
                            this.f396f++;
                            if (this.f396f >= this.f386b.length) {
                                this.f396f = 0;
                            }
                            bArr2 = this.f386b[this.f396f];
                            break;
                        case 3:
                            this.f397g++;
                            if (this.f397g >= this.f390c.length) {
                                this.f397g = 0;
                            }
                            bArr2 = this.f390c[this.f397g];
                            break;
                        default:
                            bArr2 = new byte[3];
                            break;
                    }
                    this.f389c = bArr2;
                    this.f398h = 0;
                    m325a(bArr[i], this.f366a);
                    this.f398h = 1;
                }
                i++;
            } else {
                if (this.f392d) {
                    m329b(bArr[i], this.f366a);
                } else {
                    m325a(bArr[i], this.f366a);
                }
                i++;
            }
        }
    }

    /* renamed from: a */
    private void m325a(byte b, long j) {
        if (this.f389c != null && this.f398h < this.f389c.length) {
            this.f389c[this.f398h] = b;
            this.f398h++;
            if (this.f398h == this.f399i) {
                mo8153a(this.f365a, -1, this.f389c, j);
                this.f398h = 1;
            }
        }
    }

    /* renamed from: b */
    private void m329b(byte b, long j) {
        byte[] bArr;
        boolean z = (b & 255) == 247;
        if (this.f395e) {
            byte[] bArr2 = this.f393d;
            int i = this.f400j;
            this.f400j = i + 1;
            bArr2[i] = b;
            if (z) {
                if (this.f368a != null && (this.f393d[0] & 255) == 240 && C0484p.m347a(this.f393d)) {
                    C0484p.logln(3, "mnet SysEx assembled, length " + this.f400j + " type " + (C0484p.m347a(this.f393d) >> 8));
                    if ((C0484p.m347a(this.f393d) >> 8) == 1) {
                        int max = Math.max(this.f368a.f576d, C0484p.m394b(this.f393d));
                        if (max != this.f368a.f576d) {
                            C0484p.logln(1, "client - maxPktSize " + max);
                        }
                        this.f368a.f576d = max;
                    } else if ((C0484p.m347a(this.f393d) >> 8) == 0) {
                        int b2 = C0484p.m394b(this.f393d);
                        if (b2 == 0) {
                            C0484p.logln(3, "assembled - New " + (this.f387c == 2 ? "bluettoth" : "adb") + " connection");
                            NMJConfig.m59a(this.f365a, 4, 32);
                        } else if (b2 == 1) {
                            C0484p.logln(3, "assembled - Connection closed by client");
                            this.f368a.mo8114b();
                        }
                    }
                }
                for (int i2 = 0; i2 < this.f400j; i2++) {
                    this.f393d[i2] = 0;
                }
                this.f375a.removeAllElements();
                return;
            }
            return;
        }
        this.f375a.add(new Byte(b));
        if (this.f375a.size() == 8 && C0484p.m389a(this.f375a)) {
            for (int i3 = 0; i3 < 8; i3++) {
                this.f393d[i3] = this.f375a.get(i3).byteValue();
            }
            this.f400j = 8;
            this.f395e = true;
        }
        if (z) {
            if (this.f375a.size() < this.f379a.length) {
                int size = this.f375a.size();
                byte[] bArr3 = this.f377a;
                bArr3[size] = (byte) (bArr3[size] + 1);
                if (this.f377a[size] > 9) {
                    this.f377a[size] = 0;
                }
                if (this.f379a[size][this.f377a[size]] == null) {
                    this.f379a[size][this.f377a[size]] = new byte[size];
                }
                bArr = this.f379a[size][this.f377a[size]];
            } else {
                bArr = new byte[this.f375a.size()];
            }
            for (int i4 = 0; i4 < this.f375a.size(); i4++) {
                bArr[i4] = this.f375a.get(i4).byteValue();
            }
            this.f375a.removeAllElements();
            mo8153a(this.f365a, -1, bArr, j);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8153a(int i, int i2, byte[] bArr, long j) {
        Iterator<NetworkMidiListener> it = this.f383b.iterator();
        while (it.hasNext()) {
            it.next().midiReceived(i, i2, bArr, j);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8154a(byte[] bArr) throws Exception {
        if (this.f376a) {
            if (this.f387c == 0 || this.f387c == 3) {
                if (bArr.length < 1024) {
                    this.f381b.setData(bArr, 0, bArr.length);
                    this.f382b.send(this.f381b);
                    return;
                }
                if (this.f385b == null) {
                    this.f385b = new byte[1024];
                }
                int i = 0;
                while (i < bArr.length) {
                    int min = Math.min(1024, bArr.length - i);
                    System.arraycopy(bArr, i, this.f385b, 0, min);
                    this.f381b.setData(this.f385b, 0, min);
                    this.f382b.send(this.f381b);
                    try {
                        Thread.currentThread();
                        Thread.sleep(15);
                    } catch (Exception e) {
                    }
                    i += min;
                }
            } else if (this.f368a != null) {
                this.f368a.mo8034a(bArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final C0503w mo8151a() {
        return this.f368a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo8158b() {
        if (this.f368a != null) {
            this.f368a.mo8199d();
        }
    }
}
