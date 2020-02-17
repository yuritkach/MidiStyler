using Java.IO;
using System.IO;

namespace rtpmidi.messages { 

    public abstract class RtpMidiInvitation:RtpMidiCommand {

    public int ProtocolVersion { get; set;}
    public int InitiatorToken { get; set; }
    public string Name { get; set; }

    public RtpMidiInvitation(CommandWord commandWord, int protocolVersion, int initiatorToken,
                        int ssrc, string name):base(commandWord,ssrc) {
        ProtocolVersion = protocolVersion;
        InitiatorToken = initiatorToken;
        Name = name;
    }

    public override byte[] ToByteArray() {
            try {
                MemoryStream outputStream = new MemoryStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.Write(MIDI_COMMAND_HEADER1);
                dataOutputStream.Write(MIDI_COMMAND_HEADER2);
                dataOutputStream.Write(System.Text.Encoding.UTF8.GetBytes(CommandWord.ToString()));
                dataOutputStream.WriteInt(ProtocolVersion);
                dataOutputStream.WriteInt(InitiatorToken);
                dataOutputStream.WriteInt(Ssrc);
                dataOutputStream.Write(System.Text.Encoding.UTF8.GetBytes(Name));
                dataOutputStream.WriteByte(0);
                dataOutputStream.Flush();
                return outputStream.ToArray();
            }
            catch (System.Exception e)
            {
                throw new System.IO.IOException(e.Message);
            }
    }
}
}