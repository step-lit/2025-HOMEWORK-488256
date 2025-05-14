package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.OrdineBorsaPesoCrescenteONomeCrescente;
import it.uniroma3.diadia.giocatore.OrdinePerNOme;

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

	@Test 
	public void testGetContenutoOrdinatoPerNome() {
		Attrezzo attrezzo2 = new Attrezzo("ps", 5);
		this.borsaTest.addAttrezzo(attrezzo2);
		Attrezzo attrezzo4 = new Attrezzo("libro", 5);
		this.borsaTest.addAttrezzo(attrezzo4);
		SortedSet<Attrezzo> a = new TreeSet<Attrezzo>(new OrdinePerNOme());
		a.addAll(Set.of(attrezzo4, attrezzo2));
		assertEquals(a, this.borsaTest.getContenutoOrdinatoPerNome());
	}
	@Test 
	public void getContenutoOrdinatoPerPeso() {
		Attrezzo attrezzo1 = new Attrezzo("ps", 4);
		this.borsaTest.addAttrezzo(attrezzo1);
		Attrezzo attrezzo2 = new Attrezzo("libro", 5);
		this.borsaTest.addAttrezzo(attrezzo2);
		List<Attrezzo> a = new ArrayList<Attrezzo>(List.of( attrezzo1, attrezzo2));
		assertEquals(a, this.borsaTest.getContenutoOrdinatoPerPeso());
	}
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Attrezzo piuma1 = new Attrezzo("piuma1", 1);
		this.borsaTest.addAttrezzo(piuma1);
		Attrezzo piuma2 = new Attrezzo("piuma2", 1);
		this.borsaTest.addAttrezzo(piuma2);
		//assertEquals(HashMap<Integer,Set<Attrezzo>>.ofEntries(entry(1, Set.of(new Attrezzo("piuma2", 1), (new Attrezzo("piuma2", 1))))), null);
		Map<Integer,Set<Attrezzo>> a = new TreeMap<Integer,Set<Attrezzo>>();
		a.put(Integer.valueOf(1),Set.of(piuma1, piuma2));
		assertEquals(a, this.borsaTest.getContenutoRaggruppatoPerPeso());
	}
	@Test 
	public void testGetSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> a = new TreeSet<Attrezzo>(new OrdineBorsaPesoCrescenteONomeCrescente());
		Attrezzo attrezzo1 = new Attrezzo("ps", 4);
		this.borsaTest.addAttrezzo(attrezzo1);
		a.add(attrezzo1);
		Attrezzo attrezzo2 = new Attrezzo("libro", 5);
		this.borsaTest.addAttrezzo(attrezzo2);
		a.add(attrezzo2);
		assertEquals(a, this.borsaTest.getSortedSetOrdinatoPerPeso());
	}
}
