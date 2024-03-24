package proiect;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.SwingConstants;
import java.awt.Color;

public class Firma {

	private JFrame frame3;
	private JTextField txtFirma;
	private JTextField txtLivrariOnorate;
	private JTextField txtLivrariEsuate;
	private JTextField txtLivrariOnorate_1;
	static private JTextField txtTest;
	static private JTextField textField_1;
	static private JTextField textField_2;
	public static Vector<Integer> performante = new Vector<>(3);
	Image img = Toolkit.getDefaultToolkit().getImage("photo.jpg");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Firma window = new Firma();
					window.frame3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Firma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		 //initializare performante 1-livrat 2-esuat 3-retur
		 for (int i = 0; i < 3; i++) {
	            performante.add(0);
	        }
		 
		 
		 
		frame3 = new JFrame();
		frame3.getContentPane().setBackground(new Color(128, 128, 128));
		frame3.setBounds(100, 100, 450, 300);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.getContentPane().setLayout(null);
		JPanel backgroundPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		backgroundPanel.setLayout(null);
		//frame3.add(backgroundPanel);

		frame3.getContentPane().add(backgroundPanel);

		txtFirma = new JTextField();
		txtFirma = new JTextField();
		txtFirma.setBackground(new Color(160, 208, 224));
		txtFirma.setHorizontalAlignment(SwingConstants.CENTER);
		txtFirma.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtFirma.setText("Firma");
		txtFirma.setBounds(67, 11, 310, 55);
		frame3.getContentPane().add(txtFirma);
		txtFirma.setColumns(10);

		txtLivrariOnorate = new JTextField();
		txtLivrariOnorate.setBackground(new Color(192, 192, 192));
		txtLivrariOnorate.setEditable(false);
		txtLivrariOnorate.setHorizontalAlignment(SwingConstants.CENTER);
		txtLivrariOnorate.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLivrariOnorate.setText("Livrari onorate");
		txtLivrariOnorate.setBounds(37, 106, 121, 37);
		frame3.getContentPane().add(txtLivrariOnorate);
		txtLivrariOnorate.setColumns(10);

		txtLivrariEsuate = new JTextField();
		txtLivrariEsuate.setBackground(new Color(192, 192, 192));
		txtLivrariEsuate.setEditable(false);
		txtLivrariEsuate.setHorizontalAlignment(SwingConstants.CENTER);
		txtLivrariEsuate.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLivrariEsuate.setText("Livrari esuate");
		txtLivrariEsuate.setBounds(37, 154, 121, 37);
		frame3.getContentPane().add(txtLivrariEsuate);
		txtLivrariEsuate.setColumns(10);

		txtLivrariOnorate_1 = new JTextField();
		txtLivrariOnorate_1.setBackground(new Color(192, 192, 192));
		txtLivrariOnorate_1.setEditable(false);
		txtLivrariOnorate_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtLivrariOnorate_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLivrariOnorate_1.setText("Livrari returnate");
		txtLivrariOnorate_1.setBounds(37, 202, 121, 37);
		frame3.getContentPane().add(txtLivrariOnorate_1);
		txtLivrariOnorate_1.setColumns(10);

		txtTest = new JTextField();
		txtTest.setBackground(new Color(192, 192, 192));
		txtTest.setEditable(false);
		txtTest.setBounds(180, 107, 54, 37);
		frame3.getContentPane().add(txtTest);
		txtTest.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(192, 192, 192));
		textField_1.setBounds(180, 152, 54, 39);
		frame3.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBackground(new Color(192, 192, 192));
		textField_2.setBounds(180, 202, 54, 37);
		frame3.getContentPane().add(textField_2);
		textField_2.setColumns(10);


		//lista 1
		DefaultListModel<String> listModel = new DefaultListModel<>();
		frame3.setVisible(true);

		//lista 2

		DefaultListModel<String> listModel1 = new DefaultListModel<>();
		frame3.setVisible(true);

		//lista 3
		DefaultListModel<String> listModel2 = new DefaultListModel<>();
		frame3.setVisible(true);


	}
	


	public static void updateTable()
	{
		
		txtTest.setText(String.valueOf(performante.get(0)));
		
		
		
		
		textField_1.setText(String.valueOf(performante.get(1)));
		
		
		
		
		textField_2.setText(String.valueOf(performante.get(2)));
		
		
		
		
		unlink();
		
		
	}
		

	private static void unlink()
	{
	
		if(performante.get(0)>2)
			performante.set(0,3);
		if(performante.get(1)>3)
			performante.set(1,4);
		if (performante.get(2)>4)
			performante.set(2,5);
		
	
	}
}

