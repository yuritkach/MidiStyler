using Android.OS;
using Android.Util;
using midi.internal_events;
using midi.utility;
using midi;
using Java.Lang;

namespace midi { 

    public class MIDIMessage:RTPMessage {

        private bool valid;
        private DataBuffer m;

        private bool firstHasDeltaTime;

        private int channel_status;
        private int channel;
        private int note;
        private int velocity;

        public static MIDIMessage NewUsing(int cs, int c, int n, int v) {
            MIDIMessage m = new MIDIMessage();
            m.CreateNote(cs,c,n,v);
            return m;
        }

        public static MIDIMessage NewUsing(Bundle m) {
            return NewUsing(m.GetInt(midi.MIDIConstants.MSG_COMMAND,0x09),
                        m.GetInt(midi.MIDIConstants.MSG_CHANNEL,0),
                        m.GetInt(midi.MIDIConstants.MSG_NOTE,0),
                        m.GetInt(midi.MIDIConstants.MSG_VELOCITY,0));
        }


        public MIDIMessage() {
        }

        public bool ParseMessage(PacketEvent packet) {
            this.valid = false;
            Parse(packet);
            DataBufferReader reader = new DataBufferReader();
            DataBuffer rawPayload = new DataBuffer(payload, payload_length);

            // payload should contain command + journal
            int block4 = reader.Read8(rawPayload);
            channel_status = block4 >> 4;
            channel = block4 & 0xf;
            int block5 = reader.Read8(rawPayload);
            note = block5 & 0x7f;
            int block6 = reader.Read8(rawPayload);
            velocity = block6 & 0x7f;

            this.valid = true;

            Log.Debug("MIDIMessage", "cs:" + channel_status + " c:" + channel + " n:" + note + " v" + velocity);
            return true;
        }

        public Bundle ToBundle() {
            Bundle midi = new Bundle();
            midi.PutInt(MIDIConstants.MSG_COMMAND,this.channel_status);
            midi.PutInt(MIDIConstants.MSG_CHANNEL,this.channel);
            midi.PutInt(MIDIConstants.MSG_NOTE, this.note);
            midi.PutInt(MIDIConstants.MSG_VELOCITY, this.velocity);
            return midi;
        }

        public void CreateNote(int channel_status, int channel, int note, int velocity) {
            this.channel_status = channel_status;
            this.channel = channel;
            this.note = note;
            this.velocity = velocity;
        }
        public void CreateNote(int note, int velocity) {
            this.note = note;
            this.velocity = velocity;
        }

        public bool IsValid() {
            return valid;
        }

        public byte[] GenerateBuffer() {
            OutDataBuffer buffer = GeneratePayload();
    // TODO : this doesn't handle channel_status or channel correctly
    //        buffer.write8(0x00);
            buffer.Write16(new Integer(0x0390));
            buffer.Write8(new Integer(note));
            buffer.Write8(new Integer(velocity));
            return buffer.ToByteArray();
        }
    }
}