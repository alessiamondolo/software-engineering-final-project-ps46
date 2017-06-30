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
		immediateEffects.activateEffect(game);
		game.getCurrentPlayer().getPersonalBoard().putCharacterCardInPlayerSet(this);
	}
}
