namespace midi.utility {

    public class DataBufferReader {

        public int Read16(DataBuffer rawInput) {
            int firstByte = (0x000000FF & ((int)rawInput.getBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            int secondByte = (0x000000FF & ((int)rawInput.getBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));

            return (firstByte << 8
                    | secondByte)
                    & 0xFFFF;
        }

        /**
         * Get the length of the string currently in the byte stream.
         */
        public int LengthOfCurrentString(DataBuffer rawInput) {
            int len = 0;
            while (rawInput.GetBytes()[rawInput.GetStreamPosition() + len] != 0) {
                len++;
            }
            return len;
        }
        /**
         * Move to the next byte with an index in the byte array
         * which is dividable by four.
         */
        public void MoveToFourByteBoundry(DataBuffer rawInput) {
            // If i am already at a 4 byte boundry, I need to move to the next one
            int mod = rawInput.GetStreamPosition() % 4;
            rawInput.AddToStreamPosition(4 - mod);
        }

        public String ReadString(DataBuffer rawInput) {
            int strLen = lengthOfCurrentString(rawInput);
            //        final String res = new String(rawInput.getBytes(), rawInput.getStreamPosition(), strLen, charset);
            String res = new String(rawInput.GetBytes(), rawInput.GetStreamPosition(), strLen);
            rawInput.AddToStreamPosition(strLen);
            MoveToFourByteBoundry(rawInput);
            return res;
        }

        /**
         * Reads an Integer (32 bit integer) from the byte stream.
         * @return an {@link Integer}
         */
        public Integer ReadInteger(DataBuffer rawInput) {
            BigInteger intBits = ReadBigInteger(rawInput, 4);
            return intBits.intValue();
        }

        public BigInteger readBigInteger(DataBuffer rawInput, int numBytes) {
            byte[] myBytes = new byte[numBytes];
            System.ArrayCopy(rawInput.GetBytes(), rawInput.GetStreamPosition(), myBytes, 0, numBytes);
            rawInput.AddToStreamPosition(numBytes);
            return new BigInteger(myBytes);
        }

        public int read8(DataBuffer rawInput) {
            int firstByte = (0x000000FF & ((int)rawInput.getBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            return (firstByte & 0xFF);
        }

        public int read24(DataBuffer rawInput) {
            int firstByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            int secondByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            int thirdByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            return ((int)(firstByte << 16
                    | secondByte << 8
                    | thirdByte))
                    & 0xFFFFFF;
        }

        public Long readUnsignedInteger(DataBuffer rawInput) {

            int firstByte = (0x000000FF & ((int)rawInput.getBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            int secondByte = (0x000000FF & ((int)rawInput.getBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            int thirdByte = (0x000000FF & ((int)rawInput.getBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            int fourthByte = (0x000000FF & ((int)rawInput.getBytes()[rawInput.getAndIncreaseStreamPositionByOne()]));
            return ((long)(firstByte << 24
                    | secondByte << 16
                    | thirdByte << 8
                    | fourthByte))
                    & 0xFFFFFFFFL;
        }

        public long readUnsignedInteger64(DataBuffer rawInput) {

            long highword = readUnsignedInteger(rawInput);
            long lowword = readUnsignedInteger(rawInput);

            long x = highword;
            x <<= 32;
            x += lowword;
            x &= 0xFFFFFFFFFFFFFFFFL;
            return x;
        }

    }
}