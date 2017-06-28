package it.polimi.ingsw.ps46.server;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * This Class contains towerFloors memorized into a list of  <TowerFloor> + nMax of floors = 4 + the color of each floor.
 * 
 * @author Andrea.Masi
 */

public class Tower implements Serializable {
	
	private static final long serialVersionUID = 988637687619263552L;
	
	private int numberOfFloors;
	private ArrayList<TowerFloor> floors;
	private String colorOfTheTower;

	
	/**
	 * Creates a new Tower object.
	 **/
	public Tower (String towerColor, ArrayList<TowerFloor> floors){
		colorOfTheTower = towerColor;
	//	this.floors = floors;
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
	
	
	public ArrayList<TowerFloor> getFloors() {
		return floors;
	}


	public String getColorOfTheTower(){
		
		return colorOfTheTower;
	}
	
	public int getNumberOfFloors() {
		return numberOfFloors;
	}


	public TowerFloor getTowerFloor(int floor) {
		return floors.get(floor);

	}


}