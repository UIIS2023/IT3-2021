package command;

import geometry.Point;
import geometry.Rectangle;
import observer.Button;

public class UpdateRectangleCmd implements Command {

	private Rectangle rectangle, newState;
	private Rectangle temp=new Rectangle(new Point());
	private Button support;
	
	public UpdateRectangleCmd(Rectangle rectangle,Rectangle newState,Button support) {
		this.rectangle=rectangle;
		this.newState=newState;
		this.support=support;
		this.temp=rectangle.clone();
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	
		rectangle.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		rectangle.setHeight(newState.getHeight());
		rectangle.setWidth(newState.getWidth());
		rectangle.seteColor(newState.geteColor());
		rectangle.setiColor(newState.getiColor());
		
		support.buttonVisibilityChanged();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		rectangle.getUpperLeftPoint().setX(temp.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(temp.getUpperLeftPoint().getY());
		rectangle.setHeight(temp.getHeight());
		rectangle.setWidth(temp.getWidth());
		rectangle.seteColor(temp.geteColor());
		rectangle.setiColor(temp.getiColor());
		support.buttonVisibilityChanged();
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Updated: "+ temp.toString()+" "+rectangle.toString();	
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
