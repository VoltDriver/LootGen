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
    public partial class FrmImport : Form
    {
        private List<Item> m_dataBaseItems;
        private List<ItemPool> m_dataBaseItemPools;
        private List<Template> m_dataBaseTemplates;

        public FrmImport(List<Item> pItems, List<ItemPool> pItemPools, List<Template> pTemplates)
        {
            InitializeComponent();

            m_dataBaseItems = pItems;
            m_dataBaseTemplates = pTemplates;
            m_dataBaseItemPools = pItemPools;

            this.CenterToScreen();
        }

        private void btn_items_browse_Click(object sender, EventArgs e)
        {
            OpenFileDialog fdlg = new OpenFileDialog();
            if (fdlg.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    txt_itemsPath.Text = fdlg.FileName;
                }
                catch
                {
                    MessageBox.Show("Could not retrieve the file.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
        }

        private void btn_itemPools_browse_Click(object sender, EventArgs e)
        {
            OpenFileDialog fdlg = new OpenFileDialog();
            if (fdlg.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    txt_itemPoolsPath.Text = fdlg.FileName;
                }
                catch
                {
                    MessageBox.Show("Could not retrieve the file.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
        }

        private void btn_templates_browse_Click(object sender, EventArgs e)
        {
            OpenFileDialog fdlg = new OpenFileDialog();
            if (fdlg.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    txt_templatesPath.Text = fdlg.FileName;
                }
                catch
                {
                    MessageBox.Show("Could not retrieve the file.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
        }

        private void btn_cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btn_import_Click(object sender, EventArgs e)
        {
            bool? successTemplates = null;
            bool? successItems = null;
            bool? successItemPools = null;

            if (txt_templatesPath.Text != string.Empty)
            {
                List<Template> m_lstTemplates = new List<Template>();

                StreamReader objReader = new StreamReader(txt_templatesPath.Text);
                try
                {
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

                    successTemplates = true;
                }

                catch (Exception exce)
                {
                    MessageBox.Show("There was an error importing the Templates." + Environment.NewLine + "Error: " + exce.Message
                    + Environment.NewLine + "Operation was aborted.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    successTemplates = false;
                }

                finally
                {
                    objReader.Close();
                }

                if (successTemplates.Value)
                {
                    foreach (Template temp in m_lstTemplates)
                    {
                        //bool dupeFound = false;
                        //for (int i = 0; i < m_dataBaseTemplates.Count; i++)
                        //{
                        //    Template existing = m_dataBaseTemplates[i];
                        //    if (existing.Name.Equals(temp.Name))
                        //    {
                        //        if(chk_overwrite.Checked)
                        //        {
                        //            m_dataBaseTemplates[i] = temp;
                        //            dupeFound = true;
                        //        }
                        //        else
                        //        {
                        //            if (MessageBox.Show("A Template with the name \"" + temp.Name + "\" already exists. Would you like to overwrite it?", "Warning",
                        //            MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
                        //            {
                        //                m_dataBaseTemplates[i] = temp;
                        //                dupeFound = true;
                        //            }
                        //        }                                
                        //    }

                        //}
                        bool dupeFound = false;

                        var value = m_dataBaseTemplates.Find(t => t.Name.Equals(temp.Name));

                        if(value != null)
                        {
                            if (chk_overwrite.Checked)
                            {
                                value = temp;
                                m_dataBaseTemplates.Find(t => t.Name.Equals(temp.Name)).Name = temp.Name;
                                m_dataBaseTemplates.Find(t => t.Name.Equals(temp.Name)).Properties = temp.Properties;
                                dupeFound = true;
                            }
                            else
                            {
                                if (MessageBox.Show("A Template with the name \"" + temp.Name + "\" already exists. Would you like to overwrite it?", "Warning",
                                MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
                                {
                                    value = temp;
                                    m_dataBaseTemplates.Find(t => t.Name.Equals(temp.Name)).Name = temp.Name;
                                    m_dataBaseTemplates.Find(t => t.Name.Equals(temp.Name)).Properties = temp.Properties;
                                    dupeFound = true;
                                }
                            } 
                        }

                        if (!dupeFound)
                            m_dataBaseTemplates.Add(temp);
                    }

                    StreamWriter objWriter = new StreamWriter(MainMenu.PATH_FILE_TEMPLATES);

                    foreach (Template temp in m_dataBaseTemplates)
                    {
                        objWriter.WriteLine(temp.ToString());
                    }

                    objWriter.Close();
                }
            }

            if(txt_itemsPath.Text != string.Empty)
            {
                List<Item> m_lstItems = new List<Item>();

                StreamReader objReader = new StreamReader(txt_itemsPath.Text);
                try
                {
                    while (!objReader.EndOfStream)
                    {
                        // Getting the whole item.
                        string[] tabContent = objReader.ReadLine().Split(new string[] { "*|*" }, StringSplitOptions.None);

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

                    successItems = true;
                }

                catch (Exception exce)
                {
                    MessageBox.Show("There was an error importing the Items." + Environment.NewLine + "Error: " + exce.Message
                    + Environment.NewLine + "Operation was aborted.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    successItems = false;
                }

                finally
                {
                    objReader.Close();
                }

                if (successItems.Value)
                {
                    foreach (Item item in m_lstItems)
                    {
                        bool dupeFound = false;

                        //for (int i = 0; i < m_dataBaseItems.Count; i++)
                        //{
                        //    Item existing = m_dataBaseItems[i];
                        //    if (existing.Name.Equals(item.Name))
                        //    {
                        //        if(chk_overwrite.Checked)
                        //        {
                        //            m_dataBaseItems[i] = item;
                        //            dupeFound = true;
                        //        }
                        //        else
                        //        {
                        //            if (MessageBox.Show("An item with the name \"" + item.Name + "\" already exists. Would you like to overwrite it?", "Warning",
                        //                                                MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
                        //            {
                        //                m_dataBaseItems[i] = item;
                        //                dupeFound = true;
                        //            }
                        //        }                               
                        //    }
                        //}

                        var value = m_dataBaseItems.Find(i => i.Name.Equals(item.Name));

                        if(value != null)
                        {
                            if (chk_overwrite.Checked)
                            {
                                value = item;
                                m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Name = item.Name;
                                m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).ImagePath = item.ImagePath;
                                m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Category = item.Category;
                                m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Properties = item.Properties;
                                m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Rarity = item.Rarity;
                                m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Template = item.Template;
                                dupeFound = true;
                            }
                            else
                            {
                                if (MessageBox.Show("An item with the name \"" + item.Name + "\" already exists. Would you like to overwrite it?", "Warning",
                                                                    MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
                                {
                                    value = item;
                                    m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Name = item.Name;
                                    m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).ImagePath = item.ImagePath;
                                    m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Category = item.Category;
                                    m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Properties = item.Properties;
                                    m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Rarity = item.Rarity;
                                    m_dataBaseItems.Find(t => t.Name.Equals(item.Name)).Template = item.Template;
                                    dupeFound = true;
                                }
                            }
                        }

                        if (!dupeFound)
                            m_dataBaseItems.Add(item);
                    }

                    StreamWriter objWriter = new StreamWriter(MainMenu.PATH_FILE_ITEMS);

                    foreach (Item items in m_dataBaseItems)
                    {
                        objWriter.WriteLine(items.ToString());
                    }

                    objWriter.Close();
                }
            }

            if(txt_itemPoolsPath.Text != string.Empty)
            {
                StreamReader objReader = new StreamReader(txt_itemPoolsPath.Text);

                List<ItemPool> m_lstItemPools = new List<ItemPool>();
                try
                {
                    while (!objReader.EndOfStream)
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

                    successItemPools = true;
                }

                catch(Exception exce)
                {
                    MessageBox.Show("There was an error importing the Item Pools." + Environment.NewLine + "Error: " + exce.Message
                    + Environment.NewLine + "Operation was aborted."  , "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                    successItemPools = false;
                }

                finally
                {
                    objReader.Close();
                }

                if(successItemPools.Value)
                {
                    foreach (ItemPool item in m_lstItemPools)
                    {
                        bool dupeFound = false;

                        for(int i = 0; i < m_dataBaseItemPools.Count; i++)
                        {
                            ItemPool pool = m_dataBaseItemPools[i];
                            if(pool.Name.Equals(item.Name))
                            {
                                if(chk_overwrite.Checked)
                                {
                                    m_dataBaseItemPools[i] = item;
                                    dupeFound = true;
                                }
                                else
                                {
                                    if (MessageBox.Show("An item pool with the name \"" + pool.Name + "\" already exists. Would you like to overwrite it?", "Warning",
                                                                        MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.Yes)
                                    {
                                        m_dataBaseItemPools[i] = item;
                                        dupeFound = true;
                                    }
                                }                             
                            }
                        }

                        if (!dupeFound)
                            m_dataBaseItemPools.Add(item);
                    }

                    StreamWriter objWriter = new StreamWriter(MainMenu.PATH_FILE_ITEMPOOLS);

                    foreach (ItemPool itemPool in m_dataBaseItemPools)
                    {
                        objWriter.WriteLine(itemPool.ToString());
                    }

                    objWriter.Close();
                }
            }

            string resultItems = "Items: ";
            string resultTemplates = "Templates: ";
            string resultItemPools = "Item Pools: ";

            if(successTemplates != null)
            {
                if(successTemplates.Value)
                {
                    resultTemplates += "Imported successfully.";
                }
                else
                {
                    resultTemplates += "Could not be imported.";
                }
            }
            else
            {
                resultTemplates += "No imports.";
            }

            if(successItems != null)
            {
                if(successItems.Value)
                {
                    resultItems += "Imported successfully.";
                }
                else
                {
                    resultItems += "Could not be imported.";
                }
            }
            else
            {
                resultItems += "No imports.";
            }

            if(successItemPools != null)
            {
                if(successItemPools.Value)
                {
                    resultItemPools += "Imported successfully.";
                }
                else
                {
                    resultItemPools += "Could not be imported.";
                }
            }
            else
            {
                resultItemPools += "No imports.";
            }

            MessageBox.Show("Importation complete." + Environment.NewLine + 
                            "Here are the results of the import: " + Environment.NewLine +
                            resultTemplates + Environment.NewLine + resultItems + Environment.NewLine + resultItemPools + Environment.NewLine, "Results",
            MessageBoxButtons.OK, MessageBoxIcon.Information);

            this.Close();
        }

        private void chk_overwrite_CheckedChanged(object sender, EventArgs e)
        {

        }
    }
}
