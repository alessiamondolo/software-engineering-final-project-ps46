package it.polimi.ingsw.ps46.server.resources;


/**
 * MilitaryPoints is a type of Resource.
 * 
 * @author Alessia Mondolo
 */
public class MilitaryPoints extends Resource {
	
	private static final long serialVersionUID = 8732852356545191886L;

	/**
	 * The constructor.
	 */
	public MilitaryPoints(int quantity) {
		super("MilitaryPoints", quantity);
	}
	
	public MilitaryPoints(MilitaryPoints beforeMilitaryPoints, MilitaryPoints afterMilitaryPoints){
		super(beforeMilitaryPoints, afterMilitaryPoints);
	}

}
