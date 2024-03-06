package command;

import java.util.Stack;

import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class AddShapeCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private Button support;
	
	
	

	public AddShapeCmd(Shape shape, DrawingModel model,Button support) {
		this.shape = shape;
		this.model = model;
		this.support=support;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.add(shape);
		support.buttonVisibilityChanged();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		support.buttonVisibilityChanged();
		
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Added: "+shape.toString();
	}

	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo added: "+shape.toString();
	}

	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		return "Redo added: "+shape.toString();	}
}
