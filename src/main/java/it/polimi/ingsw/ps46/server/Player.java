package it.polimi.ingsw.ps46.server;

import java.util.HashSet;


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
	private Resources resources = new Resources();

	/**
	 * Description of the property idPlayer.
	 */
	public Object idPlayer;


	/**
	 * The constructor.
	 */
	public Player() {
		super();
	}

	/**
	 * Description of the method getCards.
	 */
	public void getCards() {
	}

	/**
	 * Description of the method getNeutralFamilyMember.
	 */
	public void getNeutralFamilyMember() {
	}

	/**
	 * Description of the method getOrangeFamilyMember.
	 */
	public void getOrangeFamilyMember() {
	}

	/**
	 * Description of the method getBlackFamilyMember.
	 */
	public void getBlackFamilyMember() {
	}

	/**
	 * Description of the method getWhiteFamilyMember.
	 */
	public void getWhiteFamilyMember() {
	}

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
	 * Returns resources.
	 * @return resources 
	 */
	public Resources getResources() {
		return this.resources;
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
