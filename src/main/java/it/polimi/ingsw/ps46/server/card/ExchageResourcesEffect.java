package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * This Class implements Effect, it's used to exchange resources between two differents resourceSet.
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
			
			
			//TODO SCELTA DI QUALE EXCHANGE RESOURCES EFFETTUARE?
			
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().sub(requiredResources);
			
			ResourceSet temporaryEffectResourceSet = new ResourceSet(gainedResources);
			if (!game.getCurrentPlayer().getDecreaseResourcesMalus().isEmpty())
			{
				for (DecreaseResourcesMalus decreaseResourcesMalus : game.getCurrentPlayer().getDecreaseResourcesMalus()) {
					if (decreaseResourcesMalus.name == "DecreaseResourcesMalus"){
						
						temporaryEffectResourceSet.sub(decreaseResourcesMalus.getDecreasedResources());
					}	
				}
			}
			// POSSIBILE MILGIORAMENTO DEL CODICE PER IMPEDIRE CHE IL MALUS VENGA IGNORATO nel caso di:
			//player resources 2 ; increase +1; decrease -2 ===> risultato 3; 
			
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(temporaryEffectResourceSet);
		}
		//else throw exception - NotActivableEffect
	}
	
	public boolean canBeActivated(Game game) {
		return(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().greaterOrEqual(requiredResources));
	}

}
