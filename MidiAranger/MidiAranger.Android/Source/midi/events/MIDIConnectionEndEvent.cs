
using Android.OS;

namespace midi.events {

    public class MIDIConnectionEndEvent {
        public Bundle rinfo;

    public MIDIConnectionEndEvent(Bundle b) {
            rinfo = b;
        }

    }
}