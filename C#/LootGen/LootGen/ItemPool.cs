using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LootGen
{
    public class ItemPool
    {
        #region Properties

        private string m_name;

        public string Name
        {
            get { return m_name; }
            set { m_name = value; }
        }

        private List<Item_Chance> m_lstItems;

        public List<Item_Chance> Items
        {
            get { return m_lstItems; }
            set { m_lstItems = value; }
        }

        #endregion

        #region Constructor

        public ItemPool(string pName, List<Item_Chance> pItems)
        {
            Name = pName;
            Items = pItems;
        }

        #endregion

        #region Methods

        public override string ToString()
        {
            string longStringOfItems = "";

            for (int i = 0; i < Items.Count; i++)
            {
                if(i != Items.Count - 1)
                {
                    longStringOfItems += Items[i].itemIndex.ToString() + "***" + Items[i].dropPercent.ToString() + "*,*";
                }
                else
                {
                    longStringOfItems += Items[i].itemIndex.ToString() + "***" + Items[i].dropPercent.ToString();
                }
            }

            return Name + "*|*" +  longStringOfItems;
        }

        #endregion
    }
}
