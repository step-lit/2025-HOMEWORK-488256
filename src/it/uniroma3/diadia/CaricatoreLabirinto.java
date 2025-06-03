package it.uniroma3.diadia;

import java.io.*;
import java.util.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale e della stanza vincente */
	private static final String ESTREMI_MARKER = "Estremi:";    

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto

		Stanze:
		N10
		Biblioteca
		Estremi:
		N10
		Biblioteca
		Attrezzi:
		Osso 5 N10
		Uscite:
		N10 nord Biblioteca
		Biblioteca sud N10

	 */
	
	private LineNumberReader reader;
	private Labirinto.LabirintoBuilder builder;
	private String rigaAccantonata;


	public CaricatoreLabirinto(Reader reader) {
		this.reader = new LineNumberReader(reader);
		this.builder = new Labirinto.LabirintoBuilder();
		this.rigaAccantonata = null;
	}
	
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiEstremi();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} catch (IOException e) {
			throw new FormatoFileNonValidoException("Errore durante la lettura del labirinto: " + e.getMessage() + " (linea " + reader.getLineNumber() + ")");
			
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void leggiMarker(String marker) throws IOException, FormatoFileNonValidoException {
        String rigaLetta;
        if (this.rigaAccantonata != null) {
            rigaLetta = this.rigaAccantonata;
            this.rigaAccantonata = null;
        } else {
            rigaLetta = this.reader.readLine();
        }

        while (rigaLetta != null && rigaLetta.trim().isEmpty()) {
            rigaLetta = this.reader.readLine();
        }
        
        check(rigaLetta != null, "Fine del file inaspettata. Atteso marker: " + marker);
        check(rigaLetta.trim().equals(marker), "Formato file errato. Attesa riga con marker '" + marker + "', trovata: '" + rigaLetta + "'");
    }
	
	private String leggiProssimaRigaValida() throws IOException {
        String riga = this.reader.readLine();
        while (riga != null) {
            String rigaDopoTrim = riga.trim();
            if (rigaDopoTrim.isEmpty()) {
                riga = this.reader.readLine();
                continue;
            }
            if (isMarker(rigaDopoTrim)) { 
                this.rigaAccantonata = rigaDopoTrim; 
                return null; 
            }
            return rigaDopoTrim; 
        }
        return null;
    }
	
	private boolean isMarker(String riga) {
        if (riga == null) return false;
        return riga.equals(STANZE_MARKER) || riga.equals(ESTREMI_MARKER) ||
               riga.equals(ATTREZZI_MARKER) || riga.equals(USCITE_MARKER);
    }

	private void leggiECreaStanze() throws IOException, FormatoFileNonValidoException  {
		leggiMarker(STANZE_MARKER);
		String nomeStanza;
		while( (nomeStanza = leggiProssimaRigaValida()) != null ) {
			this.builder.addStanza(nomeStanza);
		}
	}

	private void leggiEstremi() throws IOException, FormatoFileNonValidoException {
		leggiMarker(ESTREMI_MARKER);
		
		String nomeStanzaIniziale = leggiProssimaRigaValida();
		check(nomeStanzaIniziale != null, nomeStanzaIniziale +" non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		
		String nomeStanzaVincente = leggiProssimaRigaValida();
		check(nomeStanzaVincente != null, nomeStanzaVincente + " non definita");
		this.builder.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws IOException, FormatoFileNonValidoException {
		leggiMarker(ATTREZZI_MARKER);

		String specificaAttrezzo;
		while ( (specificaAttrezzo = leggiProssimaRigaValida() ) != null) {
            String nomeAttrezzo = null;
            String pesoAttrezzo = null;
            String nomeStanza = null; 
            
            try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
                nomeAttrezzo = scannerLinea.next();
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
                pesoAttrezzo = scannerLinea.next();
                check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
                nomeStanza = scannerLinea.next();
            }
            posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
        }
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
        int peso;
        try {
            peso = Integer.parseInt(pesoAttrezzo);
        } catch (NumberFormatException e) {
            check(false, "Peso attrezzo '" + nomeAttrezzo + "' non valido: '" + pesoAttrezzo + "'. Deve essere un numero intero.");
            return;
        }
        
        Stanza stanzaDestinazione = this.builder.getStanza(nomeStanza);
        check(isStanzaValida(nomeStanza), "Attrezzo '" + nomeAttrezzo + "' non collocabile: stanza '" + nomeStanza + "' inesistente.");
        
        Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
        stanzaDestinazione.addAttrezzo(attrezzo);
    }


	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getStanza(nomeStanza) != null;
	}

	private void leggiEImpostaUscite() throws IOException, FormatoFileNonValidoException {
        leggiMarker(USCITE_MARKER);

        String specificaUscita;
        while ( (specificaUscita = leggiProssimaRigaValida()) != null ) {
            try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {
                check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la stanza di partenza per impostare l'uscita."));
                String stanzaPartenza = scannerDiLinea.next();
                check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la direzione dell'uscita della stanza " + stanzaPartenza));
                String dir = scannerDiLinea.next();
                check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la stanza di destinazione dell'uscita della stanza " + stanzaPartenza + " nella direzione " + dir));
                String stanzaDestinazione = scannerDiLinea.next();
                
                impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
            }
        } 
    }
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDaNome, String dir, String stanzaANome) throws FormatoFileNonValidoException {
        check(isStanzaValida(stanzaDaNome), "Stanza di partenza '" + stanzaDaNome + "' sconosciuta.");
        check(isStanzaValida(stanzaANome), "Stanza di destinazione '" + stanzaANome + "' sconosciuta.");
        this.builder.addAdiacenza(stanzaDaNome, stanzaANome, dir);
    }


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Labirinto getLabirintoCostruito() {
        return this.builder.build();
    }

}
