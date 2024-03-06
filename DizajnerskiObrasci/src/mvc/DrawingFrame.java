package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import geometry.Point;
import geometry.Shape;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class DrawingFrame extends JFrame{

 private static final long serialVersionUID = 1L;
 private DrawingController controller;
 private DrawingView panel=new DrawingView();
 private JPanel contentPane;
 private Color edgeColor,innerColor;
 //Buttons
 JToggleButton tglbtnPoint;
 JToggleButton tglbtnDonut;
 JToggleButton tglbtnCircle;
 JToggleButton tglbtnLine;
 JToggleButton tglbtnRectangle;
 JToggleButton tglbtnDel;
 JToggleButton tglbtnSel;
 JToggleButton tglbtnDraw;
 JToggleButton tglbtnMod;
 private final ButtonGroup buttonGroupShapes = new ButtonGroup();
 private final ButtonGroup workButtons = new ButtonGroup();
 private JToggleButton tglbtnUndo;
 private JToggleButton tglbtnRedo;
 private JToggleButton tglbtnTF;
 private JToggleButton tglbtnTB;
 private JButton btnEdgeColor;
 private JButton btnInnerColor;
 private JToggleButton tglbtnBTF;
 private JToggleButton tglbtnBTB;
 private JToggleButton tglbtnHexagon;
 private JScrollPane scrollPane;
 private JMenuBar menuBar;
 private JMenuItem importFile;
 private JMenuItem saveFile;
 private JMenuItem loadPerCommand;


 
public DrawingFrame() {
	setTitle("Jelena Mihalek, IT3/2021");
	
	menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	
	saveFile = new JMenuItem("Save");
	saveFile.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			controller.save();
		}
	});
	menuBar.add(saveFile);
	
	importFile = new JMenuItem("Import");
	importFile.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			controller.load();
		}
	});
	menuBar.add(importFile);
	
	loadPerCommand = new JMenuItem("Load Per Command");
	loadPerCommand.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			controller.loadPerCommand();
		}
	});
	menuBar.add(loadPerCommand);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	setContentPane(contentPane);

	JPanel panelNorth = new JPanel();
	contentPane.add(panelNorth,BorderLayout.NORTH);
	GridBagConstraints gbc_panelNorth = new GridBagConstraints();
	gbc_panelNorth.anchor = GridBagConstraints.NORTHWEST;
	gbc_panelNorth.insets = new Insets(0, 0, 5, 5);
	gbc_panelNorth.gridx = 3;
	gbc_panelNorth.gridy = 0;
	panelNorth.setBounds(100, 100, 500, 200);
	panelNorth.setBackground(new Color(50, 143, 168));
	JPanel panelWest = new JPanel();
	contentPane.add(panelWest, BorderLayout.WEST);
	panelWest.setBackground(new Color(50, 143, 168));
	JPanel panelEast = new JPanel();
	panelEast.setBackground(new Color(50, 143, 168));
	contentPane.add(panelEast, BorderLayout.EAST);
	GridBagLayout gbl_panelEast = new GridBagLayout();
	gbl_panelEast.columnWidths = new int[] { 50,0, 120, 20  };
	gbl_panelEast.rowHeights = new int[] { 0, 400 };
	gbl_panelEast.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
	gbl_panelEast.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	panelEast.setLayout(gbl_panelEast);
	
	scrollPane = new JScrollPane();
	scrollPane.setName("Commands");

	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	scrollPane.setBackground(Color.white);
	scrollPane.setSize(150, 350);
	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	gbc_scrollPane.fill = GridBagConstraints.BOTH;
	gbc_scrollPane.gridx = 2;
	gbc_scrollPane.gridy = 1;
	panelEast.add(scrollPane, gbc_scrollPane);
	
	JPanel panelSouth = new JPanel();
	contentPane.add(panelSouth, BorderLayout.SOUTH);
	GridBagLayout gbl_panelSouth = new GridBagLayout();
	gbl_panelSouth.columnWidths = new int[] { 80, 0, 0, 0, 0, 0, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gbl_panelSouth.rowHeights = new int[] {20, 0, 30, 0};
	gbl_panelSouth.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	gbl_panelSouth.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
	panelSouth.setLayout(gbl_panelSouth);
	panelSouth.setBackground(new Color(50, 143, 168));
	getContentPane().add(panel,BorderLayout.CENTER);

	
	
	tglbtnHexagon = new JToggleButton("HEXAGON");
	tglbtnHexagon.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tglbtnHexagon.setSelected(true);
		}
	});
	
	
			
	tglbtnPoint = new JToggleButton("POINT");
	tglbtnPoint.setSelected(true);
	GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
	gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
	gbc_tglbtnPoint.gridx = 1;
	gbc_tglbtnPoint.gridy = 0;
	panelNorth.add(tglbtnPoint, gbc_tglbtnPoint);
	buttonGroupShapes.add(tglbtnPoint);
	tglbtnPoint.addActionListener(new ActionListener() {
					    
		 public void actionPerformed(ActionEvent e) {
			 tglbtnPoint.setSelected(true);	
			 }
				});
	panelNorth.add(tglbtnHexagon);
	buttonGroupShapes.add(tglbtnHexagon);
	  tglbtnLine = new JToggleButton("LINE");
    	GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnLine.gridx = 2;
		gbc_tglbtnLine.gridy = 0;
		panelNorth.add(tglbtnLine, gbc_tglbtnLine);
		buttonGroupShapes.add(tglbtnLine);
		tglbtnLine.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  tglbtnLine.setSelected(true);		
			  }
		});

	
	
			tglbtnRectangle = new JToggleButton("RECTANGLE");
			GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
			gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnRectangle.gridx = 4;
			gbc_tglbtnRectangle.gridy = 0;
			panelNorth.add(tglbtnRectangle, gbc_tglbtnRectangle);
			buttonGroupShapes.add(tglbtnRectangle);
			tglbtnRectangle.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	 tglbtnRectangle.setSelected(true);	
			    	}
			});

			tglbtnCircle = new JToggleButton("CIRCLE");
			GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
			gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnCircle.gridx = 5;
			gbc_tglbtnCircle.gridy = 0;
			panelNorth.add(tglbtnCircle, gbc_tglbtnCircle);
			buttonGroupShapes.add(tglbtnCircle);
			tglbtnCircle.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        tglbtnCircle.setSelected(true);
			    }
			});

	tglbtnDonut = new JToggleButton("DONUT");
	GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
	gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 0);
	gbc_tglbtnDonut.gridx = 6;
	gbc_tglbtnDonut.gridy = 0;
	panelNorth.add(tglbtnDonut, gbc_tglbtnDonut);
	buttonGroupShapes.add(tglbtnDonut);
	tglbtnDonut.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	       tglbtnDonut.setSelected(true);
	    }
	});
	
	panel.setBackground(new Color(255, 255, 255));
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[] {0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gbl_panel.rowHeights = new int[] { 0, 0 };
	gbl_panel.columnWeights = new double[] {0.0,0.0,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
	gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	panel.setLayout(gbl_panel);
	//--------------------------------------------OBRADJUJEMO KLIK NA PANELU------------------------------------------------s
	panel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point mouseClick = new Point(e.getX(), e.getY());
            controller.handleMouseClick(mouseClick);
        }
    });
			
				
			
				tglbtnSel = new JToggleButton("SELECT");
				tglbtnSel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tglbtnSel.setSelected(true);
					}
				});
				
					
					tglbtnDraw = new JToggleButton("DRAW");
					tglbtnDraw.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tglbtnDraw.setSelected(true); 
						}
					});
					
		    	workButtons.add(tglbtnDraw);
				GridBagConstraints gbc_tglbtnDraw = new GridBagConstraints();
				gbc_tglbtnDraw.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnDraw.gridx = 1;
				gbc_tglbtnDraw.gridy = 1;
				panelSouth.add(tglbtnDraw, gbc_tglbtnDraw);
				workButtons.add(tglbtnSel);
				GridBagConstraints gbc_tglbtnSel = new GridBagConstraints();
				gbc_tglbtnSel.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnSel.gridx = 2;
				gbc_tglbtnSel.gridy = 1;
				panelSouth.add(tglbtnSel, gbc_tglbtnSel);
		
