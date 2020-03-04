package p004de.humatic.nmj;

/* renamed from: de.humatic.nmj.NetworkMidiInput */
public class NetworkMidiInput extends NetworkMidiPort {

    /* renamed from: a */
    C0479n f185a;

    public NetworkMidiInput() {
        this.f208b = true;
    }

    NetworkMidiInput(int i, NetworkMidiSystem networkMidiSystem, NetworkMidiClient networkMidiClient) throws Exception {
        super(i, networkMidiSystem, networkMidiClient);
        this.f185a = new C0479n(i);
        if (networkMidiClient instanceof NetworkMidiListener) {
            addMidiListener((NetworkMidiListener) networkMidiClient);
        }
        this.f207a = false;
        this.f205a.mo8101a(this.f204a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final NetworkMidiInput m181a(NetworkMidiClient networkMidiClient) {
        if (this.f208b) {
            return null;
        }
        if (!m181a(networkMidiClient) || !(networkMidiClient instanceof NetworkMidiListener)) {
            return this;
        }
        addMidiListener((NetworkMidiListener) networkMidiClient);
        return this;
    }

    public synchronized void close(NetworkMidiClient networkMidiClient) {
        if (!this.f207a && !this.f208b) {
            C0484p.logln(5, String.valueOf(this.f204a) + " - Closing " + this + " nr. clients: " + this.f206a.size());
            if (networkMidiClient != null) {
                mo8098b(networkMidiClient);
                if (this.f206a.size() > 0) {
                    mo8089a();
                }
            }
            this.f185a.mo8151a();
            this.f206a.removeAllElements();
            this.f205a.mo8102a(0, this.f204a);
            this.f207a = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo8089a() {
        if (!this.f208b) {
            this.f206a.removeAllElements();
            if (this.f185a != null) {
                this.f185a.mo8151a();
            }
            this.f205a.mo8102a(NMJConfig.getIO(this.f204a), this.f204a);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8088a(int i) {
        if (!this.f208b) {
            this.f204a = i;
            if (this.f185a != null) {
                this.f185a.mo8152a(i);
            }
        }
    }

    public void addMidiListener(NetworkMidiListener networkMidiListener) {
        if (networkMidiListener != null && !this.f208b) {
            this.f185a.mo8150a(networkMidiListener);
            try {
                m181a((NetworkMidiClient) networkMidiListener);
            } catch (Exception e) {
            }
        }
    }

    public void removeMidiListener(NetworkMidiListener networkMidiListener) {
        if (networkMidiListener != null && !this.f208b) {
            int b = this.f185a.mo8157b(networkMidiListener);
            try {
                mo8098b(networkMidiListener);
            } catch (Exception e) {
            }
            if (b == 0 && this.f206a.size() == 0) {
                close((NetworkMidiClient) null);
            }
        }
    }

    public void setLink(NetworkMidiOutput networkMidiOutput, boolean z) {
        if (!this.f208b) {
            if (z) {
                addMidiListener(networkMidiOutput.asMidiListener(true));
            } else {
                removeMidiListener(networkMidiOutput.asMidiListener(false));
            }
        }
    }
}
