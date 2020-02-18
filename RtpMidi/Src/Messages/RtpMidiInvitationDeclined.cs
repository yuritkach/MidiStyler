namespace rtpmidi.messages { 

    public class RtpMidiInvitationDeclined:RtpMidiInvitation {

        public RtpMidiInvitationDeclined(int protocolVersion, int initiatorToken, int ssrc,
                                       string name):base(CommandWord.NO, protocolVersion, initiatorToken, ssrc, name)
        {
        }

    }
}
