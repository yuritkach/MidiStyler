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

namespace MidiAranger.Droid.Source.midiplayer
{

    public struct HalfToneOffset
    {
        public string Name;
        public byte Offset;
        public HalfToneOffset(string name, byte offset)
        {
            Name=name;
            Offset=offset;
        }
    }

    public struct ChordDeclaration
    {
        public string ChordName;
        public string ChordOffsets;

        public ChordDeclaration(string chordName, string chorOffsets)
        {
            ChordName = chordName;
            ChordOffsets = chorOffsets;
        }
    }


    public class ChordRecognizer
    {
        private readonly static HalfToneOffset[] halfTones = {
            new HalfToneOffset("1",0),
            new HalfToneOffset("b2",1),
            new HalfToneOffset( "2",2),
            new HalfToneOffset( "#2",3),
            new HalfToneOffset( "b3",3),
            new HalfToneOffset( "3",4),
            new HalfToneOffset( "4",5),
            new HalfToneOffset( "#4",6),
            new HalfToneOffset( "b5",6),
            new HalfToneOffset( "5",7),
            new HalfToneOffset( "#5",8),
            new HalfToneOffset( "b6",8),
            new HalfToneOffset( "6",9),
            new HalfToneOffset( "bb7",9),
            new HalfToneOffset( "#6",10),
            new HalfToneOffset( "b7",10),
            new HalfToneOffset( "7",11),
            new HalfToneOffset( "8",12),
            new HalfToneOffset( "b9",13),
            new HalfToneOffset( "9",14),
            new HalfToneOffset( "#9",15),
            new HalfToneOffset( "10",16),
            new HalfToneOffset( "11",17),
            new HalfToneOffset( "#11",18),
            new HalfToneOffset( "12",19),
            new HalfToneOffset( "b13",20),
            new HalfToneOffset( "13",21)
        };



