package p004de.humatic.nmj;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;
import java.util.Vector;

/* renamed from: de.humatic.nmj.c */
final class C0456c extends C0503w {

    /* renamed from: a */
    static int f236a;

    /* renamed from: a */
    static BroadcastReceiver f237a;

    /* renamed from: a */
    static Vector<BluetoothDevice> f238a;

    /* renamed from: a */
    private static final String[] f239a = {"1E873BF0-4349-4b0f-9835-1117C7E55C8D", "D74754C7-17EB-4350-AEF9-A048188D10AB", "105BEADD-0FC0-44e6-B46F-831DE171B5E6", "A35EC3BD-8A57-4e94-8B7A-777217BF3CA3"};

    /* renamed from: b */
    static int f240b;

    /* renamed from: b */
    private static BluetoothAdapter f241b;

    /* renamed from: a */
    private BluetoothAdapter f242a;

    /* renamed from: a */
    private C0460a f243a;

    /* renamed from: a */
    private C0462c f244a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public InputStream f245a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public OutputStream f246a;

    /* renamed from: a */
    private String f247a;

    /* renamed from: a */
    private byte[] f248a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f249b = true;

    /* renamed from: c */
    private Vector f250c;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public boolean f251c;

    /* renamed from: d */
    private Vector<C0455b> f252d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public int f253e;

