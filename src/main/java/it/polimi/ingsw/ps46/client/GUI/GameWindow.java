package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import it.polimi.ingsw.ps46.server.Game;

/**
 * The GUI root Container for the main game screen. 
 * It shows two main areas, on the left a PlayerArea and on the right
 * the game's board. These two areas contains their own sub-containers.
 * @author lorenzo
 *
 */

public class GameWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6629824344371954788L;

	private MainBoard mainBoard;
	private PlayerArea playerArea;
	private Game game;

	
	public GameWindow(Game game) {
		
		this.game = game;
		int mainWindowWidth = 1400;  //1400 o 1200
		int mainWindowHeight = 900;   //900 o 700
		int boardWidth = (mainWindowWidth * 6)/ 14;
		int boardHeight = (mainWindowHeight * 7) / 9;
		int playerAreaWidth = (mainWindowWidth * 65)/ 140;
		
		Dimension playerAreaDimension = new Dimension(playerAreaWidth, boardHeight);
		Dimension boardDimension = new Dimension(boardWidth, boardHeight);
		
		this.setTitle("Lorenzo il Magnifico");
		this.setPreferredSize(new Dimension(mainWindowWidth, mainWindowHeight));
		
		this.mainBoard = createBoardArea(boardDimension);
		this.add(mainBoard);
		this.playerArea = createplayerArea(playerAreaDimension, this.game);
		this.add(playerArea, BorderLayout.WEST);
		this.pack();
		
	}
	
	
	private MainBoard createBoardArea(Dimension boardDimension) {
		
		MainBoard board = new MainBoard(boardDimension);
		return board;
	}
	
	
	private PlayerArea createplayerArea(Dimension playerAreaDimension, Game game)  {
		
		PlayerArea playerArea = new PlayerArea(playerAreaDimension, this.game);
		return playerArea;
	}
	
	
	public MainBoard getMainBoard() {
		return this.mainBoard;	
	}
	
	
	public PlayerArea getPlayerArea() {
		return this.playerArea;
	}
	
	public void update(Game game) {
		
		this.game = game;
		this.playerArea.update(game);
		this.mainBoard.update(game);
		this.pack();
		repaint();
	}
	
	/**
	 * Sets the title of the root frame accordingly with info provided by the client
	 * @param username : client's username
	 * @param color : client's color
	 */
	public void setPlayerInfo(String username, String color) {
		this.setTitle("Lorenzo il Magnifico - player: " + username + " - color: " + color);
	}
}
	
	