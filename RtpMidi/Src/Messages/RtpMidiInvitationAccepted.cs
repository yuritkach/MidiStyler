namespace rtpmidi.messages
{ 

	public class RtpMidiInvitationAccepted:RtpMidiInvitation
    {

		public RtpMidiInvitationAccepted(int protocolVersion, int initiatorToken, int ssrc,
                                       string name):base(CommandWord.OK, protocolVersion, initiatorToken, ssrc, name)
        {    
		}

	}
}