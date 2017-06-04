package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * TerrytoryCard is an object that represent a single territory card of the game.
 * Territory cards have no cost, their immediate effects are of the type IncreaseResourcesEffect
 * and their permanent effects are harvest effects of the type IncreaseResourcesEffect.
 * 
 * @author Alessia Mondolo
 */
public class TerritoryCard extends Card {

	//Territory cards don't have any cost
	//private static final ResourceSet cost = null;
	//private Dice harvestValue;

	public TerritoryCard(String cardName, IncreaseResourcesEffect immediateEffects, IncreaseResourcesEffect permanentEffects,
			ResourceSet cost) {
		super(cardName, immediateEffects, permanentEffects, cost);
		//this.harvestValue = harvestValue;
	}
	
	/*
	public Dice getHarvestValue() {
		return harvestValue;
	}*/
	
}
