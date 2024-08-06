package command;

import geometry.Donut;
import geometry.Point;

public class UpdateDonutCmd implements Command {
	
	private Donut donut;
	private Donut newState;
	private Donut original = new Donut(new Point(0,0), 1, 0);

	public UpdateDonutCmd(Donut donut, Donut newState) {
		this.donut = donut;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original.getCenter().setX(donut.getCenter().getX());
		original.getCenter().setY(donut.getCenter().getY());
		original.setRadius(donut.getRadius());
		original.setInnerRadius(donut.getInnerRadius());
		original.setColor(donut.getColor());
		original.setInnerColor(donut.getInnerColor());
		
		donut.getCenter().setX(newState.getCenter().getX());
		donut.getCenter().setY(newState.getCenter().getY());
		donut.setRadius(newState.getRadius());
		donut.setInnerRadius(newState.getInnerRadius());
		donut.setColor(newState.getColor());
		donut.setInnerColor(newState.getInnerColor());

	}

	@Override
	public void unexecute() {
		donut.getCenter().setX(original.getCenter().getX());
		donut.getCenter().setY(original.getCenter().getY());
		donut.setRadius(original.getRadius());
		donut.setInnerRadius(original.getInnerRadius());
		donut.setColor(original.getColor());
		donut.setInnerColor(original.getInnerColor());
	}

}
