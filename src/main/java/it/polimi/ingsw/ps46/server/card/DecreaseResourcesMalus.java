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

	private static final long serialVersionUID = -6258564858523810922L;
	
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
	
	
	/**
	 * This constructor is used to do a deep clone of an existing decreaseRescourcesMalus.
	 * 
	 * @param decreaseResourcesMalusToBeCloned
	 */
	public DecreaseResourcesMalus(DecreaseResourcesMalus decreaseResourcesMalusToBeCloned){
		String clonedName = decreaseResourcesMalusToBeCloned.getName();
		ResourceSet clonedResourceSet = new ResourceSet(decreaseResourcesMalusToBeCloned.getDecreasedResources());
		
		name = clonedName;
		decreasedResources = clonedResourceSet;
	}
	

	@Override
	public void activationMalus(Game game){
		
		game.getCurrentPlayer().setDecreaseResourcesMalus(this);
	}
	
	/**
	 * Getter of the class, return a ResourceSet
	 * @return decreasedResources
	 */

	public ResourceSet getDecreasedResources() {
		return decreasedResources;
	}
	
	@Override
	public String toString() {
		return "Name: " + name  + ", decreased resources: " + decreasedResources;
	}
	
}