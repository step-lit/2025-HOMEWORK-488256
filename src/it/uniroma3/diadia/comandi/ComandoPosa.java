package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Comando "posa".
 * Il giocatore prende l'attrezzo dalla borsa 
 * chiamato nomeAttrezzo e lo posa nella stanza corrente.
 */
public class ComandoPosa implements Comando {
	
	private String nomeAttrezzo;
	private IO io;
	
	
	@Override
	public void addIO(IO io) {
		this.io = io;
	}
	
	public ComandoPosa(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null)
			io.mostraMessaggio("Non hai specificato l'attrezzo da posare!");
		else if ( partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo) ) {
				Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				
				if( partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaPosare) ) {
					partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					io.mostraMessaggio("Attrezzo posato nella stanza");
				}
				else
					io.mostraMessaggio("Attrezzo non posato: la stanza e' piena!");
		}
		else {
			io.mostraMessaggio("Non hai un oggetto in borsa con quel nome");
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
		return "posa";
	}
	
}
