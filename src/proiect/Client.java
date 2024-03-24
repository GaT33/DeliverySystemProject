package proiect;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client implements Runnable {

	private JFrame frame3;
	private JTextField txtClient;
	public  JTextField txtAwbComanda;
	public 	JTextField txtStatusComanda;
	private static int number = 0;
	private final int idC = number++;
	public boolean updaterCl; 
	public boolean  returCl;

	
	public static void main(String[] args) {
		new Thread(new Client()).start();
	}

	

	public void run() {
		//initialize();
	}
	
	
	public Client()
	{
		initialize();
	}
	
	private void initialize() {
		
		
		
		//parametrii JFrame
		frame3 = new JFrame("Client");
		frame3.getContentPane().setBackground(new Color(128, 128, 128));
		frame3.setBounds(100, 100, 450, 300);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.getContentPane().setLayout(null);
		
		//parametrii titlu Client
		txtClient = new JTextField();
		txtClient.setEditable(false);
		txtClient.setBackground(new Color(164, 221, 221));
		txtClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtClient.setHorizontalAlignment(SwingConstants.CENTER);
		txtClient.setText("Client "+ (idC+1));
		txtClient.setBounds(81, 11, 267, 45);
		frame3.getContentPane().add(txtClient);
		txtClient.setColumns(10);
		
		
		//parametrii buton Confirm livrare
		JButton btnNewButton = new JButton("Confirm livrarea");
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updaterCl=true;
        		//System.out.println("Am modificat: " + updaterCl);
        			
        	}
        });
		btnNewButton.setBounds(10, 174, 171, 76);
		frame3.getContentPane().add(btnNewButton);
		
		
		//parametrii buton Doresc retur
		JButton btnNewButton_1 = new JButton("Doresc retur");
		btnNewButton_1.setBackground(new Color(255, 255, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returCl=true;
			}
		});
		btnNewButton_1.setBounds(272, 174, 152, 76);
		frame3.getContentPane().add(btnNewButton_1);
		
		
		//parametrii AWB afisat client
		txtAwbComanda = new JTextField();
		txtAwbComanda.setEditable(false);
		txtAwbComanda.setBackground(new Color(192, 192, 192));
		txtAwbComanda.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtAwbComanda.setText("AWB Comanda: " + Server.listModel.get(idC));
		txtAwbComanda.setBounds(51, 67, 326, 30);
		frame3.getContentPane().add(txtAwbComanda);
		txtAwbComanda.setColumns(10);
		
		
		//parametrii Status Comanda
		txtStatusComanda = new JTextField();
		txtStatusComanda.setEditable(false);
		txtStatusComanda.setBackground(new Color(192, 192, 192));
		txtStatusComanda.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtStatusComanda.setText("Status Comanda:" + Server.listModel1C.get(idC));
		txtStatusComanda.setBounds(51, 108, 326, 53);
		frame3.getContentPane().add(txtStatusComanda);
		txtStatusComanda.setColumns(10);
		
		
		 frame3.setVisible(true);
	}
	
	
	//verificare status retur
	public static void retur(int i)
	{
		if(MainTest.ClientV.elementAt(i).returCl==true)
		{
			

			Server.listModel1C.set(i,"Retur acceptat.");
			MainTest.ClientV.elementAt(i).txtStatusComanda.setText(Server.listModel1C.elementAt(i));
			
			MainTest.ClientV.elementAt(i).updaterCl=false;
			int index=Server.listModelC.indexOf(Server.listModel.elementAt(i));
			
			//actualizare status curier
			if(index==i)
			{
		
				Server.listModel1.set(i,"Cerere retur");
				Server.listModelC.set(index,Server.listModelC.elementAt(i)+"RET");
				Server.listModel.set(i, Server.listModel.elementAt(i)+"RET");
				Curier.list.revalidate();
				MainTest.ClientV.elementAt(i).txtAwbComanda.setText("AWB Comanda: " + Server.listModel.get(i));
			}
			else
			{
				
				if(index<0) //daca nu exista deja AWB-ul in lista Curierului, il adaugam
				{
				Server.listModel.set(i, Server.listModel.elementAt(i)+"RET");
				Server.listModelC.addElement(Server.listModel.elementAt(i));
				Curier.list.revalidate();
				Server.listModel1.set(i,"Cerere retur");
				MainTest.ClientV.elementAt(i).txtAwbComanda.setText("AWB Comanda: " + Server.listModel.get(i));
				}
				else //actualizam 
				{
					Server.listModel1.set(i,"Cerere retur");
					Server.listModelC.set(index,Server.listModelC.elementAt(index)+"RET");
					Server.listModel.set(i, Server.listModel.elementAt(i)+"RET");
					Curier.list.revalidate();
					MainTest.ClientV.elementAt(i).txtAwbComanda.setText("AWB Comanda: " + Server.listModel.get(i));
				}
				
			}
			MainTest.ClientV.elementAt(i).returCl=false;
			try
			{
			Firma.performante.set(2, Firma.performante.get(2)+1);
			}
			catch(Exception e)
			{
				;
			}
		}
		
	}
	
}
