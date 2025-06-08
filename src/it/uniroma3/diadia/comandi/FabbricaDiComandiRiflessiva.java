package it.uniroma3.diadia.comandi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private IO io;

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) throws IllegalAccessException {
		
		String nomeComando = null; 	// es. ‘vai’
		String parametro = null; 	// es. ‘sud’
		Comando comando = null;
		
		try ( Scanner scannerDiParole = new Scanner(istruzione) )	{  // es. ‘vai sud’
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next(); //prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();   //seconda parola: eventuale parametro
		} //chiudo qui lo scanner
		
		if(nomeComando == null) {
			return new ComandoNonValido(io);
		}
		
		try {
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
			nomeClasse.append( nomeComando.substring(1) );
			
			Class<?> classeComando = Class.forName(nomeClasse.toString());
			Constructor<?> costruttore = classeComando.getDeclaredConstructor();
			comando = (Comando) costruttore.newInstance();
			comando.addIO(io);
		}
		catch (ClassNotFoundException e) {
			comando = new ComandoNonValido(io);
			this.io.mostraMessaggio("Comando '" + nomeComando + "' inesistente.");
		}
		catch (NoSuchMethodException | InstantiationException | IllegalArgumentException | InvocationTargetException e) {
			comando = new ComandoNonValido(io);
			this.io.mostraMessaggio("Errore: il comando '" + nomeComando + "' non può essere eseguito.");
			e.printStackTrace();
		}
		
		if( parametro != null) {
			comando.setParametro(parametro);
		}
		return comando;
	}
}
