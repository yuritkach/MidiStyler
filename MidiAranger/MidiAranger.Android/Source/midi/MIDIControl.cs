using Android.Util;
using Java.Lang;
using midi.internal_events;
using midi.utility;
using System.Collections.Generic;

namespace midi { 

    public class MIDIControl {

        private bool valid;
        private DataBuffer m;
        public AppleMIDICommand command;

        public int protocol_version;
        public int initiator_token;
        public int ssrc;
        public string name;
        public int count;
        public int padding;
        public long timestamp1,timestamp2,timestamp3;
        public int sequenceNumber;


        public MIDIControl() {}

        public bool Parse(PacketEvent packet) {
            this.valid = false;
            DataBuffer rawInput = new DataBuffer(packet.GetData(),packet.GetLength());
            DataBufferReader reader = new DataBufferReader();
            int protocol = reader.Read16(rawInput);
            if(protocol == 0xffff) {
                command = commandMap.GetValueOrDefault<Integer,AppleMIDICommand>(new Integer(reader.Read16(rawInput)));
                switch (command) {
                    case AppleMIDICommand.INVITATION:
                    case AppleMIDICommand.INVITATION_ACCEPTED:
                    case AppleMIDICommand.INVITATION_REJECTED:
                    case AppleMIDICommand.END:
                        this.valid = true;
                        protocol_version = reader.ReadInteger(rawInput);
                        initiator_token = reader.ReadInteger(rawInput);
                        ssrc = reader.ReadInteger(rawInput);
                        name = reader.ReadString(rawInput);
    //                    this.version = buffer.readUInt32BE(4);
    //                    this.token = buffer.readUInt32BE(8);
    //                    this.ssrc = buffer.readUInt32BE(12);
    //                    this.name = buffer.toString('utf-8', 16);

                        break;
                    case AppleMIDICommand.SYNCHRONIZATION:
                        this.valid = true;
                        ssrc = reader.ReadInteger(rawInput);
                        count = reader.Read8(rawInput);
                        padding = reader.Read24(rawInput);
                        timestamp1 = reader.readUnsignedInteger64(rawInput);
                        timestamp2 = reader.readUnsignedInteger64(rawInput);
                        timestamp3 = reader.readUnsignedInteger64(rawInput);
    //                    this.ssrc = buffer.readUInt32BE(4, 8);
    //                    this.count = buffer.readUInt8(8);
    //                    this.padding = (buffer.readUInt8(9) << 0xF0) + buffer.readUInt16BE(10);
    //                    this.timestamp1 = buffer.slice(12, 20); //[buffer.readUInt32BE(12), buffer.readUInt32BE(16)];
    //                    this.timestamp2 = buffer.slice(20, 28); //[buffer.readUInt32BE(20), buffer.readUInt32BE(24)];
    //                    this.timestamp3 = buffer.slice(28, 36); //[buffer.readUInt32BE(28), buffer.readUInt32BE(32)];
                        break;
                    case AppleMIDICommand.RECEIVER_FEEDBACK:
                        this.valid = true;
                        ssrc = reader.ReadInteger(rawInput);
                        sequenceNumber = reader.Read16(rawInput);
    //                    this.ssrc = buffer.readUInt32BE(4, 8);
    //                    this.sequenceNumber = buffer.readUInt16BE(8);
                        break;
                    case AppleMIDICommand.BITRATE_RECEIVE_LIMIT:
                        this.valid = true;
                        break;

                }
            }
            return valid;
        }

        public bool IsValid() {
            return valid;
        }

        public void CreateInvitation(int token, int ssrc, string name) {
            this.name = name;
            this.initiator_token = token;
            this.ssrc = ssrc;
            this.protocol_version = 2;
            this.command = AppleMIDICommand.INVITATION;
        }

        public void CreateInvitationAccepted(int token, int ssrc, string name) {
            this.name = name;
            this.initiator_token = token;
            this.ssrc = ssrc;
            this.protocol_version = 2;
            this.command = AppleMIDICommand.INVITATION_ACCEPTED;
        }

        public void CreateInvitationRejected(int token, int ssrc, string name) {
            this.name = name;
            this.initiator_token = token;
            this.ssrc = ssrc;
            this.protocol_version = 2;
            this.command = AppleMIDICommand.INVITATION_REJECTED;
        }
        public void CreateEnd(int token, int ssrc, string name) {
            this.name = name;
            this.initiator_token = token;
            this.ssrc = ssrc;
            this.protocol_version = 2;
            this.command = AppleMIDICommand.END;
        }
        public void CreateSyncronization(int ssrc, int count, long t1, long t2, long t3) {
            this.ssrc = ssrc;
            this.count = count;
            this.timestamp1 = t1;
            this.timestamp2 = t2;
            this.timestamp3 = t3;
            this.command = AppleMIDICommand.SYNCHRONIZATION;
        }

