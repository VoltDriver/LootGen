namespace LootGen
{
    partial class FrmManageItems
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FrmManageItems));
            this.lsv_items = new System.Windows.Forms.ListView();
            this.lbl_thumbnail = new System.Windows.Forms.Label();
            this.picbox_thumbnail = new System.Windows.Forms.PictureBox();
            this.btn_edit = new System.Windows.Forms.Button();
            this.btn_delete = new System.Windows.Forms.Button();
            this.btn_close = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.picbox_thumbnail)).BeginInit();
            this.SuspendLayout();
            // 
            // lsv_items
            // 
            this.lsv_items.Location = new System.Drawing.Point(12, 12);
            this.lsv_items.Name = "lsv_items";
            this.lsv_items.Size = new System.Drawing.Size(438, 346);
            this.lsv_items.TabIndex = 1;
            this.lsv_items.UseCompatibleStateImageBehavior = false;
            this.lsv_items.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsv_items_ColumnClick);
            this.lsv_items.SelectedIndexChanged += new System.EventHandler(this.lsv_items_SelectedIndexChanged);
            // 
            // lbl_thumbnail
            // 
            this.lbl_thumbnail.AutoSize = true;
            this.lbl_thumbnail.Location = new System.Drawing.Point(457, 13);
            this.lbl_thumbnail.Name = "lbl_thumbnail";
            this.lbl_thumbnail.Size = new System.Drawing.Size(59, 13);
            this.lbl_thumbnail.TabIndex = 1;
            this.lbl_thumbnail.Text = "Thumbnail:";
            // 
            // picbox_thumbnail
            // 
            this.picbox_thumbnail.Location = new System.Drawing.Point(460, 40);
            this.picbox_thumbnail.Name = "picbox_thumbnail";
            this.picbox_thumbnail.Size = new System.Drawing.Size(200, 200);
            this.picbox_thumbnail.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picbox_thumbnail.TabIndex = 2;
            this.picbox_thumbnail.TabStop = false;
            // 
            // btn_edit
            // 
            this.btn_edit.Location = new System.Drawing.Point(460, 247);
            this.btn_edit.Name = "btn_edit";
            this.btn_edit.Size = new System.Drawing.Size(200, 23);
            this.btn_edit.TabIndex = 2;
            this.btn_edit.Text = "Edit";
            this.btn_edit.UseVisualStyleBackColor = true;
            this.btn_edit.Click += new System.EventHandler(this.btn_edit_Click);
            // 
            // btn_delete
            // 
            this.btn_delete.Location = new System.Drawing.Point(460, 276);
            this.btn_delete.Name = "btn_delete";
            this.btn_delete.Size = new System.Drawing.Size(200, 23);
            this.btn_delete.TabIndex = 3;
            this.btn_delete.Text = "Delete";
            this.btn_delete.UseVisualStyleBackColor = true;
            this.btn_delete.Click += new System.EventHandler(this.btn_delete_Click);
            // 
            // btn_close
            // 
            this.btn_close.Location = new System.Drawing.Point(231, 366);
            this.btn_close.Name = "btn_close";
            this.btn_close.Size = new System.Drawing.Size(200, 23);
            this.btn_close.TabIndex = 4;
            this.btn_close.Text = "Close";
            this.btn_close.UseVisualStyleBackColor = true;
            this.btn_close.Click += new System.EventHandler(this.btn_close_Click);
            // 
            // FrmManageItems
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(675, 401);
            this.Controls.Add(this.btn_close);
            this.Controls.Add(this.btn_delete);
            this.Controls.Add(this.btn_edit);
            this.Controls.Add(this.picbox_thumbnail);
            this.Controls.Add(this.lbl_thumbnail);
            this.Controls.Add(this.lsv_items);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FrmManageItems";
            this.Text = "Manage Items";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.FrmManageItems_FormClosing);
            ((System.ComponentModel.ISupportInitialize)(this.picbox_thumbnail)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView lsv_items;
        private System.Windows.Forms.Label lbl_thumbnail;
        private System.Windows.Forms.PictureBox picbox_thumbnail;
        private System.Windows.Forms.Button btn_edit;
        private System.Windows.Forms.Button btn_delete;
        private System.Windows.Forms.Button btn_close;
    }
}