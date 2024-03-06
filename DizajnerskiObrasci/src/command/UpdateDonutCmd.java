package command;

import geometry.Donut;
import geometry.Point;
import observer.Button;

public class UpdateDonutCmd implements Command {

	private Donut donut,newState;
	private Donut temp=new Donut(new Point());
	private Button support;
	
	public UpdateDonutCmd(Donut donut, Donut newState,Button support) {
		this.donut=donut;
		this.newState=newState;
		this.support=support;
		this.temp=donut.clone();
				
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		
		donut.getCenter().setX(newState.getCenter().getX());
		donut.getCenter().setY(newState.getCenter().getY());
		try {
			donut.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(newState.getInnerRadius());
		donut.seteColor(newState.geteColor());
		donut.setiColor(newState.getiColor());
		support.buttonVisibilityChanged();
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		donut.getCenter().setX(temp.getCenter().getX());
		donut.getCenter().setY(temp.getCenter().getY());
		try {
			donut.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(temp.getInnerRadius());
		donut.seteColor(temp.geteColor());
		donut.setiColor(temp.getiColor());
		support.buttonVisibilityChanged();
		
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Updated: "+ temp.toString()+" "+donut.toString();
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
