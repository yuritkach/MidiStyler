namespace midi.events {

    public class MIDIConnectionRequestRejectedEvent {
        public Bundle rinfo;

    public MIDIConnectionRequestRejectedEvent(Bundle r) {
            rinfo = r;
        }

    }
}