package mvc;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingView extends JPanel {
	public DrawingView() {
	}
	
	private DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	public void paint(Graphics g) {
		super.paint(g);
		model.getShapes().forEach(e -> e.Draw(g));
	}
}
