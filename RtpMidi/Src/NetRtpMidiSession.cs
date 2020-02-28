using rtpmidi.model;
using rtpmidi.session;

namespace rtipmidi
{

/**
 * {@link AppleMidiSession} which converts {@link MidiMessage} to {@link javax.sound.midi.MidiMessage}
 */
public abstract class NetRtpMidiSession:RtpMidiSession {

    
    private NetMidiMessageConverter midiMessageConverter = new NetMidiMessageConverter();

        /**
         * This method is called when a new {@link javax.sound.midi.MidiMessage} is received
         *
         * @param message   The MIDI-message
         * @param timestamp The timestamp of this message
         */

        protected override void OnMidiMessage(MidiMessage message, long timestamp) {
        OnMidiMessage(midiMessageConverter.Convert(message), timestamp);
    }

   

    /**
     * This method sends a {@link javax.sound.midi.MidiMessage}
     *
     * @param message   The message to deliver
     * @param timestamp The timestamp of this message
     */
    public override void SendMidiMessage(MidiMessage message, long timestamp) {
        MidiMessage midiMessage = midiMessageConverter.Convert(message);
        SendMidiMessage(midiMessage, timestamp);
    }
}
}