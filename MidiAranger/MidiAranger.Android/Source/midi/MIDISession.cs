using Android.Annotation;
using Android.Content;
using Android.Content.PM;
using Android.Net;
using Android.Net.Nsd;
using Android.Net.Wifi;
using Android.OS;
using Android.Runtime;
using Android.Util;
using Com.Xamarin.Formsviewgroup;
using Java.Lang;
using Java.Math;
using Java.Net;
using Java.Util;
using midi.events;
using midi.internal_events;
using MidiAranger.Droid.Source.common;
using System;
using System.Collections.Generic;
using Xamarin.Forms;
using Math = Java.Lang.Math;
using String = Java.Lang.String;

//using net.rehacktive.waspdb.WaspDb;
//using net.rehacktive.waspdb.WaspFactory;
//using net.rehacktive.waspdb.WaspHash;
//using net.rehacktive.waspdb.WaspListener;
//using net.rehacktive.waspdb.WaspObserver;


namespace midi {

    public class MIDISession
    {
        private static MIDISession midiSessionInstance;
        private readonly static string TAG = typeof(MIDISession).Name;
        private readonly static string BONJOUR_TYPE = "_apple-midi._udp";
        
        // private WaspDb db;
        // private WaspHash midiAddressBook;
        // private WaspObserver observer;
        Dictionary<string, MIDIAddressBookEntry> midiAddressBook;


        private void Subscribe()
        {
            MessagingCenter.Subscribe<AddressBookReadyEvent>(this, "AddressBookReadyEvent", OnAddressBookReadyEvent);
            MessagingCenter.Subscribe<StreamConnectedEvent>(this, "StreamConnectedEvent", OnStreamConnected);
            MessagingCenter.Subscribe<ListeningEvent>(this, "ListeningEvent", OnMIDI2ListeningEvent);
            MessagingCenter.Subscribe<SyncronizeStartedEvent>(this, "SyncronizeStartedEvent", OnSyncronizeStartedEvent);
            MessagingCenter.Subscribe<SyncronizeStoppedEvent>(this, "SyncronizeStoppedEvent", OnSyncronizeStoppedEvent);
            MessagingCenter.Subscribe<ConnectionEstablishedEvent>(this, "ConnectionEstablishedEvent", OnConnectionEstablishedEvent);
            MessagingCenter.Subscribe<PacketEvent>(this, "PacketEvent", OnPacketEvent);
            MessagingCenter.Subscribe<StreamDisconnectEvent>(this, "StreamDisconnectEvent", OnStreamDisconnectEvent);
            MessagingCenter.Subscribe<ConnectionFailedEvent>(this, "ConnectionFailedEvent", OnConnectionFailedEvent);
        }

        private MIDISession()
        {
            this.rate = 10000;
            this.port = 5008;
            System.Random rand = new System.Random();
            this.ssrc = (int)Math.Round(rand.NextDouble() * Java.Lang.Math.Pow(2, 8 * 4));
            this.startTime = (Common.CurrentTimeMillis() / 1000L) * (long)this.rate;
            this.startTimeHR = System.DateTime.Now.Millisecond;// nanoTime();
            this.registered_eb = false;
            this.published_bonjour = false;
            this.autoReconnect = false;

            SetupLocalDB();
            Subscribe();
        }

        public static MIDISession GetInstance()
        {
            if (midiSessionInstance == null)
            {
                midiSessionInstance = new MIDISession();
            }
            return midiSessionInstance;
        }

        private bool shouldBeRunning = false;
        private bool isRunning = false;
        private Context appContext = null;
        private SparseArray<MIDIStream> streams;
        private SparseArray<MIDIStream> pendingStreams;
        private Dictionary<String, Bundle> failedConnections;

        public string bonjourName = Build.Model;
        public InetAddress bonjourHost = null;
        public InetAddress netmask = null;

        public int bonjourPort = 0;

        public int port;
        public int ssrc;
        private bool registered_eb = false;
        private bool published_bonjour = false;
        private bool initialized = false;
        private readonly bool started = false;
        
        private readonly int rate;
        private readonly long startTime;
        private readonly long startTimeHR;

        private MIDIPort controlChannel;
        private MIDIPort messageChannel;

        private NsdManager mNsdManager;
        private NsdManager.IResolveListener mResolveListener;
        private NsdManager.IRegistrationListener mRegistrationListener;
        private NsdServiceInfo serviceInfo = new NsdServiceInfo();

        private bool autoReconnect = false;

        public void Init(Context context)
        {
            if (started)
                return;

            this.appContext = context;
            if (!registered_eb)
            {
                // EventBus.getDefault().register(this); TYV
                registered_eb = true;
            }
            if (!initialized)
            {
                //setupWaspDB();   TYV
                initialized = true;
            }
        }

        public void Start(Context context)
        {
            Init(context);
            Start();
        }

        public void Start()
        {
            SetupNetworkListener();
            if (!IsOnline())
            {
                Log.Debug(TAG, "MIDI Start : not online");
                shouldBeRunning = true;
                return;
            }
            if (this.appContext == null)
            {
                Log.Debug(TAG, "MIDI Start : ctx is null");
                return;
            }
            if (!registered_eb)
            {
                //EventBus.getDefault().register(this); TYV
                registered_eb = true;
            }
            try
            {
                this.bonjourHost = InetAddress.GetByName("127.0.0.1");
            }
            catch (UnknownHostException e)
            {
                e.PrintStackTrace();
            }
            //        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            //
            //            this.bonjourHost = getWifiAddressNew();
            //        } else {
            this.bonjourHost = GetWifiAddress();
            //        }
            this.bonjourPort = this.port;
            controlChannel = MIDIPort.NewUsing(this.port);
            controlChannel.Start();
            messageChannel = MIDIPort.NewUsing(this.port + 1);
            messageChannel.Start();

            this.streams = new SparseArray<midi.MIDIStream>(2);
            this.pendingStreams = new SparseArray<midi.MIDIStream>(2);
            this.failedConnections = new Dictionary<String, Bundle>(2);
            try
            {
                InitializeResolveListener();
                RegisterService();
                isRunning = true;
                shouldBeRunning = false;

                //EventBus.getDefault().post(new MIDISessionStartEvent());
                MessagingCenter.Send<MIDISessionStartEvent>(new MIDISessionStartEvent(), "MIDISessionStartEvent");
                CheckAddressBookForReconnect();
            }
            catch (UnknownHostException e)
            {
                e.PrintStackTrace();
            }
        }


