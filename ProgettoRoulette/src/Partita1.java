import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Partita1 {   

     void Luck(String localHost, ArrayList<Giocatore2> giocatoriList) throws NotBoundException, UnknownHostException, URISyntaxException, IOException
    {
        Registry registro = LocateRegistry.getRegistry(localHost,1099);
        Roulette server = (Roulette) registro.lookup("Roulette");
    }
//--------------------------------------------------------------- metodo Luck da eliminare nel caso si unisca partita con roulette
    private ArrayList<Giocatore2> mPlayers;
    private ArrayList<Giocatore2> mWaitingPlayers;

    private Boolean mIsGameOpen;
    private Boolean mIsGameRunning;

    public Partita1()
    {
        mPlayers = new ArrayList<Giocatore2>();
        mWaitingPlayers = new ArrayList<Giocatore2>();        
    }   

    public void checkNplayer(ArrayList<Giocatore2> players)//controllo il numero dei giocatori nella partita
    {
        if(players.size()<=10)
        mIsGameOpen=true;//setto la variabile per indicare se c'è ancora posto 
        else
        mIsGameOpen=false;
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
        Thread.sleep(5000);

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
}
