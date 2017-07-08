package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Tower;
import it.polimi.ingsw.ps46.server.TowerFloor;
import it.polimi.ingsw.ps46.server.card.Card;

/**
 * Board area where cards get displayed. An ActionTower is made of four TowerActionCell
 * arranged in a top-down BoxLayout.
 * @author lorenzo
 *
 */
public class ActionTower extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7840687245711696423L;
	private ArrayList <CardCell> cardCells = new ArrayList <CardCell> ();
  
	public ActionTower(double widthSmall, double heightSmall) {
	
		setActionTower(widthSmall, heightSmall);	
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		for (int i = 0; i < 4; i++) {
			
			CardCell cell = new CardCell();
			cell.setPreferredSize(new Dimension((int) ((2*widthSmall)), (int) (heightSmall*16-heightSmall/5)/5));
			cell.setMaximumSize(getPreferredSize());
			cell.setMinimumSize(getPreferredSize());
			this.add(cell);
			cardCells.add(cell);
		}

		
	}
	
	private void setActionTower(double widthSmall, double heightSmall) {
		
		this.setPreferredSize(new Dimension((int) ((7*widthSmall/3)), (int) (heightSmall*16-heightSmall/5)));
		this.setMaximumSize(getPreferredSize());
		this.setMinimumSize(getPreferredSize());
		this.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		this.setBorder(border);	
	
	}

	public void update(Game game, int i) {
		Tower t = game.getBoard().getTower(i);
		
		for (int floor = 0; floor < t.getNumberOfFloors(); floor++) {
		
			TowerFloor piano = t.getTowerFloor(t.getNumberOfFloors() - 1 - floor);
			Card card = piano.getCard();
			CardCell cc = cardCells.get(floor);
			cc.removeAll();
			if (card != null) {
				cc.add(card);
				String nomeCarta = card.getCardName();
			}
		}
		repaint();
	}
}
