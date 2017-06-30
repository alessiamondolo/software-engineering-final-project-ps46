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
	 * <ul>
	 * 		<li>if it is a tower action space it collects and activates the card in the action space;</li>
	 * 		<li>if it is a production or harvest action space, it launches the production or harvest action;</li>
	 * 		<li>it it is a market action space, it launches the market space action;</li>
	 * 		<li>if it is a council action space, it launches a council space action.</li>
	 * </ul>
	 * </ul>
	 */
	public boolean execute() {
		
		System.out.println("I'm inside the action with the " + familyMember.getColor() + " family member and " + servants + " servants.");
		
		if(isLegal()) {
			//Sets the action space as not available
			//TODO check modifications to this method
			actionSpace.updateAvailability();
			//TODO set the family member as used			
			switch(actionSpace.getType()) {
				case "TowerActionSpace" : {
					//TODO capire come prendere il piano in cui si trova la carta
					//Action nextAction = new CollectCardAction(Player player, Card card);
					//nextAction.execute();
					break;
				}
				case "ProductionActionSpace" : { /*
					Action nextAction = new ActivateProductionAction();
					return nextAction.execute();*/
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
			}
		}
		return false;
	}

	
	
	/**
	 * Verifies the following conditions:<br>
	 * <li>The player that wants to move to an action space has to be the current player;</li>
	 * <li>The action space has to be available;</li>
	 * <li>The family member has to be available - it can't be in other action spaces;</li>
	 * <li>The value of the family member that needs to be moved to the action space, summed with the number of servants
	 * added to the family member, has to be greater or equal than the value of the dice of the action space.</li>
	 * 
	 * @return true if the move is legal, otherwise false
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
		//The value of the family member that needs to be moved to the action space, summed with the number of servants
		// added to the family member, has to be greater or equal than the value of the dice of the action space
		if(familyMember.getValueFamilyMember().getValue() + servants < actionSpace.getRequiredFamilyMemberValue().getValue())
			return false;
		return true;
	}

}
