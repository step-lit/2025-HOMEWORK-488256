package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private Attrezzo martello;
	private Attrezzo foglio;
	private Attrezzo torcia;
	private StanzaMagica stanzaTest;
	
	
	@BeforeEach
	void setUp() {
		this.martello = new Attrezzo("Martello", 2);
		this.foglio = new Attrezzo("Foglio", 0);
		this.torcia = new Attrezzo("Torcia", 1);
		
		this.stanzaTest = new StanzaMagica("N11"); //soglia di default, 3 attrezzi
		this.stanzaTest.addAttrezzo(martello);
		this.stanzaTest.addAttrezzo(foglio);
		this.stanzaTest.addAttrezzo(torcia);
		
	}
	
	
	
	/*****************************************************************************
	 *   metodi di test per il metodo addAttrezzo() della classe StanzaMagica.   *
	 ****************************************************************************/
	
	@Test
	void testaddAttrezzoPreSoglia() {
		
		Attrezzo[] attrezzi = new Attrezzo[10];
		attrezzi[0] = this.martello;
		attrezzi[1] = this.foglio;
		attrezzi[2] = this.torcia;
		
		assertArrayEquals(attrezzi, this.stanzaTest.getAttrezzi());
	}

	@Test
	void testaddAttrezzoSogliaSuperata() {
		
		this.stanzaTest.addAttrezzo(torcia); //questo attrezzo viene modificato magicamente
		assertEquals("aicroT", this.stanzaTest.getAttrezzi()[3].getNome());
		assertEquals(2, this.stanzaTest.getAttrezzi()[3].getPeso());
		
	}
	
	@Test
	void testaddAttrezzoSogliaCustomSuperata() {
		
		this.stanzaTest = new StanzaMagica("N11",  6); //soglia pari a 6
		for(int i = 0; i < 6; i++)
			this.stanzaTest.addAttrezzo(torcia); //torcia viene aggiunto 6 volte
		
		this.stanzaTest.addAttrezzo(martello);
		assertEquals("olletraM", this.stanzaTest.getAttrezzi()[6].getNome());
		assertEquals(4, this.stanzaTest.getAttrezzi()[6].getPeso());
	}
	
}
