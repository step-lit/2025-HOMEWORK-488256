package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private Attrezzo martello;
	private Attrezzo foglio;
	private Attrezzo torcia;
	private StanzaMagica stanzaTest;
	private Map<String, Attrezzo> attrezzi;
	
	
	@BeforeEach
	void setUp() {
		
		this.martello = new Attrezzo("Martello", 2);
		this.foglio = new Attrezzo("Foglio", 0);
		this.torcia = new Attrezzo("Torcia", 1);
		
		this.attrezzi = new HashMap<>();
		
		this.stanzaTest = new StanzaMagica("N11"); //soglia di default, 3 attrezzi
		this.stanzaTest.addAttrezzo(this.martello);
		this.stanzaTest.addAttrezzo(this.foglio);
		this.stanzaTest.addAttrezzo(this.torcia);
		
	}
	
	
	
	/*****************************************************************************
	 *   metodi di test per il metodo addAttrezzo() della classe StanzaMagica.   *
	 ****************************************************************************/
	
	@Test
	void testaddAttrezzoPreSoglia() {
		this.attrezzi.put(this.martello.getNome(), this.martello);
		this.attrezzi.put(this.foglio.getNome(), this.foglio);
		this.attrezzi.put(this.torcia.getNome(), this.torcia);
		assertEquals(attrezzi, this.stanzaTest.getAttrezzi());
	}

	@Test
	void testaddAttrezzoSogliaSuperata() {
		
		this.stanzaTest.addAttrezzo(torcia); //questo attrezzo viene modificato magicamente
		assertTrue(this.stanzaTest.getAttrezzi().containsKey("aicroT"));
		assertEquals(2, this.stanzaTest.getAttrezzi().get("aicroT").getPeso());
		
	}
	
	@Test
	void testaddAttrezzoSogliaCustomSuperata() {
		
		this.stanzaTest = new StanzaMagica("N11",  6); //soglia pari a 6
		for(int i = 0; i < 6; i++) {
			Integer numero = i;
			Attrezzo attrezzo = new Attrezzo(numero.toString(),i);
			this.stanzaTest.addAttrezzo(attrezzo); //torcia viene aggiunto 6 volte
		}
		
		this.stanzaTest.addAttrezzo(martello);
		assertTrue(this.stanzaTest.getAttrezzi().containsKey("olletraM"));
		assertEquals(4, this.stanzaTest.getAttrezzi().get("olletraM").getPeso());
	}
	
}
