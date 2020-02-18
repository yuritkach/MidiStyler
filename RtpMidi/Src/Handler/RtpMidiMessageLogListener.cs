using Android.Util;
using rtpmidi.messages;
using rtpmidi.model;

namespace rtpmidi.handler { 
    class RtpMidiMessageLogListener:IRtpMidiMessageListener
    {
        public void OnMidiMessage(MidiCommandHeader midiCommandHeader, MidiMessage message,int timestamp)
        {
            Log.Debug("RtpMidi", "MIDI message: midiCommandHeader: {}, message: {}, timestamp: {}", midiCommandHeader, message, timestamp);
        }
    }
}