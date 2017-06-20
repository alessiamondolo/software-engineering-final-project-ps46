package it.polimi.ingsw.ps46.server.card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.ResourcesFactory;
import it.polimi.ingsw.ps46.utils.MyJSONParser;

/**
 * 
 * 
 * @author Alessia Mondolo
 */

public class FactoryCards {
	
	private static FactoryCards factoryCards = null;
	private MyJSONParser myJSONParser = new MyJSONParser();
	
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
	public HashSet<TerritoryCard> createTerritoryCards(String configFile) {
		
		HashSet<TerritoryCard> cardsDeck = new HashSet<TerritoryCard>();
		
		JSONParser parser = new JSONParser();
		ResourcesFactory resourcesFactory = new ResourcesFactory();
		
        try {

        	URL url = getClass().getResource(configFile);
        	Object obj = parser.parse(new FileReader(url.getPath()));
        	
        	JSONArray territoryCards = (JSONArray) obj;
        	Iterator iterator = territoryCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");
                
                //Parsing of cardEra field
                int cardEra = ((Long) jsonObject.get("cardEra")).intValue();

                //BEGIN of parsing of immediateEffect field
                JSONObject effect = (JSONObject) jsonObject.get("immediateEffects");
                JSONArray additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> immediateEffectResourceList = new ArrayList<Resource>();
                Iterator i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    immediateEffectResourceList.add(newResource);
                }
                ResourceSet additionalResources = new ResourceSet(immediateEffectResourceList);
                IncreaseResourcesEffect immediateEffects = new IncreaseResourcesEffect(additionalResources);
                //END of parsing of immediateEffect field
                
                //BEGIN of parsing of permanentEffect field
                effect = (JSONObject) jsonObject.get("permanentEffects");
                additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> permanentEffectResourceList = new ArrayList<Resource>();
                i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    permanentEffectResourceList.add(newResource);
                }
                IncreaseResourcesEffect permanentEffects = new IncreaseResourcesEffect(new ResourceSet(permanentEffectResourceList));
                //END of parsing of permanentEffect field
                
