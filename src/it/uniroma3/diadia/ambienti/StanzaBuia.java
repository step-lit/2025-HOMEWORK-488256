package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoRivelatore;

	public StanzaBuia(String nome, String nomeAttrRivelatore) {
		super(nome);
		this.nomeAttrezzoRivelatore = nomeAttrRivelatore;
	}
	@Override
	public String getDescrizione() {
		if( this.getAttrezzi().containsKey(nomeAttrezzoRivelatore) )
			return this.toString();
        return "qui c'è buio pesto";
    }
	
	public String getAttrezzoRivelatore() {
		return this.nomeAttrezzoRivelatore;
	}
	
}
