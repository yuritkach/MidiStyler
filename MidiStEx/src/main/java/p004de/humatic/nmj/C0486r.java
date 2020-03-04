package p004de.humatic.nmj;

import android.hardware.usb.UsbDevice;
import android.os.Build;
import java.io.IOException;

/* renamed from: de.humatic.nmj.r */
final class C0486r extends C0501u {

    /* renamed from: e */
    private int f435e = 0;

    C0486r(int i, UsbDevice usbDevice) throws IOException {
        super(i, usbDevice);
        int i2;
        if (Build.VERSION.SDK_INT >= 13) {
            byte[] rawDescriptors = this.f545a.getRawDescriptors();
            C0484p.m370a(5, "Prolific, raw descriptors:", rawDescriptors);
            byte b = -1;
            byte b2 = -1;
            int i3 = 0;
            while (i3 < rawDescriptors.length) {
                int i4 = i3 + 1;
                byte b3 = rawDescriptors[i3] & 255;
                int i5 = i4 + 1;
                byte b4 = rawDescriptors[i4] & 255;
                if (b3 <= 2) {
                    break;
                }
                if (b4 == 1) {
                    b2 = rawDescriptors[i5 + 5] & 255;
                    C0484p.logln(3, "   Ctrl mps " + b2);
                } else if (b4 == 5) {
                    b = ((rawDescriptors[i5 + 3] & 255) << 8) | (rawDescriptors[i5 + 2] & 255);
                    C0484p.logln(3, "   Endpoint mps " + b);
                }
                i3 = (b3 - 2) + i5;
            }
            if (b < 0 && b2 < 0) {
                b2 = 4;
            } else if (b >= 0) {
                b2 = b;
            }
            this.f550b = b2;
            C0484p.logln(5, "nr. ports: " + this.f554c + ", max pkt size " + this.f550b);
            if (this.f544a.getDeviceClass() == 2) {
                this.f435e = 1;
            } else if (this.f550b == 64 || !(this.f544a.getDeviceClass() == 0 || this.f544a.getDeviceClass() == 255)) {
                this.f435e = 0;
            } else {
                this.f435e = 2;
            }
        }
        mo8206a(64, 1, 0, 0, (byte[]) null);
        mo8206a(64, 1, 8, 0, (byte[]) null);
        mo8206a(64, 1, 9, 0, (byte[]) null);
        mo8209a(192, 1, 33924, 0, 1);
        mo8206a(64, 1, 1028, 0, (byte[]) null);
        mo8209a(192, 1, 33924, 0, 1);
        mo8209a(192, 1, 33667, 0, 1);
        mo8209a(192, 1, 33924, 0, 1);
        mo8206a(64, 1, 1028, 1, (byte[]) null);
        mo8209a(192, 1, 33924, 0, 1);
        mo8209a(192, 1, 33667, 0, 1);
        mo8206a(64, 1, 0, 1, (byte[]) null);
        mo8206a(64, 1, 1, 0, (byte[]) null);
        if (this.f435e == 0) {
            i2 = 68;
        } else {
            i2 = 36;
        }
        mo8206a(64, 1, 2, i2, (byte[]) null);
    }
}
