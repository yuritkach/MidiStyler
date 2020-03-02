using Android.App;
using Android.OS;
using Android.Support.V7.App;
using Java.Net;
using Android.Net.Rtp;
using System;

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
                InetAddress multicastAddress = InetAddress.GetByName("192.168.2.229");
                DatagramSocket socket = new DatagramSocket(5004);
                byte[] buff = new byte[18];

                DatagramPacket packet = new DatagramPacket(buff,buff.Length);
                socket.ConnectAsync(multicastAddress,5008);
                socket.ReceiveAsync(packet);
            }
            catch (Exception e)
            {
            }


        }
    }
 
}