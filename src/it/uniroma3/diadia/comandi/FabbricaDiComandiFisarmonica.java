package it.uniroma3.diadia.comandi;

import java.util.Scanner;
import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	
	IO io;
	
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}
	
	@Override
	public Comando costruisciComando(String istruzione) {
		
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); //prima parola: nomeComando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); //seconda parola: parametro (eventuale)
		
		if (nomeComando == null)
			comando = new ComandoNonValido(this.io);
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai(this.io);
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(this.io);
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(this.io);
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto(this.io);
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine(this.io);
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda(this.io);
		else comando = new ComandoNonValido(this.io);
		
		comando.setParametro(parametro);
		return comando;
		
	}
	
	
}
