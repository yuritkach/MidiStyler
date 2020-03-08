using Android.Util;
using System;
using System.Timers;
using midi.events;
using midi.internal_events;
using Xamarin.Forms;
using Java.Util.Concurrent;
using MidiAranger.Droid.Source.common;
using Android.OS;
using System.Threading;
using Timer = System.Timers.Timer;
using static midi.MIDIControl;

namespace midi { 

/* ---------------------------------------------------------
    internal events:
        connection invite received
        connection invite sent
        connection invite reject received
        connection invite accept received
        connection end received
        primary sync started
        primary sync complete
        connection failed (reason?)
            unable to connect
            connection lost, no reconnect
            connection lost, reconnect timeout
            connection lost
   --------------------------------------------------------- */
public class MIDIStream {

    public int initiator_token = 0;
    public int ssrc = 0;

    public Bundle rinfo1 = null;
    public Bundle rinfo2 = null;

    private string name = "";
    int lastSentSequenceNr;

    int firstReceivedSequenceNumber = -1;
    int lastReceivedSequenceNumber = -1;
    long latency = 0L;

    private bool isConnected = false;

    private long receiverFeedbackTimeout = 0L;
    private long lastMessageTime = 0L;
    private long timeDifference = 0L;
    private bool isInitiator = false;
    private static bool syncStarted = false;
    private static bool primarySyncComplete = false;
    private static int syncCount = 0;

    private static long lastPacketReceivedTime = 0L;

    private static IScheduledExecutorService connectService, syncService, checkConnectionService;
    private static Timer connectFuture, syncFuture, checkConnectionFuture;
    public static int connectTaskCount = 0, syncTaskCount=0;
    public static int syncFailCount = 0;

    public static readonly int reconnectFailCount = 0;

    public static bool receivedSyncResponse = true;

    private static readonly int STREAM_CONNECTED_TIMEOUT_DEFAULT = 60000;
    private static readonly int SYNC_PRIMARY_FREQUENCY_DEFAULT = 2000;
    private static readonly int SYNC_FREQUENCY_DEFAULT = 30000;
    private static readonly int CONNECT_COUNT_DEFAULT = 12;
    private static readonly int PRIMARY_SYNC_COUNT_DEFAULT = 10;
    private static readonly int SYNC_FAIL_COUNT_DEFAULT = 10;
    private static readonly string TAG = "MIDIStream";
    private static bool DEBUG = false;

    private static readonly int connectionTimeoutMax = STREAM_CONNECTED_TIMEOUT_DEFAULT;
    private static readonly int connectCountMax = CONNECT_COUNT_DEFAULT;
    private static readonly int primarySyncCountMax = PRIMARY_SYNC_COUNT_DEFAULT;
    private static readonly int syncFailCountMax = SYNC_FAIL_COUNT_DEFAULT;
    private static readonly int syncServicePrimaryFrequency = SYNC_PRIMARY_FREQUENCY_DEFAULT;
    private static readonly int syncServiceFrequency = SYNC_FREQUENCY_DEFAULT;

    public MIDIStream() {
        isConnected = false;
        isInitiator = false;
        int lastSentSequenceNr = (new Random(0xffff)).Next();

        connectService = Executors.NewSingleThreadScheduledExecutor();
        syncService = Executors.NewSingleThreadScheduledExecutor();
        checkConnectionService = Executors.NewSingleThreadScheduledExecutor();
        connectFuture = null;
        primarySyncComplete = false;
    }

    ~MIDIStream() {
        CancelConnectFuture();
        CancelSyncFuture();
        CancelCheckConnectionFuture();
        if(!connectService.IsShutdown) {
            connectService.ShutdownNow();
        }
        if(!syncService.IsShutdown) {
            syncService.ShutdownNow();
        }
        if(!checkConnectionService.IsShutdown) {
            checkConnectionService.ShutdownNow();
        }
    
    }

