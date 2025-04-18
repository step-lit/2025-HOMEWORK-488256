package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private StanzaBuia stanzaTest;
	private Attrezzo oggettoRivelatore;
	private Attrezzo martello;
	
	@BeforeEach
	void setUp() {
		this.stanzaTest = new StanzaBuia("Corridoio","Lanterna"); //non ci sono attrezzi;
		this.oggettoRivelatore = new Attrezzo("Lanterna",2);
		this.martello = new Attrezzo("Martello",2);
	}
	
	@Test
	void testGetDescrizioneNoAttrezzi() {
		//nella stanza non c'è l'oggetto sbloccante
		assertEquals("qui c'è buio pesto", this.stanzaTest.getDescrizione());
	}
	
	@Test
	void testGetDescrizioneNoRivelatore() {
		//nella stanza c'è solo il martello
		stanzaTest.addAttrezzo(this.martello);
		assertEquals("qui c'è buio pesto", this.stanzaTest.getDescrizione());
	}
		
	@Test
	void testGetDescrizioneRivelatore() {
		//nella stanza c'è il martello e anche il rivelatore
		stanzaTest.addAttrezzo(this.martello);
		stanzaTest.addAttrezzo(this.oggettoRivelatore);
		assertEquals(this.stanzaTest.toString(), this.stanzaTest.getDescrizione());
	}
	
}
