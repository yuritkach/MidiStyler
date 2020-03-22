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
        public int id;         // MThd signature
        public int size;       // Always 6 (big-endian)
        public ushort format;   // format
        public ushort tracks;   // count of tracks
        public ushort ticks;    // ticks per quarter note
    }

    public struct MIDITrackInfo {
        public int id; // Mtrk signature
        public int length; // Length of track in bytes (big-endian)

    }

    public class MIDITrack {
        public MIDITrackInfo info;
        public int currentEventIndex;
        public byte lastCommand;
        public int absTime;
        public List<MIDIEvent> MidiEvents;
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
        public MIDIHeaderInfo midiHeaderInfo;
        public MIDITrackInfo midiTrackInfo;
        public List<MIDITrack> tracks;
        protected int currentOffset;
        public MIDIFile() {

        }
        public int InitMidiFile(string fileName)
        {
            //ByteBuff = File.ReadAllBytes(fileName);
            ByteBuff = DEBUGDATA.midifileData;
            currentOffset = 0;
            FileSize = ByteBuff.Length;
            midiHeaderInfo = ProcessMIDIHeader();
            tracks = ProcessMIDITracks();
            return 0;
        }

        protected MIDIHeaderInfo ProcessMIDIHeader()
        {
            MIDIHeaderInfo result = new MIDIHeaderInfo();
            result.id = GetUint();
            if (result.id != 0x4D546864) // MThd
                throw new System.Exception("MIDIFile incorrect file signature!");
            result.size = GetUint();
            result.format = GetUShort();
            result.tracks = GetUShort();
            result.ticks = GetUShort();
            return result;
        }

        protected List<MIDITrack> ProcessMIDITracks() {
            List<MIDITrack> result = new List<MIDITrack>();
            for (int i = 0; i < midiHeaderInfo.tracks; i++)
            {
                MIDITrack track = new MIDITrack();
                ProcessMIDITrack(track);
                result.Add(track);
            }
            return result;
        }

        protected void ProcessMIDITrack(MIDITrack track) {
            track.info = ProcessMidiTrackInfo();
            track.absTime = 0;
            track.lastCommand = 0;
            track.currentEventIndex = 0;
            track.MidiEvents = ProcessMidiEvents(track);
        }

        protected List<MIDIEvent> ProcessMidiEvents(MIDITrack track) {
            List<MIDIEvent> result = new List<MIDIEvent>();
            int _old;
            while (track.info.length>0) {
                _old = currentOffset;
                MIDIEvent midiEvent = LoadMidiEvent(track);
                track.absTime+= midiEvent.absTime;
                midiEvent.absTime = track.absTime;
                result.Add(midiEvent);
                track.info.length -= (currentOffset-_old);

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
                if (track.lastCommand == 0)
                    throw new System.Exception("Bad midi file");
                command = track.lastCommand;
                currentOffset--; // Command byte - its 1 first data dyte
            }
            else
                track.lastCommand = command;

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
            if (track.lastCommand == 0xFF)
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
            if (track.lastCommand == 0xF0)
            {
                return GetVarData();
            }
            else
            if (track.lastCommand == 0xF7)
            {
                throw new NotImplementedException();
            }
            else
            // Channel message
            if (IsMasked(track.lastCommand, 0x8) || IsMasked(track.lastCommand, 0x9) || IsMasked(track.lastCommand, 0xA) || IsMasked(track.lastCommand, 0xB) || IsMasked(track.lastCommand, 0xE))
            {
                return new byte[2] { GetByte(), GetByte() };
            }
            else
            if (IsMasked(track.lastCommand, 0xC) || IsMasked(track.lastCommand, 0xD))
            {
                return new byte[1] { GetByte() };
            }
            
            
            return new byte[0];
        }
            protected bool IsMasked(byte b1, byte mask) {
            return (b1 >> 4) == mask;
        }

        protected MIDITrackInfo ProcessMidiTrackInfo()
        {
            MIDITrackInfo result = new MIDITrackInfo();
            result.id = GetUint();
            if (result.id != 0x4D54726B) //MTrk
                throw new System.Exception("MIDI-File contains bad track signature");
            result.length = GetUint();
            return result;
        }

        protected int GetUint() {
            int result = GetUShort();
            result = result << 16;
            result = result | GetUShort();
            return result;
        }

        protected ushort GetUShort() {
            ushort result = GetByte();
            result =(ushort)(result << 8);
            result =(ushort)(result | GetByte());
            return result;
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
            throw new System.Exception("eee");
        }

    }


    class MIDIPlayer
    {
        private bool isPlaying = false;
        public List<MIDITrack> Tracks { get; set; }
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
            pulsesPerQuarterNote = 500000; // 120 BPM

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

        public bool SendTestMIDI()
        {
            Log.Debug("Main", "send note on 35,7F");
            MIDISession.GetInstance().SendMessage(new byte[] { 0x90,0x35,0x7F});
            return true;
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
                //SendTestMIDI();
                PlayCurrentTrackPositions();
                currentSongPosition++;
            }

        }

        protected void PlayCurrentTrackPositions() {
            foreach (MIDITrack track in Tracks)
                for (int i = track.currentEventIndex; i < track.MidiEvents.Count; i++)
                {
                    if (track.MidiEvents[i].absTime == currentSongPosition)
                        if (track.MidiEvents[i].MidiMessage[0] == 0xFF)
                            ProcessMetaEvent(track.MidiEvents[i].MidiMessage);
                        else
                            MIDISession.GetInstance().SendMessage(track.MidiEvents[i].MidiMessage);
                    else
                        if (track.MidiEvents[i].absTime > currentSongPosition)
                    {
                        track.currentEventIndex = i;
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