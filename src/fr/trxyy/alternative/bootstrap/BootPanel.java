package fr.trxyy.alternative.bootstrap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.trxyy.alternative.bootstrap.ui.JCircleProgressBar;

public final class BootPanel extends JPanel {
	/** ================= LA POLICE D'ECRITURE ================= **/
	public Font titleFont;
	/** ================= LA PROGRESSBAR ================= **/
	public static JCircleProgressBar progressBar = new JCircleProgressBar();
	/** ================= LA CLASSE D'UPDATE ================= **/
	public Downloader downloader;
	/** ================= POUR AFFICHER LE LOGO DU SERVEUR ================= **/
	private BufferedImage logoIcon;

	public BootPanel(final Home home) {
		this.setLayout(new BorderLayout());
		/** ================= ON RECUPERE LE LOGO DU SERVEUR ================= **/
		try {
			this.logoIcon = ImageIO.read(getClass().getResourceAsStream("alternative_api.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** ================= POLICE PERSONNALISEE ================= **/
		InputStream is = null;
		try {
			is = BootPanel.class.getResource("minecraft.ttf").openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.titleFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 15f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		/** ================= ON DEFINI LA POLICE PERSONNALISEE ================= **/
		this.setFont(this.titleFont.deriveFont(20.0F));
		/** ================= ON DEFINI LA CLASSE D'UPDATE ================= **/
		this.downloader = new Downloader(home);
		/** ================= ON AJOUTE LA PROGRESSBAR ================= **/
		this.add(progressBar);
		/** ================= DEMARRAGE DE LA MISE A JOUR ================= **/
		this.updateJar();
	}

	private void updateJar() {
		Thread thread = new Thread() {
			public void run() {
				downloader.run();
			}
		};
		thread.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (this.logoIcon != null) {
			g.drawImage(this.logoIcon, 0, 40, 340, 130, this);
		}
		g.setColor(Color.WHITE);
		String percentageToDisplay = String.valueOf(downloader.getProgress()) + "%";
		g.drawString(percentageToDisplay, 415 - g.getFontMetrics(this.titleFont).stringWidth(percentageToDisplay) / 2 + 5, 110);
	}

	public static JCircleProgressBar getProgressBar() {
		return progressBar;
	}
}
