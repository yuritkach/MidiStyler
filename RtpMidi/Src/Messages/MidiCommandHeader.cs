using Java.IO;
using System.IO;

namespace rtpmidi.messages
{ 
    public class MidiCommandHeader {

        public bool B { get; protected set; }
        public bool J { get; protected set; }
        public bool Z { get; protected set; }
        public bool P { get; protected set; }
        public short length;
        private RtpHeader rtpHeader;

        public byte[] ToByteArray()
        {
            MemoryStream byteArrayOutputStream = new MemoryStream();
            DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);
            outputStream.Write(rtpHeader.ToByteArray());

            byte midiCommandHeader1 = 0;

            midiCommandHeader1 |= (byte)((B ? 1 : 0) << 7);
            midiCommandHeader1 |= (byte)((J ? 1 : 0) << 6);
            midiCommandHeader1 |= (byte)((Z ? 1 : 0) << 5);
            midiCommandHeader1 |= (byte)((P ? 1 : 0) << 4);

            if (B) {
                midiCommandHeader1 |= (byte)((length & 0x0F00) >> 8);
                outputStream.WriteByte(midiCommandHeader1);
                outputStream.WriteByte(length & 0x00FF);
            } else {
                midiCommandHeader1 |= (byte) length;
                outputStream.WriteByte(midiCommandHeader1);
            }

            outputStream.Flush();
            return byteArrayOutputStream.ToArray();
        }
    }
}