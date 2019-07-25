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
using Troschuetz.Random;

namespace LootGen
{
    public partial class MainMenu : Form
    {
        /* ABOUT FILES
         * Alright, heres how the files are separated.
         * *|* Is a big enumeration. Like if you were saving an Item *|* Template *|* House.
         * *** Is a smaller enumeration. It is used to separate inner properties. For example, in an Item, you have Name *** Cost.
         * *,* Is the smallest enumeration. It is used to separate stuff inside an inner property. For example, in an Item, you could have magical properties, and then those
         * are listed as Fire *,* Lightning. Occasionnally, it is used to enumerate multiple inner properties (That have their own separation).
         * */

        public static int SEED_VALUE_GLOBAL;

        private List<Template> m_lstTemplates;
        private List<Item> m_lstItems;
        private List<ItemPool> m_lstItemPools;
        private List<Item> m_lstLootItems;
        private ListViewColumnSorter lvwColumnSorter;

        public const string PATH_FILE_TEMPLATES = "Ressources\\Templates.txt";
        public const string PATH_FILE_ITEMS = "Ressources\\Items.txt";
        public const string PATH_FILE_ITEMPOOLS = "Ressources\\ItemPools.txt";
        public const string PATH_FOLDER_RARITYIMAGES = "Ressources\\Pictures\\Rarity\\";
        public const string PATH_UNKNOWN_IMAGE = "Ressources\\Pictures\\unknown.png";

        #region Constructor

        public MainMenu()
        {
            InitializeComponent();

            // Create an instance of a ListView column sorter and assign it 
            // to the ListView control.
            lvwColumnSorter = new ListViewColumnSorter();
            this.lsv_lootView.ListViewItemSorter = lvwColumnSorter;

            try
            {
                LoadTemplates();
            }
            catch
            {
                if (MessageBox.Show("Error while loading Templates database. File may be corrupted. Would you like to create a new Templates database? (You will not be using any of your stored Templates. They will not be lost either, just not used anymore.)", "Error",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Error) == DialogResult.Yes)
                {
                    try
                    {
                        System.IO.File.Move(PATH_FILE_TEMPLATES, "Ressources\\OldTemplates.txt");
                    }
                    catch
                    {
                        if (MessageBox.Show("Could not save your old Templates. They will be overwritten (lost). If this is the first time you are running this application, disregard this message. Continue?", "Error",
                            MessageBoxButtons.YesNo, MessageBoxIcon.Error) == DialogResult.No)
                        {
                            Environment.Exit(0);
                            return;
                        }
                    }

                    StreamWriter objWriter = new StreamWriter(PATH_FILE_TEMPLATES);

                    objWriter.WriteLine("");

                    objWriter.Close();

                    LoadTemplates();
                }
                else
                {
                    Environment.Exit(0);
                    return;
                }
            }

            try
            {
                LoadItemsDataBase();
            }
            catch
            {
                if (MessageBox.Show("Error while loading Items database. File may be corrupted. Would you like to create a new Items database? (You will not be using any of your stored Items. They will not be lost either, just not used anymore.)", "Error",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Error) == DialogResult.Yes)
                {
                    try
                    {
                        System.IO.File.Move(PATH_FILE_ITEMS, "Ressources\\OldItems.txt");
                    }
                    catch
                    {
                        if (MessageBox.Show("Could not save your old Items. They will be overwritten (lost). If this is the first time you are running this application, disregard this message. Continue?", "Error",
                            MessageBoxButtons.YesNo, MessageBoxIcon.Error) == DialogResult.No)
                        {
                            Environment.Exit(0);
                            return;
                        }
                    }

                    StreamWriter objWriter = new StreamWriter(PATH_FILE_ITEMS);

                    objWriter.WriteLine("");

                    objWriter.Close();

                    LoadItemsDataBase();
                }
                else
                {
                    Environment.Exit(0);
                    return;
                }
            }

            try
            {
                LoadItemPools();
            }
            catch
            {
                if (MessageBox.Show("Error while loading Item Pools database. File may be corrupted. Would you like to create a new Item Pools database? (You will not be using any of your stored Item Pools. They will not be lost either, just not used anymore.)", "Error",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Error) == DialogResult.Yes)
                {
                    try
                    {
                        System.IO.File.Move(PATH_FILE_ITEMPOOLS, "Ressources\\OldItemPools.txt");
                    }
                    catch
                    {
                        if (MessageBox.Show("Could not save your old Item Pools. They will be overwritten (lost). If this is the first time you are running this application, disregard this message. Continue?", "Error",
                            MessageBoxButtons.YesNo, MessageBoxIcon.Error) == DialogResult.No)
                        {
                            Environment.Exit(0);
                            return;
                        }
                    }

                    StreamWriter objWriter = new StreamWriter(PATH_FILE_ITEMPOOLS);

                    objWriter.WriteLine("");

                    objWriter.Close();

                    LoadItemPools();
                }
                else
                {
                    Environment.Exit(0);
                    return;
                }
            }

            cbo_itemPool1.Items.Add("None");
            cbo_itemPool2.Items.Add("None");
            cbo_itemPool3.Items.Add("None");

            foreach (ItemPool pool in m_lstItemPools)
            {
                cbo_itemPool1.Items.Add(pool.Name);
                cbo_itemPool2.Items.Add(pool.Name);
                cbo_itemPool3.Items.Add(pool.Name);
            }

            cbo_itemPool1.SelectedIndex = 0;
            cbo_itemPool2.SelectedIndex = 0;
            cbo_itemPool3.SelectedIndex = 0;

            cbo_dropChanceMod.Items.Add("0.1X");
            cbo_dropChanceMod.Items.Add("0.2X");
            cbo_dropChanceMod.Items.Add("0.5X");
            cbo_dropChanceMod.Items.Add("Normal");
            cbo_dropChanceMod.Items.Add("1.1X");
            cbo_dropChanceMod.Items.Add("1.2X");
            cbo_dropChanceMod.Items.Add("1.5X");
            cbo_dropChanceMod.Items.Add("2X");
            cbo_dropChanceMod.Items.Add("3X");

            // Select 1X
            cbo_dropChanceMod.SelectedIndex = 3;

            btn_generateLoot.Enabled = false;
            btn_inspect.Enabled = false;

            lsv_lootView.Columns.Add("ID", 0, HorizontalAlignment.Left);
            lsv_lootView.Columns.Add("Name", 330, HorizontalAlignment.Left);
            lsv_lootView.Columns.Add("Category", 110, HorizontalAlignment.Left);
            lsv_lootView.Columns.Add("Rarity", 39, HorizontalAlignment.Right);
            lsv_lootView.Columns.Add("Qty", 30, HorizontalAlignment.Right);

            lsv_lootView.Columns[0].Width = 0;
            lsv_lootView.ColumnWidthChanging += lsv_lootView_ColumnWidthChanging;

            lsv_lootView.View = View.Details;
            lsv_lootView.HideSelection = false;
            lsv_lootView.FullRowSelect = true;

            m_lstLootItems = new List<Item>();

            SEED_VALUE_GLOBAL = -1;

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_lootView.Sort();

            this.CenterToScreen();
        }
        #endregion

