package it.polimi.ingsw.ps46.server.resources;


/**
 * VictoryPoints is a type of Resource.
 * 
 * @author Alessia Mondolo
 */
public class VictoryPoints extends Resource {
	
	private static final long serialVersionUID = 6944583845767205278L;

	/**
	 * The constructor.
	 */
	public VictoryPoints(int quantity) {
		super("VictoryPoints", quantity);
	}
	
	public VictoryPoints(VictoryPoints beforeVictoryPoints, VictoryPoints afterVictoryPoints){
		super(beforeVictoryPoints, afterVictoryPoints);
	}
}
