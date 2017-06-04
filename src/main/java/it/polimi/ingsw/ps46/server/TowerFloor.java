package it.polimi.ingsw.ps46.server;

import it.polimi.ingsw.ps46.server.card.Card;

/**
 * Description of Class TowerFloor.
 * This Class is used to create a single floor of a tower 
 * 
 * @author Andrea.Masi
 */


public class TowerFloor {
	private int floor = 0; 
	private ActionSpace actionOfTheTower = null;
	private Card cardInTheFloor = null;
	
	
	/**
	 * Description of the constructor TowerFloor.
	 * This constructor is made of two parts the number of the floor and an actionSpace. 
	 * 
	 * @param actualFloor
	 * @param action
	 */
	public TowerFloor(int actualFloor, ActionSpace action)
	{
		floor = actualFloor;
		actionOfTheTower = action;
	
	}
	 
	
	/**
	 * Description of the method setCard.
	 * 
	 * @param newCard
	 */
	public void setCard(Card newCard){
		cardInTheFloor = newCard;
	}
	
	
	/**
	 * Description of the method getCard.
	 * 
	 * @return cardInTheFloor
	 */
	
	public Card getCard(){
		
		return cardInTheFloor;
	
	}
	
	
	/**
	 * Description of the method getActionSpace.
	 * 
	 * @return actionOfTheTower
	 */
	public ActionSpace getActionSpace(){
		
		return actionOfTheTower;
	
	}
	
}