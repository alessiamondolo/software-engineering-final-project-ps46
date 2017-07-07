package it.polimi.ingsw.ps46.server.action;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * This class implements action and represents the action of Council Palace
 * 
 * @author Andrea.Masi
 */
public class CouncilAction implements Action {
	
	private Game game;
	private ActionSpace councilActionSpace;
	private FamilyMember familyMemberUsed;
	
	
	
	public CouncilAction (Game game, ActionSpace councilActionSpace, FamilyMember familyMemberUsed){
		this.game = game;
		this.councilActionSpace = councilActionSpace;
		this.familyMemberUsed = familyMemberUsed;
	}
	
	
	/**
	 *This method execute the action of the councilActionSpace (it's always executable):<br>
	 * <ul>
	 * <li>Set the new order of the next turn, into the council palace order.</li>
	 * <li>Check if are there present some malus, and sum the gained resources to player resourceSet.</li>
	 * </ul>
	 * 
	 * @return boolean
	 */
	@Override
	public boolean execute() {
		if(isLegal()){
			if(!game.getCouncilPalaceOrder().contains(game.getCurrentPlayer())){
				game.getCouncilPalaceOrder().add(game.getCurrentPlayer());
			}
						
			ResourceSet temporaryEffectResourceSet = new ResourceSet(councilActionSpace.getEffectOfActionSpace().getAdditionalResources());
			if (!game.getCurrentPlayer().getDecreaseResourcesMalus().isEmpty())
			{
				for (DecreaseResourcesMalus decreaseResourcesMalus : game.getCurrentPlayer().getDecreaseResourcesMalus()) {
					if (decreaseResourcesMalus.getName() == "DecreaseResourcesMalus"){
						
						temporaryEffectResourceSet.sub(decreaseResourcesMalus.getDecreasedResources());
					}	
				}
			}
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(temporaryEffectResourceSet);
			//TODO cambio dell'ordine di gioco dei giocatori
			councilActionSpace.updateAvailability();
			councilActionSpace.setPlayerColor(game.getCurrentPlayer().getColor());
			familyMemberUsed.setPositionOfFamilyMember(councilActionSpace.getId());
			familyMemberUsed.use();
			
			return true;
		}
		else 
			return false;
	}
	
	
	/**
	 *	Nothing to verify for this action, always executable.
	 *
	 *	@return true
	 */
	@Override
	public boolean isLegal() {
		return true;
	}

}