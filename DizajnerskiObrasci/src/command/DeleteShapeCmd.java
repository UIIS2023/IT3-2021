package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;
import observer.Button;

public class DeleteShapeCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private Button support;
	private List<Shape> tempSelectedShapes;
	private List<Shape> deletedShapes;
	private int index;
	private List<Integer> indexi;

	
	public DeleteShapeCmd(Shape shape, DrawingModel model,Button support,int index) {
		this.shape=shape;
		this.model=model;
		this.support=support;
		this.index=index;
		deletedShapes=new ArrayList<Shape>();
		//indexi=new int[tempSelectedShapes.size()];
	}
	public DeleteShapeCmd(List<Shape>tempSelectedShapes, DrawingModel model,Button support) {
		this.tempSelectedShapes=tempSelectedShapes;
		this.model=model;
		this.support=support;
		}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShape(i).isSelected()) {
			deletedShapes.add(model.getShape(i));
			indexi.add(i);
			}
		
		}
		
		
		
		for(int i=0;i<tempSelectedShapes.size();i++) {
			model.getShapes().remove(deletedShapes.get(i));
		
		
		}
		tempSelectedShapes.clear();
		support.buttonVisibilityChanged();
	}
	@Override
	public void unexecute() {
		// TODO Auto-generated method stu
	for (int i=0;i<deletedShapes.size();i++) {
		
		
		if (indexi.get(i) > model.getShapes().size()-1) {
			//System.out.println("ako je posldenji"+indexi[i]);
			
			model.getShapes().add(indexi.get(i),deletedShapes.get(i));
					
		} 
		else {
			//System.out.println(indexi[i]);
			model.getShapes().add(indexi.get(i),deletedShapes.get(i));
		
		}
		tempSelectedShapes.add(deletedShapes.get(i));
		
	}
//	deletedShapes.clear();
		support.buttonVisibilityChanged();
	}
	/*public String getName() {
		
			return "Deleted: "+shape.toString();
			
	}
	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		return "Undo deleted: "+shape.toString();
			
		}
	@Override
	public String getNameForRedo() {
		return "Redo deleted: "+shape.toString();
		}
	*/
	/*@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(int i =0;i<tempSelectedShapes.size();i++) {
			deletedShapes.add(tempSelectedShapes.get(i));
		model.remove(tempSelectedShapes.get(i));
	return "Deleted: "+shape.toString();
		}
		tempSelectedShapes.clear();
		//tempSelectedShapes.clear();
		support.buttonVisibilityChanged();
	}
	@Override
	public void unexecute() {
		// TODO Auto-generated method stu
		
		for(int i =0;i<deletedShapes.size();i++) {
			tempSelectedShapes.add(deletedShapes.get(i));
			model.add(deletedShapes.get(i));
			}
		//deletedShapes.clear();
		support.buttonVisibilityChanged();
	}*/
	
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		StringBuilder names=new StringBuilder();
		// TODO Auto-generated method stub

		for(int i=0;i<deletedShapes.size();i++) {
			names.append("Deleted: "+deletedShapes.get(i).toString());
			 if (i < deletedShapes.size() - 1) {
		            names.append(","); 
		        }
		}
		return names.toString();
	}
	@Override
	public String getNameForUndo() {
		// TODO Auto-generated method stub
		StringBuilder names=new StringBuilder();
		// TODO Auto-generated method stub

		for(int i=0;i<tempSelectedShapes.size();i++) {
			names.append("Undo deleted: "+tempSelectedShapes.get(i).toString());
			 if (i < tempSelectedShapes.size() - 1) {
		            names.append(","); 
		        }
		}
		return names.toString();
		}
	@Override
	public String getNameForRedo() {
		StringBuilder names=new StringBuilder();
		// TODO Auto-generated method stub

		for(int i=0;i<deletedShapes.size();i++) {
			names.append("Redo deleted: "+deletedShapes.get(i).toString());
			 if (i < deletedShapes.size() - 1) {
		            names.append(","); 
		        }
		}
		return names.toString();
		}
		
	public List<Shape> getTempSelectedShapes() {
		return tempSelectedShapes;
	}
	public void setTempSelectedShapes(List<Shape> tempSelectedShapes) {
		this.tempSelectedShapes = tempSelectedShapes;
		deletedShapes=new ArrayList<Shape>(tempSelectedShapes.size());
		indexi=new ArrayList<Integer>(tempSelectedShapes.size());
	}
	
	

}
