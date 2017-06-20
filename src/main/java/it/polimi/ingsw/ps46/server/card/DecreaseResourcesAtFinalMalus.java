package it.polimi.ingsw.ps46.server.card;

/**
 * DecreaseResourcesAtFinalMalus extends DecreaseResourcesMalus. 
 * It's used to implements the last malus effect of the ExcommunicationTiles acting on the building cards and resourceSet of the player. 
 * 
 * @author Andrea.Masi
 */

public class DecreaseResourcesAtFinalMalus extends DecreaseResourcesMalus{
	
	String from;
	
	
	public DecreaseResourcesAtFinalMalus(){
		from = null;
	}
	
	
	/**
	 * This Constructor creates a new object DecreaseResourcesAtFinalMalus.
	 * Built by .json file.
	 *
	 *@param from
	 */	
	public DecreaseResourcesAtFinalMalus(String from){
		 this.from = from;
	}
	
	/**
	 * Getter of the class DecreaseResourcesAtFinalMalus. return a String.
	 * @return from
	 */
	public String getType() {
		return from;
	}
	
}
