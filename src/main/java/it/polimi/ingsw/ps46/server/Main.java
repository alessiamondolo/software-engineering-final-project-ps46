package it.polimi.ingsw.ps46.server;

import it.polimi.ingsw.ps46.client.ConsoleView;
import it.polimi.ingsw.ps46.client.View;

/**
 * Description of Main.
 * 
 * @author a.mondolo
 */
public class Main {
	
	private Game game;	
	private View view;
	private Controller controller;
	private Integer numberPlayers;
	
	/**
	 * The constructor.
	 */
	public Main() {
		game = new Game(numberPlayers);
		view = new ConsoleView(System.in, System.out);
		controller = new Controller(game, view);
		view.addObserver(controller);
		game.addObserver(controller);
	}

	/**
	 * Description of the method main.
	 */
	public static void main() {
		Main main = new Main();
		main.run();
	}
	
	public void run() {
		while(true) {
			
			
		}
	}

}
