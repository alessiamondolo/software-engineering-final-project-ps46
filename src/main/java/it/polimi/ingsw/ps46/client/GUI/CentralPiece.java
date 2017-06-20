package it.polimi.ingsw.ps46.client.GUI;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CentralPiece extends JPanel {

	//Questo lavoro di composizione usa design pattern?
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5672600042329800625L;

	public CentralPiece() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//teoricamente qui dovrei chiamare il costruttore con super?
		this.setPreferredSize(new Dimension (380,580) );
		
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		this.setBorder(border);
		this.setOpaque(false);
		Dimension a = this.getSize();
		System.out.println("Width" + String.valueOf(a.getWidth()));
		System.out.println("Height" + String.valueOf(a.getHeight()));
		
		composeCentralPiece();
	}

	private void composeCentralPiece() {
		// TODO Auto-generated method stub
		
		UpperPiece upperPiece = new UpperPiece(); 
		LowerPiece lowerPiece = new LowerPiece();
		
		upperPiece.setPreferredSize(new Dimension(430, 335));
		lowerPiece.setPreferredSize(new Dimension (430, 270));
		upperPiece.setOpaque(false);
		lowerPiece.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.YELLOW, 1);
		upperPiece.setBorder(border);
		lowerPiece.setBorder(border);
		
		
		this.add(upperPiece);
		this.add(lowerPiece);
		
		
	}
	
	
	@Override
	public Dimension getSize() {
		return (super.getSize());
	}
	
}
