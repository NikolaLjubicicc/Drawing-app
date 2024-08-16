package command;

import geometry.Line;

public class UpdateLineCmd implements Command {

	private Line line;
	private Line newState;
	private Line original = new Line(); 
	
	public UpdateLineCmd(Line line, Line newState) {
		this.line = line;
		this.newState = newState;
	}
	@Override
	public void execute() {
		original.clone(line);
		line.clone(newState);
	}

	@Override
	public void unexecute() {
		line.clone(original);
		
	}
	public String toString() {
		return "UpdateLineCmd -> " + original + ", newState: " + newState ;
	}

}
