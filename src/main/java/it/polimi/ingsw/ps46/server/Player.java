package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesAtFinalMalus;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.card.DiceMalusEffect;
import it.polimi.ingsw.ps46.server.card.GenericMalusEffect;
import it.polimi.ingsw.ps46.server.card.LeaderCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * This Class is used to create a single instance of player with his ID, Username, Decks, Set of resources, Family members, Bonus and discounts.
 * 
 * @author Andrea.Masi
 */
public class Player {

	private int idPlayer;
	private String username;
	private String color;
	private ResourceSet playerResources;

	private ArrayList<TerritoryCard> territoryCards;
	private ArrayList<VentureCard> ventureCards;
	private ArrayList<BuildingCard> buildingCards;
	private ArrayList<CharacterCard> characterCards;
	
	private ArrayList<LeaderCard> leaderCards;  //TODO completare tutta la lista delle carte i suoi effetti ecc ecc
	
	private PersonalBoard playerPersonaBoard;
	
	private Map <String,FamilyMember> familyMembers;
	
	//private final static int MAXOFFAMILYMEMBERS = 4;
	private final static int MAXNUMBEROFCARDS = 6;

	private Map<String, Dice> bonus;
	private Map<String, ResourceSet> discount;
	private Map<String, ResourceSet> optionalDiscount;
	
	private boolean preacherEffect = false; 
	
	private ArrayList<DiceMalusEffect> diceMalus;
	private ArrayList<GenericMalusEffect> genericMalus;
	private ArrayList<DecreaseResourcesMalus> decreaseResourcesMalus;
	private DecreaseResourcesAtFinalMalus decreaseAtFinalMalus;


	
	/**
	 * The constructor of Player.
	 * 
	 * @param idPlayer
	 * @configurationParam xConfigurationColorOftheFamilyMember, yConfigurationFamilyMember. Used to put the right values by configuration file.
	 */
	public Player(int idPlayer) {
		
		this.idPlayer = idPlayer; 
		
		playerResources = new ResourceSet();

		territoryCards = new ArrayList<TerritoryCard>();
		ventureCards = new ArrayList<VentureCard>();
		buildingCards = new ArrayList<BuildingCard>();
		characterCards = new ArrayList<CharacterCard>();
		leaderCards = new ArrayList<LeaderCard>();

		
		familyMembers = new LinkedHashMap<String,FamilyMember>();
		familyMembers.put("White", new FamilyMember("White"));
		familyMembers.put("Black", new FamilyMember("Black"));
		familyMembers.put("Orange", new FamilyMember("Orange"));
		familyMembers.put("Neutral", new FamilyMember("Neutral"));
		/*
		for (int i = 0; i < MAXOFFAMILYMEMBERS; i++)
		{
			String xConfigurationColorOftheFamilyMember= "WHITE FAMILY MEMBER";
			FamilyMember yConfigurationFamilyMember = new FamilyMember();
			familyMembers.put(xConfigurationColorOftheFamilyMember, yConfigurationFamilyMember);
		}
		*/
		int init = 0;
		Dice initializationDice = new Dice(init);
		bonus = new HashMap<String, Dice>();
		bonus.put("VentureCards", initializationDice);
		bonus.put("BuildingCards", initializationDice);
		bonus.put("VentureCards", initializationDice);
		bonus.put("CharactersCards", initializationDice);
		bonus.put("ProductionActions", initializationDice);
		bonus.put("HarvestActions", initializationDice);
		
		
		discount = new HashMap<String, ResourceSet>();
		optionalDiscount = new HashMap<String, ResourceSet>();
		
		diceMalus = new ArrayList<DiceMalusEffect>();
		genericMalus = new ArrayList<GenericMalusEffect>();
		decreaseResourcesMalus = new ArrayList<DecreaseResourcesMalus>();
		decreaseAtFinalMalus = new DecreaseResourcesAtFinalMalus();
		
		
		/* TENGO QUESTA LISTA PER COMODITA' ---> COSì DA SAPERE IN CHE ERE CI SONO I VARI EFFETTI
		// excommunicationMalus of the first era.
		excommunicationMalus.put("DecreaseResourcesMalus", new DecreaseResourcesMalus() );
		excommunicationMalus.put("DiceMalusEffect", new DiceMalusEffect() );

		
		// excommunicationMalus of the second era.
		excommunicationMalus.put("DiceMalusEffectForCards", new DiceMalusEffect() );
		excommunicationMalus.put("noMoveToMarketSpace", new GenericMalusEffect() );
		excommunicationMalus.put("doubleServantsCost", new GenericMalusEffect() );
		excommunicationMalus.put("passYourFirstMove", new GenericMalusEffect() );
		
		// excommunicationMalus of the third era.
		excommunicationMalus.put("notCountingVictoryPointsFromCards", new GenericMalusEffect() );
		excommunicationMalus.put("loseOneVictoryPointEveryXResource", new DecreaseResourcesMalus() );
		excommunicationMalus.put("loseOneVictoryPointEveryXResource", new DecreaseResourcesAtFinalMalus() );
		*/
	}
	
	
	//ID & USERNAME PLAYER - GETTER AND SETTER//
	///////////////////////////////////////////
	
