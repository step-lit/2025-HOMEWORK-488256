package it.uniroma3.diadia.comandi;
 
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
 
class ComandoVaiTest {
	
	private IO io;
	private Stanza iniziale;
	private ComandoVai comando;
	private Partita partita;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoVai(this.io);
		this.partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("Atrio")
														 .addStanzaVincente("Biblioteca")
														 .addStanza("N11")
														 .addAdiacenza("Atrio", "Biblioteca", "nord")
														 .addAdiacenza("Biblioteca", "Atrio", "sud")
														 .addAdiacenza("Atrio","N11","est")
														 .addAdiacenza("N11","Atrio","ovest")
														 .build());
		this.iniziale = this.partita.getLabirinto().getStanzaCorrente(); //all'inizio e' l'atrio
	}
	
	/**************************************************************************************
	 *    metodi di test per il metodo esegui(Partita partita) della classe ComandoVai    *
	 **************************************************************************************/
	
	@Test
	void testEseguiNoParametro() {
		//il parametro stringa e' ancora nullo
		this.comando.esegui(this.partita);
		assertEquals(this.iniziale, this.partita.getLabirinto().getStanzaCorrente());
		assertEquals(20, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testEseguiConParametro() {
		//spostiamo la stanza corrente a nord dell'atrio, ovvero in biblioteca
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita);
		assertEquals(this.iniziale.getStanzaAdiacente(Direzione.nord), this.partita.getLabirinto().getStanzaCorrente());
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
 
	@Test
	void testEseguiConParametroErrato() {
		//la stanza nord da N11 non esiste (N11 e' ad est dell'atrio)
		this.comando.setParametro("est"); //prima sposto ad est
		this.comando.esegui(this.partita);
		this.comando.setParametro("nord"); //qui non trova nulla
		this.comando.esegui(this.partita);
		assertEquals(this.iniziale.getStanzaAdiacente(Direzione.est), this.partita.getLabirinto().getStanzaCorrente());
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
	
}
