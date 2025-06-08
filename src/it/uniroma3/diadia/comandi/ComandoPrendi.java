package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Comando "prendi".
 * Il giocatore prende l'attrezzo dalla stanza corrente 
 * chiamato nomeAttrezzo e lo mette in borsa.
 */
public class ComandoPrendi implements Comando {
	
	private String nomeAttrezzo;
	private IO io;
	
	public ComandoPrendi(IO io) {
		this.io = io;
	}	
	
	public ComandoPrendi() {}	
	
	@Override
	public void addIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		if( partita.getStanzaCorrente().getAttrezzi().size() == 0 )
			io.mostraMessaggio("La stanza in questo momento non ha attrezzi.");
		if(nomeAttrezzo == null || !partita.getStanzaCorrente().getAttrezzi().containsKey(nomeAttrezzo))
			io.mostraMessaggio("Non hai specificato l'attrezzo da prendere o non c'è nessun attrezzo con quel nome!");
		else if( partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo) ) {
			Attrezzo attrezzoVoluto = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			
			/* se l'attrezzo viene aggiunto in borsa con successo addAttrezzo *
			 * ritorna true, altrimenti false                                 */
			if( partita.getGiocatore().getBorsa().addAttrezzo(attrezzoVoluto) ) {
				partita.getLabirinto().getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
				io.mostraMessaggio("Attrezzo aggiunto nella borsa");
			}
			/* altrimenti vuol dire che non ho raggiunto un limite per la borsa */
			else {
				io.mostraMessaggio("Attrezzo non preso: borsa piena o max peso raggiunto!");
			}
		}
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
}