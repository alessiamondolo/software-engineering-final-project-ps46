package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import it.polimi.ingsw.ps46.server.Player;

public class ToTower extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7643108840401757037L;
	private ArrayList <PointCell> actionCells = new ArrayList <PointCell> ();
	
	public ToTower(int index, double widthSmall, double heightSmall) {

		this.setPreferredSize(new Dimension((int) (9*widthSmall/5), (int) (heightSmall*16-heightSmall/5)));
		this.setMaximumSize(getPreferredSize());
		this.setMinimumSize(getPreferredSize());
		this.setOpaque(false);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		for (int i = 0; i < 4; i++) {
			PointCell cell = new PointCell((4-i) + index*4);
			cell.setPreferredSize(new Dimension((int) ((9*widthSmall/5)), (int) (heightSmall*16-heightSmall/5)/5));
			cell.setMaximumSize(getPreferredSize());
			cell.setMinimumSize(getPreferredSize());
			
			this.add(cell);
			actionCells.add(cell);
		}
	}

	@Override
	public void removeAll() {
		for (PointCell pc : actionCells) {
			pc.removeAll();
		}
	}
	
	public void add(Player player, String fmColor, int floor) {
		actionCells.get(floor).add(player, fmColor);
	}
}
	
