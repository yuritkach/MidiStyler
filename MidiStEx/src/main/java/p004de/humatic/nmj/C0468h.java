package p004de.humatic.nmj;

import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.Vector;

/* renamed from: de.humatic.nmj.h */
final class C0468h {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public int f284a = -1;

    /* renamed from: a */
    private long f285a;

    /* renamed from: a */
    private C0472d f286a;

    /* renamed from: a */
    private C0474j f287a;

    /* renamed from: a */
    private String f288a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public DatagramPacket f289a;

    /* renamed from: a */
    private MulticastSocket f290a;

    /* renamed from: a */
    private NetworkInterface f291a;

    /* renamed from: a */
    private SocketAddress f292a;

    /* renamed from: a */
    private Vector f293a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f294a = true;

    /* renamed from: a */
    private byte[] f295a;

    /* renamed from: a */
    private C0471c[] f296a;

    /* renamed from: a */
    private String[] f297a;

    /* renamed from: b */
    private int f298b = 5;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public long f299b;

    /* renamed from: b */
    private String f300b;

    /* renamed from: b */
    private DatagramPacket f301b;

    /* renamed from: b */
    private MulticastSocket f302b;

    /* renamed from: b */
    private SocketAddress f303b;

    /* renamed from: b */
    private Vector<C0467g> f304b;

    /* renamed from: b */
    private boolean f305b = true;

    /* renamed from: b */
    private byte[] f306b;

    /* renamed from: c */
    private int f307c = 12;

    /* renamed from: c */
    private long f308c;

    /* renamed from: c */
    private String f309c;

    /* renamed from: c */
    private DatagramPacket f310c;

    /* renamed from: c */
    private Vector<String> f311c;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public boolean f312c;

    /* renamed from: c */
    private byte[] f313c;

    /* renamed from: d */
    private int f314d = -1;

    /* renamed from: d */
    private String f315d;

    /* renamed from: d */
    private Vector<C0467g> f316d;

    /* renamed from: d */
    private boolean f317d;

    /* renamed from: d */
    private byte[] f318d;

    /* renamed from: e */
    private int f319e = 1;

    /* renamed from: e */
    private String f320e = "WIFI";
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f321e;

    /* renamed from: f */
    private int f322f = -1;

    /* renamed from: f */
    private String f323f = "";

    /* renamed from: f */
    private boolean f324f = true;

    /* renamed from: g */
    private boolean f325g = false;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean f326h;

    /* renamed from: i */
    private boolean f327i;

    /* JADX WARNING: Removed duplicated region for block: B:27:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0292  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    C0468h(p004de.humatic.nmj.C0474j r10, int r11) throws java.lang.Exception {
        /*
            r9 = this;
            r8 = 1500(0x5dc, float:2.102E-42)
            r7 = 2
            r2 = 0
            r1 = 1
            r6 = -1
            r9.<init>()
            r9.f294a = r1
            r9.f305b = r1
            r9.f324f = r1
            r9.f325g = r2
            r9.f284a = r6
            r0 = 5
            r9.f298b = r0
            r0 = 12
            r9.f307c = r0
            r9.f314d = r6
            r9.f319e = r1
            r9.f322f = r6
            java.lang.String r0 = "WIFI"
            r9.f320e = r0
            java.lang.String r0 = ""
            r9.f323f = r0
            byte[] r0 = new byte[r7]
            r3 = 64
            r0[r1] = r3
            r9.f313c = r0
            r0 = 1460(0x5b4, float:2.046E-42)
            byte[] r0 = new byte[r0]
            r9.f318d = r0
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r9.f293a = r0
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r9.f304b = r0
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r9.f311c = r0
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r9.f316d = r0
            r9.f287a = r10
            int r0 = p004de.humatic.nmj.C0484p.m393b()
            r0 = r0 & 32
            if (r0 == 0) goto L_0x0098
            r0 = r1
        L_0x005d:
            r9.f321e = r0
            java.util.Vector<java.lang.String> r0 = r9.f311c
            java.lang.String r3 = "_apple-midi"
            r0.add(r3)
            switch(r11) {
                case 1: goto L_0x009a;
                case 2: goto L_0x00a1;
                case 64: goto L_0x00c2;
                case 256: goto L_0x00ac;
                default: goto L_0x0069;
            }
        L_0x0069:
            java.net.NetworkInterface r0 = r9.f291a
            if (r0 == 0) goto L_0x007b
            java.net.NetworkInterface r0 = r9.f291a
            java.lang.String r0 = r0.getName()
            java.lang.String r3 = "lo"
            int r0 = r0.indexOf(r3)
            if (r0 == r6) goto L_0x00cf
        L_0x007b:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "DNS ("
            r1.<init>(r2)
            java.lang.String r2 = r9.f320e
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ") - No suitable interface found"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0098:
            r0 = r2
            goto L_0x005d
        L_0x009a:
            java.net.NetworkInterface r0 = p004de.humatic.nmj.C0484p.m354a((int) r1)
            r9.f291a = r0
            goto L_0x0069
        L_0x00a1:
            java.net.NetworkInterface r0 = p004de.humatic.nmj.C0484p.m354a((int) r7)
            r9.f291a = r0
            java.lang.String r0 = "USB"
            r9.f320e = r0
            goto L_0x0069
        L_0x00ac:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 14
            if (r0 >= r3) goto L_0x00b3
        L_0x00b2:
            return
        L_0x00b3:
            java.lang.Object[] r0 = p004de.humatic.nmj.C0484p.m359a((boolean) r2)
            r0 = r0[r2]
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0
            r9.f291a = r0
            java.lang.String r0 = "P2P"
            r9.f320e = r0
            goto L_0x0069
        L_0x00c2:
            r0 = 64
            java.net.NetworkInterface r0 = p004de.humatic.nmj.C0484p.m354a((int) r0)
            r9.f291a = r0
            java.lang.String r0 = "BT-pan"
            r9.f320e = r0
            goto L_0x0069
        L_0x00cf:
            r9.f319e = r11
            r0 = 3
            r9.m297c((int) r0)
            r0 = 0
            boolean r3 = r9.f324f
            if (r3 == 0) goto L_0x00b2
            java.net.DatagramPacket r3 = new java.net.DatagramPacket
            byte[] r4 = new byte[r8]
            r3.<init>(r4, r8)
            r9.f289a = r3
            java.net.MulticastSocket r3 = new java.net.MulticastSocket
            r4 = 5353(0x14e9, float:7.501E-42)
            r3.<init>(r4)
            r9.f290a = r3
            java.net.MulticastSocket r3 = r9.f290a
            java.net.NetworkInterface r4 = r9.f291a
            r3.setNetworkInterface(r4)
            int r3 = p004de.humatic.nmj.NMJConfig.getFlags(r6)
            r3 = r3 & 4
            if (r3 != 0) goto L_0x0100
            java.net.MulticastSocket r3 = r9.f290a
            r3.setLoopbackMode(r1)
        L_0x0100:
            java.net.NetworkInterface r3 = r9.f291a
            java.util.Enumeration r3 = r3.getInetAddresses()
        L_0x0106:
            boolean r4 = r3.hasMoreElements()
            if (r4 != 0) goto L_0x0216
        L_0x010c:
            if (r0 != 0) goto L_0x024b
            java.net.NetworkInterface r3 = r9.f291a
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "[/192"
            int r4 = r3.indexOf(r4)
            if (r4 == r6) goto L_0x0222
            java.lang.String r4 = "[/"
            int r4 = r3.lastIndexOf(r4)
            int r4 = r4 + 2
            int r5 = r3.length()
            int r5 = r5 + -1
            java.lang.String r3 = r3.substring(r4, r5)
            r9.f315d = r3
        L_0x0130:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "DNS ("
            r3.<init>(r4)
            java.lang.String r4 = r9.f320e
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = "), starting w/ "
            java.lang.StringBuilder r3 = r3.append(r4)
            de.humatic.nmj.h$c[] r4 = r9.f296a
            int r4 = r4.length
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = " sessions"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            p004de.humatic.nmj.C0484p.logln(r7, r3)
            java.lang.String r3 = r9.f315d
            java.lang.String r4 = "/"
            int r3 = r3.indexOf(r4)
            if (r3 == r6) goto L_0x0173
            java.lang.String r3 = r9.f315d
            java.lang.String r4 = r9.f315d
            java.lang.String r5 = "/"
            int r4 = r4.indexOf(r5)
            int r4 = r4 + 1
            java.lang.String r3 = r3.substring(r4)
            r9.f315d = r3
        L_0x0173:
            java.lang.String r3 = r9.f315d
            java.lang.String r4 = "10.0.2.15"
            int r3 = r3.indexOf(r4)
            if (r3 >= 0) goto L_0x0292
            byte[] r0 = r0.getAddress()
            r9.f295a = r0
        L_0x0183:
            java.lang.String r0 = p004de.humatic.nmj.NMJConfig.m39a((int) r6)
            r9.f288a = r0
            java.lang.String r0 = r9.f288a
            if (r0 != 0) goto L_0x0199
            java.lang.String r0 = r9.f315d
            java.lang.String r1 = "."
            java.lang.String r3 = "_"
            java.lang.String r0 = r0.replace(r1, r3)
            r9.f288a = r0
        L_0x0199:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "DNS ("
            r0.<init>(r1)
            java.lang.String r1 = r9.f320e
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "), using interface: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.net.NetworkInterface r1 = r9.f291a
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r9.f315d
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " name: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r9.f288a
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            p004de.humatic.nmj.C0484p.logln(r7, r0)
            java.net.MulticastSocket r0 = r9.f290a
            r1 = 255(0xff, float:3.57E-43)
            r0.setTimeToLive(r1)
            android.net.wifi.WifiManager$MulticastLock r0 = p004de.humatic.nmj.NetworkMidiSystem.m198a()     // Catch:{ Exception -> 0x02a0 }
            r0.acquire()     // Catch:{ Exception -> 0x02a0 }
        L_0x01df:
            java.lang.String r0 = "224.0.0.251"
            java.net.InetAddress r0 = java.net.InetAddress.getByName(r0)
            java.net.DatagramPacket r1 = new java.net.DatagramPacket
            byte[] r3 = new byte[r8]
            r1.<init>(r3, r8)
            r9.f289a = r1
            java.net.DatagramPacket r1 = new java.net.DatagramPacket
            r3 = 512(0x200, float:7.175E-43)
            byte[] r3 = new byte[r3]
            r4 = 512(0x200, float:7.175E-43)
            r5 = 5353(0x14e9, float:7.501E-42)
            r1.<init>(r3, r4, r0, r5)
            r9.f301b = r1
            java.net.InetSocketAddress r1 = new java.net.InetSocketAddress
            r1.<init>(r0, r2)
            r9.f292a = r1
            java.net.MulticastSocket r0 = r9.f290a
            java.net.SocketAddress r1 = r9.f292a
            java.net.NetworkInterface r2 = r9.f291a
            r0.joinGroup(r1, r2)
            java.net.MulticastSocket r0 = r9.f290a
            r1 = 5000(0x1388, float:7.006E-42)
            r0.setSoTimeout(r1)
            goto L_0x00b2
        L_0x0216:
            java.lang.Object r0 = r3.nextElement()
            java.net.InetAddress r0 = (java.net.InetAddress) r0
            boolean r4 = r0 instanceof java.net.Inet4Address
            if (r4 != 0) goto L_0x010c
            goto L_0x0106
        L_0x0222:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "DNS ("
            r1.<init>(r2)
            java.lang.String r2 = r9.f320e
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ") Failed to get link local address on "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.net.NetworkInterface r2 = r9.f291a
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ". Check WIFI state."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x024b:
            boolean r3 = p004de.humatic.nmj.NMJConfig.f67a
            boolean r3 = r0 instanceof java.net.Inet6Address
            if (r3 == 0) goto L_0x026e
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "No valid addresses on local interface "
            r1.<init>(r2)
            java.net.NetworkInterface r2 = r9.f291a
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ". Check WIFI state."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x026e:
            java.lang.String r3 = r0.toString()
            r9.f315d = r3
            java.lang.String r3 = r9.f315d
            java.lang.String r4 = "/"
            int r3 = r3.indexOf(r4)
            if (r3 == r6) goto L_0x0130
            java.lang.String r3 = r9.f315d
            java.lang.String r4 = r9.f315d
            java.lang.String r5 = "/"
            int r4 = r4.indexOf(r5)
            int r4 = r4 + 1
            java.lang.String r3 = r3.substring(r4)
            r9.f315d = r3
            goto L_0x0130
        L_0x0292:
            r0 = 4
            byte[] r0 = new byte[r0]
            r3 = 127(0x7f, float:1.78E-43)
            r0[r2] = r3
            r3 = 3
            r0[r3] = r1
            r9.f295a = r0
            goto L_0x0183
        L_0x02a0:
            r0 = move-exception
            goto L_0x01df
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0468h.<init>(de.humatic.nmj.j, int):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8129a() {
        boolean z;
        this.f312c = false;
        this.f305b = (NMJConfig.getFlags(-1) & 1) != 0;
        if ((NMJConfig.getFlags(-1) & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.f294a = z;
        if (!this.f317d) {
            if (!this.f305b || !this.f294a) {
                C0484p.logln(2, "DNS - discover: " + this.f305b + ", announce: " + this.f294a);
            }
            if (this.f324f) {
                new Thread(new C0470b(this, (byte) 0)).start();
            }
            this.f286a = new C0472d(this, (byte) 0);
            if (this.f294a) {
                new Thread(new C0469a(this, (byte) 0)).start();
            } else {
                try {
                    byte[] a = m282a("_apple-midi", "_udp", "local");
                    m262a(a, a.length);
                } catch (Exception e) {
                }
            }
        } else {
            this.f284a = 0;
        }
        this.f317d = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo8133b() {
        this.f312c = true;
        try {
            this.f286a.mo8139a();
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo8135c() {
        for (int i = 0; i < this.f296a.length; i++) {
            try {
                mo8130a(this.f296a[i].f330a);
            } catch (Exception e) {
            }
        }
        mo8133b();
        this.f317d = false;
        try {
            this.f290a.leaveGroup(this.f292a, this.f291a);
        } catch (Exception e2) {
        }
        try {
            this.f290a.close();
        } catch (Exception e3) {
        }
        try {
            this.f302b.leaveGroup(this.f303b, this.f291a);
        } catch (Exception e4) {
        }
        try {
            this.f302b.close();
        } catch (Exception e5) {
        }
        C0484p.logln(2, "DNS (" + this.f320e + ") closed");
        NMJConfig.m39a(this.f319e);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8131a(int i, int i2) {
        try {
            m297c(3);
        } catch (Exception e) {
        }
        if (this.f284a < 4) {
            this.f284a = 0;
            return;
        }
        this.f284a = 0;
        this.f286a.mo8139a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8130a(int i) {
        String name;
        String str = "_apple-midi";
        String str2 = "_udp";
        if (NMJConfig.getMode(i) == 6) {
            str = "_ws-midi";
            str2 = "_tcp";
        }
        if (NMJConfig.getIP(i) != null) {
            name = NMJConfig.m39a(i);
        } else {
            name = NMJConfig.getName(i);
        }
        try {
            byte[] a = m283a(str, str2, "local", name);
            if (this.f324f) {
                this.f301b.setData(a, 0, a.length);
                this.f290a.send(this.f301b);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: de.humatic.nmj.h$a */
    class C0469a extends Thread {
        private C0469a() {
        }

