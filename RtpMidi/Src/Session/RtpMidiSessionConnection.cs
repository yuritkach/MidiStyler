using Android.Util;
using Java.Lang;
using Java.Util;
using rtpmidi.messages;
using rtpmidi.model;
using System;
using System.Collections.Generic;
using System.IO;

namespace rtpmidi.session {
    /**
     * This class represents a connection between the local session and the remote {@link AppleMidiServer}. The connections
     * is able to send {@link MidiMessage}s to the {@link AppleMidiMessageSender}.
     */
    class RtpMidiSessionConnection:IRtpMidiSessionSender
    {
        public RtpMidiSession RtpMidiSession { get; protected set; }
        public model.RtpMidiServer RtpMidiServer { get; protected set; }
        public int Ssrc { get; protected set; }
        public IRtpMidiMessageSender RtpMidiMessageSender { get; protected set; }

        private short sequenceNumber = (short)new System.Random().Next(Short.MaxValue + 1);

        public RtpMidiSessionConnection(RtpMidiSession rtpMidiSession,model.RtpMidiServer rtpMidiServer, int ssrc, IRtpMidiMessageSender rtpMidiMessageSender)
        {
            RtpMidiSession = rtpMidiSession;
            RtpMidiServer = rtpMidiServer;
            Ssrc = ssrc;
            RtpMidiMessageSender = rtpMidiMessageSender;
        }


        public void SendMidiMessage(MidiMessage message, long timestamp)
        {
            sequenceNumber++;
            long currentTimeIn100Microseconds = RtpMidiSession.GetCurrentTimestamp();
            int rtpTimestamp = ((int)currentTimeIn100Microseconds);
            RtpHeader rtpHeader =
                new RtpHeader((byte)2, false, false, (byte)0, false, (byte)97, sequenceNumber, rtpTimestamp, Ssrc);
            Log.Debug("RtpMidi","Sending RTP-Header: {}", rtpHeader);
            bool b = message.Length > 15;
            MidiCommandHeader midiCommandHeader =
                new MidiCommandHeader(b, false, false, false, (short)message.Length, rtpHeader);
            var commandCol = new List<MidiTimestampPair>();
            commandCol.Add(new MidiTimestampPair(0, message));    
            RtpMidiMessage rtpMidiMessage = new RtpMidiMessage(midiCommandHeader, commandCol);

            try
            {
                RtpMidiMessageSender.Send(rtpMidiMessage, RtpMidiServer);
            }
            catch (IOException e)
            {
                Log.Error("RtpMidi","Error sending MidiMessage to {}", RtpMidiServer, e);
            }
        }
    }
}