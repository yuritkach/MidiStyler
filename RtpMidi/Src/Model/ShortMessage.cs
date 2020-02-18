namespace rtpmidi.model { 

    public class ShortMessage:MidiMessage {

        public const int MIDI_TIME_CODE = 0xF1; // 241
        public const int SONG_POSITION_POINTER = 0xF2; // 242
        public const int SONG_SELECT = 0xF3; // 243
        public const int TUNE_REQUEST = 0xF6; // 246
        public const int TIMING_CLOCK = 0xF8; // 248
        public const int START = 0xFA; // 250
        public const int STOP = 0xFC; //252
        public const int ACTIVE_SENSING = 0xFE; // 254
        public const int SYSTEM_RESET = 0xFF; // 255
        public const int NOTE_OFF = 0x80;  // 128
        public const int NOTE_ON = 0x90;  // 144
        public const int POLY_PRESSURE = 0xA0;  // 160
        public const int CONTROL_CHANGE = 0xB0;  // 176
        public const int PROGRAM_CHANGE = 0xC0;  // 192
        public const int CHANNEL_PRESSURE = 0xD0;  // 208
        public const int PITCH_BEND = 0xE0;  // 224

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