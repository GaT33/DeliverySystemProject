package proiect;


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server implements Runnable {

	JFrame frame1;
	private JTextField txtListaAwburi;
	private JTextField txtStatusComenzi;
	private JTextField txtServerFirmaCurierat;
	public static List<String> ListaClienti=new ArrayList<String>();
	public static DefaultListModel<String> listModel;
	public static DefaultListModel<String> listModelC;
	public static DefaultListModel<String> listModel1;
	public static DefaultListModel<String> listModel1C;
	

	
	public static void main(String[] args) {
		new Thread(new Server()).start();
	}

	
	public void run() {
		//initialize();
	}
	
	public Server()
	{
		initialize();
	}
	
	
	public String AWBGenerator() {
		String uuid;
		uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0,10);
		ListaClienti.add(uuid);
        return uuid;
       
    }


	
	private void initialize() {
		
		
		//parametrii frame Server
		frame1 = new JFrame("Server");
		frame1.getContentPane().setBackground(new Color(128, 128, 128));
		frame1.setBounds(100, 100, 450, 300);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		
		//parametrii titlu Server
		txtServerFirmaCurierat = new JTextField();
		txtServerFirmaCurierat.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtServerFirmaCurierat.setEditable(false);
		txtServerFirmaCurierat.setBackground(new Color(164, 221, 221));
		txtServerFirmaCurierat.setSelectionColor(new Color(192, 192, 192));
		txtServerFirmaCurierat.setSelectedTextColor(new Color(192, 192, 192));
		txtServerFirmaCurierat.setText("Server Firma Curierat");
		txtServerFirmaCurierat.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerFirmaCurierat.setBounds(87, 11, 262, 29);
		frame1.getContentPane().add(txtServerFirmaCurierat);
		
		
		//parametrii lista AWB-uri
		txtListaAwburi = new JTextField();
		txtListaAwburi.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtListaAwburi.setEditable(false);
		txtListaAwburi.setBackground(new Color(192, 192, 192));
		txtListaAwburi.setHorizontalAlignment(SwingConstants.CENTER);
		txtListaAwburi.setText("Lista AWB-uri");
		txtListaAwburi.setBounds(35, 53, 145, 20);
		frame1.getContentPane().add(txtListaAwburi);

		
		
		
		listModel = new DefaultListModel<>();
		
		//Adaugare AWB-uri
        for(int i=0;i<MainTest.clienti;i++)
        	listModel.add(i, AWBGenerator());
        
        //Pregatire lista pentru curier
        listModelC=cloneList(listModel);
        
        
        JList<String> list = new JList<>(listModel);
		list.setBounds(35, 73, 145, 20);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(35, 80, 145, 170);
        frame1.getContentPane().add(scrollPane);
        
        
        
       
		//parametrii Status Comenzi
		txtStatusComenzi = new JTextField();
		txtStatusComenzi.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtStatusComenzi.setEditable(false);
		txtStatusComenzi.setBackground(new Color(192, 192, 192));
		txtStatusComenzi.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatusComenzi.setText("Status comenzi");
		txtStatusComenzi.setBounds(255, 53, 165, 20);
		frame1.getContentPane().add(txtStatusComenzi);
		
		 
        
        
        listModel1 = new DefaultListModel<>();
        
        // Adăugare status Comenzi
        for(int i=0;i<MainTest.clienti;i++)
        	listModel1.add(i, "AWB Creat - Colet in livrare");
        listModel1C=cloneList(listModel1);
        
        JList<String> list2 = new JList<>(listModel1);
		list2.setBounds(255, 73, 165, 40);
        JScrollPane scrollPane2 = new JScrollPane(list2);
        scrollPane2.setBounds(255, 80, 165, 170);
        frame1.getContentPane().add(scrollPane2);
   

		
		frame1.setVisible(true);
		
	}
	
	//functie pentru a copia o Lista intr-o alta Lista cu deep copy (fara referinte comune)
	public static DefaultListModel<String> cloneList(DefaultListModel<String> listModel) {
		DefaultListModel<String> clonedList = new DefaultListModel<String>();
	    for (int i=0; i<listModel.getSize(); i++) {
	    	clonedList.add(i,listModel.elementAt(i));
	    }
	    return clonedList;
	}
	
	//verificare succes livrare
	public static void sliv(int i)
	{
		
		int index=Server.listModelC.indexOf(Server.listModel.elementAt(i));
		if(index>=0 && (Curier.updaterCr[index]==true && MainTest.ClientV.elementAt(i).updaterCl==true))
		{
			
			listModel1.set(i,"Livrat!" );
			
			listModel1C.set(i,"Comanda Livrata. Multumim!");
			MainTest.ClientV.elementAt(i).txtStatusComanda.setText(listModel1C.elementAt(i));;
			
			
			long startTime = System.nanoTime();
	        long duration = 3000000000L; // Durata în nanosecunde
	        //while (System.nanoTime() - startTime < duration) {}  //timp de asteptare ptr vizualizare
	        
	        //updatere liste + stare curieri
	        listModelC.remove(index);
	        Curier.list.revalidate();
	        Curier.updaterCr[index]=false;
	        MainTest.ClientV.elementAt(i).updaterCl=false;
	        
	        try
	        {
	        Firma.performante.set(0, Firma.performante.get(0)+1);
	        }
	        catch(Exception e)
	        {
	        	;
	        }

		}
	}
}
	
	
	
	
