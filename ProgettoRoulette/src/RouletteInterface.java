import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RouletteInterface extends Remote {
	public void game(int id, ArrayList<Double> allQuote, ArrayList<Integer> allNum, int play, String showmustgoon) throws RemoteException; 
	public int/*o boolean a seconda del codice*/ busy() throws RemoteException; //TODO controller max persone
	public int winner() throws RemoteException;//TODO 
}
