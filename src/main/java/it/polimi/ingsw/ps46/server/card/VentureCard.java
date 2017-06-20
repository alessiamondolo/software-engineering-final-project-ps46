package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * Description of the Class VentureCard.
 * 
 * VentureCard is an object that represent a single venture card of the game.
 * Their immediate effects are of differents types of Effect and their permanent effects are of the type IncreaseResourcesEffect: 
 * they give points that will be added to the player's points at the end of the game.
 * 
 * @author Andrea.Masi
 */
public class VentureCard extends Card {
	boolean doubleCostChoice = false;
	ResourceSet costTwo;


	public VentureCard(String cardName, int cardEra, Effect immediateEffects, 
			IncreaseResourcesEffect permanentEffects, ResourceSet cost, 
			Boolean doubleCostChoice, ResourceSet costTwo, ResourceSet requiredResource) {
		super(cardName, cardEra, immediateEffects, permanentEffects, cost);
		
		this.doubleCostChoice = doubleCostChoice;
		this.costTwo = costTwo;
		
	}
	
	public boolean getdoubleCostChoice(){
		
		return getdoubleCostChoice();
	}
	
	public ResourceSet costTwo(){
		
		return costTwo;
	}

}
