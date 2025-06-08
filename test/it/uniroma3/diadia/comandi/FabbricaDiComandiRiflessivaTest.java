package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiRiflessivaTest {

	private it.uniroma3.diadia.comandi.Comando comando;
	private FabbricaDiComandiRiflessiva fabbrica;

	@BeforeEach
	void setUp() throws Exception {
		fabbrica = new FabbricaDiComandiRiflessiva(null);
	}

	@Test
	void testComandoFine() throws Throwable {
		this.comando = this.fabbrica.costruisciComando("fine");
		assertEquals("fine", this.comando.getNome());
	}

}