        private void MainMenu_Load(object sender, EventArgs e)
        {

        }

        private void ajouterToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void lbl_item_Click(object sender, EventArgs e)
        {

        }

        private void mnu_create_template_Click(object sender, EventArgs e)
        {
            CreateTemplate frmTemplate = new CreateTemplate(m_lstTemplates);
            frmTemplate.ShowDialog();
        }

        private void mnu_create_item_Click(object sender, EventArgs e)
        {
            CreateItem frmItem = new CreateItem(m_lstTemplates, m_lstItems);

            frmItem.ShowDialog();
        }

        private void mnu_create_itemPool_Click(object sender, EventArgs e)
        {
            CreateItemPool frmItemPool = new CreateItemPool(m_lstItems, m_lstItemPools);

            frmItemPool.ShowDialog();

            ManageItemPools();
        }

        private void LoadTemplates()
        {
            m_lstTemplates = new List<Template>();

            StreamReader objReader = new StreamReader(PATH_FILE_TEMPLATES);

            while (!objReader.EndOfStream)
            {
                string[] content = objReader.ReadLine().Split(new string[] { "***" }, StringSplitOptions.None);

                if (content.Length == 0 || content.Length == 1)
                    break;
                
                string name = content[0];

                List<string> lstProp = new List<string>();
                string[] tab_lstString = content[1].Split(new string[] { "*,*" }, StringSplitOptions.None);
                foreach (string prop in tab_lstString)
                {
                    lstProp.Add(prop);
                }

                Template temp = new Template(name, lstProp);

                m_lstTemplates.Add(temp);
            }

            objReader.Close();
        }

