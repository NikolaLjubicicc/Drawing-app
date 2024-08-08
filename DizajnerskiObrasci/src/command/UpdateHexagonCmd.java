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
		original.setX(hexagon.getX());
		original.setY(hexagon.getY());
		original.setR(hexagon.getR());
		original.setColor(hexagon.getColor());
		original.setInnerColor(hexagon.getInnerColor());
		
		hexagon.setX(newState.getX());
		hexagon.setY(newState.getY());
		hexagon.setR(newState.getR());
		hexagon.setColor(newState.getColor());
		hexagon.setInnerColor(newState.getInnerColor());

	}

	@Override
	public void unexecute() {
		hexagon.setX(original.getX());
		hexagon.setY(original.getY());
		hexagon.setR(original.getR());
		hexagon.setColor(original.getColor());
		hexagon.setInnerColor(original.getInnerColor());

	}

}
