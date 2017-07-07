package it.polimi.ingsw.ps46.server.resources;


/**
 * Wood is a type of Resource.
 * 
 * @author Alessia Mondolo
 */
public class Wood extends Resource {
	
	private static final long serialVersionUID = -4380517635662947467L;

	/**
	 * The constructor.
	 */
	public Wood(int quantity) {
		super("Wood", quantity);
	}

	public Wood(Wood beforeWood, Wood afterWood){
		super(beforeWood, afterWood);
	}
}
