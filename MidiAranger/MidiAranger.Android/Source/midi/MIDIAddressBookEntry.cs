using Android.OS;
using Java.Lang;

namespace midi {

    public class MIDIAddressBookEntry {

        private string address;
        private int port;
        private string name;
        private bool reconnect;

        public MIDIAddressBookEntry() {
        }

        public MIDIAddressBookEntry(Bundle rinfo) {
            address = rinfo.GetString(MIDIConstants.RINFO_ADDR, "");
            port = rinfo.GetInt(MIDIConstants.RINFO_PORT);
            name = rinfo.GetString(MIDIConstants.RINFO_NAME, "");
            reconnect = rinfo.GetBoolean(MIDIConstants.RINFO_RECON, false);
        }

        public Bundle rinfo() {
            Bundle rinfo = new Bundle();
            rinfo.PutString(MIDIConstants.RINFO_NAME, name);
            rinfo.PutString(MIDIConstants.RINFO_ADDR, address);
            rinfo.PutInt(MIDIConstants.RINFO_PORT, port);
            rinfo.PutBoolean(MIDIConstants.RINFO_RECON, reconnect);
            return rinfo;
        }

        public void SetAddress(string a) {
            address = a;
        }

        public string GetAddress() {
            return address;
        }

        public void SetPort(int p) {
            port = p;
        }

        public int GetPort() {
            return port;
        }

        public string GetAddressPort() { return address + ":" + port; }

        public void SetName(string n) {
            name = n;
        }

        public string GetName() {
            return name;
        }

        public void SetReconnect(bool b) {
            reconnect = b;
        }

        public bool GetReconnect() {
            return reconnect;
        }

    }
}