using Android.Util;
using rtpmidi.messages;

namespace rtpmidi.handler { 

    class RtpMidiCommandLogListener:IRtpMidiCommandListener
    {
        public void OnMidiInvitation(RtpMidiInvitationRequest invitation, model.RtpMidiServer rtpMidiServer)
        {
            Log.Debug("RtpMidi","MIDI invitation: invitation: {}, appleMidiServer: {}", invitation, rtpMidiServer);
        }

        public void OnClockSynchronization(RtpMidiClockSynchronization clockSynchronization, model.RtpMidiServer rtpMidiServer)
        {
            Log.Debug("RtpMidi","MIDI clock synchronization: clockSynchronization: {}, appleMidiServer: {}", clockSynchronization,rtpMidiServer);
        }

        public void OnEndSession(RtpMidiEndSession rtpMidiEndSession, model.RtpMidiServer rtpMidiServer)
        {
            Log.Debug("RtpMidi","MIDI end session: rtpMidiEndSession: {}, rtpMidiServer: {}", rtpMidiEndSession,
                rtpMidiServer);
        }
    }
}