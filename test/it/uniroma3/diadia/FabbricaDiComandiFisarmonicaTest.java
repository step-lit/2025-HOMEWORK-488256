package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {
	
	private Comando comando;
	private FabbricaDiComandiFisarmonica fabbrica;
	
	@BeforeEach
	void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica(new IOConsole());
	}
	
	@Test
	void testComandoVai() {
		this.comando = this.fabbrica.costruisciComando("vai nord");
		assertEquals("vai", this.comando.getNome());
		assertEquals("nord", this.comando.getParametro());
	}
	
	@Test
	void testComandoFine() {
		this.comando = this.fabbrica.costruisciComando("fine");
		assertEquals("fine", this.comando.getNome());
	}
	
	@Test
	void testComandoGuarda() {
		this.comando = this.fabbrica.costruisciComando("guarda");
		assertEquals("guarda", this.comando.getNome());
	}
	
	@Test
	void testComandoPosa() {
		this.comando = this.fabbrica.costruisciComando("posa");
		assertEquals("posa", this.comando.getNome());
	}
	
	@Test
	void testComandoPrendi() {
		this.comando = this.fabbrica.costruisciComando("prendi");
		assertEquals("prendi", this.comando.getNome());
	}
	
	@Test
	void testComandoAiuto() {
		this.comando = this.fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto", this.comando.getNome());
		assertEquals(null, this.comando.getParametro());
	}

	@Test
	void testComandoNonValido() {
		this.comando = this.fabbrica.costruisciComando("");
		assertEquals(null, this.comando.getNome());
		assertEquals(null, this.comando.getParametro());
	}
	
}
