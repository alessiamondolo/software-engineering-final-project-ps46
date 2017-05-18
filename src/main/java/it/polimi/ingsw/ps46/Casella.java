package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Casella.
 * 
 * @author a.mondolo
 */
public class Casella {
	/**
	 * Description of the property idPosition.
	 */
	public Object idPosition;

	/**
	 * Description of the property requiredFamilyMemberValue.
	 */
	public Integer requiredFamilyMemberValue = Integer.valueOf(0);

	/**
	 * Description of the property avl.
	 */
	public Object avl;
	
	// Start of user code (user defined attributes for Casella)
	
	// End of user code

	/**
	 * The constructor.
	 */
	public Casella() {
		// Start of user code constructor for Casella)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Casella)

	// End of user code
	/**
	 * Returns idPosition.
	 * @return idPosition 
	 */
	public Object getIdPosition() {
		return this.idPosition;
	}

	/**
	 * Sets a value to attribute idPosition. 
	 * @param newIdPosition 
	 */
	public void setIdPosition(Object newIdPosition) {
		this.idPosition = newIdPosition;
	}

	/**
	 * Returns requiredFamilyMemberValue.
	 * @return requiredFamilyMemberValue 
	 */
	public Integer getRequiredFamilyMemberValue() {
		return this.requiredFamilyMemberValue;
	}

	/**
	 * Sets a value to attribute requiredFamilyMemberValue. 
	 * @param newRequiredFamilyMemberValue 
	 */
	public void setRequiredFamilyMemberValue(Integer newRequiredFamilyMemberValue) {
		this.requiredFamilyMemberValue = newRequiredFamilyMemberValue;
	}

	/**
	 * Returns avl.
	 * @return avl 
	 */
	public Object getAvl() {
		return this.avl;
	}

	/**
	 * Sets a value to attribute avl. 
	 * @param newAvl 
	 */
	public void setAvl(Object newAvl) {
		this.avl = newAvl;
	}

}