                //BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                List<Resource> costResourceList = new ArrayList<Resource>();
                i = costArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    costResourceList.add(newResource);
                }
                ResourceSet cost = new ResourceSet(costResourceList);
                //END of parsing of cost field
                
                //BEGIN of parsing of harvestValue field
                JSONObject harvestValueJSON = (JSONObject) jsonObject.get("harvestValue");
            	int diceValue = ((Long) harvestValueJSON.get("value")).intValue();
            	Dice harvestValue = new Dice(diceValue);
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
	public HashSet<BuildingCard> createBuildingCards(String configFile) {
			
			HashSet<BuildingCard> cardsDeck = new HashSet<BuildingCard>();
			
			JSONParser parser = new JSONParser();
			ResourcesFactory resourcesFactory = new ResourcesFactory();
	
	        try {
	
	        	URL url = getClass().getResource(configFile);
	        	Object obj = parser.parse(new FileReader(url.getPath()));
	        	
	        	JSONArray territoryCards = (JSONArray) obj;
	        	Iterator iterator = territoryCards.iterator();
	        	while (iterator.hasNext()) {
	        		
	        		JSONObject jsonObject = (JSONObject) iterator.next();
	        		
	        		//Parsing of cardName field
	                String cardName = (String) jsonObject.get("cardName");
	                
	                //Parsing of cardEra field
	                int cardEra = ((Long) jsonObject.get("cardEra")).intValue();
	
	                //BEGIN of parsing of immediateEffect field
	                JSONObject effect = (JSONObject) jsonObject.get("immediateEffects");
	                JSONArray additionalResourcesArray = (JSONArray) effect.get("additionalResources");
	                List<Resource> immediateEffectResourceList = new ArrayList<Resource>();
	                Iterator i = additionalResourcesArray.iterator();
	                while (i.hasNext()) {
	                    JSONObject resource = (JSONObject) i.next();
	                    String id = (String)resource.get("id");
	                    int quantity = ((Long) resource.get("quantity")).intValue();
	                    Resource newResource = resourcesFactory.getResource(id, quantity);
	                    immediateEffectResourceList.add(newResource);
	                }
	                ResourceSet additionalResources = new ResourceSet(immediateEffectResourceList);
	                IncreaseResourcesEffect immediateEffects = new IncreaseResourcesEffect(additionalResources);
	                //END of parsing of immediateEffect field
	                
	                Boolean doubleChoice = ((Boolean) jsonObject.get("doubleChoice"));
	                
	                //BEGIN of parsing of permanentEffect field
	                effect = (JSONObject) jsonObject.get("permanentEffects");
	                additionalResourcesArray = (JSONArray) effect.get("requiredResources");
					ResourceSet requiredResources = myJSONParser.buildResourceSet(additionalResourcesArray);
					additionalResourcesArray = (JSONArray) effect.get("gainedResources");
					ResourceSet gainedResources = myJSONParser.buildResourceSet(additionalResourcesArray);
					ExchageResourcesEffect permanentEffects = new ExchageResourcesEffect(requiredResources, gainedResources);
	                //END of parsing of permanentEffect field
					
					//BEGIN of parsing of permanentEffectTwo field
	                effect = (JSONObject) jsonObject.get("permanentEffectsTwo");
	                additionalResourcesArray = (JSONArray) effect.get("requiredResources");
					requiredResources = myJSONParser.buildResourceSet(additionalResourcesArray);
					additionalResourcesArray = (JSONArray) effect.get("gainedResources");
					gainedResources = myJSONParser.buildResourceSet(additionalResourcesArray);
					ExchageResourcesEffect permanentEffectsTwo = new ExchageResourcesEffect(requiredResources, gainedResources);
	                //END of parsing of permanentEffectTwo field
	                
	                //BEGIN of parsing of cost field
	                JSONArray costArray = (JSONArray) jsonObject.get("cost");
	                List<Resource> costResourceList = new ArrayList<Resource>();
	                i = costArray.iterator();
	                while (i.hasNext()) {
	                    JSONObject resource = (JSONObject) i.next();
	                    String id = (String)resource.get("id");
	                    int quantity = ((Long) resource.get("quantity")).intValue();
	                    Resource newResource = resourcesFactory.getResource(id, quantity);
	                    costResourceList.add(newResource);
	                }
	                ResourceSet cost = new ResourceSet(costResourceList);
	                //END of parsing of cost field
	                
	                //BEGIN of parsing of productionValue field
	                JSONObject productionValueJSON = (JSONObject) jsonObject.get("productionValue");
	            	int diceValue = ((Long) productionValueJSON.get("value")).intValue();
	            	Dice productionValue = new Dice(diceValue);
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
	public HashSet<CharacterCard> createCharacterCards(String configFile) {
		/*
		HashSet<CharacterCard> cardsDeck = new HashSet<CharacterCard>();
		
		JSONParser parser = new JSONParser();
		ResourcesFactory resourcesFactory = new ResourcesFactory();

        try {

        	URL url = getClass().getResource(configFile);
        	Object obj = parser.parse(new FileReader(url.getPath()));
        	
        	JSONArray territoryCards = (JSONArray) obj;
        	Iterator iterator = territoryCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");

                //BEGIN of parsing of immediateEffect field
                JSONObject effect = (JSONObject) jsonObject.get("immediateEffects");
                JSONArray additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> immediateEffectResourceList = new ArrayList<Resource>();
                Iterator i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    immediateEffectResourceList.add(newResource);
                }
                ResourceSet additionalResources = new ResourceSet(immediateEffectResourceList);
                IncreaseResourcesEffect immediateEffects = new IncreaseResourcesEffect(additionalResources);
                //END of parsing of immediateEffect field
                
                //BEGIN of parsing of permanentEffect field
                effect = (JSONObject) jsonObject.get("permanentEffects");
                additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> permanentEffectResourceList = new ArrayList<Resource>();
                i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    permanentEffectResourceList.add(newResource);
                }
                IncreaseResourcesEffect permanentEffects = new IncreaseResourcesEffect(new ResourceSet(permanentEffectResourceList));
                //END of parsing of permanentEffect field
                
                //BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                List<Resource> costResourceList = new ArrayList<Resource>();
                i = costArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    costResourceList.add(newResource);
                }
                ResourceSet cost = new ResourceSet(costResourceList);
                //END of parsing of cost field
                
                CharacterCard characterCard = new CharacterCard(cardName, immediateEffects, permanentEffects, cost);
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
		*/
		return null;
	}


	
	/**
	 * Reads the configuration file for Venture Cards and creates the deck of territory cards.
	 */
	public HashSet<VentureCard> createVentureCards(String configFile) {
		/*
		HashSet<VentureCard> cardsDeck = new HashSet<VentureCard>();
		
		JSONParser parser = new JSONParser();
		ResourcesFactory resourcesFactory = new ResourcesFactory();

        try {

        	URL url = getClass().getResource(configFile);
        	Object obj = parser.parse(new FileReader(url.getPath()));
        	
        	JSONArray territoryCards = (JSONArray) obj;
        	Iterator iterator = territoryCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");

                //BEGIN of parsing of immediateEffect field
                JSONObject effect = (JSONObject) jsonObject.get("immediateEffects");
                JSONArray additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> immediateEffectResourceList = new ArrayList<Resource>();
                Iterator i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    immediateEffectResourceList.add(newResource);
                }
                ResourceSet additionalResources = new ResourceSet(immediateEffectResourceList);
                IncreaseResourcesEffect immediateEffects = new IncreaseResourcesEffect(additionalResources);
                //END of parsing of immediateEffect field
                
                //BEGIN of parsing of permanentEffect field
                effect = (JSONObject) jsonObject.get("permanentEffects");
                additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> permanentEffectResourceList = new ArrayList<Resource>();
                i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    permanentEffectResourceList.add(newResource);
                }
                IncreaseResourcesEffect permanentEffects = new IncreaseResourcesEffect(new ResourceSet(permanentEffectResourceList));
                //END of parsing of permanentEffect field
                
                //BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                List<Resource> costResourceList = new ArrayList<Resource>();
                i = costArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    costResourceList.add(newResource);
                }
                ResourceSet cost = new ResourceSet(costResourceList);
                //END of parsing of cost field
                
                VentureCard ventureCard = new VentureCard(cardName, immediateEffects, permanentEffects, cost);
                cardsDeck.add(ventureCard);
        		
        	}
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

		return cardsDeck;
		*/
		return null;
	}

}
