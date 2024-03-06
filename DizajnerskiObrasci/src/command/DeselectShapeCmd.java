package command;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class DeselectShapeCmd implements Command {

	private DrawingModel model;
	List<Shape>tempSelectedShapes;
	Shape selected;
	Button support;
	List<Shape>deselectedShapes;
	
	
	public DeselectShapeCmd(DrawingModel model,List<Shape>tempSelectedShapes,Button support) {
		this.model=model;
		this.tempSelectedShapes = tempSelectedShapes;
		this.support=support;
		//deselectedShapes=new ArrayList<Shape>(tempSelectedShapes.size());
		
		
	}
	
	public DeselectShapeCmd(DrawingModel model,Shape selected,Button support) {
		this.model=model;
		this.selected = selected;
		this.support=support;
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		selected.setSelected(false);
		tempSelectedShapes.remove(selected);
		support.buttonVisibilityChanged();
		

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		selected.setSelected(true);
		tempSelectedShapes.add(selected);
		support.buttonVisibilityChanged();
		
	}

	public List<Shape> getTempSelectedShapes() {
		return tempSelectedShapes;
	}
	public void setTempSelectedShapes(List<Shape> tempSelectedShapes) {
		this.tempSelectedShapes = tempSelectedShapes;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Deselected: "+selected.toString();

	}
	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo deselected: "+selected.toString();
	}
	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo deselected: "+selected.toString();
	}
	
}
