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
            

           

            FindViewById<Android.Widget.Button>(Resource.Id.MainA).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.MainA); };
            FindViewById<Android.Widget.Button>(Resource.Id.MainB).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.MainB); };
            FindViewById<Android.Widget.Button>(Resource.Id.FillAB).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.FillInAB); };
            FindViewById<Android.Widget.Button>(Resource.Id.FillBA).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.FillInBA); };
            FindViewById<Android.Widget.Button>(Resource.Id.EndingB).Click += (object sender, EventArgs e) => { mplayer.GotoSection(StyleSections.EndingB); };



            var timer = new Timer( SetUIValues, null, timeInterval, timeInterval);

            chordRecognizer = new ChordRecognizer();

            MIDIStyle midiStyle = new MIDIStyle();
            midiStyle.LoadStyle("ddd");
            mplayer = new MIDIPlayer(this);
            mplayer.Tracks = midiStyle.MidiSection.Tracks;
            mplayer.Start();

        }

     

        protected void SetUIValues(object state)
        {
            RunOnUiThread(() => {
                if (mplayer.currentPressedNotes == null) return;

                byte[] b = mplayer.currentPressedNotes.ToArray();
                ChordDefinition cd = chordRecognizer.ChordRecognize(b);
                


                FindViewById<TextView>(Resource.Id.miditext).Text = cd!=null?(cd.Chord.ChordName+" -- "+((b.Length>0)?(b[cd.RootOffset].ToString()):"")) : "";
            });
            
        }

      

    }
}