package mvc;

import javax.swing.JPanel;

public class DrawingView extends JPanel {
	private DrawingModel model;

	public void setModel(DrawingModel model) {
		this.model = model;
	}
}
