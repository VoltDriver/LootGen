using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LootGen
{
    public class Property
    {
        private string m_name;

        public string Name
        {
            get { return m_name; }
            set { m_name = value; }
        }

        private string m_value;

        public string Value
        {
            get { return m_value; }
            set { m_value = value; }
        }

        public Property(string pName, string pValue)
        {
            m_name = pName;
            m_value = pValue;
        }

        public override string ToString()
        {
            return Name + "***" + Value;
        }
    }
}
