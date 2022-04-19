namespace MidiAranger
{
    public enum SectionID {MTHD=0x4D546864, CASM=0x4341534D,OTS=0x4F545363,MDB=0x464E5263,MH=0x4D486864, CSEG=0x43534547,
       Sdec=0x53646563, Ctab=0x43746162, Cntt=0x436E7474};

    public class MIDIStyle
    {


        public MIDISection MidiSection { get; set; }
        public CASMSection CasmSection { get; set; }
        //public OTSSection OtsSection { get; set; }
        //public MDBSection MdbSection { get; set; }
        //public MHSection MhSection { get; set; }

        public MIDIStyle()
        {
            
            MidiSection = new MIDISection();
            CasmSection = new CASMSection();
//            OtsSection = null;
//            MdbSection = null;
//            MhSection = null;
        }

        public void LoadStyle(string filename)
        {
            //         filebuff = File.ReadAllBytes(filename); !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Убрать после отладки
            filebuff = DEBUGDATA.midifileData;
            buffPosition = 0;
            LoadSections(ref filebuff, ref buffPosition);
        }


        private void LoadSections(ref byte[] filebuff, ref int buffPosition)
        {
            while (buffPosition < (filebuff.Length-sizeof(uint)))
            {
                SectionID sectionID = Common.GetSectionId(ref filebuff, ref buffPosition);
                switch (sectionID)
                {
                    case SectionID.MTHD: MidiSection.Parse(ref filebuff, ref buffPosition);break;
                    case SectionID.CASM: CasmSection.Parse(ref filebuff, ref buffPosition);break;
//                    case SectionID.CASM: CasmSection.Parse(ref filebuff, ref buffPosition); break;
//                    case SectionID.CASM: CasmSection.Parse(ref filebuff, ref buffPosition); break;
//                    case SectionID.CASM: CasmSection.Parse(ref filebuff, ref buffPosition); break;
//                    default: throw new ApplicationException("Unknown style-file section");
                }
            }
        }


        private int buffPosition; 
        private byte[] filebuff;



        public byte[] ProcessMessage(MIDITrack track, int messageIndex)
        {
            return track.MidiEvents[messageIndex].MidiMessage;
        }
    }
}