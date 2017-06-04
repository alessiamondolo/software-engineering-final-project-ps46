package it.polimi.ingsw.ps46.server;

import java.awt.Color;
import java.util.ArrayList;

public class Board {
	
	private ArrayList<Tower> towers;
	private ArrayList<ActionSpace> boardBoxes;
	
	/**
	 * Description of the Class Board.
	 * This Class is made by two lists of Tower and boardBoxes. there are 4 parameters used to set up all the board.
	 * 
	 * @param nOfTowers passed by configuration File
	 * @param nOfBoardBoxes passed by configuration File
	 * @param fourPlay 
	 * @configurationVar xConfigurationInt; yConfigurationDice. Used to put the right values by configuration file.
	 */
	public Board(int nOfTowers, int nOfBoardBoxes, boolean fourPlay){
		
		towers = new ArrayList<Tower>();

		for( int i = 0; i < nOfTowers; i++)
		{
			towers.add(i, new Tower(Color.yellow)); // color.yellow is just a standard color (the serial ones will be added by configuration file)
		}
		
		boardBoxes = new ArrayList<ActionSpace>();
		
		for(int j = 0; j < nOfBoardBoxes; j++)
		{
			int xConfigurationInt = 7;
			Dice yConfigurationDice = new Dice(xConfigurationInt);
			boardBoxes.add(j,new ActionSpace(yConfigurationDice,xConfigurationInt));
		
		}
		
	}
	

	/**
	 * Description of the method getTower
	 * 
	 * @param towerIndex
	 * @return the same floor of the index
	 */
	public Tower getTower (int towerIndex){
		
		return towers.get(towerIndex);
		
	}
	

	/**
	 * Description of the method getBoardBox
	 * 
	 * @param boardBoxIndex
	 * @return the same floor of the index
	 */
	public ActionSpace getBoardBox (int boardBoxIndex){
		
		return boardBoxes.get(boardBoxIndex);
		
	}
}
