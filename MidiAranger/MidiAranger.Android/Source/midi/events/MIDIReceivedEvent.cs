using Android.OS;

namespace midi.events {

    public class MIDIReceivedEvent {

        public Bundle midi;

        public MIDIReceivedEvent(Bundle m) {
            this.midi = m;
        }
    }
}