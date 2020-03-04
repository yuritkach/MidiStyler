package p004de.humatic.nmj;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Looper;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/* renamed from: de.humatic.nmj.NetworkMidiSystem */
public class NetworkMidiSystem {

    /* renamed from: a */
    private static Context f209a;

    /* renamed from: a */
    private static WifiManager.MulticastLock f210a;

    /* renamed from: a */
    private static C0452a f211a = new C0452a();

    /* renamed from: a */
    private static NetworkMidiSystem f212a = new NetworkMidiSystem();

    /* renamed from: a */
    static boolean f213a;

    /* renamed from: b */
    private static boolean f214b;

    /* renamed from: c */
    private static boolean f215c;

    /* renamed from: a */
    private Vector<NetworkMidiInput> f216a = new Vector<>();

    /* renamed from: b */
    private Vector<NetworkMidiOutput> f217b = new Vector<>();

    NetworkMidiSystem() {
    }

    public static NetworkMidiSystem get(Context context) throws Exception {
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        if (!context.equals(f209a)) {
            if (context.toString().indexOf("NMJConfigDialog") != -1) {
                try {
                    if (f209a.getApplicationContext().equals(context.getApplicationContext())) {
                        return f212a;
                    }
                } catch (Exception e) {
                }
            }
            f211a = new C0452a();
            f210a = null;
            f214b = false;
            f215c = false;
        }
        f213a = false;
        if (!f214b || f215c) {
            C0484p.logln(2, "NetworkMidiSystem init");
            if (!f215c) {
                f209a = context;
                C0484p.m393b();
                if (f210a == null) {
                    try {
                        WifiManager.MulticastLock createMulticastLock = ((WifiManager) context.getSystemService("wifi")).createMulticastLock("nmj_wifi_lock");
                        f210a = createMulticastLock;
                        createMulticastLock.setReferenceCounted(true);
                        C0484p.logln(2, "Multicast lock aquired " + f210a);
                    } catch (Exception e2) {
                    }
                }
                NMJConfig.m66a(context);
            }
            f214b = true;
            if (f215c) {
                f215c = false;
            }
        }
        return f212a;
    }

    /* renamed from: a */
    static WifiManager m199a() {
        return (WifiManager) f209a.getSystemService("wifi");
    }

    /* renamed from: a */
    static WifiManager.MulticastLock m198a() {
        return f210a;
    }

    public static NetworkMidiSystem get() {
        return f212a;
    }

    /* renamed from: a */
    static NetworkMidiSystem m200a() throws Exception {
        f215c = true;
        f213a = false;
        return get(f209a);
    }

    public synchronized NetworkMidiInput openInput(int i, NetworkMidiClient networkMidiClient) throws Exception {
        NetworkMidiInput networkMidiInput;
        boolean z = true;
        synchronized (this) {
            if (i >= 0) {
                if (i <= NMJConfig.getNumChannels()) {
                    if ((NMJConfig.getMode(i) & 240) == 0) {
                        if (NMJConfig.getIO(i) <= 0) {
                            if (!NMJConfig.canOpen(i)) {
                                if (NMJConfig.getMode(i) > 1 || (NMJConfig.getConnectivity(f209a) & 3) == 0) {
                                    z = false;
                                }
                                if (!z) {
                                    throw new IOException("Channel " + i + " - can't open, no connectivity or missing permission (" + NMJConfig.getMode(i) + " / " + C0484p.m356a((short) NMJConfig.getConnectivity(f209a)) + ")");
                                }
                            }
                            if (networkMidiClient == null) {
                                networkMidiClient = f211a;
                            }
                            Iterator<NetworkMidiInput> it = this.f216a.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    NetworkMidiInput next = it.next();
                                    if (next.f204a == i) {
                                        networkMidiInput = next.m181a(networkMidiClient);
                                        break;
                                    }
                                } else {
                                    networkMidiInput = new NetworkMidiInput(i, this, networkMidiClient);
                                    this.f216a.add(networkMidiInput);
                                    break;
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Channel not configured for input");
                        }
                    } else {
                        throw new IllegalArgumentException("Not a MIDI channel");
                    }
                }
            }
            throw new IndexOutOfBoundsException();
        }
        return networkMidiInput;
    }

