
public class MultiGiocatore {
	
	public MultiGiocatore(){
	}

	void myRun(){
		for (int i=0; i<15; i++) {
		Player p=new Player(i);
		p.start();}	
	}
	
	public static void main(String[] args) {
		new MultiGiocatore().myRun();
	}
}
