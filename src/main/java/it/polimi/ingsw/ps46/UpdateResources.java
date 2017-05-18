package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

import java.util.HashSet;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of UpdateResources.
 * 
 * @author a.mondolo
 */
public class UpdateResources implements Effect {
	/**
	 * Description of the property newResources.
	 */
	public Resources newResources = null;

	/**
	 * Description of the property resourcess.
	 */
	public HashSet<Resources> resourcess = new HashSet<Resources>();

	// Start of user code (user defined attributes for UpdateResources)

	// End of user code

	/**
	 * The constructor.
	 */
	public UpdateResources() {
		// Start of user code constructor for UpdateResources)
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

	// Start of user code (user defined methods for UpdateResources)

	// End of user code
	/**
	 * Returns newResources.
	 * @return newResources 
	 */
	public Resources getNewResources() {
		return this.newResources;
	}

	/**
	 * Sets a value to attribute newResources. 
	 * @param newNewResources 
	 */
	public void setNewResources(Resources newNewResources) {
		this.newResources = newNewResources;
	}

	/**
	 * Returns resourcess.
	 * @return resourcess 
	 */
	public HashSet<Resources> getResourcess() {
		return this.resourcess;
	}

}
