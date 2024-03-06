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

import geometry.Donut;
import geometry.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCX;
	private JTextField textCY;
	private JTextField textR;
	private JTextField textIR;
	private DlgDonut dlg;
	private Donut newDonut,modifyDonut;
	private Color eColor, iColor,tempEColor,tempIColor;
	private Point startPoint;
	protected boolean oK;
	private int radius;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
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
		gbl_panelCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCenter.setLayout(gbl_panelCenter);
		panelCenter.setBackground(new Color(110, 250, 147));
		
		JLabel lblTitle = new JLabel("DONUT");
		lblTitle.setFont(new Font("Ariel", Font.BOLD, 18));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 2;
		gbc_lblTitle.gridy = 1;
		panelCenter.add(lblTitle, gbc_lblTitle);
		
		JLabel lblCX = new JLabel(" Center X:");
		GridBagConstraints gbc_lblCX = new GridBagConstraints();
		gbc_lblCX.anchor = GridBagConstraints.EAST;
		gbc_lblCX.insets = new Insets(0, 0, 5, 5);
		gbc_lblCX.gridx = 0;
		gbc_lblCX.gridy = 3;
		panelCenter.add(lblCX, gbc_lblCX);
		
		textCX = new JTextField();
		GridBagConstraints gbc_textCX = new GridBagConstraints();
		gbc_textCX.gridwidth = 2;
		gbc_textCX.insets = new Insets(0, 0, 5, 5);
		gbc_textCX.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCX.gridx = 1;
		gbc_textCX.gridy = 3;
		panelCenter.add(textCX, gbc_textCX);
		textCX.setColumns(10);
		
		JLabel lblCY = new JLabel("Center Y:");
		GridBagConstraints gbc_lblCY = new GridBagConstraints();
		gbc_lblCY.anchor = GridBagConstraints.EAST;
		gbc_lblCY.insets = new Insets(0, 0, 5, 5);
		gbc_lblCY.gridx = 0;
		gbc_lblCY.gridy = 4;
		panelCenter.add(lblCY, gbc_lblCY);
		
		textCY = new JTextField();
		GridBagConstraints gbc_texTCY = new GridBagConstraints();
		gbc_texTCY.gridwidth = 2;
		gbc_texTCY.insets = new Insets(0, 0, 5, 5);
		gbc_texTCY.fill = GridBagConstraints.HORIZONTAL;
		gbc_texTCY.gridx = 1;
		gbc_texTCY.gridy = 4;
		panelCenter.add(textCY, gbc_texTCY);
		textCY.setColumns(10);
		
		JLabel lblR = new JLabel("Radius:");
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.anchor = GridBagConstraints.EAST;
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 0;
		gbc_lblR.gridy = 5;
		panelCenter.add(lblR, gbc_lblR);
		
		textR = new JTextField();
		GridBagConstraints gbc_textR = new GridBagConstraints();
		gbc_textR.gridwidth = 2;
		gbc_textR.insets = new Insets(0, 0, 5, 5);
		gbc_textR.fill = GridBagConstraints.HORIZONTAL;
		gbc_textR.gridx = 1;
		gbc_textR.gridy = 5;
		panelCenter.add(textR, gbc_textR);
		textR.setColumns(10);
		
		JLabel lblIR = new JLabel("Inner radius:");
		GridBagConstraints gbc_lblIR = new GridBagConstraints();
		gbc_lblIR.anchor = GridBagConstraints.EAST;
		gbc_lblIR.insets = new Insets(0, 0, 0, 5);
		gbc_lblIR.gridx = 0;
		gbc_lblIR.gridy = 6;
		panelCenter.add(lblIR, gbc_lblIR);
		
		textIR = new JTextField();
		GridBagConstraints gbc_textIR = new GridBagConstraints();
		gbc_textIR.gridwidth = 2;
		gbc_textIR.insets = new Insets(0, 0, 0, 5);
		gbc_textIR.fill = GridBagConstraints.HORIZONTAL;
		gbc_textIR.gridx = 1;
		gbc_textIR.gridy = 6;
		panelCenter.add(textIR, gbc_textIR);
		textIR.setColumns(10);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							int x = Integer.parseInt(textCX.getText());
							int y = Integer.parseInt(textCY.getText());
							int radius = Integer.parseInt(textR.getText());
							int innerRadius = Integer.parseInt(textIR.getText());
							if(x<0 || y<0 || radius<=0 ||innerRadius<=0 ||radius<=innerRadius) 
							{	JOptionPane.showMessageDialog(null,  "Radius, inner radius must be greater than 0 ! Also radius must bre greater than inner radius! Try again.","Error!", JOptionPane.ERROR_MESSAGE);
						
						return;
						}

						eColor = JColorChooser.showDialog(null, "EDGE COLOR", tempEColor);
						iColor = JColorChooser.showDialog(null, "INNER COLOR",tempIColor);
						if (eColor == null || iColor == null) {
							oK = false;
							return;
						}
						
						 if(newDonut!=null) {
							 newDonut.getCenter().setX(x);
							 newDonut.getCenter().setY(y);
							 newDonut.setRadius(radius);
							 newDonut.setInnerRadius(innerRadius);
							 newDonut.seteColor(eColor);
							 newDonut.setiColor(iColor);
						 }else {
							 modifyDonut=new Donut(new Point());
							 modifyDonut.getCenter().setX(x);
							 modifyDonut.getCenter().setY(y);
							 modifyDonut.setRadius(radius);
							 modifyDonut.setInnerRadius(innerRadius);
							 modifyDonut.seteColor(eColor);
							 modifyDonut.setiColor(iColor);
						 }
						 oK=true;
						 dispose();
						setVisible(false);
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
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	
	

	public Color getTempEColor() {
		return tempEColor;
	}

	public void setTempEColor(Color tempEColor) {
		this.tempEColor = tempEColor;
	}


	public Color getTempIColor() {
		return tempIColor;
	}

	public void setTempIColor(Color tempIColor) {
		this.tempIColor = tempIColor;
	}

	public JTextField getTextCX() {
		return textCX;
	}

	public void setTextCX(JTextField textCX) {
		this.textCX = textCX;
	}

	public JTextField getTextCY() {
		return textCY;
	}

	public void setTextCY(JTextField textCY) {
		this.textCY = textCY;
	}

	public JTextField getTextR() {
		return textR;
	}

	public void setTextR(JTextField textR) {
		this.textR = textR;
	}

	public JTextField getTextIR() {
		return textIR;
	}

	public void setTextIR(JTextField textIR) {
		this.textIR = textIR;
	}



	
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public int getRadius() {
		// TODO Auto-generated method stub
		return radius;
	}

	
	public boolean isOK() {
		return oK;
	}

	


	public Color geteColor() {
		return eColor;
	}

	public void seteColor(Color eColor) {
		this.eColor = eColor;
	}

	public Color getiColor() {
		return iColor;
	}

	public void setiColor(Color iColor) {
		this.iColor = iColor;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public void setRadius(int radius)
	{
		this.radius=radius;
	}

	public void setNewDonut(Point center) {
		this.newDonut=new Donut(center);
	    textCX.setText("" + newDonut.getCenter().getX());
		textCY.setText("" + newDonut.getCenter().getY());
	
	}
	
	
	public Donut getNewDonut() {
		return newDonut;
	}

	public void setModifyDonut(Donut modifyDonut) {
	//	this.modifyDonut=donut;
	    textCX.setText("" + modifyDonut.getCenter().getX());
		textCY.setText("" + modifyDonut.getCenter().getY());
		textR.setText("" + modifyDonut.getRadius());
		textIR.setText("" + modifyDonut.getInnerRadius());
		eColor=modifyDonut.geteColor();
		iColor=modifyDonut.getiColor();
	
	}
	

	/*public void setDonut(Point mouseClick) {
		
		textCX.setText("" + mouseClick.getX());
		textCY.setText("" + mouseClick.getY());
	}*/

	public Donut getModifyDonut() {
		return modifyDonut;
	}

	public DlgDonut getDlg() {
		return dlg;
	}

	public void setDlg(DlgDonut dlg) {
		this.dlg = dlg;
	}

	
	
}
