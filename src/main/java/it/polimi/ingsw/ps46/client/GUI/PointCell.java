package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.Player;

/**
 * A cell specific for the external grid. All players can place a token inside the cell.
 * This class provides functionalities the add, remove and show tokens on the external grid,
 * the one associated to victory points
 * @author lorenzo
 *
 */

public class PointCell extends Cell<Player> {
	
	private ArrayList <String> FM = new ArrayList <String> ();
	private static final long serialVersionUID = 591002007000957139L;
	
	
	public PointCell() {
	
	}
	
	public PointCell(int action) {
		super(action);
	}
	
	public PointCell(String fmColor) {
		super(fmColor);
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
	
	public void updateFM() {
		
		int i = 0;
		this.removeAllToken();
		
		for (Player p : itemList) {
			
			String fmColor = FM.get(i);
			Token t = null;
			try {
				t = new Token(p.getColor(), fmColor);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Token non generato causa I/0");
			}
			t.setPreferredSize(FMComputeTokenSize());
			if (! (this.getPreferredSize().width > 2*this.getPreferredSize().height)) {
			this.setLayout(new GridBagLayout());
			}
			this.add(t);
			i++;	
		}
		repaint();
	}
	
	public void add(Player player, String fmColor) {
		itemList.add(player);
		FM.add(fmColor);
		updateFM();
	}
	
	public void clearFM() {
		this.FM.clear();
	}
		
	private void removeAllToken() {
		for (Component c : this.getComponents()) {
			if (c instanceof Token) {
				this.remove(c);
			}
				
		}
	}
	
	
	
	private Dimension computeTokenSize() {
		
		double width = this.getPreferredSize().getWidth();
		double height = this.getPreferredSize().getHeight();


		int size = (int)Math.max(width, height);
		return new Dimension(size, size);
				
	}
	
	private Dimension FMComputeTokenSize() {
		double width = this.getPreferredSize().getWidth()/2;
		double height = this.getPreferredSize().getHeight()/2;
		int size;
		
		if (this.getPreferredSize().width > 2*this.getPreferredSize().height) {
			System.out.println("Sono largo il doppio");
			this.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			size = (int)Math.min(width, height);
		}
		else {
			size = (int)Math.max(width, height);
		}
		
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
		repaint();
		
	}

	private Dimension computeTokenSize(Dimension dimension) {
		double width = dimension.getWidth()/4;   //valori drastici da migliorare 
		double height = dimension.getHeight()/4;   //
		
		int size = (int)Math.max(width, height);
		return new Dimension(size, size);
	}
	
}
