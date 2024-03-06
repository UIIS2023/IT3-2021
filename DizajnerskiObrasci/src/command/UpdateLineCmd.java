package command;

import geometry.Line;
import geometry.Point;
import observer.Button;

public class UpdateLineCmd implements Command {

	private Line line,newState;
	private Line temp=new Line(new Point(),new Point());
	private Button support;
	
	
	
	public UpdateLineCmd(Line line, Line newState,Button support) {
		this.line = line;
		this.newState = newState;
		this.support=support;
		this.temp=line.clone();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		
		line.getStartPoint().setX(newState.getStartPoint().getX());
		line.getStartPoint().setY(newState.getStartPoint().getY());
		line.getEndPoint().setX(newState.getEndPoint().getX());
		line.getEndPoint().setY(newState.getEndPoint().getY());
		line.seteColor(newState.geteColor());
		support.buttonVisibilityChanged();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub\
		line.getStartPoint().setX(temp.getStartPoint().getX());
		line.getStartPoint().setY(temp.getStartPoint().getY());
		line.getEndPoint().setX(temp.getEndPoint().getX());
		line.getEndPoint().setY(temp.getEndPoint().getY());
		line.seteColor(temp.geteColor());
		support.buttonVisibilityChanged();
		
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Updated: "+ temp.toString()+" "+line.toString();
		}
	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo updated: "+ newState.toString()+" "+temp.toString();
	}

	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo updated: "+ temp.toString()+" "+newState.toString();
	}
}
