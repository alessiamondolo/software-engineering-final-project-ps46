package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * BuildingCard is an object that represent a single building card of the game.
 * Their immediate effects are of the type IncreaseResourcesEffect and their permanent effects 
 * are production effects of the type ExchangeResourcesEffect or IncreaseResourcesEffect.
 * 
 * @author Alessia Mondolo, Andrea.Masi
 */
public class BuildingCard extends Card {
	
	private Dice productionValue;
	private ExchageResourcesEffect permanentEffectsTwo;
	private IncreaseResourcesByCardsEffect CountingCardsForResources;
	private Boolean doubleChoise = false;

	public BuildingCard(String cardName, int cardEra, IncreaseResourcesEffect immediateEffects, Boolean doubleChoise, 
			ExchageResourcesEffect permanentEffects, ExchageResourcesEffect permanentEffectsTwo, 
			IncreaseResourcesByCardsEffect CountingCardsForResources, ResourceSet cost, Dice productionValue)
	{
		super(cardName,cardEra, immediateEffects, permanentEffects, cost);
		this.productionValue = productionValue;
		this.doubleChoise = doubleChoise ;
		this.permanentEffectsTwo = permanentEffectsTwo;
		this.CountingCardsForResources = CountingCardsForResources;
	}

	public Dice getProductionValue() {
		return productionValue;
	}

	public ExchageResourcesEffect getPermanentEffectsTwo() {
		return permanentEffectsTwo;
	}


	public IncreaseResourcesByCardsEffect getCountingCardsForResources() {
		return CountingCardsForResources;
	}


	public Boolean getDoubleChoise() {
		return doubleChoise;
	}

}
