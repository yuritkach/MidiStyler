using Java.IO;
using Java.Lang;
using System;
using System.Collections.Generic;
using System.IO;

namespace rtpmidi.messages { 

    public class RtpMidiMessage {

        public MidiCommandHeader MidiCommandHeader { get; protected set; }
        public List<MidiTimestampPair> Messages { get; protected set; }

        public RtpMidiMessage(MidiCommandHeader midiCommandHeader, List<MidiTimestampPair> messages)
        {
            MidiCommandHeader = midiCommandHeader;
            Messages = messages;
        }

        public byte[] ToByteArray()
        {
            MemoryStream byteArrayOutputStream = new MemoryStream();
            DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

            outputStream.Write(MidiCommandHeader.ToByteArray());

            bool first = true;
            foreach (MidiTimestampPair message in Messages) {
                if (first && !MidiCommandHeader.Z) {
                    first = false;
                }
                else
                {
                    int timestamp = message.Timestamp;
                    if (timestamp > 0x0FFFFFFF) {
                        throw new IllegalArgumentException("Timestamp too big: " + timestamp);
                    }
                    if (timestamp > 0) {
                        int numberOfSeptets =
                            (int) System.Math.Ceiling(Integer.BitCount(Integer.HighestOneBit(timestamp) * 2 - 1) / 7.0);
                            while (numberOfSeptets > 0) {
                                outputStream.WriteByte(
                                    (numberOfSeptets > 1 ? 0x80 : 0) | ((timestamp >> ((numberOfSeptets - 1) * 7)) & 0x7F));
                                numberOfSeptets--;
                            }
                    }
                    else
                    {
                        outputStream.WriteByte(0);
                    }
                }
                outputStream.Write(message.MidiMessage.Data);
            }

            outputStream.Flush();
            return byteArrayOutputStream.ToArray();
        }

    }
}