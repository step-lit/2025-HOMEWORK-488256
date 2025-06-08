package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {

	private IO io;
	private ComandoPrendi comando;
	private Partita partita;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoPrendi(this.io);
		this.partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("stanza1")
														 .addAttrezzo("Foglio", 0)
														 .build());
	}
	
	@Test
	void testEseguiNoParametro() {
		comando.esegui(this.partita); 	
		assertEquals(1, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi().size());
		assertEquals(0, this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}
	
	@Test
	void testEseguiConParametro() {
		comando.setParametro("Foglio");
		comando.esegui(this.partita); 

		assertEquals(0, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi().size());
		assertEquals(1, this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}
	
}
