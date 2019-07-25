namespace LootGen
{
    partial class CreateItem
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CreateItem));
            this.lbl_template = new System.Windows.Forms.Label();
            this.pnl_createItem = new System.Windows.Forms.Panel();
            this.txt_itemName = new System.Windows.Forms.TextBox();
            this.lbl_itemName = new System.Windows.Forms.Label();
            this.btn_modifyPropValue = new System.Windows.Forms.Button();
            this.cbo_rarity = new System.Windows.Forms.ComboBox();
            this.lbl_itemRarity = new System.Windows.Forms.Label();
            this.btn_chooseImage = new System.Windows.Forms.Button();
            this.btn_remove = new System.Windows.Forms.Button();
            this.btn_add = new System.Windows.Forms.Button();
            this.txt_propertyName = new System.Windows.Forms.TextBox();
            this.lbl_propertyName = new System.Windows.Forms.Label();
            this.imgbox_thumbnail = new System.Windows.Forms.PictureBox();
            this.label1 = new System.Windows.Forms.Label();
            this.txt_itemCategory = new System.Windows.Forms.TextBox();
            this.lbl_itemCategory = new System.Windows.Forms.Label();
            this.txt_propertyValue = new System.Windows.Forms.TextBox();
            this.lbl_editProperty = new System.Windows.Forms.Label();
            this.lstbox_properties = new System.Windows.Forms.ListBox();
            this.lbl_properties = new System.Windows.Forms.Label();
            this.cbo_Template = new System.Windows.Forms.ComboBox();
            this.btn_create = new System.Windows.Forms.Button();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.itemToolTip = new System.Windows.Forms.ToolTip(this.components);
            this.pnl_createItem.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.imgbox_thumbnail)).BeginInit();
            this.SuspendLayout();
            // 
            // lbl_template
            // 
            this.lbl_template.AutoSize = true;
            this.lbl_template.Location = new System.Drawing.Point(23, 39);
            this.lbl_template.Name = "lbl_template";
            this.lbl_template.Size = new System.Drawing.Size(54, 13);
            this.lbl_template.TabIndex = 0;
            this.lbl_template.Text = "Template:";
            this.itemToolTip.SetToolTip(this.lbl_template, "The template your item uses, if any.\r\n\r\nIf you don\'t want a template, choose \"Non" +
        "e\".\r\n\r\nA Template adds properties to your item automatically.");
            this.lbl_template.Click += new System.EventHandler(this.lbl_template_Click);
            // 
            // pnl_createItem
            // 
            this.pnl_createItem.Controls.Add(this.txt_itemName);
            this.pnl_createItem.Controls.Add(this.lbl_itemName);
            this.pnl_createItem.Controls.Add(this.btn_modifyPropValue);
            this.pnl_createItem.Controls.Add(this.cbo_rarity);
            this.pnl_createItem.Controls.Add(this.lbl_itemRarity);
            this.pnl_createItem.Controls.Add(this.btn_chooseImage);
            this.pnl_createItem.Controls.Add(this.btn_remove);
            this.pnl_createItem.Controls.Add(this.btn_add);
            this.pnl_createItem.Controls.Add(this.txt_propertyName);
            this.pnl_createItem.Controls.Add(this.lbl_propertyName);
            this.pnl_createItem.Controls.Add(this.imgbox_thumbnail);
            this.pnl_createItem.Controls.Add(this.label1);
            this.pnl_createItem.Controls.Add(this.txt_itemCategory);
            this.pnl_createItem.Controls.Add(this.lbl_itemCategory);
            this.pnl_createItem.Controls.Add(this.txt_propertyValue);
            this.pnl_createItem.Controls.Add(this.lbl_editProperty);
            this.pnl_createItem.Controls.Add(this.lstbox_properties);
            this.pnl_createItem.Controls.Add(this.lbl_properties);
            this.pnl_createItem.Controls.Add(this.cbo_Template);
            this.pnl_createItem.Controls.Add(this.lbl_template);
            this.pnl_createItem.Location = new System.Drawing.Point(12, 12);
            this.pnl_createItem.Name = "pnl_createItem";
            this.pnl_createItem.Size = new System.Drawing.Size(692, 330);
            this.pnl_createItem.TabIndex = 1;
            // 
            // txt_itemName
            // 
            this.txt_itemName.Location = new System.Drawing.Point(68, 4);
            this.txt_itemName.Name = "txt_itemName";
            this.txt_itemName.Size = new System.Drawing.Size(100, 20);
            this.txt_itemName.TabIndex = 1;
            // 
            // lbl_itemName
            // 
            this.lbl_itemName.AutoSize = true;
            this.lbl_itemName.Location = new System.Drawing.Point(26, 4);
            this.lbl_itemName.Name = "lbl_itemName";
            this.lbl_itemName.Size = new System.Drawing.Size(38, 13);
            this.lbl_itemName.TabIndex = 20;
            this.lbl_itemName.Text = "Name:";
            this.itemToolTip.SetToolTip(this.lbl_itemName, "The name of your item. This will be used everywhere\r\nelse!");
            // 
            // btn_modifyPropValue
            // 
            this.btn_modifyPropValue.Location = new System.Drawing.Point(107, 299);
            this.btn_modifyPropValue.Name = "btn_modifyPropValue";
            this.btn_modifyPropValue.Size = new System.Drawing.Size(55, 23);
            this.btn_modifyPropValue.TabIndex = 9;
            this.btn_modifyPropValue.Text = "Modify";
            this.btn_modifyPropValue.UseVisualStyleBackColor = true;
            this.btn_modifyPropValue.Click += new System.EventHandler(this.btn_modifyPropValue_Click);
            // 
            // cbo_rarity
            // 
            this.cbo_rarity.FormattingEnabled = true;
            this.cbo_rarity.Location = new System.Drawing.Point(489, 36);
            this.cbo_rarity.Name = "cbo_rarity";
            this.cbo_rarity.Size = new System.Drawing.Size(100, 21);
            this.cbo_rarity.TabIndex = 4;
            // 
            // lbl_itemRarity
            // 
            this.lbl_itemRarity.AutoSize = true;
            this.lbl_itemRarity.Location = new System.Drawing.Point(446, 39);
            this.lbl_itemRarity.Name = "lbl_itemRarity";
            this.lbl_itemRarity.Size = new System.Drawing.Size(37, 13);
            this.lbl_itemRarity.TabIndex = 17;
            this.lbl_itemRarity.Text = "Rarity:";
            this.itemToolTip.SetToolTip(this.lbl_itemRarity, "The rarity of your item. It reflects the quality of your\r\nitem. The higher the le" +
        "tter, the better exception for the\r\nletter \"S\", which is the highest letter.\r\n\r\n" +
        "SS and SSS are better than S.");
            // 
            // btn_chooseImage
            // 
            this.btn_chooseImage.Location = new System.Drawing.Point(449, 299);
            this.btn_chooseImage.Name = "btn_chooseImage";
            this.btn_chooseImage.Size = new System.Drawing.Size(200, 23);
            this.btn_chooseImage.TabIndex = 16;
            this.btn_chooseImage.Text = "Choose Image...";
            this.btn_chooseImage.UseVisualStyleBackColor = true;
            this.btn_chooseImage.Click += new System.EventHandler(this.btn_chooseImage_Click);
            // 
            // btn_remove
            // 
            this.btn_remove.Location = new System.Drawing.Point(168, 299);
            this.btn_remove.Name = "btn_remove";
            this.btn_remove.Size = new System.Drawing.Size(75, 23);
            this.btn_remove.TabIndex = 10;
            this.btn_remove.Text = "Remove";
            this.btn_remove.UseVisualStyleBackColor = true;
            this.btn_remove.Click += new System.EventHandler(this.btn_remove_Click);
            // 
            // btn_add
            // 
            this.btn_add.Location = new System.Drawing.Point(26, 299);
            this.btn_add.Name = "btn_add";
            this.btn_add.Size = new System.Drawing.Size(75, 23);
            this.btn_add.TabIndex = 8;
            this.btn_add.Text = "Add";
            this.btn_add.UseVisualStyleBackColor = true;
            this.btn_add.Click += new System.EventHandler(this.btn_add_Click);
            // 
            // txt_propertyName
            // 
            this.txt_propertyName.Location = new System.Drawing.Point(26, 274);
            this.txt_propertyName.Name = "txt_propertyName";
            this.txt_propertyName.Size = new System.Drawing.Size(217, 20);
            this.txt_propertyName.TabIndex = 6;
            // 
            // lbl_propertyName
            // 
            this.lbl_propertyName.AutoSize = true;
            this.lbl_propertyName.Location = new System.Drawing.Point(23, 258);
            this.lbl_propertyName.Name = "lbl_propertyName";
            this.lbl_propertyName.Size = new System.Drawing.Size(80, 13);
            this.lbl_propertyName.TabIndex = 12;
            this.lbl_propertyName.Text = "Property Name:";
            // 
            // imgbox_thumbnail
            // 
            this.imgbox_thumbnail.Location = new System.Drawing.Point(449, 94);
            this.imgbox_thumbnail.Name = "imgbox_thumbnail";
            this.imgbox_thumbnail.Size = new System.Drawing.Size(200, 200);
            this.imgbox_thumbnail.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.imgbox_thumbnail.TabIndex = 11;
            this.imgbox_thumbnail.TabStop = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(446, 78);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(59, 13);
            this.label1.TabIndex = 10;
            this.label1.Text = "Thumbnail:";
            this.itemToolTip.SetToolTip(this.label1, "This is the image that will be associated with your item.\r\n\r\nIt will show up a lo" +
        "t.");
            // 
            // txt_itemCategory
            // 
            this.txt_itemCategory.Location = new System.Drawing.Point(322, 36);
            this.txt_itemCategory.Name = "txt_itemCategory";
            this.txt_itemCategory.Size = new System.Drawing.Size(100, 20);
            this.txt_itemCategory.TabIndex = 3;
            // 
            // lbl_itemCategory
            // 
            this.lbl_itemCategory.AutoSize = true;
            this.lbl_itemCategory.Location = new System.Drawing.Point(241, 39);
            this.lbl_itemCategory.Name = "lbl_itemCategory";
            this.lbl_itemCategory.Size = new System.Drawing.Size(75, 13);
            this.lbl_itemCategory.TabIndex = 6;
            this.lbl_itemCategory.Text = "Item Category:";
            this.itemToolTip.SetToolTip(this.lbl_itemCategory, "The category of your item. This is optionnal.\r\n\r\nIt is used to sort your items be" +
        "tter.");
            this.lbl_itemCategory.Click += new System.EventHandler(this.lbl_itemCategory_Click);
            // 
            // txt_propertyValue
            // 
            this.txt_propertyValue.Location = new System.Drawing.Point(252, 94);
            this.txt_propertyValue.Multiline = true;
            this.txt_propertyValue.Name = "txt_propertyValue";
            this.txt_propertyValue.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txt_propertyValue.Size = new System.Drawing.Size(191, 200);
            this.txt_propertyValue.TabIndex = 7;
            this.txt_propertyValue.TextChanged += new System.EventHandler(this.txt_propertyValue_TextChanged);
            // 
            // lbl_editProperty
            // 
            this.lbl_editProperty.AutoSize = true;
            this.lbl_editProperty.Location = new System.Drawing.Point(249, 78);
            this.lbl_editProperty.Name = "lbl_editProperty";
            this.lbl_editProperty.Size = new System.Drawing.Size(79, 13);
            this.lbl_editProperty.TabIndex = 4;
            this.lbl_editProperty.Text = "Property Value:";
            this.itemToolTip.SetToolTip(this.lbl_editProperty, "The value of the property you have selected, in the list on\r\nthe left.\r\n\r\nJust en" +
        "ter the value you want here.");
            this.lbl_editProperty.Click += new System.EventHandler(this.lbl_editProperty_Click);
            // 
            // lstbox_properties
            // 
            this.lstbox_properties.FormattingEnabled = true;
            this.lstbox_properties.Location = new System.Drawing.Point(26, 78);
            this.lstbox_properties.Name = "lstbox_properties";
            this.lstbox_properties.Size = new System.Drawing.Size(217, 173);
            this.lstbox_properties.TabIndex = 5;
            this.lstbox_properties.SelectedIndexChanged += new System.EventHandler(this.lstbox_properties_SelectedIndexChanged);
            // 
            // lbl_properties
            // 
            this.lbl_properties.AutoSize = true;
            this.lbl_properties.Location = new System.Drawing.Point(23, 62);
            this.lbl_properties.Name = "lbl_properties";
            this.lbl_properties.Size = new System.Drawing.Size(80, 13);
            this.lbl_properties.TabIndex = 2;
            this.lbl_properties.Text = "Item Properties:";
            this.itemToolTip.SetToolTip(this.lbl_properties, resources.GetString("lbl_properties.ToolTip"));
            // 
            // cbo_Template
            // 
            this.cbo_Template.FormattingEnabled = true;
            this.cbo_Template.Location = new System.Drawing.Point(83, 36);
            this.cbo_Template.Name = "cbo_Template";
            this.cbo_Template.Size = new System.Drawing.Size(121, 21);
            this.cbo_Template.TabIndex = 2;
            this.cbo_Template.SelectedIndexChanged += new System.EventHandler(this.cbo_Template_SelectedIndexChanged);
            // 
            // btn_create
            // 
            this.btn_create.Location = new System.Drawing.Point(534, 348);
            this.btn_create.Name = "btn_create";
            this.btn_create.Size = new System.Drawing.Size(170, 23);
            this.btn_create.TabIndex = 11;
            this.btn_create.Text = "Create";
            this.btn_create.UseVisualStyleBackColor = true;
            this.btn_create.Click += new System.EventHandler(this.btn_create_Click);
            // 
            // btn_cancel
            // 
            this.btn_cancel.Location = new System.Drawing.Point(12, 348);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(170, 23);
            this.btn_cancel.TabIndex = 12;
            this.btn_cancel.Text = "Cancel";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // CreateItem
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(716, 383);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.btn_create);
            this.Controls.Add(this.pnl_createItem);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "CreateItem";
            this.Text = "Create a new Item";
            this.pnl_createItem.ResumeLayout(false);
            this.pnl_createItem.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.imgbox_thumbnail)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label lbl_template;
        private System.Windows.Forms.Panel pnl_createItem;
        private System.Windows.Forms.Label lbl_editProperty;
        private System.Windows.Forms.ListBox lstbox_properties;
        private System.Windows.Forms.Label lbl_properties;
        private System.Windows.Forms.ComboBox cbo_Template;
        private System.Windows.Forms.TextBox txt_itemCategory;
        private System.Windows.Forms.Label lbl_itemCategory;
        private System.Windows.Forms.PictureBox imgbox_thumbnail;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txt_propertyValue;
        private System.Windows.Forms.Button btn_create;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_chooseImage;
        private System.Windows.Forms.Button btn_remove;
        private System.Windows.Forms.Button btn_add;
        private System.Windows.Forms.TextBox txt_propertyName;
        private System.Windows.Forms.Label lbl_propertyName;
        private System.Windows.Forms.ComboBox cbo_rarity;
        private System.Windows.Forms.Label lbl_itemRarity;
        private System.Windows.Forms.Button btn_modifyPropValue;
        private System.Windows.Forms.TextBox txt_itemName;
        private System.Windows.Forms.Label lbl_itemName;
        private System.Windows.Forms.ToolTip itemToolTip;
    }
}