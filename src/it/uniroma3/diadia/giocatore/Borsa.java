package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	private List<Attrezzo> attrezzi;
	private int pesoMax;
		
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		//Collections.sort(attrezzi, new OrdineBorsaPesoCrescenteONomeCrescente());
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo b : attrezzi) {
				if (b.getNome().equals(nomeAttrezzo)) {
					return b;
				}		
		}
		return null;	
	}	
	
	public int getPeso() {
		int peso = 0;
		//collections.
		for (Attrezzo b : attrezzi) {
			if(b != null) {
				peso += b.getPeso();
			}
		}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}
	
	public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }
	
		
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
		
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = getAttrezzo(nomeAttrezzo);
		attrezzi.remove(a);
		return a;
	}
		
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo b : attrezzi) {
					s.append(b.toString()+" ");
			}
	    }
		else
			s.append("Borsa vuota");
			
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> a = new ArrayList<Attrezzo>(attrezzi);
		a.sort(new OrdineBorsaPesoCrescenteONomeCrescente());
		return a;
	}
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> a = new TreeSet<Attrezzo>(new OrdinePerNOme());
		a.addAll(attrezzi);
		return a;
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer,Set<Attrezzo>> a = new TreeMap<Integer,Set<Attrezzo>>();
		for (Attrezzo b : attrezzi) {
			Integer peso = Integer.valueOf(b.getPeso());
			Set<Attrezzo> setAttrezziStessoPeso = a.get(peso);
			if(setAttrezziStessoPeso!=null) setAttrezziStessoPeso.add(b);
			else {
				setAttrezziStessoPeso = new HashSet<Attrezzo>();
				setAttrezziStessoPeso.add(b);
				a.put(peso, setAttrezziStessoPeso);
			}
		}
		return a;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> a = new TreeSet<Attrezzo>(new OrdineBorsaPesoCrescenteONomeCrescente());
		a.addAll(attrezzi);
		return a;
	}
}
	