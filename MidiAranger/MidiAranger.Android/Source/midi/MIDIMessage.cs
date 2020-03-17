using Android.OS;
using Android.Util;
using midi.internal_events;
using midi.utility;
using midi;
using Java.Lang;
using System;

namespace midi { 

    public class MIDIMessage:RTPMessage {
        private bool valid;
        private byte[] message;

        public MIDIMessage(byte[] message) {
            this.message = message;
        }

        public bool ParseMessage(PacketEvent packet) {
            this.valid = false;
            Parse(packet);
            DataBufferReader reader = new DataBufferReader();
            DataBuffer rawPayload = new DataBuffer(payload, payload_length);
            if (payload_length > 0)
            {
                message = rawPayload.GetBytes();
                this.valid = true;
                Log.Debug("MIDIMessage", "byte array:" + BitConverter.ToString(message).Replace("-", ""));
                return true;
            }
            else return false;
        }


        public byte[] ToByteArray() {
            return message;
        }
            

        public bool IsValid() {
            return valid;
        }

        public byte[] GenerateBuffer() {
            OutDataBuffer buffer = GeneratePayload();
            buffer.Write8(message.Length);
            foreach (byte b in message)
                buffer.Write8(b);
            return buffer.ToByteArray();

        }
    }
}