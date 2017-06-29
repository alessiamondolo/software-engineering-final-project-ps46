package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import it.polimi.ingsw.ps46.server.Game;

public class ToTower extends JPanel {
	
	private Game game;
	private Dimension familyMemberDimension;
	private ArrayList <CardCell> actionCells = new ArrayList <CardCell> ();
	
	public ToTower(double widthSmall, double heightSmall) {

	this.setPreferredSize(new Dimension((int) (9*widthSmall/5), (int) (heightSmall*16-heightSmall/5)));
	this.setMaximumSize(getPreferredSize());
	this.setMinimumSize(getPreferredSize());
	this.setOpaque(false);
	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	for (int i = 0; i < 4; i++) {
		
		CardCell cell = new CardCell();
		cell.setPreferredSize(new Dimension((int) ((9*widthSmall/5)), (int) (heightSmall*16-heightSmall/5)/5));
		cell.setMaximumSize(getPreferredSize());
		cell.setMinimumSize(getPreferredSize());
		this.add(cell);
		actionCells.add(cell);
	}
	
	}
	
	public void updateToTower(Game game, int i) {
		this.game = game;
		
		
	}

}
	
