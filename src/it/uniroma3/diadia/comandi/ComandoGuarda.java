package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	private IO io;
	
	public ComandoGuarda(IO io) {
		this.io = io;
	}
	
	public ComandoGuarda() {}
	
	@Override
	public void addIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio("Cfu attuali: " + partita.getGiocatore().getCfu());
	}
	
	@Override
	public void setParametro(String parametro) {
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	
}
