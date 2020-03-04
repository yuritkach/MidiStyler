package p004de.humatic.nmj;

import android.os.Looper;
import java.lang.reflect.Array;
import java.util.Vector;

/* renamed from: de.humatic.nmj.NetworkMidiOutput */
public class NetworkMidiOutput extends NetworkMidiPort {

    /* renamed from: a */
    private long f186a;

    /* renamed from: a */
    private NetworkMidiInput f187a;

    /* renamed from: a */
    private NetworkMidiListener f188a;

    /* renamed from: a */
    private C0448a f189a;

    /* renamed from: a */
    private C0479n f190a;

    /* renamed from: a */
    private C0482o f191a;

    /* renamed from: c */
    private boolean f192c;

    public NetworkMidiOutput() {
        this.f208b = true;
    }

    NetworkMidiOutput(int i, NetworkMidiSystem networkMidiSystem, NetworkMidiClient networkMidiClient) throws Exception {
        super(i, networkMidiSystem, networkMidiClient);
        if (NMJConfig.getIO(i) > 0) {
            this.f191a = new C0482o(i);
        } else {
            this.f187a = NetworkMidiSystem.get().openInput(i, networkMidiClient);
            if (this.f187a != null) {
                this.f190a = this.f187a.f185a;
            } else {
                return;
            }
        }
        this.f207a = false;
        this.f186a = Looper.getMainLooper().getThread().getId();
        if (this.f190a == null) {
            this.f205a.mo8101a(this.f204a);
        }
    }

