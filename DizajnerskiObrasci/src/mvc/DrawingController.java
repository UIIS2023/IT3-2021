package mvc;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.text.View;

import org.w3c.dom.css.RGBColor;

import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeleteShapeCmd;
import command.DeselectShapeCmd;
import command.SelectShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import command.DeselectAllSelectedShapesCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import observer.Button;
import strategy.SaveLog;
import strategy.SaveManager;
import strategy.SavePainting;
import geometry.Point;
public class DrawingController implements 	PropertyChangeListener  {

	private	DrawingModel model;
	private	DrawingFrame frame;
    private String selectedShapeType;
    private Shape point,line,circle,rectangle,donut,hexagon;
    private Point startPoint;
    private boolean doubleClick= false;
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;  
    private List<Command> commands;
    private List<String>commandsFromFile=new ArrayList<String>();
    List<Shape>selectedShapes=new ArrayList<Shape>();
    JList<String>list;
    private DefaultListModel<String> dlm= new DefaultListModel<String>();
    Button support;
  
	 public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		commands = new ArrayList<Command>();
		undoStack=new Stack<Command>();
		redoStack=new Stack<Command>();
		support = new Button();
		support.addListener(this);		
	    list=new JList<String>();
	    frame.getScrollPane().setViewportView(list);
		list.setModel(dlm);
		support.buttonVisibilityChanged();	 
	 }
	 
	
	 
	 public void handleMouseClick(Point mouseClick) {
		 if (frame.getTglbtnDraw().isSelected()) {
			 if(frame.getTglbtnPoint().isSelected()){
				 setSelectedShapeType("POINT");
			 }
			 else if(frame.getTglbtnRectangle().isSelected()){
				 setSelectedShapeType("RECTANGLE");
			 }
			 else if(frame.getTglbtnCircle().isSelected()){
				 setSelectedShapeType("CIRCLE");
			 }
			 else if(frame.getTglbtnDonut().isSelected()){
				 setSelectedShapeType("DONUT");
			 }
			 else if(frame.getTglbtnLine().isSelected()){
				 setSelectedShapeType("LINE");
			 }
			 else if(frame.getTglbtnHexagon().isSelected()){
				 setSelectedShapeType("HEXAGON");
			 }
			 openShapeDialog(mouseClick);
		 } else if (frame.getTglbtnSel().isSelected()) {
			selectShape(mouseClick);
		 }	
	 }

	    private void openShapeDialog(Point mouseClick) {
	       
	    	AddShapeCmd shape;
	        if (selectedShapeType.equals("POINT")) {
	        	DlgPoint dlg = new DlgPoint();
	        	dlg.setTempColor(frame.getEdgeColor());
				dlg.setNewPoint(mouseClick);
				dlg.setVisible(true);
				if(dlg.isoK()) {
				 if (dlg.getNewPoint() != null)
				    point = dlg.getNewPoint();
				    shape=new AddShapeCmd(point ,model,support);
				    executeCommand(shape);
				    frame.setEdgeColor(dlg.geteColor());
				}
				return;
	
	        } else if (selectedShapeType.equals("LINE")) {
	        	if (doubleClick == true) {
					DlgLine dlg = new DlgLine();
					dlg.setTempColor(frame.getEdgeColor());
					dlg.setNewLine(startPoint, mouseClick);
					dlg.setVisible(true);
					if(dlg.isoK()) {
					line=dlg.getNewLine();
				    shape=new AddShapeCmd(line,model,support);
					executeCommand(shape);
					frame.setEdgeColor(dlg.geteColor());
					doubleClick = false; 
					}
					return;
				}
				startPoint = mouseClick;
				doubleClick = true;
				return;
	        }
	        else if (selectedShapeType.equals("RECTANGLE")) {
	        	DlgRectangle dlg = new DlgRectangle();
	        	dlg.setTempEColor(frame.getEdgeColor());
	        	dlg.setTempIColor(frame.getInnerColor());
				dlg.setNewRectangle(mouseClick);
				dlg.setVisible(true);
				
				if(dlg.isOk()) {
				if (dlg.getNewRectangle() != null)
				 rectangle=dlg.getNewRectangle();
				 shape=new AddShapeCmd(rectangle ,model,support);
				 executeCommand(shape);
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				}
				return;
	        }
	        else if (selectedShapeType.equals("CIRCLE")) {
	        	DlgCircle dlg = new DlgCircle();
	        	dlg.setTempEColor(frame.getEdgeColor());
	        	dlg.setTempIColor(frame.getInnerColor());
				dlg.setNewCircle(mouseClick);
				dlg.setVisible(true);
				if(dlg.isOk()) {
				if (dlg.getNewCircle() != null)
					circle=dlg.getNewCircle();
			    shape=new AddShapeCmd(circle ,model,support);
				executeCommand(shape);
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				}
					return;
				
	        }
	        else if (selectedShapeType.equals("DONUT")) {
	        	DlgDonut dlg = new DlgDonut();
	        	dlg.setTempEColor(frame.getEdgeColor());
	        	dlg.setTempIColor(frame.getInnerColor());
				dlg.setNewDonut(mouseClick);
				dlg.setVisible(true);
				
				if(dlg.isOK()) {
				if (dlg.getNewDonut() != null)
					donut=dlg.getNewDonut();
			    shape=new AddShapeCmd(donut ,model,support);
			    executeCommand(shape);
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				}
				return;
	        } 
	    
	        else if (selectedShapeType.equals("HEXAGON")) {
	        	
	        	DlgHexagon dlg = new DlgHexagon();
	        	dlg.setTempEColor(frame.getEdgeColor());
	        	dlg.setTempIColor(frame.getInnerColor());
				dlg.setNewHexagon(mouseClick);
				dlg.setVisible(true);
				if(dlg.isOk()) {
				if (dlg.getNewHexagon() != null) 
				hexagon=dlg.getNewHexagon();
				shape=new AddShapeCmd(hexagon ,model,support);
				executeCommand(shape);
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				}
				return;
	        } 
	       
	        }
	       
	    
	
	    
	    public void openModifyShapeDialog(Shape selected) {
		       
	    	Point temp;
			if (selected instanceof Point) {
				Point point = (Point) selected;
				DlgPoint dlg = new DlgPoint();
				dlg.setTempColor(frame.getEdgeColor()); 
				dlg.setModifyPoint(point);
				dlg.setVisible(true);
				
				if(dlg.isoK()) {
				 temp=dlg.getModifyPoint();
				
				UpdatePointCmd modifyPoint=new UpdatePointCmd(point,temp,support);
				executeCommand(modifyPoint);
				frame.repaint();
				frame.setEdgeColor(dlg.geteColor());
				
				}
				

			} else if (selected instanceof Line) {
				Line temp1;
				Line line = (Line) selected;
				DlgLine dlg = new DlgLine();
				dlg.setTempColor(frame.getEdgeColor()); 
				dlg.setModifyLine(line);
				dlg.setVisible(true);
				if(dlg.isoK()) {
				temp1=dlg.getModifyLine();
				UpdateLineCmd modifyLine=new UpdateLineCmd(line,temp1,support);
				executeCommand(modifyLine);
				frame.setEdgeColor(dlg.geteColor());
				
				}
				
				}
			
			else if (selected instanceof Donut) {
				Donut temp2;
				Donut donut = (Donut) selected;
				DlgDonut dlg = new DlgDonut();
				dlg.setTempEColor(frame.getEdgeColor()); 
				dlg.setTempIColor(frame.getEdgeColor()); 
				dlg.setModifyDonut(donut);
				dlg.setVisible(true);
				if(dlg.isOK()) {
				temp2=dlg.getModifyDonut();
				UpdateDonutCmd modifyDonut=new UpdateDonutCmd(donut,temp2,support);
				executeCommand(modifyDonut);
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				
				}
					
			}   else if (selected instanceof Circle) {
				
				Circle temp3;
				Circle circle = (Circle) selected;
				DlgCircle dlg = new DlgCircle();
				dlg.setTempEColor(frame.getEdgeColor()); 
				dlg.setTempIColor(frame.getEdgeColor()); 
				dlg.setModifyCircle(circle);
				dlg.setVisible(true);
				
				if(dlg.isOk()) {
				temp3=dlg.getModifyCircle();
				UpdateCircleCmd modifyCircle=new UpdateCircleCmd(circle,temp3,support);
				executeCommand(modifyCircle);
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				
				}
			} 
			else if (selected instanceof Rectangle) {
				
				Rectangle temp4;
				
				Rectangle rectangle = (Rectangle) selected;
				DlgRectangle dlg = new DlgRectangle();
				dlg.setTempEColor(frame.getEdgeColor()); 
				dlg.setTempIColor(frame.getEdgeColor()); 
				dlg.setModifyRectangle(rectangle);
				dlg.setVisible(true);
				
				if(dlg.isOk()) {
				temp4=dlg.getModifyRectangle();
				UpdateRectangleCmd modifyRectangle=new UpdateRectangleCmd(rectangle,temp4,support);
				executeCommand(modifyRectangle);
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				
				}
			} 
			else if (selected instanceof HexagonAdapter) {
				HexagonAdapter hex;
				HexagonAdapter hexagon = (HexagonAdapter) selected;
				DlgHexagon dlg = new DlgHexagon();
				dlg.setTempEColor(frame.getEdgeColor()); 
				dlg.setTempIColor(frame.getEdgeColor()); 
				dlg.setModifyHexagon(hexagon);
				dlg.setVisible(true);	
				if(dlg.isOk()) {
					hex=dlg.getModifyHexagon();
				UpdateHexagonCmd modifyHexagon=new UpdateHexagonCmd(hexagon,hex,support);
				executeCommand(modifyHexagon);
			
				frame.setEdgeColor(dlg.geteColor());
				frame.setInnerColor(dlg.getiColor());
				}
				
				
			}
			
		}
