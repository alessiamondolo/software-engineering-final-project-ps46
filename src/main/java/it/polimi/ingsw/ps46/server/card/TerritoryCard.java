package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;
//import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/** 
 * TerrytoryCard is an object that represent a single territory card of the game.
 * Territory cards have no cost, their immediate effects are of the type IncreaseResourcesEffect
 * and their permanent effects are harvest effects of the type IncreaseResourcesEffect.
 * 
 * @author Alessia Mondolo
 */
public class TerritoryCard extends Card {

	private static final long serialVersionUID = -638138649575488909L;
	
	//Territory cards don't have any cost
	private Dice harvestValue;

	public TerritoryCard(String cardName,int cardEra, IncreaseResourcesEffect immediateEffects, IncreaseResourcesEffect permanentEffects,
			ResourceSet cost, Dice harvestValue) {
		super(cardName,cardEra, immediateEffects, permanentEffects, cost);
		this.harvestValue = harvestValue;
	}
	
	@Override
	public void collectCard(Game game) {
		if(immediateEffects != null)
			immediateEffects.activateEffect(game);
		game.getCurrentPlayer().getPersonalBoard().putTerritoryCardInPlayerSet(this);
	}
	
	
	public Dice getHarvestValue() {
		return harvestValue;
	}
	
	@Override
	public String toString() {
		if(getImmediateEffects() != null) {
			return "Card name: " + getCardName() + "\n" + 
					"Immediate effect: " + getImmediateEffects() + "\n" +
					"Permanent effect: " + getPermanentEffects() + "\n" +
					"Harvest value: " + harvestValue + "\n" +
					"Cost: -";
		}
		else {
			return "Card name: " + getCardName() + "\n" + 
					"Immediate effect: -\n" +
					"Permanent effect: " + getPermanentEffects() + "\n" +
					"Harvest value: " + harvestValue + "\n" +
					"Cost: -";
		}
	
	}
	
}
