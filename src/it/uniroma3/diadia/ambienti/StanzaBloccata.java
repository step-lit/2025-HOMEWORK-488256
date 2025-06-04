package it.uniroma3.diadia.ambienti;

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
	
	public String getDirBloccata() {
		return this.dirBloccata;
	}
	
	@Override
	public String getDescrizione() {
		return this.toString() + "\nDirezione " + dirBloccata + " bloccata, c'è bisogno dell'attrezzo " + nomeAttrezzoSbloccante;
	}
	
}
