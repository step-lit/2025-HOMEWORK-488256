package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoRivelatore;

	public StanzaBuia(String nome, String nomeAttrRivelatore) {
		super(nome);
		this.nomeAttrezzoRivelatore = nomeAttrRivelatore;
	}
	@Override
	public String getDescrizione() {
		Attrezzo[] attrezzi = this.getAttrezzi();
		for (int i = 0; i < attrezzi.length; i++) {
			if (attrezzi[i] != null && attrezzi[i].getNome().equals(nomeAttrezzoRivelatore)) {
				return this.toString();
			}
		}
        return "qui c'è buio pesto";
    }
}
