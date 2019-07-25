namespace LootGen
{
    partial class SeedValue
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SeedValue));
            this.lbl_seed = new System.Windows.Forms.Label();
            this.lbl_seedInfo1 = new System.Windows.Forms.Label();
            this.lbl_seedInfo2 = new System.Windows.Forms.Label();
            this.num_seedValue = new System.Windows.Forms.NumericUpDown();
            this.btn_setSeedValue = new System.Windows.Forms.Button();
            this.btn_resetSeed = new System.Windows.Forms.Button();
            this.seedTooltip = new System.Windows.Forms.ToolTip(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.num_seedValue)).BeginInit();
            this.SuspendLayout();
            // 
            // lbl_seed
            // 
            this.lbl_seed.AutoSize = true;
            this.lbl_seed.Location = new System.Drawing.Point(12, 84);
            this.lbl_seed.Name = "lbl_seed";
            this.lbl_seed.Size = new System.Drawing.Size(35, 13);
            this.lbl_seed.TabIndex = 0;
            this.lbl_seed.Text = "Seed:";
            this.seedTooltip.SetToolTip(this.lbl_seed, resources.GetString("lbl_seed.ToolTip"));
            // 
            // lbl_seedInfo1
            // 
            this.lbl_seedInfo1.AutoSize = true;
            this.lbl_seedInfo1.Location = new System.Drawing.Point(12, 9);
            this.lbl_seedInfo1.Name = "lbl_seedInfo1";
            this.lbl_seedInfo1.Size = new System.Drawing.Size(99, 13);
            this.lbl_seedInfo1.TabIndex = 1;
            this.lbl_seedInfo1.Text = "Enter a seed value.";
            // 
            // lbl_seedInfo2
            // 
            this.lbl_seedInfo2.Location = new System.Drawing.Point(12, 32);
            this.lbl_seedInfo2.Name = "lbl_seedInfo2";
            this.lbl_seedInfo2.Size = new System.Drawing.Size(247, 43);
            this.lbl_seedInfo2.TabIndex = 2;
            this.lbl_seedInfo2.Text = "This will be used by the Random Number Generator to generate loot. If you don\'t k" +
    "now what you\'re doing, it would be best not to enter one.";
            // 
            // num_seedValue
            // 
            this.num_seedValue.Location = new System.Drawing.Point(53, 82);
            this.num_seedValue.Maximum = new decimal(new int[] {
            9999,
            0,
            0,
            0});
            this.num_seedValue.Name = "num_seedValue";
            this.num_seedValue.Size = new System.Drawing.Size(120, 20);
            this.num_seedValue.TabIndex = 1;
            // 
            // btn_setSeedValue
            // 
            this.btn_setSeedValue.Location = new System.Drawing.Point(151, 122);
            this.btn_setSeedValue.Name = "btn_setSeedValue";
            this.btn_setSeedValue.Size = new System.Drawing.Size(104, 23);
            this.btn_setSeedValue.TabIndex = 2;
            this.btn_setSeedValue.Text = "Set Seed Value";
            this.btn_setSeedValue.UseVisualStyleBackColor = true;
            this.btn_setSeedValue.Click += new System.EventHandler(this.btn_setSeedValue_Click);
            // 
            // btn_resetSeed
            // 
            this.btn_resetSeed.Location = new System.Drawing.Point(12, 122);
            this.btn_resetSeed.Name = "btn_resetSeed";
            this.btn_resetSeed.Size = new System.Drawing.Size(104, 23);
            this.btn_resetSeed.TabIndex = 3;
            this.btn_resetSeed.Text = "Reset Seed";
            this.seedTooltip.SetToolTip(this.btn_resetSeed, "Resetting the seed will remove the active seed.\r\nDo this if you want to get rid o" +
        "f the seed.");
            this.btn_resetSeed.UseVisualStyleBackColor = true;
            this.btn_resetSeed.Click += new System.EventHandler(this.btn_resetSeed_Click);
            // 
            // seedTooltip
            // 
            this.seedTooltip.AutoPopDelay = 30000;
            this.seedTooltip.InitialDelay = 500;
            this.seedTooltip.ReshowDelay = 100;
            // 
            // SeedValue
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(267, 157);
            this.Controls.Add(this.btn_resetSeed);
            this.Controls.Add(this.btn_setSeedValue);
            this.Controls.Add(this.num_seedValue);
            this.Controls.Add(this.lbl_seedInfo2);
            this.Controls.Add(this.lbl_seedInfo1);
            this.Controls.Add(this.lbl_seed);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "SeedValue";
            this.Text = "Set Seed Value";
            ((System.ComponentModel.ISupportInitialize)(this.num_seedValue)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbl_seed;
        private System.Windows.Forms.Label lbl_seedInfo1;
        private System.Windows.Forms.Label lbl_seedInfo2;
        private System.Windows.Forms.NumericUpDown num_seedValue;
        private System.Windows.Forms.Button btn_setSeedValue;
        private System.Windows.Forms.Button btn_resetSeed;
        private System.Windows.Forms.ToolTip seedTooltip;
    }
}