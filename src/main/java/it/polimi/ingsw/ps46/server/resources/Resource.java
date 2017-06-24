package it.polimi.ingsw.ps46.server.resources;

import java.io.Serializable;

/**
 * Resource can be used to represent any type of resource.
 * 
 * @author Alessia Mondolo 
 */
public abstract class Resource implements Serializable {
	
	private static final long serialVersionUID = -2185596359494356372L;
	
	/**
	 * Stores the id  that identifies the type of resource.
	 */
	private String id;
	/**
	 * Stores the quantity of the specific resource.
	 */
	private int quantity;
	
	/**
	 * The constructor.
	 */
	public Resource(String id, int quantity){
		this.id = id;
		this.quantity = quantity;
	}
	
	/**
	 * Returns id.
	 * @return id 
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns quantity.
	 * @return quantity 
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets quantity.
	 * @return quantity 
	 */
	public void setQuantity(int newQuantity) { //errore se è negativo
		quantity = newQuantity;
	}
	
	/**
	 * Increases the value of quantity by additionalQuantity. 
	 * @param additionalQuantity 
	 */
	public void add(Resource moreResource) {
		quantity += moreResource.getQuantity();
	}
	
	/**
	 * Decreases the value of quantity by lessQuantity, if there are enough resources.
	 * Otherwise, throws and exception. 
	 * @param lessQuantity 
	 */
	public void sub(Resource lessResource) {
		if(greaterOrEqual(lessResource))
			quantity -= lessResource.getQuantity();
		//else 
		//	throw new Exception()
	}

	public boolean greaterOrEqual(Resource resource) {
		return (this.quantity >= resource.getQuantity());
	}
	
	@Override
	public String toString() {
		return id + ": " + quantity;
	}
	
}
