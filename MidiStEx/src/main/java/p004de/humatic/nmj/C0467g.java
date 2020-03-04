package p004de.humatic.nmj;

/* renamed from: de.humatic.nmj.g */
final class C0467g {

    /* renamed from: a */
    int f277a;

    /* renamed from: a */
    String f278a;

    /* renamed from: b */
    int f279b;

    /* renamed from: b */
    String f280b;

    /* renamed from: c */
    String f281c;

    /* renamed from: d */
    String f282d;

    /* renamed from: e */
    String f283e;

    protected C0467g(String str, int i) {
        byte[] bArr = {-30, Byte.MIN_VALUE, -103};
        this.f280b = str;
        this.f277a = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8126a(String str) {
        if (str.indexOf(" _") != -1) {
            this.f278a = str.substring(0, str.indexOf(" _"));
        } else {
            this.f278a = str;
        }
    }

    public final boolean equals(Object obj) {
        try {
            C0467g gVar = (C0467g) obj;
            if (gVar.f280b != null && this.f280b != null && !gVar.f280b.equalsIgnoreCase(this.f280b)) {
                return false;
            }
            if (gVar.f281c != null && this.f281c != null && !gVar.f281c.equals(this.f281c)) {
                return false;
            }
            if (gVar.f282d != null && this.f282d != null && !gVar.f282d.equals(this.f282d)) {
                return false;
            }
            if (gVar.f278a != null && this.f278a != null && !gVar.f278a.equals(this.f278a)) {
                return false;
            }
            if ((gVar.f283e == null || this.f283e == null || gVar.f283e.equals(this.f283e)) && gVar.f279b == this.f279b) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("=========================\nName: ");
        stringBuffer.append(this.f278a);
        stringBuffer.append("\nServiceName: ");
        stringBuffer.append(this.f280b);
        stringBuffer.append("\nDomain: ");
        stringBuffer.append(this.f283e == null ? "local" : this.f283e);
        stringBuffer.append("\nIP4: ");
        stringBuffer.append(this.f281c);
        stringBuffer.append("\nIP6: ");
        stringBuffer.append(this.f282d);
        stringBuffer.append("\nPort: ");
        stringBuffer.append(this.f279b);
        stringBuffer.append("\n=========================\n");
        return stringBuffer.toString();
    }
}
