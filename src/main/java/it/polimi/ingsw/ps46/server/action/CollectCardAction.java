package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.Card;


/**
 * CollectCardAction represents the action of a player that collects a card from a tower action space
 * after having correctly moved to a tower action space.
 * 
 * @author Alessia Mondolo
 */
public class CollectCardAction implements Action {
	
	private Player player;
	private Card card;

	public CollectCardAction(Player player, Card card) {
		this.player = player;
		this.card = card;
	}
	
	@Override
	public void execute() {
		if(isLegal()) {
			
			
		}
		else {
			//throw Exception illigal action
		}
	}

	@Override
	public boolean isLegal() {
		// TODO Auto-generated method stub
		return false;
	}

}
