package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class BringToFrontCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private Button support;
	private int index;
	public BringToFrontCmd(Shape shape, DrawingModel model,Button support) {
		this.shape = shape;
		this.model = model;
		this.support=support;
		index=model.getShapes().indexOf(shape);
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	        Shape tempShape = model.getShapes().remove(index);
	        model.getShapes().add(tempShape);
	        support.buttonVisibilityChanged();
		
	    
	}

	@Override
	public void unexecute() {
		
		   Shape tempShape = model.getShapes().remove(model.getShapes().size()-1);
		   model.getShapes().add(index, tempShape);
		   support.buttonVisibilityChanged();
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BroughtToFront: "+shape.toString();
	}

	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo BroughtToFront: "+shape.toString();	}

	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo BroughtToFront: "+shape.toString();	}


}
