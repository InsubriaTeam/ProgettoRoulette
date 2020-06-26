import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RouletteInterface extends Remote {
	
	public void game(int id, ArrayList<Double> allQuote, ArrayList<Integer> allNum, int play) throws RemoteException; 
	public boolean busy(ArrayList<Giocatore2> players) throws RemoteException; 
	public boolean ext() throws RemoteException; //controller estrazione
	public int winner() throws RemoteException;
	
}
