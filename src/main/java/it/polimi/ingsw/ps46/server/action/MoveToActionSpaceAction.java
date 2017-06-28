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
	 * After checking if the action is valid, this method:<br>
	 * <ul>
	 * <li>updates the number of spots available in the action space;</li>
	 * <li>sets the family member as used;</li>
	 * <li>checks the type of action space and based on it launches the next action:</li>
	 * 		- if it is a tower action space it collects and activates the card in the action space;<br>
	 * 		- if it is a production or harvest action space, it launches the production or harvest action;<br>
	 * 		- it it is a market action space, it launches the market space action;<br>
	 * 		- if it is a council action space, it launches a council space action.<br>
	 * </ul>
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
					Action nextAction = new CollectCardAction(player, actionSpace, game.getBoard().isEmptyTower(actionSpace.getIdLocalActionSpaces()));
				//nextAction.execute();
					break;
				}
				case "ProductionActionSpace" : {
					Action nextAction = new ActivateProductionAction(game, actionSpace, familyMember);
					return nextAction.execute();
				}
				case "HarvestActionSpace" : {
					Action nextAction = new ActivateHarvestAction(game, actionSpace, familyMember);
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
	 * Verifies the following conditions:<br>
	 * <ul>
	 * <li>The player that wants to move to an action space has to be the current player;</li>
	 * <li>The action space has to be available;</li>
	 * <li>The family member has to be available - it can't be in other action spaces;</li>
	 * <li>The value of the family member that needs to be moved to the action space has to be
	 * 	 greater or equal than the value of the dice of the same color of the family member.</li>
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
