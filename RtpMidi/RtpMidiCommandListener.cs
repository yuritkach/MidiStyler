using rtpmidi.messages;

namespace rtpmidi
{
    public interface IRtpMidiCommandListener:IEndSessionListener
    {
        /**
             * This method is called for every invitation request.
             *
             * param invitation      The invitation request
             * param rtpMidiServer The origin server of this message
             */
        void OnMidiInvitation(RtpMidiInvitationRequest invitation, model.RtpMidiServer rtpMidiServer);

        /**
         * This method is called for every clock synchronization request.
         *
         * param clockSynchronization The clock synchronization request
         * param rtpMidiServer      The origin server of this message
         */
        void OnClockSynchronization(RtpMidiClockSynchronization clockSynchronization,model.RtpMidiServer rtpMidiServer);
    }
}

