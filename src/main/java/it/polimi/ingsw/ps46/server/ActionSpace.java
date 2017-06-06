package it.polimi.ingsw.ps46.server;

/**
 * Description of the Class ActionSpace.
 * This Class represents the structure of all the boxes of the board, it's called actionSpace because is the space where you can put your family member..
 * 
 * @author Andrea.Masi
 */
public class ActionSpace {
	
	private int idActionSpaces = 0;
	//TODO check usage of "type" attribute
	private String type;
	private Dice requiredFamilyMemberValue;
	private Boolean available = true;
	private int totalNumberOfSpot = 0;
	private int numberFamilyMemberOnTheActionSpace = 0;

	
	/** 
	 * Description of the Constructor ActionSpace().
	 * Create a new actionSpace with the value of requiredFamilyMember and the maximum number of free spotted passed as arguments.
	 * 
	 * @param value
	 * @param totNumberSpot 
	 */
	
	public ActionSpace(Dice FamilyMembervalue, int totNumberSpot) {
		idActionSpaces ++;
		FamilyMembervalue = new Dice();
		requiredFamilyMemberValue = FamilyMembervalue;
		totalNumberOfSpot = totNumberSpot; 
	}


	
	/**
	 * Description of the method updateNumberOfSpotsAvailable().
	 * Make a double check (on availability & number of free spots of an actionSpace) if is it possible to put a family member on a box. 
	 */

	public void updateNumberOfSpotsAvailable(){
		if ( (available == true) && (numberFamilyMemberOnTheActionSpace < totalNumberOfSpot) )
		{
			numberFamilyMemberOnTheActionSpace++;
		}
		else if ((available == true) && (numberFamilyMemberOnTheActionSpace == totalNumberOfSpot))
		{
			available = false;
		}
	}
	
	
	/**
	 * Description of the method getIdActionSpaces().
	 * 
	 * @return idActionSpaces 
	 */
	public int getIdActionSpaces() {
		return idActionSpaces;
		
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
	 * Description of the method getNumberOfMembersOnTheSpot
	 * @return numberFamilyMemberOnTheActionSpace
	 */
	public int getNumberOfMembersOnTheSpot()
	{
		return numberFamilyMemberOnTheActionSpace;

	}
	
	
	/**
	 * Description of the method GetTotalNumberOfSpot.
	 * 
	 * @return totalNumberOfSpot
	 */
	public int GetTotalNumberOfSpot(){
		return totalNumberOfSpot;
		
	}



	public String getType() {
		return type;
	}
	
}