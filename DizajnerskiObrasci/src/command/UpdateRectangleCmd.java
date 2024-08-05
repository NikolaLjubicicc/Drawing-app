package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {

	private Rectangle rectangle;
	private Rectangle newState;
	private Rectangle original = new Rectangle();
	
	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		this.rectangle = rectangle;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		original.getUpperLeft().setX(rectangle.getUpperLeft().getX());
		original.getUpperLeft().setY(rectangle.getUpperLeft().getY());
		original.setHeight(rectangle.getHeight());
		original.setWidth(rectangle.getWidth());
		original.setColor(rectangle.getColor());
		original.setInnerColor(rectangle.getInnerColor());
		
		rectangle.getUpperLeft().setX(newState.getUpperLeft().getX());
		rectangle.getUpperLeft().setY(newState.getUpperLeft().getY());
		rectangle.setHeight(newState.getHeight());
		rectangle.setWidth(newState.getWidth());
		rectangle.setColor(newState.getColor());
		rectangle.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		rectangle.getUpperLeft().setX(original.getUpperLeft().getX());
		rectangle.getUpperLeft().setY(original.getUpperLeft().getY());
		rectangle.setHeight(original.getHeight());
		rectangle.setWidth(original.getWidth());
		rectangle.setColor(original.getColor());
		rectangle.setInnerColor(original.getInnerColor());

	}

}