        public void Stop()
        {
            if (streams != null)
                for (int i = 0; i < streams.Size(); i++)
                    streams.Get(streams.KeyAt(i)).SendEnd();
            if (pendingStreams != null)
                for (int i = 0; i < pendingStreams.Size(); i++)
                    pendingStreams.Get(pendingStreams.KeyAt(i)).SendEnd();
            if (controlChannel != null)
                controlChannel.Stop();
            if (messageChannel != null)
                messageChannel.Stop();
            isRunning = false;
            ShutdownNSDListener();
            MessagingCenter.Send<MIDISessionStopEvent>(new MIDISessionStopEvent(), "MIDISessionStopEvent");
        }


        ~MIDISession()
        {
            Stop();
            RemoveNetworkListener();
            registered_eb = false;
        }

        public void Connect(Bundle rinfo)
        {
            if (isRunning)
            {
                if (!IsAlreadyConnected(rinfo))
                {
                    Log.Debug(TAG, "opening connection to " + rinfo);
                    MIDIStream stream = new MIDIStream();
                    if (failedConnections.ContainsKey(new String(RinfoToKey(rinfo))))
                    {
                        Bundle reconnectRinfo = failedConnections.GetValueOrDefault(new String(RinfoToKey(rinfo)));
                        if (reconnectRinfo.GetInt(MIDIConstants.RINFO_FAIL, 0) > 3)
                        {
                            Log.Debug(TAG, "failed more than 3 times...");
                            return;
                        }

                        stream.Connect(reconnectRinfo);
                    }
                    else
                    {
                        stream.Connect(rinfo);
                    }
                    Log.Debug(TAG, "put " + stream.initiator_token.ToString() + " in pendingStreams");
                    pendingStreams.Put(stream.initiator_token, stream);
                }
                else
                {
                    Log.Error(TAG, "already have open session to " + rinfo.ToString());
                }
            }
            else
            {
                Log.Error(TAG, "MIDI not running");
            }
        }

        public void Disconnect(Bundle rinfo)
        {
            Log.Debug(TAG, "disconnect " + rinfo);
            MIDIStream s = GetStream(rinfo);
            if (s != null)
            {
                Log.Debug(TAG, "stream to disconnect : " + s.ssrc);
                s.SendEnd();
            }
            else
            {
                Log.Error(TAG, "didn't find stream");
            }
        }

        public void Disconnect(int remote_ssrc)
        {
            if (remote_ssrc != 0)
            {
                streams.Get(remote_ssrc).Disconnect();
                streams.Get(remote_ssrc).Shutdown();
                streams.Remove(remote_ssrc);
            }

        }

        private MIDIStream GetStream(Bundle rinfo)
        {
            for (int i = 0; i < streams.Size(); i++)
            {
                MIDIStream s = streams.Get(streams.KeyAt(i));
                if (s.ConnectionMatch(rinfo))
                    return s;
            }
            return null;
        }

        public void SetAutoReconnect(bool b)
        {
            autoReconnect = b;
        }

        public bool GetAutoReconnect()
        {
            return autoReconnect;
        }

        private bool IsAlreadyConnected(Bundle rinfo)
        {
            Log.Debug(TAG, "isAlreadyConnected " + pendingStreams.Size() + " " + streams.Size());
            bool existsInPendingStreams = false;
            bool existsInStreams = false;
            Log.Error(TAG, "checking pendingStreams... (" + pendingStreams.Size() + ") " + rinfo.ToString());
            for (int i = 0; i < pendingStreams.Size(); i++)
            {
                MIDIStream ps = pendingStreams.Get(pendingStreams.KeyAt(i));
                if ((ps != null) && ps.ConnectionMatch(rinfo))
                {
                    existsInPendingStreams = true;
                    break;
                }
            }

            if (!existsInPendingStreams)
            {
                for (int i = 0; i < streams.Size(); i++)
                {
                    MIDIStream s = streams.Get(streams.KeyAt(i));
                    if (s == null)
                        Log.Error(TAG, "error in isAlreadyConnected " + i + " rinfo " + rinfo.ToString());
                    else
                    {
                        Log.Error(TAG, "checking streams...");
                        if (streams.Get(streams.KeyAt(i)).ConnectionMatch(rinfo))
                            existsInStreams = true;
                    }
                }
            }

            Log.Debug(TAG, "existsInPendingStreams:" + (existsInPendingStreams ? "YES" : "NO"));
            Log.Debug(TAG, "existsInStreams:" + (existsInStreams ? "YES" : "NO"));

            return (existsInPendingStreams || existsInStreams);
            //        for (int i = 0; i < streams.size(); i++) {
            ////            streams.get(streams.keyAt(i)).sendMessage(message);
            ////            String key = ((MIDIStream)streams.keyAt(i));
            ////            Bundle b = (MIDIStream)streams. .getRinfo1();
            //            Bundle b = streams.get(streams.keyAt(i)).getRinfo1();
            //            if(b.getString(MIDIConstants.RINFO_ADDR).equals(rinfo.getString(MIDIConstants.RINFO_ADDR)) && b.getInt(MIDIConstants.RINFO_PORT) == rinfo.getInt(MIDIConstants.RINFO_PORT)) {
            //                return true;
            //            }
            //        }
            //        return false;
        }

