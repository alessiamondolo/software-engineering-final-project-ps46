package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * MainWindow Class is the GUI root Container for the main game screen. 
 * The main window shows two main areas, on the left a PlayerArea and on the right
 * the game's board. These two areas contains their own sub-containers.
 * @author lorenzo
 *
 */

public class MainWindow extends JFrame implements Observer {
	
	
	
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
		this.add(createplayerArea(playerAreaDimension), BorderLayout.WEST);
		this.add(createBoardArea(boardDimension));
		
		  //parametri che dovrebbero essere calcolati in base alla risoluzione dello schermo su cui il programma gira
		
		//this.getContentPane().setLayout(new BorderLayout());
		//this.setLayout(new BorderLayout());
		//this.add(boardArea, BorderLayout.EAST);


	}
	
	private PlayerArea createplayerArea(Dimension playerAreaDimension)  {
		
		PlayerArea playerArea = new PlayerArea(playerAreaDimension);
		return playerArea;

	}
	
	private MainBoard createBoardArea(Dimension boardDimension) {
		
		MainBoard board = new MainBoard(boardDimension);
		return board;
	
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
	
	