using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LootGen
{
    public partial class FrmNotepad : Form
    {
        public string m_text;

        public FrmNotepad(string pText)
        {
            InitializeComponent();

            m_text = pText;

            txt_note.Text = m_text;
        }
    }
}