        public void SendUDPMessage(MIDIControl control, Bundle rinfo)
        {
            if (control != null && rinfo != null)
            {
                Log.Debug("MIDISession", "sendUDPMessage:control " + rinfo.ToString());
                if (rinfo.GetInt(midi.MIDIConstants.RINFO_PORT) % 2 == 0)
                {
                    Log.Debug("MIDISession", "sendUDPMessage control 5004 rinfo:" + rinfo.ToString());
                    controlChannel.SendMidi(control, rinfo);
                }
                else
                {
                    Log.Debug("MIDISession", "sendUDPMessage control 5005 rinfo:" + rinfo.ToString());
                    messageChannel.SendMidi(control, rinfo);
                }
            }
            else
            {
                Log.Error(TAG, "rinfo or control was null...");
            }
        }

        public void SendUDPMessage(MIDIMessage m, Bundle rinfo)
        {
            Log.Debug("MIDISession", "sendUDPMessage:message " + rinfo.ToString());
            if (m != null && rinfo != null)
            {
                if (rinfo.GetInt(midi.MIDIConstants.RINFO_PORT) % 2 == 0)
                {
                    Log.Debug("MIDISession", "sendUDPMessage message 5004 rinfo:" + rinfo.ToString());
                    controlChannel.SendMidi(m, rinfo);
                }
                else
                {
                    Log.Debug("MIDISession", "sendUDPMessage message 5004 rinfo:" + rinfo.ToString());
                    messageChannel.SendMidi(m, rinfo);
                }
            }
        }
        
        public void SendMessage(byte[] msg)
        {
            if (published_bonjour && streams.Size() > 0)
            {
                Log.Debug("MIDISession", "byte array:" + BitConverter.ToString(msg).Replace("-", ""));
                MIDIMessage message = new MIDIMessage();
                message.ssrc = this.ssrc;

                for (int i = 0; i < streams.Size(); i++)
                    streams.Get(streams.KeyAt(i)).SendMessage(message);
            }
        }

        // TODO : figure out what this is supposed to return... becuase I don't think this is right
        // getNow returns a unix (long)timestamp
        public long GetNow()
        {
            long hrtime = System.DateTime.Now.Millisecond - this.startTimeHR;
            long result = Math.Round((hrtime / 1000L / 1000L / 1000L) * this.rate);
            return result;
        }

        public void OnAddressBookReadyEvent(AddressBookReadyEvent _event)
        {
            Log.Debug(TAG, "Addressbook ready");
            CheckAddressBookForReconnect();
            DumpAddressBook();
        }

        // streamConnectedEvent is called when client initiates connection... ...
        public void OnStreamConnected(StreamConnectedEvent e)
        {
            Log.Debug("MIDISession", "StreamConnectedEvent");
            Log.Debug(TAG, "get " + e.initiator_token + " from pendingStreams");
            MIDIStream stream = pendingStreams.Get(e.initiator_token);
            if (stream != null)
            {
                Log.Debug(TAG, "put " + e.initiator_token + " in  streams");
                streams.Put(stream.ssrc, stream);
            }
            Log.Debug(TAG, "remove " + e.initiator_token + " from pendingStreams");
            pendingStreams.Delete(e.initiator_token);
            MessagingCenter.Send<MIDIConnectionEstablishedEvent>(new MIDIConnectionEstablishedEvent(new Bundle()), "MIDIConnectionEstablishedEvent");
        }


        public void OnMIDI2ListeningEvent(ListeningEvent e)
        {
        }

        public void OnSyncronizeStartedEvent(SyncronizeStartedEvent e)
        {
            Log.Debug("MIDISession","SyncronizeStartedEvent");
            MessagingCenter.Send<MIDISyncronizationStartEvent>(new MIDISyncronizationStartEvent(e.rinfo), "MIDISyncronizationStartEvent");
        }


        public void OnSyncronizeStoppedEvent(SyncronizeStoppedEvent e)
        {
            Log.Debug("MIDISession","SyncronizeStoppedEvent");
            MessagingCenter.Send<MIDISyncronizationCompleteEvent>(new MIDISyncronizationCompleteEvent(e.rinfo), "MIDISyncronizationCompleteEvent");
        }


        public void OnConnectionEstablishedEvent(ConnectionEstablishedEvent e)
        {
            Log.Debug("MIDISession", "ConnectionEstablishedEvent");
            MessagingCenter.Send<MIDIConnectionEstablishedEvent>(new MIDIConnectionEstablishedEvent(e.rinfo), "MIDIConnectionEstablishedEvent");
            AddToAddressBook(e.rinfo);
        }


        public void OnPacketEvent(PacketEvent e)
        {
            Log.Debug("MIDISession", "PacketEvent packet from " + e.GetAddress().HostAddress + ":" + e.GetPort());
            // try control first
            MIDIControl applecontrol = new MIDIControl();
            MIDIMessage message = new MIDIMessage();
            if (applecontrol.Parse(e))
            {
                Log.Debug("MIDISession", "- parsed as apple control packet");
                if (applecontrol.IsValid())
                {
                    //                applecontrol.dumppacket();
                    if (applecontrol.initiator_token != 0)
                    {
                        MIDIStream pending = pendingStreams.Get(applecontrol.initiator_token);
                        if (pending != null)
                        {
                            Log.Debug("MIDISession", " - got pending stream by token");
                            pending.HandleControlMessage(applecontrol, e.GetRInfo());
                            return;
                        }
                    }
                    // check if this applecontrol.ssrc is known stream
                    MIDIStream stream = streams.Get(applecontrol.ssrc);
                    if (stream == null)
                    {
                        // else, check if this is an invitation
                        //       create stream and tell stream to handle invite
                        Log.Debug("MIDISession", "- create new stream " + applecontrol.ssrc);
                        stream = new MIDIStream();
                        streams.Put(applecontrol.ssrc, stream);
                    }
                    else
                    {
                        Log.Debug("MIDISession", " - got existing stream by ssrc " + applecontrol.ssrc);
                    }
                    Log.Debug("MIDISession", "- pass control packet to stream");
                    stream.HandleControlMessage(applecontrol, e.GetRInfo());
                }
                // control packet
            }
            else
            {
                Log.Debug("MIDISession","message?");
                message.ParseMessage(e);
                if (message.IsValid())
                    MessagingCenter.Send<MIDIReceivedEvent>(new MIDIReceivedEvent(message.ToByteArray()), "MIDIReceivedEvent");
            }
        }


