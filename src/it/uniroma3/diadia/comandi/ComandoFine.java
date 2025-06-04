package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 * Il giocatore sceglie di
 * terminare il gioco.
 */
public class ComandoFine implements Comando {
	
	private IO io;
	
	public ComandoFine(IO io) {
		this.io = io;
	}
	public ComandoFine() {}
	
	@Override
	public void addIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita(); //si desidera smettere
		io.mostraMessaggio("Grazie di aver giocato!");
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
		return "fine";
	}
	
	
}
