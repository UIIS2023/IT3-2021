package command;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class DeselectAllSelectedShapesCmd implements Command {
	List<Shape>tempSelectedShapes;
	private DrawingModel model;
	Button support;

	public DeselectAllSelectedShapesCmd(List<Shape> tempSelectedShapes,DrawingModel model,Button support) {
		
		this.tempSelectedShapes = tempSelectedShapes;
		this.model=model;
		this.support=support;
		}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
			for(int i=0;i<tempSelectedShapes.size();i++) {
				model.getShape(i).setSelected(false);
			}
			support.buttonVisibilityChanged();
		//	tempSelectedShapes.clear();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		for(int i=0;i<tempSelectedShapes.size();i++) {
			model.getShape(i).setSelected(true);
			
		}
		support.buttonVisibilityChanged();
		

	}
	  public Command clone() {
	       return new DeselectAllSelectedShapesCmd(tempSelectedShapes,model,support);
	      
	    }
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		StringBuilder names=new StringBuilder();
		// TODO Auto-generated method stub

		for(int i=0;i<tempSelectedShapes.size();i++) {
			names.append("Deselected: "+tempSelectedShapes.get(i).toString());
			 if (i < tempSelectedShapes.size() - 1) {
		            names.append("\n "); 
		        }
		}
		return names.toString();
	}

	@Override
	public String getNameForUndo() {
		
		StringBuilder names=new StringBuilder();
		
		for(int i=0;i<tempSelectedShapes.size();i++) {
			names.append("Undo deselected: "+tempSelectedShapes.get(i).toString());
			 if (i < tempSelectedShapes.size() - 1) {
		            names.append(","); 
		        }
		}
		return names.toString();
		
			
	}

	@Override
	public String getNameForRedo() {
		// TODO Auto-generated method stub
		
		StringBuilder names=new StringBuilder();
		
		for(int i=0;i<tempSelectedShapes.size();i++) {
			names.append("Redo deselected: "+tempSelectedShapes.get(i).toString());
			 if (i < tempSelectedShapes.size() - 1) {
		            names.append(", "); 
		        }
		}
		return names.toString();
	}
	
	

}
