package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


/**
 * The GameController controls and coordinates the game logic.
 * 
 * @author Alessia Mondolo
 */
public class GameController implements Observer {

	private Game game;
	private View view;


	/**
	 * Creates a new GameController object.
	 */
	public GameController(Game game, View view) {
		this.game = game;
		this.view = view;
	}

	
	@Override
	public void update(Observable o, Object arg) {
		//WIP
	}

	
	
	/**
	 * Main method that controls all the game logic, from the setup of the game until the end of the game.
	 */
	public void run() {
		setupGame();
		
		for(int period = 1; period <= game.getPERIODS(); period++) {
			for(int round = 1; round <= game.getROUNDS_PER_PERIOD(); round++) {
				roundSetup(period, round);
				for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
					Player player=iterator.next();
					game.setCurrentPlayer(player);
					view.printPlayerStatus(game.getCurrentPlayer().getUsername());
					playerActions();
				}
				if(round == game.getROUNDS_PER_PERIOD())
					vaticanReport();
				endRound();
			}
		}
		
	}

	
	
	/**
	 * Sets up the game, by calling other methods to welcome the players, ask the players' username, 
	 * select a random initial order for the game and ask the players which color they want to use.
	 */
	private void setupGame() {
		game.startGame();
		setupPlayers();
		setupInitialOrder();
		setupPlayersColor();
	}

	
	
	/**
	 * Iterates through the list of players, asking each player through the view the username that they want to use during the game.
	 */
	private void setupPlayers() {
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			game.setCurrentPlayer(player);
			//TODO check per avere username univoco?
			player.setUsername(view.getPlayerUserame(game.getCurrentPlayer().getIdPlayer()));
		}
	}
	
	
	
	/**
	 * Chooses a random order for the first turn of the game and shows the order to the players through the view.
	 */
	private void setupInitialOrder() {
		game.setInitialOrder();
		List<String> initialOrder = new ArrayList<String>();
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			game.setCurrentPlayer(player);
			initialOrder.add(player.getUsername());
		}
		view.showInitialOrder(initialOrder);
	}
	
	
	
	/**
	 * Asks the players through the view the color that they want to use during the game.
	 */
	private void setupPlayersColor() {
		List<String> colors = new ArrayList<String>();
		colors.add("Red");
		colors.add("Yellow");
		colors.add("Blue");
		colors.add("Green");
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			game.setCurrentPlayer(player);
			String color = view.getPlayerColor(game.getCurrentPlayer().getUsername(), colors);
			player.setColor(color);
			colors.remove(color);
		}
	}
	
	
	
	/**
	 * @param round 
	 * @param period 
	 * 
	 */
	private void roundSetup(int period, int round) {
		view.updateRoundInfo(period, round);
		//Girare le carte associate a quel periodo nelle torri
		
		//Throw dice and update board in the view
		Map<String, Dice> diceMap = game.getDice();
		for(String key : diceMap.keySet())
			diceMap.get(key).throwDice();
		List<Integer> dice = new ArrayList<Integer>();
		Dice die = game.getDice("Black");
		dice.add(Integer.valueOf(die.getValue()));
		die = game.getDice("White");
		dice.add(Integer.valueOf(die.getValue()));
		die = game.getDice("Orange");
		dice.add(Integer.valueOf(die.getValue()));
		view.printBoard(dice);
	}
	
	
	
	/**
	 * 
	 */
	private void playerActions() {
		//ActionSpaceName move = 
				view.getPlayerAction();
	}
	
	
	
	/**
	 * 
	 */
	private void vaticanReport() {
		// TODO Auto-generated method stub
		//Da implementare con le regole avanzate
	}
	
	
	
	/**
	 * 
	 */
	private void endRound() {
		// TODO Auto-generated method stub
	}

}
