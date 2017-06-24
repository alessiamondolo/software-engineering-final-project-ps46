package it.polimi.ingsw.ps46.server.card;

import java.io.Serializable;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * 
 * 
 * @author Alessia Mondolo
 */
public class ExchageResourcesEffect implements Effect, Serializable { 

	private static final long serialVersionUID = 730731164960929718L;
	
	private ResourceSet requiredResources;
	private ResourceSet gainedResources;

	
	public ExchageResourcesEffect(ResourceSet requiredResources, ResourceSet gainedResources) {
		this.requiredResources = requiredResources;
		this.gainedResources = gainedResources;
	}

	public void activateEffect(Game game) {
		if(canBeActivated(game)) {
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().sub(requiredResources);
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(gainedResources);
		}
		//else throw exception - NotActivableEffect
	}
	
	public boolean canBeActivated(Game game) {
		return(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().greaterOrEqual(requiredResources));
	}

}
