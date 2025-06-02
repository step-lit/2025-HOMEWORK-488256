package it.uniroma3.diadia.ambienti;

import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {

	Stanza ultimaStanzaAggiunta;
	
	public LabirintoBuilder() {
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza stanzaIniziale = new Stanza(nome);
		this.setStanzaCorrente(stanzaIniziale);
		this.setStanzaIniziale(stanzaIniziale);
		this.addStanzaLabirinto(stanzaIniziale); //aggiunge la stanza alla mappa delle stanze
		this.ultimaStanzaAggiunta = stanzaIniziale;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza stanzaVincente = new Stanza(nome);
		this.setStanzaVincente(stanzaVincente);
		this.addStanzaLabirinto(stanzaVincente);
		this.ultimaStanzaAggiunta = stanzaVincente;
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanza, String stanzaAdiacente, String direzione) {
		Stanza adiacente = this.getStanzaLabirinto(stanzaAdiacente);
		Stanza cercata = this.getStanzaLabirinto(stanza);
		cercata.impostaStanzaAdiacente(direzione, adiacente);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza = new Stanza(nome);
		this.addStanzaLabirinto(stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		StanzaMagica stanza = new StanzaMagica(nome,soglia);
		this.addStanzaLabirinto(stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String nomeAttr, String direzione) {
		StanzaBloccata stanza = new StanzaBloccata(nome,nomeAttr,direzione);
		this.addStanzaLabirinto(stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String nomeAttr) {
		StanzaBuia stanza = new StanzaBuia(nome,nomeAttr);
		this.addStanzaLabirinto(stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo = new Attrezzo(nome,peso);
		this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		return this;
	}
	
	public Map<String, Stanza> getListaStanze() {
		Map<String, Stanza> stanzeLabirinto = this.getMapStanzeLabirinto();
		return stanzeLabirinto;
	}

}
