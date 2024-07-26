package mvc;

import javax.swing.JFrame;

public class DrawingFrame extends JFrame{
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
}
