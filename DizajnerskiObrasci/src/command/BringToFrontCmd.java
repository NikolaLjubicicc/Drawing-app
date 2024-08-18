package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private int index;
	
	public BringToFrontCmd (Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		index = model.getIndex(shape); 
		model.remove(shape);
		model.add(shape);

	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.addIndex(index, shape);

	}
	public String toString() {
		return "BringToFrontCmd -> " + shape;
			
	}

}
