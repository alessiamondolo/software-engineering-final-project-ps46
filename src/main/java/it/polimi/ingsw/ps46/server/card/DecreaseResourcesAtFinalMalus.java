package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;

/**
 * DecreaseResourcesAtFinalMalus extends DecreaseResourcesMalus. 
 * It's used to implements the last malus effect of the ExcommunicationTiles acting on the building cards and resourceSet of the player. 
 * 
 * @author Andrea.Masi
 */

public class DecreaseResourcesAtFinalMalus extends DecreaseResourcesMalus{
	
	private static final long serialVersionUID = 4638592999712222217L;
	
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
	
	
	@Override
	public void activationMalus(Game game){
		
		game.getCurrentPlayer().setDecreaseAtFinalMalus(this);

	}
	
	/**
	 * Getter of the class DecreaseResourcesAtFinalMalus. return a String.
	 * @return from
	 */
	public String getFrom() {
		return from;
	}
	
	
	
}
