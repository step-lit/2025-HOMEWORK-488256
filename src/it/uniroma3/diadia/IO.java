package it.uniroma3.diadia;

import java.io.Closeable;

public interface IO extends Closeable{
	
	public void mostraMessaggio(String msg);
	public String leggiRiga();
	
}
