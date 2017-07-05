package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
	private ArrayList<PlayerDashboard> dashboards = new ArrayList<PlayerDashboard>();
	private ArrayList<JPanel> lCardsPanels = new ArrayList<JPanel>();
	private ZoomBox zoomBox = new ZoomBox();
	private Dimension playerAreaDimension = new Dimension();
	private Dimension dashboardAreaDimension = new Dimension();
	private ArrayList<CardCell> player1LeaderCardCells = new ArrayList <CardCell>();
	private ArrayList<CardCell> player2LeaderCardCells = new ArrayList <CardCell>();
	private ArrayList<CardCell> player3LeaderCardCells = new ArrayList <CardCell>();
	private ArrayList<CardCell> player4LeaderCardCells = new ArrayList <CardCell>();
	private double controlAreaWidth;
	private double controlAreaHeight;

	
	public PlayerArea(Dimension playerAreaDimension, Game game) {
		
		this.game = game;
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

		return dashboards;	
	}
	
	private void createDashboardArea() {
		
/*		int nr = this.game.getNumberPlayers();
		
		for (int i = 0; i < nr; i++) {
			
			Player player = this.game.getPlayers().get(i);
			PlayerDashboard playerDashboard = new PlayerDashboard(playerAreaDimension, player);
			System.out.println("Sto creando la dashboard di " +player.getUsername());
			dashboards.add(playerDashboard);
		}
		
		this.dashboardAreaDimension = new Dimension ((int) playerAreaDimension.getWidth(), 
		                              (int) (playerAreaDimension.getHeight()*9/27));
		
		dashboardArea.setPreferredSize(dashboardAreaDimension);
		
		for (int i = 0; i < dashboards.size(); i++) {
			
			dashboardArea.addTab("Player " +(i + 1), null, dashboards.get(i), null);
		}
		
		dashboardArea.setSelectedIndex(0);
		
		this.add(dashboardArea, BorderLayout.NORTH);*/
	
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
	
		for ( int x = 0; x < 4; x++) {   //game.getNumberPlayers();
			
			JPanel playerLeaderCards = new JPanel();
			playerLeaderCards.setPreferredSize(new Dimension((int) width, (int) height/6));
			playerLeaderCards.setBorder(border);
			lCardsPanels.add(playerLeaderCards);
			
			for (i = 0; i < 4; i++) {
				CardCell cardCell = new CardCell();
				cardCell.setPreferredSize(new Dimension( (int) width/5, (int) height/6));
				playerLeaderCards.add(cardCell);
				
				if ( x == 0 ) {
					player1LeaderCardCells.add(cardCell);
				} else if ( x == 1) {
					player2LeaderCardCells.add(cardCell);
				} else if ( x == 2 ) {                //&& game.getNumberPlayers() == 3
					player3LeaderCardCells.add(cardCell);
				} else if ( x == 3 ) {         //&& game.getNumberPlayers() == 4
					player4LeaderCardCells.add(cardCell);
				}
				
			}
		}	
		
		for ( i = 0; i < lCardsPanels.size(); i++) {
			
			leaderCardPanel.addTab("P " +(i + 1), null, lCardsPanels.get(i), null);
		
		}
	
	}
	
	
	public void update(Game game) {
		this.game = game;
		
		for ( PlayerDashboard pd : dashboards) {
			pd.update(this.game);
		}
		
		//TODO da pensare a come visualizzare e fare update delle carte leader. Complesso.
		// deve coesistere anche con la modalità di gioco basic advanced
		
	}
	
}
