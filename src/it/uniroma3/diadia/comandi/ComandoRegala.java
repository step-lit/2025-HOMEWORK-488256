package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class ComandoRegala implements Comando {

	private static final String MESSAGGIO_CON_CHI =
			"Non c'è nessuno a cui puoi regalare l'attrezzo";
	private String messaggio;
	private IO io;
	private String nomeAttrezzo;

	public ComandoRegala(IO io) {
		this.io = io;
	}	
	@Override
	public void addIO(IO io) {
		this.io = io;
	}
	

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			if(nomeAttrezzo == null)
				io.mostraMessaggio("Non hai specificato l'attrezzo da regalare!");
			else if ( partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo) ) {
				Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				messaggio = personaggio.riceviRegalo(attrezzoDaPosare, partita);
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				io.mostraMessaggio(this.messaggio);
			}
			else {
				io.mostraMessaggio("Non hai un oggetto in borsa con quel nome");
			}
			io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		} else io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "interagisci";
	}
	
	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}
}
