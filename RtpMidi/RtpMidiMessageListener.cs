using rtpmidi.messages;
using rtpmidi.model;
namespace rtpmidi
{
    public interface IRtpMidiMessageListener {

        /**
         * This method is called when a new MIDI message from the origin server is received
         *
         * @param midiCommandHeader The MIDI command meta information
         * @param message           The MIDI message
         * @param timestamp         Timestamp of this MIDI message
         */
        void OnMidiMessage(MidiCommandHeader midiCommandHeader, MidiMessage message, int timestamp);
    }
}