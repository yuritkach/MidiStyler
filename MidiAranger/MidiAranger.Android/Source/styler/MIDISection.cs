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
using MidiAranger.Droid.Source.common;
using MidiAranger.Droid.Source.midiplayer;

namespace MidiAranger.Droid.Source.styler
{
    public struct MIDIHeaderInfo
    {
        public int Id;         // MThd signature
        public int Size;       // Always 6 (big-endian)
        public ushort Format;   // format
        public ushort TrackCount;   // count of tracks
        public ushort Ticks;    // ticks per quarter note
    }

    public struct MIDITrackInfo
    {
        public int Id; // Mtrk signature
        public int Length; // Length of track in bytes (big-endian)

    }

    public class MIDIMarker
    {
        public string Name { get; set; }
        public int Index { get; set; }
    }

    

    public class MIDITrack
    {
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

    public struct MIDIEvent
    {
        public int absTime;
        public byte[] MidiMessage;
    }



    class MIDISection : IFileParser
    {
        public byte[] _debugFileBuff = { 1, 2 };


        public int FileSize;
        public byte[] ByteBuff = null;
    //    public int PulsesPerQuarterNote;
        public MIDIHeaderInfo MidiHeaderInfo;
        public MIDITrackInfo MidiTrackInfo;
        public List<MIDITrack> Tracks;

        public string StyleName { get; protected set; }
        protected int currentOffset;

        public MIDISection()
        {
            Tracks = new List<MIDITrack>();
            MidiHeaderInfo = new MIDIHeaderInfo();

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

        protected void ProcessMIDITracks()
        {
            for (int i = 0; i < MidiHeaderInfo.TrackCount; i++)
            {
                MIDITrack track = new MIDITrack();
                ProcessMIDITrack(track);
                Tracks.Add(track);
            }
        }

        protected void ProcessMIDITrack(MIDITrack track)
        {
            ProcessMidiTrackInfo(ref track.Info);
            track.AbsTime = 0;
            track.LastCommand = 0;
            track.CurrentEventIndex = 0;
            ProcessMidiEvents(track);
        }

        protected void ProcessMidiEvents(MIDITrack track)
        {
            track.MidiEvents = new List<MIDIEvent>();
            int _old;
            while (track.Info.Length > 0)
            {
                _old = currentOffset;
                MIDIEvent midiEvent = LoadMidiEvent(track);
                track.AbsTime += midiEvent.absTime;
                midiEvent.absTime = track.AbsTime;
                track.MidiEvents.Add(midiEvent);
                track.Info.Length -= (currentOffset - _old);

            }
        }

        protected MIDIEvent LoadMidiEvent(MIDITrack track)
        {
            MIDIEvent result = new MIDIEvent();
            result.absTime = GetVariableNumber();
            result.MidiMessage = GetMidiMessage(track);
            return result;
        }

        protected byte[] GetMidiMessage(MIDITrack track)
        {
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

        protected byte[] GetVarData()
        {
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

                if (type == 0x06)
                {
                    MIDIMarker marker = new MIDIMarker();
                    marker.Index = track.MidiEvents.Count();
                    marker.Name = Encoding.Default.GetString(b);
                    track.MidiMarkers.Add(marker);
                }
                if (type == 0x03)
                {
                    StyleName = Encoding.Default.GetString(b);
                }



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
            if (Common.IsMasked(track.LastCommand, 0x8) || Common.IsMasked(track.LastCommand, 0x9) || Common.IsMasked(track.LastCommand, 0xA) || Common.IsMasked(track.LastCommand, 0xB) || Common.IsMasked(track.LastCommand, 0xE))
            {
                return new byte[2] { GetByte(), GetByte() };
            }
            else
            if (Common.IsMasked(track.LastCommand, 0xC) || Common.IsMasked(track.LastCommand, 0xD))
            {
                return new byte[1] { GetByte() };
            }
            return new byte[0];
        }


        protected void ProcessMidiTrackInfo(ref MIDITrackInfo track)
        {
            track.Id = GetUint();
            if (track.Id != 0x4D54726B) //MTrk
                throw new System.Exception("MIDI-File contains bad track signature");
            track.Length = GetUint();
        }

        protected int GetUint()
        {
            return GetUShort() << 16 | GetUShort();
        }

        protected ushort GetUShort()
        {
            return (ushort)(GetByte() << 8 | GetByte());
        }

        protected byte GetByte()
        {
            return ByteBuff[currentOffset++];
        }

        protected int GetVariableNumber()
        {
            int result = 0;
            byte byte_in;
            for (;;)
            {
                byte_in = GetByte();
                result = (result << 7) | byte_in & 0x7f;
                if ((byte_in & 0x80) == 0)
                    return result;
            }
        }


        public void Parse(ref byte[] buff, ref int position)
        {
            //ByteBuff = buff;
            ByteBuff = DEBUGDATA.midifileData;
            currentOffset = position;
            FileSize = ByteBuff.Length;
            ProcessMIDIHeader();
            ProcessMIDITracks();
        }
    }
}