        private void LoadItemsDataBase()
        {
            m_lstItems = new List<Item>();

            StreamReader objReader = new StreamReader(PATH_FILE_ITEMS);

            //while (!objReader.EndOfStream)
            //{
            //    // Getting the whole item.
            //    string[] tabContent = objReader.ReadLine().Split(new string[] { "*|*" }, StringSplitOptions.None);

            //    if (tabContent.Length != 6)
            //        continue;
            //    // Getting the item's name.
            //    string itemName = tabContent[0];

            //    // Getting the item's template.
            //    string[] tabTempContent = tabContent[1].Split(new string[] { "***" }, StringSplitOptions.None);

            //    string tempName = tabTempContent[0];

            //    List<string> lstTempPropNames = new List<string>();
            //    string[] tabTempPropNames = tabTempContent[1].Split(new string[] { "*,*" }, StringSplitOptions.None);
            //    foreach (string prop in tabTempPropNames)
            //    {
            //        lstTempPropNames.Add(prop);
            //    }

            //    Template itemTemp = new Template(tempName, lstTempPropNames);

            //    // Getting the item's Rarity

            //    Rarity itemRarity;

            //    switch (tabContent[2].ToString())
            //    {
            //        case "F":
            //            itemRarity = Rarity.F;
            //            break;
            //        case "E":
            //            itemRarity = Rarity.E;
            //            break;
            //        case "D":
            //            itemRarity = Rarity.D;
            //            break;
            //        case "C":
            //            itemRarity = Rarity.C;
            //            break;
            //        case "B":
            //            itemRarity = Rarity.B;
            //            break;
            //        case "A":
            //            itemRarity = Rarity.A;
            //            break;
            //        case "S":
            //            itemRarity = Rarity.S;
            //            break;
            //        case "SS":
            //            itemRarity = Rarity.SS;
            //            break;
            //        case "SSS":
            //            itemRarity = Rarity.SSS;
            //            break;
            //        default:
            //            itemRarity = Rarity.F;
            //            break;
            //    }

            //    // Getting the item's properties and values
            //    string[] tabItemPropertiesContent = tabContent[3].Split(new string[] { "*,*" }, StringSplitOptions.None);

            //    List<Property> itemPropertiesList = new List<Property>();
            //    foreach (string property in tabItemPropertiesContent)
            //    {
            //        string[] tabPropertyContent = property.Split(new string[] { "***" }, StringSplitOptions.None);

            //        if (tabPropertyContent.Length != 2)
            //            continue;
            //        Property prop = new Property(tabPropertyContent[0], tabPropertyContent[1]);
            //        itemPropertiesList.Add(prop);
            //    }

            //    // Getting the item's image path

            //    string itemImagePath = tabContent[4];

            //    // Getting the item's category

            //    string itemCategory = tabContent[5];

            //    // Reforming the item

            //    Item itemRead = new Item(itemName, itemTemp, itemRarity, itemPropertiesList, itemImagePath, itemCategory);

            //    m_lstItems.Add(itemRead);
            //}

            // Getting the whole item.
            string allitemsString = objReader.ReadToEnd();

            string[] tabAllItems = allitemsString.Split(new string[] { "*&*" }, StringSplitOptions.None);

            for (int i = 0; i < tabAllItems.Length; i++)
            {
                string[] tabContent = tabAllItems[i].Split(new string[] { "*|*" }, StringSplitOptions.None);

                if (tabContent.Length != 6)
                    continue;
                // Getting the item's name.
                string itemName = tabContent[0];

                // Getting the item's template.
                string[] tabTempContent = tabContent[1].Split(new string[] { "***" }, StringSplitOptions.None);

                string tempName = tabTempContent[0];

                List<string> lstTempPropNames = new List<string>();
                string[] tabTempPropNames = tabTempContent[1].Split(new string[] { "*,*" }, StringSplitOptions.None);
                foreach (string prop in tabTempPropNames)
                {
                    lstTempPropNames.Add(prop);
                }

                Template itemTemp = new Template(tempName, lstTempPropNames);

                // Getting the item's Rarity

                Rarity itemRarity;

                switch (tabContent[2].ToString())
                {
                    case "F":
                        itemRarity = Rarity.F;
                        break;
                    case "E":
                        itemRarity = Rarity.E;
                        break;
                    case "D":
                        itemRarity = Rarity.D;
                        break;
                    case "C":
                        itemRarity = Rarity.C;
                        break;
                    case "B":
                        itemRarity = Rarity.B;
                        break;
                    case "A":
                        itemRarity = Rarity.A;
                        break;
                    case "S":
                        itemRarity = Rarity.S;
                        break;
                    case "SS":
                        itemRarity = Rarity.SS;
                        break;
                    case "SSS":
                        itemRarity = Rarity.SSS;
                        break;
                    default:
                        itemRarity = Rarity.F;
                        break;
                }

                // Getting the item's properties and values
                string[] tabItemPropertiesContent = tabContent[3].Split(new string[] { "*,*" }, StringSplitOptions.None);

                List<Property> itemPropertiesList = new List<Property>();
                foreach (string property in tabItemPropertiesContent)
                {
                    string[] tabPropertyContent = property.Split(new string[] { "***" }, StringSplitOptions.None);

                    if (tabPropertyContent.Length != 2)
                        continue;
                    Property prop = new Property(tabPropertyContent[0], tabPropertyContent[1]);
                    itemPropertiesList.Add(prop);
                }

                // Getting the item's image path

                string itemImagePath = tabContent[4];

                // Getting the item's category

                string itemCategory = tabContent[5];

                // Reforming the item

                Item itemRead = new Item(itemName, itemTemp, itemRarity, itemPropertiesList, itemImagePath, itemCategory);

                m_lstItems.Add(itemRead);
            }           

            objReader.Close();
        }

