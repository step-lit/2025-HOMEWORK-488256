package it.uniroma3.diadia.giocatore;

public class Giocatore {

	private Borsa borsa;
	private int cfu;
	
	public Giocatore(int pesoBorsa, int cfu){
		this.cfu = cfu;
		this.borsa = new Borsa(pesoBorsa);
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public Borsa getBorsa() {
		return this.borsa;
	}
}
