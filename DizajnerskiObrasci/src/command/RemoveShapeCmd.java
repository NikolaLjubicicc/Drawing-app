package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int original;

	@Override
	public void execute() {
		original= model.getIndex(shape);
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		model.addIndex(original, shape);

	}

}
