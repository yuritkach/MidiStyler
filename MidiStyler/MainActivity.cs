using System;
using Android.App;
using Android.OS;
using Android.Runtime;
using Android.Support.Design.Widget;
using Android.Support.V7.App;
using Android.Views;
using Android.Widget;
using System.Linq;
using Commons.Music.Midi;

using DE.Humatic.Nmj;
using Java.Lang;
using Android.Util;
using Android.Preferences;
using Android.Content;
using System.Threading.Tasks;

namespace MidiStyler
{
    [Activity(Label = "@string/app_name", Theme = "@style/AppTheme.NoActionBar", MainLauncher = true)]
    public class MainActivity : AppCompatActivity, INetworkMidiListener, INMJSystemListener
    {

        private const System.String TAG = "nmjSample";
        private int lastPosition;

        private NetworkMidiInput midiIn;
        private NetworkMidiOutput midiOut;

        private byte[] myNote = new byte[] { (byte)0x90, (byte)0x24, 0 };

        protected MidiLogger midiLogger;

        private NetworkMidiSystem nmjs;


        private static readonly DateTime Jan1st1970 = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
        public static long CurrentTimeMillis()
        {
            return (long)(DateTime.UtcNow - Jan1st1970).TotalMilliseconds;
        }

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.activity_main);

            ISharedPreferences prefs = PreferenceManager.GetDefaultSharedPreferences(this);

            if (1==1)//(prefs.GetLong("firstRun", -1) == -1)
            {

                try
                {

                    /** 
                     * Configure another channel on top of the default 3.
                     * This is just to demonstrate the basic principle of creating a default configuration
                     * after installation and is not bound to RTP channels: Channels are all virtual and only defined by
                     * preferences set and read via methods in NMJConfig. 
                     */

                    NMJConfig.Edit(this, true);

                    int chIdx = NMJConfig.AddChannel();

                    /** Only really need to set the mode. Other parameters will use mode
                     * dependent default values unless you have "hard set" your own preferences
                     * for a given channel.
                     * If no mode is set, newly created channels will repeat the multicast out / multicast
                     * in / RTP pattern.
                    */
                    NMJConfig.SetMode(chIdx, NMJConfig.Rtp);

                    ISharedPreferencesEditor editor = prefs.Edit();
                    editor.PutLong("firstRun", CurrentTimeMillis());
                    editor.Commit();

                }
                catch (Java.Lang.Exception e)
                {
                    // TODO Auto-generated catch block
                    e.PrintStackTrace();
                }
            }


            /** 
             * Initializing stuff that calls network related methods in an AsyncTask 
             * This is to play nicely with StrictMode in Android API version 11+ and
             * avoids "network on main thread" errors. Can make it a bit more work
             * to have UIs reflect the system's state correctly. In this demo we just build 
             * the UI when the task returns...
             **/

