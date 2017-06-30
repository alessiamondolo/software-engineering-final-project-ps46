package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JTabbedPane leaderCardPanel = new JTabbedPane();
	private PlayerDashboard playerDashboard1;
	private PlayerDashboard playerDashboard2;
	private PlayerDashboard playerDashboard3;
	private PlayerDashboard playerDashboard4;
	private ZoomBox zoomBox = new ZoomBox();
	private Dimension playerAreaDimension = new Dimension();
	private Dimension dashboardAreaDimension = new Dimension();
	private ArrayList<CardCell> player1LeaderCardCells = new ArrayList <CardCell>();
	private ArrayList<CardCell> player2LeaderCardCells = new ArrayList <CardCell>();
	private ArrayList<CardCell> player3LeaderCardCells = new ArrayList <CardCell>();
	private ArrayList<CardCell> player4LeaderCardCells = new ArrayList <CardCell>();
	private double controlAreaWidth;
	private double controlAreaHeight;
	
	public PlayerArea(Dimension playerAreaDimension) {
		
		this.playerAreaDimension = playerAreaDimension;
		this.setPreferredSize(playerAreaDimension);
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		this.setBorder(border);
		
		this.setLayout(new BorderLayout());
		
		createDashboardArea();
		createZoomBox();
		createControlArea();
		createInfoArea();
	}
	
	public ArrayList<PlayerDashboard> getDashboards() {
		
		ArrayList <PlayerDashboard> list = new ArrayList<PlayerDashboard>();
		
		list.add(playerDashboard1);
		list.add(playerDashboard2);
		list.add(playerDashboard3);
		list.add(playerDashboard4);
		
		return list;	
	}
	
	private void createDashboardArea() {
		
		playerDashboard1 = new PlayerDashboard(playerAreaDimension);
		playerDashboard2 = new PlayerDashboard(playerAreaDimension);
		playerDashboard3 = new PlayerDashboard(playerAreaDimension);
		playerDashboard4 = new PlayerDashboard(playerAreaDimension);
		
		this.dashboardAreaDimension = new Dimension ((int) playerAreaDimension.getWidth(), 
		                              (int) (playerAreaDimension.getHeight()*9/27));
		
		dashboardArea.setPreferredSize(dashboardAreaDimension);
		dashboardArea.addTab("Player 1", null, playerDashboard1, "Tab 1");
		dashboardArea.setSelectedIndex(0);
		dashboardArea.addTab("Player 2", null, playerDashboard2, "Tab 2");
		dashboardArea.addTab("Player 3", null, playerDashboard3, "Tab 3");
		dashboardArea.addTab("Player 4", null, playerDashboard4, "Tab 4");
		
		this.add(dashboardArea, BorderLayout.NORTH);
	
	}
	
	private void createZoomBox() {
		
		double pWidth = playerAreaDimension.getWidth();
		double pHeight = playerAreaDimension.getHeight();
		
	    ImageZoom zoom = new ImageZoom(zoomBox);
	    JPanel chooseZoomPanel = zoom.getUIPanel();
	    chooseZoomPanel.setPreferredSize(new Dimension((int) pWidth/16, (int) (pHeight*3)/4));
	    chooseZoomPanel.setMaximumSize(chooseZoomPanel.getPreferredSize());
	    this.add(chooseZoomPanel, BorderLayout.WEST);
	    
	    zoomBox.setPreferredSize(new Dimension((int) (pWidth*7)/13, (int) (pHeight*5)/6));
	    zoomBox.setMaximumSize(zoomBox.getPreferredSize());
	    this.add(zoomBox, BorderLayout.CENTER);
	    this.add(new JScrollPane(zoomBox));
	}
	
	private void createControlArea() {
		
		double pWidth = playerAreaDimension.getWidth();
		double pHeight = playerAreaDimension.getHeight();
		this.controlAreaWidth = (pWidth*83)/208;
		this.controlAreaHeight = (pHeight*5)/6;
	
		JPanel controlArea = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		controlArea.setBorder(border);
		controlArea.setPreferredSize(new Dimension((int) controlAreaWidth, (int) controlAreaHeight));
		
		this.add(controlArea, BorderLayout.EAST);
		
		controlArea.setLayout(new BoxLayout(controlArea, BoxLayout.Y_AXIS));
		
		controlArea.add(leaderCardPanel);
		leaderCardPanel.setBorder(border);
		leaderCardPanel.setPreferredSize(new Dimension( (int) controlAreaWidth, (int) controlAreaHeight/4));
		leaderCardPanel.setMaximumSize(leaderCardPanel.getPreferredSize());
		leaderCardPanel.setMinimumSize(leaderCardPanel.getPreferredSize());
		
		controlArea.add(familyMembers);
		familyMembers.setBorder(border);
		
		controlArea.add(turnOrder);
		turnOrder.setBorder(border);
		
		addLeaderTabs(controlAreaWidth, controlAreaHeight);
	}
	
	private void createInfoArea() {
		
		double pWidth = playerAreaDimension.getWidth();
		double pHeight = playerAreaDimension.getHeight();
		
		JPanel infoArea = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		infoArea.setBorder(border);
		infoArea.setPreferredSize(new Dimension((int) (pWidth), (int) (pHeight)/6));
		this.add(infoArea, BorderLayout.SOUTH);
	}
	
	private void addLeaderTabs(double width, double height) {
		
		int i;
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
	
		JPanel player1LeaderCards = new JPanel();
		player1LeaderCards.setPreferredSize(new Dimension((int) width, (int) height/6));
		player1LeaderCards.setBorder(border);
		
		for (i = 0; i < 4; i++) {
			CardCell cardCell = new CardCell();
			cardCell.setPreferredSize(new Dimension( (int) width/5, (int) height/6));
			player1LeaderCards.add(cardCell);
			player1LeaderCardCells.add(cardCell);
		}
		
		JPanel player2LeaderCards = new JPanel();
		player2LeaderCards.setPreferredSize(new Dimension((int) width, (int) height/6));
		player2LeaderCards.setBorder(border);
		
		for (i = 0; i < 4; i++) {
			CardCell cardCell = new CardCell();
			cardCell.setPreferredSize(new Dimension( (int) width/5, (int) height/6));
			player2LeaderCards.add(cardCell);
			player2LeaderCardCells.add(cardCell);
		}
		
		JPanel player3LeaderCards = new JPanel();
		player3LeaderCards.setPreferredSize(new Dimension((int) width, (int) height/6));
		player3LeaderCards.setBorder(border);
		
		for (i = 0; i < 4; i++) {
			CardCell cardCell = new CardCell();
			cardCell.setPreferredSize(new Dimension( (int) width/5, (int) height/6));
			player3LeaderCards.add(cardCell);
			player3LeaderCardCells.add(cardCell);
		}
		
		JPanel player4LeaderCards = new JPanel();
		player4LeaderCards.setPreferredSize(new Dimension((int) width, (int) height/6));
		player4LeaderCards.setBorder(border);
		
		for (i = 0; i < 4; i++) {
			CardCell cardCell = new CardCell();
			cardCell.setPreferredSize(new Dimension( (int) width/5, (int) height/6));
			player4LeaderCards.add(cardCell);
			player4LeaderCardCells.add(cardCell);
		}
		
		leaderCardPanel.addTab("P 1", null, player1LeaderCards, "Tab 1");
		leaderCardPanel.setSelectedIndex(0);
		leaderCardPanel.addTab("P 2", null, player2LeaderCards, "Tab 2");
		leaderCardPanel.addTab("P 3", null, player3LeaderCards, "Tab 3");
		leaderCardPanel.addTab("P 4", null, player4LeaderCards, "Tab 4");
	}
}
