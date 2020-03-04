package p004de.humatic.nmj;

import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.p002os.EnvironmentCompat;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* renamed from: de.humatic.nmj.z */
final class C0514z extends Thread implements C0466f {

    /* renamed from: a */
    int f626a;

    /* renamed from: a */
    private C0420A f627a;

    /* renamed from: a */
    private C0477l f628a;

    /* renamed from: a */
    private ByteArrayOutputStream f629a;

    /* renamed from: a */
    private InputStream f630a;

    /* renamed from: a */
    private OutputStream f631a;

    /* renamed from: a */
    private String f632a;

    /* renamed from: a */
    private StringBuffer f633a;

    /* renamed from: a */
    private InetSocketAddress f634a;

    /* renamed from: a */
    private Socket f635a;

    /* renamed from: a */
    boolean f636a;

    /* renamed from: a */
    private byte[] f637a = new byte[1500];

    /* renamed from: a */
    private int[] f638a = new int[3];

    /* renamed from: b */
    private int f639b;

    /* renamed from: b */
    private String f640b = "WSClient ";

    /* renamed from: b */
    private boolean f641b;

    /* renamed from: b */
    private byte[] f642b = new byte[1500];

    /* renamed from: c */
    private int f643c;

    /* renamed from: c */
    private String f644c = EnvironmentCompat.MEDIA_UNKNOWN;

    /* renamed from: c */
    private boolean f645c;

    /* renamed from: c */
    private byte[] f646c = new byte[1500];

    /* renamed from: d */
    private int f647d;

    /* renamed from: d */
    private boolean f648d;

    /* renamed from: d */
    private byte[] f649d = new byte[128];

    /* renamed from: e */
    private int f650e;

    /* renamed from: e */
    private boolean f651e;

    /* renamed from: e */
    private byte[] f652e = new byte[16];

    /* renamed from: f */
    private int f653f = SupportMenu.USER_MASK;

    /* renamed from: f */
    private byte[] f654f = new byte[8];

    /* renamed from: g */
    private int f655g = 0;

    /* renamed from: g */
    private byte[] f656g;

    /* renamed from: h */
    private byte[] f657h = new byte[1500];

    C0514z(C0420A a, Socket socket, int i, int i2, String str, int i3) {
        boolean z = false;
        this.f627a = a;
        this.f639b = a.f575c;
        this.f635a = socket;
        this.f626a = i;
        this.f643c = i2;
        this.f640b = str == null ? "WSClient " + i : str;
        this.f636a = (i3 & 1) == 0 ? true : z;
        try {
            this.f644c = ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().toString();
        } catch (Exception e) {
        }
    }

