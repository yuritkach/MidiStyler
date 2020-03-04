package p004de.humatic.nmj;

import java.lang.reflect.Array;
import java.util.Vector;

/* renamed from: de.humatic.nmj.l */
final class C0477l {

    /* renamed from: a */
    private byte f342a;

    /* renamed from: a */
    private int f343a;

    /* renamed from: a */
    private long f344a;

    /* renamed from: a */
    private C0466f f345a;

    /* renamed from: a */
    private Vector f346a;

    /* renamed from: a */
    private byte[] f347a;

    /* renamed from: a */
    private byte[][] f348a = new byte[70][];

    /* renamed from: b */
    private int f349b;

    /* renamed from: b */
    private long f350b = -1;

    /* renamed from: b */
    private Vector<byte[]> f351b;

    /* renamed from: b */
    private byte[][] f352b = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 1}));

    /* renamed from: c */
    private int f353c = ((int) (Math.random() * 65536.0d));

    /* renamed from: c */
    private Vector<byte[]> f354c;

    /* renamed from: c */
    private byte[][] f355c = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{32, 2}));

    /* renamed from: d */
    private int f356d;

    /* renamed from: d */
    private byte[][] f357d = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{64, 3}));

    /* renamed from: e */
    private int f358e = 256;

    /* renamed from: e */
    private byte[][] f359e;

    /* renamed from: f */
    private int f360f;

    /* renamed from: g */
    private int f361g;

    /* renamed from: h */
    private int f362h;

    /* renamed from: i */
    private int f363i;

    /* renamed from: j */
    private int f364j = -1;

    C0477l(C0466f fVar, int i, int i2, long j) {
        byte[] bArr = new byte[11];
        bArr[0] = -16;
        bArr[1] = Byte.MAX_VALUE;
        bArr[2] = Byte.MAX_VALUE;
        bArr[3] = 1;
        bArr[4] = 1;
        bArr[10] = -9;
        this.f347a = bArr;
        this.f346a = new Vector();
        this.f351b = new Vector<>();
        this.f354c = new Vector<>();
        this.f359e = new byte[1][];
        this.f343a = i;
        this.f349b = i2;
        this.f345a = fVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8148a(byte[] bArr, int i) {
        mo8149a(bArr, 0, i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:214:?, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8149a(byte[] r24, int r25, int r26) {
        /*
            r23 = this;
            int r4 = r25 + 8
            r0 = r24
            int r15 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)
            int r4 = r25 + 2
            byte r4 = r24[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 8
            int r5 = r25 + 3
            byte r5 = r24[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            r4 = r4 | r5
            r0 = r23
            int r5 = r0.f364j
            r6 = -1
            if (r5 != r6) goto L_0x0022
            r0 = r23
            r0.f364j = r4
        L_0x0022:
            int r4 = r25 + 4
            r0 = r24
            int r4 = p004de.humatic.nmj.C0484p.m348a((byte[]) r0, (int) r4)
            r0 = r23
            long r6 = r0.f350b
            r8 = -1
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 != 0) goto L_0x0039
            long r6 = (long) r4
            r0 = r23
            r0.f350b = r6
        L_0x0039:
            long r4 = (long) r4
            r0 = r23
            long r6 = r0.f350b
            long r4 = r4 - r6
            int r0 = (int) r4
            r16 = r0
            r5 = 1
            int r4 = r25 + 12
            byte r4 = r24[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 >> 7
            r6 = 1
            if (r4 != r6) goto L_0x008f
            r5 = 2
            int r4 = r25 + 12
            byte r4 = r24[r4]
            r4 = r4 & 15
            int r4 = r4 << 8
            int r6 = r25 + 13
            byte r6 = r24[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r4 = r4 | r6
            r22 = r4
            r4 = r5
            r5 = r22
        L_0x0063:
            int r6 = r25 + 12
            byte r6 = r24[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 >> 6
            r6 = r6 & 1
            if (r6 == 0) goto L_0x009b
            r6 = 1
        L_0x0070:
            int r7 = r25 + 12
            byte r7 = r24[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r7 = r7 >> 5
            r7 = r7 & 1
            if (r7 == 0) goto L_0x009d
            r7 = 1
        L_0x007d:
            int r8 = r25 + 12
            int r11 = r8 + r4
            int r17 = r11 + r5
            r14 = 0
            r10 = 0
            r8 = r14
        L_0x0086:
            if (r10 >= r5) goto L_0x008c
            r0 = r26
            if (r11 < r0) goto L_0x009f
        L_0x008c:
            if (r6 != 0) goto L_0x049e
        L_0x008e:
            return
        L_0x008f:
            int r4 = r25 + 12
            byte r4 = r24[r4]
            r4 = r4 & 15
            r22 = r4
            r4 = r5
            r5 = r22
            goto L_0x0063
        L_0x009b:
            r6 = 0
            goto L_0x0070
        L_0x009d:
            r7 = 0
            goto L_0x007d
        L_0x009f:
            r4 = 1
            if (r10 > 0) goto L_0x00a4
            if (r7 == 0) goto L_0x04a6
        L_0x00a4:
            byte r8 = r24[r11]
            int r8 = r8 >> 7
            r9 = 1
            if (r8 == r9) goto L_0x0107
            r0 = r24
            int r8 = p004de.humatic.nmj.C0484p.m349a((byte[]) r0, (int) r11, (int) r4)
            r9 = 2139062143(0x7f7f7f7f, float:3.3961514E38)
            r8 = r8 & r9
            int r11 = r11 + r4
            int r10 = r10 + r4
            r14 = r8
        L_0x00b8:
            int r4 = r26 + -1
            if (r11 > r4) goto L_0x008c
            r8 = 1
            r4 = 0
            byte r9 = r24[r11]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 >> 7
            r12 = 1
            if (r9 != r12) goto L_0x010a
            byte r9 = r24[r11]
            r0 = r23
            r0.f342a = r9
            int r11 = r11 + 1
            r8 = 2
            int r10 = r10 + 1
        L_0x00d2:
            int r12 = r16 + r14
            int r12 = r12 / 10
            long r0 = (long) r12
            r18 = r0
            r12 = r9 & 255(0xff, float:3.57E-43)
            r13 = 240(0xf0, float:3.36E-43)
            if (r12 == r13) goto L_0x0250
            r12 = r9 & 255(0xff, float:3.57E-43)
            r13 = 247(0xf7, float:3.46E-43)
            if (r12 == r13) goto L_0x0250
            r4 = r9 & 255(0xff, float:3.57E-43)
            r8 = 240(0xf0, float:3.36E-43)
            if (r4 >= r8) goto L_0x0112
            r4 = r9 & 255(0xff, float:3.57E-43)
            r8 = 192(0xc0, float:2.69E-43)
            if (r4 < r8) goto L_0x0110
            r4 = r9 & 255(0xff, float:3.57E-43)
            r8 = 224(0xe0, float:3.14E-43)
            if (r4 >= r8) goto L_0x0110
            r4 = 1
        L_0x00f8:
            if (r4 < 0) goto L_0x008c
            int r8 = r11 + r4
            r0 = r24
            int r12 = r0.length
            if (r8 >= r12) goto L_0x008c
            int r10 = r10 + r4
            switch(r4) {
                case 0: goto L_0x0132;
                case 1: goto L_0x016d;
                case 2: goto L_0x01d5;
                default: goto L_0x0105;
            }
        L_0x0105:
            r8 = r14
            goto L_0x0086
        L_0x0107:
            int r4 = r4 + 1
            goto L_0x00a4
        L_0x010a:
            r4 = 1
            r0 = r23
            byte r9 = r0.f342a
            goto L_0x00d2
        L_0x0110:
            r4 = 2
            goto L_0x00f8
        L_0x0112:
            r4 = r9 & 255(0xff, float:3.57E-43)
            r8 = 240(0xf0, float:3.36E-43)
            if (r4 <= r8) goto L_0x0130
            r4 = r9 & 255(0xff, float:3.57E-43)
            r8 = 242(0xf2, float:3.39E-43)
            if (r4 != r8) goto L_0x0120
            r4 = 2
            goto L_0x00f8
        L_0x0120:
            r4 = r9 & 255(0xff, float:3.57E-43)
            r8 = 241(0xf1, float:3.38E-43)
            if (r4 == r8) goto L_0x012c
            r4 = r9 & 255(0xff, float:3.57E-43)
            r8 = 243(0xf3, float:3.4E-43)
            if (r4 != r8) goto L_0x012e
        L_0x012c:
            r4 = 1
            goto L_0x00f8
        L_0x012e:
            r4 = 0
            goto L_0x00f8
        L_0x0130:
            r4 = -1
            goto L_0x00f8
        L_0x0132:
            r0 = r23
            int r4 = r0.f360f
            int r4 = r4 + 1
            r0 = r23
            r0.f360f = r4
            r0 = r23
            int r4 = r0.f360f
            r0 = r23
            byte[][] r8 = r0.f352b
            int r8 = r8.length
            if (r4 < r8) goto L_0x014c
            r4 = 0
            r0 = r23
            r0.f360f = r4
        L_0x014c:
            r0 = r23
            byte[][] r4 = r0.f352b
            r0 = r23
            int r8 = r0.f360f
            r4 = r4[r8]
            r8 = 0
            r4[r8] = r9
            r0 = r23
            byte[][] r4 = r0.f352b
            r0 = r23
            int r8 = r0.f360f
            r4 = r4[r8]
            r0 = r23
            r1 = r18
            r0.m317a((int) r15, (byte[]) r4, (long) r1)
            r8 = r14
            goto L_0x0086
        L_0x016d:
            int r4 = r11 + 1
            byte r8 = r24[r11]
            r11 = r8 & 128(0x80, float:1.794E-43)
            if (r11 == 0) goto L_0x018c
            r5 = 1
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "RTP - Invalid MIDI at "
            r6.<init>(r7)
            int r4 = r4 + -1
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.String r4 = r4.toString()
            p004de.humatic.nmj.C0484p.logln(r5, r4)
            goto L_0x008e
        L_0x018c:
            r0 = r23
            int r11 = r0.f361g
            int r11 = r11 + 1
            r0 = r23
            r0.f361g = r11
            r0 = r23
            int r11 = r0.f361g
            r0 = r23
            byte[][] r12 = r0.f355c
            int r12 = r12.length
            if (r11 < r12) goto L_0x01a6
            r11 = 0
            r0 = r23
            r0.f361g = r11
        L_0x01a6:
            r0 = r23
            byte[][] r11 = r0.f355c
            r0 = r23
            int r12 = r0.f361g
            r11 = r11[r12]
            r12 = 0
            r11[r12] = r9
            r0 = r23
            byte[][] r9 = r0.f355c
            r0 = r23
            int r11 = r0.f361g
            r9 = r9[r11]
            r11 = 1
            r9[r11] = r8
            r0 = r23
            byte[][] r8 = r0.f355c
            r0 = r23
            int r9 = r0.f361g
            r8 = r8[r9]
            r0 = r23
            r1 = r18
            r0.m317a((int) r15, (byte[]) r8, (long) r1)
            r8 = r14
            r11 = r4
            goto L_0x0086
        L_0x01d5:
            int r4 = r11 + 1
            byte r8 = r24[r11]
            int r11 = r4 + 1
            byte r4 = r24[r4]
            r12 = r8 & 128(0x80, float:1.794E-43)
            if (r12 != 0) goto L_0x01e5
            r12 = r4 & 128(0x80, float:1.794E-43)
            if (r12 == 0) goto L_0x01fc
        L_0x01e5:
            r4 = 1
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "RTP - Invalid MIDI at "
            r5.<init>(r6)
            int r6 = r11 + -1
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            p004de.humatic.nmj.C0484p.logln(r4, r5)
            goto L_0x008e
        L_0x01fc:
            r0 = r23
            int r12 = r0.f362h
            int r12 = r12 + 1
            r0 = r23
            r0.f362h = r12
            r0 = r23
            int r12 = r0.f362h
            r0 = r23
            byte[][] r13 = r0.f357d
            int r13 = r13.length
            if (r12 < r13) goto L_0x0216
            r12 = 0
            r0 = r23
            r0.f362h = r12
        L_0x0216:
            r0 = r23
            byte[][] r12 = r0.f357d
            r0 = r23
            int r13 = r0.f362h
            r12 = r12[r13]
            r13 = 0
            r12[r13] = r9
            r0 = r23
            byte[][] r9 = r0.f357d
            r0 = r23
            int r12 = r0.f362h
            r9 = r9[r12]
            r12 = 1
            r9[r12] = r8
            r0 = r23
            byte[][] r8 = r0.f357d
            r0 = r23
            int r9 = r0.f362h
            r8 = r8[r9]
            r9 = 2
            r8[r9] = r4
            r0 = r23
            byte[][] r4 = r0.f357d
            r0 = r23
            int r8 = r0.f362h
            r4 = r4[r8]
            r0 = r23
            r1 = r18
            r0.m317a((int) r15, (byte[]) r4, (long) r1)
            goto L_0x0105
        L_0x0250:
            int r13 = r17 + -1
            r12 = r9 & 255(0xff, float:3.57E-43)
            r20 = 240(0xf0, float:3.36E-43)
            r0 = r20
            if (r12 != r0) goto L_0x025f
            r12 = r11
        L_0x025b:
            r0 = r17
            if (r12 < r0) goto L_0x02de
        L_0x025f:
            r12 = r13
        L_0x0260:
            int r13 = r12 - r11
            int r13 = r13 + 1
            r0 = r9 & 255(0xff, float:3.57E-43)
            r20 = r0
            r21 = 240(0xf0, float:3.36E-43)
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0302
            if (r4 != 0) goto L_0x0302
            if (r12 <= 0) goto L_0x0302
            r0 = r26
            if (r12 >= r0) goto L_0x0302
            byte r20 = r24[r12]
            r0 = r20
            r0 = r0 & 255(0xff, float:3.57E-43)
            r20 = r0
            r21 = 247(0xf7, float:3.46E-43)
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0302
            int r4 = r13 + 1
            r8 = 70
            if (r4 >= r8) goto L_0x02fd
            int r4 = r13 + 1
            r0 = r23
            byte[][] r8 = r0.f348a
            r8 = r8[r4]
            if (r8 != 0) goto L_0x02a0
            r0 = r23
            byte[][] r8 = r0.f348a
            byte[] r9 = new byte[r4]
            r8[r4] = r9
        L_0x02a0:
            r0 = r23
            byte[][] r8 = r0.f348a
            r4 = r8[r4]
        L_0x02a6:
            r8 = 0
            r9 = -16
            r4[r8] = r9
            r8 = 1
            r0 = r24
            java.lang.System.arraycopy(r0, r11, r4, r8, r13)
            r0 = r23
            r1 = r18
            r0.m317a((int) r15, (byte[]) r4, (long) r1)
        L_0x02b8:
            int r4 = r11 + r13
            r0 = r24
            int r8 = r0.length
            if (r4 <= r8) goto L_0x0499
            r4 = 2
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "oops, next offset beyond length: "
            r5.<init>(r7)
            java.lang.StringBuilder r5 = r5.append(r11)
            java.lang.String r7 = " "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.StringBuilder r5 = r5.append(r13)
            java.lang.String r5 = r5.toString()
            p004de.humatic.nmj.C0484p.logln(r4, r5)
            goto L_0x008c
        L_0x02de:
            r0 = r24
            int r0 = r0.length
            r20 = r0
            int r20 = r20 + -1
            r0 = r20
            if (r12 > r0) goto L_0x008e
            byte r20 = r24[r12]
            r0 = r20
            r0 = r0 & 255(0xff, float:3.57E-43)
            r20 = r0
            r21 = 247(0xf7, float:3.46E-43)
            r0 = r20
            r1 = r21
            if (r0 == r1) goto L_0x0260
            int r12 = r12 + 1
            goto L_0x025b
        L_0x02fd:
            int r4 = r13 + 1
            byte[] r4 = new byte[r4]
            goto L_0x02a6
        L_0x0302:
            r0 = r9 & 255(0xff, float:3.57E-43)
            r18 = r0
            r19 = 240(0xf0, float:3.36E-43)
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x0356
            if (r12 <= 0) goto L_0x0356
            r0 = r26
            if (r12 >= r0) goto L_0x0356
            byte r18 = r24[r12]
            r0 = r18
            r0 = r0 & 255(0xff, float:3.57E-43)
            r18 = r0
            r19 = 240(0xf0, float:3.36E-43)
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x0356
            r4 = 0
        L_0x0325:
            if (r4 < r13) goto L_0x032f
            int r4 = r16 / 10
            long r8 = (long) r4
            r0 = r23
            r0.f344a = r8
            goto L_0x02b8
        L_0x032f:
            int r8 = r11 + r4
            byte r8 = r24[r8]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 240(0xf0, float:3.36E-43)
            if (r8 == r9) goto L_0x0353
            int r8 = r11 + r4
            byte r8 = r24[r8]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 247(0xf7, float:3.46E-43)
            if (r8 == r9) goto L_0x0353
            r0 = r23
            java.util.Vector r8 = r0.f346a
            java.lang.Byte r9 = new java.lang.Byte
            int r12 = r11 + r4
            byte r12 = r24[r12]
            r9.<init>(r12)
            r8.add(r9)
        L_0x0353:
            int r4 = r4 + 1
            goto L_0x0325
        L_0x0356:
            r0 = r9 & 255(0xff, float:3.57E-43)
            r18 = r0
            r19 = 247(0xf7, float:3.46E-43)
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x03a4
            int r18 = r11 + r5
            int r18 = r18 - r8
            r0 = r18
            r1 = r26
            if (r0 >= r1) goto L_0x03a4
            int r18 = r11 + r5
            int r8 = r18 - r8
            byte r8 = r24[r8]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r18 = 240(0xf0, float:3.36E-43)
            r0 = r18
            if (r8 != r0) goto L_0x03a4
            r4 = 0
        L_0x037b:
            if (r4 >= r13) goto L_0x02b8
            int r8 = r11 + r4
            byte r8 = r24[r8]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 240(0xf0, float:3.36E-43)
            if (r8 == r9) goto L_0x03a1
            int r8 = r11 + r4
            byte r8 = r24[r8]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 247(0xf7, float:3.46E-43)
            if (r8 == r9) goto L_0x03a1
            r0 = r23
            java.util.Vector r8 = r0.f346a
            java.lang.Byte r9 = new java.lang.Byte
            int r12 = r11 + r4
            byte r12 = r24[r12]
            r9.<init>(r12)
            r8.add(r9)
        L_0x03a1:
            int r4 = r4 + 1
            goto L_0x037b
        L_0x03a4:
            r8 = r9 & 255(0xff, float:3.57E-43)
            r18 = 247(0xf7, float:3.46E-43)
            r0 = r18
            if (r8 != r0) goto L_0x0422
            if (r12 <= 0) goto L_0x0422
            r0 = r26
            if (r12 >= r0) goto L_0x0422
            byte r8 = r24[r12]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r18 = 244(0xf4, float:3.42E-43)
            r0 = r18
            if (r8 != r0) goto L_0x0422
            r0 = r23
            java.util.Vector r4 = r0.f346a
            int r4 = r4.size()
            int r4 = r4 + r13
            int r4 = r4 + 1
            byte[] r9 = new byte[r4]
            r4 = 0
            r8 = -16
            r9[r4] = r8
            r4 = 0
            r8 = r4
        L_0x03d0:
            r0 = r23
            java.util.Vector r4 = r0.f346a
            int r4 = r4.size()
            if (r8 < r4) goto L_0x03fa
            r4 = 1
            if (r13 != r4) goto L_0x0410
            int r4 = r9.length
            int r4 = r4 + -1
            r8 = -9
            r9[r4] = r8
        L_0x03e4:
            r0 = r23
            java.util.Vector r4 = r0.f346a
            r4.removeAllElements()
            r0 = r23
            long r0 = r0.f344a
            r18 = r0
            r0 = r23
            r1 = r18
            r0.m317a((int) r15, (byte[]) r9, (long) r1)
            goto L_0x02b8
        L_0x03fa:
            int r12 = r8 + 1
            r0 = r23
            java.util.Vector r4 = r0.f346a
            java.lang.Object r4 = r4.get(r8)
            java.lang.Byte r4 = (java.lang.Byte) r4
            byte r4 = r4.byteValue()
            r9[r12] = r4
            int r4 = r8 + 1
            r8 = r4
            goto L_0x03d0
        L_0x0410:
            r0 = r23
            java.util.Vector r4 = r0.f346a
            int r4 = r4.size()
            int r4 = r4 + 1
            int r8 = r13 + -1
            r0 = r24
            java.lang.System.arraycopy(r0, r11, r9, r4, r8)
            goto L_0x03e4
        L_0x0422:
            r8 = r9 & 255(0xff, float:3.57E-43)
            r18 = 247(0xf7, float:3.46E-43)
            r0 = r18
            if (r8 == r0) goto L_0x0440
            r8 = r9 & 255(0xff, float:3.57E-43)
            r9 = 240(0xf0, float:3.36E-43)
            if (r8 != r9) goto L_0x02b8
            if (r4 == 0) goto L_0x02b8
            if (r12 <= 0) goto L_0x02b8
            r0 = r26
            if (r12 >= r0) goto L_0x02b8
            byte r4 = r24[r12]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r8 = 247(0xf7, float:3.46E-43)
            if (r4 != r8) goto L_0x02b8
        L_0x0440:
            r0 = r23
            java.util.Vector r4 = r0.f346a
            int r4 = r4.size()
            int r4 = r4 + r13
            int r4 = r4 + 1
            byte[] r9 = new byte[r4]
            r4 = 0
            r8 = r4
        L_0x044f:
            r0 = r23
            java.util.Vector r4 = r0.f346a
            int r4 = r4.size()
            if (r8 < r4) goto L_0x0483
            r0 = r23
            java.util.Vector r4 = r0.f346a
            int r4 = r4.size()
            int r4 = r4 + 1
            r0 = r24
            java.lang.System.arraycopy(r0, r11, r9, r4, r13)
            r0 = r23
            java.util.Vector r4 = r0.f346a
            r4.removeAllElements()
            r4 = 0
            r8 = -16
            r9[r4] = r8
            r0 = r23
            long r0 = r0.f344a
            r18 = r0
            r0 = r23
            r1 = r18
            r0.m317a((int) r15, (byte[]) r9, (long) r1)
            goto L_0x02b8
        L_0x0483:
            int r12 = r8 + 1
            r0 = r23
            java.util.Vector r4 = r0.f346a
            java.lang.Object r4 = r4.get(r8)
            java.lang.Byte r4 = (java.lang.Byte) r4
            byte r4 = r4.byteValue()
            r9[r12] = r4
            int r4 = r8 + 1
            r8 = r4
            goto L_0x044f
        L_0x0499:
            int r11 = r11 + r13
            int r10 = r10 + r13
            r8 = r14
            goto L_0x0086
        L_0x049e:
            int r4 = r16 / 10
            long r4 = (long) r4
            goto L_0x008e
        L_0x04a3:
            r4 = move-exception
            goto L_0x008e
        L_0x04a6:
            r14 = r8
            goto L_0x00b8
        */
        throw new UnsupportedOperationException("Method not decompiled: p004de.humatic.nmj.C0477l.mo8149a(byte[], int, int):void");
    }

    /* renamed from: a */
    private void m317a(int i, byte[] bArr, long j) {
        this.f345a.mo8125a(bArr, 0, bArr.length, j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8147a(int i) {
        this.f363i = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final byte[][] m322a(byte[] bArr, int i) {
        this.f356d = i;
        this.f351b.removeAllElements();
        this.f354c.removeAllElements();
        int i2 = 0;
        byte b = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= bArr.length || this.f351b.size() >= 256) {
                break;
            }
            if ((bArr[i3] & 128) != 0) {
                b = bArr[i3];
                i2 = C0484p.m395b(bArr, i3);
                i3++;
            }
            if (i2 <= 0) {
                if (i2 != 0) {
                    byte[] bArr2 = new byte[(bArr.length - i3)];
                    bArr2[0] = (byte) b;
                    System.arraycopy(bArr, i3, bArr2, 1, bArr.length - i3);
                    this.f351b.add(bArr2);
                    break;
                } else if (b == 0) {
                    C0484p.m370a(2, "RTP encode - invalid status byte at idx: " + i3, bArr);
                    NMJConfig.m60a(this.f343a, (int) NMJConfig.E_INVALID_DATA, "RTP encode - invalid status byte at idx: " + i3);
                    break;
                } else {
                    this.f351b.add(new byte[]{(byte) b});
                }
            } else if (i3 + i2 > bArr.length) {
                C0484p.m370a(2, "RTP encode - invalid data on input at idx: " + i3, bArr);
                NMJConfig.m60a(this.f343a, (int) NMJConfig.E_INVALID_DATA, "RTP encode - invalid data on input at idx: " + i3);
                break;
            } else {
                byte[] bArr3 = new byte[(i2 + 1)];
                bArr3[0] = (byte) b;
                System.arraycopy(bArr, i3, bArr3, 1, i2);
                this.f351b.add(bArr3);
                i3 += i2;
            }
        }
        if (this.f351b.size() >= 255) {
            C0484p.m370a(2, "potential overflow", bArr);
            NMJConfig.m60a(this.f343a, (int) NMJConfig.E_INVALID_DATA, "RTP encode - overflow");
            while (this.f351b.size() > 1) {
                this.f351b.remove(this.f351b.size() - 1);
            }
        }
        if (this.f351b.size() == 0) {
            return null;
        }
        for (int i4 = 0; i4 < this.f351b.size(); i4++) {
            if ((this.f351b.get(i4)[0] & 255) == 240) {
                m318a(this.f351b.get(i4));
            }
        }
        m316a();
        if (this.f359e.length != this.f354c.size()) {
            this.f359e = new byte[this.f354c.size()][];
        }
        this.f354c.copyInto(this.f359e);
        return this.f359e;
    }

    /* renamed from: a */
    private void m316a() {
        int length;
        byte b;
        int i;
        int i2;
        int i3 = 0;
        byte b2 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.f351b.size(); i5++) {
            if ((this.f351b.get(i5)[0] & 255) != 240) {
                if (i3 > 0) {
                    i4++;
                }
                if ((this.f351b.get(i5)[0] & 255) == b2) {
                    i2 = (this.f351b.get(i5).length - 1) + i4;
                } else {
                    int length2 = i4 + this.f351b.get(i5).length;
                    b2 = this.f351b.get(i5)[0] & 255;
                    i2 = length2;
                }
                i3++;
                i4 = i2;
            }
        }
        if (i4 != 0 && i3 != 0) {
            byte[] bArr = new byte[((i4 > 15 ? 1 : 0) + i4 + 13)];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = 97;
            this.f353c++;
            if (this.f353c > 65535) {
                this.f353c = 0;
            }
            C0484p.m373a(this.f353c, bArr, 2, 2);
            C0484p.m372a(this.f356d, bArr, 4);
            C0484p.m372a(this.f349b, bArr, 8);
            boolean z = i4 > 15 ? true : true;
            if (z) {
                bArr[12] = (byte) i4;
            } else {
                bArr[12] = (byte) ((i4 >> 8) | 128);
                bArr[13] = (byte) i4;
            }
            int i6 = 0;
            byte b3 = 0;
            int i7 = z ? 13 : 14;
            for (int i8 = 0; i8 < this.f351b.size(); i8++) {
                if ((this.f351b.get(i8)[0] & 255) != 240) {
                    if (i6 > 0) {
                        bArr[i7] = 0;
                        i7++;
                    }
                    if ((this.f351b.get(i8)[0] & 255) == b3) {
                        b = b3;
                        length = this.f351b.get(i8).length - 1;
                        i = 1;
                    } else {
                        length = this.f351b.get(i8).length;
                        b = this.f351b.get(i8)[0] & 255;
                        i = 0;
                    }
                    System.arraycopy(this.f351b.get(i8), i, bArr, i7, length);
                    i7 += length;
                    i6++;
                    b3 = b;
                }
            }
            this.f354c.add(bArr);
        }
    }

    /* renamed from: a */
    private void m318a(byte[] bArr) {
        int i;
        int length = (bArr.length / this.f358e) + 1;
        byte[][] bArr2 = new byte[length][];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            int min = Math.min(this.f358e, bArr.length - i2);
            if (length > 1) {
                i = (i3 == 0 || i3 == bArr2.length + -1) ? 1 : 2;
            } else {
                i = 0;
            }
            int i4 = min + i <= 15 ? 13 : 14;
            bArr2[i3] = new byte[(i4 + min + i)];
            bArr2[i3][0] = Byte.MIN_VALUE;
            bArr2[i3][1] = 97;
            this.f353c++;
            if (this.f353c > 65535) {
                this.f353c = 0;
            }
            C0484p.m373a(this.f353c, bArr2[i3], 2, 2);
            C0484p.m372a(this.f356d, bArr2[i3], 4);
            C0484p.m372a(this.f349b, bArr2[i3], 8);
            byte b = i3 == 0 ? (byte) -16 : -9;
            byte b2 = i3 == bArr2.length + -1 ? (byte) -9 : -16;
            if (min + i > 15) {
                bArr2[i3][12] = (byte) (((min + i) >> 8) | 128);
                bArr2[i3][13] = (byte) (min + i);
            } else {
                bArr2[i3][12] = (byte) (min + i);
            }
            System.arraycopy(bArr, i2, bArr2[i3], (i3 == 0 ? 0 : 1) + i4, min);
            if (i3 != 0) {
                bArr2[i3][i4] = b;
            }
            i2 += min;
            if (i3 != bArr2.length - 1) {
                bArr2[i3][i4 + min + (i - 1)] = b2;
            }
            this.f354c.add(bArr2[i3]);
            i3++;
        }
    }
}
