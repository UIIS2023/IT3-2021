package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class ToFrontCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private Button support;
	private int index;
	
	public ToFrontCmd(Shape shape, DrawingModel model,Button support) {
		this.shape = shape;
		this.model = model;
		this.support=support;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		index=model.getShapes().indexOf(shape);
	   // Zamena pozicija odabranog oblika i oblika iznad njega
		 Shape tempShape = model.getShapes().remove(index);
		 model.getShapes().add(index+1, tempShape);
		 
		 //model.getShapes().set(index, model.getShape(index + 1));
		 //model.getShapes().set(index + 1, shape);
		 support.buttonVisibilityChanged();
		 
		            
		           
		

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		// int index = model.getShapes().indexOf(shape);
		// if(index!=0) {
		index=model.getShapes().indexOf(shape);
		 Shape tempShape = model.getShapes().remove(index);
		 model.getShapes().add(index-1, tempShape);
		   // model.getShapes().set(index, model.getShape(index - 1));
		   // model.getShapes().set(index - 1, shape);
		    support.buttonVisibilityChanged();
		// }

	}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ToFront: "+shape.toString();
	}
	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo ToFront: "+shape.toString();
	}
	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo ToFront: "+shape.toString();
	}
}
