package it.polimi.ingsw.ps46.server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ListIterator;

import it.polimi.ingsw.ps46.utils.ReadInput;


public class ConsoleView extends View {
	
	private ReadInput input;
	private PrintStream output;
	
	public ConsoleView(OutputStream output) {
		this.output = new PrintStream(output);
		input = new ReadInput();
	}
	
	@Override
	public void run() {
		//WIP	
	}
	
	public void welcomeMessage() {
		output.println("Welcome to the game Lorenzo Il Magnifico!");
	}
	
	public String getPlayerUserame(int id) {
		output.println("Player " + id + ": what is your username?");
		String username = input.stringFromConsole();
		return username;
	}
	
	public void showInitialOrder(List<String> initialOrder) {
		output.println("The initial game order will be:");
		int position = 1;
		for(ListIterator<String> iterator=initialOrder.listIterator(); iterator.hasNext();){
			String username =iterator.next();
			output.println(position + ". " + username);
			position++;
		}
		
	}
	
}
