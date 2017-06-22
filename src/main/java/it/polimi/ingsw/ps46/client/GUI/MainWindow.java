package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * MainWindow Class is the GUI root Container for the main game screen, here are placed all 
 * the various sub-layers and sub-containers, starting with four PlayerDashboard placed
 * on the left and a MainBoard on the right.
 * @author lorenzo
 *
 */

public class MainWindow extends JFrame {
	
	private JPanel dashboardArea = new JPanel();
	private JPanel boardArea = new JPanel();
	
	public MainWindow() {
		
		// da mettere un check che le dimensioni dei component siano < di quelle della Wind
		//così sto però dando per scontata la risoluzione dell'utente
		
		int mainWindowWidth = 1400;  //parametri che dovrebbero essere calcolati in base alla risoluzione dello schermo su cui il programma gira
		int mainWindowHeight = 900;
		
		int boardWidth = (mainWindowWidth * 6)/ 14;
		int boardHeight = (mainWindowHeight * 7) / 9;
		int dashboardAreaWidth = (mainWindowWidth * 65)/ 140;
		
		Dimension dashboardAreaDimension = new Dimension(dashboardAreaWidth, boardHeight);
		Dimension boardDimension = new Dimension(boardWidth, boardHeight);
		
		this.setLayout(new BorderLayout());
		this.setTitle("Lorenzo il Magnifico");
		this.setPreferredSize(new Dimension(mainWindowWidth, mainWindowHeight));
		dashboardArea.setPreferredSize(dashboardAreaDimension);
		//this.add(dashboardArea);
		this.add(dashboardArea, BorderLayout.WEST);
		boardArea.setPreferredSize(boardDimension);
		this.add(boardArea);
		//this.add(boardArea, BorderLayout.EAST);
		
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		dashboardArea.setBorder(border);
		
		setDashboardArea();
		setBoardArea(boardDimension);
		
		//BoardArea.setPreferredSize(new Dimension(boardWidth, boardHeight));
	
	}
	
	private void setDashboardArea() {
		
		//MainBoard board = new MainBoard(new Dimension (400, 500));
		//dashboardArea.add(board);
	
		/*PlayerDashboard player1Dash = new PlayerDashboard(); 
		PlayerDashboard player2Dash = new PlayerDashboard();
		PlayerDashboard player3Dash = new PlayerDashboard();
		PlayerDashboard player4Dash = new PlayerDashboard();
		
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);*/
	
	}
	
	private void setBoardArea(Dimension boardDimension) {
		
		MainBoard board = new MainBoard(boardDimension);
		boardArea.add(board);
		//boardArea.setLayout(new BorderLayout());
		//boardArea.setLayout(new FlowLayout());
		//boardArea.add(board, FlowLayout.CENTER);
	}
}
