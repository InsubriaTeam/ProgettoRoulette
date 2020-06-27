
public class MultiGiocatore {
	

	private static final int Nthreads = 15;

	public static void main(String[] args) {
		for(int i=1; i<Nthreads+1; i++) {
			new Player(i).start();
		}
	}

}
