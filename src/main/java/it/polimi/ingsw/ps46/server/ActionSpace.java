package it.polimi.ingsw.ps46.server;

import java.io.Serializable;

import it.polimi.ingsw.ps46.server.card.IncreaseResourcesEffect;

/**
 * This Class represents the structure of all the boxes of the board, it's called actionSpace because is the space where you can put your family member..
 * 
 * @author Andrea.Masi
 */
public class ActionSpace implements Serializable {
	
	private static final long serialVersionUID = -3813912382960678889L;
	
	private static int IDACTIONSPACE = 0;
	private int idLocalActionSpaces = 0;
	private String type;
	private IncreaseResourcesEffect effectOfActionSpace;
	private Dice requiredFamilyMemberValue;
	private boolean available = true;
	private Boolean maxOnePlayer;
	private String playerColor = "";

	
	/** 
	 * Description of the Constructor ActionSpace().
	 * Create a new actionSpace with the value of requiredFamilyMember and the maximum number of free spotted passed as arguments.
	 * 
	 * @param value
	 * @param totNumberSpot 
	 */
	
	public ActionSpace(Dice familyMembervalue, Boolean maxOnePlayer, IncreaseResourcesEffect effectOfActionSpace) {
		
		setIdLocalActionSpaces(IDACTIONSPACE);
		IDACTIONSPACE ++;
		this.effectOfActionSpace = effectOfActionSpace;
		requiredFamilyMemberValue = familyMembervalue;
		this.maxOnePlayer = maxOnePlayer; 
	}


	
	/**
	 * Description of the method updateAvailability().
	 * If the action space is available and there is the limitation of maximum one player,
	 * sets available as false.
	 */

	public void updateAvailability(){
		if ((available == true) && (maxOnePlayer)) {
			available = false;
		}
	}
	
	public void setAsAvailable() {
			available = true;
	}

	
	/**
	 * Description of the method getRequiredFamilyMemberValue().
	 * 
	 * @return requiredFamilyMemberValue 
	 */
	public Dice getRequiredFamilyMemberValue() {
		return requiredFamilyMemberValue;
		
	}
	
	
	/**
	 * Description of the method getAvailability
	 * 
	 * @return available
	 */
	public boolean getAvailability() {
		return available;	
	}
	
	
	/**
	 * Description of the method GetTotalNumberOfSpot.
	 * 
	 * @return totalNumberOfSpot
	 */
	public boolean isMaxOnePlayer(){
		return maxOnePlayer;
		
	}


	public String getType() {
		return type;
	}
	

	public int getIdLocalActionSpaces() {
		return idLocalActionSpaces;
	}
	
	public IncreaseResourcesEffect getEffectOfActionSpace() {
		return effectOfActionSpace;
	}

	private void setIdLocalActionSpaces(int idLocalActionSpaces) {
		this.idLocalActionSpaces = idLocalActionSpaces;
	}



	public String getPlayerColor() {
		return playerColor;
	}



	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}
	
}