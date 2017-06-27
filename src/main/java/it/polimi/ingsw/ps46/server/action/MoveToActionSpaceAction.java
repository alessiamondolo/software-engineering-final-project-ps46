package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;


/**
 * MoveToActionSpaceAction represents the action of a player moving one of his family members to an action space.
 * 
 * @author Alessia Mondolo
 */
public class MoveToActionSpaceAction implements Action {

	private Game game;
	private Player player;
	private FamilyMember familyMember;
	private int servants;
	private ActionSpace actionSpace;
	
	
	public MoveToActionSpaceAction(Game game, Player player, FamilyMember familyMember, int servants, ActionSpace actionSpace) {
		this.game = game;
		this.player = player;
		this.familyMember = familyMember;
		this.servants = servants;
		this.actionSpace = actionSpace;
	}
	
	
	
	/**
	 * After checking if the action is valid, this method:
	 * - updates the number of spots available in the action space;
	 * - sets the family member as used;
	 * - checks the type of action space and based on it launches the next action:
	 * 		- if it is a tower action space it collects and activates the card in the action space;
	 * 		- if it is a production or harvest action space, it launches the production or harvest action;
	 * 		- it it is a market action space, it launches the market space action;
	 * 		- if it is a council action space, it launches a council space action.
	 */
	public boolean execute() {
		
		System.out.println("I'm inside the action with the " + familyMember.getColor() + " family member and " + servants + " servants.");
		
		if(isLegal()) {
			//Sets the action space as not available
			//TODO check modifications to this method
			actionSpace.updateAvailability ();
			//TODO set the family member as used			
			switch(actionSpace.getType()) {
				case "TowerActionSpace" : {
					//TODO verificare se ci sono altri giocatori nella stessa torre
					//TODO capire come prendere il piano in cui si trova la carta
					//nextAction = new CollectCardAction(Player player, Card card);
					//nextAction.execute();
					break;
				}
				case "ProductionActionSpace" : {
					Action nextAction = new ActivateProductionAction();
					return nextAction.execute();
				}
				case "HarvestActionSpace" : {
					Action nextAction = new ActivateHarvestAction();
					return nextAction.execute();
				}
				case "MarketActionSpace" : {
					//Effect getFromMarket = new IncreaseResourcesEffect(actionSpace.getResources());
					return false;
				}
				case "CouncilActionSpace" : {
					Action nextAction = new CouncilAction();
					return nextAction.execute();
				}
				default : 
					return false;		
			}
		}
		
		return false;

	}

	
	
	/**
	 * Verifies the following conditions:
	 * - The player that wants to move to an action space has to be the current player;
	 * - The action space has to be available;
	 * - The family member has to be available - it can't be in other action spaces;
	 * - The value of the family member that needs to be moved to the action space has to be
	 * 	 greater or equal than the value of the dice of the same color of the family member.
	 * @return boolean
	 */
	public boolean isLegal() {
		
		//The player that wants to move to an action space has to be the current player
		if (game.getCurrentPlayer().getIdPlayer() != player.getIdPlayer())
			return false;
		//The action space has to be available
		if(!(actionSpace.getAvailability()))
			return false;
		//The family member has to be available - it can't be in other action spaces
		if((familyMember.isUsed()))
			return false;
		//The value of the family member that needs to be moved to the action space has to be
		//greater or equal than the value of the dice of the same color of the family member
		if(!familyMember.getValueFamilyMember().greaterOrEqual(game.getDice(familyMember.getColor())))
			return false;
		return true;
	}

}
