package it.polimi.ingsw.ps46.server.resources;


/**
 * CouncilPrivilege is a type of Resource.
 * 
 * @author Alessia Mondolo
 */
public class CouncilPrivilege extends Resource {
	
	private static final long serialVersionUID = -1942721378066858491L;

	/**
	 * The constructor.
	 */
	public CouncilPrivilege(int quantity) {
		super("CouncilPrivilege", quantity);
	}
	
	public CouncilPrivilege(CouncilPrivilege beforeCounsilPrivilege, CouncilPrivilege afterCounsilPrivilege){
		super(beforeCounsilPrivilege, afterCounsilPrivilege);
	}
	
}