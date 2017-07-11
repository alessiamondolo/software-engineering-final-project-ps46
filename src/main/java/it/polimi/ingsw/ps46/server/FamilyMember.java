package it.polimi.ingsw.ps46.server;

import java.io.Serializable;

/**
 * This Class creates an object family member formed by its Value (Dice), position (int), color (String) and if isUsed(boolean)
 * 
 * @author Andrea.Masi
 */

public class FamilyMember implements Serializable {
	
	private static final long serialVersionUID = -6382000537229373852L;
	
	private boolean isUsed = false;
	private Dice value = new Dice(0);
	private int positionOfFamilyMember = 0;
	private String color;
	

	
	public FamilyMember(String color){
		this.color = color;
	}

	///////////////SETTER METHODS//////////////////
	//////////////////////////////////////////////
	
	public void setValueOfFamilyMember(Dice setValue){
		
		value = setValue;
		
	}
	
	public void use() {
		isUsed = true;
	}
	
	public void setPositionOfFamilyMember(int newPositionOfFamilyMember) {
		positionOfFamilyMember = newPositionOfFamilyMember;
	}
	
	public void clearPositionOfFamilyMember() {
		positionOfFamilyMember = 0;
		isUsed = false;
	}

	
	///////////////GETTER METHODS//////////////////
	//////////////////////////////////////////////

	public boolean isUsed(){
		return isUsed;
	}
	

	public Dice getValueFamilyMember(){
		
		return value;
		
	}

	public String getColor() {
		return color;
	}

	public int getPositionOfFamilyMember() {
		return positionOfFamilyMember;
	}
}