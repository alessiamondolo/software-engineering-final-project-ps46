package it.polimi.ingsw.ps46;


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
	

	/**
	 * The constructor.
	 */
	public CharacterCard() {
		super();
	}

	/**
	 * Description of the method acquireCard.
	 */
	public void acquireCard() {
	}

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
