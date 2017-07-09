package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * VentureCard is an object that represent a single venture card of the game.
 * Their immediate effects are of different types of Effect and their permanent effects are of the type IncreaseResourcesEffect: 
 * they give points that will be added to the player's points at the end of the game.
 * 
 * @author Andrea.Masi
 */
public class VentureCard extends Card {
	
	private static final long serialVersionUID = 1821348715129955902L;
	
	boolean doubleCostChoice = false;
	ResourceSet costTwo;
	ResourceSet requiredResource;

	public VentureCard(String cardName, int cardEra, Effect immediateEffects, 
			IncreaseResourcesEffect permanentEffects, ResourceSet cost, 
			Boolean doubleCostChoice, ResourceSet costTwo, ResourceSet requiredResource) {
		super(cardName, cardEra, immediateEffects, permanentEffects, cost);
		
		this.doubleCostChoice = doubleCostChoice;
		this.costTwo = costTwo;
		this.requiredResource = requiredResource;
	}
	
	
	@Override
	public void collectCard(Game game) {
		immediateEffects.activateEffect(game);
		game.getCurrentPlayer().getPersonalBoard().putVentureCardInPlayerSet(this);
	}
	
	
	public boolean getdoubleCostChoice(){
		
		return doubleCostChoice;
	}
	
	
	public ResourceSet getCostTwo(){
		
		return costTwo;
	}
	
	
	public ResourceSet getRequiredResource() {
		return requiredResource;
	}
	
	@Override
	public String toString() {
	
		if(!doubleCostChoice) {
			return "Card name: " + getCardName() + "\n" + 
					"Immediate effect: " + getImmediateEffects() + "\n" +
					"Permanent effect: " + getPermanentEffects() + "\n" +
					"Required resource: " + requiredResource + "\n" +
					"Cost: " + getCost();
		}
		else {
			return "Card name: " + getCardName() + "\n" + 
					"Immediate effect: " + getImmediateEffects() + "\n" +
					"Permanent effect: " + getPermanentEffects() + "\n" +
					"Permanent effect: " + getPermanentEffects() + "\n" +
					"Required resource: " + requiredResource + "\n" +
					"Cost (option 1): " + getCost() + "\n" +
					"Cost (option 2): " + costTwo;
		}
	}

}
