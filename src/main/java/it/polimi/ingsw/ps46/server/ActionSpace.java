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
	
	private int id;
	private String type;
	private IncreaseResourcesEffect effectOfActionSpace;
	private Dice requiredFamilyMemberValue;
	private boolean available = true;
	private boolean maxOnePlayer;
	private String playerColor = "";

	
	/** 
	 * Create a new actionSpace with the value of requiredFamilyMember and the maximum number of free spotted passed as arguments.
	 * 
	 * @param value
	 * @param totNumberSpot 
	 */
	
	public ActionSpace(String type, int id, Dice familyMembervalue, boolean maxOnePlayer, IncreaseResourcesEffect effectOfActionSpace) {
		this.id = id;
		this.type = type;
		this.effectOfActionSpace = effectOfActionSpace;
		requiredFamilyMemberValue = familyMembervalue;
		this.maxOnePlayer = maxOnePlayer; 
	}


	
	/**
	 * If the actionSpace is available and there is the limitation of maximum one player,
	 * sets available as false.
	 */

	public void updateAvailability(){
		if ((available == true) && (maxOnePlayer)) {
			available = false;
		}
	}
	
	///////////////SETTER METHODS//////////////////
	//////////////////////////////////////////////
	public void setAsAvailable() {
			available = true;
	}


	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}
	
	///////////////GETTER METHODS//////////////////
	//////////////////////////////////////////////
	
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
	

	public int getId() {
		return id;
	}
	
	public IncreaseResourcesEffect getEffectOfActionSpace() {
		return effectOfActionSpace;
	}


	public String getPlayerColor() {
		return playerColor;
	}

	
}