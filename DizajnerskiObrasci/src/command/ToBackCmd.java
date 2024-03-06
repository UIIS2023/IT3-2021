package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class ToBackCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private Button support;
	
	public ToBackCmd(Shape shape, DrawingModel model,Button support) {
		this.shape = shape;
		this.model = model;
		this.support=support;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int index = model.getShapes().indexOf(shape);
		//if(index!=-1) {
	    //model.getShapes().set(index, model.getShape(index - 1));
	    //model.getShapes().set(index - 1, shape);
		Shape tempShape = model.getShapes().remove(index);
		 model.getShapes().add(index-1, tempShape);
		//}
	    support.buttonVisibilityChanged();

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		 int index = model.getShapes().indexOf(shape);
		    
	   // Zamena pozicija odabranog oblika i oblika iznad njega
		 //if(index!=model.getShapes().size()-1) {
		 //model.getShapes().set(index, model.getShape(index + 1));
	    // model.getShapes().set( index + 1, shape);
		 Shape tempShape = model.getShapes().remove(index);
		 model.getShapes().add(index+1, tempShape);
	     support.buttonVisibilityChanged();
		 //}
		           
		       
		 

	}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ToBack: "+shape.toString();
	}
	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo ToBack: "+shape.toString();
	}
	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo ToBack: "+shape.toString();
	}

}
