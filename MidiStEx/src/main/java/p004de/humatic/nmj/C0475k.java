package p004de.humatic.nmj;

import android.hardware.usb.UsbDevice;
import android.support.p000v4.view.InputDeviceCompat;
import java.io.IOException;

/* renamed from: de.humatic.nmj.k */
final class C0475k extends C0501u {

    /* renamed from: a */
    private int[] f334a = {26, 52, 49230, 16696};

    /* renamed from: a */
    private byte[][] f335a;

    /* renamed from: c */
    private boolean f336c;

    /* renamed from: c */
    private byte[] f337c = new byte[520];

    /* renamed from: d */
    private byte[] f338d = new byte[3];

    /* renamed from: e */
    private int f339e;

    /* renamed from: f */
    private int f340f;

    C0475k(int i, UsbDevice usbDevice) throws IOException {
        super(i, usbDevice);
        this.f552b = true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8143a(boolean z) throws IOException {
        if (z) {
            mo8206a(64, 0, 0, 2, (byte[]) null);
            mo8206a(64, 0, 1, 2, (byte[]) null);
            mo8206a(64, 0, 2, 2, (byte[]) null);
            mo8206a(64, 2, 0, 2, (byte[]) null);
            mo8206a(64, 3, this.f334a[0], 2, (byte[]) null);
            mo8206a(64, 4, 8, 2, (byte[]) null);
        }
        if (this.f557d == 0) {
            new Thread(new Runnable() {
                public final void run() {
                    try {
                        Thread.sleep(1000);
                        C0475k.this.f548a = true;
                        if (C0475k.this.f557d == 0) {
                            C0475k kVar = C0475k.this;
                            byte[] bArr = new byte[7];
                            bArr[0] = 126;
                            bArr[1] = 3;
                            bArr[2] = 2;
                            bArr[5] = 1;
                            bArr[6] = -25;
                            kVar.mo8208a(bArr, 7);
                        }
                        Thread.sleep(1000);
                        C0475k.this.f548a = false;
                    } catch (Exception e) {
                    }
                }
            }).start();
        } else if (this.f557d == 1) {
            this.f549a = new byte[512];
            this.f553b = new byte[512];
            mo8208a(new byte[]{13}, 1);
            mo8208a("'".getBytes(), 1);
            mo8208a(new String("[\r").getBytes(), 2);
            mo8208a(new String("/1\r").getBytes(), 3);
        } else if (this.f557d == 2) {
            this.f549a = new byte[512];
            this.f553b = new byte[512];
            byte[] bArr = new byte[7];
            bArr[0] = 126;
            bArr[1] = 3;
            bArr[2] = 2;
            bArr[5] = 1;
            bArr[6] = -25;
            mo8208a(bArr, 7);
            byte[] bArr2 = new byte[6];
            bArr2[0] = 126;
            bArr2[1] = 8;
            bArr2[2] = 1;
            bArr2[4] = 1;
            bArr2[5] = -25;
            mo8208a(bArr2, 6);
            this.f335a = new byte[5][];
            for (int i = 0; i < this.f335a.length; i++) {
                int i2 = (32 << i) + 1;
                this.f335a[i] = new byte[(i2 + 5)];
                this.f335a[i][0] = 126;
                this.f335a[i][1] = 6;
                this.f335a[i][2] = (byte) i2;
                this.f335a[i][3] = (byte) (i2 >> 8);
                this.f335a[i][4] = 0;
                this.f335a[i][this.f335a[i].length - 1] = -25;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0044 A[EDGE_INSN: B:110:0x0044->B:15:0x0044 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x011c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] mo8145a(byte[] r12, int r13, int r14) throws java.io.IOException {
        /*
            r11 = this;
            r10 = 13
            r9 = 3
            r8 = 2
            r7 = 1
            r1 = 0
            int r0 = r11.f557d
            if (r0 != 0) goto L_0x007b
            p004de.humatic.nmj.C0484p.m349a((byte[]) r12, (int) r13, (int) r14)
            boolean r0 = r11.f548a
            if (r0 == 0) goto L_0x0075
            byte r0 = r12[r13]
            r2 = 63
            if (r0 != r2) goto L_0x001d
            int r0 = r13 + 1
            byte r0 = r12[r0]
            if (r0 == r10) goto L_0x0031
        L_0x001d:
            byte r0 = r12[r13]
            r2 = 62
            if (r0 != r2) goto L_0x0045
            int r0 = r13 + 1
            byte r0 = r12[r0]
            r2 = 63
            if (r0 != r2) goto L_0x0045
            int r0 = r13 + 2
            byte r0 = r12[r0]
            if (r0 != r10) goto L_0x0045
        L_0x0031:
            r11.f557d = r7
            int r0 = r11.f543a
            int r2 = r11.f557d
            p004de.humatic.nmj.NMJConfig.setPort(r0, r2)
            r11.f548a = r1
            java.lang.String r0 = "Cinetix USB DMX interface detected"
            p004de.humatic.nmj.C0484p.logln(r8, r0)
            r11.mo8143a((boolean) r1)
        L_0x0044:
            return r12
        L_0x0045:
            byte r0 = r12[r13]
            r2 = 126(0x7e, float:1.77E-43)
            if (r0 != r2) goto L_0x0051
            int r0 = r13 + 1
            byte r0 = r12[r0]
            if (r0 == r9) goto L_0x0061
        L_0x0051:
            byte r0 = r12[r13]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r2 = 231(0xe7, float:3.24E-43)
            if (r0 == r2) goto L_0x0061
            int r0 = r13 + 1
            byte r0 = r12[r0]
            r2 = 126(0x7e, float:1.77E-43)
            if (r0 != r2) goto L_0x0044
        L_0x0061:
            r11.f557d = r8
            int r0 = r11.f543a
            int r2 = r11.f557d
            p004de.humatic.nmj.NMJConfig.setPort(r0, r2)
            r11.f548a = r1
            java.lang.String r0 = "Enttec USB DMX Pro detected"
            p004de.humatic.nmj.C0484p.logln(r8, r0)
            r11.mo8143a((boolean) r1)
            goto L_0x0044
        L_0x0075:
            de.humatic.nmj.y r0 = r11.f547a
            r0.mo8216a(r12, r13, r14)
            goto L_0x0044
        L_0x007b:
            int r0 = r11.f557d
            if (r0 != r7) goto L_0x01fb
            r11.f339e = r13
            r0 = r1
        L_0x0082:
            int r2 = r11.f339e
            if (r2 >= r14) goto L_0x0044
        L_0x0086:
            int r2 = r11.f339e
            byte r2 = r12[r2]
            r3 = 62
            if (r2 == r3) goto L_0x0094
            int r2 = r11.f339e
            byte r2 = r12[r2]
            if (r2 != r10) goto L_0x009c
        L_0x0094:
            int r2 = r11.f339e
            int r3 = r2 + 1
            r11.f339e = r3
            if (r2 < r14) goto L_0x017c
        L_0x009c:
            int r2 = r11.f339e
            if (r2 >= r14) goto L_0x0044
        L_0x00a0:
            int r2 = r11.f339e
            byte r2 = r12[r2]
            r3 = 64
            if (r2 >= r3) goto L_0x00b0
            int r2 = r11.f339e
            int r3 = r2 + 1
            r11.f339e = r3
            if (r2 < r14) goto L_0x00a0
        L_0x00b0:
            int r2 = r11.f339e
            if (r2 >= r14) goto L_0x0044
            int r2 = r13 + 1
            byte r3 = r12[r13]
            r4 = 78
            if (r3 != r4) goto L_0x0180
        L_0x00bc:
            int r0 = r11.f339e
            byte r0 = r12[r0]
            r3 = 58
            if (r0 == r3) goto L_0x00cc
            int r0 = r11.f339e
            int r3 = r0 + 1
            r11.f339e = r3
            if (r0 < r14) goto L_0x00bc
        L_0x00cc:
            int r0 = r11.f339e
            if (r0 >= r14) goto L_0x0044
            int r0 = r11.f339e
            int r0 = r0 - r2
            if (r0 != r9) goto L_0x0248
            int r0 = r11.f339e
            int r0 = r0 + -3
            byte r0 = r12[r0]
            r0 = r0 & 15
            int r0 = r0 * 100
            int r0 = r0 + 0
        L_0x00e1:
            int r3 = r11.f339e
            int r3 = r3 - r2
            if (r3 < r8) goto L_0x00f1
            int r3 = r11.f339e
            int r3 = r3 + -2
            byte r3 = r12[r3]
            r3 = r3 & 15
            int r3 = r3 * 10
            int r0 = r0 + r3
        L_0x00f1:
            int r3 = r11.f339e
            int r2 = r3 - r2
            if (r2 <= 0) goto L_0x0100
            int r2 = r11.f339e
            int r2 = r2 + -1
            byte r2 = r12[r2]
            r2 = r2 & 15
            int r0 = r0 + r2
        L_0x0100:
            if (r0 <= 0) goto L_0x0104
            int r0 = r0 + -1
        L_0x0104:
            int r2 = r11.f339e
            int r2 = r2 + 1
        L_0x0108:
            int r3 = r11.f339e
            byte r3 = r12[r3]
            r4 = 42
            if (r3 == r4) goto L_0x0118
            int r3 = r11.f339e
            int r4 = r3 + 1
            r11.f339e = r4
            if (r3 < r14) goto L_0x0108
        L_0x0118:
            int r3 = r11.f339e
            if (r3 >= r14) goto L_0x0044
            int r3 = r11.f339e
            int r3 = r3 - r2
            if (r3 != r9) goto L_0x0245
            int r3 = r11.f339e
            int r3 = r3 + -3
            byte r3 = r12[r3]
            r3 = r3 & 15
            int r3 = r3 * 100
            int r3 = r3 + 0
        L_0x012d:
            int r4 = r11.f339e
            int r4 = r4 - r2
            if (r4 < r8) goto L_0x013d
            int r4 = r11.f339e
            int r4 = r4 + -2
            byte r4 = r12[r4]
            r4 = r4 & 15
            int r4 = r4 * 10
            int r3 = r3 + r4
        L_0x013d:
            int r4 = r11.f339e
            int r2 = r4 - r2
            if (r2 <= 0) goto L_0x014c
            int r2 = r11.f339e
            int r2 = r2 + -1
            byte r2 = r12[r2]
            r2 = r2 & 15
            int r3 = r3 + r2
        L_0x014c:
            int r2 = r11.f339e
            int r13 = r2 + 1
            byte[] r2 = r11.f549a
            byte r2 = r2[r0]
            byte r4 = (byte) r3
            if (r2 == r4) goto L_0x0082
            byte[] r2 = r11.f549a
            byte r4 = (byte) r3
            r2[r0] = r4
            byte[] r2 = r11.f338d
            int r4 = r0 / 128
            r4 = r4 | 176(0xb0, float:2.47E-43)
            byte r4 = (byte) r4
            r2[r1] = r4
            byte[] r2 = r11.f338d
            int r4 = r0 % 128
            byte r4 = (byte) r4
            r2[r7] = r4
            byte[] r2 = r11.f338d
            int r3 = r3 / 2
            byte r3 = (byte) r3
            r2[r8] = r3
            de.humatic.nmj.y r2 = r11.f547a
            byte[] r3 = r11.f338d
            r2.mo8216a(r3, r1, r9)
            goto L_0x0082
        L_0x017c:
            int r13 = r13 + 1
            goto L_0x0086
        L_0x0180:
            r4 = 82
            if (r3 != r4) goto L_0x01a8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Cinetix, Firmware revision R"
            r3.<init>(r4)
            int r4 = r2 + 1
            byte r2 = r12[r2]
            r2 = r2 & 15
            java.lang.StringBuilder r3 = r3.append(r2)
            int r2 = r4 + 1
            byte r4 = r12[r4]
            r4 = r4 & 15
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            p004de.humatic.nmj.C0484p.logln(r7, r3)
            goto L_0x0108
        L_0x01a8:
            r4 = 100
            if (r3 != r4) goto L_0x01d4
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Cinetix, Firmware version: "
            r3.<init>(r4)
            byte r4 = r12[r2]
            r4 = r4 & 15
            java.lang.StringBuilder r3 = r3.append(r4)
            int r4 = r2 + 1
            byte r4 = r12[r4]
            r4 = r4 & 15
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            p004de.humatic.nmj.C0484p.logln(r7, r3)
            int r3 = r2 + 5
            r11.f339e = r3
            int r13 = r2 + 5
            goto L_0x0082
        L_0x01d4:
            r4 = 68
            if (r3 != r4) goto L_0x0108
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Cinetix, merge mode: "
            r3.<init>(r4)
            java.lang.String r4 = new java.lang.String
            int r5 = r2 + -1
            int r6 = r2 + 5
            r4.<init>(r12, r5, r6)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            p004de.humatic.nmj.C0484p.logln(r7, r3)
            int r3 = r2 + 6
            r11.f339e = r3
            int r13 = r2 + 6
            goto L_0x0082
        L_0x01fb:
            int r0 = r11.f557d
            if (r0 != r8) goto L_0x0044
        L_0x01ff:
            if (r13 >= r14) goto L_0x0044
            byte r0 = r12[r13]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r2 = 126(0x7e, float:1.77E-43)
            if (r0 != r2) goto L_0x0216
            r11.f336c = r7
            byte[] r0 = r11.f337c
            byte r2 = r12[r13]
            r0[r1] = r2
            r11.f339e = r7
        L_0x0213:
            int r13 = r13 + 1
            goto L_0x01ff
        L_0x0216:
            byte r0 = r12[r13]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r2 = 231(0xe7, float:3.24E-43)
            if (r0 != r2) goto L_0x0234
            r11.f336c = r1
            byte[] r0 = r11.f337c
            int r2 = r11.f339e
            int r3 = r2 + 1
            r11.f339e = r3
            byte r3 = r12[r13]
            r0[r2] = r3
            byte[] r0 = r11.f337c
            int r2 = r11.f339e
            r11.m312b(r0, r2)
            goto L_0x0213
        L_0x0234:
            boolean r0 = r11.f336c
            if (r0 == 0) goto L_0x0213
            byte[] r0 = r11.f337c
            int r2 = r11.f339e
            int r3 = r2 + 1
            r11.f339e = r3
            byte r3 = r12[r13]
            r0[r2] = r3
            goto L_0x0213
        L_0x0245:
            r3 = r1
            goto L_0x012d
        L_0x0248:
            r0 = r1
            goto L_0x00e1
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0475k.mo8145a(byte[], int, int):byte[]");
    }

    /* renamed from: b */
    private void m312b(byte[] bArr, int i) {
        byte b;
        int i2;
        switch (bArr[1]) {
            case 3:
                C0484p.logln(2, "ENTTEC USB DMX pro:\nFirmware version: " + (bArr[4] | (bArr[5] << 8)) + "\nDMX output breaktime: " + (((float) bArr[6]) * 10.67f) + " msec\nDMX output mark: " + (((float) bArr[7]) * 10.67f) + " msec\nDMX output rate: " + bArr[8] + " packets/sec\n");
                return;
            case 5:
            case 9:
                int i3 = 2;
                int i4 = 0;
                while (i3 < i) {
                    int i5 = i3 + 1;
                    byte b2 = bArr[i3] & 255;
                    i3 = i5 + 1;
                    byte b3 = b2 | ((bArr[i5] & 255) << 8);
                    if (i3 + b3 >= i) {
                        return;
                    }
                    if (bArr[1] == 9) {
                        int i6 = i3 + 1;
                        int i7 = (bArr[i3] & 255) << 3;
                        int i8 = 0;
                        while (true) {
                            int i9 = i8;
                            int i10 = i6;
                            if (i9 >= 5) {
                                int i11 = i10 + 1;
                                i3 = i10 + 3;
                                i4 = i11;
                            } else {
                                byte b4 = bArr[i4 + 5 + i9] & 255;
                                int i12 = 0;
                                int i13 = i10 + 1;
                                int i14 = 0;
                                while (i14 < 8) {
                                    if (((1 << i14) & b4) != 0) {
                                        i2 = i12 + 1;
                                        byte b5 = bArr[i12 + i4 + 10] & 255;
                                        int i15 = (i9 << 3) + i7 + i14;
                                        if (!(i15 >= 512 || this.f549a[i15] == ((byte) b5) || (b5 % 8 == 0 && this.f549a[i15] % 8 == 0))) {
                                            this.f549a[i15] = (byte) b5;
                                            this.f338d[0] = (byte) ((i15 / 128) | 176);
                                            this.f338d[1] = (byte) (i15 % 128);
                                            this.f338d[2] = (byte) (b5 / 2);
                                            this.f547a.mo8216a(this.f338d, 0, 3);
                                        }
                                    } else {
                                        i2 = i12;
                                    }
                                    i14++;
                                    i12 = i2;
                                }
                                i6 = i12 + i13;
                                i8 = i9 + 1;
                            }
                        }
                    } else if (bArr[1] == 5) {
                        int i16 = i3 + 1;
                        if (bArr[i3] == 0) {
                            int i17 = i16 + 1;
                            int i18 = (bArr[i16] & 255) << 3;
                            for (int i19 = i17; i19 < (i17 + b3) - 1; i19++) {
                                int i20 = (i19 - i17) + i18;
                                if (i20 <= this.f549a.length - 1 && this.f549a[i20] != (b = bArr[i19])) {
                                    this.f549a[i20] = b;
                                    this.f338d[0] = (byte) ((i20 / 128) | 176);
                                    this.f338d[1] = (byte) (i20 % 128);
                                    this.f338d[2] = (byte) (b / 2);
                                    this.f547a.mo8216a(this.f338d, 0, 3);
                                }
                            }
                            i3 = i;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final byte[] mo8144a(byte[] bArr) {
        byte b;
        int i = 0;
        if (this.f557d == 1) {
            if ((bArr[0] & 15) > 3) {
                return null;
            }
            switch (bArr[0] & 255) {
                case 128:
                    bArr[2] = 0;
                    break;
                case 144:
                case 176:
                    break;
                default:
                    return null;
            }
            int i2 = bArr[1] + ((bArr[0] & 15) << 7);
            if (i2 >= 512) {
                return null;
            }
            if (this.f553b[i2] == (bArr[2] << 1)) {
                return null;
            }
            if (i2 < 256 || i2 == 512) {
                bArr[0] = 2;
                if (i2 != 512) {
                    i = i2 + 1;
                }
                bArr[1] = (byte) i;
                bArr[2] = (byte) (bArr[2] << 1);
            } else {
                bArr[0] = 3;
                bArr[1] = (byte) (i2 + InputDeviceCompat.SOURCE_ANY);
                bArr[2] = (byte) (bArr[2] << 1);
            }
            this.f553b[i2] = bArr[2];
            return bArr;
        } else if (this.f557d != 2) {
            return bArr;
        } else {
            switch (bArr[0] & 240) {
                case 128:
                    if ((bArr[0] & 15) <= 3) {
                        b = 0;
                        break;
                    } else {
                        return null;
                    }
                case 144:
                case 176:
                    if ((bArr[0] & 15) <= 3) {
                        b = (byte) (bArr[2] << 1);
                        break;
                    } else {
                        return null;
                    }
                default:
                    return null;
            }
            int i3 = bArr[1] + ((bArr[0] & 15) << 7);
            if (this.f553b[i3] == b) {
                return null;
            }
            while (true) {
                if (i >= this.f335a.length) {
                    i = -1;
                } else if (i3 >= this.f335a[i].length - 6) {
                    i++;
                }
            }
            if (i != this.f340f) {
                System.arraycopy(this.f335a[this.f340f], 5, this.f335a[i], 5, Math.min(this.f335a[i].length - 6, this.f335a[this.f340f].length - 6));
                this.f340f = i;
            }
            this.f335a[i][i3 + 5] = b;
            this.f553b[i3] = b;
            return this.f335a[i];
        }
    }
}
