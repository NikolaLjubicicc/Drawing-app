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
		original.clone(donut);
		donut.clone(newState);

	}

	@Override
	public void unexecute() {
		donut.clone(original);
	}
	public String toString() {
		return "UpdateDonutCmd -> " + donut + ", newState: " + newState ;
	}

}
