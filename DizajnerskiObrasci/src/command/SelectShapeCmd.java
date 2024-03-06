package command;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class SelectShapeCmd implements Command {
	
	private DrawingModel model;
	Shape selectedShape;
	Button support;
	List<Shape>tempSelectedShapes;
	
	List<Shape>selectedShapes;
	public SelectShapeCmd(DrawingModel model,Shape selectedShape,Button support) {
		this.model=model;
		this.selectedShape=selectedShape ;
		this.support=support;
}

/*	public SelectShapeCmd(DrawingModel model,List<Shape> selectedShapes,Button support) {
		this.model=model;
		this.selectedShapes=selectedShapes ;
		this.support=support;
}*/
	
	public void execute() {
		
		selectedShape.setSelected(true);
		tempSelectedShapes.add(selectedShape);		
		/*for(int i=0;i<selectedShapes.size();i++) {
			model.getShape(i).setSelected(true);
		}*/
		support.buttonVisibilityChanged();
			
	}

	@Override
	public void unexecute() {
		
		selectedShape.setSelected(false);
		tempSelectedShapes.remove(selectedShape);	
	/*	for(int i=0;i<selectedShapes.size();i++) {
			model.getShape(i).setSelected(false);
		}*/
		support.buttonVisibilityChanged();
		
	}



	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Selected: "+selectedShape.toString();
	}



	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo selected: "+selectedShape.toString();
		}



	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return  "Redo selected: "+selectedShape.toString();	
		}

	public List<Shape> getTempSelectedShapes() {
		return tempSelectedShapes;
	}

	public void setTempSelectedShapes(List<Shape> tempSelectedShapes) {
		this.tempSelectedShapes = tempSelectedShapes;
	}

	
	
}
