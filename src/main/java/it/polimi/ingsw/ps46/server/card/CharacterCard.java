package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/** 
 * CharacterCard is an object that represent a single Character card of the game.
 * Their Immediate effect and Permanent effect are various.
 * 
 * @author Andrea.Masi
 */

public class CharacterCard extends Card {
		

	public CharacterCard(String cardName,int cardEra, Effect immediateEffects, Effect permanentEffects, 
		 ResourceSet cost) {
		super(cardName, cardEra, immediateEffects, permanentEffects, cost);
	}
}
