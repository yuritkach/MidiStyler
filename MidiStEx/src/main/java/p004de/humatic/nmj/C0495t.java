package p004de.humatic.nmj;

import android.os.Build;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.TimerTask;
import java.util.Vector;

/* renamed from: de.humatic.nmj.t */
final class C0495t extends C0503w {

    /* renamed from: a */
    private int f508a = 4031;

    /* renamed from: a */
    private long f509a;

    /* renamed from: a */
    private Runnable f510a;

    /* renamed from: a */
    private String f511a;

    /* renamed from: a */
    private DatagramPacket f512a;

    /* renamed from: a */
    private DatagramSocket f513a;

    /* renamed from: a */
    private TimerTask f514a;

    /* renamed from: a */
    private Vector<C0487s> f515a = new Vector<>();

    /* renamed from: a */
    private byte[] f516a = new byte[36];

    /* renamed from: b */
    private int f517b;

    /* renamed from: b */
    private long f518b;

    /* renamed from: b */
    private DatagramPacket f519b;

    /* renamed from: b */
    private DatagramSocket f520b;

    /* renamed from: b */
    private boolean f521b = true;

    /* renamed from: c */
    private long f522c = 0;

    /* renamed from: c */
    private boolean f523c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public long f524d = -1;

    /* renamed from: d */
    private boolean f525d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public int f526e = -1;

    /* renamed from: e */
    private boolean f527e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public int f528f = -1;

    /* renamed from: g */
    private int f529g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public int f530h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public int f531i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f532j;

