package it.polimi.ingsw.ps46.server;

import java.io.PrintStream;

import it.polimi.ingsw.ps46.utils.ReadInput;


/**
 * Main class for the server.
 * It is possible to choose between playing a local game or starting the server for handling online games.
 * 
 * @author Alessia Mondolo
 */
public class ServerApplication {
	
	private static PrintStream printStream = System.out;
	private static ReadInput readInput = new ReadInput();
	
	
	private Game game;	
	private View view;
	private GameController controller;
	private static int numberPlayers;
	
	/**
	 * The constructor.
	 */
	public ServerApplication(int numberPlayers) {
		game = new Game(numberPlayers);
		printStream.println("Do you want to play with the CLI or with the GUI?");
		printStream.println("1. CLI");
		printStream.println("2. GUI");
		int UI = readInput.IntegerFromConsole(1, 2);
		if(UI == 1)
			view = new ConsoleView(System.out);
		else
			//TODO da cambiare mettendo GUIView
			view = new ConsoleView(System.out);
		controller = new GameController(game, view);
	}

	/**
	 * Description of the method main.
	 */
	public static void main(String[] args) {
		printStream.println("Choose the game mode:");
		printStream.println("1. Local");
		printStream.println("2. Online (this will be the server for the game)");
		int gameMode = readInput.IntegerFromConsole(1, 2);
		if(gameMode == 1) {
			printStream.println("Chose the number of players (2, 3 or 4):");
			numberPlayers = readInput.IntegerFromConsole(2, 4);
			ServerApplication localGame = new ServerApplication(numberPlayers);
			localGame.run();
		}
		else {
			//TODO setup of RMI and Socket connection for online game
		}
	}
	
	public void run() {
		view.addObserver(controller);
		//game.addObserver(controller);
		game.addObserver(view);
		controller.run();
	}

}
