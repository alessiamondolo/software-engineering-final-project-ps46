package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import it.polimi.ingsw.ps46.server.Game;

/**
 * MainWindow Class is the GUI root Container for the main game screen. 
 * The main window shows two main areas, on the left a PlayerArea and on the right
 * the game's board. These two areas contains their own sub-containers.
 * @author lorenzo
 *
 */

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6629824344371954788L;

	private MainBoard mainBoard;
	private PlayerArea playerArea;
	
	public MainWindow() {
		
		// da mettere un check che le dimensioni dei component siano < di quelle della Wind
		//così sto però dando per scontata la risoluzione dell'utente
		
		int mainWindowWidth = 1400;  //1400
		int mainWindowHeight = 900;   //900
		int boardWidth = (mainWindowWidth * 6)/ 14;
		int boardHeight = (mainWindowHeight * 7) / 9;
		int playerAreaWidth = (mainWindowWidth * 65)/ 140;
		
		Dimension playerAreaDimension = new Dimension(playerAreaWidth, boardHeight);
		Dimension boardDimension = new Dimension(boardWidth, boardHeight);
		
		this.setTitle("Lorenzo il Magnifico");
		this.setPreferredSize(new Dimension(mainWindowWidth, mainWindowHeight));
		
		this.mainBoard = createBoardArea(boardDimension);
		this.add(mainBoard);
		this.playerArea = createplayerArea(playerAreaDimension);
		this.add(playerArea, BorderLayout.WEST);
		
	}
	
	
	private MainBoard createBoardArea(Dimension boardDimension) {
		
		MainBoard board = new MainBoard(boardDimension);
		return board;
	}
	
	
	private PlayerArea createplayerArea(Dimension playerAreaDimension)  {
		
		PlayerArea playerArea = new PlayerArea(playerAreaDimension);
		return playerArea;
	}
	
	
	public MainBoard getMainBoard() {
		return this.mainBoard;	
	}
	
	
	public PlayerArea getPlayerArea() {
		return this.playerArea;
	}
	
}
	
	