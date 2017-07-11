package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;

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
	
	private static BufferedImage red_img = null;
	private static BufferedImage blue_img = null;
	private static BufferedImage yellow_img = null;
	private static BufferedImage green_img = null;
	private static HashMap<String, BufferedImage> hmap = new HashMap<String, BufferedImage>();
	
	private Token() {
		
	}
	
	public Token(String color) {
		this();
		
		if (color == null) {
			System.out.println("E' stato chiesto un familiare con colore null");
			return;
		}
		
		try {
			switch(color) {
			
			case "Red" :
				if (red_img == null)
					//red_img = getImage("img/token/red_token.png");
					red_img = getImagePathMode("token/red_token.png");
				image = red_img;
				break;
			
			case "Blue" :
				if (blue_img == null)
					//blue_img = getImage("img/token/blue_token.png");
					blue_img = getImagePathMode("token/blue_token.png");
				image = blue_img;
				break;
			
			case "Green" :
				if (green_img == null)
					//green_img = getImage("img/token/green_token.png");
					green_img = getImagePathMode("token/green_token.png");
				image = green_img;
				break;
			
			case "Yellow" :
				if (yellow_img == null)
					//yellow_img = getImage("img/token/yellow_token.png");
					yellow_img = getImagePathMode("token/yellow_token.png");
				image = yellow_img;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			repaint();
		}

	}

	public Token(String color, String fmColor) throws IOException {
		this();
		
		loadFMImages();
		
		if (color == null) {
				System.out.println("E' stato chiesto un familiare con colore null");
				return;
		}
			
		String key = (color+ "_" +fmColor);
		image = hmap.get(key);		
		 
	}
	
	
	private Rectangle d = null;
	public void paint(Graphics g) {
		
		super.paint (g);
		
		if (d == null || d.width != g.getClipBounds().width ||
				 d.height != g.getClipBounds().height) {
			d = g.getClipBounds();
			ImageIcon ii = new ImageIcon(image.getScaledInstance(g.getClipBounds().width, g.getClipBounds().height, Image.SCALE_SMOOTH));
			this.setIcon(ii);
		}
		
	}
	
	
/*	public BufferedImage getImage(String imagePathName) throws IOException {
		return ImageIO.read(getClass().getResource(imagePathName));
	}*/
	
	public static BufferedImage getImagePathMode (String relativePathName) throws IOException {
		String imagesPath = "./src/main/java/it/polimi/ingsw/ps46/client/GUI/img/";
		return ImageIO.read(new File(imagesPath + relativePathName));
	}
	
	
	private void loadFMImages () throws IOException {
		
		String [] tokenColors = { "Red", "Blue", "Yellow" , "Green" };
		String[] fmTypes = { "Neutral", "Black", "White", "Orange" };
		
	
		
		if ( hmap.isEmpty()) {
			for ( String color : tokenColors) {
				for (String fmType : fmTypes ) {
					
					BufferedImage fmBImage;
					fmBImage = getImagePathMode("family_member/" +color+ "_" +fmType+ "FM.png");
					hmap.put(color+ "_" +fmType, fmBImage);
				}
			}
		}
		
	}
}

