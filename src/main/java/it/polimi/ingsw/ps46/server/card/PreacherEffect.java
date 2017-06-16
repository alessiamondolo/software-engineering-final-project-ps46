package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;


/**
 * Description of the Class PreacherEffect.
 * 
 * This Class implements Effect class to describe the behavior of the Card "Preacher".
 * If the boolean of the malus (present as a player's attribute) is "true" the players movements are differents (this effect is developed by the various actions)
 * 
 * @author Andrea.Masi
 */
public class PreacherEffect implements Effect{

	private boolean malusEffect;
	
	public PreacherEffect(boolean malusEffect)
	{
		this.malusEffect = malusEffect;
	}
	
	
	public void activateEffect(Game game) {
		game.getCurrentPlayer().setPreacherEffect(malusEffect);
		
	}


	public boolean isMalusEffect() {
		return malusEffect;
	}
}
