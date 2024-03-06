package stack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import java.awt.GridLayout;
import geometry.Circle;
import java.awt.Font;
import javax.swing.JScrollPane;


public class FrmStack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Circle> dlm= new DefaultListModel<Circle>();
	private ArrayList <Circle> circles=new ArrayList <Circle>();
	private JList<Circle> list;
	DlgAddDelete dialog=new DlgAddDelete();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setTitle("Jelena Mihalek, IT3/2021");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470,370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter);
		panelCenter.setBackground(new Color(230, 230, 250));
				panelCenter.setLayout(new BorderLayout(0, 0));
				
				
				
				JPanel panelSouth = new JPanel();
				panelSouth.setBackground(new Color(230, 230, 250));
				GridBagLayout gbl_panelSouth = new GridBagLayout();
				gbl_panelSouth.columnWidths = new int[]{151, 0, 0, 0, 0, 51, 67, 0};
				gbl_panelSouth.rowHeights = new int[]{21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_panelSouth.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panelSouth.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelSouth.setLayout(gbl_panelSouth);
					panelCenter.add(panelSouth);
					
					JLabel lblTitle= new JLabel("DO YOU WANT TO SEE HOW STACK WORKS?");
					
					GridBagConstraints gbc_lblTitle = new GridBagConstraints();
					gbc_lblTitle.gridwidth = 7;
					gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
					gbc_lblTitle.gridx = 0;
					gbc_lblTitle.gridy = 0;
					panelSouth.add(lblTitle, gbc_lblTitle);
					lblTitle.setForeground(Color.black);
					lblTitle.setFont(new Font("Ariel", Font.BOLD, 18));
					lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
					
					JLabel lblStack = new JLabel("STACK");
					GridBagConstraints gbc_lblStack = new GridBagConstraints();
					gbc_lblStack.gridwidth = 3;
					gbc_lblStack.insets = new Insets(0, 0, 5, 5);
					gbc_lblStack.gridx = 1;
					gbc_lblStack.gridy = 2;
					panelSouth.add(lblStack, gbc_lblStack);
					lblStack.setForeground(Color.black);
					lblStack.setFont(new Font("Ariel", Font.BOLD, 20));
					lblStack.setHorizontalAlignment(SwingConstants.CENTER);
					
					
					
					
					JScrollPane scrollPane = new JScrollPane();
					GridBagConstraints gbc_scrollPane = new GridBagConstraints();
					gbc_scrollPane.gridheight = 5;
					gbc_scrollPane.gridwidth = 7;
					gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
					gbc_scrollPane.fill = GridBagConstraints.BOTH;
					gbc_scrollPane.gridx = 0;
					gbc_scrollPane.gridy = 3;
					panelSouth.add(scrollPane, gbc_scrollPane);
					
				
					
			
					
					JButton btnAdd = new JButton("ADD");
					btnAdd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							DlgAddDelete dialog=new DlgAddDelete ();
							dialog.setVisible(true);
							if(dialog.getCircle() != null) {
									dlm.add(0, dialog.getCircle());//ali se dodaje u listu
									circles.add(dialog.getCircle());
									JOptionPane.showMessageDialog(null,"Circle is added!","GREAT!", JOptionPane.INFORMATION_MESSAGE);
							}
							
							
							
						}
					});
					
					GridBagConstraints gbc_btnAdd = new GridBagConstraints();
					gbc_btnAdd.anchor = GridBagConstraints.NORTHWEST;
					gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
					gbc_btnAdd.gridx = 2;
					gbc_btnAdd.gridy = 9;
					panelSouth.add(btnAdd, gbc_btnAdd);
					
					JButton btnDelete = new JButton("DELETE");
					btnDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							DlgAddDelete dialog=new DlgAddDelete();
							
							if(dlm.isEmpty()==false)
							{
							
							int[] fields=new int[3] ;//napravili niz i pokupili podatke iz liste
							fields[0]=dlm.getElementAt(0).getCenter().getX();
							fields[1]=dlm.getElementAt(0).getCenter().getY();
							fields[2]=dlm.getElementAt(0).getRadius();
							dialog.getTextCX().setText(String.valueOf(fields[0]));
							dialog.getTextCY().setText(String.valueOf(fields[1]));
							dialog.getTextR().setText(String.valueOf(fields[2]));//popunili smo polja
							
							
							  dialog.setVisible(true);
							  if (dialog.isOk==true)
							  {
					
						        dialog.setCircle(dlm.getElementAt(0));
								dlm.removeElementAt(0);
								 circles.remove(0);
							  }
								  
							
							
							
							}
							else {
							JOptionPane.showMessageDialog(null,"Stack is empty!","ERROR", JOptionPane.ERROR_MESSAGE);
							}	
						}
					});
					
					GridBagConstraints gbc_btnDelete = new GridBagConstraints();
					gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
					gbc_btnDelete.anchor = GridBagConstraints.NORTHWEST;
					gbc_btnDelete.gridx = 3;
					gbc_btnDelete.gridy = 9;
					panelSouth.add(btnDelete, gbc_btnDelete);
		
		
					list = new JList<Circle>();
					scrollPane.setViewportView(list);
					list.setModel(dlm);
					
					
					
	}
}
