package p004de.humatic.nmj;

import java.util.Vector;

/* renamed from: de.humatic.nmj.NetworkMidiPort */
public class NetworkMidiPort {

    /* renamed from: a */
    int f204a;

    /* renamed from: a */
    NetworkMidiSystem f205a;

    /* renamed from: a */
    Vector f206a = new Vector();

    /* renamed from: a */
    boolean f207a;

    /* renamed from: b */
    boolean f208b;

    NetworkMidiPort() {
    }

    NetworkMidiPort(int i, NetworkMidiSystem networkMidiSystem, NetworkMidiClient networkMidiClient) throws Exception {
        this.f204a = i;
        this.f205a = networkMidiSystem;
        mo8087a(networkMidiClient);
        C0484p.m393b();
    }

    public void close(NetworkMidiClient networkMidiClient) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public NetworkMidiPort mo8087a(NetworkMidiClient networkMidiClient) {
        if (!mo8087a(networkMidiClient)) {
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m196a(NetworkMidiClient networkMidiClient) {
        boolean z = false;
        for (int i = 0; i < this.f206a.size(); i++) {
            if (this.f206a.get(i).getClass().getName().equalsIgnoreCase(networkMidiClient.getClass().getName()) && this.f206a.get(i).hashCode() == networkMidiClient.hashCode()) {
                z = true;
            }
        }
        if (z) {
            return false;
        }
        this.f206a.add(networkMidiClient);
        C0484p.logln(5, this + " added " + networkMidiClient.getClass().getName() + "[" + Long.toHexString((long) networkMidiClient.hashCode()) + "], nr clients: " + this.f206a.size());
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo8098b(NetworkMidiClient networkMidiClient) {
        boolean z = false;
        for (int i = 0; i < this.f206a.size(); i++) {
            if (this.f206a.get(i).getClass().getName().equalsIgnoreCase(networkMidiClient.getClass().getName()) && this.f206a.get(i).hashCode() == networkMidiClient.hashCode()) {
                z = true;
            }
        }
        if (!z) {
            return false;
        }
        this.f206a.remove(networkMidiClient);
        C0484p.logln(5, String.valueOf(this.f204a) + " - Removed " + networkMidiClient.getClass().getName() + "[" + Long.toHexString((long) networkMidiClient.hashCode()) + "], nr clients: " + this.f206a.size());
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8089a() {
        C0484p.logln(5, "Clients remaining: " + this.f206a.size());
        for (int i = 0; i < this.f206a.size(); i++) {
            C0484p.logln(5, String.valueOf(this.f206a.get(i).getClass().getName()) + " " + this.f206a.get(i).hashCode());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m195a() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8088a(int i) {
        this.f204a = i;
    }

    public int getChannelIndex() {
        return this.f204a;
    }

    public String toString() {
        String name = getClass().getName();
        return String.valueOf(name.substring(name.lastIndexOf(".") + 1)) + "[" + Long.toHexString((long) hashCode()) + "] ID: " + this.f204a + " name: " + NMJConfig.getName(this.f204a);
    }
}
