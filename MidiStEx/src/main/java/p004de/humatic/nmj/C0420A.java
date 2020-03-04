package p004de.humatic.nmj;

import android.os.Build;
import android.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.Vector;

/* renamed from: de.humatic.nmj.A */
final class C0420A extends C0503w {

    /* renamed from: a */
    private C0421a f28a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0423c f29a;

    /* renamed from: a */
    private C0420A f30a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public ServerSocket f31a;

    /* renamed from: a */
    private TimerTask f32a;

    /* renamed from: a */
    private Vector<C0514z> f33a = new Vector<>();

    C0420A(int i, C0479n nVar) throws IOException {
        super(nVar, i);
        if (Build.VERSION.SDK_INT < 8) {
            throw new IOException("WebSockets require Android 2.2 or greater");
        }
        this.f30a = this;
        if (NMJConfig.getIP(this.f575c) != null) {
            C0514z zVar = new C0514z(this.f30a);
            new Thread(zVar).start();
            this.f33a.add(zVar);
            return;
        }
        this.f28a = new C0421a(this, (byte) 0);
        new Thread(this.f28a).start();
        NMJConfig.m59a(this.f575c, 4, 4);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8031a() {
        C0484p.logln(4, "WebSocketIO close, nr. clients: " + this.f33a.size());
        Iterator<C0514z> it = this.f33a.iterator();
        while (it.hasNext()) {
            it.next().mo8219a();
        }
        if (this.f28a != null) {
            try {
                this.f573a = true;
                this.f31a.close();
            } catch (Exception e) {
            }
            try {
                this.f29a.cancel();
            } catch (Exception e2) {
            }
        } else {
            try {
                this.f32a.cancel();
            } catch (Exception e3) {
            }
        }
        NMJConfig.m59a(this.f575c, 4, 128);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8034a(byte[] bArr) throws IOException {
        Iterator<C0514z> it = this.f33a.iterator();
        while (it.hasNext()) {
            it.next().mo8221a(bArr);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8035a(byte[] bArr, int i, int i2) {
        this.f572a.mo8156a(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8033a(C0514z zVar, byte[] bArr, long j) {
        this.f572a.mo8153a(this.f575c, zVar.mo8219a(), bArr, j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8032a(C0514z zVar) {
        if (zVar.f626a < 0) {
            if (NMJConfig.getRTPState(this.f575c) == 32) {
                NMJConfig.m59a(this.f575c, 4, 128);
            }
            this.f33a.remove(zVar);
            this.f32a = new C0422b(this, (byte) 0);
            NMJConfig.m69a(this.f32a, 15000, -1);
            C0484p.logln(3, "WebSocketIO local client closed " + zVar);
            return;
        }
        int i = this.f575c;
        zVar.mo8219a();
        NMJConfig.m84b(i, 64);
        this.f33a.remove(zVar);
        if (this.f33a.size() == 0) {
            NMJConfig.m59a(this.f575c, 4, 128);
            NMJConfig.m59a(this.f575c, 4, 4);
            try {
                this.f29a.cancel();
            } catch (Exception e) {
            }
            this.f29a = null;
        }
        C0484p.logln(3, "Client disconnected " + zVar + ", rem. clients: " + this.f33a.size());
        if (this.f33a.size() > 0) {
            Iterator<C0514z> it = this.f33a.iterator();
            while (it.hasNext()) {
                C0484p.logln(3, it.next().toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0502v[] m20a() {
        if (this.f33a == null || this.f33a.size() == 0) {
            return null;
        }
        C0502v[] vVarArr = new C0502v[this.f33a.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f33a.size()) {
                return vVarArr;
            }
            vVarArr[i2] = this.f33a.get(i2).mo8219a();
            i = i2 + 1;
        }
    }

    /* renamed from: de.humatic.nmj.A$a */
    class C0421a extends Thread {

        /* renamed from: a */
        private StringBuffer f35a;

        /* renamed from: a */
        private byte[] f36a;

        private C0421a() {
            this.f36a = new byte[1500];
        }

        /* synthetic */ C0421a(C0420A a, byte b) {
            this();
        }

        public final void run() {
            try {
                int localPort = NMJConfig.getLocalPort(C0420A.this.f575c);
                C0420A.this.f31a = new ServerSocket();
                C0420A.m8a(C0420A.this).setPerformancePreferences(0, 1, 0);
                C0420A.m8a(C0420A.this).bind(new InetSocketAddress(localPort));
                C0484p.logln(4, "WebSocketIO, listening for connections on port " + localPort);
                while (!C0420A.this.f573a) {
                    m21a();
                }
            } catch (Exception e) {
                if (e.toString().indexOf("closed") < 0 && e.toString().indexOf("Bad file number") < 0) {
                    e.printStackTrace();
                }
            }
            try {
                C0420A.m8a(C0420A.this).close();
            } catch (Exception e2) {
            }
            C0484p.logln(4, "WebSocketIO, accept thread done");
        }

        /* renamed from: a */
        private void m23a() throws IOException, GeneralSecurityException {
            int i = 0;
            String str = "";
            Socket accept = C0420A.m8a(C0420A.this).accept();
            accept.setTcpNoDelay(true);
            OutputStream outputStream = accept.getOutputStream();
            InputStream inputStream = accept.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            String a = m22a(inputStream, true);
            if (a == null || a.indexOf("HTTP/1.1") < 0) {
                stringBuffer.append("HTTP/1.0 404\r\n");
                outputStream.write(stringBuffer.toString().getBytes());
                return;
            }
            stringBuffer.append("HTTP/1.1 101 Switching Protocols\r\n");
            stringBuffer.append("Upgrade: websocket\r\n");
            stringBuffer.append("Connection: Upgrade\r\n");
            boolean z = false;
            while (a != null && a.length() > 0) {
                String a2 = m22a(inputStream, true);
                if (a2 == null) {
                    break;
                } else if (a2 == null || a2.indexOf("Sec-WebSocket-Key") == -1) {
                    if (a2.indexOf("Sec-WebSocket-Protocol") != -1) {
                        if (a2.trim().indexOf("rtp") != -1) {
                            stringBuffer.append("Sec-WebSocket-Protocol: rtp\r\n");
                            z = true;
                            a = a2;
                        }
                    } else if (a2.indexOf("Cookie: ssrc=") != -1) {
                        String[] split = a2.substring(8).split(";");
                        int i2 = i;
                        String str2 = str;
                        for (int i3 = 0; i3 < split.length; i3++) {
                            try {
                                if (split[i3].indexOf("ssrc") != -1) {
                                    i2 = Integer.parseInt(split[i3].split("=")[1].trim());
                                } else if (split[i3].indexOf("name") != -1) {
                                    str2 = split[i3].split("=")[1].trim();
                                }
                            } catch (Exception e) {
                            }
                        }
                        str = str2;
                        i = i2;
                        a = a2;
                    }
                    a = a2;
                } else {
                    String substring = a2.substring(a2.indexOf(": ") + 2, a2.length());
                    StringBuilder sb = new StringBuilder("Sec-WebSocket-Accept: ");
                    MessageDigest instance = MessageDigest.getInstance("SHA-1");
                    instance.update((String.valueOf(substring.trim()) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes());
                    stringBuffer.append(sb.append(Base64.encodeToString(instance.digest(), 0).trim()).append("\r\n").toString());
                    a = a2;
                }
            }
            stringBuffer.append("Origin: " + NMJConfig.m54a(false) + "\r\n");
            stringBuffer.append("\r\n");
            outputStream.write(stringBuffer.toString().getBytes());
            outputStream.flush();
            C0514z zVar = new C0514z(C0420A.m8a(C0420A.this), accept, m21a(), i, str, z ? 0 : 1);
            new Thread(zVar).start();
            C0420A.m8a(C0420A.this).add(zVar);
            if (C0420A.m8a(C0420A.this).size() == 1 && C0420A.m8a(C0420A.this) == null) {
                NMJConfig.m59a(C0420A.this.f575c, 4, 32);
                C0420A.this.f29a = new C0423c(C0420A.this, (byte) 0);
                NMJConfig.m69a((TimerTask) C0420A.m8a(C0420A.this), 5000, 5000);
            }
            int i4 = C0420A.this.f575c;
            zVar.mo8219a();
            NMJConfig.m84b(i4, 32);
            C0484p.logln(3, "Client connected to local session: " + zVar);
        }

        /* renamed from: a */
        private String m22a(InputStream inputStream, boolean z) throws IOException {
            if (this.f35a == null) {
                this.f35a = new StringBuffer();
            }
            this.f35a.delete(0, this.f35a.length());
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            while (read != 10) {
                if (read != 13) {
                    this.f35a.append((char) read);
                }
                read = inputStream.read();
                if (read == -1) {
                    return null;
                }
            }
            return this.f35a.toString();
        }

        /* renamed from: a */
        private int m21a() {
            int i = -1;
            Iterator it = C0420A.m8a(C0420A.this).iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2 + 1;
                }
                i = Math.max(i2, ((C0514z) it.next()).f626a);
            }
        }
    }

    /* renamed from: de.humatic.nmj.A$c */
    class C0423c extends TimerTask {

        /* renamed from: a */
        private boolean f39a;

        private C0423c() {
        }

        /* synthetic */ C0423c(C0420A a, byte b) {
            this();
        }

        public final void run() {
            if (C0420A.m8a(C0420A.this).size() != 0) {
                this.f39a = !this.f39a;
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    Iterator it = C0420A.m8a(C0420A.this).iterator();
                    while (it.hasNext()) {
                        C0514z zVar = (C0514z) it.next();
                        try {
                            if (this.f39a) {
                                zVar.mo8220a(currentTimeMillis);
                            } else if (zVar.f636a) {
                                zVar.mo8222b(currentTimeMillis);
                            }
                        } catch (IOException e) {
                            C0484p.logln(2, "error sending Ping " + e.toString());
                            if (e.toString().indexOf("Broken pipe") != -1) {
                                C0420A.this.mo8032a(zVar);
                            }
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }
    }

    /* renamed from: de.humatic.nmj.A$b */
    class C0422b extends TimerTask {
        private C0422b() {
        }

        /* synthetic */ C0422b(C0420A a, byte b) {
            this();
        }

        public final void run() {
            C0420A.m8a(C0420A.this);
        }
    }
}
