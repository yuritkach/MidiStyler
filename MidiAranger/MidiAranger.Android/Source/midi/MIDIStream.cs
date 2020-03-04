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

    private Android.OS.Bundle rinfo1 = null;
    private Android.OS.Bundle rinfo2 = null;

    private string name = "";
    int lastSentSequenceNr;

    int firstReceivedSequenceNumber = -1;
    int lastReceivedSequenceNumber = -1;
    long latency = 0L;

    private bool isConnected = false;

    long receiverFeedbackTimeout = 0L;
    long lastMessageTime = 0L;
    private long timeDifference = 0L;
    private bool isInitiator = false;
    private static bool syncStarted = false;
    private static bool primarySyncComplete = false;
    private static int syncCount = 0;

    private static long lastPacketReceivedTime = 0L;

    private static IScheduledExecutorService connectService, syncService, checkConnectionService;
    private static Timer connectFuture, syncFuture, checkConnectionFuture;
    static int connectTaskCount = 0, syncTaskCount=0;
    static int syncFailCount = 0;

    static int reconnectFailCount = 0;

    static bool receivedSyncResponse = true;

    private static int STREAM_CONNECTED_TIMEOUT_DEFAULT = 60000;
    private static int SYNC_PRIMARY_FREQUENCY_DEFAULT = 2000;
    private static int SYNC_FREQUENCY_DEFAULT = 30000;
    private static int CONNECT_COUNT_DEFAULT = 12;
    private static int PRIMARY_SYNC_COUNT_DEFAULT = 10;
    private static int SYNC_FAIL_COUNT_DEFAULT = 10;
    private static string TAG = "MIDIStream";
    private static bool DEBUG = false;

    private static int connectionTimeoutMax = STREAM_CONNECTED_TIMEOUT_DEFAULT;
    private static int connectCountMax = CONNECT_COUNT_DEFAULT;
    private static int primarySyncCountMax = PRIMARY_SYNC_COUNT_DEFAULT;
    private static int syncFailCountMax = SYNC_FAIL_COUNT_DEFAULT;
    private static int syncServicePrimaryFrequency = SYNC_PRIMARY_FREQUENCY_DEFAULT;
    private static int syncServiceFrequency = SYNC_FREQUENCY_DEFAULT;

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

    private class SyncTask {

        Bundle rinfo = null;
        MIDIStream parent = null;


        public void SetBundle(MIDIStream parent,Bundle b) {
            this.parent = parent;
            rinfo = b;
            Thread newThread = new Thread(new ThreadStart(Run));
            newThread.Start();
            }

        public void Run() {
            try {
                syncTaskCount++;
//                Log.d("Sync","syncTaskCount:"+syncTaskCount+" timedifference:"+timeDifference);
                if(!receivedSyncResponse) {
                    syncFailCount++;
                }
                if(syncFailCount >= syncFailCountMax) {
                    Log.Debug(TAG,"Sync fail count > max ");
                        //EventBus.getDefault().post(new ConnectionFailedEvent(MIDIFailCode.SYNC_FAILURE,rinfo));
                    MessagingCenter.Send<ConnectionFailedEvent>(new ConnectionFailedEvent(MIDIFailCode.SYNC_FAILURE, rinfo), "ConnectionFailedEvent");
                    parent.CleanupFailedConnection();
                    Shutdown();
                }
                if(parent.isInitiator) {
                    receivedSyncResponse = false;
                }
                parent.SendSynchronization(null);
                if(!primarySyncComplete && syncTaskCount > primarySyncCountMax) {
                    primarySyncComplete = true;
//                    EventBus.getDefault().post(new MIDISyncronizationCompleteEvent());
//                    EventBus.getDefault().post(new MIDIConnectionEstablishedEvent());
//                    Log.d("Sync", "primary sync complete");

                    parent.SendSyncComplete();
                    parent.ResetSyncService(syncServiceFrequency);
                }
            } catch (Exception e) {
                Shutdown();
            }
        }
        void Shutdown() {

            parent.SendEnd(rinfo);
            syncFuture.Stop();
            syncFuture = null;
        }
    }

    private class CheckConnectionTask {

        MIDIStream parent=null;
        public CheckConnectionTask(MIDIStream parent)
        {
            this.parent = parent;
            Thread newThread = new Thread(new ThreadStart(Run));
            newThread.Start();
        }


        public void Run() {

            long timedifference = Common.CurrentTimeMillis() - lastPacketReceivedTime;
//            Log.e(TAG,"last packet time difference is "+timedifference);
            if(timedifference > connectionTimeoutMax) {
                // stream connection has probably failed
                Log.Error(TAG,"last packet time difference is "+timedifference+"   probably lost connection with "+parent.rinfo1+" ssrc"+parent.ssrc);
                    //                rinfo1.putBoolean(RINFO_FAIL,true);
                MessagingCenter.Send<ConnectionFailedEvent>(new ConnectionFailedEvent(MIDIFailCode.CONNECTION_LOST, parent.rinfo1), "ConnectionFailedEvent");
                parent.CleanupFailedConnection();
                Shutdown();
            } else {
                Log.Error(TAG,"last packet time difference is "+timedifference+"   connection still up "+parent.rinfo1.ToString()+" ssrc"+parent.ssrc);
            }
        }

        void Shutdown() {
            parent.SendEnd();
            checkConnectionFuture.Stop();
            checkConnectionFuture = null;
        }
    }



    private void SendSyncComplete() {
        receivedSyncResponse = true;
        syncFailCount = 0;
        // TODO: change this to synccomplete
        MessagingCenter.Send<SyncronizeStoppedEvent>(new SyncronizeStoppedEvent(rinfo1), "SyncronizeStoppedEvent");
        }


    void Connect(Bundle rinfo) {
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
        if(syncFuture != null && !syncFuture.IsCancelled) {
            syncFuture.Cancel(true);
        }
    }

    private void CancelCheckConnectionFuture() {
        if(checkConnectionFuture != null && !checkConnectionFuture.IsCancelled) {
            checkConnectionFuture.Cancel(true);
        }
    }

    void Disconnect() {
        CancelConnectFuture();
        CancelSyncFuture();
        CancelCheckConnectionFuture();
    }

    void Shutdown() {
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
        EventBus.getDefault().post(new MIDIConnectionSentRequestEvent(rinfo));
    }

    private int GenerateRandomInteger(int octets) {
            Random rnd = new Random();
            return (int) Math.Round(rnd.NextDouble() * Math.Pow(2, 8 * octets));
    }

    void HandleControlMessage(MIDIControl control, Bundle rinfo) {

        lastPacketReceivedTime = Common.CurrentTimeMillis();

        switch(control.command) {
            case INVITATION:
//                Log.d("MIDIStream","handle INVITATION");
                handleInvitation(control,rinfo);
                resetCheckConnectionService();
                break;
            case INVITATION_ACCEPTED:
//                Log.d("MIDIStream","handle INVITATION_ACCEPTED");
                handleInvitationAccepted(control, rinfo);
                break;
            case INVITATION_REJECTED:
//                Log.d("MIDIStream","handle INVITATION_REJECTED");
                handleInvitationRejected(control, rinfo);
                break;
            case END:
//                Log.d("MIDIStream","handle END");
                handleEnd();
                break;
            case SYNCHRONIZATION:
//                Log.d("MIDIStream","handle SYNCHRONIZATION");
                if(isInitiator) {
                    receivedSyncResponse = true;
                }
                SendSynchronization(control);
                break;
            case RECEIVER_FEEDBACK:
//                Log.d("MIDIStream","handle RECEIVER_FEEDBACK");
                break;
            case BITRATE_RECEIVE_LIMIT:
//                Log.d("MIDIStream","handle BITRATE_RECEIVE_LIMIT");
                break;
            default:
//                Log.d("MIDIStream", "unhandled command");
                break;
        }
    }



    private void handleInvitation(MIDIControl control, Bundle rinfo) {
        if(rinfo1 == null) {
            rinfo1 = (Bundle) rinfo.clone();
            this.initiator_token = control.initiator_token;
            this.name = control.name;
            this.ssrc = control.ssrc;
            rinfo1.putString(com.disappointedpig.midi.MIDIConstants.RINFO_NAME, control.name);
            EventBus.getDefault().post(new MIDIConnectionRequestReceivedEvent(rinfo1));
        } else if(rinfo.getInt(com.disappointedpig.midi.MIDIConstants.RINFO_PORT) == rinfo1.getInt(com.disappointedpig.midi.MIDIConstants.RINFO_PORT)) {
            return;
        } else if(rinfo2 == null) {
            rinfo2 = (Bundle) rinfo.clone();
            this.isConnected = true;

        } else {
            return;
        }

// this needs work...
        if(MIDISession.getInstance().isHostConnectionAllowed(rinfo)) {
            this.sendInvitationAccepted(rinfo);
        } else {
            this.sendInvitationRejected(rinfo);
        }
    }

    private void HandleInvitationAccepted(MIDIControl control, Bundle rinfo) {
        Log.Debug(TAG,"handleInvitationAccepted "+rinfo.toString());
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
        } else if(!this.isConnected && rinfo2 == null) {
            Log.Debug(TAG,"cancel connectFuture");
            connectFuture.Stop();
            connectFuture = null;
            connectTaskCount = 0;

            this.isConnected = true;
            this.name = control.name;
            this.ssrc = control.ssrc;
            Log.Debug(TAG,"set rinfo2 "+rinfo.ToString());
            rinfo2 = (Bundle) rinfo.Clone();
            EventBus.getDefault().post(new StreamConnectedEvent(this.initiator_token));

            ResetSyncService(syncServicePrimaryFrequency);
        } else {
            Log.Debug("MIDI2Stream","unhandled invitation accept");
        }
    }

    public void ResetCheckConnectionService() {
        if(checkConnectionFuture != null) {
            checkConnectionFuture.cancel(true);
        }

        CheckConnectionTask t = new CheckConnectionTask();
        checkConnectionFuture = checkConnectionService.scheduleAtFixedRate(t, 0, 30000, MILLISECONDS);

    }

    public void ResetSyncService(int time) {
        if(!primarySyncComplete) {
            EventBus.getDefault().post(new SyncronizeStartedEvent(this.rinfo1));
        }
        if(syncFuture != null) {
            syncFuture.cancel(true);
        }

        SyncTask t = new SyncTask();
        syncFuture = syncService.scheduleAtFixedRate(t, 0, time, MILLISECONDS);

    }

    public void HandleInvitationRejected(MIDIControl control, Bundle rinfo) {
        connectFuture.Cancel(true);


        EventBus.getDefault().post(new ConnectionFailedEvent(MIDIFailCode.REJECTED_INVITATION,rinfo,control.initiator_token));
        EventBus.getDefault().post(new MIDIConnectionRequestRejectedEvent(rinfo));
    }

    private void handleEnd() {
        this.isConnected = false;
        // shutdown sync
        if(connectFuture != null && !connectFuture.isCancelled()) {
            connectFuture.cancel(true);
        }
        if(syncFuture != null && !syncFuture.isCancelled()) {
            syncFuture.cancel(true);
        }
        if(checkConnectionFuture != null && !checkConnectionFuture.isCancelled()) {
            checkConnectionFuture.cancel(true);
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
        EventBus.getDefault().post(new StreamDisconnectEvent(ssrc,(Bundle)this.rinfo1.clone()));
    }

    private void sendInvitationAccepted(Bundle rinfo) {
        MIDIControl message = new MIDIControl();

        message.createInvitationAccepted(initiator_token, MIDISession.GetInstance().ssrc, MIDISession.GetInstance().bonjourName);
        MIDISession.getInstance().sendUDPMessage(message, rinfo);

        if(!syncStarted && rinfo2 != null) {
            syncStarted = true;
            EventBus.getDefault().post(new SyncronizeStartedEvent(this.rinfo1));
        }

    }

    private void sendInvitationRejected(Bundle rinfo) {
        MIDIControl message = new MIDIControl();
        message.createInvitationRejected(this.initiator_token, MIDISession.getInstance().ssrc, MIDISession.getInstance().bonjourName);
        MIDISession.getInstance().sendUDPMessage(message, rinfo);

    }


    void SendMessage(MIDIMessage m) {
        this.lastSentSequenceNr = (this.lastSentSequenceNr + 1) % 0x10000;
        m.SequenceNumber = this.lastSentSequenceNr;
        MIDISession.GetInstance().sendUDPMessage(m, rinfo2);
    }

    void SendEnd() {
//        MIDIControl message = new MIDIControl();
//        message.createEnd(this.initiator_token, MIDISession.getInstance().ssrc, MIDISession.getInstance().bonjourName);
//        MIDISession.getInstance().sendUDPMessage(message, rinfo1);
//        EventBus.getDefault().post(new StreamDisconnectEvent(ssrc));
        if(rinfo1 != null) {
            SendEnd(rinfo1);
        }
    }

    private void SendEnd(Bundle rinfo) {
        Log.Debug(TAG,"send end "+rinfo.ToString());
        MIDIControl message = new MIDIControl();
        message.CreateEnd(this.initiator_token, MIDISession.GetInstance().ssrc, MIDISession.GetInstance().bonjourName);
        MIDISession.GetInstance().sendUDPMessage(message, rinfo);

        if(isConnected) {
            EventBus.getDefault().post(new StreamDisconnectEvent(ssrc, (Bundle)this.rinfo1.clone()));
        }
        else {
            EventBus.getDefault().post(new StreamDisconnectEvent(ssrc, initiator_token, (Bundle)this.rinfo1.clone()));
        }
        isConnected = false;

    }

    public void SendSynchronization(MIDIControl inboundSyncMessage) {
        long now = MIDISession.GetInstance().getNow();
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

        }
        if(outboundSyncMessage.count < 3) {
            MIDISession.getInstance().sendUDPMessage(outboundSyncMessage, rinfo2);
            if(!primarySyncComplete && ++syncCount > 3) {
                primarySyncComplete = true;
                EventBus.getDefault().post(new SyncronizeStoppedEvent(this.rinfo1));
                EventBus.getDefault().post(new ConnectionEstablishedEvent(rinfo1));
            }
        }

    }

}
}