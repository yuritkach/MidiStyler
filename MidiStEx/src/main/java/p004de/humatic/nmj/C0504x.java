package p004de.humatic.nmj;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p003v7.widget.helper.ItemTouchHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/* renamed from: de.humatic.nmj.x */
final class C0504x extends C0503w {

    /* renamed from: a */
    private static C0474j f577a;

    /* renamed from: a */
    private static C0509d f578a;

    /* renamed from: a */
    private static Vector<C0506a> f579a = new Vector<>();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static UsbDevice f580b = null;

    /* renamed from: b */
    private static UsbManager f581b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static Vector<UsbDevice> f582c = new Vector<>();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static int f583f;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public int f584a;

    /* renamed from: a */
    private BroadcastReceiver f585a;

    /* renamed from: a */
    private UsbDevice f586a;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public UsbDeviceConnection f587a;

    /* renamed from: a */
    UsbEndpoint f588a = null;

    /* renamed from: a */
    private final UsbManager f589a;

    /* renamed from: a */
    private byte[] f590a = new byte[3];

    /* renamed from: b */
    private int f591b;

    /* renamed from: b */
    UsbEndpoint f592b = null;

    /* renamed from: b */
    private byte[] f593b = new byte[4];

    /* renamed from: e */
    private int f594e;

    /* renamed from: de.humatic.nmj.x$b */
    interface C0507b {
        /* renamed from: a */
        void mo8211a();
    }

