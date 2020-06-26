package Progetto.progetto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RouletteInterface extends Remote{
	
	//public void addPlayer(Giocatore g) throws RemoteException;
	//public void removePlayer(Giocatore g) throws RemoteException;
	public void game( int id, ArrayList<Double> allQuote, ArrayList<Integer> allNum, int play,String showmustgoon);
}
