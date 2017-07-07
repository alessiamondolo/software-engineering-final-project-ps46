package it.polimi.ingsw.ps46.server.action;


import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.Card;
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
	private final static Money TOWERFEE = new Money(3);
	

	public CollectCardAction(Game game, ActionSpace actionSpace, FamilyMember familyMemberUsed) {
		this.game = game;
		this.actionSpace = actionSpace;
		isTheTowerEmpty = game.getBoard().isEmptyTower( actionSpace.getId() );
		this.familyMemberUsed = familyMemberUsed;
		
		card = game.getBoard().getCardOfTheTowerFloor( actionSpace.getId() );
	}
	
	
	 /**
	  * This method execute the action of collect a card after the operation of checking done by isValid method:<br>
	  *  <ul>
	  *  <li>If the card to collect is a character one, active the permanent effect (bonus/malus of the card)
	  *  <li>Active the immediate effect of the new card.</li>
	  *  <li>Collect it into the personalBoard of the player.</li>
	  *  <li>Set as used the family member and occupied the actionSpace.</li>
	  *  </ul>
	  */
	@Override
	public boolean execute() {
		if(isLegal()) {	
			if (game.getBoard().getColorOfTower(actionSpace.getId()) == "blue")
					card.use(game);
			
			//checking the leaderCard Effect of "Santa Rita"
			if(game.getCurrentPlayer().getLeaderCards().containsKey("Santa Rita") && (game.getCurrentPlayer().getLeaderCards().get("Santa Rita").isActive()) ){
				//make a copy of player resourceSet 
				ResourceSet temporaryResourceSet = new ResourceSet(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());

				card.collectCard(game);
	
				//seeing the difference between the initial player resourceSet and the modified one.
				ResourceSet difference = new ResourceSet(temporaryResourceSet, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());
			
					//if there is a malus, sum the malus into the difference 
					if (game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources() != null) {
						difference.add(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources());
					}
					//setting to 0 all the resources not touched by the effect of the leader card
					for (String key : difference.getResourcesMap().keySet()) {
						if((key != "Wood") && (key != "Stones") && (key != "Money") && ( key != "Servants")) {
							difference.getResourcesMap().get(key).setQuantity(0);
						}
					}	
					game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(difference);
			}
			else
				card.collectCard(game);
						
			int tower = (actionSpace.getId() - 1) / game.getBoard().getNumberOfTowers();
			int floor = (actionSpace.getId() - 1) % game.getBoard().getNumberOfTowers();
			game.getBoard().getTower(tower).getTowerFloor(floor).setCard(null);
			game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().setPlayerColor(game.getCurrentPlayer().getColor());
			
			//setting occupied this actionSpace and used the familyMember
			actionSpace.updateAvailability();

			familyMemberUsed.setPositionOfFamilyMember(actionSpace.getId());
			familyMemberUsed.use();
			
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
		//checking the leaderCard Effect of "Filippo Brunelleschi"
		if (!isTheTowerEmpty) {
			if(!game.getCurrentPlayer().getLeaderCards().containsKey("Filippo Brunelleschi") || !(game.getCurrentPlayer().getLeaderCards().get("Filippo Brunelleschi").isActive()) ){
				if(!temporaryPlayerResourceSet.greaterOrEqual(TOWERFEE)) return false;
				temporaryPlayerResourceSet.sub(TOWERFEE);
			}

		}
		
		ResourceSet temporaryEffectResourceSet = new ResourceSet(actionSpace.getEffectOfActionSpace().getAdditionalResources());
		if (game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources() != null) {
			
			temporaryEffectResourceSet.sub(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources());
			}
		
		temporaryPlayerResourceSet.add(temporaryEffectResourceSet);
		//checking the leaderCard Effect of "Pico della Mirandola"
		
		ResourceSet temporaryCost = new ResourceSet(card.getCost());
		if (game.getCurrentPlayer().getLeaderCards().containsKey("Pico della Mirandola") && (game.getCurrentPlayer().getLeaderCards().get("Pico della Mirandola").isActive()) ){
			Money moneyDiscounted = new Money(3);
			if ( temporaryCost.getResourcesMap().get("Money").getQuantity() < 3) {
				temporaryCost.getResourcesMap().get("Money").setQuantity(0);
				//altrimenti per come è stata pensata la classe "sub" delle risorse..
				//se ho un costo 2 - 3 = 2? (non fa nulla) ---> così invece imposto il risultato a 0
			}
			else
				temporaryCost.sub(moneyDiscounted);
		}
		if (!temporaryPlayerResourceSet.greaterOrEqual(temporaryCost))
			return false;	
		
		temporaryPlayerResourceSet.sub(temporaryCost);
		
		
		game.getCurrentPlayer().getPersonalBoard().setResources(temporaryPlayerResourceSet);
		return true;
	}
}
