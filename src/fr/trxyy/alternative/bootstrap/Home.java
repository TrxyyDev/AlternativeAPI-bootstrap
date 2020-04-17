package fr.trxyy.alternative.bootstrap;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Home extends JFrame {

	private static final long serialVersionUID = 2047851418739459065L;

	public Home() throws IOException {
		this.setTitle("Bootstrap");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		BootPanel boot = new BootPanel(this);
		this.setContentPane(boot);
		this.setVisible(true);
		this.setIconImage(BootstrapConstants.getResourceLocation().loadImageAWT("favicon.png"));
	}

	public static void main(String[] args) throws IOException {
		new Home();
	}
}
