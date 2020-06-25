import java.util.ArrayList;
import java.util.Scanner;

public class Giocatore extends Thread{
	
	private String ID; 
	private float quote; 
	private int number; 
	private int play=5;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public float getQuote() {
		return quote;
	}
	public void setQuote(float quote) {
		this.quote = quote;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getPlay() {
		return play;
	}
	public void setPlay(int play) {
		this.play = play;
	}

	public synchronized void Bet() {
		
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
