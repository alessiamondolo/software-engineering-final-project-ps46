package it.polimi.ingsw.ps46.client.GUI;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The central part of the Board which is then again structured in two subpanels:
 * an @UpperPiece and a @LowerPiece
 * @author lorenzo
 *
 */

public class CentralPiece extends JPanel {

	
	
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
		// TODO Auto-generated method stub
		
		UpperPiece upperPiece = new UpperPiece(widthSmall, heightSmall); 
		
		LowerPiece lowerPiece = new LowerPiece(widthSmall, heightSmall);
		
		//lowerPiece.setPreferredSize(new Dimension (425, 260));
		
		
	
		upperPiece.setOpaque(false);
		lowerPiece.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.YELLOW, 1);
		upperPiece.setBorder(border);
		lowerPiece.setBorder(border);
		
		
		this.add(upperPiece);
		this.add(lowerPiece);
		
		
	}
	
}
