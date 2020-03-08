using Java.IO;
using Java.Lang;
using Java.Nio.Charset;
using Integer = Java.Lang.Integer;

namespace midi.utility {

    public class OutDataBuffer {
        private ByteArrayOutputStream stream;
        private Charset charset;

        private byte[] charByte;
        private byte[] shortBytes;
        private byte[] mediumBytes;
        private byte[] intBytes;
        private byte[] longintBytes;

        public OutDataBuffer() {
            this.stream = new ByteArrayOutputStream();
            this.charset = Charset.DefaultCharset();
            this.charByte = new byte[1];
            this.shortBytes = new byte[2];
            this.mediumBytes = new byte[3];
            this.intBytes = new byte[4];
            this.longintBytes = new byte[8];
        }

        private void AlignStream() {
            int alignmentOverlap = stream.Size() % 4;
            int padLen = (4 - alignmentOverlap) % 4;
            for (int pci = 0; pci < padLen; pci++) {
                stream.Write(0);
            }
        }

        public void Write(int anInt) {
            WriteInteger32ToByteArray(anInt);
        }

        public void Write(Float aFloat) {
            WriteInteger32ToByteArray(Float.FloatToIntBits(aFloat.FloatValue()));
        }

        public void Write(Double aDouble) {
            WriteInteger64ToByteArray(Double.DoubleToRawLongBits(aDouble.DoubleValue()));
        }


        public void Write16(int anInt) {
            WriteInteger16ToByteArray(anInt);
        }

        public void Write8(int anInt) {
            WriteInteger8ToByteArray(anInt);
        }

        public void Write24(int anInt) {
            WriteInteger24ToByteArray(anInt);
        }

        public void Write32(int anInt) {
            WriteInteger32ToByteArray(anInt);
        }

        public void Write64(Long aLong) {
            WriteInteger64ToByteArray(aLong.LongValue());
        }

        public void Write(String aString) {
            //        final byte[] stringBytes = aString.getBytes(charset);
            byte[] stringBytes = aString.GetBytes();
            WriteUnderHandler(stringBytes);
            stream.Write(0);
            AlignStream();
        }


        private void WriteUnderHandler(byte[] bytes) {

            try {
                stream.Write(bytes);
            } catch (IOException ex) {
                throw new RuntimeException("You're screwed:"
                        + " IOException writing to a ByteArrayOutputStream", ex);
            }
        }

        /**
         * Write a 32 bit integer to the byte array without allocating memory.
         *
         * @param value a 32 bit integer.
         */
        private void WriteInteger32ToByteArray(int value) {
            //byte[] intBytes = new byte[4];
            //I allocated the this buffer globally so the GC has less work

            intBytes[3] = (byte)value;
            value >>= 8;
            intBytes[2] = (byte)value;
            value >>= 8;
            intBytes[1] = (byte)value;
            value >>= 8;
            intBytes[0] = (byte)value;

            WriteUnderHandler(intBytes);
        }

        private void WriteInteger16ToByteArray(int value) {
            //        Log.d("converter","("+Integer.toBinaryString(value)+") "+ String.format("%02x",value & 0x000000FF) +" "+String.format("%02x",(value>>>8 & 0x000000FF)));
            shortBytes[1] = (byte)((value & 0x000000FF));
            //        Log.d("-----1","("+Integer.toBinaryString(shortBytes[1])+") "+ String.format("%02x", shortBytes[1]));

            shortBytes[0] = (byte)(value >> 8 & 0x000000FF);
            //        Log.d("-----0","("+Integer.toBinaryString(shortBytes[0])+") "+ String.format("%02x", shortBytes[0]));
            //        Log.d("-----:"," "+ (shortBytes.length));
            WriteUnderHandler(shortBytes);
        }


        private void WriteInteger8ToByteArray(int value) {
            charByte[0] = (byte)((value & 0x000000FF));
            WriteUnderHandler(charByte);
        }

        private void WriteInteger24ToByteArray(int value) {
            mediumBytes[2] = (byte)((value & 0x000000FF));
            mediumBytes[1] = (byte)(value >> 8 & 0x000000FF);
            mediumBytes[0] = (byte)(value >> 8 & 0x000000FF);
            WriteUnderHandler(mediumBytes);
        }

        /**
         * Write a 64 bit integer to the byte array without allocating memory.
         *
         * @param value a 64 bit integer.
         */
        private void WriteInteger64ToByteArray(long value) {
            longintBytes[7] = (byte)value;
            value >>= 8;
            longintBytes[6] = (byte)value;
            value >>= 8;
            longintBytes[5] = (byte)value;
            value >>= 8;
            longintBytes[4] = (byte)value;
            value >>= 8;
            longintBytes[3] = (byte)value;
            value >>= 8;
            longintBytes[2] = (byte)value;
            value >>= 8;
            longintBytes[1] = (byte)value;
            value >>= 8;
            longintBytes[0] = (byte)value;

            WriteUnderHandler(longintBytes);
        }

        public byte[] ToByteArray() {
            return stream.ToByteArray();
        }

    }
}