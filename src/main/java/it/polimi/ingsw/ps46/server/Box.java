package it.polimi.ingsw.ps46.server;


/**
 * Description of Box.
 * 
 * @author a.mondolo
 */
public class Box {
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

	/**
	 * The constructor.
	 */
	public Box() {
		super();
	}

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
