package it.polimi.ingsw.ps46.server.resources;


/**
 * FaithPoints is a type of Resource.
 * 
 * @author Alessia Mondolo
 */
public class FaithPoints extends Resource {
	
	private static final long serialVersionUID = -2214115230289013436L;

	/**
	 * The constructor.
	 */
	public FaithPoints(int quantity) {
		super("FaithPoints", quantity);
	}
	
	public FaithPoints(FaithPoints beforeFaithPoints, FaithPoints afterFaithPoints){
		super(beforeFaithPoints, afterFaithPoints);
	}
	
}
