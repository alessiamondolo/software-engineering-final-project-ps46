package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


public class ExchageResourcesEffect implements Effect {

	public ResourceSet requiredResources;
	public ResourceSet gainedResources;

	
	public ExchageResourcesEffect(ResourceSet requiredResources, ResourceSet gainedResources) {
		this.requiredResources = requiredResources;
		this.gainedResources = gainedResources;
	}

	public void activateEffect(Game game) {
		if(canBeActivated(game)) {
			game.getCurrentPlayer().getResources().sub(requiredResources);
			game.getCurrentPlayer().getResources().add(gainedResources);
		}
		//else throw exception - NotActivableEffect
	}
	
	public boolean canBeActivated(Game game) {
		return(game.getCurrentPlayer().getResources().greaterOrEqual(requiredResources));
	}

}
