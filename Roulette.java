package Progetto.progetto;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;

public class Roulette extends UnicastRemoteObject implements RouletteInterface
{
    public static void main(String[] args) throws RemoteException, NotBoundException
    {
        try 
        {
            Roulette r = new Roulette();
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("Roulette", r);
            System.err.println("Server ready");
        } 
        catch (Exception e) 
        {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
        }

    }
        int id;
        ArrayList<Double> allQuote;
        ArrayList<Integer> allNum;
        int play;
        String showmustgoon;

        public void game(int idimp, ArrayList<Double> allQuoteimp, ArrayList<Integer> allNumimp, int playimp,
                String showmustgoonimp) //throws RemoteException
        {
            id=idimp;
            allQuote=allQuoteimp;
            allNum=allNumimp;
            play=playimp;
            showmustgoon=showmustgoonimp;
        }


        private static final long serialVersionUID = 1L;

	    private ArrayList<Player> mPlayers;
        private Boolean mIsGameOpen;
        private Boolean mIsGameRunning;


        //richiamo roulette,busy,addplayer, start game
        public Roulette() throws RemoteException
        {
            mPlayers = new ArrayList<Player>();  
            //----------------startGame();    
        }   

        public void addPlayer(Player g)//Aggiunta dei giocatori alla partita
        {
            if (mIsGameOpen)
            {
                mPlayers.add(g);
            }
            else
            {
                return;
            }
        }
        @Override
                public boolean busy()//controllo il numero dei giocatori nella partita
        {
            if(mPlayers.size()<=10)
            mIsGameOpen=true;//setto la variabile per indicare se c'è ancora posto 
            else
            mIsGameOpen=false;
            return mIsGameOpen;
        }

        // svolgimento partita

        public void startGame() throws RemoteException 
        {
            while (mIsGameRunning==true && mPlayers.size()>0 )       
            {
                prepareGame();

                closeBets();
                winner();
                //int extNumber = lanciaPallina();

                //giveAway(extNumber);
            }
        }

        private void prepareGame() throws RemoteException 
        {
 
            for (Player g:mPlayers)
            {
                if(play==0 || showmustgoon != "Y")//controlla il numero di giocate
                kickOut(g);
                try 
                {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void kickOut(Player g)//rimuove il giocatore dalla lista dei partecipanti
        {
            mPlayers.remove(g);
        }

        public void closeBets()
        {
            mIsGameOpen=false;
        }
        
        public int winner() throws RemoteException
        {
            try 
            {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int extractedNumber = generateRandomNumber();
            return extractedNumber;
        }

        private int generateRandomNumber()
        {
            return (int) (Math.random() * 35);//randomico
        }

       public void stop()
        {
            mIsGameRunning = false;
        }
    
}