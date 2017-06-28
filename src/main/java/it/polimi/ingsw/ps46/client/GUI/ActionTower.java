package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;

/**
 * Board area where cards get displayed. An ActionTower is made of four TowerActionCell
 * arranged in a top-down BoxLayout.
 * @author lorenzo
 *
 */
public class ActionTower extends JPanel {
	
	private Game game;
	private Dimension cardDimension;
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

	public void updateTower(Game game, int i) {
		this.game = game;
		
		for (int x = 0; x < 4; x++) {
		game.getBoard().getTower(i).getTowerFloor(x).getCard();
		//cardCells.get(x).paint(null);  dopo aver ottenuto la nuova carta la devo stampare
		}
		
	}
}
