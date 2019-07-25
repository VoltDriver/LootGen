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
    public partial class FrmItemView : Form
    {
        private Item m_item;

        public FrmItemView(Item pItem)
        {
            InitializeComponent();
            
            m_item = pItem;

            txt_itemName.Text = pItem.Name;

            txt_itemCategory.Text = pItem.Category;

            txt_rarity.Text = pItem.Rarity.ToString();

            txt_template.Text = pItem.Template.Name;

            imgbox_thumbnail.Load(pItem.ImagePath);

            foreach (Property prop in pItem.Properties)
            {
                lstbox_properties.Items.Add(prop.Name);
            }

            this.CenterToScreen();
        }

        private void btn_cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void lstbox_properties_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(lstbox_properties.SelectedIndices.Count != 0 && lstbox_properties.SelectedIndices[0] != -1)
            {
                foreach (Property prop in m_item.Properties)
                {
                    if(prop.Name.Equals(lstbox_properties.Items[lstbox_properties.SelectedIndices[0]]))
                    {
                        txt_propertyValue.Text = prop.Value;
                        return;
                    }
                }
            }
        }
    }
}
