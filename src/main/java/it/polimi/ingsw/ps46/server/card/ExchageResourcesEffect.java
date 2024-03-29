package it.polimi.ingsw.ps46.server.card;

import java.io.Serializable;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * This Class implements Effect, it's used to exchange resources between two differents resourceSet.
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
			
			ResourceSet temporaryEffectResourceSet = new ResourceSet(gainedResources);
			if (game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources() != null) {
				temporaryEffectResourceSet.sub(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources());
				}

			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(temporaryEffectResourceSet);
			
		}
			//else throw exception - NotActivableEffect
	}
	
	
	public boolean canBeActivated(Game game) {
		return(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().greaterOrEqual(requiredResources));
	}
	
	
	public String toString() {
		return "Exchange: " + requiredResources.toString() + "\n" +
				"with: " + gainedResources.toString();
	}

}
