namespace midi.utility {

    public class DataBuffer {

        private byte[] bytes;
        private int bytesLength;
        private int streamPosition;

        public DataBuffer(byte[] bytes,int bytesLength) {

            this.bytes = bytes;
            this.bytesLength = bytesLength;
            this.streamPosition = 0;
        }

        public byte[] GetBytes() {
            return bytes;
        }

        public int GetBytesLength() {
            return bytesLength;
        }

        public int GetAndIncreaseStreamPositionByOne() {
            return streamPosition++;
        }

        public void AddToStreamPosition(int toAdd) {
            streamPosition += toAdd;
        }

        public int GetStreamPosition() {
            return streamPosition;
        }

        public byte[] slice(int position) {
            return Arrays.CopyOfRange(this.bytes, position, bytesLength);
        }
    }

}