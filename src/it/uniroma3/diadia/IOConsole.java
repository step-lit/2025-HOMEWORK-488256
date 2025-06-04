package it.uniroma3.diadia;
import java.io.IOException;
import java.util.Scanner;

public class IOConsole implements IO {
	Scanner scannerDiLinee;

	public Scanner getScannerDiLinee() {
		return scannerDiLinee;
	}

	public void setScannerDiLinee(Scanner scannerDiLinee) {
		this.scannerDiLinee = scannerDiLinee;
	}

	public IOConsole() {
		this.scannerDiLinee = new Scanner(System.in);
	}

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		String riga;
		riga = scannerDiLinee.nextLine();
		return riga;
	}

	@Override
	public void close() throws IOException {
		this.scannerDiLinee.close();
	}

}