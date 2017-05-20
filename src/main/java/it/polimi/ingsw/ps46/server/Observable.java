package it.polimi.ingsw.ps46.server;

import java.util.HashSet;


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


	/**
	 * The constructor.
	 */
	public Observable() {
		super();
	}

	/**
	 * Returns observers.
	 * @return observers 
	 */
	public HashSet<Observer> getObservers() {
		return this.observers;
	}

}
