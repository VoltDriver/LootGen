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
    public partial class SeedValue : Form
    {

        public SeedValue()
        {
            InitializeComponent();

            if (MainMenu.SEED_VALUE_GLOBAL != null && MainMenu.SEED_VALUE_GLOBAL != -1)
                num_seedValue.Value = MainMenu.SEED_VALUE_GLOBAL;

            this.CenterToScreen();
        }

        private void btn_setSeedValue_Click(object sender, EventArgs e)
        {
            MainMenu.SEED_VALUE_GLOBAL = (int)num_seedValue.Value;
            this.Close();
        }

        private void btn_resetSeed_Click(object sender, EventArgs e)
        {
            MainMenu.SEED_VALUE_GLOBAL = -1;
            this.Close();
        }
    }
}
