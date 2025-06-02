package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {
	
	private IO io;
	private Stanza iniziale;
	private ComandoVai comando;
	private Partita partita;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoVai(this.io); //non viene inizialmente impostato il parametro
		this.partita = new Partita(null);
		this.iniziale = this.partita.getLabirinto().getStanzaCorrente(); //all'inizio e' l'atrio
	}
	
	
	
	/**************************************************************************************
	 *    metodi di test per il metodo esegui(Partita partita) della classe ComandoVai    *
	 **************************************************************************************/
	
	@Test
	void testEseguiNoParametro() {
		//il parametro stringa e' ancora nullo
		this.comando.esegui(this.partita);
		assertEquals(this.iniziale, this.partita.getLabirinto().getStanzaCorrente());
		assertEquals(20, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testEseguiConParametro() {
		//spostiamo la stanza corrente a nord dell'atrio, ovvero in biblioteca
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita);
		assertEquals(this.iniziale.getStanzaAdiacente("nord"), this.partita.getLabirinto().getStanzaCorrente());
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}

	@Test
	void testEseguiConParametroErrato() {
		//la stanza nord da N11 non esiste (N11 e' ad est dell'atrio)
		this.comando.setParametro("est"); //prima sposto ad est
		this.comando.esegui(this.partita);
		this.comando.setParametro("nord"); //qui non trova nulla
		this.comando.esegui(this.partita);
		assertEquals(this.iniziale.getStanzaAdiacente("est"), this.partita.getLabirinto().getStanzaCorrente());
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
	
}
