using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using System.IO;
using Xamarin.Forms;
using midi;
using midi.events;
using Android.Util;
using Java.Lang;
using System.Threading;
using MidiAranger.Droid.Source.common;
using MidiAranger.Droid.Source.styler;
using static MidiAranger.Droid.Source.common.Common;

namespace MidiAranger.Droid.Source.midiplayer
{
   

  
   
    public class MIDIFile {
        
    }


    class MIDIPlayer
    {
        private bool isPlaying = false;
        public List<MIDITrack> Tracks;
        private byte[] message;
        private readonly Context context;
        public int currentSongPosition;
        public int pulsesPerQuarterNote;



        private System.Threading.Thread thread;
        private long difTime1, difTime2;
        public List<byte> currentPressedNotes;
        public MIDIStyle Style { get; protected set; }

        public MIDIPlayer(Context context, MIDIStyle style) {
            this.context = context;
            Style = style;

            MIDISession.GetInstance().Init(context);
            MIDISession.GetInstance().Start();

            Bundle rinfo = new Bundle();
            rinfo.PutString(MIDIConstants.RINFO_ADDR, "192.168.1.63");
            rinfo.PutInt(MIDIConstants.RINFO_PORT, 5008);
            rinfo.PutBoolean(MIDIConstants.RINFO_RECON, true);
            MIDISession.GetInstance().Connect(rinfo);
           
            currentSongPosition = 0;
            pulsesPerQuarterNote = 500000; // 120 BPM initial tempo

            thread = new System.Threading.Thread(new ThreadStart(Run));
            thread.Start();
            isPlaying = false;
            currentPressedNotes = new List<byte>();
            Subscribe();
        }


        public void Start()
        {
            currentSongPosition = 0;
            isPlaying = true;
        }

        public void Stop()
        {
            isPlaying = false;
        }

        public void Continue()
        {
            isPlaying = true;
        }


        private void Subscribe()
        {
            MessagingCenter.Subscribe<MIDIReceivedEvent>(this, "MIDIReceivedEvent", OnMIDIReceivedEvent);
        }

        protected void OnMIDIReceivedEvent(MIDIReceivedEvent _event)
        {
            ParseMidiMessage(_event.message);
        }

        protected void AddToCurrentPressed(byte note)
        {
            if(!currentPressedNotes.Exists(n=>n==note))
                currentPressedNotes.Add(note);
        }
        protected void RemoveFromCurrentPressed(byte note)
        {
             currentPressedNotes.Remove(note);
        }

        protected void ParseMidiMessage(byte[] mes)
        {
            if (mes[0] == 0xF8) { } // Sync
            else
            {
                message = mes;
                if (Common.IsMasked(mes[0], 0x9))
                    AddToCurrentPressed(mes[1]);
                if (Common.IsMasked(mes[0], 0x8))
                    RemoveFromCurrentPressed(mes[1]);


            }
        }

        protected void ProcessMetaEvent(byte[] mes)
        {
            switch (mes[1])
            {
                case 0x51: pulsesPerQuarterNote = (mes[2] * 0x10000) + (mes[3] * 0x100) + mes[4]; break;// SetTempo
                default:break;
            }

        }


        protected void USleep(int waitTime)
        {
            if (waitTime == 0) return;
            difTime1 = JavaLangSystem.NanoTime();
            do { difTime2 = JavaLangSystem.NanoTime(); }
            while ((difTime2 - difTime1) < waitTime);
        }

        

        public void GotoSection(Common.StyleSections section)
        {
            MIDIMarker marker = Tracks[0].MidiMarkers.Where(j => j.Name == Common.GetSectionName(section)).FirstOrDefault();
            Tracks[0].CurrentEventIndex = marker.StartIndex;
            currentSongPosition = Tracks[0].MidiEvents[marker.StartIndex].absTime;

        }

        public void Run()
        {
            while (isPlaying)
            {
                // check state
                USleep(pulsesPerQuarterNote);
                PlayCurrentTrackPositions();
                currentSongPosition++;
                if (currentSongPosition > 34000)
                {
                    GotoSection(StyleSections.IntroB);
                }
            }

        }

        private byte[] ba;
        protected void PlayCurrentTrackPositions() {
            foreach (MIDITrack track in Tracks)
                for (int i = track.CurrentEventIndex; i < track.MidiEvents.Count; i++)
                {
                    if (track.MidiEvents[i].absTime == currentSongPosition)
                        if (track.MidiEvents[i].MidiMessage[0] == 0xFF)
                            ProcessMetaEvent(track.MidiEvents[i].MidiMessage);
                        else
                        {
                            ba = Style.ProcessMessage(track, i);
                            MIDISession.GetInstance().SendMessage(ba);
                        }
                        
                    else
                        if (track.MidiEvents[i].absTime > currentSongPosition)
                    {
                        track.CurrentEventIndex = i;
                        break;
                    }   

                        
                }
        }
    }

    public static class JavaLangSystem
    {
        static readonly IntPtr class_ref;
        static readonly IntPtr id_nanoTime;
        public readonly static long Avg;

        static JavaLangSystem()
        {
            class_ref = JNIEnv.FindClass("java/lang/System");
            id_nanoTime = JNIEnv.GetStaticMethodID(class_ref, "nanoTime", "()J");

            for (int i = 0; i < 10; i++)
            {
                NanoTime();
            }

            long start = NanoTime();
            for (int i = 0; i < 1000; i++)
            {
                NanoTime();
            }
            Avg = (NanoTime() - start) / 1000;
        }

        [Register("nanoTime", "()J", "")]
        public static long NanoTime()
        {
            return JNIEnv.CallStaticLongMethod(class_ref, id_nanoTime);
        }
    }
}