package p004de.humatic.nmj;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.support.p000v4.internal.view.SupportMenu;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import p004de.humatic.nmj.C0504x;

/* renamed from: de.humatic.nmj.y */
final class C0510y extends C0503w {

    /* renamed from: a */
    private static C0474j f610a;

    /* renamed from: a */
    private static C0513b f611a;

    /* renamed from: a */
    private static Vector<UsbDevice> f612a = new Vector<>();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static UsbDevice f613b;

    /* renamed from: b */
    private static UsbManager f614b;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static int f615f;

    /* renamed from: a */
    private int f616a;

    /* renamed from: a */
    private BroadcastReceiver f617a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public UsbDevice f618a;

    /* renamed from: a */
    private final UsbManager f619a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0501u f620a;

    /* renamed from: b */
    private int f621b;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public int f622e;

    static {
        new Vector();
    }

    C0510y(int i, C0479n nVar) throws Exception {
        super(nVar, i);
        if (Build.VERSION.SDK_INT < 12) {
            throw new IllegalArgumentException("USB hostmode requires Android 3.1 or greater");
        }
        this.f616a = NMJConfig.getPort(this.f575c);
        this.f621b = (NMJConfig.getLocalPort(this.f575c) >> 16) & SupportMenu.USER_MASK;
        this.f622e = NMJConfig.getLocalPort(this.f575c) & SupportMenu.USER_MASK;
        NMJConfig.getIP(this.f575c);
        this.f619a = (UsbManager) NMJConfig.m38a().getSystemService("usb");
        this.f617a = new C0512a(new C0504x.C0507b() {
            /* renamed from: a */
            public final void mo8211a() {
                NMJConfig.m60a(C0510y.this.f575c, (int) NMJConfig.E_USB, "USB permission denied");
                C0510y.this.mo8031a();
            }
        });
        for (UsbDevice next : this.f619a.getDeviceList().values()) {
            if (!NMJConfig.m40a(next.getVendorId(), next.getProductId())) {
                C0484p.logln(3, "USB device " + next + " vendorID: " + next.getVendorId() + ", Product ID: " + next.getProductId());
                if (next.getVendorId() == (this.f621b & SupportMenu.USER_MASK) && next.getProductId() == (this.f622e & SupportMenu.USER_MASK)) {
                    C0484p.logln(3, "USBSerial - using device " + next.toString() + "\nPermission: " + this.f619a.hasPermission(next));
                    this.f618a = next;
                    if (!this.f619a.hasPermission(next)) {
                        PendingIntent broadcast = PendingIntent.getBroadcast(NMJConfig.m38a(), 0, new Intent("de.humatic.nmj.USB"), 0);
                        NMJConfig.m38a().registerReceiver(this.f617a, new IntentFilter("de.humatic.nmj.USB"));
                        this.f619a.requestPermission(next, broadcast);
                    } else {
                        if (this.f620a == null) {
                            try {
                                this.f620a = C0501u.m514a(i, this.f618a);
                                new Thread(this.f620a).start();
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        m594f();
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void m596a() {
        if (this.f620a != null) {
            C0484p.logln(4, "USBSerial closing device " + this.f616a);
            this.f620a.mo8205a();
            this.f620a = null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8034a(byte[] bArr) throws IOException {
        if (this.f618a == null) {
            C0484p.m362a("USB " + this.f575c + " - no device");
        } else if (bArr.length > 3) {
            this.f574b.removeAllElements();
            C0484p.m384a(bArr, (Vector<byte[]>) this.f574b);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.f574b.size()) {
                    this.f620a.mo8144a((byte[]) this.f574b.get(i2));
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        } else {
            this.f620a.mo8144a(bArr);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8216a(byte[] bArr, int i, int i2) {
        this.f572a.mo8156a(bArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int m595a() {
        return this.f616a;
    }

    /* renamed from: c */
    protected static void m589c() {
        try {
            NMJConfig.m38a().unregisterReceiver(f611a);
        } catch (Exception e) {
        }
    }

    /* renamed from: f */
    private void m594f() {
        if (this.f620a != null) {
            this.f620a.mo8207a(this);
        }
    }

    /* renamed from: de.humatic.nmj.y$a */
    class C0512a extends BroadcastReceiver {

        /* renamed from: a */
        private final C0504x.C0507b f624a;

        public C0512a(C0504x.C0507b bVar) {
            this.f624a = bVar;
        }

        public final void onReceive(Context context, Intent intent) {
            NMJConfig.m38a().unregisterReceiver(this);
            if (!intent.getAction().equals("de.humatic.nmj.USB")) {
                return;
            }
            if (!intent.getBooleanExtra("permission", false)) {
                C0504x.C0507b bVar = this.f624a;
                intent.getParcelableExtra("device");
                bVar.mo8211a();
                return;
            }
            C0484p.logln(4, "Permission granted");
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            if (usbDevice == null) {
                NMJConfig.m60a(C0510y.this.f575c, (int) NMJConfig.E_USB, "USB - Device not available");
            } else if (usbDevice.getVendorId() == C0510y.m574a(C0510y.this) && usbDevice.getProductId() == C0510y.this.f622e) {
                C0510y.this.f618a = usbDevice;
                if (C0510y.m574a(C0510y.this) == null) {
                    try {
                        C0510y.this.f620a = C0501u.m514a(C0510y.this.f575c, C0510y.m574a(C0510y.this));
                        new Thread(C0510y.m574a(C0510y.this)).start();
                    } catch (IOException e) {
                        NMJConfig.m60a(C0510y.this.f575c, (int) NMJConfig.E_USB, "USB - failed to initialize device: " + e.toString());
                        e.printStackTrace();
                        return;
                    }
                }
                C0510y.m574a(C0510y.this);
            }
        }
    }

    /* renamed from: a */
    protected static void m582a(C0474j jVar) {
        try {
            jVar.mo8141a(7, 0);
            f613b = null;
            f612a.removeAllElements();
            f610a = jVar;
            UsbManager usbManager = (UsbManager) NMJConfig.m38a().getSystemService("usb");
            f614b = usbManager;
            HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
            if (deviceList.isEmpty()) {
                C0484p.logln(3, "USBSerial - No USB devices attached");
                jVar.mo8141a(7, 1);
                return;
            }
            deviceList.size();
            f615f = 0;
            f611a = new C0513b();
            NMJConfig.m38a().registerReceiver(f611a, new IntentFilter("de.humatic.nmj.USB"));
            if (deviceList.values().size() > 1) {
                C0484p.logln(3, "USBSerial - num devs " + deviceList.values().size());
            }
            for (UsbDevice next : deviceList.values()) {
                if (((next.getDeviceClass() == 255 && next.getDeviceSubclass() == 255) || ((next.getDeviceClass() == 2 && next.getDeviceSubclass() == 0) || (next.getDeviceClass() == 0 && next.getDeviceSubclass() == 0))) && !f612a.contains(next) && !NMJConfig.m40a(next.getVendorId(), next.getProductId())) {
                    f612a.add(next);
                }
            }
            m591d(f612a.get(0));
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static void m591d(UsbDevice usbDevice) {
        try {
            if (!f614b.hasPermission(usbDevice)) {
                PendingIntent broadcast = PendingIntent.getBroadcast(NMJConfig.m38a(), 0, new Intent("de.humatic.nmj.USB"), 0);
                C0484p.logln(3, "USBSerial - requesting permission for " + usbDevice.getDeviceName());
                f614b.requestPermission(usbDevice, broadcast);
                return;
            }
            C0484p.logln(3, "USBSerial - already got permission for " + usbDevice.getDeviceName());
            m593e(usbDevice);
            int i = f615f + 1;
            f615f = i;
            if (i < f612a.size()) {
                m591d(f612a.get(f615f));
                return;
            }
            f612a.removeAllElements();
            f610a.mo8141a(7, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    protected static UsbDevice mo8031a() {
        return f613b;
    }

    /* renamed from: e */
    protected static void m592e() {
        f613b = null;
    }

    /* renamed from: de.humatic.nmj.y$b */
    static class C0513b extends BroadcastReceiver {
        public final void onReceive(Context context, Intent intent) {
            C0510y.f615f = C0510y.f615f + 1;
            if (intent.getAction().equals("de.humatic.nmj.USB")) {
                if (!intent.getBooleanExtra("permission", false)) {
                    C0510y.f613b = null;
                } else {
                    C0510y.m593e((UsbDevice) intent.getParcelableExtra("device"));
                }
            }
            if (C0510y.f615f < C0510y.mo8031a().size()) {
                C0510y.m591d((UsbDevice) C0510y.mo8031a().get(C0510y.f615f));
                return;
            }
            NMJConfig.m38a().unregisterReceiver(this);
            C0510y.mo8031a().removeAllElements();
            C0510y.mo8031a().mo8141a(7, 1);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static void m593e(UsbDevice usbDevice) {
        int i;
        String str;
        try {
            C0484p.logln(3, "USBSerial - examining device " + usbDevice);
            if ((usbDevice.getDeviceClass() == 0 && usbDevice.getDeviceSubclass() == 0) || ((usbDevice.getDeviceClass() == 2 && usbDevice.getDeviceSubclass() == 0) || (usbDevice.getDeviceClass() == 255 && usbDevice.getDeviceSubclass() == 255))) {
                if (usbDevice.getDeviceClass() == 2) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (usbDevice.getInterfaceCount() == 0) {
                    C0484p.logln(3, "No interfaces on " + usbDevice);
                    return;
                }
                UsbDeviceConnection openDevice = ((UsbManager) NMJConfig.m38a().getSystemService("usb")).openDevice(usbDevice);
                if (!openDevice.claimInterface(usbDevice.getInterface(i), true)) {
                    C0484p.logln(1, "USBSerial - failed to claim interface for " + usbDevice);
                    return;
                }
                UsbInterface usbInterface = usbDevice.getInterface(i);
                C0484p.logln(2, "interface  " + usbInterface);
                switch (usbInterface.getInterfaceClass()) {
                    case 2:
                    case 10:
                    case 255:
                        C0484p.logln(2, "num endpoints " + usbInterface.getEndpointCount());
                        for (int i2 = 0; i2 < usbInterface.getEndpointCount(); i2++) {
                            if (usbInterface.getEndpoint(i2).getType() == 2) {
                                if (usbInterface.getEndpoint(i2).getDirection() == 128) {
                                    C0484p.logln(2, "input " + usbInterface.getEndpoint(i2));
                                } else {
                                    C0484p.logln(2, "output " + usbInterface.getEndpoint(i2));
                                }
                            }
                        }
                        f613b = usbDevice;
                        int vendorId = (usbDevice.getVendorId() << 16) | usbDevice.getProductId();
                        C0467g gVar = new C0467g((String) null, -1);
                        gVar.f281c = usbDevice.getDeviceName();
                        gVar.f282d = String.valueOf(vendorId);
                        int vendorId2 = usbDevice.getVendorId();
                        String str2 = " (" + C0484p.m354a(vendorId2) + ":" + C0484p.m354a(usbDevice.getProductId()) + ")";
                        switch (vendorId2) {
                            case 1027:
                                str = "FTDI" + str2;
                                break;
                            case 1240:
                                str = "Microchip Technology" + str2;
                                break;
                            case 1659:
                                str = "Prolific USB Serial" + str2;
                                break;
                            case 9025:
                                str = "Arduino" + str2;
                                break;
                            default:
                                str = "USB Serial " + str2;
                                break;
                        }
                        gVar.mo8126a(str);
                        f610a.mo8142a(7, gVar, -1);
                        openDevice.close();
                        return;
                    default:
                        C0484p.logln(3, "USBSerial - interface class mismatch: " + usbInterface.getInterfaceClass());
                        NMJConfig.m61a(7, String.valueOf(usbDevice.getVendorId()) + ":" + usbDevice.getProductId());
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
