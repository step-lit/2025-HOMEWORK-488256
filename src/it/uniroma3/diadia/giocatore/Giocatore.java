package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private Borsa borsa;
	private int cfu;
	
	public Giocatore(){
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
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
