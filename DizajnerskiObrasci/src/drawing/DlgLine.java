package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;
import stack.DlgAddDelete;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textSPX;
	private JTextField textSPY;
	private JTextField textEPX;
	private JTextField textEPY;
	private DlgAddDelete dialog;
	private Line newLine,modifyLine;
	private Color eColor,tempColor;
	protected boolean oK;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	
	public DlgLine() {
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		contentPanel.add(panelNorth);
		
		JPanel panelWest = new JPanel();
		contentPanel.add(panelWest);
		
		JPanel panelCenter = new JPanel();
		contentPanel.add(panelCenter);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCenter.setLayout(gbl_panelCenter);
		panelCenter.setBackground(new Color(110, 250, 147));
		
		JLabel lblTitle = new JLabel("LINE");
		lblTitle.setFont(new Font("Ariel", Font.BOLD, 18));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 2;
		gbc_lblTitle.gridy = 1;
		panelCenter.add(lblTitle, gbc_lblTitle);
		
		JLabel lblSPX = new JLabel(" Start point X:");
		GridBagConstraints gbc_lblSPX = new GridBagConstraints();
		gbc_lblSPX.anchor = GridBagConstraints.EAST;
		gbc_lblSPX.insets = new Insets(0, 0, 5, 5);
		gbc_lblSPX.gridx = 0;
		gbc_lblSPX.gridy = 3;
		panelCenter.add(lblSPX, gbc_lblSPX);
		
		textSPX = new JTextField();
		GridBagConstraints gbc_textSPX = new GridBagConstraints();
		gbc_textSPX.gridwidth = 2;
		gbc_textSPX.insets = new Insets(0, 0, 5, 5);
		gbc_textSPX.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSPX.gridx = 1;
		gbc_textSPX.gridy = 3;
		panelCenter.add(textSPX, gbc_textSPX);
		textSPX.setColumns(10);
		
		JLabel lblSPY = new JLabel("Start point Y:");
		GridBagConstraints gbc_lblSPY = new GridBagConstraints();
		gbc_lblSPY.anchor = GridBagConstraints.EAST;
		gbc_lblSPY.insets = new Insets(0, 0, 5, 5);
		gbc_lblSPY.gridx = 0;
		gbc_lblSPY.gridy = 4;
		panelCenter.add(lblSPY, gbc_lblSPY);
		
		textSPY = new JTextField();
		GridBagConstraints gbc_texTUPY = new GridBagConstraints();
		gbc_texTUPY.gridwidth = 2;
		gbc_texTUPY.insets = new Insets(0, 0, 5, 5);
		gbc_texTUPY.fill = GridBagConstraints.HORIZONTAL;
		gbc_texTUPY.gridx = 1;
		gbc_texTUPY.gridy = 4;
		panelCenter.add(textSPY, gbc_texTUPY);
		textSPY.setColumns(10);
		
		JLabel lblEPX = new JLabel("End point X:");
		GridBagConstraints gbc_lblEPX = new GridBagConstraints();
		gbc_lblEPX.anchor = GridBagConstraints.EAST;
		gbc_lblEPX.insets = new Insets(0, 0, 5, 5);
		gbc_lblEPX.gridx = 0;
		gbc_lblEPX.gridy = 5;
		panelCenter.add(lblEPX, gbc_lblEPX);
		
		textEPX = new JTextField();
		GridBagConstraints gbc_textEPX = new GridBagConstraints();
		gbc_textEPX.gridwidth = 2;
		gbc_textEPX.insets = new Insets(0, 0, 5, 5);
		gbc_textEPX.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEPX.gridx = 1;
		gbc_textEPX.gridy = 5;
		panelCenter.add(textEPX, gbc_textEPX);
		textEPX.setColumns(10);
		
		JLabel lblEPY = new JLabel("End point Y:");
		GridBagConstraints gbc_lblEPY = new GridBagConstraints();
		gbc_lblEPY.anchor = GridBagConstraints.EAST;
		gbc_lblEPY.insets = new Insets(0, 0, 5, 5);
		gbc_lblEPY.gridx = 0;
		gbc_lblEPY.gridy = 6;
		panelCenter.add(lblEPY, gbc_lblEPY);
		
		textEPY = new JTextField();
		GridBagConstraints gbc_textEPY = new GridBagConstraints();
		gbc_textEPY.gridwidth = 2;
		gbc_textEPY.insets = new Insets(0, 0, 5, 5);
		gbc_textEPY.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEPY.gridx = 1;
		gbc_textEPY.gridy = 6;
		panelCenter.add(textEPY, gbc_textEPY);
		textEPY.setColumns(10);
		
	
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					try {	
						int x1 = Integer.parseInt(textSPX.getText());
						int y1 = Integer.parseInt(textSPY.getText());
						int x2 = Integer.parseInt(textEPX.getText());
						int y2 = Integer.parseInt(textEPY.getText());
						
						if (x1 < 0 || y1 < 0 || x2<0 || y2<0) {
							JOptionPane.showMessageDialog(null, "X and Y must be greater or equals than 0",
									"Error!", JOptionPane.ERROR_MESSAGE);
							return;
					}
						eColor = JColorChooser.showDialog(null, "Choose a color", tempColor);
						if (eColor == null ) {
							oK = false;
							return;
						}
						if(newLine!=null) 
							{
							newLine.getStartPoint().setX(x1);
							newLine.getStartPoint().setY(y1);
							newLine.getEndPoint().setX(x2);
							newLine.getEndPoint().setY(y2);						
							newLine.seteColor(eColor);
							}
						else
						{
							modifyLine=new Line(new Point(),new Point());
							modifyLine.getStartPoint().setX(x1);
							modifyLine.getStartPoint().setY(y1);
							modifyLine.getEndPoint().setX(x2);
							modifyLine.getEndPoint().setY(y2);
							modifyLine.seteColor(eColor);			
							}
						oK=true;
						setVisible(false);
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Please enter a number!", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//setVisible(false);
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTextSPX() {
		return textSPX;
	}

	public void setTextSPX(JTextField textSPX) {
		this.textSPX = textSPX;
	}

	public JTextField getTextSPY() {
		return textSPY;
	}

	public void setTextSPY(JTextField textSPY) {
		this.textSPY = textSPY;
	}

	public JTextField getTextEPX() {
		return textEPX;
	}

	public void setTextEPX(JTextField textEPX) {
		this.textEPX = textEPX;
	}

	public JTextField getTextEPY() {
		return textEPY;
	}

	public void setTextEPY(JTextField textEPY) {
		this.textEPY = textEPY;
	}

	public DlgAddDelete getDialog() {
		return dialog;
	}

	public void setDialog(DlgAddDelete dialog) {
		this.dialog = dialog;
	}


	public JPanel getContentPanel() {
		return contentPanel;
	}

	
	public void setModifyLine(Line modifyLine) {
		//this.modifyLine=modifyLine;
		textSPX.setText("" + modifyLine.getStartPoint().getX());
		textSPY.setText("" + modifyLine.getStartPoint().getY());
		textEPX.setText("" + modifyLine.getEndPoint().getX());
		textEPY.setText("" + modifyLine.getEndPoint().getY());
		eColor = modifyLine.geteColor();
	}

	public Line getNewLine() {
		return newLine;
	}

	public void setNewLine(Point start,Point end) {
		this.newLine=new Line(start,end);
		textSPX.setText("" + start.getX());
		textSPY.setText("" + start.getY());
		textEPX.setText("" + end.getX());
		textEPY.setText("" + end.getY());
		eColor = newLine.geteColor();
	}

	public Color geteColor() {
		return eColor;
	}

	public void seteColor(Color eColor) {
		this.eColor = eColor;
	}


	public void setStartPoint(Point mouseClick) {
		
		// TODO Auto-generated method stub
		textSPX.setText("" +mouseClick.getX());
		textSPY.setText("" + mouseClick.getY());
		textEPX.setText("" + mouseClick.getX());
		textEPY.setText("" + mouseClick.getY());
		
		
	}

	public Line getModifyLine() {
		return modifyLine;
	}

	public void setTempColor(Color newTempColor) {
		this.tempColor=newTempColor;
	}

	public boolean isoK() {
		return oK;
	}

	
}