        public void OnStreamDisconnectEvent(StreamDisconnectEvent e)
        {
            Log.Debug(TAG, "onStreamDisconnectEvent - ssrc:" + e.stream_ssrc + " it:" + e.initiator_token + " #streams:" + streams.Size() + " #pendstreams:" + pendingStreams.Size());
            MIDIStream a = streams.Get(e.stream_ssrc, null);
            if (a == null)
            {
                Log.Debug(TAG, "can't find stream with ssrc " + e.stream_ssrc);
            }
            else
            {
                Bundle rinfo = (Bundle)a.rinfo1.Clone();
                a.Shutdown();
                streams.Delete(e.stream_ssrc);
                CheckAddressBookForReconnect();

                //            if(rinfo.getBoolean(MIDIConstants.RINFO_RECON,false)) {
                //                Log.Debug(TAG,"will try reconnect to "+rinfo.getString(RINFO_ADDR));
                //                connect(rinfo);
                //            } else {
                //                Log.Debug(TAG,"will not reconnect to "+rinfo.getString(RINFO_ADDR));
                //            }
                //            if(autoReconnect) {
                //                connect(rinfo);
                //            }
            }
            if (e.initiator_token != 0)
            {
                MIDIStream p = pendingStreams.Get(e.initiator_token, null);
                if (p == null)
                {
                    Log.Debug(TAG, "can't find pending stream with IT " + e.initiator_token);
                }
                else
                {
                    p.Shutdown();
                    pendingStreams.Delete(e.initiator_token);
                }
            }
            if (e.rinfo != null)
            {
                MessagingCenter.Send<MIDIConnectionEndEvent>(new MIDIConnectionEndEvent((Bundle)e.rinfo.Clone()), "MIDIConnectionEndEvent");
            }
            Log.Debug(TAG, "                     - ssrc:" + e.stream_ssrc + " it:" + e.initiator_token + " #streams:" + streams.Size() + " #pendstreams:" + pendingStreams.Size());
        }


        public void OnConnectionFailedEvent(ConnectionFailedEvent e)
        {
            Log.Debug(TAG, "onConnectionFailedEvent");
            switch (e.code)
            {
                case MIDIFailCode.REJECTED_INVITATION: Log.Debug(TAG, "...REJECTED_INVITATION initiator_code " + e.initiator_code); break;
                case MIDIFailCode.SYNC_FAILURE: Log.Debug(TAG, "...SYNC_FAILURE initiator_code " + e.initiator_code); break;
                case MIDIFailCode.UNABLE_TO_CONNECT: Log.Debug(TAG, "...UNABLE_TO_CONNECT initiator_code " + e.initiator_code); break;
                case MIDIFailCode.CONNECTION_LOST: Log.Debug(TAG, "...CONNECTION_LOST initiator_code " + e.initiator_code); break;
                default: break;
            }
            pendingStreams.Delete(e.initiator_code);
            String key = new String(RinfoToKey(e.rinfo));
            if (failedConnections.ContainsKey(key))
            {
                Bundle r = failedConnections.GetValueOrDefault<String, Bundle>(key);
                int fail = r.GetInt(MIDIConstants.RINFO_FAIL, 0);
                r.PutInt(MIDIConstants.RINFO_FAIL, fail + 1);
                failedConnections.Add(key, r);
                Log.Debug(TAG, " rinfo: " + r.ToString());
            }
            else
            {
                e.rinfo.PutInt(MIDIConstants.RINFO_FAIL, 1);
                failedConnections.Add(key, e.rinfo);
                Log.Debug(TAG, " rinfo: " + e.rinfo.ToString());
            }
            CheckAddressBookForReconnect();
        }

        //    @TargetApi(21)
        //    public InetAddress getWifiAddressNew() {
        //
        //
        //        InetAddress a = null;
        //        WifiManager wm = (WifiManager) appContext.getSystemService(WIFI_SERVICE);
        //        Network network = wm.getCurrentNetwork();
        //        ConnectivityManager cm = (ConnectivityManager)
        //                appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        //        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        //
        //
        //        if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
        //            Network network = cm.getActiveNetwork();
        //            LinkProperties prop = cm.getLinkProperties();
        //
        //        }
        //        //        LinkProperties prop = cm.getLinkProperties(wifiInfo);
        //
        //        Iterator<InetAddress> dns = prop.getDnsServers().iterator();
        //        while (dns.hasNext()) {
        //            Log.Debug(TAG,"DNS: "+dns.next().getHostAddress());
        //        }
        //
        //        Log.Debug(TAG,"DNS: "+prop.getDnsServers());
        //        Log.Debug(TAG,"domains: "+prop.getDomains());
        //        Log.Debug(TAG,"imterface: "+prop.getInterfaceName());
        //        Log.Debug(TAG,"string: "+prop.toString());
        //        Log.Debug(TAG,"mask: "+prop.);
        //
        //        Iterator<LinkAddress> iter = prop.getLinkAddresses().iterator();
        //        while(iter.hasNext()) {
        //            a = iter.next().getAddress();
        //            Log.Debug(TAG,"address: "+a.getHostAddress());
        //        }
        //        return a;
        //
        //    }

