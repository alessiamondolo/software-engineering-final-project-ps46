package it.polimi.ingsw.ps46.server;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.card.ExcommunicationTile;

/**
 * This Class contains all the objects of the game board, all the towers and all the actionSpaces.
 * 
 * @author Andrea.Masi
 */
public class Board implements Serializable {
	
	private static final long serialVersionUID = -5261619069411649403L;
	
	private int numberOfTowers;
	private ArrayList<Tower> towers;
	private ArrayList<ActionSpace> boardBoxes;
	private ArrayList<ExcommunicationTile> excommunicationTiles;
	
	/**
	 * This Class is made by two lists of Tower and boardBoxes. 
	 */
	public Board(ArrayList<Tower> towers, ArrayList<ActionSpace> boardBoxes){
		
		this.towers = towers;
		this.boardBoxes = boardBoxes;
		if(boardBoxes != null)
			numberOfTowers = towers.size();
		
	}


	
	public void setExcommunicationTiles(ArrayList<ExcommunicationTile> excommunicationTiles) {
		this.excommunicationTiles = excommunicationTiles;
	}
	
	///////////////GETTER METHODS//////////////////
	//////////////////////////////////////////////
	
	public Tower getTower (int towerIndex){
		
		return towers.get(towerIndex);
		
	}
	

	/**
	 *  
	 * @param boardBoxIndex
	 * @return the same floor of the index
	 */
	public ActionSpace getBoardBox (int boardBoxIndex){
		
		return boardBoxes.get(boardBoxIndex);
		
	}
	

	public int getNumberOfTowers() {
		return numberOfTowers;
	}

	
	/**
	 * This class is used to check if the tower where a player's family member is moving on is empty or not,
	 * passing one by one all the floors of the tower and checking its availability.
	 * 
	 * @param idActionSpace
	 * @return boolean
	 */
	public boolean isEmptyTower(int idActionSpace) {
		
		int towerId = getWhichTowerContain(idActionSpace);
		if (towerId != -1 ){
			for (TowerFloor towerFloor : towers.get(towerId).getFloors()) {
				if(towerFloor.getActionSpace().getAvailability() == false)
					return false;
			}
			return true;
		}
		else 
			System.out.println("Errore nella lettura delle torri, oggetto non trovato e 'return -1'");
			return false;
	}
	
	/**
	 * This class is used to find out which is the index of the tower where a player's family member is moving on, 
	 * checking one by one all the towers and all the floors of every tower,
	 * comparing the id of the action space with the passed one by argument. 
	 * 
	 * @param idActionSpace
	 * @return indexOf(tower) which contains the actionSpace
	 */
	private int getWhichTowerContain(int idActionSpace) {
		
		for (Tower tower : towers) {
			for (TowerFloor towerFloor : tower.getFloors()) {
				if( towerFloor.getActionSpace().getId() == idActionSpace){
					return (towers.indexOf(tower));
				}	
			}
		}
		return -1;
	}
	
	
	/**
	 * This class is used to get the card associate with the actionSpace passed as argument.
	 * 
	 * @param idActionSpace
	 * @return towerFloor.getCard()
	 */
	public Card getCardOfTheTowerFloor(int idActionSpace) {
		
		int towerId = getWhichTowerContain(idActionSpace);	
		
		if (towerId != -1 ){
				for (TowerFloor towerFloor : towers.get(towerId).getFloors()) {
					if (towerFloor.getActionSpace().getId() == idActionSpace){
						return towerFloor.getCard();	
					}		
				}
			}		
		System.out.println("Errore nella lettura delle torri, oggetto non trovato e 'return null'");
		return null;
	}
	
	
	/**
	 * This class is used to get the color of the tower associate with the actionSpace passed as argument.

	 * @param idActionSpace
	 * @return tower.getColorOfTheTower()
	 */
	public String getColorOfTower (int idActionSpace){
		
		for (Tower tower : towers) {
			for (TowerFloor towerFloor : tower.getFloors()) {
				if( towerFloor.getActionSpace().getId() == idActionSpace){
					return (tower.getColorOfTheTower());
				}	
			}
		}
		System.out.println("Errore nella lettura delle torri, oggetto non trovato e 'return null'");
		return null; 
	}


	public ArrayList<ActionSpace> getBoardBoxes() {
		return boardBoxes;
	}


	public ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}

}