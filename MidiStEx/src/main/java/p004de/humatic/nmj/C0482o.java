package p004de.humatic.nmj;

import android.os.Looper;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/* renamed from: de.humatic.nmj.o */
final class C0482o {

    /* renamed from: a */
    private int f403a;

    /* renamed from: a */
    private ADBMidiIO f404a;

    /* renamed from: a */
    private C0456c f405a;

    /* renamed from: a */
    private C0504x f406a;

    /* renamed from: a */
    private DatagramPacket f407a;

    /* renamed from: a */
    private DatagramSocket f408a;

    /* renamed from: a */
    private InetAddress f409a;

    /* renamed from: a */
    private InetSocketAddress f410a;

    /* renamed from: a */
    private MulticastSocket f411a;

    /* renamed from: a */
    private NetworkInterface f412a;

    /* renamed from: a */
    private boolean f413a;

    /* renamed from: a */
    private byte[] f414a;

    C0482o(int i) throws Exception {
        boolean z;
        this.f403a = i;
        String ip = NMJConfig.getIP(this.f403a);
        int port = NMJConfig.getPort(this.f403a);
        if (NMJConfig.getMode(this.f403a) == 2) {
            this.f405a = new C0456c(this.f403a, (C0479n) null);
        } else if (NMJConfig.getMode(this.f403a) == 4) {
            this.f404a = new ADBMidiIO(this.f403a, (C0479n) null);
        } else if (NMJConfig.getMode(this.f403a) == 5) {
            this.f406a = new C0504x(this.f403a, (C0479n) null);
        } else {
            int localPort = NMJConfig.getLocalPort(this.f403a);
            int networkAdapter = NMJConfig.getNetworkAdapter(this.f403a);
            this.f413a = true;
            try {
                if (new Integer(ip.substring(0, ip.indexOf("."))).intValue() >= 223) {
                    z = true;
                } else {
                    z = false;
                }
                this.f413a = z;
            } catch (Exception e) {
            }
            if (this.f413a) {
                m342a(networkAdapter, port, ip);
                return;
            }
            try {
                if (NMJConfig.getMode(this.f403a) != 3) {
                    this.f408a = new DatagramSocket(localPort);
                } else {
                    this.f408a = C0484p.m357a(networkAdapter, -1);
                }
                this.f407a = new DatagramPacket(new byte[16], 16, InetAddress.getByName(ip), port);
                NMJConfig.m59a(this.f403a, 1, 0);
                C0484p.logln(2, "Unicast client up and running");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private void m342a(int i, int i2, String str) throws IOException {
        C0484p.logln(2, "Multicast server, opening adapter " + i);
        this.f411a = new MulticastSocket();
        if (i <= 0) {
            this.f412a = C0484p.m346a();
        } else {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i3 = 0;
            while (networkInterfaces.hasMoreElements() && i3 < i) {
                this.f412a = networkInterfaces.nextElement();
                i3++;
            }
        }
        if (this.f412a == null) {
            throw new IOException("No interface");
        }
        try {
            this.f411a.setInterface(this.f412a.getInetAddresses().nextElement());
            this.f409a = InetAddress.getByName(str);
            try {
                this.f410a = new InetSocketAddress(this.f409a, 0);
                this.f411a.joinGroup(this.f410a, this.f412a);
                this.f407a = new DatagramPacket(new byte[256], 256, InetAddress.getByName(str), i2);
                C0484p.logln(2, "Multicast server up and running via:" + this.f412a.toString() + " " + InetAddress.getByName(str) + " " + i2);
                NMJConfig.m59a(this.f403a, 1, 0);
            } catch (SocketException e) {
                C0484p.logln(2, "Multicast server, error on group join: " + e.getMessage() + "\nParams:" + this.f412a.toString() + " " + InetAddress.getByName(str) + " " + i2);
                throw e;
            } catch (SecurityException e2) {
                throw e2;
            }
        } catch (NoSuchElementException e3) {
            throw new IOException("No valid addresses on " + this.f412a);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8162a(int i) {
        this.f403a = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8161a() {
        try {
            if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                new Thread(new Runnable() {
                    public final void run() {
                        C0482o.this.mo8161a();
                    }
                }).start();
                return;
            }
        } catch (Exception e) {
        }
        try {
            if (this.f413a) {
                this.f411a.leaveGroup(this.f410a, this.f412a);
                this.f411a.close();
            } else {
                this.f408a.close();
            }
            NMJConfig.m59a(this.f403a, 2, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (this.f405a != null) {
                this.f405a.mo8031a();
            } else if (this.f404a != null) {
                this.f404a.mo8031a();
            } else if (this.f406a != null) {
                this.f406a.mo8031a();
            } else {
                return;
            }
            NMJConfig.m59a(this.f403a, 2, 0);
        } catch (Exception e3) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8163a(byte[] bArr) throws Exception {
        if (this.f405a != null) {
            this.f405a.mo8034a(bArr);
        } else if (this.f404a != null) {
            this.f404a.mo8034a(bArr);
        } else if (this.f406a != null) {
            this.f406a.mo8034a(bArr);
        } else if (bArr.length < 256) {
            this.f407a.setData(bArr);
            if (this.f413a) {
                this.f411a.send(this.f407a);
            } else {
                this.f408a.send(this.f407a);
            }
        } else {
            if (this.f414a == null) {
                this.f414a = new byte[1024];
            }
            int i = 0;
            while (i < bArr.length) {
                int min = Math.min(1024, bArr.length - i);
                System.arraycopy(bArr, i, this.f414a, 0, min);
                this.f407a.setData(this.f414a, 0, min);
                if (this.f413a) {
                    this.f411a.send(this.f407a);
                } else {
                    this.f408a.send(this.f407a);
                }
                i += min;
            }
        }
    }
}
