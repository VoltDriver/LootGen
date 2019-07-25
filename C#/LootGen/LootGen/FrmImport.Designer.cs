namespace LootGen
{
    partial class FrmImport
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FrmImport));
            this.btn_import = new System.Windows.Forms.Button();
            this.lbl_info = new System.Windows.Forms.Label();
            this.lbl_Items = new System.Windows.Forms.Label();
            this.lbl_itemPools = new System.Windows.Forms.Label();
            this.lbl_templates = new System.Windows.Forms.Label();
            this.txt_itemsPath = new System.Windows.Forms.TextBox();
            this.txt_itemPoolsPath = new System.Windows.Forms.TextBox();
            this.txt_templatesPath = new System.Windows.Forms.TextBox();
            this.btn_items_browse = new System.Windows.Forms.Button();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.btn_itemPools_browse = new System.Windows.Forms.Button();
            this.btn_templates_browse = new System.Windows.Forms.Button();
            this.importTooltip = new System.Windows.Forms.ToolTip(this.components);
            this.chk_overwrite = new System.Windows.Forms.CheckBox();
            this.SuspendLayout();
            // 
            // btn_import
            // 
            this.btn_import.Location = new System.Drawing.Point(269, 150);
            this.btn_import.Name = "btn_import";
            this.btn_import.Size = new System.Drawing.Size(113, 23);
            this.btn_import.TabIndex = 0;
            this.btn_import.Text = "Import";
            this.btn_import.UseVisualStyleBackColor = true;
            this.btn_import.Click += new System.EventHandler(this.btn_import_Click);
            // 
            // lbl_info
            // 
            this.lbl_info.Location = new System.Drawing.Point(119, 9);
            this.lbl_info.Name = "lbl_info";
            this.lbl_info.Size = new System.Drawing.Size(259, 47);
            this.lbl_info.TabIndex = 1;
            this.lbl_info.Text = "Here you can choose to import an existing database. You can choose to import Item" +
    "s, Item Pools, Templates, or all three.";
            this.importTooltip.SetToolTip(this.lbl_info, resources.GetString("lbl_info.ToolTip"));
            // 
            // lbl_Items
            // 
            this.lbl_Items.AutoSize = true;
            this.lbl_Items.Location = new System.Drawing.Point(3, 66);
            this.lbl_Items.Name = "lbl_Items";
            this.lbl_Items.Size = new System.Drawing.Size(67, 13);
            this.lbl_Items.TabIndex = 2;
            this.lbl_Items.Text = "Import Items:";
            // 
            // lbl_itemPools
            // 
            this.lbl_itemPools.AutoSize = true;
            this.lbl_itemPools.Location = new System.Drawing.Point(3, 93);
            this.lbl_itemPools.Name = "lbl_itemPools";
            this.lbl_itemPools.Size = new System.Drawing.Size(91, 13);
            this.lbl_itemPools.TabIndex = 3;
            this.lbl_itemPools.Text = "Import Item Pools:";
            // 
            // lbl_templates
            // 
            this.lbl_templates.AutoSize = true;
            this.lbl_templates.Location = new System.Drawing.Point(3, 120);
            this.lbl_templates.Name = "lbl_templates";
            this.lbl_templates.Size = new System.Drawing.Size(91, 13);
            this.lbl_templates.TabIndex = 4;
            this.lbl_templates.Text = "Import Templates:";
            // 
            // txt_itemsPath
            // 
            this.txt_itemsPath.Location = new System.Drawing.Point(100, 63);
            this.txt_itemsPath.Name = "txt_itemsPath";
            this.txt_itemsPath.Size = new System.Drawing.Size(257, 20);
            this.txt_itemsPath.TabIndex = 5;
            // 
            // txt_itemPoolsPath
            // 
            this.txt_itemPoolsPath.Location = new System.Drawing.Point(100, 90);
            this.txt_itemPoolsPath.Name = "txt_itemPoolsPath";
            this.txt_itemPoolsPath.Size = new System.Drawing.Size(257, 20);
            this.txt_itemPoolsPath.TabIndex = 6;
            // 
            // txt_templatesPath
            // 
            this.txt_templatesPath.Location = new System.Drawing.Point(100, 117);
            this.txt_templatesPath.Name = "txt_templatesPath";
            this.txt_templatesPath.Size = new System.Drawing.Size(257, 20);
            this.txt_templatesPath.TabIndex = 7;
            // 
            // btn_items_browse
            // 
            this.btn_items_browse.Location = new System.Drawing.Point(363, 61);
            this.btn_items_browse.Name = "btn_items_browse";
            this.btn_items_browse.Size = new System.Drawing.Size(27, 23);
            this.btn_items_browse.TabIndex = 8;
            this.btn_items_browse.Text = "...";
            this.btn_items_browse.UseVisualStyleBackColor = true;
            this.btn_items_browse.Click += new System.EventHandler(this.btn_items_browse_Click);
            // 
            // btn_cancel
            // 
            this.btn_cancel.Location = new System.Drawing.Point(12, 150);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(113, 23);
            this.btn_cancel.TabIndex = 9;
            this.btn_cancel.Text = "Cancel";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // btn_itemPools_browse
            // 
            this.btn_itemPools_browse.Location = new System.Drawing.Point(363, 88);
            this.btn_itemPools_browse.Name = "btn_itemPools_browse";
            this.btn_itemPools_browse.Size = new System.Drawing.Size(27, 23);
            this.btn_itemPools_browse.TabIndex = 10;
            this.btn_itemPools_browse.Text = "...";
            this.btn_itemPools_browse.UseVisualStyleBackColor = true;
            this.btn_itemPools_browse.Click += new System.EventHandler(this.btn_itemPools_browse_Click);
            // 
            // btn_templates_browse
            // 
            this.btn_templates_browse.Location = new System.Drawing.Point(363, 115);
            this.btn_templates_browse.Name = "btn_templates_browse";
            this.btn_templates_browse.Size = new System.Drawing.Size(27, 23);
            this.btn_templates_browse.TabIndex = 11;
            this.btn_templates_browse.Text = "...";
            this.btn_templates_browse.UseVisualStyleBackColor = true;
            this.btn_templates_browse.Click += new System.EventHandler(this.btn_templates_browse_Click);
            // 
            // importTooltip
            // 
            this.importTooltip.AutoPopDelay = 10000;
            this.importTooltip.InitialDelay = 500;
            this.importTooltip.ReshowDelay = 100;
            // 
            // chk_overwrite
            // 
            this.chk_overwrite.AutoSize = true;
            this.chk_overwrite.Location = new System.Drawing.Point(6, 38);
            this.chk_overwrite.Name = "chk_overwrite";
            this.chk_overwrite.Size = new System.Drawing.Size(85, 17);
            this.chk_overwrite.TabIndex = 12;
            this.chk_overwrite.Text = "Overwrite All";
            this.importTooltip.SetToolTip(this.chk_overwrite, "Check this if you want the import to overwirte any\r\nduplicate without asking you." +
        "");
            this.chk_overwrite.UseVisualStyleBackColor = true;
            this.chk_overwrite.CheckedChanged += new System.EventHandler(this.chk_overwrite_CheckedChanged);
            // 
            // FrmImport
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(394, 185);
            this.Controls.Add(this.chk_overwrite);
            this.Controls.Add(this.btn_templates_browse);
            this.Controls.Add(this.btn_itemPools_browse);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.btn_items_browse);
            this.Controls.Add(this.txt_templatesPath);
            this.Controls.Add(this.txt_itemPoolsPath);
            this.Controls.Add(this.txt_itemsPath);
            this.Controls.Add(this.lbl_templates);
            this.Controls.Add(this.lbl_itemPools);
            this.Controls.Add(this.lbl_Items);
            this.Controls.Add(this.lbl_info);
            this.Controls.Add(this.btn_import);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FrmImport";
            this.Text = "Import files";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_import;
        private System.Windows.Forms.Label lbl_info;
        private System.Windows.Forms.Label lbl_Items;
        private System.Windows.Forms.Label lbl_itemPools;
        private System.Windows.Forms.Label lbl_templates;
        private System.Windows.Forms.TextBox txt_itemsPath;
        private System.Windows.Forms.TextBox txt_itemPoolsPath;
        private System.Windows.Forms.TextBox txt_templatesPath;
        private System.Windows.Forms.Button btn_items_browse;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_itemPools_browse;
        private System.Windows.Forms.Button btn_templates_browse;
        private System.Windows.Forms.ToolTip importTooltip;
        private System.Windows.Forms.CheckBox chk_overwrite;
    }
}