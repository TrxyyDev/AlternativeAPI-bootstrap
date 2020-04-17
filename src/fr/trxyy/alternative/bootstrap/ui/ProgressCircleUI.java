package fr.trxyy.alternative.bootstrap.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;

import fr.trxyy.alternative.bootstrap.BootstrapConstants;

public class ProgressCircleUI extends BasicProgressBarUI {
	public Dimension getPreferredSize(JComponent c) {
		Dimension d = super.getPreferredSize(c);
		int v = Math.max(d.width, d.height);
		d.setSize(v, v);
		return d;
	}

	public void paint(Graphics g, JComponent c) {
		Insets b = this.progressBar.getInsets();
		int barRectWidth = this.progressBar.getWidth() - b.right - b.left + 350;
		int barRectHeight = this.progressBar.getHeight() - b.top - b.bottom;
		if (barRectWidth <= 0 || barRectHeight <= 0) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double degree = 360.0D * this.progressBar.getPercentComplete();
		double sz = Math.min(barRectWidth, barRectHeight);
		double cx = b.left + barRectWidth * 0.5D;
		double cy = b.top + barRectHeight * 0.5D;
		double or = sz * 0.5D;

		double ir = or * 0.5D;
		Shape inner = new Ellipse2D.Double(cx - ir, cy - ir, ir * 2.0D, ir * 2.0D);
		Shape outer = new Ellipse2D.Double(cx - or, cy - or, sz, sz);
		Shape sector = new Arc2D.Double(cx - or, cy - or, sz, sz, 90.0D - degree, degree, 2);

		Area foreground = new Area(sector);
		Area background = new Area(outer);
		Area hole = new Area(inner);

		foreground.subtract(hole);
		background.subtract(hole);

		/** ===== COULEUR DE FOND ===== **/
		g2.setPaint(new Color(0.0F, 0.0F, 0.0F, 0.55F));
		g2.fill(background);

		/** ===== COULEUR DE REMPLISSAGE ===== **/
		g2.setPaint(BootstrapConstants.getFillColor());
		g2.fill(foreground);
		g2.dispose();

		if (this.progressBar.isStringPainted()) {
			paintString(g, b.left, b.top, barRectWidth, barRectHeight, 0, b);
		}
	}
}
