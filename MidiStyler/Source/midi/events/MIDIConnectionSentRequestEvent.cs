namespace midi.events {
    
    public class MIDIConnectionSentRequestEvent {

        public Bundle rinfo;

    public MIDIConnectionSentRequestEvent(Bundle r) {
            rinfo = r;
        }
    }
}