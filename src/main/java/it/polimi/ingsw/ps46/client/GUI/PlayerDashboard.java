package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.GameState;
import it.polimi.ingsw.ps46.server.PersonalBoard;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * A dashboard that allows the player to visualizes resources and cards. 
 * @author lorenzo
 *
 */

// TODO da rendere la dashboard associata ai giocatore, il player che visualizza il terminale deve avere vista preferenziale

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
	
	private ArrayList <CardCell> buildingCardCells = new ArrayList <CardCell> ();
	private ArrayList <CardCell> territoryCardCells = new ArrayList <CardCell> ();
	private ArrayList <CardCell> ventureCardCells = new ArrayList <CardCell> ();
	private ArrayList <CardCell> characterCardCells = new ArrayList <CardCell> ();
	private ArrayList <Card> cards = new ArrayList <Card> ();
	
	private int buildingCardsCounter = 0;
	private int territoryCardsCounter = 0;
	private int ventureCardsCounter = 0;
	private int characterCardsCounter = 0;
	
	public PlayerDashboard(Dimension playerAreaDimension, Player player) {
		
		this.player = player;
		this.dashboardHeight = (playerAreaDimension.getHeight()*8)/28;
		this.dashboardWidth = (playerAreaDimension.getWidth()*12)/13;
		this.bonusTileWidth = (playerAreaDimension.getWidth()*3)/65;
		
		this.dashboardDimension = new Dimension((int) dashboardWidth, (int) dashboardHeight);
		
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
		GridBagConstraints gbc;
		JLabel dashboardImage = new JLabel();
		moneyValue.setForeground(Color.WHITE);
		woodValue.setForeground(Color.WHITE);
		stonesValue.setForeground(Color.WHITE);
		servantsValue.setForeground(Color.WHITE);
		
		for (int i = 0; i < 36; i++) {
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		CardCell cardcell = new CardCell();

		
		if ( 0 <= i && i < 12) {
				gbc.gridx = i;
				gbc.gridy = 0;
				gbc.gridheight = 1;
				cardcell.setPreferredSize(new Dimension( (int) dashboardWidth/12, (int) (3*dashboardHeight)/8));
				cardcell.setMaximumSize(cardcell.getPreferredSize());
				cardcell.setMinimumSize(cardcell.getPreferredSize());
				if (i < 6) {
					buildingCardCells.add(cardcell);
				} else {
					ventureCardCells.add(cardcell);
				}
			} else if ( 12 <= i && i < 24 ) {
				gbc.gridx = i - 12;
				gbc.gridy = 3;
				gbc.gridheight = 1;
				cardcell.setPreferredSize(new Dimension( (int) dashboardWidth/12, (int) (3*dashboardHeight)/8));
				cardcell.setMaximumSize(cardcell.getPreferredSize());
				cardcell.setMinimumSize(cardcell.getPreferredSize());
				if (i < 18) {
					territoryCardCells.add(cardcell);
				} else {
					characterCardCells.add(cardcell);
				}
			} else if ( 24 <= i && i < 36) {
				gbc.gridx = i - 24;
				gbc.gridy = 6;
				gbc.gridheight = 1;
				gbc.weighty = 0.60;
				cardcell.setPreferredSize(new Dimension( (int) dashboardWidth/12, (int) (2*dashboardHeight)/8));
				cardcell.setMaximumSize(cardcell.getPreferredSize());
				cardcell.setMinimumSize(cardcell.getPreferredSize());
			}
		
		if (i == 24) {
			moneyValue.setHorizontalAlignment(SwingConstants.CENTER);
			dashboard.add(moneyValue, gbc);
		} else if (i == 25) {
			woodValue.setHorizontalAlignment(SwingConstants.CENTER);
			dashboard.add(woodValue, gbc);
		} else if (i == 26) {
			stonesValue.setHorizontalAlignment(SwingConstants.CENTER);
			dashboard.add(stonesValue, gbc);
		} else if (i == 27) {
			servantsValue.setHorizontalAlignment(SwingConstants.CENTER);
			dashboard.add(servantsValue, gbc);
		} 
		
			dashboard.add(cardcell, gbc);
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
		
/*		Image img = territoryCardCells.get(0).loadCard(4);
		Image img1 = ventureCardCells.get(5).loadCard(5);
		Image img2 = characterCardCells.get(3).loadCard(6);
		Image img3 = buildingCardCells.get(4).loadCard(78);
		
		ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		territoryCardCells.get(0).setIcon(imageIcon);
		imageIcon = new ImageIcon(img1.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		ventureCardCells.get(5).setIcon(imageIcon);
		imageIcon = new ImageIcon(img2.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		characterCardCells.get(3).setIcon(imageIcon);
		imageIcon = new ImageIcon(img3.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		buildingCardCells.get(4).setIcon(imageIcon);*/
		
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
	 * This needs to be called in order to display resource accordingly to the player's values stored in model
	 */
	
	public void update(Game game) {
		
		this.game = game;
		
		updateResource();	
		updateCards();
	}
	
	
	private void updateResource() {
		
		System.out.println("sono " +this.player.getUsername()+ "e sto provando a fare update della dash");
		PersonalBoard board = this.player.getPersonalBoard();
		GameState gameState = this.game.getGameState();
		System.out.println(gameState);
		
		if (board == null) {
			System.out.println("Board null");
		}
		
		ResourceSet rs =this.player.getPersonalBoard().getPlayerResourceSet();
		if (rs == null) {
			System.out.println("rs null");
		}
		
		LinkedHashMap<String, Resource> rm = this.player.getPersonalBoard().getPlayerResourceSet().getResourcesMap();
		if (rm == null) {
			System.out.println("rm null, ossia l'ultima invocazione");
		}
		
		LinkedHashMap<String, Resource> map = this.player.getPersonalBoard().getPlayerResourceSet().getResourcesMap();
		moneyValue.setText(String.valueOf(map.get("Money").getQuantity()));
		woodValue.setText(String.valueOf(map.get("Wood").getQuantity()));
		stonesValue.setText(String.valueOf(map.get("Stones").getQuantity()));
		servantsValue.setText(String.valueOf(map != null && map.get("Servants") != null ? map.get("Servants").getQuantity() : 0));	
	}
	
	//controllare che le associazioni sugli indici delle strutture dati siano valide
	//attenzione eccezioni! cosa succede quando cerco di accedere 
	
	private void updateCards() {
		
			for (int i = 0; i < this.player.getPersonalBoard().getBuildingDeck().size(); i++) {
				Card card = this.player.getPersonalBoard().getBuildingDeck().get(i);
				if (! cards.contains(card)) {
					cards.add(card);
					buildingCardCells.get(buildingCardsCounter).add(card);
					buildingCardsCounter ++;
				}
			}
			
			for (int i = 0; i < this.player.getPersonalBoard().getVentureDeck().size(); i++) {
				Card card = this.player.getPersonalBoard().getVentureDeck().get(i);
				if (! cards.contains(card)) {
					cards.add(card);
					ventureCardCells.get(ventureCardsCounter).add(card);
					ventureCardsCounter ++;
				}
			}
			
			for (int i = 0; i < this.player.getPersonalBoard().getTerritoryDeck().size(); i++) {
				Card card = this.player.getPersonalBoard().getTerritoryDeck().get(i);
				if (! cards.contains(card)) {
					cards.add(card);
					territoryCardCells.get(territoryCardsCounter).add(card);
					territoryCardsCounter ++;
				}
			}
			
			for (int i = 0; i < this.player.getPersonalBoard().getCharacterDeck().size(); i++) {
				Card card = this.player.getPersonalBoard().getCharacterDeck().get(i);
				if (! cards.contains(card)) {
					cards.add(card);
					characterCardCells.get(characterCardsCounter).add(card);
					characterCardsCounter ++;
				}
			}		
	}
}
