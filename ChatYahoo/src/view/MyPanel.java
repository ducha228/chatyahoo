package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private Image img = null;
	public MyPanel(String fileName) {
		this.setLayout(null);
		this.img = new ImageIcon(fileName).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
	}
}