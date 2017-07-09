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
		
		if(resourceType.equals("Wood"))
			return new Wood(quantity);
		
		else if(resourceType.equals("Stones"))
			return new Stones(quantity);
		
		else if(resourceType.equals("Servants"))
			return new Servants(quantity);
		
		else if(resourceType.equals("Money"))
			return new Money(quantity);
		
		else if(resourceType.equals("FaithPoints"))
			return new FaithPoints(quantity);
		
		else if(resourceType.equals("MilitaryPoints"))
			return new MilitaryPoints(quantity);
		
		else if(resourceType.equals("VictoryPoints"))
			return new VictoryPoints(quantity);
		
		else if(resourceType.equals("CouncilPrivilege"))
			return new CouncilPrivilege(quantity);
		
		else return null;
		
	}
	
public Resource getResourceByDifference(Resource beforeResource, Resource afterResource) {
		

		if(beforeResource.getId().equals("Wood") && afterResource.getId().equals("Wood")) {
			Wood beforeWood = new Wood(beforeResource.getQuantity());
			Wood afterWood = new Wood(afterResource.getQuantity());
			return new Wood(beforeWood, afterWood);
		}
		
		else if(beforeResource.getId().equals("Stones") && afterResource.getId().equals("Stones")) {
			Stones beforeStones = new Stones(beforeResource.getQuantity());
			Stones afterStones = new Stones(afterResource.getQuantity());
			return new Stones(beforeStones, afterStones);
		}
		
		else if(beforeResource.getId().equals("Servants") && afterResource.getId().equals("Servants")) {
			Servants beforeServants = new Servants(beforeResource.getQuantity());
			Servants afterServants = new Servants(afterResource.getQuantity());
			return new Servants(beforeServants, afterServants);
		}
		
		else if(beforeResource.getId().equals("Money") && afterResource.getId().equals("Money")) {
			Money beforeMoney = new Money(beforeResource.getQuantity());
			Money afterMoney = new Money(afterResource.getQuantity());
			return new Money(beforeMoney, afterMoney);
		}
		
		else if(beforeResource.getId().equals("FaithPoints") && afterResource.getId().equals("FaithPoints")) {
			FaithPoints beforeFaithPoints = new FaithPoints(beforeResource.getQuantity());
			FaithPoints afterFaithPoints = new FaithPoints(afterResource.getQuantity());
			return new FaithPoints(beforeFaithPoints, afterFaithPoints);
		}
		
		else if(beforeResource.getId().equals("MilitaryPoints") && afterResource.getId().equals("MilitaryPoints")) {
			MilitaryPoints beforeMilitaryPoints = new MilitaryPoints(beforeResource.getQuantity());
			MilitaryPoints afterMilitaryPoints = new MilitaryPoints(afterResource.getQuantity());
			return new MilitaryPoints(beforeMilitaryPoints, afterMilitaryPoints);
		}
		
		else if(beforeResource.getId().equals("VictoryPoints") && afterResource.getId().equals("VictoryPoints")) {
			VictoryPoints beforeVictoryPoints = new VictoryPoints(beforeResource.getQuantity());
			VictoryPoints afterVictoryPoints = new VictoryPoints(afterResource.getQuantity());
			return new VictoryPoints(beforeVictoryPoints, afterVictoryPoints);
		}
		
		else if(beforeResource.getId().equals("CouncilPrivilege") && afterResource.getId().equals("CouncilPrivilege")) {
			CouncilPrivilege beforeCouncilPrivilage = new CouncilPrivilege(beforeResource.getQuantity());
			CouncilPrivilege afterCouncilPrivilage = new CouncilPrivilege(afterResource.getQuantity());
			return new CouncilPrivilege(beforeCouncilPrivilage, afterCouncilPrivilage);
		}
		else 
			return null;
		
	}
	
}
