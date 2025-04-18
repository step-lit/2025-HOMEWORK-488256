package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private Borsa borsaTest;
	private Attrezzo foglio;
	private Attrezzo torcia;
	private Attrezzo martello;
	
	@BeforeEach
	public void setUp() {
		this.borsaTest = new Borsa();
		this.foglio = new Attrezzo("Foglio", 0);
		this.torcia = new Attrezzo("Torcia", 1);
		this.martello = new Attrezzo("Martello", 2);
	}
	
	/*********************************************************************
	 *    metodi di test per il metodo AddAttrezzo(Attrezzo attrezzo)    *
	 *********************************************************************/
	
	@Test
	public void testAddAttrezzo() {
		//la borsa inizialmente non ha attrezzi
		assertTrue(this.borsaTest.addAttrezzo(this.martello));
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		//la borsa è piena (10 attrezzi)
		for(int i=0; i<10; i++) {
			this.borsaTest.addAttrezzo(this.foglio); //aggiunge 10 fogli di peso 0
		}
		assertFalse(this.borsaTest.addAttrezzo(this.foglio)); //da false perché piena
	}
	
	@Test
	public void testAddAttrezzoMaxPeso() {
		//la borsa ha raggiunto il peso max
		for(int i=0; i<5; i++) {
			this.borsaTest.addAttrezzo(this.martello); //aggiunge 5 martelli di peso 2
		}
		assertFalse(this.borsaTest.addAttrezzo(this.martello)); //da false perché supera il peso max
	}
	
	
	
	/********************************************************************
	 * metodi di test per il metodo removeAttrezzo(String nomeAttrezzo) *
	 ********************************************************************/
	
	@Test
	public void testRemoveAttrezzoNoAttrezzi() {
		//la borsa inizialmente non ha attrezzi
		assertNull(this.borsaTest.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		//la borsa contiene l'attrezzo martello
		this.borsaTest.addAttrezzo(this.torcia);
		this.borsaTest.addAttrezzo(this.martello);
		this.borsaTest.addAttrezzo(this.foglio);
		assertEquals(this.martello, this.borsaTest.removeAttrezzo("Martello"));
	}

	@Test
	public void testRemoveAttrezzoNonPresente() {
		//la borsa prova a rimuovere un attrezzo non contenuto
		this.borsaTest.addAttrezzo(this.torcia);
		this.borsaTest.addAttrezzo(this.foglio);
		assertNull(borsaTest.removeAttrezzo("Martello"));
	}
	
	
	
	/*********************************************************************
	 *   metodi di test per il metodo getAttrezzo(String nomeAttrezzo)   *
	 *********************************************************************/
	
	@Test
	public void testGetAttrezzoNoAttrezzi() {
		//la borsa inizialmente non ha attrezzi
		assertNull(this.borsaTest.getAttrezzo("Foglio"));
	}
	
	@Test
	public void testGetAttrezzo() {
		//la borsa ha l'attrezzo cercato
		this.borsaTest.addAttrezzo(this.torcia);
		this.borsaTest.addAttrezzo(this.foglio);
		this.borsaTest.addAttrezzo(this.martello);
		assertEquals(this.foglio, this.borsaTest.getAttrezzo("Foglio"));
	}
	
	@Test
	public void testGetAttrezzoNonPresente() {
		//la borsa non ha l'attrezzo cercato
		this.borsaTest.addAttrezzo(this.torcia);
		this.borsaTest.addAttrezzo(this.foglio);
		assertNull(this.borsaTest.getAttrezzo("Martello"));
		System.out.println("è");
	}
	
}
