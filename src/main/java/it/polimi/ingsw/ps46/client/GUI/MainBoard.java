package it.polimi.ingsw.ps46.client.GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * MainBoard class displays the board through a top-bottom approach:
 * a board is composed by a background picture and two central layout, a GridBagLayout
 * and 
 * @author lorenzo
 *
 */

public class MainBoard extends JPanel  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6546742554971391289L;

	public MainBoard() {
		
		// Aggiungo immagine pannello di sfondo
		// this.setPreferredSize(new Dimension (500, 1000));
		this.add(createGrid(new CentralPiece(), new MilitaryTower()));
		
		// this.getComponent(n)
	}
	
	// Returns a panel that represents the entire board which is 
	// composed of a Central Piece, a Military Tower and and external
	// Grid that is made through this function. Instead Central Piece 
	// and Military Tower objects are passed as parameters.
	
	private JPanel createGrid(CentralPiece centralPiece, MilitaryTower militaryTower) {
		int x = 20;
		int y = 2;
		int width = 36;
		int height = 40; 
		JPanel panel = new JPanel();
		//JLabel background = new JLabel();
		
		
		//panel.add(background);
		//panel.setPreferredSize(new Dimension(600, 700));
		panel.setLayout(new GridBagLayout());
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		for (int i = 0; i < 100; i++) {
			
		
			
			
			
			if (i == 0) {	
				gbc.fill = GridBagConstraints.BOTH;  
				gbc.gridwidth = 2;
				gbc.gridheight = 2;
				gbc.weightx = 1;
				gbc.weighty = 1;
				
				gbc.ipadx = 10;
				gbc.gridx = 0;
				gbc.gridy = 0;
				JLabel l = new JLabel();
				l.setPreferredSize(new Dimension(width, height));
				Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
				l.setBorder(border);
				l.setText(String.valueOf(i));
				panel.add(l, gbc);
			} else
				if (1 <= i && i < 20) {
					gbc = new GridBagConstraints();
					gbc.fill = GridBagConstraints.BOTH;  
					gbc.gridwidth = 1;
					gbc.gridheight = 2;
					gbc.weightx = 1;
					gbc.weighty = 1;
					gbc.gridx = i + 1;
					gbc.gridy = 0;
					JLabel l = new JLabel();
					l.setPreferredSize(new Dimension(width/2, height));
					Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
					l.setBorder(border);
					l.setText(String.valueOf(i));
					panel.add(l, gbc);
				} else 
					if (i == 20) {
					gbc = new GridBagConstraints();
					gbc.fill = GridBagConstraints.BOTH;  
					gbc.gridwidth = 2;
					gbc.gridheight = 2;
					gbc.weightx = 1;
					gbc.ipadx = 10;
					
					gbc.weighty = 1;
					gbc.gridx = 21;
					gbc.gridy = 0;
					JLabel l = new JLabel();
					l.setPreferredSize(new Dimension(width, height));
					Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
					l.setBorder(border);
					l.setText(String.valueOf(i));
					panel.add(l, gbc);
				} else
					if (20 < i && i < 50) {
					gbc = new GridBagConstraints();
					gbc.gridx = 21;
					gbc.gridy = y;
					gbc.fill = GridBagConstraints.BOTH;  
					gbc.gridwidth = 2;
					gbc.gridheight = 1;
					gbc.weightx = 1;
					gbc.weighty = 1;
					JLabel l = new JLabel();
					l.setPreferredSize(new Dimension(width, height/2));
					Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
					l.setBorder(border);
					l.setText(String.valueOf(i));
					panel.add(l, gbc);
					y ++;
					} else
						if (i == 50) {
							gbc = new GridBagConstraints();
							gbc.fill = GridBagConstraints.BOTH;  
							gbc.gridwidth = 2;
							gbc.gridheight = 2;
							gbc.weightx = 1;
							gbc.weighty = 1;
							gbc.gridx = 21;
							gbc.gridy = y;
							JLabel l = new JLabel();
							l.setPreferredSize(new Dimension(width, height));
							Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
							l.setBorder(border);
							l.setText(String.valueOf(i));
							panel.add(l, gbc);
						} else 
							if (50 < i && i < 70) {
							gbc = new GridBagConstraints();
							gbc.fill = GridBagConstraints.BOTH;  
							gbc.gridwidth = 1;
							gbc.gridheight = 2;
							gbc.weightx = 1;
							gbc.weighty = 1;
							gbc.gridx = x;
							gbc.gridy = y;
							JLabel l = new JLabel();
							l.setPreferredSize(new Dimension(width/2, height));
							Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
							l.setBorder(border);
							l.setText(String.valueOf(i));
							panel.add(l, gbc);
							x --;
						} else
							if (i == 70) {
								gbc = new GridBagConstraints();
								gbc.fill = GridBagConstraints.BOTH;  
								gbc.gridwidth = 2;
								gbc.gridheight = 2;
								gbc.weightx = 1;
								gbc.weighty = 1;
								gbc.gridx = 0;
								gbc.gridy = y;
								JLabel l = new JLabel();
								l.setPreferredSize(new Dimension(width, height));
								Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
								l.setBorder(border);
								l.setText(String.valueOf(i));
								panel.add(l, gbc);
								y --;
							} else
							if (70 < i && i < 100) {
								gbc = new GridBagConstraints();
								gbc.fill = GridBagConstraints.BOTH;  
								gbc.gridwidth = 2;
								gbc.gridheight = 1;
								gbc.weightx = 1;
								gbc.weighty = 1;
								gbc.gridx = 0;
								gbc.gridy = y;
								JLabel l = new JLabel();
								l.setPreferredSize(new Dimension(width, height/2));
								Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
								l.setBorder(border);
								l.setText(String.valueOf(i));
								panel.add(l, gbc);
								y --;
							}
	
			}
			
		/*	if ((i == 0) || ( 20 <= i && i <= 50) || ( 70 <= i && i <= 100 )) 
				gbc.gridwidth = 2;
			else
				gbc.gridwidth = 1;
			
			if (( 0 <= i && i <= 20 ) || ( 50 <= i && i <= 70 ))
				gbc.gridheight = 2;
			else
				gbc.gridheight = 1;
			
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			
			l.setText(String.valueOf(i));
			panel.add(l, gbc);*/
			
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 16;
		gbc.gridheight = 29;
		gbc.ipadx = 10;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;	 
		panel.add(centralPiece, gbc);
		
		//posizione military tower da specificare
		
		// la faccio larga di due colonne e poi gioco con gli insets per spostare
		// a destra il JPanel con il BoxLayout in modo che mi combaci
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 2;
		gbc.gridheight = 29;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 18;
		gbc.gridy = 2;	 
		panel.add(militaryTower, gbc);
		
		JLabel board = new JLabel();
		board.setPreferredSize(new Dimension(600, 700));
		//board.setIcon(returnBoardImage());
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 23;
		gbc.gridheight = 33;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(board, gbc);
		

		//To show the background picture of the board
		//background.setOpaque(false);
		
		
		return panel;
	}
	

	//da risolvere problemi legati alla visualizzazione dei file immagini
	/*private ImageIcon returnBoardImage() {
		
		 BufferedImage image = null;
			try {
				image = ImageIO.read(getClass().getResource("gameboard.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Image img = image.getScaledInstance(600, 700, Image.SCALE_SMOOTH);
	    ImageIcon imageIcon = new ImageIcon(img);
		
		return imageIcon;
		
	}*/
    
  /*  @Override
    protected void paintComponent(Graphics g) {
    	Image image = null;
    try {
			 image = ImageIO.read(getClass().getResource("gameboard.png"));
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	super.paintComponent(g);
    	//Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	
    	g.drawImage(image, 0, 0, 600, 800, this);
   	
    }*/
    
}
