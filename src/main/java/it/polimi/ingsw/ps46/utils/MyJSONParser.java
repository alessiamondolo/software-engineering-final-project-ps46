
package it.polimi.ingsw.ps46.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.BonusTile;
import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Tower;
import it.polimi.ingsw.ps46.server.TowerFloor;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesAtFinalMalus;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.card.DiceBonusEffect;
import it.polimi.ingsw.ps46.server.card.DiceBonusEffectDiscounted;
import it.polimi.ingsw.ps46.server.card.DiceMalusEffect;
import it.polimi.ingsw.ps46.server.card.Effect;
import it.polimi.ingsw.ps46.server.card.ExchageResourcesEffect;
import it.polimi.ingsw.ps46.server.card.ExcommunicationTile;
import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;
import it.polimi.ingsw.ps46.server.card.GenericMalusEffect;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesByElementsEffect;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesEffect;
import it.polimi.ingsw.ps46.server.card.LeaderCard;
import it.polimi.ingsw.ps46.server.card.MalusEffect;
import it.polimi.ingsw.ps46.server.card.PreacherEffect;
import it.polimi.ingsw.ps46.server.card.SetValueFamilyMemberEffect;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.ResourcesFactory;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;

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
        Iterator<?> i = resourceSetArray.iterator();
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
		
		//Parsing of type field
        String type = (String) actionSpaceJSON.get("type");
        
      //Parsing of id field
        int id = ((Long) actionSpaceJSON.get("id")).intValue();
		
		//BEGIN of parsing of requiredFamilyMemberValue field
		JSONObject requiredFamilyMemberValueJSON = (JSONObject) actionSpaceJSON.get("requiredFamilyMemberValue");
    	Dice requiredFamilyMemberValue = buildDice(requiredFamilyMemberValueJSON);
    	//END of parsing of requiredFamilyMemberValue field
    	
    	//Parsing of maxOnePlayer field
    	boolean maxOnePlayer = (boolean) actionSpaceJSON.get("maxOnePlayer");
    	
    	//BEGIN of parsing of action space's effect
    	JSONObject effect = (JSONObject) actionSpaceJSON.get("effectOfActionSpace");
    	JSONArray additionalResourcesArray = (JSONArray) effect.get("additionalResources");
    	ResourceSet resourceSet = buildResourceSet(additionalResourcesArray);
        IncreaseResourcesEffect effectOfActionSpace = new IncreaseResourcesEffect(resourceSet);
    	//END of parsing of action space's effect
        ActionSpace actionSpace = new ActionSpace(type, id, requiredFamilyMemberValue, maxOnePlayer, effectOfActionSpace);
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
        Iterator<?> i = floorsArray.iterator();
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
	
	
	
	/**
	 * Builds a new Dice object from a JSONObject.
	 * @param diceJSON
	 * @return Dice object
	 */
	public Dice buildDice(JSONObject diceJSON) {
		int value = ((Long) diceJSON.get("value")).intValue();
    	Dice dice = new Dice(value);
    	return dice;
	}
	
	
	
	/**
	 * Builds a new IncreaseResourcesEffect object from a JSONObject.
	 * @param effectJSON
	 * @return IncreaseResourcesEffect object
	 */
	public IncreaseResourcesEffect buildIncreaseResourcesEffect(JSONObject effectJSON) {
		JSONArray additionalResourcesArray = (JSONArray) effectJSON.get("additionalResources");
        ResourceSet additionalResources = buildResourceSet(additionalResourcesArray);
        IncreaseResourcesEffect effect = new IncreaseResourcesEffect(additionalResources);
        return effect;
	}
	
	
	
	/**
	 * Builds a new ExchageResourcesEffect object from a JSONObject.
	 * @param effectJSON
	 * @return ExchageResourcesEffect object
	 */
	public ExchageResourcesEffect buildExchageResourcesEffect(JSONObject effectJSON) {
		JSONArray additionalResourcesArray = (JSONArray) effectJSON.get("requiredResources");
		ResourceSet requiredResources = buildResourceSet(additionalResourcesArray);
		additionalResourcesArray = (JSONArray) effectJSON.get("gainedResources");
		ResourceSet gainedResources = buildResourceSet(additionalResourcesArray);
		ExchageResourcesEffect effect = new ExchageResourcesEffect(requiredResources, gainedResources);
		return effect;
	}
	
	
	
	/**
	 * Builds a new IncreaseResourcesByElementsEffect object from a JSONObject.
	 * @param effectJSON
	 * @return IncreaseResourcesByElementsEffect object
	 */
	public IncreaseResourcesByElementsEffect buildIncreaseResourcesByElementsEffect(JSONObject effectJSON) {
		JSONArray additionalResourcesArray = (JSONArray) effectJSON.get("additionalResources");
        ResourceSet additionalResources = buildResourceSet(additionalResourcesArray);
        String type = (String) effectJSON.get("type");
        IncreaseResourcesByElementsEffect effect = new IncreaseResourcesByElementsEffect(additionalResources, type);
        return effect;
	}
	
	
	
	/**
	 * Builds a new ExtraMoveEffect object from a JSONObject.
	 * @param effectJSON
	 * @return ExtraMoveEffect object
	 */
	public ExtraMoveEffect buildExtraMoveEffect(JSONObject effectJSON) {
		String extraMoveType = (String) effectJSON.get("extraMoveType");
		String whichActionSpace = (String) effectJSON.get("whichActionSpace");
		JSONObject diceJSON = (JSONObject) effectJSON.get("extraMoveValue");
		Dice extraMoveValue = buildDice(diceJSON);
		boolean resourcesDiscounted = (boolean) effectJSON.get("resourcesDiscounted");
		JSONArray additionalResourcesArray = (JSONArray) effectJSON.get("additionalResources");
        ResourceSet additionalResources = buildResourceSet(additionalResourcesArray);
		ExtraMoveEffect effect = new ExtraMoveEffect(extraMoveType, whichActionSpace, extraMoveValue, resourcesDiscounted, 
				additionalResources);
		return effect;
	}
	
	
	
	/**
	 * Builds a new DiceBonusEffect object from a JSONObject.
	 * @param effectJSON
	 * @return DiceBonusEffect object
	 */
	public DiceBonusEffect buildDiceBonusEffect(JSONObject effectJSON) {
		String type = (String) effectJSON.get("type");
		JSONObject diceJSON = (JSONObject) effectJSON.get("bonus");
		Dice bonus = buildDice(diceJSON);
		DiceBonusEffect effect = new DiceBonusEffect(bonus, type);
		return effect;
	}
	
	
	
	/**
	 * Builds a new DiceBonusEffectDiscounted object from a JSONObject.
	 * @param effectJSON
	 * @return DiceBonusEffectDiscounted object
	 */
	public DiceBonusEffectDiscounted buildDiceBonusEffectDiscounted(JSONObject effectJSON) {
		String type = (String) effectJSON.get("type");
		JSONObject diceJSON = (JSONObject) effectJSON.get("bonus");
		Dice bonus = buildDice(diceJSON);
		JSONArray resourcesDiscountedArray = (JSONArray) effectJSON.get("resourcesDiscounted");
        ResourceSet resourcesDiscounted = buildResourceSet(resourcesDiscountedArray);
        boolean doubleChoice = (boolean) effectJSON.get("doubleChoice");
		DiceBonusEffectDiscounted effect = new DiceBonusEffectDiscounted(bonus, type, resourcesDiscounted, doubleChoice);
		return effect;
	}
	
	
	
	/**
	 * 
	 */
	public SetValueFamilyMemberEffect buildSetValueFamilyMemberEffect(JSONObject effectJSON) {
		String whichFamilyMember = (String) effectJSON.get("whichFamilyMember");
		JSONObject diceJSON = (JSONObject) effectJSON.get("newValue");
		Dice newValue = buildDice(diceJSON);
		SetValueFamilyMemberEffect effect = new SetValueFamilyMemberEffect(whichFamilyMember, newValue);
		return effect;
	}
	
	
	
	public PreacherEffect buildPreacherEffect(JSONObject effectJSON) {
		boolean malusEffect = (boolean) effectJSON.get("malusEffect");
		PreacherEffect effect = new PreacherEffect(malusEffect);
		return effect;
	}



	public BonusTile buildBonusTile(JSONObject bonusTileJSON) {
		int id = ((Long) bonusTileJSON.get("personalBoardNumber")).intValue();
		boolean advancedPersonalBoard = (boolean) bonusTileJSON.get("advancedPersonalBoard");
		JSONObject diceJSON = (JSONObject) bonusTileJSON.get("requiredFamilyMemberValue");
		Dice requiredFamilyMemberValue = buildDice(diceJSON);
		JSONArray gainedFromPersonalBoardProductionArray = (JSONArray) bonusTileJSON.get("gainedFromPersonalBoardProduction");
        ResourceSet gainedFromPersonalBoardProduction = buildResourceSet(gainedFromPersonalBoardProductionArray);
        JSONArray gainedFromPersonalBoardHarvestArray = (JSONArray) bonusTileJSON.get("gainedFromPersonalBoardHarvest");
        ResourceSet gainedFromPersonalBoardHarvest = buildResourceSet(gainedFromPersonalBoardHarvestArray);
		BonusTile bonusTile = new BonusTile(id, advancedPersonalBoard, requiredFamilyMemberValue, requiredFamilyMemberValue, 
				gainedFromPersonalBoardProduction, gainedFromPersonalBoardHarvest);
		return bonusTile;
	}
	
	
	
	public LinkedHashMap<Integer, VictoryPoints> buildVictoryPointsMap(JSONArray victoryPointsArray) {
		LinkedHashMap<Integer, VictoryPoints> victoryPointsMap = new LinkedHashMap<Integer, VictoryPoints>();
		Iterator<?> i = victoryPointsArray.iterator();
        while (i.hasNext()) {
            JSONObject victoryPointsJSON = (JSONObject) i.next();
            int numberOfCards = ((Long) victoryPointsJSON.get("numberOfCards")).intValue();
            int points = ((Long) victoryPointsJSON.get("victoryPoints")).intValue();
            VictoryPoints victoryPoints = new VictoryPoints(points);
            victoryPointsMap.put(new Integer(numberOfCards), victoryPoints);
        }
		return victoryPointsMap;
	}
	
	
	
	public LinkedHashMap<Integer, MilitaryPoints> buildRequiredMilitaryPointsForTerritoryCardsMap(JSONArray requiredPointsArray) {
		LinkedHashMap<Integer, MilitaryPoints> victoryPointsMap = new LinkedHashMap<Integer, MilitaryPoints>();
		Iterator<?> i = requiredPointsArray.iterator();
        while (i.hasNext()) {
            JSONObject requiredPointsJSON = (JSONObject) i.next();
            int numberOfCards = ((Long) requiredPointsJSON.get("numberOfCards")).intValue();
            int points = ((Long) requiredPointsJSON.get("militaryPoints")).intValue();
            MilitaryPoints militaryPoints = new MilitaryPoints(points);
            victoryPointsMap.put(new Integer(numberOfCards), militaryPoints);
        }
		return victoryPointsMap;
	}
	
	
	
	public ExcommunicationTile buildExcommunicationTile(JSONObject excommunicationTileJSON) {
		int id = ((Long) excommunicationTileJSON.get("id")).intValue();
		int era = ((Long) excommunicationTileJSON.get("Era")).intValue();
		JSONObject permanentMalusJSON = (JSONObject) excommunicationTileJSON.get("permanentMalus");
		String malusType = (String) permanentMalusJSON.get("malusType");
		String name = (String) permanentMalusJSON.get("name");
		MalusEffect malusEffect = null;
		switch(malusType) {
		case "DecreaseResourcesMalus" :
			JSONArray decreasedResourcesArray = (JSONArray) permanentMalusJSON.get("decreasedResources");
	        ResourceSet decreasedResources = buildResourceSet(decreasedResourcesArray);
	        malusEffect = new DecreaseResourcesMalus(name, decreasedResources);
			break;
		case "DiceMalusEffect" :
			String type = (String) permanentMalusJSON.get("type");
			JSONObject diceJSON = (JSONObject) permanentMalusJSON.get("malus");
			Dice malus = buildDice(diceJSON);
			malusEffect = new DiceMalusEffect(name, type, malus);
			break;
		case "GenericMalusEffect" :
			type = (String) permanentMalusJSON.get("type");
			malusEffect = new GenericMalusEffect(name, type);
			break;
		case "DecreaseResourcesAtFinalMalus" :
			decreasedResourcesArray = (JSONArray) permanentMalusJSON.get("decreasedResources");
	        decreasedResources = buildResourceSet(decreasedResourcesArray);
	        String from = (String) permanentMalusJSON.get("from");
	        malusEffect = new DecreaseResourcesAtFinalMalus(name, decreasedResources, from);
	        break;
		default :
			break;
		}
		ExcommunicationTile excommunicationTile = new ExcommunicationTile(id, era, malusEffect);
		return excommunicationTile;
	}



	public LeaderCard buildLeaderCard(JSONObject leaderCardJSON) {
		String cardName = (String) leaderCardJSON.get("cardName");
		Effect leaderEffect = null;
		JSONObject leaderEffectJSON = (JSONObject) leaderCardJSON.get("leaderEffect");
		String effectType = (String) leaderEffectJSON.get("effectType");
		switch(effectType) {
		case "ExtraMoveEffect" :
			leaderEffect = buildExtraMoveEffect(leaderEffectJSON);
			break;
		case "DiceBonusEffect" :
			leaderEffect = buildDiceBonusEffect(leaderEffectJSON);
			break;
		case "IncreaseResourcesEffect" :
			leaderEffect = buildIncreaseResourcesEffect(leaderEffectJSON);
			break;
		case "SetValueFamilyMemberEffect" :
			leaderEffect = buildSetValueFamilyMemberEffect(leaderEffectJSON);
			break;
		}
		boolean isPermanent = (boolean) leaderCardJSON.get("isPermanent");
		LinkedHashMap<String, Integer> requiredCards = new LinkedHashMap<String, Integer>();
		JSONArray requiredCardsArray = (JSONArray) leaderCardJSON.get("requiredCards");
		Iterator<?> iterator = requiredCardsArray.iterator();
        while (iterator.hasNext()) {
        	JSONObject requiredCardsJSON = (JSONObject) iterator.next();
        	String cardType = (String) requiredCardsJSON.get("cardType");
        	int quantity = ((Long) requiredCardsJSON.get("quantity")).intValue();
        	requiredCards.put(cardType, new Integer(quantity));
        }
        JSONArray requiredResourcesArray = (JSONArray) leaderCardJSON.get("requiredResources");
        ResourceSet requiredResources = buildResourceSet(requiredResourcesArray);
        LeaderCard leaderCard = new LeaderCard(cardName, leaderEffect, requiredCards, requiredResources, isPermanent);
		return leaderCard;
	}
	
}
