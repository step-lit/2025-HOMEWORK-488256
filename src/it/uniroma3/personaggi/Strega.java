package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
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
		if(dono!=null) {
			messaggio.append("Visto la tua gentilezza, ho deciso di darti un premio...\n"
					+ "ti ritroverai nella stanza con più attrezzi,\n"
					+ "Chissà, magari troverai qualcosa di utile... nieHAHAHAHA!");
			Labirinto labirinto = partita.getLabirinto();
		}
		return null;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		dono = attrezzo;
		return "La strega sghignazza n segno d'apprezzamento\n"
				+ "'nieHAHAHAHAHAHAHHAAAHAHAAUGH-'inizia a tossire\n"
				+ "\nForse lo ha apprezzato un pò troppo";
	}

}
