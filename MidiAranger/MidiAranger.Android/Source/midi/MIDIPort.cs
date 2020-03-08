using Android.OS;
using Android.Util;
using Java.Net;
using Java.Nio;
using Java.Nio.Channels;
using midi.internal_events;
using System;
using System.Collections.Generic;
using System.IO;
using System.Threading;
using Xamarin.Forms;
using ThreadPriority = System.Threading.ThreadPriority;

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

    public static MIDIPort NewUsing(int port) {
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

    public void Start() {
        isListening = true;

//        final Thread thread = new Thread(this);
//        // The JVM exits when the only threads running are all daemon threads.
        thread.Daemon=true;
        thread.Priority = (int)ThreadPriority.Normal;
        thread.Start();
//        Log.d(TAG,"create thread : "+thread.getId());

    }

    public void Stop() {
        isListening = false;
    }

    private void HandleRead(SelectionKey key) {

            //        Log.d("MIDIPort2","handleRead");
            DatagramChannel c = (DatagramChannel)key.Channel();
            UDPBuffer b = (UDPBuffer)key.Attachment();
            try
            {
                //  разбираться почему буффер пустой

                b.buffer.Clear();
                b.socketAddress = c.Receive(b.buffer);
                //EventBus.getDefault().post(new PacketEvent(new DatagramPacket(b.buffer.array(),b.buffer.capacity(),b.socketAddress)));
                
                b.buffer.Flip();
                var buff = new byte[b.buffer.Limit()];
                b.buffer.Get(buff,0, b.buffer.Limit());
                if (buff.Length == 16)
                    ;
                var a = new Java.Net.DatagramPacket(buff, buff.Length,b.socketAddress);
                var d = new PacketEvent(a);
                MessagingCenter.Send<PacketEvent>(d, "PacketEvent");
            }
            catch (Exception e)
            {
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


    public void SendMidi(MIDIControl control, Bundle rinfo) {
//        Log.d("MIDIPort2","sendMidi(control)");
        if (!isListening) {
            Log.Debug(TAG,"not listening...");
            return;
        }
        AddToOutboundQueue(control.GenerateBuffer(),rinfo);
    }

    public void SendMidi(MIDIMessage message, Bundle rinfo) {
//        Log.d("MIDIPort","sendMidi(message)");
        if (!isListening) {
            Log.Debug(TAG,"not listening...");
            return;
        }
        AddToOutboundQueue(message.GenerateBuffer(),rinfo);
    }

    private void AddToOutboundQueue(byte[] data, Bundle rinfo) {
        try {
            outboundQueue.Enqueue(new DatagramPacket(data, data.Length, 
                InetAddress.GetByName(rinfo.GetString(midi.MIDIConstants.RINFO_ADDR)),rinfo.GetInt(midi.MIDIConstants.RINFO_PORT)));
            selector.Wakeup();
        } catch (UnknownHostException e) {
            throw new UnknownHostException(e.StackTrace);
        }
    }

    public class UDPBuffer:Java.Lang.Object {
        public DatagramPacket datagramPacket;
        public SocketAddress  socketAddress;
        public ByteBuffer buffer = ByteBuffer.Allocate(BUFFER_SIZE);

    }
}
}