    public bool ConnectionMatch(Bundle r) {
        bool match = false;
        if(r != null) {
            Log.Debug(TAG, "ConnectionMatch " + r.ToString() + " ? " + rinfo1.ToString() + "/" + rinfo2.ToString());

            if (rinfo1 == null || rinfo2 == null) {
                return false;
            }

            if (r.GetString(midi.MIDIConstants.RINFO_ADDR).Equals(rinfo1.GetString(midi.MIDIConstants.RINFO_ADDR))) {
                Log.Debug(TAG, "addr = addr " + r.GetString(midi.MIDIConstants.RINFO_ADDR));
                if ((r.GetInt(midi.MIDIConstants.RINFO_PORT) == rinfo1.GetInt(midi.MIDIConstants.RINFO_PORT)) ||
                        ((r.GetInt(midi.MIDIConstants.RINFO_PORT) == rinfo2.GetInt(midi.MIDIConstants.RINFO_PORT)))) {
                    Log.Debug(TAG, "port = port " + r.GetInt(midi.MIDIConstants.RINFO_PORT));
                    match = true;
                } else {
                    Log.Debug(TAG, "port != port ");

                }
            } else {
                Log.Debug(TAG, "address != address ");

            }
        }
        return match;
    }

    private void SendSyncComplete() {
        receivedSyncResponse = true;
        syncFailCount = 0;
        // TODO: change this to synccomplete
        MessagingCenter.Send<SyncronizeStoppedEvent>(new SyncronizeStoppedEvent(rinfo1), "SyncronizeStoppedEvent");
        }


    public void Connect(Bundle rinfo) {
        Log.Debug("MIDIStream","connect "+rinfo.GetString(midi.MIDIConstants.RINFO_ADDR)
            +":"+rinfo.GetInt(midi.MIDIConstants.RINFO_PORT));
        if(isConnected) {
            // already connected, should not reconnect with same stream
            Log.Error("MIDI2Stream","this stream is already connected");
            return;
        }
        if (this.initiator_token == 0) {
            this.initiator_token = GenerateRandomInteger(4);
            Log.Error("MIDIStream","generateRandomInteger for initiator initiator_token "+this.initiator_token);
        }
        this.isInitiator = true;


        Log.Debug(TAG,"create connection "+rinfo.GetInt(MIDIConstants.RINFO_FAIL,0));
        int delay =(rinfo.GetInt(MIDIConstants.RINFO_FAIL,0) * 5);
        connectFuture = new Timer(1000);
        connectFuture.Elapsed +=(object sender, System.Timers.ElapsedEventArgs e) =>
                {
                    connectFuture.Stop();
                    try
                    {
                        if (rinfo == null)
                        {
                            Log.Debug(TAG, "connection task shutdown");
                            SendEnd(rinfo);
                            connectFuture.Stop();
                            connectFuture = null;
                        }
                        else
                        {
                            connectTaskCount++;
                            if (connectTaskCount > connectCountMax)
                            {
                                Log.Debug(TAG, "connection task count hit max");
                                rinfo.PutBoolean(MIDIConstants.RINFO_FAIL, true);
                                MessagingCenter.Send<ConnectionFailedEvent>(
                                    (new ConnectionFailedEvent(MIDIFailCode.UNABLE_TO_CONNECT, rinfo, initiator_token)),
                                    "ConnectionFailedEvent");

                                CleanupFailedConnection();

                                Shutdown();
                                return;
                            }
                            Log.Debug("MIDIConnectTask", "connectTaskCount:" + connectTaskCount + " ssrc:" + ssrc);
                            SendInvitation(rinfo);
                        }

                    }
                    catch (Exception er)
                    {
                        Log.Debug("MIDIStream", "exception in send midi task " + er.StackTrace);
                    }
                };
            connectFuture.Enabled = true;
            connectFuture.Start();

            //        connectService.schedule(new Runnable() {
            //            @Override
            //            public void run() {
            //                Log.d(TAG,"timeout - cancel connection task ");
            //                if(connectFuture != null) {
            //                    connectFuture.cancel(false);
            //                }
            //            }
            //        }, 1, MINUTES);
        }

    protected void CleanupFailedConnection() {
        Log.Error(TAG,"CleanupFailedConnection");
        isConnected = false;
        isInitiator = false;
        primarySyncComplete = false;
        rinfo2 = rinfo1 = null;
        CancelCheckConnectionFuture();
        CancelConnectFuture();
        CancelSyncFuture();
    }

    private void CancelConnectFuture() {
        if(connectFuture != null) {
                connectFuture.Stop();
                connectFuture = null;
        }
    }

