package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.personaggi.AbstractPersonaggio;

class ComandoRegalaTest {

	private static final String DONO = "dono";
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
		this.partita = new Partita(new Labirinto());
		this.iniziale = new Stanza("test"); //all'inizio e' l'atrio
		Fake = new FakePersonaggio("fake", null);
		this.partita.getLabirinto().addStanzaLabirinto(iniziale);
		this.partita.getLabirinto().setStanzaCorrente(iniziale);
		this.partita.getStanzaCorrente().aggiungiPersonaggio(Fake);
		this.giocatore = partita.getGiocatore();
		this.giocatore.getBorsa().addAttrezzo(new Attrezzo(DONO, 2));
		this.comando.setParametro(DONO);
		
	}

	@Test
	void testRegala() {
		assertTrue(this.giocatore.getBorsa().hasAttrezzo(DONO));
		comando.esegui(partita);
		assertFalse(this.giocatore.getBorsa().hasAttrezzo(DONO));
	}

}
