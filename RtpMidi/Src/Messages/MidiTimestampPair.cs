using rtpmidi.model;
namespace rtpmidi.messages {
    public class MidiTimestampPair {

        public int Timestamp { get; protected set; }
        public MidiMessage MidiMessage { get; protected set; }
    }
}