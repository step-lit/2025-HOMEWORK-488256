package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private IO io;
	private ComandoPosa comando;
	private Partita partita;
	private Attrezzo foglio;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole(new Scanner(System.in));
		this.comando = new ComandoPosa(this.io);
		this.partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("stanza1").build());
		this.foglio = new Attrezzo("Foglio", 0);
		
		/* aggiungiamo alla borsa tre attrezzi diversi */
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.foglio);
	}
	
	@Test
	void testEseguiNoParametro() {
		this.comando.esegui(this.partita);
		
		assertEquals(0, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi().size());
		assertEquals(1, this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}

	@Test
	void testEseguiConParametro() {
		this.comando.setParametro("Foglio");
		this.comando.esegui(this.partita); //la torcia è stata posato
		
		assertEquals(1, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi().size());
		assertEquals(0, this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}

}
