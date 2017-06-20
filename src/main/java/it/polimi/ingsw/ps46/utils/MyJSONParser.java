
package it.polimi.ingsw.ps46.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Tower;
import it.polimi.ingsw.ps46.server.TowerFloor;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesEffect;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.ResourcesFactory;

public class MyJSONParser {
	
	public MyJSONParser() {
	}
	
	
	
	/**
	 * Builds a new ResourceSet object from a JSONArray.
	 * 
	 * @param resourceSetArray
	 * @return ResourceSet object
	 */
	public ResourceSet buildResourceSet(JSONArray resourceSetArray) {
		
		ResourcesFactory resourcesFactory = new ResourcesFactory();
        List<Resource> resourceList = new ArrayList<Resource>();
        Iterator i = resourceSetArray.iterator();
        while (i.hasNext()) {
            JSONObject resource = (JSONObject) i.next();
            String id = (String)resource.get("id");
            int quantity = ((Long) resource.get("quantity")).intValue();
            Resource newResource = resourcesFactory.getResource(id, quantity);
            resourceList.add(newResource);
        }
        ResourceSet resourceSet = new ResourceSet(resourceList);
		return resourceSet;
		
	}
	
	
	
	/**
	 * Builds a new ActionSpace object from a JSONObject.
	 * 
	 * @param actionSpaceJSON
	 * @return ActionSpace object
	 */
	public ActionSpace buildActionSpace(JSONObject actionSpaceJSON) {
		
		JSONObject requiredFamilyMemberValueJSON = (JSONObject) actionSpaceJSON.get("requiredFamilyMemberValue");
    	int diceValue = ((Long) requiredFamilyMemberValueJSON.get("value")).intValue();
    	Dice requiredFamilyMemberValue = new Dice(diceValue);
    	Boolean maxOnePlayer = ((Boolean) actionSpaceJSON.get("maxOnePlayer"));
    	//BEGIN of parsing of action space's effect
    	JSONObject effect = (JSONObject) actionSpaceJSON.get("effectOfActionSpace");
    	JSONArray additionalResourcesArray = (JSONArray) effect.get("additionalResources");
    	ResourceSet resourceSet = buildResourceSet(additionalResourcesArray);
        IncreaseResourcesEffect effectOfActionSpace = new IncreaseResourcesEffect(resourceSet);
    	//END of parsing of action space's effect
        ActionSpace actionSpace = new ActionSpace(requiredFamilyMemberValue, maxOnePlayer, effectOfActionSpace);
        return actionSpace;
        
	}
	
	
	
	/**
	 * Builds a new Tower object from a JSONObject.
	 * 
	 * @param towerJSON
	 * @return Tower object
	 */
	public Tower buildTower(JSONObject towerJSON) {

		//Parsing of color field
        String color = (String) towerJSON.get("color");

        //BEGIN of parsing of floors field
        JSONArray floorsArray = (JSONArray) towerJSON.get("floors");
        ArrayList<TowerFloor> floors = new ArrayList<TowerFloor>();
        Iterator i = floorsArray.iterator();
        while (i.hasNext()) {
        	JSONObject floorJSON = (JSONObject) i.next();
        	int floor = ((Long) floorJSON.get("floor")).intValue();
        	JSONObject actionSpaceJSON = (JSONObject) floorJSON.get("action");
            ActionSpace actionSpace = buildActionSpace(actionSpaceJSON);
            TowerFloor towerFloor = new TowerFloor(floor, actionSpace);
            floors.add(towerFloor);
        }
        //END of parsing of floors field
        
        Tower tower = new Tower(color, floors);
        return tower;   
        
	}
	
}
