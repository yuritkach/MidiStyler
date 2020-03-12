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

namespace MidiAranger.Droid
{
    [Activity(Label = "MidiAranger", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public partial class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        private byte[] message;
        private int midiClockCount;
        private int timeInterval = 1000;
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.ActivityMain);
            

            MIDISession.GetInstance().Init(this);
            MIDISession.GetInstance().Start();

            Bundle rinfo = new Bundle();
            rinfo.PutString(MIDIConstants.RINFO_ADDR, "192.168.1.63");
            rinfo.PutInt(MIDIConstants.RINFO_PORT, 5008);
            rinfo.PutBoolean(MIDIConstants.RINFO_RECON, true);
            MIDISession.GetInstance().Connect(rinfo);

            FindViewById<Android.Widget.Button>(Resource.Id.SendMidiButton).Click += (object sender, EventArgs e) => {
                SendTestMIDI();
            };

            Subscribe();
            
            var timer = new Timer( SetUIValues, null, timeInterval, timeInterval);

                ;
        }

        private void Subscribe()
        {
            MessagingCenter.Subscribe<MIDIReceivedEvent>(this, "MIDIReceivedEvent", OnMIDIReceivedEvent);
        }

            public bool SendTestMIDI()
        {
            Log.Debug("Main", "sendTestMidi 41,127");
            Bundle testMessage = new Bundle();
            testMessage.PutInt(MIDIConstants.MSG_COMMAND, 0x09);
            testMessage.PutInt(MIDIConstants.MSG_CHANNEL, 0);
            testMessage.PutInt(MIDIConstants.MSG_NOTE, 41);
            testMessage.PutInt(MIDIConstants.MSG_VELOCITY, 127);
            //        MIDIMessage m = MIDIMessage.newUsing(testMessage);
            MIDISession.GetInstance().SendMessage(testMessage);
            //        MIDISession.getInstance().sendMessage(41,127);
            //        MIDIMessage message = MIDISession.getInstance().sendNote(41,127);
            //        if(message != null) {
            //            MIDISession.getInstance().sendNote(41,127);
            //        }
            return true;
        }

        protected void OnMIDIReceivedEvent(MIDIReceivedEvent _event) {
            ParseMidiMessage(_event.message);
        }


        protected void SetUIValues(object state)
        {
         
            
            RunOnUiThread(() => {
                if (message == null)
                    message = new byte[0];
                string s = BitConverter.ToString(message).Replace("-", "");
                FindViewById<TextView>(Resource.Id.miditext).Text = s;
                FindViewById<TextView>(Resource.Id.midiClockCount).Text = (midiClockCount*(60000/timeInterval)/24).ToString();
                midiClockCount = 0;
            });
            
        }

        protected void ParseMidiMessage(byte[] mes) {
            if (mes[0] == 248)
            {
                midiClockCount++;

            }
            else
            {
                message = mes;
            }
        }


    }
}