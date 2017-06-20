package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * 
 * 
 * @author Alessia Mondolo
 */
public class ExchageResourcesEffect implements Effect { // ho cambiato gli attributi da public a private 

	private ResourceSet requiredResources;
	private ResourceSet gainedResources;

	
	public ExchageResourcesEffect(ResourceSet requiredResources, ResourceSet gainedResources) {
		this.requiredResources = requiredResources;
		this.gainedResources = gainedResources;
	}

	public void activateEffect(Game game) {
		if(canBeActivated(game)) {
			game.getCurrentPlayer().getPlayerResourceSet().sub(requiredResources);
			game.getCurrentPlayer().getPlayerResourceSet().add(gainedResources);
		}
		//else throw exception - NotActivableEffect
	}
	
	public boolean canBeActivated(Game game) {
		return(game.getCurrentPlayer().getPlayerResourceSet().greaterOrEqual(requiredResources));
	}

}
