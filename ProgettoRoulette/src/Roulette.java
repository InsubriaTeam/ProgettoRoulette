
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Roulette extends UnicastRemoteObject implements RouletteInterface{
	private static final long serialVersionUID = 1L;

	private ArrayList<Giocatore2> mPlayers;
    private ArrayList<Giocatore2> mWaitingPlayers;
    
    private Boolean mIsGameOpen;
    private Boolean mIsGameRunning;
    
    public Roulette() throws RemoteException
    {
        mPlayers = new ArrayList<Giocatore2>();
        mWaitingPlayers = new ArrayList<Giocatore2>();        
    }   
    
    public boolean busy(ArrayList<Giocatore2> players)//controllo il numero dei giocatori nella partita
    {
        if(players.size()<=10)
        mIsGameOpen=true;//setto la variabile per indicare se c'è ancora posto 
        else
        mIsGameOpen=false;
        return mIsGameOpen;
    }
    
    public void addPlayer(Giocatore2 g)//Aggiunta dei giocatori alla partita
    {
        if (mIsGameOpen)
        {
            mPlayers.add(g);
        }
        else
        {
            mWaitingPlayers.add(g);//lista dei giocatori in attesa
        }
    }
    
    // svolgimento partita
    public void startGame() throws RemoteException 
    {
        while (mIsGameRunning)        
        {
            prepareGame();

            closeBets();
            int extNumber = lanciaPallina();

            giveAway(extNumber);
        }
    }

    private void prepareGame() throws RemoteException 
    {
 
        for (Giocatore2 g:mPlayers)
        {
            if(g.getPlay()==0)//controlla il numero di giocate
            kickOut(g);
        }

        if (mWaitingPlayers.isEmpty()) //fase di preparazione
        return;

        else if(mIsGameOpen)
        {       
             for (Giocatore2 waiting : mWaitingPlayers)//controllo la lista dei giocatori in attesa
            {
                //se la partita è ancora aperto li inserisco
                mPlayers.add(waiting);
            }
        }

        mWaitingPlayers.clear();

    }

    public void kickOut(Giocatore2 g)//rimuove il giocatore dalla lista dei partecipanti
    {
        mPlayers.remove(g);
    }

    public void closeBets()
    {
        //---------------------metto in sleep il thread delle iscrizioni per 7000 o interrupt sulle iscrizioni
    }
    
    private int lanciaPallina()
    {
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        int extractedNumber = generateRandomNumber();
        return extractedNumber;
    }

    private int generateRandomNumber()
    {
        return (int) (Math.random() * 37);//randomico
    }

    private void giveAway(int number) throws RemoteException
    {
        for (Giocatore2 g : mPlayers)
        {
            
            System.out.println(verifyBet(number, g.getNumber(), g.getQuote()));//-----------controllare il get number che ritorni allNum e non solo number
        }
    }

    private int verifyBet(int extNumber, ArrayList<Integer> number, ArrayList<Float> quote)
    {
        int sum=0;
        for(int n : number)
        {
            if (extNumber == n)
            {
                int index=number.indexOf(n);
                sum+=(quote.get(index)*2);//somma la quota corrispondente all indice del numero puntato moltiplicata per due
                
            }
            else
            {
                int index=number.indexOf(n);
                sum-=(quote.get(index));
            };

        }
        return sum;
        

    }

    public void stop()
    {
        mIsGameRunning = false;
    }
    

	public static void main(String[] args) throws RemoteException, NotBoundException {
		try {
			Roulette r = new Roulette();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("Roulette", r);
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}

	
