using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LootGen
{
    public class Item_Chance
    {
        public int itemIndex;

        public decimal dropPercent;

        public Item_Chance(int pItemIndex, decimal pDropPercent)
        {
            itemIndex = pItemIndex;
            dropPercent = pDropPercent;
        }

        public override string ToString()
        {
            return itemIndex.ToString() + "*|*" + dropPercent.ToString();
        }
    }
}
