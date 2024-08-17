package command;

import adapter.HexagonAdapter;
import geometry.Point;

public class UpdateHexagonCmd implements Command {

	private HexagonAdapter hexagon;
	private HexagonAdapter newState;
	private HexagonAdapter original = new HexagonAdapter(new Point(0,0),0);
	
	public UpdateHexagonCmd(HexagonAdapter hexagon, HexagonAdapter newState) {
		this.hexagon = hexagon;
		this.newState = newState;
	}
	
	
	@Override
	public void execute() {
		original.clone(hexagon);
		hexagon.clone(newState);
	}

	@Override
	public void unexecute() {
		hexagon.clone(original);

	}
	public String toString() {
		return "UpdateHexagonCmd -> " + original + ", newState: " + newState ;
	}

}
