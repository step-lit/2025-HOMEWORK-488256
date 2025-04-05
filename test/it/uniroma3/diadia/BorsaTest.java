package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	Borsa borsaTest;
	Attrezzo foglio;
	Attrezzo torcia;
	Attrezzo martello;
	
	@BeforeEach
	public void setUp() {
		borsaTest = new Borsa();
		foglio = new Attrezzo("Foglio", 0);
		torcia = new Attrezzo("Torcia", 1);
		martello = new Attrezzo("Martello", 2);
	}
	
	/*********************************************************************
	 *    metodi di test per il metodo AddAttrezzo(Attrezzo attrezzo)    *
	 *********************************************************************/
	
	@Test
	public void testAddAttrezzo() {
		//la borsa inizialmente non ha attrezzi
		assertTrue(borsaTest.addAttrezzo(martello));
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		//la borsa è piena (10 attrezzi)
		for(int i=0; i<10; i++) {
			borsaTest.addAttrezzo(foglio); //aggiunge 10 fogli di peso 0
		}
		assertFalse(borsaTest.addAttrezzo(foglio)); //da false perché piena
	}
	
	@Test
	public void testAddAttrezzoMaxPeso() {
		//la borsa ha raggiunto il peso max
		for(int i=0; i<5; i++) {
			borsaTest.addAttrezzo(martello); //aggiunge 5 martelli di peso 2
		}
		assertFalse(borsaTest.addAttrezzo(martello)); //da false perché supera il peso max
	}
	
	
	
	/********************************************************************
	 * metodi di test per il metodo removeAttrezzo(String nomeAttrezzo) *
	 ********************************************************************/
	
	@Test
	public void testRemoveAttrezzoNoAttrezzi() {
		//la borsa inizialmente non ha attrezzi
		assertNull(borsaTest.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		//la borsa contiene l'attrezzo martello
		borsaTest.addAttrezzo(torcia);
		borsaTest.addAttrezzo(martello);
		borsaTest.addAttrezzo(foglio);
		assertEquals(martello, borsaTest.removeAttrezzo("Martello"));
	}

	@Test
	public void testRemoveAttrezzoNonPresente() {
		//la borsa prova a rimuovere un attrezzo non contenuto
		borsaTest.addAttrezzo(torcia);
		borsaTest.addAttrezzo(foglio);
		assertNull(borsaTest.removeAttrezzo("Martello"));
	}
	
	
	
	/*********************************************************************
	 *   metodi di test per il metodo getAttrezzo(String nomeAttrezzo)   *
	 *********************************************************************/
	
	@Test
	public void testGetAttrezzoNoAttrezzi() {
		//la borsa inizialmente non ha attrezzi
		assertNull(borsaTest.getAttrezzo("Foglio"));
	}
	
	@Test
	public void testGetAttrezzo() {
		//la borsa ha l'attrezzo cercato
		borsaTest.addAttrezzo(torcia);
		borsaTest.addAttrezzo(foglio);
		borsaTest.addAttrezzo(martello);
		assertEquals(foglio, borsaTest.getAttrezzo("Foglio"));
	}
	
	@Test
	public void testGetAttrezzoNonPresente() {
		//la borsa non ha l'attrezzo cercato
		borsaTest.addAttrezzo(torcia);
		borsaTest.addAttrezzo(foglio);
		assertNull(borsaTest.getAttrezzo("Martello"));
	}
	
}
