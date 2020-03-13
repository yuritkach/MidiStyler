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
        public byte lastEvent;
        public uint absTime;
        public List<MIDIEvent> MidiEvents;
    }

    public struct MIDIEvent {
        public uint absTime;
        public int bufferOffset;
        public byte midievent;
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
                result.Add(ProcessMIDITrack());
            return result;
        }

        protected MIDITrack ProcessMIDITrack() {
            MIDITrack result = new MIDITrack();
            result.info = ProcessMidiTrackInfo();
            result.MidiEvents = ProcessMidiEvents(result.info.length);
            result.lastEvent = 0;
            return result;
        }

        protected List<MIDIEvent> ProcessMidiEvents(uint length) {
            List<MIDIEvent> result = new List<MIDIEvent>();
            while (length>0) {
                result.Add(LoadMidiEvent(ref length));
            }
            return result;
        }

        protected MIDIEvent LoadMidiEvent(ref uint length) {
            MIDIEvent result = new MIDIEvent();
            uint timeOffset = GetVariableNumber(ref length);
            byte command = GetByte();
            length--;

            if ((command & 0x80) == 0x80) // is command?
            {
                if ((command & 0xF0) == 0xF0) // common command
                {
                    // common command

                }
                else                          // channel command 
                {
                    // cnannel command
                    
                }
            }
            else
            {
                //data (00-7F - data)

            }


            return result;
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


    class MidiPlayer
    {
    }
}