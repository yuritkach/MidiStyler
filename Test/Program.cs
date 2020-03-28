using System;
using System.Collections.Generic;

namespace Test
{

    public class ChordRecognizer
    {
        public List<ushort> ProcessChordDefinition(int startIndex,ushort mask, string[] definitions,int leng)
        {
            
            for (int i = startIndex; i < definitions.Length; i++)
            {
                string s = definitions[i];
                if (s.Substring(0, 1) == "(")
                {
                    List<ushort> res1 = ProcessChordDefinition(i + 1, mask,definitions,leng);
                    mask = (ushort)(mask | (1 << (16 - byte.Parse(s.Substring(1,s.Length-2)))));
                    List<ushort> res2 = ProcessChordDefinition(i + 1, mask,definitions,leng);
                    res1.AddRange(res2);
                    return res1;
                }
                else
                    mask =(ushort) (mask| (1<<(16-byte.Parse(s))));
            }
            List<ushort> result = new List<ushort>();
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
