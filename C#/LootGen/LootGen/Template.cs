using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LootGen
{
    public class Template
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

        private List<string> m_lstProperties;

        public List<string> Properties
        {
            get { return m_lstProperties; }
            set { m_lstProperties = value; }
        }


        #endregion

        #region Constructor

        public Template(string pName, List<string> pProperties)
        {
            Name = pName;
            Properties = pProperties;
        }

        #endregion

        #region Methods

        public override string ToString()
        {
            string longStringOfProps = "";

            for (int i = 0; i < m_lstProperties.Count; i++)
            {
                if(i != m_lstProperties.Count - 1)
                    longStringOfProps += m_lstProperties[i] + "*,*";
                else
                    longStringOfProps += m_lstProperties[i];
            }

            return (Name + "***" + longStringOfProps);
        }

        #endregion
    }
}
