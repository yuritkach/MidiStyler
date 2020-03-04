package p004de.humatic.nmj;

/* renamed from: de.humatic.nmj.NMJException */
public class NMJException extends RuntimeException {

    /* renamed from: a */
    private int f184a = 0;

    public NMJException(Throwable th) {
        super(th);
    }

    public NMJException(String str) {
        super(str);
    }

    public NMJException(String str, int i) {
        super(str);
        this.f184a = i;
    }

    public int getErrorCode() {
        return this.f184a;
    }
}