    private void CancelSyncFuture() {
        if(syncFuture != null) {
            syncFuture.Stop();
            syncFuture = null;
        }
    }

    private void CancelCheckConnectionFuture() {
        if(checkConnectionFuture != null) {
            checkConnectionFuture.Stop();
            checkConnectionFuture = null;
        }
    }

    public void Disconnect() {
        CancelConnectFuture();
        CancelSyncFuture();
        CancelCheckConnectionFuture();
    }

    public void Shutdown() {
        Disconnect();
        if(!connectService.IsShutdown) {
            connectService.Shutdown();
        }
        if(!syncService.IsShutdown) {
            syncService.Shutdown();
        }
        if(!checkConnectionService.IsShutdown) {
            checkConnectionService.Shutdown();
        }

    }

    Bundle GetRinfo1() {
        return rinfo1;
    }

    public void SendInvitation(Bundle rinfo) {
        Log.Debug("MIDIStream","sendInvitation "+rinfo.GetString(MIDIConstants.RINFO_ADDR)+":"+rinfo.GetInt(MIDIConstants.RINFO_PORT));
        MIDIControl invite = new MIDIControl();
        invite.CreateInvitation(initiator_token, MIDISession.GetInstance().ssrc, MIDISession.GetInstance().bonjourName);
//        invite.dumppacket();
        MIDISession.GetInstance().SendUDPMessage(invite,rinfo);
        MessagingCenter.Send<MIDIConnectionSentRequestEvent>(new MIDIConnectionSentRequestEvent(rinfo), "MIDIConnectionSentRequestEvent");
        }

    private int GenerateRandomInteger(int octets) {
            Random rnd = new Random();
            return (int) Math.Round(rnd.NextDouble() * Math.Pow(2, 8 * octets));
    }

    public void HandleControlMessage(MIDIControl control, Bundle rinfo) {

        lastPacketReceivedTime = Common.CurrentTimeMillis();

        switch(control.command) {
            case AppleMIDICommand.INVITATION:
//                Log.d("MIDIStream","handle INVITATION");
                HandleInvitation(control,rinfo);
                ResetCheckConnectionService();
                break;
            case AppleMIDICommand.INVITATION_ACCEPTED:
//                Log.d("MIDIStream","handle INVITATION_ACCEPTED");
                HandleInvitationAccepted(control, rinfo);
                break;
            case AppleMIDICommand.INVITATION_REJECTED:
//                Log.d("MIDIStream","handle INVITATION_REJECTED");
                HandleInvitationRejected(control, rinfo);
                break;
            case AppleMIDICommand.END:
//                Log.d("MIDIStream","handle END");
                HandleEnd();
                break;
            case AppleMIDICommand.SYNCHRONIZATION:
//                Log.d("MIDIStream","handle SYNCHRONIZATION");
                if(isInitiator) {
                    receivedSyncResponse = true;
                }
                SendSynchronization(control);
                break;
            case AppleMIDICommand.RECEIVER_FEEDBACK:
//                Log.d("MIDIStream","handle RECEIVER_FEEDBACK");
                break;
            case AppleMIDICommand.BITRATE_RECEIVE_LIMIT:
//                Log.d("MIDIStream","handle BITRATE_RECEIVE_LIMIT");
                break;
            default:
//                Log.d("MIDIStream", "unhandled command");
                break;
        }
    }



    private void HandleInvitation(MIDIControl control, Bundle rinfo) {
        if(rinfo1 == null) {
            rinfo1 = (Bundle) rinfo.Clone();
            this.initiator_token = control.initiator_token;
            this.name = control.name;
            this.ssrc = control.ssrc;
            rinfo1.PutString(midi.MIDIConstants.RINFO_NAME, control.name);
            MessagingCenter.Send<MIDIConnectionRequestReceivedEvent>(new MIDIConnectionRequestReceivedEvent(rinfo1), "MIDIConnectionRequestReceivedEvent");
            } else if(rinfo.GetInt(midi.MIDIConstants.RINFO_PORT) == rinfo1.GetInt(midi.MIDIConstants.RINFO_PORT)) {
            return;
        } else if(rinfo2 == null) {
            rinfo2 = (Bundle) rinfo.Clone();
            this.isConnected = true;

        } else {
            return;
        }

// this needs work...
        if(MIDISession.GetInstance().IsHostConnectionAllowed(rinfo)) {
            this.SendInvitationAccepted(rinfo);
        } else {
            this.SendInvitationRejected(rinfo);
        }
    }

