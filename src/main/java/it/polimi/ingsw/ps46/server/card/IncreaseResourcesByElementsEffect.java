package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * 
 * @author Andrea.Masi
 *
 */

public class IncreaseResourcesByElementsEffect extends IncreaseResourcesEffect {

	private String type;
	
	public IncreaseResourcesByElementsEffect(ResourceSet additionalResources, String type) {
		
		super(additionalResources);
		
		this.type = type;	
	}

	public void activateEffect(Game game) {
		int numberOfElements = 0;
		
		switch (type) {
		case "BuildingCards":
			numberOfElements = game.getCurrentPlayer().getBuildingDeck().size();
			break;
			
		case "VentureCards":
			numberOfElements = game.getCurrentPlayer().getVentureDeck().size();
			break;
		
		case "CharactersCards":
			numberOfElements = game.getCurrentPlayer().getCharacterDeck().size();
			break;
			
		case "TerritoryCards":
			numberOfElements = game.getCurrentPlayer().getTerritoryDeck().size();
			break;
			
		case "MilitaryPoints":
			numberOfElements = game.getCurrentPlayer().getPlayerResourceSet().getResourcesMap().get(type).getQuantity();
			numberOfElements = numberOfElements / 2 ;  // come gestire sto numero?
			break;
			
		default:
			break;
		}
	
			ResourceSet resourceSet = getAdditionalResources();
			for(String key : resourceSet.getResourcesMap().keySet()) 
			{
				int quantity = resourceSet.getResourcesMap().get(key).getQuantity();
				quantity = quantity * numberOfElements;
				resourceSet.getResourcesMap().get(key).setQuantity(quantity);
			}
			
			game.getCurrentPlayer().getPlayerResourceSet().add(resourceSet);
	}
	
	
	
	public String getType() {
		return type;
	}

	
	
	


}
