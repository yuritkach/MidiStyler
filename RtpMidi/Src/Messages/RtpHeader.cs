using Java.IO;
using System.IO;

namespace rtpmidi.messages
{ 

    public class RtpHeader {

        public byte Version { get; protected set; }
        public bool PaddingFlag { get; protected set; }
        public bool ExtensionFlag { get; protected set; }
        public byte ContributingSourceIdentifiersCount { get; protected set; }
        public bool MarkerFlag { get; protected set; }
        public byte PayloadType { get; protected set; }
        public short SequenceNumber { get; protected set; }
        public int Timestamp { get; protected set; }
        public int Ssrc { get; protected set; }

        public RtpHeader(byte version, bool paddingFlag, bool extensionFlag,
              byte contributingSourceIdentifiersCount, bool markerFlag, byte payloadType, short sequenceNumber,
              int timestamp, int ssrc)
        {
            Version = version;
            PaddingFlag = paddingFlag;
            ExtensionFlag = extensionFlag;
            ContributingSourceIdentifiersCount = contributingSourceIdentifiersCount;
            MarkerFlag = markerFlag;
            PayloadType = payloadType;
            SequenceNumber = sequenceNumber;
            Timestamp = timestamp;
            Ssrc = ssrc;
        }

        public byte[] ToByteArray()
        {
            MemoryStream byteArrayOutputStream = new MemoryStream(12);
            DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

            byte header1 = 0;
            header1 |= (byte)(Version << 6);
            header1 |= (byte)((PaddingFlag ? 1 : 0) << 5);
            header1 |= (byte)((ExtensionFlag ? 1 : 0) << 4);
            header1 |= ContributingSourceIdentifiersCount;

            outputStream.WriteByte(header1);

            byte header2 = 0;
            header2 |= (byte)((MarkerFlag ? 1 : 0) << 7);
            header2 |= PayloadType;

            outputStream.WriteByte(header2);

            outputStream.WriteShort(SequenceNumber);
            outputStream.WriteInt(Timestamp);
            outputStream.WriteInt(Ssrc);
            outputStream.Flush();
            return byteArrayOutputStream.ToArray();
        }
    }
}