//-----------------------------------------------------------------LOGIKA ZA DELETE--------------------------------------------
	    
	 public void deleteSelectedShape() {
		
				String[] answer = { "YES", "NO" };
				{
					int option = JOptionPane.showOptionDialog(null, "Do you want to delete selected shapes?",
							"WARNING!", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, answer, answer[0]);
					if (option == 0) {			
						 removeSelected();        
					}
				}
	 }

			public int getSelected() {
				for (int i = model.getShapes().size()-1; i >= 0; i--) {
					if (model.getShapes().get(i).isSelected()) {
						return i;
					}
				}
				return -1;
			}
			
		public void removeSelected() {	
			
		//for(int i=0;i<selectedShapes.size();i++) {
		//	if(model.getShape(i).isSelected())
			//{
			DeleteShapeCmd deleteShape=new DeleteShapeCmd(selectedShapes,model,support);
			deleteShape.setTempSelectedShapes(selectedShapes);
			executeCommand(deleteShape);
			//}
			//frame.repaint();
		//}
			
				// DeleteShapeCmd deleteShape=new DeleteShapeCmd(selectedShapes,model,support);
				 //   executeCommand(deleteShape);
				   // selectedShapes.clear();
				   
				//   for (int i=0;i<selectedShapes.size();i++) {
					//   model.getShape(i).setSelected(false);
				  // }
		   	 
		}
		 	
		public boolean isEmpty() {
			return model.getShapes().isEmpty();
		}
		
		
		
 //---------------------------------------------LOGIKA ZA SELECT-------------------------------------------------
	 
	 private void selectShape(Point mouseClick) {
	
			for(int i=model.getShapes().size()-1;i>=0;i--) {
				if(model.getShape(i).contains(mouseClick.getX(), mouseClick.getY())) {
					if(model.getShape(i).isSelected()) {
					//	selectedShapes.remove(model.getShape(i));
						DeselectShapeCmd deselectShape=new DeselectShapeCmd(model,model.getShape(i),support);
						deselectShape.setTempSelectedShapes(selectedShapes);
						executeCommand(deselectShape);
						
						return;
					}else {
						//selectedShapes.add(model.getShape(i));
				    SelectShapeCmd selectShape=new SelectShapeCmd(model,model.getShape(i),support);
				    
					selectShape.setTempSelectedShapes(selectedShapes);
					executeCommand(selectShape);
					return;
					}
				}
			}
			
			
	 }
	 
	 public void toFront(Shape selected) {
		 ToFrontCmd tftb=new ToFrontCmd(selected,model,support);
		 executeCommand(tftb);
		
	 }
	 
	 public void toBack(Shape selected) {
		 ToBackCmd tftb=new ToBackCmd(selected,model,support);
			executeCommand(tftb);	
	 }
	 public void bringToFront(Shape selected) {
		BringToFrontCmd btfbtb=new BringToFrontCmd(selected,model,support);
		executeCommand(btfbtb);	        
   } 
	 public void bringToBack(Shape selected) {
			
		BringToBackCmd btfbtb=new BringToBackCmd(selected,model,support);
		executeCommand(btfbtb);	        
	   } 
			 

	    
