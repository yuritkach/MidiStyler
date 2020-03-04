using Android.OS;
using midi;

namespace midi.internal_events {


    public class ConnectionFailedEvent {
        public Bundle rinfo;
        public MIDIFailCode code;
        public int initiator_code;

        public ConnectionFailedEvent(MIDIFailCode c,Bundle r)
        {
            code = c;
            rinfo = r;
            initiator_code = 0;
        }

        public ConnectionFailedEvent(MIDIFailCode c, Bundle r,int i)
        {
            code = c;
            rinfo = r;
            initiator_code = i;
        }
    }
}