//----------------------------DELETE-------------------------------------
		tglbtnDel = new JToggleButton("DELETE");
		workButtons.add(tglbtnDel);
		tglbtnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteSelectedShape();
			}
		});
		
			GridBagConstraints gbc_tglbtnDel = new GridBagConstraints();
			gbc_tglbtnDel.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnDel.gridx = 3;
			gbc_tglbtnDel.gridy = 1;
			panelSouth.add(tglbtnDel, gbc_tglbtnDel);
			
				//-----------------------------------MODIFIKACIJA-------------------------
				tglbtnMod = new JToggleButton("MODIFY");
				tglbtnMod.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {		
						tglbtnMod.setSelected(true);
						int index = controller.getSelected();
						if (index < 0)
							return;
						Shape selected = controller.getModel().getShape(index);
						controller.openModifyShapeDialog(selected);
						}
				});
				
				workButtons.add(tglbtnMod);
				GridBagConstraints gbc_tglbtnMod = new GridBagConstraints();
				gbc_tglbtnMod.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnMod.gridx = 4;
				gbc_tglbtnMod.gridy = 1;
				panelSouth.add(tglbtnMod, gbc_tglbtnMod);
			
			tglbtnUndo = new JToggleButton("UNDO");
			tglbtnUndo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.undo();
				}
			});
			GridBagConstraints gbc_tglbtnUndo = new GridBagConstraints();
			gbc_tglbtnUndo.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnUndo.gridx = 5;
			gbc_tglbtnUndo.gridy = 1;
			panelSouth.add(tglbtnUndo, gbc_tglbtnUndo);
			workButtons.add(tglbtnUndo);
					
					tglbtnRedo = new JToggleButton("REDO");
					tglbtnRedo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							controller.redo();
						}
					});
					workButtons.add(tglbtnRedo);
					GridBagConstraints gbc_tglbtnRedo= new GridBagConstraints();
					gbc_tglbtnRedo.insets = new Insets(0, 0, 5, 5);
					gbc_tglbtnRedo.gridx = 6;
					gbc_tglbtnRedo.gridy = 1;
					panelSouth.add(tglbtnRedo, gbc_tglbtnRedo);
					
					tglbtnTF = new JToggleButton("TO FRONT");
					tglbtnTF.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tglbtnTF.setSelected(true);
							
							Shape selected = controller.getModel().getShape(controller.getSelected());
							controller.toFront(selected);
							
						}
					});
					workButtons.add(tglbtnTF);
					GridBagConstraints gbc_tglbtnBTF = new GridBagConstraints();
					gbc_tglbtnBTF.insets = new Insets(0, 0, 5, 5);
					gbc_tglbtnBTF.gridx = 7;
					gbc_tglbtnBTF.gridy = 1;
					panelSouth.add(tglbtnTF, gbc_tglbtnBTF);
					
					tglbtnTB = new JToggleButton("TO BACK");
					tglbtnTB.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tglbtnTB.setSelected(true);
							Shape selected = controller.getModel().getShape(controller.getSelected());
							controller.toBack(selected);
						}
					});
					workButtons.add(tglbtnTB);
					GridBagConstraints gbc_tglbtnBTB = new GridBagConstraints();
					gbc_tglbtnBTB.insets = new Insets(0, 0, 5, 5);
					gbc_tglbtnBTB.gridx = 8;
					gbc_tglbtnBTB.gridy = 1;
					panelSouth.add(tglbtnTB, gbc_tglbtnBTB);
					
					tglbtnBTF = new JToggleButton("BRING TO FRONT");
					tglbtnBTF.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Shape selected = controller.getModel().getShape(controller.getSelected());
							controller.bringToFront(selected);
						}
					});
					workButtons.add(tglbtnBTF);
					GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
					gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 5);
					gbc_tglbtnNewToggleButton.gridx = 9;
					gbc_tglbtnNewToggleButton.gridy = 1;
					panelSouth.add(tglbtnBTF, gbc_tglbtnNewToggleButton);
					
					tglbtnBTB = new JToggleButton("BRING TO BACK");
					tglbtnBTB.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Shape selected = controller.getModel().getShape(controller.getSelected());
							controller.bringToBack(selected);
						}
					});
					workButtons.add(tglbtnBTB);
					GridBagConstraints gbc_tglbtnNewToggleButton1 = new GridBagConstraints();
					gbc_tglbtnNewToggleButton1.insets = new Insets(0, 0, 5, 5);
					gbc_tglbtnNewToggleButton1.gridx = 10;
					gbc_tglbtnNewToggleButton1.gridy = 1;
					panelSouth.add(tglbtnBTB, gbc_tglbtnNewToggleButton1);
					
							JButton btnE = new JButton("EXIT");
							btnE.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									setVisible(false);
								}
							});
							GridBagConstraints gbc_btnE = new GridBagConstraints();
							gbc_btnE.insets = new Insets(0, 0, 5, 5);
							gbc_btnE.anchor = GridBagConstraints.NORTHWEST;
							gbc_btnE.gridx = 11;
							gbc_btnE.gridy = 1;
							panelSouth.add(btnE, gbc_btnE);
			
			
