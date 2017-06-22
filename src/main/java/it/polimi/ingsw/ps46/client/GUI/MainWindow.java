package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	
	
	
	public MainWindow() {
		
		// da mettere un check che le dimensioni dei component siano < di quelle della Wind
		//così sto però dando per scontata la risoluzione dell'utente
		
		int mainWindowWidth = 1400;
		int mainWindowHeight = 900;
		int boardWidth = (mainWindowWidth * 6)/ 14;
		int boardHeight = (mainWindowHeight * 7) / 9;
		int dashboardAreaWidth = (mainWindowWidth * 65)/ 140;
		
		Dimension dashboardAreaDimension = new Dimension(dashboardAreaWidth, boardHeight);
		Dimension boardDimension = new Dimension(boardWidth, boardHeight);
		
		this.setTitle("Lorenzo il Magnifico");
		this.setPreferredSize(new Dimension(mainWindowWidth, mainWindowHeight));
		this.add(createDashboardArea(dashboardAreaDimension), BorderLayout.WEST);
		this.add(createBoardArea(boardDimension));
		
		  //parametri che dovrebbero essere calcolati in base alla risoluzione dello schermo su cui il programma gira
		
		//this.getContentPane().setLayout(new BorderLayout());
		//this.setLayout(new BorderLayout());
		//this.add(boardArea, BorderLayout.EAST);


	}
	
	private JPanel createDashboardArea(Dimension dashboardAreaDimension)  {
		
		JPanel dashboardArea = new JPanel();
		
		PlayerDashboard player1Dash = new PlayerDashboard(); 
		PlayerDashboard player2Dash = new PlayerDashboard();
		PlayerDashboard player3Dash = new PlayerDashboard();
		PlayerDashboard player4Dash = new PlayerDashboard();
		
		dashboardArea.setPreferredSize(dashboardAreaDimension);
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		dashboardArea.setBorder(border);
		
		dashboardArea.setLayout(new BorderLayout());
		BoxLayout l = new BoxLayout(dashboardArea, BoxLayout.Y_AXIS);
		dashboardArea.setLayout(l);
			
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);

		
		
		return dashboardArea;

	}
	
	private MainBoard createBoardArea(Dimension boardDimension) {
		
		MainBoard board = new MainBoard(boardDimension);
		return board;
	
	}
	

	
		/*PlayerDashboard player1Dash = new PlayerDashboard(); 
		PlayerDashboard player2Dash = new PlayerDashboard();
		PlayerDashboard player3Dash = new PlayerDashboard();
		PlayerDashboard player4Dash = new PlayerDashboard();
		
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);
		dashboardArea.add(player1Dash);*/
	
	
}
	
	