//---------------------------------------------------------------UNDO I REDO---------------------------------------------------------------------------
	 public void executeCommand(Command command) {
		 
		    //commands.add(command);
		    undoStack.push(command);
		    command.execute();
		 //   undoStack.push(command);
		    dlm.addElement(command.getName());
		    frame.repaint();
		    redoStack.clear();   
		}
	 public void executeCommandForRedo(Command command) {
		 
		    //commands.add(command);
		   // undoStack.push(command);
		    undoStack.push(command);
		    command.execute();
		    dlm.addElement(command.getNameForRedo());
		    frame.repaint();
		   // redoStack.clear();   
		}
	 
	 public void unexecuteCommand(Command command) {	 
		    //commands.add(command);
		    redoStack.push(command);
		    command.unexecute();
		   // redoStack.push(command);
		    dlm.addElement(command.getNameForUndo());
		    frame.repaint();    
		}
	 public void undo() {
		 
		        Command undoneCommand = undoStack.pop();
		        unexecuteCommand(undoneCommand);
		
	 }
	 public void redo() {
		
			 Command redoneCommand = redoStack.pop();
		        //commands.add(redoneCommand);
		        //undoStack.push(redoneCommand);
		        //redoneCommand.execute();
		        executeCommandForRedo(redoneCommand);
		        //dlm.addElement(redoneCommand.getNameForRedo());
		        //frame.repaint();
		 
		   
		}

//-------------------------------------------------------OBSERVER-------------------------------------------------------------
	 
	 public void updateButtonStates() {
		 if(undoStack.isEmpty()) {
        	 setIsEnabledUndo(false);
        }
        else   setIsEnabledUndo(true);

        if (redoStack.isEmpty()) {
            setIsEnabledRedo(false);
        } else {
            setIsEnabledRedo(true);
        }
		    if (model.getShapes().isEmpty()) {
		        //setIsEnabledUndo(false);
		        //setIsEnabledRedo(false);
		        setIsEnabledModify(false);
		        setIsEnabledDelete(false);
		        setIsEnabledTB(false);
		        setIsEnabledBTB(false);
		        setIsEnabledBTF(false);
		        setIsEnabledTF(false);
		        setIsEnabledSelect(false);
		    } else {
		    	
		        setIsEnabledSelect(true);
		        if(undoStack.isEmpty()) {
		        	 setIsEnabledUndo(false);
		        }
		        else   setIsEnabledUndo(true);

		        if (redoStack.isEmpty()) {
		            setIsEnabledRedo(false);
		        } else {
		            setIsEnabledRedo(true);
		        }
		    

		    if (selectedShapes.isEmpty()) {
		        setIsEnabledModify(false);
		        setIsEnabledDelete(false);
		        setIsEnabledTB(false);
		        setIsEnabledBTB(false);
		        setIsEnabledBTF(false);
		        setIsEnabledTF(false);
		        
		    } else if (selectedShapes.size() == 1) {
		        int index = getSelected();
		        
		        setIsEnabledModify(true);
		        setIsEnabledDelete(true);

		        if (model.getShapes().size() == 1) {
		            setIsEnabledTB(false);
		            setIsEnabledBTB(false);
		            setIsEnabledBTF(false);
		            setIsEnabledTF(false);
		        } else {
		            if (index == 0) {
		                setIsEnabledTB(false);
		                setIsEnabledBTB(false);
		                setIsEnabledBTF(true);
		                setIsEnabledTF(true);
		            } else if (index == model.getShapes().size() - 1) {
		                setIsEnabledTB(true);
		                setIsEnabledBTB(true);
		                setIsEnabledBTF(false);
		                setIsEnabledTF(false);
		            } else {
		                setIsEnabledTB(true);
		                setIsEnabledBTB(true);
		                setIsEnabledBTF(true);
		                setIsEnabledTF(true);
		            }
		        }
		    } else if (selectedShapes.size() > 1) {
		        setIsEnabledModify(false);
		        setIsEnabledDelete(true);
		        setIsEnabledTB(false);
		        setIsEnabledBTB(false);
		        setIsEnabledBTF(false);
		        setIsEnabledTF(false);
		    }
		    }
		}
	
		 public void setIsEnabledDraw(boolean isEnabled) {
				frame.getTglbtnDraw().setEnabled(isEnabled);
			}
		 public void setIsEnabledModify(boolean isEnabled) {
				frame.getTglbtnMod().setEnabled(isEnabled);
			}
		 public void setIsEnabledDelete(boolean isEnabled) {
				frame.getTglbtnDel().setEnabled(isEnabled);
			}
		 public void setIsEnabledSelect(boolean isEnabled) {
				frame.getTglbtnSel().setEnabled(isEnabled);
			}
		 public void setIsEnabledUndo(boolean isEnabled) {
				
				frame.getTglbtnUndo().setEnabled(isEnabled);
			}	
		 public void setIsEnabledRedo(boolean isEnabled) {
				frame.getTglbtnRedo().setEnabled(isEnabled);
				
			}
		 public void setIsEnabledBTB(boolean isEnabled) {
				frame.getTglbtnBTB().setEnabled(isEnabled);
			}
		 public void setIsEnabledBTF(boolean isEnabled) {
				frame.getTglbtnBTF().setEnabled(isEnabled);
			} public void setIsEnabledTF(boolean isEnabled) {
				frame.getTglbtnTF().setEnabled(isEnabled);
			}
		 public void setIsEnabledTB(boolean isEnabled) {
					frame.getTglbtnTB().setEnabled(isEnabled);
		    }
