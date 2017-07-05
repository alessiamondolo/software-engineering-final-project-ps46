package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;


public class MilitaryTower extends JPanel {
	
	private Game game;
	private Dimension tokenDimension;   //probabilmente questo non serve salvarlo

	private ArrayList<PointCell> militaryPointCells = new ArrayList<PointCell>();
	
	public MilitaryTower(double width, double height) {
		
		//this.tokenDimension = new Dimension((int) ((width*7)/25), (int) ((height*7)/21));
		Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
		this.setBorder(border);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension((int) (2*width), (int) (29*height)));	
		System.out.println(String.valueOf(width));
		System.out.println(String.valueOf(height));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));    
		
		this.add(new Box.Filler(new Dimension((int)(width*8)/25, (int)(height*8)/20), new Dimension((int)(width*8)/25, (int)(height*8)/20), new Dimension((int)(width*8)/25, (int)(height*8)/20)));
		
		for (int i = 0; i < 26; i++) {

			PointCell cell = new PointCell();
			
			if (i == 25) {
				cell.setPreferredSize(new Dimension((int) (2*width), (int) (2*height)));
				cell.setMaximumSize(getPreferredSize());
				cell.setMinimumSize(getPreferredSize());
				this.add(cell);
			} else {
				cell.setPreferredSize(new Dimension((int) (9*width), (int) height));
				cell.setMaximumSize(getPreferredSize());
				cell.setMinimumSize(getPreferredSize());
				this.add(cell);
			}
			
			militaryPointCells.add(cell);
		}

		provaTokenGUI();
	}
	
	public void updateMilitaryPoints(Game game, Dimension tokenDimension) {
		
		this.game = game;
		
		//algoritmo da verificare
		
		for (Player player : game.getPlayers()) {
			int vp = player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("VictoryPoints").getQuantity();
			for (int i = 0; i < militaryPointCells.size(); i++) {
				PointCell mc = militaryPointCells.get(i); 
				
				ArrayList<Player> players = mc.getItemList();
				for ( Player cellPlayer : players) {
					if ( player.getIdPlayer() == cellPlayer.getIdPlayer()) {
						mc.remove(cellPlayer);
					}
				}
				if (i == (vp - (25 - i*2))) {
						mc.add(player);
					} 
						
			}
		}	
	}
	
	//metodo di prova
	
	public void provaTokenGUI() {
		
		PointCell cell = null; 
		for (int i = 0; i < 26; i ++) {
			cell = militaryPointCells.get(i);
			cell.repaint();
		}
	
	}
}
