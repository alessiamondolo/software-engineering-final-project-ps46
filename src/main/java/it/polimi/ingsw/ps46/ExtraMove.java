package it.polimi.ingsw.ps46;

import java.util.HashSet;

/**
 * Description of ExtraMove.
 * 
 * @author a.mondolo
 */
public class ExtraMove implements Effect {
	/**
	 * Description of the property diceValue.
	 */
	public Dice diceValue = null;

	/**
	 * Description of the property dices.
	 */
	public HashSet<Dice> dices = new HashSet<Dice>();


	/**
	 * The constructor.
	 */
	public ExtraMove() {
		super();
	}

	/**
	 * Description of the method activateEffect.
	 */
	public void activateEffect() {
	}

	/**
	 * Returns diceValue.
	 * @return diceValue 
	 */
	public Dice getDiceValue() {
		return this.diceValue;
	}

	/**
	 * Sets a value to attribute diceValue. 
	 * @param newDiceValue 
	 */
	public void setDiceValue(Dice newDiceValue) {
		this.diceValue = newDiceValue;
	}

	/**
	 * Returns dices.
	 * @return dices 
	 */
	public HashSet<Dice> getDices() {
		return this.dices;
	}

}