    public synchronized NetworkMidiOutput openOutput(int i, NetworkMidiClient networkMidiClient) throws Exception {
        NetworkMidiOutput networkMidiOutput;
        if (i >= 0) {
            if (i <= NMJConfig.getNumChannels()) {
                if ((NMJConfig.getMode(i) & 240) == 0) {
                    if (NMJConfig.getIO(i) != 0) {
                        if (NMJConfig.canOpen(i)) {
                            if (networkMidiClient == null) {
                                networkMidiClient = f211a;
                            }
                            Iterator<NetworkMidiOutput> it = this.f217b.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    NetworkMidiOutput next = it.next();
                                    if (next.f204a == i) {
                                        networkMidiOutput = (NetworkMidiOutput) next.mo8087a(networkMidiClient);
                                        break;
                                    }
                                } else {
                                    networkMidiOutput = new NetworkMidiOutput(i, this, networkMidiClient);
                                    this.f217b.add(networkMidiOutput);
                                    break;
                                }
                            }
                        } else {
                            throw new IOException("Channel " + i + " - can't open, no connectivity or missing permission (" + NMJConfig.getMode(i) + " / " + C0484p.m356a((short) NMJConfig.getConnectivity(f209a)) + ")");
                        }
                    } else {
                        throw new IllegalArgumentException("Channel not configured for output");
                    }
                } else {
                    throw new IllegalArgumentException("Not a MIDI channel");
                }
            }
        }
        throw new IndexOutOfBoundsException();
        return networkMidiOutput;
    }

    public boolean isOpen(int i, int i2) {
        if (i <= 0) {
            Iterator<NetworkMidiInput> it = this.f216a.iterator();
            while (it.hasNext()) {
                if (it.next().f204a == i2) {
                    return true;
                }
            }
        }
        if (Math.abs(i) == 1) {
            Iterator<NetworkMidiOutput> it2 = this.f217b.iterator();
            while (it2.hasNext()) {
                if (it2.next().f204a == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final NetworkMidiPort mo8101a(int i) {
        if (NMJConfig.getIO(i) <= 0) {
            Iterator<NetworkMidiInput> it = this.f216a.iterator();
            while (it.hasNext()) {
                NetworkMidiInput next = it.next();
                if (next.f204a == i) {
                    return next;
                }
            }
        } else {
            Iterator<NetworkMidiOutput> it2 = this.f217b.iterator();
            while (it2.hasNext()) {
                NetworkMidiOutput next2 = it2.next();
                if (next2.f204a == i) {
                    return next2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final NetworkMidiPort mo8102a(int i, int i2) {
        if (i <= 0) {
            Iterator<NetworkMidiInput> it = this.f216a.iterator();
            while (it.hasNext()) {
                NetworkMidiInput next = it.next();
                if (next.f204a == i2) {
                    return next;
                }
            }
        } else {
            Iterator<NetworkMidiOutput> it2 = this.f217b.iterator();
            while (it2.hasNext()) {
                NetworkMidiOutput next2 = it2.next();
                if (next2.f204a == i2) {
                    return next2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m203a(final int i) {
        new Thread(new Runnable(this) {
            public final void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
                NMJConfig.m83b(i);
            }
        }).start();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m204a(int i, final int i2) {
        if (i == 0) {
            try {
                Iterator<NetworkMidiInput> it = this.f216a.iterator();
                while (it.hasNext()) {
                    NetworkMidiInput next = it.next();
                    if (next.f204a == i2) {
                        this.f216a.remove(next);
                    }
                }
            } catch (Exception e) {
                return;
            }
        } else {
            Iterator<NetworkMidiOutput> it2 = this.f217b.iterator();
            while (it2.hasNext()) {
                NetworkMidiOutput next2 = it2.next();
                if (next2.f204a == i2) {
                    this.f217b.remove(next2);
                }
            }
        }
        new Thread(new Runnable(this) {
            public final void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
                NMJConfig.m99c(i2);
            }
        }).start();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m205a(int i) {
        for (int i2 = 0; i2 < this.f216a.size(); i2++) {
            if (this.f216a.get(i2).f204a == i) {
                this.f216a.get(i2).mo8089a();
            }
        }
        for (int i3 = 0; i3 < this.f216a.size(); i3++) {
            NetworkMidiInput networkMidiInput = this.f216a.get(i3);
            if (networkMidiInput.f204a > i) {
                networkMidiInput.mo8088a(networkMidiInput.f204a - 1);
            }
        }
        for (int i4 = 0; i4 < this.f217b.size(); i4++) {
            if (this.f217b.get(i4).f204a == i) {
                this.f217b.get(i4).mo8089a();
            }
        }
        for (int i5 = 0; i5 < this.f217b.size(); i5++) {
            NetworkMidiOutput networkMidiOutput = this.f217b.get(i5);
            if (networkMidiOutput.f204a > i) {
                networkMidiOutput.mo8088a(networkMidiOutput.f204a - 1);
            }
        }
        return true;
    }

    public void exit() {
        try {
            if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                new Thread(new Runnable() {
                    public final void run() {
                        NetworkMidiSystem.this.exit();
                    }
                }).start();
                int i = 0;
                while (f214b) {
                    int i2 = i + 1;
                    if (i < 10) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(100);
                            i = i2;
                        } catch (InterruptedException e) {
                            i = i2;
                        }
                    } else {
                        return;
                    }
                }
                return;
            }
        } catch (Exception e2) {
        }
        f213a = true;
        try {
            for (int size = this.f216a.size() - 1; size >= 0; size--) {
                try {
                    this.f216a.get(size).close((NetworkMidiClient) null);
                } catch (Exception e3) {
                }
            }
        } catch (Exception e4) {
        }
        try {
            for (int size2 = this.f217b.size() - 1; size2 >= 0; size2--) {
                try {
                    this.f217b.get(size2).close((NetworkMidiClient) null);
                } catch (Exception e5) {
                }
            }
        } catch (Exception e6) {
        }
        try {
            if (f210a != null) {
                f210a.release();
                f210a = null;
                C0484p.logln(5, "Multicast lock released");
            }
        } catch (Exception e7) {
        }
        NMJConfig.m38a();
        if (f214b) {
            C0484p.logln(2, "NetworkMidiSystem closed");
        }
        f214b = false;
    }

    /* renamed from: de.humatic.nmj.NetworkMidiSystem$a */
    static class C0452a implements NetworkMidiClient {
        C0452a() {
        }
    }
}
