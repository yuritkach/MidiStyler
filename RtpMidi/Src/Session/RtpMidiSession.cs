using Android.Util;
using rtpmidi.messages;
using rtpmidi.model;
using System;

namespace rtpmidi.session
{

    /**
    * This class represents a single MIDI session with a remote server. It simplifies the methods of {@link
    * RtpMidiMessageListener} and {@link RtpMidiCommandListener} for the subclasses.
    */
    public abstract class RtpMidiSession:IRtpMidiMessageListener, IRtpMidiCommandListener {

        public long OffsetEstimate { get; set; }
        public IRtpMidiSessionSender Sender { get; set; }
        protected int timestampOffset = new Random().Next();

        /**
        * Returns the current timestamp in 100 microseconds. The default implementation uses the JVM startup time as
        * reference.
        *
        * @return The timestamp in 100 microseconds or -1 if the session does not care about the timestamp
        */
        public long GetCurrentTimestamp() {
            return timestampOffset + Java.Lang.JavaSystem.CurrentTimeMillis() * 10;
        }

    
        public virtual void OnMidiMessage(MidiCommandHeader midiCommandHeader, MidiMessage message, int timestamp) {
            OnMidiMessage(message, timestamp + OffsetEstimate);
        }

        /**
        * Called for every received MIDI messages
        *
        * @param message   The MIDI message
        * @param timestamp The timestamp of the message
        */
        protected abstract void OnMidiMessage(MidiMessage message, long timestamp);

        /**
        * Sends the provided MIDI-message via {@link AppleMidiSessionSender}
        *
        * @param message   The {@link MidiMessage} to deliver
        * @param timestamp The timestamp of the message
        */
        public virtual void SendMidiMessage(MidiMessage message, long timestamp) {
            if (Sender == null)
            {
                Log.Debug("RtpMidi","No sender available. Not sending message");
                return;
            }
            Sender.SendMidiMessage(message, timestamp);
        }

    
        public void OnMidiInvitation(RtpMidiInvitationRequest invitation,model.RtpMidiServer rtpMidiServer)
        {
        }

    
        public void OnClockSynchronization(RtpMidiClockSynchronization clockSynchronization, model.RtpMidiServer rtpMidiServer)
        {
        }

        public void OnEndSession(RtpMidiEndSession rtpMidiEndSession, model.RtpMidiServer rtpMidiServer)
        {
            OnEndSession();
        }

        /**
        * Called on end session. Any clean-up can happen here.
        */
        protected void OnEndSession()
        {
        }
    }
}