package fr.trxyy.alternative.bootstrap.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class JCircleProgressBar extends JProgressBar {

	public void updateUI() {
		super.updateUI();
		this.setUI(new ProgressCircleUI());
		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		this.setForeground(new Color(0.0F, 1.0F, 0.0F, 0.75F));
	}
}