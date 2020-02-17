namespace rtpmidi.messages
{
    public class RtpMidiCommand {

        public static byte MIDI_COMMAND_HEADER1 = (byte)0xFF;
        public static byte MIDI_COMMAND_HEADER2 = (byte)0xFF;
        public CommandWord CommandWord { get; protected set; }
        public int Ssrc { get; protected set; }

        public RtpMidiCommand(CommandWord commandWord, int ssrc) {
            CommandWord = commandWord;
            Ssrc = ssrc;
        }

        public virtual byte[] ToByteArray() {
            throw new System.Exception("Must be overriden!");
        }
    }
}