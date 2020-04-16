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
using static MidiAranger.Droid.Source.common.Common;
using static MidiAranger.Droid.Source.midiplayer.MIDIPlayer;

namespace MidiAranger.Droid.Source.styler
{
    class Sdec : IFileParser
    {
        public List<StyleSections> Sections { get; protected set; }
        public Sdec()
        {
            Sections = new List<StyleSections>();
        }

        public void Parse(ref byte[] buff, ref int position)
        {
            int sectionSize = Common.GetUint(ref buff, ref position);
            byte[] b = new byte[sectionSize];
            for (int i = 0; i < sectionSize; i++)
                b[i] = Common.GetByte(ref buff, ref position);
            string[] sections = Encoding.Default.GetString(b).Split(",");
            foreach (string section in sections)
                Sections.Add(Common.GetSectionCode(section));
        }
    }
    class Ctab : IFileParser
    {
        public byte SourceChanel { get; protected set; }
        public string Name { get; protected set; }
        public byte DestionationChanel { get; protected set; }
        public byte Editable { get; protected set; }
        public byte NoteMute1 { get; protected set; }
        public byte NoteMute2 { get; protected set; }
        public byte ChordMute1 { get; protected set; }
        public byte ChordMute2 { get; protected set; }
        public byte ChordMute3 { get; protected set; }
        public byte ChordMute4 { get; protected set; }
        public byte ChordMute5 { get; protected set; }
        public byte SourceChord { get; protected set; }
        public byte SourceChordType { get; protected set; }
        public byte NoteTranspositionRule { get; protected set; }
        public byte NoteTranspositionTable { get; protected set; }
        public byte HighKey { get; protected set; }
        public byte NoteLowLimit { get; protected set; }
        public byte NoteHighLimit { get; protected set; }
        public byte RetriggerRule { get; protected set; }
        public byte[] SpecialFeatures { get; protected set; }


        public Ctab()
        {
            SpecialFeatures = null;
        }

        public void Parse(ref byte[] buff, ref int position)
        {
            int sectionSize = Common.GetUint(ref buff, ref position);
            int _old = position;
            SourceChanel = Common.GetByte(ref buff, ref position); 
            byte[] b = new byte[8];
            for (int i = 0; i < 8; i++)
                b[i] = GetByte(ref buff, ref position);
            
            Name = Encoding.Default.GetString(b);
            DestionationChanel = Common.GetByte(ref buff, ref position); 
            Editable = Common.GetByte(ref buff, ref position); 
            NoteMute1 = Common.GetByte(ref buff, ref position);
            NoteMute2 = Common.GetByte(ref buff, ref position);
            ChordMute1 = Common.GetByte(ref buff, ref position);
            ChordMute2 = Common.GetByte(ref buff, ref position);
            ChordMute3 = Common.GetByte(ref buff, ref position);
            ChordMute4 = Common.GetByte(ref buff, ref position);
            ChordMute5 = Common.GetByte(ref buff, ref position);
            SourceChord = Common.GetByte(ref buff, ref position);
            SourceChordType = Common.GetByte(ref buff, ref position); 
            NoteTranspositionRule = Common.GetByte(ref buff, ref position); 
            NoteTranspositionTable = Common.GetByte(ref buff, ref position);
            HighKey = Common.GetByte(ref buff, ref position); 
            NoteLowLimit = Common.GetByte(ref buff, ref position); 
            NoteHighLimit = Common.GetByte(ref buff, ref position);
            RetriggerRule = Common.GetByte(ref buff, ref position);

            int rest = sectionSize - (position - _old);

            byte f = Common.GetByte(ref buff, ref position);
            rest--;
            if (f != 0)
            {
                SpecialFeatures = new byte[rest];
                for (int i=0;i<rest;i++)
                    SpecialFeatures[i]= Common.GetByte(ref buff, ref position);
            }
        }
    }
    class Cntt
    {
    }



    class CSEGStructure : IFileParser
    {
        public Sdec Sdec { get; protected set; }
        public List<Ctab> Ctabs { get; protected set; }
        public List<Cntt> Cntts { get; protected set; }
        
        public CSEGStructure()
        {
            Sdec = new Sdec();
            Ctabs = new List<Ctab>();
            Cntts = new List<Cntt>();
        }


        public void Parse(ref byte[] buff, ref int position)
        {
            int sectionSize = Common.GetUint(ref buff, ref position);
            int _old;
            while (sectionSize > sizeof(int))
            {
                _old = position;
                SectionID sectionID = Common.GetSectionId(ref buff, ref position);
                switch (sectionID)
                {
                    case SectionID.Sdec:
                        Sdec.Parse(ref buff, ref position);
                        break;
                    case SectionID.Ctab:
                        Ctab ctab = new Ctab();
                        ctab.Parse(ref buff, ref position);
                        Ctabs.Add(ctab);
                        break;
                    case SectionID.Cntt:
                        break;
                    default: throw new ApplicationException("Unknown CASM sub structure!");

                }
                sectionSize -= (position - _old);
            }
        }
    }


    class CASMSection : IFileParser
    {
        public List<CSEGStructure> CSEGS { get; protected set; }

        public CASMSection()
        {
            CSEGS = new List<CSEGStructure>();
        }

        public void Parse(ref byte[] buff, ref int position)
        {
            int sectionSize = Common.GetUint(ref buff, ref position);
            int _old;
            while (sectionSize > 0)
            {
                _old = position;
                SectionID sectionID = Common.GetSectionId(ref buff, ref position);
                if (sectionID != SectionID.CSEG)
                    throw new ApplicationException("Unknown CASM sub structure!");

                CSEGStructure cseg = new CSEGStructure();
                cseg.Parse(ref buff, ref position);
                CSEGS.Add(cseg);
                sectionSize -= (position - _old); 
            }
        }
    }
}