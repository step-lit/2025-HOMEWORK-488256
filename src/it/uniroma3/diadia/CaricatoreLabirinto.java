package it.uniroma3.diadia;

import java.io.*;
import java.util.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	private static final String STANZE_MARKER = "Stanze:";      
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
    private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";
    private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
	private static final String ESTREMI_MARKER = "Estremi:";    
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	private static final String PERSONAGGI_MARKER = "Personaggi:";
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto

		Stanze:
		N10
		Biblioteca
		AtrioMagico
		CorridoioBloccato
		AulaBuia
		
		StanzeMagiche: 
		AtrioMagico 1
		
		StanzeBloccate:
		CorridoioBloccato chiave nord
		
		StanzeBuie:
		AulaBuia lanterna 
		
		Estremi:
		N10
		Biblioteca
		
		Attrezzi:
		Osso 5 N10
		Lanterna 1 AtrioMagico
		
		Personaggi: 
		Mago Gandalf "Un vecchio saggio" Bacchetta 5 AtrioMagico 
		Cane Fido "Bau bau!" N10
		Strega Malefica "Hihihi!" CorridoioBloccato
		
		Uscite:
		N10 nord Biblioteca
		Biblioteca sud N10

	 */
	
	private LineNumberReader reader;
	private Labirinto.LabirintoBuilder builder;
	private String rigaAccantonata;


	public CaricatoreLabirinto(Reader reader) {
		this.reader = new LineNumberReader(reader);
		this.builder = Labirinto.newBuilder();
		this.rigaAccantonata = null;
	}
	
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiStanzeMagiche();
			this.leggiStanzeBloccate();
			this.leggiStanzeBuie();
			this.leggiEstremi();
			this.leggiECollocaAttrezzi();
			this.leggiPersonaggi();
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
        if (this.rigaAccantonata != null) {
        	check(this.rigaAccantonata.equals(marker), "Formato file errato. Atteso marker '" + marker + "', ma trovato marker accantonato: '" + this.rigaAccantonata + "'");
            this.rigaAccantonata = null;
            return;
        }
        
        String rigaLetta = this.reader.readLine();
        while (rigaLetta != null && rigaLetta.trim().isEmpty()) {
            rigaLetta = this.reader.readLine();
        }
        
        check(rigaLetta != null, "Fine del file inaspettata. Atteso marker: " + marker);
        check(rigaLetta.trim().equals(marker), "Formato file errato. Attesa riga con marker '" + marker + "', trovata: '" + rigaLetta.trim() + "'");
    }
	
	private String leggiProssimaRigaValida() throws IOException {
        if(this.rigaAccantonata != null) {
        	return null;
        }
        
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
               riga.equals(ATTREZZI_MARKER) || riga.equals(USCITE_MARKER) ||
               riga.equals(STANZE_MAGICHE_MARKER) || riga.equals(STANZE_BLOCCATE_MARKER) ||
               riga.equals(STANZE_BUIE_MARKER) || riga.equals(PERSONAGGI_MARKER);
    }

	private void leggiECreaStanze() throws IOException, FormatoFileNonValidoException  {
		leggiMarker(STANZE_MARKER);
		String nomeStanza;
		while( (nomeStanza = leggiProssimaRigaValida()) != null ) {
			this.builder.addStanza(nomeStanza);
		}
	}
	
	private void leggiStanzeMagiche() throws IOException, FormatoFileNonValidoException {
		if(this.rigaAccantonata != null && !this.rigaAccantonata.equals(STANZE_MAGICHE_MARKER)) {
			return; //marker sbagliato
		}
			
		if (this.rigaAccantonata == null) {
			String prossimaRiga = reader.readLine();
			if (prossimaRiga == null) return; //se è vuota è la fine del file
			prossimaRiga = prossimaRiga.trim();
			if (!prossimaRiga.equals(STANZE_MAGICHE_MARKER)) {
				this.rigaAccantonata = prossimaRiga; //se non è il marker la accantono e termino
				return;
			}
		} else { //se era il marker giusto
			this.rigaAccantonata = null;
		}
		
		
		String specificaStanza;
		while ( (specificaStanza = leggiProssimaRigaValida()) != null ) {
			try (Scanner scanner = new Scanner(specificaStanza)){
				String nomeStanza = scanner.next();
				check(isStanzaValida(nomeStanza), "Stanza magica '" + nomeStanza + "' non dichiarata precedentemente.");
				
                if(scanner.hasNextInt()) {//se è stata specificata una soglia
                	int soglia = scanner.nextInt();
                	if(scanner.hasNextInt()) {
                		check(false, "Token extra dopo la soglia per stanza magica '" + nomeStanza + "'. Riga: " + specificaStanza);
                	}
                	this.builder.addStanzaMagica(nomeStanza, soglia);
                }
                else {
                	this.builder.addStanzaMagica(nomeStanza);
                }
			}
		}
	}
	
	private void leggiStanzeBloccate() throws IOException, FormatoFileNonValidoException {
        if (this.rigaAccantonata != null && !this.rigaAccantonata.equals(STANZE_BLOCCATE_MARKER)) return; //marker sbagliato
        if (this.rigaAccantonata == null) {
            String prossimaRiga = reader.readLine();
            if (prossimaRiga == null) return; //se è vuota è la fine del file
            prossimaRiga = prossimaRiga.trim();
            if (!prossimaRiga.equals(STANZE_BLOCCATE_MARKER)) {
                this.rigaAccantonata = prossimaRiga; 
                return;
            }
        } else { 
        	this.rigaAccantonata = null;
        }

        String specificaStanza;
        while( (specificaStanza = leggiProssimaRigaValida()) != null ) {
            try (Scanner scanner = new Scanner(specificaStanza)) {
                String nomeStanza = scanner.next();
                check(isStanzaValida(nomeStanza), "Stanza bloccata '" + nomeStanza + "' non dichiarata precedentemente.");
                String attrezzo = scanner.next();
                String direzione = scanner.next();
                if (scanner.hasNext()) check(false, "Token extra per stanza bloccata " + nomeStanza);
                this.builder.addStanzaBloccata(nomeStanza, attrezzo, direzione);
            }
        }
    }
	
	private void leggiStanzeBuie() throws IOException, FormatoFileNonValidoException {
        if (this.rigaAccantonata != null && !this.rigaAccantonata.equals(STANZE_BUIE_MARKER)) return; //marker sbagliato
        if (this.rigaAccantonata == null) {
            String prossimaRiga = reader.readLine();
            if (prossimaRiga == null) return;
            prossimaRiga = prossimaRiga.trim();
            if (!prossimaRiga.equals(STANZE_BUIE_MARKER)) {
                this.rigaAccantonata = prossimaRiga; return;
            }
        } else { 
        	this.rigaAccantonata = null;
        }

        String specificaStanza;
        while( (specificaStanza = leggiProssimaRigaValida()) != null ) {
            try (Scanner scanner = new Scanner(specificaStanza)) {
                String nomeStanza = scanner.next();
                check(isStanzaValida(nomeStanza), "Stanza buia '" + nomeStanza + "' non dichiarata precedentemente.");
                String attrezzo = scanner.next();
                if (scanner.hasNext()) check(false, "Token extra per stanza buia " + nomeStanza);
                this.builder.addStanzaBuia(nomeStanza, attrezzo);
            }
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
            check(false, "Il peso dell'attrezzo '" + nomeAttrezzo + "' non è valido: '" + pesoAttrezzo + "'. Deve essere un numero intero.");
            return;
        }
        
        Stanza stanzaDestinazione = this.builder.getStanza(nomeStanza);
        check(isStanzaValida(nomeStanza), "L'attrezzo '" + nomeAttrezzo + "' non è collocabile nella stanza '" + nomeStanza + "' perché inesistente.");
        
        Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
        stanzaDestinazione.addAttrezzo(attrezzo);
    }

	private void leggiPersonaggi() throws IOException, FormatoFileNonValidoException {
        if (this.rigaAccantonata != null && !this.rigaAccantonata.equals(PERSONAGGI_MARKER)) return;
        if (this.rigaAccantonata == null) {
            String prossimaRiga = reader.readLine();
            if (prossimaRiga == null) return; // Fine file, sezione opzionale
            prossimaRiga = prossimaRiga.trim();
            if (!prossimaRiga.equals(PERSONAGGI_MARKER)) {
                this.rigaAccantonata = prossimaRiga; // Non è questo marker, accantona e esci
                return;
            }
        } else { // rigaAccantonata era PERSONAGGI_MARKER
            this.rigaAccantonata = null; // Consuma
        }

        String specificaPersonaggio;
        while ((specificaPersonaggio = leggiProssimaRigaValida()) != null) {
            try (Scanner scanner = new Scanner(specificaPersonaggio)) {
                scanner.useDelimiter("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //divide per spazi e riconosce le virgolette

                String tipoPersonaggio = scanner.next();
                String nomePersonaggio = scanner.next();
                String presentazione = scanner.next().replace("\"", ""); // rimuove virgolette
                String nomeStanzaCollocamento;

                switch (tipoPersonaggio) {
                    case "Mago":
                        String nomeAttrezzoMago = scanner.next();
                        check(scanner.hasNextInt(), "Il peso dell'attrezzo per il Mago " + nomePersonaggio + "è mancante o non numerico.");
                        int pesoAttrezzoMago = scanner.nextInt();
                        nomeStanzaCollocamento = scanner.next();
                        check(isStanzaValida(nomeStanzaCollocamento), "La stanza '" + nomeStanzaCollocamento + "' per il Mago " + nomePersonaggio + " non è definita.");
                        this.builder.addMago(nomePersonaggio, presentazione, nomeAttrezzoMago, pesoAttrezzoMago, nomeStanzaCollocamento);
                        break;
                    case "Cane":
                        nomeStanzaCollocamento = scanner.next();
                         check(isStanzaValida(nomeStanzaCollocamento), "La stanza '" + nomeStanzaCollocamento + "' per il Cane " + nomePersonaggio + " non è definita.");
                        this.builder.addCane(nomePersonaggio, presentazione, nomeStanzaCollocamento);
                        break;
                    case "Strega":
                        nomeStanzaCollocamento = scanner.next();
                        check(isStanzaValida(nomeStanzaCollocamento), "La stanza '" + nomeStanzaCollocamento + "' per la Strega " + nomePersonaggio + " non è definita.");
                        this.builder.addStrega(nomePersonaggio, presentazione, nomeStanzaCollocamento);
                        break;
                    default:
                        check(false, "Tipo di personaggio sconosciuto: " + tipoPersonaggio);
                }
                 if (scanner.hasNext()) check(false, "C'è un token non necessario nella riga del personaggio" + nomePersonaggio);
            }
        }
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
