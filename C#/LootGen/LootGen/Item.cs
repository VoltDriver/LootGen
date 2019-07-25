using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LootGen
{
    public class Item
    {
        #region Properties

        private int m_index;

        public int Index
        {
            get { return m_index; }
            set { m_index = value; }
        }

        private string m_name;

        public string Name
        {
            get { return m_name; }
            set { m_name = value; }
        }

        private Template m_template;

        public Template Template
        {
            get { return m_template; }
            set { m_template = value; }
        }

        private string m_category;

        public string Category
        {
            get { return m_category; }
            set { m_category = value; }
        }

        private Rarity m_rarity;

        public Rarity Rarity
        {
            get { return m_rarity; }
            set { m_rarity = value; }
        }

        private List<Property> m_lstProperties;

        public List<Property> Properties
        {
            get { return m_lstProperties; }
            set { m_lstProperties = value; }
        }

        private string m_imagePath;

        public string ImagePath
        {
            get { return m_imagePath; }
            set { m_imagePath = value; }
        }
        #endregion

        #region Constructor
        public Item(string pName, Template pTemplate, Rarity pRarity, List<Property> pLstProperties, string pImagePath, string pCategory)
        {
            Name = pName;
            Template = pTemplate;
            Rarity = pRarity;
            Properties = pLstProperties;
            ImagePath = pImagePath;
            Category = pCategory;
        }
        #endregion

        #region Methods

        public override string ToString()
        {
            string longStringOfProps = "";

            for (int i = 0; i < Properties.Count; i++)
            {
                if(i != Properties.Count - 1)
                {
                    longStringOfProps += Properties[i].ToString() + "*,*";
                }
                else
                {
                    longStringOfProps += Properties[i].ToString();
                }
            }

            return Name + "*|*" + Template.ToString() + "*|*" + Rarity.ToString() + "*|*" + longStringOfProps + "*|*" + ImagePath + "*|*" + Category;
        }

        #endregion
    }
}
