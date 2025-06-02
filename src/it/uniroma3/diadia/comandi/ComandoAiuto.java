package it.uniroma3.diadia.comandi;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Comando "aiuto".
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {
	
	private IO io;
	private ArrayList<String> elencoComandi;
	public ComandoAiuto(IO io) {
		this.io = io;
		try {
			StringBuilder FileName;
			Stream<Path> FIleList = Files.list(Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()));
			for(Path f : FIleList.toList()) {
				FileName = new StringBuilder(f.toString());
			}
			
			FIleList.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
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
