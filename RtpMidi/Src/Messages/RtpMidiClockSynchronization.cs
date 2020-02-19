using Java.IO;
using System.IO;

namespace rtpmidi.messages
{ 

    public class RtpMidiClockSynchronization:RtpMidiCommand
    {

        public byte Count { get; protected set; }
        public long Timestamp1 { get; protected set; }
        public long Timestamp2 { get; protected set; }
        public long Timestamp3 { get; protected set; }

        public RtpMidiClockSynchronization(int ssrc, byte count, long timestamp1, long timestamp2, long timestamp3):base(CommandWord.CK, ssrc)
        {
            Count = count;
            Timestamp1 = timestamp1;
            Timestamp2 = timestamp2;
            Timestamp3 = timestamp3;
        }

        public byte[] ToByteArray()
        {
            MemoryStream outputStream = new MemoryStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.Write(MIDI_COMMAND_HEADER1);
            dataOutputStream.Write(MIDI_COMMAND_HEADER2);
            dataOutputStream.Write(System.Text.Encoding.UTF8.GetBytes(CommandWord.ToString()));
            dataOutputStream.WriteInt(Ssrc);
            dataOutputStream.WriteByte(Count);
            dataOutputStream.Write(new byte[3]);
            dataOutputStream.WriteLong(Timestamp1);
            dataOutputStream.WriteLong(Timestamp2);
            dataOutputStream.WriteLong(Timestamp3);
            dataOutputStream.Flush();
            return outputStream.ToArray();
        }
    }
}