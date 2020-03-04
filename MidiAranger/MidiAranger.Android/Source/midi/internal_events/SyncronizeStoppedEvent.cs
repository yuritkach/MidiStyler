using Android.OS;

namespace midi.internal_events {

    public class SyncronizeStoppedEvent {
        public Bundle rinfo;

    public SyncronizeStoppedEvent(Bundle r) {
            rinfo = r;
        }

    }
}