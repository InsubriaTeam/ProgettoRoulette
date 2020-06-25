import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RouletteInterface extends Remote {
	public void game(int id, double[] allQuote,int [] allNum, int play) throws RemoteException; 
	public int/*o boolean a seconda del codice*/ busy() throws RemoteException; //TODO controller max persone
	public boolean ext() throws RemoteException; //TODO controller estrazione
	public int winner() throws RemoteException;
}
