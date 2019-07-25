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
    public partial class FrmManageItems : Form
    {
        private List<Item> m_databaseItems;
        private List<Template> m_databaseTemplates;
        private ListViewColumnSorter lvwColumnSorter;
        private List<ItemPool> m_databaseItemPools;

        public bool wasAnEdit = false;
        public bool deleteHappened = false;

        public FrmManageItems(List<Item> pDataBaseItems, List<Template> pDatabaseTemplates, List<ItemPool> pDatabaseItemPools)
        {
            InitializeComponent();

            lvwColumnSorter = new ListViewColumnSorter();
            this.lsv_items.ListViewItemSorter = lvwColumnSorter;

            lsv_items.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_items.Columns.Add("Name", 200, HorizontalAlignment.Left);
            lsv_items.Columns.Add("Category", 100, HorizontalAlignment.Left);
            lsv_items.Columns.Add("Rarity", 39, HorizontalAlignment.Left);
            lsv_items.Columns.Add("Properties", 120, HorizontalAlignment.Left);

            lsv_items.View = View.Details;
            lsv_items.HideSelection = false;
            lsv_items.FullRowSelect = true;

            lsv_items.Columns[0].Width = 0;
            lsv_items.ColumnWidthChanging += lsv_items_ColumnWidthChanging;

            m_databaseItems = pDataBaseItems;
            m_databaseTemplates = pDatabaseTemplates;
            m_databaseItemPools = pDatabaseItemPools;

            btn_edit.Enabled = false;
            btn_delete.Enabled = false;

            LoadItems();



            this.CenterToScreen();
        }

        private void lsv_items_ColumnWidthChanging(object sender, ColumnWidthChangingEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.NewWidth = 0;
                e.Cancel = true;
            }
        }

        private void btn_edit_Click(object sender, EventArgs e)
        {
            int realIndexItem = int.Parse(lsv_items.Items[lsv_items.SelectedIndices[0]].Text);

            Item item = m_databaseItems[realIndexItem];

            CreateItem frmItem = new CreateItem(m_databaseTemplates, m_databaseItems, item);
            frmItem.itemManager = this;


            frmItem.ShowDialog();

            if(wasAnEdit)
                LoadItems();

            btn_edit.Enabled = false;
            btn_delete.Enabled = false;

            btn_edit.Enabled = false;
            btn_delete.Enabled = false;
        }

        private void btn_delete_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Are you sure you want to delete this Item?", "Confirmation",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
            {
                int indexIndatabase = int.Parse(lsv_items.Items[lsv_items.SelectedIndices[0]].Text);

                foreach (ItemPool pool in m_databaseItemPools)
                {
                    for (int i = 0; i < pool.Items.Count; i++)
                    {
                        if (pool.Items[i].itemIndex == indexIndatabase)
                        {
                            pool.Items.RemoveAt(i);
                            i--;
                        }                      
                    }
                }
                m_databaseItems.RemoveAt(indexIndatabase);
            }

            deleteHappened = true;

            LoadItems();
        }

        private void btn_close_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void lsv_items_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lsv_items.SelectedIndices.Count == 0 || lsv_items.SelectedIndices[0] == -1)
            {
                btn_edit.Enabled = false;
                btn_delete.Enabled = false;
                return;
            }
                

            int itemIndex = int.Parse(lsv_items.Items[lsv_items.SelectedIndices[0]].Text);

            Item realItem = m_databaseItems[itemIndex];

            try
            {
                picbox_thumbnail.Load(realItem.ImagePath);
            }
            catch
            {
                picbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
            }

            btn_edit.Enabled = true;
            btn_delete.Enabled = true;
        }

        private void LoadItems()
        {
            lsv_items.Items.Clear();

            for (int i = 0; i < m_databaseItems.Count; i++)
            {
                Item item = m_databaseItems[i];

                ListViewItem lsvIt = new ListViewItem(i.ToString());
                lsvIt.SubItems.Add(item.Name);
                lsvIt.SubItems.Add(item.Category);
                lsvIt.SubItems.Add(item.Rarity.ToString());

                string longStringOfProps = "";

                for (int b = 0; b < item.Properties.Count; b++)
                {
                    if (b != item.Properties.Count - 1)
                    {
                        longStringOfProps += item.Properties[b].Name.ToString() + ",";
                    }
                    else
                    {
                        longStringOfProps += item.Properties[b].Name.ToString();
                    }
                }

                lsvIt.SubItems.Add(longStringOfProps);

                lsv_items.Items.Add(lsvIt);
            }

            lsv_items.Sort();

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_items.Sort();

            btn_edit.Enabled = false;
            btn_delete.Enabled = false;
        }

        private void FrmManageItems_FormClosing(object sender, FormClosingEventArgs e)
        {

            if(deleteHappened || wasAnEdit)
            {
                StreamWriter objFichier = new StreamWriter(MainMenu.PATH_FILE_ITEMS);

                for (int i = 0; i < m_databaseItems.Count; i++)
                {
                    Item item = m_databaseItems[i];

                    if (i == m_databaseItems.Count - 1)
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

                deleteHappened = false;
                wasAnEdit = false;
            }           
        }

        private void lsv_items_ColumnClick(object sender, ColumnClickEventArgs e)
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
            this.lsv_items.Sort();
        }
    }
}
