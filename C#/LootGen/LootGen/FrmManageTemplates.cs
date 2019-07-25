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
    public partial class FrmManageTemplates : Form
    {
        List<Template> m_databaseTemplates;
        private ListViewColumnSorter lvwColumnSorter;

        public FrmManageTemplates(List<Template> pDataBaseTemplates)
        {
            InitializeComponent();

            // Create an instance of a ListView column sorter and assign it 
            // to the ListView control.
            lvwColumnSorter = new ListViewColumnSorter();
            this.lsv_templates.ListViewItemSorter = lvwColumnSorter;

            lsv_templates.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_templates.Columns.Add("Name", 210, HorizontalAlignment.Left);
            lsv_templates.Columns.Add("Properties", 210, HorizontalAlignment.Left);

            lsv_templates.View = View.Details;
            lsv_templates.HideSelection = false;
            lsv_templates.FullRowSelect = true;

            lsv_templates.Columns[0].Width = 0;
            lsv_templates.ColumnWidthChanging += lsv_templates_ColumnWidthChanging;

            m_databaseTemplates = pDataBaseTemplates;

            LoadTemplates();

            this.CenterToScreen();
        }

        private void lsv_templates_ColumnWidthChanging(object sender, ColumnWidthChangingEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.NewWidth = 0;
                e.Cancel = true;
            }
        }

        private void btn_close_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void FrmManageTemplates_FormClosing(object sender, FormClosingEventArgs e)
        {
            SaveTemplates();
        }

        private void SaveTemplates()
        {
            StreamWriter objFichier = new StreamWriter(MainMenu.PATH_FILE_TEMPLATES);

            foreach (Template temp in m_databaseTemplates)
            {
                objFichier.WriteLine(temp.ToString());
            }

            objFichier.Close();
        }

        private void btn_delete_Click(object sender, EventArgs e)
        {
            if(MessageBox.Show("Are you sure you want to delete this Template?", "Confirmation",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
            {
                m_databaseTemplates.RemoveAt(int.Parse(lsv_templates.Items[lsv_templates.SelectedIndices[0]].Text));
            }

            LoadTemplates();
        }

        private void btn_edit_Click(object sender, EventArgs e)
        {
            int realIndexTemp = int.Parse(lsv_templates.Items[lsv_templates.SelectedIndices[0]].Text);

            Template temp = m_databaseTemplates[realIndexTemp];

            CreateTemplate frmTemplate = new CreateTemplate(m_databaseTemplates, true, temp);

            frmTemplate.ShowDialog();

            LoadTemplates();
        }

        private void LoadTemplates()
        {
            lsv_templates.Items.Clear();

            for (int i = 0; i < m_databaseTemplates.Count; i++)
            {
                Template temp = m_databaseTemplates[i];

                ListViewItem lsvIt = new ListViewItem(i.ToString());
                lsvIt.SubItems.Add(temp.Name);

                string longStringOfProps = "";

                for (int b = 0; b < temp.Properties.Count; b++)
                {
                    if(b != temp.Properties.Count - 1)
                    {
                        longStringOfProps += temp.Properties[b] + ",";
                    }
                    else
                    {
                        longStringOfProps += temp.Properties[b];
                    }
                }

                lsvIt.SubItems.Add(longStringOfProps);

                lsv_templates.Items.Add(lsvIt);
            }

            lsv_templates.Sort();

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_templates.Sort();

            btn_delete.Enabled = false;
            btn_edit.Enabled = false;
        }

        private void lsv_templates_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(lsv_templates.SelectedIndices.Count == 0 || lsv_templates.SelectedIndices[0] == -1)
            {
                btn_delete.Enabled = false;
                btn_edit.Enabled = false;
                return;
            }

            btn_delete.Enabled = true;
            btn_edit.Enabled = true;
        }

        private void lsv_templates_ColumnClick(object sender, ColumnClickEventArgs e)
        {
            // Determine if clicked column is already the column that is being sorted.
            if (e.Column == lvwColumnSorter.SortColumn)
            {
                // Reverse the current sort direction for this column.
                if (lvwColumnSorter.Order == SortOrder.Ascending)
                {
                    lvwColumnSorter.Order = SortOrder.Descending;
                }
                else
                {
                    lvwColumnSorter.Order = SortOrder.Ascending;
                }
            }
            else
            {
                // Set the column number that is to be sorted; default to ascending.
                lvwColumnSorter.SortColumn = e.Column;
                lvwColumnSorter.Order = SortOrder.Ascending;
            }

            // Perform the sort with these new sort options.
            this.lsv_templates.Sort();
        }
    }
}
