package it.polimi.ingsw.ps46.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class ConsoleView extends View {
	
	private Scanner scanner;
	private PrintStream output;
	
	public ConsoleView(InputStream inputStream, OutputStream output) {
		this.scanner = new Scanner(inputStream);
		this.output = new PrintStream(output);
	}
	
	@Override
	public void run() {
		//WIP	
	}
	
}
