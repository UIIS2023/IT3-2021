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

import geometry.Point;
import geometry.Shape;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textX;
	private JTextField textY;
	private Point newPoint,modifyPoint;
	private Color eColor,tempColor;
	protected boolean oK;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	
	public DlgPoint() {
		setModal(true);
		setResizable(false);
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
		gbl_panelCenter.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCenter.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCenter.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCenter.setLayout(gbl_panelCenter);
		panelCenter.setBackground(new Color(50, 143, 168));
		
		JLabel lblTitle = new JLabel("POINT");
		lblTitle.setFont(new Font("Ariel", Font.BOLD, 18));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 2;
		gbc_lblTitle.gridy = 1;
		panelCenter.add(lblTitle, gbc_lblTitle);

		JLabel lblX = new JLabel("X:");
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.anchor = GridBagConstraints.EAST;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 0;
		gbc_lblX.gridy = 3;
		panelCenter.add(lblX, gbc_lblX);

		textX = new JTextField();
		GridBagConstraints gbc_textX = new GridBagConstraints();
		gbc_textX.gridwidth = 2;
		gbc_textX.insets = new Insets(0, 0, 5, 5);
		gbc_textX.fill = GridBagConstraints.HORIZONTAL;
		gbc_textX.gridx = 1;
		gbc_textX.gridy = 3;
		panelCenter.add(textX, gbc_textX);
		textX.setColumns(10);

		JLabel lblY = new JLabel(" Y:");
		GridBagConstraints gbc_lblY = new GridBagConstraints();
		gbc_lblY.anchor = GridBagConstraints.EAST;
		gbc_lblY.insets = new Insets(0, 0, 5, 5);
		gbc_lblY.gridx = 0;
		gbc_lblY.gridy = 4;
		panelCenter.add(lblY, gbc_lblY);

		textY = new JTextField();
		GridBagConstraints gbc_texTY = new GridBagConstraints();
		gbc_texTY.gridwidth = 2;
		gbc_texTY.insets = new Insets(0, 0, 5, 5);
		gbc_texTY.fill = GridBagConstraints.HORIZONTAL;
		gbc_texTY.gridx = 1;
		gbc_texTY.gridy = 4;
		panelCenter.add(textY, gbc_texTY);
		textY.setColumns(10);
		
		
		
	
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
							int x = Integer.parseInt(textX.getText());
							int y = Integer.parseInt(textY.getText());

							if (x < 0 || y < 0) {
								JOptionPane.showMessageDialog(null, "X and Y must be greater or equals than 0",
										"Error!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							eColor =JColorChooser.showDialog(null, "Choose a color", tempColor);
							System.out.println("u dijalogu boja kada kliknem na ok"+tempColor);
							if (eColor == null) {
								oK = false;
								return;
							}
						
							if(newPoint!=null) {
								newPoint.setX(x);
								newPoint.setY(y);
								newPoint.seteColor(eColor);
							} else {
								modifyPoint=new Point();
								modifyPoint.setX(x);
								modifyPoint.setY(y);
								modifyPoint.seteColor(eColor);
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
						
						dispose();
						setVisible(false);
						

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				dispose();
			}
		}
	}

	public JTextField getTextX() {
		return textX;
	}

	public Color geteColor() {
		return eColor;
	}

	public void seteColor(Color eColor) {
		this.eColor = eColor;
	}

	public void setTextX(JTextField textX) {
		this.textX = textX;
	}

	public JTextField getTextY() {
		return textY;
	}

	public void setTextY(JTextField textY) {
		this.textY = textY;
	}

	

	public void setColor(Color eColor) {

		this.eColor =eColor;

	}
	

	public Point getNewPoint() {
		return newPoint;
	}
	public void setNewPoint(Point newPoint) {
		this.newPoint = newPoint;
		textX.setText("" + newPoint.getX());
		textY.setText("" + newPoint.getY());
		
	}

	public void setModifyPoint(Point modifyPoint) {
		//this.modifyPoint = modifyPoint;
		textX.setText("" + modifyPoint.getX());
		textY.setText("" + modifyPoint.getY());
		setColor(eColor);
	}

	public Point getModifyPoint() {
		return modifyPoint;
	}

	public Color getTempColor() {
		return tempColor;
	}

	public void setTempColor(Color tempColor) {
		this.tempColor = tempColor;
	}

	public boolean isoK() {
		return oK;
	}
	
	
	


}
