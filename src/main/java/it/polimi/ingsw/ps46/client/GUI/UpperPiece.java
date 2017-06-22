package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
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
	
	public UpperPiece(double widthSmall, double heightSmall) {
		
		this.setPreferredSize(new Dimension((int) widthSmall*17, (int) (heightSmall*16-heightSmall/5)));
		setActionTowers(widthSmall, heightSmall);
		
	}

	private void setActionTowers(double widthSmall, double heightSmall) {
		
		BoxLayout bl = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(bl);
		
		ActionTower greenTower = new ActionTower(widthSmall, heightSmall);
		ActionTower azureTower = new ActionTower(widthSmall, heightSmall);
		ActionTower yellowTower = new ActionTower(widthSmall, heightSmall);
		ActionTower violetTower = new ActionTower(widthSmall, heightSmall);
		
		
		//da scrivere meglio con magari una lista per rendere più veloci istruzioni
		JPanel toGreenTower = new JPanel();
		toGreenTower.setPreferredSize(new Dimension((int) widthSmall/2, (int) (heightSmall*16-heightSmall/5)));
		JPanel toAzureTower = new JPanel();
		toAzureTower.setPreferredSize(new Dimension((int) widthSmall/2, (int) (heightSmall*16-heightSmall/5)));
		JPanel toYellowTower = new JPanel();
		toYellowTower.setPreferredSize(new Dimension((int) widthSmall/2, (int) (heightSmall*16-heightSmall/5)));
		JPanel toVioletTower = new JPanel();
		toVioletTower.setPreferredSize(new Dimension((int) widthSmall/2, (int) (heightSmall*16-heightSmall/5)));
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		/*toGreenTower.setBorder(border);
		toAzureTower.setBorder(border);
		toYellowTower.setBorder(border);
		toVioletTower.setBorder(border);*/
		toGreenTower.setOpaque(false);
		toAzureTower.setOpaque(false);
		toYellowTower.setOpaque(false);
		toVioletTower.setOpaque(false);
		

		this.add(greenTower);
		this.add(toGreenTower);
		
		this.add(azureTower);
		this.add(toAzureTower);
		
		this.add(yellowTower);
		this.add(toYellowTower);
		
		this.add(new Box.Filler(new Dimension(5, 5), new Dimension(5, 5), new Dimension(5, 5)));
		
		this.add(violetTower);
		this.add(toVioletTower);
		
	}

}
