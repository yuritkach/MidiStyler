using InetAddress = Java.Net.InetAddress;
using DatagramPacket = Java.Net.DatagramPacket;
using Android.OS;
using Android.Util;

namespace midi.internal_events {
    public class PacketEvent {
        private Java.Net.InetAddress address;
        private readonly int port;
        private readonly byte[] data;
        private readonly int length;
        public PacketEvent(DatagramPacket packet) {
            address = packet.Address;
            port = packet.Port;
            data = packet.GetData();
            length = packet.Length;
    //        Log.Debug("PacketEvent"," p:"+packet.Length+ " d:"+data.Length);
        }

        public Bundle GetRInfo() {
            Bundle rinfo = new Bundle();
            rinfo.PutString(midi.MIDIConstants.RINFO_ADDR, address.HostAddress);
            rinfo.PutInt(MIDIConstants.RINFO_PORT, port);
            return rinfo;
        }

        public InetAddress GetAddress() {
            return address;
        }

        public int GetPort() {
            return port;
        }

        public byte[] GetData() {
            return data;
        }

        public int GetLength() {
            return length;
        }
    }
}