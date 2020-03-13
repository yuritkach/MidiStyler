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
                currentTrack = track;
                ProcessMIDITrack(track);
                result.Add(track);
            }
            return result;
        }

        protected void ProcessMIDITrack(MIDITrack track) {
            track.info = ProcessMidiTrackInfo();
            track.MidiEvents = ProcessMidiEvents(track.info.length);
            track.lastCommand = 0;
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
            }
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
                length = length - resultLength;
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

            // Channel message
            if (IsMasked(command, 0x80) || IsMasked(command, 0x90) || IsMasked(command, 0xA0) ||
                IsMasked(command, 0xB0) || IsMasked(command, 0xE0))
            {
                length -= 2;
                return new byte[2] { GetByte(), GetByte() };
            }
            if (IsMasked(command, 0xC0) || IsMasked(command, 0xD0))
            {
                length--;
                return new byte[1] { GetByte() };
            }
            
            
            return new byte[0];
        }
            protected bool IsMasked(byte b1, byte mask) {
            return (b1 & mask) == mask;


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