    C0495t(int i, C0479n nVar) throws Exception {
        super(nVar, i);
        boolean z;
        boolean z2;
        int i2;
        int i3;
        String ip = NMJConfig.getIP(this.f575c);
        this.f523c = ip == null;
        int port = NMJConfig.getPort(this.f575c);
        if (!(ip == null || ip.indexOf(":") == -1)) {
            port = Integer.parseInt(ip.split(":")[1]);
            ip = ip.split(":")[0];
        }
        this.f508a = NMJConfig.getLocalPort(this.f575c);
        if (this.f508a < 0) {
            this.f508a = 0;
        }
        NMJConfig.m84b(i, -1);
        try {
            NMJConfig.f68a[i] = 0;
        } catch (Exception e) {
        }
        int networkAdapter = NMJConfig.getNetworkAdapter(this.f575c);
        C0484p.m393b();
        this.f527e = (C0484p.m393b() & 16) != 0;
        if ((NMJConfig.getFlags(-1) & 65536) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.f525d = z;
        C0484p.logln(2, String.valueOf(this.f575c) + " - Opening rtp channel " + ip + ":" + (ip == null ? this.f508a : port) + ", nwa: " + networkAdapter + ", sr: " + this.f525d);
        this.f518b = System.currentTimeMillis();
        this.f511a = this.f523c ? NMJConfig.getName(this.f575c) : NMJConfig.m39a(this.f575c);
        if (this.f511a.equalsIgnoreCase("localhost")) {
            try {
                this.f511a = "nmj (" + Build.MODEL + ") Ch_" + (this.f575c + 1);
            } catch (Exception e2) {
            }
        }
        this.f517b = (int) (Math.random() * 2.147483647E9d);
        if ((NMJConfig.getFlags(-1) & 4096) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 && !this.f523c) {
            C0484p.logln(2, String.valueOf(this.f575c) + " - RTP using fixed local port: " + this.f508a);
        }
        if (this.f523c) {
            i2 = this.f508a;
        } else {
            i2 = z2 ? this.f508a : 0;
        }
        this.f520b = C0484p.m357a(networkAdapter, i2);
        C0484p.logln(2, String.valueOf(this.f575c) + " - RTP ctrl socket created on: " + this.f520b.getLocalSocketAddress());
        this.f520b.setSoTimeout(5000);
        this.f512a = new DatagramPacket(new byte[64], 64);
        if (!this.f523c) {
            C0487s sVar = new C0487s(this, this.f517b, this.f518b);
            sVar.mo8174a(NMJConfig.getName(this.f575c));
            sVar.mo8172a(0, new InetSocketAddress(ip, port));
            sVar.mo8172a(1, new InetSocketAddress(ip, port + 1));
            this.f515a.add(sVar);
        }
        if (this.f523c) {
            i3 = this.f508a + 1;
        } else {
            i3 = z2 ? this.f508a + 1 : 0;
        }
        this.f513a = C0484p.m357a(networkAdapter, i3);
        C0484p.logln(2, String.valueOf(this.f575c) + " - RTP midi socket created on: " + this.f513a.getLocalSocketAddress());
        this.f513a.setSoTimeout(5000);
        this.f519b = new DatagramPacket(new byte[1500], 1500);
        this.f521b = true;
        new Thread(new C0498a(this, this, (byte) 0)).start();
        new Thread(new C0500c(this, this, (byte) 0)).start();
        NMJConfig.m59a(this.f575c, 1, 0);
        if (this.f523c) {
            NMJConfig.m59a(this.f575c, 4, 4);
        } else if ((NMJConfig.getFlags(-1) & 8192) != 0) {
            new Thread(new Runnable() {
                public final void run() {
                    try {
                        Thread.sleep(1500);
                        C0484p.logln(2, "Ch. " + C0495t.this.f575c + " - running delayed autoconnect");
                        C0495t.this.mo8194a(0);
                    } catch (Exception e) {
                    }
                }
            }).start();
        } else {
            mo8194a(0);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void m505a() {
        if (this.f523c || (this.f515a.size() > 0 && this.f515a.get(0).mo8167a())) {
            try {
                m491b((C0487s) null);
            } catch (Exception e) {
            }
        }
        this.f521b = false;
        this.f520b.close();
        this.f513a.close();
        C0484p.logln(1, String.valueOf(this.f575c) + " - RTPSession closed");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo8198c() throws Exception {
        try {
            Thread.currentThread();
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        try {
            this.f515a.get(0).mo8176a(this.f523c);
        } catch (Exception e2) {
        }
        this.f524d = -1;
        C0484p.logln(4, String.valueOf(this.f575c) + " - Restarting invTask");
        this.f531i = 0;
        this.f529g = 0;
        this.f532j = 0;
        mo8194a(0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m506a(int i) throws Exception {
        if (i < this.f529g) {
            C0484p.logln(2, String.valueOf(this.f575c) + " - Autoconnection: allready connected");
            return;
        }
        this.f529g = i;
        if (i != 1 || !this.f515a.get(0).mo8167a()) {
            byte[] bArr = new byte[(this.f511a.length() + 17)];
            bArr[1] = -1;
            bArr[0] = -1;
            System.arraycopy(new String("IN").getBytes(), 0, bArr, 2, 2);
            bArr[7] = 2;
            C0484p.m372a(this.f517b, bArr, 12);
            System.arraycopy(this.f511a.getBytes(), 0, bArr, 16, this.f511a.length());
            if (this.f526e == -1) {
                this.f526e = (int) (Math.random() * 2.147483647E9d);
            }
            C0484p.m372a(this.f526e, bArr, 8);
            if (i == 0) {
                DatagramPacket a = this.f515a.get(0).mo8170a(0);
                a.setData(bArr, 0, bArr.length);
                try {
                    this.f520b.send(a);
                } catch (Exception e) {
                    if (e.toString().toLowerCase().indexOf("closed") != -1) {
                        return;
                    }
                }
                if (this.f528f == -1) {
                    this.f528f = (int) (Math.random() * 2.147483647E9d);
                }
            } else {
                C0484p.m372a(this.f528f, bArr, 8);
                DatagramPacket a2 = this.f515a.get(0).mo8170a(1);
                a2.setData(bArr, 0, bArr.length);
                try {
                    this.f513a.send(a2);
                } catch (Exception e2) {
                    if (e2.toString().toLowerCase().indexOf("closed") != -1) {
                        return;
                    }
                }
            }
            int i2 = this.f530h;
            this.f530h = i2 + 1;
            if (i2 <= 6 || this.f525d) {
                this.f514a = new C0499b(this, (byte) 0);
                NMJConfig.m69a(this.f514a, this.f530h < 4 ? 2000 : 15000, 0);
                return;
            }
            this.f530h = 0;
            NMJConfig.m59a(this.f575c, 4, 4096);
        }
    }

    /* renamed from: b */
    private void m491b(C0487s sVar) {
        String sVar2;
        if (sVar == null) {
            try {
                if (this.f515a.size() <= 0) {
                    return;
                }
            } catch (Exception e) {
            }
        }
        StringBuilder append = new StringBuilder(String.valueOf(this.f575c)).append(" - Posting BYE to ");
        if (sVar == null) {
            sVar2 = this.f523c ? String.valueOf(this.f515a.size()) + " client(s)" : "session host";
        } else {
            sVar2 = sVar.toString();
        }
        C0484p.logln(5, append.append(sVar2).toString());
        byte[] bArr = new byte[(this.f511a.length() + 17)];
        bArr[1] = -1;
        bArr[0] = -1;
        System.arraycopy(new String("BY").getBytes(), 0, bArr, 2, 2);
        bArr[7] = 2;
        C0484p.m372a(this.f528f, bArr, 8);
        C0484p.m372a(this.f517b, bArr, 12);
        System.arraycopy(this.f511a.getBytes(), 0, bArr, 16, this.f511a.length());
        if (sVar == null) {
            for (int i = 0; i < this.f515a.size(); i++) {
                C0487s sVar3 = this.f515a.get(i);
                DatagramPacket a = sVar3.mo8170a(1);
                if (a != null) {
                    a.setData(bArr, 0, this.f511a.length() + 17);
                    this.f520b.send(a);
                }
                C0484p.m372a(this.f526e, bArr, 8);
                DatagramPacket a2 = sVar3.mo8170a(0);
                if (a2 != null) {
                    a2.setData(bArr, 0, this.f511a.length() + 17);
                    this.f520b.send(a2);
                }
            }
        } else {
            DatagramPacket a3 = sVar.mo8170a(1);
            if (a3 != null) {
                a3.setData(bArr, 0, this.f511a.length() + 17);
                this.f520b.send(a3);
            }
            C0484p.m372a(this.f526e, bArr, 8);
            DatagramPacket a4 = sVar.mo8170a(0);
            if (a4 != null) {
                a4.setData(bArr, 0, this.f511a.length() + 17);
                this.f520b.send(a4);
            }
        }
        if (sVar == null) {
            NMJConfig.m59a(this.f575c, 4, 128);
        }
    }

    /* renamed from: e */
    private void m502e() {
        if (this.f515a.size() != 0) {
            this.f516a[8] = 0;
            this.f509a = mo8031a();
            C0484p.m374a(this.f509a, this.f516a, 12);
            C0484p.m374a(0, this.f516a, 20);
            C0484p.m374a(0, this.f516a, 28);
            this.f522c = this.f509a;
            try {
                DatagramPacket a = this.f515a.get(0).mo8170a(1);
                a.setData(this.f516a, 0, this.f516a.length);
                this.f513a.send(a);
            } catch (Exception e) {
                if (this.f521b && e.toString().indexOf("closed") < 0 && e.toString().indexOf("EINVAL") < 0) {
                    C0484p.m377a(e, "");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public int m472a(C0487s sVar, int i, long j, long j2) {
        final int i2;
        if (this.f573a) {
            return -1;
        }
        switch (i) {
            case 0:
                this.f516a[8] = 1;
                C0484p.m374a(j, this.f516a, 12);
                C0484p.m374a(mo8031a(), this.f516a, 20);
                C0484p.m374a(0, this.f516a, 28);
                try {
                    DatagramPacket a = sVar.mo8170a(1);
                    a.setData(this.f516a, 0, this.f516a.length);
                    this.f513a.send(a);
                    break;
                } catch (Exception e) {
                    return -1;
                }
            case 1:
                for (int i3 = 20; i3 < 32; i3++) {
                    this.f516a[i3] = 0;
                }
                this.f516a[8] = 2;
                C0484p.m374a(j2, this.f516a, 20);
                this.f509a = mo8031a();
                C0484p.m374a(this.f509a, this.f516a, 28);
                if (this.f527e) {
                    C0484p.m380a("write sync ", this.f516a);
                }
                try {
                    DatagramPacket a2 = sVar.mo8170a(1);
                    a2.setData(this.f516a, 0, this.f516a.length);
                    this.f513a.send(a2);
                    if (sVar.f448c > 8) {
                        i2 = 10000;
                    } else {
                        i2 = 1000;
                    }
                    if (this.f510a == null) {
                        this.f510a = new Runnable() {
                            public final void run() {
                                if (i2 > 0) {
                                    try {
                                        Thread.sleep((long) i2);
                                    } catch (InterruptedException e) {
                                    }
                                }
                                C0495t.m473a(C0495t.this);
                            }
                        };
                    }
                    C0484p.m378a(this.f510a);
                    break;
                } catch (Exception e2) {
                    return -1;
                }
        }
        return 0;
    }

    /* renamed from: a */
    private long mo8031a() {
        return (System.currentTimeMillis() - this.f518b) * 10;
    }

    /* renamed from: de.humatic.nmj.t$a */
    class C0498a extends Thread {

        /* renamed from: a */
        private C0495t f536a;

        private C0498a(C0495t tVar) {
            this.f536a = tVar;
        }

        /* synthetic */ C0498a(C0495t tVar, C0495t tVar2, byte b) {
            this(tVar2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x00fb, code lost:
            p004de.humatic.nmj.C0495t.m487b(r8.f537b);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0215, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0263, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00fa A[ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:2:0x0009] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r8 = this;
            L_0x0000:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this
                boolean r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)
                if (r0 != 0) goto L_0x0009
            L_0x0008:
                return
            L_0x0009:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 64
                r0.setLength(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramSocket r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.receive(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                boolean r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 == 0) goto L_0x0008
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                byte[] r2 = r0.getData()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0 = 0
                byte r0 = r2[r0]     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = -1
                if (r0 != r1) goto L_0x0000
                r0 = 1
                byte r0 = r2[r0]     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = -1
                if (r0 != r1) goto L_0x0000
                java.lang.String r0 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 2
                r3 = 2
                r0.<init>(r2, r1, r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r1 = "OK"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 == 0) goto L_0x0102
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ Exception -> 0x03d0, SocketTimeoutException -> 0x00fa }
                java.util.TimerTask r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ Exception -> 0x03d0, SocketTimeoutException -> 0x00fa }
                r0.cancel()     // Catch:{ Exception -> 0x03d0, SocketTimeoutException -> 0x00fa }
            L_0x0059:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 0
                java.lang.Object r0 = r0.get(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.s r0 = (p004de.humatic.nmj.C0487s) r0     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 12
                int r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.mo8170a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 0
                java.lang.Object r0 = r0.get(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.s r0 = (p004de.humatic.nmj.C0487s) r0     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 0
                r3 = 1
                r0.mo8173a((int) r1, (boolean) r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0 = 17
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r1 = r1.getLength()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 >= r1) goto L_0x00c9
                r0 = 2
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = "RTP "
                r1.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r3 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r3 = r3.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = " - ctrl, invitation accepted by "
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r4 = 16
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r5 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5.getLength()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5 + -16
                int r5 = r5 + -1
                r3.<init>(r2, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r2 = r3.trim()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r1 = r1.toString()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0484p.logln(r0, r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
            L_0x00c9:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 0
                r0.f530h = 0     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 1
                r0.f531i = 1     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.mo8194a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 0
                java.lang.Object r0 = r0.get(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.s r0 = (p004de.humatic.nmj.C0487s) r0     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                boolean r0 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 == 0) goto L_0x0000
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x00fa:
                r0 = move-exception
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this
                p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r0)
                goto L_0x0000
            L_0x0102:
                java.lang.String r1 = "IN"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 == 0) goto L_0x02dd
                r0 = 4
                p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 8
                int r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.f526e = r1     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0 = 12
                int r3 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.s r0 = r0.mo8194a((int) r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 != 0) goto L_0x0289
                de.humatic.nmj.s r1 = new de.humatic.nmj.s     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = r8.f536a     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r4 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                long r6 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.<init>(r0, r4, r6)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.mo8170a((int) r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r0 = p004de.humatic.nmj.C0495t.m495c((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.f436a = r0     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r4 = 0
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.SocketAddress r0 = r0.getSocketAddress()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.mo8172a((int) r4, (java.net.InetSocketAddress) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0 = 17
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r4 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r4 = r4.getLength()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 >= r4) goto L_0x0268
                java.lang.String r0 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r4 = 16
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r5 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5.getLength()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5 + -16
                int r5 = r5 + -1
                r0.<init>(r2, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.mo8174a((java.lang.String) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0 = 2
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = " - RTP ctrl, connection request from "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = r1.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = " ssrc: "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
            L_0x01a6:
                r0 = 0
                r3 = 1
                r1.mo8173a((int) r0, (boolean) r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.add(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0 = r1
            L_0x01b5:
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r1 = r1.length()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r1 = r1 + 17
                byte[] r1 = new byte[r1]     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r3 = 0
                r4 = 0
                r5 = 16
                java.lang.System.arraycopy(r2, r3, r1, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r2 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = "OK"
                r2.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                byte[] r2 = r2.getBytes()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r3 = 0
                r4 = 2
                r5 = 2
                java.lang.System.arraycopy(r2, r3, r1, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r2 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r2 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r3 = 12
                p004de.humatic.nmj.C0484p.m372a((int) r2, (byte[]) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r2 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r2 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                byte[] r2 = r2.getBytes()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r3 = 0
                r4 = 16
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5.length()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.System.arraycopy(r2, r3, r1, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r2 = 0
                java.net.DatagramPacket r0 = r0.mo8170a((int) r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r2 = 0
                int r3 = r1.length     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.setData(r1, r2, r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramSocket r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.send(r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x0215:
                r0 = move-exception
                java.lang.String r1 = r0.toString()
                java.lang.String r1 = r1.toLowerCase()
                java.lang.String r2 = "closed"
                int r1 = r1.indexOf(r2)
                if (r1 >= 0) goto L_0x0000
                java.lang.String r1 = r0.toString()
                java.lang.String r1 = r1.toLowerCase()
                java.lang.String r2 = "cancelled"
                int r1 = r1.indexOf(r2)
                if (r1 >= 0) goto L_0x0000
                java.lang.String r1 = r0.toString()
                java.lang.String r1 = r1.toLowerCase()
                java.lang.String r2 = "interrupted"
                int r1 = r1.indexOf(r2)
                if (r1 >= 0) goto L_0x0000
                java.lang.String r1 = r0.toString()
                java.lang.String r1 = r1.toLowerCase()
                java.lang.String r2 = "non-socket"
                int r1 = r1.indexOf(r2)
                if (r1 >= 0) goto L_0x0000
                java.lang.String r1 = r0.toString()
                java.lang.String r2 = "Bad file number"
                int r1 = r1.indexOf(r2)
                r2 = -1
                if (r1 != r2) goto L_0x0000
                r0.printStackTrace()
                goto L_0x0000
            L_0x0268:
                r0 = 2
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = " - RTP ctrl, connection request from ssrc: "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x01a6
            L_0x0289:
                r1 = 2
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r5 = r5.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r5 = " - RTP client allready connected: "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0484p.logln(r1, r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r3 = p004de.humatic.nmj.C0495t.m498d((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r4 = r3 + 1
                r1.f532j = r4     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 10
                if (r3 != r1) goto L_0x01b5
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                boolean r1 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.mo8176a((boolean) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.util.Vector r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.remove(r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = -2147483647(0xffffffff80000001, float:-1.4E-45)
                java.lang.String r2 = "There seems to be a network problem.\nReceiving repeated RTP invitations.\nThe remote side does not seem to get the replies"
                p004de.humatic.nmj.NMJConfig.m60a((int) r0, (int) r1, (java.lang.String) r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 0
                r0.f532j = r1     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x02dd:
                java.lang.String r1 = "BY"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 == 0) goto L_0x034a
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 12
                r3 = 4
                int r1 = p004de.humatic.nmj.C0484p.m349a((byte[]) r2, (int) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.s r0 = r0.mo8194a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 == 0) goto L_0x0000
                boolean r1 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 == 0) goto L_0x0000
                r1 = 0
                r2 = 0
                r0.mo8173a((int) r1, (boolean) r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 2
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r3 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r3 = r3.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r2.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = " - RTP ctrl, connection closed by client: "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r3 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r2 = r2.toString()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0484p.logln(r1, r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                boolean r1 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 != 0) goto L_0x0343
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 4
                r2 = 128(0x80, float:1.794E-43)
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r1, (int) r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                boolean r0 = p004de.humatic.nmj.C0495t.m495c((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 == 0) goto L_0x0000
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.mo8198c()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x0343:
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.mo8196a((p004de.humatic.nmj.C0487s) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x034a:
                java.lang.String r1 = "RS"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 == 0) goto L_0x036a
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 4
                int r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.s r0 = r0.mo8194a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 == 0) goto L_0x0000
                r1 = 8
                short r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r0.mo8175a((short) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x036a:
                java.lang.String r1 = "CK"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 == 0) goto L_0x0385
                r0 = 5
                java.lang.String r1 = "sync received on ctrl channel"
                r3 = 0
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.net.DatagramPacket r4 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r4 = r4.getLength()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0484p.m371a((int) r0, (java.lang.String) r1, (byte[]) r2, (int) r3, (int) r4)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x0385:
                java.lang.String r1 = "NO"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r1 == 0) goto L_0x03b4
                r0 = 5
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r2 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r2 = r2.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r2 = " - RTP ctrl, connection refused by client "
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                java.lang.String r1 = r1.toString()     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                p004de.humatic.nmj.C0484p.logln(r0, r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 4
                r2 = 2048(0x800, float:2.87E-42)
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r1, (int) r2)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x03b4:
                java.lang.String r1 = "RL"
                boolean r0 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 == 0) goto L_0x0000
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                r1 = 4
                int r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                de.humatic.nmj.s r0 = r0.mo8194a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                if (r0 == 0) goto L_0x0000
                r0 = 8
                p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r0)     // Catch:{ SocketTimeoutException -> 0x00fa, Exception -> 0x0215 }
                goto L_0x0000
            L_0x03d0:
                r0 = move-exception
                goto L_0x0059
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0495t.C0498a.run():void");
        }
    }

    /* renamed from: de.humatic.nmj.t$c */
    class C0500c extends Thread {

        /* renamed from: a */
        private long f539a;

        /* renamed from: a */
        private C0495t f540a;

        /* renamed from: a */
        private byte[] f541a;

        private C0500c(C0495t tVar) {
            this.f541a = new byte[12];
            this.f540a = tVar;
            byte[] a = C0495t.m473a((C0495t) C0495t.this);
            byte[] a2 = C0495t.m473a((C0495t) C0495t.this);
            byte[] bArr = this.f541a;
            this.f541a[1] = -1;
            bArr[0] = -1;
            a2[1] = -1;
            a[0] = -1;
            System.arraycopy(new String("CK").getBytes(), 0, C0495t.m473a((C0495t) C0495t.this), 2, 2);
            System.arraycopy(new String("RS").getBytes(), 0, this.f541a, 2, 2);
            C0484p.m372a(C0495t.m487b((C0495t) C0495t.this), C0495t.m473a((C0495t) C0495t.this), 4);
            C0484p.m372a(C0495t.m487b((C0495t) C0495t.this), this.f541a, 4);
            C0495t.m473a((C0495t) C0495t.this)[8] = 1;
            setPriority(10);
        }

        /* synthetic */ C0500c(C0495t tVar, C0495t tVar2, byte b) {
            this(tVar2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:38:0x023b, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x00ef A[Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }, ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:3:0x000b] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r14 = this;
                r13 = 2
                r12 = 1
            L_0x0002:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this
                boolean r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)
                if (r0 != 0) goto L_0x000b
            L_0x000a:
                return
            L_0x000b:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r0 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 1500(0x5dc, float:2.102E-42)
                r0.setLength(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramSocket r0 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r1 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.receive(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                boolean r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 == 0) goto L_0x000a
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r0 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                byte[] r2 = r0.getData()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = 0
                byte r0 = r2[r0]     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = -1
                if (r0 != r1) goto L_0x0419
                r0 = 1
                byte r0 = r2[r0]     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = -1
                if (r0 != r1) goto L_0x0419
                java.lang.String r0 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 2
                r3 = 2
                r0.<init>(r2, r1, r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r1 = "OK"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 == 0) goto L_0x00f2
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ Exception -> 0x048d, SocketTimeoutException -> 0x00ef }
                java.util.TimerTask r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ Exception -> 0x048d, SocketTimeoutException -> 0x00ef }
                r0.cancel()     // Catch:{ Exception -> 0x048d, SocketTimeoutException -> 0x00ef }
            L_0x005b:
                r0 = 17
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r1 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r1 = r1.getLength()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 >= r1) goto L_0x00a3
                r0 = 2
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = "RTP "
                r1.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r3 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r3 = r3.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = " - midi, invitation accepted by "
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r4 = 16
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r5 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r5 = r5.getLength()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r5 = r5 + -16
                int r5 = r5 + -1
                r3.<init>(r2, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = r3.trim()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r1 = r1.toString()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.logln(r0, r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
            L_0x00a3:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 0
                java.lang.Object r0 = r0.get(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.s r0 = (p004de.humatic.nmj.C0487s) r0     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 12
                int r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.mo8170a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 0
                java.lang.Object r0 = r0.get(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.s r0 = (p004de.humatic.nmj.C0487s) r0     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 1
                r2 = 1
                r0.mo8173a((int) r1, (boolean) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 4
                r2 = 32
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r1, (int) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 0
                java.lang.Object r0 = r0.get(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.s r0 = (p004de.humatic.nmj.C0487s) r0     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                boolean r0 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 == 0) goto L_0x0002
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x00ef:
                r0 = move-exception
                goto L_0x0002
            L_0x00f2:
                java.lang.String r1 = "IN"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 == 0) goto L_0x02b4
                r0 = 4
                p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 8
                int r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.f528f = r1     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = 12
                int r3 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.s r1 = r0.mo8194a((int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 != 0) goto L_0x025b
                de.humatic.nmj.s r1 = new de.humatic.nmj.s     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = r14.f540a     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r4 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                long r6 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.<init>(r0, r4, r6)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.mo8170a((int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r4 = 1
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r0 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.SocketAddress r0 = r0.getSocketAddress()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.mo8172a((int) r4, (java.net.InetSocketAddress) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = p004de.humatic.nmj.C0495t.m495c((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.f436a = r0     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = 1
                r4 = 1
                r1.mo8173a((int) r0, (boolean) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.size()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 != 0) goto L_0x015e
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r4 = 4
                r5 = 32
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r4, (int) r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
            L_0x015e:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.add(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = 17
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r4 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r4 = r4.getLength()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 >= r4) goto L_0x01a7
                r0 = 2
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = "mrt - connection request from "
                r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r6 = 16
                de.humatic.nmj.t r7 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r7 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r7)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r7 = r7.getLength()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r7 = r7 + -16
                int r7 = r7 + -1
                r5.<init>(r2, r6, r7)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = " ssrc: "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r4 = r4.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r4 = r4.toString()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.logln(r0, r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
            L_0x01a7:
                r0 = 2
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = "RTPClient "
                r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = r1.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = ", "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r4 = " joined session on MIDI thread"
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 32
                r1.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.NMJConfig.m84b((int) r0, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = r1
            L_0x01db:
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r1 = r1.length()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r1 = r1 + 17
                byte[] r1 = new byte[r1]     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 0
                r4 = 0
                r5 = 16
                java.lang.System.arraycopy(r2, r3, r1, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r2 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = "OK"
                r2.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                byte[] r2 = r2.getBytes()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 0
                r4 = 2
                r5 = 2
                java.lang.System.arraycopy(r2, r3, r1, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r2 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r2 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 12
                p004de.humatic.nmj.C0484p.m372a((int) r2, (byte[]) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r2 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r2 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                byte[] r2 = r2.getBytes()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 0
                r4 = 16
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r5 = r5.length()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.System.arraycopy(r2, r3, r1, r4, r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r2 = 1
                java.net.DatagramPacket r0 = r0.mo8170a((int) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r2 = 0
                int r3 = r1.length     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.setData(r1, r2, r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ Exception -> 0x023b, SocketTimeoutException -> 0x00ef }
                java.net.DatagramSocket r1 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r1)     // Catch:{ Exception -> 0x023b, SocketTimeoutException -> 0x00ef }
                r1.send(r0)     // Catch:{ Exception -> 0x023b, SocketTimeoutException -> 0x00ef }
                goto L_0x0002
            L_0x023b:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x0241:
                r0 = move-exception
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this
                boolean r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)
                if (r1 == 0) goto L_0x0002
                java.lang.String r1 = r0.toString()
                java.lang.String r2 = "closed"
                int r1 = r1.indexOf(r2)
                if (r1 >= 0) goto L_0x0002
                r0.printStackTrace()
                goto L_0x0002
            L_0x025b:
                r0 = 2
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r5 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r5 = r5.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r5 = " - RTP midi, connection request from ssrc: "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 1
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r0 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.SocketAddress r0 = r0.getSocketAddress()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.mo8172a((int) r3, (java.net.InetSocketAddress) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = 1
                r3 = 1
                r1.mo8173a((int) r0, (boolean) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.util.Vector r0 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.size()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 != r12) goto L_0x02a5
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 4
                r4 = 32
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r3, (int) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
            L_0x02a5:
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 32
                r1.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.NMJConfig.m84b((int) r0, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = r1
                goto L_0x01db
            L_0x02b4:
                java.lang.String r1 = "CK"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 == 0) goto L_0x0363
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 4
                r3 = 4
                int r1 = p004de.humatic.nmj.C0484p.m349a((byte[]) r2, (int) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.s r0 = r0.mo8194a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 == 0) goto L_0x0002
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                long r4 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.f524d = r4     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 8
                byte r1 = r2[r1]     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = r1 & 255(0xff, float:3.57E-43)
                r3 = 12
                long r4 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r14.f539a = r4     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 20
                long r8 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 28
                long r10 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r3 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                boolean r3 = p004de.humatic.nmj.C0495t.m498d((p004de.humatic.nmj.C0495t) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r3 == 0) goto L_0x034d
                r3 = 0
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r4 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r4 = r4.getLength()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.m349a((byte[]) r2, (int) r3, (int) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                long r4 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r2.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = " sync, valid ts: "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r2 = r2.append(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = " "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                long r4 = r14.f539a     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = java.lang.Long.toHexString(r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = " "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = java.lang.Long.toHexString(r8)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = " "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = java.lang.Long.toHexString(r10)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r2 = r2.toString()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.m362a((java.lang.String) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
            L_0x034d:
                long r2 = r14.f539a     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                long r4 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.mo8171a((int) r1, (long) r2, (long) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r3 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                long r6 = r14.f539a     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r4 = r0
                r5 = r1
                int unused = r3.m472a((p004de.humatic.nmj.C0487s) r4, (int) r5, (long) r6, (long) r8)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x0363:
                java.lang.String r1 = "BY"
                boolean r1 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 == 0) goto L_0x03e0
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 12
                r3 = 4
                int r1 = p004de.humatic.nmj.C0484p.m349a((byte[]) r2, (int) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.s r0 = r0.mo8194a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 == 0) goto L_0x0002
                boolean r1 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 == 0) goto L_0x0002
                r1 = 1
                r2 = 0
                r0.mo8173a((int) r1, (boolean) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 2
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r3 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r3 = r3.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r2.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = " - RTP midi, connection closed by client "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r3 = ", sr: "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r3 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                boolean r3 = p004de.humatic.nmj.C0495t.m495c((p004de.humatic.nmj.C0495t) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r2 = r2.toString()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.logln(r1, r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                boolean r1 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 != 0) goto L_0x03d9
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 4
                r2 = 128(0x80, float:1.794E-43)
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r1, (int) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                boolean r0 = p004de.humatic.nmj.C0495t.m495c((p004de.humatic.nmj.C0495t) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 == 0) goto L_0x0002
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.mo8198c()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x03d9:
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.mo8196a((p004de.humatic.nmj.C0487s) r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x03e0:
                java.lang.String r1 = "NO"
                boolean r0 = r0.equalsIgnoreCase(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 == 0) goto L_0x0002
                r0 = 5
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r2 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r2 = r2.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r2 = " - RTP midi, connection refused by client "
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r2 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r2 = r2.f530h     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.lang.String r1 = r1.toString()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.logln(r0, r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 4
                r2 = 2048(0x800, float:2.87E-42)
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r1, (int) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x0419:
                r0 = 0
                byte r0 = r2[r0]     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0 = r0 & 128(0x80, float:1.794E-43)
                r1 = 128(0x80, float:1.794E-43)
                if (r0 != r1) goto L_0x047a
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 8
                int r1 = p004de.humatic.nmj.C0484p.m348a((byte[]) r2, (int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.s r0 = r0.mo8194a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r0 == 0) goto L_0x0002
                boolean r1 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 == 0) goto L_0x0002
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r1 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r1 = r1.getLength()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                long r4 = java.lang.System.currentTimeMillis()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r3 = (int) r4     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r1 = r0.mo8169a((byte[]) r2, (int) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                if (r1 != r12) goto L_0x046c
                short r1 = r0.mo8167a()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                byte[] r2 = r14.f541a     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r3 = 8
                r4 = 2
                p004de.humatic.nmj.C0484p.m373a((int) r1, (byte[]) r2, (int) r3, (int) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 0
                java.net.DatagramPacket r0 = r0.mo8170a((int) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                byte[] r1 = r14.f541a     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r0.setData(r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                de.humatic.nmj.t r1 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramSocket r1 = p004de.humatic.nmj.C0495t.m473a((p004de.humatic.nmj.C0495t) r1)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1.send(r0)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x046c:
                if (r1 != r13) goto L_0x0002
                de.humatic.nmj.t r0 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r0 = r0.f575c     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                r1 = 4
                r2 = 8192(0x2000, float:1.14794E-41)
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r1, (int) r2)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x047a:
                r0 = 2
                java.lang.String r1 = "Unknown data: "
                r3 = 0
                de.humatic.nmj.t r4 = p004de.humatic.nmj.C0495t.this     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                java.net.DatagramPacket r4 = p004de.humatic.nmj.C0495t.m487b((p004de.humatic.nmj.C0495t) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                int r4 = r4.getLength()     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                p004de.humatic.nmj.C0484p.m371a((int) r0, (java.lang.String) r1, (byte[]) r2, (int) r3, (int) r4)     // Catch:{ SocketTimeoutException -> 0x00ef, Exception -> 0x0241 }
                goto L_0x0002
            L_0x048d:
                r0 = move-exception
                goto L_0x005b
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0495t.C0500c.run():void");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8034a(byte[] bArr) throws IOException {
        this.f509a = mo8031a();
        for (int i = 0; i < this.f515a.size(); i++) {
            C0487s sVar = this.f515a.get(i);
            DatagramPacket a = sVar.mo8170a(1);
            if (a == null) {
                C0484p.m370a(3, "oops, " + sVar + " - no MIDI packet", bArr);
            } else {
                byte[][] a2 = sVar.mo8177a(bArr, (int) this.f509a);
                if (a2 != null) {
                    for (int i2 = 0; i2 < a2.length; i2++) {
                        a.setData(a2[i2], 0, a2[i2].length);
                        this.f513a.send(a);
                        if (a2.length > 1 && a2[i2].length > 256) {
                            try {
                                Thread.currentThread();
                                Thread.sleep(15);
                            } catch (Exception e) {
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8195a(int i, int i2, byte[] bArr, long j) {
        if (this.f515a.size() > 1) {
            m490b(i, i2, bArr, j);
        } else {
            this.f572a.mo8153a(i, i2, bArr, j);
        }
    }

    /* renamed from: b */
    private synchronized void m490b(int i, int i2, byte[] bArr, long j) {
        this.f572a.mo8153a(i, i2, bArr, j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final C0487s mo8194a(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.f515a.size()) {
                return null;
            }
            if (this.f515a.get(i3).mo8167a() == i) {
                return this.f515a.get(i3);
            }
            i2 = i3 + 1;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo8196a(C0487s sVar) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f515a.size()) {
                break;
            } else if (this.f515a.get(i2).mo8167a() == sVar.mo8167a()) {
                int i3 = this.f575c;
                sVar.mo8167a();
                NMJConfig.m84b(i3, 64);
                this.f515a.remove(i2);
                C0484p.logln(2, String.valueOf(this.f575c) + " - RTPClient left session: " + sVar.mo8167a() + ", " + sVar.mo8167a() + ", nr. clients left: " + this.f515a.size());
                if (this.f515a.size() == 0) {
                    NMJConfig.m59a(this.f575c, 4, 128);
                    NMJConfig.m59a(this.f575c, 4, 4);
                }
            } else {
                i = i2 + 1;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo8197b(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.f515a.size()) {
                if (this.f515a.get(i3).f436a == i) {
                    C0487s sVar = this.f515a.get(i3);
                    m491b(sVar);
                    int i4 = this.f575c;
                    sVar.mo8167a();
                    NMJConfig.m84b(i4, 64);
                    this.f515a.remove(i3);
                    C0484p.logln(2, "RTPClient left session: " + sVar.mo8167a() + ", " + sVar.mo8167a() + ", num clients left: " + this.f515a.size());
                    if (this.f515a.size() == 0) {
                        NMJConfig.m59a(this.f575c, 4, 128);
                        NMJConfig.m59a(this.f575c, 4, 4);
                        return;
                    }
                    return;
                }
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0502v[] m510a() {
        if (this.f515a == null || this.f515a.size() == 0) {
            return null;
        }
        C0502v[] vVarArr = new C0502v[this.f515a.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f515a.size()) {
                return vVarArr;
            }
            vVarArr[i2] = this.f515a.get(i2).mo8167a();
            i = i2 + 1;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final void mo8199d() {
        boolean z = (NMJConfig.getFlags(-1) & 65536) != 0;
        if (this.f525d != z) {
            this.f525d = z;
            if (this.f525d && !this.f523c && !this.f515a.get(0).mo8167a()) {
                try {
                    mo8198c();
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: de.humatic.nmj.t$b */
    class C0499b extends TimerTask {
        private C0499b() {
        }

        /* synthetic */ C0499b(C0495t tVar, byte b) {
            this();
        }

        public final void run() {
            try {
                C0495t.this.mo8194a(C0495t.m473a(C0495t.this));
            } catch (Exception e) {
            }
        }
    }
}
