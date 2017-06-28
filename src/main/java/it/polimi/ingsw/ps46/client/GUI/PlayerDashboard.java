package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.PersonalBoard;
import it.polimi.ingsw.ps46.server.Player;

/**
 * A dashboard that allows the player to visualizes resources and cards. 
 * @author lorenzo
 *
 */

public class PlayerDashboard extends JPanel {

	private static final long serialVersionUID = 1221239488547892322L;
	
	private Game game;
	private Player player;
	private JPanel dashboard;
	private JLabel bonusTile;
	
	private Dimension dashboardDimension;
	private double dashboardHeight;
	private double dashboardWidth;
	private double bonusTileWidth;
	
	private JLabel moneyValue = new JLabel();
	private JLabel woodValue = new JLabel();
	private JLabel stonesValue = new JLabel();
	private JLabel servantsValue = new JLabel();
	
	public PlayerDashboard(Dimension playerAreaDimension) {
		
		this.dashboardHeight = (playerAreaDimension.getHeight()*8)/28;
		this.dashboardWidth = (playerAreaDimension.getWidth()*12)/13;
		this.bonusTileWidth = (playerAreaDimension.getWidth()*3)/65;
		
		this.dashboardDimension = new Dimension((int) dashboardWidth, (int) dashboardHeight);
		
		System.out.println(String.valueOf(dashboardHeight));
		System.out.println(String.valueOf(dashboardWidth));
		
		this.bonusTile= createBonusTile();
		this.add(bonusTile);
		this.dashboard = createDashboard(); 
		this.add(dashboard);
			
	}

	/**
	 * Arrange a GridBagLayout in which are placed the dashboard image and CardCells
	 * @return
	 */
	
	private JPanel createDashboard() {
		
		JPanel dashboard = new JPanel();
		dashboard.setPreferredSize(dashboardDimension);
		GridBagLayout gbl = new GridBagLayout();
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		dashboard.setBorder(border);
		dashboard.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel dashboardImage = new JLabel();
		 
	
		
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		for (int i = 0; i < 96; i++) {
			
			JLabel l = new JLabel();
			l.setText(String.valueOf(i));
			Border b1 = BorderFactory.createLineBorder(Color.BLACK, 1);
			l.setBorder(b1);
			
			if ( 0 <= i && i < 12 ) {
				gbc.gridx = i;
				gbc.gridy = 0;	
			} else if ( 12 <= i && i < 24 ) {
				gbc.gridx = i - 12;
				gbc.gridy = 1;
			} else if ( 24 <= i && i < 36 ) {
				gbc.gridx = i - 24;
				gbc.gridy = 2;
			} else if ( 36 <= i && i < 48) {
				gbc.gridx = i - 36;
				gbc.gridy = 3;
			} else if ( 48 <= i && i < 60) {
				gbc.gridx = i - 48;
				gbc.gridy = 4;
			} else if ( 60 <= i && i < 72) {
				gbc.gridx = i - 60;
				gbc.gridy = 5;
			} else if ( 72 <= i && i < 84 ) {
				gbc.gridx = i - 72;
				gbc.gridy = 6;
			} else if ( 84 <= i && i < 96 ) {
				gbc.gridx = i - 84;
				gbc.gridy = 7;
			} 
			
			if (i == 84) {
				moneyValue.setHorizontalAlignment(SwingConstants.CENTER);
				dashboard.add(moneyValue, gbc);
			} else if (i == 85) {
				woodValue.setHorizontalAlignment(SwingConstants.CENTER);
				dashboard.add(woodValue, gbc);
			} else if (i == 86) {
				stonesValue.setHorizontalAlignment(SwingConstants.CENTER);
				dashboard.add(stonesValue, gbc);
			} else if (i == 87) {
				servantsValue.setHorizontalAlignment(SwingConstants.CENTER);
				dashboard.add(servantsValue, gbc);
			} 
			
			dashboard.add(l, gbc);
		}
		
		dashboardImage.setIcon(returndashboardImage());
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 12;
		gbc.gridheight = 8;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		dashboard.add(dashboardImage, gbc);
		
		return dashboard;	
	
	}
	
	private ImageIcon returndashboardImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("img/dashboard/dashboard.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Image img = image.getScaledInstance((int) (dashboardWidth/2 - 5), (int) dashboardHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);

		return imageIcon;
	}
	
	
	private JLabel createBonusTile() {
		
		JLabel bonusTile = new JLabel();
		
		//if (game == basic) 	TODO
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("img/mixed/personalbonustile_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Image img = image.getScaledInstance((int) bonusTileWidth, (int) dashboardHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);
		bonusTile.setIcon(imageIcon);

		return bonusTile;
		
	}
	
	/**
	 * Method to be called at early stages, as soon as the players list is available this 
	 * associates a dashboard to a player.
	 * @param player
	 */
	
	public void setPlayer(Player player) {
		
		this.player = player;
	
	}
 
	
	
	/**
	 * This needs to be called in order to display resource accordingly to the player's values stored in model
	 */
	
	public void update(Game game) {
		
		this.game = game;
		
		moneyValue.setText(String.valueOf(this.player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Money").getQuantity()));
		woodValue.setText(String.valueOf(this.player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Wood").getQuantity()));
		stonesValue.setText(String.valueOf(this.player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Stones").getQuantity()));
		servantsValue.setText(String.valueOf(this.player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").getQuantity()));	
	
	}
	
}
