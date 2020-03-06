using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Util;
using midi;

namespace MidiAranger.Droid
{
    [Activity(Label = "MidiAranger", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        protected override void OnCreate(Bundle savedInstanceState)
        {
            TabLayoutResource = Resource.Layout.Tabbar;
            ToolbarResource = Resource.Layout.Toolbar;

            base.OnCreate(savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            LoadApplication(new App());

            MIDISession.GetInstance().Init(this);
            MIDISession.GetInstance().Start();

            Bundle rinfo = new Bundle();
            rinfo.PutString(MIDIConstants.RINFO_ADDR, "192.168.56.1");
            rinfo.PutInt(MIDIConstants.RINFO_PORT, 5008);
            rinfo.PutBoolean(MIDIConstants.RINFO_RECON, true);
            MIDISession.GetInstance().Connect(rinfo);

            for (; ; )
            {
                SendTestMIDI();
                System.Threading.Thread.Sleep(100);
            }
                
            
        }


        public void SendTestMIDI()
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
        }

    }
}