package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {
	
	private Comando comando;
	private FabbricaDiComandiRiflessiva fabbrica;
	
	@BeforeEach
	void setUp() {
		this.fabbrica = new FabbricaDiComandiRiflessiva(new IOConsole(new Scanner(System.in)));
	}
	
	@Test
	void testComandoVai() throws IllegalAccessException  {
		this.comando = this.fabbrica.costruisciComando("vai nord");
		assertEquals("vai", this.comando.getNome());
		assertEquals("nord", this.comando.getParametro());
	}
	
	@Test
	void testComandoFine() throws IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("fine");
		assertEquals("fine", this.comando.getNome());
	}
	
	@Test
	void testComandoGuarda() throws IllegalAccessException  {
		this.comando = this.fabbrica.costruisciComando("guarda");
		assertEquals("guarda", this.comando.getNome());
	}
	
	@Test
	void testComandoPosa() throws IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("posa");
		assertEquals("posa", this.comando.getNome());
	}
	
	@Test
	void testComandoPrendi() throws IllegalAccessException  {
		this.comando = this.fabbrica.costruisciComando("prendi");
		assertEquals("prendi", this.comando.getNome());
	}
	
	@Test
	void testComandoAiuto() throws IllegalAccessException  {
		this.comando = this.fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto", this.comando.getNome());
		assertEquals(null, this.comando.getParametro());
	}

	@Test
	void testComandoNonValido() throws IllegalAccessException  {
		this.comando = this.fabbrica.costruisciComando("");
		assertEquals(null, this.comando.getNome());
		assertEquals(null, this.comando.getParametro());
	}
	
}
