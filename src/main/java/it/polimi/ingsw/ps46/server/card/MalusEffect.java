package it.polimi.ingsw.ps46.server.card;


/**
 * Description of the Class MalusEffect.
 * 
 * @author Andrea.Masi
 */

public abstract class MalusEffect {
	
	protected String name;
	private boolean isActive = false;
	
	
	public void activationMalus(){
		isActive = true;
	}
	

	public boolean getIfActive() {
		return isActive;
	}
	
	public String getName() {
		return name;
	}

}
