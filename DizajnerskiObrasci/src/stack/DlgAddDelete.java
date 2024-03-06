package stack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import geometry.Circle;
import geometry.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgAddDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCX;
	private JTextField textCY;
	private JTextField textR;
	 private Circle circle;
	 protected boolean isOk;
	 private Color iColor,eColor;
	
	JButton okButton;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DlgAddDelete() {
		setModal(true);
		
		setTitle("Mihalek Jelena, IT3/2021");
		setResizable(false);
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		contentPanel.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setBackground(new Color(230, 230, 250));
		
		JPanel panelWest = new JPanel();
		contentPanel.add(panelWest, BorderLayout.WEST);
		panelWest.setBackground(new Color(230, 230, 250));
		
		JPanel panelCenter = new JPanel();
		contentPanel.add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCenter.setLayout(gbl_panelCenter);
		panelCenter.setBackground(new Color(230, 230, 250));
		
		JLabel lblTitle = new JLabel("CIRCLE");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 2;
		gbc_lblTitle.gridy = 1;
		panelCenter.add(lblTitle, gbc_lblTitle);
		lblTitle.setFont(new Font("Ariel", Font.BOLD, 18));
		
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
		gbc_lblR.insets = new Insets(0, 0, 0, 5);
		gbc_lblR.gridx = 0;
		gbc_lblR.gridy = 5;
		panelCenter.add(lblR, gbc_lblR);
		
		textR = new JTextField();
		GridBagConstraints gbc_textR = new GridBagConstraints();
		gbc_textR.gridwidth = 2;
		gbc_textR.insets = new Insets(0, 0, 0, 5);
		gbc_textR.fill = GridBagConstraints.HORIZONTAL;
		gbc_textR.gridx = 1;
		gbc_textR.gridy = 5;
		panelCenter.add(textR, gbc_textR);
		textR.setColumns(10);
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			 okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						circle=null;
						try {
						int x =Integer.parseInt(textCX.getText());
						int y = Integer.parseInt(textCY.getText());
						Point center=new Point(x,y);
						
						int radius = Integer.parseInt(textR.getText());
						
						if(radius<=0) 
							JOptionPane.showMessageDialog(null,  "Radius must be greater than 0 ! Try again.","Error!", JOptionPane.ERROR_MESSAGE);
						else if(x<0 || y<0)
							JOptionPane.showMessageDialog(null,  "X and Y must be greater than 0 ! Try again.","Error!", JOptionPane.ERROR_MESSAGE);
							
						else
						circle=new Circle(center,radius);
						setVisible(false);
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null,  "Please enter a number! Try again.","Error!", JOptionPane.ERROR_MESSAGE);
						}
						}
						
					
						
					}
				
				);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				 isOk = true;
				setVisible(false);
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


	

	public void setTextCX(JTextField textCX) {
		this.textCX = textCX;
	}

	public void setTextCY(JTextField textCY) {
		this.textCY = textCY;
	}

	public void setTextR(JTextField textR) {
		this.textR = textR;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public void setCircle(Point center, int radius, Color eColor, Color iColor) {
		textCX.setText("" + circle.getCenter().getX());
		textCY.setText("" + circle.getCenter().getY());
		textR.setText("" + circle.getRadius());
		eColor = circle.geteColor();
		iColor = circle.getiColor();
	}
	public void setPoint (Point startPoint)
	{
		textCX.setText("" + circle.getCenter().getX());
		textCY.setText("" + circle.getCenter().getY());	
	}


	public int getRadius() {
		// TODO Auto-generated method stub
		return circle.getRadius();
	}

	public Color getiColor() {
		return iColor;
	}

	public void setiColor(Color iColor) {
		this.iColor = iColor;
	}

	public Color geteColor() {
		return eColor;
	}

	public void seteColor(Color eColor) {
		this.eColor = eColor;
	}

	public JTextField getTextCX() {
		return textCX;
	}

	public JTextField getTextCY() {
		return textCY;
	}

	public JTextField getTextR() {
		return textR;
	}




	public void setCircle(Point mouseClick) {
		
		textCX.setText("" + mouseClick.getX());
		textCY.setText("" + mouseClick.getY());
	}


	
	
	
	
}
