package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Direzione;

public class StanzaBloccata extends Stanza {
	
	private String nomeAttrezzoSbloccante;
	private Direzione dirBloccata;

	public StanzaBloccata(String nome, String nomeAttr, String dirBloccata) {
		super(nome);
		this.nomeAttrezzoSbloccante = nomeAttr;
		this.dirBloccata = Direzione.valueOf(dirBloccata);
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(direzione == dirBloccata) {
			if (this.getAttrezzi().containsKey(nomeAttrezzoSbloccante) ) {
					return super.getStanzaAdiacente(direzione);
			}
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
	
	public String getNomeAttrezzoSbloccante() {
		return this.nomeAttrezzoSbloccante;
	}
	
	public Direzione getDirBloccata() {
		return this.dirBloccata;
	}
	
	@Override
	public String getDescrizione() {
		return this.toString() + "\nDirezione " + dirBloccata + " bloccata, c'è bisogno dell'attrezzo " + nomeAttrezzoSbloccante;
	}
	
}
