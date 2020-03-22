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

namespace MidiAranger.Droid.Source.midiplayer
{
    public struct MIDIHeaderInfo {
        public int Id;         // MThd signature
        public int Size;       // Always 6 (big-endian)
        public ushort Format;   // format
        public ushort TrackCount;   // count of tracks
        public ushort Ticks;    // ticks per quarter note
    }

    public struct MIDITrackInfo {
        public int Id; // Mtrk signature
        public int Length; // Length of track in bytes (big-endian)

    }

    public class MIDIMarker
    {
        public string Name { get; set; }
        public int Index { get; set; }
    }

    public class MIDITrack {
        public MIDITrackInfo Info;
        public int CurrentEventIndex;
        public byte LastCommand;
        public int AbsTime;
        public List<MIDIEvent> MidiEvents;
        public List<MIDIMarker> MidiMarkers;

        public MIDITrack()
        {
            MidiEvents = new List<MIDIEvent>();
            MidiMarkers = new List<MIDIMarker>();
            CurrentEventIndex = 0;
            LastCommand = 0;
            AbsTime = 0;
            Info = new MIDITrackInfo();
        }


    }

    public struct MIDIEvent {
        public int absTime;
        public byte[] MidiMessage;
    }

    public class MIDIFile {
        public byte[] _debugFileBuff = { 1 ,2};


        public int FileSize;
        public byte[] ByteBuff=null;
        public int PulsesPerQuarterNote;
        public MIDIHeaderInfo MidiHeaderInfo;
        public MIDITrackInfo MidiTrackInfo;
        public List<MIDITrack> Tracks;
        protected int currentOffset;
        public MIDIFile() {
            Tracks = new List<MIDITrack>();
            MidiHeaderInfo = new MIDIHeaderInfo();

        }
        public int InitMidiFile(string fileName)
        {
            //ByteBuff = File.ReadAllBytes(fileName);
            ByteBuff = DEBUGDATA.midifileData;
            currentOffset = 0;
            FileSize = ByteBuff.Length;
            ProcessMIDIHeader();
            ProcessMIDITracks();
            return 0;
        }

        protected void ProcessMIDIHeader()
        {
            
            MidiHeaderInfo.Id = GetUint();
            if (MidiHeaderInfo.Id != 0x4D546864) // MThd
                throw new System.Exception("MIDIFile incorrect file signature!");
            MidiHeaderInfo.Size = GetUint();
            MidiHeaderInfo.Format = GetUShort();
            MidiHeaderInfo.TrackCount = GetUShort();
            MidiHeaderInfo.Ticks = GetUShort();
        }

        protected void ProcessMIDITracks() {
            for (int i = 0; i < MidiHeaderInfo.TrackCount; i++)
            {
                MIDITrack track = new MIDITrack();
                ProcessMIDITrack(track);
                Tracks.Add(track);
            }
        }

        protected void ProcessMIDITrack(MIDITrack track) {
            ProcessMidiTrackInfo(ref track.Info);
            track.AbsTime = 0;
            track.LastCommand = 0;
            track.CurrentEventIndex = 0;
            track.MidiEvents = ProcessMidiEvents(track);
        }

        protected List<MIDIEvent> ProcessMidiEvents(MIDITrack track) {
            List<MIDIEvent> result = new List<MIDIEvent>();
            int _old;
            while (track.Info.Length>0) {
                _old = currentOffset;
                MIDIEvent midiEvent = LoadMidiEvent(track);
                track.AbsTime+= midiEvent.absTime;
                midiEvent.absTime = track.AbsTime;
                result.Add(midiEvent);
                track.Info.Length -= (currentOffset-_old);

            }
            return result;
        }

        protected MIDIEvent LoadMidiEvent(MIDITrack track) {
            MIDIEvent result = new MIDIEvent();
            result.absTime = GetVariableNumber();
            result.MidiMessage = GetMidiMessage(track);
            return result;
        }