        public InetAddress GetWifiAddress()
        {
            try
            {
                if (appContext == null)
                    return InetAddress.GetByName("127.0.0.1");
                DhcpInfo dhcpInfo;
                WifiManager wm = (WifiManager)appContext.GetSystemService(Context.WifiService);
                dhcpInfo = wm.DhcpInfo;
                Log.Debug(TAG, "DNS 1: " + IntToIp(dhcpInfo.Dns1));
                Log.Debug(TAG, "DNS 2: " + IntToIp(dhcpInfo.Dns2));
                Log.Debug(TAG, "Gateway: " + IntToIp(dhcpInfo.Gateway));
                Log.Debug(TAG, "ip Address: " + IntToIp(dhcpInfo.IpAddress));
                Log.Debug(TAG, "lease time: " + IntToIp(dhcpInfo.LeaseDuration));
                Log.Debug(TAG, "mask: " + dhcpInfo.Netmask);
                Log.Debug(TAG, "server ip: " + IntToIp(dhcpInfo.ServerAddress));
                //
                //            vIpAddress="IP Address: "+intToIp(dhcpInfo.ipAddress);
                //            vLeaseDuration="Lease Time: "+String.valueOf(dhcpInfo.leaseDuration);
                //            vNetmask="Subnet Mask: "+intToIp(dhcpInfo.netmask);
                //            vServerAddress="Server IP: "+intToIp(dhcpInfo.serverAddress);

                byte[] ipbytearray = BigInteger.ValueOf(wm.ConnectionInfo.IpAddress).ToByteArray();
                ReverseByteArray(ipbytearray);
                if (ipbytearray.Length != 4)
                    return InetAddress.GetByName("127.0.0.1");

                IEnumeration nis = NetworkInterface.NetworkInterfaces;
                while (nis.HasMoreElements)
                {
                    NetworkInterface ni = (NetworkInterface)nis.NextElement();
                    List<InterfaceAddress> intAs = new List<InterfaceAddress>();
                    foreach (var item in ni.InterfaceAddresses)
                        intAs.Add(item);

                    foreach (InterfaceAddress ia in intAs)
                    {
                        Log.Debug(TAG, " ia: " + ia.Address.HostAddress);
                        if (SameIP(ia.Address, InetAddress.GetByAddress(ipbytearray)))
                        {
                            Log.Debug(TAG, "same!!! " + ia.Address.HostAddress + "/" + ia.NetworkPrefixLength);
                            Log.Debug(TAG, "netmask: " + IntToIp(PrefixLengthToNetmaskInt(ia.NetworkPrefixLength)));
                            netmask = InetAddress.GetByName((IntToIp(PrefixLengthToNetmaskInt(ia.NetworkPrefixLength))).ToString());
                        }
                    }
                    //                Enumeration<InetAddress> inetAs = ni.getInetAddresses();
                    //                while(inetAs.hasMoreElements()) {
                    //                    InetAddress addr = inetAs.nextElement();
                    //                    Log.Debug(TAG, " ia: "+addr.getHostAddress());
                    //                    if(sameIP(addr,InetAddress.getByAddress(ipbytearray))) {
                    //                        Log.Debug(TAG,"same!!! "+ni.getDisplayName() + "  "+ni.toString());
                    //
                    //                    }
                    //                }

                }

                //            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getByAddress(ipbytearray));
                //            for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
                //
                //                    networkInterface.isLoopback()
                //                    Log.Debug(TAG, "network prefix: " + address.getNetworkPrefixLength());
                //            }

                //            if(ipbytearray != null && ipbytearray.length > 0) {
                //                Log.Debug(TAG, "new netmask: " + intToIp(prefixLengthToNetmaskInt(getNetmask(InetAddress.getByAddress(ipbytearray)))));
                //            }

                return InetAddress.GetByAddress(ipbytearray);
            }
            catch (UnknownHostException)
            {
                return null;
            }
            catch (SocketException e)
            {
                e.PrintStackTrace();
                return null;
            }
            catch (NullPointerException e)
            {
                e.PrintStackTrace();
                return null;
            }
        }

        //    public String getLocalIpAddress() {
        //        try {
        //            for (Enumeration<NetworkInterface> en = NetworkInterface
        //                    .getNetworkInterfaces(); en.hasMoreElements();) {
        //                NetworkInterface intf = en.nextElement();
        //                for (Enumeration<InetAddress> enumIpAddr = intf
        //                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
        //                    InetAddress inetAddress = enumIpAddr.nextElement();
        //                    System.out.println("ip1--:" + inetAddress);
        //                    System.out.println("ip2--:" + inetAddress.getHostAddress());
        //
        //                    // for getting IPV4 format
        //                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4 = inetAddress.getHostAddress())) {
        //
        //                        String ip = inetAddress.getHostAddress().toString();
        //                        System.out.println("ip---::" + ip);
        ////                        EditText tv = (EditText) findViewById(R.id.ipadd);
        ////                        tv.setText(ip);
        //                        // return inetAddress.getHostAddress().toString();
        //                        return ip;
        //                    }
        //                }
        //            }
        //        } catch (Exception ex) {
        //            Log.e("IP Address", ex.toString());
        //        }
        //        return null;
        //    }

        public int PrefixLengthToNetmaskInt(int prefixLength)
        {
            try
            {
                Log.Debug(TAG, "prefixLengthToNetmaskInt:" + prefixLength);
                if (prefixLength < 0 || prefixLength > 32)
                {
                    //            throw new IllegalArgumentException("Invalid prefix length (0 <= prefix <= 32)");
                    return 0;
                }
                int value = (int)(0xffffffff << (32 - prefixLength));
                return Integer.ReverseBytes(value);
            }

            catch (Java.Lang.Exception e)
            {
                throw new IllegalArgumentException(e.Message);
            }

        }

