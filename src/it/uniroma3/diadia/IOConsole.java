package it.uniroma3.diadia;
import java.io.IOException;
import java.util.Scanner;

public class IOConsole implements IO {
	
	private Scanner scannerDiLinee;

	public Scanner getScannerDiLinee() {
		return scannerDiLinee;
	}

	public void setScannerDiLinee(Scanner scannerDiLinee) {
		this.scannerDiLinee = scannerDiLinee;
	}

	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		return this.scannerDiLinee.nextLine();
	}

	@Override
	public void close() throws IOException {
	}

}