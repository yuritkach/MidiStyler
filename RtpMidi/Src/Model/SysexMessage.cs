namespace rtpmidi.model { 

	public class SysexMessage:MidiMessage
    {

		public static int SYSTEM_EXCLUSIVE = 0xF0; // 240
		public static int SPECIAL_SYSTEM_EXCLUSIVE = 0xF7; // 247

		public SysexMessage(byte[] data, int length):base(data,length)
        {
		}
	}
}