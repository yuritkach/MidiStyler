using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using MidiAranger.Droid.Source.styler;

namespace MidiAranger.Droid.Source.common
{
    public interface IOnActionEventListener
    {
        void DoAction(string s);
    }

    public enum StyleSections { Init, IntroA, IntroB, IntroC, MainA, MainB, MainC, MainD, FillInAA, FillInBB, FillInCC, FillInDD, FillInBA, FillInAB, EndingA, EndingB, EndingC, none }

    public static class Common
    {
        private static readonly DateTime Jan1st1970 = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
        public static string[] NoteNames = new string[] { "C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B" };

        public static long CurrentTimeMillis()=>(long)(DateTime.UtcNow - Jan1st1970).TotalMilliseconds;

        public static bool IsMasked(byte b1, byte mask) =>(b1 >> 4) == mask;

        public static SectionID GetSectionId(ref byte[] filebuff, ref int buffPosition) =>(SectionID)GetUint(ref filebuff, ref buffPosition);

        public static int GetUint(ref byte[] filebuff, ref int buffPosition) =>GetUShort(ref filebuff, ref buffPosition) << 16 | GetUShort(ref filebuff, ref buffPosition);

        public static ushort GetUShort(ref byte[] filebuff, ref int buffPosition) =>(ushort)(GetByte(ref filebuff, ref buffPosition) << 8 | GetByte(ref filebuff, ref buffPosition));

        public static byte GetByte(ref byte[] filebuff, ref int buffPosition)=>filebuff[buffPosition++];

        public static int GetVariableNumber(ref byte[] filebuff, ref int buffPosition)
        {
            int result = 0;
            byte byte_in;
            for (; ; )
            {
                byte_in = GetByte(ref filebuff, ref buffPosition);
                result = (result << 7) | byte_in & 0x7f;
                if ((byte_in & 0x80) == 0)
                    return result;
            }
        }


      
        public static string GetSectionName(StyleSections styleSection)
        {
            switch (styleSection)
            {
                case StyleSections.Init: return "Init";
                case StyleSections.IntroA: return "Intro A";
                case StyleSections.IntroB: return "Intro B";
                case StyleSections.IntroC: return "Intro C";
                case StyleSections.MainA: return "Main A";
                case StyleSections.MainB: return "Main B";
                case StyleSections.MainC: return "Main C";
                case StyleSections.MainD: return "Main D";
                case StyleSections.FillInAA: return "Fill In AA";
                case StyleSections.FillInBB: return "Fill In BB";
                case StyleSections.FillInCC: return "Fill In CC";
                case StyleSections.FillInDD: return "Fill In DD";
                case StyleSections.FillInBA: return "Fill In BA";
                case StyleSections.FillInAB: return "Fill In AB";
                case StyleSections.EndingA: return "Ending A";
                case StyleSections.EndingB: return "Ending B";
                case StyleSections.EndingC: return "Ending C";
                default: return null;
            }
        }

        public static StyleSections GetSectionCode(string sectionName)
        {
            switch (sectionName)
            {
                case "Init": return StyleSections.Init;
                case "Intro A": return StyleSections.IntroA;
                case "Intro B" : return StyleSections.IntroB;
                case "Intro C" : return StyleSections.IntroC;
                case "Main A" : return StyleSections.MainA;
                case "Main B" : return StyleSections.MainB;
                case "Main C" : return StyleSections.MainC;
                case "Main D" : return StyleSections.MainD;
                case "Fill In AA" : return StyleSections.FillInAA;
                case "Fill In BB" : return StyleSections.FillInBB;
                case "Fill In CC" : return StyleSections.FillInCC;
                case "Fill In DD" : return StyleSections.FillInDD;
                case "Fill In BA" : return StyleSections.FillInBA;
                case "Fill In AB" : return StyleSections.FillInAB;
                case "Ending A" : return StyleSections.EndingA;
                case "Ending B" : return StyleSections.EndingB;
                case "Ending C" : return StyleSections.EndingC;
                default: return StyleSections.none;
            }
        }


        public static string GetNoteName(byte noteOffset) => NoteNames[noteOffset % 12];



        public const string StartAction = "0";
        public const string StopAction = "1";
        public const string MainAAction = "2";
        public const string MainBAction = "3";
        public const string FillInABAction = "4";
        public const string FillInBAAction = "5";
        public const string EndingBAction = "6";


    }



}