package it.polimi.ingsw.ps46.server.resources;


/**
 * ResourceFactory is a factory that can be used to create Resource objects.
 * It takes as parameters the resource type, that will be used to pick the right builder for the resource,
 * and the quantity of the resource.
 * 
 * @author Alessia Mondolo
 */
public class ResourcesFactory {
	
	/**
	 * getResource builds a new Resource of the type specified in the parameter resourceType.
	 * If there is no Class for the resourceType passed as parameter, it will return a null value.
	 * 
	 * @param resourceType
	 * @param quantity
	 */
	public Resource getResource(String resourceType, int quantity) {
		
		if(resourceType == null)
			return null;
		
		else if(resourceType == "Wood")
			return new Wood(quantity);
		
		else if(resourceType == "Stones")
			return new Stones(quantity);
		
		else if(resourceType == "Servants")
			return new Servants(quantity);
		
		else if(resourceType == "Money")
			return new Money(quantity);
		
		else if(resourceType == "FaithPoints")
			return new FaithPoints(quantity);
		
		else if(resourceType == "MilitaryPoints")
			return new MilitaryPoints(quantity);
		
		else if(resourceType == "VictoryPoints")
			return new VictoryPoints(quantity);
		
		return null;
		
	}
	
}
