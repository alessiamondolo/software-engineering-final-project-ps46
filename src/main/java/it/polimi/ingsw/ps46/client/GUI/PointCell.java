package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
	
	public PointCell(int action) {
		super(action);
		
	}
	
	public void update() {
		
		this.removeAllToken();
		
		for (Player p : itemList) {
			Token t = new Token(p.getColor());
			
			t.setPreferredSize(computeTokenSize());
			this.add(t);
		
		}
		
		repaint();
	}
	
	public void update(String fmColor) {
		
		this.removeAllToken();
		
		for (Player p : itemList) {
			Token t = new Token(p.getColor(), fmColor);
			t.setPreferredSize(computeTokenSize());
			this.setLayout(new FlowLayout(CENTER, ((int) this.getPreferredSize().getWidth()/3), (int) this.getPreferredSize().getHeight()/2));
			this.add(t);
			
			
		}
		repaint();
	}
	
	@Override
	public void add(Player pl) {
		itemList.add(pl);
		update();
		
	}
	
	public void add(Player player, String fmColor) {
		itemList.add(player);
		update(fmColor);
	}
	

	
	public void removeAllToken() {
		for (Component c : this.getComponents()) {
			if (c instanceof Token) {
				this.remove(c);
			}
				
		}
	}
	
	
	//probabilmente questo metodo non serve piu
	private Dimension computeTokenSize() {
		
		double width = this.getPreferredSize().getWidth()/4;
		double height = this.getPreferredSize().getHeight()/4;


		int size = (int)Math.max(width, height);
		return new Dimension(size, size);
				
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (Component c : getComponents()) {
			if (c instanceof Token) {
				
				Dimension dimension = new Dimension((int) g.getClipBounds().getWidth(), (int) g.getClipBounds().getHeight());
				c.setPreferredSize(computeTokenSize(dimension)); 
			}
			
		}
		
	}

	private Dimension computeTokenSize(Dimension dimension) {
		double width = dimension.getWidth()/4;   //valori drastici da migliorare 
		double height = dimension.getHeight()/4;   //
		
		int size = (int)Math.max(width, height);
		return new Dimension(size, size);
	}
	
}
