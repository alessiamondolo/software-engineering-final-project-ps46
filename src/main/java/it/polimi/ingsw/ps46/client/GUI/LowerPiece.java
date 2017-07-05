package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

public class LowerPiece extends JPanel {
	
	JPanel councilPanel = new JPanel();
	JPanel faithPanel = new JPanel();
	JPanel productionPanel = new JPanel();
	private ArrayList <PointCell> faithCells = new ArrayList <PointCell> ();
	double width;
	double height;
	Game game;
	Dimension tokenDimension;
	ArrayList <CardCell> excommCards = new ArrayList<CardCell>();
	ArrayList <PointCell> actionCells = new ArrayList<PointCell>();
	double widthSmall;
	double heightSmall;

	public LowerPiece(double widthSmall, double heightSmall) {
		
		this.widthSmall = widthSmall;
		this.heightSmall = heightSmall;
		this.width = widthSmall*17;
		this.height = heightSmall*13;

		this.setPreferredSize(new Dimension((int) width, (int) height));

		this.setLayout(new GridBagLayout());
		Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
		
		councilPanel.setOpaque(false);
		faithPanel.setOpaque(false);
		productionPanel.setOpaque(false);
		
		//councilPanel.setBorder(border);
		
		productionPanel.setBorder(border);
			
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.65;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(councilPanel, gbc);	
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.10;
		gbc.fill = GridBagConstraints.BOTH;
		//faithPanel.setPreferredSize(new Dimension( (int) this.width, (int) this.height/50));
		//faithPanel.setMaximumSize(getPreferredSize());
		//faithPanel.setMinimumSize(getPreferredSize());
		this.add(faithPanel, gbc);
	
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.7;
		gbc.fill = GridBagConstraints.BOTH;	
		this.add(productionPanel, gbc);
		
		
		setCouncilPanel();
		setFaithPanel();
		setProductionPanel();
	}
	
	private void setCouncilPanel() {
		
		//JPanel exCard1 = new JPanel();
		//JPanel exCard2 = new JPanel();
		//JPanel exCard3 = new JPanel();
		
		councilPanel.setLayout(null);

		Insets insets = councilPanel.getInsets();
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
			excommCards.add(new CardCell());
			excommCards.get(i).setPreferredSize(new Dimension((int) widthSmall*7/5, (int) heightSmall*5/2));
			councilPanel.add(excommCards.get(i));
			Dimension size = excommCards.get(i).getPreferredSize();
			excommCards.get(i).setBounds((int) widthSmall*13/5 + insets.left, (int) heightSmall*2 + insets.top, size.width, size.height);	
			} else if (i == 1) {
				excommCards.add(new CardCell());
				excommCards.get(i).setPreferredSize(new Dimension((int) widthSmall*8/5, (int) heightSmall*11/4));
				councilPanel.add(excommCards.get(i));
				Dimension size = excommCards.get(i).getPreferredSize();
				excommCards.get(i).setBounds((int) widthSmall*21/5 + insets.left, (int) heightSmall*2 + insets.top, size.width, size.height);	
			} else {
				excommCards.add(new CardCell());
				excommCards.get(i).setPreferredSize(new Dimension(35,50));
				councilPanel.add(excommCards.get(i));
				Dimension size = excommCards.get(i).getPreferredSize();
				excommCards.get(i).setBounds((int) widthSmall*147/25 + insets.left, (int) heightSmall*2 + insets.top, size.width, size.height);	
			}
		}
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		PointCell counsilCell = new PointCell(25);
		councilPanel.add(counsilCell);
		actionCells.add(counsilCell);
		counsilCell.setPreferredSize(new Dimension((int) widthSmall*27/5, (int) heightSmall*9/4 +2 ));
		Dimension size = counsilCell.getPreferredSize();
		counsilCell.setBounds((int) widthSmall*43/5 + insets.left, insets.top, size.width, size.height);
		
	}
	
	private void setFaithPanel() {
		
		faithPanel.setLayout(new BoxLayout(faithPanel, BoxLayout.X_AXIS));
		
		System.out.println(width);
		System.out.println(height);
		double cellWidth = (this.width)/16;
		System.out.println(this.width/16);
		double cellHeight = this.height/40;
		
		
		for (int i = 0; i < 16; i++) {
			
			
			PointCell faithCell = new PointCell();
			
			if ( i == 0) {
				faithCell.setPreferredSize(new Dimension( (int) (cellWidth*6)/10, (int) cellHeight));
				faithCell.setMaximumSize(getPreferredSize());
				faithCell.setMinimumSize(getPreferredSize());
			}
			
			
			if (i == 1 || i == 2) {
				faithCell.setPreferredSize(new Dimension( (int) cellWidth, (int) cellHeight));
				faithCell.setMaximumSize(getPreferredSize());
				faithCell.setMinimumSize(getPreferredSize());
			}
			
			if (i == 3 || i == 4 || i == 5) {
				faithCell.setPreferredSize(new Dimension( (int) (cellWidth*14)/10, (int) cellHeight));
				faithCell.setMaximumSize(getPreferredSize());
				faithCell.setMinimumSize(getPreferredSize());
			}
			
			if (5 < i && i < 16) {
				faithCell.setPreferredSize(new Dimension( (int) (cellWidth*9)/10, (int) cellHeight));
				faithCell.setMaximumSize(getPreferredSize());
				faithCell.setMinimumSize(getPreferredSize());
			}
			
			faithCells.add(faithCell);
			faithPanel.add(faithCell);
			
		}
		
	}
	
	private void setProductionPanel() {
		
	}
	
	public void updateLowerPiece(Game game) {
		
		this.game = game;
		updateFaithPoints();
		updateCounsilPanel();
		
		
	}
	
	private void updateFaithPoints() {
		
		for (Player player : game.getPlayers()) {
			int vp = player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("FaithPoints").getQuantity();
			for (int i = 0; i < faithCells.size(); i++) {
				PointCell fc = faithCells.get(i); 
				if (i != vp) fc.remove(player);
				else fc.add(player); 
				
			}
		}	
	}

	private void  updateCounsilPanel() {
		
		for (int i = 0; i < excommCards.size(); i++) {
			
			//metodo per recuperare le carte scomunica correntemente li presenti e visualizzar
		}
		
		ArrayList <String> fmColors = new ArrayList <String>();
		fmColors.add("White");
		fmColors.add("Black");
		fmColors.add("Orange");
		fmColors.add("Neutral");
		
		for (String fmColor : fmColors) {
			for (Player player : game.getPlayers()) {
				FamilyMember fm = player.getFamilyMember(fmColor);
				int fmPosition = fm.getPositionOfFamilyMember();
				
				if (fmPosition == 25){
					PointCell counsilCell  = (PointCell) actionCells.get(0);
					counsilCell.add(player, fmColor);   
				}
			}
		}
		
	}
}
	
