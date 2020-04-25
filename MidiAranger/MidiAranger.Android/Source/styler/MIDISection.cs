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
        public int StartIndex { get; set; }
        public int StopIndex { get; set; }

    }



    public class MIDITrack
    {
        public MIDITrackInfo Info;
        public int CurrentEventIndex;
        public byte LastCommand;
        public int AbsTime;
        public List<MIDIEvent> MidiEvents;
        public List<MIDIMarker> MidiMarkers;

        public MIDIMarker CurrentMarker;

        public MIDITrack()
        {
            MidiEvents = new List<MIDIEvent>();
            MidiMarkers = new List<MIDIMarker>();
            CurrentEventIndex = 0;
            LastCommand = 0;
            AbsTime = 0;
            Info = new MIDITrackInfo();
            CurrentMarker = null;
        }


    }

    public struct MIDIEvent
    {
        public int absTime;
        public byte[] MidiMessage;
    }



    class MIDISection : MIDIFileBase,IFileParser
    {
        public byte[] _debugFileBuff = { 1, 2 };


        public int FileSize;
        public byte[] ByteBuff;
    //    public int PulsesPerQuarterNote;
        public MIDIHeaderInfo MidiHeaderInfo;
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
            MidiHeaderInfo.Size = Common.GetUint(ref ByteBuff, ref currentOffset);
            MidiHeaderInfo.Format = Common.GetUShort(ref ByteBuff, ref currentOffset);
            MidiHeaderInfo.TrackCount = Common.GetUShort(ref ByteBuff, ref currentOffset);
            MidiHeaderInfo.Ticks = Common.GetUShort(ref ByteBuff, ref currentOffset);
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
            if (track.CurrentMarker!=null)
                track.CurrentMarker.StopIndex = track.MidiEvents.Count() - 1;
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
            result.absTime = Common.GetVariableNumber(ref ByteBuff, ref currentOffset);
            result.MidiMessage = GetMidiMessage(track);
            return result;
        }

        protected byte[] GetMidiMessage(MIDITrack track)
        {
            byte command = Common.GetByte(ref ByteBuff, ref currentOffset);
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
            byte[] result = new byte[Common.GetVariableNumber(ref ByteBuff, ref currentOffset)];
            for (int i = 0; i < result.Length; i++) result[i] = Common.GetByte(ref ByteBuff, ref currentOffset);
            return result;

        }

        protected byte[] GetMessageDataForCommand(MIDITrack track)
        {
            //Meta
            if (track.LastCommand == 0xFF)
            {
                byte type = Common.GetByte(ref ByteBuff, ref currentOffset);
                byte[] b = GetVarData();
                byte[] result = new byte[b.Length + 1];
                result[0] = type;
                Array.Copy(b, 0, result, 1, b.Length);

                if (type == 0x06)
                {
                    if (track.CurrentMarker != null)
                    {
                        track.CurrentMarker.StopIndex = track.MidiEvents.Count()-1;
                    }
                    MIDIMarker marker = new MIDIMarker();
                    marker.StartIndex = track.MidiEvents.Count();
                    marker.Name = Encoding.Default.GetString(b);
                    track.CurrentMarker = marker;
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
                return new byte[2] { Common.GetByte(ref ByteBuff, ref currentOffset), Common.GetByte(ref ByteBuff, ref currentOffset) };
            }
            else
            if (Common.IsMasked(track.LastCommand, 0xC) || Common.IsMasked(track.LastCommand, 0xD))
            {
                return new byte[1] { Common.GetByte(ref ByteBuff, ref currentOffset) };
            }
            return new byte[0];
        }


        protected void ProcessMidiTrackInfo(ref MIDITrackInfo track)
        {
            track.Id = Common.GetUint(ref ByteBuff, ref currentOffset);
            if (track.Id != 0x4D54726B) //MTrk
                throw new System.Exception("MIDI-File contains bad track signature");
            track.Length = Common.GetUint(ref ByteBuff, ref currentOffset);
        }

        

        public void Parse(ref byte[] buff, ref int position)
        {
            ByteBuff = buff;
            currentOffset = position;
            FileSize = ByteBuff.Length;
            ProcessMIDIHeader();
            ProcessMIDITracks();
            position = currentOffset; 
        }
    }
}