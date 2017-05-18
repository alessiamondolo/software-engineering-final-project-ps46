package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

import java.util.HashSet;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Observable.
 * 
 * @author a.mondolo
 */
public abstract class Observable {
	/**
	 * Description of the property observers.
	 */
	public HashSet<Observer> observers = new HashSet<Observer>();

	// Start of user code (user defined attributes for Observable)

	// End of user code

	/**
	 * The constructor.
	 */
	public Observable() {
		// Start of user code constructor for Observable)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Observable)

	// End of user code
	/**
	 * Returns observers.
	 * @return observers 
	 */
	public HashSet<Observer> getObservers() {
		return this.observers;
	}

}
