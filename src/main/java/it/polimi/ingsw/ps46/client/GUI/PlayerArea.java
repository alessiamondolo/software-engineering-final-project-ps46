package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	private TurnPanel turnOrder;
	private FMPanel fmPanel;
	private JTabbedPane leaderCardPanel = new JTabbedPane();
	private ArrayList<PlayerDashboard> dashboards = new ArrayList<PlayerDashboard>();
	private ArrayList<LeaderCardBox> lCardsBoxes = new ArrayList<LeaderCardBox>();
	private Dimension playerAreaDimension = new Dimension();
	private Dimension dashboardAreaDimension = new Dimension();

	private double controlAreaWidth;
	private double controlAreaHeight;
	private InfoArea infoPanel;
	ArrayList<String> playersnames = new ArrayList<String>();

	
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
		
	int nr = this.game.getNumberPlayers();
		
		for (int i = 0; i < nr; i++) {
			
			playersnames.add(game.getPlayers().get(i).getUsername());
			Player player = this.game.getPlayers().get(i);
			PlayerDashboard playerDashboard = new PlayerDashboard(playerAreaDimension, player);
			dashboards.add(playerDashboard);
		}
		
		this.dashboardAreaDimension = new Dimension ((int) playerAreaDimension.getWidth(), 
		                              (int) (playerAreaDimension.getHeight()*9/27));
		
		dashboardArea.setPreferredSize(dashboardAreaDimension);
		
		for (int i = 0; i < dashboards.size(); i++) {
			
			dashboardArea.addTab(playersnames.get(i), null, dashboards.get(i), null);
		}
		
		dashboardArea.setSelectedIndex(0);
		
		this.add(dashboardArea, BorderLayout.NORTH);
	
	}
	
	private void createZoomBox() {
		
		double pWidth = playerAreaDimension.getWidth();
		double pHeight = playerAreaDimension.getHeight();
		
	    ZoomBox zoom = ZoomBox.getZoomBox();
	    this.add(zoom, BorderLayout.WEST);  
	    zoom.setPreferredSize(new Dimension((int) (pWidth*31)/52, (int) (pHeight*5)/6));

	}
	
	private void createControlArea() {
		
		double pWidth = playerAreaDimension.getWidth();
		double pHeight = playerAreaDimension.getHeight();
		this.controlAreaWidth = (pWidth*83)/208;
		this.controlAreaHeight = (pHeight*5)/6;
		this.setOpaque(true);
		this.setBackground(new Color(200, 134, 145, 123));
	
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
		
		Dimension dimension = new Dimension( (int) controlAreaWidth, (int) controlAreaHeight/4);
		this.fmPanel = new FMPanel(dimension);
		fmPanel.setPreferredSize(dimension);
		fmPanel.setMaximumSize(leaderCardPanel.getPreferredSize());
		
		controlArea.add(fmPanel);
		
		this.turnOrder = new TurnPanel(dimension);
		controlArea.add(turnOrder);
		turnOrder.setMaximumSize(leaderCardPanel.getPreferredSize());
		
		addLeaderTabs(controlAreaWidth, controlAreaHeight);
	}
	
	private void createInfoArea() {
		
		double pWidth = playerAreaDimension.getWidth();
		double pHeight = playerAreaDimension.getHeight();
		Dimension dimension = new Dimension((int) (pWidth), (int) (pHeight)/6);
		
		this.infoPanel = new InfoArea(dimension);
		infoPanel.setPreferredSize(dimension);
		this.add(infoPanel, BorderLayout.SOUTH);
	}
	
	private void addLeaderTabs(double width, double height) {
		
		int i;
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
	
		for ( int x = 0; x < game.getNumberPlayers(); x++) {   //game.getNumberPlayers()
			
			Player player = this.game.getPlayers().get(x);
			LeaderCardBox playerLeaderCards = new LeaderCardBox(player, width, height);
			playerLeaderCards.setOpaque(true);
			playerLeaderCards.setBackground(new Color(213, 50, 90, 123));
			playerLeaderCards.setPreferredSize(new Dimension((int) width, (int) height/6));
			playerLeaderCards.setBorder(border);
			lCardsBoxes.add(playerLeaderCards);
			
		}	
		
		for ( i = 0; i < lCardsBoxes.size(); i++) {
			
			leaderCardPanel.addTab(playersnames.get(i), null, lCardsBoxes.get(i), null);
		
		}
	
	}
	
	
	public void update(Game game) {
		this.game = game;
		
		for ( PlayerDashboard pd : dashboards) {
			pd.update(this.game);
		}
		
		for ( LeaderCardBox lBox : lCardsBoxes) {
			lBox.update(this.game);
		}
		
		fmPanel.update(game);
		turnOrder.update(game);
		infoPanel.update(game);
		
	}
	
}
