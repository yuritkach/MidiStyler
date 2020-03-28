using System;
using System.Collections.Generic;

namespace Test
{

    public class ChordRecognizer
    {
        public List<ushort> ProcessChordDefinition(int startIndex,ushort mask, string[] definitions,int leng)
        {
            List<ushort> result =new List<ushort>();
            for (int i = startIndex; i < definitions.Length; i++)
            {
                string sss = definitions[i];
                if (sss.Substring(0, 1) == "(")
                {
                    List<ushort> res1 = ProcessChordDefinition(i + 1, mask,definitions,leng);
                    mask = (ushort)(mask | (1 << (16 - byte.Parse(sss.Substring(1,sss.Length-2)))));
                    List<ushort> res2 = ProcessChordDefinition(i + 1, mask,definitions,leng);
                    result.AddRange(res1);
                    result.AddRange(res2);
                    return result;
                }
                else
                {
                    mask =(ushort) (mask| (1<<(16-byte.Parse(sss))));
                    
                }
            }
            result.Add(mask);
            return result;

        }

    }

    class Program
    {




        static void Main(string[] args)
        {
            List<ushort> result;
            ChordRecognizer r = new ChordRecognizer();
            string[] keys = ("(1),3,(5),(7)").Split(",");
            result = r.ProcessChordDefinition(0,0,keys,keys.Length);
            Console.WriteLine(result.ToString());
            Console.ReadLine();
        }



    

    }

    
}
