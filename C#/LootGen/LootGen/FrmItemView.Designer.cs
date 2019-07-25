namespace LootGen
{
    partial class FrmItemView
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FrmItemView));
            this.lbl_itemRarity = new System.Windows.Forms.Label();
            this.imgbox_thumbnail = new System.Windows.Forms.PictureBox();
            this.label1 = new System.Windows.Forms.Label();
            this.txt_itemCategory = new System.Windows.Forms.TextBox();
            this.lbl_itemCategory = new System.Windows.Forms.Label();
            this.txt_propertyValue = new System.Windows.Forms.TextBox();
            this.lbl_editProperty = new System.Windows.Forms.Label();
            this.lstbox_properties = new System.Windows.Forms.ListBox();
            this.lbl_properties = new System.Windows.Forms.Label();
            this.lbl_template = new System.Windows.Forms.Label();
            this.txt_itemName = new System.Windows.Forms.TextBox();
            this.lbl_itemName = new System.Windows.Forms.Label();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.txt_template = new System.Windows.Forms.TextBox();
            this.txt_rarity = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.imgbox_thumbnail)).BeginInit();
            this.SuspendLayout();
            // 
            // lbl_itemRarity
            // 
            this.lbl_itemRarity.AutoSize = true;
            this.lbl_itemRarity.Location = new System.Drawing.Point(432, 55);
            this.lbl_itemRarity.Name = "lbl_itemRarity";
            this.lbl_itemRarity.Size = new System.Drawing.Size(37, 13);
            this.lbl_itemRarity.TabIndex = 29;
            this.lbl_itemRarity.Text = "Rarity:";
            // 
            // imgbox_thumbnail
            // 
            this.imgbox_thumbnail.Location = new System.Drawing.Point(435, 110);
            this.imgbox_thumbnail.Name = "imgbox_thumbnail";
            this.imgbox_thumbnail.Size = new System.Drawing.Size(200, 200);
            this.imgbox_thumbnail.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.imgbox_thumbnail.TabIndex = 28;
            this.imgbox_thumbnail.TabStop = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(432, 94);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(59, 13);
            this.label1.TabIndex = 27;
            this.label1.Text = "Thumbnail:";
            // 
            // txt_itemCategory
            // 
            this.txt_itemCategory.Location = new System.Drawing.Point(308, 52);
            this.txt_itemCategory.Name = "txt_itemCategory";
            this.txt_itemCategory.ReadOnly = true;
            this.txt_itemCategory.Size = new System.Drawing.Size(100, 20);
            this.txt_itemCategory.TabIndex = 21;
            // 
            // lbl_itemCategory
            // 
            this.lbl_itemCategory.AutoSize = true;
            this.lbl_itemCategory.Location = new System.Drawing.Point(227, 55);
            this.lbl_itemCategory.Name = "lbl_itemCategory";
            this.lbl_itemCategory.Size = new System.Drawing.Size(75, 13);
            this.lbl_itemCategory.TabIndex = 25;
            this.lbl_itemCategory.Text = "Item Category:";
            // 
            // txt_propertyValue
            // 
            this.txt_propertyValue.Location = new System.Drawing.Point(238, 103);
            this.txt_propertyValue.Multiline = true;
            this.txt_propertyValue.Name = "txt_propertyValue";
            this.txt_propertyValue.ReadOnly = true;
            this.txt_propertyValue.Size = new System.Drawing.Size(170, 200);
            this.txt_propertyValue.TabIndex = 26;
            // 
            // lbl_editProperty
            // 
            this.lbl_editProperty.AutoSize = true;
            this.lbl_editProperty.Location = new System.Drawing.Point(235, 87);
            this.lbl_editProperty.Name = "lbl_editProperty";
            this.lbl_editProperty.Size = new System.Drawing.Size(79, 13);
            this.lbl_editProperty.TabIndex = 23;
            this.lbl_editProperty.Text = "Property Value:";
            // 
            // lstbox_properties
            // 
            this.lstbox_properties.FormattingEnabled = true;
            this.lstbox_properties.Location = new System.Drawing.Point(12, 103);
            this.lstbox_properties.Name = "lstbox_properties";
            this.lstbox_properties.Size = new System.Drawing.Size(217, 173);
            this.lstbox_properties.TabIndex = 24;
            this.lstbox_properties.SelectedIndexChanged += new System.EventHandler(this.lstbox_properties_SelectedIndexChanged);
            // 
            // lbl_properties
            // 
            this.lbl_properties.AutoSize = true;
            this.lbl_properties.Location = new System.Drawing.Point(9, 87);
            this.lbl_properties.Name = "lbl_properties";
            this.lbl_properties.Size = new System.Drawing.Size(80, 13);
            this.lbl_properties.TabIndex = 19;
            this.lbl_properties.Text = "Item Properties:";
            // 
            // lbl_template
            // 
            this.lbl_template.AutoSize = true;
            this.lbl_template.Location = new System.Drawing.Point(9, 55);
            this.lbl_template.Name = "lbl_template";
            this.lbl_template.Size = new System.Drawing.Size(54, 13);
            this.lbl_template.TabIndex = 18;
            this.lbl_template.Text = "Template:";
            // 
            // txt_itemName
            // 
            this.txt_itemName.Location = new System.Drawing.Point(269, 9);
            this.txt_itemName.Name = "txt_itemName";
            this.txt_itemName.ReadOnly = true;
            this.txt_itemName.Size = new System.Drawing.Size(100, 20);
            this.txt_itemName.TabIndex = 30;
            // 
            // lbl_itemName
            // 
            this.lbl_itemName.AutoSize = true;
            this.lbl_itemName.Location = new System.Drawing.Point(227, 9);
            this.lbl_itemName.Name = "lbl_itemName";
            this.lbl_itemName.Size = new System.Drawing.Size(38, 13);
            this.lbl_itemName.TabIndex = 31;
            this.lbl_itemName.Text = "Name:";
            // 
            // btn_cancel
            // 
            this.btn_cancel.Location = new System.Drawing.Point(218, 320);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(170, 23);
            this.btn_cancel.TabIndex = 32;
            this.btn_cancel.Text = "Close";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // txt_template
            // 
            this.txt_template.Location = new System.Drawing.Point(69, 52);
            this.txt_template.Name = "txt_template";
            this.txt_template.ReadOnly = true;
            this.txt_template.Size = new System.Drawing.Size(124, 20);
            this.txt_template.TabIndex = 33;
            // 
            // txt_rarity
            // 
            this.txt_rarity.Location = new System.Drawing.Point(475, 52);
            this.txt_rarity.Name = "txt_rarity";
            this.txt_rarity.ReadOnly = true;
            this.txt_rarity.Size = new System.Drawing.Size(100, 20);
            this.txt_rarity.TabIndex = 34;
            // 
            // FrmItemView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(648, 355);
            this.Controls.Add(this.txt_rarity);
            this.Controls.Add(this.txt_template);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.txt_itemName);
            this.Controls.Add(this.lbl_itemName);
            this.Controls.Add(this.lbl_itemRarity);
            this.Controls.Add(this.imgbox_thumbnail);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txt_itemCategory);
            this.Controls.Add(this.lbl_itemCategory);
            this.Controls.Add(this.txt_propertyValue);
            this.Controls.Add(this.lbl_editProperty);
            this.Controls.Add(this.lstbox_properties);
            this.Controls.Add(this.lbl_properties);
            this.Controls.Add(this.lbl_template);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FrmItemView";
            this.Text = "View item";
            ((System.ComponentModel.ISupportInitialize)(this.imgbox_thumbnail)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbl_itemRarity;
        private System.Windows.Forms.PictureBox imgbox_thumbnail;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txt_itemCategory;
        private System.Windows.Forms.Label lbl_itemCategory;
        private System.Windows.Forms.TextBox txt_propertyValue;
        private System.Windows.Forms.Label lbl_editProperty;
        private System.Windows.Forms.ListBox lstbox_properties;
        private System.Windows.Forms.Label lbl_properties;
        private System.Windows.Forms.Label lbl_template;
        private System.Windows.Forms.TextBox txt_itemName;
        private System.Windows.Forms.Label lbl_itemName;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.TextBox txt_template;
        private System.Windows.Forms.TextBox txt_rarity;

    }
}