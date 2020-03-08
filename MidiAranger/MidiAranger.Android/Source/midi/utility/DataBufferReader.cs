using Java.Lang;
using Java.Math;
using System;
using System.Text;

namespace midi.utility {

    public class DataBufferReader {

        public int Read16(DataBuffer rawInput) {
            int firstByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            int secondByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));

            return (firstByte << 8
                    | secondByte)
                    & 0xFFFF;
        }

        /**
         * Get the length of the string currently in the byte stream.
         */
        public int LengthOfCurrentString(DataBuffer rawInput) {
            int len = 0;
            int pos = rawInput.GetStreamPosition();
            int ln = rawInput.GetBytesLength();


            while ((pos<=ln)&&(rawInput.GetBytes()[pos-1 + len] != 0))  {
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

        public string ReadString(DataBuffer rawInput) {
            int strLen = LengthOfCurrentString(rawInput);
            //        final String res = new String(rawInput.getBytes(), rawInput.getStreamPosition(), strLen, charset);
            string res = Encoding.UTF8.GetString(rawInput.GetBytes(), rawInput.GetStreamPosition(), strLen);
            rawInput.AddToStreamPosition(strLen);
            MoveToFourByteBoundry(rawInput);
            return res;
        }

        /**
         * Reads an Integer (32 bit integer) from the byte stream.
         * @return an {@link Integer}
         */
        public int ReadInteger(DataBuffer rawInput) {
            BigInteger intBits = ReadBigInteger(rawInput, 4);
            return intBits.IntValue();
        }

        public BigInteger ReadBigInteger(DataBuffer rawInput, int numBytes) {
            byte[] myBytes = new byte[numBytes];
            Array.Copy(rawInput.GetBytes(), rawInput.GetStreamPosition(), myBytes, 0, numBytes);
            rawInput.AddToStreamPosition(numBytes);
            return new BigInteger(myBytes);
        }

        public int Read8(DataBuffer rawInput) {
            int firstByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            return (firstByte & 0xFF);
        }

        public int Read24(DataBuffer rawInput) {
            int firstByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            int secondByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            int thirdByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            return ((int)(firstByte << 16
                    | secondByte << 8
                    | thirdByte))
                    & 0xFFFFFF;
        }

        public long ReadUnsignedInteger(DataBuffer rawInput) {

            int firstByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            int secondByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            int thirdByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            int fourthByte = (0x000000FF & ((int)rawInput.GetBytes()[rawInput.GetAndIncreaseStreamPositionByOne()]));
            return ((long)(firstByte << 24
                    | secondByte << 16
                    | thirdByte << 8
                    | fourthByte))
                    & 0xFFFFFFFFL;
        }

        public long readUnsignedInteger64(DataBuffer rawInput) {

            long highword = ReadUnsignedInteger(rawInput);
            long lowword = ReadUnsignedInteger(rawInput);

            long x = highword;
            x <<= 32;
            x += lowword;
            x &= 0xFFFFFFFFFFFFFFF;
            return x;
        }

    }
}