﻿using System;
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

namespace MidiAranger.Droid.Source.midiplayer
{

    public class HalfToneOffset
    {
        public string Name { get; set; }
        public byte Offset { get; set; }
        public HalfToneOffset(string name, byte offset)
        {
            Name=name;
            Offset=offset;
        }
    }

    public class ChordDeclaration
    {
        public int ChordId { get; protected set; }
        public string ChordName { get; protected set; }
        public string ChordOffsets { get; protected set; }

        public ChordDeclaration(int id,string chordName, string chorOffsets)
        {
            ChordId = id;
            ChordName = chordName;
            ChordOffsets = chorOffsets;
        }
    }


    public class ChordRecognizer
    {
        
        protected readonly static ChordDeclaration[] chordDeclarations = {
            new ChordDeclaration(0x00, "RMaj", "0,4,7"),
            new ChordDeclaration(0x01, "RMaj6", "0,4,7,9"),
            new ChordDeclaration(0x02, "RMaj7", "0,4,(7),11"),
            new ChordDeclaration(0x03, "RMaj7#11", "0,(2),4,6,(7),11"),
            new ChordDeclaration(0x04, "RMajAdd9", "0,2,4,7"),
            new ChordDeclaration(0x05, "RMaj7(9)", "0,2,4,(7),11"),
            new ChordDeclaration(0x06, "RMaj6(9)", "0,2,4,(7),9"),
            new ChordDeclaration(0x07, "RAug", "0,4,8"),

            new ChordDeclaration(0x08, "Rmin", "0,3,7"),
            new ChordDeclaration(0x09, "Rmin6", "0,3,7,9"),
            new ChordDeclaration(0x0A, "Rmin7", "0,3,(7),10"),
            new ChordDeclaration(0x0B, "Rmin7b5", "0,3,6,10"),
            new ChordDeclaration(0x0C, "RminAdd9", "0,2,3,7"),
            new ChordDeclaration(0x0D, "Rmin7(9)", "0,2,3,(7),10"),
            new ChordDeclaration(0x0E, "Rmin7(11)", "0,(2),3,5,7,(10)"),
            new ChordDeclaration(0x0F, "RminMaj7", "0,3,(7),11"),
            new ChordDeclaration(0x10, "RminMaj7(9)", "0,2,3,(7),11"),

            new ChordDeclaration(0x11, "Rdim", "0,3,6"),
            new ChordDeclaration(0x12, "Rdim7", "0,3,6,9"),

            new ChordDeclaration(0x13, "R7th", "0,4,(7),10"),
            new ChordDeclaration(0x14, "R7sus4", "0,5,7,10"),
            new ChordDeclaration(0x15, "R7b5", "0,4,6,10"),

            new ChordDeclaration(0x16, "R7(9)", "0,2,4,(7),10"),
            new ChordDeclaration(0x17, "R7#11", "0,(2),4,6,(7),10"),

            new ChordDeclaration(0x18, "R7(13)", "0,4,(7),9,10"),
            new ChordDeclaration(0x19, "R7(b9)", "0,1,4,(7),10"),
            new ChordDeclaration(0x1A, "R7(b13)", "0,4,7,8,10"),
            new ChordDeclaration(0x1B, "R7(#9)", "(0),3,4,(7),10"),
            new ChordDeclaration(0x1C, "RMaj7Aug", "0,4,8,11"),
            new ChordDeclaration(0x1D, "R7Aug", "(0),4,8,10"),
            new ChordDeclaration(0x1E, "R1+8", "0,12"),
            new ChordDeclaration(0x1F, "R1+5", "0,7"),
            new ChordDeclaration(0x20, "Rsus4", "0,5,7"),
            new ChordDeclaration(0x21, "R1+2+5", "0,2,7")
        };

       
        public class ChordDefinition
        {
            public string ChordName;
            public uint HalfTonesBitMask;
            public ChordDefinition(string chordName, uint halfToneBitMask)
            {
                ChordName = chordName;
                HalfTonesBitMask = halfToneBitMask;
            }
        }

        private List<ChordDefinition> chordDefinitions;

        private RedBlackTree<int> chordIndex;

        public ChordRecognizer() {
            InitializeChordDefinitions();


        }
        private int _delta;
        private int MakeOffset(int rootIndex, int curIndex,byte s)
        {
            int ofs = s;

            if (curIndex < rootIndex)
            {
                if (_delta==0)
                    _delta = Math.Abs(ofs - 12);
                ofs = ofs - 12 + _delta;
            }
            else
            if (curIndex >= rootIndex && rootIndex != 0)
            {
                ofs = ofs + _delta;
            }
            else
            {
                _delta = 0;
            }

            return ofs;
        }

