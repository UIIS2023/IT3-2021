package command;

import geometry.Circle;
import geometry.Point;
import observer.Button;

public class UpdateCircleCmd implements Command {

	private Circle circle,newState;
	private Circle temp=new Circle(new Point());
	private Button support;
	
	
	public UpdateCircleCmd(Circle circle, Circle newState,Button support) {
		this.circle = circle;
		this.newState = newState;
		this.support=support;
		this.temp=circle.clone();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		
		circle.getCenter().setX(newState.getCenter().getX());
		circle.getCenter().setY(newState.getCenter().getY());
		try {
			circle.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.seteColor(newState.geteColor());
		circle.setiColor(newState.getiColor());
		support.buttonVisibilityChanged();
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		circle.getCenter().setX(temp.getCenter().getX());
		circle.getCenter().setY(temp.getCenter().getY());
		try {
			circle.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.seteColor(temp.geteColor());
		circle.setiColor(temp.getiColor());
		support.buttonVisibilityChanged();
	}
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Updated: "+ temp.toString()+" "+circle.toString();
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
