namespace LootGen
{
    partial class CreateTemplate
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CreateTemplate));
            this.pnl_createItem = new System.Windows.Forms.Panel();
            this.txt_templateName = new System.Windows.Forms.TextBox();
            this.lbl_templateName = new System.Windows.Forms.Label();
            this.btn_remove = new System.Windows.Forms.Button();
            this.btn_modify = new System.Windows.Forms.Button();
            this.txt_selectedName = new System.Windows.Forms.TextBox();
            this.lbl_selectedName = new System.Windows.Forms.Label();
            this.btn_addProperty = new System.Windows.Forms.Button();
            this.txt_propertyName = new System.Windows.Forms.TextBox();
            this.lbl_propertyName = new System.Windows.Forms.Label();
            this.lstbox_properties = new System.Windows.Forms.ListBox();
            this.lbl_properties = new System.Windows.Forms.Label();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.btn_create = new System.Windows.Forms.Button();
            this.templateToolTip = new System.Windows.Forms.ToolTip(this.components);
            this.pnl_createItem.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnl_createItem
            // 
            this.pnl_createItem.Controls.Add(this.txt_templateName);
            this.pnl_createItem.Controls.Add(this.lbl_templateName);
            this.pnl_createItem.Controls.Add(this.btn_remove);
            this.pnl_createItem.Controls.Add(this.btn_modify);
            this.pnl_createItem.Controls.Add(this.txt_selectedName);
            this.pnl_createItem.Controls.Add(this.lbl_selectedName);
            this.pnl_createItem.Controls.Add(this.btn_addProperty);
            this.pnl_createItem.Controls.Add(this.txt_propertyName);
            this.pnl_createItem.Controls.Add(this.lbl_propertyName);
            this.pnl_createItem.Controls.Add(this.lstbox_properties);
            this.pnl_createItem.Controls.Add(this.lbl_properties);
            this.pnl_createItem.Location = new System.Drawing.Point(12, 12);
            this.pnl_createItem.Name = "pnl_createItem";
            this.pnl_createItem.Size = new System.Drawing.Size(692, 319);
            this.pnl_createItem.TabIndex = 2;
            this.pnl_createItem.Paint += new System.Windows.Forms.PaintEventHandler(this.pnl_createItem_Paint);
            // 
            // txt_templateName
            // 
            this.txt_templateName.Location = new System.Drawing.Point(110, 16);
            this.txt_templateName.Name = "txt_templateName";
            this.txt_templateName.Size = new System.Drawing.Size(129, 20);
            this.txt_templateName.TabIndex = 1;
            // 
            // lbl_templateName
            // 
            this.lbl_templateName.AutoSize = true;
            this.lbl_templateName.Location = new System.Drawing.Point(18, 16);
            this.lbl_templateName.Name = "lbl_templateName";
            this.lbl_templateName.Size = new System.Drawing.Size(85, 13);
            this.lbl_templateName.TabIndex = 11;
            this.lbl_templateName.Text = "Template Name:";
            this.templateToolTip.SetToolTip(this.lbl_templateName, resources.GetString("lbl_templateName.ToolTip"));
            // 
            // btn_remove
            // 
            this.btn_remove.Location = new System.Drawing.Point(470, 121);
            this.btn_remove.Name = "btn_remove";
            this.btn_remove.Size = new System.Drawing.Size(75, 23);
            this.btn_remove.TabIndex = 7;
            this.btn_remove.Text = "Remove";
            this.btn_remove.UseVisualStyleBackColor = true;
            this.btn_remove.Click += new System.EventHandler(this.btn_remove_Click);
            // 
            // btn_modify
            // 
            this.btn_modify.Location = new System.Drawing.Point(470, 92);
            this.btn_modify.Name = "btn_modify";
            this.btn_modify.Size = new System.Drawing.Size(75, 23);
            this.btn_modify.TabIndex = 6;
            this.btn_modify.Text = "Modify";
            this.btn_modify.UseVisualStyleBackColor = true;
            this.btn_modify.Click += new System.EventHandler(this.btn_modify_Click);
            // 
            // txt_selectedName
            // 
            this.txt_selectedName.Location = new System.Drawing.Point(470, 66);
            this.txt_selectedName.Name = "txt_selectedName";
            this.txt_selectedName.Size = new System.Drawing.Size(197, 20);
            this.txt_selectedName.TabIndex = 5;
            // 
            // lbl_selectedName
            // 
            this.lbl_selectedName.AutoSize = true;
            this.lbl_selectedName.Location = new System.Drawing.Point(467, 50);
            this.lbl_selectedName.Name = "lbl_selectedName";
            this.lbl_selectedName.Size = new System.Drawing.Size(52, 13);
            this.lbl_selectedName.TabIndex = 7;
            this.lbl_selectedName.Text = "Selected:";
            this.templateToolTip.SetToolTip(this.lbl_selectedName, resources.GetString("lbl_selectedName.ToolTip"));
            // 
            // btn_addProperty
            // 
            this.btn_addProperty.Location = new System.Drawing.Point(21, 92);
            this.btn_addProperty.Name = "btn_addProperty";
            this.btn_addProperty.Size = new System.Drawing.Size(75, 23);
            this.btn_addProperty.TabIndex = 3;
            this.btn_addProperty.Text = "Add";
            this.btn_addProperty.UseVisualStyleBackColor = true;
            this.btn_addProperty.Click += new System.EventHandler(this.btn_addProperty_Click);
            // 
            // txt_propertyName
            // 
            this.txt_propertyName.Location = new System.Drawing.Point(21, 66);
            this.txt_propertyName.Name = "txt_propertyName";
            this.txt_propertyName.Size = new System.Drawing.Size(197, 20);
            this.txt_propertyName.TabIndex = 2;
            // 
            // lbl_propertyName
            // 
            this.lbl_propertyName.AutoSize = true;
            this.lbl_propertyName.Location = new System.Drawing.Point(18, 50);
            this.lbl_propertyName.Name = "lbl_propertyName";
            this.lbl_propertyName.Size = new System.Drawing.Size(80, 13);
            this.lbl_propertyName.TabIndex = 4;
            this.lbl_propertyName.Text = "Property Name:";
            this.templateToolTip.SetToolTip(this.lbl_propertyName, resources.GetString("lbl_propertyName.ToolTip"));
            // 
            // lstbox_properties
            // 
            this.lstbox_properties.FormattingEnabled = true;
            this.lstbox_properties.Location = new System.Drawing.Point(239, 66);
            this.lstbox_properties.Name = "lstbox_properties";
            this.lstbox_properties.Size = new System.Drawing.Size(217, 225);
            this.lstbox_properties.TabIndex = 4;
            this.lstbox_properties.SelectedIndexChanged += new System.EventHandler(this.lstbox_properties_SelectedIndexChanged);
            // 
            // lbl_properties
            // 
            this.lbl_properties.AutoSize = true;
            this.lbl_properties.Location = new System.Drawing.Point(236, 50);
            this.lbl_properties.Name = "lbl_properties";
            this.lbl_properties.Size = new System.Drawing.Size(80, 13);
            this.lbl_properties.TabIndex = 2;
            this.lbl_properties.Text = "Item Properties:";
            this.lbl_properties.Click += new System.EventHandler(this.lbl_properties_Click);
            // 
            // btn_cancel
            // 
            this.btn_cancel.Location = new System.Drawing.Point(12, 337);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(170, 23);
            this.btn_cancel.TabIndex = 9;
            this.btn_cancel.Text = "Cancel";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // btn_create
            // 
            this.btn_create.Location = new System.Drawing.Point(534, 337);
            this.btn_create.Name = "btn_create";
            this.btn_create.Size = new System.Drawing.Size(170, 23);
            this.btn_create.TabIndex = 8;
            this.btn_create.Text = "Create";
            this.btn_create.UseVisualStyleBackColor = true;
            this.btn_create.Click += new System.EventHandler(this.btn_create_Click);
            // 
            // templateToolTip
            // 
            this.templateToolTip.AutoPopDelay = 10000;
            this.templateToolTip.InitialDelay = 500;
            this.templateToolTip.ReshowDelay = 100;
            // 
            // CreateTemplate
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(716, 369);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.btn_create);
            this.Controls.Add(this.pnl_createItem);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "CreateTemplate";
            this.Text = "Create a new Template";
            this.Load += new System.EventHandler(this.CreateTemplate_Load);
            this.pnl_createItem.ResumeLayout(false);
            this.pnl_createItem.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel pnl_createItem;
        private System.Windows.Forms.ListBox lstbox_properties;
        private System.Windows.Forms.Label lbl_properties;
        private System.Windows.Forms.Button btn_remove;
        private System.Windows.Forms.Button btn_modify;
        private System.Windows.Forms.TextBox txt_selectedName;
        private System.Windows.Forms.Label lbl_selectedName;
        private System.Windows.Forms.Button btn_addProperty;
        private System.Windows.Forms.TextBox txt_propertyName;
        private System.Windows.Forms.Label lbl_propertyName;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_create;
        private System.Windows.Forms.TextBox txt_templateName;
        private System.Windows.Forms.Label lbl_templateName;
        private System.Windows.Forms.ToolTip templateToolTip;
    }
}