        private void LoadItemPools()
        {
            m_lstItemPools = new List<ItemPool>();

            StreamReader objReader = new StreamReader(PATH_FILE_ITEMPOOLS);

            while(!objReader.EndOfStream)
            {
                string[] tabContent = objReader.ReadLine().Split(new string[] { "*|*" }, StringSplitOptions.None);

                if (tabContent.Length != 2)
                    continue;

                string name = tabContent[0];

                List<Item_Chance> lstItems = new List<Item_Chance>();

                string[] allItems = tabContent[1].Split(new string[] { "*,*" }, StringSplitOptions.None);

                for (int i = 0; i < allItems.Length; i++)
                {
                    string[] itemContent = allItems[i].Split(new string[] { "***" }, StringSplitOptions.None);

                    if (itemContent.Length != 2)
                        continue;

                    int itemIndex = int.Parse(itemContent[0]);

                    decimal dropPercent = decimal.Parse(itemContent[1]);

                    Item_Chance newItemChance = new Item_Chance(itemIndex, dropPercent);

                    lstItems.Add(newItemChance);
                }

                ItemPool pool = new ItemPool(name, lstItems);

                m_lstItemPools.Add(pool);
            }

            objReader.Close();
        }

        // cbo_itemPool1
        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            ManageGenerateLootButton();
        }

        private void cbo_itemPool2_SelectedIndexChanged(object sender, EventArgs e)
        {
            ManageGenerateLootButton();
        }

        private void cbo_itemPool3_SelectedIndexChanged(object sender, EventArgs e)
        {
            ManageGenerateLootButton();
        }

        private void ManageGenerateLootButton()
        {
            if ((cbo_itemPool1.SelectedIndex == -1 && cbo_itemPool2.SelectedIndex == -1 && cbo_itemPool3.SelectedIndex == -1) ||
               (cbo_itemPool1.SelectedIndex == 0 && cbo_itemPool2.SelectedIndex == 0 && cbo_itemPool3.SelectedIndex == 0))
                btn_generateLoot.Enabled = false;
            else
                btn_generateLoot.Enabled = true;
        }

        private void RefreshLootItems()
        {
            lsv_lootView.Items.Clear();

            //for (int i = 0; i < m_poolItems.Count; i++)
            //{
            //    Item item = m_dataBaseItems[m_poolItems[i].itemIndex];

            //    ListViewItem itemToAdd = new ListViewItem(i.ToString());
            //    itemToAdd.SubItems.Add(item.Name);
            //    itemToAdd.SubItems.Add(item.Category);
            //    itemToAdd.SubItems.Add(item.Rarity.ToString());
            //    itemToAdd.SubItems.Add(m_poolItems[i].dropPercent.ToString() + "%");

            //    lsv_lootView.Items.Add(itemToAdd);
            //}
        }

