import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Player extends Thread {

	int myID;
	double quote = 0;
	int number;
	int play = 5;
	String showmustgoon;
	RouletteInterface ri;

	public Player(int id) {
		myID=id; //assegna l'id della sessione creato da MultiClient
		// creata sessione di gioco
		try {
			Registry reg = LocateRegistry.getRegistry();
			ri = (RouletteInterface) reg.lookup("Roulette");

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	
	
	// ---------------------
	
	public void run() {
		try {
			bet();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public synchronized void bet() throws RemoteException {

		ArrayList<Double> allQuote = new ArrayList<Double>();
		ArrayList<Integer> allNum = new ArrayList<Integer>();

		if (ri.busy() == true) {
			System.out.println("Mi dispiace, siamo pieni. Si prega di riprovare");
			System.exit(0);
		}

		Scanner s = new Scanner(System.in);

		String risposta = "Y";

		while ((risposta.toUpperCase()).equals("Y")) {

			risposta = "Z";

			System.out.println("Inserisci la quota da scommettere");
			double quote = s.nextFloat();

			allQuote.add(quote);

			int number = -1; // obbligo ad entrare nel while

			while ((number < 0) || (number > 36)) {
				System.out.println("Inserisci il numero da 0 a 36");
				number = s.nextInt();
				if ((number < 0) || (number > 36)) {
					System.out.println("Mi dispiace, il numero non è valido");
				} else {
					allNum.add(number);
				}
			}

			do {
				System.out.println("Vuoi fare un'altra scommessa? Y/N");
				risposta = s.nextLine().toUpperCase();

			} while (!risposta.toUpperCase().equals("Y") && !risposta.toUpperCase().equals("N"));

		}

		showmustgoon = "T";
		do {
			System.out.println("Vuoi continuare a giocare? Y/N");
			showmustgoon = s.nextLine().toUpperCase();
		} while (!showmustgoon.toUpperCase().equals("Y") && !showmustgoon.toUpperCase().equals("N"));

		System.out.println(
				"Si vuole ricordare che se si volesse smettere di giocare non serve inserire la quota da scommettere");

		ri.game(myID, allQuote, allNum, play, showmustgoon);

		s.close();

	}

}
