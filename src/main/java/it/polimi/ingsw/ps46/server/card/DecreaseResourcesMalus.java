package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * Description of the Class DecreaseResourcesMalus.
 * 
 * 
 * @author Andrea.Masi
 */
public class DecreaseResourcesMalus extends MalusEffect {

	private ResourceSet decreasedResources;
	
	
	public DecreaseResourcesMalus() {
		
		name = "DecreaseResourcesMalus";
		decreasedResources = null;
	}
	
	public DecreaseResourcesMalus(String name, ResourceSet decreasedResources){
		
		this.name = name;
		this.decreasedResources = decreasedResources;
	}
	
	

	public ResourceSet getDecreasedResources() {
		return decreasedResources;
	}
}