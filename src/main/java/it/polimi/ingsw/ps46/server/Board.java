package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;


public class Board {
	
	private int numberOfTowers;
	private ArrayList<Tower> towers;
	private ArrayList<ActionSpace> boardBoxes;
	
	/**
	 * This Class is made by two lists of Tower and boardBoxes. 
	 */
	public Board(ArrayList<Tower> towers, ArrayList<ActionSpace> boardBoxes){
		
		this.towers = towers;
		this.boardBoxes = boardBoxes;
		if(boardBoxes != null)
			numberOfTowers = towers.size();
		
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


	public int getNumberOfTowers() {
		return numberOfTowers;
	}
}
