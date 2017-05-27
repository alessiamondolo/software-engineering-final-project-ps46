package it.polimi.ingsw.ps46.server;

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
	
}
