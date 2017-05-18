package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

import java.util.HashSet;

// Start of user code (user defined imports)

// End of user code

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

	// Start of user code (user defined attributes for FamilyMember)

	// End of user code

	/**
	 * The constructor.
	 */
	public FamilyMember() {
		// Start of user code constructor for FamilyMember)
		super();
		// End of user code
	}

	/**
	 * Description of the method updatePosition.
	 */
	public void updatePosition() {
		// Start of user code for method updatePosition
		// End of user code
	}

	/**
	 * Description of the method updateValue.
	 */
	public void updateValue() {
		// Start of user code for method updateValue
		// End of user code
	}

	// Start of user code (user defined methods for FamilyMember)

	// End of user code
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
