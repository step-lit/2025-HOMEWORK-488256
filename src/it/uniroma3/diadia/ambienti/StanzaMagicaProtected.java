package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		if (this.attrezzi.size() < this.NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		else return false;
	}
	
	/** 
	 * 	metodo che prende in input un attrezzo e ritorna l'attrezzo
	 *  magicamente modificato 
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso()*2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		
		return attrezzo;
	}

}