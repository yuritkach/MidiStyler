using Java.Lang;
using rtpmidi.model;
using rtpmidi.adapter;

namespace rtipmidi {
    
    /**
     * Converter class to convert from {@link io.github.leovr.rtipmidi.model.MidiMessage} to {@link MidiMessage}
     */
    public class NetMidiMessageConverter {

        public MidiMessage Convert(MidiMessage message) {
            if (message is ShortMessage) {
                return HandleShortMessage((ShortMessage)message);
            } else if (message is SysexMessage) {
                return HandleSysexMessage((SysexMessage)message);
            }
            throw new IllegalArgumentException("Message could not be converted");
        }

//        MidiMessage Convert(MidiMessage message) {
//            if (message instanceof javax.sound.midi.ShortMessage) {
//                final javax.sound.midi.ShortMessage shortMessage = (javax.sound.midi.ShortMessage)message;
//                return new ShortMessage((byte)shortMessage.getCommand(), (byte)shortMessage.getData1(),
//                        (byte)shortMessage.getData2());
//            } else if (message instanceof javax.sound.midi.SysexMessage) {
//                final javax.sound.midi.SysexMessage sysexMessage = (javax.sound.midi.SysexMessage)message;
//                return new SysexMessage(sysexMessage.getMessage(), sysexMessage.getLength());
//            }
//            throw new IllegalArgumentException("Message could not be converted");
//        }

        private MidiMessage HandleSysexMessage(SysexMessage message) {
            return new SysexMessageAdapter(message);
        }

        private MidiMessage HandleShortMessage(ShortMessage message) {
            return new ShortMessageAdapter(message);
        }

    }
}