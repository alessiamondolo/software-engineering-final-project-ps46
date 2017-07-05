package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A token to represent visually player's ranking and placement choices in the game's board
 * @author lorenzo
 *
 */


public class Token extends JLabel {
	
	/**
	 * Per fare testing tengo due costruttori temporaneamente
	 */
	
	private static final long serialVersionUID = 1903309058325267711L;
	private BufferedImage image = null;
	
	private Token() {
		
	}
	
	public Token(String color) {
		this();
		
		//da migliorare la gestione dell'IO
		
		if (color == null) color = "";
		
		//teoricamente con concantenazione di stringe non serve neanche lo switch
		
		switch(color) {
		
		case "Red" :
			try {
				image = ImageIO.read(getClass().getResource("img/token/red_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//img = image.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			//ImageIcon imageIcon = new ImageIcon(img);
			
			break;
		
		case "Blue" :
			try {
				image = ImageIO.read(getClass().getResource("img/token/blue_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		
		case "Green" :
			try {
				image = ImageIO.read(getClass().getResource("img/token/green_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		
		case "Yellow" :
			try {
				image = ImageIO.read(getClass().getResource("img/token/yellow_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		default :
			try {
				image = ImageIO.read(getClass().getResource("img/token/red_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//serve getscaled instance
			
		}
	}

	public Token(String color, String fmColor) {
		this();
		
		try {
			if (color == null) color = "";
				
			switch (fmColor) {
					
				case "White" :
						image = ImageIO.read(getClass().getResource("img/family_member/" +color+ "_WhiteFM.png"));
					break;
				case "Black" :
						image = ImageIO.read(getClass().getResource("img/family_member/" +color+ "_BlackFM.png"));
					break;
				case "Orange" :
						image = ImageIO.read(getClass().getResource("img/family_member/" +color+ "_OrangeFM.png"));
					break;
				case "Neutral" :
						image = ImageIO.read(getClass().getResource("img/family_member/" +color+ "_NeutralFM.png"));
					break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	private Rectangle d = null;
	public void paint(Graphics g) {
		
		if (d == null || d.width != g.getClipBounds().width ||
				 d.height != g.getClipBounds().height) {
			d = g.getClipBounds();
			ImageIcon ii = new ImageIcon(image.getScaledInstance(g.getClipBounds().width, g.getClipBounds().height, Image.SCALE_SMOOTH));
			this.setIcon(ii);
		}
		
		super.paint (g);
	}
	
}

