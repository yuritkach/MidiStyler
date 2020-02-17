namespace rtpmidi.model { 

    public class ShortMessage:MidiMessage {

        public static int MIDI_TIME_CODE = 0xF1; // 241
        public static int SONG_POSITION_POINTER = 0xF2; // 242
        public static int SONG_SELECT = 0xF3; // 243
        public static int TUNE_REQUEST = 0xF6; // 246
        public static int TIMING_CLOCK = 0xF8; // 248
        public static int START = 0xFA; // 250
        public static int STOP = 0xFC; //252
        public static int ACTIVE_SENSING = 0xFE; // 254
        public static int SYSTEM_RESET = 0xFF; // 255
        public static int NOTE_OFF = 0x80;  // 128
        public static int NOTE_ON = 0x90;  // 144
        public static int POLY_PRESSURE = 0xA0;  // 160
        public static int CONTROL_CHANGE = 0xB0;  // 176
        public static int PROGRAM_CHANGE = 0xC0;  // 192
        public static int CHANNEL_PRESSURE = 0xD0;  // 208
        public static int PITCH_BEND = 0xE0;  // 224

        public ShortMessage(byte command):base(new byte[] { command },1)
        {
        }

        public ShortMessage(byte command,byte data1):base(new byte[] { command, data1 }, 2)
        {
        }

        public ShortMessage(byte command, byte data1, byte data2):base(new byte[]{command, data1, data2 }, 3)
        {
        }

    }
}