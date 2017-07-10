package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/** 
 * CharacterCard is an object that represent a single Character card of the game.
 * Their Immediate effect and Permanent effect are various.
 * 
 * @author Andrea.Masi
 */

public class CharacterCard extends Card {
	
	private static final long serialVersionUID = -7181349811096100892L;

	public CharacterCard(String cardName,int cardEra, Effect immediateEffects, Effect permanentEffects, 
		 ResourceSet cost) {
		super(cardName, cardEra, immediateEffects, permanentEffects, cost);
	}
	
	@Override
	public void collectCard(Game game) {
		if(immediateEffects != null)
			immediateEffects.activateEffect(game);
		game.getCurrentPlayer().getPersonalBoard().putCharacterCardInPlayerSet(this);
	}
	
	
	@Override
	public String toString() {
		
		if(getImmediateEffects() == null && getPermanentEffects() != null) {
			return "Card name: " + getCardName() + "\n" + 
					"Immediate effect: -\n" +
					"Permanent effect: " + getPermanentEffects() + "\n" +
					"Cost: " + getCost();
		}
		else if(getImmediateEffects() != null && getPermanentEffects() == null) {
			return "Card name: " + getCardName() + "\n" + 
					"Immediate effect: " + getImmediateEffects() + "\n" +
					"Permanent effect: -\n" +
					"Cost: " + getCost();
		}
		else {
			return "Card name: " + getCardName() + "\n" + 
					"Immediate effect: " + getImmediateEffects() + "\n" +
					"Permanent effect: " + getPermanentEffects() + "\n" +
					"Cost: " + getCost();
		}
	
	}
	
}
