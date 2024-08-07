package command;

import geometry.Shape;
import mvc.DrawingModel;

public class SelectShapeCmd implements Command {

	Shape shape;
	DrawingModel model;
	
	
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

}
