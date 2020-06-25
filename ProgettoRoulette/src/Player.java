import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Thread{
	
	int id; 
	double quote; 
	int number; 
	int play=5;
	Roulette r;
	
	/*public void notifica(String vincita) throws RemoteException{
		System.out.println(ID+": vincita pari a "+vincita);
	}*/
	
	public Player (int id)  {
		//creata sessione di gioco
		try {
			Registry registro = LocateRegistry.getRegistry();
			Roulette r = (Roulette) registro.lookup("Roulette");
			new Giocatore2(r);
		} catch (RemoteException | NotBoundException e) {	}  
	}


	//---------------------
	
	public synchronized void Bet() throws RemoteException{
		
		ArrayList allQuote= new ArrayList<>();
		ArrayList allNum = new ArrayList<>();
		
		Scanner s = new Scanner(System.in);
		System.out.print("Inserisci il tuo ID");
		String ID = s.nextLine();
		
		String risposta="Y";
		
		while((risposta.toUpperCase()).equals("Y")) {
				
			System.out.println("Inserisci la quota da scommettere");
			float quote = s.nextFloat();
			
			allQuote.add(quote);
			
			System.out.println("Inserisci il numero preferito"); 
			int number = s.nextInt();
			
			allNum.add(number);
			
			System.out.println("Vuoi fare un'altra scommessa? Y/N");
			risposta=s.nextLine().toUpperCase();
		
		}
		
		if (quote==0) {play=play-1;
						if (play==0) {/*TODO SISTEMARE IL METODO OUT DA ROULETTE */}}
		
		
		
		
		
	}
	
}
