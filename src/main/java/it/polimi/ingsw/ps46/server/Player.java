package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import it.polimi.ingsw.ps46.server.card.DecreaseResourcesAtFinalMalus;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.card.DiceMalusEffect;
import it.polimi.ingsw.ps46.server.card.GenericMalusEffect;
import it.polimi.ingsw.ps46.server.card.LeaderCard;
import it.polimi.ingsw.ps46.server.card.MalusEffect;
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
	
	private ArrayList<LeaderCard> leaderCards = null;  //TODO completare tutta la lista delle carte i suoi effetti ecc ecc
	
	private PersonalBoard personalBoard;
	
	private Map <String,FamilyMember> familyMembers;
	private Map<String, Dice> bonus;
	private Map<String, ResourceSet> discount;
	private Map<String, ResourceSet> optionalDiscount;
	
	private boolean preacherEffect = false; // da usare come malus e magari come parametro il numero di torre!
	
	private Map <String, MalusEffect> excommunicationMalus;


	
	/**
	 * The constructor.
	 * 
	 * @param idPlayer
	 * @configurationParam xConfigurationColorOftheFamilyMember, yConfigurationFamilyMember. Used to put the right values by configuration file.
	 */
	
	public Player(int idPlayer) {
		
		this.idPlayer = idPlayer; 
		
		familyMembers = new LinkedHashMap<String,FamilyMember>();
		familyMembers.put("White", new FamilyMember("White"));
		familyMembers.put("Black", new FamilyMember("Black"));
		familyMembers.put("Orange", new FamilyMember("Orange"));
		familyMembers.put("Neutral", new FamilyMember("Neutral"));
		
		//TODO parsing del file personalboard
		FactoryBoard factoryBoard = FactoryBoard.getFactoryBoard();
		personalBoard = factoryBoard.createPersonalBoard("PersonalBoard.json");
		
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

		
		excommunicationMalus = new HashMap<String, MalusEffect>();
		
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

	}
	
	/**
	 * Description of the method getIdPlayer.
	 * @return idPlayer 
	 */
	public int getIdPlayer() {
		return idPlayer;
	}

	

	/**
	 * Description of the method getFamilyMembers.
	 * This method returns a selected familyMember by colorKey (String).
	 * 
	 * @return selectfamilyMember 
	 */
	public FamilyMember getFamilyMember(String colorKey) {
		return familyMembers.get(colorKey);
		
	}
	
	public Map<String, FamilyMember> getFamilyMembers() {
		return familyMembers;
	}


	public String getUsername() {
		return username;
	}
	
	
	public String getColor() {
		return color;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	

	public Map<String, Dice> getBonusMap() {
		return bonus;
	}
	
	public Dice getBonus(String bonusName) {
		
		return bonus.get(bonusName);
		
	}

	/**
	 * Description of the method updateBonus.
	 * 
	 * This Method is used to upDate the bonus Map.
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

	/** 
	 * Description of the method getDiscount.
	 * 
	 * This Method is used to get the entire Map.
	 * @return Discount
	 */

	public Map<String, ResourceSet> getDiscount() {
		return discount;
	}
	
	/** 
	 * Description of the method getDiscountByKey.
	 * 
	 * This Method is used to get the resourceSet (using the key discountKey)
	 * @return discount.get(discountKey)
	 */
	
	public ResourceSet getDiscountByKey(String discountKey) {
		return discount.get(discountKey);
		
	}

	
	/**
	 * Description of the method updateDiscount.
	 * 
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
	
	
	/** 
	 * Description of the method getOptionalDiscountsMap.
	 * 
	 * This Method is used to get the entire Map
	 * @return optionalDiscount
	 */
	public Map<String, ResourceSet> getOptionalDiscountsMap() {
		return optionalDiscount;
	}
	
	
	/**
	 * Description of the method getOptionalDiscountsListByKey.
	 * 
	 * This Method is used to get the list of resourceSet
	 * @param discountKey
	 * @return optionalDiscount.get(discountKey)
	 */
	public ResourceSet getOptionalDiscountsKey(String discountKey) {
		return optionalDiscount.get(discountKey);
		
	}

	
	/**
	 * Description of the method setOptionalDiscount.
	 * 
	 * This Method is used to set the single resourceSetList into the map, it's formed by differents options of resourceSet.
	 * @param discountKey
	 * @param valuesOptionalDiscount
	 */	
	public void setOptionalDiscount(String discountKey, ResourceSet  valuesOptionalDiscount) {
	
			optionalDiscount.put(discountKey, valuesOptionalDiscount);
		}

	public boolean isPreacherEffect() {
		return preacherEffect;
	}

	public void setPreacherEffect(boolean preacherEffect) {
		this.preacherEffect = preacherEffect;
	}

	
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	public void setPersonalBoard(PersonalBoard personalBoard) {
		this.personalBoard = personalBoard;
	}
	
	/**
	 * Getter and Setter of the attribute leaderCards.
	 * 
	 * @return leaderCards
	 */
	
	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}

	public void setLeaderCards(LeaderCard leaderCard) {
		leaderCards.add(leaderCard);
	}


	/**
	 * Getter of the Map excommunicationMalus.
	 * 
	 * @return excommunicationMalus
	 */
	public Map <String, MalusEffect> getExcommunicationMap() {
		return excommunicationMalus;
	}
	
	/**
	 * Getter of the Malus excommunicationMalus.
	 * 
	 * @return excommunicationMalus.get(type)
	 */
	
	public MalusEffect getExcommunicationMalus(String type){
		
		return excommunicationMalus.get(type);
		
	}

	public void setColor(String color) {
		this.color = color;		
	}

}