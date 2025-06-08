package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaTest;
	private Stanza stanzaNord;
	private Stanza stanzaEst;
	private Attrezzo oggettoSbloccante;
	
	@BeforeEach
	void setUp() {
		this.stanzaTest = new StanzaBloccata("Corridoio","Chiave","nord"); //all'inizio non c'è la chiave
		this.stanzaTest.impostaStanzaAdiacente("nord", this.stanzaNord);
		this.stanzaTest.impostaStanzaAdiacente("est", this.stanzaEst);
		oggettoSbloccante = new Attrezzo("Chiave",2);
	}
	
	@Test
	void testGetStanzaAdiacenteNoAttrezzi() {
		//la stanza non ha attrezzi (e neanche quello sbloccante)
		assertSame(this.stanzaTest, this.stanzaTest.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacenteConChiave() {
		//nella stanza è presente solo l'attrezzo sbloccante per la direzione nord
		this.stanzaTest.addAttrezzo(this.oggettoSbloccante);
		assertSame(this.stanzaNord, this.stanzaTest.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacenteNoBlocco() {
		//la stanza ad est non è quella bloccata
		assertSame(this.stanzaEst, this.stanzaTest.getStanzaAdiacente("est"));
	}
}
