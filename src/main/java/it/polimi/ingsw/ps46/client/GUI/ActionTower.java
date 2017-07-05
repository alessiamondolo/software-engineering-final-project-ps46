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
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.Effect;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesEffect;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.Wood;

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
		
/*		Image img = cardCells.get(0).loadCard(4);
		Image img1 = cardCells.get(0).loadCard(5);
		Image img2 = cardCells.get(0).loadCard(6);
		Image img3 = cardCells.get(0).loadCard(78);
		
		ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		cardCells.get(0).setIcon(imageIcon);
		imageIcon = new ImageIcon(img1.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		cardCells.get(1).setIcon(imageIcon);
		imageIcon = new ImageIcon(img2.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		cardCells.get(2).setIcon(imageIcon);
		imageIcon = new ImageIcon(img3.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		cardCells.get(3).setIcon(imageIcon);*/
		
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
		//quando la tower non ha carte la card può essere = null?
		Card card = game.getBoard().getTower(i).getTowerFloor(x + (3 - x*2)).getCard();
		
		CardCell cc = cardCells.get(x);
		
		//cosa succede se itemlist è vuota
		cc.itemList.clear();  
		if (card != null) {
			cc.add(card);
		}
		
		}
		
	}
}
