package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Player;

/**
 * A cell specific for the external grid. All players can place a token inside the cell.
 * This class provides functionalities the add, remove and show tokens on the external grid,
 * the one associated to victory points
 * @author lorenzo
 *
 */

public class PointCell extends Cell<Player> {
	
	private static final long serialVersionUID = 591002007000957139L;
	
	public PointCell() {
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paint(new Dimension(5, 5));
	}
	
	public void paint(Dimension dimension) {
		
		this.removeAll();

		this.add(new Token(dimension));
		this.add(new Token(dimension));
		this.add(new Token(dimension));
		this.add(new Token(dimension));
		
		/*for (player p : itemList) {
			
			this.add(new Token(p.getColor));
			
		}*/

		//System.out.println(String.valueOf(itemList.get(2).getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("MilitaryPoints)")));
		
	}
	
}

