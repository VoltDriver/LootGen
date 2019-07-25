namespace LootGen
{
    partial class MainMenu
    {
        /// <summary>
        /// Variable nécessaire au concepteur.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Nettoyage des ressources utilisées.
        /// </summary>
        /// <param name="disposing">true si les ressources managées doivent être supprimées ; sinon, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Code généré par le Concepteur Windows Form

        /// <summary>
        /// Méthode requise pour la prise en charge du concepteur - ne modifiez pas
        /// le contenu de cette méthode avec l'éditeur de code.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainMenu));
            this.btn_generateLoot = new System.Windows.Forms.Button();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.mnu_create = new System.Windows.Forms.ToolStripMenuItem();
            this.mnu_create_template = new System.Windows.Forms.ToolStripMenuItem();
            this.mnu_create_item = new System.Windows.Forms.ToolStripMenuItem();
            this.mnu_create_itemPool = new System.Windows.Forms.ToolStripMenuItem();
            this.mnu_manage = new System.Windows.Forms.ToolStripMenuItem();
            this.templateToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.itemsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.itemPoolsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.mnu_advanced = new System.Windows.Forms.ToolStripMenuItem();
            this.mnu_advanced_chooseSeed = new System.Windows.Forms.ToolStripMenuItem();
            this.importToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.pnl_mainmenu = new System.Windows.Forms.Panel();
            this.btn_inspect = new System.Windows.Forms.Button();
            this.lbl_processing = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.lbl_itemPools = new System.Windows.Forms.Label();
            this.num_itemPool3Chance = new System.Windows.Forms.NumericUpDown();
            this.num_itemPool2Chance = new System.Windows.Forms.NumericUpDown();
            this.num_itemPool1Chance = new System.Windows.Forms.NumericUpDown();
            this.chk_instantLoot = new System.Windows.Forms.CheckBox();
            this.lsv_lootView = new System.Windows.Forms.ListView();
            this.num_nbrOfItems = new System.Windows.Forms.NumericUpDown();
            this.lbl_nbrOfItems = new System.Windows.Forms.Label();
            this.txt_itemName = new System.Windows.Forms.TextBox();
            this.picbox_rarityImage = new System.Windows.Forms.PictureBox();
            this.lbl_itemRarity = new System.Windows.Forms.Label();
            this.picbox_thumbnail = new System.Windows.Forms.PictureBox();
            this.lbl_item = new System.Windows.Forms.Label();
            this.cbo_dropChanceMod = new System.Windows.Forms.ComboBox();
            this.lbl_dropChanceMod = new System.Windows.Forms.Label();
            this.cbo_itemPool3 = new System.Windows.Forms.ComboBox();
            this.cbo_itemPool2 = new System.Windows.Forms.ComboBox();
            this.cbo_itemPool1 = new System.Windows.Forms.ComboBox();
            this.mainmenu_tooltip = new System.Windows.Forms.ToolTip(this.components);
            this.chk_equaPool1 = new System.Windows.Forms.CheckBox();
            this.chk_equaPool2 = new System.Windows.Forms.CheckBox();
            this.chk_equaPool3 = new System.Windows.Forms.CheckBox();
            this.btn_exportLoot = new System.Windows.Forms.Button();
            this.menuStrip1.SuspendLayout();
            this.pnl_mainmenu.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.num_itemPool3Chance)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.num_itemPool2Chance)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.num_itemPool1Chance)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.num_nbrOfItems)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picbox_rarityImage)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picbox_thumbnail)).BeginInit();
            this.SuspendLayout();
            // 
            // btn_generateLoot
            // 
            this.btn_generateLoot.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_generateLoot.Location = new System.Drawing.Point(17, 260);
            this.btn_generateLoot.Name = "btn_generateLoot";
            this.btn_generateLoot.Size = new System.Drawing.Size(144, 39);
            this.btn_generateLoot.TabIndex = 10;
            this.btn_generateLoot.Text = "Generate Loot";
            this.mainmenu_tooltip.SetToolTip(this.btn_generateLoot, "Click to begin generating!");
            this.btn_generateLoot.UseVisualStyleBackColor = true;
            this.btn_generateLoot.Click += new System.EventHandler(this.btn_generateLoot_Click);
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnu_create,
            this.mnu_manage,
            this.mnu_advanced});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(917, 24);
            this.menuStrip1.TabIndex = 12;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // mnu_create
            // 
            this.mnu_create.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnu_create_template,
            this.mnu_create_item,
            this.mnu_create_itemPool});
            this.mnu_create.Name = "mnu_create";
            this.mnu_create.ShortcutKeyDisplayString = "CTRL + C";
            this.mnu_create.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.C)));
            this.mnu_create.Size = new System.Drawing.Size(62, 20);
            this.mnu_create.Text = "Create...";
            this.mnu_create.ToolTipText = "Create templates, items and item pools here!";
            this.mnu_create.Click += new System.EventHandler(this.ajouterToolStripMenuItem_Click);
            // 
            // mnu_create_template
            // 
            this.mnu_create_template.Name = "mnu_create_template";
            this.mnu_create_template.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.T)));
            this.mnu_create_template.Size = new System.Drawing.Size(166, 22);
            this.mnu_create_template.Text = "Template";
            this.mnu_create_template.Click += new System.EventHandler(this.mnu_create_template_Click);
            // 
            // mnu_create_item
            // 
            this.mnu_create_item.Name = "mnu_create_item";
            this.mnu_create_item.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.I)));
            this.mnu_create_item.Size = new System.Drawing.Size(166, 22);
            this.mnu_create_item.Text = "Item";
            this.mnu_create_item.Click += new System.EventHandler(this.mnu_create_item_Click);
            // 
            // mnu_create_itemPool
            // 
            this.mnu_create_itemPool.Name = "mnu_create_itemPool";
            this.mnu_create_itemPool.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.P)));
            this.mnu_create_itemPool.Size = new System.Drawing.Size(166, 22);
            this.mnu_create_itemPool.Text = "Item Pool";
            this.mnu_create_itemPool.Click += new System.EventHandler(this.mnu_create_itemPool_Click);
            // 
            // mnu_manage
            // 
            this.mnu_manage.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.templateToolStripMenuItem,
            this.itemsToolStripMenuItem,
            this.itemPoolsToolStripMenuItem});
            this.mnu_manage.Name = "mnu_manage";
            this.mnu_manage.Size = new System.Drawing.Size(71, 20);
            this.mnu_manage.Text = "Manage...";
            this.mnu_manage.ToolTipText = "View, edit and delete templates, items and item pools.";
            // 
            // templateToolStripMenuItem
            // 
            this.templateToolStripMenuItem.Name = "templateToolStripMenuItem";
            this.templateToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)(((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.Shift) 
            | System.Windows.Forms.Keys.T)));
            this.templateToolStripMenuItem.Size = new System.Drawing.Size(203, 22);
            this.templateToolStripMenuItem.Text = "Templates";
            this.templateToolStripMenuItem.Click += new System.EventHandler(this.templateToolStripMenuItem_Click);
            // 
            // itemsToolStripMenuItem
            // 
            this.itemsToolStripMenuItem.Name = "itemsToolStripMenuItem";
            this.itemsToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)(((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.Shift) 
            | System.Windows.Forms.Keys.I)));
            this.itemsToolStripMenuItem.Size = new System.Drawing.Size(203, 22);
            this.itemsToolStripMenuItem.Text = "Items";
            this.itemsToolStripMenuItem.Click += new System.EventHandler(this.itemsToolStripMenuItem_Click);
            // 
            // itemPoolsToolStripMenuItem
            // 
            this.itemPoolsToolStripMenuItem.Name = "itemPoolsToolStripMenuItem";
            this.itemPoolsToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)(((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.Shift) 
            | System.Windows.Forms.Keys.P)));
            this.itemPoolsToolStripMenuItem.Size = new System.Drawing.Size(203, 22);
            this.itemPoolsToolStripMenuItem.Text = "Item Pools";
            this.itemPoolsToolStripMenuItem.Click += new System.EventHandler(this.itemPoolsToolStripMenuItem_Click);
            // 
            // mnu_advanced
            // 
            this.mnu_advanced.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnu_advanced_chooseSeed,
            this.importToolStripMenuItem});
            this.mnu_advanced.Name = "mnu_advanced";
            this.mnu_advanced.Size = new System.Drawing.Size(81, 20);
            this.mnu_advanced.Text = "Advanced...";
            this.mnu_advanced.ToolTipText = "More complicated features of LootGen.";
            this.mnu_advanced.Click += new System.EventHandler(this.mnu_advanced_Click);
            // 
            // mnu_advanced_chooseSeed
            // 
            this.mnu_advanced_chooseSeed.Name = "mnu_advanced_chooseSeed";
            this.mnu_advanced_chooseSeed.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.S)));
            this.mnu_advanced_chooseSeed.Size = new System.Drawing.Size(182, 22);
            this.mnu_advanced_chooseSeed.Text = "Choose Seed";
            this.mnu_advanced_chooseSeed.Click += new System.EventHandler(this.mnu_advanced_chooseSeed_Click);
            // 
            // importToolStripMenuItem
            // 
            this.importToolStripMenuItem.Name = "importToolStripMenuItem";
            this.importToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.O)));
            this.importToolStripMenuItem.Size = new System.Drawing.Size(182, 22);
            this.importToolStripMenuItem.Text = "Import";
            this.importToolStripMenuItem.Click += new System.EventHandler(this.importToolStripMenuItem_Click);
            // 
            // pnl_mainmenu
            // 
            this.pnl_mainmenu.Controls.Add(this.btn_exportLoot);
            this.pnl_mainmenu.Controls.Add(this.chk_equaPool3);
            this.pnl_mainmenu.Controls.Add(this.chk_equaPool2);
            this.pnl_mainmenu.Controls.Add(this.chk_equaPool1);
            this.pnl_mainmenu.Controls.Add(this.btn_inspect);
            this.pnl_mainmenu.Controls.Add(this.lbl_processing);
            this.pnl_mainmenu.Controls.Add(this.label1);
            this.pnl_mainmenu.Controls.Add(this.lbl_itemPools);
            this.pnl_mainmenu.Controls.Add(this.num_itemPool3Chance);
            this.pnl_mainmenu.Controls.Add(this.num_itemPool2Chance);
            this.pnl_mainmenu.Controls.Add(this.num_itemPool1Chance);
            this.pnl_mainmenu.Controls.Add(this.chk_instantLoot);
            this.pnl_mainmenu.Controls.Add(this.lsv_lootView);
            this.pnl_mainmenu.Controls.Add(this.num_nbrOfItems);
            this.pnl_mainmenu.Controls.Add(this.lbl_nbrOfItems);
            this.pnl_mainmenu.Controls.Add(this.txt_itemName);
            this.pnl_mainmenu.Controls.Add(this.picbox_rarityImage);
            this.pnl_mainmenu.Controls.Add(this.lbl_itemRarity);
            this.pnl_mainmenu.Controls.Add(this.picbox_thumbnail);
            this.pnl_mainmenu.Controls.Add(this.lbl_item);
            this.pnl_mainmenu.Controls.Add(this.cbo_dropChanceMod);
            this.pnl_mainmenu.Controls.Add(this.lbl_dropChanceMod);
            this.pnl_mainmenu.Controls.Add(this.cbo_itemPool3);
            this.pnl_mainmenu.Controls.Add(this.cbo_itemPool2);
            this.pnl_mainmenu.Controls.Add(this.cbo_itemPool1);
            this.pnl_mainmenu.Controls.Add(this.btn_generateLoot);
            this.pnl_mainmenu.Location = new System.Drawing.Point(12, 27);
            this.pnl_mainmenu.Name = "pnl_mainmenu";
            this.pnl_mainmenu.Size = new System.Drawing.Size(893, 362);
            this.pnl_mainmenu.TabIndex = 2;
            // 
            // btn_inspect
            // 
            this.btn_inspect.Location = new System.Drawing.Point(737, 209);
            this.btn_inspect.Name = "btn_inspect";
            this.btn_inspect.Size = new System.Drawing.Size(153, 30);
            this.btn_inspect.TabIndex = 33;
            this.btn_inspect.Text = "Inspect Item";
            this.mainmenu_tooltip.SetToolTip(this.btn_inspect, "Click this button to view the item selected and its properties.");
            this.btn_inspect.UseVisualStyleBackColor = true;
            this.btn_inspect.Click += new System.EventHandler(this.btn_inspect_Click);
            // 
            // lbl_processing
            // 
            this.lbl_processing.AutoSize = true;
            this.lbl_processing.Location = new System.Drawing.Point(165, 274);
            this.lbl_processing.Name = "lbl_processing";
            this.lbl_processing.Size = new System.Drawing.Size(36, 13);
            this.lbl_processing.TabIndex = 32;
            this.lbl_processing.Text = "Done!";
            this.lbl_processing.Visible = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(141, 8);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(60, 13);
            this.label1.TabIndex = 31;
            this.label1.Text = "Chances:";
            this.mainmenu_tooltip.SetToolTip(this.label1, resources.GetString("label1.ToolTip"));
            // 
            // lbl_itemPools
            // 
            this.lbl_itemPools.AutoSize = true;
            this.lbl_itemPools.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_itemPools.Location = new System.Drawing.Point(17, 8);
            this.lbl_itemPools.Name = "lbl_itemPools";
            this.lbl_itemPools.Size = new System.Drawing.Size(70, 13);
            this.lbl_itemPools.TabIndex = 30;
            this.lbl_itemPools.Text = "Item Pools:";
            this.mainmenu_tooltip.SetToolTip(this.lbl_itemPools, "Here you can input your item pools.\r\nThose will determine what items are drawn wh" +
        "en you generate loot.\r\nIf you have no item pools, create one using the \"Create.." +
        ".\" menu.");
            // 
            // num_itemPool3Chance
            // 
            this.num_itemPool3Chance.Location = new System.Drawing.Point(144, 93);
            this.num_itemPool3Chance.Name = "num_itemPool3Chance";
            this.num_itemPool3Chance.Size = new System.Drawing.Size(54, 20);
            this.num_itemPool3Chance.TabIndex = 6;
            // 
            // num_itemPool2Chance
            // 
            this.num_itemPool2Chance.Location = new System.Drawing.Point(144, 62);
            this.num_itemPool2Chance.Name = "num_itemPool2Chance";
            this.num_itemPool2Chance.Size = new System.Drawing.Size(54, 20);
            this.num_itemPool2Chance.TabIndex = 4;
            // 
            // num_itemPool1Chance
            // 
            this.num_itemPool1Chance.Location = new System.Drawing.Point(144, 31);
            this.num_itemPool1Chance.Name = "num_itemPool1Chance";
            this.num_itemPool1Chance.Size = new System.Drawing.Size(54, 20);
            this.num_itemPool1Chance.TabIndex = 2;
            // 
            // chk_instantLoot
            // 
            this.chk_instantLoot.AutoSize = true;
            this.chk_instantLoot.Location = new System.Drawing.Point(17, 237);
            this.chk_instantLoot.Name = "chk_instantLoot";
            this.chk_instantLoot.Size = new System.Drawing.Size(82, 17);
            this.chk_instantLoot.TabIndex = 9;
            this.chk_instantLoot.Text = "Instant Loot";
            this.mainmenu_tooltip.SetToolTip(this.chk_instantLoot, "When Instant Loot is checked, there will be no animations\r\nplayed when generating" +
        " loot. You will get your items\r\ninstantly.");
            this.chk_instantLoot.UseVisualStyleBackColor = true;
            this.chk_instantLoot.CheckedChanged += new System.EventHandler(this.chk_instantLoot_CheckedChanged);
            // 
            // lsv_lootView
            // 
            this.lsv_lootView.Location = new System.Drawing.Point(346, 260);
            this.lsv_lootView.Name = "lsv_lootView";
            this.lsv_lootView.Size = new System.Drawing.Size(544, 97);
            this.lsv_lootView.TabIndex = 11;
            this.mainmenu_tooltip.SetToolTip(this.lsv_lootView, "This is the Loot result. Here are shown all the items\r\ngenerated.");
            this.lsv_lootView.UseCompatibleStateImageBehavior = false;
            this.lsv_lootView.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsv_lootView_ColumnClick);
            this.lsv_lootView.SelectedIndexChanged += new System.EventHandler(this.lsv_lootView_SelectedIndexChanged);
            // 
            // num_nbrOfItems
            // 
            this.num_nbrOfItems.Location = new System.Drawing.Point(107, 124);
            this.num_nbrOfItems.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.num_nbrOfItems.Name = "num_nbrOfItems";
            this.num_nbrOfItems.Size = new System.Drawing.Size(97, 20);
            this.num_nbrOfItems.TabIndex = 7;
            this.num_nbrOfItems.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // lbl_nbrOfItems
            // 
            this.lbl_nbrOfItems.AutoSize = true;
            this.lbl_nbrOfItems.Location = new System.Drawing.Point(14, 124);
            this.lbl_nbrOfItems.Name = "lbl_nbrOfItems";
            this.lbl_nbrOfItems.Size = new System.Drawing.Size(86, 13);
            this.lbl_nbrOfItems.TabIndex = 23;
            this.lbl_nbrOfItems.Text = "Number of items:";
            this.mainmenu_tooltip.SetToolTip(this.lbl_nbrOfItems, "The number of items you want to be drawn with the next\r\npress of \"Generate Loot\"." +
        "\r\n\r\nExample: If you put 10, LootGen will generate 10 items.");
            // 
            // txt_itemName
            // 
            this.txt_itemName.Location = new System.Drawing.Point(407, 13);
            this.txt_itemName.Name = "txt_itemName";
            this.txt_itemName.ReadOnly = true;
            this.txt_itemName.Size = new System.Drawing.Size(324, 20);
            this.txt_itemName.TabIndex = 22;
            // 
            // picbox_rarityImage
            // 
            this.picbox_rarityImage.Location = new System.Drawing.Point(571, 89);
            this.picbox_rarityImage.Name = "picbox_rarityImage";
            this.picbox_rarityImage.Size = new System.Drawing.Size(160, 150);
            this.picbox_rarityImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picbox_rarityImage.TabIndex = 21;
            this.picbox_rarityImage.TabStop = false;
            this.picbox_rarityImage.Click += new System.EventHandler(this.picbox_rarityImage_Click);
            // 
            // lbl_itemRarity
            // 
            this.lbl_itemRarity.AutoSize = true;
            this.lbl_itemRarity.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_itemRarity.Location = new System.Drawing.Point(615, 61);
            this.lbl_itemRarity.Name = "lbl_itemRarity";
            this.lbl_itemRarity.Size = new System.Drawing.Size(80, 25);
            this.lbl_itemRarity.TabIndex = 12;
            this.lbl_itemRarity.Text = "Rarity: ";
            this.mainmenu_tooltip.SetToolTip(this.lbl_itemRarity, resources.GetString("lbl_itemRarity.ToolTip"));
            // 
            // picbox_thumbnail
            // 
            this.picbox_thumbnail.Location = new System.Drawing.Point(346, 39);
            this.picbox_thumbnail.Name = "picbox_thumbnail";
            this.picbox_thumbnail.Size = new System.Drawing.Size(200, 200);
            this.picbox_thumbnail.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picbox_thumbnail.TabIndex = 19;
            this.picbox_thumbnail.TabStop = false;
            // 
            // lbl_item
            // 
            this.lbl_item.AutoSize = true;
            this.lbl_item.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_item.Location = new System.Drawing.Point(341, 8);
            this.lbl_item.Name = "lbl_item";
            this.lbl_item.Size = new System.Drawing.Size(60, 25);
            this.lbl_item.TabIndex = 18;
            this.lbl_item.Text = "Item:";
            this.mainmenu_tooltip.SetToolTip(this.lbl_item, "This represents the name of the item drawn, or selected.\r\n");
            this.lbl_item.Click += new System.EventHandler(this.lbl_item_Click);
            // 
            // cbo_dropChanceMod
            // 
            this.cbo_dropChanceMod.FormattingEnabled = true;
            this.cbo_dropChanceMod.Location = new System.Drawing.Point(17, 173);
            this.cbo_dropChanceMod.Name = "cbo_dropChanceMod";
            this.cbo_dropChanceMod.Size = new System.Drawing.Size(121, 21);
            this.cbo_dropChanceMod.TabIndex = 8;
            this.cbo_dropChanceMod.Visible = false;
            // 
            // lbl_dropChanceMod
            // 
            this.lbl_dropChanceMod.AutoSize = true;
            this.lbl_dropChanceMod.Location = new System.Drawing.Point(14, 157);
            this.lbl_dropChanceMod.Name = "lbl_dropChanceMod";
            this.lbl_dropChanceMod.Size = new System.Drawing.Size(113, 13);
            this.lbl_dropChanceMod.TabIndex = 16;
            this.lbl_dropChanceMod.Text = "Drop Chance Modifier:";
            this.mainmenu_tooltip.SetToolTip(this.lbl_dropChanceMod, resources.GetString("lbl_dropChanceMod.ToolTip"));
            this.lbl_dropChanceMod.Visible = false;
            // 
            // cbo_itemPool3
            // 
            this.cbo_itemPool3.FormattingEnabled = true;
            this.cbo_itemPool3.Location = new System.Drawing.Point(17, 92);
            this.cbo_itemPool3.Name = "cbo_itemPool3";
            this.cbo_itemPool3.Size = new System.Drawing.Size(121, 21);
            this.cbo_itemPool3.TabIndex = 5;
            this.cbo_itemPool3.SelectedIndexChanged += new System.EventHandler(this.cbo_itemPool3_SelectedIndexChanged);
            // 
            // cbo_itemPool2
            // 
            this.cbo_itemPool2.FormattingEnabled = true;
            this.cbo_itemPool2.Location = new System.Drawing.Point(17, 61);
            this.cbo_itemPool2.Name = "cbo_itemPool2";
            this.cbo_itemPool2.Size = new System.Drawing.Size(121, 21);
            this.cbo_itemPool2.TabIndex = 3;
            this.cbo_itemPool2.SelectedIndexChanged += new System.EventHandler(this.cbo_itemPool2_SelectedIndexChanged);
            // 
            // cbo_itemPool1
            // 
            this.cbo_itemPool1.FormattingEnabled = true;
            this.cbo_itemPool1.Location = new System.Drawing.Point(17, 30);
            this.cbo_itemPool1.Name = "cbo_itemPool1";
            this.cbo_itemPool1.Size = new System.Drawing.Size(121, 21);
            this.cbo_itemPool1.TabIndex = 1;
            this.cbo_itemPool1.SelectedIndexChanged += new System.EventHandler(this.comboBox1_SelectedIndexChanged);
            // 
            // mainmenu_tooltip
            // 
            this.mainmenu_tooltip.AutoPopDelay = 10000;
            this.mainmenu_tooltip.InitialDelay = 500;
            this.mainmenu_tooltip.ReshowDelay = 100;
            // 
            // chk_equaPool1
            // 
            this.chk_equaPool1.AutoSize = true;
            this.chk_equaPool1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.chk_equaPool1.Location = new System.Drawing.Point(204, 33);
            this.chk_equaPool1.Name = "chk_equaPool1";
            this.chk_equaPool1.Size = new System.Drawing.Size(137, 17);
            this.chk_equaPool1.TabIndex = 34;
            this.chk_equaPool1.Text = "Equalize Drop Chances";
            this.mainmenu_tooltip.SetToolTip(this.chk_equaPool1, resources.GetString("chk_equaPool1.ToolTip"));
            this.chk_equaPool1.UseVisualStyleBackColor = true;
            // 
            // chk_equaPool2
            // 
            this.chk_equaPool2.AutoSize = true;
            this.chk_equaPool2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.chk_equaPool2.Location = new System.Drawing.Point(203, 63);
            this.chk_equaPool2.Name = "chk_equaPool2";
            this.chk_equaPool2.Size = new System.Drawing.Size(137, 17);
            this.chk_equaPool2.TabIndex = 35;
            this.chk_equaPool2.Text = "Equalize Drop Chances";
            this.mainmenu_tooltip.SetToolTip(this.chk_equaPool2, resources.GetString("chk_equaPool2.ToolTip"));
            this.chk_equaPool2.UseVisualStyleBackColor = true;
            // 
            // chk_equaPool3
            // 
            this.chk_equaPool3.AutoSize = true;
            this.chk_equaPool3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.chk_equaPool3.Location = new System.Drawing.Point(203, 94);
            this.chk_equaPool3.Name = "chk_equaPool3";
            this.chk_equaPool3.Size = new System.Drawing.Size(137, 17);
            this.chk_equaPool3.TabIndex = 36;
            this.chk_equaPool3.Text = "Equalize Drop Chances";
            this.mainmenu_tooltip.SetToolTip(this.chk_equaPool3, resources.GetString("chk_equaPool3.ToolTip"));
            this.chk_equaPool3.UseVisualStyleBackColor = true;
            // 
            // btn_exportLoot
            // 
            this.btn_exportLoot.Enabled = false;
            this.btn_exportLoot.Location = new System.Drawing.Point(17, 305);
            this.btn_exportLoot.Name = "btn_exportLoot";
            this.btn_exportLoot.Size = new System.Drawing.Size(144, 23);
            this.btn_exportLoot.TabIndex = 37;
            this.btn_exportLoot.Text = "Export Loot to Text";
            this.btn_exportLoot.UseVisualStyleBackColor = true;
            this.btn_exportLoot.Click += new System.EventHandler(this.btn_exportLoot_Click);
            // 
            // MainMenu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(917, 401);
            this.Controls.Add(this.pnl_mainmenu);
            this.Controls.Add(this.menuStrip1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "MainMenu";
            this.Text = "LootGen : The Generic Loot Generator";
            this.Load += new System.EventHandler(this.MainMenu_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.pnl_mainmenu.ResumeLayout(false);
            this.pnl_mainmenu.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.num_itemPool3Chance)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.num_itemPool2Chance)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.num_itemPool1Chance)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.num_nbrOfItems)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picbox_rarityImage)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picbox_thumbnail)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_generateLoot;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem mnu_create;
        private System.Windows.Forms.ToolStripMenuItem mnu_create_template;
        private System.Windows.Forms.ToolStripMenuItem mnu_create_item;
        private System.Windows.Forms.ToolStripMenuItem mnu_create_itemPool;
        private System.Windows.Forms.ToolStripMenuItem mnu_manage;
        private System.Windows.Forms.Panel pnl_mainmenu;
        private System.Windows.Forms.ComboBox cbo_itemPool1;
        private System.Windows.Forms.ComboBox cbo_dropChanceMod;
        private System.Windows.Forms.Label lbl_dropChanceMod;
        private System.Windows.Forms.ComboBox cbo_itemPool3;
        private System.Windows.Forms.ComboBox cbo_itemPool2;
        private System.Windows.Forms.PictureBox picbox_rarityImage;
        private System.Windows.Forms.Label lbl_itemRarity;
        private System.Windows.Forms.PictureBox picbox_thumbnail;
        private System.Windows.Forms.Label lbl_item;
        private System.Windows.Forms.NumericUpDown num_nbrOfItems;
        private System.Windows.Forms.Label lbl_nbrOfItems;
        private System.Windows.Forms.TextBox txt_itemName;
        private System.Windows.Forms.ListView lsv_lootView;
        private System.Windows.Forms.CheckBox chk_instantLoot;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lbl_itemPools;
        private System.Windows.Forms.NumericUpDown num_itemPool3Chance;
        private System.Windows.Forms.NumericUpDown num_itemPool2Chance;
        private System.Windows.Forms.NumericUpDown num_itemPool1Chance;
        private System.Windows.Forms.ToolStripMenuItem mnu_advanced;
        private System.Windows.Forms.ToolStripMenuItem mnu_advanced_chooseSeed;
        private System.Windows.Forms.Label lbl_processing;
        private System.Windows.Forms.ToolStripMenuItem templateToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem itemsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem itemPoolsToolStripMenuItem;
        private System.Windows.Forms.ToolTip mainmenu_tooltip;
        private System.Windows.Forms.Button btn_inspect;
        private System.Windows.Forms.ToolStripMenuItem importToolStripMenuItem;
        private System.Windows.Forms.CheckBox chk_equaPool3;
        private System.Windows.Forms.CheckBox chk_equaPool2;
        private System.Windows.Forms.CheckBox chk_equaPool1;
        private System.Windows.Forms.Button btn_exportLoot;
    }
}

