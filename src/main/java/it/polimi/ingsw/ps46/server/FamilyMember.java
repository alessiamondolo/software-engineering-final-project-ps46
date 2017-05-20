package it.polimi.ingsw.ps46.server;

import java.util.HashSet;


/**
 * Description of FamilyMember.
 * 
 * @author a.mondolo
 */
public class FamilyMember {
	/**
	 * Description of the property position.
	 */
	public Integer position = Integer.valueOf(0);

	/**
	 * Description of the property value.
	 */
	public Dice value = null;

	/**
	 * Description of the property dices.
	 */
	public HashSet<Dice> dices = new HashSet<Dice>();


	/**
	 * The constructor.
	 */
	public FamilyMember() {
		super();
	}

	/**
	 * Description of the method updatePosition.
	 */
	public void updatePosition() {
	}

	/**
	 * Description of the method updateValue.
	 */
	public void updateValue() {
	}

	/**
	 * Returns position.
	 * @return position 
	 */
	public Integer getPosition() {
		return this.position;
	}

	/**
	 * Sets a value to attribute position. 
	 * @param newPosition 
	 */
	public void setPosition(Integer newPosition) {
		this.position = newPosition;
	}

	/**
	 * Returns value.
	 * @return value 
	 */
	public Dice getValue() {
		return this.value;
	}

	/**
	 * Sets a value to attribute value. 
	 * @param newValue 
	 */
	public void setValue(Dice newValue) {
		this.value = newValue;
	}

	/**
	 * Returns dices.
	 * @return dices 
	 */
	public HashSet<Dice> getDices() {
		return this.dices;
	}

}
