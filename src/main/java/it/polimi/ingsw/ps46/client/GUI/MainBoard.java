package it.polimi.ingsw.ps46.client.GUI;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.EventAcceptor;
import it.polimi.ingsw.ps46.server.EventMV;
import it.polimi.ingsw.ps46.server.EventMessage;
import it.polimi.ingsw.ps46.server.EventVisitor;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.NewStateMessage;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;
import it.polimi.ingsw.ps46.server.resources.Wood;

/**
 * MainBoard class displays the board through a top-bottom approach:
 * a board is composed by a background picture, a @CentralPiece and a GridBagLayout 
 * to map the Victory Points cells.
 * 
 * @author lorenzo
 *
 */

public class MainBoard extends JPanel {

	private static final long serialVersionUID = 6546742554971391289L;

	private ArrayList<PointCell> victoryPointCells;
	private Game game;
	private JPanel board;
	private CentralPiece centralPiece;
	private MilitaryTower militaryTower;
	private Dimension tokenDimension;
	
	
	public MainBoard(Dimension boardDimension) {
		
		victoryPointCells = new ArrayList<PointCell>();
		
		this.board = createBoard(boardDimension);
		this.add(board);
		
		//provaTokenModel();
		provaTokenGUI(tokenDimension);
		
	}
	
	/**
	 * Insert external grid, background image and central piece of the board.
	 * Dimensions for the board are chosen based on the resolution (1808, 2493) of 
	 * the image gameboard.png, a default dimension for displaying the image is chosen
	 * and then the right values for the others parameters are calculated via proportions.
	 * To build the external grid the method takes into account also pixel that ...
	 * @return
	 */
	
	private JPanel createBoard(Dimension boardDimension) {
		
		double widthBig = 180.0*((int)boardDimension.getWidth())/1808;		//60
		double heightBig = 180.0*((int)boardDimension.getHeight())/2493;    //50,5
		
		double widthSmall = (boardDimension.getWidth() - widthBig * 2) / 19;   //25,2
		double heightSmall = (boardDimension.getHeight() - heightBig * 2) / 29;   //20,7
		this.tokenDimension = new Dimension((int) ((widthSmall*7)/25), (int) ((heightSmall*7)/21));
		
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
			
			PointCell pointCell = new PointCell();
			victoryPointCells.add(pointCell);
			
			if (i == 0) {	
				gbc.gridx = 0;
				gbc.gridy = 0;
				pointCell.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			} else if (1 <= i && i < 20) {
				gbc.gridx = i;
				gbc.gridy = 0;
				accX += widthSmall - (int)widthSmall;
				if (accX >= 1.0) {
					pointCell.setPreferredSize(new Dimension((int)widthSmall+1, (int)heightBig));		
					accX -= 1.0;
				} else
					pointCell.setPreferredSize(new Dimension((int)widthSmall, (int)heightBig));
			} else if (i == 20) {
				gbc.gridx = 20;
				gbc.gridy = 0;
				pointCell.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			} else if (20 < i && i < 50) {
				gbc.gridx = 20;
				gbc.gridy = i-20;
				accY += heightSmall - (int)heightSmall;
				if (accY >= 1.0) {
					pointCell.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall+1));		
					accY -= 1.0;
				} else
					pointCell.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall));		
			} else if (i == 50) {
				gbc.gridx = 20;
				gbc.gridy = 30;
				pointCell.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			} else if (50 < i && i < 70) {
				gbc.gridx = 70-i; // 20 - (i-50)
				gbc.gridy = 30;
				if (accX >= 1.0) {
					pointCell.setPreferredSize(new Dimension((int)widthSmall+1, (int)heightBig));		
					accX -= 1.0;
				} else
					pointCell.setPreferredSize(new Dimension((int)widthSmall, (int)heightBig));
			} else if (i == 70) {
				gbc.gridx = 0;
				gbc.gridy = 30;
				pointCell.setPreferredSize(new Dimension((int)widthBig, (int)heightBig));
			
				
			} else if (70 < i && i < 100) {
				gbc.gridx = 0;
				gbc.gridy = 100-i;
				accY += heightSmall - (int)heightSmall;
				if (accY >= 1.0) {
					pointCell.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall+1));
					accY -= 1.0;
				} else
					pointCell.setPreferredSize(new Dimension((int)heightBig, (int)heightSmall));		
			}
				
			panel.add(pointCell, gbc);
			
		}
			
		this.centralPiece = new CentralPiece((widthSmall), (heightSmall));
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 17;
		gbc.gridheight = 29;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;	 
		panel.add(centralPiece, gbc);
		
		this.militaryTower = new MilitaryTower(widthSmall, heightSmall);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 2;
		gbc.gridheight = 29;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 18;
		gbc.gridy = 1;	 
		panel.add(militaryTower, gbc);
		
		JLabel boardImage = new JLabel();
		boardImage.setPreferredSize(boardDimension);
		boardImage.setIcon(returnBoardImage(boardDimension));
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 23;
		gbc.gridheight = 33;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(boardImage, gbc);
		
		return panel;
	}	

	/**
	 * Obtain the background board image
	 * @param boardDimension
	 * @return imageIcon
	 */
	
	private ImageIcon returnBoardImage(Dimension boardDimension) {
		
		 BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("img/gameboard.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Image img = image.getScaledInstance((int) boardDimension.getWidth(), (int) boardDimension.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);
	    return imageIcon;
		
	}
	
	//metodo di prova per testare i token
	
/*	public void provaTokenModel() {
				
		List<Resource> list = new ArrayList<Resource>();
		list.add(new VictoryPoints(10));
		Player player = new Player(2);
		player.setColor("Red");
		ResourceSet set = new ResourceSet(list);
		
		player.getPersonalBoard().setResources(set);
		//player.getPersonalBoard().getPlayerResourceSet().add(new Wood (10));
		player.getPersonalBoard().getPlayerResourceSet().add(new VictoryPoints (10));
		
		int vp = player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("VictoryPoints").getQuantity();
		for (int i = 0; i < victoryPointCells.size(); i++) {
			PointCell pc = victoryPointCells.get(i);
			if (i != vp) pc.remove(player);
			else pc.add(player); 
			//pc.paint();    crea problemi
		}
	}*/
	
	public void provaTokenGUI(Dimension tokenDimension) {
		
		PointCell cell = null; 
		for (int i = 0; i < 100; i ++) {
			cell = victoryPointCells.get(i);
		}
		System.out.println(String.valueOf(victoryPointCells.size()));
	
	}


	/**
	 *  Method to display current Victory Points accordingly to Model's values. This method 
	 *  is part of the macro method printBoard which is in charge of providing a snapshot 
	 *  of the Model's situation at the time.
	 */

	public void update(Game game) {
		
		this.game = game; 
		
		this.militaryTower.updateMilitaryPoints(this.game, this.tokenDimension);
	
		//List <Player> players  = new ArrayList <Player> ();
		//players = game.getPlayers();
		
		for (Player player : game.getPlayers()) {
			int vp = player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("VictoryPoints").getQuantity();
			for (int i = 0; i < victoryPointCells.size(); i++) {
				PointCell pc = victoryPointCells.get(i);
				if (i != vp) pc.remove(player);
				else pc.add(player); 
				//pc.paint(tokenDimension);  //e player color come parametri
			}
		}	
	
		
		centralPiece.updateUpperPiece(game);
		centralPiece.updateLowerPiece(game);
		
	}

}