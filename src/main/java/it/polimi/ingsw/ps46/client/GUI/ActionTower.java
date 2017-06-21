package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Board area where cards get displayed. An ActionTower is made of four TowerActionCell
 * arranged in a top-down BoxLayout.
 * @author lorenzo
 *
 */
public class ActionTower extends JPanel {
	
	
  
	public ActionTower(double widthSmall, double heightSmall) {
		
		
		setActionTower(widthSmall, heightSmall);
			
		
	}
	
	private void setActionTower(double widthSmall, double heightSmall) {
		
		this.setPreferredSize(new Dimension((int) widthSmall, (int) (heightSmall*16-heightSmall/5)));
		this.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		this.setBorder(border);	
	
	}
		
/*	private void getCard() {
			
		BufferedImage image = null;
			try {
				image = ImageIO.read(getClass().getResourceAsStream("LorenzoCards_compressed_png/devcards_f_en_c_1.png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image img = image.getScaledInstance(35, 75, Image.SCALE_SMOOTH);
		    ImageIcon cardIcon = new ImageIcon(img);
		    
		    card.setIcon(cardIcon);
			
		} */
		
	/*@Override
    protected void paintComponent(Graphics g) {
    	Image image = null;
    try {
			 image = ImageIO.read(getClass().getResource("LorenzoCards_compressed_png/devcards_f_en_c_1.png"));
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	super.paintComponent(g);
    	//Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	
    	g.drawImage(image, 0, 0, 600, 800, this);
   	
    }*/


}
