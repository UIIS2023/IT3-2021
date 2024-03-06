package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class BringToBackCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private Button support;
	private int  index;
	
	
	public BringToBackCmd(Shape shape, DrawingModel model,Button support) {
		this.shape = shape;
		this.model = model;
		this.support=support;
		index=model.getShapes().indexOf(shape);
		}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	   
	        Shape tempShape = model.getShapes().remove(index);
	        model.getShapes().add(0, tempShape);
	        support.buttonVisibilityChanged();
	  
		
	}

	@Override
	public void unexecute() {
		 
		
	        Shape tempShape = model.getShapes().remove(0);
	        model.getShapes().add(index,tempShape);
	        support.buttonVisibilityChanged();
	   
	}
	
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BroughtToBack:"+shape.toString();
	}

	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo BroughtToBack: "+shape.toString();
	}

	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo BroughtToBack: "+shape.toString();	}


}
