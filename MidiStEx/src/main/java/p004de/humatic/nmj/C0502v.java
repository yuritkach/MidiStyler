package p004de.humatic.nmj;

/* renamed from: de.humatic.nmj.v */
final class C0502v {

    /* renamed from: a */
    public int f562a;

    /* renamed from: a */
    String f563a;

    /* renamed from: b */
    int f564b;

    /* renamed from: b */
    String f565b;

    /* renamed from: c */
    int f566c;

    /* renamed from: c */
    String f567c;

    /* renamed from: d */
    int f568d;

    /* renamed from: e */
    int f569e;

    /* renamed from: f */
    int f570f;

    /* renamed from: g */
    int f571g;

    C0502v() {
    }

    C0502v(int i) {
        this.f562a = i;
        this.f563a = NMJConfig.getName(this.f562a);
        this.f565b = NMJConfig.getIP(this.f562a);
        this.f564b = NMJConfig.getMode(this.f562a);
        this.f566c = NMJConfig.getPort(this.f562a);
        this.f568d = NMJConfig.getLocalPort(this.f562a);
        this.f569e = NMJConfig.getFlags(this.f562a);
        this.f570f = NMJConfig.getRTPState(this.f562a);
        this.f571g = NMJConfig.getNetworkAdapter(this.f562a);
        if (this.f564b == 1 && this.f565b != null) {
            this.f567c = NMJConfig.m53a(this.f562a, "localClientName", (String) null);
        }
    }
}
