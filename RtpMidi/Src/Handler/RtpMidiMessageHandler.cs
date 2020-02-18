using Android.Util;
using Java.IO;
using rtpmidi.messages;
using rtpmidi.model;
using System.Collections.Generic;
using System.IO;

namespace rtpmidi.handler
{

    public class RtpMidiMessageHandler {

        private static int RTP_MIDI = 97;
        private List<IRtpMidiMessageListener> listeners = new List<IRtpMidiMessageListener>();

        public RtpMidiMessageHandler()
        {
            listeners.Add(new RtpMidiMessageLogListener());
        }

        public void Handle(byte[] data, RtpMidiServer rtpMidiServer)
        {
            DataInputStream dataInputStream = new DataInputStream(new MemoryStream(data));
            byte header1 = (byte) dataInputStream.ReadByte();
            byte version = (byte)((header1 >> 6) & 0x03);
            bool paddingFlag = ((header1 >> 5) & 0x01) == 1;
            bool extensionFlag = ((header1 >> 4) & 0x01) == 1;
            byte contributingSourceIdentifiersCount = (byte)(header1 & 0x0F);

            byte header2 = (byte) dataInputStream.ReadByte();
            bool markerFlag = ((header2 >> 7) & 0x01) == 1;
            byte payloadType = (byte)(header2 & 0x7F);
            if (payloadType != RTP_MIDI)
            {
                return;
            }

            short sequenceNumber = dataInputStream.ReadShort();
            int timestamp = dataInputStream.ReadInt();
            int ssrc = dataInputStream.ReadInt();

            RtpHeader rtpHeader = new RtpHeader(version, paddingFlag, extensionFlag, contributingSourceIdentifiersCount, markerFlag,
                                payloadType, sequenceNumber, timestamp, ssrc);

            byte midiCommandHeader1 = (byte) dataInputStream.ReadByte();
            bool b = ((midiCommandHeader1 >> 7) & 0x01) == 1;
            bool j = ((midiCommandHeader1 >> 6) & 0x01) == 1;
            bool z = ((midiCommandHeader1 >> 5) & 0x01) == 1;
            bool p = ((midiCommandHeader1 >> 4) & 0x01) == 1;
            // Header 2 octets
            short length;
            if (b)
            {
                byte midiCommandHeader2 = (byte) dataInputStream.ReadByte();
                length = (short)((midiCommandHeader1 << 8 | midiCommandHeader2) & 0x0FFF);
            }
            else
            {
                length = (short)(midiCommandHeader1 & 0x0F);
            }

            MidiCommandHeader midiCommandHeader = new MidiCommandHeader(b, j, z, p, length, rtpHeader);

            byte[] midiCommandBuffer = new byte[length];
            int midiCommandBytesRead = dataInputStream.Read(midiCommandBuffer);
            if (((short)midiCommandBytesRead) != length)
            {
                return;
            }

            List<MidiTimestampPair> messages = new List<MidiTimestampPair>();
            try
            {
                DataInputStream midiInputStream = new DataInputStream(new MemoryStream(midiCommandBuffer));
                messages.AddRange(ReadMidiMessages(midiCommandHeader, midiInputStream));
                HandleMessage(new RtpMidiMessage(midiCommandHeader, messages));
            }
            catch (System.IO.IOException e) 
            {
                Log.Error("RtpMidi","IOException while processing MIDI message", e);
            }

        }

        private void HandleMessage(RtpMidiMessage message)
        {
            foreach (MidiTimestampPair midiPair in message.Messages)
            {
                foreach (IRtpMidiMessageListener listener in listeners)
                {
                    listener.OnMidiMessage(message.MidiCommandHeader, midiPair.MidiMessage, midiPair.Timestamp);
                }

            }

        }