        public int GetNetmask(InetAddress addr)
        {
            try
            {
                NetworkInterface networkInterface = NetworkInterface.GetByInetAddress(addr);
                Log.Debug(TAG, "    interface: " + addr.HostAddress);
                foreach (InterfaceAddress address in networkInterface.InterfaceAddresses)
                {
                    Log.Debug(TAG, "    " + address.Address.HostAddress + "/" + address.NetworkPrefixLength);
                    int netPrefix = address.NetworkPrefixLength;
                    return netPrefix;
                }
            }
            catch (SocketException e)
            {
                e.PrintStackTrace();
            }
            return 0;
        }

        public String IntToIp(int i)
        {
            i = Integer.ReverseBytes(i);
            return new String(((i >> 24) & 0xFF) + "." +
                    ((i >> 16) & 0xFF) + "." +
                    ((i >> 8) & 0xFF) + "." +
                    (i & 0xFF));
        }

        private static void ReverseByteArray(byte[] array)
        {
            if (array == null)
                return;
            int i = 0;
            int j = array.Length - 1;
            byte tmp;
            while (j > i)
            {
                tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;
                j--;
                i++;
            }
        }


        // --------------------------------------------
        // bonjour stuff
        //

        public void SetBonjourName(string name)
        {
            this.bonjourName = name;
        }

        private void RegisterService()
        {
            try
            {
                if (Build.VERSION.SdkInt >= BuildVersionCodes.JellyBean)
                {
                    // Create the NsdServiceInfo object, and populate it.
                    // The name is subject to change based on conflicts
                    // with other services advertised on the same network.

                    serviceInfo.ServiceName = this.bonjourName;
                    serviceInfo.ServiceType = BONJOUR_TYPE;
                    serviceInfo.Host = this.bonjourHost;
                    serviceInfo.Port = this.bonjourPort;

                    //            if(DEBUG) {
                    //                Log.Debug(TAG,"register service: "+serviceInfo.toString());
                    //            }
                    mNsdManager = (NsdManager)appContext.ApplicationContext.GetSystemService(Context.NsdService);

                    InitializeNSDRegistrationListener();

                    mNsdManager.RegisterService(serviceInfo, NsdProtocol.DnsSd, mRegistrationListener);
                    //    mNsdManager.ResolveService(serviceInfo, mResolveListener);
                }
            }
            catch (System.Exception e)
            {
                throw new UnknownHostException(e.Message);
            }
        }

        public class MIDIRegistrationListener : Java.Lang.Object, NsdManager.IRegistrationListener
        {
            public MIDISession parent = null;
            public MIDIRegistrationListener(MIDISession parent)
            {
                this.parent = parent;
            }

            public void OnRegistrationFailed(NsdServiceInfo serviceInfo, [GeneratedEnum] NsdFailure errorCode)
            {
                Console.WriteLine("onRegistrationFailed \n" + serviceInfo.ToString() + "\nerror code: " + errorCode);
                parent.published_bonjour = false;
            }

            public void OnServiceRegistered(NsdServiceInfo serviceInfo)
            {
                // Save the service name.  Android may have changed it in order to
                //                // resolve a conflict, so update the name you initially requested
                //                // with the name Android actually used.
                Log.Debug(TAG, "Service Registered " + serviceInfo.ToString());
                if (serviceInfo.ServiceName != null && parent.bonjourName != serviceInfo.ServiceName)
                {
                    parent.bonjourName = serviceInfo.ServiceName;
                    serviceInfo.ServiceName = parent.bonjourName;
                    ////                    mNsdManager.resolveService(serviceInfo, mResolveListener);
                }
                ////parent.mNsdManager.ResolveService(serviceInfo, parent.mResolveListener);
                parent.published_bonjour = true;
                MessagingCenter.Send<MIDISessionNameRegisteredEvent>(new MIDISessionNameRegisteredEvent(), "MIDISessionNameRegisteredEvent");
            }

            public void OnServiceUnregistered(NsdServiceInfo serviceInfo)
            {
                //                // Service has been unregistered.  This only happens when you call
                //                // NsdManager.unregisterService() and pass in this listener.
                Console.WriteLine("onServiceUnregistered ");
                parent.published_bonjour = false;
            }

            public void OnUnregistrationFailed(NsdServiceInfo serviceInfo, [GeneratedEnum] NsdFailure errorCode)
            {
                // Unregistration failed.  Put debugging code here to determine why.
                Console.WriteLine("onUnregistrationFailed ");
                parent.published_bonjour = false;
            }
        }

        //[TargetApi(Value = 16)]  // JELLY_BEAN
        private void InitializeNSDRegistrationListener()
        {
            mRegistrationListener = new MIDIRegistrationListener(this);
        }

        public class MIDIResolveListener : Java.Lang.Object, NsdManager.IResolveListener
        {
            private readonly MIDISession parent = null;

            public MIDIResolveListener(MIDISession parent)
            {
                this.parent = parent;
            }

            public void OnResolveFailed(NsdServiceInfo serviceInfo, int errorCode)
            {
                // Called when the resolve fails.  Use the error code to debug.
                Log.Error(TAG, "Resolve failed" + errorCode);
            }

            public void OnResolveFailed(NsdServiceInfo serviceInfo, [GeneratedEnum] NsdFailure errorCode)
            {
                Log.Error(TAG, "Resolve failed" + errorCode);
            }

            public void OnServiceResolved(NsdServiceInfo serviceInfo)
            {
                Log.Error(TAG, "Resolve Succeeded. " + serviceInfo);
                // parent.bonjourName = serviceInfo.ServiceName;
                parent.port = serviceInfo.Port;
                parent.bonjourHost = serviceInfo.Host;
            }
        }

