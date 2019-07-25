using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LootGen
{
    public partial class CreateItem : Form
    {
        private List<Template> m_lstTemplates;
        private List<Property> m_lstProperties;
        private List<Item> m_dataBaseITems;

        public FrmManageItems itemManager;

        private Item m_editedItem;
        private Item m_originalEditedItem;

        private int m_oldIndex;

        private bool firstTimeOpening;

        public CreateItem(List<Template> pLstTemplates, List<Item> pLstItems, Item pEditedItem)
        {
            InitializeComponent();

            firstTimeOpening = true;

            m_lstTemplates = pLstTemplates;

            cbo_Template.Items.Add("None");
            foreach (Template temp in m_lstTemplates)
            {
                cbo_Template.Items.Add(temp.Name);
            }

            m_lstProperties = pEditedItem.Properties;
            m_dataBaseITems = pLstItems;

            for (int i = 0; i < 9; i++)
            {
                cbo_rarity.Items.Add((Rarity)i);
            }

            m_editedItem = pEditedItem;
            m_originalEditedItem = new Item(pEditedItem.Name, pEditedItem.Template, pEditedItem.Rarity, pEditedItem.Properties, pEditedItem.ImagePath, pEditedItem.Category);

            switch (pEditedItem.Rarity)
            {
                case Rarity.F:
                    cbo_rarity.SelectedIndex = 0;
                    break;
                case Rarity.E:
                    cbo_rarity.SelectedIndex = 1;
                    break;
                case Rarity.D:
                    cbo_rarity.SelectedIndex = 2;
                    break;
                case Rarity.C:
                    cbo_rarity.SelectedIndex = 3;
                    break;
                case Rarity.B:
                    cbo_rarity.SelectedIndex = 4;
                    break;
                case Rarity.A:
                    cbo_rarity.SelectedIndex = 5;
                    break;
                case Rarity.S:
                    cbo_rarity.SelectedIndex = 6;
                    break;
                case Rarity.SS:
                    cbo_rarity.SelectedIndex = 7;
                    break;
                case Rarity.SSS:
                    cbo_rarity.SelectedIndex = 8;
                    break;
                default:
                    break;
            }

            int indexTemp = -1;

            for (int i = 0; i < cbo_Template.Items.Count; i++)
            {
                if(cbo_Template.Items[i].Equals(pEditedItem.Template.Name))
                {
                    indexTemp = i;
                }
            }

            cbo_Template.SelectedIndex = indexTemp;

            RefreshPropertiesList();

            m_oldIndex = -1;

            btn_modifyPropValue.Enabled = false;
            btn_remove.Enabled = false;

            this.CenterToScreen();

            try
            {
                imgbox_thumbnail.Load(pEditedItem.ImagePath);
            }

            catch
            {
                imgbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
            }
            

            txt_itemCategory.Text = pEditedItem.Category;
            txt_itemName.Text = pEditedItem.Name;

            btn_create.Text = "Apply Changes";

            this.Text = "Edit an Item";
        }

        public CreateItem(List<Template> pLstTemplates, List<Item> pLstItems)
        {
            InitializeComponent();
            m_lstTemplates = pLstTemplates;

            cbo_Template.Items.Add("None");
            foreach (Template temp in m_lstTemplates)
            {
                cbo_Template.Items.Add(temp.Name);
            }

            m_lstProperties = new List<Property>();
            m_dataBaseITems = pLstItems;

            for (int i = 0; i < 9; i++)
            {
                cbo_rarity.Items.Add((Rarity)i);
            }

            cbo_rarity.SelectedIndex = 0;
            cbo_Template.SelectedIndex = 0;

            m_oldIndex = -1;

            btn_modifyPropValue.Enabled = false;
            btn_remove.Enabled = false;

            this.CenterToScreen();

            imgbox_thumbnail.Load("Ressources\\Pictures\\Equipment Default.png");
        }

        private void lbl_template_Click(object sender, EventArgs e)
        {

        }

        private void lbl_itemCategory_Click(object sender, EventArgs e)
        {

        }

        private void cbo_Template_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(cbo_Template.SelectedIndex == 0)
                return;

            if (firstTimeOpening)
            {
                firstTimeOpening = false;

                RefreshPropertiesList();
                return;
            }
                

            if(MessageBox.Show("Changing the template will clear all current properties. Continue?", "Warning",
                MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
            {
                List<string> lstPropFromTemp = (m_lstTemplates[cbo_Template.SelectedIndex - 1]).Properties;

                m_lstProperties.Clear();

                foreach (string prop in lstPropFromTemp)
                {
                    m_lstProperties.Add(new Property(prop, ""));
                }

                RefreshPropertiesList();
            }
            else
            {
                cbo_Template.SelectedIndex = 0;
                return;
            }
        }

        private void btn_chooseImage_Click(object sender, EventArgs e)
        {
            OpenFileDialog fdlg = new OpenFileDialog();
            if (fdlg.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    imgbox_thumbnail.ImageLocation = fdlg.FileName;
                    imgbox_thumbnail.Load(fdlg.FileName);
                }
                catch
                {
                    MessageBox.Show("Could not retrieve the image.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
        }

        private void btn_cancel_Click(object sender, EventArgs e)
        {
            if(itemManager != null)
            {
                itemManager.wasAnEdit = false;
            }

            this.Close();
        }

        private void RefreshPropertiesList()
        {
            lstbox_properties.Items.Clear();

            txt_propertyName.Text = string.Empty;
            txt_propertyValue.Text = string.Empty;

            foreach (Property prop in m_lstProperties)
            {
                lstbox_properties.Items.Add(prop.Name);
            }

            m_oldIndex = -1;

        }

        private void lstbox_properties_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(m_oldIndex != -1)
            {
                //if (txt_propertyName.Text != string.Empty)
                //{
                //    m_lstProperties[m_oldIndex].Name = txt_propertyName.Text;
                //    lstbox_properties.Items[m_oldIndex] = txt_propertyName.Text;
                //}

                m_lstProperties[m_oldIndex].Value = txt_propertyValue.Text;
            }

            if(lstbox_properties.SelectedIndex == -1)
            {
                txt_propertyName.Text = string.Empty;
                txt_propertyValue.Text = string.Empty;

                btn_modifyPropValue.Enabled = false;
                btn_remove.Enabled = false;
                return;
            }

            txt_propertyName.Text = m_lstProperties[lstbox_properties.SelectedIndex].Name;
            txt_propertyValue.Text = m_lstProperties[lstbox_properties.SelectedIndex].Value;

            m_oldIndex = lstbox_properties.SelectedIndex;

            btn_modifyPropValue.Enabled = true;
            btn_remove.Enabled = true;
        }

        private void btn_add_Click(object sender, EventArgs e)
        {
            if(txt_propertyName.Text == string.Empty)
            {
                MessageBox.Show("Please enter a name in the \"Property Name\" field.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            foreach (Property prop in m_lstProperties)
	        {
                if(prop.Name.Equals(txt_propertyName.Text))
                {
                    MessageBox.Show("The Property you are trying to add is already on the list. Please use the \"Modify\" button to change the property value.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
	        }

            Property nwProp = new Property(txt_propertyName.Text, txt_propertyValue.Text);

            m_lstProperties.Add(nwProp);

            RefreshPropertiesList();

            btn_modifyPropValue.Enabled = false;
            btn_remove.Enabled = false;
        }

        private void btn_remove_Click(object sender, EventArgs e)
        {
            if (txt_propertyName.Text == string.Empty)
            {
                MessageBox.Show("Please enter a name in the \"Property Name\" field.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if(lstbox_properties.SelectedIndex == -1)
            {
                MessageBox.Show("Please select a Property from the list.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            try
            {
                m_lstProperties.RemoveAt(lstbox_properties.SelectedIndex);
                RefreshPropertiesList();
            }
            catch
            {
                MessageBox.Show("The Property you are trying to remove is not on the list.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

            btn_modifyPropValue.Enabled = false;
            btn_remove.Enabled = false;
        }

        private void btn_modifyPropValue_Click(object sender, EventArgs e)
        {
            if(txt_propertyName.Text == string.Empty)
            {
                MessageBox.Show("Please enter a name in the \"Property Name\" field.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if(lstbox_properties.SelectedIndex == -1)
            {
                MessageBox.Show("Please select a Property from the list.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            m_lstProperties[lstbox_properties.SelectedIndex].Name = txt_propertyName.Text;
            m_lstProperties[lstbox_properties.SelectedIndex].Value = txt_propertyValue.Text;

            RefreshPropertiesList();

            btn_modifyPropValue.Enabled = false;
            btn_remove.Enabled = false;
        }

        private void btn_create_Click(object sender, EventArgs e)
        {
            if(txt_itemName.Text == string.Empty)
            {
                MessageBox.Show("Please enter a name for this Item in the \"Name\" field.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if(m_lstProperties.Count == 0)
            {
                MessageBox.Show("Please create at least one property for this item.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string name = txt_itemName.Text;

            if (m_editedItem == null)
            {
                var value = m_dataBaseITems.Find(i => i.Name.Equals(name));

                if (value != null)
                {
                    MessageBox.Show("An item with this name already exists. Please use a different name.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }

                //foreach (Item item in m_dataBaseITems)
                //{
                //    if (item.Name.Equals(name))
                //    {
                //        MessageBox.Show("An item with this name already exists. Please use a different name.", "Error",
                //        MessageBoxButtons.OK, MessageBoxIcon.Error);
                //        return;
                //    }
                //}
            }

            if (!cbo_rarity.Text.Equals("A") &&
                !cbo_rarity.Text.Equals("B") &&
                !cbo_rarity.Text.Equals("C") &&
                !cbo_rarity.Text.Equals("D") &&
                !cbo_rarity.Text.Equals("E") &&
                !cbo_rarity.Text.Equals("F") &&
                !cbo_rarity.Text.Equals("S") &&
                !cbo_rarity.Text.Equals("SS") &&
                !cbo_rarity.Text.Equals("SSS"))
            {
                MessageBox.Show("The rarity you have entered is invalid. Please choose a Rarity from the drop down list.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if(lstbox_properties.SelectedIndices.Count != 0 && lstbox_properties.SelectedIndices[0] != -1)
            {
                m_lstProperties[lstbox_properties.SelectedIndex].Value = txt_propertyValue.Text;
            }

            if(lstbox_properties.Items.Count != 0)
            {
                lstbox_properties.SelectedIndex = 0;
                lstbox_properties.SelectedIndex = lstbox_properties.Items.Count - 1;
            }
            

            Rarity rar = (Rarity)cbo_rarity.SelectedIndex;

            Template temp;
            if (cbo_Template.SelectedIndex != 0)
                temp = m_lstTemplates[cbo_Template.SelectedIndex - 1];
            else
                temp = new Template("None", new List<string>());

            string cat = txt_itemCategory.Text;

            string fileName = imgbox_thumbnail.ImageLocation;

            if (!File.Exists(@"Ressources\Pictures\" + Path.GetFileName(imgbox_thumbnail.ImageLocation)))
                File.Copy(@fileName, @"Ressources\Pictures\" + Path.GetFileName(imgbox_thumbnail.ImageLocation));

            fileName = @"Ressources\Pictures\" + Path.GetFileName(imgbox_thumbnail.ImageLocation);

            List<Property> lstProp = m_lstProperties;

            Item newItem = new Item(name, temp, rar, lstProp, fileName, cat);

            if(m_editedItem == null)
            {
                m_dataBaseITems.Add(newItem);
                SaveThisItem(newItem);
            }
            else
            {
                m_editedItem.Name = name;
                m_editedItem.Template = temp;
                m_editedItem.Rarity = rar;
                m_editedItem.Properties = lstProp;
                m_editedItem.ImagePath = fileName;
                m_editedItem.Category = cat;

                if(m_editedItem.Name.Equals(m_originalEditedItem.Name) &&
                   m_editedItem.Properties.Equals(m_originalEditedItem.Properties) &&
                    m_editedItem.Rarity.Equals(m_originalEditedItem.Rarity) &&
                    m_editedItem.Template.Equals(m_originalEditedItem.Template) &&
                    m_editedItem.ImagePath.Equals(m_originalEditedItem.ImagePath) &&
                    m_editedItem.Category.Equals(m_originalEditedItem.Category))
                {
                    
                }
                else
                {
                    SaveItems();
                }            
            }

            if(itemManager != null)
            {
                if (m_editedItem != null)
                    itemManager.wasAnEdit = true;
                else
                    itemManager.wasAnEdit = false;
            }

            this.Close();
        }


        private void SaveThisItem(Item item)
        {
            StreamWriter objFichier = new StreamWriter(MainMenu.PATH_FILE_ITEMS, true);

            objFichier.Write(item.ToString() + "*&*");

            objFichier.Close();
        }

        private void SaveItems()
        {
            StreamWriter objFichier = new StreamWriter(MainMenu.PATH_FILE_ITEMS);

            for (int i = 0; i < m_dataBaseITems.Count; i++)
            {
                Item item = m_dataBaseITems[i];

                if(i == m_dataBaseITems.Count - 1)
                {
                    objFichier.Write(item.ToString());
                }
                else
                {
                    objFichier.Write(item.ToString() + "*&*");
                }
            }

            //foreach (Item item in m_dataBaseITems)
            //{
            //    objFichier.WriteLine(item.ToString());
            //}

            objFichier.Close();
        }

        private string GetRelativePath(string filespec, string folder)
        {
            Uri pathUri = new Uri(filespec);
            // Folders must end in a slash
            if (!folder.EndsWith(Path.DirectorySeparatorChar.ToString()))
            {
                folder += Path.DirectorySeparatorChar;
            }
            Uri folderUri = new Uri(folder);
            return Uri.UnescapeDataString(folderUri.MakeRelativeUri(pathUri).ToString().Replace('/', Path.DirectorySeparatorChar));
        }

        private void txt_propertyValue_TextChanged(object sender, EventArgs e)
        {

        }

        private void lbl_editProperty_Click(object sender, EventArgs e)
        {

        }
    }
}
