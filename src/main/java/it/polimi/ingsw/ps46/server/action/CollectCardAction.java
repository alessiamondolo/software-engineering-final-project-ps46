package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * CollectCardAction represents the action of a player that collects a card from a tower action space
 * after having correctly moved to a tower action space.
 * 
 * @author Alessia Mondolo
 */
public class CollectCardAction implements Action {
	
	private boolean isTheTowerFree = false;
	private Player player;
	private ActionSpace actionSpace;
	private Card card;
	

	public CollectCardAction(Player player, ActionSpace actionSpace, boolean isTheTowerFree) {
		this.player = player;
		this.actionSpace = actionSpace;
		this.isTheTowerFree = isTheTowerFree;
	}
	
	
	/*sono già in una casella della torre dove posso stare, mi è stato passato:
	  	- il valore del Family member usato
		- in che action Space sono stato messo
	*/
	@Override
	public boolean execute() {
		if(isLegal()) {	
			return true;	
		}
		return false;
	}

	/**
	 *	Verifies the following conditions:<br>
	 * <ul>
	 * <li>If the tower is already occupied (the player has to pay 3 money), so check if can he afford this fee;
	 * <li>Check if the player can afford the cost of the card, but before It should be added the bonus of the tower floor.
	 * If he cannot afford them fees return false;
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isLegal() {
		ResourceSet temporaryPlayerResourceSet = new ResourceSet(player.getPersonalBoard().getPlayerResourceSet());
		
	
		
		return false;
	}


	public ActionSpace getActionSpace() {
		return actionSpace;
	}

}