    private void HandleInvitationAccepted(MIDIControl control, Bundle rinfo) {
        Log.Debug(TAG,"handleInvitationAccepted "+rinfo.ToString());
        if (!this.isConnected && this.rinfo1 == null) {
            Log.Debug(TAG,"cancel connectFuture");
            connectFuture.Stop();
            connectFuture = null;

            Log.Debug(TAG,"set rinfo1 "+rinfo.ToString());
            rinfo1 = (Bundle) rinfo.Clone();
            rinfo1.PutString(midi.MIDIConstants.RINFO_NAME,control.name);
            rinfo.PutInt(midi.MIDIConstants.RINFO_PORT,rinfo.GetInt(midi.MIDIConstants.RINFO_PORT)+1);
            connectTaskCount = 0;
            Connect(rinfo);
        }
        else 
        if(!this.isConnected && rinfo2 == null) {
            Log.Debug(TAG,"cancel connectFuture");
            connectFuture.Stop();
            connectFuture = null;
            connectTaskCount = 0;

            this.isConnected = true;
            this.name = control.name;
            this.ssrc = control.ssrc;
            Log.Debug(TAG,"set rinfo2 "+rinfo.ToString());
            rinfo2 = (Bundle) rinfo.Clone();
            MessagingCenter.Send<StreamConnectedEvent>(new StreamConnectedEvent(this.initiator_token), "StreamConnectedEvent");
            ResetSyncService(syncServicePrimaryFrequency);
        }
        else
        {
            Log.Debug("MIDI2Stream","unhandled invitation accept");
        }
    }

        public void ResetCheckConnectionService() {
            if(checkConnectionFuture != null) {
                checkConnectionFuture.Stop();
                checkConnectionFuture = null;
            }

        
            checkConnectionFuture = new Timer(30000);
            checkConnectionFuture.Elapsed += (object sender, System.Timers.ElapsedEventArgs e) =>
            {
                checkConnectionFuture.Stop();
                long timedifference = Common.CurrentTimeMillis() - lastPacketReceivedTime;
                //            Log.e(TAG,"last packet time difference is "+timedifference);
                if (timedifference > connectionTimeoutMax)
                {
                    // stream connection has probably failed
                    Log.Error(TAG, "last packet time difference is " + timedifference + "   probably lost connection with " + rinfo1 + " ssrc" + ssrc);
                    //                rinfo1.putBoolean(RINFO_FAIL,true);
                    MessagingCenter.Send<ConnectionFailedEvent>(new ConnectionFailedEvent(MIDIFailCode.CONNECTION_LOST, rinfo1), "ConnectionFailedEvent");
                    CleanupFailedConnection();
                    ConnectionFutureShutdown();
                }
                else
                {
                    Log.Error(TAG, "last packet time difference is " + timedifference + "   connection still up " + rinfo1.ToString() + " ssrc" + ssrc);
                }
            };
            checkConnectionFuture.Start();
        }

        void ConnectionFutureShutdown()
        {
            SendEnd();
            checkConnectionFuture.Stop();
            checkConnectionFuture = null;
        }


