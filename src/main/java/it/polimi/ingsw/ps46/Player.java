package it.polimi.ingsw.ps46;

import java.util.HashSet;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Player.
 * 
 * @author a.mondolo
 */
public class Player {
	/**
	 * Description of the property username.
	 */
	public Object username;

	/**
	 * Description of the property territoryCards.
	 */
	public Object territoryCards;

	/**
	 * Description of the property ventureCards.
	 */
	public Object ventureCards;

	/**
	 * Description of the property familyMembers.
	 */
	public HashSet<FamilyMember> familyMembers = new HashSet<FamilyMember>();

	/**
	 * Description of the property buildingCards.
	 */
	public Object buildingCards;

	/**
	 * Description of the property characterCards.
	 */
	public Object characterCards;

	/**
	 * Description of the property resourcess.
	 */
	public HashSet<Resources> resourcess = new HashSet<Resources>();

	/**
	 * Description of the property idPlayer.
	 */
	public Object idPlayer;
	
	// Start of user code (user defined attributes for Player)
	
	// End of user code

	/**
	 * The constructor.
	 */
	public Player() {
		// Start of user code constructor for Player)
		super();
		// End of user code
	}

	/**
	 * Description of the method getResources.
	 */
	public void getResources() {
		// Start of user code for method getResources
		// End of user code
	}

	/**
	 * Description of the method getCards.
	 */
	public void getCards() {
		// Start of user code for method getCards
		// End of user code
	}

	/**
	 * Description of the method getNeutralFamilyMember.
	 */
	public void getNeutralFamilyMember() {
		// Start of user code for method getNeutralFamilyMember
		// End of user code
	}

	/**
	 * Description of the method getOrangeFamilyMember.
	 */
	public void getOrangeFamilyMember() {
		// Start of user code for method getOrangeFamilyMember
		// End of user code
	}

	/**
	 * Description of the method getBlackFamilyMember.
	 */
	public void getBlackFamilyMember() {
		// Start of user code for method getBlackFamilyMember
		// End of user code
	}

	/**
	 * Description of the method getWhiteFamilyMember.
	 */
	public void getWhiteFamilyMember() {
		// Start of user code for method getWhiteFamilyMember
		// End of user code
	}

	// Start of user code (user defined methods for Player)

	// End of user code
	/**
	 * Returns username.
	 * @return username 
	 */
	public Object getUsername() {
		return this.username;
	}

	/**
	 * Sets a value to attribute username. 
	 * @param newUsername 
	 */
	public void setUsername(Object newUsername) {
		this.username = newUsername;
	}

	/**
	 * Returns territoryCards.
	 * @return territoryCards 
	 */
	public Object getTerritoryCards() {
		return this.territoryCards;
	}

	/**
	 * Sets a value to attribute territoryCards. 
	 * @param newTerritoryCards 
	 */
	public void setTerritoryCards(Object newTerritoryCards) {
		this.territoryCards = newTerritoryCards;
	}

	/**
	 * Returns ventureCards.
	 * @return ventureCards 
	 */
	public Object getVentureCards() {
		return this.ventureCards;
	}

	/**
	 * Sets a value to attribute ventureCards. 
	 * @param newVentureCards 
	 */
	public void setVentureCards(Object newVentureCards) {
		this.ventureCards = newVentureCards;
	}

	/**
	 * Returns familyMembers.
	 * @return familyMembers 
	 */
	public HashSet<FamilyMember> getFamilyMembers() {
		return this.familyMembers;
	}

	/**
	 * Returns buildingCards.
	 * @return buildingCards 
	 */
	public Object getBuildingCards() {
		return this.buildingCards;
	}

	/**
	 * Sets a value to attribute buildingCards. 
	 * @param newBuildingCards 
	 */
	public void setBuildingCards(Object newBuildingCards) {
		this.buildingCards = newBuildingCards;
	}

	/**
	 * Returns characterCards.
	 * @return characterCards 
	 */
	public Object getCharacterCards() {
		return this.characterCards;
	}

	/**
	 * Sets a value to attribute characterCards. 
	 * @param newCharacterCards 
	 */
	public void setCharacterCards(Object newCharacterCards) {
		this.characterCards = newCharacterCards;
	}

	/**
	 * Returns resourcess.
	 * @return resourcess 
	 */
	public HashSet<Resources> getResourcess() {
		return this.resourcess;
	}

	/**
	 * Returns idPlayer.
	 * @return idPlayer 
	 */
	public Object getIdPlayer() {
		return this.idPlayer;
	}

	/**
	 * Sets a value to attribute idPlayer. 
	 * @param newIdPlayer 
	 */
	public void setIdPlayer(Object newIdPlayer) {
		this.idPlayer = newIdPlayer;
	}

}
