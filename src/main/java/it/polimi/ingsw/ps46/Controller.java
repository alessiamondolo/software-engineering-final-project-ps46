package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

import java.util.HashSet;

// Start of user code (user defined imports)

// End of user code

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

	// Start of user code (user defined attributes for Controller)

	// End of user code

	/**
	 * The constructor.
	 */
	public Controller() {
		// Start of user code constructor for Controller)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Controller)

	// End of user code
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