        //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private void InitializeResolveListener()
        {
            if (Build.VERSION.SdkInt >= BuildVersionCodes.JellyBean)
            {
                mResolveListener = new MIDIResolveListener(this);
            }
        }

        private void ShutdownNSDListener()
        {
            if (Build.VERSION.SdkInt >= BuildVersionCodes.JellyBean)
            {
                try
                {
                    if (mNsdManager != null)
                    {
                        mNsdManager.UnregisterService(mRegistrationListener);
                    }
                    //            mNsdManager.stopServiceDiscovery(mDiscoveryListener);
                }
                catch (IllegalArgumentException)
                {
                    // absorb stupid listener not registered exception...
                }
            }

        }

        public String Version()
        {
            return new String(BuildConfig.VersionName);
        }

        // TODO : make this actually work...
        public bool IsHostConnectionAllowed(Bundle rinfo)
        {
            return true;
        }

        public void SetupLocalDB()
        {
            midiAddressBook = new Dictionary<string, MIDIAddressBookEntry>();
        }
        // -------------------------------------------------
        //    public void setupWaspDB() {
        //        String path = appContext.getFilesDir().getPath();
        //        String databaseName = "MIDIAddressBook";
        //        String password = "passw0rd";

        //        WaspFactory.openOrCreateDatabase(path, databaseName, password, new WaspListener<WaspDb>() {
        //            @Override
        //            public void onDone(WaspDb waspDb) {
        //                db = waspDb;
        //                try {
        //                    midiAddressBook = db.openOrCreateHash("midiAddressBook");
        //                    if (midiAddressBook != null && midiAddressBook.getAllKeys() != null) {
        //                        Log.Debug(TAG, "setupWaspDB - count " + midiAddressBook.getAllKeys().size());
        //                        EventBus.getDefault().post(new AddressBookReadyEvent());
        //                    }
        //                } catch (KryoException e) {
        //                    e.printStackTrace();
        //                    Log.e(TAG,"remove and recreate midiAddressBook");
        //                    db.removeHash("midiAddressBook");
        //                    midiAddressBook = db.openOrCreateHash("midiAddressBook");
        //                    Log.Debug(TAG, "setupWaspDB - count " + midiAddressBook.getAllKeys().size());
        //                    EventBus.getDefault().post(new AddressBookReadyEvent());
        //                }
        //            }

        //        });

        ////            db = WaspFactory.openOrCreateDatabase(path, databaseName, password, new WaspListener<WaspDb>() {
        ////                        @Override
        ////                        public void onDone(WaspDb waspDb) {
        ////                            Log.Debug("WaspFactoryINIT","on done?");
        ////                        }
        ////                    });
        //    }


        public Bundle GetEntryFromAddressBook(string key)
        {
            MIDIAddressBookEntry abe = midiAddressBook.GetValueOrDefault(key);
            return abe.Rinfo();
        }

        public bool AddToAddressBook(Bundle rinfo)
        {
            string key = RinfoToKey(rinfo);
            Log.Debug(TAG, "addToAddressBook : " + key + " " + rinfo.ToString());
            //        if(!rinfo.getBoolean(RINFO_RECON, false)) {
            //            // reinforce false (in case RECON isn't in bundle) - I guess I could
            //            // iterate over keySet - honestly, I don't know why I'm bothering to do this
            //            Log.Debug(TAG,"reinforce false?");
            //            rinfo.putBoolean(RINFO_RECON,false);
            //        }
            var a = midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(key);

            if (a == null)
            {
                bool status = midiAddressBook.TryAdd(key, new MIDIAddressBookEntry(rinfo));
                if (status)
                {
                    Log.Debug(TAG, "status is good");
                    MessagingCenter.Send<MIDIAddressBookEvent>(new MIDIAddressBookEvent(), "MIDIAddressBookEvent");
                }
            }
            else
            {
                Log.Debug(TAG, "already in addressbook");
                MIDIAddressBookEntry e = midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(RinfoToKey(rinfo));
                e.SetReconnect(rinfo.GetBoolean(MIDIConstants.RINFO_RECON, e.GetReconnect()));

                bool status = midiAddressBook.TryAdd(RinfoToKey(rinfo), e);
                if (status)
                {
                    Log.Debug(TAG, "status is good - updated entry");
                    MessagingCenter.Send<MIDIAddressBookEvent>(new MIDIAddressBookEvent(), "MIDIAddressBookEvent");
                }
            }
            Log.Debug(TAG, "about to dump ab");
            DumpAddressBook();
            //        getAllAddressBook();
            return true;
        }

        private string RinfoToKey(Bundle rinfo)
        {
            return string.Format(Locale.English.ToString(), "%1$s:%2$d", rinfo.GetString(MIDIConstants.RINFO_ADDR), rinfo.GetInt(MIDIConstants.RINFO_PORT, 1234));
        }

        public bool AddToAddressBook(MIDIAddressBookEntry m)
        {
            if (midiAddressBook != null)
            {
                return midiAddressBook.TryAdd(RinfoToKey(m.Rinfo()), new MIDIAddressBookEntry(m.Rinfo()));
            }
            return false;
        }

        public bool DeleteFromAddressBook(MIDIAddressBookEntry m)
        {
            return midiAddressBook.Remove(RinfoToKey(m.Rinfo()));

        }

        public bool AddressBookIsEmpty()
        {
            return midiAddressBook == null;
        }

        public List<MIDIAddressBookEntry> GetAllAddressBook()
        {
            Log.Debug(TAG, "getAllAddressBook");
            if (midiAddressBook != null)
            {
                Log.Debug(TAG, "value count: " + midiAddressBook.Values.Count);
                List<MIDIAddressBookEntry> list = new List<MIDIAddressBookEntry>();
                foreach (MIDIAddressBookEntry value in midiAddressBook.Values)
                {
                    list.Add(value);
                }
                return list;
            }
            return null;
        }

