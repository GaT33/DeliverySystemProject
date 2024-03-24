package proiect;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Curier implements Runnable {

	JFrame frame2;
	private JTextField txtCurier;
	public JTextField txtListaAwburiDe;
	public static JList<String> list;
	public static boolean[] updaterCr = new boolean[MainTest.clienti];
	public static boolean[] failCr = new boolean[MainTest.clienti];
	private static int index;

	
	public static void main(String[] args) {
		new Thread(new Curier()).start();
	}

	public void run() {
		//initialize();
	}
	
	public Curier()
	{
		initialize();
	}
	

	private void initialize() {
		
		//parametrii frame
		frame2 = new JFrame("Curier");
		frame2.getContentPane().setBackground(new Color(128, 128, 128));
		frame2.setBounds(100, 100, 450, 300);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		//parametrii titlu Curier
		txtCurier = new JTextField();
		txtCurier.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtCurier.setEditable(false);
		txtCurier.setBackground(new Color(164, 221, 221));
		txtCurier.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurier.setText("CURIER");
		txtCurier.setBounds(120, 11, 198, 36);
		frame2.getContentPane().add(txtCurier);
		
		//parametrii lista AWB-uri
        txtListaAwburiDe = new JTextField();
        txtListaAwburiDe.setEditable(false);
        txtListaAwburiDe.setBackground(new Color(192, 192, 192));
        txtListaAwburiDe.setHorizontalAlignment(SwingConstants.CENTER);
        txtListaAwburiDe.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtListaAwburiDe.setText("Lista AWB-uri");
        txtListaAwburiDe.setBounds(29, 55, 163, 20);
        frame2.getContentPane().add(txtListaAwburiDe);
        txtListaAwburiDe.setColumns(10);

      
		
		/*
		 * DefaultListModel<String>listModel = new DefaultListModel<>();
		 * listModel.add(0, "0"); listModel.add(1, "1");
		 */
        
        
         list = new JList<>(Server.listModelC);
        list.setBackground(new Color(192, 192, 192));
		list.setBounds(29, 69, 163, 167);
		
		
		list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getID() == MouseEvent.MOUSE_CLICKED) { 
                     index = list.locationToIndex(e.getPoint());
                    //System.out.println("Element selectat: " + Server.listModelC.getElementAt(index)+" "+updaterCr[index]);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(29, 86, 163, 167);
        frame2.getContentPane().add(scrollPane);
        
        
        
        //parametrii buton Colet Livrat
        JButton btnNewButton = new JButton("Colet livrat");
        btnNewButton.setBackground(new Color(0, 255, 0));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updaterCr[index]=true;
        		//System.out.println("Am modificat: " + updaterCr[index]);
        	}
        });
        btnNewButton.setBounds(257, 89, 124, 44);
        frame2.getContentPane().add(btnNewButton);
        
        
        //parametrii buton Livrare esuata
        JButton btnNewButton_1 = new JButton("Livrare esuata");
        btnNewButton_1.setBackground(new Color(255, 0, 0));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		failCr[index]=true;
        	}
        });
        btnNewButton_1.setBounds(257, 163, 124, 44);
        frame2.getContentPane().add(btnNewButton_1);
        
        
       

        frame2.setVisible(true);
		
		
	}
	
	
	public static void fail(int i)
	{
		if(failCr[i]==true)
		{
			//actualizare status client + server
			if(!Server.listModelC.isEmpty())
			{
			int index=Server.listModel.indexOf(Server.listModelC.elementAt(i));
			
			MainTest.ClientV.elementAt(index).updaterCl=false;
			Server.listModel1.set(index, "Comanda esuata.Ayae");
			Server.listModel1C.set(i, "Incercam maine...");
			MainTest.ClientV.elementAt(index).txtStatusComanda.setText(Server.listModel1C.elementAt(i));
			failCr[i]=false;
			}
			
			try
			{
			Firma.performante.set(1, Firma.performante.get(1)+1);
			}
			catch(Exception e)
			{
				;
			}
			
		}
		
	}
}

