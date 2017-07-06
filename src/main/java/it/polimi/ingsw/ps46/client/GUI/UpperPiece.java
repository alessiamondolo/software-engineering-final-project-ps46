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

import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

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
		
		ToTower toGreenTower = new ToTower(0, widthSmall, heightSmall);
		ToTower toAzureTower = new ToTower(1, widthSmall, heightSmall);
		ToTower toYellowTower = new ToTower(2, widthSmall, heightSmall);
		ToTower toVioletTower = new ToTower(3, widthSmall, heightSmall);
		
		toTowers.add(toGreenTower);
		toTowers.add(toAzureTower);
		toTowers.add(toYellowTower);
		toTowers.add(toVioletTower);
		

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
		
		this.game = game;
		
		//per le carte dovrei allo stesso modo usare la clear?
		
		for ( ActionTower tower : actionTowers) {
			int i = 0;
			tower.updateTower(game, i);
			i++;
		}
		
		ArrayList <String> fmColors = new ArrayList <String>();
		fmColors.add("White");
		fmColors.add("Black");
		fmColors.add("Orange");
		fmColors.add("Neutral");
		
		for ( int x = 0; x < 4; x++) {
			ArrayList <PointCell> actionCells = toTowers.get(x).getActionCells();
			for (PointCell cell : actionCells) {
				cell.itemList.clear();
			}
		}
		
		for (String fmColor : fmColors) {
			for (Player player : game.getPlayers()) {
				int i;
				FamilyMember fm = player.getFamilyMember(fmColor);
				int fmPosition = fm.getPositionOfFamilyMember();
				
				if (0 < fmPosition && fmPosition < 5){
					i = 0;
					PointCell position = (PointCell) toTowers.get(i).getActionCells().get(4 - fmPosition);
					position.add(player, fmColor);   
				}
				
				if (4 < fmPosition && fmPosition < 9){
					i = 1;
					PointCell position = (PointCell) toTowers.get(i).getActionCells().get(8 - fmPosition);
					position.add(player, fmColor);   
				}
				
				if (8 < fmPosition && fmPosition < 13){
					i = 2;
					PointCell position = (PointCell) toTowers.get(i).getActionCells().get(12 - fmPosition);
					position.add(player, fmColor);   
				}
				
				if (12 < fmPosition && fmPosition < 17){
					i = 3;
					PointCell position = (PointCell) toTowers.get(i).getActionCells().get(16 - fmPosition);
					position.add(player, fmColor);   
				}
			}
		}
		
	}

}
