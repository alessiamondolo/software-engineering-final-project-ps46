package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


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
			switch(actionSpace.getType()) {
				case "TowerActionSpace" : {
					Action nextAction = new CollectCardAction(game, actionSpace, familyMember);
					nextAction.execute();
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
					if(!actionSpace.getEffectOfActionSpace().getAdditionalResources().getResourcesMap().containsKey("CounsilPrivilege")){
						//check sui malus
						ResourceSet temporaryEffectResourceSet = new ResourceSet(actionSpace.getEffectOfActionSpace().getAdditionalResources());
						if (!game.getCurrentPlayer().getDecreaseResourcesMalus().isEmpty())
						{
							for (DecreaseResourcesMalus decreaseResourcesMalus : game.getCurrentPlayer().getDecreaseResourcesMalus()) {
								if (decreaseResourcesMalus.getName() == "DecreaseResourcesMalus"){
									
									temporaryEffectResourceSet.sub(decreaseResourcesMalus.getDecreasedResources());
								}	
							}
						}
						game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(temporaryEffectResourceSet);
						
						actionSpace.updateAvailability();
						familyMember.setPositionOfFamilyMember(actionSpace.getIdLocalActionSpaces());
						return true;
					}
					else 
					{
						//TODO ci sono i due bonus del consiglio da prendere
						return true;
					}
				}
				case "CouncilActionSpace" : {
					//TODO interazione col giocatore
					Action nextAction = new CouncilAction(game, actionSpace, familyMember );
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
		//TODO Quando faccio un'azione bonus, perchè dovrei settare il family member utilizzato?
		//The player that wants to move to an action space has to be the current player
		if (game.getCurrentPlayer().getIdPlayer() != player.getIdPlayer())
			return false;
		//The action space has to be available 
		if(!(actionSpace.getAvailability()))
			if(!(game.getCurrentPlayer().getLeaderCards().containsKey("Ludovico Ariosto") || !(game.getCurrentPlayer().getLeaderCards().get("Ludovico Ariosto").isActive())))
				return false;
		//If the actionSpace is the green tower check military points liked with the number of territory cards got by the player
		//if()
		
		
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
