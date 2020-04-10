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
            new ChordDeclaration(0x00, "RMaj", "1,3,5"),
            new ChordDeclaration(0x01, "RMaj6", "1,3,5,6"),
            new ChordDeclaration(0x02, "RMaj7", "1,3,(5),7"),
            new ChordDeclaration(0x03, "RMaj7#11", "1,(2),3,#4,(5),7"),
            new ChordDeclaration(0x04, "RMajAdd9", "1,2,3,5"),
            new ChordDeclaration(0x05, "RMaj7(9)", "1,2,3,(5),7"),
            new ChordDeclaration(0x06, "RMaj6(9)", "1,2,3,(5),6"),

            new ChordDeclaration(0x07, "RAug", "1,3,#5"),

            new ChordDeclaration(0x08, "Rmin", "1,b3,5"),
            new ChordDeclaration(0x09, "Rmin6", "1,b3,5,6"),
            new ChordDeclaration(0x0A, "Rmin7", "1,b3,(5),7"),
            new ChordDeclaration(0x0B, "Rmin7b5", "1,b3,b5,b7"),
            new ChordDeclaration(0x0C, "RminAdd9", "1,2,b3,5"),
            new ChordDeclaration(0x0D, "Rmin7(9)", "1,2,b3,(5),b7"),
            new ChordDeclaration(0x0E, "Rmin7(11)", "1,(2),b3,4,5,(b7)"),
            new ChordDeclaration(0x0F, "RminMaj7", "1,b3,(5),7"),
            new ChordDeclaration(0x10, "RminMaj7(9)", "1,2,b3,(5),7"),

            new ChordDeclaration(0x11, "Rdim", "1,b3,b5"),
            new ChordDeclaration(0x12, "Rdim7", "1,b3,b5,6"),

            new ChordDeclaration(0x13, "R7th", "1,3,(5),b7"),
            new ChordDeclaration(0x14, "R7sus4", "1,4,5,b7"),
            new ChordDeclaration(0x15, "R7b5", "1,3,b5,b7"),
            new ChordDeclaration(0x16, "R7(9)", "1,2,3,(5),b7"),
            new ChordDeclaration(0x17, "R7#11", "1,(2),3,#4,(5),b7"),

            new ChordDeclaration(0x18, "R7(13)", "1,3,(5),6,b7"),
            new ChordDeclaration(0x19, "R7(b9)", "1,b2,3,(5),b7"),
            new ChordDeclaration(0x1A, "R7(b13)", "1,3,5,b6,b7"),
            new ChordDeclaration(0x1B, "R7(#9)", "(1),#2,3,(5),b7"),
            new ChordDeclaration(0x1C, "RMaj7Aug", "1,3,#5,7"),
            new ChordDeclaration(0x1D, "R7Aug", "(1),3,#5,b7"),
            new ChordDeclaration(0x1E, "R1+8", "1,8"),
            new ChordDeclaration(0x1F, "R1+5", "1,5"),
            new ChordDeclaration(0x20, "Rsus4", "1,4,5"),
            new ChordDeclaration(0x21, "R1+2+5", "1,2,5")
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
                    uint[] res = new uint[res1.Length + res2.Length];
                    res1.CopyTo(res, 0);
                    res2.CopyTo(res, res1.Length);
                    return res;
                }
                else
                    mask = mask | (uint)(1 << (31 - GetOffset(s)));
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



        private void InitializeChordDefinitions()
        {
            uint[] offsets;
            chordDefinitions = new List<ChordDefinition>();
            for (int i = 0; i < chordDeclarations.Length; i++)
            {
                
                List<string> mixedDefs = GetMixedDefinitions(chordDeclarations[i].ChordOffsets);
                foreach (string key in mixedDefs)
                {

                    string[] keys = key.Split(",");
                    offsets = ProcessChordDefinition(0, 0, keys, keys.Length);
                    for (int j = 0; j < offsets.Length; j++)
                    {
                        ChordDefinition cd = new ChordDefinition(chordDeclarations[i].ChordName, offsets[j]);
                        chordDefinitions.Add(cd);
                    }

                }
            }


            for (int i = 0; i < chordDefinitions.Count; i++)
            {
                if (chordDefinitions[i].HalfTonesBitMask == 25525)
                    break;

            }

        }


    }
}