using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Util;
using midi;
using Xamarin.Forms;
using midi.events;
using System.Threading;
using MidiAranger.Droid.Source.midiplayer;
using static MidiAranger.Droid.Source.midiplayer.ChordRecognizer;
using MidiAranger.Droid.Source.styler;
using static MidiAranger.Droid.Source.common.Common;
using MidiAranger.Droid.Source.common;

namespace MidiAranger.Droid
{
    [Activity(Label = "MidiAranger", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public partial class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        
        private int timeInterval = 100;
        MIDIPlayer mplayer;
        ChordRecognizer chordRecognizer;
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.ActivityMain);
            

           

            FindViewById<Android.Widget.Button>(Resource.Id.MainA).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.MainA,false); };
            FindViewById<Android.Widget.Button>(Resource.Id.MainB).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.MainB,false); };
            FindViewById<Android.Widget.Button>(Resource.Id.FillAB).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.FillInAB,true); };
            FindViewById<Android.Widget.Button>(Resource.Id.FillBA).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.FillInBA,true); };
            FindViewById<Android.Widget.Button>(Resource.Id.EndingB).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.EndingB,false); };



            var timer = new Timer( SetUIValues, null, timeInterval, timeInterval);

            chordRecognizer = new ChordRecognizer();

            MIDIStyle midiStyle = new MIDIStyle();
            midiStyle.LoadStyle("ddd");
            mplayer = new MIDIPlayer(this,midiStyle);
            mplayer.Tracks = midiStyle.MidiSection.Tracks;
            mplayer.Start();

        }

     

        protected void SetUIValues(object state)
        {
            RunOnUiThread(() => {
                if (mplayer.currentPressedNotes == null) return;
                string t = "";
                byte[] pressedNotes = mplayer.currentPressedNotes.ToArray();
                if (pressedNotes.Length > 0) {
                    ChordDefinition cd = chordRecognizer.ChordRecognize(pressedNotes);
                    if (cd != null)
                    {
                        t = Common.GetNoteName(pressedNotes[cd.RootOffset]) + cd.Chord.ChordName.Substring(1) + " -- " + pressedNotes[cd.RootOffset].ToString();
                    }
                }
                FindViewById<TextView>(Resource.Id.miditext).Text = t;
            });
            
        }

      

    }
}