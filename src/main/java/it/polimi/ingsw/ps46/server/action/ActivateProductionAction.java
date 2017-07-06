package it.polimi.ingsw.ps46.server.action;


import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.card.DiceMalusEffect;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * This Class implements the Action of Production.
 * 
 * @author Andrea.Masi
 */
public class ActivateProductionAction implements Action {
	
	private Game game;
	private ActionSpace productionActionSpace;
	private FamilyMember familyMemberUsed;
	
	private Dice penality;
	private final static int PENALITYOFTHEACTIONSPACE = 3;
	
	
	
	public ActivateProductionAction(Game game, ActionSpace productionActionSpace, FamilyMember familyMemberUsed){
		
		this.game = game;
		this.productionActionSpace = productionActionSpace;
		this.familyMemberUsed = familyMemberUsed;
		
		}
	
	
	/**
	 * This method execute the action of production checking and doing several operations:<br>
	 * <ul>
	 * <li>Check: Got the familyMemberValue and if the action is legal.</li>
	 * <li>Activate the effect of those player's cards activable by the action of production (if familyMemberValue is enough).</li>
	 * <li>TODO chiedere al giocatore quale delle due scelte di risorse vuole effettuare (per alcuni tipi di carta)?</li>
	 * <li>Check if are there some malus acting on the personal board production resourceSet gained.</li>
	 * <li>Update player's resourceSet.</li>
	 * </ul>
	 * @param familyMemberValue
	 */
	
	@Override
	public boolean execute() {
		
		if (isLegal() == true){
			
			for (BuildingCard buildingCard : game.getCurrentPlayer().getPersonalBoard().getBuildingDeck()) {
				
				if(familyMemberUsed.getValueFamilyMember().greaterOrEqual(buildingCard.getProductionValue())){
					
					game.useCard(buildingCard);
					
				}
			}

			ResourceSet personalBoardResourceSet = new ResourceSet(game.getCurrentPlayer().getPersonalBoard().getBonusTile().getGainedFromPersonalBoardProduction());
			if (!game.getCurrentPlayer().getDecreaseResourcesMalus().isEmpty()){
				
				for (DecreaseResourcesMalus decreaseResourcesMalus : game.getCurrentPlayer().getDecreaseResourcesMalus()) {
					if (decreaseResourcesMalus.getName() == "DecreaseResourcesMalus"){
						personalBoardResourceSet.sub(decreaseResourcesMalus.getDecreasedResources());
					}	
				}
			}
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(personalBoardResourceSet);
			//setting occupied this actionSpace and used the familyMember
			productionActionSpace.updateAvailability();
			productionActionSpace.setPlayerColor(game.getCurrentPlayer().getColor());
			familyMemberUsed.setPositionOfFamilyMember(productionActionSpace.getId());
			familyMemberUsed.use();
			
			return true;
		}
		else
			return false;
	}


	
	
	/**
	 *	This method check if the called action of production by a player with his family member into a 
	 * specific produtionActionSpace is Valid or not:<br>
	 * <ul>
	 * <li>Check: if the selected action space for production is for more than one family members, 
	 * putting the Dice penalty if it is.</li>
	 * <li>Check: all the dice bonus on the player's attribute "Bonus" (given by all the character cards got by the player), 
	 * and Sum (if present).</li>
	 * <li>Check: possible DiceMalus got by excommunication on the player's attribute "DiceMalusEffect", and Sub (if present).</li>
	 * <li>Update the temporary Family member Dice value for the next step.</li>
	 * <li>Check: For Activation of the action, if the production is executable for at least one building card with 
	 * the updated Family Member Dice value, or the production is executable for the personal board production. If it is, return true.</li>
	 * </ul>
	 *	@return boolean 
	 */
	
	@Override
	public boolean isLegal() {
		
		Dice temporaryDice = new Dice( familyMemberUsed.getValueFamilyMember().getValue());
		//calculating every penality, bonus, malus on a copy of the familyMember Dice
		if (!productionActionSpace.isMaxOnePlayer())
		{
			penality = new Dice( PENALITYOFTHEACTIONSPACE );
		}
		else
			penality = new Dice(0);

		temporaryDice.subDice( penality );
		temporaryDice.sumDice( game.getCurrentPlayer().getBonusMap().get( "ProductionAction" ));
		
	
		if( !game.getCurrentPlayer().getDiceMalus().isEmpty() )
			for( DiceMalusEffect diceMalusEffect : game.getCurrentPlayer().getDiceMalus() ){
				
				if ( diceMalusEffect.getType() == "ProductionAction" ){
					temporaryDice.subDice( diceMalusEffect.getMalus() );	
				}
			}
		
		if(!temporaryDice.greaterOrEqual(game.getCurrentPlayer().getPersonalBoard().getBonusTile().getProductionValue())){
			return false;
		}
		
		familyMemberUsed.setValueOfFamilyMember(temporaryDice);
		return true;
	}

}