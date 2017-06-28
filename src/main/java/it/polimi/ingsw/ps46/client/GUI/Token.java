package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Player;

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
	
	private Color color;
		
	public Token(String color) {    //il costruttore qui ha solo bisogno del colore del giocatore in realtà
		
		double width = 5; 
		double height = 5; 
		
		BufferedImage image = null;
		Image img;
		
		switch(color) {
		
		case "Red" :
			this.color = Color.RED;
			try {
				image = ImageIO.read(getClass().getResource("img/token/red_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			img = image.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(img);
			this.setIcon(imageIcon);
			break;
		
		case "Blue" :
			this.color = Color.BLUE;
			try {
				image = ImageIO.read(getClass().getResource("img/token/blue_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			img = image.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(img);
			this.setIcon(imageIcon);
			break;
		
		case "Green" :
			this.color = Color.GREEN;
			try {
				image = ImageIO.read(getClass().getResource("img/token/green_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			img = image.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(img);
			this.setIcon(imageIcon);
			break;
		
		case "Yellow" :
			this.color = Color.YELLOW;
			try {
				image = ImageIO.read(getClass().getResource("img/token/yellow_token.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			img = image.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(img);
			this.setIcon(imageIcon);
			break;
		}
		
	
	}
	
	//costruttore vuoto per simulazione
	
	private static Image redImg = null;
	private static Image blueImg = null;
	public Token(Dimension dimension) {    //anche questo costruttore dovrebbe prendere in ingresso un colore
		
		double width = dimension.getWidth(); 
		double height = dimension.getHeight(); 
		try {
			if (redImg == null)
				redImg = ImageIO.read(getClass().getResource("img/token/red_token.png")).getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			if (blueImg == null)
				blueImg = ImageIO.read(getClass().getResource("img/token/blue_token.png")).getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
			//this.color = Color.RED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Image image = Math.random() > 0.5 ? redImg : blueImg;
		ImageIcon imageIcon = new ImageIcon(image);
		this.setIcon(imageIcon);
	}

	public Color getColor() {
		return this.color;
	}
	
}