@Override
public void propertyChange(PropertyChangeEvent evt) {
	// TODO Auto-generated method stub
	if(evt.getPropertyName().equals("buttonVisibilityChanged")) {
		updateButtonStates();	
	}
}

//--------------------------------------------------------------CUVANJE I UCITAVANJE-----------------------------------------------------------------------
public void save() {
	SaveManager save=new SaveManager(new SaveLog(dlm),new SavePainting(model.getShapes()));
	save.save();
	
}

public void load() {
	SaveManager save=new SaveManager(new SaveLog(dlm),new SavePainting(model.getShapes()));
	save.load();
    frame.repaint(); 

}

 //------------------------------------------------------------UCITAVANJE KOMANDA PO KOMANDU------------------------------------------------------------------------------------
public void getCommands() {
	JFileChooser chooser = new JFileChooser();
    int result = chooser.showOpenDialog(chooser);

    if (result == JFileChooser.APPROVE_OPTION) {
    	File selectedFile = chooser.getSelectedFile();
        if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
            JOptionPane.showMessageDialog(null, "Invalid file format. Please select a .txt file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            dlm.clear();
            String line;
            while ((line = reader.readLine()) != null) {
               commandsFromFile.add(line);
            }
            for (String command : commandsFromFile) {
				int option = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to apply the following command?\n" + command,
                        "Apply Command",
                        JOptionPane.YES_NO_OPTION
                );

                if (option == JOptionPane.YES_OPTION) {
                    applyCommand(command);
                  
                    
                } else {
                    break;
                }
            }  JOptionPane.showMessageDialog(  null,  "All commands have been loaded.",  "Command Loading Complete",  JOptionPane.INFORMATION_MESSAGE );
        }catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading log: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}

public void loadPerCommand() {
	getCommands();
	  
}

