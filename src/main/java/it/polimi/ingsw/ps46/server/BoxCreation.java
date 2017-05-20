package it.polimi.ingsw.ps46.server;

/**
 * Description of BoxCreation.
 * 
 * @author a.mondolo
 */
public class BoxCreation extends Box {
	/**
	 * Description of the property type.
	 */
	public Object type;

	/**
	 * Description of the property malus.
	 */
	public Object malus;
	
	/**
	 * The constructor.
	 */
	public BoxCreation() {
		super();
	}

	/**
	 * Description of the method produce.
	 */
	public void produce() {
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

	/**
	 * Returns malus.
	 * @return malus 
	 */
	public Object getMalus() {
		return this.malus;
	}

	/**
	 * Sets a value to attribute malus. 
	 * @param newMalus 
	 */
	public void setMalus(Object newMalus) {
		this.malus = newMalus;
	}

}
