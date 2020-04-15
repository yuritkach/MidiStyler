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

        public MIDIPlayer(Context context) {
            this.context = context;

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

        public enum StyleSections { IntroA, IntroB, IntroC, MainA, MainB, MainC, MainD, FillInAA, FillInBB, FillInCC, FillInDD, FillInBA, FillInAB, EndingA, EndingB, EndingC }
        public string SectionName(StyleSections styleSection)
        {
            switch (styleSection)
            {
                case StyleSections.IntroA:return "Intro A";
                case StyleSections.IntroB: return "Intro B";
                case StyleSections.IntroC: return "Intro C";
                case StyleSections.MainA: return "Main A";
                case StyleSections.MainB: return "Main B";
                case StyleSections.MainC: return "Main C";
                case StyleSections.MainD: return "Main D";
                case StyleSections.FillInAA: return "Fill In AA";
                case StyleSections.FillInBB: return "Fill In BB";
                case StyleSections.FillInCC: return "Fill In CC";
                case StyleSections.FillInDD: return "Fill In DD";
                case StyleSections.FillInBA: return "Fill In BA";
                case StyleSections.FillInAB: return "Fill In AB";
                case StyleSections.EndingA: return "Ending A";
                case StyleSections.EndingB: return "Ending B";
                case StyleSections.EndingC: return "Ending C";
                default: return null;
            }
        }


        public void GotoSection(StyleSections section)
        {
            MIDIMarker marker = Tracks[0].MidiMarkers.Where(j => j.Name == SectionName(section)).FirstOrDefault();
            Tracks[0].CurrentEventIndex = marker.Index;
            currentSongPosition = Tracks[0].MidiEvents[marker.Index].absTime;

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
                    GotoSection(StyleSections.MainB);
                }
            }

        }

        protected void PlayCurrentTrackPositions() {
            foreach (MIDITrack track in Tracks)
                for (int i = track.CurrentEventIndex; i < track.MidiEvents.Count; i++)
                {
                    if (track.MidiEvents[i].absTime == currentSongPosition)
                        if (track.MidiEvents[i].MidiMessage[0] == 0xFF)
                            ProcessMetaEvent(track.MidiEvents[i].MidiMessage);
                        else
                            MIDISession.GetInstance().SendMessage(track.MidiEvents[i].MidiMessage);
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