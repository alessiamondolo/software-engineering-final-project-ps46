package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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

	public void run() {
		setupGame();
		
	}

	private void setupGame() {
		view.welcomeMessage();
		setupPlayers();
		setupInitialOrder();
		
	}

	private void setupPlayers() {
		// TODO Auto-generated method stub
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			game.setCurrentPlayer(player);
			//TODO check per avere username univoco?
			player.setUsername(view.getPlayerUserame(game.getCurrentPlayer().getIdPlayer()));
		}
		
	}
	
	private void setupInitialOrder() {
		game.setInitialOrder();
		List<String> initialOrder = new ArrayList<String>();
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			initialOrder.add(player.getUsername());
		}
		view.showInitialOrder(initialOrder);
	}

}
