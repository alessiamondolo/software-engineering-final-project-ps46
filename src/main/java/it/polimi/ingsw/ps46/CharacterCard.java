package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of CharacterCard.
 * 
 * @author a.mondolo
 */
public class CharacterCard extends Card {
	/**
	 * Description of the property requiredMoney.
	 */
	public Integer requiredMoney = Integer.valueOf(0);

	// Start of user code (user defined attributes for CharacterCard)

	// End of user code

	/**
	 * The constructor.
	 */
	public CharacterCard() {
		// Start of user code constructor for CharacterCard)
		super();
		// End of user code
	}

	/**
	 * Description of the method acquireCard.
	 */
	public void acquireCard() {
		// Start of user code for method acquireCard
		// End of user code
	}

	// Start of user code (user defined methods for CharacterCard)

	// End of user code
	/**
	 * Returns requiredMoney.
	 * @return requiredMoney 
	 */
	public Integer getRequiredMoney() {
		return this.requiredMoney;
	}

	/**
	 * Sets a value to attribute requiredMoney. 
	 * @param newRequiredMoney 
	 */
	public void setRequiredMoney(Integer newRequiredMoney) {
		this.requiredMoney = newRequiredMoney;
	}

}
