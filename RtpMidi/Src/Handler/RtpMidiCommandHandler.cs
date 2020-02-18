using Android.Util;
using Java.IO;
using Java.Lang;
using Java.Util;
using rtpmidi.messages;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace rtpmidi.handler { 

    public class RtpMidiCommandHandler {

        private static int NUMBER_OF_PADDING_BYTES = 3;
        private static int PROTOCOL_VERSION = 2;
        private static int COMMAND_BUFFER_LENGTH = 2;
        private static string NUL_TERMINATOR = "\u0000";
        private List<IRtpMidiCommandListener> listeners = new List<IRtpMidiCommandListener>();

        public RtpMidiCommandHandler() {
            listeners.Add(new RtpMidiCommandLogListener());
        }

        public void handle(byte[] data, RtpMidiServer appleMidiServer) {
            DataInputStream dataInputStream = new DataInputStream(new MemoryStream(data));
            try {
                byte header1 = (byte) dataInputStream.ReadByte();
                if (header1 != RtpMidiCommand.MIDI_COMMAND_HEADER1) {
                    Log.Info("RtpMidi","Header did not contain first MIDI command header");
                    return;
                }
                byte header2 = (byte) dataInputStream.ReadByte();
                if (header2 != RtpMidiCommand.MIDI_COMMAND_HEADER2) {
                    Log.Info("RtpMidi","Header did not contain second MIDI command header");
                    return;
                }
                byte[] commandBuffer = new byte[COMMAND_BUFFER_LENGTH];
                int commandBytesRead = dataInputStream.Read(commandBuffer);
                if (commandBytesRead != COMMAND_BUFFER_LENGTH) {
                    Log.Info("RtpMidi","The number of command bytes: {} did not match: {}", commandBytesRead, COMMAND_BUFFER_LENGTH);
                    return;
                }
                string command = Encoding.UTF8.GetString(commandBuffer);
                CommandWord commandWord;
                try
                {
                    commandWord = (CommandWord) System.Enum.Parse(typeof(CommandWord), command);
                }
                catch (IllegalArgumentException e) {
                    Log.Info("RtpMidi","Could not parse command word from: {}", command);
                    return;
                }
                switch (commandWord)
                {
                    case CommandWord.IN:
                        HandleInvitation(dataInputStream, appleMidiServer);
                        break;
                    case CommandWord.CK:
                        HandleSynchronization(dataInputStream, appleMidiServer);
                        break;
                    case CommandWord.BY:
                        HandleEndSession(dataInputStream, appleMidiServer);
                        break;
                }
                }
            catch (System.IO.IOException e)
            {
                Log.Error("RtpMidi","IOException while parsing message", e);
            }
        }

        private void HandleEndSession(DataInputStream dataInputStream, RtpMidiServer rtpMidiServer)
        {
            int protocolVersion = dataInputStream.ReadInt();
            if (protocolVersion != PROTOCOL_VERSION)
            {
                Log.Info("RtpMidi","Protocol version: {} did not match version {}", protocolVersion, PROTOCOL_VERSION);
                return;
            }
            int initiatorToken = dataInputStream.ReadInt();
            int ssrc = dataInputStream.ReadInt();
            foreach (IRtpMidiCommandListener listener in listeners)
            {
                listener.OnEndSession(new RtpMidiEndSession(protocolVersion, initiatorToken, ssrc), rtpMidiServer);
            }
        }

        private void HandleSynchronization(DataInputStream dataInputStream, RtpMidiServer rtpMidiServer)
        {
            int ssrc = dataInputStream.ReadInt();
            byte count = (byte) dataInputStream.ReadByte();
            int paddingBytes = dataInputStream.Read(new byte[NUMBER_OF_PADDING_BYTES]);
            if (paddingBytes != NUMBER_OF_PADDING_BYTES)
            {
                Log.Info("RtpMidi","The number of padding bytes: {} did not match: {}", paddingBytes, NUMBER_OF_PADDING_BYTES);
                return;
            }
            long timestamp1 = dataInputStream.ReadLong();
            long timestamp2 = dataInputStream.ReadLong();
            long timestamp3 = dataInputStream.ReadLong();
            foreach (IRtpMidiCommandListener listener in listeners)
            {
                listener.OnClockSynchronization(new RtpMidiClockSynchronization(ssrc, count, timestamp1, timestamp2, timestamp3),rtpMidiServer);
            }
        }

        private void HandleInvitation(DataInputStream dataInputStream, RtpMidiServer rtpMidiServer)
        {
            int protocolVersion = dataInputStream.ReadInt();
            if (protocolVersion != PROTOCOL_VERSION)
            {
                Log.Info("RtpMidi","Protocol version: {} did not match version {}", protocolVersion, PROTOCOL_VERSION);
                return;
            }
            int initiatorToken = dataInputStream.ReadInt();
            int ssrc = dataInputStream.ReadInt();
            //Scanner scanner = new Scanner(dataInputStream).UseDelimiter(NUL_TERMINATOR);
            //if (!scanner.HasNext)
            //{
            //    Log.Info("RtpMidi","Could not find \\0 terminating string");
            //    return;
            //}
            //string name = scanner.Next();
            string name = dataInputStream.ReadLine();

            foreach (IRtpMidiCommandListener listener in listeners)
            {
                listener.OnMidiInvitation(new RtpMidiInvitationRequest(protocolVersion, initiatorToken, ssrc, name),rtpMidiServer);
            }
        }

        public void RegisterListener(IRtpMidiCommandListener rtpMidiCommandListener)
        {
            listeners.Add(rtpMidiCommandListener);
        }

        public void UnRegisterListener(IRtpMidiCommandListener rtpMidiCommandListener)
        {
            listeners.Remove(rtpMidiCommandListener);
        }

    }
}