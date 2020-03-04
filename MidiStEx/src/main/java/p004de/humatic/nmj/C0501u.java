package p004de.humatic.nmj;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import java.io.IOException;

/* renamed from: de.humatic.nmj.u */
class C0501u extends Thread {

    /* renamed from: a */
    protected int f543a;

    /* renamed from: a */
    protected UsbDevice f544a;

    /* renamed from: a */
    protected UsbDeviceConnection f545a;

    /* renamed from: a */
    private UsbEndpoint f546a = null;

    /* renamed from: a */
    protected C0510y f547a;

    /* renamed from: a */
    protected boolean f548a;

    /* renamed from: a */
    protected byte[] f549a;

    /* renamed from: b */
    protected int f550b = 32;

    /* renamed from: b */
    private UsbEndpoint f551b = null;

    /* renamed from: b */
    protected boolean f552b;

    /* renamed from: b */
    protected byte[] f553b;

    /* renamed from: c */
    protected int f554c = 1;

    /* renamed from: c */
    private boolean f555c;

    /* renamed from: c */
    private byte[] f556c;

    /* renamed from: d */
    protected int f557d;

    /* renamed from: d */
    private byte[] f558d;

    /* renamed from: e */
    private int f559e;

    /* renamed from: e */
    private byte[] f560e;

    /* renamed from: f */
    private int f561f = 20;

    protected C0501u(int i, UsbDevice usbDevice) {
        byte[] bArr = new byte[6];
        bArr[0] = 126;
        bArr[1] = 8;
        bArr[2] = 1;
        bArr[4] = 1;
        bArr[5] = -25;
        this.f560e = bArr;
        this.f543a = i;
        this.f557d = NMJConfig.getPort(i);
        if (this.f557d > 3) {
            this.f557d = 0;
        }
        this.f544a = usbDevice;
        this.f545a = ((UsbManager) NMJConfig.m38a().getSystemService("usb")).openDevice(usbDevice);
        m515b();
    }

