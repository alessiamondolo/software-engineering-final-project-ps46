package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;


/**
 * This Class contains towerFloors memorized into a list of  <TowerFloor> + nMax of floors = 4 + the color of each floor.
 * 
 * @author Andrea.Masi
 */

public class Tower {
	
	private int numberOfFloors;
	private ArrayList<TowerFloor> floors;
	private String colorOfTheTower;

	
	/**
	 * Creates a new Tower object.
	 **/
	public Tower (String towerColor, ArrayList<TowerFloor> floors){
		colorOfTheTower = towerColor;
		this.floors = floors;
		if(floors != null)
		numberOfFloors = floors.size();
	}
	
	
	/**
	 * Description of the method sameColor.
	 * 
	 * @param colorOfTheCard
	 * @return boolean
	 */
	
	public Boolean sameColor(String colorOfTheCard){
		
		if(colorOfTheTower.equals(colorOfTheCard))
			return true;
		
		else
			return false;
	}
	
	/**
	 * Description of the method getTowerFloor
	 * 
	 * @param floorTowerIndex
	 * @return the same floor of the floorTowerIndex
	 */
	
	public TowerFloor getTowerFloor (int floorTowerIndex){
		
		return floors.get(floorTowerIndex);
		
	}


	public int getNumberOfFloors() {
		return numberOfFloors;
	}
	
}