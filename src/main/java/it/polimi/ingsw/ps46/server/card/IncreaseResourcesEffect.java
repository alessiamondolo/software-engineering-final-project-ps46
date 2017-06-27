package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * IncreaseResourcesEffect is an object that represent an effect that increases the resources of a player.
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
	 * It's implemented the excommunication malus effect -1 called "DecreaseResourcesMalus"
	 */
	public void activateEffect(Game game) {
		//System.out.println("STAMPO ADDITIONAL RESOURCES...prima L'ATTIVAZIONE DEL MALUS");
		//System.out.println(additionalResources.toString());
		
		ResourceSet temporaryEffectResourceSet = new ResourceSet(additionalResources);
		
		//this part is used to check if there are excommunication Malus action on the effect
		
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
		//System.out.println("STAMPO ADDITIONAL RESOURCES...dopo L'ATTIVAZIONE DEL MALUS _NON DOVREBBE ESSER STATO MODIFICATO");
		//System.out.println(additionalResources.toString());
		
		//System.out.println("STAMPO TEMPORARY RESOURCESET...dopo L'ATTIVAZIONE DEL MALUS_ DOVREBBE ESSERE STATO MODIFICATO");
		//System.out.println(temporaryEffectResourceSet.toString());
	}
	
	
	public String toString() {
		return additionalResources.toString();
	}

}
