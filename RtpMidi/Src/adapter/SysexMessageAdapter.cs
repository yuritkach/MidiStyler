using rtpmidi.model;

namespace rtpmidi.adapter { 
/**
 * Adapter class to make the {@link SysexMessage} compatible with {@link javax.sound.midi.SysexMessage}
 */
    public class SysexMessageAdapter:SysexMessage {

        public SysexMessageAdapter(SysexMessage message):base(message.Data, message.Length)
        {
        }
    }
}