//---------------------------------------------------------BOJE------------------------------------------------------------		
			
			btnEdgeColor = new JButton("EDGE COLOR");
			
			
			btnEdgeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edgeColor=JColorChooser.showDialog(null, "EDGE COLOR", edgeColor);
					btnEdgeColor.setBackground(edgeColor);
				//updateBtnEdgeColorBackground(JColorChooser.showDialog(null, "EDGE COLOR", edgeColor));
				}
			});
			
			panelNorth.add(btnEdgeColor);
			
			btnInnerColor = new JButton("INNER COLOR");
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor=JColorChooser.showDialog(null, "INNER COLOR", innerColor);
					btnInnerColor.setBackground(innerColor);
					//updateBtnInnerColorBackground(JColorChooser.showDialog(null, "INNER COLOR", innerColor));
				}
			});
			
			panelNorth.add(btnInnerColor);
					
}

public JToggleButton getTglbtnDraw() {
	return tglbtnDraw;
}

public void setTglbtnDraw(JToggleButton tglbtnDraw) {
	this.tglbtnDraw = tglbtnDraw;
}

public JToggleButton getTglbtnPoint() {
	return tglbtnPoint;
}

public void setTglbtnPoint(JToggleButton tglbtnPoint) {
	this.tglbtnPoint = tglbtnPoint;
}

