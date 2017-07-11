package it.polimi.ingsw.ps46.server.card;

import java.util.Map;
import java.io.Serializable;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

public class LeaderCard implements Serializable {
	
	private static final long serialVersionUID = -2466278746354123237L;
	private String cardName;
	private LinkedHashMap <String, Integer> requiredCards; 
	private ResourceSet requiredResources;
	private Effect leaderEffect;
	private boolean isActive = false;
	private boolean isPermanent;
	
	public LeaderCard (String name, Effect leaderEffect, LinkedHashMap<String,Integer> requiredCards, ResourceSet requiredResources, boolean isPermanent) {
	
		this.cardName = name;
		this.leaderEffect = leaderEffect;
		this.requiredCards = requiredCards;
		this.requiredResources = requiredResources;
		this.isPermanent = isPermanent;
		
	}
	
	
	
	public boolean isActivable(Game game) {
		if(!requiredCards.isEmpty()){
			if (!(cardName.equals( "Lucrezia Borgia"))){
				for (String string : requiredCards.keySet()) {
					
					if (string.equals( "TerritoryCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().size() < requiredCards.get(string)){
							return false;
						}
					}
					

					if (string.equals("BuldingCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().size() < requiredCards.get(string)){
							return false;
						}
					}
					

					if (string.equals("VentureCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getVentureDeck().size() < requiredCards.get(string)){
							return false;
						}
					}
					

					if (string.equals("CharacterCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getCharacterDeck().size() < requiredCards.get(string)){
							return false;
						}	
					}
				}	
			}
			else
			{
				boolean cardOrChecked = false;
				for (String string : requiredCards.keySet()) {

					if (string.equals("TerritoryCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().size() == requiredCards.get(string)){
							cardOrChecked = true;
							break;
						}
					}
					

					if (string.equals("BuldingCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().size() == requiredCards.get(string)){
							cardOrChecked = true;
							break;
						}
					}
					

					if (string.equals("VentureCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getVentureDeck().size() == requiredCards.get(string)){
							cardOrChecked = true;
							break;
						}
					}
					

					if (string.equals("CharacterCards")){
						if(game.getCurrentPlayer().getPersonalBoard().getCharacterDeck().size() == requiredCards.get(string)){
							cardOrChecked = true;
							break;
						}	
					}
				}
				if (cardOrChecked == false)
					return false;
			}
		}
		if(!requiredResources.getResourcesMap().isEmpty()){
			if (!game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().greaterOrEqual(requiredResources)){
			return false;
			}
		}
		if(isActive == false)
			return true;
		else
			return false;
	}

	public void use(Game game){
		if (leaderEffect != null)
			leaderEffect.activateEffect(game);
		setAsActive();
	}

	

	public String getCardName() {
		return cardName;
	}

	public Map<String,Integer> getRequiredCards() {
		return requiredCards;
	}

	public ResourceSet getRequiredResources() {
		return requiredResources;
	}


	public boolean isActive() {
		return isActive;
	}

	public void setAsActive(){
		isActive = true;
	}
	public void setAsInactive(){
		isActive = false;
	}


	public Effect getLeaderEffect() {
		return leaderEffect;
	}



	public boolean isPermanent() {
		return isPermanent;
	}
	
	@Override
	public String toString() {
		return "Name : " + cardName + "\n" +
				"Required cards: " + requiredCards + "\n" +
				"Required resources: " + requiredResources + requiredCards + "\n" +
				"Effect: " + leaderEffect + "\n" +
				"Is active: " + isActive + "\n" +
				"Is permanent: " + isPermanent;
	}

}