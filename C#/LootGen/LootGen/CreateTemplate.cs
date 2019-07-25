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
    public partial class CreateTemplate : Form
    {
        private List<string> m_properties;
        private List<Template> m_templates;
        private Template m_tempEdited;

        private bool m_isEdit;

        private const string PATH_FILE_TEMPLATES = MainMenu.PATH_FILE_TEMPLATES;

        public CreateTemplate(List<Template> pLstTemplate)
        {
            InitializeComponent();
            m_templates = pLstTemplate;
            m_properties = new List<string>();

            btn_modify.Enabled = false;
            btn_remove.Enabled = false;

            this.CenterToScreen();
        }

        public CreateTemplate(List<Template> pLstTemplate, bool isEdit, Template pTemp)
        {
            InitializeComponent();
            m_templates = pLstTemplate;
            m_properties = new List<string>();

            btn_modify.Enabled = false;
            btn_remove.Enabled = false;

            m_isEdit = isEdit;

            if (m_isEdit)
            {
                btn_create.Text = "Apply Changes";
                this.Text = "Edit a Template";
            }

            txt_templateName.Text = pTemp.Name;
            m_properties = pTemp.Properties;

            m_tempEdited = pTemp;

            RefreshPropertiesList();

            this.CenterToScreen();
        }

        private void lbl_properties_Click(object sender, EventArgs e)
        {

        }

        private void btn_cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btn_create_Click(object sender, EventArgs e)
        {
            if(txt_templateName.Text == string.Empty)
            {
                MessageBox.Show("Please enter a name for the Template.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if(m_isEdit)
            {
                Template newTemplate = new Template(txt_templateName.Text, m_properties);

                m_tempEdited.Name = newTemplate.Name;
                m_tempEdited.Properties = newTemplate.Properties;
            }
            else
            {
                Template newTemplate = new Template(txt_templateName.Text, m_properties);

                m_templates.Add(newTemplate);                
            }

            SaveTemplates();
            this.Close();
        }

        private void CreateTemplate_Load(object sender, EventArgs e)
        {

        }

        private void btn_addProperty_Click(object sender, EventArgs e)
        {
            string propName = txt_propertyName.Text;

            if(propName.Length == 0)
            {
                MessageBox.Show("Please enter the name of the property to add.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            foreach (var property in m_properties)
            {
                if(property.Equals(propName))
                {
                    MessageBox.Show("The property you want to add is already in the list.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }

            m_properties.Add(propName);

            txt_propertyName.Text = "";

            RefreshPropertiesList();
        }

        private void RefreshPropertiesList()
        {
            lstbox_properties.Items.Clear();

            m_properties.Sort();

            foreach (string property in m_properties)
            {
                lstbox_properties.Items.Add(property);
            }
        }

        private void lstbox_properties_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(lstbox_properties.SelectedIndex == -1)
            {
                txt_selectedName.Text = "";
                btn_modify.Enabled = false;
                btn_remove.Enabled = false;
                return;
            }
            
            string selectedPropName = lstbox_properties.SelectedItem.ToString();

            txt_selectedName.Text = selectedPropName;

            btn_modify.Enabled = true;
            btn_remove.Enabled = true;
        }

        private void btn_modify_Click(object sender, EventArgs e)
        {
            string modifiedName = txt_selectedName.Text;

            if (m_properties.Count == 0)
            {
                MessageBox.Show("There are no properties in this template.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (modifiedName.Equals(string.Empty))
            {
                MessageBox.Show("Please enter the name of the property in the \"Selected\" field.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            try
            {
                m_properties[lstbox_properties.SelectedIndex] = modifiedName;
            }
            catch
            {
                MessageBox.Show("The property you want to modify was removed.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            txt_selectedName.Text = "";

            RefreshPropertiesList();

            btn_modify.Enabled = false;
            btn_remove.Enabled = false;
        }

        private void btn_remove_Click(object sender, EventArgs e)
        {
            string nameOfProp = txt_selectedName.Text;

            if(m_properties.Count == 0)
            {
                MessageBox.Show("There are no properties in this template.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if(nameOfProp.Equals(string.Empty))
            {
                MessageBox.Show("Please enter the name of the property in the \"Selected\" field.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            try
            {
                m_properties.RemoveAt(lstbox_properties.SelectedIndex);
            }
            catch
            {
                MessageBox.Show("The property you want to remove was already removed.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            txt_selectedName.Text = "";

            RefreshPropertiesList();

            btn_modify.Enabled = false;
            btn_remove.Enabled = false;
        }

        private void pnl_createItem_Paint(object sender, PaintEventArgs e)
        {

        }

        private void SaveTemplates()
        {
            StreamWriter objFichier = new StreamWriter(PATH_FILE_TEMPLATES);

            foreach (Template temp in m_templates)
            {
                objFichier.WriteLine(temp.ToString());
            }

            objFichier.Close();
        }
    }
}
