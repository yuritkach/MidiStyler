using Android.Media.Midi;
using System.Collections.Generic;
namespace rtpmidi { 
/**
 * {@link AppleMidiSession} with one or more {@link MidiDevice} as the receiver or sender of the MIDI messages
 */
public class MidiDeviceAppleMidiSession:NetRtpMidiSession {

    private List<MidiDevice> midiDevices = new List<MidiDevice>();
    private List<Receiver> receivers = new List<Receiver>();

    public MidiDeviceAppleMidiSession(@Nonnull final MidiDeviceModePair midiDeviceModePair) {
        this(Collections.singletonList(midiDeviceModePair));
    }

    public MidiDeviceAppleMidiSession(final Collection<MidiDeviceModePair> midiDeviceModePairs) {
        for (final MidiDeviceModePair pair : midiDeviceModePairs) {
            final MidiDevice device = pair.getDevice();
            final MidiDeviceMode mode = pair.getMode();

            openMidiDevice(device);

            initReading(device, mode);
            initWriting(device, mode);

            midiDevices.add(device);
        }
    }

    private void initWriting(final @Nonnull MidiDevice midiDevice, final @Nonnull MidiDeviceMode deviceMode) {
        if (deviceMode == MidiDeviceMode.BIDIRECTIONAL || deviceMode == MidiDeviceMode.WRITE_ONLY) {
            if (midiDevice.getMaxTransmitters() == 0) {
                throw new AppleMidiSessionInstantiationException(
                        "MIDI-Device cannot have any more transmitters or does not support transmitters");
            }
            try {
                midiDevice.getTransmitter().setReceiver(new MidiDeviceReceiver());
            } catch (final MidiUnavailableException e) {
                throw new AppleMidiSessionInstantiationException("Error getting transmitter", e);
            }
        }
    }

    private void initReading(final @Nonnull MidiDevice midiDevice, final @Nonnull MidiDeviceMode deviceMode) {
        if (deviceMode == MidiDeviceMode.BIDIRECTIONAL || deviceMode == MidiDeviceMode.READ_ONLY) {
            if (midiDevice.getMaxReceivers() == 0) {
                throw new AppleMidiSessionInstantiationException(
                        "MIDI-Device cannot have any more receivers or does not support receivers");
            }
            try {
                receivers.add(midiDevice.getReceiver());
            } catch (final MidiUnavailableException e) {
                throw new AppleMidiSessionInstantiationException("Error getting receiver", e);
            }
        }
    }

    private void openMidiDevice(final @Nonnull MidiDevice midiDevice) {
        if (midiDevice.isOpen()) {
            throw new AppleMidiSessionInstantiationException("MIDI-Device is already open");
        }
        try {
            midiDevice.open();
        } catch (final MidiUnavailableException e) {
            throw new AppleMidiSessionInstantiationException("Error opening MIDI device", e);
        }
    }

    @Override
    protected void onMidiMessage(final MidiMessage message, final long timestamp) {
        if (receivers.isEmpty()) {
            return;
        }
        for (final Receiver receiver : receivers) {
            receiver.send(message, timestamp);
        }
    }

    @Override
    public long getCurrentTimestamp() {
        for (final MidiDevice device : midiDevices) {
            final long microsecondPosition = device.getMicrosecondPosition();
            if (microsecondPosition != -1) {
                return timestampOffset + microsecondPosition / 100;
            }
        }
        return -1;
    }

    @Override
    protected void onEndSession() {
        for (final MidiDevice device : midiDevices) {
            device.close();
        }
    }

    private class MidiDeviceReceiver implements Receiver {

        @Override
        public void send(final MidiMessage message, final long timeStamp) {
            sendMidiMessage(message, timeStamp);
        }

        @Override
        public void close() {
        }
    }
}
}