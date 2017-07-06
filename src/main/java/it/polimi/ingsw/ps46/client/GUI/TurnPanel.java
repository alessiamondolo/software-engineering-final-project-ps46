package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

public class TurnPanel extends JPanel  {

	private ArrayList<PointCell> turnCells = new ArrayList<PointCell>();
	
	public TurnPanel(Dimension dimension) {
		
		this.setOpaque(true);
		this.setBackground(new Color(200, 134, 145, 123));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Current Turn Order:"));
		
		for (int i = 0; i < 4; i++) {
					
				PointCell turnCell = new PointCell();
				turnCell.setBackground(Color.lightGray);
				turnCell.setOpaque(true);
				turnCells.add(turnCell);
				turnCell.setPreferredSize((new Dimension(dimension.width/5,dimension.height/3)));
				this.add(turnCell);
		}
			
	}

	public void update(Game game) {
		
		for ( PointCell cell : turnCells) {
			cell.itemList.clear();
		}
		
		ArrayList<Player> players = (ArrayList<Player>) game.getPlayers();
		for ( int i = 0; i < players.size(); i++) {
			PointCell cell = turnCells.get(i);
			Player player = players.get(i);
			cell.add(player);
		}
		
	}
	
}
