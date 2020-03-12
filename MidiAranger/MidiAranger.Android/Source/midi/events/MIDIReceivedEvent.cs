using Android.OS;

namespace midi.events {

    public class MIDIReceivedEvent {

        public Bundle midi;
        public byte[] message;

        public MIDIReceivedEvent(Bundle m) {
            this.midi = m;
        }
        public MIDIReceivedEvent(byte[] bytes)
        {
            this.message = bytes;
        }
    }
}