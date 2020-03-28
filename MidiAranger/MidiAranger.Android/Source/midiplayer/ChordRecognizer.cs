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
    public class ChordRecognizer
    {
        private readonly static Dictionary<string, int> halfTones = new Dictionary<string, int>() {
            {"1",0},
            {"b2",1},
            {"2",2},
            {"#2",3},
            {"b3",3},
            {"3",4},
            {"4",5},
            {"#4",6},
            {"b5",6},
            {"5",7},
            {"#5",8},
            {"b6",8},
            {"6",9},
            {"bb7",9},
            {"#6",10},
            {"b7",10},
            {"7",11},
            {"8",12},
            {"b9",13},
            {"9",14},
            {"#9",15},
            {"10",16},
            {"11",17},
            {"#11",18},
            {"12",19},
            {"b13",20},
            {"13",21}
        };

        static readonly Dictionary<string, string> chords = new Dictionary<string, string>()   {
            {"R Major","1,5"},
            {"R5","1,5" },
            {"R-5","1,3,b5" },
            {"R6","1,3,5,6" },
            {"R6/9","1,3,(5),6,9" },
            {"R7","1,3,(5),b7" },
            {"Radd9","1,3,5,9" },
            {"Rmaj7","1,3,5,7" },
            {"Rmaj7+5","1,3,#5,7" },
            {"Rmaj9","1,3,(5),7,9" },
            {"Rmaj11", "1,(3),5,7,(9),11" },
            {"Rmaj13", "1,3,(5),7,(9),(11),13" },
            {"R2", "1,2,3,5" },
            {"Rm", "1,b3,5" },
            {"Rm6", "1,b3,5,6" },
            {"Rm6/9", "1,b3,(5),6,9" },
            {"Rmmaj7",  "1,b3,5,7" },
            {"Rmmaj9",  "1,b3,(5),7,9" },
            {"Rmadd9",  "1,b3,(5),9" },
            {"Rm7",  "1,b3,5,b7" },
            {"Rm9",  "1,b3,(5),b7,9" },
            {"Rm11", "1,b3,(5),b7,(9),11" },
            {"Rm13", "1,b3,(5),b7,(9),(11),13" },
            {"Rm-5", "1,b3,b5" },
            {"Rdim",  "1,b3,b5" },
            {"Rdim7","1,b3,b5,bb7" },
            {"Rm7-5",  "1,b3,b5,b7" },
            {"R7","1,3,5,b7" },
            {"R7-9",  "1,3,(5),b7,b9" },
            {"R7+9", "1,3,(5),b7,#9" },
            {"R7-5", "1,3,b5,b7" },
            {"R7+5",  "1,3,#5,b7" },
            {"R7/6",  "1,3,(5),6,b7" },
            {"R9",  "1,3,(5),b7,9" },
            {"R9-5", "1,(3),b5,b7,9" },
            {"R9+5", "1,(3),#5,b7,9" },
            {"Radd9", "1,3,5,9" },
            {"R9/6",  "1,(3),(5),6,b7,9" },
            {"R9+11", "1,3,(5),b7,9,#11" },
            {"R11",  "1,(3),5,b7,(9),11" },
            {"R11-9", "1,(3),(5),b7,b9,11" },
            {"R13",  "1,(3),5,b7,(9),(11),13" },
            {"R13-9","1,(3),(5),b7,b9,(11),13" },
            {"R13-9-5", "(1),(3),b5,b7,b9,(11),13" },
            {"R13-9+11", "(1),(3),(5),b7,b9,#11,13" },
            {"R13+11", "1,(3),(5),b7,(9),#11,13" },
            {"R7/13", "1,3,(5),b7,13" },
            {"Raug",  "1,3,#5" },
            {"Rsus2", "1,2,5" },
            {"Rsus4","1,4,5" },
            {"R7sus4", "1,4,5,b7" },
            {"R-9",  "1,3,(5),b7,b9" },
            {"R-9+5", "1,(3),#5,b7,b9" },
            {"R-9+11", "1,(3),(5),b7,b9,#11" },
            {"R-9-5",  "1,(3),b5,b7,b9" },
            {"R+5",  "1,3,#5" },
            {"R+9",  "1,3,(5),b7,#9" },
            {"R+11", "1,(3),(5),b7,9,#11" }

        };



        private class ChordDefinition
        {
            public string Name;
            public byte[] HalfTones;
        }

        private List<ChordDefinition> chordDefinitions = new List<ChordDefinition>();

        static ChordRecognizer() {
            InitializeChordDefinitions();


        }

        private static void InitializeChordDefinitions()
        {
            foreach (var chord in chords)
            {


            }


        }


    }
}