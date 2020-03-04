using Android.OS;

namespace midi.events {

    public class MIDISyncronizationStartEvent {

        public Bundle rinfo;

    public MIDISyncronizationStartEvent(Bundle r) {
            rinfo = r;
        }
    }
}