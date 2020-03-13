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

    public struct MIDITrack {
        public MIDITrackInfo info;
        public int bufferOffset;
        public byte lastEvent;
        public uint absTime;
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
            //tracks = ProcessMIDITracks();
            return 0;
        }

        protected MIDIHeaderInfo ProcessMIDIHeader()
        {
            MIDIHeaderInfo result = new MIDIHeaderInfo();
            result.id = GetUint();
            if (result.id != 0x4D546864)
                throw new Exception("MIDIFile incorrect file signature!");
            result.size = GetUint();
            result.format = GetUShort();
            result.tracks = GetUShort();
            result.ticks = GetUShort();
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

    }


    class MidiPlayer
    {
    }
}