//--------------------------------------------------------------PARSIRANJE-----------------------------------------------------------------------------------------
private void applyCommand(String command) {
	String cleanedCommand = command.replaceAll("[(),:=\\s\\]]"," ");
	
    String[] parts = cleanedCommand.split(" ");

    int x,x1,y,y1,radius,radius1,innerRadius,innerRadius1,width,width1,height,height1,a,b,c,d;
  
    Color eColor,eColor1,iColor,iColor1;
  
    AddShapeCmd addShape;
    SelectShapeCmd selectShape;
    DeleteShapeCmd deleteShape;
    BringToFrontCmd btfShape;
    BringToBackCmd btbShape;
    ToFrontCmd tfShape;
    ToBackCmd tbShape;
    DeselectShapeCmd deselectShape;
    UpdatePointCmd point;
    UpdateLineCmd line;
    UpdateRectangleCmd rectangle;
    UpdateCircleCmd circle;
    UpdateDonutCmd donut;
    UpdateHexagonCmd hexagon;
    int r,g,bl,r1,g1,b1, ir,ig,ib,ir1,ig1,ib1;
   

    if ("Added".equals(parts[0])) {
    	
    	if("Point".equals(parts[2])) {
    		x=Integer.parseInt(parts[4]);
    		y=Integer.parseInt(parts[5]);
    		r=Integer.parseInt(parts[8]);
    		g=Integer.parseInt(parts[10]);
    		bl=Integer.parseInt(parts[12]);
    		eColor=new Color(r,g,bl);
    		addShape=new AddShapeCmd(new Point(x,y,eColor),model,support);
    		executeCommand(addShape);
    		
    	}else if("Rectangle".equals(parts[2])) {
    		x=Integer.parseInt(parts[7]);
    		y=Integer.parseInt(parts[8]);
    		width=Integer.parseInt(parts[12]);
    		height=Integer.parseInt(parts[16]);
    		r=Integer.parseInt(parts[19]);
    		g=Integer.parseInt(parts[21]);
    		b=Integer.parseInt(parts[23]);
    		r1=Integer.parseInt(parts[27]);
    		g1=Integer.parseInt(parts[29]);
    		b1=Integer.parseInt(parts[31]);
    		
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		addShape=new AddShapeCmd(new Rectangle(new Point(x,y),width,height,eColor,iColor),model,support);
    		executeCommand(addShape);
    		
    		
    	}else if("Line".equals(parts[2])) {
    		x=Integer.parseInt(parts[5]);
    		y=Integer.parseInt(parts[6]);
    		x1=Integer.parseInt(parts[9]);
    		y1=Integer.parseInt(parts[10]);
    		r=Integer.parseInt(parts[13]);
    		g=Integer.parseInt(parts[15]);
    		bl=Integer.parseInt(parts[17]);
    		eColor=new Color(r,g,bl);
    		addShape=new AddShapeCmd(new Line(new Point(x,y),new Point(x1,y1),eColor),model,support);
    		executeCommand(addShape);
    		
    		
    	}else if("Circle".equals(parts[2])) {
    		x=Integer.parseInt(parts[6]);
    		y=Integer.parseInt(parts[7]);
    		radius=Integer.parseInt(parts[11]);
    		
    		r=Integer.parseInt(parts[14]);
    		g=Integer.parseInt(parts[16]);
    		b=Integer.parseInt(parts[18]);
    		r1=Integer.parseInt(parts[22]);
    		g1=Integer.parseInt(parts[24]);
    		b1=Integer.parseInt(parts[26]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		
    		addShape=new AddShapeCmd(new Circle(new Point(x,y),radius,eColor,iColor),model,support);
    		executeCommand(addShape);
    		
    		
    	}else if("Donut".equals(parts[2])) {
    		x=Integer.parseInt(parts[5]);
    		y=Integer.parseInt(parts[6]);
    		radius=Integer.parseInt(parts[10]);
    		innerRadius=Integer.parseInt(parts[13]);
    		r=Integer.parseInt(parts[16]);
    		g=Integer.parseInt(parts[18]);
    		b=Integer.parseInt(parts[20]);
    		r1=Integer.parseInt(parts[24]);
    		g1=Integer.parseInt(parts[26]);
    		b1=Integer.parseInt(parts[28]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		addShape=new AddShapeCmd(new Donut(new Point(x,y),radius,innerRadius,eColor,iColor),model,support);
    		executeCommand(addShape);
    		
    	}else if("Hexagon".equals(parts[2])) {
    		x=Integer.parseInt(parts[6]);
    		y=Integer.parseInt(parts[7]);
    		radius=Integer.parseInt(parts[11]);
    		
    		r=Integer.parseInt(parts[14]);
    		g=Integer.parseInt(parts[16]);
    		b=Integer.parseInt(parts[18]);
    		r1=Integer.parseInt(parts[22]);
    		g1=Integer.parseInt(parts[24]);
    		b1=Integer.parseInt(parts[26]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		Hexagon hex=new Hexagon(x,y,radius);
    		addShape=new AddShapeCmd(new HexagonAdapter(hex,eColor,iColor),model,support);
    		executeCommand(addShape);
    		
    		
    	}
    	
    } else if("Updated".equals(parts[0])) {
    	
    	if ("Point".equals(parts[2])) {
    		x=Integer.parseInt(parts[4]);
    		y=Integer.parseInt(parts[5]);
    		r=Integer.parseInt(parts[8]);
    		g=Integer.parseInt(parts[10]);
    		b=Integer.parseInt(parts[12]);
    		
    		x1=Integer.parseInt(parts[16]);
    		y1=Integer.parseInt(parts[17]);
    		r1=Integer.parseInt(parts[20]);
    		g1=Integer.parseInt(parts[22]);
    		b1=Integer.parseInt(parts[24]);
    		eColor=new Color(r,g,b);
    		eColor1=new Color(r1,g1,g1);
    		
    		for(int i=0;i<model.getShapes().size();i++) {
    			if (model.getShape(i).equals(new Point(x,y,eColor))) {
    		point=new UpdatePointCmd((Point)model.getShape(i),new Point(x1,y1,eColor1),support);
    		executeCommand(point);
    			}
    		}
    		
      	}else if("Rectangle".equals(parts[2])) {
    		x=Integer.parseInt(parts[7]);
    		y=Integer.parseInt(parts[8]);
    		width=Integer.parseInt(parts[12]);
    		height=Integer.parseInt(parts[16]);
    		r=Integer.parseInt(parts[19]);
    		g=Integer.parseInt(parts[21]);
    		b=Integer.parseInt(parts[23]);
    		ir=Integer.parseInt(parts[27]);
    		ig=Integer.parseInt(parts[29]);
    		ib=Integer.parseInt(parts[31]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(ir,ig,ib);
    		
    		x1=Integer.parseInt(parts[39]);
    		y1=Integer.parseInt(parts[40]);
    		width1=Integer.parseInt(parts[44]);
    		height1=Integer.parseInt(parts[48]);
    		r1=Integer.parseInt(parts[51]);
    		g1=Integer.parseInt(parts[53]);
    		b1=Integer.parseInt(parts[55]);
    		ir1=Integer.parseInt(parts[59]);
    		ig1=Integer.parseInt(parts[61]);
    		ib1=Integer.parseInt(parts[63]);
    		eColor1=new Color(r1,g1,b1);
    		iColor1=new Color(ir1,ig1,ib1);
    		
    		
    		for(int i=0;i<model.getShapes().size();i++) {
    			if (model.getShape(i).equals(new Rectangle(new Point(x,y),width,height,eColor,iColor))) {
    				rectangle=new UpdateRectangleCmd((Rectangle)model.getShape(i),new Rectangle(new Point(x1,y1),width1,height1,eColor1,iColor1),support);
    	    		executeCommand(rectangle);
    			}
    		}
    		
    		
    	}else if("Line".equals(parts[2])) {
    		x=Integer.parseInt(parts[5]);
    		y=Integer.parseInt(parts[6]);
    		x1=Integer.parseInt(parts[9]);
    		y1=Integer.parseInt(parts[10]);
    		r=Integer.parseInt(parts[13]);
    		g=Integer.parseInt(parts[15]);
    		bl=Integer.parseInt(parts[17]);
    		
    		a=Integer.parseInt(parts[23]);
    		b=Integer.parseInt(parts[24]);
    		c=Integer.parseInt(parts[27]);
    		d=Integer.parseInt(parts[28]);
    		r1=Integer.parseInt(parts[31]);
    		g1=Integer.parseInt(parts[33]);
    		b1=Integer.parseInt(parts[35]);
    		eColor=new Color(r,g,bl);
    	   eColor1=new Color (r1,g1,b1);
    	     
    	   for(int i=0;i<model.getShapes().size();i++) {
   			if (model.getShape(i).equals(new Line(new Point(x,y),new Point(x1,y1),eColor))) {
   				line=new UpdateLineCmd((Line)model.getShape(i),new Line(new Point(a,b),new Point(c,d),eColor1),support);
   	    		executeCommand(line);
   			}
   		}
    		
    		
    	}else if("Circle".equals(parts[2])) {
    		x=Integer.parseInt(parts[6]);
    		y=Integer.parseInt(parts[7]);
    		radius=Integer.parseInt(parts[11]);
    		r=Integer.parseInt(parts[14]);
    		g=Integer.parseInt(parts[16]);
    		b=Integer.parseInt(parts[18]);
    		ir=Integer.parseInt(parts[22]);
    		ig=Integer.parseInt(parts[24]);
    		ib=Integer.parseInt(parts[26]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(ir,ig,ib);
    		
    		
    		x1=Integer.parseInt(parts[33]);
    		y1=Integer.parseInt(parts[34]);
    		radius1=Integer.parseInt(parts[38]);
    		r1=Integer.parseInt(parts[41]);
    		g1=Integer.parseInt(parts[43]);
    		b1=Integer.parseInt(parts[45]);
    		ir1=Integer.parseInt(parts[49]);
    		ig1=Integer.parseInt(parts[51]);
    		ib1=Integer.parseInt(parts[53]);
    		eColor1=new Color(r1,g1,b1);
    		iColor1=new Color(ir1,ig1,ib1);
    		
    		  for(int i=0;i<model.getShapes().size();i++) {
    	   			if (model.getShape(i).equals(new Circle(new Point(x,y),radius,eColor,iColor))) {
    	   				circle=new UpdateCircleCmd((Circle)model.getShape(i),new Circle(new Point(x1,y1),radius1,eColor1,iColor1),support);
    	   	    		executeCommand(circle);
    	   			}
    	   		}
    		
    		
    	}else if("Donut".equals(parts[2])) {
    		x=Integer.parseInt(parts[5]);
    		y=Integer.parseInt(parts[6]);
    		radius=Integer.parseInt(parts[10]);
    		innerRadius=Integer.parseInt(parts[13]);
    		r=Integer.parseInt(parts[16]);
    		g=Integer.parseInt(parts[18]);
    		b=Integer.parseInt(parts[20]);
    		ir=Integer.parseInt(parts[24]);
    		ig=Integer.parseInt(parts[26]);
    		ib=Integer.parseInt(parts[28]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(ir,ig,ib);
    		
    		
    		x1=Integer.parseInt(parts[34]);
    		y1=Integer.parseInt(parts[35]);
    		radius1=Integer.parseInt(parts[39]);
    		innerRadius1=Integer.parseInt(parts[42]);
    		r1=Integer.parseInt(parts[45]);
    		g1=Integer.parseInt(parts[47]);
    		b1=Integer.parseInt(parts[49]);
    		ir1=Integer.parseInt(parts[53]);
    		ig1=Integer.parseInt(parts[55]);
    		ib1=Integer.parseInt(parts[57]);
    		eColor1=new Color(r1,g1,b1);
    		iColor1=new Color(ir1,ig1,ib1);
    		
    		for(int i=0;i<model.getShapes().size();i++) {
	   			if (model.getShape(i).equals(new Donut(new Point(x,y),radius,innerRadius))) {
	   				donut=new UpdateDonutCmd((Donut)model.getShape(i),new Donut(new Point(x1,y1),radius1,innerRadius1),support);
	   	    		executeCommand(donut);
	   			}
	   		}
    		
    		
    	}else if("Hexagon".equals(parts[2])) {
    		x=Integer.parseInt(parts[6]);
    		y=Integer.parseInt(parts[7]);
    		radius=Integer.parseInt(parts[11]);
    		
    		r=Integer.parseInt(parts[14]);
    		g=Integer.parseInt(parts[16]);
    		b=Integer.parseInt(parts[18]);
    		ir=Integer.parseInt(parts[22]);
    		ig=Integer.parseInt(parts[24]);
    		ib=Integer.parseInt(parts[26]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(ir,ig,ib);
    		
    		
    		x1=Integer.parseInt(parts[33]);
    		y1=Integer.parseInt(parts[34]);
    		radius1=Integer.parseInt(parts[38]);
    		r1=Integer.parseInt(parts[41]);
    		g1=Integer.parseInt(parts[43]);
    		b1=Integer.parseInt(parts[45]);
    		ir1=Integer.parseInt(parts[49]);
    		ig1=Integer.parseInt(parts[51]);
    		ib1=Integer.parseInt(parts[53]);
    		eColor1=new Color(r1,g1,b1);
    		iColor1=new Color(ir1,ig1,ib1);
    		
    		for(int i=0;i<model.getShapes().size();i++) {
	   			if (model.getShape(i).equals(new HexagonAdapter(new Point(x,y),radius,eColor,iColor))) {
	   				hexagon=new UpdateHexagonCmd((HexagonAdapter)model.getShape(i),new HexagonAdapter(new Point(x1,y1),radius1,eColor1,iColor1),support);
	   	    		executeCommand(hexagon);
	   			}
	   		}
    		
    	}
    	
    }else if("Deleted".equals(parts[0])) {

    	
    	 deleteShape=new DeleteShapeCmd(selectedShapes,model,support);
		deleteShape.setTempSelectedShapes(selectedShapes);
		executeCommand(deleteShape);
    	
    }else if ("BroughtToBack".equals(parts[0])) {
    	
    	int index=getSelected();
    	btbShape=new BringToBackCmd(model.getShape(index),model,support);
		executeCommand(btbShape);
    	
    } else if("BroughtToFront".equals(parts[0])) {
    	
    	int index=getSelected();
    	btfShape=new BringToFrontCmd(model.getShape(index),model,support);
		executeCommand(btfShape);
    	
    }else if("ToFront".equals(parts[0])) {
    	int index=getSelected();
    	tfShape=new ToFrontCmd(model.getShape(index),model,support);
		executeCommand(tfShape);
    	
    } else if("ToBack".equals(parts[0])) {
    	
    	int index=getSelected();
    	tbShape=new ToBackCmd(model.getShape(index),model,support);
    	executeCommand(tbShape);
	
    }else if("Selected".equals(parts[0])) {
    	
    	if ("Point".equals(parts[2])) {
    		x=Integer.parseInt(parts[4]);
    		y=Integer.parseInt(parts[5]);
    		r=Integer.parseInt(parts[8]);
    		g=Integer.parseInt(parts[10]);
    		bl=Integer.parseInt(parts[12]);
    		eColor=new Color(r,g,bl);
    		for(int i=0;i<model.getShapes().size();i++) {
    			if(model.getShape(i).equals(new Point(x,y,eColor))) {
    				selectShape=new SelectShapeCmd(model,model.getShape(i),support);
    				selectShape.setTempSelectedShapes(selectedShapes);
    				selectShape.setTempSelectedShapes(selectedShapes);
    				executeCommand(selectShape);
    			}  			
    		}
    				
	}else if("Rectangle".equals(parts[2])) {
		x=Integer.parseInt(parts[7]);
		y=Integer.parseInt(parts[8]);
		width=Integer.parseInt(parts[12]);
		height=Integer.parseInt(parts[16]);
		r=Integer.parseInt(parts[19]);
		g=Integer.parseInt(parts[21]);
		b=Integer.parseInt(parts[23]);
		r1=Integer.parseInt(parts[27]);
		g1=Integer.parseInt(parts[29]);
		b1=Integer.parseInt(parts[31]);
		
		eColor=new Color(r,g,b);
		iColor=new Color(r1,g1,b1);
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShape(i).equals(new Rectangle(new Point(x,y),width,height,eColor,iColor))) {
				selectShape=new SelectShapeCmd(model,model.getShape(i),support);
				selectShape.setTempSelectedShapes(selectedShapes);
				executeCommand(selectShape);
			}
		}

		
		
	}else if("Line".equals(parts[2])) {
		x=Integer.parseInt(parts[5]);
		y=Integer.parseInt(parts[6]);
		x1=Integer.parseInt(parts[9]);
		y1=Integer.parseInt(parts[10]);
		r=Integer.parseInt(parts[13]);
		g=Integer.parseInt(parts[15]);
		bl=Integer.parseInt(parts[17]);
		eColor=new Color(r,g,bl);
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShape(i).equals(new Line(new Point(x,y),new Point(x1,y1),eColor))) {
				selectShape=new SelectShapeCmd(model,model.getShape(i),support);
				selectShape.setTempSelectedShapes(selectedShapes);
				executeCommand(selectShape);
			}
		
		}
		
	}else if("Circle".equals(parts[2])) {
		x=Integer.parseInt(parts[6]);
		y=Integer.parseInt(parts[7]);
		radius=Integer.parseInt(parts[11]);
		
		r=Integer.parseInt(parts[14]);
		g=Integer.parseInt(parts[16]);
		b=Integer.parseInt(parts[18]);
		r1=Integer.parseInt(parts[22]);
		g1=Integer.parseInt(parts[24]);
		b1=Integer.parseInt(parts[26]);
		eColor=new Color(r,g,b);
		iColor=new Color(r1,g1,b1);
		
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShape(i).equals(new Circle(new Point(x,y),radius,eColor,iColor))) {
				selectShape=new SelectShapeCmd(model,model.getShape(i),support);
				selectShape.setTempSelectedShapes(selectedShapes);
				executeCommand(selectShape);
			}
		
		}
		
		
		
		
	}else if("Donut".equals(parts[2])) {
		x=Integer.parseInt(parts[5]);
		y=Integer.parseInt(parts[6]);
		radius=Integer.parseInt(parts[10]);
		innerRadius=Integer.parseInt(parts[13]);
		r=Integer.parseInt(parts[16]);
		g=Integer.parseInt(parts[18]);
		b=Integer.parseInt(parts[20]);
		r1=Integer.parseInt(parts[24]);
		g1=Integer.parseInt(parts[26]);
		b1=Integer.parseInt(parts[28]);
		eColor=new Color(r,g,b);
		iColor=new Color(r1,g1,b1);
		
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShape(i).equals(new Donut(new Point(x,y),radius,innerRadius,eColor,iColor))) {
				selectShape=new SelectShapeCmd(model,model.getShape(i),support);
				selectShape.setTempSelectedShapes(selectedShapes);
				executeCommand(selectShape);
			}
		
		}
		
	}else if("Hexagon".equals(parts[2])) {
		x=Integer.parseInt(parts[6]);
		y=Integer.parseInt(parts[7]);
		radius=Integer.parseInt(parts[11]);
		
		r=Integer.parseInt(parts[14]);
		g=Integer.parseInt(parts[16]);
		b=Integer.parseInt(parts[18]);
		r1=Integer.parseInt(parts[22]);
		g1=Integer.parseInt(parts[24]);
		b1=Integer.parseInt(parts[26]);
		eColor=new Color(r,g,b);
		iColor=new Color(r1,g1,b1);
		Hexagon hex=new Hexagon(x,y,radius);
		
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShape(i).equals(new HexagonAdapter(hex,eColor,iColor))) {
				selectShape=new SelectShapeCmd(model,model.getShape(i),support);
				selectShape.setTempSelectedShapes(selectedShapes);
				executeCommand(selectShape);
			}
		
		}
				
	}
	
    }else if("Deselected".equals(parts[0])) {
    	
    	if ("Point".equals(parts[2])) {
    		x=Integer.parseInt(parts[4]);
    		y=Integer.parseInt(parts[5]);
    		r=Integer.parseInt(parts[8]);
    		g=Integer.parseInt(parts[10]);
    		bl=Integer.parseInt(parts[12]);
    		eColor=new Color(r,g,bl);
    		
    		for(int i=0;i<model.getShapes().size();i++) {
    			if(model.getShape(i).equals(new Point(x,y,eColor))) {
    				deselectShape=new DeselectShapeCmd(model,model.getShape(i),support);
    				deselectShape.setTempSelectedShapes(selectedShapes);
    				executeCommand(deselectShape);
    			}
    			
    		}
    		
    	}else if("Rectangle".equals(parts[2])) {
    		x=Integer.parseInt(parts[7]);
    		y=Integer.parseInt(parts[8]);
    		width=Integer.parseInt(parts[12]);
    		height=Integer.parseInt(parts[16]);
    		r=Integer.parseInt(parts[19]);
    		g=Integer.parseInt(parts[21]);
    		b=Integer.parseInt(parts[23]);
    		r1=Integer.parseInt(parts[27]);
    		g1=Integer.parseInt(parts[29]);
    		b1=Integer.parseInt(parts[31]);
    		
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		for(int i=0;i<model.getShapes().size();i++) {
    			if(model.getShape(i).equals(new Rectangle(new Point(x,y),width,height,eColor,iColor))) {
    				deselectShape=new DeselectShapeCmd(model,model.getShape(i),support);
    				deselectShape.setTempSelectedShapes(selectedShapes);
    				executeCommand(deselectShape);
    			}
    		}
    		
    	}else if("Line".equals(parts[2])) {
    		x=Integer.parseInt(parts[5]);
    		y=Integer.parseInt(parts[6]);
    		x1=Integer.parseInt(parts[9]);
    		y1=Integer.parseInt(parts[10]);
    		r=Integer.parseInt(parts[13]);
    		g=Integer.parseInt(parts[15]);
    		bl=Integer.parseInt(parts[17]);
    		eColor=new Color(r,g,bl);
    		
    		for(int i=0;i<model.getShapes().size();i++) {
    			if(model.getShape(i).equals(new Line(new Point(x,y),new Point(x1,y1),eColor))) {
    				deselectShape=new DeselectShapeCmd(model,model.getShape(i),support);
    				deselectShape.setTempSelectedShapes(selectedShapes);
    				executeCommand(deselectShape);
    			}
    		
    		}
    		
    		
    	}else if("Circle".equals(parts[2])) {
    		x=Integer.parseInt(parts[6]);
    		y=Integer.parseInt(parts[7]);
    		radius=Integer.parseInt(parts[11]);
    		
    		r=Integer.parseInt(parts[14]);
    		g=Integer.parseInt(parts[16]);
    		b=Integer.parseInt(parts[18]);
    		r1=Integer.parseInt(parts[22]);
    		g1=Integer.parseInt(parts[24]);
    		b1=Integer.parseInt(parts[26]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		
    		for(int i=0;i<model.getShapes().size();i++) {
    			if(model.getShape(i).equals(new Circle(new Point(x,y),radius,eColor,iColor))) {
    				deselectShape=new DeselectShapeCmd(model,model.getShape(i),support);
    				deselectShape.setTempSelectedShapes(selectedShapes);
    				executeCommand(deselectShape);
    			}
    		
    		}
    		
    		
    	}else if("Donut".equals(parts[2])) {
    		x=Integer.parseInt(parts[5]);
    		y=Integer.parseInt(parts[6]);
    		radius=Integer.parseInt(parts[10]);
    		innerRadius=Integer.parseInt(parts[13]);
    		r=Integer.parseInt(parts[16]);
    		g=Integer.parseInt(parts[18]);
    		b=Integer.parseInt(parts[20]);
    		r1=Integer.parseInt(parts[24]);
    		g1=Integer.parseInt(parts[26]);
    		b1=Integer.parseInt(parts[28]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		for(int i=0;i<model.getShapes().size();i++) {
    			if(model.getShape(i).equals(new Donut(new Point(x,y),radius,innerRadius,eColor,iColor))) {
    				deselectShape=new DeselectShapeCmd(model,model.getShape(i),support);
    				deselectShape.setTempSelectedShapes(selectedShapes);
    				executeCommand(deselectShape);
    			}
    		
    		}
    		
    	}else if("Hexagon".equals(parts[2])) {
    		x=Integer.parseInt(parts[6]);
    		y=Integer.parseInt(parts[7]);
    		radius=Integer.parseInt(parts[11]);
    		
    		r=Integer.parseInt(parts[14]);
    		g=Integer.parseInt(parts[16]);
    		b=Integer.parseInt(parts[18]);
    		r1=Integer.parseInt(parts[22]);
    		g1=Integer.parseInt(parts[24]);
    		b1=Integer.parseInt(parts[26]);
    		eColor=new Color(r,g,b);
    		iColor=new Color(r1,g1,b1);
    		Hexagon hex=new Hexagon(x,y,radius);
    		for(int i=0;i<selectedShapes.size();i++) {
    			if(selectedShapes.get(i).equals(new HexagonAdapter(hex,eColor,iColor))) {
    				deselectShape=new DeselectShapeCmd(model,selectedShapes.get(i),support);
    				deselectShape.setTempSelectedShapes(selectedShapes);
    				executeCommand(deselectShape);
    			}
    		
    		}
    				
    		
    }
    	
///------------------------------------------------------------------------DEO VEZAN ZA UNDO------------------------------------------------------------------	
      }else if("Undo".equals(parts[0])) {
    	  
    	 undo();
    	 
      } else if ("Redo".equals(parts[0])) {
    	  
    	 redo();
    	 }		
      }
    		
public void setSelectedShapeType(String shapeType) {
    this.selectedShapeType = shapeType;
}

public DrawingModel getModel() {
return model;
}


public void setModel(DrawingModel model) {
this.model = model;
}

public DrawingFrame getFrame() {
return frame;
}

public void setFrame(DrawingFrame frame) {
this.frame = frame;
}





	
	
}
