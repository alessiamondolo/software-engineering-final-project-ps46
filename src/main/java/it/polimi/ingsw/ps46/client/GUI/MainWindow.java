package it.polimi.ingsw.ps46.client.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * MainWindow Class is the GUI root Container for the main game screen, here are placed all 
 * the various sub-layers and sub-containers, starting with four PlayerDashboard placed
 * on the left and a MainBoard on the right.
 * @author lorenzo
 *
 */

public class MainWindow extends JFrame {
	
	private PlayerDashboard player1Dash = new PlayerDashboard(); 
	private PlayerDashboard player2Dash = new PlayerDashboard();
	private PlayerDashboard player3Dash = new PlayerDashboard();
	private PlayerDashboard player4Dash = new PlayerDashboard();
	
	private MainBoard mainBoard = new MainBoard();
	

	
	public MainWindow() {
		
		//pensare al layout	e a come strutturare la schermata se ci sono pochi players
		this.add(player1Dash);
		this.add(player1Dash);
		this.add(player1Dash);
		this.add(player1Dash);
		
		this.add(mainBoard);
	}
}
