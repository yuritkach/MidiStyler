using Java.IO;
using System.IO;

namespace rtpmidi.messages
{ 

    public class RtpHeader {

        private byte version;
        private bool paddingFlag;
        private bool extensionFlag;
        private byte contributingSourceIdentifiersCount;
        private bool markerFlag;
        private byte payloadType;
        private short sequenceNumber;
        private int timestamp;
        private int ssrc;

        public byte[] ToByteArray()
        {
            MemoryStream byteArrayOutputStream = new MemoryStream(12);
            DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

            byte header1 = 0;
            header1 |= (byte)(version << 6);
            header1 |= (byte)((paddingFlag ? 1 : 0) << 5);
            header1 |= (byte)((extensionFlag ? 1 : 0) << 4);
            header1 |= contributingSourceIdentifiersCount;

            outputStream.WriteByte(header1);

            byte header2 = 0;
            header2 |= (byte)((markerFlag ? 1 : 0) << 7);
            header2 |= payloadType;

            outputStream.WriteByte(header2);

            outputStream.WriteShort(sequenceNumber);
            outputStream.WriteInt(timestamp);
            outputStream.WriteInt(ssrc);
            outputStream.Flush();
            return byteArrayOutputStream.ToArray();
        }
    }
}