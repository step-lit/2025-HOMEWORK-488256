package it.uniroma3.personaggi;

import java.util.Collection;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	Attrezzo dono;
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
		dono = null;
	}

	@Override
	public String agisci(Partita partita) {
		StringBuilder messaggio = new StringBuilder("La strega ha iniziato un'incantesimo...\n");
		Stanza nuovaStanza;
		Collection<Stanza> collezioneDiStanze = partita.getLabirinto().getMapStanzeLabirinto().values();
		collezioneDiStanze.remove(partita.getLabirinto().getStanzaVincente());
		if(dono!=null) {
			messaggio.append("'Visto la tua gentilezza, ho deciso di darti un premio...\n"
					+ "ti ritroverai nella stanza con più attrezzi,\n"
					+ "Chissà, magari troverai qualcosa di utile... nieHAHAHAHA!'");
			Stanza stanzaPiùAttrezzi = partita.getLabirinto().getStanzaCorrente();
			for(Stanza a : collezioneDiStanze) {
				if(stanzaPiùAttrezzi.getAttrezzi().size() < a.getAttrezzi().size()) stanzaPiùAttrezzi = a;
			}
			nuovaStanza = stanzaPiùAttrezzi;
		}
		else {
			messaggio.append("'nieHAHAHAHA! Vedrai cosa succede agli scortesi!'");
			Stanza stanzaMenoAttrezzi = partita.getLabirinto().getStanzaCorrente();
			for(Stanza a : collezioneDiStanze) {
				if(stanzaMenoAttrezzi.getAttrezzi().size() < a.getAttrezzi().size()) stanzaMenoAttrezzi = a;
			}
			nuovaStanza = stanzaMenoAttrezzi;
		}
		partita.getLabirinto().setStanzaCorrente(nuovaStanza);
		messaggio.append("Dopo essere investito da un baglio di luce lilla, ti ritrovi immerso in una strana nebbia..."
				+ "\nDopo qualche secondo, la nebbia svanisce, e ti accorgi di trovarti in un'altra stanza dell'universita!"+
				"\nTi guardi intorno:\n"+partita.getLabirinto().getStanzaCorrente().getDescrizione());
		return messaggio.toString();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		dono = attrezzo;
		return "La strega sghignazza n segno d'apprezzamento\n"
				+ "'nieHAHAHAHAHAHAHHAAAHAHAAUGH-'inizia a tossire\n"
				+ "\nForse lo ha apprezzato un pò troppo";
	}

}
