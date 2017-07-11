package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;


public class MilitaryTower extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1584775035550323431L;
	
	private ArrayList<PointCell> militaryPointCells = new ArrayList<PointCell>();
	
	public MilitaryTower(double width, double height) {
		
		Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
		this.setBorder(border);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension((int) (2*width), (int) (29*height)));	
		
		
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
	}
	
	public void updateMilitaryPoints(Game game, Dimension tokenDimension) {
		
		for (PointCell pc : militaryPointCells) {
			pc.removeAll();
		}
		
		for (Player player : game.getPlayers()) {
			int mp;
			try {
				mp = player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("MilitaryPoints").getQuantity();
				
			} catch (NullPointerException e) {
				mp = 0;
			}
			militaryPointCells.get(25 - mp).add(player);
		}	
		repaint();
	}
}
