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

namespace MidiAranger.Droid
{
    [Activity(Label = "MidiAranger", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public partial class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        
        private int timeInterval = 1000;
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.ActivityMain);
            

           

            FindViewById<Android.Widget.Button>(Resource.Id.SendMidiButton).Click += (object sender, EventArgs e) => {
             //   SendTestMIDI();
            };

            
            
            var timer = new Timer( SetUIValues, null, timeInterval, timeInterval);

            MIDIFile midiFile = new MIDIFile();
            midiFile.InitMidiFile("ddd");
            MIDIPlayer mplayer = new MIDIPlayer(this);
            mplayer.Tracks = midiFile.tracks;
            mplayer.Start();

        }

     

        protected void SetUIValues(object state)
        {
         
            
            RunOnUiThread(() => {
//                if (message == null)
//                    message = new byte[0];
//                string s = BitConverter.ToString(message).Replace("-", "");
//                FindViewById<TextView>(Resource.Id.miditext).Text = s;
//                FindViewById<TextView>(Resource.Id.midiClockCount).Text = (midiClockCount*(60000/timeInterval)/24).ToString();
//                midiClockCount = 0;
            });
            
        }

      

    }
}