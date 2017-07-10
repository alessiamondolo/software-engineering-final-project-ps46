package it.polimi.ingsw.ps46.server.card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.utils.MyJSONParser;

/**
 * 
 * 
 * @author Alessia Mondolo
 */

public class FactoryCards {
	
	private static FactoryCards factoryCards = null;
	private MyJSONParser myJSONParser = new MyJSONParser();
	private String configFilesPath = "./src/main/java/it/polimi/ingsw/ps46/server/config/";
	
	private FactoryCards() {
	}
	
	
	/**
	 * Creates a singleton of the FactoryCards if it doesn't exist, 
	 * otherwise it returns the reference to the existing FactoryCards.
	 */
	public static FactoryCards getFactoryCards() {
		if (factoryCards == null) 
			factoryCards = new FactoryCards();
		return factoryCards;
	}
	
	
	
	/**
	 * Reads the configuration file for Territory Cards and creates the deck of territory cards.
	 */
	public ArrayList<TerritoryCard> createTerritoryCards(String configFile) {
		
		ArrayList<TerritoryCard> cardsDeck = new ArrayList<TerritoryCard>();
		
		JSONParser parser = new JSONParser();
		
        try {

        	Object obj = parser.parse(new FileReader(configFilesPath + configFile));
        	
        	JSONArray territoryCards = (JSONArray) obj;
        	Iterator<?> iterator = territoryCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");
                
                //Parsing of cardEra field
                int cardEra = ((Long) jsonObject.get("cardEra")).intValue();
                
                //BEGIN of parsing of immediateEffect field
                JSONObject effectJSON = (JSONObject) jsonObject.get("immediateEffects");
                IncreaseResourcesEffect immediateEffects = myJSONParser.buildIncreaseResourcesEffect(effectJSON);
                //END of parsing of immediateEffect field
                
                //BEGIN of parsing of permanentEffect field
                effectJSON = (JSONObject) jsonObject.get("permanentEffects");
                IncreaseResourcesEffect permanentEffects = myJSONParser.buildIncreaseResourcesEffect(effectJSON);
                //END of parsing of permanentEffect field
                
                //BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                ResourceSet cost = myJSONParser.buildResourceSet(costArray);
                //END of parsing of cost field
                
                //BEGIN of parsing of harvestValue field
                JSONObject harvestValueJSON = (JSONObject) jsonObject.get("harvestValue");
            	Dice harvestValue = myJSONParser.buildDice(harvestValueJSON);
                //END of parsing of harvestValue field
                
                TerritoryCard territoryCard = new TerritoryCard(cardName, cardEra, immediateEffects, permanentEffects, cost, harvestValue);
            	cardsDeck.add(territoryCard);
        		
        	}
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

		return cardsDeck;
		
	}
	
	
	
	/**
	 * Reads the configuration file for Building Cards and creates the deck of territory cards.
	 */
	public ArrayList<BuildingCard> createBuildingCards(String configFile) {
		
		ArrayList<BuildingCard> cardsDeck = new ArrayList<BuildingCard>();
			
		JSONParser parser = new JSONParser();
				
        try {

        	Object obj = parser.parse(new FileReader(configFilesPath + configFile));
        	
        	JSONArray buildingCards = (JSONArray) obj;
        	Iterator<?> iterator = buildingCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");
                
                //Parsing of cardEra field
                int cardEra = ((Long) jsonObject.get("cardEra")).intValue();

                //BEGIN of parsing of immediateEffect field
                JSONObject effectJSON = (JSONObject) jsonObject.get("immediateEffects");
                IncreaseResourcesEffect immediateEffects = myJSONParser.buildIncreaseResourcesEffect(effectJSON);
                //END of parsing of immediateEffect field
                
                //Parsing of doubleChoice field
                Boolean doubleChoice = ((Boolean) jsonObject.get("doubleChoice"));
                
                //BEGIN of parsing of permanentEffect field
                effectJSON = (JSONObject) jsonObject.get("permanentEffects");
                String effectType = (String) effectJSON.get("effectType");
                Effect permanentEffects = null;
                switch(effectType) {
                case "ExchageResourcesEffect" : 
                	permanentEffects = myJSONParser.buildExchageResourcesEffect(effectJSON);
                	break;
                case "IncreaseResourcesByElementsEffect" :
                	permanentEffects = myJSONParser.buildIncreaseResourcesByElementsEffect(effectJSON);
                	break;
                }
                //END of parsing of permanentEffect field
				
				//BEGIN of parsing of permanentEffectTwo field
				ExchageResourcesEffect permanentEffectsTwo;
				if(doubleChoice == false) {
					permanentEffectsTwo = null;
				}
				else {
					effectJSON = (JSONObject) jsonObject.get("permanentEffectsTwo");
					permanentEffectsTwo = myJSONParser.buildExchageResourcesEffect(effectJSON);
				}
                //END of parsing of permanentEffectTwo field
                
				//BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                ResourceSet cost = myJSONParser.buildResourceSet(costArray);
                //END of parsing of cost field
                
                //BEGIN of parsing of productionValue field
            	JSONObject productionValueJSON = (JSONObject) jsonObject.get("productionValue");
            	Dice productionValue = myJSONParser.buildDice(productionValueJSON);
                //END of parsing of harvestValue field

                BuildingCard buildingCard = new BuildingCard(cardName, cardEra, immediateEffects, doubleChoice, permanentEffects, permanentEffectsTwo, cost, productionValue);
                cardsDeck.add(buildingCard);
        		
    	}
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

		return cardsDeck;
		
	}

	
	
