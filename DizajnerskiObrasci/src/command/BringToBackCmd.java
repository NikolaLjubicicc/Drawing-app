package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int index;
	
	public BringToBackCmd (Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		index= model.getShapes().indexOf(shape);
		model.remove(shape);
		model.addIndex(0, shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.addIndex(index, shape);
	}

}
