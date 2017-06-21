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
 * a board is composed by a background picture, a @CentralPiece and a GridBagLayout 
 * to map the Victory Points cells.
 * 
 * @author lorenzo
 *
 */

public class MainBoard extends JPanel  {

	private static final long serialVersionUID = 6546742554971391289L;

	public MainBoard() {
		
		this.add(createBoard());
		
	}
	
	/**
	 * Insert external grid, background image and central piece of the board.
	 * Dimensions for the board are chosen based on the resolution (1808, 2493) of 
	 * the image gameboard.png, a default dimension for displaying the image is chosen
	 * and then the right values for the others parameters are calculated via proportions.
	 * To build the external grid the method takes into account also pixel that ...
	 * @return
	 */
	
	private JPanel createBoard() {
		
		Dimension boardDimension =  new Dimension (600, 700); //(512, 700) proporzioni corrette per non distorcere originale
		
		double widthBig = 180.0*((int)boardDimension.getWidth())/1808;
		double heightBig = 180.0*((int)boardDimension.getHeight())/2493;
		
		double widthSmall = (boardDimension.getWidth() - widthBig * 2) / 19;
		double heightSmall = (boardDimension.getHeight() - heightBig * 2) / 29;
		
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
	
		double accX = 0;
		double accY = 0;
		
		for (int i = 0; i < 100; i++) {
			
			JLabel l = new JLabel();
			Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
			l.setBorder(border);
			// l.setBackground(Color.BLUE);
			l.setText(String.valueOf(i));
			
			if (i == 0) {	
				gbc.gridx = 0;
				gbc.gridy = 0;
				l.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			} else if (1 <= i && i < 20) {
				gbc.gridx = i;
				gbc.gridy = 0;
				accX += widthSmall - (int)widthSmall;
				if (accX >= 1.0) {
					l.setPreferredSize(new Dimension((int)widthSmall+1, (int)heightBig));		
					accX -= 1.0;
				} else
					l.setPreferredSize(new Dimension((int)widthSmall, (int)heightBig));
			} else if (i == 20) {
				gbc.gridx = 20;
				gbc.gridy = 0;
				l.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			} else if (20 < i && i < 50) {
				gbc.gridx = 20;
				gbc.gridy = i-20;
				accY += heightSmall - (int)heightSmall;
				if (accY >= 1.0) {
					l.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall+1));		
					accY -= 1.0;
				} else
					l.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall));		
			} else if (i == 50) {
				gbc.gridx = 20;
				gbc.gridy = 30;
				l.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			} else if (50 < i && i < 70) {
				gbc.gridx = 70-i; // 20 - (i-50)
				gbc.gridy = 30;
				if (accX >= 1.0) {
					l.setPreferredSize(new Dimension((int)widthSmall+1, (int)heightBig));		
					accX -= 1.0;
				} else
					l.setPreferredSize(new Dimension((int)widthSmall, (int)heightBig));
			} else if (i == 70) {
				gbc.gridx = 0;
				gbc.gridy = 30;
				l.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			} else if (70 < i && i < 100) {
				gbc.gridx = 0;
				gbc.gridy = 100-i;
				accY += heightSmall - (int)heightSmall;
				if (accY >= 1.0) {
					l.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall+1));		
					accY -= 1.0;
				} else
					l.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall));		
			}
				
			panel.add(l, gbc);
			
		}
			
		CentralPiece centralPiece = new CentralPiece((widthSmall), (heightSmall));
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 17;
		gbc.gridheight = 29;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;	 
		panel.add(centralPiece, gbc);
		
		MilitaryTower militaryTower = new MilitaryTower();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 2;
		gbc.gridheight = 29;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 18;
		gbc.gridy = 1;	 
		panel.add(militaryTower, gbc);
		
		JLabel board = new JLabel();
		board.setPreferredSize(boardDimension);
		//board.setIcon(returnBoardImage(boardDimension));
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 23;
		gbc.gridheight = 33;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(board, gbc);
			
		return panel;
	}
	

	/**
	 * Obtain the background board image
	 * @param boardDimension
	 * @return imageIcon
	 */
	
/*	private ImageIcon returnBoardImage(Dimension boardDimension) {
		
		 BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("gameboard.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Image img = image.getScaledInstance((int) boardDimension.getWidth(), (int) boardDimension.getHeight(), Image.SCALE_SMOOTH);
		//Image img = image.getScaledInstance(471, 650, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(img);
	    String path = this.getClass().getClassLoader().getResource("gameboard.png").toExternalForm();
		System.out.println(path);
	    
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
