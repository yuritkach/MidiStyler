package p004de.humatic.nmj;

/* renamed from: de.humatic.nmj.q */
final class C0485q {

    /* renamed from: a */
    private int f434a;

    protected C0485q(int i) {
        this.f434a = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo8165a() {
        return NMJConfig.getName(this.f434a);
    }

    public final String toString() {
        return "NMC@" + hashCode() + ", " + NMJConfig.getName(this.f434a) + ", " + NMJConfig.getIP(this.f434a);
    }
}