        private void chk_instantLoot_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void btn_generateLoot_Click(object sender, EventArgs e)
        {
            btn_exportLoot.Enabled = true;

            lsv_lootView.Items.Clear();
            m_lstLootItems.Clear();

            decimal totalDrop = 0;

            totalDrop += num_itemPool1Chance.Value;
            totalDrop += num_itemPool2Chance.Value;
            totalDrop += num_itemPool3Chance.Value;

            if(totalDrop != 100)
            {
                MessageBox.Show("The sum of the Item Pools Chances must be 100%. Please readjust the Chances for Item Pools.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                num_itemPool1Chance.Focus();

                num_itemPool1Chance.BackColor = Color.Yellow;
                num_itemPool2Chance.BackColor = Color.Yellow;
                num_itemPool3Chance.BackColor = Color.Yellow;
                return;
            }

            num_itemPool1Chance.BackColor = Color.White;
            num_itemPool2Chance.BackColor = Color.White;
            num_itemPool3Chance.BackColor = Color.White;

            if(chk_instantLoot.Checked)
            {
                GenerateLootInstant();
            }
            else
            {
                AnimatedLoot();
                //for (int itemCount = 0; itemCount < num_nbrOfItems.Value; itemCount++)
                //{                   
                    //Delayed(2000, () => GenerateASingleItem(rand));
                    //Delayed(2000, () => GenerateASingleItem(rand));
                    //Delayed(2000, () => GenerateASingleItem(rand));
                //}

            }

            // Set the column number that is to be sorted; default to ascending.
            lvwColumnSorter.SortColumn = 1;
            lvwColumnSorter.Order = SortOrder.Ascending;

            // Perform the sort with these new sort options.
            this.lsv_lootView.Sort();

            btn_inspect.Enabled = false;
        }

        private async void AnimatedLoot()
        {
            MT19937Generator rand;

            if (SEED_VALUE_GLOBAL != -1)
                rand = new MT19937Generator(SEED_VALUE_GLOBAL);
            else
                rand = new MT19937Generator();

            lbl_processing.Visible = false;

            for (int itemCount = 0; itemCount < num_nbrOfItems.Value; itemCount++)
            {
                GenerateASingleItem(rand);

                if(itemCount != num_nbrOfItems.Value - 1)
                    await Task.Delay(1000);
            }

            lbl_processing.Visible = true;
        }

        #region Generate Loot

        private void GenerateLoot()
        {

            // Formating chances...
            int chancePool1 = (int)num_itemPool1Chance.Value;
            int chancePool2 = chancePool1 + (int)num_itemPool2Chance.Value;
            int chancePool3 = chancePool2 + (int)num_itemPool3Chance.Value;

            if (num_itemPool1Chance.Value == 0)
                chancePool1 = 0;
            if (num_itemPool2Chance.Value == 0)
                chancePool2 = 0;
            if (num_itemPool3Chance.Value == 0)
                chancePool3 = 0;

            MT19937Generator rand;

            if (SEED_VALUE_GLOBAL != -1)
                rand = new MT19937Generator(SEED_VALUE_GLOBAL);
            else
                rand = new MT19937Generator();
            

            // For each item... (in the Number of items chosen by user)
            for (int itemCount = 0; itemCount < num_nbrOfItems.Value; itemCount++)
            {
                int poolIndex;

                // Choosing an item pool. Each pool has its chance.
                int poolRoll = rand.Next(1, 101);

                int numberPool = -1;

                if (poolRoll <= chancePool1)
                {
                    poolIndex = cbo_itemPool1.SelectedIndex;
                    numberPool = 1;
                }
                else if (poolRoll > chancePool1 && poolRoll <= chancePool2)
                {
                    poolIndex = cbo_itemPool2.SelectedIndex;
                    numberPool = 2;
                }
                else
                {
                    poolIndex = cbo_itemPool3.SelectedIndex;
                    numberPool = 3;
                }

                // Setting the pool.
                ItemPool poolDrawn = m_lstItemPools[poolIndex - 1];

                // Formatting item drop chances...
                List<int> dropTable = new List<int>();

                int precedentDropChance = 0;

                bool equalizeDropChances = false;

                if (numberPool == 1)
                {
                    if (chk_equaPool1.Checked)
                    {
                        equalizeDropChances = true;
                    }
                }
                if (numberPool == 2)
                {
                    if (chk_equaPool2.Checked)
                    {
                        equalizeDropChances = true;
                    }
                }
                if (numberPool == 3)
                {
                    if (chk_equaPool3.Checked)
                    {
                        equalizeDropChances = true;
                    }
                }

                if (equalizeDropChances)
                {
                    foreach (Item_Chance item in poolDrawn.Items)
                    {
                        dropTable.Add(1 + precedentDropChance);
                        precedentDropChance += 1;
                    }
                }
                else
                {
                    foreach (Item_Chance item in poolDrawn.Items)
                    {
                        dropTable.Add((int)item.dropPercent + precedentDropChance);
                        precedentDropChance += (int)item.dropPercent;
                    }
                }

                int itemRoll;

                if (equalizeDropChances)
                {
                    itemRoll = rand.Next(1, poolDrawn.Items.Count + 1);
                }
                else
                {
                    itemRoll = rand.Next(1, 101);
                }

                int itemIndexDrawn = 0;

                for (int i = 0; i < dropTable.Count; i++)
                {
                    if(itemRoll <= dropTable[i])
                    {
                        itemIndexDrawn = i;
                        break;
                    }
                }

                Item itemDrawn = m_lstItems[poolDrawn.Items[itemIndexDrawn].itemIndex];

                txt_itemName.Text = itemDrawn.Name;

                try
                {
                    picbox_thumbnail.Load(itemDrawn.ImagePath);
                }
                catch
                {
                    picbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
                }
                
                int itemDatabaseIndex = poolDrawn.Items[itemIndexDrawn].itemIndex;

                LoadRarity(itemDrawn.Rarity);

                bool itemExists = false;
                foreach (Item item in m_lstLootItems)
                {
                    if(item.Name.Equals(itemDrawn.Name))
                    {
                        foreach (ListViewItem lsvItem in lsv_lootView.Items)
                        {
                            if (lsvItem.SubItems[1].Text.Equals(item.Name))
                            {
                                lsvItem.SubItems[4].Text = (int.Parse(lsvItem.SubItems[4].Text) + 1).ToString();
                                itemExists = true;
                            }
                        }
                    }
                }

                if(!itemExists)
                {
                    ListViewItem itemToAdd = new ListViewItem(itemDatabaseIndex.ToString());
                    itemToAdd.SubItems.Add(itemDrawn.Name);
                    itemToAdd.SubItems.Add(itemDrawn.Category);
                    itemToAdd.SubItems.Add(itemDrawn.Rarity.ToString());
                    itemToAdd.SubItems.Add("1");

                    lsv_lootView.Items.Add(itemToAdd);

                    m_lstLootItems.Add(itemDrawn);
                }

                //System.Threading.Thread.Sleep(1000);
            }
        }

#endregion

        #region Instant Loot
        
        private void GenerateLootInstant()
        {
             // Formating chances...
            int chancePool1 = (int)num_itemPool1Chance.Value;
            int chancePool2 = chancePool1 + (int)num_itemPool2Chance.Value;
            int chancePool3 = chancePool2 + (int)num_itemPool3Chance.Value;

            MT19937Generator rand;

            if (num_itemPool1Chance.Value == 0)
                chancePool1 = 0;
            if (num_itemPool2Chance.Value == 0)
                chancePool2 = 0;
            if (num_itemPool3Chance.Value == 0)
                chancePool3 = 0;

            if (SEED_VALUE_GLOBAL != -1)
                rand = new MT19937Generator(SEED_VALUE_GLOBAL);
            else
                rand = new MT19937Generator();
            

            // For each item... (in the Number of items chosen by user)
            for (int itemCount = 0; itemCount < num_nbrOfItems.Value; itemCount++)
            {
                int poolIndex;

                // Choosing an item pool. Each pool has its chance.
                int poolRoll = rand.Next(1, 101);

                int numberPool = -1;

                if (poolRoll <= chancePool1)
                {
                    poolIndex = cbo_itemPool1.SelectedIndex;
                    numberPool = 1;
                }
                else if (poolRoll > chancePool1 && poolRoll <= chancePool2)
                {
                    poolIndex = cbo_itemPool2.SelectedIndex;
                    numberPool = 2;
                }
                else
                {
                    poolIndex = cbo_itemPool3.SelectedIndex;
                    numberPool = 3;
                }

                // Setting the pool.
                ItemPool poolDrawn = m_lstItemPools[poolIndex - 1];

                // Formatting item drop chances...
                List<int> dropTable = new List<int>();

                int precedentDropChance = 0;

                bool equalizeDropChances = false;

                if (numberPool == 1)
                {
                    if (chk_equaPool1.Checked)
                    {
                        equalizeDropChances = true;
                    }
                }
                if (numberPool == 2)
                {
                    if (chk_equaPool2.Checked)
                    {
                        equalizeDropChances = true;
                    }
                }
                if (numberPool == 3)
                {
                    if (chk_equaPool3.Checked)
                    {
                        equalizeDropChances = true;
                    }
                }

                if (equalizeDropChances)
                {
                    foreach (Item_Chance item in poolDrawn.Items)
                    {
                        dropTable.Add(1 + precedentDropChance);
                        precedentDropChance += 1;
                    }
                }
                else
                {
                    foreach (Item_Chance item in poolDrawn.Items)
                    {
                        dropTable.Add((int)item.dropPercent + precedentDropChance);
                        precedentDropChance += (int)item.dropPercent;
                    }
                }

                int itemRoll;

                if (equalizeDropChances)
                {
                    itemRoll = rand.Next(1, poolDrawn.Items.Count + 1);
                }
                else
                {
                    itemRoll = rand.Next(1, 101);
                }

                int itemIndexDrawn = 0;

                for (int i = 0; i < dropTable.Count; i++)
                {
                    if (itemRoll <= dropTable[i])
                    {
                        itemIndexDrawn = i;
                        break;
                    }
                }

                Item itemDrawn = m_lstItems[poolDrawn.Items[itemIndexDrawn].itemIndex];

                txt_itemName.Text = itemDrawn.Name;
                
                try
                {
                    picbox_thumbnail.Load(itemDrawn.ImagePath);
                }
                catch
                {
                    picbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
                }
                

                int itemDatabaseIndex = poolDrawn.Items[itemIndexDrawn].itemIndex;

                LoadRarity(itemDrawn.Rarity);

                bool itemExists = false;
                foreach (Item item in m_lstLootItems)
                {
                    if (item.Name.Equals(itemDrawn.Name))
                    {
                        foreach (ListViewItem lsvItem in lsv_lootView.Items)
                        {
                            if (lsvItem.SubItems[1].Text.Equals(item.Name))
                            {
                                lsvItem.SubItems[4].Text = (int.Parse(lsvItem.SubItems[4].Text) + 1).ToString();
                                itemExists = true;
                            }
                        }
                    }
                }

                if (!itemExists)
                {
                    ListViewItem itemToAdd = new ListViewItem(itemDatabaseIndex.ToString());
                    itemToAdd.SubItems.Add(itemDrawn.Name);
                    itemToAdd.SubItems.Add(itemDrawn.Category);
                    itemToAdd.SubItems.Add(itemDrawn.Rarity.ToString());
                    itemToAdd.SubItems.Add("1");

                    lsv_lootView.Items.Add(itemToAdd);

                    m_lstLootItems.Add(itemDrawn);
                }

                //System.Threading.Thread.Sleep(1000);
            }
        }
        #endregion

        #region Generate Single Item

        
        private void GenerateASingleItem(MT19937Generator rand)
        {
            // Formating chances...
            int chancePool1 = (int)num_itemPool1Chance.Value;
            int chancePool2 = chancePool1 + (int)num_itemPool2Chance.Value;
            int chancePool3 = chancePool2 + (int)num_itemPool3Chance.Value;

            if (num_itemPool1Chance.Value == 0)
                chancePool1 = 0;
            if (num_itemPool2Chance.Value == 0)
                chancePool2 = 0;
            if (num_itemPool3Chance.Value == 0)
                chancePool3 = 0;

            int poolIndex;

            // Choosing an item pool. Each pool has its chance.
            int poolRoll = rand.Next(1, 101);

            int numberPool = -1;

            if (poolRoll <= chancePool1)
            {
                poolIndex = cbo_itemPool1.SelectedIndex;
                numberPool = 1;
            }               
            else if (poolRoll > chancePool1 && poolRoll <= chancePool2)
            {
                poolIndex = cbo_itemPool2.SelectedIndex;
                numberPool = 2;
            }               
            else
            {
                poolIndex = cbo_itemPool3.SelectedIndex;
                numberPool = 3;
            }
                
            // Setting the pool.
            ItemPool poolDrawn = m_lstItemPools[poolIndex - 1];

            // Formatting item drop chances...
            List<int> dropTable = new List<int>();

            int precedentDropChance = 0;

            bool equalizeDropChances = false;

            if(numberPool == 1)
            {
                if(chk_equaPool1.Checked)
                {
                    equalizeDropChances = true;
                }
            }
            if(numberPool == 2)
            {
                if (chk_equaPool2.Checked)
                {
                    equalizeDropChances = true;
                }
            }
            if(numberPool == 3)
            {
                if (chk_equaPool3.Checked)
                {
                    equalizeDropChances = true;
                }
            }

            if(equalizeDropChances)
            {
                foreach (Item_Chance item in poolDrawn.Items)
                {
                    dropTable.Add(1 + precedentDropChance);
                    precedentDropChance += 1;
                }
            }
            else
            {
                foreach (Item_Chance item in poolDrawn.Items)
                {
                    dropTable.Add((int)item.dropPercent + precedentDropChance);
                    precedentDropChance += (int)item.dropPercent;
                }
            }

            int itemRoll;

            if(equalizeDropChances)
            {
                itemRoll = rand.Next(1, poolDrawn.Items.Count + 1);
            }
            else
            {
                itemRoll = rand.Next(1, 101);
            }

            
            int itemIndexDrawn = 0;

            for (int i = 0; i < dropTable.Count; i++)
            {
                if (itemRoll <= dropTable[i])
                {
                    itemIndexDrawn = i;
                    break;
                }
            }

            Item itemDrawn = m_lstItems[poolDrawn.Items[itemIndexDrawn].itemIndex];

            txt_itemName.Text = itemDrawn.Name;

            try
            {
                picbox_thumbnail.Load(itemDrawn.ImagePath);
            }
            catch
            {
                picbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
            }
            
            int itemDatabaseIndex = poolDrawn.Items[itemIndexDrawn].itemIndex;

            LoadRarity(itemDrawn.Rarity);

            bool itemExists = false;
            foreach (Item item in m_lstLootItems)
            {
                if (item.Name.Equals(itemDrawn.Name))
                {
                    foreach (ListViewItem lsvItem in lsv_lootView.Items)
                    {
                        if (lsvItem.SubItems[1].Text.Equals(item.Name))
                        {
                            lsvItem.SubItems[4].Text = (int.Parse(lsvItem.SubItems[4].Text) + 1).ToString();
                            itemExists = true;
                        }
                    }
                }
            }

            if (!itemExists)
            {
                ListViewItem itemToAdd = new ListViewItem(itemDatabaseIndex.ToString());
                itemToAdd.SubItems.Add(itemDrawn.Name);
                itemToAdd.SubItems.Add(itemDrawn.Category);
                itemToAdd.SubItems.Add(itemDrawn.Rarity.ToString());
                itemToAdd.SubItems.Add("1");

                lsv_lootView.Items.Add(itemToAdd);

                m_lstLootItems.Add(itemDrawn);
            }
        }
        #endregion

        private void mnu_advanced_chooseSeed_Click(object sender, EventArgs e)
        {
            SeedValue seedValueForm = new SeedValue();

            seedValueForm.ShowDialog();
        }

        private void lsv_lootView_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                if (lsv_lootView.SelectedIndices.Count == 0 || lsv_lootView.SelectedIndices[0] == -1)
                {
                    btn_inspect.Enabled = false;
                    return;
                }

                int itemIndexReal = int.Parse(lsv_lootView.Items[lsv_lootView.SelectedIndices[0]].Text);

                Item realItem = m_lstItems[itemIndexReal];

                txt_itemName.Text = realItem.Name;

                try
                {
                    picbox_thumbnail.Load(realItem.ImagePath);
                }
                catch
                {
                    picbox_thumbnail.Load(MainMenu.PATH_UNKNOWN_IMAGE);
                }


                LoadRarity(realItem.Rarity);

                btn_inspect.Enabled = true;
            }
            catch
            {
                MessageBox.Show("The item you selected is no longer in the database.", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);

                lsv_lootView.Items[lsv_lootView.SelectedIndices[0]].Remove();
            }
        }

        private void lsv_lootView_ColumnWidthChanging(object sender, ColumnWidthChangingEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.NewWidth = 0;
                e.Cancel = true;
            }
        }

        private void LoadRarity(Rarity pRarity)
        {
            switch (pRarity)
            {
                case Rarity.F:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingF.png");
                    break;
                case Rarity.E:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingE.png");
                    break;
                case Rarity.D:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingD.png");
                    break;
                case Rarity.C:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingC.png");
                    break;
                case Rarity.B:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingB.png");
                    break;
                case Rarity.A:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingA.png");
                    break;
                case Rarity.S:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingS.png");
                    break;
                case Rarity.SS:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingSS.png");
                    break;
                case Rarity.SSS:
                    picbox_rarityImage.Load(PATH_FOLDER_RARITYIMAGES + "RatingSSS.png");
                    break;
                default:
                    picbox_rarityImage.Load(PATH_UNKNOWN_IMAGE);
                    break;
            }
        }

        private void templateToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FrmManageTemplates frmTemp = new FrmManageTemplates(m_lstTemplates);

            frmTemp.ShowDialog();
        }

        private void itemsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FrmManageItems frmItems = new FrmManageItems(m_lstItems, m_lstTemplates, m_lstItemPools);

            frmItems.ShowDialog();
        }

        private void itemPoolsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FrmManageItemPools frmItemPool = new FrmManageItemPools(m_lstItemPools, m_lstItems);

            frmItemPool.ShowDialog();

            ManageItemPools();
        }

        private void importToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FrmImport frmImport = new FrmImport(m_lstItems, m_lstItemPools, m_lstTemplates);

            frmImport.ShowDialog();
        }

        private void mnu_advanced_Click(object sender, EventArgs e)
        {

        }

        private void lsv_lootView_ColumnClick(object sender, ColumnClickEventArgs e)
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
            this.lsv_lootView.Sort();
        }

        private void ManageItemPools()
        {
            cbo_itemPool1.Items.Clear();
            cbo_itemPool2.Items.Clear();
            cbo_itemPool3.Items.Clear();

            cbo_itemPool1.Items.Add("None");
            cbo_itemPool2.Items.Add("None");
            cbo_itemPool3.Items.Add("None");

            foreach (ItemPool pool in m_lstItemPools)
            {
                cbo_itemPool1.Items.Add(pool.Name);
                cbo_itemPool2.Items.Add(pool.Name);
                cbo_itemPool3.Items.Add(pool.Name);
            }

            cbo_itemPool1.SelectedIndex = 0;
            cbo_itemPool2.SelectedIndex = 0;
            cbo_itemPool3.SelectedIndex = 0;
        }

        private void btn_inspect_Click(object sender, EventArgs e)
        {
            int itemSelectedIndex = int.Parse(lsv_lootView.Items[lsv_lootView.SelectedIndices[0]].Text);
            Item itemSelected = m_lstItems[itemSelectedIndex];

            FrmItemView frmItemView = new FrmItemView(itemSelected);

            frmItemView.Show();
        }

        private void picbox_rarityImage_Click(object sender, EventArgs e)
        {

        }

        private void btn_exportLoot_Click(object sender, EventArgs e)
        {
            string text = "";

            foreach (ListViewItem item in lsv_lootView.Items)
            {
                text += item.SubItems[1].Text;
                text += "  -  ";
                text += item.SubItems[2].Text;
                text += "  -  ";
                text += item.SubItems[3].Text;
                text += "  -  ";
                text += item.SubItems[4].Text;

                text += Environment.NewLine;
            }

            FrmNotepad note = new FrmNotepad(text);

            note.Show();
        }

        #region Timer and delayed actions

        //public void Delayed(int delay, Action action)
        //{
        //    Timer timer = new Timer();
        //    timer.Interval = delay;
        //    timer.Tick += (s, e) =>
        //    {
        //        action();
        //        timer.Stop();
        //    };
        //    timer.Start();
        //}

        #endregion
    }
}
