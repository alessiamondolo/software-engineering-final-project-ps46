package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

import java.util.HashSet;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of PermanentEffect.
 * 
 * @author a.mondolo
 */
public class PermanentEffect implements Effect {
	/**
	 * Description of the property giveResources.
	 */
	public Resources giveResources = null;

	/**
	 * Description of the property resourcess.
	 */
	public HashSet<Resources> resourcess = new HashSet<Resources>();

	/**
	 * Description of the property recievedResources.
	 */
	public Resources recievedResources = null;

	// Start of user code (user defined attributes for PermanentEffect)

	// End of user code

	/**
	 * The constructor.
	 */
	public PermanentEffect() {
		// Start of user code constructor for PermanentEffect)
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

	// Start of user code (user defined methods for PermanentEffect)

	// End of user code
	/**
	 * Returns giveResources.
	 * @return giveResources 
	 */
	public Resources getGiveResources() {
		return this.giveResources;
	}

	/**
	 * Sets a value to attribute giveResources. 
	 * @param newGiveResources 
	 */
	public void setGiveResources(Resources newGiveResources) {
		this.giveResources = newGiveResources;
	}

	/**
	 * Returns resourcess.
	 * @return resourcess 
	 */
	public HashSet<Resources> getResourcess() {
		return this.resourcess;
	}

	/**
	 * Returns recievedResources.
	 * @return recievedResources 
	 */
	public Resources getRecievedResources() {
		return this.recievedResources;
	}

	/**
	 * Sets a value to attribute recievedResources. 
	 * @param newRecievedResources 
	 */
	public void setRecievedResources(Resources newRecievedResources) {
		this.recievedResources = newRecievedResources;
	}

}
