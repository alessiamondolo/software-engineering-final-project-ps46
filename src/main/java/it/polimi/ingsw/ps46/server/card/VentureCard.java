package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * VentureCard is an object that represent a single venture card of the game.
 * Their immediate effects are of the type ?Effect and their permanent effects are of the type IncreaseResourcesEffect: 
 * they give points that will be added to the player's points at the end of the game.
 * 
 * @author Alessia Mondolo
 */
public class VentureCard extends Card {


	public VentureCard(String cardName,int cardEra,Effect immediateEffects, IncreaseResourcesEffect permanentEffects,
			ResourceSet cost) {
		super(cardName,cardEra, immediateEffects, permanentEffects, cost);
	}

}