    C0514z(C0420A a) throws IOException {
        this.f627a = a;
        this.f639b = a.f575c;
        this.f641b = true;
        this.f626a = -1;
        this.f640b = String.valueOf(this.f640b) + NMJConfig.m39a(this.f639b);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int m607a() {
        return this.f643c;
    }

    public final String toString() {
        return "WSClient " + this.f626a + ", ch:" + this.f639b + " ip: " + (this.f626a >= 0 ? this.f644c : NMJConfig.getIP(this.f639b)) + " rtp: " + this.f636a + " ssrc: " + this.f643c;
    }

    public final void run() {
        String str;
        URI uri;
        int i;
        Socket socket;
        String str2;
        try {
            if (this.f643c == 0) {
                this.f643c = (int) (Math.random() * 2.147483647E9d);
            }
            System.arraycopy("CK:".getBytes(), 0, this.f652e, 0, 3);
            this.f652e[3] = 0;
            C0484p.m372a(this.f643c, this.f652e, 4);
            if (this.f641b) {
                String ip = NMJConfig.getIP(this.f639b);
                if (ip.indexOf("ws") < 0) {
                    ip = "ws://" + ip;
                }
                if (ip.indexOf(":", ip.indexOf("://") + 3) < 0) {
                    if (ip.indexOf("/", ip.indexOf("://") + 3) < 0) {
                        ip = String.valueOf(ip) + ":" + NMJConfig.getPort(this.f639b);
                    } else {
                        int indexOf = ip.indexOf("/", ip.indexOf("://") + 3);
                        ip = String.valueOf(ip.substring(0, indexOf)) + ":" + NMJConfig.getPort(this.f639b) + ip.substring(indexOf);
                    }
                }
                URI create = URI.create(ip);
                String path = create.getPath();
                if (path.trim().length() == 0) {
                    str = "/";
                } else {
                    str = path;
                }
                if (create.getHost() != null) {
                    if (create.getScheme().equals("wss")) {
                        str2 = "https";
                    } else {
                        str2 = "http";
                    }
                    uri = new URI(str2, "//" + create.getHost(), (String) null);
                } else {
                    uri = null;
                }
                try {
                    int port = create.getPort();
                    if (port < 0) {
                        i = create.getScheme().equals("wss") ? 443 : 80;
                    } else {
                        i = port;
                    }
                    if (create.getScheme().equals("wss")) {
                        SSLContext instance = SSLContext.getInstance("TLS");
                        instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                        socket = instance.getSocketFactory().createSocket(create.getHost(), i);
                    } else {
                        socket = new Socket();
                        InetSocketAddress inetSocketAddress = new InetSocketAddress(create.getHost(), i);
                        socket.setTcpNoDelay(true);
                        socket.connect(inetSocketAddress, 2000);
                    }
                    this.f635a = socket;
                    this.f635a.setSoTimeout(5000);
                    this.f635a.setTcpNoDelay(true);
                    this.f631a = this.f635a.getOutputStream();
                    this.f630a = this.f635a.getInputStream();
                    NMJConfig.m59a(this.f639b, 1, 0);
                    this.f632a = C0484p.m346a();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("GET " + str + " HTTP/1.1\r\n");
                    stringBuffer.append("Upgrade: websocket\r\n");
                    stringBuffer.append("Connection: Upgrade\r\n");
                    stringBuffer.append("Host: " + create.getHost() + "\r\n");
                    if (uri != null) {
                        stringBuffer.append("Origin: " + uri.toString() + "\r\n");
                    }
                    stringBuffer.append("Sec-WebSocket-Version: 13\r\n");
                    stringBuffer.append("Sec-WebSocket-Protocol: rtp\r\n");
                    stringBuffer.append("Sec-WebSocket-Key: " + this.f632a + "\r\n");
                    stringBuffer.append("Cookie: ssrc=" + this.f643c + ";name=" + this.f640b + "\r\n");
                    stringBuffer.append("\r\n");
                    this.f631a.write(stringBuffer.toString().getBytes());
                    this.f631a.flush();
                    if (!mo8219a()) {
                        C0484p.logln(4, "MWS - Failed to verify connection");
                        return;
                    } else {
                        C0484p.logln(4, "MWS " + this.f639b + " - connection verified, rtp: " + this.f636a + ", starting socket io");
                        NMJConfig.m59a(this.f639b, 4, 32);
                    }
                } catch (SocketTimeoutException e) {
                    this.f627a.mo8032a(this);
                    return;
                }
            } else {
                this.f631a = this.f635a.getOutputStream();
                this.f630a = this.f635a.getInputStream();
                this.f634a = (InetSocketAddress) this.f635a.getRemoteSocketAddress();
            }
            if (this.f636a) {
                this.f628a = new C0477l(this, this.f639b, this.f643c, System.currentTimeMillis());
            }
            while (!this.f645c) {
                try {
                    int available = this.f630a.available();
                    if (available != 0) {
                        int read = this.f630a.read(this.f637a, 0, Math.min(available, this.f637a.length));
                        if (read < 0) {
                            break;
                        }
                        m603a(this.f637a, read);
                    } else {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e2) {
                        }
                    }
                } catch (SocketTimeoutException e3) {
                } catch (SocketException e4) {
                    if (e4.toString().indexOf("timed out") == -1) {
                        if (e4.toString().indexOf("recvfrom failed") != -1) {
                            C0484p.logln(-1, "WS " + e4.toString());
                            this.f645c = true;
                            this.f627a.mo8032a(this);
                        } else {
                            e4.printStackTrace();
                        }
                    }
                } catch (IllegalArgumentException e5) {
                    if (e5.toString().indexOf("base-64") != -1) {
                        C0484p.m371a(3, "bad base-64:", this.f637a, 0, 16);
                    } else {
                        e5.printStackTrace();
                    }
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        if (!this.f641b && !this.f648d && !this.f651e) {
            C0484p.logln(2, "WSClient - Client missed to send CLOSE event");
            this.f627a.mo8032a(this);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m609a() {
        this.f651e = true;
        try {
            C0484p.logln(3, "WSClient " + this.f626a + " sending CLOSE");
            m606b(this.f649d, m600a((Object) "", 8, 1000, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f645c = true;
    }

    /* renamed from: a */
    private boolean mo8219a() throws IOException, GeneralSecurityException {
        String a = m602a(true);
        C0484p.logln(4, a);
        if (a == null || a.indexOf("HTTP/1.1 101") < 0) {
            return false;
        }
        boolean z = false;
        while (true) {
            String a2 = m602a(true);
            if (!(a2 == null || a2.trim().length() == 0)) {
                String[] split = a2.split(":");
                C0484p.logln(4, a2);
                if (split[0].trim().equalsIgnoreCase("Sec-WebSocket-Accept")) {
                    String trim = split[1].trim();
                    MessageDigest instance = MessageDigest.getInstance("SHA-1");
                    instance.update((String.valueOf(this.f632a) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes());
                    if (!trim.equals(Base64.encodeToString(instance.digest(), 0).trim())) {
                        throw new IOException("Accept header value mismatch");
                    }
                    z = true;
                } else if (split[0].trim().equalsIgnoreCase("Sec-WebSocket-Protocol") && split[1].trim().equals("rtp")) {
                    this.f636a = true;
                }
            }
        }
        if (!z) {
            throw new IOException("Sec-WebSocket-Accept header missing");
        }
        C0484p.m362a("verified...");
        return z;
    }

    /* renamed from: a */
    private synchronized int m600a(Object obj, int i, int i2, int i3) throws IOException {
        int i4;
        if (this.f645c) {
            i4 = -1;
        } else {
            boolean z = this.f641b;
            byte[] bArr = this.f649d;
            if (i == 2) {
                bArr = this.f642b;
            } else if (i == 1) {
                bArr = this.f646c;
            }
            byte[] bytes = obj instanceof String ? ((String) obj).getBytes("UTF-8") : (byte[]) obj;
            int i5 = i2 > 0 ? 2 : 0;
            int length = bytes.length + i5;
            int i6 = length <= 125 ? 2 : length <= 65535 ? 4 : 10;
            int i7 = i6 + (z ? 4 : 0);
            int i8 = z ? 128 : 0;
            if (bArr.length < length + i7) {
                bArr = new byte[(length + i7)];
            }
            bArr[0] = (byte) (((byte) i) | Byte.MIN_VALUE);
            if (length <= 125) {
                bArr[1] = (byte) (i8 | length);
            } else if (length <= 65535) {
                bArr[1] = (byte) (i8 | 126);
                C0484p.m382a((short) length, bArr, 2);
            } else {
                bArr[1] = (byte) (i8 | 127);
                C0484p.m374a((long) length, bArr, 2);
            }
            if (i2 > 0) {
                bArr[i7] = (byte) ((int) Math.floor((double) (i2 / 256)));
                bArr[i7 + 1] = (byte) i2;
            }
            System.arraycopy(bytes, 0, bArr, i5 + i7, bytes.length);
            if (z) {
                int random = (int) (Math.random() * 2.147483647E9d);
                C0484p.m372a(random, bArr, i6);
                int i9 = 24;
                for (int i10 = i7; i10 < i7 + length; i10++) {
                    bArr[i10] = (byte) (bArr[i10] ^ (random >> i9));
                    i9 -= 8;
                    if (i9 < 0) {
                        i9 = 24;
                    }
                }
            }
            i4 = i7 + length;
        }
        return i4;
    }

    /* renamed from: a */
    private void m603a(byte[] bArr, int i) throws IOException {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        boolean z;
        this.f647d++;
        int i8 = 0;
        int i9 = 0;
        int i10 = i;
        while (i9 >= 0 && i9 < i10) {
            if (this.f647d == this.f650e) {
                i2 = i8 + 1;
                if (i8 > 100) {
                    return;
                }
            } else {
                i2 = i8;
            }
            if ((bArr[i9] & 112) != 0) {
                i3 = i9;
                while (true) {
                    if (i3 >= i10) {
                        break;
                    }
                    if ((bArr[i3] & 240) == 128) {
                        byte b = bArr[i3] & 15;
                        if (b == 1 || b == 2 || b == 9 || b == 10) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z && (bArr[i3] & 112) == 0 && (this.f641b || i3 + 1 >= i10 || (bArr[i3 + 1] & 128) != 0)) {
                            C0484p.m371a(-1, "WS - Invalid reserved fields. Resync, fs: " + i3 + " " + this.f641b, bArr, i3, 16);
                        }
                    }
                    i3++;
                }
                C0484p.m371a(-1, "WS - Invalid reserved fields. Resync, fs: " + i3 + " " + this.f641b, bArr, i3, 16);
            } else {
                i3 = i9;
            }
            this.f650e = this.f647d;
            if (i3 > i10 - 1) {
                C0484p.m362a("WS - frameStart beyond length " + i3 + " " + i10);
                return;
            }
            int i11 = i3 + 2;
            if (i3 == i10 - 1) {
                bArr[0] = bArr[i3];
                bArr[1] = (byte) this.f630a.read();
                i5 = 0;
                i4 = 2;
            } else {
                i4 = i10;
                i5 = i3;
            }
            if (i5 + 1 <= i4) {
                int[] a = m603a(bArr, i5);
                i7 = a[1];
                i11 = a[0];
                i6 = a[2];
            } else {
                i6 = 0;
                i7 = 0;
            }
            if (i7 < 0 || i11 + i6 + i7 > i4 || (i7 > 0 && i11 >= i4)) {
                System.arraycopy(bArr, i5, this.f657h, 0, i4 - i5);
                int i12 = i4 - i5;
                if (i7 < 0) {
                    C0484p.logln(-1, "Could not read dataLength, missing " + Math.abs(i7));
                    this.f630a.read(this.f657h, i12, Math.abs(i7));
                    int[] a2 = m603a(this.f657h, 0);
                    i7 = a2[1];
                    i12 = a2[0];
                    i6 = a2[2];
                    i11 = i12;
                    i5 = 0;
                }
                int i13 = i7 + ((i6 + i11) - i5);
                int i14 = 0;
                while (i14 != -1 && i12 < i13) {
                    i14 = this.f630a.read(this.f657h, i12, i13 - i12);
                    if (i14 >= 0) {
                        i12 += i14;
                    } else {
                        return;
                    }
                }
                int[] a3 = m603a(this.f657h, 0);
                m601a(this.f657h, 0, a3[0], a3[1]);
                return;
            }
            i9 = m601a(bArr, i5, i11, i7);
            i8 = i2;
            i10 = i4;
        }
    }

    /* renamed from: a */
    private int[] m605a(byte[] bArr, int i) {
        this.f638a[1] = bArr[i + 1] & Byte.MAX_VALUE;
        this.f638a[0] = i + 2;
        this.f638a[2] = (bArr[i + 1] & 128) != 0 ? 4 : 0;
        if (this.f638a[1] == 126) {
            if (i + 4 < bArr.length) {
                this.f638a[1] = C0484p.m349a(bArr, i + 2, 2);
                this.f638a[0] = i + 4;
            } else {
                this.f638a[1] = (i + 4) - bArr.length;
            }
        } else if (this.f638a[1] == 127) {
            if (i + 10 < bArr.length) {
                this.f638a[1] = (int) C0484p.m348a(bArr, i + 2);
                this.f638a[0] = i + 10;
            } else {
                this.f638a[1] = (i + 10) - bArr.length;
            }
        }
        return this.f638a;
    }

    /* renamed from: a */
    private int m601a(byte[] bArr, int i, int i2, int i3) throws IOException {
        boolean z;
        byte[] decode;
        boolean z2 = false;
        boolean z3 = (bArr[i] & 128) != 0;
        if ((bArr[i + 1] & 128) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int a = C0484p.m348a(bArr, i2);
            i2 += 4;
            int i4 = 24;
            for (int i5 = i2; i5 < i2 + i3; i5++) {
                bArr[i5] = (byte) (bArr[i5] ^ (a >> i4));
                i4 -= 8;
                if (i4 < 0) {
                    i4 = 24;
                }
            }
        }
        switch (bArr[i] & 15) {
            case 0:
                if (Math.abs(this.f647d) >= 3) {
                    C0484p.logln(2, "CONTINUE at " + i);
                    if (this.f629a != null) {
                        if (this.f629a.size() <= 65535) {
                            this.f629a.write(bArr, i2, i3);
                            if (z3) {
                                byte[] byteArray = this.f629a.toByteArray();
                                try {
                                    if (this.f636a) {
                                        this.f628a.mo8149a(byteArray, 0, byteArray.length);
                                    } else {
                                        this.f627a.mo8035a(byteArray, 0, byteArray.length);
                                    }
                                } catch (Exception e) {
                                }
                                this.f629a.reset();
                                break;
                            }
                        }
                    } else {
                        C0484p.logln(2, "Fragmented message not opened");
                        break;
                    }
                }
                break;
            case 1:
                if (i3 >= 2 && (decode = Base64.decode(new String(bArr, i2, i3), 0)) != null && decode.length >= 2 && C0484p.m349a(decode, 0, 2) == 17227) {
                    byte b = decode[3];
                    if (!this.f641b) {
                        if (b == 1) {
                            this.f652e[3] = 2;
                            C0484p.m372a((int) System.currentTimeMillis(), this.f652e, 8);
                            m606b(this.f646c, m600a((Object) Base64.encode(this.f652e, 0), 1, 0, 0));
                            break;
                        } else {
                            C0484p.logln(2, "oops wrong sync count: " + b);
                            break;
                        }
                    } else if (b >= 2) {
                        System.currentTimeMillis();
                        C0484p.m348a(this.f652e, 8);
                        break;
                    } else {
                        this.f652e[3] = (byte) (b + 1);
                        C0484p.m372a((int) System.currentTimeMillis(), this.f652e, 8);
                        m606b(this.f646c, m600a((Object) Base64.encode(this.f652e, 0), 1, 0, 0));
                        break;
                    }
                }
            case 2:
                if (z3) {
                    if (!this.f636a) {
                        this.f627a.mo8035a(bArr, i2, i2 + i3);
                        break;
                    } else {
                        int a2 = C0484p.m349a(bArr, i2 + 2, 2);
                        if (this.f653f != 65535 && a2 - this.f653f > 1 && Math.abs(this.f647d) > 3) {
                            C0484p.logln(-3, "WS framedrop - seqNr: " + a2 + " (pkt: " + this.f647d + "), last: " + this.f653f + " (pkt: " + this.f655g + ") " + i);
                        }
                        this.f655g = this.f647d;
                        this.f653f = a2;
                        this.f628a.mo8149a(bArr, i2, i2 + i3);
                        break;
                    }
                } else {
                    C0484p.m371a(2, "non terminated at " + i + " " + (bArr[i] & 255) + ", length" + (i3 - i2), bArr, 0, i2 + i3);
                    if (this.f629a == null) {
                        this.f629a = new ByteArrayOutputStream();
                    }
                    this.f629a.write(bArr, i2, i3);
                    break;
                }
            case 8:
                if (i3 >= 2) {
                    int a3 = C0484p.m349a(bArr, i2, 2);
                    if (a3 == 0 || (a3 >= 1000 && a3 <= 1015)) {
                        z2 = true;
                    }
                    if (z2) {
                        C0484p.logln(2, "WS - received CLOSE, cause: " + a3);
                    }
                } else {
                    C0484p.logln(2, "WS - received CLOSE, no data");
                }
                this.f648d = true;
                this.f645c = true;
                this.f627a.mo8032a(this);
                break;
            case 9:
                if (i3 <= 0) {
                    m606b(this.f649d, m600a((Object) "", 10, 0, 0));
                    break;
                } else {
                    if (this.f656g == null || this.f656g.length != i3) {
                        this.f656g = new byte[i3];
                    }
                    System.arraycopy(bArr, i2, this.f656g, 0, i3);
                    m606b(this.f649d, m600a((Object) this.f656g, 10, 0, 0));
                    break;
                }
            case 10:
                if (i3 == 8 && C0484p.m348a(bArr, i2) == C0484p.m348a(this.f654f, 0)) {
                    System.currentTimeMillis();
                    C0484p.m348a(this.f654f, 0);
                    break;
                }
        }
        return i2 + i3;
    }

    /* renamed from: a */
    private String m602a(boolean z) throws IOException {
        if (this.f633a == null) {
            this.f633a = new StringBuffer();
        }
        this.f633a.delete(0, this.f633a.length());
        int read = this.f630a.read();
        if (read == -1) {
            return null;
        }
        while (read != 10) {
            if (read != 13) {
                this.f633a.append((char) read);
            }
            read = this.f630a.read();
            if (read == -1) {
                return null;
            }
        }
        return this.f633a.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8221a(byte[] bArr) throws IOException {
        if (this.f636a) {
            byte[][] a = this.f628a.mo8148a(bArr, (int) System.currentTimeMillis());
            if (a != null) {
                for (byte[] a2 : a) {
                    m606b(this.f642b, m600a((Object) a2, 2, 0, 0));
                }
                return;
            }
            return;
        }
        m606b(this.f642b, m600a((Object) bArr, 2, 0, 0));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8220a(long j) throws IOException {
        C0484p.m374a(j, this.f654f, 0);
        m606b(this.f649d, m600a((Object) this.f654f, 9, 0, 0));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo8222b(long j) throws IOException {
        this.f652e[3] = 0;
        C0484p.m372a((int) j, this.f652e, 8);
        m606b(this.f646c, m600a((Object) Base64.encode(this.f652e, 0), 1, 0, 0));
    }

    /* renamed from: b */
    private synchronized void m606b(byte[] bArr, int i) throws IOException {
        if (i > 0) {
            if (this.f631a != null) {
                try {
                    this.f631a.write(bArr, 0, i);
                    this.f631a.flush();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        return;
    }

    /* renamed from: a */
    public final void mo8125a(byte[] bArr, int i, int i2, long j) {
        this.f627a.mo8033a(this, bArr, j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0502v m608a() {
        C0502v vVar = new C0502v();
        vVar.f562a = this.f626a;
        vVar.f563a = this.f640b;
        vVar.f564b = 6;
        try {
            vVar.f565b = this.f634a.getAddress().toString();
            vVar.f566c = this.f634a.getPort();
        } catch (Exception e) {
        }
        return vVar;
    }
}
