namespace rtpmidi.model
{
    public abstract class MidiMessage {
        public byte[] Data { get; protected set; }
        public int Length { get; protected set; }
    }
}