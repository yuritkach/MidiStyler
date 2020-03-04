package p004de.humatic.nmj;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/* renamed from: de.humatic.nmj.a */
final class C0453a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public InputStream f221a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public OutputStream f222a;

    /* renamed from: a */
    private ServerSocket f223a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Socket f224a;

    /* renamed from: a */
    private Vector<Integer> f225a = new Vector<>();

    /* renamed from: a */
    boolean f226a;

    /* renamed from: b */
    private ServerSocket f227b;

    /* renamed from: b */
    private boolean f228b;

    C0453a() {
        int i;
        try {
            i = Integer.parseInt(NMJConfig.getProperty("adb_query_port", "9999"));
        } catch (Exception e) {
            i = 9999;
        }
        if (i != 9999) {
            C0484p.logln(5, "ADBQuery, custom port " + i);
        }
        try {
            this.f223a = new ServerSocket(i);
            this.f223a.setReuseAddress(true);
            new Thread(new C0454a(this, this.f223a, (byte) 0)).start();
        } catch (BindException e2) {
            C0484p.logln(2, "ADBQueryServer: EADDRINUSE, using existing server");
            this.f226a = true;
            mo8111a();
            return;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (i == 9999) {
            try {
                this.f227b = new ServerSocket(9998);
                this.f227b.setReuseAddress(true);
                new Thread(new C0454a(this, this.f227b, (byte) 0)).start();
            } catch (BindException e4) {
                C0484p.logln(2, "ADBQueryServer: " + e4.toString());
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m218a() {
        try {
            int[] a = m216a(true);
            if (a.length == 0) {
                return false;
            }
            C0484p.logln(2, "ADBQueryServer: connecting to running app " + a.length);
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream outputStream = socket.getOutputStream();
            String a2 = NMJConfig.m38a();
            int length = (a.length * 4) + 2;
            if (a2.length() > 0 && a2.indexOf("nmj_") < 0) {
                length += a2.length() + 1;
            }
            byte[] bArr = new byte[length];
            bArr[0] = 16;
            bArr[1] = (byte) a.length;
            for (int i = 0; i < a.length; i++) {
                C0484p.m372a(a[i], bArr, (i << 2) + 2);
            }
            if (a2.length() > 0 && a2.indexOf("nmj_") < 0) {
                bArr[(a.length << 2) + 2] = (byte) a2.length();
                System.arraycopy(a2.getBytes(), 0, bArr, (a.length << 2) + 3, a2.length());
            }
            outputStream.write(bArr);
            outputStream.close();
            socket.close();
            return true;
        } catch (ConnectException e) {
            if (e.toString().indexOf("Connection refused") == -1) {
                return false;
            }
            NMJConfig.m71a(false, true);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8111a() {
        C0484p.logln(2, "ADBQueryServer closing");
        this.f228b = true;
        try {
            this.f223a.close();
        } catch (Exception e) {
        }
        try {
            this.f227b.close();
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public static int[] m216a(boolean z) {
        int i = 0;
        for (int i2 = 0; i2 < NMJConfig.getNumChannels(); i2++) {
            if (NMJConfig.getMode(i2) == 4) {
                if (!z) {
                    i++;
                } else if (NetworkMidiSystem.get().isOpen(-1, i2)) {
                    i++;
                }
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < NMJConfig.getNumChannels(); i4++) {
            if (NMJConfig.getMode(i4) == 4) {
                if (!z) {
                    iArr[i3] = NMJConfig.getPort(i4);
                    i3++;
                } else if (NetworkMidiSystem.get().isOpen(-1, i4)) {
                    iArr[i3] = NMJConfig.getPort(i4);
                    i3++;
                }
            }
        }
        return iArr;
    }

    /* renamed from: a */
    static /* synthetic */ boolean m214a(C0453a aVar, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < NMJConfig.getNumChannels(); i4++) {
            if (NMJConfig.getMode(i4) == 4) {
                if (i3 == i) {
                    NMJConfig.setPort(i4, i2);
                    C0484p.logln(1, String.valueOf(i) + " - changed port " + NMJConfig.getPort(i4));
                    return true;
                }
                i3++;
            }
        }
        return false;
    }

    /* renamed from: de.humatic.nmj.a$a */
    class C0454a extends Thread {

        /* renamed from: a */
        private ServerSocket f230a;

        /* renamed from: a */
        private byte[] f231a;

        private C0454a(ServerSocket serverSocket) {
            this.f231a = new byte[96];
            this.f230a = serverSocket;
        }

        /* synthetic */ C0454a(C0453a aVar, ServerSocket serverSocket, byte b) {
            this(serverSocket);
        }

        /* JADX WARNING: Removed duplicated region for block: B:65:0x0254 A[Catch:{ Exception -> 0x00d9 }] */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x0071 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r9 = this;
                r1 = 1
                r2 = 0
            L_0x0002:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this
                boolean r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)
                if (r0 == 0) goto L_0x000b
                return
            L_0x000b:
                java.net.ServerSocket r0 = r9.f230a     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.getLocalPort()     // Catch:{ Exception -> 0x00d9 }
                r3 = 9998(0x270e, float:1.401E-41)
                if (r0 == r3) goto L_0x002a
                r0 = 2
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r4 = "ADBQueryServer running on "
                r3.<init>(r4)     // Catch:{ Exception -> 0x00d9 }
                java.net.ServerSocket r4 = r9.f230a     // Catch:{ Exception -> 0x00d9 }
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00d9 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ Exception -> 0x00d9 }
            L_0x002a:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.net.ServerSocket r3 = r9.f230a     // Catch:{ Exception -> 0x00d9 }
                java.net.Socket r3 = r3.accept()     // Catch:{ Exception -> 0x00d9 }
                r0.f224a = r3     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r3 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.net.Socket r3 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r3)     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r3 = r3.getInputStream()     // Catch:{ Exception -> 0x00d9 }
                r0.f221a = r3     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r3 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.net.Socket r3 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r3)     // Catch:{ Exception -> 0x00d9 }
                java.io.OutputStream r3 = r3.getOutputStream()     // Catch:{ Exception -> 0x00d9 }
                r0.f222a = r3     // Catch:{ Exception -> 0x00d9 }
                r0 = 2
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d9 }
                r3.<init>()     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r4 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.net.Socket r4 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r4)     // Catch:{ Exception -> 0x00d9 }
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r4 = " connected"
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00d9 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ Exception -> 0x00d9 }
                r5 = r2
            L_0x0071:
                if (r5 != 0) goto L_0x0002
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.read()     // Catch:{ Exception -> 0x00d9 }
                if (r0 < 0) goto L_0x0002
                switch(r0) {
                    case 1: goto L_0x0083;
                    case 2: goto L_0x0177;
                    case 4: goto L_0x01e5;
                    case 16: goto L_0x020d;
                    default: goto L_0x0082;
                }     // Catch:{ Exception -> 0x00d9 }
            L_0x0082:
                goto L_0x0071
            L_0x0083:
                byte[] r0 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r3 = 0
                r4 = 1
                r0[r3] = r4     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                r3 = 1
                int[] r4 = p004de.humatic.nmj.C0453a.m216a(true)     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.size()     // Catch:{ Exception -> 0x00d9 }
                if (r0 != 0) goto L_0x0111
                int r0 = r4.length     // Catch:{ Exception -> 0x00d9 }
                byte[] r3 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r6 = 1
                p004de.humatic.nmj.C0484p.m372a((int) r0, (byte[]) r3, (int) r6)     // Catch:{ Exception -> 0x00d9 }
                r0 = r2
            L_0x00a4:
                int r3 = r4.length     // Catch:{ Exception -> 0x00d9 }
                if (r0 < r3) goto L_0x0103
                int r0 = r4.length     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0 * 4
                int r0 = r0 + 5
            L_0x00ac:
                java.lang.String r3 = android.os.Build.MODEL     // Catch:{ Exception -> 0x02d2 }
                int r4 = r3.length()     // Catch:{ Exception -> 0x02d2 }
                byte[] r6 = r9.f231a     // Catch:{ Exception -> 0x02d2 }
                p004de.humatic.nmj.C0484p.m372a((int) r4, (byte[]) r6, (int) r0)     // Catch:{ Exception -> 0x02d2 }
                int r0 = r0 + 4
                byte[] r4 = r3.getBytes()     // Catch:{ Exception -> 0x02d2 }
                r6 = 0
                byte[] r7 = r9.f231a     // Catch:{ Exception -> 0x02d2 }
                int r8 = r3.length()     // Catch:{ Exception -> 0x02d2 }
                java.lang.System.arraycopy(r4, r6, r7, r0, r8)     // Catch:{ Exception -> 0x02d2 }
                int r3 = r3.length()     // Catch:{ Exception -> 0x02d2 }
                int r0 = r0 + r3
            L_0x00cc:
                de.humatic.nmj.a r3 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.OutputStream r3 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r3)     // Catch:{ Exception -> 0x00d9 }
                byte[] r4 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r6 = 0
                r3.write(r4, r6, r0)     // Catch:{ Exception -> 0x00d9 }
                goto L_0x0071
            L_0x00d9:
                r0 = move-exception
                java.lang.String r3 = r0.toString()
                java.lang.String r4 = "closed"
                int r3 = r3.indexOf(r4)
                if (r3 >= 0) goto L_0x0002
                java.lang.String r3 = r0.toString()
                java.lang.String r4 = "Interrupted"
                int r3 = r3.indexOf(r4)
                if (r3 >= 0) goto L_0x0002
                java.lang.String r3 = r0.toString()
                java.lang.String r4 = "Bad file number"
                int r3 = r3.indexOf(r4)
                if (r3 >= 0) goto L_0x0002
                r0.printStackTrace()
                goto L_0x0002
            L_0x0103:
                r3 = r4[r0]     // Catch:{ Exception -> 0x00d9 }
                byte[] r6 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                int r7 = r0 * 4
                int r7 = r7 + 5
                p004de.humatic.nmj.C0484p.m372a((int) r3, (byte[]) r6, (int) r7)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0 + 1
                goto L_0x00a4
            L_0x0111:
                int r0 = r4.length     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r3 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r3 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r3)     // Catch:{ Exception -> 0x00d9 }
                int r3 = r3.size()     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0 + r3
                byte[] r3 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r6 = 1
                p004de.humatic.nmj.C0484p.m372a((int) r0, (byte[]) r3, (int) r6)     // Catch:{ Exception -> 0x00d9 }
                r0 = r2
            L_0x0124:
                int r3 = r4.length     // Catch:{ Exception -> 0x00d9 }
                if (r0 < r3) goto L_0x014d
                int r0 = r4.length     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0 * 4
                int r6 = r0 + 5
                r3 = r2
            L_0x012d:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.size()     // Catch:{ Exception -> 0x00d9 }
                if (r3 < r0) goto L_0x015b
                int r0 = r4.length     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0 * 4
                int r0 = r0 + 5
                de.humatic.nmj.a r3 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r3 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r3)     // Catch:{ Exception -> 0x00d9 }
                int r3 = r3.size()     // Catch:{ Exception -> 0x00d9 }
                int r3 = r3 * 4
                int r0 = r0 + r3
                goto L_0x00ac
            L_0x014d:
                r3 = r4[r0]     // Catch:{ Exception -> 0x00d9 }
                byte[] r6 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                int r7 = r0 * 4
                int r7 = r7 + 5
                p004de.humatic.nmj.C0484p.m372a((int) r3, (byte[]) r6, (int) r7)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0 + 1
                goto L_0x0124
            L_0x015b:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                java.lang.Object r0 = r0.get(r3)     // Catch:{ Exception -> 0x00d9 }
                java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.intValue()     // Catch:{ Exception -> 0x00d9 }
                byte[] r7 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                int r8 = r3 * 4
                int r8 = r8 + r6
                p004de.humatic.nmj.C0484p.m372a((int) r0, (byte[]) r7, (int) r8)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r3 + 1
                r3 = r0
                goto L_0x012d
            L_0x0177:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                byte[] r3 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r4 = 0
                de.humatic.nmj.a r6 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r6 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r6)     // Catch:{ Exception -> 0x00d9 }
                int r6 = r6.available()     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.read(r3, r4, r6)     // Catch:{ Exception -> 0x00d9 }
                r3 = 1
                java.lang.String r4 = "Port change received"
                byte[] r6 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r7 = 0
                p004de.humatic.nmj.C0484p.m371a((int) r3, (java.lang.String) r4, (byte[]) r6, (int) r7, (int) r0)     // Catch:{ Exception -> 0x00d9 }
                byte[] r0 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r3 = 0
                int r3 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r3)     // Catch:{ Exception -> 0x00d9 }
                r0 = r2
            L_0x019f:
                if (r0 < r3) goto L_0x01b9
                r0 = r1
            L_0x01a2:
                if (r0 == 0) goto L_0x0071
                byte[] r0 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r3 = 0
                r4 = 4
                r0[r3] = r4     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.OutputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                byte[] r3 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r4 = 0
                r6 = 1
                r0.write(r3, r4, r6)     // Catch:{ Exception -> 0x00d9 }
                goto L_0x0071
            L_0x01b9:
                byte[] r4 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                int r6 = r0 * 4
                int r6 = r6 + 4
                int r4 = p004de.humatic.nmj.C0484p.m348a((byte[]) r4, (int) r6)     // Catch:{ Exception -> 0x00d9 }
                if (r4 == 0) goto L_0x01e2
                de.humatic.nmj.a r6 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                boolean r4 = p004de.humatic.nmj.C0453a.m214a(r6, r0, r4)     // Catch:{ Exception -> 0x00d9 }
                if (r4 != 0) goto L_0x01e2
                byte[] r0 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r3 = 0
                r4 = 2
                r0[r3] = r4     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.OutputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                byte[] r3 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r4 = 0
                r6 = 1
                r0.write(r3, r4, r6)     // Catch:{ Exception -> 0x00d9 }
                r0 = r2
                goto L_0x01a2
            L_0x01e2:
                int r0 = r0 + 1
                goto L_0x019f
            L_0x01e5:
                byte[] r0 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r3 = 0
                r4 = 4
                r0[r3] = r4     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.OutputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                byte[] r3 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r4 = 0
                r5 = 1
                r0.write(r3, r4, r5)     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.OutputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                r0.flush()     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.net.Socket r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                r0.close()     // Catch:{ Exception -> 0x00d9 }
                r5 = r1
                goto L_0x0071
            L_0x020d:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                int r6 = r0.read()     // Catch:{ Exception -> 0x00d9 }
                if (r6 <= 0) goto L_0x0071
                r0 = 2
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r4 = "Secondary app w/ "
                r3.<init>(r4)     // Catch:{ Exception -> 0x00d9 }
                java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r4 = " channels"
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00d9 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ Exception -> 0x00d9 }
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                byte[] r3 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r4 = 0
                int r7 = r6 * 4
                int r0 = r0.read(r3, r4, r7)     // Catch:{ Exception -> 0x00d9 }
                int r3 = r6 * 4
                if (r0 != r3) goto L_0x0071
                r4 = r2
            L_0x0246:
                if (r4 < r6) goto L_0x027d
            L_0x0248:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.read()     // Catch:{ Exception -> 0x00d9 }
                if (r0 <= 0) goto L_0x0071
                de.humatic.nmj.a r3 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.io.InputStream r3 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r3)     // Catch:{ Exception -> 0x00d9 }
                byte[] r4 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r6 = 0
                r3.read(r4, r6, r0)     // Catch:{ Exception -> 0x00d9 }
                r3 = 2
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r6 = "sec. app name: "
                r4.<init>(r6)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x00d9 }
                byte[] r7 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                r8 = 0
                r6.<init>(r7, r8, r0)     // Catch:{ Exception -> 0x00d9 }
                java.lang.StringBuilder r0 = r4.append(r6)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00d9 }
                p004de.humatic.nmj.C0484p.logln(r3, r0)     // Catch:{ Exception -> 0x00d9 }
                goto L_0x0071
            L_0x027d:
                byte[] r0 = r9.f231a     // Catch:{ Exception -> 0x00d9 }
                int r3 = r4 * 4
                int r7 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r3)     // Catch:{ Exception -> 0x00d9 }
                r0 = 2
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r8 = "Secondary app port "
                r3.<init>(r8)     // Catch:{ Exception -> 0x00d9 }
                java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ Exception -> 0x00d9 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00d9 }
                p004de.humatic.nmj.C0484p.logln(r0, r3)     // Catch:{ Exception -> 0x00d9 }
                r3 = r2
            L_0x0299:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.size()     // Catch:{ Exception -> 0x00d9 }
                if (r3 < r0) goto L_0x02ba
                r0 = r2
            L_0x02a6:
                if (r0 != 0) goto L_0x0248
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                java.lang.Integer r3 = new java.lang.Integer     // Catch:{ Exception -> 0x00d9 }
                r3.<init>(r7)     // Catch:{ Exception -> 0x00d9 }
                r0.add(r3)     // Catch:{ Exception -> 0x00d9 }
                int r0 = r4 + 1
                r4 = r0
                goto L_0x0246
            L_0x02ba:
                de.humatic.nmj.a r0 = p004de.humatic.nmj.C0453a.this     // Catch:{ Exception -> 0x00d9 }
                java.util.Vector r0 = p004de.humatic.nmj.C0453a.m206a((p004de.humatic.nmj.C0453a) r0)     // Catch:{ Exception -> 0x00d9 }
                java.lang.Object r0 = r0.get(r3)     // Catch:{ Exception -> 0x00d9 }
                java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x00d9 }
                int r0 = r0.intValue()     // Catch:{ Exception -> 0x00d9 }
                if (r0 != r7) goto L_0x02ce
                r0 = r1
                goto L_0x02a6
            L_0x02ce:
                int r0 = r3 + 1
                r3 = r0
                goto L_0x0299
            L_0x02d2:
                r3 = move-exception
                goto L_0x00cc
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0453a.C0454a.run():void");
        }
    }
}
