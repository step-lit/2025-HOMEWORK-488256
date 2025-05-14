package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class OrdineBorsaPesoCrescenteONomeCrescente implements Comparator<Attrezzo>{
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		int diffPesi = a1.getPeso()-a2.getPeso();
		if(diffPesi == 0) {
			return a1.getNome().compareTo(a2.getNome());
		}
		return diffPesi;
	}
}
