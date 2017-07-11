package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.GameState;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
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
	private ActionSpace actionSpace;
	private ResourceSet cost;
	
	
	public MoveToActionSpaceAction(Game game, Player player, FamilyMember familyMember, ActionSpace actionSpace, ResourceSet cost) {
		this.game = game;
		this.player = player;
		this.familyMember = familyMember;
		this.actionSpace = actionSpace;
		this.cost = cost;
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
		
		if(isLegal()) {
			
			switch(actionSpace.getType()) {
				case "Tower" : {
					Action nextAction = new CollectCardAction(game, actionSpace, familyMember, cost);
					return nextAction.execute();
				}
				case "Production" : {
					Action nextAction = new ActivateProductionAction(game, actionSpace, familyMember);
					return nextAction.execute();
				}
				case "Harvest" : {
					Action nextAction = new ActivateHarvestAction(game, actionSpace, familyMember);
					return nextAction.execute();
				}
				case "Market" : {
					//check on malus
					ResourceSet temporaryEffectResourceSet = new ResourceSet(actionSpace.getEffectOfActionSpace().getAdditionalResources());
					
					if (game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources() != null) {
						temporaryEffectResourceSet.sub(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources());
					}
					
					game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(temporaryEffectResourceSet);
					
					actionSpace.updateAvailability();
					actionSpace.setPlayerColor(game.getCurrentPlayer().getColor());
					familyMember.setPositionOfFamilyMember(actionSpace.getId());
					familyMember.use();
					return true;
					
				}
				case "Council" : {
					Action nextAction = new CouncilAction(game, actionSpace, familyMember);
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
		//TODO Quando faccio un'azione bonus, perchè dovrei settare il family member utilizzato?
		
		//The player that wants to move to an action space has to be the current player
		if (game.getCurrentPlayer().getIdPlayer() != player.getIdPlayer()) {
			return false;
		}
		
		//The action space has to be a valid action space
		if(actionSpace == null)
			return false;
		
		//Check the malus given by excommunication that avoid you to put your family member on a marketSpace
		if(actionSpace.getType().equals("Market")) {
			if(!(player.getGenericMalus().isEmpty()) && (player.getGenericMalus().containsKey("noMoveToMarketSpace")) ) 
				return false;
		}
		
		//The action space has to be available 
		//If there is activated "Ludovico Ariosto" LeaderCard you ignore this requirement
		if(!(actionSpace.getAvailability()))
			if(!(game.getCurrentPlayer().getLeaderCards().containsKey("Ludovico Ariosto") || !(game.getCurrentPlayer().getLeaderCards().get("Ludovico Ariosto").isActive())))
				return false;
			
		//If the actionSpace is the green tower, check military points liked with the number of territory cards got by the player.
		//You can ignore this requirement if you have activated the LeaderCard effect of Cesare Borgia.
		if (game.getBoard().getColorOfTower(actionSpace.getId()).equals("green")){
			if(!( game.getCurrentPlayer().getLeaderCards().containsKey("Cesare Borgia") || !(game.getCurrentPlayer().getLeaderCards().get("Cesare Borgia").isActive())))
			{
				int numberOfCards = game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().size();
				if(numberOfCards >= 2 ) {
				MilitaryPoints temporaryMilitaryPointsRequired;
				temporaryMilitaryPointsRequired = game.getCurrentPlayer().getPersonalBoard().getRequiredMilitaryPointsForTerritoryCardsMap().get(numberOfCards);					
				if (!player.getPersonalBoard().getPlayerResourceSet().greaterOrEqual(temporaryMilitaryPointsRequired)) {
					return false;
					}
				}
			}
		}

		//The family member has to be available - it can't be in other action spaces
		//We ignore this only if the player is doing an extra move without placing a familyMember
		if(!game.getGameState().equals(GameState.EXTRA_MOVE)) {
			if((familyMember.isUsed())) {
				return false;
			}
		}
		
		//The value of the family member that needs to be moved to the action space, summed with the number of servants
		// added to the family member, has to be greater or equal than the value of the dice of the action space
		if(familyMember.getValueFamilyMember().getValue() < actionSpace.getRequiredFamilyMemberValue().getValue()) {
			return false;
		}
		
		return true;
	}

}