    public synchronized void close(NetworkMidiClient networkMidiClient) {
        if (!this.f207a && !this.f208b) {
            C0484p.logln(5, String.valueOf(this.f204a) + " - Closing " + this + " nr. clients: " + this.f206a.size());
            if (networkMidiClient != null) {
                mo8098b(networkMidiClient);
                if (this.f206a.size() > 0) {
                    mo8089a();
                }
            }
            if (this.f191a != null) {
                this.f191a.mo8161a();
            } else if (this.f190a != null) {
                this.f187a.close(networkMidiClient);
            }
            this.f206a.removeAllElements();
            this.f207a = true;
            this.f205a.mo8102a(1, this.f204a);
            this.f207a = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo8089a() {
        if (!this.f208b) {
            this.f206a.removeAllElements();
            if (this.f191a != null) {
                this.f191a.mo8161a();
            } else if (this.f190a != null) {
                this.f190a.mo8151a();
            }
            this.f205a.mo8102a(NMJConfig.getIO(this.f204a), this.f204a);
            this.f207a = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8088a(int i) {
        if (!this.f208b) {
            this.f204a = i;
            if (this.f191a != null) {
                this.f191a.mo8162a(i);
            }
            if (this.f190a != null) {
                this.f190a.mo8152a(i);
            }
        }
    }

    public void sendMidi(byte[] bArr) throws Exception {
        if (!this.f207a && bArr != null && !this.f208b) {
            if (this.f192c) {
                m187a(bArr);
                return;
            }
            if (this.f191a != null) {
                this.f191a.mo8163a(bArr);
            }
            if (this.f190a != null) {
                this.f190a.mo8154a(bArr);
            }
        }
    }

    public void sendMidiOnThread(byte[] bArr) throws Exception {
        if (!this.f208b) {
            if (this.f189a == null) {
                this.f189a = new C0448a(this, (byte) 0);
                this.f189a.start();
            }
            if (this.f192c) {
                m187a(bArr);
            } else {
                this.f189a.m191a(bArr);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m187a(byte[] bArr) throws Exception {
        if (!this.f207a && bArr != null && !this.f208b) {
            if (this.f189a == null || Thread.currentThread().getId() != this.f186a) {
                if (this.f191a != null) {
                    this.f191a.mo8163a(bArr);
                }
                if (this.f190a != null) {
                    this.f190a.mo8154a(bArr);
                    return;
                }
                return;
            }
            this.f189a.m191a(bArr);
        }
    }

    public NetworkMidiListener asMidiListener(boolean z) {
        if (this.f208b) {
            return null;
        }
        this.f192c = z;
        if (z && this.f188a == null) {
            this.f188a = new NetworkMidiListener() {
                public final void midiReceived(int i, int i2, byte[] bArr, long j) {
                    try {
                        NetworkMidiOutput.this.m187a(bArr);
                    } catch (Exception e) {
                        String lowerCase = e.toString().toLowerCase();
                        if (lowerCase.indexOf("closed") < 0 && lowerCase.indexOf("cancelled") < 0 && lowerCase.indexOf("interrupted") < 0 && lowerCase.indexOf("bad file number") == -1) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }
        return this.f188a;
    }

    /* renamed from: de.humatic.nmj.NetworkMidiOutput$a */
    class C0448a extends Thread {

        /* renamed from: a */
        private int f194a;

        /* renamed from: a */
        private Object f196a;

        /* renamed from: a */
        private Vector<byte[]> f197a;

        /* renamed from: a */
        private byte[][] f198a;

        /* renamed from: b */
        private int f199b;

        /* renamed from: b */
        private byte[][] f200b;

        /* renamed from: c */
        private int f201c;

        /* renamed from: c */
        private byte[][] f202c;

        /* renamed from: d */
        private int f203d;

        private C0448a() {
            this.f197a = new Vector<>();
            this.f198a = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 1});
            this.f200b = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 2});
            this.f202c = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{64, 3});
            this.f196a = new Object();
        }

        /* synthetic */ C0448a(NetworkMidiOutput networkMidiOutput, byte b) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public synchronized void m191a(byte[] bArr) {
            byte[] bArr2;
            synchronized (this.f196a) {
                Vector<byte[]> vector = this.f197a;
                switch (bArr.length) {
                    case 1:
                        this.f199b++;
                        if (this.f199b >= this.f198a.length) {
                            this.f199b = 0;
                        }
                        System.arraycopy(bArr, 0, this.f198a[this.f199b], 0, bArr.length);
                        bArr2 = this.f198a[this.f199b];
                        break;
                    case 2:
                        this.f201c++;
                        if (this.f201c >= this.f200b.length - 1) {
                            this.f201c = 0;
                        }
                        System.arraycopy(bArr, 0, this.f200b[this.f201c], 0, bArr.length);
                        bArr2 = this.f200b[this.f201c];
                        break;
                    case 3:
                        this.f203d++;
                        if (this.f203d >= this.f202c.length) {
                            this.f203d = 0;
                        }
                        System.arraycopy(bArr, 0, this.f202c[this.f203d], 0, bArr.length);
                        bArr2 = this.f202c[this.f203d];
                        break;
                    default:
                        bArr2 = new byte[bArr.length];
                        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                        break;
                }
                vector.add(bArr2);
            }
            this.f194a = 1;
            notify();
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
            	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
            	at java.util.ArrayList.get(ArrayList.java:433)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:225)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
            */
        public final void run() {
            /*
                r6 = this;
                r5 = 0
                r4 = 1
            L_0x0002:
                de.humatic.nmj.NetworkMidiOutput r0 = p004de.humatic.nmj.NetworkMidiOutput.this
                boolean r0 = r0.f207a
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                monitor-enter(r6)
            L_0x000a:
                int r0 = r6.f194a     // Catch:{ all -> 0x0028 }
                if (r0 == 0) goto L_0x0022
                monitor-exit(r6)     // Catch:{ all -> 0x0028 }
                int r0 = r6.f194a     // Catch:{ Exception -> 0x005c }
                if (r0 != r4) goto L_0x001f
                java.lang.Object r1 = r6.f196a     // Catch:{ Exception -> 0x005c }
                monitor-enter(r1)     // Catch:{ Exception -> 0x005c }
            L_0x0016:
                java.util.Vector<byte[]> r0 = r6.f197a     // Catch:{ all -> 0x0059 }
                int r0 = r0.size()     // Catch:{ all -> 0x0059 }
                if (r0 > 0) goto L_0x002b
            L_0x001e:
                monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            L_0x001f:
                r6.f194a = r5
                goto L_0x0002
            L_0x0022:
                r6.wait()     // Catch:{ InterruptedException -> 0x0026 }
                goto L_0x000a
            L_0x0026:
                r0 = move-exception
                goto L_0x000a
            L_0x0028:
                r0 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x0028 }
                throw r0
            L_0x002b:
                java.util.Vector<byte[]> r0 = r6.f197a     // Catch:{ all -> 0x0059 }
                r2 = 0
                java.lang.Object r0 = r0.remove(r2)     // Catch:{ all -> 0x0059 }
                byte[] r0 = (byte[]) r0     // Catch:{ all -> 0x0059 }
                if (r0 == 0) goto L_0x001e
                de.humatic.nmj.NetworkMidiOutput r2 = p004de.humatic.nmj.NetworkMidiOutput.this     // Catch:{ all -> 0x0059 }
                de.humatic.nmj.o r2 = p004de.humatic.nmj.NetworkMidiOutput.m184a((p004de.humatic.nmj.NetworkMidiOutput) r2)     // Catch:{ all -> 0x0059 }
                if (r2 == 0) goto L_0x0047
                de.humatic.nmj.NetworkMidiOutput r2 = p004de.humatic.nmj.NetworkMidiOutput.this     // Catch:{ all -> 0x0059 }
                de.humatic.nmj.o r2 = p004de.humatic.nmj.NetworkMidiOutput.m184a((p004de.humatic.nmj.NetworkMidiOutput) r2)     // Catch:{ all -> 0x0059 }
                r2.mo8163a((byte[]) r0)     // Catch:{ all -> 0x0059 }
            L_0x0047:
                de.humatic.nmj.NetworkMidiOutput r2 = p004de.humatic.nmj.NetworkMidiOutput.this     // Catch:{ all -> 0x0059 }
                de.humatic.nmj.n r2 = p004de.humatic.nmj.NetworkMidiOutput.m184a((p004de.humatic.nmj.NetworkMidiOutput) r2)     // Catch:{ all -> 0x0059 }
                if (r2 == 0) goto L_0x0016
                de.humatic.nmj.NetworkMidiOutput r2 = p004de.humatic.nmj.NetworkMidiOutput.this     // Catch:{ all -> 0x0059 }
                de.humatic.nmj.n r2 = p004de.humatic.nmj.NetworkMidiOutput.m184a((p004de.humatic.nmj.NetworkMidiOutput) r2)     // Catch:{ all -> 0x0059 }
                r2.mo8154a((byte[]) r0)     // Catch:{ all -> 0x0059 }
                goto L_0x0016
            L_0x0059:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0059 }
                throw r0     // Catch:{ Exception -> 0x005c }
            L_0x005c:
                r0 = move-exception
                java.lang.String r1 = r0.toString()
                java.lang.String r2 = "closed"
                int r1 = r1.indexOf(r2)
                r2 = -1
                if (r1 != r2) goto L_0x009a
                java.lang.String r1 = r0.toString()
                java.lang.String r2 = "sendto failed"
                int r1 = r1.indexOf(r2)
                if (r1 != r4) goto L_0x009a
                java.lang.String r1 = r0.toString()
                java.lang.String r2 = "EINVAL"
                int r1 = r1.indexOf(r2)
                if (r1 != r4) goto L_0x009a
                java.lang.String r1 = r0.toString()
                java.lang.String r2 = "EPIPE"
                int r1 = r1.indexOf(r2)
                if (r1 != r4) goto L_0x009a
                java.lang.String r1 = r0.toString()
                java.lang.String r2 = "ENETUNREACH"
                int r1 = r1.indexOf(r2)
                if (r1 == r4) goto L_0x00c6
            L_0x009a:
                de.humatic.nmj.NetworkMidiOutput r1 = p004de.humatic.nmj.NetworkMidiOutput.this
                int r1 = r1.f204a
                r2 = -2147483647(0xffffffff80000001, float:-1.4E-45)
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r0 = r0.getMessage()
                java.lang.String r0 = java.lang.String.valueOf(r0)
                r3.<init>(r0)
                java.lang.String r0 = ", closing"
                java.lang.StringBuilder r0 = r3.append(r0)
                java.lang.String r0 = r0.toString()
                p004de.humatic.nmj.NMJConfig.m60a((int) r1, (int) r2, (java.lang.String) r0)
                de.humatic.nmj.NetworkMidiOutput r0 = p004de.humatic.nmj.NetworkMidiOutput.this     // Catch:{ Exception -> 0x00c3 }
                r1 = 0
                r0.close(r1)     // Catch:{ Exception -> 0x00c3 }
                goto L_0x001f
            L_0x00c3:
                r0 = move-exception
                goto L_0x001f
            L_0x00c6:
                java.lang.String r1 = "sendMidiOnThread"
                p004de.humatic.nmj.C0484p.m377a((java.lang.Exception) r0, (java.lang.String) r1)
                de.humatic.nmj.NetworkMidiOutput r1 = p004de.humatic.nmj.NetworkMidiOutput.this
                int r1 = r1.f204a
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                java.lang.String r0 = r0.getMessage()
                p004de.humatic.nmj.NMJConfig.m60a((int) r1, (int) r2, (java.lang.String) r0)
                goto L_0x001f
            */
            throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.NetworkMidiOutput.C0448a.run():void");
        }
    }
}
