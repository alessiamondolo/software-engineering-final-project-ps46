package it.polimi.ingsw.ps46.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.utils.MyJSONParser;

/**
 * TODO COMMENTARE
 * 
 * @author Alessia Mondolo
 */
public class PersonalBoard implements Serializable {
	
	private static final long serialVersionUID = -2644034889315743722L;

	private final static int MAX_NUMBER_OF_CARDS = 6;
	
	private BonusTile bonusTile = null;
	private LinkedHashMap<Integer, MilitaryPoints> requiredMilitaryPointsForTerritoryCardsMap;
	
	private ArrayList<TerritoryCard> territoryCards = new ArrayList<TerritoryCard>();
	private ArrayList<VentureCard> ventureCards = new ArrayList<VentureCard>();
	private ArrayList<BuildingCard> buildingCards = new ArrayList<BuildingCard>();
	private ArrayList<CharacterCard> characterCards = new ArrayList<CharacterCard>();
	
	private ResourceSet playerResources = null; 
	
	public PersonalBoard() {
		requiredMilitaryPointsForTerritoryCardsMap = new LinkedHashMap<>();

		configRequiredMilitaryPointsForTerritoryCardsMap();
    	System.out.println(requiredMilitaryPointsForTerritoryCardsMap);
	}
	
	private void configRequiredMilitaryPointsForTerritoryCardsMap(){
		JSONParser parser = new JSONParser();
		MyJSONParser myJSONParser = new MyJSONParser();

		try {
        	Object obj = parser.parse(new FileReader("./src/main/java/it/polimi/ingsw/ps46/server/config/RequiredMilitaryPointsForTerritoryCards.json"));
        	JSONArray requiredPointsArray = (JSONArray) obj;        	
        	requiredMilitaryPointsForTerritoryCardsMap = myJSONParser.buildRequiredMilitaryPointsForTerritoryCardsMap(requiredPointsArray);
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}
    
	//TODO se non riesce a mettere la carta nel set è perchè ho troppe carte di quel tipo ---> bisogna avvisare il giocatore
	
	public boolean putTerritoryCardInPlayerSet(TerritoryCard newTerritoryCard) { 
		if (territoryCards.size() < MAX_NUMBER_OF_CARDS)
		{
			territoryCards.add(newTerritoryCard);
			return true;
		}
		else 
			return false;
			
	}
	
	public boolean putCharacterCardInPlayerSet(CharacterCard newCharacterCard) { 
		if (characterCards.size() < MAX_NUMBER_OF_CARDS)
		{
			characterCards.add(newCharacterCard);
			return true;
		}
		else 
			return false;
			
	}
		
	public boolean putVentureCardInPlayerSet(VentureCard newVentureCard) { 
		if (ventureCards.size() < MAX_NUMBER_OF_CARDS)
		{
			ventureCards.add(newVentureCard);
			return true;
		}
		else 
			return false;
			
	}
	
	
	public boolean putBuildingCardInPlayerSet(BuildingCard newBuildingCard) { 
		if (buildingCards.size() < MAX_NUMBER_OF_CARDS)
		{
			buildingCards.add(newBuildingCard);
			return true;
		}
		else 
			return false;
			
	}
	

	/**
	 * Returns territoryCards.
	 * @return territoryCards 
	 */
	
	public ArrayList<TerritoryCard> getTerritoryDeck()
	{
		return territoryCards;
		
	}
	
	/**
	 * Returns ventureCards.
	 * @return ventureCards 
	 */

	public ArrayList<VentureCard> getVentureDeck()
	{
		return ventureCards;
	}
	
	/**
	 * Returns buildingCards.
	 * @return buildingCards 
	 */

	public ArrayList<BuildingCard> getBuildingDeck()
	{
		return buildingCards;
		
	}
	
	/**
	 * Returns characterCards.
	 * @return characterCards 
	 */
	
	public ArrayList<CharacterCard> getCharacterDeck()
	{
		return characterCards;
		
	}


	public ResourceSet getPlayerResourceSet() {
		return playerResources;
	}

	
	public void setResources(ResourceSet resources) {
		playerResources = resources;
	}

	public BonusTile getBonusTile() {
		return bonusTile;
	}


	public void setBonusTile(BonusTile bonusTile) {
		this.bonusTile = bonusTile;
	}
	
	public LinkedHashMap<Integer, MilitaryPoints> getRequiredMilitaryPointsForTerritoryCardsMap() {
		return requiredMilitaryPointsForTerritoryCardsMap;
	}
	
}
