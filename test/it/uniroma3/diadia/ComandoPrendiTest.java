package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {

	private IO io;
	private ComandoPrendi comando;
	private Partita partita;
//	private Stanza stanzaTest;
	private Attrezzo foglio;
	private Labirinto.LabirintoBuilder labirintoTest;
//	private Attrezzo torcia;
//	private Attrezzo martello;
//	private Attrezzo[] attrezziStanza;
//	private Attrezzo[] attrezziBorsa;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoPrendi(this.io); //all'inizio non ha un parametro
		labirintoTest = new Labirinto.LabirintoBuilder();
		labirintoTest.addStanzaIniziale("stanza1");
		this.partita = new Partita(labirintoTest.build());
//		this.stanzaTest = new Stanza("test");
		this.foglio = new Attrezzo("Foglio", 0);
//		this.torcia = new Attrezzo("Torcia", 1);
//		this.martello = new Attrezzo("Martello", 2);
//		
//		this.attrezziStanza = new Attrezzo[10]; //array di test per stanza
//		this.attrezziBorsa = new Attrezzo[10]; //array di test per borsa
		
		/* impostiamo la stanza corrente alla stanzaTest ed aggiungo un foglio */
//		this.partita.getLabirinto().setStanzaCorrente(this.stanzaTest);
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.foglio);
	}
	
	@Test
	void testEseguiNoParametro() {
		/* all'inizio la stanza corrente è atrio con attrezzo osso */
		comando.esegui(this.partita); 
		
//		attrezziStanza[0] = this.foglio;
		
		assertEquals(1, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi().size());
		assertEquals(0, this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}
	
	@Test
	void testEseguiConParametro() {
		comando.setParametro("Foglio");
		comando.esegui(this.partita); 
		
//		attrezziBorsa[0] = this.foglio;
		
		assertEquals(0, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi().size());
		assertEquals(1, this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}
	
//	@Test
//	void testEseguiConParametroErrato() {
//		comando.setParametro("Torcia");
//		comando.esegui(this.partita); 
//		
//		attrezziStanza[0] = this.foglio;
//		
//		assertArrayEquals(this.attrezziStanza, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi());
//		assertArrayEquals(this.attrezziBorsa, this.partita.getGiocatore().getBorsa().getAttrezzi());
//		
//	}

}
