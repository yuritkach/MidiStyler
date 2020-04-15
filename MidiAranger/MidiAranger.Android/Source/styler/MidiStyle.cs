using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;

namespace MidiAranger.Droid.Source.styler
{
    

    class MIDIStyle
    {


        public MIDISection MidiSection { get; set; }
        //public CASMSection CasmSection { get; set; }
        //public OTSSection OtsSection { get; set; }
        //public MDBSection MdbSection { get; set; }
        //public MHSection MhSection { get; set; }

        public MIDIStyle()
        {
            buffPosition = 0;
            MidiSection = new MIDISection();
         //   CasmSection = new CASMSection();
         //   OtsSection = new OTSSection();
         //   MdbSection = new MDBSection();
         //   MhSection = new MHSection();
        }

        public void LoadStyle(string filename)
        {
   //         filebuff = File.ReadAllBytes(filename); !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Убрать после отладки
            MidiSection.Parse(ref filebuff, ref buffPosition);
        }

        private int buffPosition; 
        private byte[] filebuff;
    }
}