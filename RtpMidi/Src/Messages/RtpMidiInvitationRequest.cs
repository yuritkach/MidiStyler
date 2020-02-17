namespace rtpmidi.messages { 

    public class RtpMidiInvitationRequest:RtpMidiInvitation {

        public RtpMidiInvitationRequest(int protocolVersion, int initiatorToken, int ssrc,
                                      string name):base(CommandWord.IN, protocolVersion, initiatorToken, ssrc, name)
        {    
        }

    }
}