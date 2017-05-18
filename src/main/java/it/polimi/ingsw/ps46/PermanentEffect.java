package it.polimi.ingsw.ps46;

import java.util.HashSet;


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

	/**
	 * The constructor.
	 */
	public PermanentEffect() {
		super();
	}

	/**
	 * Description of the method activateEffect.
	 */
	public void activateEffect() {
	}

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
