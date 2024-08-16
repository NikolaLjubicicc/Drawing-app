package command;

import geometry.Shape;
import mvc.DrawingModel;

public class SelectShapeCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private int index;
	
	
	
	public SelectShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		shape.setselected(true);
	}

	@Override
	public void unexecute() {
		shape.setselected(false);

	}
	public String toString() {
		return "SelectShapeCmd -> " +shape;
	}

}