            midiSystemLoad(this);

        }

        protected override void OnDestroy()
        {
            base.OnDestroy();

            try
            {
                /** The exit code takes care of leaving the main thread internally **/
                nmjs.Exit();

                /** 
                 * This removes all remote channels above the first argument
                 * from the configuration, cleaning up eventually discovered services. The first
                 * argument will normally be set to the number of channels an app defined itself -1 
                 * (i.e. the index of the last predefined channel) and expects to find on every run. 
                 * If your app had opened any remote channels, you can pass their IDs
                 * in the int[] argument. They will then be kept but may be relocated.
                 * The method would return an int[] of equal length with the new channel IDs,
                 * so you can update your storage. 
                 * cleanup() only operates on SharedPreferences, no network on main thread trouble to be expected.
                 */
                NMJConfig.Cleanup(3, null);

            }
            catch (Java.Lang.Exception e) { e.PrintStackTrace(); }
        }

        protected void buildUI()
        {

            NMJConfig.AddSystemListener(this);

            SetContentView(Resource.Layout.activity_main);

            Button button = (Button)FindViewById(Resource.Id.Button01);

            INetworkMidiListener ml = this;

            Spinner spinner = (Spinner)FindViewById(Resource.Id.Spinner01);
            int numCh = NMJConfig.NumChannels;
            string[] channelArray = new string[numCh];
            for (int i = 0; i < numCh; i++) channelArray[i] = NMJConfig.GetName(i);

            var adapter = new ArrayAdapter<string>(this, Android.Resource.Layout.SimpleSpinnerItem, channelArray);
            adapter.SetDropDownViewResource(Android.Resource.Layout.SimpleSpinnerDropDownItem);
            spinner.Adapter = adapter;
            spinner.ItemSelected+=(sender, args) => {
                if (args.Position == lastPosition) return;
                lastPosition = args.Position;
                ((TextView)FindViewById(Resource.Id.TextView01)).Text="";
                button.Enabled=true;

                new System.Threading.Thread(() => {
                    /** using null instead of a specific client or listener removes all 
                    * eventually attached clients and closes the port. if you
                    * remove listeners separately the port will be closed when the
                    * last client is detached
                    */

                    try
                    {
                        midiIn.Close(null);
                    }
                    catch (System.NullReferenceException ne)
                    {
                    }

                    try
                    {
                        midiOut.Close(null);
                    }
                    catch (System.NullReferenceException ne)
                    {
                    }

                    try
                    {
                        midiIn = nmjs.OpenInput(args.Position, ml);
                    }
                    catch (Java.Lang.Exception e)
                    {
                        /* channel probably is output only */
                    }
                    
                    try
                    {
                        midiOut = nmjs.OpenOutput(args.Position, ml);
                    }
                    catch (Java.Lang.Exception e)
                    {
                    /** channel probably is input only, disable send button. Must be done on main thread. */
                    Message msg = Message.Obtain();
                    msg.What = 1;
                    midiLogger.SendMessage(msg);
                    }

                }).Start();

            };

            button.Touch += (sender, args) => {
                try
                {
                /** Uses NetworkMIDIOutput.sendMIDIOnThread to avoid "network on main
                * thread" complaints. The method uses a simple wait / notify mechanism and
                * should fire messages without any noticeable delay. If you want more control
                * over this, implement your own nonUI thread and use sendMidi(..) from there instead. 
                * Alternatively ignore Android's "fluid UI first" paradigm and either disable StrictMode or
                * soften the penalty for "network on main thread". After all there may be good 
                * reasons to prefer having MIDI sent directly on a UI event.
                */
                    if (args.Event.Action == MotionEventActions.Down)
                    {
                        myNote[2] = (byte)100;
                        midiOut.SendMidiOnThread(myNote);
                    }
                    else 
                    if (args.Event.Action == MotionEventActions.Up)
                    {
                        myNote[2] = 0;
                        midiOut.SendMidiOnThread(myNote);
                    }
                }
                catch (Java.Lang.Exception ex)
                {
                    ex.PrintStackTrace();
                }
            };

            ((Button)FindViewById(Resource.Id.Button02)).Touch += (sender, args)=>{
                Intent si = new Intent((Context)this, typeof(NMJConfigDialog));
                StartActivity(si);
            };

            TextView tv = (TextView) FindViewById(Resource.Id.TextView01);
            midiLogger = new MidiLogger(tv,this);

        }
    
        public override bool OnCreateOptionsMenu(IMenu menu)
        {
            MenuInflater.Inflate(Resource.Menu.menu_main, menu);
            return true;
        }

        private async void midiSystemLoad(Context ctx)
        {
            await Task.Factory.StartNew(() =>
            {
                try
                {
                    nmjs = NetworkMidiSystem.Get(ctx);

                    /* Set a port base that local RTP sessions will have their ports calculated from (long as no */
                    /* preference has been "hard-set" with setLocalPort(..))*/
                    /* This is to avoid collisions when multiple apps deploying the library on a per app basis*/
                    /* run at the same time. Before publishing anything please request a portbase for your project!*/

                    NMJConfig.SetBasePort(NMJConfig.Rtp, 6704);
                    //TYV
                    NMJConfig.SetIP(NMJConfig.Rtp, "192.168.2.229");
                    //TYV


                    return nmjs;

                }
                catch (Java.Lang.Exception e)
                {
                    e.PrintStackTrace();
                }
                return null;
            });
            buildUI();
        }


        public void MidiReceived(int channel, int ssrc, byte[] data, long timestamp)
        {
            /**
		 * As MIDI does not come in on the UI thread, it needs to be off-loaded in
		 * order to be displayed. Android's Handler class is one way to do this.
		 * Note: If you need to work with large numbers of simultaneous MIDI events 
		 * or high rate streams, consider copying the incoming data into your own 
		 * arrays before sending them via Handlers. nmj will internally reuse memory
		 * and you may risk having data overwritten before you see it appearing in
		 * Handler.handleMessage() otherwise.
		*/
            Message msg = Message.Obtain();
            msg.What = 0;
            Bundle b = new Bundle();
            b.PutByteArray("MIDI", data);
            b.PutInt("CH", channel);
            msg.Data = b;


            midiOut.SendMidiOnThread(data); ////////TYV

            midiLogger.SendMessage(msg);
        }

        public void SystemChanged(int channel, int property, int value)
        {


            Log.Info(TAG, " System changed " + channel + " " + property + " " + value);

            if (channel >= 0 && ((property == NMJConfig.ChState && value == NMJConfig.RtpaChDiscovered) || property == NMJConfig.ChRemoved))
            {
                /**
                 * Given multicast works on your device then DNS might 
                 * uncover more RTP and MWS channels and call this for notification. 
                 * Newly found USB host channels will also be announced here.
                 */
                Spinner spinner = (Spinner)FindViewById(Resource.Id.Spinner01);
                int numCh = NMJConfig.NumChannels;
                string[] channelArray = new string[numCh];
                for (int i = 0; i < numCh; i++) channelArray[i] = NMJConfig.GetName(i);

                var adapter = new ArrayAdapter<string>(this, Android.Resource.Layout.SimpleSpinnerItem, channelArray);
                spinner.Adapter = adapter;
            }
        }

        public void SystemError(int channel, int err, string description)
        {

        /** 
		 * Make sure to override this method to be informed about eventual errors on
		 * lower levels. Much of what the library does runs on port specific threads that
		 * nmj can not throw exception that would be catchable in application code 
		 * from.    
		 */
        Log.Error(TAG, " Error on ch " + channel + ", code: " + err + ", desc: " + description);

        }


        public class MidiLogger : Handler
        {
            private StringBuffer sb = new StringBuffer();
            private TextView tv;
            private Activity activity;

            public MidiLogger(TextView tv, Activity activity) : base()
            {
                this.tv = tv;
                this.activity = activity;
            }

            public override void HandleMessage(Android.OS.Message msg)
            {
                base.HandleMessage(msg);

                switch (msg.What)
                {
                    case 0:
                        Bundle b = msg.Data;
                        sb.Delete(0, sb.Length());
                        byte[] data = b.GetByteArray("MIDI");

                        sb.Append("MIDI received: ");
                        for (int i = 0; i < data.Length; i++) sb.Append(System.String.Format("%X", (data[i] & 0xFF)) + " ");
                        sb.Append("\n");
                        tv.Text = sb.ToString();
                        break;
                    case 1:
                        activity.FindViewById<Button>(Resource.Id.Button01).Enabled = false;
                        break;

                }
            }

        }

    }
    
}