    C0504x(int i, C0479n nVar) throws Exception {
        super(nVar, i);
        if (Build.VERSION.SDK_INT < 12) {
            throw new IllegalArgumentException("USB hostmode requires Android 3.1 or greater");
        }
        this.f584a = NMJConfig.getPort(this.f575c);
        this.f591b = (NMJConfig.getLocalPort(this.f575c) >> 16) & SupportMenu.USER_MASK;
        this.f594e = NMJConfig.getLocalPort(this.f575c) & SupportMenu.USER_MASK;
        NMJConfig.getIP(this.f575c);
        this.f589a = (UsbManager) NMJConfig.m38a().getSystemService("usb");
        this.f585a = new C0508c(new C0507b() {
            /* renamed from: a */
            public final void mo8211a() {
                NMJConfig.m60a(C0504x.this.f575c, (int) NMJConfig.E_USB, "USB permission denied");
                C0504x.this.mo8031a();
            }
        });
        for (UsbDevice next : this.f589a.getDeviceList().values()) {
            C0484p.logln(5, "USB device " + next + " vendorID: 0x" + C0484p.m354a(next.getVendorId()) + ", Product ID: 0x" + C0484p.m354a(next.getProductId()));
            if (next.getVendorId() == (this.f591b & SupportMenu.USER_MASK) && next.getProductId() == (this.f594e & SupportMenu.USER_MASK)) {
                C0484p.logln(3, "USBMidi " + this.f575c + " - using device " + next.toString() + "\nPermission: " + this.f589a.hasPermission(next));
                this.f586a = next;
                if (!this.f589a.hasPermission(next)) {
                    PendingIntent broadcast = PendingIntent.getBroadcast(NMJConfig.m38a(), 0, new Intent("de.humatic.nmj.USB"), 0);
                    NMJConfig.m38a().registerReceiver(this.f585a, new IntentFilter("de.humatic.nmj.USB"));
                    this.f589a.requestPermission(next, broadcast);
                } else {
                    if (mo8031a() == null) {
                        C0506a aVar = new C0506a(this, this.f586a, (byte) 0);
                        f579a.add(aVar);
                        new Thread(aVar).start();
                    }
                    m564f();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void m566a(byte[] bArr) throws IOException {
        if (this.f586a == null) {
            C0484p.m362a("USB " + this.f575c + " - no device");
        } else if (bArr.length > 3) {
            this.f574b.removeAllElements();
            C0484p.m384a(bArr, (Vector<byte[]>) this.f574b);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.f574b.size()) {
                    mo8031a().mo8212a(this.f584a, (byte[]) this.f574b.get(i2));
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        } else {
            mo8031a().mo8212a(this.f584a, bArr);
        }
    }

    /* renamed from: a */
    private C0506a m539a() {
        Iterator<C0506a> it = f579a.iterator();
        while (it.hasNext()) {
            C0506a next = it.next();
            if (next.f597a.equals(this.f586a)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void m565a() {
        if (f579a.size() != 0 && this.f586a != null) {
            C0484p.logln(4, "USBMidi unregistering client " + this.f584a);
            C0506a.m570a(mo8031a(), this);
        }
    }

    /* renamed from: c */
    protected static void m559c() {
        try {
            NMJConfig.m38a().unregisterReceiver(f578a);
        } catch (Exception e) {
        }
    }

    /* renamed from: f */
    private void m564f() {
        if (this.f586a != null) {
            C0506a.m571b(mo8031a(), this);
        }
    }

    /* renamed from: de.humatic.nmj.x$c */
    class C0508c extends BroadcastReceiver {

        /* renamed from: a */
        private final C0507b f607a;

        public C0508c(C0507b bVar) {
            this.f607a = bVar;
        }

        public final void onReceive(Context context, Intent intent) {
            NMJConfig.m38a().unregisterReceiver(this);
            if (!intent.getAction().equals("de.humatic.nmj.USB")) {
                return;
            }
            if (!intent.getBooleanExtra("permission", false)) {
                C0507b bVar = this.f607a;
                intent.getParcelableExtra("device");
                bVar.mo8211a();
                return;
            }
            C0484p.logln(4, "Permission granted");
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            if (usbDevice == null) {
                NMJConfig.m60a(C0504x.this.f575c, (int) NMJConfig.E_USB, "USB - Device not available");
            } else if (usbDevice.getVendorId() == (C0504x.m532a(C0504x.this) & SupportMenu.USER_MASK) && usbDevice.getProductId() == (C0504x.m552b(C0504x.this) & SupportMenu.USER_MASK)) {
                C0504x.m540a(C0504x.this, usbDevice);
                if (C0504x.m540a(C0504x.this, C0504x.m532a(C0504x.this)) == null) {
                    C0506a aVar = new C0506a(C0504x.this, C0504x.m532a(C0504x.this), (byte) 0);
                    C0504x.mo8031a().add(aVar);
                    new Thread(aVar).start();
                }
                C0504x.m532a(C0504x.this);
            }
        }
    }

    /* renamed from: a */
    protected static void m545a(C0474j jVar) {
        try {
            jVar.mo8141a(5, 0);
            f580b = null;
            f582c.removeAllElements();
            f577a = jVar;
            UsbManager usbManager = (UsbManager) NMJConfig.m38a().getSystemService("usb");
            f581b = usbManager;
            HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
            if (deviceList.isEmpty()) {
                C0484p.logln(3, "USBMidi - No USB devices attached");
                jVar.mo8141a(5, 1);
                return;
            }
            deviceList.size();
            f583f = 0;
            f578a = new C0509d();
            NMJConfig.m38a().registerReceiver(f578a, new IntentFilter("de.humatic.nmj.USB"));
            if (deviceList.values().size() > 1) {
                C0484p.logln(3, "USBHost - num devs " + deviceList.values().size());
            }
            for (UsbDevice next : deviceList.values()) {
                if (((next.getDeviceClass() == 1 && next.getDeviceSubclass() == 3) || (next.getDeviceClass() == 0 && next.getDeviceSubclass() == 0)) && !f582c.contains(next) && !NMJConfig.m40a(next.getVendorId(), next.getProductId())) {
                    f582c.add(next);
                }
            }
            if (f582c.size() == 0) {
                f577a.mo8141a(5, 1);
            } else {
                m561d(f582c.get(0));
            }
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static void m561d(UsbDevice usbDevice) {
        try {
            if (!f581b.hasPermission(usbDevice)) {
                PendingIntent broadcast = PendingIntent.getBroadcast(NMJConfig.m38a(), 0, new Intent("de.humatic.nmj.USB"), 0);
                C0484p.logln(3, "USBMidi - requesting permission for " + usbDevice.getDeviceName());
                f581b.requestPermission(usbDevice, broadcast);
                return;
            }
            C0484p.logln(3, "USBMidi - already got permission for " + usbDevice.getDeviceName());
            m563e(usbDevice);
            int i = f583f + 1;
            f583f = i;
            if (i < f582c.size()) {
                m561d(f582c.get(f583f));
                return;
            }
            f582c.removeAllElements();
            m559c();
            f577a.mo8141a(5, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    protected static UsbDevice m534a() {
        return f580b;
    }

    /* renamed from: e */
    protected static void m562e() {
        f580b = null;
    }

    /* renamed from: de.humatic.nmj.x$d */
    static class C0509d extends BroadcastReceiver {

        /* renamed from: a */
        private boolean f609a;

        public final void onReceive(Context context, Intent intent) {
            C0504x.f583f = C0504x.mo8031a() + 1;
            if (intent.getAction().equals("de.humatic.nmj.USB")) {
                if (!intent.getBooleanExtra("permission", false)) {
                    C0504x.f580b = null;
                    this.f609a = true;
                } else {
                    C0504x.m563e((UsbDevice) intent.getParcelableExtra("device"));
                }
            }
            if (C0504x.mo8031a() < C0504x.f582c.size()) {
                C0504x.m561d((UsbDevice) C0504x.f582c.get(C0504x.mo8031a()));
                return;
            }
            NMJConfig.m38a().unregisterReceiver(this);
            C0504x.f582c.removeAllElements();
            C0504x.mo8031a().mo8141a(5, this.f609a ? 2 : 1);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static void m563e(UsbDevice usbDevice) {
        int i;
        UsbInterface usbInterface;
        int i2;
        int i3;
        String str;
        int[] iArr;
        int i4;
        String str2;
        int max;
        int i5;
        try {
            C0484p.logln(3, "USBMidi - examining device " + C0484p.m354a(usbDevice.getVendorId()) + " " + C0484p.m354a(usbDevice.getProductId()) + " " + usbDevice);
            if (usbDevice.getDeviceClass() == 1 && usbDevice.getDeviceSubclass() == 3) {
                f580b = usbDevice;
            } else if (usbDevice.getDeviceClass() == 0 && usbDevice.getDeviceSubclass() == 0) {
                C0484p.logln(3, "Nr. of interfaces " + usbDevice.getInterfaceCount());
                if (usbDevice.getInterfaceCount() >= 2) {
                    UsbDeviceConnection openDevice = ((UsbManager) NMJConfig.m38a().getSystemService("usb")).openDevice(usbDevice);
                    C0484p.logln(3, "DeviceConnection established " + openDevice);
                    UsbInterface usbInterface2 = null;
                    if (openDevice.claimInterface(usbDevice.getInterface(1), true)) {
                        usbInterface2 = usbDevice.getInterface(1);
                        i = usbInterface2.getEndpointCount();
                    } else if (usbDevice.getInterfaceCount() <= 2) {
                        C0484p.logln(1, "USBMidi - failed to claim interface 1" + " for " + usbDevice);
                        return;
                    } else {
                        i = 0;
                    }
                    if (i == 0) {
                        openDevice.releaseInterface(usbInterface2);
                        if (usbDevice.getInterfaceCount() > 2) {
                            i5 = 1;
                            while (i5 < usbDevice.getInterfaceCount()) {
                                i5++;
                                if (openDevice.claimInterface(usbDevice.getInterface(i5), true)) {
                                    usbInterface2 = usbDevice.getInterface(i5);
                                    i = usbInterface2.getEndpointCount();
                                    if (i != 0 && usbInterface2.getInterfaceClass() == 1) {
                                        if (usbInterface2.getInterfaceSubclass() == 3) {
                                            break;
                                        }
                                    }
                                } else {
                                    C0484p.logln(1, "USBMidi - failed to claim interface " + i5 + " for " + usbDevice);
                                    return;
                                }
                            }
                        } else {
                            i5 = 1;
                        }
                        if (i == 0) {
                            C0484p.logln(1, "USB - No endpoints");
                            return;
                        }
                        usbInterface = usbInterface2;
                        i2 = i;
                        i3 = i5;
                    } else {
                        usbInterface = usbInterface2;
                        i2 = i;
                        i3 = 1;
                    }
                    C0484p.logln(3, "Interface " + i3 + " claimed, nr endpoints: " + i2);
                    if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                        C0484p.logln(3, "Interface class: " + usbInterface.getInterfaceClass() + " subclass: " + usbInterface.getInterfaceSubclass());
                        try {
                            byte[] bArr = new byte[64];
                            int controlTransfer = openDevice.controlTransfer(128, 6, 770, 0, bArr, 64, 500);
                            if (controlTransfer > 0) {
                                C0484p.m370a(5, "ctrl transfer ret.:", bArr);
                                byte[] bArr2 = new byte[(controlTransfer / 2)];
                                for (int i6 = 0; i6 < bArr2.length; i6++) {
                                    bArr2[i6] = bArr[i6 << 1];
                                }
                                str = new String(bArr2).substring(1);
                            } else {
                                str = null;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            str = null;
                        }
                        int[] iArr2 = null;
                        if (Build.VERSION.SDK_INT >= 13) {
                            byte[] rawDescriptors = openDevice.getRawDescriptors();
                            C0484p.m370a(5, "Raw descriptors:", rawDescriptors);
                            int b = m553b(rawDescriptors);
                            int[] b2 = m553b(rawDescriptors);
                            if (b2[0] <= 8 || b2[1] <= 8) {
                                max = Math.max(b2[0], b2[1]);
                            } else {
                                max = Math.min(b2[0], b2[1]);
                            }
                            int[] iArr3 = new int[max];
                            if (max == 1) {
                                iArr3[0] = 0;
                                if (b2[0] > 0) {
                                    iArr3[0] = iArr3[0] | 1;
                                }
                                if (b2[1] > 0) {
                                    iArr3[0] = iArr3[0] | 2;
                                }
                            } else {
                                for (int i7 = 0; i7 < max; i7++) {
                                    iArr3[i7] = 3;
                                }
                            }
                            C0484p.logln(2, "nr. ports: " + max + ", max pkt size " + b);
                            iArr = iArr3;
                            i4 = max;
                        } else {
                            int i8 = 1;
                            for (int i9 = 0; i9 < usbInterface.getEndpointCount(); i9++) {
                                if (usbInterface.getEndpoint(i9).getType() == 2) {
                                    int maxPacketSize = usbInterface.getEndpoint(i9).getMaxPacketSize();
                                    if (maxPacketSize > 32) {
                                        i8 = maxPacketSize / 32;
                                    }
                                    iArr2 = new int[i8];
                                    if (usbInterface.getEndpoint(i9).getDirection() == 128) {
                                        C0484p.logln(2, "input " + usbInterface.getEndpoint(i9));
                                        for (int i10 = 0; i10 < i8; i10++) {
                                            iArr2[i10] = iArr2[i10] | 1;
                                        }
                                    } else {
                                        C0484p.logln(2, "output " + usbInterface.getEndpoint(i9));
                                        for (int i11 = 0; i11 < i8; i11++) {
                                            iArr2[i11] = iArr2[i11] | 2;
                                        }
                                    }
                                }
                            }
                            iArr = iArr2;
                            i4 = i8;
                        }
                        f580b = usbDevice;
                        int vendorId = (usbDevice.getVendorId() << 16) | usbDevice.getProductId();
                        if (i4 == 1) {
                            C0467g gVar = new C0467g((String) null, -1);
                            gVar.f281c = usbDevice.getDeviceName();
                            gVar.f282d = String.valueOf(vendorId);
                            if (str == null) {
                                str = m541a(usbDevice.getVendorId(), usbDevice.getProductId());
                            }
                            gVar.mo8126a(str);
                            gVar.f279b = iArr[0] << 8;
                            f577a.mo8142a(5, gVar, -1);
                        } else {
                            if (str == null) {
                                str2 = m541a(usbDevice.getVendorId(), usbDevice.getProductId());
                            } else {
                                str2 = str;
                            }
                            for (int i12 = 0; i12 < i4; i12++) {
                                C0467g gVar2 = new C0467g((String) null, -1);
                                gVar2.f281c = usbDevice.getDeviceName();
                                gVar2.f282d = String.valueOf(vendorId);
                                gVar2.mo8126a(String.valueOf(str2) + " Port " + (i12 + 1));
                                gVar2.f279b = (iArr[i12] << 8) | i12;
                                f577a.mo8142a(5, gVar2, -1);
                            }
                        }
                        openDevice.close();
                        return;
                    }
                    C0484p.logln(1, "USBMidi - class / subclass mismatch " + usbInterface.getInterfaceClass() + " " + usbInterface.getInterfaceSubclass());
                    openDevice.close();
                    NMJConfig.m61a(5, String.valueOf(usbDevice.getVendorId()) + ":" + usbDevice.getProductId());
                } else if (usbDevice.getInterfaceCount() == 0) {
                    C0484p.logln(3, "No interfaces on " + usbDevice);
                } else {
                    C0484p.logln(3, "Not a MIDI interface, only 1 interface on " + usbDevice);
                    NMJConfig.m61a(5, String.valueOf(usbDevice.getVendorId()) + ":" + usbDevice.getProductId());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: b */
    private static int[] m557b(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < bArr.length) {
            int i4 = i3 + 1;
            byte b = bArr[i3] & 255;
            int i5 = i4 + 1;
            byte b2 = bArr[i4] & 255;
            if (b <= 2) {
                break;
            }
            if (b2 == 36) {
                int i6 = i5 + 1;
                byte b3 = bArr[i5] & 255;
                if (b3 == 2 || b3 == 3) {
                    byte b4 = bArr[i6] & 255;
                    if (b3 == 2) {
                        if (b4 == 1) {
                            i2++;
                        }
                    } else if (b3 == 3 && b4 == 1) {
                        i++;
                    }
                }
            }
            i3 = (b - 2) + i5;
        }
        return new int[]{i2, i};
    }

    /* renamed from: b */
    private static int m553b(byte[] bArr) {
        byte b = -1;
        int i = 0;
        byte b2 = -1;
        while (i < bArr.length) {
            int i2 = i + 1;
            byte b3 = bArr[i] & 255;
            int i3 = i2 + 1;
            byte b4 = bArr[i2] & 255;
            if (b3 <= 2) {
                break;
            }
            if (b4 == 1) {
                b = bArr[i3 + 5] & 255;
                C0484p.logln(3, "   Ctrl mps " + b);
            } else if (b4 == 5) {
                b2 = ((bArr[i3 + 3] & 255) << 8) | (bArr[i3 + 2] & 255);
                C0484p.logln(3, "   Endpoint mps " + b2);
            }
            i = (b3 - 2) + i3;
        }
        if (b2 < 0 && b < 0) {
            return 4;
        }
        if (b2 >= 0) {
            return b2;
        }
        return b;
    }

    /* renamed from: a */
    private static String m541a(int i, int i2) {
        C0484p.logln(2, "getKnownDeviceName " + C0484p.m354a(i) + " " + C0484p.m354a(i2));
        String str = " (" + C0484p.m354a(i) + ":" + C0484p.m354a(i2) + ")";
        switch (i) {
            case 1054:
                if (i2 == 16128) {
                    return "E-Mu Xboard 25 MIDI Controller";
                }
                if (i2 == 16130) {
                    return "E-Mu 0202";
                }
                if (i2 == 16132) {
                    return "E-Mu 0404";
                }
                if (i2 == 16135) {
                    return "E-Mu Xmidi 1x1";
                }
                return "Creative" + str;
            case 1177:
                return "Yamaha" + str;
            case 1240:
                if (i2 == 64156) {
                    return "Missing Link USB";
                }
                return "Microchip Technology" + str;
            case 1410:
                if (i2 == 0) {
                    return "UA-100";
                }
                if (i2 == 2) {
                    return "UM-4/MPU-64";
                }
                if (i2 == 5) {
                    return "Edirol UM-2";
                }
                if (i2 == 9 || i2 == 82) {
                    return "Edirol UM-1SX";
                }
                if (i2 == 154) {
                    return "Edirol UM-3EX";
                }
                if (i2 == 157) {
                    return "Edirol UM-1";
                }
                return "Roland" + str;
            case 1891:
                if (i2 == 4176) {
                    return "MIDISport 2x2 Anniv.";
                }
                if (i2 == 279) {
                    return "Trigger Finger";
                }
                if (i2 == 336) {
                    return "MIDISport Uno";
                }
                if (i2 == 405) {
                    return "Oxygen 8 v2";
                }
                if (i2 == 4129) {
                    return "MidiSport 4x4 Anniv.";
                }
                return "M-Audio" + str;
            case 1999:
                if (i2 == 26626) {
                    return "Casio MIDI Keyboard";
                }
                return "Casio" + str;
            case 2045:
                if (i2 == 0) {
                    return "FastLane MIDI Interface";
                }
                if (i2 == 1) {
                    return "FastLane Quad MIDI Interface";
                }
                return "MOTU" + str;
            case 2372:
                if (i2 == 32) {
                    return "KAOSS Pad KP3";
                }
                if (i2 == 35) {
                    return "KAOSSILATOR PRO";
                }
                if (i2 == 269) {
                    return "nanoKEY";
                }
                if (i2 == 270) {
                    return "nanoPAD";
                }
                if (i2 == 271) {
                    return "nanoKONTROL";
                }
                if (i2 == 279) {
                    return "nanoKONTROL2";
                }
                if (i2 == 3843) {
                    return "K-Series K61P";
                }
                return "Korg" + str;
            case 2536:
                if (i2 == 98) {
                    return "Akai MPD16";
                }
                if (i2 == 13) {
                    return "Akai EWI";
                }
                if (i2 == 113) {
                    return "Akai MPK25";
                }
                if (i2 == 118) {
                    return "Akai LPK25";
                }
                return "Akai" + str;
            case 2623:
            case 5899:
                if (i2 == 17) {
                    return "Swissonic MIDI-USB 1x1";
                }
                return "Swissonic" + str;
            case 2637:
                if (i2 == 140) {
                    return "TerraTec MIDI MASTER";
                }
                if (i2 == 142) {
                    return "TerraTec MK-249C";
                }
                if (i2 == 163) {
                    return "TerraTec MK-461C";
                }
                if (i2 == 245) {
                    return "TerraTec UC-33e";
                }
                return "Terratec" + str;
            case 2638:
                return "Steinberg" + str;
            case 2663:
                if (i2 == 20497) {
                    return "Fame HD 1000";
                }
                return "Medeli Electronics" + str;
            case 2706:
                if (i2 == 4096) {
                    return "MIDI Mate";
                }
                if (i2 == 4240) {
                    return "KeyControl49";
                }
                if (i2 == 4256) {
                    return "KeyControl25";
                }
                return "AudioTrak" + str;
            case 3649:
                return "Line6" + str;
            case 5015:
                if (i2 == 188) {
                    return "Behringer BCF2000";
                }
                if (i2 == 436) {
                    return "Behringer UMA25S";
                }
                return "Behringer" + str;
            case 5040:
                if (i2 == 10) {
                    return "Alesis Photon X25";
                }
                break;
            case 5042:
                break;
            case 5578:
                if (i2 == 101 || i2 == 1806) {
                    return "Textech MIDI cable";
                }
                return "Textech" + str;
            case 5636:
                return "Tascam" + str;
            case 6790:
                if (((double) i2) == 752.0d) {
                    return "CH345 MIDI adapter";
                }
                return "QinHeng" + str;
            case 7447:
                if (i2 == 1) {
                    return "AXiS-49";
                }
                return "C-Thru Music Ltd." + str;
            case 18258:
                if (i2 == 17) {
                    return "Midistart-2";
                }
                return "Miditech" + str;
            case 28932:
                return "CME" + str;
            default:
                return "Class compl. MIDI" + str;
        }
        return "Alesis" + str;
    }

    /* renamed from: de.humatic.nmj.x$a */
    class C0506a extends Thread {

        /* renamed from: a */
        private int f596a;
        /* access modifiers changed from: private */

        /* renamed from: a */
        public UsbDevice f597a;

        /* renamed from: a */
        private Vector<C0504x> f599a;

        /* renamed from: a */
        private boolean f600a;

        /* renamed from: a */
        private byte[] f601a;

        /* renamed from: a */
        private int[] f602a;

        /* renamed from: a */
        private boolean[] f603a;

        /* renamed from: a */
        private byte[][] f604a;

        /* renamed from: b */
        private int f605b;

        /* renamed from: c */
        private int f606c;

        private C0506a(UsbDevice usbDevice) {
            this.f599a = new Vector<>();
            this.f600a = true;
            this.f596a = 32;
            this.f605b = 1;
            this.f606c = 20;
            this.f597a = usbDevice;
        }

        /* synthetic */ C0506a(C0504x xVar, UsbDevice usbDevice, byte b) {
            this(usbDevice);
        }

        public final void run() {
            int i;
            int i2;
            try {
                C0504x.this.f587a = C0504x.m532a(C0504x.this).openDevice(this.f597a);
                UsbInterface usbInterface = null;
                if (C0504x.m532a(C0504x.this).claimInterface(this.f597a.getInterface(1), true)) {
                    usbInterface = this.f597a.getInterface(1);
                    i = usbInterface.getEndpointCount();
                } else if (this.f597a.getInterfaceCount() <= 2) {
                    C0504x.this.mo8031a();
                    NMJConfig.m60a(C0504x.this.f575c, (int) NMJConfig.E_USB, new StringBuilder("USB - Failed to claim interface 1").toString());
                    return;
                } else {
                    i = 0;
                }
                if (i == 0) {
                    C0484p.logln(1, "USB - No endpoints on interface 1");
                    try {
                        C0504x.m532a(C0504x.this).releaseInterface(usbInterface);
                    } catch (Exception e) {
                    }
                    if (this.f597a.getInterfaceCount() > 2) {
                        int i3 = 1;
                        while (i3 < this.f597a.getInterfaceCount()) {
                            i3++;
                            if (C0504x.m532a(C0504x.this).claimInterface(this.f597a.getInterface(i3), true)) {
                                usbInterface = this.f597a.getInterface(i3);
                                i = usbInterface.getEndpointCount();
                                if (i != 0 && usbInterface.getInterfaceClass() == 1) {
                                    if (usbInterface.getInterfaceSubclass() == 3) {
                                        break;
                                    }
                                }
                            } else {
                                C0484p.logln(1, "USBMidi - failed to claim interface " + i3 + " for " + this.f597a);
                            }
                        }
                    }
                    if (i == 0) {
                        C0504x.this.mo8031a();
                        NMJConfig.m60a(C0504x.this.f575c, (int) NMJConfig.E_USB, "USB - No endpoints");
                        return;
                    }
                }
                UsbInterface usbInterface2 = usbInterface;
                int i4 = i;
                UsbInterface usbInterface3 = usbInterface2;
                C0484p.logln(2, "USBIO - Interface aquired");
                C0484p.logln(2, "class " + usbInterface3.getInterfaceClass() + " subclass " + usbInterface3.getInterfaceSubclass() + " protocol " + usbInterface3.getInterfaceProtocol());
                if (usbInterface3.getInterfaceClass() == 1 && usbInterface3.getInterfaceSubclass() == 3) {
                    C0484p.logln(2, "num endpoints " + i4);
                    if (Build.VERSION.SDK_INT >= 13) {
                        byte[] rawDescriptors = C0504x.m532a(C0504x.this).getRawDescriptors();
                        C0484p.m370a(5, "Raw descriptors:", rawDescriptors);
                        this.f596a = C0504x.mo8034a(rawDescriptors);
                        int[] a = C0504x.mo8034a(rawDescriptors);
                        if (a[0] <= 8 || a[1] <= 8) {
                            this.f605b = Math.max(a[0], a[1]);
                        } else {
                            this.f605b = Math.min(a[0], a[1]);
                        }
                        C0484p.logln(2, "nr. ports: " + this.f605b + ", max pkt size " + this.f596a);
                    }
                    for (int i5 = 0; i5 < usbInterface3.getEndpointCount(); i5++) {
                        if (usbInterface3.getEndpoint(i5).getType() == 2) {
                            if (usbInterface3.getEndpoint(i5).getDirection() == 128) {
                                C0504x.this.f588a = usbInterface3.getEndpoint(i5);
                                if (Build.VERSION.SDK_INT < 13) {
                                    this.f596a = usbInterface3.getEndpoint(i5).getMaxPacketSize();
                                    if (this.f596a > 32) {
                                        this.f605b = this.f596a / 32;
                                    }
                                }
                            } else {
                                C0504x.this.f592b = usbInterface3.getEndpoint(i5);
                            }
                        }
                    }
                    C0484p.logln(2, "Input: " + C0504x.this.f588a + "\nOutput: " + C0504x.this.f592b);
                    NMJConfig.m59a(C0504x.this.f575c, 4, 32);
                    try {
                        this.f601a = new byte[this.f596a];
                        this.f604a = new byte[this.f605b][];
                        this.f603a = new boolean[this.f605b];
                        this.f602a = new int[this.f605b];
                        while (!C0504x.this.f573a) {
                            try {
                                int bulkTransfer = C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f588a, this.f601a, this.f596a, 500);
                                if (bulkTransfer >= 0) {
                                    if (bulkTransfer > 0 || (this.f601a[0] & 15) > 2) {
                                        if ((this.f601a[0] & 15) != 15 || bulkTransfer > 4 || !this.f600a || (this.f601a[1] & 255) != 254) {
                                            int i6 = 0;
                                            while (true) {
                                                if (bulkTransfer == 0) {
                                                    i2 = 4;
                                                } else {
                                                    i2 = bulkTransfer;
                                                }
                                                if (i6 < i2) {
                                                    int i7 = this.f601a[i6] >> 4;
                                                    if ((this.f601a[i6] & 15) <= 7 || (this.f601a[i6] & 15) >= 15) {
                                                        int i8 = this.f601a[i6] >> 4;
                                                        switch (this.f601a[i6] & 15) {
                                                            case 3:
                                                                System.arraycopy(this.f601a, i6 + 1, C0504x.m532a(C0504x.this), 0, 3);
                                                                Iterator<C0504x> it = this.f599a.iterator();
                                                                while (it.hasNext()) {
                                                                    C0504x next = it.next();
                                                                    if (next.f584a == i8) {
                                                                        next.f572a.mo8155a(C0504x.m532a(C0504x.this), 3);
                                                                    }
                                                                }
                                                                break;
                                                            case 4:
                                                                if (this.f603a[i8]) {
                                                                    if (this.f602a[i8] > this.f604a[i8].length - 4) {
                                                                        m569a(i8, -1);
                                                                    }
                                                                    if ((this.f601a[i6 + 1] & 255) != 240) {
                                                                        System.arraycopy(this.f601a, i6 + 1, this.f604a[i8], this.f602a[i8], 3);
                                                                        int[] iArr = this.f602a;
                                                                        iArr[i8] = iArr[i8] + 3;
                                                                        break;
                                                                    } else {
                                                                        System.arraycopy(this.f601a, i6 + 2, this.f604a[i8], this.f602a[i8], 2);
                                                                        int[] iArr2 = this.f602a;
                                                                        iArr2[i8] = iArr2[i8] + 2;
                                                                        break;
                                                                    }
                                                                } else {
                                                                    if (this.f604a[i8] == null) {
                                                                        this.f604a[i8] = new byte[4096];
                                                                    }
                                                                    this.f603a[i8] = true;
                                                                    System.arraycopy(this.f601a, i6 + 1, this.f604a[i8], 0, 3);
                                                                    this.f602a[i8] = 3;
                                                                    break;
                                                                }
                                                            case 5:
                                                                if (this.f603a[i8] && (this.f601a[i6 + 1] & 255) == 247) {
                                                                    byte[] bArr = this.f604a[i8];
                                                                    int[] iArr3 = this.f602a;
                                                                    int i9 = iArr3[i8];
                                                                    iArr3[i8] = i9 + 1;
                                                                    bArr[i9] = -9;
                                                                    Iterator<C0504x> it2 = this.f599a.iterator();
                                                                    while (it2.hasNext()) {
                                                                        C0504x next2 = it2.next();
                                                                        if (next2.f584a == i8) {
                                                                            next2.f572a.mo8155a(this.f604a[i8], this.f602a[i8]);
                                                                        }
                                                                    }
                                                                    this.f603a[i8] = false;
                                                                    break;
                                                                }
                                                            case 6:
                                                                if (this.f602a[i8] > this.f604a[i8].length - 4) {
                                                                    m569a(i8, 1);
                                                                }
                                                                if ((this.f601a[i6 + 1] & 255) == 240) {
                                                                    byte[] bArr2 = this.f604a[i8];
                                                                    int[] iArr4 = this.f602a;
                                                                    int i10 = iArr4[i8];
                                                                    iArr4[i8] = i10 + 1;
                                                                    bArr2[i10] = -9;
                                                                } else {
                                                                    byte[] bArr3 = this.f604a[i8];
                                                                    int[] iArr5 = this.f602a;
                                                                    int i11 = iArr5[i8];
                                                                    iArr5[i8] = i11 + 1;
                                                                    bArr3[i11] = this.f601a[i6 + 1];
                                                                    byte[] bArr4 = this.f604a[i8];
                                                                    int[] iArr6 = this.f602a;
                                                                    int i12 = iArr6[i8];
                                                                    iArr6[i8] = i12 + 1;
                                                                    bArr4[i12] = -9;
                                                                }
                                                                Iterator<C0504x> it3 = this.f599a.iterator();
                                                                while (it3.hasNext()) {
                                                                    C0504x next3 = it3.next();
                                                                    if (next3.f584a == i8) {
                                                                        next3.f572a.mo8155a(this.f604a[i8], this.f602a[i8]);
                                                                    }
                                                                }
                                                                this.f603a[i8] = false;
                                                                break;
                                                            case 7:
                                                                if (this.f602a[i8] > this.f604a[i8].length - 4) {
                                                                    m569a(i8, 2);
                                                                }
                                                                if ((this.f601a[i6 + 1] & 255) == 240) {
                                                                    byte[] bArr5 = this.f604a[i8];
                                                                    int[] iArr7 = this.f602a;
                                                                    int i13 = iArr7[i8];
                                                                    iArr7[i8] = i13 + 1;
                                                                    bArr5[i13] = this.f601a[i6 + 2];
                                                                    byte[] bArr6 = this.f604a[i8];
                                                                    int[] iArr8 = this.f602a;
                                                                    int i14 = iArr8[i8];
                                                                    iArr8[i8] = i14 + 1;
                                                                    bArr6[i14] = -9;
                                                                } else {
                                                                    byte[] bArr7 = this.f604a[i8];
                                                                    int[] iArr9 = this.f602a;
                                                                    int i15 = iArr9[i8];
                                                                    iArr9[i8] = i15 + 1;
                                                                    bArr7[i15] = this.f601a[i6 + 1];
                                                                    byte[] bArr8 = this.f604a[i8];
                                                                    int[] iArr10 = this.f602a;
                                                                    int i16 = iArr10[i8];
                                                                    iArr10[i8] = i16 + 1;
                                                                    bArr8[i16] = this.f601a[i6 + 2];
                                                                    byte[] bArr9 = this.f604a[i8];
                                                                    int[] iArr11 = this.f602a;
                                                                    int i17 = iArr11[i8];
                                                                    iArr11[i8] = i17 + 1;
                                                                    bArr9[i17] = -9;
                                                                }
                                                                Iterator<C0504x> it4 = this.f599a.iterator();
                                                                while (it4.hasNext()) {
                                                                    C0504x next4 = it4.next();
                                                                    if (next4.f584a == i8) {
                                                                        next4.f572a.mo8155a(this.f604a[i8], this.f602a[i8]);
                                                                    }
                                                                }
                                                                this.f603a[i8] = false;
                                                                break;
                                                            case 15:
                                                                if (i8 >= 0) {
                                                                    if (!this.f603a[i8] || (this.f601a[i6 + 1] & 240) >= 128) {
                                                                        if (this.f600a && (this.f601a[i6 + 1] & 255) == 254) {
                                                                            break;
                                                                        } else {
                                                                            C0504x.m532a(C0504x.this)[0] = this.f601a[i6 + 1];
                                                                            Iterator<C0504x> it5 = this.f599a.iterator();
                                                                            while (it5.hasNext()) {
                                                                                C0504x next5 = it5.next();
                                                                                if (next5.f584a == i8) {
                                                                                    next5.f572a.mo8155a(C0504x.m532a(C0504x.this), 1);
                                                                                }
                                                                            }
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        byte[] bArr10 = this.f604a[i8];
                                                                        int[] iArr12 = this.f602a;
                                                                        int i18 = iArr12[i8];
                                                                        iArr12[i8] = i18 + 1;
                                                                        bArr10[i18] = this.f601a[i6 + 1];
                                                                        break;
                                                                    }
                                                                } else {
                                                                    break;
                                                                }
                                                        }
                                                    } else {
                                                        int b = C0484p.m395b(this.f601a, i6 + 1) + 1;
                                                        System.arraycopy(this.f601a, i6 + 1, C0504x.m532a(C0504x.this), 0, b);
                                                        Iterator<C0504x> it6 = this.f599a.iterator();
                                                        while (it6.hasNext()) {
                                                            C0504x next6 = it6.next();
                                                            if (next6.f584a == i7) {
                                                                next6.f572a.mo8155a(C0504x.m532a(C0504x.this), b);
                                                            }
                                                        }
                                                    }
                                                    i6 += 4;
                                                }
                                            }
                                        }
                                    }
                                    this.f601a[0] = 0;
                                }
                            } catch (Exception e2) {
                                if (e2.toString().indexOf("closed") < 0) {
                                    e2.printStackTrace();
                                }
                                if (!(e2 instanceof IndexOutOfBoundsException)) {
                                    return;
                                }
                            }
                        }
                    } catch (Exception e3) {
                    }
                } else {
                    C0504x.this.mo8031a();
                    NMJConfig.m60a(C0504x.this.f575c, (int) NMJConfig.E_USB, "USB - Not a class-compliant MIDI device");
                }
            } catch (Exception e4) {
                C0484p.m377a(e4, "");
                if (!C0504x.this.f573a) {
                    NMJConfig.m60a(C0504x.this.f575c, (int) NMJConfig.E_USB, e4.toString());
                }
            }
        }

        /* renamed from: a */
        private void m569a(int i, int i2) {
            byte[] bArr = new byte[this.f604a[i].length];
            System.arraycopy(this.f604a[i], 0, bArr, 0, bArr.length);
            this.f604a[i] = new byte[(i2 < 0 ? bArr.length << 1 : bArr.length + i2)];
            System.arraycopy(bArr, 0, this.f604a[i], 0, bArr.length);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public final void mo8212a(int i, byte[] bArr) throws IOException {
            if (i > this.f605b - 1) {
                C0484p.logln(3, "USB_HOST " + C0504x.this.f575c + " invalid cable ID " + i);
                i = 0;
            }
            if ((bArr[0] & 240) < 240) {
                C0504x.m552b(C0504x.this)[0] = (byte) (((bArr[0] & 240) >> 4) | (i << 4));
                int b = C0484p.m395b(bArr, 0) + 1;
                System.arraycopy(bArr, 0, C0504x.m552b(C0504x.this), 1, b);
                if (b < 3) {
                    for (int i2 = b + 1; i2 < 4; i2++) {
                        C0504x.m552b(C0504x.this)[i2] = 0;
                    }
                }
                int bulkTransfer = C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                if (bulkTransfer < 0) {
                    C0484p.logln(3, "USB_HOST " + C0504x.this.f575c + " bulkTransfer returned " + bulkTransfer);
                    return;
                }
                return;
            }
            switch (bArr[0] & 240) {
                case 240:
                    int i3 = 0;
                    while (i3 + 3 < bArr.length) {
                        C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 4);
                        System.arraycopy(bArr, i3, C0504x.m552b(C0504x.this), 1, 3);
                        C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                        i3 += 3;
                    }
                    switch (bArr.length - i3) {
                        case 1:
                            C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 5);
                            C0504x.m552b(C0504x.this)[1] = -9;
                            C0504x.m552b(C0504x.this)[2] = 0;
                            C0504x.m552b(C0504x.this)[3] = 0;
                            C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                            return;
                        case 2:
                            C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 6);
                            C0504x.m552b(C0504x.this)[1] = bArr[i3];
                            C0504x.m552b(C0504x.this)[2] = -9;
                            C0504x.m552b(C0504x.this)[3] = 0;
                            C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                            return;
                        case 3:
                            C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 7);
                            C0504x.m552b(C0504x.this)[1] = bArr[i3];
                            C0504x.m552b(C0504x.this)[2] = bArr[i3 + 1];
                            C0504x.m552b(C0504x.this)[3] = -9;
                            C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                            return;
                        default:
                            return;
                    }
                case 241:
                case 243:
                    C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 2);
                    System.arraycopy(bArr, 0, C0504x.m552b(C0504x.this), 1, 2);
                    C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                    return;
                case 242:
                    C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 3);
                    System.arraycopy(bArr, 0, C0504x.m552b(C0504x.this), 1, 3);
                    C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                    return;
                case 244:
                case 245:
                case 246:
                    C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 5);
                    C0504x.m552b(C0504x.this)[1] = bArr[0];
                    C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                    return;
                case 248:
                case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                case 251:
                case 252:
                    C0504x.m552b(C0504x.this)[0] = (byte) ((i << 4) | 15);
                    C0504x.m552b(C0504x.this)[1] = bArr[0];
                    C0504x.m532a(C0504x.this).bulkTransfer(C0504x.this.f592b, C0504x.m552b(C0504x.this), 4, this.f606c);
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        static /* synthetic */ void m570a(C0506a aVar, C0504x xVar) {
            if (aVar.f599a.contains(xVar)) {
                aVar.f599a.remove(xVar);
            }
            if (aVar.f599a.size() == 0) {
                try {
                    C0504x.this.f573a = true;
                    C0504x.m532a(C0504x.this).close();
                    C0504x.mo8031a().remove(C0504x.m540a(C0504x.this, C0504x.m532a(C0504x.this)));
                    C0504x.m540a(C0504x.this, (UsbDevice) null);
                    C0484p.logln(2, "USB IOThread closed");
                } catch (Exception e) {
                }
            }
        }

        /* renamed from: b */
        static /* synthetic */ void m571b(C0506a aVar, C0504x xVar) {
            if (!aVar.f599a.contains(xVar)) {
                aVar.f599a.add(xVar);
                C0484p.logln(3, "USBIO - client added " + xVar + " " + xVar.f584a);
            }
        }
    }
}
