package p004de.humatic.nmj.service;

import android.os.Bundle;
import p004de.humatic.nmj.NMJConfig;

/* renamed from: de.humatic.nmj.service.NMJChannel */
public class NMJChannel {
    public int flags;

    /* renamed from: id */
    public int f457id;

    /* renamed from: io */
    public int f458io = -1;

    /* renamed from: ip */
    public String f459ip;
    public int localPort;
    public int mode = -1;
    public String name;
    public int nwa;
    public int port;

    NMJChannel() {
    }

    NMJChannel(int i) {
        this.f457id = i;
        this.name = NMJConfig.getName(this.f457id);
        this.f459ip = NMJConfig.getIP(this.f457id);
        this.mode = NMJConfig.getMode(this.f457id);
        this.f458io = NMJConfig.getIO(this.f457id);
        this.port = NMJConfig.getPort(this.f457id);
        this.localPort = NMJConfig.getLocalPort(this.f457id);
        this.flags = NMJConfig.getFlags(this.f457id);
        this.nwa = NMJConfig.getNetworkAdapter(this.f457id);
    }

    public static NMJChannel fromBundle(Bundle bundle) {
        NMJChannel nMJChannel = new NMJChannel();
        nMJChannel.f457id = bundle.getInt("id");
        nMJChannel.f459ip = bundle.getString("ip", (String) null);
        nMJChannel.name = bundle.getString("name", (String) null);
        nMJChannel.mode = bundle.getInt("mode", (nMJChannel.f457id + 1) % 3 == 0 ? 1 : 0);
        nMJChannel.f458io = bundle.getInt("io", (nMJChannel.f457id + 1) % 3 == 0 ? -1 : (nMJChannel.f457id + 1) % 2);
        nMJChannel.port = bundle.getInt("port");
        nMJChannel.localPort = bundle.getInt("local_port");
        nMJChannel.flags = bundle.getInt("flags");
        nMJChannel.nwa = bundle.getInt("nwa");
        return nMJChannel;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("NMJChannel @" + hashCode() + " id " + this.f457id);
        stringBuffer.append(", name " + this.name);
        stringBuffer.append(", mode " + this.mode);
        stringBuffer.append(", ip " + this.f459ip);
        stringBuffer.append(", io " + this.f458io);
        stringBuffer.append(", port " + Long.toHexString((long) this.port));
        stringBuffer.append(", local port " + this.localPort);
        stringBuffer.append(", flags " + this.flags);
        stringBuffer.append(", nwa " + this.nwa);
        return stringBuffer.toString();
    }
}
