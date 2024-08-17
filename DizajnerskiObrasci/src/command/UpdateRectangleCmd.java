package command;

import geometry.Point;
import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {

	private Rectangle rectangle;
	private Rectangle newState;
	private Rectangle original = new Rectangle(new Point(0,0), 0, 0);
	
	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		this.rectangle = rectangle;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		original.clone(rectangle);
		rectangle.clone(newState);
		
	}

	@Override
	public void unexecute() {
		rectangle.clone(original);

	}
	public String toString() {
		return "UpdateRectangleCmd -> " + original + ", newState: " + newState ;
	}

}
