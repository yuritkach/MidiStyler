//import io.github.leovr.rtipmidi.messages.AppleMidiClockSynchronization;
//import io.github.leovr.rtipmidi.messages.AppleMidiInvitationRequest;
//import io.github.leovr.rtipmidi.model.AppleMidiServer;

//import javax.annotation.Nonnull;

namespace rtpmidi
{
    public interface IRtpMidiCommandListener:IEndSessionListener
    {
        /**
             * This method is called for every invitation request.
             *
             * param invitation      The invitation request
             * param appleMidiServer The origin server of this message
             */
        void onMidiInvitation(AppleMidiInvitationRequest invitation, AppleMidiServer appleMidiServer);

        /**
         * This method is called for every clock synchronization request.
         *
         * param clockSynchronization The clock synchronization request
         * param appleMidiServer      The origin server of this message
         */
        void onClockSynchronization(AppleMidiClockSynchronization clockSynchronization,AppleMidiServer appleMidiServer);
    }
}

