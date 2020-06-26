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
    
        int id;
        ArrayList<Double> allQuote;
        ArrayList<Integer> allNum;
        int play;
        String showmustgoon;
        
        public void game( int idimp, ArrayList<Double> allQuoteimp, ArrayList<Integer> allNumimp,int playimp,String showmustgoonimp)
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

        public void removePlayer(Player g) throws RemoteException
        {
            mPlayers.remove(g);
        }

        public Roulette() throws RemoteException
        {
            mPlayers = new ArrayList<Player>();      
        }   
        
       
        @Override
		public boolean busy(ArrayList<Player> players) throws RemoteException {

            if(players.size()<=10)
            mIsGameOpen=true;//setto la variabile per indicare se c'è ancora posto 
            else
            mIsGameOpen=false;
            return mIsGameOpen;
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
 
            for (Player g:mPlayers)
            {
                if(play==0)//controlla il numero di giocate
                kickOut(g);
            }

        }

        public void kickOut(Player g)//rimuove il giocatore dalla lista dei partecipanti
        {
            mPlayers.remove(g);
        }

        public void closeBets()
        {
            //---------------------metto in sleep il thread delle iscrizioni per 7000 o interrupt sulle iscrizioni
        }
        
        private int lanciaPallina()
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

        private void giveAway(int number) throws RemoteException
        {
            for (Player g : mPlayers)
            {
                verifyBet(number, allNum, allQuote);//-----------controllare il get number che ritorni allNum e non solo number
            }
        }

        private int verifyBet(int extNumber, ArrayList<Integer> number, ArrayList<Double> quote)
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

		

		@Override
		public int winner() throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}
    
}
	
