package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirintoTest;
	
	@BeforeEach
	public void setUp() {
		this.labirintoTest = Labirinto.newBuilder()
					.addStanzaIniziale("Atrio")
					.addStanzaVincente("Biblioteca")
					.build();
	}
	
	@Test
	void testGetStanzaVincente() {
		//inizialmente la stanza vincente è la stanza "Biblioteca"
		assertEquals("Biblioteca", this.labirintoTest.getStanzaVincente().getNome());
	}
	
	@Test
	void testGetStanzaCorrente() {
		//inizialmente la stanza corrente è la stanza "Atrio"
		assertEquals("Atrio", this.labirintoTest.getStanzaCorrente().getNome());
	}
	
	@Test
	void testSetStanzaCorrente() {
		//cambiamo la stanza corrente con la stanza vincente
		labirintoTest.setStanzaCorrente(labirintoTest.getStanzaVincente());
		assertEquals("Biblioteca", this.labirintoTest.getStanzaCorrente().getNome());
	}
	
}
