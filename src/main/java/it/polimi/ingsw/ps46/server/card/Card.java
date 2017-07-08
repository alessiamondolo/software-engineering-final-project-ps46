package it.polimi.ingsw.ps46.server.card;

import java.io.Serializable;
import java.util.Observable;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * Card is an object that represent a single card of the game.
 * 
 * @author Alessia Mondolo
 */
public abstract class Card extends Observable implements Serializable {
	
	private static final long serialVersionUID = 3588501054310706492L;
	
	private final String cardName;
	private final int cardEra;
	protected final Effect immediateEffects;
	private final Effect permanentEffects;
	private final ResourceSet cost;
	 
	/**
	 * 
	 * Build a new Card object.
	 */
	public Card(String cardName,int cardEra, Effect immediateEffects, Effect permanentEffects, ResourceSet cost) {
		this.cardName = cardName;
		this.cardEra = cardEra;
		this.immediateEffects = immediateEffects;
		this.permanentEffects = permanentEffects;
		this.cost = cost;
	}
	
	
	public String getCardName() {
		return cardName;
	}

	public int getCardEra() {
		return cardEra;
	}
	
	public Effect getImmediateEffects() {
		return immediateEffects;
	}

	public Effect getPermanentEffects() {
		return permanentEffects;
	}

	public ResourceSet getCost() {
		return cost;
	}
	
	/**
	 * Activates the immediate effects of the card and stores the permanent effect
	 * into the list of permanent effects of the player that uses the card.
	 * @param game
	 */
	public void use(Game game) {
		if(permanentEffects != null)
			permanentEffects.activateEffect(game);
	
	}
	/**
	 * This method is used to collect this card object (implemented into the different kind of cards @overriding)
	 * 
	 * @param game
	 */
	public void collectCard(Game game) {}
	
	@Override
	public String toString() {
		if(permanentEffects != null) {
		return "Card name: " + cardName + "\n" + 
				"Immediate effect: " + immediateEffects + "\n" +
				"Permanent effect: " + permanentEffects + "\n" +
				"Cost: " + cost;
		}
		else
			return "Card name: " + cardName + "\n" + 
			"Immediate effect: " + immediateEffects + "\n" +
			"Permanent effect: - \n" +
			"Cost: " + cost;
	}

}
