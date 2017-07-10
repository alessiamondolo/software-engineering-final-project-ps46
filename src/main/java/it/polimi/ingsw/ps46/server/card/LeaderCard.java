package it.polimi.ingsw.ps46.server.card;

import java.util.Map;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

public class LeaderCard {
	
	
	private String cardName;
	private LinkedHashMap <String, Integer> requiredCards; 
	private ResourceSet requiredResources;
	private Effect leaderEffect;
	private boolean isActive = false;
	
	
	/*
	 * EFFETTI DA IMPLEMENTARE:
	 * Ludovico Ariosto: putFamilyMemberEverywhereEffect DONE
	 * Filippo Brunelleschi: noFeeForTowerEffect DONE
	 * Ludovico il Moro: setValueToAllColoredFamilyMemberEffect ---> DONE creato un effetto che viene attivato in gameController
	 * Federico da Montefeltro: setValueToOneColoredFamilyMemberEffect ---> +O- TODO manca la scelta del giocatore
	 * Lorenzo de Medici: copyOneLeaderCardEffectOnceEffect --->TODO scelta del giocatore
	 * Cesare Borgia: NoRequiredMilitaryPointsEffect DONE
	 * Santa Rita: ResourcesX2Effect DONE
	 * Pico della Mirandola: CardsDiscountedEffect -3 Money DONE
	 * ANTONIOOOOOOOOOOOOOOOOOOOO
	 * Sisto IV da implementare con il rapporto del vaticano DONE
	 */
	
	public LeaderCard (String name, Effect leaderEffect, LinkedHashMap<String,Integer> requiredCards, ResourceSet requiredResources) {
	
		this.cardName = name;
		this.leaderEffect = leaderEffect;
		this.requiredCards = requiredCards;
		this.requiredResources = requiredResources;
		
	}
	
	
	
	public boolean isActivable(Game game) {
		if(!requiredCards.isEmpty()){
			if (cardName != "Lucrezia Borgia"){
				for (String string : requiredCards.keySet()) {
					
					if (string == "TerritoryCards"){
						if(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().size() < requiredCards.get(string)){
							return false;
						}
					}
					
					if (string == "BuldingCards"){
						if(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().size() < requiredCards.get(string)){
							return false;
						}
					}
					
					if (string == "VentureCards"){
						if(game.getCurrentPlayer().getPersonalBoard().getVentureDeck().size() < requiredCards.get(string)){
							return false;
						}
					}
					
					if (string == "CharacterCards"){
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
					
					if (string == "TerritoryCards"){
						if(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().size() == requiredCards.get(string)){
							cardOrChecked = true;
							break;
						}
					}
					
					if (string == "BuldingCards"){
						if(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().size() == requiredCards.get(string)){
							cardOrChecked = true;
							break;
						}
					}
					
					if (string == "VentureCards"){
						if(game.getCurrentPlayer().getPersonalBoard().getVentureDeck().size() == requiredCards.get(string)){
							cardOrChecked = true;
							break;
						}
					}
					
					if (string == "CharacterCards"){
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

}