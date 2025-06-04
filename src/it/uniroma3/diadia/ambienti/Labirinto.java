package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.io.FileReader;

public class Labirinto {
	
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Map<String, Stanza> stanzeLabirinto; //una mappa di tutte le stanze
	
	public Labirinto() {
		this.stanzeLabirinto = new HashMap<>();
	}
	
	public static Labirinto creaDaFile(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new FileReader(nomeFile));
        caricatore.carica();
        Labirinto lab = caricatore.getLabirintoCostruito();
        
        if (lab.stanzaIniziale == null) {
            throw new IllegalStateException("Labirinto costruito senza stanza iniziale.");
        }
        return lab;
    }
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void addStanzaLabirinto(Stanza stanza) {
		this.stanzeLabirinto.put(stanza.getNome(), stanza);
	}
	
	public Stanza getStanzaLabirinto(String nome) {
		Stanza a = this.stanzeLabirinto.get(nome);
		return a;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
	}
	
	public Map<String, Stanza> getMapStanzeLabirinto() {
		return this.stanzeLabirinto;
	}
	
	public Labirinto getLabirinto() {
		return this;
	}
	
	public static class LabirintoBuilder {
        private Labirinto labirinto;
        private Stanza ultimaStanzaAggiunta;
        private static final Set<String> DIREZIONI_VALIDE = new HashSet<>(Arrays.asList("nord", "sud", "est", "ovest"));

        public LabirintoBuilder() {
            this.labirinto = new Labirinto(); // Usa il costruttore privato di Labirinto
        }

        public LabirintoBuilder addStanzaIniziale(String nome) {
            Stanza stanza = new Stanza(nome);
            this.labirinto.stanzaIniziale = stanza;
            this.labirinto.stanzeLabirinto.put(nome, stanza);
            this.ultimaStanzaAggiunta = stanza;
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nome) {
            Stanza stanza = new Stanza(nome);
            this.labirinto.stanzaVincente = stanza;
            this.labirinto.stanzeLabirinto.put(nome, stanza);
            this.ultimaStanzaAggiunta = stanza;
            return this;
        }

        public LabirintoBuilder addStanza(String nome) {
            Stanza stanza = new Stanza(nome);
            this.labirinto.stanzeLabirinto.put(nome, stanza);
            this.ultimaStanzaAggiunta = stanza;
            return this;
        }
        
        public LabirintoBuilder addAdiacenza(String nomeStanzaPartenza, String nomeStanzaAdiacente, String direzione) {
        	if(!DIREZIONI_VALIDE.contains(direzione.toLowerCase())) {
        		System.err.println("Direzione specificata non valida. La stanza adiacente a " + nomeStanzaPartenza + "non è stata aggiunta.");
    			return this;
    		}
            Stanza stanzaPartenza = this.labirinto.stanzeLabirinto.get(nomeStanzaPartenza);
            Stanza stanzaAdiacente = this.labirinto.stanzeLabirinto.get(nomeStanzaAdiacente);
            stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
            return this;
        }

        public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
            if (this.ultimaStanzaAggiunta == null) {
                throw new IllegalStateException("Tentativo di aggiungere attrezzo senza aver prima aggiunto/selezionato una stanza.");
            }
            this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
            StanzaMagica stanza = new StanzaMagica(nome, soglia);
            this.labirinto.stanzeLabirinto.put(nome, stanza); 
            this.ultimaStanzaAggiunta = stanza;
            return this;
        }
        
        public LabirintoBuilder addStanzaBloccata(String nome, String nomeAttr, String direzione) {
    		StanzaBloccata stanza = new StanzaBloccata(nome,nomeAttr,direzione);
    		this.labirinto.stanzeLabirinto.put(nome, stanza); 
    		this.ultimaStanzaAggiunta = stanza;
    		return this;
    	}
    	
    	public LabirintoBuilder addStanzaBuia(String nome, String nomeAttr) {
    		StanzaBuia stanza = new StanzaBuia(nome,nomeAttr);
    		this.labirinto.stanzeLabirinto.put(nome, stanza); 
    		this.ultimaStanzaAggiunta = stanza;
    		return this;
    	}


        public Map<String, Stanza> getListaStanze() {
            return new HashMap<>(this.labirinto.stanzeLabirinto);
        }
        
        public Stanza getStanza(String nome) {
            return this.labirinto.stanzeLabirinto.get(nome);
        }
        
        public Labirinto build() {
        	if (this.labirinto.stanzaIniziale == null) {
                throw new IllegalStateException("Labirinto non valido: stanza iniziale non impostata.");
            }
        	this.labirinto.stanzaCorrente = this.labirinto.stanzaIniziale;
            return this.labirinto;
        }
    }	
	
}
