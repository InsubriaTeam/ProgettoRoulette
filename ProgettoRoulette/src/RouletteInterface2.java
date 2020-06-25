import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RouletteInterface2 extends Remote{
	
	public void addGiocatore(Giocatore2 g) throws RemoteException;
	public void removeGiocatore(Giocatore2 g) throws RemoteException;
}