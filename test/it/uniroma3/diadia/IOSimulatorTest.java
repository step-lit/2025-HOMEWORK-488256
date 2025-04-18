package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class IOSimulatorTest {

	DiaDia gioco;
	ArrayList<String> comandi;
	IOSimulator simulator;
	
	@BeforeEach
	void setUp() {
		comandi = new ArrayList<>();
	}
	
	
	@Test
	void testSimulatorHaiVinto() {
		comandi.add("vai nord");
		
		simulator = new IOSimulator(comandi);
		gioco = new DiaDia(simulator);
		gioco.gioca();
		
		List<String> output = simulator.getOutput();
		assertTrue(output.contains("Hai vinto!"));
	}
	
	
	@Test
	void testSimulatorDirezioneInesistente() {
		comandi.add("vai est");
		comandi.add("vai nord");
		
		simulator = new IOSimulator(comandi);
		gioco = new DiaDia(simulator);
		gioco.gioca();
		
		List<String> output = simulator.getOutput();
		assertTrue(output.contains("Direzione Inesistente"));
	}
	
	@Test
	void testSimulatorPartita() {
		comandi.add("prendi osso");
		comandi.add("vai sud");
		comandi.add("prendi lanterna");
		comandi.add("vai est");
		comandi.add("posa osso");
		comandi.add("vai ovest");
		comandi.add("posa lanterna");
		comandi.add("vai nord");
		
		simulator = new IOSimulator(comandi);
		gioco = new DiaDia(simulator);
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
		assertTrue(output.contains("Biblioteca"));
		assertTrue(output.contains("Hai vinto!"));
	}
	
}
