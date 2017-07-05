package it.polimi.ingsw.ps46.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * TODO COMMENTARE
 * 
 * @author Alessia Mondolo
 */
public class PersonalBoard implements Serializable {
	
	private static final long serialVersionUID = -2644034889315743722L;

	private final static int MAX_NUMBER_OF_CARDS = 6;
	
	private BonusTile bonusTile = null;
	private final LinkedHashMap<Integer, MilitaryPoints> requiredMilitaryPointsForTerritoryCardsMap;
	
	private ArrayList<TerritoryCard> territoryCards = new ArrayList<TerritoryCard>();
	private ArrayList<VentureCard> ventureCards = new ArrayList<VentureCard>();
	private ArrayList<BuildingCard> buildingCards = new ArrayList<BuildingCard>();
	private ArrayList<CharacterCard> characterCards = new ArrayList<CharacterCard>();
	
	private ResourceSet playerResources = null; 
	
	//private LinkedHashMap<Integer, Integer> victoryPointsFromTerritoryCards = new LinkedHashMap<Integer, Integer>(); TODO???
	//private LinkedHashMap<Integer, Integer> victoryPointsFromCharacterCards = new LinkedHashMap<Integer, Integer>(); TODO???
	
	//public PersonalBoard(LinkedHashMap<Integer, Integer> victoryPointsFromTerritoryCards, LinkedHashMap<Integer, Integer> victoryPointsFromCharacterCards) {
	public PersonalBoard() {
		requiredMilitaryPointsForTerritoryCardsMap = new LinkedHashMap<>();
		requiredMilitaryPointsForTerritoryCardsMap.put(3, new MilitaryPoints(3));
		requiredMilitaryPointsForTerritoryCardsMap.put(4, new MilitaryPoints(7));
		requiredMilitaryPointsForTerritoryCardsMap.put(5, new MilitaryPoints(12));
		requiredMilitaryPointsForTerritoryCardsMap.put(6, new MilitaryPoints(18));
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

	/*
	public LinkedHashMap<Integer, Integer> getVictoryPointsFromTerritoryCards() {
		return victoryPointsFromTerritoryCards;
	}

	private void setVictoryPointsFromTerritoryCards(LinkedHashMap<Integer, Integer> victoryPointsFromTerritoryCards) {
		this.victoryPointsFromTerritoryCards = victoryPointsFromTerritoryCards;
	}

	public LinkedHashMap<Integer, Integer> getVictoryPointsFromCharacterCards() {
		return victoryPointsFromCharacterCards;
	}

	private void setVictoryPointsFromCharacterCards(LinkedHashMap<Integer, Integer> victoryPointsFromCharacterCards) {
		this.victoryPointsFromCharacterCards = victoryPointsFromCharacterCards;
	}
	 */ 

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
