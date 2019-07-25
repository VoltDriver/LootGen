namespace LootGen
{
    partial class CreateItemPool
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CreateItemPool));
            this.btn_cancel = new System.Windows.Forms.Button();
            this.btn_create = new System.Windows.Forms.Button();
            this.pnl_createItem = new System.Windows.Forms.Panel();
            this.btn_modifyDropChance = new System.Windows.Forms.Button();
            this.num_dropChance = new System.Windows.Forms.NumericUpDown();
            this.lbl_dropChancePercent = new System.Windows.Forms.Label();
            this.lbl_dropChance = new System.Windows.Forms.Label();
            this.btn_removeItem = new System.Windows.Forms.Button();
            this.lsv_poolItems = new System.Windows.Forms.ListView();
            this.lbl_poolItems = new System.Windows.Forms.Label();
            this.btn_itemAdd = new System.Windows.Forms.Button();
            this.picbox_thumbnail = new System.Windows.Forms.PictureBox();
            this.lbl_thumbnail = new System.Windows.Forms.Label();
            this.lsv_allItems = new System.Windows.Forms.ListView();
            this.txt_itemPoolName = new System.Windows.Forms.TextBox();
            this.lbl_poolName = new System.Windows.Forms.Label();
            this.lbl_items = new System.Windows.Forms.Label();
            this.poolToolTip = new System.Windows.Forms.ToolTip(this.components);
            this.lbl_totalChance = new System.Windows.Forms.Label();
            this.pnl_createItem.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.num_dropChance)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picbox_thumbnail)).BeginInit();
            this.SuspendLayout();
            // 
            // btn_cancel
            // 
            this.btn_cancel.Location = new System.Drawing.Point(12, 376);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(170, 23);
            this.btn_cancel.TabIndex = 8;
            this.btn_cancel.Text = "Cancel";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // btn_create
            // 
            this.btn_create.Location = new System.Drawing.Point(735, 376);
            this.btn_create.Name = "btn_create";
            this.btn_create.Size = new System.Drawing.Size(170, 23);
            this.btn_create.TabIndex = 9;
            this.btn_create.Text = "Create";
            this.btn_create.UseVisualStyleBackColor = true;
            this.btn_create.Click += new System.EventHandler(this.btn_create_Click);
            // 
            // pnl_createItem
            // 
            this.pnl_createItem.Controls.Add(this.lbl_totalChance);
            this.pnl_createItem.Controls.Add(this.btn_modifyDropChance);
            this.pnl_createItem.Controls.Add(this.num_dropChance);
            this.pnl_createItem.Controls.Add(this.lbl_dropChancePercent);
            this.pnl_createItem.Controls.Add(this.lbl_dropChance);
            this.pnl_createItem.Controls.Add(this.btn_removeItem);
            this.pnl_createItem.Controls.Add(this.lsv_poolItems);
            this.pnl_createItem.Controls.Add(this.lbl_poolItems);
            this.pnl_createItem.Controls.Add(this.btn_itemAdd);
            this.pnl_createItem.Controls.Add(this.picbox_thumbnail);
            this.pnl_createItem.Controls.Add(this.lbl_thumbnail);
            this.pnl_createItem.Controls.Add(this.lsv_allItems);
            this.pnl_createItem.Controls.Add(this.txt_itemPoolName);
            this.pnl_createItem.Controls.Add(this.lbl_poolName);
            this.pnl_createItem.Controls.Add(this.lbl_items);
            this.pnl_createItem.Location = new System.Drawing.Point(12, 10);
            this.pnl_createItem.Name = "pnl_createItem";
            this.pnl_createItem.Size = new System.Drawing.Size(893, 360);
            this.pnl_createItem.TabIndex = 13;
            // 
            // btn_modifyDropChance
            // 
            this.btn_modifyDropChance.Location = new System.Drawing.Point(424, 302);
            this.btn_modifyDropChance.Name = "btn_modifyDropChance";
            this.btn_modifyDropChance.Size = new System.Drawing.Size(62, 23);
            this.btn_modifyDropChance.TabIndex = 7;
            this.btn_modifyDropChance.Text = "Modify";
            this.poolToolTip.SetToolTip(this.btn_modifyDropChance, resources.GetString("btn_modifyDropChance.ToolTip"));
            this.btn_modifyDropChance.UseVisualStyleBackColor = true;
            this.btn_modifyDropChance.Click += new System.EventHandler(this.btn_modifyDropChance_Click);
            // 
            // num_dropChance
            // 
            this.num_dropChance.Location = new System.Drawing.Point(424, 276);
            this.num_dropChance.Name = "num_dropChance";
            this.num_dropChance.Size = new System.Drawing.Size(62, 20);
            this.num_dropChance.TabIndex = 3;
            // 
            // lbl_dropChancePercent
            // 
            this.lbl_dropChancePercent.AutoSize = true;
            this.lbl_dropChancePercent.Location = new System.Drawing.Point(492, 278);
            this.lbl_dropChancePercent.Name = "lbl_dropChancePercent";
            this.lbl_dropChancePercent.Size = new System.Drawing.Size(15, 13);
            this.lbl_dropChancePercent.TabIndex = 11;
            this.lbl_dropChancePercent.Text = "%";
            // 
            // lbl_dropChance
            // 
            this.lbl_dropChance.AutoSize = true;
            this.lbl_dropChance.Location = new System.Drawing.Point(345, 279);
            this.lbl_dropChance.Name = "lbl_dropChance";
            this.lbl_dropChance.Size = new System.Drawing.Size(73, 13);
            this.lbl_dropChance.TabIndex = 10;
            this.lbl_dropChance.Text = "Drop Chance:";
            this.poolToolTip.SetToolTip(this.lbl_dropChance, resources.GetString("lbl_dropChance.ToolTip"));
            // 
            // btn_removeItem
            // 
            this.btn_removeItem.Location = new System.Drawing.Point(567, 307);
            this.btn_removeItem.Name = "btn_removeItem";
            this.btn_removeItem.Size = new System.Drawing.Size(322, 23);
            this.btn_removeItem.TabIndex = 6;
            this.btn_removeItem.Text = "Remove from Pool";
            this.btn_removeItem.UseVisualStyleBackColor = true;
            this.btn_removeItem.Click += new System.EventHandler(this.btn_removeItem_Click);
            // 
            // lsv_poolItems
            // 
            this.lsv_poolItems.Location = new System.Drawing.Point(567, 72);
            this.lsv_poolItems.Name = "lsv_poolItems";
            this.lsv_poolItems.Size = new System.Drawing.Size(322, 229);
            this.lsv_poolItems.TabIndex = 5;
            this.lsv_poolItems.UseCompatibleStateImageBehavior = false;
            this.lsv_poolItems.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsv_poolItems_ColumnClick);
            this.lsv_poolItems.SelectedIndexChanged += new System.EventHandler(this.lsv_poolItems_SelectedIndexChanged);
            // 
            // lbl_poolItems
            // 
            this.lbl_poolItems.AutoSize = true;
            this.lbl_poolItems.Location = new System.Drawing.Point(564, 56);
            this.lbl_poolItems.Name = "lbl_poolItems";
            this.lbl_poolItems.Size = new System.Drawing.Size(35, 13);
            this.lbl_poolItems.TabIndex = 7;
            this.lbl_poolItems.Text = "Items:";
            // 
            // btn_itemAdd
            // 
            this.btn_itemAdd.Location = new System.Drawing.Point(6, 307);
            this.btn_itemAdd.Name = "btn_itemAdd";
            this.btn_itemAdd.Size = new System.Drawing.Size(323, 23);
            this.btn_itemAdd.TabIndex = 4;
            this.btn_itemAdd.Text = "Add to Pool";
            this.btn_itemAdd.UseVisualStyleBackColor = true;
            this.btn_itemAdd.Click += new System.EventHandler(this.btn_itemAdd_Click);
            // 
            // picbox_thumbnail
            // 
            this.picbox_thumbnail.Location = new System.Drawing.Point(344, 72);
            this.picbox_thumbnail.Name = "picbox_thumbnail";
            this.picbox_thumbnail.Size = new System.Drawing.Size(200, 200);
            this.picbox_thumbnail.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picbox_thumbnail.TabIndex = 5;
            this.picbox_thumbnail.TabStop = false;
            // 
            // lbl_thumbnail
            // 
            this.lbl_thumbnail.AutoSize = true;
            this.lbl_thumbnail.Location = new System.Drawing.Point(341, 56);
            this.lbl_thumbnail.Name = "lbl_thumbnail";
            this.lbl_thumbnail.Size = new System.Drawing.Size(59, 13);
            this.lbl_thumbnail.TabIndex = 4;
            this.lbl_thumbnail.Text = "Thumbnail:";
            // 
            // lsv_allItems
            // 
            this.lsv_allItems.Location = new System.Drawing.Point(6, 72);
            this.lsv_allItems.Name = "lsv_allItems";
            this.lsv_allItems.Size = new System.Drawing.Size(333, 229);
            this.lsv_allItems.TabIndex = 2;
            this.lsv_allItems.UseCompatibleStateImageBehavior = false;
            this.lsv_allItems.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsv_allItems_ColumnClick);
            this.lsv_allItems.SelectedIndexChanged += new System.EventHandler(this.lsv_allItems_SelectedIndexChanged);
            // 
            // txt_itemPoolName
            // 
            this.txt_itemPoolName.Location = new System.Drawing.Point(94, 19);
            this.txt_itemPoolName.Name = "txt_itemPoolName";
            this.txt_itemPoolName.Size = new System.Drawing.Size(161, 20);
            this.txt_itemPoolName.TabIndex = 1;
            // 
            // lbl_poolName
            // 
            this.lbl_poolName.AutoSize = true;
            this.lbl_poolName.Location = new System.Drawing.Point(3, 22);
            this.lbl_poolName.Name = "lbl_poolName";
            this.lbl_poolName.Size = new System.Drawing.Size(85, 13);
            this.lbl_poolName.TabIndex = 1;
            this.lbl_poolName.Text = "Item Pool Name:";
            // 
            // lbl_items
            // 
            this.lbl_items.AutoSize = true;
            this.lbl_items.Location = new System.Drawing.Point(3, 56);
            this.lbl_items.Name = "lbl_items";
            this.lbl_items.Size = new System.Drawing.Size(35, 13);
            this.lbl_items.TabIndex = 0;
            this.lbl_items.Text = "Items:";
            this.poolToolTip.SetToolTip(this.lbl_items, "These are all the items available for your pool.\r\n\r\nIt\'s the entire Items databas" +
        "e. Feel free to choose whatever\r\nyou want!");
            // 
            // poolToolTip
            // 
            this.poolToolTip.AutoPopDelay = 10000;
            this.poolToolTip.InitialDelay = 500;
            this.poolToolTip.ReshowDelay = 100;
            // 
            // lbl_totalChance
            // 
            this.lbl_totalChance.AutoSize = true;
            this.lbl_totalChance.Location = new System.Drawing.Point(567, 337);
            this.lbl_totalChance.Name = "lbl_totalChance";
            this.lbl_totalChance.Size = new System.Drawing.Size(78, 13);
            this.lbl_totalChance.TabIndex = 12;
            this.lbl_totalChance.Text = "Total chances:";
            this.poolToolTip.SetToolTip(this.lbl_totalChance, "Represents the sum of the drop chances of all items in\r\nthe item pool.");
            // 
            // CreateItemPool
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(917, 401);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.btn_create);
            this.Controls.Add(this.pnl_createItem);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "CreateItemPool";
            this.Text = "Create a new Item Pool";
            this.pnl_createItem.ResumeLayout(false);
            this.pnl_createItem.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.num_dropChance)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picbox_thumbnail)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_create;
        private System.Windows.Forms.Panel pnl_createItem;
        private System.Windows.Forms.Button btn_removeItem;
        private System.Windows.Forms.ListView lsv_poolItems;
        private System.Windows.Forms.Label lbl_poolItems;
        private System.Windows.Forms.Button btn_itemAdd;
        private System.Windows.Forms.PictureBox picbox_thumbnail;
        private System.Windows.Forms.Label lbl_thumbnail;
        private System.Windows.Forms.ListView lsv_allItems;
        private System.Windows.Forms.TextBox txt_itemPoolName;
        private System.Windows.Forms.Label lbl_poolName;
        private System.Windows.Forms.Label lbl_items;
        private System.Windows.Forms.NumericUpDown num_dropChance;
        private System.Windows.Forms.Label lbl_dropChancePercent;
        private System.Windows.Forms.Label lbl_dropChance;
        private System.Windows.Forms.Button btn_modifyDropChance;
        private System.Windows.Forms.ToolTip poolToolTip;
        private System.Windows.Forms.Label lbl_totalChance;
    }
}