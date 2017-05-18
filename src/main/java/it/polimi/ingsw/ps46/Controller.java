package it.polimi.ingsw.ps46;

import java.util.HashSet;

/**
 * Description of Controller.
 * 
 * @author a.mondolo
 */
public class Controller implements Observer {
	/**
	 * Description of the property views.
	 */
	public HashSet<View> views = new HashSet<View>();

	/**
	 * Description of the property games.
	 */
	public HashSet<Game> games = new HashSet<Game>();


	/**
	 * The constructor.
	 */
	public Controller() {
		super();
	}

	/**
	 * Returns views.
	 * @return views 
	 */
	public HashSet<View> getViews() {
		return this.views;
	}

	/**
	 * Returns games.
	 * @return games 
	 */
	public HashSet<Game> getGames() {
		return this.games;
	}

}
