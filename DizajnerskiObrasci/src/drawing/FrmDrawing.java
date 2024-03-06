package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class FrmDrawing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PnlDrawing panel = new PnlDrawing();
	private Color eColor, iColor, currentEdgeColor,currentInnerColor;
	private Point startPoint;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	private final ButtonGroup buttonGroupShapes = new ButtonGroup();
	private final ButtonGroup workButtons = new ButtonGroup();
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnDel;
	JToggleButton tglbtnSel;
	JToggleButton tglbtnDraw;
	JToggleButton tglbtnMod;
	boolean doubleClick = false;
	private JButton btnCurrentColor;
	private JButton btnCurrentEdgeColor;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setTitle("Jelena Mihalek, IT3/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.addMouseListener(panelClickListener());
		panel.setBounds(150, 150, 500, 300);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 9;
		gbc_panel.gridheight = 14;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 2;

		panel.setBackground(new Color(192, 192, 192));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setBackground(new Color(50, 143, 168));
		JPanel panelEast = new JPanel();
		panelEast.setBackground(new Color(50, 143, 168));
		contentPane.add(panelEast, BorderLayout.EAST);
		GridBagLayout gbl_panelEast = new GridBagLayout();
		gbl_panelEast.columnWidths = new int[] { 0 };
		gbl_panelEast.rowHeights = new int[] { 0 };
		gbl_panelEast.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panelEast.rowWeights = new double[] { Double.MIN_VALUE };
		panelEast.setLayout(gbl_panelEast);
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(50, 143, 168));
		contentPane.add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] { 87, 0, 0, 10, 0, 0, 0, 0 };
		gbl_panelCenter.rowHeights = new int[] { 23, 0, 21, 21, 21, 21, 0, 0, 0 };
		gbl_panelCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCenter.setLayout(gbl_panelCenter);
				
						tglbtnPoint = new JToggleButton("POINT");
						tglbtnPoint.setSelected(true);
						GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
						gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
						gbc_tglbtnPoint.gridx = 1;
						gbc_tglbtnPoint.gridy = 0;
						panelCenter.add(tglbtnPoint, gbc_tglbtnPoint);
						buttonGroupShapes.add(tglbtnPoint);
		
				tglbtnLine = new JToggleButton("LINE");
				GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
				gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnLine.gridx = 2;
				gbc_tglbtnLine.gridy = 0;
				panelCenter.add(tglbtnLine, gbc_tglbtnLine);
				buttonGroupShapes.add(tglbtnLine);
		JPanel panelNorth = new JPanel();
		GridBagConstraints gbc_panelNorth = new GridBagConstraints();
		gbc_panelNorth.anchor = GridBagConstraints.NORTHWEST;
		gbc_panelNorth.insets = new Insets(0, 0, 5, 5);
		gbc_panelNorth.gridx = 3;
		gbc_panelNorth.gridy = 0;
		panelCenter.add(panelNorth, gbc_panelNorth);
		panelNorth.setBounds(100, 100, 500, 200);
		panelNorth.setBackground(new Color(50, 143, 168));
		
				tglbtnRectangle = new JToggleButton("RECTANGLE");
				GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
				gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnRectangle.gridx = 4;
				gbc_tglbtnRectangle.gridy = 0;
				panelCenter.add(tglbtnRectangle, gbc_tglbtnRectangle);
				buttonGroupShapes.add(tglbtnRectangle);
		
				tglbtnCircle = new JToggleButton("CIRCLE");
				GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
				gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnCircle.gridx = 5;
				gbc_tglbtnCircle.gridy = 0;
				panelCenter.add(tglbtnCircle, gbc_tglbtnCircle);
				buttonGroupShapes.add(tglbtnCircle);
		
		btnCurrentColor = new JButton("Current color");
		if(currentInnerColor==null) btnCurrentColor.setBackground(new Color(50, 143, 168));
		else btnCurrentColor.setBackground(currentInnerColor);
				tglbtnDonut = new JToggleButton("DONUT");
				GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
				gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 0);
				gbc_tglbtnDonut.gridx = 6;
				gbc_tglbtnDonut.gridy = 0;
				panelCenter.add(tglbtnDonut, gbc_tglbtnDonut);
				buttonGroupShapes.add(tglbtnDonut);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 6;
		panelCenter.add(btnCurrentColor, gbc_btnNewButton);
		
		
		
		btnCurrentEdgeColor = new JButton("Current edge color");
		if(currentEdgeColor==null) btnCurrentEdgeColor.setBackground(new Color(50, 143, 168));
		else btnCurrentEdgeColor.setBackground(currentEdgeColor);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 7;
		panelCenter.add(btnCurrentEdgeColor, gbc_btnNewButton_1);
		

		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSouth = new GridBagLayout();
		gbl_panelSouth.columnWidths = new int[] { 210, 0, 0, 55, 0, 0, 0, 0, 0, 0 };
		gbl_panelSouth.rowHeights = new int[] { 0, 21, 0 };
		gbl_panelSouth.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelSouth.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelSouth.setLayout(gbl_panelSouth);
		panelSouth.setBackground(new Color(50, 143, 168));

		tglbtnSel = new JToggleButton("SELECT");
		workButtons.add(tglbtnSel);
		

		GridBagConstraints gbc_tglbtnSel = new GridBagConstraints();
		gbc_tglbtnSel.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnSel.gridx = 1;
		gbc_tglbtnSel.gridy = 0;
		panelSouth.add(tglbtnSel, gbc_tglbtnSel);

		tglbtnDraw = new JToggleButton("DRAW");
		workButtons.add(tglbtnDraw);
		GridBagConstraints gbc_tglbtnDraw = new GridBagConstraints();
		gbc_tglbtnDraw.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnDraw.gridx = 2;
		gbc_tglbtnDraw.gridy = 0;
		panelSouth.add(tglbtnDraw, gbc_tglbtnDraw);
		
