using Java.Lang;
using midi.internal_events;
using midi.utility;

namespace midi
{
  
    public class RTPMessage {

        int version = 2;
        bool padding = false;
        bool hasExtension = false;
        int csrcCount = 0;
        bool marker = false;
        int payloadType = 0x61;
        int sequenceNumber = 0;
        int timestamp = 0;
        public int ssrc = 0;
        protected byte[] payload;
        protected int payload_length;

        public bool Parse(PacketEvent packet) {

            DataBuffer rawInput = new DataBuffer(packet.GetData(), packet.GetLength());
            DataBufferReader reader = new DataBufferReader();

            int firstByte = reader.Read8(rawInput);

            this.version = firstByte >> 6;
            this.padding = ((firstByte >> 5 & 1) != 0);
            this.hasExtension = ((firstByte >> 4 & 1) != 0);
            this.csrcCount = firstByte & 0xF;

            int secondByte = reader.Read8(rawInput);

            this.marker = (secondByte & 0x80) == 0x80;
            this.payloadType = secondByte & 0x7f;

            this.sequenceNumber = reader.Read16(rawInput);
            this.timestamp = reader.ReadInteger(rawInput);
            this.ssrc = reader.ReadInteger(rawInput);

            int block3 = reader.Read8(rawInput);
            bool bflag = (block3 >> 7 & 1) != 0;
            bool jflag = ((block3 >> 6 & 1) & 0x1) != 0;
            bool zflag = ((block3 >> 5 & 1) & 0x1) != 0;
            bool pflag = ((block3 >> 4 & 1) & 0x1) != 0;
            int command_length = block3 & 0x7;

            this.payload = rawInput.slice(rawInput.GetStreamPosition());
            this.payload_length = rawInput.GetBytesLength() - rawInput.GetStreamPosition();
            return true;
        }

        public OutDataBuffer GeneratePayload() {
            OutDataBuffer buffer = new OutDataBuffer();

            int firstByte = 0;
            firstByte |= this.version << 6;
            firstByte |= this.padding ? 0x20 : 0;
            firstByte |= this.hasExtension ? 0x10 : 0;
            // csrcs = 0... so just skip this
            //        firstByte |= (this.csrcs.length > 15 ? 15 : this.csrcs.length);

            int secondByte = this.payloadType | (this.marker ? 0x80 : 0);
            buffer.Write8(new Integer(firstByte));
            buffer.Write8(new Integer(secondByte));
            buffer.Write16(new Integer(sequenceNumber));
            long t = MIDISession.GetInstance().getNow();
            //        Log.e("RTPMessage","t:"+t+" t8:"+(t >>> 8)+" t16:"+(t >>>16)+" tint:"+(int)t);
            //        timestamp = (int)t >>> 8;
            timestamp = (int)t;
            buffer.Write32(new Integer(timestamp << 0));
            buffer.Write32(new Integer(ssrc));
            return buffer;
        }
    }
}