        protected byte[] GetMidiMessage(MIDITrack track) {
            byte command = GetByte();
            byte[] data;
            if (!((command & 0x80) == 0x80)) // is not command?
            {
                if (track.LastCommand == 0)
                    throw new System.Exception("Bad midi file");
                command = track.LastCommand;
                currentOffset--; // Command byte - its 1 first data dyte
            }
            else
                track.LastCommand = command;

            data = GetMessageDataForCommand(track);
            byte[] result = new byte[data.Length + 1];
            result[0] = command;
            Array.Copy(data, 0, result, 1, data.Length);
            return result;
        }

        protected byte[] GetVarData() {
            byte[] result = new byte[GetVariableNumber()];
            for (int i = 0; i < result.Length; i++) result[i] = GetByte();
            return result;

        }

        protected byte[] GetMessageDataForCommand(MIDITrack track)
        {
            //Meta
            if (track.LastCommand == 0xFF)
            {
                byte type = GetByte();
                byte[] b = GetVarData();
                byte[] result = new byte[b.Length + 1];
                result[0] = type;
                Array.Copy(b, 0, result, 1, b.Length);
                return result;
            }
            else
            //SysEx
            if (track.LastCommand == 0xF0)
            {
                return GetVarData();
            }
            else
            if (track.LastCommand == 0xF7)
            {
                throw new NotImplementedException();
            }
            else
            // Channel message
            if (IsMasked(track.LastCommand, 0x8) || IsMasked(track.LastCommand, 0x9) || IsMasked(track.LastCommand, 0xA) || IsMasked(track.LastCommand, 0xB) || IsMasked(track.LastCommand, 0xE))
            {
                return new byte[2] { GetByte(), GetByte() };
            }
            else
            if (IsMasked(track.LastCommand, 0xC) || IsMasked(track.LastCommand, 0xD))
            {
                return new byte[1] { GetByte() };
            }
            return new byte[0];
        }

        protected bool IsMasked(byte b1, byte mask)
        {
            return (b1 >> 4) == mask;
        }

        protected void ProcessMidiTrackInfo(ref MIDITrackInfo track)
        {
            track.Id = GetUint();
            if (track.Id != 0x4D54726B) //MTrk
                throw new System.Exception("MIDI-File contains bad track signature");
            track.Length = GetUint();
        }

        protected int GetUint() {
            return GetUShort() << 16 | GetUShort();
        }

        protected ushort GetUShort() {
            return (ushort) (GetByte() << 8 | GetByte());
        }

        protected byte GetByte() {
            return ByteBuff[currentOffset++];
        }

        protected int GetVariableNumber()
        {
            int result = 0;
            byte byte_in;
            for (; ; )
            {
                byte_in = GetByte();
                result = ((result << 7) | (int)(byte_in & 0x7f));
                if ((byte_in & 0x80)==0)
                    return result;
            }
        }

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


        public MIDIPlayer(Context context) {
            this.context = context;

            MIDISession.GetInstance().Init(context);
            MIDISession.GetInstance().Start();

            Bundle rinfo = new Bundle();
            rinfo.PutString(MIDIConstants.RINFO_ADDR, "192.168.1.63");
            rinfo.PutInt(MIDIConstants.RINFO_PORT, 5008);
            rinfo.PutBoolean(MIDIConstants.RINFO_RECON, true);
            MIDISession.GetInstance().Connect(rinfo);
            Subscribe();
            currentSongPosition = 0;
            pulsesPerQuarterNote = 500000; // 120 BPM initial tempo

            thread = new System.Threading.Thread(new ThreadStart(Run));
            thread.Start();
            isPlaying = false;
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

        protected void ParseMidiMessage(byte[] mes)
        {
            if (mes[0] == 0xF8) { } // Sync
            else
            {
                message = mes;
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

        public void Run()
        {
            while (isPlaying)
            {
                // check state
                USleep(pulsesPerQuarterNote);
                PlayCurrentTrackPositions();
                currentSongPosition++;
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