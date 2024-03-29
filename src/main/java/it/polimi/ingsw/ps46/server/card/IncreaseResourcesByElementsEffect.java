package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * This Class extends IncreaseResourcesEffect. 
 * Its use to counting the number of a specific kind of cards or military points of the current player 
 * and give the same number of a resource. 
 *
 * @author Andrea.Masi
 */

public class IncreaseResourcesByElementsEffect extends IncreaseResourcesEffect {

	private static final long serialVersionUID = -1009062813515858649L;
	
	private String type;
	
	public IncreaseResourcesByElementsEffect(ResourceSet additionalResources, String type) {
		
		super(additionalResources);
		
		this.type = type;	
	}

	
	/**
	 * This method adds the additional resources to the resources of the current player,
	 * who is the one that activated the card with the effect IncreaseResourcesByElementsEffect.
	 */
	@Override
	public void activateEffect(Game game) {
		int numberOfElements = 0;
		
		switch (type) {
		case "BuildingCards":
			numberOfElements = game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().size();
			break;
			
		case "VentureCards":
			numberOfElements = game.getCurrentPlayer().getPersonalBoard().getVentureDeck().size();
			break;
		
		case "CharactersCards":
			numberOfElements = game.getCurrentPlayer().getPersonalBoard().getCharacterDeck().size();
			break;
			
		case "TerritoryCards":
			numberOfElements = game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().size();
			break;
			
		case "MilitaryPoints":
			numberOfElements = game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(type).getQuantity();
			numberOfElements /= 2 ;  // come gestire sto numero?

			break;
			
		default:
			break;
		}
		//I'm using a copy of the resourceSet of the Card to avoid to change them values.
		ResourceSet temporaryEffectResourceSet = new ResourceSet(getAdditionalResources());
		
		for(String key : temporaryEffectResourceSet.getResourcesMap().keySet()) 
			{
				int quantity = temporaryEffectResourceSet.getResourcesMap().get(key).getQuantity();
				quantity = quantity * numberOfElements;
				temporaryEffectResourceSet.getResourcesMap().get(key).setQuantity(quantity);
			}

		if (game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources() != null) {
			temporaryEffectResourceSet.sub(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources());
			}

		game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(temporaryEffectResourceSet);
	}
	
	
	public String getType() {
		return type;
	}
	
	
	public String toString() {
		return "Gained resources: " + additionalResources.toString() +
				". Multiply gained resources by number of " + type;
	}

}
