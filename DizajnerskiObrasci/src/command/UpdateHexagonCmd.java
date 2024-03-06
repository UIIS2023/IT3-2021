package command;

import geometry.HexagonAdapter;
import geometry.Point;
import observer.Button;

public class UpdateHexagonCmd implements Command {

	private HexagonAdapter hexagon,newState;
	private HexagonAdapter temp=new HexagonAdapter();
	private Button support;
	
	public UpdateHexagonCmd(HexagonAdapter hexagon,HexagonAdapter newState,Button support) {
		this.hexagon=hexagon;
		this.newState=newState;
		this.support=support;
		this.temp=hexagon.clone();
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		hexagon.setX(newState.getX());
		hexagon.setY(newState.getY());
		try {
			hexagon.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hexagon.seteColor(newState.geteColor());
		hexagon.setiColor(newState.getiColor());
		
		support.buttonVisibilityChanged();
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		hexagon.setX(temp.getX());
		hexagon.setY(temp.getY());
		try {
			hexagon.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hexagon.seteColor(temp.geteColor());
		hexagon.setiColor(temp.getiColor());
		support.buttonVisibilityChanged();	
	}
		
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Updated: "+ temp.toString()+" "+hexagon.toString();	
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
