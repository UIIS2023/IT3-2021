package command;

import geometry.Point;
import observer.Button;

public class UpdatePointCmd implements Command {

	private Point point,newState;
	private Point temp=new Point();
	private Button support;
	
	public UpdatePointCmd(Point point, Point newState,Button support) {
		this.point = point;
		this.newState = newState;
		this.support=support;
		this.temp=point.clone();
	
	}

	@Override
	public void execute() {
		
		
		point.setX(newState.getX());
		point.setY(newState.getY());
		point.seteColor(newState.geteColor());
		support.buttonVisibilityChanged();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		point.setX(temp.getX());
		point.setY(temp.getY());
		point.seteColor(temp.geteColor());
		support.buttonVisibilityChanged();

	}
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Updated: "+ temp.toString()+" "+ point.toString();
		}
	
	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo updated: "+ point.toString()+" "+temp.toString();
	}

	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo updated: "+ temp.toString()+" "+point.toString();
	}

}
