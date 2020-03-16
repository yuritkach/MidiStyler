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

namespace MidiAranger.Droid.Source.midiplayer
{
    public struct MIDIHeaderInfo {
        public uint id;         // MThd signature
        public uint size;       // Always 6 (big-endian)
        public ushort format;   // format
        public ushort tracks;   // count of tracks
        public ushort ticks;    // ticks per quarter note
    }

    public struct MIDITrackInfo {
        public uint id; // Mtrk signature
        public uint length; // Length of track in bytes (big-endian)

    }

    public class MIDITrack {
        public MIDITrackInfo info;
        public int bufferOffset;
        public byte lastCommand;
        public uint absTime;
        public List<MIDIEvent> MidiEvents;
    }

    public struct MIDIEvent {
        public uint absTime;
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
        protected MIDITrack currentTrack;
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
                throw new Exception("MIDIFile incorrect file signature!");
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
                track.absTime = 0;
                currentTrack = track;
                ProcessMIDITrack(track);
                result.Add(track);
            }
            return result;
        }

        protected void ProcessMIDITrack(MIDITrack track) {
            track.info = ProcessMidiTrackInfo();
            track.lastCommand = 0;
            track.MidiEvents = ProcessMidiEvents(track);
            
        }

        protected List<MIDIEvent> ProcessMidiEvents(MIDITrack track) {
            List<MIDIEvent> result = new List<MIDIEvent>();
            while (track.info.length>0) {
                MIDIEvent _ev = LoadMidiEvent(ref track.info.length);

                result.Add(_ev);
            }
            return result;
        }

        protected MIDIEvent LoadMidiEvent(ref uint length) {
            MIDIEvent result = new MIDIEvent();
            result.absTime = GetVariableNumber(ref length);
            result.MidiMessage = GetMidiMessage(ref length);
            return result;
        }

        protected byte[] GetMidiMessage(ref uint length) {
            byte command = GetByte();
            byte[] data;
            length--;

            if (!((command & 0x80) == 0x80)) // is not command?
            {
                if (currentTrack.lastCommand == 0)
                    throw new Exception("Bad midi file");
                command = currentTrack.lastCommand;
                currentOffset--; // Command byte - its 1 first data dyte
            }
            else
                currentTrack.lastCommand = command;

            data = GetMessageDataForCommand(command, ref length);
            byte[] result = new byte[data.Length + 1];
            result[0] = command;
            for (int i = 0; i < data.Length; i++)
                result[i + 1] = data[i];
            return result;
        }

        protected byte[] GetMessageDataForCommand(byte command, ref uint length)
        {
            //Meta
            if (command == 0xFF)
            {
                
                byte type = GetByte();
                uint oldLength = length;
                var oldOffset = currentOffset;
                uint len = GetVariableNumber(ref length);
                var lensize=currentOffset-oldOffset;
                uint resultLength =(uint) (len + lensize + 1);
                byte[] result = new byte[resultLength];
                currentOffset = oldOffset;
                result[0] = type;
                for (int i = 0; i < (resultLength-1); i++)
                {
                    result[i+1] = GetByte();
                }
                length = length - resultLength+1;
                return result;
            }
            else
            //SysEx
            if (command == 0xF0)
            {
                throw new NotImplementedException();
            }
            else
            if (command == 0xF7)
            {
                throw new NotImplementedException();
            }
            else
            // Channel message
            if (IsMasked(command, 0x8) || IsMasked(command, 0x9) || IsMasked(command, 0xA) || IsMasked(command, 0xB) || IsMasked(command, 0xE))
            {
                length -= 2;
                return new byte[2] { GetByte(), GetByte() };
            }
            else
            if (IsMasked(command, 0xC) || IsMasked(command, 0xD))
            {
                length--;
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
                throw new Exception("MIDI-File contains bad track signature");
            result.length = GetUint();
            return result;
        }

        protected uint GetUint() {
            uint result = GetUShort();
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

        protected uint GetVariableNumber(ref uint length) {
                uint result = 0;
                byte byte_in;
                for (; ; )
                {
                    byte_in = GetByte();
                    length--;
                    result = ((result << 7) | (uint)(byte_in & 0x7f));
                    if ((byte_in & 0x80)==0)
                        return result;
                }
        }

    }


    class MIDIPlayer
    {
        private bool isPlaying = false;
        public List<MIDITrack> Tracks { get; set; }
        private byte[] message;
        private int midiClockCount;
        private Context context;

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


        }

        private void Subscribe()
        {
            MessagingCenter.Subscribe<MIDIReceivedEvent>(this, "MIDIReceivedEvent", OnMIDIReceivedEvent);
        }

        public bool SendTestMIDI()
        {
            Log.Debug("Main", "sendTestMidi 41,127");
            Bundle testMessage = new Bundle();
            testMessage.PutInt(MIDIConstants.MSG_COMMAND, 0x09);
            testMessage.PutInt(MIDIConstants.MSG_CHANNEL, 0);
            testMessage.PutInt(MIDIConstants.MSG_NOTE, 41);
            testMessage.PutInt(MIDIConstants.MSG_VELOCITY, 127);
            //        MIDIMessage m = MIDIMessage.newUsing(testMessage);
            MIDISession.GetInstance().SendMessage(testMessage);
            //        MIDISession.getInstance().sendMessage(41,127);
            //        MIDIMessage message = MIDISession.getInstance().sendNote(41,127);
            //        if(message != null) {
            //            MIDISession.getInstance().sendNote(41,127);
            //        }
            return true;
        }

        protected void OnMIDIReceivedEvent(MIDIReceivedEvent _event)
        {
            ParseMidiMessage(_event.message);
        }

        protected void ParseMidiMessage(byte[] mes)
        {
            if (mes[0] == 248)
            {
         //       midiClockCount++;

            }
            else
            {
                message = mes;
            }
        }



        protected void USleep(uint waitTime)
        {
            if (waitTime == 0) return;
            long time1, time2;

             time1 = JavaLangSystem.NanoTime();
            do
            {
                time2 = JavaLangSystem.NanoTime();
            }
            while ((time2-time1) < waitTime*1000000);
        }

        public void Run()
        {
            isPlaying = true;
            while (isPlaying)
            {
                USleep(5000);
                PlayCurrentTrackPositions();
            }

        }

        protected void PlayCurrentTrackPositions() {
            foreach (MIDITrack track in Tracks)
            {
                var _event = track.MidiEvents.Where(p => p.absTime > track.absTime).Min(n => n.absTime);


            }
        }



    }

    public static class JavaLangSystem
    {
        static IntPtr class_ref;
        static IntPtr id_nanoTime;
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