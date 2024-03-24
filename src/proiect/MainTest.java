package proiect;

import java.util.Scanner;
import java.util.Vector;

public class MainTest {
	
	public static int clienti;
	public static Vector<Client> ClientV;
	
	
	
	
	//verificare conditii pentru afisari pe server
	public static void updater()
	{
		while(true)
		{
			for(int i=0;i<MainTest.clienti;i++)
			{
				Server.sliv(i);
				Client.retur(i);
				Curier.fail(i);
			}
			Firma.updateTable();
		}
	}
	
	

	
	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Introduceti numar clienti: ");
		clienti=Integer.parseInt(myObj.nextLine());
		myObj.close();
		new Server();
		new Curier();
		new Firma();
		ClientV=new Vector<>();
		
		//initializare clienti
		 for (int i = 0; i < clienti; i++) {
			 Client C=new Client();
			 ClientV.add(C);
	        }
		 
		
		updater();
	}

}
