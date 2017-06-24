package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * This Class extends the abstract class MalusEffect.
 * It's a Malus of an excommunicationTile used to decrease the resources obtained by cards and actionSpaces.
 * 
 * @author Andrea.Masi
 */
public class DecreaseResourcesMalus extends MalusEffect {

	private ResourceSet decreasedResources;
	
	
	public DecreaseResourcesMalus() {
		
		name = "DecreaseResourcesMalus";
		decreasedResources = null;
	}
	
	
	/**
	 * This Constructor creates a new object DecreaseResourcesAtFinalMalus.
	 * Built by .json file.
	 * 
	 * @param name
	 * @param decreasedResources
	 */
	public DecreaseResourcesMalus(String name, ResourceSet decreasedResources){
		
		this.name = name;
		this.decreasedResources = decreasedResources;
	}
	

	@Override
	public void activationMalus(Game game){
		
		game.getCurrentPlayer().getDecreaseResourcesMalus().add(this);
	}
	
	/**
	 * Getter of the class, return a ResourceSet
	 * @return decreasedResources
	 */

	public ResourceSet getDecreasedResources() {
		return decreasedResources;
	}
}