package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {
	
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	private String nome;
	private AbstractPersonaggio personaggio;
    private Map<String, Attrezzo> attrezzi; //array di oggetti attrezzo
    private Map<String, Stanza> stanzeAdiacenti; //array di oggetti stanza
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.attrezzi = new HashMap<>();
        this.stanzeAdiacenti = new HashMap<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Map<String, Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
        	//scorro l'arraylist di attrezzi per verificare se c'è già un attrezzo con quel nome
        	if( this.attrezzi.containsKey(attrezzo.getNome()) )
        		return false;
        	this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	return true;
        	
        }
        else {
        	return false;
        }
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    @Override
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.getDirezioni())
    		risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi.values()) {
    		risultato.append(attrezzo.toString()+" ");
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato = false;
		if ( this.attrezzi.containsKey(nomeAttrezzo) ) {
				trovato = true;
		}
		return trovato;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzo = this.attrezzi.remove(nomeAttrezzo);
		return attrezzo != null;
	}


	public Set<String> getDirezioni() {
		Set<String> direzioni = this.stanzeAdiacenti.keySet();
	    return direzioni;
    }
	
	public Map<String, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
	public boolean isMagica() {
		return false; //se non è una sottoclasse di stanza esegue questo metodo
	}

	public AbstractPersonaggio getPersonaggio() {
		return personaggio;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || !(o instanceof Stanza)) return false;
	    Stanza stanza = (Stanza) o;
	    return this.getNome().equals(stanza.getNome());
	}

	@Override
	public int hashCode() {
	    return this.getClass().hashCode() + this.getNome().hashCode();
	}

	public void aggiungiPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
}