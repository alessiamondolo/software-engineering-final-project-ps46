package it.polimi.ingsw.ps46.server;

/**
 * Description of the Class FamilyMember.
 * This Class creates an object family member formed by its id ( set in the Class Player for a MAX of 4) Availability and its value.
 * 
 * @author Andrea.Masi
 */

public class FamilyMember {
	
	private boolean isUsed = false;
	private Dice value = null;
	private int positionOfFamilyMember = 0;
	private String color;
	
	/**
	 * Description of the constructor FamilyMember.
	 * 
	 */
	public FamilyMember(){
	}
	
	
	/**
	 * Description of the method setValueOfFamilyMember.
	 * 
	 * @param setValue
	 */
	
	public void setValueOfFamilyMember(Dice setValue){
		
		value = setValue;
		
	}
	
	
	/**
	 * Description of the method useOrSetFreeFamilyMember.
	 * 
	 */
	private boolean useOrSetFreeFamilyMember(){
		if (isUsed = false){
			isUsed = true;
			return true;
		}
		
		else isUsed = false;
		return false;
	}
	
	

	public void setPositionOfFamilyMember(int newPositionOfFamilyMember) {
		
		if (useOrSetFreeFamilyMember() == false) 
			positionOfFamilyMember = newPositionOfFamilyMember;
		
		//ECCEZIONE riporto un valore intero (0) se il metodo fa il suo lavoro o serve un'eccezione?
	}
	
	public void clearPositionOfFamilyMember() {
		
		if (useOrSetFreeFamilyMember() == true) 
			positionOfFamilyMember = 0;
		//ECCEZIONE riporto un valore intero (0) se il metodo fa il suo lavoro o serve un'eccezione?

	}
	
	
	/**
	 * Description of the method getIfUsed.
	 * @return used
	 */
	public boolean isUsed(){
		return isUsed;
	}
	
	
	/**
	 * Description of the method getValueFamilyMember.
	 * @return value
	 */
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
	
