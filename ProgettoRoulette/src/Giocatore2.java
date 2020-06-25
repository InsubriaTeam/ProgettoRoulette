import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ThreadLocalRandom;

public class Giocatore2 extends UnicastRemoteObject implements GiocatoreInterface2{
	
	private static final long serialVersionUID = 1L;
	
	private String ID; 
	private float quote; 
	private int number; 
	private int play=5;
	private Roulette2 r;
	
	//-----------
	public Giocatore2(Roulette2 r) throws RemoteException{
		this.r=r;
	}
	//----------
	
	public String getID() throws RemoteException{
		return ID;
	}
	public void setID(String iD) throws RemoteException{
		ID = iD;
	}
	public float getQuote() throws RemoteException{
		return quote;
	}
	public void setQuote(float quote) throws RemoteException{
		this.quote = quote;
	}
	public int getNumber() throws RemoteException{
		return number;
	}
	public void setNumber(int number) throws RemoteException{
		this.number = number;
	}
	
	public int getPlay() throws RemoteException{
		return play;
	}
	public void setPlay(int play) throws RemoteException{
		this.play = play;
	}
	
	//--------------------
	
	public void notifica(String vincita) throws RemoteException{
		System.out.println(ID+": vincita pari a "+vincita);
	}
	
	public static void main(String[] args) throws RemoteException  {
		try {
			Registry registro = LocateRegistry.getRegistry(1099);
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
