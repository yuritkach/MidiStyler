using Android.App;
using Android.OS;
using Android.Support.V7.App;
using Android.Runtime;
using Android.Widget;
using Android.Media.Midi;
using rtpmidi;
using rtipmidi;
using System.IO;

namespace TestRtp
{
    [Activity(Label = "@string/app_name", Theme = "@style/AppTheme", MainLauncher = true)]
    public class MainActivity : AppCompatActivity
    {
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.activity_main);

            try
            {
//                JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

  //              ServiceInfo serviceInfo =
    //                    ServiceInfo.create("_apple-midi._udp.local.", "rtpMidiJava", 50004, "apple-midi");
    //            jmdns.registerService(serviceInfo);

                //MidiDevice midiDevice =  ;//get MIDI device

                RtpMidiServer server = new RtpMidiServer();
                server.AddRtpMidiSession(
                    new MidiReceiverRtpMidiSession(new TestMidiReceiver()));
                //new MidiDeviceAppleMidiSession(new MidiDeviceModePair(midiDevice, MidiDeviceMode.READ_ONLY)));
                server.Start();

                server.Stop();
            }
            catch (IOException e) {
            }


            }
    }

        public class TestMidiReceiver : MidiReceiver
        {
            public override void OnSend(byte[] msg, int offset, int count, long timestamp)
            {
                throw new System.NotImplementedException();
            }
        }

    }