	/**
	 * Description of the method getIdPlayer.
	 * @return idPlayer 
	 */
	public int getIdPlayer() {
		return idPlayer;
	}

	
	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	//--------------------------------------------------//
	//-------------BEGIN METHODS FOR CARDS--------------//
	//--------------------------------------------------//
	
	/**
	 * Sets a value to attribute territoryCards. 
	 * @param newTerritoryCards 
	 */
	public void putTerritoryCardInPlayerSet(TerritoryCard newTerritoryCard) { //eccezione?!
		if (territoryCards.size() < MAXNUMBEROFCARDS)
		{
			territoryCards.add(newTerritoryCard);
		}
		//else ECCEZIONE?! return -1 ?boh	
			
	}
	
	public void putCharacterCardInPlayerSet(CharacterCard newCharacterCard) { //eccezione?!
		if (characterCards.size() < MAXNUMBEROFCARDS)
		{
			characterCards.add(newCharacterCard);
		}
		// else ECCEZIONE?! return -1 ?boh	
			
	}
		
	public void putVentureCardInPlayerSet(VentureCard newVentureCard) { //eccezione?!
		if (ventureCards.size() < MAXNUMBEROFCARDS)
		{
			ventureCards.add(newVentureCard);
		}
		//else ECCEZIONE?! return -1 ?boh	
			
	}
	
	
	public void putBuildingCardInPlayerSet(BuildingCard newBuildingCard) { //eccezione?!
		if (buildingCards.size() < MAXNUMBEROFCARDS)
		{
			buildingCards.add(newBuildingCard);
		}
		// else ECCEZIONE?! return -1 ?boh	
			
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


	/**
	 * Returns resources.
	 * @return resources 
	 */
	public ResourceSet getPlayerResourceSet() {
		return playerResources;
	}

	/**
	 * Setter of the Resources.
	 * @param resources 
	 */
	public void setResources(ResourceSet resources) {
		playerResources = resources;
	}
	
	//--------------------------------------------------//
	//-------------END METHODS FOR CARDS----------------//
	//--------------------------------------------------//
	
	
	
	//FAMILY MEMBER - GETTER AND SETTER//
	////////////////////////////////////
	
	/**
	 * Returns FamilyMembersMap.
	 * @return familyMembers 
	 */
	public Map<String, FamilyMember> getFamilyMembersMap() {
		return familyMembers;
	}
	

	//BONUS - GETTER AND SETTER//
	////////////////////////////

	/**
	 * Returns BonusMap.
	 * @return bonus
	 */
	public Map<String, Dice> getBonusMap() {
		return bonus;
	}
	

	/**
	 * This Method is used to update the bonus Map.
	 * @param bonusName
	 * @param updateValue
	 */
	public void updateBonus(String bonusName, Dice updateValue) {
		int current = 0;
		Dice currentValue = new Dice(current);
		currentValue = bonus.get(bonusName);
		currentValue.sumDice(updateValue);
		
		bonus.put(bonusName, currentValue);
	}

	
	// DISCOUNT - GETTER AND SETTER//
	////////////////////////////////
	
	/** 
	 * This Method is used to get the entire DiscountMap.
	 * @return Discount
	 */
	public Map<String, ResourceSet> getDiscount() {
		return discount;
	}
	
	
	/**
	 * This Method is used to update the map of discounts (previously initialized to NULL)
	 * it puts a new value if the key is not contained OR
	 * it updates the exists key (using the sum between resourceSet)
	 * @param discountKey
	 * @param valueDiscount
	 */

	public void updateDiscount(String discountKey, ResourceSet valueDiscount) {
		
		if (discount.containsKey(discountKey) == true)
		{
			discount.get(discountKey).add(valueDiscount);
		}
		else {
			
			discount.put(discountKey, valueDiscount);

		}
	
	}
	
	
	//OPTIONAL DISCOUNT - GETTER AND SETTER//
	////////////////////////////////////////
	
	/** 
	 * This Method is used to get the entire OptionalDiscountMap
	 * @return optionalDiscount
	 */
	public Map<String, ResourceSet> getOptionalDiscountsMap() {
		return optionalDiscount;
	}

	
	/**
	 * This Method is used to set the single resourceSetList into the map, it's formed by differents options of resourceSet.
	 * @param discountKey
	 * @param valuesOptionalDiscount
	 */	
	public void setOptionalDiscount(String discountKey, ResourceSet  valuesOptionalDiscount) {
	
			optionalDiscount.put(discountKey, valuesOptionalDiscount);
		}

	
	//PREACHER - GETTER AND SETTER

	public boolean isPreacherEffect() {
		return preacherEffect;
	}

	public void setPreacherEffect(boolean preacherEffect) {
		this.preacherEffect = preacherEffect;
	}

	
	//LEADER CARDS - GETTER AND SETTER//
	///////////////////////////////////

	/**
	 * Getter of the attribute leaderCards.
	 * return leaderCard
	 * @return leaderCards
	 */
	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}
	
