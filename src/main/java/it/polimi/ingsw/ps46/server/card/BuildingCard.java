package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * BuildingCard is an object that represent a single building card of the game.
 * Their immediate effects are of the type IncreaseResourcesEffect and their permanent effects 
 * are production effects of the type ExchangeResourcesEffect or IncreaseResourcesEffect.
 * 
 * @author Andrea.Masi
 */
public class BuildingCard extends Card {
	
	private static final long serialVersionUID = -3659260772278856141L;
	
	private Dice productionValue;
	private ExchageResourcesEffect permanentEffectsTwo;
	private Boolean doubleChoice = false;

	public BuildingCard(String cardName, int cardEra, IncreaseResourcesEffect immediateEffects, Boolean doubleChoice, Effect permanentEffects, 
			ExchageResourcesEffect permanentEffectsTwo, ResourceSet cost, Dice productionValue)
	{
		super(cardName,cardEra, immediateEffects, permanentEffects, cost);
		this.productionValue = productionValue;
		this.doubleChoice = doubleChoice ;
		this.permanentEffectsTwo = permanentEffectsTwo;
	}
	
	
	@Override
	public void collectCard(Game game) {
		immediateEffects.activateEffect(game);
		game.getCurrentPlayer().getPersonalBoard().putBuildingCardInPlayerSet(this);
	}

	public Dice getProductionValue() {
		return productionValue;
	}

	public ExchageResourcesEffect getPermanentEffectsTwo() {
		return permanentEffectsTwo;
	}


	public Boolean getDoubleChoice() {
		return doubleChoice;
	}

}
