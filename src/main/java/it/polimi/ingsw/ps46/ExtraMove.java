package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

import java.util.HashSet;

// Start of user code (user defined imports)

// End of user code

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

	// Start of user code (user defined attributes for ExtraMove)

	// End of user code

	/**
	 * The constructor.
	 */
	public ExtraMove() {
		// Start of user code constructor for ExtraMove)
		super();
		// End of user code
	}

	/**
	 * Description of the method activateEffect.
	 */
	public void activateEffect() {
		// Start of user code for method activateEffect
		// End of user code
	}

	// Start of user code (user defined methods for ExtraMove)

	// End of user code
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
