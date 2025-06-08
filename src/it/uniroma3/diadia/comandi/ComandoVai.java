package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


/**
 * Comando "vai".
 * Cerca di andare in una direzione. Se c'è una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando {
	
	private Direzione direzione;
	private IO io;
	
	public ComandoVai(IO io) {
		this.io = io;
	}	
	
	public ComandoVai() {}	
	
	@Override
	public void addIO(IO io) {
		this.io = io;
	}
	
	
	/**
	  * esecuzione del comando
	  */
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if (this.direzione == null) {
			io.mostraMessaggio("Dove vuoi andare? "
							 + "Devi Specificare una direzione");
			return;
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione Inesistente");
			return;
		}
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);

	}
	
	
	/**
	* set parametro del comando
	*/
	@Override
	public void setParametro(String parametro) {
		try {
			this.direzione = Direzione.valueOf(parametro);
		}
		catch (IllegalArgumentException e) {
			io.mostraMessaggio("Errore: il parametro inserito è errato.");
		}
	}
	
	@Override
	public String getParametro() {
		return this.direzione.name();
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
}
