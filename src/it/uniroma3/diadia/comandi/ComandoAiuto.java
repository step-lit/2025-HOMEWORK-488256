package it.uniroma3.diadia.comandi;
 
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
 
/**
* Comando "aiuto".
* Stampa informazioni di aiuto.
*/
public class ComandoAiuto implements Comando {
	private IO io;
	
	public ComandoAiuto(IO io) {
		this.io = io;
	}
	
	public ComandoAiuto() {}
	
	
	@Override
	public void addIO(IO io) {
		this.io = io;
	}
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "interagisci", "fine"};
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i]);
		io.mostraMessaggio("");
	}
	@Override
	public void setParametro(String parametro) {
	}
	@Override
	public String getParametro() {
		return null;
	}
	@Override
	public String getNome() {
		return "aiuto";
	}
	

}