        private List<MidiTimestampPair> ReadMidiMessages(MidiCommandHeader midiCommandHeader, DataInputStream midiInputStream)
        {
            List<MidiTimestampPair> result = new List<MidiTimestampPair>();

            int status = -1;
            ByteArrayOutputStream sysexDataStream = null;
            int deltaTimeSum = midiCommandHeader.RtpHeader.Timestamp;
            while (midiInputStream.Available() > 0)
            {
                int deltaTime;
                if (midiCommandHeader.Z)
                {
                    deltaTime = ReadDeltaTime(midiInputStream);
                }
                else
                {
                    deltaTime = 0;
                }
                deltaTimeSum += deltaTime;

                byte midiOctet1 = (byte) midiInputStream.ReadByte();
                bool systemCommonMessage = (midiOctet1 & 0xF0) == 0xF0;
                int possibleStatus = midiOctet1 & 0xFF;
                if (systemCommonMessage)
                {
                    if (midiOctet1 == (byte)SysexMessage.SYSTEM_EXCLUSIVE)
                    {
                        sysexDataStream = new ByteArrayOutputStream();
                        sysexDataStream.Write(midiOctet1);
                        bool partial = ReadSysexData(midiInputStream, sysexDataStream);
                        if (!partial)
                        {
                            result.Add(new MidiTimestampPair(deltaTimeSum,
                                        new SysexMessage(sysexDataStream.ToByteArray(), sysexDataStream.Size())));
                        }
                    }
                    else 
                    if (midiOctet1 == (byte)SysexMessage.SPECIAL_SYSTEM_EXCLUSIVE)
                    {
                        bool partial = ReadSysexData(midiInputStream, sysexDataStream);
                        if (!partial)
                        {
                            result.Add(new MidiTimestampPair(deltaTimeSum,
                                        new SysexMessage(sysexDataStream.ToByteArray(), sysexDataStream.Size())));
                        }
                    }
                    else 
                    if (midiOctet1 == (byte)0xF4)
                    {
                        sysexDataStream.Reset();
                    }
                    else
                    {
                        ShortMessage shortMessage = null;
                        switch (possibleStatus) {
                            case ShortMessage.TUNE_REQUEST:
                            case ShortMessage.TIMING_CLOCK:
                            case ShortMessage.START:
                            case ShortMessage.STOP:
                            case ShortMessage.ACTIVE_SENSING:
                            case ShortMessage.SYSTEM_RESET:
                                    status = possibleStatus;
                                    shortMessage = new ShortMessage((byte)(status & 0xFF));
                                    break;
                            case ShortMessage.MIDI_TIME_CODE:
                            case ShortMessage.SONG_SELECT:
                                    status = possibleStatus;
                                    shortMessage = new ShortMessage((byte)(status & 0xFF), (byte) midiInputStream.ReadByte());
                                    break;
                                case ShortMessage.SONG_POSITION_POINTER:
                                    status = possibleStatus;
                                    shortMessage = new ShortMessage((byte)(status & 0xFF), (byte) midiInputStream.ReadByte(),
                                            (byte) midiInputStream.ReadByte());
                                    break;
                                default:
                                    Log.Error("RtpMidi","Invalid Message-status: {}", possibleStatus);
                                    break;
                            }
                            if (shortMessage != null) {
                                result.Add(new MidiTimestampPair(deltaTimeSum, shortMessage));
                            }
                        }
                    } else {
                        ShortMessage shortMessage = null;
                        switch (midiOctet1 & 0xF0) {
                            case ShortMessage.NOTE_OFF:
                            case ShortMessage.NOTE_ON:
                            case ShortMessage.POLY_PRESSURE:
                            case ShortMessage.CONTROL_CHANGE:
                            case ShortMessage.PITCH_BEND:
                                status = possibleStatus;
                                shortMessage = new ShortMessage((byte)(status & 0xFF),(byte) midiInputStream.ReadByte(),
                                        (byte) midiInputStream.ReadByte());
                                break;
                            case ShortMessage.PROGRAM_CHANGE:
                            case ShortMessage.CHANNEL_PRESSURE:
                                status = possibleStatus;
                                shortMessage = new ShortMessage((byte)(status & 0xFF), (byte)midiInputStream.ReadByte());
                                break;
                            default:
                                Log.Error("rtpMidi","Invalid ShortMessage-status: {}", midiOctet1 & 0xF0);
                                break;
                        }
                        if (shortMessage != null) {
                            result.Add(new MidiTimestampPair(deltaTimeSum, shortMessage));
                        }
                    }
                }
                return result;
            }

            /**
             * @return Is the sysex a partial message?
             */
            private bool ReadSysexData(DataInputStream midiInputStream, ByteArrayOutputStream sysexData)
            {
                byte tmpByte;
                bool sysexEnd;
                bool sysexStart;
                do {
                    tmpByte = (byte)midiInputStream.ReadByte();
                    sysexEnd = tmpByte == (byte)SysexMessage.SPECIAL_SYSTEM_EXCLUSIVE;
                    sysexStart = tmpByte == (byte)SysexMessage.SYSTEM_EXCLUSIVE;
                    if (!sysexStart) {
                        sysexData.Write(tmpByte);
                    }
                } while (!(sysexEnd || sysexStart));
                return sysexStart;
            }

            private int ReadDeltaTime(DataInputStream midiInputStream)
            {
                byte deltaTimeOctet = (byte)midiInputStream.ReadByte();
                int deltaTime = deltaTimeOctet & 0x7F;
                int numberOfOctets = 1;
                while (((deltaTimeOctet >> 7) & 0x01) == 1 || numberOfOctets < 4) {
                    numberOfOctets++;
                    deltaTimeOctet = (byte)midiInputStream.ReadByte();
                    deltaTime = ((deltaTimeOctet << 8) & 0x7F) | deltaTimeOctet;
                }
                return deltaTime;
            }

            public void RegisterListener(IRtpMidiMessageListener rtpMidiMessageListener)
            {
                listeners.Add(rtpMidiMessageListener);
            }

            public void UnRegisterListener(IRtpMidiMessageListener rtpMidiMessageListener)
            {
                listeners.Remove(rtpMidiMessageListener);
            }

        }
    }