    C0456c(int i, C0479n nVar) throws Exception {
        super(nVar, i);
        new Vector();
        this.f250c = new Vector();
        byte[] bArr = new byte[15];
        bArr[0] = -16;
        bArr[1] = 126;
        bArr[2] = Byte.MAX_VALUE;
        bArr[3] = 6;
        bArr[4] = 2;
        bArr[5] = Byte.MAX_VALUE;
        bArr[14] = -9;
        this.f248a = bArr;
        this.f247a = "nmj Bluetooth MIDI ch.";
        this.f247a = String.valueOf(this.f247a) + i;
        this.f242a = BluetoothAdapter.getDefaultAdapter();
        if (this.f242a == null) {
            throw new IOException("Bluetooth not available");
        }
        C0484p.m382a(NMJConfig.m39a(0), this.f248a, 10);
        C0484p.m382a(NMJConfig.m39a(1), this.f248a, 12);
        if (NMJConfig.getIP(this.f575c) != null) {
            BluetoothDevice remoteDevice = this.f242a.getRemoteDevice(NMJConfig.getIP(this.f575c));
            UUID fromString = UUID.fromString(f239a[NMJConfig.getPort(this.f575c)]);
            BluetoothSocket createRfcommSocketToServiceRecord = remoteDevice.createRfcommSocketToServiceRecord(fromString);
            C0484p.logln(1, "BluetoothSocket created " + createRfcommSocketToServiceRecord.getRemoteDevice().toString() + " " + fromString.toString());
            if (createRfcommSocketToServiceRecord == null) {
                throw new IOException("Failed to create BluetoothSocket");
            }
            this.f242a.cancelDiscovery();
            createRfcommSocketToServiceRecord.connect();
            this.f253e = 3;
            this.f243a = new C0460a(this, createRfcommSocketToServiceRecord, (byte) 0);
            new Thread(this.f243a).start();
            return;
        }
        this.f252d = new Vector<>();
        this.f244a = new C0462c();
        new Thread(this.f244a).start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void m234a() {
        C0484p.logln(1, "btio close");
        this.f250c.removeAllElements();
        try {
            mo8034a(C0484p.m357a(0, 1));
            byte[] bArr = new byte[8];
            bArr[0] = -16;
            bArr[1] = 125;
            bArr[2] = 110;
            bArr[3] = 109;
            bArr[4] = 106;
            bArr[6] = 1;
            bArr[7] = -9;
            this.f246a.write(bArr);
        } catch (Exception e) {
        }
        this.f253e = 4;
        try {
            this.f244a.mo8121a();
        } catch (Exception e2) {
        }
        try {
            this.f243a.mo8118a();
        } catch (Exception e3) {
        }
    }

    /* renamed from: a */
    public final synchronized void mo8113a(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice) {
        try {
            this.f252d.add(new C0455b(this, this.f252d.size(), bluetoothDevice.getName(), bluetoothDevice.getAddress(), NMJConfig.m40a(this.f575c, 2)));
            C0484p.logln(1, String.valueOf(this.f575c) + " - bluetooth client connected, uuid: \n" + f239a[NMJConfig.m40a(this.f575c, 2)]);
            NMJConfig.m59a(this.f575c, 4, 64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NMJConfig.m59a(this.f575c, 4, 32);
        try {
            this.f242a.cancelDiscovery();
        } catch (Exception e2) {
        }
        try {
            this.f244a.mo8121a();
            this.f253e = 3;
            this.f243a = new C0460a(this, bluetoothSocket, (byte) 0);
            new Thread(this.f243a).start();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo8114b() {
        C0484p.logln(1, "BT - client closed, state: " + this.f253e);
        if (this.f253e == 3) {
            this.f253e = 4;
            NMJConfig.m59a(this.f575c, 4, 128);
            try {
                this.f243a.mo8118a();
            } catch (Exception e) {
            }
            if (NMJConfig.getIP(this.f575c) == null) {
                C0484p.logln(1, "BT - restarting server thread");
                this.f244a = new C0462c();
                new Thread(this.f244a).start();
            }
        }
    }

    /* renamed from: de.humatic.nmj.c$c */
    class C0462c extends Thread {

        /* renamed from: a */
        private final BluetoothServerSocket f265a;

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0098  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00b6  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public C0462c() {
            /*
                r10 = this;
                r1 = 0
                r8 = -1
                r7 = -2147483644(0xffffffff80000004, float:-5.6E-45)
                r6 = 1
                p004de.humatic.nmj.C0456c.this = r11
                r10.<init>()
                r0 = 1
                r11.f253e = r0     // Catch:{ IOException -> 0x006c }
                int r0 = r11.f575c     // Catch:{ IOException -> 0x006c }
                int r0 = p004de.humatic.nmj.NMJConfig.getPort(r0)     // Catch:{ IOException -> 0x006c }
                java.lang.String[] r2 = p004de.humatic.nmj.C0456c.mo8031a()     // Catch:{ IOException -> 0x006c }
                r2 = r2[r0]     // Catch:{ IOException -> 0x006c }
                java.util.UUID r2 = java.util.UUID.fromString(r2)     // Catch:{ IOException -> 0x006c }
                boolean r3 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r11)     // Catch:{ IOException -> 0x006c }
                if (r3 == 0) goto L_0x0029
                r3 = -1
                p004de.humatic.nmj.NMJConfig.getFlags(r3)     // Catch:{ IOException -> 0x006c }
            L_0x0029:
                r3 = 1
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x006c }
                java.lang.String r5 = "bluetooth midi (btIdx: "
                r4.<init>(r5)     // Catch:{ IOException -> 0x006c }
                java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ IOException -> 0x006c }
                java.lang.String r4 = "): starting server thread for uuid: \n"
                java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ IOException -> 0x006c }
                java.lang.String[] r4 = p004de.humatic.nmj.C0456c.mo8031a()     // Catch:{ IOException -> 0x006c }
                int r5 = r11.f575c     // Catch:{ IOException -> 0x006c }
                int r5 = p004de.humatic.nmj.NMJConfig.getPort(r5)     // Catch:{ IOException -> 0x006c }
                r4 = r4[r5]     // Catch:{ IOException -> 0x006c }
                java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ IOException -> 0x006c }
                java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x006c }
                p004de.humatic.nmj.C0484p.logln(r3, r0)     // Catch:{ IOException -> 0x006c }
                r0 = 0
                r11.f249b = false     // Catch:{ IOException -> 0x006c }
                android.bluetooth.BluetoothAdapter r0 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r11)     // Catch:{ IOException -> 0x006c }
                java.lang.String r3 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r11)     // Catch:{ IOException -> 0x006c }
                android.bluetooth.BluetoothServerSocket r0 = r0.listenUsingRfcommWithServiceRecord(r3, r2)     // Catch:{ IOException -> 0x006c }
                int r1 = r11.f575c     // Catch:{ IOException -> 0x00c0 }
                r2 = 4
                r3 = 4
                p004de.humatic.nmj.NMJConfig.m59a((int) r1, (int) r2, (int) r3)     // Catch:{ IOException -> 0x00c0 }
            L_0x0069:
                r10.f265a = r0
                return
            L_0x006c:
                r0 = move-exception
                r9 = r0
                r0 = r1
                r1 = r9
            L_0x0070:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "failed to set up RFCOMM listening: "
                r2.<init>(r3)
                java.lang.String r3 = r1.toString()
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                p004de.humatic.nmj.C0484p.logln(r6, r2)
                java.lang.String r2 = r1.toString()
                java.lang.String r3 = "Error: -1"
                int r2 = r2.indexOf(r3)
                if (r2 == r8) goto L_0x00b6
                int r2 = android.os.Build.VERSION.SDK_INT
                r3 = 18
                if (r2 >= r3) goto L_0x00b6
                int r1 = r11.f575c
                java.lang.String r2 = "IOException: run out of rfc control block\nThis is a leak in Android 4.2 (Bug ID 41110). To create another Bluetooth connection, restart the device"
                p004de.humatic.nmj.NMJConfig.m60a((int) r1, (int) r7, (java.lang.String) r2)
            L_0x009f:
                de.humatic.nmj.NetworkMidiSystem r1 = p004de.humatic.nmj.NetworkMidiSystem.get()     // Catch:{ Exception -> 0x00b4 }
                int r2 = r11.f575c     // Catch:{ Exception -> 0x00b4 }
                int r2 = p004de.humatic.nmj.NMJConfig.getIO(r2)     // Catch:{ Exception -> 0x00b4 }
                int r3 = r11.f575c     // Catch:{ Exception -> 0x00b4 }
                de.humatic.nmj.NetworkMidiPort r1 = r1.mo8102a((int) r2, (int) r3)     // Catch:{ Exception -> 0x00b4 }
                r2 = 0
                r1.close(r2)     // Catch:{ Exception -> 0x00b4 }
                goto L_0x0069
            L_0x00b4:
                r1 = move-exception
                goto L_0x0069
            L_0x00b6:
                int r2 = r11.f575c
                java.lang.String r1 = r1.toString()
                p004de.humatic.nmj.NMJConfig.m60a((int) r2, (int) r7, (java.lang.String) r1)
                goto L_0x009f
            L_0x00c0:
                r1 = move-exception
                goto L_0x0070
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0456c.C0462c.<init>(de.humatic.nmj.c):void");
        }

        public final void run() {
            C0456c.this.f253e = 1;
            while (C0456c.m219a(C0456c.this) != 3 && C0456c.m219a(C0456c.this) != 4) {
                try {
                    BluetoothSocket accept = this.f265a.accept();
                    if (accept != null) {
                        C0484p.logln(1, "socket accepted: " + accept);
                        synchronized (C0456c.this) {
                            switch (C0456c.m219a(C0456c.this)) {
                                case 0:
                                case 3:
                                    C0484p.logln(1, "oops, unexpected state " + C0456c.m219a(C0456c.this));
                                    try {
                                        accept.close();
                                        break;
                                    } catch (IOException e) {
                                        C0484p.logln(1, "failed to close unwanted socket: " + e.toString());
                                        break;
                                    }
                                case 1:
                                case 2:
                                    C0456c.this.f253e = 3;
                                    C0456c.this.mo8113a(accept, accept.getRemoteDevice());
                                    break;
                            }
                        }
                    }
                } catch (IOException e2) {
                    C0484p.logln(1, "socket accept() failed: " + e2);
                    return;
                } catch (Exception e3) {
                    C0484p.logln(1, "socket accept() failed: " + e3);
                    return;
                }
            }
            return;
        }

        /* renamed from: a */
        public final void mo8121a() {
            C0484p.logln(1, "shutting down server thread");
            try {
                this.f265a.close();
            } catch (IOException e) {
                C0484p.logln(1, "failed to close server" + e.toString());
            }
        }
    }

    /* renamed from: de.humatic.nmj.c$a */
    class C0460a extends Thread {

        /* renamed from: a */
        private BluetoothSocket f260a;

        /* renamed from: a */
        private byte[] f262a;

        private C0460a(BluetoothSocket bluetoothSocket) throws IOException {
            this.f260a = bluetoothSocket;
            if (NMJConfig.getIO(C0456c.this.f575c) <= 0) {
                C0456c.this.f245a = this.f260a.getInputStream();
            }
            if (Math.abs(NMJConfig.getIO(C0456c.this.f575c)) == 1) {
                C0456c.this.f246a = this.f260a.getOutputStream();
            }
            new Thread(new C0461b(C0456c.this, (byte) 0)).start();
            this.f262a = new byte[1460];
            C0456c.this.f251c = false;
        }

        /* synthetic */ C0460a(C0456c cVar, BluetoothSocket bluetoothSocket, byte b) throws IOException {
            this(bluetoothSocket);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final void mo8118a() {
            C0484p.logln(2, "BLUETOOTH MIDI thread close");
            try {
                C0456c.m219a(C0456c.this).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                C0456c.m219a(C0456c.this).close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                this.f260a.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }

        /* JADX WARNING: CFG modification limit reached, blocks count: 132 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r7 = this;
                r6 = 3
                r5 = 4
                r4 = 1
                r3 = -1
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this
                int r0 = r0.f575c
                int r0 = p004de.humatic.nmj.NMJConfig.getIO(r0)
                if (r0 != r4) goto L_0x00a7
            L_0x000e:
                return
            L_0x000f:
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this     // Catch:{ Exception -> 0x0031 }
                java.io.InputStream r0 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r0)     // Catch:{ Exception -> 0x0031 }
                byte[] r1 = r7.f262a     // Catch:{ Exception -> 0x0031 }
                int r0 = r0.read(r1)     // Catch:{ Exception -> 0x0031 }
                if (r0 >= 0) goto L_0x0098
                r0 = 2
                java.lang.String r1 = "BLUETOOTH closed by client"
                p004de.humatic.nmj.C0484p.logln(r0, r1)     // Catch:{ Exception -> 0x0031 }
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this     // Catch:{ Exception -> 0x0031 }
                int r0 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r0)     // Catch:{ Exception -> 0x0031 }
                if (r0 != r6) goto L_0x000e
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this     // Catch:{ Exception -> 0x0031 }
                r0.mo8114b()     // Catch:{ Exception -> 0x0031 }
                goto L_0x000e
            L_0x0031:
                r0 = move-exception
                java.lang.String r1 = r0.toString()
                java.lang.String r1 = r1.toLowerCase()
                java.lang.String r2 = "socket closed"
                int r1 = r1.indexOf(r2)
                if (r1 != r3) goto L_0x0062
                java.lang.String r1 = r0.toString()
                java.lang.String r1 = r1.toLowerCase()
                java.lang.String r2 = "software caused connection abort"
                int r1 = r1.indexOf(r2)
                if (r1 != r3) goto L_0x0062
                java.lang.String r1 = r0.toString()
                java.lang.String r1 = r1.toLowerCase()
                java.lang.String r2 = "connection reset by peer"
                int r1 = r1.indexOf(r2)
                if (r1 == r3) goto L_0x00b1
            L_0x0062:
                de.humatic.nmj.c r1 = p004de.humatic.nmj.C0456c.this
                int r1 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r1)
                if (r1 != r6) goto L_0x000e
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Connection closed by client "
                r1.<init>(r2)
                java.lang.String r0 = r0.getMessage()
                java.lang.StringBuilder r0 = r1.append(r0)
                java.lang.String r0 = r0.toString()
                p004de.humatic.nmj.C0484p.logln(r4, r0)
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this
                boolean r0 = r0.f251c
                if (r0 == 0) goto L_0x0091
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this
                int r0 = r0.f575c
                r1 = 128(0x80, float:1.794E-43)
                p004de.humatic.nmj.NMJConfig.m59a((int) r0, (int) r5, (int) r1)
            L_0x0091:
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this
                r0.mo8114b()
                goto L_0x000e
            L_0x0098:
                de.humatic.nmj.c r1 = p004de.humatic.nmj.C0456c.this     // Catch:{ Exception -> 0x0031 }
                r2 = 1
                r1.f251c = r2     // Catch:{ Exception -> 0x0031 }
                de.humatic.nmj.c r1 = p004de.humatic.nmj.C0456c.this     // Catch:{ Exception -> 0x0031 }
                de.humatic.nmj.n r1 = r1.f572a     // Catch:{ Exception -> 0x0031 }
                byte[] r2 = r7.f262a     // Catch:{ Exception -> 0x0031 }
                r1.mo8155a((byte[]) r2, (int) r0)     // Catch:{ Exception -> 0x0031 }
            L_0x00a7:
                de.humatic.nmj.c r0 = p004de.humatic.nmj.C0456c.this
                int r0 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r0)
                if (r0 != r5) goto L_0x000f
                goto L_0x000e
            L_0x00b1:
                de.humatic.nmj.c r1 = p004de.humatic.nmj.C0456c.this
                int r1 = p004de.humatic.nmj.C0456c.m219a((p004de.humatic.nmj.C0456c) r1)
                if (r1 == r5) goto L_0x00a7
                java.lang.String r1 = "BT receive "
                p004de.humatic.nmj.C0484p.m377a((java.lang.Exception) r0, (java.lang.String) r1)
                goto L_0x00a7
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0456c.C0460a.run():void");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8034a(byte[] bArr) throws IOException {
        if (this.f253e == 3 && this.f246a != null && bArr != null) {
            try {
                if (bArr.length < this.f576d) {
                    this.f246a.write(bArr);
                    this.f246a.flush();
                    return;
                }
                int i = 0;
                while (i < bArr.length) {
                    this.f246a.write(bArr, i, Math.min(this.f576d, bArr.length - i));
                    i += Math.min(this.f576d, bArr.length - i);
                    this.f246a.flush();
                }
            } catch (IOException e) {
                if (e.toString().toLowerCase().indexOf("software caused connection abort") == -1 && e.toString().toLowerCase().indexOf("connection reset by peer") == -1) {
                    if (e.toString().toLowerCase().indexOf("socket closed") == -1 || this.f251c) {
                        throw e;
                    }
                } else if (this.f253e != 4) {
                    C0484p.logln(1, "Connection closed by client");
                    NMJConfig.m59a(this.f575c, 4, 128);
                    mo8114b();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: de.humatic.nmj.c$b */
    class C0461b extends Thread {

        /* renamed from: a */
        private int[] f264a;

        private C0461b() {
            this.f264a = new int[]{896, 768, 512, 256, 128, 32, 16};
        }

        /* synthetic */ C0461b(C0456c cVar, byte b) {
            this();
        }

        public final void run() {
            try {
                sleep(1000);
                for (int a : this.f264a) {
                    C0456c.m219a(C0456c.this).write(C0484p.m357a(1, a));
                    C0456c.m219a(C0456c.this).flush();
                    int i = 0;
                    while (C0456c.this.f576d == 8) {
                        int i2 = i + 1;
                        if (i >= 5) {
                            break;
                        }
                        sleep(100);
                        i = i2;
                    }
                    if (C0456c.this.f576d != 8) {
                        break;
                    }
                }
                C0456c.this.mo8034a(C0484p.m357a(0, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    protected static void m224a(final BluetoothDevice bluetoothDevice, final C0474j jVar) {
        C0484p.logln(1, "getMIDIServices for " + bluetoothDevice.getName());
        for (final int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                public final void run() {
                    BluetoothSocket bluetoothSocket = null;
                    try {
                        BluetoothSocket createRfcommSocketToServiceRecord = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(C0456c.mo8031a()[i]));
                        createRfcommSocketToServiceRecord.connect();
                        C0456c.f240b++;
                        if (jVar != null) {
                            C0467g gVar = new C0467g("_bt-midi", -1);
                            gVar.f281c = bluetoothDevice.getAddress();
                            gVar.f282d = "";
                            gVar.mo8126a(String.valueOf(bluetoothDevice.getName()) + " BT-MIDI " + (i + 1));
                            gVar.f279b = i;
                            jVar.mo8142a(2, gVar, -1);
                        }
                        try {
                            createRfcommSocketToServiceRecord.close();
                        } catch (Exception e) {
                        }
                    } catch (IOException e2) {
                        C0456c.f240b++;
                        try {
                            bluetoothSocket.close();
                        } catch (Exception e3) {
                        }
                    }
                }
            }).start();
        }
    }

    /* renamed from: a */
    protected static void m225a(final Context context, final C0474j jVar) {
        f241b = BluetoothAdapter.getDefaultAdapter();
        C0484p.logln(2, "Running BT discovery");
        if (f238a != null) {
            f238a.removeAllElements();
        }
        f237a = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    if ("android.bluetooth.device.action.FOUND".equals(action)) {
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (bluetoothDevice != null && bluetoothDevice.getName() != null) {
                            C0484p.logln(2, String.valueOf(bluetoothDevice.getName()) + "\n" + bluetoothDevice.getAddress() + "\n" + bluetoothDevice.getBluetoothClass());
                            if (C0456c.f238a == null) {
                                C0456c.f238a = new Vector<>();
                            }
                            C0456c.f238a.add(bluetoothDevice);
                        }
                    } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                        C0484p.logln(2, "Bluetooth client scan finished");
                        context.unregisterReceiver(C0456c.f237a);
                        if (C0456c.f238a == null || C0456c.f238a.size() == 0) {
                            jVar.mo8141a(2, 1);
                            return;
                        }
                        C0456c.f236a = C0456c.f238a.size() << 2;
                        C0456c.f240b = 0;
                        final C0474j jVar = jVar;
                        new Thread(new Runnable(this) {
                            public final void run() {
                                while (C0456c.f240b < C0456c.f236a) {
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                    }
                                }
                                if (jVar != null) {
                                    jVar.mo8141a(2, 1);
                                }
                            }
                        }).start();
                        Iterator<BluetoothDevice> it = C0456c.f238a.iterator();
                        while (it.hasNext()) {
                            C0456c.m224a(it.next(), jVar);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    jVar.mo8141a(2, 1);
                    try {
                        context.unregisterReceiver(C0456c.f237a);
                    } catch (Exception e2) {
                    }
                }
            }
        };
        try {
            f241b.cancelDiscovery();
            Thread.currentThread();
            Thread.sleep(500);
        } catch (Exception e) {
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        context.registerReceiver(f237a, intentFilter);
        f241b.startDiscovery();
        jVar.mo8141a(2, 0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0502v[] m237a() {
        if (this.f252d == null || this.f252d.size() == 0) {
            return null;
        }
        C0502v[] vVarArr = new C0502v[this.f252d.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f252d.size()) {
                return vVarArr;
            }
            C0455b bVar = this.f252d.get(i2);
            C0502v vVar = new C0502v();
            vVar.f562a = bVar.f232a;
            vVar.f563a = bVar.f233a;
            vVar.f564b = 2;
            vVar.f565b = bVar.f235b;
            vVar.f566c = bVar.f234b;
            vVarArr[i2] = vVar;
            i = i2 + 1;
        }
    }
}
