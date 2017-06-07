package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * Description of the Class Player.
 * This Class 
 * 
 * @author Andrea.Masi
 */
public class Player {

	private int idPlayer;
	private String username;
	private ResourceSet playerResources;

	private ArrayList<TerritoryCard> territoryCards = null;
	private ArrayList<VentureCard> ventureCards = null;
	private ArrayList<BuildingCard> buildingCards = null;
	private ArrayList<CharacterCard> characterCards = null;
	
	private Map <String,FamilyMember> familyMembers;
	
	private final static int MAXOFFAMILYMEMBERS = 4;
	private final static int MAXNUMBEROFCARDS = 6;

	
	/**
	 * The constructor.
	 * 
	 * @param idPlayer
	 * @configurationParam xConfigurationColorOftheFamilyMember, yConfigurationFamilyMember. Used to put the right values by configuration file.
	 */
	
	public Player(int idPlayer) {
		//userName come parametro o settato dopo?
		this.idPlayer = idPlayer;
		//playerResources = new ResourceSet(); // occhio che non ho ancora la classe resourceSet settata!
		
		
		familyMembers = new HashMap<String,FamilyMember>(familyMembers);
		for (int i = 0; i < MAXOFFAMILYMEMBERS; i++)
		{
			String xConfigurationColorOftheFamilyMember= "WHITE FAMILY MEMBER";
			FamilyMember yConfigurationFamilyMember = new FamilyMember();
			familyMembers.put(xConfigurationColorOftheFamilyMember, yConfigurationFamilyMember);
		}
	}
	
	
	/**
	 * Description of the method getIdPlayer.
	 * @return idPlayer 
	 */
	public int getIdPlayer() {
		return idPlayer;
	}

	
	
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
	public  TerritoryCard getTerritoryCards(int index) {
		return territoryCards.get(index);
		
	}


	/**
	 * Returns ventureCards.
	 * @return ventureCards 
	 */
	public VentureCard getVentureCards(int index) {
		return ventureCards.get(index);
		
	}

	
	/**
	 * Returns buildingCards.
	 * @return buildingCards 
	 */
	public BuildingCard getBuildingCards(int index) {
		return buildingCards.get(index);
	}

	
	/**
	 * Returns characterCards.
	 * @return characterCards 
	 */
	public CharacterCard getCharacterCards(int index) {
		return characterCards.get(index);
	}


	/**
	 * Returns resources.
	 * @return resources 
	 */
	public ResourceSet getPlayerResourceSet() {
		return playerResources;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}
