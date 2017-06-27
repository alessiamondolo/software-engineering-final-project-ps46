package it.polimi.ingsw.ps46.server.resources;


/**
 * Resource can be used to represent any type of resource.
 * 
 * @author Alessia Mondolo 
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
	 * Sets quantity.
	 * @return quantity 
	 */
	public void setQuantity(int newQuantity) { 
		quantity = newQuantity;
		
	/*	if (quantity < 0){
			throw Exception;	//errore se è negativo
		}
	*/
	}
	
	/**
	 * Increases the value of quantity by additionalQuantity. 
	 * @param additionalQuantity 
	 */
	public void add(Resource moreResource) {
		quantity += moreResource.getQuantity();
	}
	
	/**
	 * Decreases the value of quantity by lessQuantity, if there are enough resources to do the sub operation return true;
	 * Otherwise, return false.
	 * 
	 * @param lessQuantity 
	 * @return boolean 
	 */
	public boolean sub(Resource lessResource) {
		if(greaterOrEqual(lessResource)){ 
			quantity -= lessResource.getQuantity();
			return true;
		}
		else 
			return false;
	}

	public boolean greaterOrEqual(Resource resource) {
		return (this.quantity >= resource.getQuantity());
	}
	
	public boolean minor(Resource resource) {
		return (this.quantity < resource.getQuantity());
		
	}
	
	@Override
	public String toString() {
		return id + ": " + quantity;
	}
	
}