    /* renamed from: a */
    static C0501u m514a(int i, UsbDevice usbDevice) throws IOException {
        switch (usbDevice.getVendorId()) {
            case 1027:
                return new C0475k(i, usbDevice);
            case 1240:
                return new C0478m(i, usbDevice);
            case 1659:
                return new C0486r(i, usbDevice);
            case 9025:
                return new C0463d(i, usbDevice);
            default:
                return new C0501u(i, usbDevice);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8205a() {
        this.f555c = true;
        this.f545a.close();
        C0484p.logln(2, "Serial IOThread closed");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8207a(C0510y yVar) {
        this.f547a = yVar;
        C0484p.logln(3, "SerialIO - client added " + yVar + " " + yVar.mo8031a());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final byte[] mo8209a(int i, int i2, int i3, int i4, int i5) throws IOException {
        byte[] bArr = new byte[1];
        int controlTransfer = this.f545a.controlTransfer(192, 1, i3, 0, bArr, 1, 500);
        if (controlTransfer == 1) {
            return bArr;
        }
        throw new IOException("input control transfer failed: " + controlTransfer);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8206a(int i, int i2, int i3, int i4, byte[] bArr) throws IOException {
        int controlTransfer = this.f545a.controlTransfer(64, i2, i3, i4, (byte[]) null, 0, 0);
        if (controlTransfer != 0) {
            throw new IOException("output control transfer failed: " + controlTransfer);
        }
    }

    /* renamed from: b */
    private void m515b() {
        int i = 1;
        try {
            if (this.f544a.getDeviceClass() != 2) {
                i = 0;
            }
            if (!this.f545a.claimInterface(this.f544a.getInterface(i), true)) {
                C0484p.m362a("USBSerial - failed to claim interface");
                return;
            }
            UsbInterface usbInterface = this.f544a.getInterface(i);
            C0484p.logln(2, "USBSerial - Interface aquired");
            C0484p.logln(2, "class " + usbInterface.getInterfaceClass() + " subclass " + usbInterface.getInterfaceSubclass() + " protocol " + usbInterface.getInterfaceProtocol());
            for (int i2 = 0; i2 < this.f544a.getInterfaceCount(); i2++) {
                usbInterface = this.f544a.getInterface(i2);
                C0484p.logln(2, "SerialDevice - interface class: " + usbInterface.getInterfaceClass() + ", interface subclass: " + usbInterface.getInterfaceSubclass());
                if (usbInterface.getInterfaceClass() == 255) {
                    if (!(usbInterface.getInterfaceSubclass() == 255 || usbInterface.getInterfaceSubclass() == 0)) {
                        this.f545a.close();
                        return;
                    }
                } else if (usbInterface.getInterfaceClass() != 10) {
                    if (usbInterface.getInterfaceSubclass() != 0) {
                        this.f545a.close();
                        return;
                    }
                } else if (usbInterface.getInterfaceClass() == 2) {
                    if (usbInterface.getInterfaceSubclass() != 2) {
                        this.f545a.close();
                        return;
                    }
                } else if (usbInterface.getInterfaceClass() != 10) {
                    this.f545a.close();
                    return;
                } else if (usbInterface.getInterfaceSubclass() != 0) {
                    this.f545a.close();
                    return;
                }
                for (int i3 = 0; i3 < usbInterface.getEndpointCount(); i3++) {
                    if (usbInterface.getEndpoint(i3).getType() == 2) {
                        this.f550b = usbInterface.getEndpoint(i3).getMaxPacketSize();
                        if (usbInterface.getEndpoint(i3).getDirection() == 128) {
                            this.f546a = usbInterface.getEndpoint(i3);
                        } else {
                            this.f551b = usbInterface.getEndpoint(i3);
                        }
                    }
                }
                if (this.f546a != null || this.f551b != null) {
                    break;
                }
            }
            this.f558d = new byte[this.f550b];
            C0484p.logln(2, "num endpoints " + usbInterface.getEndpointCount());
            C0484p.logln(2, "Input: " + this.f546a + "\nOutput: " + this.f551b);
            NMJConfig.m59a(this.f543a, 4, 32);
        } catch (Exception e) {
            if (!this.f555c) {
                NMJConfig.m60a(this.f543a, (int) NMJConfig.E_USB, e.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8143a(boolean z) throws IOException {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public byte[] m523a(byte[] bArr) {
        return bArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public byte[] mo8145a(byte[] bArr, int i, int i2) throws IOException {
        this.f547a.mo8216a(bArr, i, i2);
        return bArr;
    }

    public void run() {
        try {
            mo8143a(true);
            this.f556c = new byte[this.f550b];
            while (!this.f555c) {
                try {
                    if (this.f557d == 2) {
                        int i = this.f559e;
                        this.f559e = i - 1;
                        if (i == 0) {
                            mo8208a(this.f560e, this.f560e.length);
                        }
                    }
                    int bulkTransfer = this.f545a.bulkTransfer(this.f546a, this.f556c, this.f550b, 500);
                    if (bulkTransfer > 0) {
                        if (!this.f552b) {
                            mo8145a(this.f556c, 0, bulkTransfer);
                        } else if (bulkTransfer > 2) {
                            mo8145a(this.f556c, 2, bulkTransfer);
                        }
                    }
                } catch (Exception e) {
                    if (e.toString().indexOf("closed") < 0) {
                        e.printStackTrace();
                    }
                    if (!(e instanceof IndexOutOfBoundsException)) {
                        return;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8144a(byte[] bArr) throws IOException {
        byte[] a = mo8144a(bArr);
        if (a != null) {
            mo8208a(a, a.length);
            if (this.f557d == 2) {
                this.f559e = 2;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8208a(byte[] bArr, int i) {
        int min;
        if (i <= this.f550b) {
            int bulkTransfer = this.f545a.bulkTransfer(this.f551b, bArr, i, this.f561f);
            if (bulkTransfer != i) {
                C0484p.m362a("SerialDevice - write failed " + bulkTransfer);
                return;
            }
            return;
        }
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            int i4 = i2 + 1;
            if (i2 < 100 && (min = Math.min(this.f550b, i - i3)) != 0) {
                System.arraycopy(bArr, i3, this.f558d, 0, min);
                int bulkTransfer2 = this.f545a.bulkTransfer(this.f551b, this.f558d, min, this.f561f);
                if (bulkTransfer2 != min) {
                    C0484p.m362a("SerialDevice - write failed " + bulkTransfer2);
                    return;
                } else {
                    i3 = min + i3;
                    i2 = i4;
                }
            } else {
                return;
            }
        }
    }
}
