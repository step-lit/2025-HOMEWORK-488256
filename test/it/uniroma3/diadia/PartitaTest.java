package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita nuovaPartita;

	@BeforeEach
	public void setUp() {
		this.nuovaPartita = new Partita();
	}
	
	
	/********************************************************************
	 *    metodi di test per il metodo vinta() della classe Partita     *
	 ********************************************************************/
	
	@Test
	public void testVintaAtrio() {
		//inizialmente stanzaCorrente != stanzaVincente (e' nell'atrio)
		assertFalse(this.nuovaPartita.vinta());
	}
	
	@Test
	public void testVintaBiblioteca() {
		//imposta la biblioteca (goal) come stanza corrente della partita
		nuovaPartita.getLabirinto().setStanzaCorrente(this.nuovaPartita.getLabirinto().getStanzaVincente());
		assertTrue(this.nuovaPartita.vinta());
		assertTrue(this.nuovaPartita.isFinita()); //il test di isFinita era lo stesso di testVintaBiblioteca
	}
	
	@Test
	public void testVintaAltra() {
		//imposta un'altra stanza come stanza corrente della partita (che non sia né la stanza iniziale né il goal)
		Stanza aulaN10 = this.nuovaPartita.getLabirinto().getStanzaCorrente().getStanzaAdiacente("sud"); //estraggo l'aula N10 dalla partita
		this.nuovaPartita.getLabirinto().setStanzaCorrente(aulaN10);
		assertFalse(this.nuovaPartita.vinta());
	}
	
	
	
	/********************************************************************
	 *   metodi di test per il metodo isFinita() della classe Partita   *
	 ********************************************************************/
	
	@Test
	public void testIsFinitaAlloStart() {
		//allo start nessun operando dell'operazione OR nel metodo da true
		//finita || vinta() || cfu==0
		assertFalse(this.nuovaPartita.isFinita());
	}
	
	@Test
	public void testIsFinitaFinita() {
		//impostiamo la variabile finita a true
		//finita || vinta() || cfu==0
		this.nuovaPartita.setFinita();
		assertTrue(this.nuovaPartita.isFinita());
	}
	
	@Test
	public void testIsFinitaCfu() {
		//impostiamo il valore di cfu a zero
		//finita || vinta() || cfu==0
		this.nuovaPartita.getGiocatore().setCfu(0);
		assertTrue(this.nuovaPartita.isFinita());
	}
	
}