        public byte[] GenerateBuffer() {
            OutDataBuffer buffer = new OutDataBuffer();

            switch(this.command) {
                case AppleMIDICommand.INVITATION:
                case AppleMIDICommand.INVITATION_ACCEPTED:
                case AppleMIDICommand.INVITATION_REJECTED:
                case AppleMIDICommand.END:
                    buffer.Write16(new Integer(0xFFFF));
                    buffer.Write16(GetCommandKey(this.command));
                    buffer.Write(this.protocol_version);
                    buffer.Write(this.initiator_token);
                    buffer.Write(this.ssrc);
                    buffer.Write(new String(name));
                    break;
                case AppleMIDICommand.SYNCHRONIZATION:
                    buffer.Write16(new Integer(0xFFFF));
                    buffer.Write16(GetCommandKey(this.command));
                    buffer.Write(this.ssrc);
                    buffer.Write8(new Integer(this.count));
                    buffer.Write24(new Integer(this.padding));
                    buffer.Write64(new Long(this.timestamp1));
                    buffer.Write64(new Long(this.timestamp2));
                    buffer.Write64(new Long(this.timestamp3));
                    break;
                case AppleMIDICommand.RECEIVER_FEEDBACK:
                    buffer.Write16(new Integer(0xFFFF));
                    buffer.Write16(GetCommandKey(this.command));
                    buffer.Write(this.ssrc);
                    buffer.Write(this.sequenceNumber);
                    break;
                default:
                    return null;
            }
            return buffer.ToByteArray();
        }

        private static Dictionary<Integer, AppleMIDICommand> commandMap = new Dictionary<Integer, AppleMIDICommand>();

        static MIDIControl(){
            commandMap.Add(new Integer(0x494E), AppleMIDICommand.INVITATION);
            commandMap.Add(new Integer(0x4F4B), AppleMIDICommand.INVITATION_ACCEPTED);
            commandMap.Add(new Integer(0x4E4F), AppleMIDICommand.INVITATION_REJECTED);
            commandMap.Add(new Integer(0x4259), AppleMIDICommand.END);
            commandMap.Add(new Integer(0x434B), AppleMIDICommand.SYNCHRONIZATION);
            commandMap.Add(new Integer(0x5253), AppleMIDICommand.RECEIVER_FEEDBACK);
            commandMap.Add(new Integer(0x524C), AppleMIDICommand.BITRATE_RECEIVE_LIMIT);
        }

        private Integer GetCommandKey(AppleMIDICommand c){
            foreach(Integer key in commandMap.Keys){
                if(key.Equals(c)){
                    return key; //return the first found
                }
            }
            return null;
        }

        public enum AppleMIDICommand {
            NOOP,
            INVITATION,
            INVITATION_ACCEPTED,
            INVITATION_REJECTED,
            END,
            SYNCHRONIZATION,
            RECEIVER_FEEDBACK,
            BITRATE_RECEIVE_LIMIT
        }

        public void Dumppacket() {
            Log.Debug("MIDIControl","------------------------------");
            Log.Debug("MIDIControl","command: "+this.command.ToString());
            switch(this.command) {
                case AppleMIDICommand.INVITATION:
                    Log.Debug("MIDIControl","protocol_version : "+this.protocol_version);
                    Log.Debug("MIDIControl","initiator_token : "+ String.Format("0x%X",this.initiator_token));
                    Log.Debug("MIDIControl","ssrc : "+ String.Format("0x%X",this.ssrc));
                    Log.Debug("MIDIControl","name : "+this.name);
                    break;
                case AppleMIDICommand.INVITATION_ACCEPTED:
                    Log.Debug("MIDIControl","protocol_version : "+this.protocol_version);
                    Log.Debug("MIDIControl","initiator_token : "+ String.Format("0x%X",this.initiator_token));
                    Log.Debug("MIDIControl","ssrc : "+ String.Format("0x%X",this.ssrc));
                    Log.Debug("MIDIControl","name : "+this.name);
                    break;
                case AppleMIDICommand.INVITATION_REJECTED:
                    Log.Debug("MIDIControl","protocol_version : "+this.protocol_version);
                    Log.Debug("MIDIControl","initiator_token : "+ String.Format("0x%X",this.initiator_token));
                    Log.Debug("MIDIControl","ssrc : "+ String.Format("0x%X",this.ssrc));
                    Log.Debug("MIDIControl","name : "+this.name);
                    break;
                case AppleMIDICommand.RECEIVER_FEEDBACK:
                    Log.Debug("MIDIControl","ssrc : "+ String.Format("0x%X",this.ssrc));
                    Log.Debug("MIDIControl","name : "+this.sequenceNumber);
                    break;
                case AppleMIDICommand.BITRATE_RECEIVE_LIMIT:
                    break;
                case AppleMIDICommand.END:
                    Log.Debug("MIDIControl","protocol_version : "+this.protocol_version);
                    Log.Debug("MIDIControl","initiator_token : "+ String.Format("0x%X",this.initiator_token));
                    Log.Debug("MIDIControl","ssrc : "+ String.Format("0x%X",this.ssrc));
                    break;
                case AppleMIDICommand.SYNCHRONIZATION:
                    Log.Debug("MIDIControl","ssrc : "+ String.Format("0x%X",this.ssrc));
                    Log.Debug("MIDIControl","count : "+this.count);
                    Log.Debug("MIDIControl","padding : "+ String.Format("0x%X",this.padding));
                    Log.Debug("MIDIControl","ts1 : "+ String.Format("0x%X",this.timestamp1));
                    Log.Debug("MIDIControl","ts2 : "+ String.Format("0x%X",this.timestamp2));
                    Log.Debug("MIDIControl","ts3 : "+ String.Format("0x%X",this.timestamp3));

                    break;
                default:
                    Log.Debug("MIDIControl","unknown packet");
                    break;
            }
            Log.Debug("MIDIControl","------------------------------");
        }
    }
}