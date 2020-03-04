using Android.OS;
using Android.Util;
using Java.Net;
using Java.Nio;
using Java.Nio.Channels;
using midi.internal_events;
using System.Collections.Generic;
using System.IO;
using System.Threading;
using Xamarin.Forms;

namespace midi { 

public class MIDIPort {
    private int port;

    private Selector selector;
    private DatagramChannel channel;

    private Queue<DatagramPacket> outboundQueue;
//    private Queue<DatagramPacket> inboundQueue;

    private bool isListening = false;

    private static int BUFFER_SIZE = 1536;
    private static string TAG = "MIDIPort";
        //    private static final boolean DEBUG = false;

    private Java.Lang.Thread thread = new Java.Lang.Thread();

    static MIDIPort NewUsing(int port) {
        return new MIDIPort(port);
    }
   

    private MIDIPort(int port) {
        this.port = port;
        try {
            selector = Selector.Open();
            channel = DatagramChannel.Open();
            outboundQueue = new Queue<DatagramPacket>();
//            inboundQueue = new ConcurrentLinkedQueue<DatagramPacket>();

            InetSocketAddress address = new InetSocketAddress(this.port);
            channel.Socket().ReuseAddress = true;
            channel.ConfigureBlocking(false);
            channel.Socket().Bind(address);

//            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            channel.Register(selector, Operations.Read | Operations.Write, new UDPBuffer());

            Thread newThread = new Thread(new ThreadStart(Run));
            newThread.Start();

            }
        catch (IOException e) {
            e.StackTrace.ToString();
        }
    }

        ~MIDIPort()
        {
            try
            {
                isListening = false;
                outboundQueue.Clear();
    //            inboundQueue.clear();
                selector.Close();
                channel.Close();
                thread.Interrupt();
            }
            catch (IOException e)
            {
                e.StackTrace.ToString();
            }
        }
        
    public void Run() {
            while (isListening) {
            try {
                selector.Select();
                ICollection<SelectionKey> readyKeys = selector.SelectedKeys();
                if (readyKeys.Count==0 ) {
                    break;
                } else {
                        foreach(SelectionKey key in readyKeys) {
                            if(key.IsValid) {
                                if (key.IsReadable)
                                    HandleRead(key);
                                if (key.IsWritable)
                                    HandleWrite(key);
                            }
                        }
                        readyKeys.Clear();
                }
            } catch (IOException e) {
                throw new IOException(e.StackTrace);
            }
        }

    }

    int GetPort() {
        return this.port;
    }

    ThreadPriority GetThreadPriority() {
        return (ThreadPriority)thread.Priority;
    }

    void SetThreadPriority(ThreadPriority priority) {
        thread.Priority=(int)priority;
    }

    void Start() {
        isListening = true;

//        final Thread thread = new Thread(this);
//        // The JVM exits when the only threads running are all daemon threads.
        thread.Daemon=true;
        thread.Priority = (int)ThreadPriority.Normal;
        thread.Start();
//        Log.d(TAG,"create thread : "+thread.getId());

    }

    void Stop() {
        isListening = false;
    }

    private void HandleRead(SelectionKey key) {
//        Log.d("MIDIPort2","handleRead");
        DatagramChannel c = (DatagramChannel) key.Channel();
        UDPBuffer b = (UDPBuffer) key.Attachment();
        try {
            b.buffer.Clear();
            b.socketAddress = c.Receive(b.buffer);
                //EventBus.getDefault().post(new PacketEvent(new DatagramPacket(b.buffer.array(),b.buffer.capacity(),b.socketAddress)));
            MessagingCenter.Send<PacketEvent>(new PacketEvent(new Java.Net.DatagramPacket(b.buffer.ToArray<byte>(), b.buffer.Capacity(), b.socketAddress)), "PacketEvent");
        } catch (IOException e) {
            throw new IOException(e.StackTrace);
        }
    }

    private void HandleWrite(SelectionKey key) {
        if(!(outboundQueue.Count==0)) {
            Log.Debug("MIDIPort2","handleWrite "+ outboundQueue.Count);
            try {
                DatagramChannel c = (DatagramChannel) key.Channel();
                DatagramPacket d = outboundQueue.Dequeue();

                c.Send(ByteBuffer.Wrap(d.GetData()),d.SocketAddress);
            } catch (IOException e) {
                throw new IOException(e.StackTrace);
            }
        }
    }


    void sendMidi(MIDIControl control, Bundle rinfo) {
//        Log.d("MIDIPort2","sendMidi(control)");
        if (!isListening) {
            Log.Debug(TAG,"not listening...");
            return;
        }
        addToOutboundQueue(control.generateBuffer(),rinfo);
    }

    void sendMidi(MIDIMessage message, Bundle rinfo) {
//        Log.d("MIDIPort","sendMidi(message)");
        if (!isListening) {
            Log.Debug(TAG,"not listening...");
            return;
        }
        addToOutboundQueue(message.generateBuffer(),rinfo);
    }

    private void addToOutboundQueue(byte[] data, Bundle rinfo) {
        try {
            outboundQueue.add(new DatagramPacket(data, data.length, InetAddress.getByName(rinfo.getString(com.disappointedpig.midi.MIDIConstants.RINFO_ADDR)), rinfo.getInt(com.disappointedpig.midi.MIDIConstants.RINFO_PORT)));
            selector.wakeup();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public class UDPBuffer:Java.Lang.Object {
        public DatagramPacket datagramPacket;
        public SocketAddress  socketAddress;
        public ByteBuffer buffer = ByteBuffer.Allocate(BUFFER_SIZE);

    }
}
}