package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;

/**
 * This class implements the class Effect, adding the DiceBonusEffect value to the attribute bonus in the class Player.
 * 
 * @author Andrea.Masi
 */

public class DiceBonusEffect implements Effect {
	
	protected String type;
	protected Dice bonus; 
	
	
	/**
	 * The constructor of DiceBonusEffect.
	 * @param bonus
	 * @param type
	 */
	
	public DiceBonusEffect (Dice bonus, String type)
	{
		this.type = type;
		this.bonus = bonus;
	}
	
	
	/**
	 * This Method updates the attribute bonus of the current player after the action "CollectCard" on a CharacterCard 
	 * These cards have a Dice bonus if you collect a particular kind of cards ("type").
	 */
	public void activateEffect(Game game) {
		
		game.getCurrentPlayer().updateBonus(type, bonus);
	
	}

	
	
	public Dice getBonus() {
		return bonus;
	}


	public int getBonusValue(){
		
		return bonus.getValue();
	}
	
	public String getType() {
		return type;
	}	
	
	
	

}
