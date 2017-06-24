package it.polimi.ingsw.ps46.server.card;


import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/** 
 * This Class extends the class DiceBonusEffect and it's used to update player's attributes optionalDiscount, after the action "collectCard" used on some particular character cards.
 * These particulars card have a permanent effect which give you some bonus effect (when you want to collect a BuildingCards or a CharactersCards) + one or two (optional) discounts.
 * 
 * @author Andrea.Masi
 */

public class DiceBonusEffectDiscounted extends DiceBonusEffect {
	
	private static final long serialVersionUID = -133005720776307937L;
	
	private ResourceSet  resourcesDiscounted;
	private boolean doubleChoice;

	
	
	/**
	 * The constructor of DiceBonusEffectDiscounted.
	 * 
	 * @param bonus
	 * @param type
	 * @param resourcesDiscounted
	 * @param optionalResourcesDiscounted
	 * @param doubleChoise
	 */
	public DiceBonusEffectDiscounted(Dice bonus, String type, ResourceSet resourcesDiscounted, boolean doubleChoice) {
		
		super(bonus, type);
		
		this.doubleChoice = doubleChoice ;
		this.resourcesDiscounted = resourcesDiscounted ;
		
	}
	
	
	/**
	 * Description of the Method activateEffect.
	 * 
	 * This Method extends the same method of the class DiceBonusEffect but setting two different kinds of discounts 
	 * between the normal discount or the optional one (using the boolean attribute "doubleChoise").
	 * 
	 */
	@Override
	public void activateEffect(Game game) {
		
		game.getCurrentPlayer().updateBonus(type, bonus);

		if (doubleChoice == false)
			game.getCurrentPlayer().updateDiscount(type, resourcesDiscounted);
		
		else
			game.getCurrentPlayer().setOptionalDiscount(type, resourcesDiscounted);
		
	}

	public boolean isDoubleChoise() {
		return doubleChoice;
	}

	public ResourceSet getResourcesDiscounted() {
		return resourcesDiscounted;
	}
	
}
