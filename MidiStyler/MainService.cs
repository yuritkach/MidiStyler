using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using DE.Humatic.Nmj;

namespace MidiStyler
{

    // Example of creating an explicit Intent in an Android Activity
    //Intent myIntent = new Intent(this, typeof(MidiStylerService));
    //myIntent.data = Uri.Parse(bla_bla_bla);


    [Service(IsolatedProcess = true)]
    [IntentFilter(new String[] { "com.MidiStyler.MidiService" })]
    class MidiStylerService : Service/*, INetworkMidiListener*/
    {
     //   protected string serverIP = "192.168.1.63";
     //   protected int serverPort = 5008;

     //   protected NetworkMidiInput midiIn;
     //   protected NetworkMidiOutput midiOut;
     //   protected NetworkMidiSystem nmjs;
     //   protected int networkChanelIndex;

     //   private byte[] note = new byte[] { (byte)0x90, (byte)0x24, 0 };

     //   protected void init() {

     //       try
     //       {
     //           NMJConfig.Edit(this, true);
     //           networkChanelIndex = NMJConfig.AddChannel();
     //           NMJConfig.SetMode(networkChanelIndex, NMJConfig.Rtp);
     //           NMJConfig.SetIP(networkChanelIndex, serverIP);
     //           NMJConfig.SetPort(networkChanelIndex, serverPort);
     //           NMJConfig.SetBasePort(NMJConfig.Rtp, 6704);
     //       }
     //       catch (Exception e)
     //       {
     //           throw new Exception(e.Message);
     //       }
     //       midiSystemLoad(this);

     //   }

     //   private async void midiSystemLoad(Context ctx)
     //   {
     //       await Task.Factory.StartNew(() =>
     //       {
     //           try
     //           {
     //               nmjs = NetworkMidiSystem.Get(ctx);
     //               return nmjs;
     //           }
     //           catch (Exception e)
     //           {
     //               throw new Exception(e.Message);
     //           }
     //       });
     //       // SystemLoaded, do something...
     //   }

     //   public void MidiReceived(int channel, int ssrc, byte[] data, long timestamp)
     //   {
     //       /**
     // * As MIDI does not come in on the UI thread, it needs to be off-loaded in
     // * order to be displayed. Android's Handler class is one way to do this.
     // * Note: If you need to work with large numbers of simultaneous MIDI events 
     // * or high rate streams, consider copying the incoming data into your own 
     // * arrays before sending them via Handlers. nmj will internally reuse memory
     // * and you may risk having data overwritten before you see it appearing in
     // * Handler.handleMessage() otherwise.
     //*/
     //       Message msg = Message.Obtain();
     //       msg.What = 0;
     //       Bundle b = new Bundle();
     //       b.PutByteArray("MIDI", data);
     //       b.PutInt("CH", channel);
     //       msg.Data = b;


     //       midiOut.SendMidiOnThread(data); ////////TYV

     //      // midiLogger.SendMessage(msg);
     //   }

        public override IBinder OnBind(Intent intent)
        {
           throw new NotImplementedException();
        }
    }
}