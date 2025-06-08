package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FakePersonaggio;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.personaggi.AbstractPersonaggio;

class ComandoRegalaTest {

	private static final String DONO = "dono";
	private IO io;
	private ComandoRegala comando;
	private Partita partita;
	private AbstractPersonaggio Fake;
	private Giocatore giocatore;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole(new Scanner(System.in));
		this.comando = new ComandoRegala(this.io);
		this.partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("test").build());
		
		Fake = new FakePersonaggio("fake", null);
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
