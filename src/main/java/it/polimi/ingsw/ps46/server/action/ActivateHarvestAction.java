package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * This Class implements the Action of Harvest.
 * 
 * @author Andrea.Masi
 */

public class ActivateHarvestAction implements Action {

	private Game game;
	private ActionSpace harvestActionSpace;
	private FamilyMember familyMemberUsed;
	
	private Dice penality;
	private final static int PENALITYOFTHEACTIONSPACE = 3;
	
	
	
	public ActivateHarvestAction(Game game, ActionSpace harvestActionSpace, FamilyMember familyMemberUsed){
		
		this.game = game;
		this.harvestActionSpace = harvestActionSpace;
		this.familyMemberUsed = familyMemberUsed;
		
		}
	
	
	/**
	 * This method execute the action of harvest checking and doing several operations:<br>
	 * <ul>
	 * <li>Check: Got the familyMemberValue and if the action is legal.</li>
	 * <li>Activate the effect of those player's cards activable by the action of harvest (if familyMemberValue is enough).</li>
	 * <li>Check if are there some malus acting on the personal board harvest resourceSet gained.</li>
	 * <li>Update player's resourceSet.</li>
	 * </ul>
	 * @param familyMemberValue
	 */
	
	@Override
	public boolean execute() {
		
		if (isLegal() == true){
			
			for (TerritoryCard territoryCard : game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck()) {
				
				if(familyMemberUsed.getValueFamilyMember().greaterOrEqual(territoryCard.getHarvestValue())){

					territoryCard.use(game);	
					
				}
			}

			ResourceSet personalBoardResourceSet = new ResourceSet(game.getCurrentPlayer().getPersonalBoard().getBonusTile().getGainedFromPersonalBoardHarvest());
			if (game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources() != null) {
				personalBoardResourceSet.sub(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources());	
			}
			
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(personalBoardResourceSet);
			//setting occupied this actionSpace and used the familyMember
			harvestActionSpace.updateAvailability();
			harvestActionSpace.setPlayerColor(game.getCurrentPlayer().getColor());
			familyMemberUsed.setPositionOfFamilyMember(harvestActionSpace.getId());
			familyMemberUsed.use();
			
			return true;
		}
		else 
			return false;
	}


	
	
	/**
	 * This method check if the called action of harvest by a player with his family member into a 
	 * specific harvestActionSpace is Valid or not:<br>
	 * <ul>
	 * <li>Check: if the selected action space for harvest is for more than one family members, 
	 * putting the Dice penalty if it is.</li> 
	 * <li>Check: all the dice bonus on the attribute of player's "Bonus" (given by all the character cards got by the player),
	 *  and Sum (if present).</li>
	 * <li>Check: possible DiceMalus got by excommunication on the player's attribute "DiceMalusEffect", and Sub (if present).</li>
	 * <li>Update the temporary Family member Dice value for the next step.</li>
	 * <li>Check: For Activation of the action, if harvest is executable for at least one territory card with 
	 * the updated Family Member Dice value, or the harvest is executable for the personal board harvest. If it is, return true.</li>
	 * </ul>
	 *	@return boolean 
	 */
	
	@Override
	public boolean isLegal() {
		
		Dice temporaryDice = new Dice( familyMemberUsed.getValueFamilyMember().getValue());
		//calculating every penality, bonus, malus on a copy of the familyMember Dice
		if (!harvestActionSpace.isMaxOnePlayer())
		{
			penality = new Dice( PENALITYOFTHEACTIONSPACE );
		}
		else
			penality = new Dice(0);
		
		temporaryDice.subDice( penality );
		temporaryDice.sumDice( game.getCurrentPlayer().getBonusMap().get( "HarvestAction" ));
		
		
		if( !game.getCurrentPlayer().getDiceMalus().isEmpty() )
			for( String string : game.getCurrentPlayer().getDiceMalus().keySet()){
				
				if ( game.getCurrentPlayer().getDiceMalus().get(string).getType() == "HarvestAction" ){
					temporaryDice.subDice( game.getCurrentPlayer().getDiceMalus().get(string).getMalus() );	
				}
			}

		if(!temporaryDice.greaterOrEqual(game.getCurrentPlayer().getPersonalBoard().getBonusTile().getHarvestValue())){
			return false;
		}
		
		familyMemberUsed.setValueOfFamilyMember(temporaryDice);
		return true;
	}
	
}