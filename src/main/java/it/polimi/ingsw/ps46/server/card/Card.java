package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.GameState;
import it.polimi.ingsw.ps46.server.resources.Resources;

/**
 * Card is an object that represent a single card of the game.
 * 
 * @author Alessia Mondolo
 */
public abstract class Card {
	
	private final String cardName;
	private final Effect immediateEffects;
	private final Effect permanentEffects;
	private final Resources cost;
	
	/**
	 * Build a new Card object.
	 */
	public Card(String cardName, Effect immediateEffects, Effect permanentEffects, Resources cost) {
		this.cardName = cardName;
		this.immediateEffects = immediateEffects;
		this.permanentEffects = permanentEffects;
		this.cost = cost;
	}
	
	
	public String getCardName() {
		return cardName;
	}

	public Effect getImmediateEffects() {
		return immediateEffects;
	}

	public Effect getPermanentEffects() {
		return permanentEffects;
	}

	public Resources getCost() {
		return cost;
	}
	
	/**
	 * Activates the immediate effects of the card and stores the permanent effect
	 * into the list of permanent effects of the player that uses the card.
	 * @param game
	 */
	public void use(GameState game) {
		//activates the immediate effects of the card
		immediateEffects.activateEffect(game);
		
		//stores the permanent effects in the list of permanent effects of the player
		// TODO
		//gameState.getCurrentPlayer().addPermanentEffect(permanentEffects);
	}
	
}
