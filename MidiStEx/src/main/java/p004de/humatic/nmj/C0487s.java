package p004de.humatic.nmj;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/* renamed from: de.humatic.nmj.s */
final class C0487s implements C0466f {

    /* renamed from: a */
    int f436a;

    /* renamed from: a */
    long f437a;

    /* renamed from: a */
    private C0477l f438a;

    /* renamed from: a */
    private C0495t f439a;

    /* renamed from: a */
    private String f440a = "RTPClient";

    /* renamed from: a */
    private DatagramPacket f441a;

    /* renamed from: a */
    private short f442a;

    /* renamed from: a */
    private boolean f443a;

    /* renamed from: b */
    int f444b = 0;

    /* renamed from: b */
    private long f445b;

    /* renamed from: b */
    private DatagramPacket f446b;

    /* renamed from: b */
    private boolean f447b;

    /* renamed from: c */
    int f448c;

    /* renamed from: c */
    private long f449c;

    /* renamed from: d */
    private int f450d;

    /* renamed from: d */
    private long f451d;

    /* renamed from: e */
    private int f452e;

    /* renamed from: f */
    private int f453f;

    /* renamed from: g */
    private int f454g;

    /* renamed from: h */
    private int f455h;

    /* renamed from: i */
    private int f456i;

    C0487s(C0495t tVar, int i, long j) {
        this.f439a = tVar;
        this.f452e = this.f439a.f575c;
        this.f438a = new C0477l(this, this.f452e, i, j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo8167a() {
        return this.f450d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final String m410a() {
        return this.f440a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8174a(String str) {
        this.f440a = str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8176a(boolean z) {
        this.f450d = 0;
        if (z) {
            this.f441a = null;
            this.f446b = null;
            this.f440a = "unknown RTP Client";
            this.f436a = -1;
        }
        this.f443a = false;
        this.f447b = false;
        this.f451d = 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m413a(int i) {
        this.f450d = i;
        this.f451d = System.currentTimeMillis();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8173a(int i, boolean z) {
        if (i == 0) {
            this.f443a = z;
        } else if (i == 1) {
            this.f447b = z;
        }
        if (mo8167a()) {
            this.f437a = System.currentTimeMillis();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m421a() {
        return this.f443a && this.f447b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8172a(int i, InetSocketAddress inetSocketAddress) throws SocketException {
        if (i == 0) {
            this.f441a = new DatagramPacket(new byte[64], 64, inetSocketAddress);
        } else {
            this.f446b = new DatagramPacket(new byte[64], 64, inetSocketAddress);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final DatagramPacket mo8170a(int i) {
        if (i == 0) {
            return this.f441a;
        }
        return this.f446b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8175a(short s) {
        this.f442a = s;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final short m412a() {
        return this.f442a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final byte[][] mo8177a(byte[] bArr, int i) {
        return this.f438a.mo8148a(bArr, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo8169a(byte[] bArr, int i, int i2) {
        boolean z;
        boolean z2;
        short s = (short) (((bArr[2] & 255) << 8) | (bArr[3] & 255));
        if (Math.abs(s - this.f442a) == 1 || s == 0 || this.f442a == 0) {
            z = false;
            z2 = false;
        } else {
            if (this.f454g == 0) {
                this.f456i = i2;
            }
            if (s - this.f455h <= 0 || s - this.f455h >= 300) {
                if (s - this.f455h > 200 && this.f454g > 0) {
                    this.f454g--;
                }
                z = false;
            } else {
                this.f454g++;
                if (this.f454g > 50) {
                    z = ((long) i2) - this.f437a > 10000 && i2 - this.f456i < 30000;
                    this.f454g = 0;
                }
                z = false;
            }
            this.f455h = s;
            if (this.f438a != null) {
                this.f438a.mo8147a((int) this.f442a);
            }
            z2 = true;
        }
        this.f438a.mo8148a(bArr, i);
        this.f442a = s;
        this.f445b = (long) i2;
        if (z) {
            return 2;
        }
        if (!z2) {
            int i3 = this.f453f;
            this.f453f = i3 + 1;
            if (i3 <= 80) {
                return 0;
            }
        }
        this.f453f = 0;
        return 1;
    }

    public final String toString() {
        String str;
        String str2;
        try {
            str = ((InetSocketAddress) this.f441a.getSocketAddress()).getAddress().toString();
        } catch (Exception e) {
            str = null;
        }
        try {
            str2 = ((InetSocketAddress) this.f446b.getSocketAddress()).getAddress().toString();
        } catch (Exception e2) {
            str2 = null;
        }
        try {
            return String.valueOf(this.f440a) + " " + this.f450d + ":\n   " + str + "\n   " + str2;
        } catch (Exception e3) {
            return "RTPClient";
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0502v m409a() {
        C0502v vVar = new C0502v();
        vVar.f562a = this.f436a;
        vVar.f563a = this.f440a;
        vVar.f564b = 1;
        try {
            vVar.f565b = mo8170a(0).getAddress().toString();
            vVar.f566c = mo8170a(0).getPort();
        } catch (Exception e) {
        }
        return vVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8171a(int i, long j, long j2) {
        if (j != -1) {
            this.f449c = j;
        }
        this.f451d = j2;
        if (i >= 0) {
            this.f444b = 0;
            if (i > 0) {
                this.f448c++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo8168a(long r8) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            long r2 = r7.f451d
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x0032
            long r2 = r7.f451d
            long r4 = r8 - r2
            int r2 = r7.f448c
            r3 = 5
            if (r2 <= r3) goto L_0x002f
            r2 = 15000(0x3a98, float:2.102E-41)
        L_0x0015:
            long r2 = (long) r2
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0032
            r2 = r0
        L_0x001b:
            if (r2 == 0) goto L_0x0036
            long r2 = r7.f445b
            long r2 = r8 - r2
            r4 = 5000(0x1388, double:2.4703E-320)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0034
        L_0x0027:
            if (r0 == 0) goto L_0x002e
            long r0 = r7.f451d
            long r0 = r8 - r0
            int r1 = (int) r0
        L_0x002e:
            return r1
        L_0x002f:
            r2 = 5000(0x1388, float:7.006E-42)
            goto L_0x0015
        L_0x0032:
            r2 = r1
            goto L_0x001b
        L_0x0034:
            r0 = r1
            goto L_0x0027
        L_0x0036:
            r0 = r2
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0487s.mo8168a(long):int");
    }

    /* renamed from: a */
    public final void mo8125a(byte[] bArr, int i, int i2, long j) {
        this.f439a.mo8195a(this.f452e, this.f450d, bArr, j);
    }
}
