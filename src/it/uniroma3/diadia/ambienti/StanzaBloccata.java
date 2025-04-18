package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	
	private String nomeAttrezzoSbloccante;
	private String dirBloccata;

	public StanzaBloccata(String nome, String nomeAttr, String dirBloccata) {
		super(nome);
		this.nomeAttrezzoSbloccante = nomeAttr;
		this.dirBloccata = dirBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione == dirBloccata) {
			Attrezzo[] attrezzi = this.getAttrezzi();
			for (int i = 0; i < attrezzi.length; i++) {
				if (attrezzi[i] != null && attrezzi[i].getNome().equals(nomeAttrezzoSbloccante)) {
					return super.getStanzaAdiacente(direzione);
				}
			}
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		return this.toString() + "\nDirezione " + dirBloccata + " bloccata, c'è bisogno dell'attrezzo " + nomeAttrezzoSbloccante;
	}
	
}