        protected readonly static ChordDeclaration[] chordDeclarations = {
            new ChordDeclaration("R Major","1,5"),
            new ChordDeclaration( "R5","1,5" ),
            new ChordDeclaration( "R-5","1,3,b5" ),
            new ChordDeclaration( "R6","1,3,5,6" ),
            new ChordDeclaration( "R6/9","1,3,(5),6,9" ),
            new ChordDeclaration( "R7","1,3,(5),b7" ),
            new ChordDeclaration( "Radd9","1,3,5,9" ),
            new ChordDeclaration( "Rmaj7","1,3,5,7" ),
            new ChordDeclaration( "Rmaj7+5","1,3,#5,7" ),
            new ChordDeclaration( "Rmaj9","1,3,(5),7,9" ),
            new ChordDeclaration( "Rmaj11", "1,(3),5,7,(9),11" ),
            new ChordDeclaration( "Rmaj13", "1,3,(5),7,(9),(11),13" ),
            new ChordDeclaration( "R2", "1,2,3,5" ),
            new ChordDeclaration( "Rm", "1,b3,5" ),
            new ChordDeclaration( "Rm6", "1,b3,5,6" ),
            new ChordDeclaration( "Rm6/9", "1,b3,(5),6,9" ),
            new ChordDeclaration( "Rmmaj7",  "1,b3,5,7" ),
            new ChordDeclaration( "Rmmaj9",  "1,b3,(5),7,9" ),
            new ChordDeclaration( "Rmadd9",  "1,b3,(5),9" ),
            new ChordDeclaration( "Rm7",  "1,b3,5,b7" ),
            new ChordDeclaration( "Rm9",  "1,b3,(5),b7,9" ),
            new ChordDeclaration( "Rm11", "1,b3,(5),b7,(9),11" ),
            new ChordDeclaration( "Rm13", "1,b3,(5),b7,(9),(11),13" ),
            new ChordDeclaration( "Rm-5", "1,b3,b5" ),
            new ChordDeclaration( "Rdim",  "1,b3,b5" ),
            new ChordDeclaration( "Rdim7","1,b3,b5,bb7" ),
            new ChordDeclaration( "Rm7-5",  "1,b3,b5,b7" ),
            new ChordDeclaration( "R7","1,3,5,b7" ),
            new ChordDeclaration( "R7-9",  "1,3,(5),b7,b9" ),
            new ChordDeclaration( "R7+9", "1,3,(5),b7,#9" ),
            new ChordDeclaration( "R7-5", "1,3,b5,b7" ),
            new ChordDeclaration( "R7+5",  "1,3,#5,b7" ),
            new ChordDeclaration( "R7/6",  "1,3,(5),6,b7" ),
            new ChordDeclaration( "R9",  "1,3,(5),b7,9" ),
            new ChordDeclaration( "R9-5", "1,(3),b5,b7,9" ),
            new ChordDeclaration( "R9+5", "1,(3),#5,b7,9" ),
            new ChordDeclaration( "Radd9", "1,3,5,9" ),
            new ChordDeclaration( "R9/6",  "1,(3),(5),6,b7,9" ),
            new ChordDeclaration( "R9+11", "1,3,(5),b7,9,#11" ),
            new ChordDeclaration( "R11",  "1,(3),5,b7,(9),11" ),
            new ChordDeclaration( "R11-9", "1,(3),(5),b7,b9,11" ),
            new ChordDeclaration( "R13",  "1,(3),5,b7,(9),(11),13" ),
            new ChordDeclaration( "R13-9","1,(3),(5),b7,b9,(11),13" ),
            new ChordDeclaration( "R13-9-5", "(1),(3),b5,b7,b9,(11),13" ),
            new ChordDeclaration( "R13-9+11", "(1),(3),(5),b7,b9,#11,13" ),
            new ChordDeclaration( "R13+11", "1,(3),(5),b7,(9),#11,13" ),
            new ChordDeclaration( "R7/13", "1,3,(5),b7,13" ),
            new ChordDeclaration( "Raug",  "1,3,#5" ),
            new ChordDeclaration( "Rsus2", "1,2,5" ),
            new ChordDeclaration( "Rsus4","1,4,5" ),
            new ChordDeclaration( "R7sus4", "1,4,5,b7" ),
            new ChordDeclaration( "R-9",  "1,3,(5),b7,b9" ),
            new ChordDeclaration( "R-9+5", "1,(3),#5,b7,b9" ),
            new ChordDeclaration( "R-9+11", "1,(3),(5),b7,b9,#11" ),
            new ChordDeclaration( "R-9-5",  "1,(3),b5,b7,b9" ),
            new ChordDeclaration( "R+5",  "1,3,#5" ),
            new ChordDeclaration( "R+9",  "1,3,(5),b7,#9" ),
            new ChordDeclaration( "R+11", "1,(3),(5),b7,9,#11" )

        };

        private byte GetOffset(string key)
        {
            for (int i = 0; i < halfTones.Length; i++)
                if (halfTones[i].Name == key) return halfTones[i].Offset;
            return 0xFF;
        }

        private class ChordDefinition
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



        public ChordRecognizer() {
            InitializeChordDefinitions();


        }

        private uint[] ProcessChordDefinition(int startIndex, uint mask, string[] definitions, int leng)
        {

            for (int i = startIndex; i < definitions.Length; i++)
            {
                string s = definitions[i];
                if (s.Substring(0, 1) == "(")
                {
                    uint[] res1 = ProcessChordDefinition(i + 1, mask, definitions, leng);
                    mask = mask |(uint) (1 << (31 - GetOffset(s.Substring(1, s.Length - 2))));
                    uint[] res2 = ProcessChordDefinition(i + 1, mask, definitions, leng);
                    Array.Copy(res2,0,res1,res1.Length,res2.Length);
                    return res1;
                }
                else
                    mask = mask | (uint)(1 << (31 - GetOffset(s)));
            }
            uint[] result = new uint[1];
            result[0]=mask;
            return result;

        }


        private void InitializeChordDefinitions()
        {
            uint[] offsets;
            chordDefinitions = new List<ChordDefinition>();
            for (int i = 0; i < chordDeclarations.Length; i++)
            {
                string[] keys = chordDeclarations[i].ChordOffsets.Split(",");
                offsets = ProcessChordDefinition(0, 0, keys, keys.Length);
                for(int j = 0; j < offsets.Length; j++)
                    
                {
                    ChordDefinition cd = new ChordDefinition(chordDeclarations[i].ChordName,offsets[j]);
                    chordDefinitions.Add(cd);
                }

            }
            
        }


    }
}