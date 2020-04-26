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
        private readonly Context context;
        public int currentSongPosition;
        public int msOnPulse;

        protected MIDIMarker currentMarker;
        protected MIDIMarker nextMarker;


        private System.Threading.Thread thread;
        private long difTime1, difTime2;
        public List<byte> currentPressedNotes;
        public MIDIStyle Style { get; protected set; }

        protected int pulsesPerQuarterNote;

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
            msOnPulse = 500000; // 120 BPM initial tempo
            pulsesPerQuarterNote = style.MidiSection.MidiHeaderInfo.Ticks;


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
            currentMarker = GetMarkerOnSection(StyleSections.MainA);
            nextMarker = currentMarker;
            GotoSection(Common.GetSectionCode(currentMarker.Name),true);
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
                case 0x51: msOnPulse = (mes[2] * 0x10000) + (mes[3] * 0x100) + mes[4]; break;// SetTempo
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

        protected MIDIMarker GetMarkerOnSection(Common.StyleSections section)
        {
            return Tracks[0].MidiMarkers.Where(j => j.Name == Common.GetSectionName(section)).FirstOrDefault();
        }

        public void GotoSection(Common.StyleSections section, bool instant)
        {
            currentMarker = GetMarkerOnSection(section);
            Tracks[0].CurrentEventIndex = currentMarker.StartIndex;
            currentSongPosition = Tracks[0].MidiEvents[currentMarker.StartIndex].absTime;
            AllNotesOff();
        }

        protected void AllNotesOff()
        {
            MIDISession session = MIDISession.GetInstance();
            byte[] b = new byte[3] { 0, 123, 0 };
            for (byte i = 0xB0; i < 0xC0; i++)
            {
                b[0] =i;
                session.SendMessage(b);
            }

        }

        protected StyleSections GetNextSection()
        {
            switch (Common.GetSectionCode(currentMarker.Name))
            {
                case StyleSections.MainA:return StyleSections.MainA;
                case StyleSections.MainB: return StyleSections.MainB;
                case StyleSections.FillInAB: return StyleSections.MainB;
                case StyleSections.FillInBA: return StyleSections.MainA;
                case StyleSections.FillInAA: return StyleSections.MainA;
                case StyleSections.FillInBB: return StyleSections.MainB;
                case StyleSections.IntroA: return StyleSections.MainA;

                default:return StyleSections.EndingA;

            }

        }

        public event EventHandler<OnTactEventArgs> OnTactEvent;

        public void Run()
        {
            
            EventHandler<OnTactEventArgs> onTactHandler = OnTactEvent;
            OnTactEventArgs e = new OnTactEventArgs();
            int tact = 0;
            int counter = 1;
            while (isPlaying)
            {
                // check state
                USleep(msOnPulse);
                PlayCurrentMarkerPositions();
                currentSongPosition++;
                counter++;
                if (counter > pulsesPerQuarterNote)
                {
                    tact++;
                    e.CurrentTact = tact % 4;
                    OnTactEvent(this,e);
                    counter = 1;
                }
                if (currentSongPosition > Tracks[0].MidiEvents[currentMarker.StopIndex].absTime)
                {
                    GotoSection(GetNextSection(),false);
                }
            }

        }

        protected void PlayCurrentMarkerPositions() {
            MIDISession session = MIDISession.GetInstance();
            foreach (MIDITrack track in Tracks)
            //    for (int i = track.CurrentEventIndex; i < track.MidiEvents.Count; i++)
                for (int i = currentMarker.StartIndex; i < currentMarker.StopIndex; i++)
                {
                    if (track.MidiEvents[i].absTime == currentSongPosition)
                        if (track.MidiEvents[i].MidiMessage[0] == 0xFF)
                            ProcessMetaEvent(track.MidiEvents[i].MidiMessage);
                        else
                            session.SendMessage(Style.ProcessMessage(track, i));
                    else
                        if (track.MidiEvents[i].absTime > currentSongPosition)
                        {
                            track.CurrentEventIndex = i;
                            break;
                        }   
                }
        }
    }

    public class OnTactEventArgs : EventArgs
    {
        public int CurrentTact { get; set; }
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