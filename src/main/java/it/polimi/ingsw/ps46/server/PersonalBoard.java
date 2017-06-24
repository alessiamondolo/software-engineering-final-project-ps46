package it.polimi.ingsw.ps46.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

public class PersonalBoard implements Serializable {
	
	private static final long serialVersionUID = -2644034889315743722L;

	private final static int MAX_NUMBER_OF_CARDS = 6;
	
	private ArrayList<TerritoryCard> territoryCards = new ArrayList<TerritoryCard>();
	private ArrayList<VentureCard> ventureCards = new ArrayList<VentureCard>();
	private ArrayList<BuildingCard> buildingCards = new ArrayList<BuildingCard>();
	private ArrayList<CharacterCard> characterCards = new ArrayList<CharacterCard>();
	
	private ResourceSet playerResources = null;
	
	private Dice productionValue;
	private Dice harvestValue;
	private ResourceSet gainedFromPersonalBoardProduction;
	private ResourceSet gainedFromPersonalBoardHarvest;
	
	private LinkedHashMap<Integer, Integer> victoryPointsFromTerritoryCards = new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<Integer, Integer> victoryPointsFromCharacterCards = new LinkedHashMap<Integer, Integer>();
	
	
	public PersonalBoard(Dice productionValue, Dice harvestValue, ResourceSet gainedFromPersonalBoardProduction, ResourceSet gainedFromPersonalBoardHarvest,
			LinkedHashMap<Integer, Integer> victoryPointsFromTerritoryCards, LinkedHashMap<Integer, Integer> victoryPointsFromCharacterCards) {
		
		this.setProductionValue(productionValue);
		this.setHarvestValue(harvestValue);
		this.setGainedFromPersonalBoardHarvest(gainedFromPersonalBoardHarvest);
		this.setGainedFromPersonalBoardProduction(gainedFromPersonalBoardProduction);
		this.setVictoryPointsFromTerritoryCards(victoryPointsFromTerritoryCards);
		this.setVictoryPointsFromCharacterCards(victoryPointsFromCharacterCards);
		
	}
	
	/**
	 * Sets a value to attribute territoryCards. 
	 * @param newTerritoryCards 
	 */
	public void putTerritoryCardInPlayerSet(TerritoryCard newTerritoryCard) { //eccezione?!
		if (territoryCards.size() < MAX_NUMBER_OF_CARDS)
		{
			territoryCards.add(newTerritoryCard);
		}
		//else ECCEZIONE?! return -1 ?boh	
			
	}
	
	public void putCharacterCardInPlayerSet(CharacterCard newCharacterCard) { //eccezione?!
		if (characterCards.size() < MAX_NUMBER_OF_CARDS)
		{
			characterCards.add(newCharacterCard);
		}
		// else ECCEZIONE?! return -1 ?boh	
			
	}
		
	public void putVentureCardInPlayerSet(VentureCard newVentureCard) { //eccezione?!
		if (ventureCards.size() < MAX_NUMBER_OF_CARDS)
		{
			ventureCards.add(newVentureCard);
		}
		//else ECCEZIONE?! return -1 ?boh	
			
	}
	
	
	public void putBuildingCardInPlayerSet(BuildingCard newBuildingCard) { //eccezione?!
		if (buildingCards.size() < MAX_NUMBER_OF_CARDS)
		{
			buildingCards.add(newBuildingCard);
		}
		// else ECCEZIONE?! return -1 ?boh	
			
	}
	

	/**
	 * Returns territoryCards.
	 * @return territoryCards 
	 */
	public  TerritoryCard getTerritoryCards(int index) {
		return territoryCards.get(index);
		
	}
	
	public ArrayList<TerritoryCard> getTerritoryDeck()
	{
		return territoryCards;
		
	}


	/**
	 * Returns ventureCards.
	 * @return ventureCards 
	 */
	public VentureCard getVentureCards(int index) {
		return ventureCards.get(index);
		
	}

	public ArrayList<VentureCard> getVentureDeck()
	{
		return ventureCards;
	}
	
	/**
	 * Returns buildingCards.
	 * @return buildingCards 
	 */
	public BuildingCard getBuildingCards(int index) {
		return buildingCards.get(index);
	}

	public ArrayList<BuildingCard> getBuildingDeck()
	{
		return buildingCards;
		
	}
	
	/**
	 * Returns characterCards.
	 * @return characterCards 
	 */
	public CharacterCard getCharacterCards(int index) {
		return characterCards.get(index);
	}
	
	public ArrayList<CharacterCard> getCharacterDeck()
	{
		return characterCards;
		
	}


	/**
	 * Returns resources.
	 * @return resources 
	 */
	public ResourceSet getPlayerResourceSet() {
		return playerResources;
	}

	
	public void setResources(ResourceSet resources) {
		playerResources = resources;
	}

	public Dice getProductionValue() {
		return productionValue;
	}

	private void setProductionValue(Dice productionValue) {
		this.productionValue = productionValue;
	}

	public Dice getHarvestValue() {
		return harvestValue;
	}

	private void setHarvestValue(Dice harvestValue) {
		this.harvestValue = harvestValue;
	}

	public ResourceSet getGainedFromPersonalBoardProduction() {
		return gainedFromPersonalBoardProduction;
	}

	private void setGainedFromPersonalBoardProduction(ResourceSet gainedFromPersonalBoardProduction) {
		this.gainedFromPersonalBoardProduction = gainedFromPersonalBoardProduction;
	}

	public ResourceSet getGainedFromPersonalBoardHarvest() {
		return gainedFromPersonalBoardHarvest;
	}

	private void setGainedFromPersonalBoardHarvest(ResourceSet gainedFromPersonalBoardHarvest) {
		this.gainedFromPersonalBoardHarvest = gainedFromPersonalBoardHarvest;
	}

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
	
}