        public void ResetSyncService(int time) {
        if(!primarySyncComplete) {
            MessagingCenter.Send<SyncronizeStartedEvent>(new SyncronizeStartedEvent(this.rinfo1), "SyncronizeStartedEvent");
            }
        if(syncFuture != null) {
            syncFuture.Stop();
            syncFuture = null;
        }

        syncFuture = new Timer(time);
        syncFuture.Elapsed += (object sender, System.Timers.ElapsedEventArgs e) =>
        {
            syncFuture.Stop();
            try
            {
                syncTaskCount++;
                //                Log.d("Sync","syncTaskCount:"+syncTaskCount+" timedifference:"+timeDifference);
                if (!receivedSyncResponse)
                {
                    syncFailCount++;
                }
                if (syncFailCount >= syncFailCountMax)
                {
                    Log.Debug(TAG, "Sync fail count > max ");
                    //EventBus.getDefault().post(new ConnectionFailedEvent(MIDIFailCode.SYNC_FAILURE,rinfo));
                    MessagingCenter.Send<ConnectionFailedEvent>(new ConnectionFailedEvent(MIDIFailCode.SYNC_FAILURE, rinfo1), "ConnectionFailedEvent");
                    CleanupFailedConnection();
                    ShutdownSyncFuture();
                }
                if (isInitiator)
                {
                    receivedSyncResponse = false;
                }
                SendSynchronization(null);
                if (!primarySyncComplete && syncTaskCount > primarySyncCountMax)
                {
                    primarySyncComplete = true;
                    //                    EventBus.getDefault().post(new MIDISyncronizationCompleteEvent());
                    //                    EventBus.getDefault().post(new MIDIConnectionEstablishedEvent());
                    //                    Log.d("Sync", "primary sync complete");

                    SendSyncComplete();
                    ResetSyncService(syncServiceFrequency);
                }
            }
            catch (Exception er)
            {
                ShutdownSyncFuture();
            }
        };
        syncFuture.Enabled = true;
        syncFuture.Start();
            
        }
        void ShutdownSyncFuture()
        {

            SendEnd(rinfo1);
            syncFuture.Stop();
            syncFuture = null;
        }

 

    public void HandleInvitationRejected(MIDIControl control, Bundle rinfo) {
        connectFuture.Stop();
        connectFuture = null;
        MessagingCenter.Send<ConnectionFailedEvent>(new ConnectionFailedEvent(MIDIFailCode.REJECTED_INVITATION, rinfo, control.initiator_token), "ConnectionFailedEvent");
        MessagingCenter.Send<MIDIConnectionRequestRejectedEvent>(new MIDIConnectionRequestRejectedEvent(rinfo), "MIDIConnectionRequestRejectedEvent");
    }

    private void HandleEnd() {
        this.isConnected = false;
        // shutdown sync
        if(connectFuture != null) {
            connectFuture.Stop();
            connectFuture = null;
        }
        if(syncFuture != null) {
            syncFuture.Stop();
            syncFuture = null;
        }
        if(checkConnectionFuture != null) {
            checkConnectionFuture.Stop();
            checkConnectionFuture=null;
        }
            //        if(MIDISession.getInstance().getAutoReconnect()) {
            //            Bundle rinfo = (Bundle) rinfo1.clone();
            //            rinfo1 = rinfo2 = null;
            //            sendInvitation(rinfo);
            //            syncStarted = false;
            //            syncFailCount = 0;
            //            syncCount = 0;
            //            this.isInitiator = false;
            //            connectFuture = null;
            //            checkConnectionFuture = null;
            //            syncFuture = null;
            //            primarySyncComplete = false;
            //            return;
            //        }
            //        EventBus.getDefault().post(new MIDIConnectionEndEvent(this.rinfo1));
        MessagingCenter.Send<StreamDisconnectEvent>(new StreamDisconnectEvent(ssrc, (Bundle)this.rinfo1.Clone()), "StreamDisconnectEvent");
        }

    private void SendInvitationAccepted(Bundle rinfo) {
        MIDIControl message = new MIDIControl();

        message.CreateInvitationAccepted(initiator_token, MIDISession.GetInstance().ssrc, MIDISession.GetInstance().bonjourName);
        MIDISession.GetInstance().SendUDPMessage(message, rinfo);

        if(!syncStarted && rinfo2 != null) {
            syncStarted = true;
            MessagingCenter.Send<SyncronizeStartedEvent>(new SyncronizeStartedEvent(this.rinfo1), "SyncronizeStartedEvent");
            }

    }

    private void SendInvitationRejected(Bundle rinfo) {
        MIDIControl message = new MIDIControl();
        message.CreateInvitationRejected(this.initiator_token, MIDISession.GetInstance().ssrc, MIDISession.GetInstance().bonjourName);
        MIDISession.GetInstance().SendUDPMessage(message, rinfo);

    }


    public void SendMessage(MIDIMessage m) {
        this.lastSentSequenceNr = (this.lastSentSequenceNr + 1) % 0x10000;
        m.SequenceNumber = this.lastSentSequenceNr;
        MIDISession.GetInstance().SendUDPMessage(m, rinfo2);
    }