//----------------------------DELETE-------------------------------------
		tglbtnDel = new JToggleButton("DELETE");
		workButtons.add(tglbtnDel);
		tglbtnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are no shapes.", "ERROR!", JOptionPane.ERROR_MESSAGE);
					return;
				} else if (panel.getSelected() == -1)
					JOptionPane.showMessageDialog(null, "Please select your shape", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				else {
					String[] answer = { "YES", "NO" };
					{
						int option = JOptionPane.showOptionDialog(null, "Do you want to delete selected shape?",
								"WARNING!", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, answer, answer[0]);
						if (option == 0) {
							panel.removeSelected();
						}
					}
				}
			}
		});

		GridBagConstraints gbc_tglbtnDel = new GridBagConstraints();
		gbc_tglbtnDel.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnDel.gridx = 3;
		gbc_tglbtnDel.gridy = 0;
		panelSouth.add(tglbtnDel, gbc_tglbtnDel);

		//-----------------------------------MODIFIKACIJA-------------------------
		tglbtnMod = new JToggleButton("MODIFICATION");
		tglbtnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int index = panel.getSelected();
				if (index < 0)
					return;
				Shape selected = panel.getShape(index);

				if (selected instanceof Point) {
					Point point = (Point) selected;
					DlgPoint dlg = new DlgPoint();
					dlg.setModifyPoint(point);
					dlg.setVisible(true);
						repaint();

				} else if (selected instanceof Line) {
					Line line = (Line) selected;
					DlgLine dlg = new DlgLine();
					dlg.setModifyLine(line);
					dlg.setVisible(true);
						repaint();
					}
				
				else if (selected instanceof Donut) {
					Donut donut = (Donut) selected;
					DlgDonut dlg = new DlgDonut();
					dlg.setModifyDonut(donut);
					dlg.setVisible(true);
						repaint();
						
				}   else if (selected instanceof Circle) {
					Circle circle = (Circle) selected;
					DlgCircle dlg = new DlgCircle();
					dlg.setModifyCircle(circle);
					dlg.setVisible(true);
						repaint();
				} 
				else if (selected instanceof Rectangle) {
					Rectangle rectangle = (Rectangle) selected;
					DlgRectangle dlg = new DlgRectangle();
					dlg.setModifyRectangle(rectangle);
					dlg.setVisible(true);
						repaint();
				}
				

			}

		});
		workButtons.add(tglbtnMod);
		GridBagConstraints gbc_tglbtnMod = new GridBagConstraints();
		gbc_tglbtnMod.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnMod.gridx = 4;
		gbc_tglbtnMod.gridy = 0;
		panelSouth.add(tglbtnMod, gbc_tglbtnMod);
		
		btnNewButton = new JButton("UNDO");
		GridBagConstraints gbc_btnNewButton1 = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 0;
		panelSouth.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("REDO");
	//	GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 6;
		gbc_btnNewButton_1.gridy = 0;
		panelSouth.add(btnNewButton_1, gbc_btnNewButton_1);
		
				JButton btnE = new JButton("EXIT");
				btnE.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				GridBagConstraints gbc_btnE = new GridBagConstraints();
				gbc_btnE.insets = new Insets(0, 0, 5, 5);
				gbc_btnE.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnE.gridx = 7;
				gbc_btnE.gridy = 0;
				panelSouth.add(btnE, gbc_btnE);
	}
