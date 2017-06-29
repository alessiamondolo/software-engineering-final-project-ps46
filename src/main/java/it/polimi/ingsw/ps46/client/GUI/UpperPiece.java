package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.ps46.server.Game;

/**
 * This Class builds the panel meant to host Cards Action Spaces in the central part
 * of the board
 * @author lorenzo
 *
 */

public class UpperPiece extends JPanel {
	
	private Game game;
	private ArrayList<ActionTower> actionTowers = new ArrayList <ActionTower>();
	private ArrayList<ToTower> toTowers = new ArrayList <ToTower>();
	
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
		
		actionTowers.add(greenTower);
		actionTowers.add(azureTower);
		actionTowers.add(yellowTower);
		actionTowers.add(violetTower);
		
		ToTower toGreenTower = new ToTower(widthSmall, heightSmall);
		ToTower toAzureTower = new ToTower(widthSmall, heightSmall);
		ToTower toYellowTower = new ToTower(widthSmall, heightSmall);
		ToTower toVioletTower = new ToTower(widthSmall, heightSmall);
		
		toTowers.add(toGreenTower);
		toTowers.add(toGreenTower);
		toTowers.add(toGreenTower);
		toTowers.add(toGreenTower);
		

		this.add(greenTower);
		this.add(toGreenTower);
		
		this.add(azureTower);
		this.add(toAzureTower);
		
		this.add(yellowTower);
		this.add(toYellowTower);
		
		this.add(violetTower);
		this.add(toVioletTower);
		
	}
	
	void updateUpperPiece(Game game) {
		
		for ( ActionTower tower : actionTowers) {
			int i = 0;
			tower.updateTower(game, i);
			i++;
		}
		for ( ToTower toTower : toTowers) {
			int i = 0;
			toTower.updateToTower(game, i);
			i++;
		}
	}

}
