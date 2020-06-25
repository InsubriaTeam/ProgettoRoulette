
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class Roulette2 extends UnicastRemoteObject implements RouletteInterface2{
	private static final long serialVersionUID = 1L;

	private List<Giocatore2> giocatoriList;

	Roulette2() throws RemoteException {
		super();
		this.giocatoriList=new LinkedList<Giocatore2>();
	}
	
	public void addGiocatore(Giocatore2 g) throws RemoteException{
		giocatoriList.add(g);
		System.out.println("Roulette: aggiunto giocatore");
	}
	
	public void removeGiocatore(Giocatore2 g) throws RemoteException{
		giocatoriList.remove(g);
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		try {
			Roulette2 r = new Roulette2();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("Roulette", r);
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
}