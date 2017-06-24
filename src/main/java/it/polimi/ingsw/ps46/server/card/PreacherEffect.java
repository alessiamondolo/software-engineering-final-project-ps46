package it.polimi.ingsw.ps46.server.card;

import java.io.Serializable;

import it.polimi.ingsw.ps46.server.Game;


/**
 * This Class implements Effect class to describe the behavior of the Card "Preacher".
 * If the boolean of the Malus (present as a player's attribute) is "true" the players movements are different (this effect is developed by the various actions)
 * 
 * @author Andrea.Masi
 */
public class PreacherEffect implements Effect, Serializable {

	private static final long serialVersionUID = 4786651361605521932L;
	
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
