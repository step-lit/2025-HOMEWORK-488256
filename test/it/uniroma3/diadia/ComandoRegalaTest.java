package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.personaggi.AbstractPersonaggio;

class ComandoRegalaTest {

	private IO io;
	private Stanza iniziale;
	private ComandoRegala comando;
	private Partita partita;
	private AbstractPersonaggio Fake;
	private Giocatore giocatore;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoRegala(this.io); //non viene inizialmente impostato il parametro
		this.partita = new Partita(null);
		this.iniziale = this.partita.getLabirinto().getStanzaCorrente(); //all'inizio e' l'atrio
		Fake = new FakePersonaggio("fake", null);
		this.partita.getLabirinto().getStanzaCorrente().aggiungiPersonaggio(Fake);
		this.giocatore.getBorsa().addAttrezzo(new Attrezzo("dono", 0));
	}

	@Test
	void testRegala() {
		assertTrue(this.giocatore.getBorsa().hasAttrezzo("dono"));
		comando.esegui(partita);
		assertTrue(!this.giocatore.getBorsa().hasAttrezzo("dono"));
	}

}
