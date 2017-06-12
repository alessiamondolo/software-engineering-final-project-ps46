package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * 
 * @author Andrea.Masi
 *
 */

public class IncreaseResourcesByCardsEffect extends IncreaseResourcesEffect {

	private String type;
	
	public IncreaseResourcesByCardsEffect(ResourceSet additionalResources, String type) {
		
		super(additionalResources);
		
		this.type = type;	
	}

	public void activateEffect(Game game) {
		int numberOfCards = 0;
		
		switch (type) {
		case "BuildingCards":
			numberOfCards = game.getCurrentPlayer().getBuildingDeck().size();
			break;
			
		case "VentureCards":
			numberOfCards = game.getCurrentPlayer().getVentureDeck().size();
			break;
		
		case "CharactersCards":
			numberOfCards = game.getCurrentPlayer().getCharacterDeck().size();
			break;
			
		case "TerritoryCards":
			numberOfCards = game.getCurrentPlayer().getTerritoryDeck().size();
			break;
			
		default:
			break;
		}
		ResourceSet resourceSet = getAdditionalResources();
		for(String key : resourceSet.getResourcesMap().keySet()) {
			int quantity = resourceSet.getResourcesMap().get(key).getQuantity();
			quantity = quantity * numberOfCards;
			resourceSet.getResourcesMap().get(key).setQuantity(quantity);
		}
			
			
		game.getCurrentPlayer().getPlayerResourceSet().add(resourceSet);
	}
	
	
	
	public String getType() {
		return type;
	}

	
	
	


}
