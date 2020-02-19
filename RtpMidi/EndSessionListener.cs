using rtpmidi.messages;
using rtpmidi.model;

namespace rtpmidi
{

    public interface IEndSessionListener {

        /**
         * This method is called when the origin server ends this session
         *
         * param rtpMidiEndSession The end session request
         * param rtpMidiServer     The origin server of this message
         */
        void OnEndSession(RtpMidiEndSession appleMidiEndSession,model.RtpMidiServer rtpMidiServer);
    }
}