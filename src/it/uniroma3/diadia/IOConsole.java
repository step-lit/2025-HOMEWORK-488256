package it.uniroma3.diadia;
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

	}

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		String riga;
		Scanner scannerDiLinee = new Scanner(System.in);
		riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}

}