package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1228559942864929434L;
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
	
	public void update(Game game) {
		
		//per le carte dovrei allo stesso modo usare la clear?
		int i = 0;
		for ( ActionTower tower : actionTowers) {
			tower.update(game, i);
			i++;
		}
		
		ArrayList <String> fmColors = new ArrayList <String>();
		fmColors.add("White");
		fmColors.add("Black");
		fmColors.add("Orange");
		fmColors.add("Neutral");
		
		for ( int x = 0; x < 4; x++) {
			toTowers.get(x).removeAll();
			
		}
		
		for (String fmColor : fmColors) {
			for (Player player : game.getPlayers()) {
				FamilyMember fm = player.getFamilyMember(fmColor);
				int fmPosition = fm.getPositionOfFamilyMember();
				if (0 < fmPosition && fmPosition <= 16) {
					i = fmPosition / 4;
					toTowers.get(i).add(player, fmColor, 4 - fmPosition%4);
				}
			}
		}
		
	}

}
