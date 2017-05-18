package it.polimi.ingsw.ps46;

import java.util.HashSet;


/**
 * Description of Card.
 * 
 * @author a.mondolo
 */
public class Card {
	/**
	 * Description of the property effects.
	 */
	public HashSet<Effect> effects = new HashSet<Effect>();

	/**
	 * Description of the property idCard.
	 */
	public Integer idCard = Integer.valueOf(0);

	/**
	 * Description of the property period.
	 */
	public Integer period = Integer.valueOf(0);

	/**
	 * Description of the property type.
	 */
	public Object type;

	/**
	 * The constructor.
	 */
	public Card() {
		super();
	}

	/**
	 * Description of the method AcquireResources.
	 */
	public void AcquireResources() {
	}

	/**
	 * Returns effects.
	 * @return effects 
	 */
	public HashSet<Effect> getEffects() {
		return this.effects;
	}

	/**
	 * Returns idCard.
	 * @return idCard 
	 */
	public Integer getIdCard() {
		return this.idCard;
	}

	/**
	 * Sets a value to attribute idCard. 
	 * @param newIdCard 
	 */
	public void setIdCard(Integer newIdCard) {
		this.idCard = newIdCard;
	}

	/**
	 * Returns period.
	 * @return period 
	 */
	public Integer getPeriod() {
		return this.period;
	}

	/**
	 * Sets a value to attribute period. 
	 * @param newPeriod 
	 */
	public void setPeriod(Integer newPeriod) {
		this.period = newPeriod;
	}

	/**
	 * Returns type.
	 * @return type 
	 */
	public Object getType() {
		return this.type;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setType(Object newType) {
		this.type = newType;
	}

}
