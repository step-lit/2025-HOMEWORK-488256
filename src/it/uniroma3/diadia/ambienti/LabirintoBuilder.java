package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {

	Stanza ultimaStanzaAggiunta;
	
	public LabirintoBuilder() {
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza stanzaIniziale = new Stanza(nome);
		this.setStanzaCorrente(stanzaIniziale);
		this.addStanzaLabirinto(stanzaIniziale); //aggiunge la stanza alla mappa delle stanze
		this.ultimaStanzaAggiunta = stanzaIniziale;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza stanzaVincente = new Stanza(nome);
		this.setStanzaVincente(stanzaVincente);
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
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo = new Attrezzo(nome,peso);
		this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this;
	}
}
