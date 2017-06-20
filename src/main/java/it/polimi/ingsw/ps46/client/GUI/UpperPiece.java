package it.polimi.ingsw.ps46.client.GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * This Class builds the panel meant to host Cards Action Spaces in the central part
 * of the board
 * @author lorenzo
 *
 */

public class UpperPiece extends JPanel {
	
	public UpperPiece() {
		
	//	BoxLayout bl = new BoxLayout(this, BoxLayout.X_AXIS);
	//	this.setLayout(bl);
		setActionTowers();
		
	}

	private void setActionTowers() {
		
		BoxLayout bl = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(bl);
		
		ActionTower greenTower = new ActionTower();
		ActionTower azureTower = new ActionTower();
		ActionTower yellowTower = new ActionTower();
		ActionTower violetTower = new ActionTower();
		
		
		//da scrivere meglio con magari una lista per rendere più veloci istruzioni
		JPanel toGreenTower = new JPanel();
		toGreenTower.setPreferredSize(new Dimension(20, 320));
		JPanel toAzureTower = new JPanel();
		toAzureTower.setPreferredSize(new Dimension(20, 320));
		JPanel toYellowTower = new JPanel();
		toYellowTower.setPreferredSize(new Dimension(20, 320));
		JPanel toVioletTower = new JPanel();
		toVioletTower.setPreferredSize(new Dimension(20, 320));
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		toGreenTower.setBorder(border);
		toAzureTower.setBorder(border);
		toYellowTower.setBorder(border);
		toVioletTower.setBorder(border);
		toGreenTower.setOpaque(false);
		toAzureTower.setOpaque(false);
		toYellowTower.setOpaque(false);
		toVioletTower.setOpaque(false);
		
	
		this.add(new Box.Filler(new Dimension(5, 5), new Dimension(5, 5), new Dimension(5, 5)));
		
		this.add(greenTower);
		this.add(toGreenTower);
		
		this.add(azureTower);
		this.add(toAzureTower);
		
		
		this.add(yellowTower);
		
		
		this.add(toYellowTower);
		
		
		this.add(violetTower);
		
		this.add(toVioletTower);
		this.add(new Box.Filler(new Dimension(5, 5), new Dimension(5, 5), new Dimension(5, 5)));
		
	}

}
