using Java.IO;
using rtipmidi.messages;
using System;
using System.IO;

namespace rtpmidi.messages {

    public class RtpMidiEndSession : RtpMidiCommand {
        public int ProtocolVersion { get; protected set; }
        public int InitiatorToken { get; protected set; }

        public RtpMidiEndSession(int protocolVersion, int initiatorToken, int ssrc):base(CommandWord.BY, ssrc) {
            ProtocolVersion = protocolVersion;
            InitiatorToken = initiatorToken;
        }


        public override byte[] ToByteArray(){
            try
            {
                MemoryStream outputStream = new MemoryStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.Write(MIDI_COMMAND_HEADER1);
                dataOutputStream.Write(MIDI_COMMAND_HEADER2);
                dataOutputStream.Write(System.Text.Encoding.UTF8.GetBytes(CommandWord.ToString()));
                dataOutputStream.WriteInt(ProtocolVersion);
                dataOutputStream.WriteInt(InitiatorToken);
                dataOutputStream.WriteInt(Ssrc);
                dataOutputStream.Flush();
                return outputStream.ToArray();
            }
            catch(Exception e)
            {
                throw new System.IO.IOException(e.Message);
            }
        }
    }
}