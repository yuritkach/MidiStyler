package p004de.humatic.nmj;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/* renamed from: de.humatic.nmj.ADBMidiIO */
final class ADBMidiIO extends C0503w {

    /* renamed from: a */
    private int f40a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public ADBServer f41a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public InputStream f42a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public OutputStream f43a;

    /* renamed from: a */
    private String f44a = "ADB ";
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Socket f45a;

    ADBMidiIO(int i, C0479n nVar) throws Exception {
        super(nVar, i);
        this.f44a = String.valueOf(this.f44a) + i;
        for (int i2 = 0; i2 < this.f575c; i2++) {
            if (NMJConfig.getMode(i2) == 4) {
                this.f40a++;
            }
        }
        new Thread(new C0424a(this, (byte) 0)).start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8031a() {
        this.f573a = true;
        NMJConfig.m59a(this.f575c, 4, 128);
        try {
            this.f45a.close();
        } catch (Exception e) {
        }
        try {
            ADBServer.m35a(this.f41a);
        } catch (Exception e2) {
        }
        C0484p.logln(1, "ADBMidi closed");
    }

    /* renamed from: de.humatic.nmj.ADBMidiIO$a */
    class C0424a extends Thread {

        /* renamed from: a */
        private byte[] f49a;

        private C0424a() {
            this.f49a = new byte[1560];
        }

        /* synthetic */ C0424a(ADBMidiIO aDBMidiIO, byte b) {
            this();
        }

        public final void run() {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            try {
                NMJConfig.m71a(true, false);
                if (ADBMidiIO.m24a(ADBMidiIO.this) == null || ADBServer.m35a(ADBMidiIO.m24a(ADBMidiIO.this))) {
                    ADBMidiIO.this.f41a = new ADBServer(ADBMidiIO.this, ADBMidiIO.m24a(ADBMidiIO.this), NMJConfig.getLocalPort(ADBMidiIO.this.f575c), (byte) 0);
                }
                NMJConfig.m59a(ADBMidiIO.this.f575c, 4, 4);
                try {
                    C0484p.logln(2, "ADBIn waiting for connection from " + ADBMidiIO.m24a(ADBMidiIO.this));
                    ADBMidiIO.this.f45a = ADBMidiIO.m24a(ADBMidiIO.this).mo8039a();
                    ADBMidiIO.this.f42a = ADBMidiIO.m24a(ADBMidiIO.this).getInputStream();
                    ADBMidiIO.this.f43a = ADBMidiIO.m24a(ADBMidiIO.this).getOutputStream();
                    C0484p.logln(1, ADBMidiIO.m24a(ADBMidiIO.this) + " connected");
                    NMJConfig.m59a(ADBMidiIO.this.f575c, 4, 32);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e2) {
                    }
                    new Thread(new C0425b(ADBMidiIO.this, (byte) 0)).start();
                    while (true) {
                        if (ADBMidiIO.this.f573a) {
                            break;
                        }
                        try {
                            int read = ADBMidiIO.m24a(ADBMidiIO.this).read(this.f49a);
                            if (read < 0) {
                                ADBMidiIO.m24a(ADBMidiIO.this).close();
                                NMJConfig.m59a(ADBMidiIO.this.f575c, 4, 128);
                                break;
                            } else if (read > 0) {
                                ADBMidiIO.this.f572a.mo8155a(this.f49a, read);
                            }
                        } catch (Exception e3) {
                            if (e3.toString().indexOf("closed") < 0) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    if (!ADBMidiIO.this.f573a) {
                        new Thread(new C0424a()).start();
                    }
                } catch (Exception e4) {
                    if (e4.toString().indexOf("Interrupted system call") < 0 && e4.toString().indexOf("closed") < 0) {
                        NMJConfig.m60a(ADBMidiIO.this.f575c, (int) NMJConfig.E_ADB, e4.toString());
                    }
                }
            } catch (Exception e5) {
                if (!ADBMidiIO.this.f573a) {
                    NMJConfig.m60a(ADBMidiIO.this.f575c, (int) NMJConfig.E_ADB, e5.toString());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8034a(byte[] bArr) throws IOException {
        if (this.f43a != null) {
            if (bArr.length <= this.f576d) {
                this.f43a.write(bArr);
                this.f43a.flush();
                return;
            }
            int i = 0;
            while (i < bArr.length) {
                this.f43a.write(bArr, i, Math.min(this.f576d, bArr.length - i));
                i += Math.min(this.f576d, bArr.length - i);
                this.f43a.flush();
            }
        }
    }

    /* renamed from: de.humatic.nmj.ADBMidiIO$b */
    class C0425b extends Thread {

        /* renamed from: a */
        private int[] f51a;

        private C0425b() {
            this.f51a = new int[]{1460, 1024, 512, 256, 128, 32, 16};
        }

        /* synthetic */ C0425b(ADBMidiIO aDBMidiIO, byte b) {
            this();
        }

        public final void run() {
            try {
                sleep(2000);
                ADBMidiIO.this.mo8034a(C0484p.m357a(0, 0));
                for (int a : this.f51a) {
                    ADBMidiIO.m24a(ADBMidiIO.this).write(C0484p.m357a(1, a));
                    ADBMidiIO.m24a(ADBMidiIO.this).flush();
                    int i = 0;
                    while (ADBMidiIO.this.f576d == 8) {
                        int i2 = i + 1;
                        if (i >= 5) {
                            break;
                        }
                        sleep(100);
                        i = i2;
                    }
                    if (ADBMidiIO.this.f576d != 8) {
                        break;
                    }
                }
                ADBMidiIO.this.mo8034a(C0484p.m357a(0, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: de.humatic.nmj.ADBMidiIO$ADBServer */
    public class ADBServer {

        /* renamed from: a */
        private int f46a;

        /* renamed from: a */
        private ServerSocket f47a;

        private ADBServer(ADBMidiIO aDBMidiIO, int i, int i2) throws Exception {
            this.f46a = i2;
            this.f47a = new ServerSocket(i2);
            this.f47a.setReuseAddress(true);
        }

        /* synthetic */ ADBServer(ADBMidiIO aDBMidiIO, int i, int i2, byte b) throws Exception {
            this(aDBMidiIO, i, i2);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public final Socket mo8039a() throws IOException {
            return this.f47a.accept();
        }

        public String toString() {
            return "ADBServer " + hashCode() + ", port: " + this.f46a;
        }
    }
}
