package it.uniroma3.diadia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {
	
	private List<String> input; 
	private List<String> output;

	
	//viene passato un oggetto di una classe che implementa List
	public IOSimulator(List<String> input) {
		this.input = new ArrayList<>(input);   //ArrayList accetta nei costruttori collection oppure int (initial capacity)
		this.output = new ArrayList<>();
	}
	
	public List<String> getOutput(){
		return this.output;
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		this.output.add(msg);
	}

	@Override
	public String leggiRiga() {
		
		if(this.input.size() > 0) {
			String comando = this.input.removeFirst();
			return comando;
		}
		return null;
	}

	@Override
	public void close() throws IOException {
		
	}

}
