using Java.IO;
using System.IO;

namespace rtpmidi.messages
{ 

    public class RtpMidiClockSynchronization:RtpMidiCommand
    {

        private byte count;
        private long timestamp1;
        private long timestamp2;
        private long timestamp3;

        public RtpMidiClockSynchronization(int ssrc, byte count, long timestamp1, long timestamp2, long timestamp3):base(CommandWord.CK, ssrc)
        {
            this.count = count;
            this.timestamp1 = timestamp1;
            this.timestamp2 = timestamp2;
            this.timestamp3 = timestamp3;
        }

        public byte[] ToByteArray()
        {
            MemoryStream outputStream = new MemoryStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.Write(MIDI_COMMAND_HEADER1);
            dataOutputStream.Write(MIDI_COMMAND_HEADER2);
            dataOutputStream.Write(System.Text.Encoding.UTF8.GetBytes(CommandWord.ToString()));
            dataOutputStream.WriteInt(Ssrc);
            dataOutputStream.WriteByte(count);
            dataOutputStream.Write(new byte[3]);
            dataOutputStream.WriteLong(timestamp1);
            dataOutputStream.WriteLong(timestamp2);
            dataOutputStream.WriteLong(timestamp3);
            dataOutputStream.Flush();
            return outputStream.ToArray();
        }
    }
}