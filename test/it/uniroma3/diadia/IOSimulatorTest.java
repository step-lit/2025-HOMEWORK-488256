package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

import java.util.ArrayList;
import java.util.List;

class IOSimulatorTest {

	DiaDia gioco;
	List<String> comandi;
	IOSimulator simulator;
	LabirintoBuilder labirintoTest;
	String nomeStanzaIniziale;
	String nomeStanzaVincente;
	
	@BeforeEach
	void setUp() {
		comandi = new ArrayList<>();
		labirintoTest = new LabirintoBuilder();
		nomeStanzaIniziale = "Atrio";
		nomeStanzaVincente = "Uscita";
	}
	
	
	@Test
	void testSimulatorHaiVinto() throws Throwable {
		comandi.add("vai nord");
		
		simulator = new IOSimulator(comandi);
		Labirinto labirinto = labirintoTest
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		
		gioco = new DiaDia(labirinto, simulator);
		gioco.gioca();
		
		List<String> output = simulator.getOutput();
		assertTrue(output.contains("Hai vinto!"));
	}
	
	
	@Test
	void testSimulatorDirezioneInesistente() throws Throwable {
		comandi.add("vai est");
		comandi.add("vai nord");
		
		simulator = new IOSimulator(comandi);
		Labirinto labirinto = labirintoTest
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("Corridoio")
				.addAdiacenza(nomeStanzaIniziale, "Corridoio", "est")
				.addAdiacenza("Corridoio", nomeStanzaIniziale, "ovest")
				.addAdiacenza("Corridoio", nomeStanzaVincente, "sud")
				.addAdiacenza(nomeStanzaVincente, "Corridoio", "sud")
				.getLabirinto();
		
		gioco = new DiaDia(labirinto, simulator);
		gioco.gioca();
		
		List<String> output = simulator.getOutput();
		assertTrue(output.contains("Direzione Inesistente"));
	}
	
	@Test
	void testSimulatorPartita() throws Throwable {
		comandi.add("prendi osso");
		comandi.add("vai sud");
		comandi.add("prendi lanterna");
		comandi.add("vai est");
		comandi.add("posa osso");
		comandi.add("vai ovest");
		comandi.add("posa lanterna");
		comandi.add("vai nord");
		
		simulator = new IOSimulator(comandi);
		Labirinto labirinto = labirintoTest
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("osso", 1)
				.addStanza("Aula N10")
				.addAttrezzo("lanterna", 3)
				.addStanza("Aula N11")
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, "Aula N10", "sud")
				.addAdiacenza("Aula N10", "Aula N11", "est")
				.addAdiacenza("Aula N11", nomeStanzaIniziale, "ovest")
				.addAdiacenza("Atrio", nomeStanzaVincente, "nord")
				.getLabirinto();
		
		gioco = new DiaDia(labirinto, simulator);
		gioco.gioca();
		
		List<String> output = simulator.getOutput();
		assertTrue(output.contains("Attrezzo aggiunto nella borsa"));
		assertTrue(output.contains("Contenuto borsa (1kg/10kg): osso (1kg) "));
		assertTrue(output.contains("Aula N10"));
		assertTrue(output.contains("Contenuto borsa (4kg/10kg): osso (1kg) lanterna (3kg) "));
		assertTrue(output.contains("Aula N11"));
		assertTrue(output.contains("Attrezzo posato nella stanza"));
		assertTrue(output.contains("Contenuto borsa (3kg/10kg): lanterna (3kg) "));
		assertTrue(output.contains("Atrio"));
		assertTrue(output.contains("Borsa vuota"));
		assertTrue(output.contains("Uscita"));
		assertTrue(output.contains("Hai vinto!"));
	}
	
}
