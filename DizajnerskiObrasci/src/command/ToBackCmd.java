package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int originalIndex;
	private int newIndex;
	
	public ToBackCmd (Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		originalIndex = model.getShapes().indexOf(shape);
		newIndex = originalIndex - 1;
		Collections.swap(model.getShapes(), originalIndex, newIndex);

	}

	@Override
	public void unexecute() {
		originalIndex = newIndex + 1;
		Collections.swap(model.getShapes(), originalIndex, newIndex);

	}
	public String toString() {
		return "ToBackCmd -> " + shape;
	}

}
