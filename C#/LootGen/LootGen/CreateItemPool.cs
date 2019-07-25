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
    public partial class CreateItemPool : Form
    {
        private List<Item> m_dataBaseItems;
        private List<Item_Chance> m_poolItems;
        private List<ItemPool> m_dataBaseItemPools;
        private ItemPool m_itemPoolEdited;

        private ListViewColumnSorter lvwColumnSorter;
        private ListViewColumnSorter lvwColumnSorter2;

        public CreateItemPool(List<Item> pDataBaseItems, List<ItemPool> pDataBaseItemPools)
        {
            InitializeComponent();

            // Create an instance of a ListView column sorter and assign it 
            // to the ListView control.
            lvwColumnSorter = new ListViewColumnSorter();
            this.lsv_allItems.ListViewItemSorter = lvwColumnSorter;  

            // Create an instance of a ListView column sorter and assign it 
            // to the ListView control.
            lvwColumnSorter2 = new ListViewColumnSorter();
            this.lsv_poolItems.ListViewItemSorter = lvwColumnSorter2;   

            m_dataBaseItems = pDataBaseItems;
            m_dataBaseItemPools = pDataBaseItemPools;
            m_poolItems = new List<Item_Chance>();

            lsv_allItems.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_allItems.Columns.Add("Name", 200, HorizontalAlignment.Left);
            lsv_allItems.Columns.Add("Category", 70, HorizontalAlignment.Left);
            lsv_allItems.Columns.Add("Rarity", 39, HorizontalAlignment.Right);

            lsv_allItems.View = View.Details;
            lsv_allItems.HideSelection = false;
            lsv_allItems.FullRowSelect = true;

            lsv_allItems.Columns[0].Width = 0;
            lsv_allItems.ColumnWidthChanging += lsv_allItems_ColumnWidthChanging;

            RefreshAllItems(m_dataBaseItems, lsv_allItems);

            lsv_poolItems.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_poolItems.Columns.Add("Name", 170, HorizontalAlignment.Left);
            lsv_poolItems.Columns.Add("Category", 55, HorizontalAlignment.Left);
            lsv_poolItems.Columns.Add("Rarity", 39, HorizontalAlignment.Right);
            lsv_poolItems.Columns.Add("Drop (%)", 60, HorizontalAlignment.Right);

            //lsv_poolItems.Columns.Add("ID", -2, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Name", -1, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Category", -2, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Rarity", -2, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Drop Chance", -2, HorizontalAlignment.Left);

            lsv_poolItems.View = View.Details;
            lsv_poolItems.HideSelection = false;
            lsv_poolItems.FullRowSelect = true;

            lsv_poolItems.Columns[0].Width = 0;
            lsv_poolItems.ColumnWidthChanging += lsv_poolItems_ColumnWidthChanging;

            btn_modifyDropChance.Enabled = false;
            btn_itemAdd.Enabled = false;
            btn_removeItem.Enabled = false;

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_allItems.Sort();

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter2.SortColumn = 1;
            lvwColumnSorter2.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_poolItems.Sort();

            this.CenterToScreen();
        }

        public CreateItemPool(List<Item> pDataBaseItems, List<ItemPool> pDataBaseItemPools, ItemPool pItemPool)
        {
            InitializeComponent();

            // Create an instance of a ListView column sorter and assign it 
            // to the ListView control.
            lvwColumnSorter = new ListViewColumnSorter();
            this.lsv_allItems.ListViewItemSorter = lvwColumnSorter;

            // Create an instance of a ListView column sorter and assign it 
            // to the ListView control.
            lvwColumnSorter2 = new ListViewColumnSorter();
            this.lsv_poolItems.ListViewItemSorter = lvwColumnSorter2;

            m_dataBaseItems = pDataBaseItems;
            m_dataBaseItemPools = pDataBaseItemPools;
            m_poolItems = new List<Item_Chance>();

            lsv_allItems.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_allItems.Columns.Add("Name", 200, HorizontalAlignment.Left);
            lsv_allItems.Columns.Add("Category", 70, HorizontalAlignment.Left);
            lsv_allItems.Columns.Add("Rarity", 39, HorizontalAlignment.Right);

            lsv_allItems.View = View.Details;
            lsv_allItems.HideSelection = false;
            lsv_allItems.FullRowSelect = true;

            lsv_allItems.Columns[0].Width = 0;
            lsv_allItems.ColumnWidthChanging += lsv_allItems_ColumnWidthChanging;

            RefreshAllItems(m_dataBaseItems, lsv_allItems);

            lsv_poolItems.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_poolItems.Columns.Add("Name", 170, HorizontalAlignment.Left);
            lsv_poolItems.Columns.Add("Category", 55, HorizontalAlignment.Left);
            lsv_poolItems.Columns.Add("Rarity", 39, HorizontalAlignment.Right);
            lsv_poolItems.Columns.Add("Drop (%)", 60, HorizontalAlignment.Right);

            //lsv_poolItems.Columns.Add("ID", -2, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Name", -1, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Category", -2, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Rarity", -2, HorizontalAlignment.Left);
            //lsv_poolItems.Columns.Add("Drop Chance", -2, HorizontalAlignment.Left);

            lsv_poolItems.View = View.Details;
            lsv_poolItems.HideSelection = false;
            lsv_poolItems.FullRowSelect = true;

            lsv_poolItems.Columns[0].Width = 0;
            lsv_poolItems.ColumnWidthChanging += lsv_poolItems_ColumnWidthChanging;

            btn_modifyDropChance.Enabled = false;
            btn_itemAdd.Enabled = false;
            btn_removeItem.Enabled = false;

            m_itemPoolEdited = pItemPool;

            btn_create.Text = "Apply Changes";
            this.Text = "Edit an Item Pool";

            txt_itemPoolName.Text = pItemPool.Name;

            m_poolItems = pItemPool.Items;

            RefreshAllItems(m_dataBaseItems, lsv_allItems);
            RefreshPoolItems();

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_allItems.Sort();

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter2.SortColumn = 1;
            lvwColumnSorter2.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_poolItems.Sort();

            this.CenterToScreen();
        }

        private void btn_cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void RefreshAllItems(List<Item> source, ListView listView)
        {
            listView.Items.Clear();

            for (int i = 0; i < source.Count; i++)
            {
                Item item = source[i];

                ListViewItem itemToAdd = new ListViewItem(i.ToString());
                itemToAdd.SubItems.Add(item.Name);
                itemToAdd.SubItems.Add(item.Category);
                itemToAdd.SubItems.Add(item.Rarity.ToString());

                listView.Items.Add(itemToAdd);
            }

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_allItems.Sort();

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter2.SortColumn = 1;
            lvwColumnSorter2.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_poolItems.Sort();
        }

        private void lsv_allItems_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lsv_allItems.SelectedIndices.Count == 0 || lsv_allItems.SelectedIndices[0] == -1)
            {
                picbox_thumbnail.Image.Dispose();
                picbox_thumbnail.Image = null;
                btn_itemAdd.Enabled = false;
                return;
            }

            lsv_poolItems.SelectedIndices.Clear();

            int itemRealIndex = int.Parse(lsv_allItems.Items[lsv_allItems.SelectedIndices[0]].Text);

            Item item = m_dataBaseItems[itemRealIndex];

            try
            {
                picbox_thumbnail.Load(item.ImagePath);
            }
            catch
            {
                picbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
            }
            
            btn_itemAdd.Enabled = true;
        }

        private void lsv_allItems_ColumnWidthChanging(object sender, ColumnWidthChangingEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.NewWidth = 0;
                e.Cancel = true;
            }
        }

        private void lsv_poolItems_ColumnWidthChanging(object sender, ColumnWidthChangingEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.NewWidth = 0;
                e.Cancel = true;
            }
        }

        private void btn_itemAdd_Click(object sender, EventArgs e)
        {
            if (lsv_allItems.SelectedIndices.Count == 0 || lsv_allItems.SelectedIndices[0] == -1)
            {
                MessageBox.Show("Please select an item from the list of available items, on the left.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (num_dropChance.Value <= 0 || num_dropChance.Value > 100)
            {
                MessageBox.Show("You cannot add an item with 0% drop chance or more than 100% drop chance to the pool. Please enter a valid drop chance (between 0 and 100).", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }



            int itemRealIndex = int.Parse(lsv_allItems.Items[lsv_allItems.SelectedIndices[0]].Text);

            decimal dropChance = num_dropChance.Value;

            Item_Chance newItemChance = new Item_Chance(itemRealIndex, dropChance);

            m_poolItems.Add(newItemChance);

            RefreshPoolItems();

            lsv_allItems.SelectedIndices.Clear();
        }

        private void btn_create_Click(object sender, EventArgs e)
        {
            decimal totalDrop = 0;

            foreach (Item_Chance item in m_poolItems)
            {
                totalDrop += item.dropPercent;
            }

            if(totalDrop != 100)
            {
                MessageBox.Show("You cannot have a cummulation of drop chances of items in your pool other than 100%. Please verify that the sum of all your item's drop chances in your pool equals 100%.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if(txt_itemPoolName.Text.Equals(string.Empty))
            {
                MessageBox.Show("Please enter a name for your Item Pool.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string itemPoolName = txt_itemPoolName.Text;

            ItemPool newPool = new ItemPool(itemPoolName, m_poolItems);

            if(m_itemPoolEdited == null)
            {
                m_dataBaseItemPools.Add(newPool);
            }
            else
            {
                m_itemPoolEdited.Name = newPool.Name;
                m_itemPoolEdited.Items = newPool.Items;
            }

            SaveItemPools();

            this.Close();
        }

        private void RefreshPoolItems()
        {
            lsv_poolItems.Items.Clear();

            decimal totalPercent = 0;

            for (int i = 0; i < m_poolItems.Count; i++)
            {
                Item item = m_dataBaseItems[m_poolItems[i].itemIndex];

                ListViewItem itemToAdd = new ListViewItem(i.ToString());
                itemToAdd.SubItems.Add(item.Name);
                itemToAdd.SubItems.Add(item.Category);
                itemToAdd.SubItems.Add(item.Rarity.ToString());
                itemToAdd.SubItems.Add(m_poolItems[i].dropPercent.ToString() + "%");

                lsv_poolItems.Items.Add(itemToAdd);

                totalPercent += m_poolItems[i].dropPercent;
            }

            lbl_totalChance.Text = "Total chances: " + totalPercent.ToString() + "%";

            lsv_poolItems.Sort();
        }

        private void btn_modifyDropChance_Click(object sender, EventArgs e)
        {
            m_poolItems[int.Parse(lsv_poolItems.Items[lsv_poolItems.SelectedIndices[0]].Text)].dropPercent = num_dropChance.Value;
            lsv_poolItems.Items[lsv_poolItems.SelectedIndices[0]].SubItems[4].Text = num_dropChance.Value.ToString() + "%";
        }

        private void lsv_poolItems_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lsv_poolItems.SelectedIndices.Count == 0 || lsv_poolItems.SelectedIndices[0] == -1)
            {
                picbox_thumbnail.Image.Dispose();
                picbox_thumbnail.Image = null;
                btn_modifyDropChance.Enabled = false;
                btn_removeItem.Enabled = false;
                return;
            }

            lsv_allItems.SelectedIndices.Clear();

            int itemPoolIndex = int.Parse(lsv_poolItems.Items[lsv_poolItems.SelectedIndices[0]].Text);

            int itemRealIndex = m_poolItems[itemPoolIndex].itemIndex;

            Item item = m_dataBaseItems[itemRealIndex];

            try
            {
                picbox_thumbnail.Load(item.ImagePath);
            }

            catch
            {
                picbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
            }
            

            num_dropChance.Value = m_poolItems[itemPoolIndex].dropPercent;

            btn_modifyDropChance.Enabled = true;
            btn_removeItem.Enabled = true;
        }

        private void btn_removeItem_Click(object sender, EventArgs e)
        {
            if (lsv_poolItems.SelectedIndices.Count == 0 || lsv_poolItems.SelectedIndices[0] == -1)
            {
                MessageBox.Show("Please select an item from the list of pool items, on the right.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            int indexInPool = int.Parse(lsv_poolItems.Items[lsv_poolItems.SelectedIndices[0]].Text);

            m_poolItems.RemoveAt(indexInPool);

            RefreshPoolItems();

            btn_modifyDropChance.Enabled = false;
            btn_removeItem.Enabled = false;
            picbox_thumbnail.Image.Dispose();
            picbox_thumbnail.Image = null;
        }

        private void SaveItemPools()
        {
            StreamWriter objWriter = new StreamWriter(MainMenu.PATH_FILE_ITEMPOOLS);

            foreach (ItemPool itemPool in m_dataBaseItemPools)
            {
                objWriter.WriteLine(itemPool.ToString());
            }

            objWriter.Close();
        }

        private void lsv_allItems_ColumnClick(object sender, ColumnClickEventArgs e)
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
            this.lsv_allItems.Sort();
        }

        private void lsv_poolItems_ColumnClick(object sender, ColumnClickEventArgs e)
        {
            // Determine if clicked column is already the column that is being sorted.
            if (e.Column == lvwColumnSorter2.SortColumn)
            {
                // Reverse the current sort direction for this column.
                if (lvwColumnSorter2.Order == SortOrder.Ascending)
                {
                    lvwColumnSorter2.Order = SortOrder.Descending;
                }
                else
                {
                    lvwColumnSorter2.Order = SortOrder.Ascending;
                }
            }
            else
            {
                // Set the column number that is to be sorted; default to ascending.
                lvwColumnSorter2.SortColumn = e.Column;
                lvwColumnSorter2.Order = SortOrder.Ascending;
            }

            // Perform the sort with these new sort options.
            this.lsv_poolItems.Sort();
        }
    }
}
