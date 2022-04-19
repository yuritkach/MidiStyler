namespace MidiAranger
{
    interface IFileParser
    {
        void Parse(ref byte[] buff, ref int position);
    }
}