        /* synthetic */ C0469a(C0468h hVar, byte b) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a2, code lost:
            if ((java.lang.System.currentTimeMillis() - p004de.humatic.nmj.C0468h.m247a(r17.f328a)) > ((long) (p004de.humatic.nmj.C0468h.m286b(r17.f328a) ? 180000 : 600000))) goto L_0x00a4;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r17 = this;
                r16 = 8
                r10 = 1
                r15 = -1
                r14 = 3
                r9 = 0
            L_0x0006:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this
                boolean r2 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r2)
                if (r2 == 0) goto L_0x0011
                return
            L_0x0011:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r2 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                if (r2 == r15) goto L_0x0029
                r3 = r9
            L_0x001c:
                if (r3 < r14) goto L_0x0159
                r3 = r9
            L_0x001f:
                if (r3 < r14) goto L_0x017f
                java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x0273 }
                r2 = 500(0x1f4, double:2.47E-321)
                java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x0273 }
            L_0x0029:
                r0 = r17
                de.humatic.nmj.h r3 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r2 = p004de.humatic.nmj.C0484p.m393b()     // Catch:{ Exception -> 0x01b4 }
                r2 = r2 & 32
                if (r2 == 0) goto L_0x0204
                r2 = r10
            L_0x0036:
                r3.f321e = r2     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r3 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                int r3 = r3 + 1
                r2.f284a = r3     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r2 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                if (r2 >= r14) goto L_0x007a
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r3 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                java.lang.String r4 = "_apple-midi"
                java.lang.String r5 = "_udp"
                java.lang.String r6 = "local"
                byte[] r3 = p004de.humatic.nmj.C0468h.m282a((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x01b4 }
                r2.m262a((byte[]) r3, r3.length)     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r3 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                java.lang.String r4 = "_ws-midi"
                java.lang.String r5 = "_tcp"
                java.lang.String r6 = "local"
                byte[] r3 = p004de.humatic.nmj.C0468h.m282a((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x01b4 }
                r2.m262a((byte[]) r3, r3.length)     // Catch:{ Exception -> 0x01b4 }
            L_0x007a:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r2 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                if (r2 < r14) goto L_0x00a4
                long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r4 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                long r4 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r4)     // Catch:{ Exception -> 0x01b4 }
                long r4 = r2 - r4
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                boolean r2 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                if (r2 == 0) goto L_0x0207
                r2 = 180000(0x2bf20, float:2.52234E-40)
            L_0x009f:
                long r2 = (long) r2     // Catch:{ Exception -> 0x01b4 }
                int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r2 <= 0) goto L_0x00b0
            L_0x00a4:
                r2 = r9
            L_0x00a5:
                r0 = r17
                de.humatic.nmj.h r3 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                de.humatic.nmj.h$c[] r3 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r3)     // Catch:{ Exception -> 0x01b4 }
                int r3 = r3.length     // Catch:{ Exception -> 0x01b4 }
                if (r2 < r3) goto L_0x020c
            L_0x00b0:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                boolean r2 = p004de.humatic.nmj.C0468h.m295c((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                if (r2 != 0) goto L_0x0108
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r2 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                r3 = 4
                if (r2 < r3) goto L_0x00d1
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r2 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                int r2 = r2 % 4
                if (r2 != 0) goto L_0x0108
            L_0x00d1:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0270 }
                r0 = r17
                de.humatic.nmj.h r3 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0270 }
                byte[] r3 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r3)     // Catch:{ Exception -> 0x0270 }
                r0 = r17
                de.humatic.nmj.h r4 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0270 }
                java.lang.String r5 = "_apple-midi"
                java.lang.String r6 = "_udp"
                java.lang.String r7 = "local"
                r0 = r17
                de.humatic.nmj.h r8 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0270 }
                byte[] r8 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r8)     // Catch:{ Exception -> 0x0270 }
                int r4 = r4.m254a((java.lang.String) r5, (java.lang.String) r6, (java.lang.String) r7, (byte[]) r8)     // Catch:{ Exception -> 0x0270 }
                r2.m262a((byte[]) r3, (int) r4)     // Catch:{ Exception -> 0x0270 }
            L_0x00f6:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                java.util.Vector r2 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                java.util.Iterator r11 = r2.iterator()     // Catch:{ Exception -> 0x01b4 }
            L_0x0102:
                boolean r2 = r11.hasNext()     // Catch:{ Exception -> 0x01b4 }
                if (r2 != 0) goto L_0x022f
            L_0x0108:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                boolean r2 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                if (r2 != 0) goto L_0x0006
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01b4 }
                r2.f299b = r4     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x013e }
                de.humatic.nmj.h$d r3 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x013e }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x013e }
                int r2 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x013e }
                r0 = r16
                if (r2 >= r0) goto L_0x0265
                r2 = 2000(0x7d0, float:2.803E-42)
            L_0x0133:
                long r4 = (long) r2     // Catch:{ Exception -> 0x013e }
                monitor-enter(r3)     // Catch:{ Exception -> 0x013e }
                r3.wait(r4)     // Catch:{ InterruptedException -> 0x026d }
            L_0x0138:
                monitor-exit(r3)     // Catch:{ all -> 0x013b }
                goto L_0x0006
            L_0x013b:
                r2 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x013b }
                throw r2     // Catch:{ Exception -> 0x013e }
            L_0x013e:
                r2 = move-exception
                java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x0156 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ InterruptedException -> 0x0156 }
                int r2 = p004de.humatic.nmj.C0468h.m286b((p004de.humatic.nmj.C0468h) r2)     // Catch:{ InterruptedException -> 0x0156 }
                r0 = r16
                if (r2 >= r0) goto L_0x0269
                r2 = 2000(0x7d0, float:2.803E-42)
            L_0x0150:
                long r2 = (long) r2     // Catch:{ InterruptedException -> 0x0156 }
                java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x0156 }
                goto L_0x0006
            L_0x0156:
                r2 = move-exception
                goto L_0x0006
            L_0x0159:
                r0 = r17
                de.humatic.nmj.h r4 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r5 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                r6 = 0
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r7 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x01b4 }
                if (r3 != 0) goto L_0x017d
                r2 = r10
            L_0x016d:
                byte[] r2 = r5.mo8132a((int) r7, (boolean) r2)     // Catch:{ Exception -> 0x01b4 }
                r4.m272a((byte[]) r2)     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                int r2 = r3 + 1
                r3 = r2
                goto L_0x001c
            L_0x017d:
                r2 = r9
                goto L_0x016d
            L_0x017f:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01af }
                r0 = r17
                de.humatic.nmj.h r4 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01af }
                byte[] r4 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r4)     // Catch:{ Exception -> 0x01af }
                r0 = r17
                de.humatic.nmj.h r5 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01af }
                r0 = r17
                de.humatic.nmj.h r6 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01af }
                int r6 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r6)     // Catch:{ Exception -> 0x01af }
                java.lang.String r7 = "_udp"
                java.lang.String r8 = "local"
                r0 = r17
                de.humatic.nmj.h r11 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01af }
                byte[] r11 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r11)     // Catch:{ Exception -> 0x01af }
                int r5 = r5.m245a((int) r6, (java.lang.String) r7, (java.lang.String) r8, (byte[]) r11)     // Catch:{ Exception -> 0x01af }
                r2.m262a((byte[]) r4, (int) r5)     // Catch:{ Exception -> 0x01af }
            L_0x01aa:
                int r2 = r3 + 1
                r3 = r2
                goto L_0x001f
            L_0x01af:
                r2 = move-exception
                r2.printStackTrace()     // Catch:{ Exception -> 0x01b4 }
                goto L_0x01aa
            L_0x01b4:
                r2 = move-exception
                int r3 = p004de.humatic.nmj.NMJConfig.f54a
                r0 = r17
                de.humatic.nmj.h r4 = p004de.humatic.nmj.C0468h.this
                int r4 = p004de.humatic.nmj.C0468h.m295c((p004de.humatic.nmj.C0468h) r4)
                r3 = r3 & r4
                if (r3 == 0) goto L_0x01fb
                r3 = -2147483647(0xffffffff80000001, float:-1.4E-45)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                int r5 = p004de.humatic.nmj.NMJConfig.f54a
                java.lang.String r5 = p004de.humatic.nmj.C0484p.m354a((int) r5)
                java.lang.String r5 = java.lang.String.valueOf(r5)
                r4.<init>(r5)
                java.lang.String r5 = " DNS ("
                java.lang.StringBuilder r4 = r4.append(r5)
                r0 = r17
                de.humatic.nmj.h r5 = p004de.humatic.nmj.C0468h.this
                java.lang.String r5 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r5)
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r5 = "), closing. Error in announcement task: "
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r2 = r2.toString()
                java.lang.StringBuilder r2 = r4.append(r2)
                java.lang.String r2 = r2.toString()
                p004de.humatic.nmj.NMJConfig.m60a((int) r15, (int) r3, (java.lang.String) r2)
            L_0x01fb:
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this
                r2.mo8135c()
                goto L_0x0006
            L_0x0204:
                r2 = r9
                goto L_0x0036
            L_0x0207:
                r2 = 600000(0x927c0, float:8.40779E-40)
                goto L_0x009f
            L_0x020c:
                r0 = r17
                de.humatic.nmj.h r3 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r4 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                byte[] r4 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r4)     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r5 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                r0 = r17
                de.humatic.nmj.h r6 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x01b4 }
                byte[] r6 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r6)     // Catch:{ Exception -> 0x01b4 }
                int r5 = r5.m246a((int) r2, (byte[]) r6)     // Catch:{ Exception -> 0x01b4 }
                r3.m262a((byte[]) r4, (int) r5)     // Catch:{ Exception -> 0x01b4 }
                int r2 = r2 + 1
                goto L_0x00a5
            L_0x022f:
                java.lang.Object r2 = r11.next()     // Catch:{ Exception -> 0x01b4 }
                r0 = r2
                de.humatic.nmj.g r0 = (p004de.humatic.nmj.C0467g) r0     // Catch:{ Exception -> 0x01b4 }
                r7 = r0
                r0 = r17
                de.humatic.nmj.h r12 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0262 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0262 }
                byte[] r13 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r2)     // Catch:{ Exception -> 0x0262 }
                r0 = r17
                de.humatic.nmj.h r2 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0262 }
                java.lang.String r3 = r7.f280b     // Catch:{ Exception -> 0x0262 }
                java.lang.String r4 = r7.f278a     // Catch:{ Exception -> 0x0262 }
                java.lang.String r5 = r7.f281c     // Catch:{ Exception -> 0x0262 }
                java.lang.String r6 = r7.f282d     // Catch:{ Exception -> 0x0262 }
                int r7 = r7.f279b     // Catch:{ Exception -> 0x0262 }
                r0 = r17
                de.humatic.nmj.h r8 = p004de.humatic.nmj.C0468h.this     // Catch:{ Exception -> 0x0262 }
                byte[] r8 = p004de.humatic.nmj.C0468h.m247a((p004de.humatic.nmj.C0468h) r8)     // Catch:{ Exception -> 0x0262 }
                int r2 = r2.m253a(r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0262 }
                r12.m262a((byte[]) r13, (int) r2)     // Catch:{ Exception -> 0x0262 }
                goto L_0x0102
            L_0x0262:
                r2 = move-exception
                goto L_0x0102
            L_0x0265:
                r2 = 20000(0x4e20, float:2.8026E-41)
                goto L_0x0133
            L_0x0269:
                r2 = 20000(0x4e20, float:2.8026E-41)
                goto L_0x0150
            L_0x026d:
                r2 = move-exception
                goto L_0x0138
            L_0x0270:
                r2 = move-exception
                goto L_0x00f6
            L_0x0273:
                r2 = move-exception
                goto L_0x0029
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0468h.C0469a.run():void");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m272a(byte[] bArr) throws Exception {
        if (this.f301b != null && bArr != null && !this.f290a.isClosed() && !this.f312c) {
            this.f301b.setData(bArr, 0, bArr.length);
            this.f290a.send(this.f301b);
        }
    }

    /* renamed from: b */
    private void m291b(byte[] bArr) throws Exception {
        if (this.f310c != null && bArr != null && !this.f302b.isClosed() && !this.f312c) {
            this.f310c.setData(bArr, 0, bArr.length);
            this.f302b.send(this.f310c);
        }
    }

    /* renamed from: a */
    private void m273a(byte[] bArr, int i) throws Exception {
        if (i > 0 && !this.f312c && this.f324f && this.f301b != null && bArr != null) {
            this.f301b.setData(bArr, 0, i);
            if (!this.f290a.isClosed()) {
                this.f290a.send(this.f301b);
            }
        }
    }

    /* renamed from: de.humatic.nmj.h$b */
    class C0470b extends TimerTask {
        private C0470b() {
        }

        /* synthetic */ C0470b(C0468h hVar, byte b) {
            this();
        }

        public final void run() {
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
            }
            if (C0468h.m247a(C0468h.this) == null) {
                C0468h.this.f289a = new DatagramPacket(new byte[1500], 1500);
            }
            while (!C0468h.m247a(C0468h.this)) {
                try {
                    C0468h.m247a(C0468h.this).setLength(1500);
                    C0468h.m247a(C0468h.this).receive(C0468h.m247a(C0468h.this));
                    if (C0468h.m247a(C0468h.this).getLength() != 0) {
                        C0468h.this.f326h = true;
                        InetAddress address = ((InetSocketAddress) C0468h.m247a(C0468h.this).getSocketAddress()).getAddress();
                        if (!(address instanceof Inet6Address) && (!C0484p.m387a(address, C0468h.m295c(C0468h.this)) || ((NMJConfig.getFlags(-1) & 4) != 0 && !NMJConfig.m44a(new String(C0468h.m247a(C0468h.this).getData(), 0, C0468h.m247a(C0468h.this).getLength()))))) {
                            int unused = C0468h.this.m256a(C0468h.m247a(C0468h.this).getData(), C0468h.m247a(C0468h.this).getLength(), false, C0468h.m247a(C0468h.this).getSocketAddress());
                        }
                    }
                } catch (SocketTimeoutException e2) {
                    if (!C0468h.this.f294a && System.currentTimeMillis() - C0468h.m247a(C0468h.this) > 10000) {
                        C0468h.this.f299b = System.currentTimeMillis();
                        try {
                            C0468h.this.m262a(C0468h.m282a("_apple-midi", "_udp", "local"), C0468h.m282a("_apple-midi", "_udp", "local").length);
                        } catch (Exception e3) {
                        }
                    }
                } catch (NullPointerException e4) {
                    C0468h.this.f312c = true;
                } catch (Exception e5) {
                    if (e5.toString().indexOf("closed") == -1 && e5.toString().indexOf("Interrupted system call") == -1 && e5.toString().indexOf("Bad file number") == -1) {
                        e5.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static int m257a(byte[] bArr, String str, int i) {
        int length = str.length();
        int i2 = i + 1;
        bArr[i] = (byte) length;
        System.arraycopy(str.getBytes(), 0, bArr, i2, length);
        return length + i2;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public byte[] mo8132a(int i, boolean z) {
        byte b = 0;
        try {
            if (this.f296a == null || this.f296a.length <= i || this.f296a[i] == null || this.f296a[i].f331a == null) {
                return null;
            }
            byte[] bArr = new byte[256];
            C0484p.m382a(1, bArr, 4);
            C0484p.m382a(1, bArr, 8);
            bArr[12] = (byte) this.f296a[i].f331a.length();
            System.arraycopy(this.f296a[i].f331a.getBytes(), 0, bArr, 13, this.f296a[i].f331a.length());
            int length = this.f296a[i].f331a.length() + 13;
            int i2 = length + 1;
            bArr[length] = (byte) this.f296a[i].f333b.length();
            System.arraycopy(this.f296a[i].f333b.getBytes(), 0, bArr, i2, this.f296a[i].f333b.length());
            int length2 = this.f296a[i].f333b.length() + i2;
            int i3 = length2 + 1;
            bArr[length2] = 4;
            System.arraycopy("_udp".getBytes(), 0, bArr, i3, 4);
            int i4 = i3 + 4;
            int i5 = i4 + 1;
            bArr[i4] = 5;
            System.arraycopy("local".getBytes(), 0, bArr, i5, 5);
            int i6 = i5 + 5;
            int i7 = i6 + 1;
            bArr[i6] = 0;
            C0484p.m382a(255, bArr, i7);
            int i8 = i7 + 2;
            int i9 = i8 + 1;
            if (z) {
                b = Byte.MIN_VALUE;
            }
            bArr[i8] = b;
            int i10 = i9 + 1;
            bArr[i9] = 1;
            int i11 = i10 + 1;
            bArr[i10] = -64;
            int i12 = i11 + 1;
            bArr[i11] = 12;
            C0484p.m382a(33, bArr, i12);
            int i13 = i12 + 2;
            C0484p.m382a(1, bArr, i13);
            int i14 = i13 + 2;
            C0484p.m372a(120, bArr, i14);
            int i15 = i14 + 4;
            byte length3 = (byte) (this.f288a.length() + 8 + 1);
            int i16 = i15 + 1;
            bArr[i15] = (byte) (length3 >> 8);
            bArr[i16] = (byte) length3;
            int i17 = i16 + 1 + 4;
            C0484p.m382a((short) this.f296a[i].f332b, bArr, i17);
            int i18 = i17 + 2;
            int i19 = i18 + 1;
            bArr[i18] = (byte) this.f288a.length();
            System.arraycopy(this.f288a.getBytes(), 0, bArr, i19, this.f288a.length());
            int length4 = this.f288a.length() + i19;
            int i20 = length4 + 1;
            bArr[length4] = -64;
            int i21 = i20 + 1;
            bArr[i20] = (byte) i4;
            byte[] bArr2 = new byte[i21];
            System.arraycopy(bArr, 0, bArr2, 0, i21);
            return bArr2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public static byte[] m282a(String str, String str2, String str3) {
        byte[] bArr = new byte[(str.length() + 18 + str2.length() + str3.length() + 2)];
        C0484p.m382a(1, bArr, 4);
        bArr[12] = (byte) str.length();
        System.arraycopy(str.getBytes(), 0, bArr, 13, str.length());
        int length = str.length() + 13;
        int i = length + 1;
        bArr[length] = (byte) str2.length();
        System.arraycopy(str2.getBytes(), 0, bArr, i, str2.length());
        int length2 = str2.length() + i;
        int i2 = length2 + 1;
        bArr[length2] = (byte) str3.length();
        System.arraycopy(str3.getBytes(), 0, bArr, i2, str3.length());
        int length3 = str3.length() + i2 + 2;
        bArr[length3] = 12;
        C0484p.m382a(1, bArr, length3 + 1);
        return bArr;
    }

    /* renamed from: a */
    private int m255a(String str, byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = 0;
        }
        C0484p.m382a(1, bArr, 4);
        int a = m257a(bArr, "local", m257a(bArr, "_udp", m257a(bArr, "_apple-midi", m257a(bArr, str, 12)))) + 1;
        C0484p.m382a(33, bArr, a);
        int i2 = a + 2;
        C0484p.m382a(1, bArr, i2);
        return i2 + 2;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public int m246a(int i, byte[] bArr) {
        int a;
        if (this.f296a == null || this.f296a.length <= i || this.f296a[i] == null || this.f296a[i].f331a == null) {
            return 0;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = 0;
        }
        C0484p.m382a(-31744, bArr, 2);
        C0484p.m382a(4, bArr, 6);
        int a2 = m257a(bArr, "local", m257a(bArr, this.f288a, 12)) + 1;
        C0484p.m382a(1, bArr, a2);
        int i3 = a2 + 2;
        C0484p.m382a(1, bArr, i3);
        int i4 = i3 + 2;
        C0484p.m372a(3600, bArr, i4);
        int i5 = i4 + 4;
        C0484p.m382a(4, bArr, i5);
        int i6 = i5 + 2;
        System.arraycopy(this.f295a, 0, bArr, i6, 4);
        int i7 = i6 + 4;
        if (NMJConfig.getMode(this.f296a[i].f330a) == 1) {
            a = m257a(bArr, "_udp", m257a(bArr, "_apple-midi", i7));
        } else {
            a = NMJConfig.getMode(this.f296a[i].f330a) == 6 ? m257a(bArr, "_tcp", m257a(bArr, "_ws-midi", i7)) : i7;
        }
        int a3 = m257a(bArr, "local", a) + 1;
        C0484p.m382a(12, bArr, a3);
        int i8 = a3 + 2;
        C0484p.m382a(1, bArr, i8);
        int i9 = i8 + 2;
        C0484p.m372a(3600, bArr, i9);
        int i10 = i9 + 4;
        C0484p.m382a((short) (this.f296a[i].f331a.length() + 3), bArr, i10);
        int i11 = i10 + 2;
        int a4 = m257a(bArr, this.f296a[i].f331a, i11);
        C0484p.m382a((short) (i7 | 49152), bArr, a4);
        int i12 = a4 + 2;
        C0484p.m382a((short) (49152 | i11), bArr, i12);
        int i13 = i12 + 2;
        C0484p.m382a(33, bArr, i13);
        int i14 = i13 + 2;
        C0484p.m382a(-32767, bArr, i14);
        int i15 = i14 + 2;
        C0484p.m372a(3600, bArr, i15);
        int i16 = i15 + 4;
        C0484p.m382a((short) (this.f288a.length() + 14), bArr, i16);
        int i17 = i16 + 2 + 4;
        C0484p.m382a((short) this.f296a[i].f332b, bArr, i17);
        int a5 = m257a(bArr, "local", m257a(bArr, this.f288a, i17 + 2)) + 1;
        C0484p.m382a((short) (i11 | 49152), bArr, a5);
        int i18 = a5 + 2;
        C0484p.m382a(16, bArr, i18);
        int i19 = i18 + 2;
        C0484p.m382a(-32767, bArr, i19);
        int i20 = i19 + 2;
        C0484p.m372a(4500, bArr, i20);
        int i21 = i20 + 4;
        C0484p.m382a(1, bArr, i21);
        return i21 + 2 + 1;
    }

    /* renamed from: a */
    private byte[] m281a(String str) {
        int i = 0;
        try {
            if (this.f296a == null || this.f296a.length <= 0) {
                return null;
            }
            for (C0471c cVar : this.f296a) {
                if (cVar.f333b.equalsIgnoreCase(str)) {
                    i++;
                }
            }
            byte[] bArr = new byte[(i * 128)];
            C0484p.m382a(-31744, bArr, 2);
            C0484p.m382a((short) i, bArr, 6);
            bArr[12] = 9;
            System.arraycopy("_services".getBytes(), 0, bArr, 13, 9);
            bArr[22] = 7;
            System.arraycopy("_dns_sd".getBytes(), 0, bArr, 23, 7);
            bArr[30] = 4;
            System.arraycopy("_udp".getBytes(), 0, bArr, 31, 4);
            bArr[35] = 5;
            System.arraycopy("local".getBytes(), 0, bArr, 36, 5);
            bArr[41] = 0;
            C0484p.m382a(12, bArr, 42);
            C0484p.m382a(1, bArr, 44);
            C0484p.m372a(120, bArr, 46);
            C0484p.m382a((short) (this.f288a.length() + 3), bArr, 50);
            bArr[52] = (byte) this.f288a.length();
            System.arraycopy(this.f288a.getBytes(), 0, bArr, 53, this.f288a.length());
            int length = this.f288a.length() + 53;
            int i2 = length + 1;
            bArr[length] = -64;
            int i3 = i2 + 1;
            bArr[i2] = 30;
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, 0, bArr2, 0, i3);
            return bArr2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public int m253a(String str, String str2, String str3, String str4, int i, byte[] bArr) {
        int i2 = 0;
        while (i2 < bArr.length) {
            try {
                bArr[i2] = 0;
                i2++;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        C0484p.m382a(-31744, bArr, 2);
        C0484p.m382a(4, bArr, 6);
        int i3 = 0;
        if (str3 != null) {
            i3 = 1;
        }
        if (str4 != null) {
            i3++;
        }
        C0484p.m382a((short) i3, bArr, 10);
        if (str2.indexOf(".") != -1) {
            str2 = str2.replace(".", "_");
        }
        bArr[12] = (byte) str2.length();
        System.arraycopy(str2.getBytes(), 0, bArr, 13, str2.length());
        int length = str2.length() + 13;
        int i4 = length + 1;
        bArr[length] = (byte) str.length();
        System.arraycopy(str.getBytes(), 0, bArr, i4, str.length());
        int length2 = i4 + str.length();
        int i5 = length2 + 1;
        bArr[length2] = (byte) "_udp".length();
        System.arraycopy("_udp".getBytes(), 0, bArr, i5, "_udp".length());
        int length3 = "_udp".length() + i5;
        int i6 = length3 + 1;
        bArr[length3] = (byte) "local".length();
        System.arraycopy("local".getBytes(), 0, bArr, i6, "local".length());
        int length4 = "local".length() + i6 + 1;
        C0484p.m382a(33, bArr, length4);
        int i7 = length4 + 2;
        C0484p.m382a(-32767, bArr, i7);
        int i8 = i7 + 2;
        C0484p.m372a(120, bArr, i8);
        int i9 = i8 + 4;
        byte length5 = (byte) (this.f288a.length() + 1 + 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (length5 >> 8);
        bArr[i10] = (byte) length5;
        int i11 = i10 + 1 + 4;
        C0484p.m382a((short) i, bArr, i11);
        int i12 = i11 + 2;
        int i13 = i12 + 1;
        bArr[i12] = (byte) this.f288a.length();
        System.arraycopy(this.f288a.getBytes(), 0, bArr, i13, this.f288a.length());
        int length6 = i13 + this.f288a.length();
        int i14 = length6 + 1;
        bArr[length6] = -64;
        int i15 = i14 + 1;
        bArr[i14] = (byte) length3;
        C0484p.m382a(-16372, bArr, i15);
        int i16 = i15 + 2;
        C0484p.m382a(16, bArr, i16);
        int i17 = i16 + 2;
        C0484p.m382a(-32767, bArr, i17);
        int i18 = i17 + 2;
        C0484p.m372a(4500, bArr, i18);
        int i19 = i18 + 4;
        C0484p.m382a(1, bArr, i19);
        int i20 = i19 + 2;
        int i21 = i20 + 1;
        bArr[i20] = 0;
        int i22 = i21 + 1;
        bArr[i21] = 9;
        System.arraycopy("_services".getBytes(), 0, bArr, i22, 9);
        int i23 = i22 + 9;
        int i24 = i23 + 1;
        bArr[i23] = 7;
        System.arraycopy("_dns-sd".getBytes(), 0, bArr, i24, 7);
        int i25 = i24 + 7;
        C0484p.m382a((short) (length2 | 49152), bArr, i25);
        int i26 = i25 + 2;
        C0484p.m382a(12, bArr, i26);
        int i27 = i26 + 2;
        C0484p.m382a(1, bArr, i27);
        int i28 = i27 + 2;
        C0484p.m372a(4500, bArr, i28);
        int i29 = i28 + 4;
        C0484p.m382a(2, bArr, i29);
        int i30 = i29 + 2;
        C0484p.m382a((short) (49152 | length), bArr, i30);
        int i31 = i30 + 2;
        C0484p.m382a((short) (length | 49152), bArr, i31);
        int i32 = i31 + 2;
        C0484p.m382a(12, bArr, i32);
        int i33 = i32 + 2;
        C0484p.m382a(1, bArr, i33);
        int i34 = i33 + 2;
        C0484p.m372a(4500, bArr, i34);
        int i35 = i34 + 4;
        C0484p.m382a(2, bArr, i35);
        int i36 = i35 + 2;
        C0484p.m382a(-16372, bArr, i36);
        int i37 = i36 + 2;
        if (str3 != null) {
            C0484p.m382a((short) (49152 | i12), bArr, i37);
            int i38 = i37 + 2;
            C0484p.m382a(1, bArr, i38);
            int i39 = i38 + 2;
            C0484p.m382a(-32767, bArr, i39);
            int i40 = i39 + 2;
            C0484p.m372a(120, bArr, i40);
            int i41 = i40 + 4 + 1;
            int i42 = i41 + 1;
            bArr[i41] = 4;
            System.arraycopy(this.f295a, 0, bArr, i42, 4);
            i37 = i42 + 4;
        }
        if (str4 == null) {
            return i37;
        }
        C0484p.m382a((short) (49152 | i12), bArr, i37);
        int i43 = i37 + 2;
        C0484p.m382a(28, bArr, i43);
        int i44 = i43 + 2;
        C0484p.m382a(-32767, bArr, i44);
        int i45 = i44 + 2;
        C0484p.m372a(120, bArr, i45);
        int i46 = i45 + 4 + 1;
        int i47 = i46 + 1;
        bArr[i46] = (byte) this.f306b.length;
        System.arraycopy(this.f306b, 0, bArr, i47, this.f306b.length);
        return this.f306b.length + i47;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized int m254a(String str, String str2, String str3, byte[] bArr) {
        int i;
        int i2;
        int i3;
        try {
            if (this.f296a == null || this.f296a.length <= 0) {
                i = 0;
            } else {
                int[] iArr = new int[this.f296a.length];
                int i4 = 0;
                for (C0471c cVar : this.f296a) {
                    if (cVar.f333b.equalsIgnoreCase(str)) {
                        i4++;
                    }
                }
                if (i4 <= 0) {
                    C0484p.logln(3, "No sessions for " + str);
                    i = 0;
                } else {
                    for (int i5 = 0; i5 < bArr.length; i5++) {
                        bArr[i5] = 0;
                    }
                    C0484p.m382a(-31744, bArr, 2);
                    C0484p.m382a((short) ((i4 * 3) + 1), bArr, 6);
                    int i6 = 2;
                    if (this.f324f) {
                        i6 = 3;
                    }
                    C0484p.m382a((short) i6, bArr, 10);
                    int i7 = 0;
                    int i8 = 12;
                    int i9 = 0;
                    int i10 = 0;
                    int i11 = 0;
                    int i12 = 0;
                    for (int i13 = 0; i13 < this.f296a.length; i13++) {
                        if (!(this.f296a[i13] == null || this.f296a[i13].f333b == null || !this.f296a[i13].f333b.equalsIgnoreCase(str))) {
                            iArr[i13] = i8;
                            String str4 = this.f296a[i13].f331a;
                            if (str4.indexOf(".") != -1) {
                                str4 = str4.replace(".", "_");
                            }
                            int i14 = i8 + 1;
                            bArr[i8] = (byte) str4.length();
                            System.arraycopy(str4.getBytes(), 0, bArr, i14, str4.length());
                            int length = i14 + str4.length();
                            if (i7 == 0) {
                                int i15 = length + 1;
                                bArr[length] = (byte) str.length();
                                System.arraycopy(str.getBytes(), 0, bArr, i15, str.length());
                                i10 = i15 + str.length();
                                int i16 = i10 + 1;
                                bArr[i10] = (byte) str2.length();
                                System.arraycopy(str2.getBytes(), 0, bArr, i16, str2.length());
                                i9 = str2.length() + i16;
                                int i17 = i9 + 1;
                                bArr[i9] = (byte) str3.length();
                                System.arraycopy(str3.getBytes(), 0, bArr, i17, str3.length());
                                i2 = i17 + str3.length() + 1;
                                i11 = length;
                            } else {
                                C0484p.m382a((short) (49152 | i11), bArr, length);
                                i2 = length + 2;
                            }
                            C0484p.m382a(33, bArr, i2);
                            int i18 = i2 + 2;
                            C0484p.m382a(-32767, bArr, i18);
                            int i19 = i18 + 2;
                            C0484p.m372a(120, bArr, i19);
                            int i20 = i19 + 4;
                            byte length2 = (byte) ((i7 == 0 ? this.f288a.length() + 1 : 0) + 8);
                            int i21 = i20 + 1;
                            bArr[i20] = (byte) (length2 >> 8);
                            bArr[i21] = (byte) length2;
                            int i22 = i21 + 1 + 4;
                            C0484p.m382a((short) this.f296a[i13].f332b, bArr, i22);
                            int i23 = i22 + 2;
                            if (i7 == 0) {
                                int i24 = i23 + 1;
                                bArr[i23] = (byte) this.f288a.length();
                                System.arraycopy(this.f288a.getBytes(), 0, bArr, i24, this.f288a.length());
                                int length3 = i24 + this.f288a.length();
                                int i25 = length3 + 1;
                                bArr[length3] = -64;
                                i3 = i25 + 1;
                                bArr[i25] = (byte) i9;
                                i12 = i23;
                            } else {
                                int i26 = i23 + 1;
                                bArr[i23] = -64;
                                i3 = i26 + 1;
                                bArr[i26] = (byte) i12;
                            }
                            C0484p.m382a((short) (49152 | iArr[i13]), bArr, i3);
                            int i27 = i3 + 2;
                            C0484p.m382a(16, bArr, i27);
                            int i28 = i27 + 2;
                            C0484p.m382a(-32767, bArr, i28);
                            int i29 = i28 + 2;
                            C0484p.m372a(1800, bArr, i29);
                            int i30 = i29 + 4;
                            C0484p.m382a(1, bArr, i30);
                            int i31 = i30 + 2;
                            int i32 = i31 + 1;
                            bArr[i31] = 0;
                            if (i7 == 0) {
                                int i33 = i32 + 1;
                                bArr[i32] = 9;
                                System.arraycopy("_services".getBytes(), 0, bArr, i33, 9);
                                int i34 = i33 + 9;
                                int i35 = i34 + 1;
                                bArr[i34] = 7;
                                System.arraycopy("_dns-sd".getBytes(), 0, bArr, i35, 7);
                                int i36 = i35 + 7;
                                C0484p.m382a((short) (49152 | i10), bArr, i36);
                                int i37 = i36 + 2;
                                C0484p.m382a(12, bArr, i37);
                                int i38 = i37 + 2;
                                C0484p.m382a(1, bArr, i38);
                                int i39 = i38 + 2;
                                C0484p.m372a(1800, bArr, i39);
                                int i40 = i39 + 4;
                                C0484p.m382a(2, bArr, i40);
                                int i41 = i40 + 2;
                                C0484p.m382a((short) (49152 | i11), bArr, i41);
                                i32 = i41 + 2;
                                i7++;
                            }
                            C0484p.m382a((short) (49152 | i11), bArr, i32);
                            int i42 = i32 + 2;
                            C0484p.m382a(12, bArr, i42);
                            int i43 = i42 + 2;
                            C0484p.m382a(1, bArr, i43);
                            int i44 = i43 + 2;
                            C0484p.m372a(1800, bArr, i44);
                            int i45 = i44 + 4;
                            C0484p.m382a(2, bArr, i45);
                            int i46 = i45 + 2;
                            C0484p.m382a((short) (49152 | iArr[i13]), bArr, i46);
                            i8 = i46 + 2;
                        }
                    }
                    if (this.f324f) {
                        C0484p.m382a((short) (49152 | i12), bArr, i8);
                        int i47 = i8 + 2;
                        C0484p.m382a(1, bArr, i47);
                        int i48 = i47 + 2;
                        C0484p.m382a(-32767, bArr, i48);
                        int i49 = i48 + 2;
                        C0484p.m372a(120, bArr, i49);
                        int i50 = i49 + 4 + 1;
                        int i51 = i50 + 1;
                        bArr[i50] = 4;
                        System.arraycopy(this.f295a, 0, bArr, i51, 4);
                        i8 = i51 + 4;
                    }
                    C0484p.m382a((short) (49152 | i12), bArr, i8);
                    int i52 = i8 + 2;
                    C0484p.m382a(47, bArr, i52);
                    int i53 = i52 + 2;
                    C0484p.m382a(-32767, bArr, i53);
                    int i54 = i53 + 2;
                    C0484p.m372a(120, bArr, i54);
                    int i55 = i54 + 4;
                    C0484p.m382a(8, bArr, i55);
                    int i56 = i55 + 2;
                    C0484p.m382a((short) (49152 | i12), bArr, i56);
                    int i57 = i56 + 2;
                    C0484p.m382a(4, bArr, i57);
                    int i58 = i57 + 2;
                    C0484p.m372a(1073741832, bArr, i58);
                    int i59 = i58 + 4;
                    C0484p.m382a(-16372, bArr, i59);
                    int i60 = i59 + 2;
                    C0484p.m382a(47, bArr, i60);
                    int i61 = i60 + 2;
                    C0484p.m382a(-32767, bArr, i61);
                    int i62 = i61 + 2;
                    C0484p.m372a(120, bArr, i62);
                    int i63 = i62 + 4;
                    C0484p.m382a(9, bArr, i63);
                    int i64 = i63 + 2;
                    C0484p.m382a(-16372, bArr, i64);
                    int i65 = i64 + 2;
                    int i66 = i65 + 1;
                    bArr[i65] = 0;
                    int i67 = i66 + 1;
                    bArr[i66] = 5;
                    int i68 = i67 + 1;
                    bArr[i67] = 0;
                    C0484p.m372a(8388672, bArr, i68);
                    i = i68 + 4;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        return i;
    }

    /* renamed from: b */
    private int m288b(String str, byte[] bArr) {
        int i;
        try {
            if (this.f296a == null || this.f296a.length <= 0) {
                return 0;
            }
            int i2 = 0;
            for (C0471c cVar : this.f296a) {
                if (cVar.f333b.equalsIgnoreCase(str)) {
                    i2++;
                }
            }
            for (int i3 = 0; i3 < bArr.length; i3++) {
                bArr[i3] = 0;
            }
            C0484p.m382a(-31744, bArr, 2);
            C0484p.m382a((short) i2, bArr, 6);
            int i4 = (i2 * 3) + 1;
            if (this.f324f) {
                i4++;
            }
            C0484p.m382a((short) i4, bArr, 10);
            int i5 = 12;
            int[] iArr = new int[this.f296a.length];
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            for (int i9 = 0; i9 < this.f296a.length; i9++) {
                if (this.f296a[i9].f333b.equalsIgnoreCase(str)) {
                    if (i6 == 0) {
                        int i10 = i5 + 1;
                        bArr[i5] = (byte) this.f296a[i9].f333b.length();
                        System.arraycopy(this.f296a[i9].f333b.getBytes(), 0, bArr, i10, this.f296a[i9].f333b.length());
                        int length = i10 + this.f296a[i9].f333b.length();
                        int i11 = length + 1;
                        bArr[length] = 4;
                        System.arraycopy("_udp".getBytes(), 0, bArr, i11, 4);
                        i7 = i11 + 4;
                        int i12 = i7 + 1;
                        bArr[i7] = 5;
                        System.arraycopy("local".getBytes(), 0, bArr, i12, 5);
                        i6++;
                        int i13 = i5;
                        i = i12 + 5 + 1;
                        i8 = i13;
                    } else {
                        C0484p.m382a((short) (49152 | i8), bArr, i5);
                        i = i5 + 2;
                    }
                    C0484p.m382a(12, bArr, i);
                    int i14 = i + 2;
                    C0484p.m382a(1, bArr, i14);
                    int i15 = i14 + 2;
                    C0484p.m372a(4500, bArr, i15);
                    int i16 = i15 + 4;
                    C0484p.m382a((short) (this.f296a[i9].f331a.length() + 1 + 2), bArr, i16);
                    int i17 = i16 + 2;
                    iArr[i9] = i17;
                    int i18 = i17 + 1;
                    bArr[i17] = (byte) this.f296a[i9].f331a.length();
                    System.arraycopy(this.f296a[i9].f331a.getBytes(), 0, bArr, i18, this.f296a[i9].f331a.length());
                    int length2 = this.f296a[i9].f331a.length() + i18;
                    C0484p.m382a((short) (49152 | i8), bArr, length2);
                    i5 = length2 + 2;
                }
            }
            int i19 = i5 + 1;
            bArr[i5] = (byte) this.f288a.length();
            System.arraycopy(this.f288a.getBytes(), 0, bArr, i19, this.f288a.length());
            int length3 = i19 + this.f288a.length();
            C0484p.m382a((short) (i7 | 49152), bArr, length3);
            int i20 = length3 + 2;
            C0484p.m382a(1, bArr, i20);
            int i21 = i20 + 2;
            C0484p.m382a(-32767, bArr, i21);
            int i22 = i21 + 2;
            C0484p.m372a(120, bArr, i22);
            int i23 = i22 + 4;
            C0484p.m382a(4, bArr, i23);
            int i24 = i23 + 2;
            System.arraycopy(this.f295a, 0, bArr, i24, 4);
            int i25 = i24 + 4;
            for (int i26 = 0; i26 < this.f296a.length; i26++) {
                if (this.f296a[i26].f333b.equalsIgnoreCase(str)) {
                    C0484p.m382a((short) (iArr[i26] | 49152), bArr, i25);
                    int i27 = i25 + 2;
                    C0484p.m382a(33, bArr, i27);
                    int i28 = i27 + 2;
                    C0484p.m382a(-32767, bArr, i28);
                    int i29 = i28 + 2;
                    C0484p.m372a(120, bArr, i29);
                    int i30 = i29 + 4;
                    C0484p.m382a(8, bArr, i30);
                    int i31 = i30 + 2 + 4;
                    C0484p.m382a((short) this.f296a[i26].f332b, bArr, i31);
                    int i32 = i31 + 2;
                    C0484p.m382a((short) (49152 | i5), bArr, i32);
                    int i33 = i32 + 2;
                    C0484p.m382a((short) (iArr[i26] | 49152), bArr, i33);
                    int i34 = i33 + 2;
                    C0484p.m382a(16, bArr, i34);
                    int i35 = i34 + 2;
                    C0484p.m382a(-32767, bArr, i35);
                    int i36 = i35 + 2;
                    C0484p.m372a(4500, bArr, i36);
                    int i37 = i36 + 4;
                    C0484p.m382a(1, bArr, i37);
                    i25 = i37 + 2 + 1;
                }
            }
            C0484p.m382a((short) (49152 | i5), bArr, i25);
            int i38 = i25 + 2;
            C0484p.m382a(47, bArr, i38);
            int i39 = i38 + 2;
            C0484p.m382a(-32767, bArr, i39);
            int i40 = i39 + 2;
            C0484p.m372a(120, bArr, i40);
            int i41 = i40 + 4;
            C0484p.m382a(8, bArr, i41);
            int i42 = i41 + 2;
            C0484p.m382a((short) (49152 | i5), bArr, i42);
            int i43 = i42 + 2;
            C0484p.m382a(4, bArr, i43);
            int i44 = i43 + 2;
            C0484p.m382a(16384, bArr, i44);
            int i45 = i44 + 2;
            C0484p.m382a(8, bArr, i45);
            int i46 = i45 + 2;
            for (int i47 = 0; i47 < this.f296a.length; i47++) {
                if (this.f296a[i47].f333b.equalsIgnoreCase(str)) {
                    C0484p.m382a((short) (iArr[i47] | 49152), bArr, i46);
                    int i48 = i46 + 2;
                    C0484p.m382a(47, bArr, i48);
                    int i49 = i48 + 2;
                    C0484p.m382a(-32767, bArr, i49);
                    int i50 = i49 + 2;
                    C0484p.m372a(120, bArr, i50);
                    int i51 = i50 + 4;
                    C0484p.m382a(9, bArr, i51);
                    int i52 = i51 + 2;
                    C0484p.m382a((short) (iArr[i47] | 49152), bArr, i52);
                    int i53 = i52 + 2;
                    C0484p.m382a(5, bArr, i53);
                    int i54 = i53 + 2 + 1;
                    C0484p.m382a(128, bArr, i54);
                    int i55 = i54 + 2;
                    C0484p.m382a(64, bArr, i55);
                    i46 = i55 + 2;
                }
            }
            return i46;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: b */
    private int m285b(int i, byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = 0;
        }
        C0484p.m382a(-31744, bArr, 2);
        C0484p.m382a(1, bArr, 6);
        bArr[12] = (byte) this.f288a.length();
        System.arraycopy(this.f288a.getBytes(), 0, bArr, 13, this.f288a.length());
        int length = this.f288a.length() + 13;
        int i3 = length + 1;
        bArr[length] = 5;
        System.arraycopy("local".getBytes(), 0, bArr, i3, 5);
        int i4 = i3 + 5 + 1;
        C0484p.m382a((short) i, bArr, i4);
        int i5 = i4 + 2;
        C0484p.m382a(-32767, bArr, i5);
        int i6 = i5 + 2;
        C0484p.m372a(120, bArr, i6);
        int i7 = i6 + 4;
        if (i == 1) {
            C0484p.m382a(4, bArr, i7);
            int i8 = i7 + 2;
            System.arraycopy(this.f295a, 0, bArr, i8, 4);
            i7 = i8 + 4;
        } else if (i == 28) {
            if (this.f306b == null) {
                return 0;
            }
            C0484p.m382a((short) this.f306b.length, bArr, i7);
            int i9 = i7 + 2;
            System.arraycopy(this.f306b, 0, bArr, i9, this.f306b.length);
            i7 = i9 + this.f306b.length;
        }
        return i7;
    }

    /* renamed from: b */
    private byte[] m294b(String str) {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= this.f296a.length) {
                i2 = -1;
                i = 0;
                break;
            } else if (this.f296a[i2].f331a.equalsIgnoreCase(str) || (this.f296a[i2].f331a.indexOf(".") != -1 && this.f296a[i2].f331a.replace(".", "_").equalsIgnoreCase(str))) {
                i = this.f296a[i2].f332b;
            } else {
                i2++;
            }
        }
        i = this.f296a[i2].f332b;
        if (i2 < 0) {
            return null;
        }
        byte[] bArr = new byte[256];
        C0484p.m382a(-31744, bArr, 2);
        C0484p.m382a(1, bArr, 6);
        C0484p.m382a(1, bArr, 10);
        bArr[12] = (byte) str.length();
        System.arraycopy(str.getBytes(), 0, bArr, 13, str.length());
        int length = str.length() + 13;
        int i3 = length + 1;
        bArr[length] = 11;
        System.arraycopy(this.f296a[i2].f333b.getBytes(), 0, bArr, i3, 11);
        int i4 = i3 + 11;
        int i5 = i4 + 1;
        bArr[i4] = 4;
        System.arraycopy("_udp".getBytes(), 0, bArr, i5, 4);
        int i6 = i5 + 4;
        int i7 = i6 + 1;
        bArr[i6] = 5;
        System.arraycopy("local".getBytes(), 0, bArr, i7, 5);
        int i8 = i7 + 5 + 1;
        C0484p.m382a(33, bArr, i8);
        int i9 = i8 + 2;
        C0484p.m382a(-32767, bArr, i9);
        int i10 = i9 + 2;
        C0484p.m372a(120, bArr, i10);
        int i11 = i10 + 4;
        C0484p.m382a((short) (this.f288a.length() + 9), bArr, i11);
        int i12 = i11 + 2 + 4;
        C0484p.m382a((short) i, bArr, i12);
        int i13 = i12 + 2;
        byte b = (byte) i13;
        int i14 = i13 + 1;
        bArr[i13] = (byte) this.f288a.length();
        System.arraycopy(this.f288a.getBytes(), 0, bArr, i14, this.f288a.length());
        int length2 = this.f288a.length() + i14;
        C0484p.m382a((short) (i6 | 49152), bArr, length2);
        int i15 = length2 + 2;
        C0484p.m382a((short) (49152 | b), bArr, i15);
        int i16 = i15 + 2;
        C0484p.m382a(1, bArr, i16);
        int i17 = i16 + 2;
        C0484p.m382a(-32767, bArr, i17);
        int i18 = i17 + 2;
        C0484p.m372a(120, bArr, i18);
        int i19 = i18 + 4;
        C0484p.m382a(4, bArr, i19);
        int i20 = i19 + 2;
        System.arraycopy(this.f295a, 0, bArr, i20, 4);
        int i21 = i20 + 4;
        byte[] bArr2 = new byte[i21];
        System.arraycopy(bArr, 0, bArr2, 0, i21);
        return bArr2;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int m287b(java.lang.String r7, java.lang.String r8, java.lang.String r9, byte[] r10) {
        /*
            r6 = this;
            r2 = -1
            r0 = 0
            monitor-enter(r6)
            de.humatic.nmj.h$c[] r1 = r6.f296a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            if (r1 == 0) goto L_0x000c
            de.humatic.nmj.h$c[] r1 = r6.f296a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            int r1 = r1.length     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            if (r1 > 0) goto L_0x000e
        L_0x000c:
            monitor-exit(r6)
            return r0
        L_0x000e:
            r1 = r0
        L_0x000f:
            de.humatic.nmj.h$c[] r3 = r6.f296a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            int r3 = r3.length     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            if (r1 < r3) goto L_0x001c
            r1 = r2
        L_0x0015:
            if (r1 < 0) goto L_0x000c
            int r0 = r6.m245a((int) r1, (java.lang.String) r8, (java.lang.String) r9, (byte[]) r10)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            goto L_0x000c
        L_0x001c:
            de.humatic.nmj.h$c[] r3 = r6.f296a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            java.lang.String r3 = r3.f331a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            boolean r3 = r3.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            if (r3 != 0) goto L_0x0015
            de.humatic.nmj.h$c[] r3 = r6.f296a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            java.lang.String r3 = r3.f331a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            java.lang.String r4 = "."
            int r3 = r3.indexOf(r4)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            if (r3 == r2) goto L_0x004a
            de.humatic.nmj.h$c[] r3 = r6.f296a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            r3 = r3[r1]     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            java.lang.String r3 = r3.f331a     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            java.lang.String r4 = "."
            java.lang.String r5 = "_"
            java.lang.String r3 = r3.replace(r4, r5)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            boolean r3 = r3.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            if (r3 != 0) goto L_0x0015
        L_0x004a:
            int r1 = r1 + 1
            goto L_0x000f
        L_0x004d:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x0050:
            r1 = move-exception
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0468h.m287b(java.lang.String, java.lang.String, java.lang.String, byte[]):int");
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized int m245a(int i, String str, String str2, byte[] bArr) {
        int i2 = 0;
        synchronized (this) {
            try {
                int i3 = this.f296a[i].f332b;
                String str3 = this.f296a[i].f331a;
                String str4 = this.f296a[i].f333b;
                for (int i4 = 0; i4 < bArr.length; i4++) {
                    bArr[i4] = 0;
                }
                C0484p.m382a(-31744, bArr, 2);
                C0484p.m382a(4, bArr, 6);
                int i5 = 2;
                if (this.f324f) {
                    i5 = 3;
                }
                C0484p.m382a((short) i5, bArr, 10);
                if (str3.indexOf(".") != -1) {
                    str3 = str3.replace(".", "_");
                }
                bArr[12] = (byte) str3.length();
                System.arraycopy(str3.getBytes(), 0, bArr, 13, str3.length());
                int length = str3.length() + 13;
                int i6 = length + 1;
                bArr[length] = (byte) str4.length();
                System.arraycopy(str4.getBytes(), 0, bArr, i6, str4.length());
                int length2 = i6 + str4.length();
                int i7 = length2 + 1;
                bArr[length2] = (byte) str.length();
                System.arraycopy(str.getBytes(), 0, bArr, i7, str.length());
                int length3 = i7 + str.length();
                int i8 = length3 + 1;
                bArr[length3] = (byte) str2.length();
                System.arraycopy(str2.getBytes(), 0, bArr, i8, str2.length());
                int length4 = i8 + str2.length() + 1;
                C0484p.m382a(16, bArr, length4);
                int i9 = length4 + 2;
                int i10 = i9 + 1;
                bArr[i9] = Byte.MIN_VALUE;
                int i11 = i10 + 1;
                bArr[i10] = 1;
                C0484p.m372a(9000, bArr, i11);
                int i12 = i11 + 4;
                int i13 = i12 + 1;
                bArr[i12] = 0;
                int i14 = i13 + 1;
                bArr[i13] = 1;
                int i15 = i14 + 1;
                bArr[i14] = 0;
                int i16 = i15 + 1;
                bArr[i15] = 9;
                System.arraycopy("_services".getBytes(), 0, bArr, i16, 9);
                int i17 = i16 + 9;
                int i18 = i17 + 1;
                bArr[i17] = 7;
                System.arraycopy("_dns-sd".getBytes(), 0, bArr, i18, 7);
                int i19 = i18 + 7;
                C0484p.m382a((short) (length2 | 49152), bArr, i19);
                int i20 = i19 + 2;
                C0484p.m382a(12, bArr, i20);
                int i21 = i20 + 2;
                C0484p.m382a(1, bArr, i21);
                int i22 = i21 + 2;
                C0484p.m372a(1800, bArr, i22);
                int i23 = i22 + 4;
                C0484p.m382a(2, bArr, i23);
                int i24 = i23 + 2;
                C0484p.m382a((short) (49152 | length), bArr, i24);
                int i25 = i24 + 2;
                C0484p.m382a((short) (length | 49152), bArr, i25);
                int i26 = i25 + 2;
                C0484p.m382a(12, bArr, i26);
                int i27 = i26 + 2;
                C0484p.m382a(1, bArr, i27);
                int i28 = i27 + 2;
                C0484p.m372a(4500, bArr, i28);
                int i29 = i28 + 4;
                C0484p.m382a(2, bArr, i29);
                int i30 = i29 + 2;
                C0484p.m382a(-16372, bArr, i30);
                int i31 = i30 + 2;
                C0484p.m382a(-16372, bArr, i31);
                int i32 = i31 + 2;
                C0484p.m382a(33, bArr, i32);
                int i33 = i32 + 2;
                C0484p.m382a(-32767, bArr, i33);
                int i34 = i33 + 2;
                C0484p.m372a(4500, bArr, i34);
                int i35 = i34 + 4;
                C0484p.m382a((short) (this.f288a.length() + 9), bArr, i35);
                int i36 = i35 + 2;
                C0484p.m372a(0, bArr, i36);
                int i37 = i36 + 4;
                C0484p.m382a((short) i3, bArr, i37);
                int i38 = i37 + 2;
                int i39 = i38 + 1;
                bArr[i38] = (byte) this.f288a.length();
                System.arraycopy(this.f288a.getBytes(), 0, bArr, i39, this.f288a.length());
                int length5 = i39 + this.f288a.length();
                int i40 = length5 + 1;
                bArr[length5] = -64;
                int i41 = i40 + 1;
                bArr[i40] = (byte) length3;
                if (this.f324f) {
                    C0484p.m382a((short) (49152 | i38), bArr, i41);
                    int i42 = i41 + 2;
                    C0484p.m382a(1, bArr, i42);
                    int i43 = i42 + 2;
                    C0484p.m382a(-32767, bArr, i43);
                    int i44 = i43 + 2;
                    C0484p.m372a(120, bArr, i44);
                    int i45 = i44 + 4 + 1;
                    int i46 = i45 + 1;
                    bArr[i45] = 4;
                    System.arraycopy(this.f295a, 0, bArr, i46, 4);
                    i41 = i46 + 4;
                }
                C0484p.m382a((short) (49152 | i38), bArr, i41);
                int i47 = i41 + 2;
                C0484p.m382a(47, bArr, i47);
                int i48 = i47 + 2;
                C0484p.m382a(-32767, bArr, i48);
                int i49 = i48 + 2;
                C0484p.m372a(120, bArr, i49);
                int i50 = i49 + 4;
                C0484p.m382a(8, bArr, i50);
                int i51 = i50 + 2;
                C0484p.m382a((short) (i38 | 49152), bArr, i51);
                int i52 = i51 + 2;
                C0484p.m382a(4, bArr, i52);
                int i53 = i52 + 2;
                C0484p.m372a(1073741832, bArr, i53);
                int i54 = i53 + 4;
                C0484p.m382a(-16372, bArr, i54);
                int i55 = i54 + 2;
                C0484p.m382a(47, bArr, i55);
                int i56 = i55 + 2;
                C0484p.m382a(-32767, bArr, i56);
                int i57 = i56 + 2;
                C0484p.m372a(4500, bArr, i57);
                int i58 = i57 + 4;
                C0484p.m382a(9, bArr, i58);
                int i59 = i58 + 2;
                C0484p.m382a(-16372, bArr, i59);
                int i60 = i59 + 2;
                int i61 = i60 + 1;
                bArr[i60] = 0;
                int i62 = i61 + 1;
                bArr[i61] = 5;
                int i63 = i62 + 1;
                bArr[i62] = 0;
                C0484p.m372a(8388672, bArr, i63);
                i2 = i63 + 4;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i2;
    }

    /* renamed from: c */
    private int m296c(String str, String str2, String str3, byte[] bArr) {
        if (this.f296a == null || this.f296a.length <= 0) {
            return 0;
        }
        int i = 0;
        for (C0471c cVar : this.f296a) {
            if (cVar.f333b.equalsIgnoreCase(str)) {
                i++;
            }
        }
        if (i == 0) {
            return 0;
        }
        for (int i2 = 0; i2 < 256; i2++) {
            bArr[i2] = 0;
        }
        C0484p.m382a(-31744, bArr, 2);
        C0484p.m382a(1, bArr, 6);
        int a = m257a(bArr, "_dns-sd", m257a(bArr, "_services", 12));
        int a2 = m257a(bArr, str3, m257a(bArr, str2, a)) + 1;
        C0484p.m382a(12, bArr, a2);
        int i3 = a2 + 2;
        C0484p.m382a(1, bArr, i3);
        int i4 = i3 + 2;
        C0484p.m372a(4500, bArr, i4);
        int i5 = i4 + 4;
        C0484p.m382a((short) (str.length() + 3), bArr, i5);
        int a3 = m257a(bArr, str, i5 + 2);
        C0484p.m382a((short) (a | 49152), bArr, a3);
        return a3 + 2;
    }

    /* renamed from: a */
    private byte[] m283a(String str, String str2, String str3, String str4) {
        try {
            if (this.f288a == null) {
                try {
                    this.f288a = InetAddress.getLocalHost().getHostName();
                } catch (UnknownHostException e) {
                }
                if (!(this.f288a != null && this.f288a.indexOf("127.0.0.1") == -1 && this.f288a.indexOf("localhost") == -1)) {
                    this.f288a = NMJConfig.m54a(false).replace(".", "_");
                }
            }
            if (str4.indexOf(".") != -1) {
                str4 = str4.replace(".", "_");
            }
            byte[] bArr = new byte[128];
            C0484p.m382a(-31744, bArr, 2);
            C0484p.m382a(1, bArr, 6);
            bArr[12] = (byte) str.length();
            System.arraycopy(str.getBytes(), 0, bArr, 13, str.length());
            int length = str.length() + 13;
            int i = length + 1;
            bArr[length] = (byte) str2.length();
            System.arraycopy(str2.getBytes(), 0, bArr, i, str2.length());
            int length2 = str2.length() + i;
            int i2 = length2 + 1;
            bArr[length2] = (byte) str3.length();
            System.arraycopy(str3.getBytes(), 0, bArr, i2, str3.length());
            int length3 = str3.length() + i2 + 1;
            C0484p.m382a(12, bArr, length3);
            int i3 = length3 + 2;
            C0484p.m382a(1, bArr, i3);
            int i4 = i3 + 2;
            C0484p.m372a(0, bArr, i4);
            int i5 = i4 + 4;
            C0484p.m382a((short) ((byte) (str4.length() + 2 + 1)), bArr, i5);
            int i6 = i5 + 2;
            int i7 = i6 + 1;
            bArr[i6] = (byte) str4.length();
            System.arraycopy(str4.getBytes(), 0, bArr, i7, str4.length());
            int length4 = str4.length() + i7;
            C0484p.m382a(-16372, bArr, length4);
            int i8 = length4 + 2;
            byte[] bArr2 = new byte[i8];
            System.arraycopy(bArr, 0, bArr2, 0, i8);
            return bArr2;
        } catch (Exception e2) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public static String m262a(byte[] bArr, int i) {
        byte b = (bArr[i + 1] & 255) | ((bArr[i] & 47) << 8);
        if (b >= bArr.length) {
            return "";
        }
        return new String(bArr, b + 1, bArr[b] & 255);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v109, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v76, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v78, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v79, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v86, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v87, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v167, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v195, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v37, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v298, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v299, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v300, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v40, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v92, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v99, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v100, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v42, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v103, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v43, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v106, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v110, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v95, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v96, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v384, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v385, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v104, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v110, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v112, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v114, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v52, resolved type: byte} */
    /* JADX WARNING: type inference failed for: r13v5, types: [int] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0544 A[Catch:{ Exception -> 0x1098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x0564 A[Catch:{ Exception -> 0x10a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x057b A[Catch:{ Exception -> 0x1098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x05ca A[Catch:{ Exception -> 0x1098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x05f8  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0611  */
    /* JADX WARNING: Removed duplicated region for block: B:329:0x0725  */
    /* JADX WARNING: Removed duplicated region for block: B:742:0x1159  */
    /* JADX WARNING: Removed duplicated region for block: B:744:0x1166  */
    /* JADX WARNING: Removed duplicated region for block: B:746:0x116f  */
    /* JADX WARNING: Removed duplicated region for block: B:747:0x1173  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int m256a(byte[] r31, int r32, boolean r33, java.net.SocketAddress r34) {
        /*
            r30 = this;
            monitor-enter(r30)
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ all -> 0x030b }
            r4.removeAllElements()     // Catch:{ all -> 0x030b }
            r4 = 0
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r4 = 2
            r5 = 2
            r0 = r31
            int r4 = p004de.humatic.nmj.C0484p.m349a((byte[]) r0, (int) r4, (int) r5)     // Catch:{ all -> 0x030b }
            if (r4 != 0) goto L_0x0025
            r4 = 4
            r0 = r30
            r1 = r31
            r2 = r34
            r0.m274a((byte[]) r1, (int) r4, (java.net.SocketAddress) r2)     // Catch:{ all -> 0x030b }
            r4 = -1
        L_0x0023:
            monitor-exit(r30)
            return r4
        L_0x0025:
            r5 = 32768(0x8000, float:4.5918E-41)
            r4 = r4 & r5
            if (r4 != 0) goto L_0x002d
            r4 = -1
            goto L_0x0023
        L_0x002d:
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0038
            java.lang.String r4 = "reading query response"
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
        L_0x0038:
            r4 = 4
            r0 = r31
            short r21 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r4 = 6
            r0 = r31
            short r22 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r4 = 8
            r0 = r31
            short r23 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r4 = 10
            r0 = r31
            short r24 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r8 = 12
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0096
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r5 = "q: "
            r4.<init>(r5)     // Catch:{ all -> 0x030b }
            r0 = r21
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r5 = ", a: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x030b }
            r0 = r22
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r5 = ", auth: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x030b }
            r0 = r23
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r5 = ", add: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x030b }
            r0 = r24
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
        L_0x0096:
            r0 = r30
            java.util.Vector r4 = r0.f293a     // Catch:{ all -> 0x030b }
            r4.removeAllElements()     // Catch:{ all -> 0x030b }
            r4 = 0
            r0 = r30
            r0.f300b = r4     // Catch:{ all -> 0x030b }
            r4 = 0
            r0 = r30
            r0.f309c = r4     // Catch:{ all -> 0x030b }
            r15 = -1
            r14 = 0
            r6 = 0
            r7 = 0
            r13 = 0
            r12 = 0
            r5 = 0
            r9 = 0
            r11 = 0
            r4 = 0
            r17 = r4
            r4 = r7
        L_0x00b4:
            r0 = r17
            r1 = r21
            if (r0 < r1) goto L_0x010e
            r4 = 0
            r20 = r4
        L_0x00bd:
            r0 = r20
            r1 = r22
            if (r0 < r1) goto L_0x04b9
            r9 = -1
            if (r23 <= 0) goto L_0x00fe
            r4 = 0
            r10 = r4
            r5 = r12
            r6 = r15
        L_0x00ca:
            r0 = r23
            if (r10 < r0) goto L_0x0b5a
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x00fe
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r7 = "auth.nameserver: "
            r4.<init>(r7)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x030b }
            java.lang.String r5 = " "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x030b }
            r0 = r30
            java.lang.String r5 = r0.f300b     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x030b }
            java.lang.String r5 = " "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
        L_0x00fe:
            r4 = 0
            r6 = r8
            r8 = r4
        L_0x0101:
            r0 = r24
            if (r8 < r0) goto L_0x0d32
        L_0x0105:
            r0 = r30
            boolean r4 = r0.f305b     // Catch:{ all -> 0x030b }
            if (r4 != 0) goto L_0x0fb4
            r4 = 0
            goto L_0x0023
        L_0x010e:
            r0 = r30
            boolean r7 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r7 == 0) goto L_0x012e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r10 = "question nr "
            r7.<init>(r10)     // Catch:{ all -> 0x030b }
            r0 = r17
            java.lang.StringBuilder r7 = r7.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r10 = "\n============================================="
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch:{ all -> 0x030b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r7)     // Catch:{ all -> 0x030b }
        L_0x012e:
            r0 = r30
            java.lang.String[] r7 = r0.f297a     // Catch:{ all -> 0x030b }
            if (r7 == 0) goto L_0x013d
            r0 = r30
            java.lang.String[] r7 = r0.f297a     // Catch:{ all -> 0x030b }
            int r7 = r7.length     // Catch:{ all -> 0x030b }
            r0 = r21
            if (r7 >= r0) goto L_0x0145
        L_0x013d:
            r0 = r21
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ all -> 0x030b }
            r0 = r30
            r0.f297a = r7     // Catch:{ all -> 0x030b }
        L_0x0145:
            java.lang.String r10 = ""
            byte r7 = r31[r8]     // Catch:{ all -> 0x030b }
            r7 = r7 & 240(0xf0, float:3.36E-43)
            r16 = 192(0xc0, float:2.69E-43)
            r0 = r16
            if (r7 == r0) goto L_0x02ab
            if (r17 != 0) goto L_0x118e
            int r7 = r8 + 1
            byte r7 = r31[r7]     // Catch:{ all -> 0x030b }
            r10 = 95
            if (r7 == r10) goto L_0x118e
            int r7 = r8 + 1
            byte r8 = r31[r8]     // Catch:{ all -> 0x030b }
            r8 = r8 & 255(0xff, float:3.57E-43)
            r0 = r30
            java.lang.String[] r10 = r0.f297a     // Catch:{ all -> 0x030b }
            java.lang.String r16 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r16
            r1 = r31
            r0.<init>(r1, r7, r8)     // Catch:{ all -> 0x030b }
            r10[r17] = r16     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r10 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r10 == 0) goto L_0x017d
            java.lang.String r10 = "server"
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r10, (byte[]) r0, (int) r7, (int) r8)     // Catch:{ all -> 0x030b }
        L_0x017d:
            int r8 = r8 + r7
            r16 = r8
        L_0x0180:
            r7 = 2
            byte r8 = r31[r16]     // Catch:{ all -> 0x030b }
            r8 = r8 & 240(0xf0, float:3.36E-43)
            r10 = 192(0xc0, float:2.69E-43)
            if (r8 == r10) goto L_0x027a
            int r10 = r16 + 1
            byte r7 = r31[r16]     // Catch:{ all -> 0x030b }
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r8 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r31
            r8.<init>(r0, r10, r7)     // Catch:{ all -> 0x030b }
            java.lang.String r16 = "_services"
            r0 = r16
            boolean r16 = r8.equals(r0)     // Catch:{ all -> 0x030b }
            if (r16 == 0) goto L_0x1187
            int r7 = r7 + r10
            int r10 = r7 + 1
            byte r7 = r31[r7]     // Catch:{ all -> 0x030b }
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r8 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r31
            r8.<init>(r0, r10, r7)     // Catch:{ all -> 0x030b }
            r28 = r8
            r8 = r10
            r10 = r28
        L_0x01b3:
            r0 = r30
            boolean r0 = r0.f321e     // Catch:{ all -> 0x030b }
            r16 = r0
            if (r16 == 0) goto L_0x01c4
            java.lang.String r16 = "serviceName"
            r0 = r16
            r1 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r0, (byte[]) r1, (int) r8, (int) r7)     // Catch:{ all -> 0x030b }
        L_0x01c4:
            int r7 = r7 + r8
            byte r8 = r31[r7]     // Catch:{ all -> 0x030b }
            if (r8 == 0) goto L_0x01fc
            r4 = 2
            byte r8 = r31[r7]     // Catch:{ all -> 0x030b }
            r8 = r8 & 240(0xf0, float:3.36E-43)
            r16 = 192(0xc0, float:2.69E-43)
            r0 = r16
            if (r8 == r0) goto L_0x0287
            int r8 = r7 + 1
            byte r4 = r31[r7]     // Catch:{ all -> 0x030b }
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r31
            r7.<init>(r0, r8, r4)     // Catch:{ all -> 0x030b }
            java.lang.String r7 = r7.trim()     // Catch:{ all -> 0x030b }
            r28 = r4
            r4 = r7
            r7 = r28
        L_0x01ea:
            r0 = r30
            boolean r0 = r0.f321e     // Catch:{ all -> 0x030b }
            r16 = r0
            if (r16 == 0) goto L_0x01fb
            java.lang.String r16 = "protocol"
            r0 = r16
            r1 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r0, (byte[]) r1, (int) r8, (int) r7)     // Catch:{ all -> 0x030b }
        L_0x01fb:
            int r7 = r7 + r8
        L_0x01fc:
            byte r8 = r31[r7]     // Catch:{ all -> 0x030b }
            if (r8 == 0) goto L_0x0232
            r8 = 2
            byte r13 = r31[r7]     // Catch:{ all -> 0x030b }
            r13 = r13 & 240(0xf0, float:3.36E-43)
            r16 = 192(0xc0, float:2.69E-43)
            r0 = r16
            if (r13 == r0) goto L_0x0295
            int r8 = r7 + 1
            byte r7 = r31[r7]     // Catch:{ all -> 0x030b }
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r13 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r31
            r13.<init>(r0, r8, r7)     // Catch:{ all -> 0x030b }
        L_0x0218:
            r0 = r30
            boolean r0 = r0.f321e     // Catch:{ all -> 0x030b }
            r16 = r0
            if (r16 == 0) goto L_0x0229
            java.lang.String r16 = "area"
            r0 = r16
            r1 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r0, (byte[]) r1, (int) r8, (int) r7)     // Catch:{ all -> 0x030b }
        L_0x0229:
            r16 = 2
            r0 = r16
            int r7 = java.lang.Math.max(r7, r0)     // Catch:{ all -> 0x030b }
            int r7 = r7 + r8
        L_0x0232:
            if (r17 != 0) goto L_0x1192
            byte r8 = r31[r7]     // Catch:{ all -> 0x030b }
            if (r8 != 0) goto L_0x02a2
            int r7 = r7 + 1
            r28 = r4
            r4 = r7
            r7 = r28
        L_0x023f:
            r0 = r31
            short r16 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r8 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r8 == 0) goto L_0x0256
            java.lang.String r8 = "type"
            r18 = 2
            r0 = r31
            r1 = r18
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r8, (byte[]) r0, (int) r4, (int) r1)     // Catch:{ all -> 0x030b }
        L_0x0256:
            int r4 = r4 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r8 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r8 == 0) goto L_0x026e
            java.lang.String r8 = "cl"
            r18 = 2
            r0 = r31
            r1 = r18
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r8, (byte[]) r0, (int) r4, (int) r1)     // Catch:{ all -> 0x030b }
        L_0x026e:
            int r8 = r4 + 2
            switch(r16) {
                case 1: goto L_0x02d1;
                case 12: goto L_0x030e;
                case 16: goto L_0x0351;
                case 28: goto L_0x02d1;
                case 33: goto L_0x03c5;
                case 255: goto L_0x0473;
                default: goto L_0x0273;
            }     // Catch:{ all -> 0x030b }
        L_0x0273:
            int r4 = r17 + 1
            r17 = r4
            r4 = r7
            goto L_0x00b4
        L_0x027a:
            r0 = r31
            r1 = r16
            java.lang.String r8 = m262a((byte[]) r0, (int) r1)     // Catch:{ all -> 0x030b }
            r10 = r8
            r8 = r16
            goto L_0x01b3
        L_0x0287:
            r0 = r31
            java.lang.String r8 = m262a((byte[]) r0, (int) r7)     // Catch:{ all -> 0x030b }
            r28 = r4
            r4 = r8
            r8 = r7
            r7 = r28
            goto L_0x01ea
        L_0x0295:
            r0 = r31
            java.lang.String r13 = m262a((byte[]) r0, (int) r7)     // Catch:{ all -> 0x030b }
            r28 = r8
            r8 = r7
            r7 = r28
            goto L_0x0218
        L_0x02a2:
            byte r8 = r31[r7]     // Catch:{ all -> 0x030b }
            int r7 = r7 + r8
            r28 = r4
            r4 = r7
            r7 = r28
            goto L_0x023f
        L_0x02ab:
            r0 = r30
            java.lang.String[] r7 = r0.f297a     // Catch:{ all -> 0x030b }
            r0 = r31
            java.lang.String r16 = m262a((byte[]) r0, (int) r8)     // Catch:{ all -> 0x030b }
            r7[r17] = r16     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r7 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r7 == 0) goto L_0x02c8
            java.lang.String r7 = "server"
            r16 = 2
            r0 = r31
            r1 = r16
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r7, (byte[]) r0, (int) r8, (int) r1)     // Catch:{ all -> 0x030b }
        L_0x02c8:
            int r7 = r8 + 2
            r28 = r4
            r4 = r7
            r7 = r28
            goto L_0x023f
        L_0x02d1:
            r0 = r30
            boolean r4 = r0.f294a     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0273
            r0 = r30
            byte[] r4 = r0.f318d     // Catch:{ Exception -> 0x02ed }
            r0 = r30
            byte[] r10 = r0.f318d     // Catch:{ Exception -> 0x02ed }
            r0 = r30
            r1 = r16
            int r10 = r0.m285b((int) r1, (byte[]) r10)     // Catch:{ Exception -> 0x02ed }
            r0 = r30
            r0.m262a((byte[]) r4, (int) r10)     // Catch:{ Exception -> 0x02ed }
            goto L_0x0273
        L_0x02ed:
            r4 = move-exception
            r4.printStackTrace()     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r16 = "failed to send host resolver "
            r0 = r16
            r10.<init>(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r10.append(r4)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
            goto L_0x0273
        L_0x030b:
            r4 = move-exception
            monitor-exit(r30)
            throw r4
        L_0x030e:
            r0 = r30
            boolean r4 = r0.f294a     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0273
            boolean r4 = m293b((java.lang.String) r10)     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0273
            r0 = r30
            byte[] r4 = r0.f318d     // Catch:{ Exception -> 0x0333 }
            r0 = r30
            byte[] r0 = r0.f318d     // Catch:{ Exception -> 0x0333 }
            r16 = r0
            r0 = r30
            r1 = r16
            int r10 = r0.m288b((java.lang.String) r10, (byte[]) r1)     // Catch:{ Exception -> 0x0333 }
            r0 = r30
            r0.m262a((byte[]) r4, (int) r10)     // Catch:{ Exception -> 0x0333 }
            goto L_0x0273
        L_0x0333:
            r4 = move-exception
            r4.printStackTrace()     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r16 = "failed to send PTR response "
            r0 = r16
            r10.<init>(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r10.append(r4)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
            goto L_0x0273
        L_0x0351:
            r0 = r30
            java.lang.String[] r4 = r0.f297a     // Catch:{ all -> 0x030b }
            r16 = 0
            r4 = r4[r16]     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r4 = r0.m259a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x03ba
            r0 = r30
            byte[] r4 = r0.f318d     // Catch:{ Exception -> 0x0388 }
            r0 = r30
            java.lang.String[] r0 = r0.f297a     // Catch:{ Exception -> 0x0388 }
            r16 = r0
            r18 = 0
            r16 = r16[r18]     // Catch:{ Exception -> 0x0388 }
            r0 = r30
            byte[] r0 = r0.f318d     // Catch:{ Exception -> 0x0388 }
            r18 = r0
            r0 = r30
            r1 = r16
            r2 = r18
            int r16 = r0.m287b(r1, r7, r13, r2)     // Catch:{ Exception -> 0x0388 }
            r0 = r30
            r1 = r16
            r0.m262a((byte[]) r4, (int) r1)     // Catch:{ Exception -> 0x0388 }
            goto L_0x0273
        L_0x0388:
            r4 = move-exception
            r4.printStackTrace()     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r16 = "failed to send TXT resolver for "
            r0 = r16
            r4.<init>(r0)     // Catch:{ all -> 0x030b }
            r0 = r30
            java.lang.String[] r0 = r0.f297a     // Catch:{ all -> 0x030b }
            r16 = r0
            r18 = 0
            r16 = r16[r18]     // Catch:{ all -> 0x030b }
            r0 = r16
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r16 = ", "
            r0 = r16
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r10)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
            goto L_0x0273
        L_0x03ba:
            java.lang.String r4 = "_apple-midi"
            int r4 = r10.indexOf(r4)     // Catch:{ all -> 0x030b }
            r10 = -1
            if (r4 == r10) goto L_0x0273
            goto L_0x0273
        L_0x03c5:
            r0 = r30
            boolean r4 = r0.f294a     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0273
            r0 = r30
            java.lang.String[] r4 = r0.f297a     // Catch:{ all -> 0x030b }
            r16 = 0
            r4 = r4[r16]     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r4 = r0.m259a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0273
            java.lang.String r4 = "_udp"
            java.lang.String r16 = "_ws-midi"
            r0 = r16
            int r16 = r10.indexOf(r0)     // Catch:{ Exception -> 0x0444 }
            r18 = -1
            r0 = r16
            r1 = r18
            if (r0 == r1) goto L_0x03ef
            java.lang.String r4 = "_tcp"
        L_0x03ef:
            r0 = r30
            byte[] r16 = r0.m259a((java.lang.String) r10)     // Catch:{ Exception -> 0x0444 }
            r0 = r16
            int r0 = r0.length     // Catch:{ Exception -> 0x0444 }
            r18 = r0
            r0 = r30
            r1 = r16
            r2 = r18
            r0.m262a((byte[]) r1, (int) r2)     // Catch:{ Exception -> 0x0444 }
            r0 = r30
            java.lang.String[] r0 = r0.f297a     // Catch:{ Exception -> 0x0444 }
            r16 = r0
            r18 = 0
            r16 = r16[r18]     // Catch:{ Exception -> 0x0444 }
            r0 = r30
            r1 = r16
            byte[] r16 = r0.m293b((java.lang.String) r1)     // Catch:{ Exception -> 0x0444 }
            r0 = r16
            int r0 = r0.length     // Catch:{ Exception -> 0x0444 }
            r18 = r0
            r0 = r30
            r1 = r16
            r2 = r18
            r0.m262a((byte[]) r1, (int) r2)     // Catch:{ Exception -> 0x0444 }
            r0 = r30
            byte[] r0 = r0.f318d     // Catch:{ Exception -> 0x0444 }
            r16 = r0
            java.lang.String r18 = "local"
            r0 = r30
            byte[] r0 = r0.f318d     // Catch:{ Exception -> 0x0444 }
            r19 = r0
            r0 = r30
            r1 = r18
            r2 = r19
            int r4 = r0.m254a((java.lang.String) r10, (java.lang.String) r4, (java.lang.String) r1, (byte[]) r2)     // Catch:{ Exception -> 0x0444 }
            r0 = r30
            r1 = r16
            r0.m262a((byte[]) r1, (int) r4)     // Catch:{ Exception -> 0x0444 }
            goto L_0x0273
        L_0x0444:
            r4 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r16 = "failed to send SRV resolver for "
            r0 = r16
            r4.<init>(r0)     // Catch:{ all -> 0x030b }
            r0 = r30
            java.lang.String[] r0 = r0.f297a     // Catch:{ all -> 0x030b }
            r16 = r0
            r18 = 0
            r16 = r16[r18]     // Catch:{ all -> 0x030b }
            r0 = r16
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.String r16 = ", "
            r0 = r16
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r10)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
            goto L_0x0273
        L_0x0473:
            long r18 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x030b }
            r0 = r30
            long r0 = r0.f285a     // Catch:{ all -> 0x030b }
            r26 = r0
            long r18 = r18 - r26
            r26 = 2000(0x7d0, double:9.88E-321)
            int r4 = (r18 > r26 ? 1 : (r18 == r26 ? 0 : -1))
            if (r4 < 0) goto L_0x0273
            long r18 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x030b }
            r0 = r18
            r2 = r30
            r2.f285a = r0     // Catch:{ all -> 0x030b }
            r4 = 0
            r10 = r4
        L_0x0491:
            r0 = r30
            de.humatic.nmj.h$c[] r4 = r0.f296a     // Catch:{ all -> 0x030b }
            int r4 = r4.length     // Catch:{ all -> 0x030b }
            if (r10 >= r4) goto L_0x0273
            r4 = 0
            r0 = r30
            byte[] r4 = r0.mo8132a((int) r10, (boolean) r4)     // Catch:{ Exception -> 0x04b4 }
            r0 = r30
            r0.m272a((byte[]) r4)     // Catch:{ Exception -> 0x04b4 }
            r4 = 0
            r0 = r30
            byte[] r4 = r0.mo8132a((int) r10, (boolean) r4)     // Catch:{ Exception -> 0x04b4 }
            r0 = r30
            r0.m291b((byte[]) r4)     // Catch:{ Exception -> 0x04b4 }
        L_0x04b0:
            int r4 = r10 + 1
            r10 = r4
            goto L_0x0491
        L_0x04b4:
            r4 = move-exception
            r4.printStackTrace()     // Catch:{ all -> 0x030b }
            goto L_0x04b0
        L_0x04b9:
            if (r8 < 0) goto L_0x04bf
            int r4 = r32 + -1
            if (r8 <= r4) goto L_0x04c2
        L_0x04bf:
            r4 = -1
            goto L_0x0023
        L_0x04c2:
            r16 = 0
            r17 = 0
            r18 = 0
            byte r4 = r31[r8]     // Catch:{ Exception -> 0x1058 }
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r7 = 192(0xc0, float:2.69E-43)
            if (r4 == r7) goto L_0x074d
            int r4 = r8 + 1
            byte r4 = r31[r4]     // Catch:{ Exception -> 0x1058 }
            r7 = 95
            if (r4 != r7) goto L_0x04eb
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x1058 }
            int r7 = r8 + 1
            r10 = 9
            r0 = r31
            r4.<init>(r0, r7, r10)     // Catch:{ Exception -> 0x1058 }
            java.lang.String r7 = "_services"
            boolean r4 = r4.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x1058 }
            if (r4 == 0) goto L_0x1182
        L_0x04eb:
            int r4 = r8 + 1
            byte r4 = r31[r4]     // Catch:{ Exception -> 0x1058 }
            r7 = 95
            if (r4 != r7) goto L_0x117f
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x1058 }
            if (r4 == 0) goto L_0x04fe
            java.lang.String r4 = "_services list"
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ Exception -> 0x1058 }
        L_0x04fe:
            r10 = 1
        L_0x04ff:
            r4 = 2
            byte r5 = r31[r8]     // Catch:{ Exception -> 0x1068 }
            r5 = r5 & 240(0xf0, float:3.36E-43)
            r7 = 192(0xc0, float:2.69E-43)
            if (r5 == r7) goto L_0x0715
            int r7 = r8 + 1
            byte r4 = r31[r8]     // Catch:{ Exception -> 0x1075 }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r10 != 0) goto L_0x117b
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x1075 }
            r0 = r31
            r5.<init>(r0, r7, r4)     // Catch:{ Exception -> 0x1075 }
            r0 = r30
            boolean r6 = r0.f321e     // Catch:{ Exception -> 0x1085 }
            if (r6 == 0) goto L_0x1178
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x1085 }
            java.lang.String r8 = "server "
            r6.<init>(r8)     // Catch:{ Exception -> 0x1085 }
            java.lang.StringBuilder r6 = r6.append(r4)     // Catch:{ Exception -> 0x1085 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x1085 }
            int r8 = r7 + r4
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r6, (byte[]) r0, (int) r7, (int) r8)     // Catch:{ Exception -> 0x1085 }
            r6 = r7
        L_0x0534:
            int r7 = r6 + r4
            r6 = r10
            r10 = r5
        L_0x0538:
            r5 = 2
            r4 = 1
            byte r8 = r31[r7]     // Catch:{ Exception -> 0x1098 }
            r8 = r8 & 240(0xf0, float:3.36E-43)
            r19 = 192(0xc0, float:2.69E-43)
            r0 = r19
            if (r8 == r0) goto L_0x0725
            int r8 = r7 + 1
            byte r8 = r31[r8]     // Catch:{ Exception -> 0x1098 }
            r8 = r8 & 255(0xff, float:3.57E-43)
            r19 = 95
            r0 = r19
            if (r8 != r0) goto L_0x071e
            int r8 = r7 + 1
            byte r5 = r31[r7]     // Catch:{ Exception -> 0x10a7 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r17 = new java.lang.String     // Catch:{ Exception -> 0x10a7 }
            r0 = r17
            r1 = r31
            r0.<init>(r1, r8, r5)     // Catch:{ Exception -> 0x10a7 }
            r19 = r4
            r4 = r5
        L_0x0562:
            if (r19 == 0) goto L_0x1173
            r0 = r30
            boolean r5 = r0.f321e     // Catch:{ Exception -> 0x10a7 }
            if (r5 == 0) goto L_0x0573
            java.lang.String r5 = "serviceName"
            int r7 = r8 + r4
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r5, (byte[]) r0, (int) r8, (int) r7)     // Catch:{ Exception -> 0x10a7 }
        L_0x0573:
            int r7 = r8 + r4
            r4 = r8
        L_0x0576:
            r5 = 1
            byte r8 = r31[r7]     // Catch:{ Exception -> 0x1098 }
            if (r8 == 0) goto L_0x116f
            r8 = 2
            byte r18 = r31[r7]     // Catch:{ Exception -> 0x1098 }
            r0 = r18
            r0 = r0 & 240(0xf0, float:3.36E-43)
            r18 = r0
            r25 = 192(0xc0, float:2.69E-43)
            r0 = r18
            r1 = r25
            if (r0 == r1) goto L_0x0739
            int r18 = r7 + 1
            byte r18 = r31[r18]     // Catch:{ Exception -> 0x1098 }
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            r25 = 95
            r0 = r18
            r1 = r25
            if (r0 != r1) goto L_0x0731
            int r8 = r7 + 1
            byte r7 = r31[r7]     // Catch:{ Exception -> 0x10a7 }
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r18 = new java.lang.String     // Catch:{ Exception -> 0x10a7 }
            r0 = r18
            r1 = r31
            r0.<init>(r1, r8, r7)     // Catch:{ Exception -> 0x10a7 }
            r18.trim()     // Catch:{ Exception -> 0x10a7 }
        L_0x05b0:
            if (r5 == 0) goto L_0x116a
            r0 = r30
            boolean r0 = r0.f321e     // Catch:{ Exception -> 0x10a7 }
            r18 = r0
            if (r18 == 0) goto L_0x05c3
            java.lang.String r18 = "protocol"
            r0 = r18
            r1 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r0, (byte[]) r1, (int) r8, (int) r7)     // Catch:{ Exception -> 0x10a7 }
        L_0x05c3:
            int r7 = r7 + r8
            r18 = r5
        L_0x05c6:
            byte r5 = r31[r7]     // Catch:{ Exception -> 0x1098 }
            if (r5 == 0) goto L_0x1166
            r5 = 2
            byte r8 = r31[r7]     // Catch:{ Exception -> 0x1098 }
            r8 = r8 & 240(0xf0, float:3.36E-43)
            r25 = 192(0xc0, float:2.69E-43)
            r0 = r25
            if (r8 == r0) goto L_0x0745
            int r8 = r7 + 1
            byte r5 = r31[r7]     // Catch:{ Exception -> 0x10a7 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x10a7 }
            r0 = r31
            r7.<init>(r0, r8, r5)     // Catch:{ Exception -> 0x10a7 }
            r28 = r7
            r7 = r8
            r8 = r28
        L_0x05e7:
            r0 = r30
            boolean r13 = r0.f321e     // Catch:{ Exception -> 0x10b3 }
            if (r13 == 0) goto L_0x05f4
            java.lang.String r13 = "area"
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r13, (byte[]) r0, (int) r7, (int) r5)     // Catch:{ Exception -> 0x10b3 }
        L_0x05f4:
            int r13 = r7 + r5
        L_0x05f6:
            if (r8 == 0) goto L_0x1162
            java.lang.String r5 = "local"
            boolean r5 = r8.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x10c1 }
            if (r5 == 0) goto L_0x1162
            if (r18 != 0) goto L_0x1162
            if (r19 != 0) goto L_0x1162
            r9 = 1
            r5 = r9
            r7 = r10
        L_0x0607:
            byte r9 = r31[r13]     // Catch:{ Exception -> 0x10cc }
            if (r9 != 0) goto L_0x1159
            int r9 = r13 + 1
            byte r9 = r31[r9]     // Catch:{ Exception -> 0x10cc }
            if (r9 != 0) goto L_0x1159
            int r13 = r13 + 1
            r12 = r7
            r9 = r10
            r7 = r17
            r10 = r13
            r17 = r4
        L_0x061a:
            r0 = r31
            short r18 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r10)     // Catch:{ Exception -> 0x10d5 }
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x10d5 }
            if (r4 == 0) goto L_0x062e
            java.lang.String r4 = "type"
            r13 = 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r4, (byte[]) r0, (int) r10, (int) r13)     // Catch:{ Exception -> 0x10d5 }
        L_0x062e:
            int r10 = r10 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r10)     // Catch:{ Exception -> 0x10d5 }
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x10d5 }
            if (r4 == 0) goto L_0x0643
            java.lang.String r4 = "cl"
            r13 = 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r4, (byte[]) r0, (int) r10, (int) r13)     // Catch:{ Exception -> 0x10d5 }
        L_0x0643:
            int r13 = r10 + 2
            r0 = r31
            int r19 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r13)     // Catch:{ Exception -> 0x10de }
            int r10 = r14 + r19
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x09b2 }
            if (r4 == 0) goto L_0x065b
            java.lang.String r4 = "ttl"
            r14 = 4
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r4, (byte[]) r0, (int) r13, (int) r14)     // Catch:{ Exception -> 0x09b2 }
        L_0x065b:
            int r13 = r13 + 4
            r0 = r31
            short r4 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r13)     // Catch:{ Exception -> 0x09b2 }
            int r13 = r13 + 2
            r14 = 33
            r0 = r18
            if (r0 != r14) goto L_0x079e
            int r13 = r13 + 4
            r4 = 2
            r0 = r31
            int r14 = p004de.humatic.nmj.C0484p.m349a((byte[]) r0, (int) r13, (int) r4)     // Catch:{ Exception -> 0x09b2 }
            int r13 = r13 + 2
            byte r4 = r31[r13]     // Catch:{ Exception -> 0x10e7 }
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r15 = 192(0xc0, float:2.69E-43)
            if (r4 == r15) goto L_0x076d
            int r15 = r13 + 1
            byte r4 = r31[r13]     // Catch:{ Exception -> 0x10ef }
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r13 = new java.lang.String     // Catch:{ Exception -> 0x10ef }
            r0 = r31
            r13.<init>(r0, r15, r4)     // Catch:{ Exception -> 0x10ef }
            int r12 = r15 + r4
            byte r4 = r31[r12]     // Catch:{ Exception -> 0x10f7 }
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r15 = 192(0xc0, float:2.69E-43)
            if (r4 != r15) goto L_0x1152
            int r12 = r12 + 2
            r28 = r13
            r13 = r12
            r12 = r28
        L_0x069c:
            r0 = r30
            de.humatic.nmj.g r4 = r0.m259a((java.lang.String) r7)     // Catch:{ Exception -> 0x10e7 }
            if (r4 != 0) goto L_0x114f
            de.humatic.nmj.g r4 = new de.humatic.nmj.g     // Catch:{ Exception -> 0x10e7 }
            r0 = r17
            r4.<init>(r7, r0)     // Catch:{ Exception -> 0x10e7 }
            if (r9 == 0) goto L_0x06b0
            r4.mo8126a(r9)     // Catch:{ Exception -> 0x10e7 }
        L_0x06b0:
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r15 = r0.f304b     // Catch:{ Exception -> 0x10e7 }
            r15.add(r4)     // Catch:{ Exception -> 0x10e7 }
            r15 = r4
        L_0x06b8:
            if (r15 == 0) goto L_0x1143
            r15.f279b = r14     // Catch:{ Exception -> 0x10e7 }
            r15.f283e = r12     // Catch:{ Exception -> 0x10e7 }
            if (r5 == 0) goto L_0x0777
            if (r11 == 0) goto L_0x0777
            r15.f281c = r11     // Catch:{ Exception -> 0x10e7 }
        L_0x06c4:
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x10e7 }
            if (r4 == 0) goto L_0x1143
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x10e7 }
            java.lang.String r16 = "Filled Service on SRV "
            r0 = r16
            r4.<init>(r0)     // Catch:{ Exception -> 0x10e7 }
            java.lang.StringBuilder r4 = r4.append(r15)     // Catch:{ Exception -> 0x10e7 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x10e7 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ Exception -> 0x10e7 }
            r16 = r15
            r4 = r11
            r11 = r14
            r28 = r7
            r7 = r12
            r12 = r13
            r13 = r28
        L_0x06e8:
            if (r23 != 0) goto L_0x06f4
            if (r24 != 0) goto L_0x06f4
            r14 = 12
            r0 = r18
            if (r0 != r14) goto L_0x06f4
            if (r19 == 0) goto L_0x06fb
        L_0x06f4:
            r14 = 4
            r0 = r22
            if (r0 != r14) goto L_0x0703
            if (r10 != 0) goto L_0x0703
        L_0x06fb:
            if (r16 == 0) goto L_0x0703
            r0 = r30
            boolean r14 = r0.f305b     // Catch:{ Exception -> 0x0b36 }
            if (r14 != 0) goto L_0x0ad7
        L_0x0703:
            int r13 = r20 + 1
            r20 = r13
            r14 = r10
            r15 = r11
            r13 = r8
            r11 = r4
            r8 = r12
            r12 = r7
            r28 = r9
            r9 = r5
            r5 = r6
            r6 = r28
            goto L_0x00bd
        L_0x0715:
            r0 = r31
            java.lang.String r5 = m262a((byte[]) r0, (int) r8)     // Catch:{ Exception -> 0x1068 }
            r6 = r8
            goto L_0x0534
        L_0x071e:
            r4 = 0
            r19 = r4
            r8 = r7
            r4 = r5
            goto L_0x0562
        L_0x0725:
            r0 = r31
            java.lang.String r17 = m262a((byte[]) r0, (int) r7)     // Catch:{ Exception -> 0x1098 }
            r19 = r4
            r8 = r7
            r4 = r5
            goto L_0x0562
        L_0x0731:
            r5 = 0
            r28 = r8
            r8 = r7
            r7 = r28
            goto L_0x05b0
        L_0x0739:
            r0 = r31
            m262a((byte[]) r0, (int) r7)     // Catch:{ Exception -> 0x1098 }
            r28 = r8
            r8 = r7
            r7 = r28
            goto L_0x05b0
        L_0x0745:
            r0 = r31
            java.lang.String r8 = m262a((byte[]) r0, (int) r7)     // Catch:{ Exception -> 0x1098 }
            goto L_0x05e7
        L_0x074d:
            r0 = r31
            java.lang.String r7 = m262a((byte[]) r0, (int) r8)     // Catch:{ Exception -> 0x1058 }
            byte r4 = r31[r8]     // Catch:{ Exception -> 0x1058 }
            r4 = r4 & 47
            int r4 = r4 << 8
            int r10 = r8 + 1
            byte r10 = r31[r10]     // Catch:{ Exception -> 0x1058 }
            r10 = r10 & 255(0xff, float:3.57E-43)
            r4 = r4 | r10
            int r10 = r8 + 2
            r17 = r4
            r8 = r13
            r28 = r5
            r5 = r9
            r9 = r6
            r6 = r28
            goto L_0x061a
        L_0x076d:
            r0 = r31
            java.lang.String r12 = m262a((byte[]) r0, (int) r13)     // Catch:{ Exception -> 0x10e7 }
            int r13 = r13 + 2
            goto L_0x069c
        L_0x0777:
            if (r33 != 0) goto L_0x06c4
            java.lang.String r4 = r15.f281c     // Catch:{ Exception -> 0x10e7 }
            if (r4 != 0) goto L_0x06c4
            r4 = 1
            r0 = r22
            if (r0 != r4) goto L_0x06c4
            r0 = r34
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ Exception -> 0x079b }
            r4 = r0
            java.net.InetAddress r4 = r4.getAddress()     // Catch:{ Exception -> 0x079b }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x079b }
            r16 = 1
            r0 = r16
            java.lang.String r4 = r4.substring(r0)     // Catch:{ Exception -> 0x079b }
            r15.f281c = r4     // Catch:{ Exception -> 0x079b }
            goto L_0x06c4
        L_0x079b:
            r4 = move-exception
            goto L_0x06c4
        L_0x079e:
            r14 = 12
            r0 = r18
            if (r0 != r14) goto L_0x08af
            if (r7 == 0) goto L_0x113f
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x09b2 }
            if (r4 == 0) goto L_0x07d2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = "new Service on PTR "
            r4.<init>(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r4 = r4.append(r13)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = " "
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = " "
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r4 = r4.append(r9)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x09b2 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ Exception -> 0x09b2 }
        L_0x07d2:
            de.humatic.nmj.g r4 = new de.humatic.nmj.g     // Catch:{ Exception -> 0x09b2 }
            r0 = r17
            r4.<init>(r7, r0)     // Catch:{ Exception -> 0x09b2 }
            if (r9 == 0) goto L_0x07de
            r4.mo8126a(r9)     // Catch:{ Exception -> 0x09b2 }
        L_0x07de:
            if (r15 <= 0) goto L_0x07e2
            r4.f279b = r15     // Catch:{ Exception -> 0x09b2 }
        L_0x07e2:
            if (r12 == 0) goto L_0x07e6
            r4.f283e = r12     // Catch:{ Exception -> 0x09b2 }
        L_0x07e6:
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r14 = r0.f304b     // Catch:{ Exception -> 0x09b2 }
            r14.add(r4)     // Catch:{ Exception -> 0x09b2 }
            r17 = r4
        L_0x07ef:
            byte r4 = r31[r13]     // Catch:{ Exception -> 0x09b2 }
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r14 = 192(0xc0, float:2.69E-43)
            if (r4 != r14) goto L_0x085a
            r0 = r31
            java.lang.String r4 = m262a((byte[]) r0, (int) r13)     // Catch:{ Exception -> 0x09b2 }
            int r13 = r13 + 2
            if (r17 == 0) goto L_0x1138
            r0 = r17
            r0.mo8126a(r4)     // Catch:{ Exception -> 0x09b2 }
            r28 = r7
            r7 = r13
            r13 = r28
        L_0x080b:
            r14 = 1
            r0 = r22
            if (r0 != r14) goto L_0x1126
            int r14 = r21 + r24
            int r14 = r14 + r23
            if (r14 != 0) goto L_0x1126
            java.lang.String r14 = "_"
            int r14 = r4.indexOf(r14)     // Catch:{ Exception -> 0x1106 }
            if (r14 == 0) goto L_0x1126
            r0 = r30
            boolean r14 = r0.f321e     // Catch:{ Exception -> 0x1106 }
            if (r14 == 0) goto L_0x0838
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x1106 }
            java.lang.String r16 = "rtpMIDI mDNS - creating request for "
            r0 = r16
            r14.<init>(r0)     // Catch:{ Exception -> 0x1106 }
            java.lang.StringBuilder r14 = r14.append(r4)     // Catch:{ Exception -> 0x1106 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x1106 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r14)     // Catch:{ Exception -> 0x1106 }
        L_0x0838:
            r0 = r30
            byte[] r14 = r0.f318d     // Catch:{ Exception -> 0x1106 }
            r0 = r30
            byte[] r0 = r0.f318d     // Catch:{ Exception -> 0x1106 }
            r16 = r0
            r0 = r30
            r1 = r16
            int r4 = r0.m255a((java.lang.String) r4, (byte[]) r1)     // Catch:{ Exception -> 0x1106 }
            r0 = r30
            r0.m262a((byte[]) r14, (int) r4)     // Catch:{ Exception -> 0x1106 }
            r16 = r17
            r4 = r11
            r11 = r15
            r28 = r12
            r12 = r7
            r7 = r28
            goto L_0x06e8
        L_0x085a:
            int r14 = r13 + 1
            byte r4 = r31[r13]     // Catch:{ Exception -> 0x10fe }
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r16 = new java.lang.String     // Catch:{ Exception -> 0x10fe }
            r0 = r16
            r1 = r31
            r0.<init>(r1, r14, r4)     // Catch:{ Exception -> 0x10fe }
            int r13 = r14 + r4
            byte r4 = r31[r13]     // Catch:{ Exception -> 0x09b2 }
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r14 = 192(0xc0, float:2.69E-43)
            if (r4 != r14) goto L_0x1134
            if (r7 != 0) goto L_0x1131
            r0 = r31
            java.lang.String r4 = m262a((byte[]) r0, (int) r13)     // Catch:{ Exception -> 0x09b2 }
        L_0x087b:
            int r7 = r13 + 2
        L_0x087d:
            if (r17 == 0) goto L_0x0890
            java.lang.String r13 = "_"
            r0 = r16
            int r13 = r0.indexOf(r13)     // Catch:{ Exception -> 0x1106 }
            if (r13 == 0) goto L_0x0890
            r0 = r17
            r1 = r16
            r0.mo8126a(r1)     // Catch:{ Exception -> 0x1106 }
        L_0x0890:
            r0 = r30
            boolean r13 = r0.f321e     // Catch:{ Exception -> 0x1106 }
            if (r13 == 0) goto L_0x08aa
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x1106 }
            java.lang.String r14 = "domain name: "
            r13.<init>(r14)     // Catch:{ Exception -> 0x1106 }
            r0 = r16
            java.lang.StringBuilder r13 = r13.append(r0)     // Catch:{ Exception -> 0x1106 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x1106 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r13)     // Catch:{ Exception -> 0x1106 }
        L_0x08aa:
            r13 = r4
            r4 = r16
            goto L_0x080b
        L_0x08af:
            r14 = 1
            r0 = r18
            if (r0 == r14) goto L_0x08ba
            r14 = 28
            r0 = r18
            if (r0 != r14) goto L_0x0a7a
        L_0x08ba:
            r4 = 1
            r0 = r18
            if (r0 != r4) goto L_0x09cb
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09b2 }
            int r14 = r13 + 1
            byte r13 = r31[r13]     // Catch:{ Exception -> 0x10fe }
            r13 = r13 & 255(0xff, float:3.57E-43)
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x10fe }
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x10fe }
            r4.<init>(r13)     // Catch:{ Exception -> 0x10fe }
            java.lang.String r13 = "."
            java.lang.StringBuilder r4 = r4.append(r13)     // Catch:{ Exception -> 0x10fe }
            int r13 = r14 + 1
            byte r14 = r31[r14]     // Catch:{ Exception -> 0x09b2 }
            r14 = r14 & 255(0xff, float:3.57E-43)
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = "."
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x09b2 }
            int r14 = r13 + 1
            byte r13 = r31[r13]     // Catch:{ Exception -> 0x10fe }
            r13 = r13 & 255(0xff, float:3.57E-43)
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x10fe }
            java.lang.StringBuilder r4 = r4.append(r13)     // Catch:{ Exception -> 0x10fe }
            java.lang.String r13 = "."
            java.lang.StringBuilder r4 = r4.append(r13)     // Catch:{ Exception -> 0x10fe }
            int r13 = r14 + 1
            byte r14 = r31[r14]     // Catch:{ Exception -> 0x09b2 }
            r14 = r14 & 255(0xff, float:3.57E-43)
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            r0.f300b = r4     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x09b2 }
            if (r4 == 0) goto L_0x0932
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = "   ip4Address: "
            r4.<init>(r14)     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            java.lang.String r14 = r0.f300b     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x09b2 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ Exception -> 0x09b2 }
        L_0x0932:
            r0 = r30
            java.lang.String r4 = r0.f300b     // Catch:{ Exception -> 0x1111 }
            java.lang.String r14 = "169.254"
            int r4 = r4.indexOf(r14)     // Catch:{ Exception -> 0x1111 }
            r14 = -1
            if (r4 == r14) goto L_0x0987
            r0 = r34
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ Exception -> 0x1111 }
            r4 = r0
            java.net.InetAddress r4 = r4.getAddress()     // Catch:{ Exception -> 0x1111 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x1111 }
            java.lang.String r14 = "192"
            int r4 = r4.indexOf(r14)     // Catch:{ Exception -> 0x1111 }
            r14 = -1
            if (r4 == r14) goto L_0x0987
            r0 = r34
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ Exception -> 0x1111 }
            r4 = r0
            java.net.InetAddress r4 = r4.getAddress()     // Catch:{ Exception -> 0x1111 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x1111 }
            r14 = 1
            java.lang.String r4 = r4.substring(r14)     // Catch:{ Exception -> 0x1111 }
            r0 = r30
            r0.f300b = r4     // Catch:{ Exception -> 0x1111 }
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x1111 }
            if (r4 == 0) goto L_0x0987
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x1111 }
            java.lang.String r14 = "   corrected: "
            r4.<init>(r14)     // Catch:{ Exception -> 0x1111 }
            r0 = r30
            java.lang.String r14 = r0.f300b     // Catch:{ Exception -> 0x1111 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x1111 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x1111 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ Exception -> 0x1111 }
        L_0x0987:
            if (r5 != 0) goto L_0x09bd
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ Exception -> 0x09b2 }
            java.util.Iterator r14 = r4.iterator()     // Catch:{ Exception -> 0x09b2 }
        L_0x0991:
            boolean r4 = r14.hasNext()     // Catch:{ Exception -> 0x09b2 }
            if (r4 != 0) goto L_0x09a1
            r4 = r11
            r11 = r15
            r28 = r7
            r7 = r12
            r12 = r13
            r13 = r28
            goto L_0x06e8
        L_0x09a1:
            java.lang.Object r4 = r14.next()     // Catch:{ Exception -> 0x09b2 }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            java.lang.String r0 = r0.f300b     // Catch:{ Exception -> 0x09b2 }
            r17 = r0
            r0 = r17
            r4.f281c = r0     // Catch:{ Exception -> 0x09b2 }
            goto L_0x0991
        L_0x09b2:
            r4 = move-exception
            r7 = r12
            r12 = r13
            r13 = r4
            r4 = r11
            r11 = r15
        L_0x09b8:
            r13.printStackTrace()     // Catch:{ all -> 0x030b }
            goto L_0x0703
        L_0x09bd:
            r0 = r30
            java.lang.String r11 = r0.f300b     // Catch:{ Exception -> 0x09b2 }
            r4 = r11
            r11 = r15
            r28 = r7
            r7 = r12
            r12 = r13
            r13 = r28
            goto L_0x06e8
        L_0x09cb:
            java.lang.String r4 = ""
            r0 = r30
            r0.f309c = r4     // Catch:{ Exception -> 0x09b2 }
            r4 = 0
        L_0x09d2:
            r14 = 8
            if (r4 < r14) goto L_0x0a0c
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x09b2 }
            if (r4 == 0) goto L_0x09f2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = "   ip6Address:"
            r4.<init>(r14)     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            java.lang.String r14 = r0.f309c     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x09b2 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ Exception -> 0x09b2 }
        L_0x09f2:
            if (r5 != 0) goto L_0x111c
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ Exception -> 0x09b2 }
            java.util.Iterator r14 = r4.iterator()     // Catch:{ Exception -> 0x09b2 }
        L_0x09fc:
            boolean r4 = r14.hasNext()     // Catch:{ Exception -> 0x09b2 }
            if (r4 != 0) goto L_0x0a69
            r4 = r11
            r11 = r15
            r28 = r7
            r7 = r12
            r12 = r13
            r13 = r28
            goto L_0x06e8
        L_0x0a0c:
            r0 = r30
            java.lang.String r14 = r0.f309c     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ Exception -> 0x09b2 }
            r0 = r17
            r0.<init>(r14)     // Catch:{ Exception -> 0x09b2 }
            int r14 = r13 + 1
            byte r13 = r31[r13]     // Catch:{ Exception -> 0x10fe }
            r13 = r13 & 255(0xff, float:3.57E-43)
            java.lang.String r13 = p004de.humatic.nmj.C0484p.m354a((int) r13)     // Catch:{ Exception -> 0x10fe }
            r0 = r17
            java.lang.StringBuilder r17 = r0.append(r13)     // Catch:{ Exception -> 0x10fe }
            int r13 = r14 + 1
            byte r14 = r31[r14]     // Catch:{ Exception -> 0x09b2 }
            r14 = r14 & 255(0xff, float:3.57E-43)
            java.lang.String r14 = p004de.humatic.nmj.C0484p.m354a((int) r14)     // Catch:{ Exception -> 0x09b2 }
            r0 = r17
            java.lang.StringBuilder r14 = r0.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            r0.f309c = r14     // Catch:{ Exception -> 0x09b2 }
            r14 = 7
            if (r4 >= r14) goto L_0x0a65
            r0 = r30
            java.lang.String r14 = r0.f309c     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ Exception -> 0x09b2 }
            r0 = r17
            r0.<init>(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = ":"
            r0 = r17
            java.lang.StringBuilder r14 = r0.append(r14)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            r0.f309c = r14     // Catch:{ Exception -> 0x09b2 }
        L_0x0a65:
            int r4 = r4 + 1
            goto L_0x09d2
        L_0x0a69:
            java.lang.Object r4 = r14.next()     // Catch:{ Exception -> 0x09b2 }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ Exception -> 0x09b2 }
            r0 = r30
            java.lang.String r0 = r0.f309c     // Catch:{ Exception -> 0x09b2 }
            r17 = r0
            r0 = r17
            r4.f282d = r0     // Catch:{ Exception -> 0x09b2 }
            goto L_0x09fc
        L_0x0a7a:
            r14 = 2
            if (r4 <= r14) goto L_0x0a96
            java.lang.String r14 = new java.lang.String     // Catch:{ Exception -> 0x09b2 }
            int r17 = r13 + 1
            byte r25 = r31[r13]     // Catch:{ Exception -> 0x09b2 }
            r0 = r25
            r0 = r0 & 255(0xff, float:3.57E-43)
            r25 = r0
            r0 = r31
            r1 = r17
            r2 = r25
            r14.<init>(r0, r1, r2)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r12 = r14.trim()     // Catch:{ Exception -> 0x09b2 }
        L_0x0a96:
            r0 = r30
            boolean r14 = r0.f321e     // Catch:{ Exception -> 0x09b2 }
            if (r14 == 0) goto L_0x0acc
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r17 = "type: "
            r0 = r17
            r14.<init>(r0)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r17 = p004de.humatic.nmj.C0484p.m356a((short) r18)     // Catch:{ Exception -> 0x09b2 }
            r0 = r17
            java.lang.StringBuilder r14 = r14.append(r0)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r17 = ", length: "
            r0 = r17
            java.lang.StringBuilder r14 = r14.append(r0)     // Catch:{ Exception -> 0x09b2 }
            java.lang.StringBuilder r14 = r14.append(r4)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r17 = ", data"
            r0 = r17
            java.lang.StringBuilder r14 = r14.append(r0)     // Catch:{ Exception -> 0x09b2 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x09b2 }
            r0 = r31
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r14, (byte[]) r0, (int) r13, (int) r4)     // Catch:{ Exception -> 0x09b2 }
        L_0x0acc:
            int r13 = r13 + r4
            r4 = r11
            r11 = r15
            r28 = r7
            r7 = r12
            r12 = r13
            r13 = r28
            goto L_0x06e8
        L_0x0ad7:
            java.lang.String r14 = "_apple-midi"
            int r14 = r13.indexOf(r14)     // Catch:{ Exception -> 0x0b36 }
            r15 = -1
            if (r14 != r15) goto L_0x0ae9
            java.lang.String r14 = "_ws-midi"
            int r14 = r13.indexOf(r14)     // Catch:{ Exception -> 0x0b36 }
            r15 = -1
            if (r14 == r15) goto L_0x0b3b
        L_0x0ae9:
            java.lang.String r14 = "_apple-midi"
            int r13 = r13.indexOf(r14)     // Catch:{ Exception -> 0x0b36 }
            r14 = -1
            if (r13 == r14) goto L_0x0b39
            r13 = 1
        L_0x0af3:
            r0 = r16
            java.lang.String r14 = r0.f278a     // Catch:{ Exception -> 0x0b36 }
            if (r14 == 0) goto L_0x0b19
            r0 = r16
            java.lang.String r14 = r0.f278a     // Catch:{ Exception -> 0x0b36 }
            r0 = r30
            java.lang.String r15 = r0.f323f     // Catch:{ Exception -> 0x0b36 }
            boolean r14 = r14.equalsIgnoreCase(r15)     // Catch:{ Exception -> 0x0b36 }
            if (r14 == 0) goto L_0x0b19
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0b36 }
            r0 = r30
            long r0 = r0.f308c     // Catch:{ Exception -> 0x0b36 }
            r18 = r0
            long r14 = r14 - r18
            r18 = 500(0x1f4, double:2.47E-321)
            int r14 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r14 < 0) goto L_0x0703
        L_0x0b19:
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0b36 }
            r0 = r30
            r0.f308c = r14     // Catch:{ Exception -> 0x0b36 }
            r0 = r16
            java.lang.String r14 = r0.f278a     // Catch:{ Exception -> 0x0b36 }
            r0 = r30
            r0.f323f = r14     // Catch:{ Exception -> 0x0b36 }
            r0 = r30
            java.lang.String r14 = r0.f320e     // Catch:{ Exception -> 0x0b36 }
            r0 = r16
            java.lang.String r15 = r0.f278a     // Catch:{ Exception -> 0x0b36 }
            p004de.humatic.nmj.NMJConfig.m67a((java.lang.String) r14, (int) r13, (java.lang.String) r15)     // Catch:{ Exception -> 0x0b36 }
            goto L_0x0703
        L_0x0b36:
            r13 = move-exception
            goto L_0x09b8
        L_0x0b39:
            r13 = 6
            goto L_0x0af3
        L_0x0b3b:
            java.lang.String r14 = "_mnet-cfg"
            int r13 = r13.indexOf(r14)     // Catch:{ Exception -> 0x0b36 }
            r14 = -1
            if (r13 == r14) goto L_0x0703
            r13 = -1
            r0 = r16
            r0.f277a = r13     // Catch:{ Exception -> 0x0b36 }
            r0 = r30
            de.humatic.nmj.j r13 = r0.f287a     // Catch:{ Exception -> 0x0b36 }
            r14 = 32
            r0 = r30
            int r15 = r0.f319e     // Catch:{ Exception -> 0x0b36 }
            r0 = r16
            r13.mo8142a(r14, r0, r15)     // Catch:{ Exception -> 0x0b36 }
            goto L_0x0703
        L_0x0b5a:
            r4 = 2
            byte r7 = r31[r8]     // Catch:{ all -> 0x030b }
            r7 = r7 & 240(0xf0, float:3.36E-43)
            r11 = 192(0xc0, float:2.69E-43)
            if (r7 == r11) goto L_0x0c13
            int r7 = r8 + 1
            byte r4 = r31[r8]     // Catch:{ all -> 0x030b }
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r8 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r31
            r8.<init>(r0, r7, r4)     // Catch:{ all -> 0x030b }
            int r4 = r4 + 2
            r8 = r7
        L_0x0b73:
            int r4 = r4 + r8
            r0 = r31
            short r7 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            int r4 = r4 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            int r4 = r4 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            int r4 = r4 + 4
            r0 = r31
            short r8 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            int r4 = r4 + 2
            r11 = 1
            if (r7 == r11) goto L_0x0b99
            r11 = 28
            if (r7 != r11) goto L_0x0cc5
        L_0x0b99:
            java.lang.String r11 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r31
            r11.<init>(r0, r4, r8)     // Catch:{ all -> 0x030b }
            r8 = 1
            if (r7 != r8) goto L_0x0c27
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            int r8 = r4 + 1
            byte r4 = r31[r4]     // Catch:{ all -> 0x030b }
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x030b }
            r7.<init>(r4)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = "."
            java.lang.StringBuilder r4 = r7.append(r4)     // Catch:{ all -> 0x030b }
            int r7 = r8 + 1
            byte r8 = r31[r8]     // Catch:{ all -> 0x030b }
            r8 = r8 & 255(0xff, float:3.57E-43)
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x030b }
            java.lang.String r8 = "."
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x030b }
            int r8 = r7 + 1
            byte r7 = r31[r7]     // Catch:{ all -> 0x030b }
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ all -> 0x030b }
            java.lang.String r7 = "."
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ all -> 0x030b }
            int r7 = r8 + 1
            byte r8 = r31[r8]     // Catch:{ all -> 0x030b }
            r8 = r8 & 255(0xff, float:3.57E-43)
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            r0 = r30
            r0.f300b = r4     // Catch:{ all -> 0x030b }
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ all -> 0x030b }
            java.util.Iterator r8 = r4.iterator()     // Catch:{ all -> 0x030b }
        L_0x0c02:
            boolean r4 = r8.hasNext()     // Catch:{ all -> 0x030b }
            if (r4 != 0) goto L_0x0c1a
            r4 = r5
            r5 = r6
            r6 = r7
        L_0x0c0b:
            int r7 = r10 + 1
            r10 = r7
            r8 = r6
            r6 = r5
            r5 = r4
            goto L_0x00ca
        L_0x0c13:
            r0 = r31
            m262a((byte[]) r0, (int) r8)     // Catch:{ all -> 0x030b }
            goto L_0x0b73
        L_0x0c1a:
            java.lang.Object r4 = r8.next()     // Catch:{ all -> 0x030b }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ all -> 0x030b }
            r0 = r30
            java.lang.String r11 = r0.f300b     // Catch:{ all -> 0x030b }
            r4.f281c = r11     // Catch:{ all -> 0x030b }
            goto L_0x0c02
        L_0x0c27:
            java.lang.String r7 = ""
            r0 = r30
            r0.f309c = r7     // Catch:{ all -> 0x030b }
            r7 = 0
            r28 = r4
            r4 = r7
            r7 = r28
        L_0x0c33:
            r8 = 8
            if (r4 < r8) goto L_0x0c65
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r4 == 0) goto L_0x0c53
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r8 = "   ip6Address:"
            r4.<init>(r8)     // Catch:{ all -> 0x030b }
            r0 = r30
            java.lang.String r8 = r0.f309c     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x030b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ all -> 0x030b }
        L_0x0c53:
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ all -> 0x030b }
            java.util.Iterator r8 = r4.iterator()     // Catch:{ all -> 0x030b }
        L_0x0c5b:
            boolean r4 = r8.hasNext()     // Catch:{ all -> 0x030b }
            if (r4 != 0) goto L_0x0cb8
            r4 = r5
            r5 = r6
            r6 = r7
            goto L_0x0c0b
        L_0x0c65:
            r0 = r30
            java.lang.String r8 = r0.f309c     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x030b }
            r11.<init>(r8)     // Catch:{ all -> 0x030b }
            int r8 = r7 + 1
            byte r7 = r31[r7]     // Catch:{ all -> 0x030b }
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = p004de.humatic.nmj.C0484p.m354a((int) r7)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r11 = r11.append(r7)     // Catch:{ all -> 0x030b }
            int r7 = r8 + 1
            byte r8 = r31[r8]     // Catch:{ all -> 0x030b }
            r8 = r8 & 255(0xff, float:3.57E-43)
            java.lang.String r8 = p004de.humatic.nmj.C0484p.m354a((int) r8)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r8 = r11.append(r8)     // Catch:{ all -> 0x030b }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x030b }
            r0 = r30
            r0.f309c = r8     // Catch:{ all -> 0x030b }
            r8 = 7
            if (r4 >= r8) goto L_0x0cb4
            r0 = r30
            java.lang.String r8 = r0.f309c     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x030b }
            r11.<init>(r8)     // Catch:{ all -> 0x030b }
            java.lang.String r8 = ":"
            java.lang.StringBuilder r8 = r11.append(r8)     // Catch:{ all -> 0x030b }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x030b }
            r0 = r30
            r0.f309c = r8     // Catch:{ all -> 0x030b }
        L_0x0cb4:
            int r4 = r4 + 1
            goto L_0x0c33
        L_0x0cb8:
            java.lang.Object r4 = r8.next()     // Catch:{ all -> 0x030b }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ all -> 0x030b }
            r0 = r30
            java.lang.String r11 = r0.f309c     // Catch:{ all -> 0x030b }
            r4.f282d = r11     // Catch:{ all -> 0x030b }
            goto L_0x0c5b
        L_0x0cc5:
            r8 = 33
            if (r7 != r8) goto L_0x1114
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            int r4 = r4 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            int r4 = r4 + 2
            r0 = r31
            short r5 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r6 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x0cf5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r7 = "port.:"
            r6.<init>(r7)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r6 = r6.append(r5)     // Catch:{ all -> 0x030b }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r6)     // Catch:{ all -> 0x030b }
        L_0x0cf5:
            int r6 = r4 + 2
            byte r4 = r31[r6]     // Catch:{ all -> 0x030b }
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r7 = 192(0xc0, float:2.69E-43)
            if (r4 != r7) goto L_0x0d21
            r0 = r31
            java.lang.String r4 = m262a((byte[]) r0, (int) r6)     // Catch:{ all -> 0x030b }
            int r6 = r6 + 2
        L_0x0d07:
            r0 = r30
            boolean r7 = r0.f321e     // Catch:{ all -> 0x030b }
            if (r7 == 0) goto L_0x0c0b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r8 = "   target:"
            r7.<init>(r8)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r7 = r7.append(r4)     // Catch:{ all -> 0x030b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x030b }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r7)     // Catch:{ all -> 0x030b }
            goto L_0x0c0b
        L_0x0d21:
            int r7 = r6 + 1
            byte r4 = r31[r6]     // Catch:{ all -> 0x030b }
            r6 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x030b }
            r0 = r31
            r4.<init>(r0, r7, r6)     // Catch:{ all -> 0x030b }
            int r6 = r6 + 2
            int r6 = r6 + r7
            goto L_0x0d07
        L_0x0d32:
            if (r6 < 0) goto L_0x0105
            r0 = r32
            if (r6 >= r0) goto L_0x0105
            r4 = 2
            byte r5 = r31[r6]     // Catch:{ Exception -> 0x1050 }
            r5 = r5 & 240(0xf0, float:3.36E-43)
            r7 = 192(0xc0, float:2.69E-43)
            if (r5 == r7) goto L_0x0e64
            int r5 = r6 + 1
            byte r4 = r31[r6]     // Catch:{ Exception -> 0x0e81 }
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0e81 }
            r0 = r31
            r6.<init>(r0, r5, r4)     // Catch:{ Exception -> 0x0e81 }
            int r4 = r4 + 2
            r7 = r6
            r6 = r4
        L_0x0d52:
            r4 = 1
            r0 = r22
            if (r0 != r4) goto L_0x0e6f
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ Exception -> 0x0e81 }
            int r4 = r4.size()     // Catch:{ Exception -> 0x0e81 }
            if (r4 <= 0) goto L_0x0e6f
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ Exception -> 0x0e81 }
            r10 = 0
            java.lang.Object r4 = r4.get(r10)     // Catch:{ Exception -> 0x0e81 }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ Exception -> 0x0e81 }
        L_0x0d6c:
            int r5 = r5 + r6
            r0 = r31
            short r6 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 4
            r0 = r31
            short r10 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            r11 = 1
            if (r6 == r11) goto L_0x0d92
            r11 = 28
            if (r6 != r11) goto L_0x0f29
        L_0x0d92:
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ Exception -> 0x0e81 }
            java.util.Iterator r10 = r4.iterator()     // Catch:{ Exception -> 0x0e81 }
        L_0x0d9a:
            boolean r4 = r10.hasNext()     // Catch:{ Exception -> 0x0e81 }
            if (r4 != 0) goto L_0x0e77
            r4 = 1
            if (r6 != r4) goto L_0x0eaf
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0e81 }
            int r6 = r5 + 1
            byte r5 = r31[r5]     // Catch:{ Exception -> 0x1050 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x1050 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x1050 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x1050 }
            java.lang.String r5 = "."
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x1050 }
            int r5 = r6 + 1
            byte r6 = r31[r6]     // Catch:{ Exception -> 0x0e81 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r6 = "."
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x0e81 }
            int r6 = r5 + 1
            byte r5 = r31[r5]     // Catch:{ Exception -> 0x1050 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x1050 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x1050 }
            java.lang.String r5 = "."
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x1050 }
            int r5 = r6 + 1
            byte r6 = r31[r6]     // Catch:{ Exception -> 0x0e81 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0e81 }
            r0 = r30
            r0.f300b = r4     // Catch:{ Exception -> 0x0e81 }
            r0 = r30
            java.lang.String r4 = r0.f300b     // Catch:{ Exception -> 0x1055 }
            java.lang.String r6 = "169.254"
            int r4 = r4.indexOf(r6)     // Catch:{ Exception -> 0x1055 }
            r6 = -1
            if (r4 == r6) goto L_0x0e4f
            r0 = r34
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ Exception -> 0x1055 }
            r4 = r0
            java.net.InetAddress r4 = r4.getAddress()     // Catch:{ Exception -> 0x1055 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x1055 }
            java.lang.String r6 = "192"
            int r4 = r4.indexOf(r6)     // Catch:{ Exception -> 0x1055 }
            r6 = -1
            if (r4 == r6) goto L_0x0e4f
            r0 = r34
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ Exception -> 0x1055 }
            r4 = r0
            java.net.InetAddress r4 = r4.getAddress()     // Catch:{ Exception -> 0x1055 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x1055 }
            r6 = 1
            java.lang.String r4 = r4.substring(r6)     // Catch:{ Exception -> 0x1055 }
            r0 = r30
            r0.f300b = r4     // Catch:{ Exception -> 0x1055 }
            r0 = r30
            boolean r4 = r0.f321e     // Catch:{ Exception -> 0x1055 }
            if (r4 == 0) goto L_0x0e4f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x1055 }
            java.lang.String r6 = "   ip4 corrected: "
            r4.<init>(r6)     // Catch:{ Exception -> 0x1055 }
            r0 = r30
            java.lang.String r6 = r0.f300b     // Catch:{ Exception -> 0x1055 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x1055 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x1055 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r4)     // Catch:{ Exception -> 0x1055 }
        L_0x0e4f:
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ Exception -> 0x0e81 }
            java.util.Iterator r6 = r4.iterator()     // Catch:{ Exception -> 0x0e81 }
        L_0x0e57:
            boolean r4 = r6.hasNext()     // Catch:{ Exception -> 0x0e81 }
            if (r4 != 0) goto L_0x0ea2
            r4 = r5
        L_0x0e5e:
            int r5 = r8 + 1
            r8 = r5
            r6 = r4
            goto L_0x0101
        L_0x0e64:
            r0 = r31
            java.lang.String r5 = m262a((byte[]) r0, (int) r6)     // Catch:{ Exception -> 0x1050 }
            r7 = r5
            r5 = r6
            r6 = r4
            goto L_0x0d52
        L_0x0e6f:
            r0 = r30
            de.humatic.nmj.g r4 = r0.m259a((java.lang.String) r7)     // Catch:{ Exception -> 0x0e81 }
            goto L_0x0d6c
        L_0x0e77:
            java.lang.Object r4 = r10.next()     // Catch:{ Exception -> 0x0e81 }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ Exception -> 0x0e81 }
            r4.f283e = r7     // Catch:{ Exception -> 0x0e81 }
            goto L_0x0d9a
        L_0x0e81:
            r4 = move-exception
            r28 = r4
            r4 = r5
            r5 = r28
        L_0x0e87:
            r5.printStackTrace()     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x030b }
            java.lang.String r6 = "at "
            r5.<init>(r6)     // Catch:{ all -> 0x030b }
            java.lang.StringBuilder r5 = r5.append(r4)     // Catch:{ all -> 0x030b }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x030b }
            r6 = 0
            r0 = r31
            r1 = r32
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r5, (byte[]) r0, (int) r6, (int) r1)     // Catch:{ all -> 0x030b }
            goto L_0x0e5e
        L_0x0ea2:
            java.lang.Object r4 = r6.next()     // Catch:{ Exception -> 0x0e81 }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ Exception -> 0x0e81 }
            r0 = r30
            java.lang.String r7 = r0.f300b     // Catch:{ Exception -> 0x0e81 }
            r4.f281c = r7     // Catch:{ Exception -> 0x0e81 }
            goto L_0x0e57
        L_0x0eaf:
            java.lang.String r4 = ""
            r0 = r30
            r0.f309c = r4     // Catch:{ Exception -> 0x0e81 }
            r4 = 0
        L_0x0eb6:
            r6 = 8
            if (r4 < r6) goto L_0x0eca
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ Exception -> 0x0e81 }
            java.util.Iterator r6 = r4.iterator()     // Catch:{ Exception -> 0x0e81 }
        L_0x0ec2:
            boolean r4 = r6.hasNext()     // Catch:{ Exception -> 0x0e81 }
            if (r4 != 0) goto L_0x0f1c
            r4 = r5
            goto L_0x0e5e
        L_0x0eca:
            r0 = r30
            java.lang.String r6 = r0.f309c     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0e81 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x0e81 }
            int r6 = r5 + 1
            byte r5 = r31[r5]     // Catch:{ Exception -> 0x1050 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r5 = p004de.humatic.nmj.C0484p.m354a((int) r5)     // Catch:{ Exception -> 0x1050 }
            java.lang.StringBuilder r7 = r7.append(r5)     // Catch:{ Exception -> 0x1050 }
            int r5 = r6 + 1
            byte r6 = r31[r6]     // Catch:{ Exception -> 0x0e81 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            java.lang.String r6 = p004de.humatic.nmj.C0484p.m354a((int) r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0e81 }
            r0 = r30
            r0.f309c = r6     // Catch:{ Exception -> 0x0e81 }
            r6 = 7
            if (r4 >= r6) goto L_0x0f19
            r0 = r30
            java.lang.String r6 = r0.f309c     // Catch:{ Exception -> 0x0e81 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0e81 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r6 = ":"
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ Exception -> 0x0e81 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0e81 }
            r0 = r30
            r0.f309c = r6     // Catch:{ Exception -> 0x0e81 }
        L_0x0f19:
            int r4 = r4 + 1
            goto L_0x0eb6
        L_0x0f1c:
            java.lang.Object r4 = r6.next()     // Catch:{ Exception -> 0x0e81 }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ Exception -> 0x0e81 }
            r0 = r30
            java.lang.String r7 = r0.f309c     // Catch:{ Exception -> 0x0e81 }
            r4.f282d = r7     // Catch:{ Exception -> 0x0e81 }
            goto L_0x0ec2
        L_0x0f29:
            r7 = 33
            if (r6 != r7) goto L_0x0f8f
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            r0 = r31
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            r0 = r31
            short r6 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            if (r4 == 0) goto L_0x0f47
            r4.f279b = r6     // Catch:{ Exception -> 0x0e81 }
        L_0x0f47:
            byte r4 = r31[r5]     // Catch:{ Exception -> 0x0e81 }
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r6 = 192(0xc0, float:2.69E-43)
            if (r4 != r6) goto L_0x0f79
            r0 = r31
            java.lang.String r4 = m262a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            r28 = r4
            r4 = r5
            r5 = r28
        L_0x0f5c:
            r0 = r30
            boolean r6 = r0.f321e     // Catch:{ Exception -> 0x0f76 }
            if (r6 == 0) goto L_0x0e5e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0f76 }
            java.lang.String r7 = "   target: "
            r6.<init>(r7)     // Catch:{ Exception -> 0x0f76 }
            java.lang.StringBuilder r5 = r6.append(r5)     // Catch:{ Exception -> 0x0f76 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0f76 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r5)     // Catch:{ Exception -> 0x0f76 }
            goto L_0x0e5e
        L_0x0f76:
            r5 = move-exception
            goto L_0x0e87
        L_0x0f79:
            int r6 = r5 + 1
            byte r4 = r31[r5]     // Catch:{ Exception -> 0x1050 }
            r5 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x1050 }
            r0 = r31
            r4.<init>(r0, r6, r5)     // Catch:{ Exception -> 0x1050 }
            int r5 = r5 + 2
            int r5 = r5 + r6
            r28 = r4
            r4 = r5
            r5 = r28
            goto L_0x0f5c
        L_0x0f8f:
            r4 = 47
            if (r6 != r4) goto L_0x0fb0
            r0 = r31
            m262a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            r0 = r31
            short r4 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)     // Catch:{ Exception -> 0x0e81 }
            int r5 = r5 + 2
            int r6 = r5 + r4
            r0 = r30
            byte[] r7 = r0.f313c     // Catch:{ Exception -> 0x0e81 }
            r0 = r31
            p004de.humatic.nmj.C0484p.m350a((byte[]) r0, (int) r5, (int) r6, (byte[]) r7)     // Catch:{ Exception -> 0x0e81 }
            int r4 = r4 + r5
            goto L_0x0e5e
        L_0x0fb0:
            int r4 = r5 + r10
            goto L_0x0e5e
        L_0x0fb4:
            r0 = r30
            java.util.Vector<de.humatic.nmj.g> r4 = r0.f304b     // Catch:{ all -> 0x030b }
            java.util.Iterator r7 = r4.iterator()     // Catch:{ all -> 0x030b }
            r5 = r9
        L_0x0fbd:
            boolean r4 = r7.hasNext()     // Catch:{ all -> 0x030b }
            if (r4 != 0) goto L_0x0fc6
        L_0x0fc3:
            r4 = r5
            goto L_0x0023
        L_0x0fc6:
            java.lang.Object r4 = r7.next()     // Catch:{ all -> 0x030b }
            de.humatic.nmj.g r4 = (p004de.humatic.nmj.C0467g) r4     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r6 = r0.f312c     // Catch:{ all -> 0x030b }
            if (r6 != 0) goto L_0x0fc3
            java.lang.String r6 = r4.f278a     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x100b
            java.lang.String r6 = r4.f283e     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x100b
            java.lang.String r6 = r4.f280b     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x100b
            java.lang.String r6 = r4.f281c     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x100b
            int r6 = r4.f279b     // Catch:{ all -> 0x030b }
            if (r6 <= 0) goto L_0x100b
            r6 = 1
        L_0x0fe7:
            if (r6 == 0) goto L_0x0fbd
            java.lang.String r6 = r4.f280b     // Catch:{ all -> 0x030b }
            java.lang.String r8 = "_apple-midi"
            boolean r6 = r6.equalsIgnoreCase(r8)     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x100d
            java.lang.String r6 = r4.f278a     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r6 = r0.m259a((java.lang.String) r6)     // Catch:{ all -> 0x030b }
            if (r6 != 0) goto L_0x0fbd
            r0 = r30
            de.humatic.nmj.j r5 = r0.f287a     // Catch:{ all -> 0x030b }
            r6 = 1
            r0 = r30
            int r8 = r0.f319e     // Catch:{ all -> 0x030b }
            r5.mo8142a(r6, r4, r8)     // Catch:{ all -> 0x030b }
            r5 = 1
            goto L_0x0fbd
        L_0x100b:
            r6 = 0
            goto L_0x0fe7
        L_0x100d:
            java.lang.String r6 = r4.f280b     // Catch:{ all -> 0x030b }
            java.lang.String r8 = "_mnet-cfg"
            boolean r6 = r6.equalsIgnoreCase(r8)     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x102a
            r5 = 0
            r4.f277a = r5     // Catch:{ all -> 0x030b }
            r0 = r30
            de.humatic.nmj.j r5 = r0.f287a     // Catch:{ all -> 0x030b }
            r6 = 32
            r0 = r30
            int r8 = r0.f319e     // Catch:{ all -> 0x030b }
            r5.mo8142a(r6, r4, r8)     // Catch:{ all -> 0x030b }
            r5 = 32
            goto L_0x0fbd
        L_0x102a:
            java.lang.String r6 = r4.f280b     // Catch:{ all -> 0x030b }
            java.lang.String r8 = "_ws-midi"
            boolean r6 = r6.equalsIgnoreCase(r8)     // Catch:{ all -> 0x030b }
            if (r6 == 0) goto L_0x0fbd
            java.lang.String r6 = r4.f278a     // Catch:{ all -> 0x030b }
            r0 = r30
            boolean r6 = r0.m259a((java.lang.String) r6)     // Catch:{ all -> 0x030b }
            if (r6 != 0) goto L_0x0fbd
            r5 = 0
            r4.f277a = r5     // Catch:{ all -> 0x030b }
            r0 = r30
            de.humatic.nmj.j r5 = r0.f287a     // Catch:{ all -> 0x030b }
            r6 = 6
            r0 = r30
            int r8 = r0.f319e     // Catch:{ all -> 0x030b }
            r5.mo8142a(r6, r4, r8)     // Catch:{ all -> 0x030b }
            r5 = 6
            goto L_0x0fbd
        L_0x1050:
            r4 = move-exception
            r5 = r4
            r4 = r6
            goto L_0x0e87
        L_0x1055:
            r4 = move-exception
            goto L_0x0e4f
        L_0x1058:
            r4 = move-exception
            r7 = r12
            r10 = r14
            r12 = r8
            r8 = r13
            r13 = r4
            r4 = r11
            r11 = r15
            r28 = r5
            r5 = r9
            r9 = r6
            r6 = r28
            goto L_0x09b8
        L_0x1068:
            r4 = move-exception
            r5 = r9
            r7 = r12
            r9 = r6
            r12 = r8
            r8 = r13
            r6 = r10
            r13 = r4
            r10 = r14
            r4 = r11
            r11 = r15
            goto L_0x09b8
        L_0x1075:
            r4 = move-exception
            r5 = r9
            r8 = r13
            r13 = r4
            r9 = r6
            r4 = r11
            r6 = r10
            r10 = r14
            r11 = r15
            r28 = r12
            r12 = r7
            r7 = r28
            goto L_0x09b8
        L_0x1085:
            r4 = move-exception
            r6 = r10
            r8 = r13
            r13 = r4
            r10 = r14
            r4 = r11
            r11 = r15
            r28 = r5
            r5 = r9
            r9 = r28
            r29 = r12
            r12 = r7
            r7 = r29
            goto L_0x09b8
        L_0x1098:
            r4 = move-exception
            r5 = r9
            r8 = r13
            r13 = r4
            r9 = r10
            r4 = r11
            r10 = r14
            r11 = r15
            r28 = r12
            r12 = r7
            r7 = r28
            goto L_0x09b8
        L_0x10a7:
            r4 = move-exception
            r5 = r9
            r7 = r12
            r9 = r10
            r12 = r8
            r8 = r13
            r10 = r14
            r13 = r4
            r4 = r11
            r11 = r15
            goto L_0x09b8
        L_0x10b3:
            r4 = move-exception
            r13 = r4
            r5 = r9
            r4 = r11
            r9 = r10
            r10 = r14
            r11 = r15
            r28 = r12
            r12 = r7
            r7 = r28
            goto L_0x09b8
        L_0x10c1:
            r4 = move-exception
            r5 = r9
            r7 = r12
            r9 = r10
            r12 = r13
            r13 = r4
            r10 = r14
            r4 = r11
            r11 = r15
            goto L_0x09b8
        L_0x10cc:
            r4 = move-exception
            r9 = r10
            r12 = r13
            r13 = r4
            r10 = r14
            r4 = r11
            r11 = r15
            goto L_0x09b8
        L_0x10d5:
            r4 = move-exception
            r13 = r4
            r7 = r12
            r4 = r11
            r12 = r10
            r11 = r15
            r10 = r14
            goto L_0x09b8
        L_0x10de:
            r4 = move-exception
            r7 = r12
            r10 = r14
            r12 = r13
            r13 = r4
            r4 = r11
            r11 = r15
            goto L_0x09b8
        L_0x10e7:
            r4 = move-exception
            r7 = r12
            r12 = r13
            r13 = r4
            r4 = r11
            r11 = r14
            goto L_0x09b8
        L_0x10ef:
            r4 = move-exception
            r13 = r4
            r7 = r12
            r4 = r11
            r12 = r15
            r11 = r14
            goto L_0x09b8
        L_0x10f7:
            r4 = move-exception
            r7 = r13
            r13 = r4
            r4 = r11
            r11 = r14
            goto L_0x09b8
        L_0x10fe:
            r4 = move-exception
            r13 = r4
            r7 = r12
            r4 = r11
            r12 = r14
            r11 = r15
            goto L_0x09b8
        L_0x1106:
            r4 = move-exception
            r13 = r4
            r4 = r11
            r11 = r15
            r28 = r12
            r12 = r7
            r7 = r28
            goto L_0x09b8
        L_0x1111:
            r4 = move-exception
            goto L_0x0987
        L_0x1114:
            r28 = r5
            r5 = r6
            r6 = r4
            r4 = r28
            goto L_0x0c0b
        L_0x111c:
            r4 = r11
            r11 = r15
            r28 = r7
            r7 = r12
            r12 = r13
            r13 = r28
            goto L_0x06e8
        L_0x1126:
            r16 = r17
            r4 = r11
            r11 = r15
            r28 = r12
            r12 = r7
            r7 = r28
            goto L_0x06e8
        L_0x1131:
            r4 = r7
            goto L_0x087b
        L_0x1134:
            r4 = r7
            r7 = r13
            goto L_0x087d
        L_0x1138:
            r28 = r7
            r7 = r13
            r13 = r28
            goto L_0x080b
        L_0x113f:
            r17 = r16
            goto L_0x07ef
        L_0x1143:
            r16 = r15
            r4 = r11
            r11 = r14
            r28 = r7
            r7 = r12
            r12 = r13
            r13 = r28
            goto L_0x06e8
        L_0x114f:
            r15 = r4
            goto L_0x06b8
        L_0x1152:
            r28 = r13
            r13 = r12
            r12 = r28
            goto L_0x069c
        L_0x1159:
            r12 = r7
            r9 = r10
            r7 = r17
            r10 = r13
            r17 = r4
            goto L_0x061a
        L_0x1162:
            r5 = r9
            r7 = r12
            goto L_0x0607
        L_0x1166:
            r8 = r13
            r13 = r7
            goto L_0x05f6
        L_0x116a:
            r18 = r5
            r7 = r8
            goto L_0x05c6
        L_0x116f:
            r18 = r5
            goto L_0x05c6
        L_0x1173:
            r4 = r18
            r7 = r8
            goto L_0x0576
        L_0x1178:
            r6 = r7
            goto L_0x0534
        L_0x117b:
            r5 = r6
            r6 = r7
            goto L_0x0534
        L_0x117f:
            r10 = r5
            goto L_0x04ff
        L_0x1182:
            r10 = r6
            r7 = r8
            r6 = r5
            goto L_0x0538
        L_0x1187:
            r28 = r8
            r8 = r10
            r10 = r28
            goto L_0x01b3
        L_0x118e:
            r16 = r8
            goto L_0x0180
        L_0x1192:
            r28 = r4
            r4 = r7
            r7 = r28
            goto L_0x023f
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0468h.m256a(byte[], int, boolean, java.net.SocketAddress):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: byte} */
    /* JADX WARNING: type inference failed for: r6v20, types: [int] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m274a(byte[] r20, int r21, java.net.SocketAddress r22) {
        /*
            r19 = this;
            r2 = 4
            r0 = r20
            short r11 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r2)
            int r2 = r21 + 2
            r3 = 6
            r0 = r20
            short r3 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r3)
            int r2 = r2 + 2
            r4 = 8
            r0 = r20
            short r12 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)
            int r2 = r2 + 2
            r4 = 10
            r0 = r20
            short r4 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)
            int r6 = r2 + 2
            r0 = r19
            boolean r2 = r0.f321e
            if (r2 == 0) goto L_0x005c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "readQuery, q: "
            r2.<init>(r5)
            java.lang.StringBuilder r2 = r2.append(r11)
            java.lang.String r5 = ", a: "
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ", auth: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r12)
            java.lang.String r3 = ", add: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r2)
        L_0x005c:
            r0 = r19
            java.lang.String[] r2 = r0.f297a
            if (r2 == 0) goto L_0x0069
            r0 = r19
            java.lang.String[] r2 = r0.f297a
            int r2 = r2.length
            if (r2 >= r11) goto L_0x006f
        L_0x0069:
            java.lang.String[] r2 = new java.lang.String[r11]
            r0 = r19
            r0.f297a = r2
        L_0x006f:
            java.lang.String r5 = ""
            java.lang.String r4 = ""
            java.lang.String r3 = ""
            r9 = 0
            r8 = 0
            r2 = 0
            r10 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
        L_0x007d:
            if (r10 < r11) goto L_0x0108
            if (r12 <= 0) goto L_0x0107
            r3 = 0
            r4 = 0
            r2 = 0
            r7 = r5
            r5 = r4
            r4 = r9
            r9 = r2
        L_0x0088:
            if (r9 < r12) goto L_0x0437
            if (r8 == 0) goto L_0x0107
            if (r5 != 0) goto L_0x0094
            r0 = r19
            java.lang.String r2 = r0.f309c     // Catch:{ Exception -> 0x0620 }
            if (r2 == 0) goto L_0x0107
        L_0x0094:
            if (r4 == 0) goto L_0x0107
            if (r3 <= 0) goto L_0x0107
            r0 = r19
            boolean r2 = r0.f321e     // Catch:{ Exception -> 0x0620 }
            if (r2 == 0) goto L_0x00d6
            r2 = -1
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0620 }
            java.lang.String r7 = "rtpMIDI in auth.nameserver: "
            r6.<init>(r7)     // Catch:{ Exception -> 0x0620 }
            r0 = r19
            java.lang.String[] r7 = r0.f297a     // Catch:{ Exception -> 0x0620 }
            r8 = 0
            r7 = r7[r8]     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r7 = " "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r6 = r6.append(r4)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r7 = " "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r6 = r6.append(r5)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r7 = " "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0620 }
            p004de.humatic.nmj.C0484p.logln(r2, r6)     // Catch:{ Exception -> 0x0620 }
        L_0x00d6:
            de.humatic.nmj.g r2 = new de.humatic.nmj.g     // Catch:{ Exception -> 0x0620 }
            java.lang.String r6 = "_apple-midi"
            r7 = -1
            r2.<init>(r6, r7)     // Catch:{ Exception -> 0x0620 }
            r2.f281c = r5     // Catch:{ Exception -> 0x0620 }
            r0 = r19
            java.lang.String r5 = r0.f309c     // Catch:{ Exception -> 0x0620 }
            r2.f282d = r5     // Catch:{ Exception -> 0x0620 }
            r0 = r19
            java.lang.String[] r5 = r0.f297a     // Catch:{ Exception -> 0x0620 }
            r6 = 0
            r5 = r5[r6]     // Catch:{ Exception -> 0x0620 }
            if (r5 == 0) goto L_0x00f6
            r0 = r19
            java.lang.String[] r4 = r0.f297a     // Catch:{ Exception -> 0x0620 }
            r5 = 0
            r4 = r4[r5]     // Catch:{ Exception -> 0x0620 }
        L_0x00f6:
            r2.mo8126a(r4)     // Catch:{ Exception -> 0x0620 }
            r2.f279b = r3     // Catch:{ Exception -> 0x0620 }
            r0 = r19
            de.humatic.nmj.j r3 = r0.f287a     // Catch:{ Exception -> 0x0620 }
            r4 = 1
            r0 = r19
            int r5 = r0.f319e     // Catch:{ Exception -> 0x0620 }
            r3.mo8142a(r4, r2, r5)     // Catch:{ Exception -> 0x0620 }
        L_0x0107:
            return
        L_0x0108:
            byte r6 = r20[r5]
            r6 = r6 & 240(0xf0, float:3.36E-43)
            r7 = 192(0xc0, float:2.69E-43)
            if (r6 == r7) goto L_0x0246
            if (r10 != 0) goto L_0x065b
            int r4 = r5 + 1
            byte r4 = r20[r4]
            r6 = 95
            if (r4 == r6) goto L_0x065b
            int r4 = r5 + 1
            byte r5 = r20[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            r0 = r19
            java.lang.String[] r6 = r0.f297a
            java.lang.String r7 = new java.lang.String
            r0 = r20
            r7.<init>(r0, r4, r5)
            r6[r10] = r7
            r0 = r19
            boolean r6 = r0.f321e
            if (r6 == 0) goto L_0x0147
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "server "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r5)
            java.lang.String r6 = r6.toString()
            r0 = r20
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r6, (byte[]) r0, (int) r4, (int) r5)
        L_0x0147:
            int r4 = r4 + r5
        L_0x0148:
            r5 = 2
            byte r6 = r20[r4]
            r6 = r6 & 240(0xf0, float:3.36E-43)
            r7 = 192(0xc0, float:2.69E-43)
            if (r6 == r7) goto L_0x0221
            int r5 = r4 + 1
            byte r4 = r20[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = new java.lang.String
            r0 = r20
            r7.<init>(r0, r5, r4)
            java.lang.String r6 = "_services"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0174
            int r4 = r4 + r5
            int r5 = r4 + 1
            byte r4 = r20[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = new java.lang.String
            r0 = r20
            r7.<init>(r0, r5, r4)
        L_0x0174:
            r0 = r19
            boolean r6 = r0.f321e
            if (r6 == 0) goto L_0x0181
            java.lang.String r6 = "serviceName"
            r0 = r20
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r6, (byte[]) r0, (int) r5, (int) r4)
        L_0x0181:
            int r6 = r5 + r4
            byte r4 = r20[r6]
            if (r4 == 0) goto L_0x0657
            r3 = 2
            byte r4 = r20[r6]
            r4 = r4 & 240(0xf0, float:3.36E-43)
            r5 = 192(0xc0, float:2.69E-43)
            if (r4 == r5) goto L_0x022e
            int r5 = r6 + 1
            byte r3 = r20[r6]
            r3 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r4 = new java.lang.String
            r0 = r20
            r4.<init>(r0, r5, r3)
            java.lang.String r4 = r4.trim()
        L_0x01a1:
            r0 = r19
            boolean r6 = r0.f321e
            if (r6 == 0) goto L_0x01ae
            java.lang.String r6 = "protocol"
            r0 = r20
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r6, (byte[]) r0, (int) r5, (int) r3)
        L_0x01ae:
            int r5 = r5 + r3
            r6 = r4
        L_0x01b0:
            byte r3 = r20[r5]
            if (r3 == 0) goto L_0x0654
            r2 = 2
            byte r3 = r20[r5]
            r3 = r3 & 240(0xf0, float:3.36E-43)
            r4 = 192(0xc0, float:2.69E-43)
            if (r3 == r4) goto L_0x0237
            int r4 = r5 + 1
            byte r2 = r20[r5]
            r2 = r2 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = new java.lang.String
            r0 = r20
            r3.<init>(r0, r4, r2)
        L_0x01ca:
            r0 = r19
            boolean r5 = r0.f321e
            if (r5 == 0) goto L_0x01d7
            java.lang.String r5 = "area"
            r0 = r20
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r5, (byte[]) r0, (int) r4, (int) r2)
        L_0x01d7:
            r5 = 2
            int r2 = java.lang.Math.max(r2, r5)
            int r5 = r4 + r2
            r2 = r3
            r3 = r5
        L_0x01e0:
            if (r10 != 0) goto L_0x065e
            byte r4 = r20[r3]
            if (r4 != 0) goto L_0x023f
            int r3 = r3 + 1
            r4 = r7
            r5 = r3
            r3 = r6
        L_0x01eb:
            r0 = r20
            short r6 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)
            r0 = r19
            boolean r7 = r0.f321e
            if (r7 == 0) goto L_0x01ff
            java.lang.String r7 = "type"
            r13 = 2
            r0 = r20
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r7, (byte[]) r0, (int) r5, (int) r13)
        L_0x01ff:
            int r5 = r5 + 2
            r0 = r20
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r5)
            r0 = r19
            boolean r7 = r0.f321e
            if (r7 == 0) goto L_0x0214
            java.lang.String r7 = "cl"
            r13 = 2
            r0 = r20
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r7, (byte[]) r0, (int) r5, (int) r13)
        L_0x0214:
            int r7 = r5 + 2
            switch(r6) {
                case 1: goto L_0x0263;
                case 12: goto L_0x0289;
                case 16: goto L_0x031c;
                case 28: goto L_0x0263;
                case 33: goto L_0x037b;
                case 255: goto L_0x03f5;
                default: goto L_0x0219;
            }
        L_0x0219:
            r5 = r8
        L_0x021a:
            int r6 = r10 + 1
            r10 = r6
            r8 = r5
            r5 = r7
            goto L_0x007d
        L_0x0221:
            r0 = r20
            java.lang.String r7 = m262a((byte[]) r0, (int) r4)
            r18 = r5
            r5 = r4
            r4 = r18
            goto L_0x0174
        L_0x022e:
            r0 = r20
            java.lang.String r4 = m262a((byte[]) r0, (int) r6)
            r5 = r6
            goto L_0x01a1
        L_0x0237:
            r0 = r20
            java.lang.String r3 = m262a((byte[]) r0, (int) r5)
            r4 = r5
            goto L_0x01ca
        L_0x023f:
            byte r4 = r20[r3]
            int r3 = r3 + r4
            r4 = r7
            r5 = r3
            r3 = r6
            goto L_0x01eb
        L_0x0246:
            r0 = r19
            java.lang.String[] r6 = r0.f297a
            r0 = r20
            java.lang.String r7 = m262a((byte[]) r0, (int) r5)
            r6[r10] = r7
            r0 = r19
            boolean r6 = r0.f321e
            if (r6 == 0) goto L_0x0260
            java.lang.String r6 = "server"
            r7 = 2
            r0 = r20
            p004de.humatic.nmj.C0484p.m381a((java.lang.String) r6, (byte[]) r0, (int) r5, (int) r7)
        L_0x0260:
            int r5 = r5 + 2
            goto L_0x01eb
        L_0x0263:
            r0 = r19
            boolean r5 = r0.f294a
            if (r5 == 0) goto L_0x0219
            r0 = r19
            byte[] r5 = r0.f318d     // Catch:{ Exception -> 0x027e }
            r0 = r19
            byte[] r13 = r0.f318d     // Catch:{ Exception -> 0x027e }
            r0 = r19
            int r6 = r0.m285b((int) r6, (byte[]) r13)     // Catch:{ Exception -> 0x027e }
            r0 = r19
            r0.m262a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x027e }
            r5 = r8
            goto L_0x021a
        L_0x027e:
            r5 = move-exception
            r5.printStackTrace()
            java.lang.String r5 = "failed to send host resolver"
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r5)
            r5 = r8
            goto L_0x021a
        L_0x0289:
            boolean r5 = m293b((java.lang.String) r4)
            if (r5 == 0) goto L_0x02bf
            r0 = r19
            byte[] r5 = r0.f318d     // Catch:{ Exception -> 0x02a5 }
            r0 = r19
            byte[] r6 = r0.f318d     // Catch:{ Exception -> 0x02a5 }
            r0 = r19
            int r6 = r0.m288b((java.lang.String) r4, (byte[]) r6)     // Catch:{ Exception -> 0x02a5 }
            r0 = r19
            r0.m262a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x02a5 }
            r5 = r8
            goto L_0x021a
        L_0x02a5:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r13 = "failed to send PTR response "
            r6.<init>(r13)
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r5 = r5.toString()
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r5)
            r5 = r8
            goto L_0x021a
        L_0x02bf:
            java.lang.String r5 = "_dns-sd"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0219
            r0 = r19
            boolean r5 = r0.f294a
            if (r5 == 0) goto L_0x0219
            r0 = r19
            byte[] r5 = r0.f318d     // Catch:{ Exception -> 0x0302 }
            java.lang.String r6 = "_apple-midi"
            java.lang.String r13 = "_udp"
            java.lang.String r14 = "local"
            r0 = r19
            byte[] r15 = r0.f318d     // Catch:{ Exception -> 0x0302 }
            r0 = r19
            int r6 = r0.m296c(r6, r13, r14, r15)     // Catch:{ Exception -> 0x0302 }
            r0 = r19
            r0.m262a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x0302 }
            r0 = r19
            byte[] r5 = r0.f318d     // Catch:{ Exception -> 0x0302 }
            java.lang.String r6 = "_ws-midi"
            java.lang.String r13 = "_tcp"
            java.lang.String r14 = "local"
            r0 = r19
            byte[] r15 = r0.f318d     // Catch:{ Exception -> 0x0302 }
            r0 = r19
            int r6 = r0.m296c(r6, r13, r14, r15)     // Catch:{ Exception -> 0x0302 }
            r0 = r19
            r0.m262a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x0302 }
            r5 = r8
            goto L_0x021a
        L_0x0302:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r13 = "failed to send servicelist response "
            r6.<init>(r13)
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r5 = r5.toString()
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r5)
            r5 = r8
            goto L_0x021a
        L_0x031c:
            r0 = r19
            java.lang.String[] r5 = r0.f297a
            r6 = 0
            r5 = r5[r6]
            r0 = r19
            boolean r5 = r0.m259a((java.lang.String) r5)
            if (r5 == 0) goto L_0x036f
            r0 = r19
            byte[] r5 = r0.f318d     // Catch:{ Exception -> 0x0348 }
            r0 = r19
            java.lang.String[] r6 = r0.f297a     // Catch:{ Exception -> 0x0348 }
            r13 = 0
            r6 = r6[r13]     // Catch:{ Exception -> 0x0348 }
            r0 = r19
            byte[] r13 = r0.f318d     // Catch:{ Exception -> 0x0348 }
            r0 = r19
            int r6 = r0.m287b(r6, r3, r2, r13)     // Catch:{ Exception -> 0x0348 }
            r0 = r19
            r0.m262a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x0348 }
            r5 = r8
            goto L_0x021a
        L_0x0348:
            r5 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "failed to send TXT resolver for "
            r5.<init>(r6)
            r0 = r19
            java.lang.String[] r6 = r0.f297a
            r13 = 0
            r6 = r6[r13]
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = ", "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r5 = r5.toString()
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r5)
            r5 = r8
            goto L_0x021a
        L_0x036f:
            java.lang.String r5 = "_apple-midi"
            int r5 = r4.indexOf(r5)
            r6 = -1
            if (r5 == r6) goto L_0x0219
            r5 = 1
            goto L_0x021a
        L_0x037b:
            r0 = r19
            java.lang.String[] r5 = r0.f297a
            r6 = 0
            r5 = r5[r6]
            r0 = r19
            boolean r5 = r0.m259a((java.lang.String) r5)
            if (r5 == 0) goto L_0x0219
            r0 = r19
            boolean r5 = r0.f321e
            if (r5 == 0) goto L_0x03b4
            r5 = 4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r13 = "SRV resolve request for: "
            r6.<init>(r13)
            r0 = r19
            java.lang.String[] r13 = r0.f297a
            r14 = 0
            r13 = r13[r14]
            java.lang.StringBuilder r6 = r6.append(r13)
            java.lang.String r13 = " "
            java.lang.StringBuilder r6 = r6.append(r13)
            java.lang.StringBuilder r6 = r6.append(r4)
            java.lang.String r6 = r6.toString()
            p004de.humatic.nmj.C0484p.logln(r5, r6)
        L_0x03b4:
            r0 = r19
            byte[] r5 = r0.f318d     // Catch:{ Exception -> 0x03ce }
            java.lang.String r6 = "_udp"
            java.lang.String r13 = "local"
            r0 = r19
            byte[] r14 = r0.f318d     // Catch:{ Exception -> 0x03ce }
            r0 = r19
            int r6 = r0.m254a((java.lang.String) r4, (java.lang.String) r6, (java.lang.String) r13, (byte[]) r14)     // Catch:{ Exception -> 0x03ce }
            r0 = r19
            r0.m262a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x03ce }
            r5 = r8
            goto L_0x021a
        L_0x03ce:
            r5 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "failed to send SRV resolver for "
            r5.<init>(r6)
            r0 = r19
            java.lang.String[] r6 = r0.f297a
            r13 = 0
            r6 = r6[r13]
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = ", "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r5 = r5.toString()
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r5)
            r5 = r8
            goto L_0x021a
        L_0x03f5:
            long r14 = java.lang.System.currentTimeMillis()
            r0 = r19
            long r0 = r0.f285a
            r16 = r0
            long r14 = r14 - r16
            r16 = 2000(0x7d0, double:9.88E-321)
            int r5 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r5 < 0) goto L_0x0219
            long r14 = java.lang.System.currentTimeMillis()
            r0 = r19
            r0.f285a = r14
            r5 = 0
        L_0x0410:
            r0 = r19
            de.humatic.nmj.h$c[] r6 = r0.f296a
            int r6 = r6.length
            if (r5 >= r6) goto L_0x0219
            r6 = 0
            r0 = r19
            byte[] r6 = r0.mo8132a((int) r5, (boolean) r6)     // Catch:{ Exception -> 0x0432 }
            r0 = r19
            r0.m272a((byte[]) r6)     // Catch:{ Exception -> 0x0432 }
            r6 = 0
            r0 = r19
            byte[] r6 = r0.mo8132a((int) r5, (boolean) r6)     // Catch:{ Exception -> 0x0432 }
            r0 = r19
            r0.m291b((byte[]) r6)     // Catch:{ Exception -> 0x0432 }
        L_0x042f:
            int r5 = r5 + 1
            goto L_0x0410
        L_0x0432:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x042f
        L_0x0437:
            r2 = 2
            byte r6 = r20[r7]     // Catch:{ Exception -> 0x0620 }
            r6 = r6 & 240(0xf0, float:3.36E-43)
            r10 = 192(0xc0, float:2.69E-43)
            if (r6 == r10) goto L_0x0535
            int r6 = r7 + 1
            byte r2 = r20[r7]     // Catch:{ Exception -> 0x0620 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x0620 }
            r0 = r20
            r7.<init>(r0, r6, r2)     // Catch:{ Exception -> 0x0620 }
        L_0x044d:
            int r2 = r2 + r6
            byte r6 = r20[r2]     // Catch:{ Exception -> 0x0620 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 >> 4
            r7 = 12
            if (r6 != r7) goto L_0x045a
            int r2 = r2 + 2
        L_0x045a:
            r0 = r20
            short r6 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r2)     // Catch:{ Exception -> 0x0620 }
            int r2 = r2 + 2
            r0 = r20
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r2)     // Catch:{ Exception -> 0x0620 }
            int r2 = r2 + 2
            r0 = r20
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r2)     // Catch:{ Exception -> 0x0620 }
            int r2 = r2 + 4
            r0 = r20
            short r7 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r2)     // Catch:{ Exception -> 0x0620 }
            int r2 = r2 + 2
            r10 = 1
            if (r6 == r10) goto L_0x047f
            r10 = 28
            if (r6 != r10) goto L_0x05ca
        L_0x047f:
            java.lang.String r10 = new java.lang.String     // Catch:{ Exception -> 0x0620 }
            r0 = r20
            r10.<init>(r0, r2, r7)     // Catch:{ Exception -> 0x0620 }
            r7 = 1
            if (r6 != r7) goto L_0x0547
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0620 }
            int r6 = r2 + 1
            byte r2 = r20[r2]     // Catch:{ Exception -> 0x0620 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0620 }
            r5.<init>(r2)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r2 = "."
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ Exception -> 0x0620 }
            int r5 = r6 + 1
            byte r6 = r20[r6]     // Catch:{ Exception -> 0x0620 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r2 = r2.append(r6)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r6 = "."
            java.lang.StringBuilder r2 = r2.append(r6)     // Catch:{ Exception -> 0x0620 }
            int r7 = r5 + 1
            byte r5 = r20[r5]     // Catch:{ Exception -> 0x0620 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r5 = "."
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ Exception -> 0x0620 }
            int r6 = r7 + 1
            byte r5 = r20[r7]     // Catch:{ Exception -> 0x0620 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x0620 }
            java.lang.String r2 = "169.254"
            int r2 = r5.indexOf(r2)     // Catch:{ Exception -> 0x053d }
            r7 = -1
            if (r2 == r7) goto L_0x064e
            r0 = r22
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ Exception -> 0x053d }
            r2 = r0
            java.net.InetAddress r2 = r2.getAddress()     // Catch:{ Exception -> 0x053d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x053d }
            java.lang.String r7 = "192"
            int r2 = r2.indexOf(r7)     // Catch:{ Exception -> 0x053d }
            r7 = -1
            if (r2 == r7) goto L_0x064e
            r0 = r22
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ Exception -> 0x053d }
            r2 = r0
            java.net.InetAddress r2 = r2.getAddress()     // Catch:{ Exception -> 0x053d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x053d }
            r7 = 1
            java.lang.String r2 = r2.substring(r7)     // Catch:{ Exception -> 0x053d }
            r0 = r19
            boolean r5 = r0.f321e     // Catch:{ Exception -> 0x0639 }
            if (r5 == 0) goto L_0x0645
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0639 }
            java.lang.String r7 = "   ip4 corrected: "
            r5.<init>(r7)     // Catch:{ Exception -> 0x0639 }
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch:{ Exception -> 0x0639 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0639 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r5)     // Catch:{ Exception -> 0x0639 }
            r5 = r6
            r18 = r4
            r4 = r2
            r2 = r3
            r3 = r18
        L_0x052c:
            int r6 = r9 + 1
            r9 = r6
            r7 = r5
            r5 = r4
            r4 = r3
            r3 = r2
            goto L_0x0088
        L_0x0535:
            r0 = r20
            m262a((byte[]) r0, (int) r7)     // Catch:{ Exception -> 0x0620 }
            r6 = r7
            goto L_0x044d
        L_0x053d:
            r2 = move-exception
            r2 = r5
        L_0x053f:
            r5 = r6
            r18 = r4
            r4 = r2
            r2 = r3
            r3 = r18
            goto L_0x052c
        L_0x0547:
            java.lang.String r6 = ""
            r0 = r19
            r0.f309c = r6     // Catch:{ Exception -> 0x0620 }
            r6 = 0
        L_0x054e:
            r7 = 8
            if (r6 < r7) goto L_0x0576
            r0 = r19
            boolean r6 = r0.f321e     // Catch:{ Exception -> 0x0620 }
            if (r6 == 0) goto L_0x063c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0620 }
            java.lang.String r7 = "   ip6Address:"
            r6.<init>(r7)     // Catch:{ Exception -> 0x0620 }
            r0 = r19
            java.lang.String r7 = r0.f309c     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0620 }
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r6)     // Catch:{ Exception -> 0x0620 }
            r18 = r3
            r3 = r4
            r4 = r5
            r5 = r2
            r2 = r18
            goto L_0x052c
        L_0x0576:
            r0 = r19
            java.lang.String r7 = r0.f309c     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0620 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x0620 }
            r10.<init>(r7)     // Catch:{ Exception -> 0x0620 }
            int r11 = r2 + 1
            byte r2 = r20[r2]     // Catch:{ Exception -> 0x0620 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            java.lang.String r2 = p004de.humatic.nmj.C0484p.m354a((int) r2)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r2 = r10.append(r2)     // Catch:{ Exception -> 0x0620 }
            int r7 = r11 + 1
            byte r10 = r20[r11]     // Catch:{ Exception -> 0x0620 }
            r10 = r10 & 255(0xff, float:3.57E-43)
            java.lang.String r10 = p004de.humatic.nmj.C0484p.m354a((int) r10)     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r2 = r2.append(r10)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0620 }
            r0 = r19
            r0.f309c = r2     // Catch:{ Exception -> 0x0620 }
            r2 = 7
            if (r6 >= r2) goto L_0x05c5
            r0 = r19
            java.lang.String r2 = r0.f309c     // Catch:{ Exception -> 0x0620 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0620 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0620 }
            r10.<init>(r2)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r2 = ":"
            java.lang.StringBuilder r2 = r10.append(r2)     // Catch:{ Exception -> 0x0620 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0620 }
            r0 = r19
            r0.f309c = r2     // Catch:{ Exception -> 0x0620 }
        L_0x05c5:
            int r2 = r6 + 1
            r6 = r2
            r2 = r7
            goto L_0x054e
        L_0x05ca:
            r10 = 33
            if (r6 != r10) goto L_0x0612
            r0 = r20
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r2)     // Catch:{ Exception -> 0x0620 }
            int r2 = r2 + 2
            r0 = r20
            p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r2)     // Catch:{ Exception -> 0x0620 }
            int r3 = r2 + 2
            r0 = r20
            short r2 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r3)     // Catch:{ Exception -> 0x0620 }
            int r4 = r3 + 2
            byte r3 = r20[r4]     // Catch:{ Exception -> 0x0620 }
            r3 = r3 & 240(0xf0, float:3.36E-43)
            r6 = 192(0xc0, float:2.69E-43)
            if (r3 != r6) goto L_0x05fb
            r0 = r20
            java.lang.String r3 = m262a((byte[]) r0, (int) r4)     // Catch:{ Exception -> 0x0620 }
            int r4 = r4 + 2
            r18 = r5
            r5 = r4
            r4 = r18
            goto L_0x052c
        L_0x05fb:
            int r6 = r4 + 1
            byte r3 = r20[r4]     // Catch:{ Exception -> 0x0620 }
            r4 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0620 }
            r0 = r20
            r3.<init>(r0, r6, r4)     // Catch:{ Exception -> 0x0620 }
            int r4 = r4 + 2
            int r4 = r4 + r6
            r18 = r5
            r5 = r4
            r4 = r18
            goto L_0x052c
        L_0x0612:
            r10 = 16
            if (r6 != r10) goto L_0x063c
            int r2 = r2 + r7
            r18 = r3
            r3 = r4
            r4 = r5
            r5 = r2
            r2 = r18
            goto L_0x052c
        L_0x0620:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "in resolve authoritve nameservers "
            r3.<init>(r4)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            p004de.humatic.nmj.C0484p.m362a((java.lang.String) r2)
            goto L_0x0107
        L_0x0639:
            r5 = move-exception
            goto L_0x053f
        L_0x063c:
            r18 = r3
            r3 = r4
            r4 = r5
            r5 = r2
            r2 = r18
            goto L_0x052c
        L_0x0645:
            r5 = r6
            r18 = r4
            r4 = r2
            r2 = r3
            r3 = r18
            goto L_0x052c
        L_0x064e:
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            goto L_0x052c
        L_0x0654:
            r3 = r5
            goto L_0x01e0
        L_0x0657:
            r5 = r6
            r6 = r3
            goto L_0x01b0
        L_0x065b:
            r4 = r5
            goto L_0x0148
        L_0x065e:
            r4 = r7
            r5 = r3
            r3 = r6
            goto L_0x01eb
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0468h.m274a(byte[], int, java.net.SocketAddress):void");
    }

    /* renamed from: a */
    private boolean m276a(String str) {
        m297c(3);
        for (int i = 0; i < this.f296a.length; i++) {
            try {
                if (this.f296a[i].f331a.equalsIgnoreCase(str) || (this.f296a[i].f331a.indexOf(".") != -1 && this.f296a[i].f331a.replace(".", "_").equalsIgnoreCase(str))) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    /* renamed from: b */
    private static boolean m293b(String str) {
        if (str.equals("_apple-midi") || str.equals("_ws-midi")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final void mo8136d() {
        try {
            m297c(3);
            if (this.f294a) {
                this.f284a = 0;
                C0484p.logln(2, "DNS (" + this.f320e + ") - channel opened, nr. sessions: " + this.f296a.length);
                for (int i = 0; i < this.f296a.length; i++) {
                    C0484p.logln(2, "-> " + NMJConfig.getName(this.f296a[i].f330a));
                    m262a(this.f318d, m246a(i, this.f318d));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo8134b(int i) {
        boolean z = false;
        if (this.f296a != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.f296a.length) {
                    break;
                } else if (this.f296a[i2].f330a == i) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (z) {
            if (this.f322f != i) {
                C0484p.logln(2, "DNS (" + this.f320e + ") - channel unlinked: " + i + " name " + NMJConfig.getName(i));
                this.f322f = i;
            } else {
                this.f322f = -1;
            }
            mo8130a(i);
            m297c(3);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void m304a(int i, boolean z) {
        C0467g gVar = new C0467g("_apple-midi", -1);
        gVar.mo8126a(NMJConfig.m39a(i));
        gVar.f279b = NMJConfig.getLocalPort(i);
        if (z) {
            Iterator<C0467g> it = this.f316d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C0467g next = it.next();
                try {
                    if (next.f278a.equalsIgnoreCase(gVar.f278a) && next.f279b == gVar.f279b) {
                        C0484p.logln(2, "Removing session announcement for client " + i);
                        this.f316d.remove(next);
                        break;
                    }
                } catch (Exception e) {
                }
            }
        } else {
            Iterator<C0467g> it2 = this.f316d.iterator();
            while (it2.hasNext()) {
                C0467g next2 = it2.next();
                if (next2.f278a.equalsIgnoreCase(gVar.f278a) && next2.f279b == gVar.f279b) {
                    return;
                }
            }
            C0484p.logln(2, "Creating session announcement for client " + i);
            gVar.f281c = NMJConfig.m88b(false);
            this.f316d.add(gVar);
        }
        m297c(3);
    }

    /* renamed from: c */
    private synchronized void m297c(int i) {
        try {
            this.f327i = false;
            int[] a = NMJConfig.m59a(-1, 3, this.f319e);
            this.f296a = new C0471c[(a.length + this.f316d.size())];
            for (int i2 = 0; i2 < a.length; i2++) {
                this.f296a[i2] = new C0471c(this, a[i2], (byte) 0);
            }
            for (int i3 = 0; i3 < this.f316d.size(); i3++) {
                C0467g gVar = this.f316d.get(i3);
                int length = a.length + i3;
                if (gVar.f280b.equalsIgnoreCase("_apple-midi")) {
                    int[] a2 = NMJConfig.m39a(0);
                    int i4 = 0;
                    while (true) {
                        if (i4 < a2.length) {
                            if (NMJConfig.m39a(a2[i4]).equalsIgnoreCase(gVar.f278a) && NMJConfig.getLocalPort(a2[i4]) == gVar.f279b) {
                                length = a2[i4];
                                this.f327i = true;
                                break;
                            }
                            i4++;
                        } else {
                            break;
                        }
                    }
                }
                this.f296a[a.length + i3] = new C0471c(this, length, gVar, (byte) 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /* renamed from: a */
    private C0467g m259a(String str) {
        if (str == null) {
            return null;
        }
        Iterator<C0467g> it = this.f304b.iterator();
        while (it.hasNext()) {
            C0467g next = it.next();
            if (next.f278a != null && next.f278a.equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: de.humatic.nmj.h$c */
    class C0471c {

        /* renamed from: a */
        int f330a;

        /* renamed from: a */
        String f331a;

        /* renamed from: b */
        int f332b;

        /* renamed from: b */
        String f333b;

        private C0471c(C0468h hVar, int i) {
            this.f330a = i;
            this.f331a = NMJConfig.getName(i);
            NMJConfig.getIP(i);
            this.f332b = NMJConfig.getLocalPort(i);
            this.f333b = "_apple-midi";
            if (NMJConfig.getMode(this.f330a) == 6) {
                this.f333b = "_ws-midi";
            }
        }

        /* synthetic */ C0471c(C0468h hVar, int i, byte b) {
            this(hVar, i);
        }

        private C0471c(C0468h hVar, int i, C0467g gVar) {
            this.f330a = i;
            this.f331a = gVar.f278a;
            this.f332b = gVar.f279b;
            this.f333b = gVar.f280b;
        }

        /* synthetic */ C0471c(C0468h hVar, int i, C0467g gVar, byte b) {
            this(hVar, i, gVar);
        }
    }

    /* renamed from: de.humatic.nmj.h$d */
    class C0472d {
        private C0472d(C0468h hVar) {
        }

        /* synthetic */ C0472d(C0468h hVar, byte b) {
            this(hVar);
        }

        /* renamed from: a */
        public final void mo8139a() {
            synchronized (this) {
                notify();
            }
        }
    }
}
