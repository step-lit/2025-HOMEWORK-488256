package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {
		String istruzione; 
		
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}  


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome() != null) {
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else if (comandoDaEseguire.getNome().equals("prendi"))
				this.prendi(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("posa"))
				this.posa(comandoDaEseguire.getParametro());
			else
				console.mostraMessaggio("Comando sconosciuto");
			if (this.partita.vinta()) {
				console.mostraMessaggio("Hai vinto!");
				return true;
			} else
				return false;
		}
		console.mostraMessaggio("Inserisci un comando");
		return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
		console.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Comando "Prendi".
	 */
	private void prendi(String nomeAttrezzo) {
		//Il giocatore prende l'oggetto dalla stanza corrente con nomeAttrezzo e lo mette in borsa
		if(nomeAttrezzo == null)
			console.mostraMessaggio("Non hai specificato l'attrezzo da prendere!");
		
		else if( this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo) ) {
			Attrezzo attrezzoVoluto = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			
			if( this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoVoluto) ) {
				this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
				console.mostraMessaggio("Attrezzo aggiunto nella borsa");
			}
			else {
				console.mostraMessaggio("Attrezzo non preso: borsa piena o max peso raggiunto!");
			}
		}
		else {
			console.mostraMessaggio("Non ci sono attrezzi nella stanza con quel nome");
		}
		console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Comando "Posa".
	 */
	private void posa(String nomeAttrezzo) {
		//Il giocatore toglie l'oggetto dalla borsa con nomeAttrezzo e lo posa nella stanza corrente
		if(nomeAttrezzo == null)
			console.mostraMessaggio("Non hai specificato l'attrezzo da posare!");
		else if ( this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo) ) {
				Attrezzo attrezzoDaPosare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				
				if( this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaPosare) ) {
					this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					console.mostraMessaggio("Attrezzo posato nella stanza");
				}
				else
					console.mostraMessaggio("Attrezzo non posato: la stanza e' piena!");
		}
		else {
			console.mostraMessaggio("Non hai un oggetto in borsa con quel nome");
		}
		console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console); //creando DiaDia inizializza una nuova partita (costruttore)
		gioco.gioca();
	}
}