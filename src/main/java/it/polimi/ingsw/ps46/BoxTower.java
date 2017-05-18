package it.polimi.ingsw.ps46;


/**
 * Description of BoxTower.
 * 
 * @author a.mondolo
 */
public class BoxTower extends Box {
	/**
	 * Description of the property card.
	 */
	public Object card;

	/**
	 * Description of the property bonus.
	 */
	public Object bonus;

	/**
	 * Description of the property empty.
	 */
	public Object empty;

	/**
	 * The constructor.
	 */
	public BoxTower() {
		super();
	}

	/**
	 * Description of the method updateIdCard.
	 */
	public void updateIdCard() {
	}

	/**
	 * Description of the method updateBonusFromFile.
	 */
	public void updateBonusFromFile() {
	}

	/**
	 * Returns card.
	 * @return card 
	 */
	public Object getCard() {
		return this.card;
	}

	/**
	 * Sets a value to attribute card. 
	 * @param newCard 
	 */
	public void setCard(Object newCard) {
		this.card = newCard;
	}

	/**
	 * Returns bonus.
	 * @return bonus 
	 */
	public Object getBonus() {
		return this.bonus;
	}

	/**
	 * Sets a value to attribute bonus. 
	 * @param newBonus 
	 */
	public void setBonus(Object newBonus) {
		this.bonus = newBonus;
	}

	/**
	 * Returns empty.
	 * @return empty 
	 */
	public Object getEmpty() {
		return this.empty;
	}

	/**
	 * Sets a value to attribute empty. 
	 * @param newEmpty 
	 */
	public void setEmpty(Object newEmpty) {
		this.empty = newEmpty;
	}

}
