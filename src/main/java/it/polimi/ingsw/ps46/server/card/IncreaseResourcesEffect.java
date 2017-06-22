package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * IncreaseResourcesEffect is an object that represent an effect that increases
 * the resources of a player.
 * 
 * @author Alessia Mondolo
 */
public class IncreaseResourcesEffect implements Effect {
	
	protected ResourceSet additionalResources; 

	/**
	 * Build a new effect of the type IncreaseResourcesEffect.
	 */
	public IncreaseResourcesEffect(ResourceSet additionalResources) {
		this.additionalResources = additionalResources;
	}

	/**
	 * Returns additionalResources.
	 * @return additionalResources 
	 */
	public ResourceSet getAdditionalResources() {
		return this.additionalResources;
	}
	
	/**
	 * This method adds the additional resources to the resources of the current player,
	 * who is the one that activated the card with the effect IncreaseResourcesEffect.
	 */
	public void activateEffect(Game game) {
		game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(additionalResources);
	}
	
	public String toString() {
		return additionalResources.toString();
	}

}
