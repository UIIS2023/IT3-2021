package sort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import geometry.Circle;
import stack.DlgAddDelete;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	private ArrayList <Circle> circles=new ArrayList<Circle>();
	private DefaultListModel<Circle> dlm= new DefaultListModel<Circle>();
	JList<Circle> list;
	private int i=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
		setTitle("Jelena Mihalek, IT3/2021");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setBackground(new Color(212, 123, 237));
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCenter.setLayout(gbl_panelCenter);
		
		JLabel lblTitle= new JLabel("ADD AND SORT CIRCLES");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 10;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panelCenter.add(lblTitle, gbc_lblNewLabel);
		lblTitle.setBackground(new Color(230,230,230));
		lblTitle.setFont(new Font("Ariel", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		panelCenter.add(scrollPane, gbc_scrollPane);
		
		  list=new JList<Circle>();
		 scrollPane.setViewportView(list);
		 list.setModel(dlm);
		
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setBackground(new Color(230, 128, 240));
		
		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
	
		

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAddDelete dialog=new DlgAddDelete ();
				dialog.setVisible(true);
				if(dialog.getCircle() != null) {
						dlm.add(i, dialog.getCircle());//ali se dodaje u listu
						i++;
						JOptionPane.showMessageDialog(null,"Circle is added!","GREAT!", JOptionPane.INFORMATION_MESSAGE);
						circles.add(dialog.getCircle());
				}
			
			}
		});
		panelSouth.add(btnAdd);
		
		JButton btnC = new JButton("CANCEL");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JButton btnS = new JButton("SORT");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dlm.isEmpty()==false)
				{
					circles.sort(null);
					list.setModel(sort());//da nam u poveze listu i metodu sort
				}
			}
		});
		panelSouth.add(btnS);
		panelSouth.add(btnC);
		
		
	}

	public DefaultListModel<Circle> sort() {
		Iterator<Circle> iterator = circles.iterator();
		DefaultListModel<Circle> dlm = new DefaultListModel<Circle>();
		while(iterator.hasNext()) {
		
			dlm.addElement(iterator.next());
		}	
		return dlm;
		
	}

}