//------------------------------BUTTON DRAW I SELECTED--------------------------------------------------------------
	private MouseAdapter panelClickListener() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point mouseClick = new Point(e.getX(), e.getY());
				panel.deselect();

				if (tglbtnDraw.isSelected() == true) {
					panel.select(mouseClick);
					
						{
						if (tglbtnPoint.isSelected()==true) {
							DlgPoint dlg = new DlgPoint();
							dlg.setNewPoint(mouseClick);
						    dlg.setColor(eColor);
							dlg.setVisible(true);
					
							if (dlg.getNewPoint() != null)
								panel.add(dlg.getNewPoint());
							return;
							

						} else if (tglbtnLine.isSelected()==true) {
							if (doubleClick == true) {
								DlgLine dlg = new DlgLine();
								dlg.setNewLine(startPoint, mouseClick);
								dlg.seteColor(eColor);
								dlg.setVisible(true);
								//currentEdgeColor=eColor;
								
									panel.add(dlg.getNewLine());

								doubleClick = false;   // da treca tacka bude nova pocetna tacka
								return;
							}
							startPoint = mouseClick;
							doubleClick = true;
							return;

						} else if (tglbtnRectangle.isSelected()==true) {
							DlgRectangle dlg = new DlgRectangle();
							dlg.setNewRectangle(mouseClick);
							dlg.seteColor(eColor);
							dlg.setiColor(iColor);
							dlg.setVisible(true);
							
							if (dlg.getNewRectangle() != null)
								panel.add(dlg.getNewRectangle());
							return;
						} 
						else if (tglbtnCircle.isSelected()==true) {
							DlgCircle dlg = new DlgCircle();
							dlg.setNewCircle(mouseClick);
							dlg.seteColor(eColor);
							dlg.setiColor(iColor);
							dlg.setVisible(true);
							if (dlg.getNewCircle() != null)
								panel.add(dlg.getNewCircle());
							return;
							
						}	else if (tglbtnDonut.isSelected()==true) {
							DlgDonut dlg = new DlgDonut();
							dlg.setNewDonut(mouseClick);
							dlg.seteColor(eColor);
							dlg.setiColor(iColor);
							dlg.setVisible(true);
							
							if (dlg.getNewDonut() != null)
								panel.add(dlg.getNewDonut());
							return;
							
						}
						panel.deselect();
						
					}
					;
				}

				else if (tglbtnSel.isSelected() == true) {
					panel.select(mouseClick);
					Iterator<Shape> it = shapes.iterator();
					while (it.hasNext()) {
						Shape shape = it.next();
						shape.setSelected(false);
						if (shape.contains(e.getX(), e.getY())) {
							
							shape.setSelected(true);

						} else if (shape.isSelected() == true)
							panel.deselect();
					}
				}
			}
		};
	};

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

	public JToggleButton getTglbtnDraw() {
		return tglbtnDraw;
	}

	public void setTglbtnDraw(JToggleButton tglbtnDraw) {
		this.tglbtnDraw = tglbtnDraw;
	}

	public JToggleButton getTglbtnMod() {
		return tglbtnMod;
	}

	public void setTglbtnMod(JToggleButton tglbtnMod) {
		this.tglbtnMod = tglbtnMod;
	}

	public ButtonGroup getButtonGroupShapes() {
		return buttonGroupShapes;
	}

	public ButtonGroup getWorkButtons() {
		return workButtons;
	}

	public Color getEColor() {
		// TODO Auto-generated method stub
		return eColor;
	}

	public Color getIColor() {
		// TODO Auto-generated method stub
		return iColor;
	}
}
