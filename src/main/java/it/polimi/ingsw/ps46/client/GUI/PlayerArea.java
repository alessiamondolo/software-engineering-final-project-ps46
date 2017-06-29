package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

public class PlayerArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2761715758225360886L;
	
	private Game game;
	
	private JTabbedPane dashboardArea = new JTabbedPane();
	private JPanel turnOrder = new JPanel();
	private JPanel familyMembers = new JPanel();
	private JTabbedPane leaderCard = new JTabbedPane();
	private PlayerDashboard playerDashboard1;
	private PlayerDashboard playerDashboard2;
	private PlayerDashboard playerDashboard3;
	private PlayerDashboard playerDashboard4;
	
	
	public PlayerArea(Dimension playerAreaDimension) {
		
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
		
		// TODO qui ho messo un Player fittizio di prova
		Player player = new Player(3);
		player.setColor("Red");
		
		playerDashboard1 = new PlayerDashboard(playerAreaDimension);
		playerDashboard2 = new PlayerDashboard(playerAreaDimension);
		playerDashboard3 = new PlayerDashboard(playerAreaDimension);
		playerDashboard4 = new PlayerDashboard(playerAreaDimension);
		
		dashboardArea.setBorder(border);
		dashboardArea.addTab("Player 1", null, playerDashboard1, "Tab 1");
		dashboardArea.setSelectedIndex(0);
		dashboardArea.addTab("Player 2", null, playerDashboard2, "Tab 2");
		dashboardArea.addTab("Player 3", null, playerDashboard3, "Tab 3");
		dashboardArea.addTab("Player 4", null, playerDashboard4, "Tab 4");
		
		this.add(dashboardArea, gbc);
		
	}
	
	public ArrayList<PlayerDashboard> getDashboards() {
		
		ArrayList <PlayerDashboard> list = new ArrayList<PlayerDashboard>();
		
		list.add(playerDashboard1);
		list.add(playerDashboard2);
		list.add(playerDashboard3);
		list.add(playerDashboard4);
		
		return list;	
	}
	
}
