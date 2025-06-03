package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.io.StringReader;


class CaricatoreLabirintoTest {

	private Labirinto caricaLabirinto(String stringa) throws FormatoFileNonValidoException {
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(stringa));
        caricatore.carica();
        return caricatore.getLabirintoCostruito();
    }

	@Test
    public void testMonolocale() throws FormatoFileNonValidoException {
        String prompt = 
            "Stanze:\n" +
            "Atrio\n" +
            "Estremi:\n" +
            "Atrio\n" +
            "Atrio\n" +
            "Attrezzi:\n" +
            "Uscite:\n";
        
        Labirinto l = caricaLabirinto(prompt);
        
        assertNotNull(l);
        assertNotNull(l.getStanzaIniziale());
        assertEquals("Atrio", l.getStanzaIniziale().getNome());
        assertNotNull(l.getStanzaVincente());
        assertEquals("Atrio", l.getStanzaVincente().getNome());
        assertTrue(l.getStanzaIniziale().getAttrezzi().isEmpty());
        assertTrue(l.getStanzaIniziale().getMapStanzeAdiacenti().isEmpty());
    }
	
	@Test
    public void testBilocaleSemplice() throws FormatoFileNonValidoException {
        String prompt = 
            "Stanze:\n" +
            "N10\n" +
            "N11\n" +
            "Estremi:\n" +
            "N10\n" +
            "N11\n" +
            "Attrezzi:\n" + 
            "Uscite:\n"; 
        
        Labirinto l = caricaLabirinto(prompt);

        Stanza n10 = l.getStanzaLabirinto("N10");
        Stanza n11 = l.getStanzaLabirinto("N11");

        assertNotNull(n10);
        assertNotNull(n11);
        assertEquals(n10, l.getStanzaIniziale());
        assertEquals(n11, l.getStanzaVincente());
    }
	
	@Test
    public void testBilocaleConUscite() throws FormatoFileNonValidoException {
        String prompt = 
            "Stanze:\n" +
            "N10\n" +
            "N11\n" +
            "Estremi:\n" +
            "N10\n" +
            "N11\n" +
            "Attrezzi:\n" +
            "Uscite:\n" +
            "N10 nord N11\n" +
            "N11 sud N10\n";
        
        Labirinto l = caricaLabirinto(prompt);

        Stanza n10 = l.getStanzaLabirinto("N10");
        Stanza n11 = l.getStanzaLabirinto("N11");

        assertNotNull(n10);
        assertNotNull(n11);
        assertEquals(n10, l.getStanzaIniziale());
        assertEquals(n11, l.getStanzaVincente());
        
        assertNotNull(n10.getStanzaAdiacente("nord"));
        assertEquals(n11, n10.getStanzaAdiacente("nord"));
        assertNotNull(n11.getStanzaAdiacente("sud"));
        assertEquals(n10, n11.getStanzaAdiacente("sud"));
        
    }
	
	@Test
    public void testMonolocaleConAttrezzo() throws FormatoFileNonValidoException {
        String prompt =
            "Stanze:\n" +
            "Corridoio\n" +
            "Estremi:\n" +
            "Corridoio\n" +
            "Corridoio\n" +
            "Attrezzi:\n" +
            "Osso 1 Corridoio\n" +
            "Uscite:\n";
        
        Labirinto l = caricaLabirinto(prompt);

        Stanza corridoio = l.getStanzaLabirinto("Corridoio");
        assertNotNull(corridoio);
        assertEquals(corridoio, l.getStanzaIniziale());
        
        assertFalse(corridoio.getAttrezzi().isEmpty());
        Attrezzo osso = corridoio.getAttrezzo("Osso");
        assertNotNull(osso);
        assertEquals(1, osso.getPeso());
    }
	
	@Test
    public void testTrilocaleCompleto() throws FormatoFileNonValidoException {
        String trilocaleSpec =
            "Stanze:\n" +
            "Ingresso\n" +
            "Salone\n" +
            "Cucina\n" +
            "Estremi:\n" +
            "Ingresso\n" +
            "Cucina\n" +
            "Attrezzi:\n" +
            "Spada 10 Salone\n" +
            "Scudo 15 Salone\n" +
            "Chiave 1 Ingresso\n" +
            "Uscite:\n" +
            "Ingresso nord Salone\n" +
            "Salone sud Ingresso\n" +
            "Salone est Cucina\n" +
            "Cucina ovest Salone\n";
        
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(trilocaleSpec));
        caricatore.carica();
        Labirinto l = caricatore.getLabirintoCostruito();

        Stanza ingresso = l.getStanzaLabirinto("Ingresso");
        Stanza salone = l.getStanzaLabirinto("Salone");
        Stanza cucina = l.getStanzaLabirinto("Cucina");

        assertNotNull(ingresso);
        assertNotNull(salone);
        assertNotNull(cucina);

        assertEquals(ingresso, l.getStanzaIniziale());
        assertEquals(cucina, l.getStanzaVincente());

        assertNotNull(ingresso.getAttrezzo("Chiave"));
        assertEquals(1, ingresso.getAttrezzo("Chiave").getPeso());
        assertNotNull(salone.getAttrezzo("Spada"));
        assertEquals(10, salone.getAttrezzo("Spada").getPeso());
        assertNotNull(salone.getAttrezzo("Scudo"));
        assertEquals(15, salone.getAttrezzo("Scudo").getPeso());

        assertEquals(salone, ingresso.getStanzaAdiacente("nord"));
        assertEquals(ingresso, salone.getStanzaAdiacente("sud"));
        assertEquals(cucina, salone.getStanzaAdiacente("est"));
        assertEquals(salone, cucina.getStanzaAdiacente("ovest"));
    }
	
}