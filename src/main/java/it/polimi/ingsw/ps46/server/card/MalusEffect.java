package it.polimi.ingsw.ps46.server.card;


/**
 * This abstract Class implements the Malus effect of the ExcommunicationTiles.
 * 
 * @author Andrea.Masi
 */

public abstract class MalusEffect {
	
	protected String name;
	private boolean isActive = false;
	
	
	/**
	 * Used to activate the MalusEffect of an ExcommunicationTile (called by an Action Class) on a player who get the Excommunication.
	 */
	public void activationMalus(){
		isActive = true;
	}
	

	public boolean getIfActive() {
		return isActive;
	}
	
	public String getName() {
		return name;
	}

}
