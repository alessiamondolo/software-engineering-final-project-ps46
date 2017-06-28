package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.Money;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * CollectCardAction represents the action of a player that collects a card from a tower action space
 * after having correctly moved to a tower action space.
 * 
 * @author Andrea Masi
 */
public class CollectCardAction implements Action {
	
	private Game game;
	private ActionSpace actionSpace;
	private boolean isTheTowerEmpty = false;
	private FamilyMember familyMemberUsed;

	private Card card;
	//private TerritoryCard territoryCard;
	//private CharacterCard characterCard;
	//private BuildingCard buildingCard;
	//private VentureCard ventureCard;
	private final static Money TOWERFEE = new Money(3);
	

	public CollectCardAction(Game game, ActionSpace actionSpace, FamilyMember familyMemberUsed) {
		this.game = game;
		this.actionSpace = actionSpace;
		isTheTowerEmpty = game.getBoard().isEmptyTower( actionSpace.getIdLocalActionSpaces() );
		this.familyMemberUsed = familyMemberUsed;
		
		card = game.getBoard().getCardOfTheTowerFloor( actionSpace.getIdLocalActionSpaces() );

		/*switch ( game.getBoard().getColorOfTower(actionSpace.getIdLocalActionSpaces()) ) {
		
		case "green":
			
			break;
		case "blue":
			
			break;	
		case "yellow":
			
			break;
		case "purple":

			break;
		
		default:
			break;
		}*/
	}
	
	
	
	 /**
	  * This method execute the action of collect a card after the operation of checking done by isValid method:<br>
	  *  <ul>
	  *  <li>Active the immediate effect of the new card.
	  *  <li>Collect it into the personalBoard of the player.
	  *  <li>Set as used the family member and occupied the actionSpace.
	  */
	@Override
	public boolean execute() {
		if(isLegal()) {	
			card.use(game);
			card.collectCard(game);
			
			//setting occupied this actionSpace and used the familyMember
			actionSpace.updateAvailability();
			familyMemberUsed.setPositionOfFamilyMember(actionSpace.getIdLocalActionSpaces());
			return true;	
		}
		else 
			return false;
	}

	/**
	 *	Verifies the following conditions:<br>
	 * <ul>
	 * <li>If the tower is already occupied (the player has to pay 3 money), so check if can he afford this fee;
	 * <li>Check if the player can afford the cost of the card, but before it should be added the bonus of the tower floor.
	 * If he cannot afford these fees, re-Set the resourceSet as before and return false.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isLegal() {
		//I'm copying player's resourceSet just to can activate the effect of the increaseResourcesEffect
		//(it's acting on the player resourceSet)
		ResourceSet temporaryPlayerResourceSet = new ResourceSet(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());
		
		if (!isTheTowerEmpty) {
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().sub(TOWERFEE);
		}
		actionSpace.getEffectOfActionSpace().activateEffect(game);
		
		if(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().greaterOrEqual(card.getCost())){
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().sub(card.getCost());
			return true;
		}
		
		else
			game.getCurrentPlayer().getPersonalBoard().setResources(temporaryPlayerResourceSet);
			return false;
	}

}
