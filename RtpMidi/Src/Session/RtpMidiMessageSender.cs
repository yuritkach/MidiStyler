using rtpmidi.messages;
namespace rtpmidi.session
{
    /**
     * An implementation of this interface is responsible for sending {@link AppleMidiMessage}s to {@link
     * AppleMidiServer}s.
     */
    public interface IRtpMidiMessageSender {

        /**
         * Sends the provided {@link AppleMidiMessage} to the corrresponding {@link AppleMidiServer}
         *
         * @param appleMidiMessage The message to send
         * @param appleMidiServer  The target server
         * @throws IOException When a communication failure happens
         */
        void Send(RtpMidiMessage rtpMidiMessage, model.RtpMidiServer rtpMidiServer);
    }
}