public JToggleButton getTglbtnDonut() {
	return tglbtnDonut;
}

public void setTglbtnDonut(JToggleButton tglbtnDonut) {
	this.tglbtnDonut = tglbtnDonut;
}

public JToggleButton getTglbtnCircle() {
	return tglbtnCircle;
}

public void setTglbtnCircle(JToggleButton tglbtnCircle) {
	this.tglbtnCircle = tglbtnCircle;
}

public JToggleButton getTglbtnLine() {
	return tglbtnLine;
}

public void setTglbtnLine(JToggleButton tglbtnLine) {
	this.tglbtnLine = tglbtnLine;
}

public JToggleButton getTglbtnRectangle() {
	return tglbtnRectangle;
}


public JToggleButton getTglbtnHexagon() {
	return tglbtnHexagon;
}

public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
	this.tglbtnHexagon = tglbtnHexagon;
}

public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
	this.tglbtnRectangle = tglbtnRectangle;
}

public JToggleButton getTglbtnDel() {
	return tglbtnDel;
}

public void setTglbtnDel(JToggleButton tglbtnDel) {
	this.tglbtnDel = tglbtnDel;
}

public JToggleButton getTglbtnSel() {
	return tglbtnSel;
}

public void setTglbtnSel(JToggleButton tglbtnSel) {
	this.tglbtnSel = tglbtnSel;
}

public JToggleButton getTglbtnMod() {
	return tglbtnMod;
}

public void setTglbtnMod(JToggleButton tglbtnMod) {
	this.tglbtnMod = tglbtnMod;
}	


public JToggleButton getTglbtnUndo() {
	return tglbtnUndo;
}

public void setTglbtnUndo(JToggleButton tglbtnUndo) {
	this.tglbtnUndo = tglbtnUndo;
}

public JToggleButton getTglbtnRedo() {
	return tglbtnRedo;
}

public void setTglbtnRedo(JToggleButton tglbtnRedo) {
	this.tglbtnRedo = tglbtnRedo;
}

public DrawingController getController() {
	return controller;
}

public void setController(DrawingController controller) {
	this.controller = controller;
}

public DrawingView getView() {
	return panel;
}

public void setView(DrawingView view) {
	this.panel = view;
}

public void setEdgeColor(Color newEdgeColor) {
	this.edgeColor = newEdgeColor;
	btnEdgeColor.setBackground(newEdgeColor);
}

public Color getInnerColor() {
	return innerColor;
}

public void setInnerColor(Color innerColor) {
	this.innerColor = innerColor;
	btnInnerColor.setBackground(innerColor);
}

public Color getEdgeColor() {
	return edgeColor;
}

public void updateBtnEdgeColorBackground(Color newEdgeColor) {
	//edgeColor = newEdgeColor;
	btnEdgeColor.setBackground(newEdgeColor);
}

public void updateBtnInnerColorBackground(Color newInnerColor) {
	innerColor = newInnerColor;
	btnInnerColor.setBackground(newInnerColor);
}

public JToggleButton getTglbtnTF() {
	return tglbtnTF;
}

public void setTglbtnTF(JToggleButton tglbtnTF) {
	this.tglbtnTF = tglbtnTF;
}

public JToggleButton getTglbtnTB() {
	return tglbtnTB;
}

public void setTglbtnTB(JToggleButton tglbtnTB) {
	this.tglbtnTB = tglbtnTB;
}

public JToggleButton getTglbtnBTF() {
	return tglbtnBTF;
}

public void setTglbtnBTF(JToggleButton tglbtnBTF) {
	this.tglbtnBTF = tglbtnBTF;
}

public JToggleButton getTglbtnBTB() {
	return tglbtnBTB;
}

public void setTglbtnBTB(JToggleButton tglbtnBTB) {
	this.tglbtnBTB = tglbtnBTB;
}

public JScrollPane getScrollPane() {
	return scrollPane;
}

public void setScrollPane(JScrollPane scrollPane) {
	this.scrollPane = scrollPane;
}

}
