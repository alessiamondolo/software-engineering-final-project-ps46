package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * BuildingCard is an object that represent a single building card of the game.
 * Their immediate effects are of the type IncreaseResourcesEffect and their permanent effects 
 * are production effects of the type ExchangeResourcesEffect or IncreaseResourcesEffect.
 * 
 * @author Alessia Mondolo
 */
public class BuildingCard extends Card {
	
	private Dice productionValue;

	public BuildingCard(String cardName, IncreaseResourcesEffect immediateEffects, Effect permanentEffects,
			ResourceSet cost, Dice productionValue) {
		super(cardName, immediateEffects, permanentEffects, cost);
		this.productionValue = productionValue;
	}

	public Dice getProductionValue() {
		return productionValue;
	}

}
