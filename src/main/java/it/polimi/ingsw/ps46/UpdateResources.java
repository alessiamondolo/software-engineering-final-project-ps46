package it.polimi.ingsw.ps46;

import java.util.HashSet;


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


	/**
	 * The constructor.
	 */
	public UpdateResources() {
		super();
	}

	/**
	 * Description of the method activateEffect.
	 */
	public void activateEffect() {
	}

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
