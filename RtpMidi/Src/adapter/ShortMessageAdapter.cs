using rtpmidi.model;

namespace rtpmidi.adapter { 

/**
 * Adapter class to make the {@link ShortMessage} compatible with {@link javax.sound.midi.ShortMessage}
 */
    public class ShortMessageAdapter : ShortMessage
    {
        public ShortMessageAdapter(ShortMessage message):base(message.Data[0])
        {

        }
    }
}