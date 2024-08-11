package command;

import geometry.Point;

public class UpdatePointCmd implements Command {

	private Point point;
	private Point newState;
	private Point original = new Point();

	public UpdatePointCmd(Point point, Point newState) {
		this.point = point;
		this.newState = newState;
	}
	@Override
	public void execute() {
		original.clone(point);
		point.clone(newState);

	}

	@Override
	public void unexecute() {
		point.clone(original);
	}
	public String toString() {
		return "UpdatePointCmd -> " + point + ", newState: " + newState ;
	}

}
