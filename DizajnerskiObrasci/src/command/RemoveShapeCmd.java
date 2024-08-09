package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int original;
	
	public RemoveShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		original= model.getIndex(shape);
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		model.addIndex(original, shape);

	}
	public String toString() {
		return "RemoveShapeCmd -> [ index=" + original + " ]";
	}

}
