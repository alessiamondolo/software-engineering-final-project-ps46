package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.GameState;
import it.polimi.ingsw.ps46.server.Resources;


/**
 * IncreaseResources is an object that represent an effect that increases
 * the resources of a player.
 * 
 * @author Alessia Mondolo
 */
public class IncreaseResources implements Effect {
	
	public final Resources additionalResources;

	/**
	 * Build a new effect of the type IncreaseResources.
	 */
	public IncreaseResources(Resources additionalResources) {
		this.additionalResources = additionalResources;
	}

	/**
	 * Returns additionalResources.
	 * @return additionalResources 
	 */
	public Resources getAdditionalResources() {
		return this.additionalResources;
	}
	
	/**
	 * This method adds the additional resources to the resources of the current player,
	 * who is the one that activated the card with the effect IncreaseResources.
	 */
	public void activateEffect(GameState game) {
		game.getCurrentPlayer().getResources().addResources(additionalResources);
	}

}
