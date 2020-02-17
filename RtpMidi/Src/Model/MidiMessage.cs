namespace rtpmidi.model
{
    public class MidiMessage {
        public byte[] Data { get; protected set; }
        public int Length { get; protected set; }
        public MidiMessage(byte[] data, int length)
        {
            Data = data;
            Length = length;
        }
    }
}