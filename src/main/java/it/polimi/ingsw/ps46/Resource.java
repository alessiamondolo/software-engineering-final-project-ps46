package it.polimi.ingsw.ps46;


/**
 * Description of Resource.
 * 
 * @author a.mondolo
 */
public abstract class Resource {
	
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
	 * Increases the value of quantity by additionalQuantity. 
	 * @param additionalQuantity 
	 */
	public void addQuantity(int additionalQuantity) {
		quantity += additionalQuantity;
	}
	
	/**
	 * Decreases the value of quantity by lessQuantity, if there are enough resources.
	 * Otherwise, throws and exception. 
	 * @param lessQuantity 
	 */
	public void removeQuantity(int lessQuantity) {
		if(quantity >= lessQuantity)
			quantity -= lessQuantity;
		//else 
		//	throw new Exception()
	}
	
}