	/**
	 * Setter of the attribute leaderCards.
	 * @param leaderCard
	 */
	public void setLeaderCards(LeaderCard leaderCard) {
		leaderCards.add(leaderCard);
	}
	
	
	//PERSONAL BOARD - GETTER//
	//////////////////////////
	
	public PersonalBoard getPlayerPersonaBoard() {
		return playerPersonaBoard;
	}
	
	
	//COLOR - GETTER AND SETTER//
	////////////////////////////
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;		
	}

	
	//DICE MALUS - GETTER//
	//////////////////////

	public ArrayList<DiceMalusEffect> getDiceMalus() {
		return diceMalus;
	}


	//GENERIC MALUS - GETTER//
	/////////////////////////
	
	public ArrayList<GenericMalusEffect> getGenericMalus() {
		return genericMalus;
	}


	//DECREASE RESOURCES MALUS - GETTER//
	////////////////////////////////////
	
	public ArrayList<DecreaseResourcesMalus> getDecreaseResourcesMalus() {
		return decreaseResourcesMalus;
	}


	//DECREASE AT FINAL MALUS - GETTER AND SETTER//
	//////////////////////////////////////////////
	
	public DecreaseResourcesAtFinalMalus getDecreaseAtFinalMalus() {
		return decreaseAtFinalMalus;
	}


	public void setDecreaseAtFinalMalus(DecreaseResourcesAtFinalMalus decreaseAtFinalMalus) {
		this.decreaseAtFinalMalus = decreaseAtFinalMalus;
	}
	
}