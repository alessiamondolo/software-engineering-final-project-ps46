package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
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
		isTheTowerEmpty = game.getBoard().isEmptyTower( actionSpace.getId() );
		this.familyMemberUsed = familyMemberUsed;
		
		card = game.getBoard().getCardOfTheTowerFloor( actionSpace.getId() );

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
	  *  <li>Active the immediate effect of the new card.</li>
	  *  <li>Collect it into the personalBoard of the player.</li>
	  *  <li>Set as used the family member and occupied the actionSpace.</li>
	  *  </ul>
	  */
	@Override
	public boolean execute() {
		if(isLegal()) {	
			card.use(game);
			card.collectCard(game);
			
			int tower = (actionSpace.getId() - 1) / game.getBoard().getNumberOfTowers();
			int floor = (actionSpace.getId() - 1) % game.getBoard().getNumberOfTowers();
			game.getBoard().getTower(tower).getTowerFloor(floor).setCard(null);
			game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().setPlayerColor(game.getCurrentPlayer().getColor());
			
			//setting occupied this actionSpace and used the familyMember
			actionSpace.updateAvailability();
			familyMemberUsed.setPositionOfFamilyMember(actionSpace.getId());
			return true;	
		}
		else 
			return false;
	}

	
	
	/**
	 *	Verifies the following conditions:<br>
	 * <ul>
	 * <li>If the tower is already occupied (the player has to pay 3 money), so check if can he afford this fee;</li>
	 * <li>Check if the player can afford the cost of the card, but before it should be added the bonus of the tower floor.
	 * If he cannot afford these fees, re-Set the resourceSet as before and return false.</li>
	 * </ul>
	 * @return boolean
	 */
	@Override
	public boolean isLegal() {
		//I'm copying player's resourceSet just to can activate the effect of the increaseResourcesEffect
		//(it's acting on the player resourceSet)
		ResourceSet temporaryPlayerResourceSet = new ResourceSet(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());
		
		if (!isTheTowerEmpty) {
			
			if(!temporaryPlayerResourceSet.greaterOrEqual(TOWERFEE)) {
				return false;
			}
			temporaryPlayerResourceSet.sub(TOWERFEE);
		}
		
		ResourceSet temporaryEffectResourceSet = new ResourceSet(actionSpace.getEffectOfActionSpace().getAdditionalResources());
		if (!game.getCurrentPlayer().getDecreaseResourcesMalus().isEmpty())
		{
			for (DecreaseResourcesMalus decreaseResourcesMalus : game.getCurrentPlayer().getDecreaseResourcesMalus()) {
				if (decreaseResourcesMalus.getName() == "DecreaseResourcesMalus"){
					
					temporaryEffectResourceSet.sub(decreaseResourcesMalus.getDecreasedResources());
				}	
			}
		}
		temporaryPlayerResourceSet.add(temporaryEffectResourceSet);
		
		if(!temporaryPlayerResourceSet.greaterOrEqual(card.getCost())) {
			return false;
		}
		temporaryPlayerResourceSet.sub(card.getCost());
		
		game.getCurrentPlayer().getPersonalBoard().setResources(temporaryPlayerResourceSet);
		return true;
	}
}
