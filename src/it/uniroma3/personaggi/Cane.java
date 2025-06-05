package it.uniroma3.personaggi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	private int CFUDaPerdere;
	private Attrezzo Cibo;
	private final ArrayList<Attrezzo> AttrezziPossibili = new ArrayList<Attrezzo>(List.of(
			new Attrezzo("Martello", 5),
			new Attrezzo("Chiave", 1)
			//aggiungere altri attrezzi(?)
			));
	
	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
		CFUDaPerdere = 1;
		Cibo = null;
	}
	
	@Override public String saluta() {
		StringBuilder messaggio = new StringBuilder();
		if (Cibo == null) {
			messaggio.append("Il cane sembra arrabiato, forse è meglio stargli lontano, oppure calmarlo in qualche modo...");
		}
		else messaggio.append("Anche avendo ricevuto il suo cibo preferito, il cane sembra comunque arrabbiato, meglio stargli lontano...");
		return messaggio.toString();
	}
	

	@Override
	public String agisci(Partita partita) {
		// TODO Auto-generated method stub
		StringBuilder messaggio = new StringBuilder("OUCH! Ti ha morso il cane! hai perso " + CFUDaPerdere + " CFU! \nSenti che la prossima volta il morso sarà più potente!");
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - CFUDaPerdere);
		CFUDaPerdere ++;
		return messaggio.toString();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder messaggio = new StringBuilder();
		if(attrezzo.getNome().equals("Bistecca") || attrezzo.getNome().equals("Osso")) {
			messaggio.append("Il cane sembra apprezzare!\n"
					+ "Noti che ha rilasciato un'attrezzo per prendere il cibo, magari sarà utile\n"
					+ "(anche se pieno di bava, che schifo!).");
			partita.getStanzaCorrente().addAttrezzo(AttrezziPossibili.get(new Random().nextInt(AttrezziPossibili.size())));
		}
		else {
			messaggio.append("Il cane sembra non voler un qualsiasi attrezzo\n"
					+ "In effetti, ti rendi conto che è affamato, se solo ci fosse del cibo...");
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);//il metodo regala rimuove l'ggetto dalla borsa
		}
		return messaggio.toString();
	}

}
