package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.card.Effect;

/**
 * This Class contains towerFloors memorized into a list of  <TowerFloor> + nMax of floors = 4 + the color of each floor.
 * There are 3 parameters used to set up all the tower with differents values.

 * 
 * @author Andrea.Masi
 */

public class Tower {
	private ArrayList<TowerFloor> floors;
	private static final int numberOfFloors = 4;
	private String colorOfTheTower;

	
	/**Description of the constructor Tower.
	 * This constructor uses the parametric value towerColor and some variables used to put the right value by configuration file.
	 * 
	 * @param towerColor
	 * @configurationParam xConfigurationInt; yConfigurationActionSpace; zConfigurationDice. Used to put the right values by configuration file.
	 */
	public Tower (String towerColor, ArrayList<TowerFloor> floors)
	{
		colorOfTheTower = towerColor;
		this.floors = floors;
		
		for(int i = 0; i< numberOfFloors; i++)
		{
			int xConfigurationInt = 7; 
			Dice zConfigurationDice = new Dice(xConfigurationInt);
			//Effect wConfigurationEffect = new Effect();                        //da sistemare con la classe Effect pushata da alessia.M
	     	//ActionSpace yConfigurationActionSpace = new ActionSpace(zConfigurationDice, xConfigurationInt, wConfigurationEffect);
			//floors.add(i, new TowerFloor(xConfigurationInt, yConfigurationActionSpace));
		}	
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
	
}