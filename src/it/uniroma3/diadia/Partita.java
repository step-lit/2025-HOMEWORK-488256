package it.uniroma3.diadia;

import java.io.*;
import java.util.Properties;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;
	
	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.giocatore = creaGiocatore();
		this.finita = false;
	}
	
	
	/**
	 * Metodo per la creazione di un giocatore con parametri
	 * presi da file properties;
	 * @return l'oggetto giocatore
	 */
	private Giocatore creaGiocatore() {
		int pesoBorsa;
		int cfu;
		Properties prop = new Properties();
		InputStream stream = Partita.class.getClassLoader().getResourceAsStream("diadia.properties");
		try {
			prop.load(stream);
			cfu = Integer.parseInt(prop.getProperty("cfu_iniziali"));
			pesoBorsa = Integer.parseInt(prop.getProperty("peso_max_borsa"));
		} catch (IOException e) {
			cfu = 20;
			pesoBorsa = 10;
		}
		return new Giocatore(pesoBorsa,cfu);
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}
	
	/**
	 * Restituisce vero se il giocatore non ha 
	 * esaurito i cfu
	 */
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() > 0;
	}
	
	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public Stanza getStanzaCorrente() {
		return labirinto.getStanzaCorrente();
	}
	
}
