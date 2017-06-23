package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PlayerArea extends JPanel {

	public PlayerArea(Dimension playerAreaDimension) {
	
//il MainWindow launcher dovrà passare informazioni ai suoi metodi, per esempio dimensione e username dei players

	/*	PlayerDashboard player1Dash = new PlayerDashboard(); 
	PlayerDashboard player2Dash = new PlayerDashboard();
	PlayerDashboard player3Dash = new PlayerDashboard();
	PlayerDashboard player4Dash = new PlayerDashboard();*/
	
	String username = "Ciao";
	this.setPreferredSize(playerAreaDimension);
	Border border = BorderFactory.createLineBorder(Color.RED, 1);
	this.setBorder(border);
	
	this.setLayout(new BorderLayout());
	BoxLayout l = new BoxLayout(this, BoxLayout.Y_AXIS);
	this.setLayout(l);
	
	this.add(new PlayerDashboard(username, playerAreaDimension));
		
/*	this.add(player1Dash);
	this.add(player1Dash);
	this.add(player1Dash);
	this.add(player1Dash);*/
	
	}
	
	
	
}
