package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.Strega;

class StregaTest {
	Labirinto labirintoTest;
	Partita partita;
	Strega strega1;
	@BeforeEach
	void setUp() throws Exception {
		labirintoTest = new LabirintoBuilder()
				.addStanzaIniziale("corrente")
				.addAttrezzo("0", 0)
				.addStrega("s", "", "corrente")
				.addStanza("più attrezzi")
				.addAttrezzo("1", 0)
				.addAttrezzo("2", 0)
				.addStanza("meno attrezzi")
				.build();
		partita = new Partita(labirintoTest);
	}

	@Test
	void testInteragisciSenzaAttrezzo() {
		labirintoTest.getStanzaCorrente().getPersonaggio().agisci(partita);
		assertEquals(labirintoTest.getStanzaLabirinto("meno attrezzi"), labirintoTest.getStanzaCorrente());
	}
	@Test
	void testInteragisciConAttrezzo() {
		labirintoTest.getStanzaCorrente().getPersonaggio().riceviRegalo(new Attrezzo("", 0), partita);
		labirintoTest.getStanzaCorrente().getPersonaggio().agisci(partita);
		assertEquals(labirintoTest.getStanzaLabirinto("più attrezzi"), labirintoTest.getStanzaCorrente());
	}


}