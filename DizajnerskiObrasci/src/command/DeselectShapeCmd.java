package command;

import geometry.Shape;
import mvc.DrawingModel;

public class DeselectShapeCmd implements Command {

	Shape shape;
	DrawingModel model;
	
	public DeselectShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		shape.setselected(false);

	}

	@Override
	public void unexecute() {
		shape.setselected(true);

	}

}
