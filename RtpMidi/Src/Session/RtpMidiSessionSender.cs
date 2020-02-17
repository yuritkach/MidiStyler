using rtpmidi.model;

namespace rtpmidi.session {

    /**
     * The implementation of this interface is responsible for sending {@link MidiMessage} over the network.
     */
    public interface IRtpMidiSessionSender {

        /**
         * Sends a {@link MidiMessage} over the network
         *
         * @param message   The message to deliver
         * @param timestamp The timestamp of this message
         */
        void SendMidiMessage(MidiMessage message, long timestamp);
    }
}