        //    // whenever a connect is called, check addressbook to see if we need to
        //    // add RECON:true
        //    private void checkAddressBookForReconnect(Bundle rinfo) {
        //        Bundle abentry = getEntryFromAddressBook(rinfoToKey(rinfo));
        //        if(abentry != null) {
        //            Log.Debug(TAG,"checkAddressBookForReconnect : ");
        //            rinfo.putBoolean(RINFO_RECON,abentry.getBoolean(RINFO_RECON,false));
        //        }
        //    }

        private void DumpAddressBook()
        {
            if (midiAddressBook != null)
            {
                Log.Debug(TAG, "-----------------------------------------");
                foreach (string key in midiAddressBook.Keys)
                    Log.Debug(TAG, " (" + key + ") : " + midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(key).GetAddressPort());
                Log.Debug(TAG, "-----------------------------------------");
            }
            else
                Log.Debug(TAG, "-----------------MIDI Address Book null-------------");
        }

        public void CheckAddressBookForReconnect()
        {
            if (midiAddressBook != null)
            {
                Log.Debug(TAG, "-----------------------------------------");
                foreach (string key in midiAddressBook.Keys)
                {
                    MIDIAddressBookEntry e = midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(key);
                    Log.Debug(TAG, " checking for reconnect - (" + key + ") : " + e.GetAddressPort() + " " + (e.GetReconnect() ? "YES" : "NO"));
                    if (e.GetReconnect())
                    {
                        Connect(midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(key).Rinfo());
                        if (OnSameNetwork(new String(midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(key).GetAddress())))
                            Log.Debug(TAG, " same network - (" + key + ") : " + midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(key).GetAddressPort());
                        else
                            Log.Debug(TAG, " different network -  (" + key + ") : " + midiAddressBook.GetValueOrDefault<string, MIDIAddressBookEntry>(key).GetAddressPort());
                    }
                }
                Log.Debug(TAG, "-----------------------------------------");
            }
            else
            {
                Log.Debug(TAG, "-----------------MIDI Address Book null-------------");
            }
        }

        public bool OnSameNetwork(String ip)
        {
            try
            {
                byte[] a1 = InetAddress.GetByName(ip.ToString()).GetAddress();
                byte[] a2 = bonjourHost.GetAddress();
                byte[] m = netmask.GetAddress();
                for (int i = 0; i < a1.Length; i++)
                    if ((a1[i] & m[i]) != (a2[i] & m[i]))
                        return false;
                return true;
            }
            catch (UnknownHostException)
            {
                return false;
            }
        }

        //    public static boolean sameNetwork(String ip1, String ip2, String mask)
        //            throws Exception {
        //
        //        byte[] a1 = InetAddress.getByName(ip1).getAddress();
        //        byte[] a2 = InetAddress.getByName(ip2).getAddress();
        //        byte[] m = InetAddress.getByName(mask).getAddress();
        //
        //        for (int i = 0; i < a1.length; i++)
        //            if ((a1[i] & m[i]) != (a2[i] & m[i]))
        //                return false;
        //
        //        return true;
        //
        //    }

        public bool SameIP(InetAddress a1, InetAddress a2)
        {
            byte[] b1 = a1.GetAddress();
            byte[] b2 = a2.GetAddress();
            for (int i = 0; i < b1.Length; i++)
                if (b1[i] != b2[i])
                    return false;

            return true;
        }

        private BroadcastReceiver wifiReceiver;
        private bool networkListenerRegistered = false;

        public class MidiBroadCastReceiver : BroadcastReceiver
        {
            MIDISession parent;
            public MidiBroadCastReceiver(MIDISession parent)
            {
                this.parent = parent;
            }

            public override void OnReceive(Context context, Intent intent)
            {
                // Do whatever you need it to do when it receives the broadcast
                // Example show a Toast message...
                //                showSuccessfulBroadcast();
                Log.Debug(TAG, "wifiReceiver - " + intent.Action);
                //                checkAddressBookForReconnect();
                if (parent.IsOnline())
                {
                    Log.Debug(TAG, "network is online");
                    if (parent.shouldBeRunning && !(parent.isRunning))
                        parent.Start();
                }
                else
                {
                    Log.Debug(TAG, "network not online");
                    if (parent.isRunning)
                    {
                        parent.shouldBeRunning = true;
                        parent.Stop();
                    }
                    //                    shouldBeRunning = true;
                    //                    stop();
                    //
                }
            }
        }

        public void SetupNetworkListener()
        {

            //        if(this.wifiReceiver != null) {
            //            removeNetworkListener();
            //        }
            if (networkListenerRegistered)
                RemoveNetworkListener();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.AddAction(ConnectivityManager.ConnectivityAction);
            this.wifiReceiver = new MidiBroadCastReceiver(this);
            if (appContext != null)
                appContext.RegisterReceiver(this.wifiReceiver, intentFilter);
            networkListenerRegistered = true;
        }

        public void RemoveNetworkListener()
        {
            if (appContext != null && wifiReceiver != null)
            {
                try
                {
                    appContext.UnregisterReceiver(wifiReceiver);
                    networkListenerRegistered = false;

                }
                catch (IllegalArgumentException e)
                {
                    e.PrintStackTrace();
                }
            }
        }

        public bool IsOnline()
        {
            ConnectivityManager cm =
                    (ConnectivityManager)appContext.GetSystemService(Context.ConnectivityService);
            Log.Debug(TAG, "isOnline? " + ((cm.ActiveNetworkInfo != null &&
                    cm.ActiveNetworkInfo.IsConnected) ? "ON" : "OFF"));
            return cm.ActiveNetworkInfo != null && cm.ActiveNetworkInfo.IsConnected;
        }
    } 
}