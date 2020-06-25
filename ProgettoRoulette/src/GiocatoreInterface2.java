import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GiocatoreInterface2 extends Remote {
	public void notifica(String vincita) throws RemoteException;
}

