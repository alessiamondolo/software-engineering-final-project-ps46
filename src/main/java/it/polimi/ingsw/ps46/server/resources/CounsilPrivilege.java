package it.polimi.ingsw.ps46.server.resources;


/**
 * CounsilPrivilege is a type of Resource.
 * 
 * @author Alessia Mondolo
 */
public class CounsilPrivilege extends Resource {
	
	private static final long serialVersionUID = -1942721378066858491L;

	/**
	 * The constructor.
	 */
	public CounsilPrivilege(int quantity) {
		super("CounsilPrivilege", quantity);
	}
	
	public CounsilPrivilege(CounsilPrivilege beforeCounsilPrivilege, CounsilPrivilege afterCounsilPrivilege){
		super(beforeCounsilPrivilege, afterCounsilPrivilege);
	}
	
}