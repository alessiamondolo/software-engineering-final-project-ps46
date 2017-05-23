package it.polimi.ingsw.ps46.server;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps46.client.View;


/**
 * Description of Controller.
 * 
 * @author a.mondolo
 */
public class Controller implements Observer {
	/**
	 * Description of the property game.
	 */
	private Game game;
	
	/**
	 * Description of the property view.
	 */
	private View view;


	/**
	 * The constructor.
	 */
	public Controller(Game game, View view) {
		this.game = game;
		this.view = view;
	}

	@Override
	public void update(Observable o, Object arg) {
		//WIP
	}
	

}
