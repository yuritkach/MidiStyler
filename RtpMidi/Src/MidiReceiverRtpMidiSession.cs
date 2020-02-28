using Android.Media.Midi;
using Java.Util;
using rtpmidi;
using rtpmidi.error;
using rtpmidi.model;
using System;
using System.Collections.Generic;

namespace rtipmidi {
    /**
     * {@link AppleMidiSession} with one or more {@link Receiver} as the receiver(s) of the MIDI messages
     */
    public class MidiReceiverRtpMidiSession : NetRtpMidiSession {

        private List<MidiReceiver> receivers;

        public MidiReceiverRtpMidiSession(MidiReceiver receiver)
        {
            try
            {
                receivers = new List<MidiReceiver>();
                receivers.Add(receiver);
            }
            catch (Exception e)
            {
                throw new RtpMidiSessionInstantiationException(e.Message);
            }
    }

    public MidiReceiverRtpMidiSession(List<MidiReceiver> receivers)
    {
        this.receivers = receivers;
    }

    
        protected override void OnMidiMessage(MidiMessage message, long timestamp)
        {
            foreach (MidiReceiver receiver in receivers)
            {
                receiver.Send(message.Data,0, message.Data.Length);
            }
        }
    }
}