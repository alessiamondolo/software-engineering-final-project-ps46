package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class PlayerArea extends JPanel {

	JTabbedPane dashboardArea = new JTabbedPane();
	
	public PlayerArea(Dimension playerAreaDimension) {

		
//la Guiview dovrà passare informazioni ai suoi metodi, per esempio dimensione e username dei players
	
	String username = "Ciao";
	this.setPreferredSize(playerAreaDimension);
	Border border = BorderFactory.createLineBorder(Color.RED, 1);
	this.setBorder(border);
	
	this.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	
	gbc.fill = GridBagConstraints.BOTH;  
	gbc.gridwidth = 24;
	gbc.gridheight = 8;
	gbc.weightx = 1;
	gbc.weighty = 1;
	gbc.gridx = 2;
	gbc.gridy = 4;
	
	dashboardArea.setBorder(border);
	dashboardArea.addTab("Player 1", null, new PlayerDashboard(username, playerAreaDimension), "Tab 1");
	dashboardArea.setSelectedIndex(0);
	dashboardArea.addTab("Player 2", null, new PlayerDashboard(username, playerAreaDimension), "Tab 2");
	dashboardArea.addTab("Player 3", null, new PlayerDashboard(username, playerAreaDimension), "Tab 3");
	dashboardArea.addTab("Player 4", null, new PlayerDashboard(username, playerAreaDimension), "Tab 4");
	
	this.add(dashboardArea, gbc);
	
	}
	
	
	
}
