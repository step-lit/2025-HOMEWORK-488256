package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

class FabbricaDiComandiFisarmonicaTest {
	
	private Comando comando;
	private FabbricaDiComandiRiflessiva fabbrica;
	
	@BeforeEach
	void setUp() {
		this.fabbrica = new FabbricaDiComandiRiflessiva(new IOConsole());
	}
	
	@Test
	void testComandoVai() throws NoSuchMethodException, SecurityException, 
								 IllegalArgumentException, InvocationTargetException, 
								 IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("vai nord");
		assertEquals("vai", this.comando.getNome());
		assertEquals("nord", this.comando.getParametro());
	}
	
	@Test
	void testComandoFine() throws NoSuchMethodException, SecurityException, 
								  IllegalArgumentException, InvocationTargetException,
								  IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("fine");
		assertEquals("fine", this.comando.getNome());
	}
	
	@Test
	void testComandoGuarda() throws NoSuchMethodException, SecurityException,
									IllegalArgumentException, InvocationTargetException, 
									IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("guarda");
		assertEquals("guarda", this.comando.getNome());
	}
	
	@Test
	void testComandoPosa() throws NoSuchMethodException, SecurityException, 
								  IllegalArgumentException, InvocationTargetException, 
								  IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("posa");
		assertEquals("posa", this.comando.getNome());
	}
	
	@Test
	void testComandoPrendi() throws NoSuchMethodException, SecurityException,
									IllegalArgumentException, InvocationTargetException, 
									IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("prendi");
		assertEquals("prendi", this.comando.getNome());
	}
	
	@Test
	void testComandoAiuto() throws NoSuchMethodException, SecurityException,
								   IllegalArgumentException, InvocationTargetException,
								   IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto", this.comando.getNome());
		assertEquals(null, this.comando.getParametro());
	}

	@Test
	void testComandoNonValido() throws NoSuchMethodException, SecurityException,
									   IllegalArgumentException, InvocationTargetException,
									   IllegalAccessException {
		this.comando = this.fabbrica.costruisciComando("");
		assertEquals(null, this.comando.getNome());
		assertEquals(null, this.comando.getParametro());
	}
	
}
