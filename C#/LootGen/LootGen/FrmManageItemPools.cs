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
    public partial class FrmManageItemPools : Form
    {
        List<ItemPool> m_databaseItemPools;
        List<Item> m_databaseItems;
        private ListViewColumnSorter lvwColumnSorter;

        public FrmManageItemPools(List<ItemPool> pItemPools, List<Item> pDataBaseItems)
        {
            InitializeComponent();

            lvwColumnSorter = new ListViewColumnSorter();
            this.lsv_itemPools.ListViewItemSorter = lvwColumnSorter;

            lsv_itemPools.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_itemPools.Columns.Add("Name", 320, HorizontalAlignment.Left);
            lsv_itemPools.Columns.Add("Items", 565, HorizontalAlignment.Left);

            lsv_itemPools.View = View.Details;
            lsv_itemPools.HideSelection = false;
            lsv_itemPools.FullRowSelect = true;

            lsv_itemPools.Columns[0].Width = 0;
            lsv_itemPools.ColumnWidthChanging += lsv_itemPools_ColumnWidthChanging;

            m_databaseItemPools = pItemPools;
            m_databaseItems = pDataBaseItems;

            LoadItemPools();

            this.CenterToScreen();
        }

        private void lsv_itemPools_ColumnWidthChanging(object sender, ColumnWidthChangingEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.NewWidth = 0;
                e.Cancel = true;
            }
        }

        private void LoadItemPools()
        {
            lsv_itemPools.Items.Clear();

            for (int i = 0; i < m_databaseItemPools.Count; i++)
            {
                ItemPool itemPool = m_databaseItemPools[i];

                ListViewItem lsvIt = new ListViewItem(i.ToString());
                lsvIt.SubItems.Add(itemPool.Name);

                string longStringOfProps = "";

                for (int b = 0; b < itemPool.Items.Count; b++)
                {
                    try
                    {
                        if (b != itemPool.Items.Count - 1)
                        {
                            longStringOfProps += m_databaseItems[itemPool.Items[b].itemIndex].Name + "(" + itemPool.Items[b].dropPercent + "%)" + ",";
                        }
                        else
                        {
                            longStringOfProps += m_databaseItems[itemPool.Items[b].itemIndex].Name + "(" + itemPool.Items[b].dropPercent + "%)";
                        }
                    }
                    catch
                    {
                        continue;
                    }        
                }

                lsvIt.SubItems.Add(longStringOfProps);

                lsv_itemPools.Items.Add(lsvIt);
            }

            btn_delete.Enabled = false;
            btn_edit.Enabled = false;

            lsv_itemPools.Sort();

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_itemPools.Sort();
        }

        private void btn_delete_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Are you sure you want to delete this Item Pool?", "Confirmation",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
            {
                m_databaseItemPools.RemoveAt(int.Parse(lsv_itemPools.Items[lsv_itemPools.SelectedIndices[0]].Text));
            }

            LoadItemPools();
        }

        private void btn_edit_Click(object sender, EventArgs e)
        {
            int realIndexPool = int.Parse(lsv_itemPools.Items[lsv_itemPools.SelectedIndices[0]].Text);

            ItemPool itemP = m_databaseItemPools[realIndexPool];

            CreateItemPool frmItemPool = new CreateItemPool(m_databaseItems, m_databaseItemPools, itemP);

            frmItemPool.ShowDialog();

            LoadItemPools();
        }

        private void btn_close_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void FrmManageItemPools_FormClosing(object sender, FormClosingEventArgs e)
        {
            StreamWriter objWriter = new StreamWriter(MainMenu.PATH_FILE_ITEMPOOLS);

            foreach (ItemPool itemPool in m_databaseItemPools)
            {
                objWriter.WriteLine(itemPool.ToString());
            }

            objWriter.Close();
        }

        private void lsv_itemPools_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lsv_itemPools.SelectedIndices.Count == 0 || lsv_itemPools.SelectedIndices[0] == -1)
            {
                btn_delete.Enabled = false;
                btn_edit.Enabled = false;
                return;
            }

            btn_delete.Enabled = true;
            btn_edit.Enabled = true;
        }

        private void lsv_itemPools_ColumnClick(object sender, ColumnClickEventArgs e)
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
            this.lsv_itemPools.Sort();
        }
    }
}