    public void SendEnd() {
//        MIDIControl message = new MIDIControl();
//        message.createEnd(this.initiator_token, MIDISession.getInstance().ssrc, MIDISession.getInstance().bonjourName);
//        MIDISession.getInstance().sendUDPMessage(message, rinfo1);
//        EventBus.getDefault().post(new StreamDisconnectEvent(ssrc));
        if(rinfo1 != null) {
            SendEnd(rinfo1);
        }
    }

    public void SendEnd(Bundle rinfo) {
        Log.Debug(TAG,"send end "+rinfo.ToString());
        MIDIControl message = new MIDIControl();
        message.CreateEnd(this.initiator_token, MIDISession.GetInstance().ssrc, MIDISession.GetInstance().bonjourName);
        MIDISession.GetInstance().SendUDPMessage(message, rinfo);

        if(isConnected) {
            MessagingCenter.Send<StreamDisconnectEvent>(new StreamDisconnectEvent(ssrc, (Bundle)this.rinfo1.Clone()), "StreamDisconnectEvent");
            }
        else {
            MessagingCenter.Send<StreamDisconnectEvent>(new StreamDisconnectEvent(ssrc, initiator_token, (Bundle)this.rinfo1.Clone()), "StreamDisconnectEvent");
            }
        isConnected = false;

    }

    public void SendSynchronization(MIDIControl inboundSyncMessage) {
        long now = MIDISession.GetInstance().GetNow();
        MIDIControl outboundSyncMessage;
        int count = (inboundSyncMessage != null) ? inboundSyncMessage.count : -1;
        if(count == 3) { count = -1; }
        outboundSyncMessage = new MIDIControl();
        outboundSyncMessage.CreateSyncronization(
                MIDISession.GetInstance().ssrc,
                count+1,
                (count != -1) ? inboundSyncMessage.timestamp1 : 0,
                (count != -1) ? inboundSyncMessage.timestamp2 : 0,
                (count != -1) ? inboundSyncMessage.timestamp3 : 0);

        switch(count) {
            case -1: // send count:0
                outboundSyncMessage.timestamp1 = now;
                outboundSyncMessage.timestamp2 = (timeDifference != 0L) ? now - timeDifference : 0L;
                outboundSyncMessage.timestamp3 = (timeDifference != 0L) ? now + timeDifference : 0L;
                break;
            case 0: // received count:0, respond count:1
                outboundSyncMessage.timestamp2 = now;
                outboundSyncMessage.timestamp3 = now - timeDifference;
                break;
            case 1: // received count:1, respond count:2
//                outboundSyncMessage.sync_timestamp3 = now;
//                latency = inboundSyncMessage.sync_timestamp3 - inboundSyncMessage.sync_timestamp1;
//                timeDifference = Math.round(inboundSyncMessage.sync_timestamp3-inboundSyncMessage.sync_timestamp2) - latency;

                timeDifference = (inboundSyncMessage.timestamp3 - inboundSyncMessage.timestamp1) / 2;
                timeDifference = inboundSyncMessage.timestamp3 + timeDifference - now;
                outboundSyncMessage.timestamp3 = now;
                break;
            case 2:  // received count:2, sending nothing
                     /* compute media delay */
//                diff = ( command->data.sync.timestamp3 - command->data.sync.timestamp1 ) / 2;
                      /* approximate time difference between peer and self */
//                diff = command->data.sync.timestamp3 + diff - timestamp;
                timeDifference = (inboundSyncMessage.timestamp3 - inboundSyncMessage.timestamp1) / 2;
                timeDifference = inboundSyncMessage.timestamp3 + timeDifference - now;
                break;
                default:break;
        }
        if(outboundSyncMessage.count < 3) {
            MIDISession.GetInstance().SendUDPMessage(outboundSyncMessage, rinfo2);
            if(!primarySyncComplete && ++syncCount > 3) {
                primarySyncComplete = true;
                MessagingCenter.Send<SyncronizeStoppedEvent>(new SyncronizeStoppedEvent(this.rinfo1), "SyncronizeStoppedEvent");
                MessagingCenter.Send<ConnectionEstablishedEvent>(new ConnectionEstablishedEvent(rinfo1), "ConnectionEstablishedEvent");
                }
        }

    }

}
}