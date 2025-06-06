package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	IO io;

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione)throws Throwable{
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		String nomeComando = null; // es. ‘vai’
		String parametro = null; // es. ‘sud’
		Comando comando = null;
		if (scannerDiParole.hasNext())
		nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
		parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		StringBuilder nomeClasse

		= new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
		nomeClasse.append( nomeComando.substring(1) ) ;
		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
		//comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		// POSSIBILE ALTERNATIVA basata sul rendere il tipo Class<Comando> esplicito:
		try {
			comando = ((Class<Comando>)Class.forName(nomeClasse.toString())).newInstance();
			comando.addIO(io);
		}
		catch(InstantiationException e) {
			this.io.mostraMessaggio("ERRORE: la classe del comando non può essere creata");
			comando = new ComandoNonValido(io);
		}
		catch(IllegalAccessException e) {
			this.io.mostraMessaggio("ERRORE: la classe del comando esiste, ma non è accessibile");
			comando = new ComandoNonValido(io);
		}
		catch(ClassNotFoundException e) {
			/* possibile causa: comando ignoto – errore digitazione utente */
			comando = new ComandoNonValido(io);
			this.io.mostraMessaggio("Comando inesistente");
		}
		comando.setParametro(parametro);
		return comando;
	}
}
