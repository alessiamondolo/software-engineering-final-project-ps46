package it.polimi.ingsw.ps46.client.GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;

/**
 * The central part of the Board which, again, has two children:
 * an @UpperPiece and a @LowerPiece
 * @author lorenzo
 *
 */

public class CentralPiece extends JPanel {

	Game game;
	private UpperPiece upperPiece;
	private LowerPiece lowerPiece;
	
	private static final long serialVersionUID = -5672600042329800625L;

	public CentralPiece(double widthSmall, double heightSmall) {
		
		super(new FlowLayout(FlowLayout.LEFT));
		
		this.setPreferredSize(new Dimension ((int) widthSmall*17, (int) heightSmall*29));
		
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		this.setBorder(border);
		this.setOpaque(false);
		
		composeCentralPiece(widthSmall, heightSmall);
	}

	private void composeCentralPiece(double widthSmall, double heightSmall) {
		
		this.upperPiece = new UpperPiece(widthSmall, heightSmall); 
		
		this.lowerPiece = new LowerPiece(widthSmall, heightSmall);

	
		upperPiece.setOpaque(false);
		lowerPiece.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.YELLOW, 1);
		upperPiece.setBorder(border);
		lowerPiece.setBorder(border);
		
		
		this.add(upperPiece);
		this.add(lowerPiece);
		
	}
	/**
	 * Update the contents showed in the subpanels accordingly to game values
	 * @param game
	 */
	
	public void update(Game game) {
	
		this.game = game;
		this.upperPiece.update(game);
		this.lowerPiece.update(game);
		
	}
	
	
}