	/**
	 * Reads the configuration file for Character Cards and creates the deck of territory cards.
	 */
	public ArrayList<CharacterCard> createCharacterCards(String configFile) {
		
		ArrayList<CharacterCard> cardsDeck = new ArrayList<CharacterCard>();
		
		JSONParser parser = new JSONParser();

        try {

        	Object obj = parser.parse(new FileReader(configFilesPath + configFile));
        	
        	JSONArray territoryCards = (JSONArray) obj;
        	Iterator<?> iterator = territoryCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");

                //Parsing of cardEra field
                int cardEra = ((Long) jsonObject.get("cardEra")).intValue();

                //BEGIN of parsing of immediateEffect field
                JSONObject effectJSON = (JSONObject) jsonObject.get("immediateEffects");
                String effectType = (String) effectJSON.get("effectType");
                Effect immediateEffects = null;
                switch(effectType) {
                case "IncreaseResourcesEffect" : 
                	immediateEffects = myJSONParser.buildIncreaseResourcesEffect(effectJSON);
                	break;
                case "ExtraMoveEffect" :
                	immediateEffects = myJSONParser.buildExtraMoveEffect(effectJSON);
                	break;
                case "IncreaseResourcesByElementsEffect" : 
                	immediateEffects = myJSONParser.buildIncreaseResourcesByElementsEffect(effectJSON);
                	break;
                }
                //END of parsing of immediateEffect field
                
                //BEGIN of parsing of permanentEffect field
                effectJSON = (JSONObject) jsonObject.get("permanentEffects");
                effectType = (String) effectJSON.get("effectType");
                Effect permanentEffects = null;
                switch(effectType) {
                case "DiceBonusEffect" :
                	permanentEffects = myJSONParser.buildDiceBonusEffect(effectJSON);
                	break;
                case "DiceBonusEffectDiscounted" :
                	permanentEffects = myJSONParser.buildDiceBonusEffectDiscounted(effectJSON);
                	break;
                case "PreacherEffect" :
                	permanentEffects = myJSONParser.buildPreacherEffect(effectJSON);
                	break;
                }
                //END of parsing of permanentEffect field
                
                //BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                ResourceSet cost = myJSONParser.buildResourceSet(costArray);
                //END of parsing of cost field
                
                CharacterCard characterCard = new CharacterCard(cardName, cardEra, immediateEffects, permanentEffects, cost);
                cardsDeck.add(characterCard);
        		
        	}
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

		return cardsDeck;
		
	}


	
	/**
	 * Reads the configuration file for Venture Cards and creates the deck of territory cards.
	 */
	public ArrayList<VentureCard> createVentureCards(String configFile) {
		
		ArrayList<VentureCard> cardsDeck = new ArrayList<VentureCard>();
		
		JSONParser parser = new JSONParser();

        try {

        	Object obj = parser.parse(new FileReader(configFilesPath + configFile));
        	
        	JSONArray territoryCards = (JSONArray) obj;
        	Iterator<?> iterator = territoryCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");

                //Parsing of cardEra field
                int cardEra = ((Long) jsonObject.get("cardEra")).intValue();

                //BEGIN of parsing of immediateEffect field
                JSONObject effectJSON = (JSONObject) jsonObject.get("immediateEffects");
                String effectType = (String) effectJSON.get("effectType");
                Effect immediateEffects = null;
                switch(effectType) {
                case "IncreaseResourcesEffect" : 
                	immediateEffects = myJSONParser.buildIncreaseResourcesEffect(effectJSON);
                	break;
                case "ExtraMoveEffect" :
                	immediateEffects = myJSONParser.buildExtraMoveEffect(effectJSON);
                	break;
                }
                //END of parsing of immediateEffect field
                
                //BEGIN of parsing of permanentEffect field
                effectJSON = (JSONObject) jsonObject.get("permanentEffects");
                IncreaseResourcesEffect permanentEffects = myJSONParser.buildIncreaseResourcesEffect(effectJSON);
                //END of parsing of permanentEffect field
                
                //BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                ResourceSet cost = myJSONParser.buildResourceSet(costArray);
                //END of parsing of cost field
                
                //Parsing of doubleCostChoice field
                boolean doubleCostChoice = (boolean) jsonObject.get("doubleCostChoice");
                
                //BEGIN of parsing of costTwo field
                ResourceSet costTwo;
                if(doubleCostChoice == false)
                	costTwo = null;
                else {
                	costArray = (JSONArray) jsonObject.get("costTwo");
                    costTwo = myJSONParser.buildResourceSet(costArray);
                }
                //END of parsing of costTwo field
                
                //BEGIN of parsing of requiredResource field
                JSONArray requiredResourceArray = (JSONArray) jsonObject.get("requiredResource");
                ResourceSet requiredResource = myJSONParser.buildResourceSet(requiredResourceArray);
                //END of parsing of cost field
                
                VentureCard characterCard = new VentureCard(cardName, cardEra, immediateEffects, permanentEffects, cost,
                		doubleCostChoice, costTwo, requiredResource);
                cardsDeck.add(characterCard);
        		
        	}
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

		return cardsDeck;
		
	}

}
