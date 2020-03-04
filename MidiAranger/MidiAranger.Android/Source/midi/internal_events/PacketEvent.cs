using InetAddress = Java.Net.InetAddress;
using DatagramPacket = Java.Net.DatagramPacket;
using Android.OS;

namespace midi.internal_events {
    public class PacketEvent {
        private Java.Net.InetAddress address;
        private int port;
        private byte[] data;
        private int length;
        public PacketEvent(DatagramPacket packet) {
            address = packet.Address;
            port = packet.Port;
            data = packet.GetData();
            length = packet.Length;
            //        Log.d("PacketEvent"," p:"+packet.getLength()+ " d:"+data.length);
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
            return data.Length;
        }
    }
}