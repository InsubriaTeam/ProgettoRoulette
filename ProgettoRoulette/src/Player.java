import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Player extends Thread {

	int id;
	double quote = 0;
	int number;
	int play = 5;
	RouletteInterface ri;

	public Player(int id) {
		// creata sessione di gioco
		try {
			Registry reg = LocateRegistry.getRegistry();
			ri = (RouletteInterface) reg.lookup("Roulette");

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	// ---------------------

	public synchronized void bet() throws RemoteException {

		ArrayList<Double> allQuote = new ArrayList<Double>();
		ArrayList<Integer> allNum = new ArrayList<Integer>();

		Scanner s = new Scanner(System.in);
		System.out.print("Inserisci il tuo ID");
		int id = s.nextInt();

		String risposta = "Y";

		while ((risposta.toUpperCase()).equals("Y")) {

			risposta = "Z";

			System.out.println("Inserisci la quota da scommettere");
			double quote = s.nextFloat();

			allQuote.add(quote);

			int number = -1; // obbligo ad entrare nel while

			while ((number < 0) || (number > 37)) {
				System.out.println("Inserisci il numero da 0 a 37");
				number = s.nextInt();
				if ((number < 0) || (number > 37)) {
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
		
		ri.game(id, allQuote, allNum, play);

		
		s.close();

	}

}
