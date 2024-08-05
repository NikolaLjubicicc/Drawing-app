package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command {
	
	private Circle circle;
	private Circle newState;
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle circle, Circle newState) {
		this.circle = circle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original.getCenter().setX(circle.getCenter().getX());
		original.getCenter().setY(circle.getCenter().getY());
		original.setRadius(circle.getRadius());
		original.setColor(circle.getColor());
		original.setInnerColor(circle.getInnerColor());
		
		circle.getCenter().setX(newState.getCenter().getX());
		circle.getCenter().setY(newState.getCenter().getY());
		circle.setRadius(newState.getRadius());
		circle.setColor(newState.getColor());
		circle.setInnerColor(newState.getInnerColor());
		
		

	}

	@Override
	public void unexecute() {
		circle.getCenter().setX(original.getCenter().getX());
		circle.getCenter().setY(original.getCenter().getY());
		circle.setRadius(original.getRadius());
		circle.setColor(original.getColor());
		circle.setInnerColor(original.getInnerColor());

	}

}