        private uint[] ProcessChordDefinition(int startIndex, uint mask, string[] definitions, int leng, int rootIndex)
        {

            for (int i = startIndex; i < definitions.Length; i++)
            {
                string s = definitions[i];
        
                if (s.Substring(0, 1) == "(")
                {
                    uint[] res1 = ProcessChordDefinition(i + 1, mask, definitions, leng, rootIndex);

                    
                    mask = mask |(uint) (1 <<(31-MakeOffset(rootIndex,i,byte.Parse(s.Substring(1, s.Length - 2)))));
                    uint[] res2 = ProcessChordDefinition(i + 1, mask, definitions, leng, rootIndex);
                    uint[] res = new uint[res1.Length + res2.Length];
                    res1.CopyTo(res, 0);
                    res2.CopyTo(res, res1.Length);
                    return res;
                }
                else
                    mask = mask | (uint)(1 << (31 - MakeOffset(rootIndex,i,byte.Parse(s))));
            }
            uint[] result = new uint[1];
            result[0]=mask;
            return result;

        }

        public List<string> GetMixedDefinitions(string definition)
        {
            List<string> result = new List<string>();
            string s;
            string[] keys = definition.Split(",");
            for (int i = 0; i < keys.Length; i++)
            {
                s = "";
                for (int j = 0; j < i; j++) s = s + "," + keys[j];
                for (int j = i + 1; j < keys.Length; j++) s = s + "," + keys[j];
                if (keys.Length > 1)
                {
                    s = s.Substring(1);
                    List<string> r = GetMixedDefinitions(s);
                    for (int j = 0; j < r.Count; j++)
                    {
                        s = keys[i] + "," + r[j];
                        result.Add(s);
                    }
                }
                else result.Add(keys[i]);

            }

            return result;
        }

        private byte OnlyNumber(string s)
        {
            if (s.Substring(0,1) == "(")
                return byte.Parse(s.Substring(1, s.Length - 2));
            else return byte.Parse(s);
        }

        private void SortLeftPart(ref string[] keys, int rootOffset)
        {
            string s;
            // Sort by offset left part to rootOffset
            for (int i = 0; i < rootOffset; i++)
            {
                for (int j = 1; j < rootOffset; j++)
                {
                    if (OnlyNumber(keys[j]) < OnlyNumber(keys[i]))
                    {
                        s = keys[i];
                        keys[i] = keys[j];
                        keys[j] = s;
                    }

                }

            }


        }


        private void InitializeChordDefinitions()
        {
            chordIndex = new RedBlackTree<int>();
            uint[] offsets;
            int rootIndex;
            chordDefinitions = new List<ChordDefinition>();
            for (int i = 0; i < chordDeclarations.Length; i++)
            {
                
                List<string> mixedDefs = GetMixedDefinitions(chordDeclarations[i].ChordOffsets);
                string rootOffset = chordDeclarations[i].ChordOffsets.Split(",")[0] ;
                foreach (string key in mixedDefs)
                {

                    string[] keys = key.Split(",");

                    _delta = 0;

                    rootIndex = Array.IndexOf(keys, rootOffset);
                    SortLeftPart(ref keys, rootIndex);
                    offsets = ProcessChordDefinition(0, 0, keys, keys.Length, rootIndex);
                    for (int j = 0; j < offsets.Length; j++)
                    {
                        ChordDefinition cd = new ChordDefinition(chordDeclarations[i].ChordName, offsets[j]);
                        if (!chordIndex.Exists(cd.HalfTonesBitMask))
                        {
                            chordDefinitions.Add(cd);
                            chordIndex.Add(cd.HalfTonesBitMask, chordDeclarations[i].ChordId);
                        }
                        
                    }

                }
            }

            
        }

        public ChordDeclaration Recognize(byte[] notes)
        {
            if (notes.Length < 2) return null;
            Array.Sort(notes);
            int initialOffset = notes[0];
            uint mask = 0;
            for (int i = 0; i < notes.Length; i++)
            {
                int ofs = 31-(notes[i] - initialOffset);
                mask = mask | (uint)(1 << ofs);
            }

            int res;
            try
            {
                res = chordIndex.Get(mask);
            }
            catch
            {
                res = -1;
            }

            return res ==-1 ? null : chordDeclarations[res];
        }

    }
}