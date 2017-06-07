package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;


public class TerritoryCard extends Card {

	//da mettere a zero	
	private static final ResourceSet cost = null;

	public TerritoryCard(String cardName, IncreaseResources immediateEffects, Effect permanentEffects) {
		super(cardName, immediateEffects, permanentEffects, cost);
		// TODO Auto-generated constructor stub
	}
	
}
