package it.polimi.ingsw.ps46.server;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Description of the class Tower.
 * This Class contains towerFloors memorized into a list of  <TowerFloor> + nMax of floors = 4 + the color of each floor.
 * There are 3 parameters used to set up all the tower with differents values.

 * 
 * @author Andrea.Masi
 */

public class Tower {
	private ArrayList<TowerFloor> floors;
	private static final int numberOfFloors = 4;
	private Color colorOfTheTower;

	
	/**Description of the constructor Tower.
	 * This constructor uses the parametric value towerColor and some variables used to put the right value by configuration file.
	 * 
	 * @param towerColor
	 * @configuration  var  xConfigurationInt; yConfigurationActionSpace; zConfigurationDice. Used to put the right values by configuration file.
	 */
	public Tower (Color towerColor)
	{
		colorOfTheTower = towerColor;
		floors = new ArrayList<TowerFloor>();
		
		for(int i = 0; i< numberOfFloors; i++)
		{
			int xConfigurationInt = 7; 
			Dice zConfigurationDice = new Dice(xConfigurationInt);
			ActionSpace yConfigurationActionSpace = new ActionSpace(zConfigurationDice, xConfigurationInt);
			floors.add(i, new TowerFloor(xConfigurationInt, yConfigurationActionSpace));
		}	
	}
	
	
	/**
	 * Description of the method sameColor.
	 * 
	 * @param colorOfTheCard
	 * @return boolean
	 */
	
	public Boolean sameColor(Color colorOfTheCard){
		
		if(colorOfTheTower == colorOfTheCard)
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
	
}