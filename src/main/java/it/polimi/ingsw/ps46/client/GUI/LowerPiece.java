package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

public class LowerPiece extends JPanel {
	
	JPanel upperPanel = new JPanel();
	JPanel faithPanel = new JPanel();
	JPanel actionPanel = new JPanel();
	private ArrayList <PointCell> faithCells = new ArrayList <PointCell> ();
	double width;
	double height;
	Game game;
	Dimension tokenDimension;
	ArrayList <CardCell> excommCards = new ArrayList<CardCell>();
	ArrayList <PointCell> actionCells = new ArrayList<PointCell>();
	double widthSmall;
	double heightSmall;
	PointCell councilCell = new PointCell(25);
	JLabel blackDice = new JLabel();
	JLabel whiteDice = new JLabel();
	JLabel orangeDice = new JLabel();
	

	public LowerPiece(double widthSmall, double heightSmall) {
		
		this.widthSmall = widthSmall;
		this.heightSmall = heightSmall;
		this.width = widthSmall*17;
		this.height = heightSmall*13;

		this.setPreferredSize(new Dimension((int) width, (int) height));

		this.setLayout(new GridBagLayout());
		Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
		
		upperPanel.setOpaque(false);
		faithPanel.setOpaque(false);
		actionPanel.setOpaque(false);
		
		actionPanel.setBorder(border);
			
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.65;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(upperPanel, gbc);	
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.10;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(faithPanel, gbc);
	
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.7;
		gbc.fill = GridBagConstraints.BOTH;	
		this.add(actionPanel, gbc);
		
		
		setUpperPanel();
		setFaithPanel();
		setActionPanel();
	}
	
	private void setUpperPanel() {
		
		upperPanel.setLayout(null);

		Insets insets = upperPanel.getInsets();
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
			excommCards.add(new CardCell());
			excommCards.get(i).setPreferredSize(new Dimension((int) widthSmall*7/5, (int) heightSmall*5/2));
			upperPanel.add(excommCards.get(i));
			Dimension size = excommCards.get(i).getPreferredSize();
			excommCards.get(i).setBounds((int) widthSmall*13/5 + insets.left, (int) heightSmall*2 + insets.top, size.width, size.height);	
			} else if (i == 1) {
				excommCards.add(new CardCell());
				excommCards.get(i).setPreferredSize(new Dimension((int) widthSmall*8/5, (int) heightSmall*11/4));
				upperPanel.add(excommCards.get(i));
				Dimension size = excommCards.get(i).getPreferredSize();
				excommCards.get(i).setBounds((int) widthSmall*21/5 + insets.left, (int) heightSmall*2 + insets.top, size.width, size.height);	
			} else {
				excommCards.add(new CardCell());
				excommCards.get(i).setPreferredSize(new Dimension(35,50));
				upperPanel.add(excommCards.get(i));
				Dimension size = excommCards.get(i).getPreferredSize();
				excommCards.get(i).setBounds((int) widthSmall*147/25 + insets.left, (int) heightSmall*2 + insets.top, size.width, size.height);	
			}
		}
		
		upperPanel.add(councilCell);
		councilCell.setPreferredSize(new Dimension((int) widthSmall*27/5, (int) heightSmall*9/4 +2 ));
		Dimension size = councilCell.getPreferredSize();
		councilCell.setBounds((int) widthSmall*43/5 + insets.left, insets.top, size.width, size.height);
		
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
	
	private void setActionPanel() {
		
		Dimension size;
		
		actionPanel.setLayout(null);

		Insets insets = actionPanel.getInsets();
		
		PointCell smallPCell = new PointCell(17);
		actionCells.add(smallPCell);
		PointCell bigPCell = new PointCell(18);
		actionCells.add(bigPCell);
		PointCell smallHCell = new PointCell(19);
		actionCells.add(smallHCell);
		PointCell bigHCell = new PointCell(20);
		actionCells.add(bigHCell);
		
		smallHCell.setPreferredSize(new Dimension((int) widthSmall*4/3, (int) heightSmall*9/5));
		bigHCell.setPreferredSize(new Dimension((int) widthSmall*13/3, (int) heightSmall*9/5));
		smallPCell.setPreferredSize(new Dimension((int) widthSmall*4/3, (int) heightSmall*9/5));
		bigPCell.setPreferredSize(new Dimension((int) widthSmall*13/3, (int) heightSmall*9/5));
		
		size = smallHCell.getPreferredSize();
		smallHCell.setBounds(insets.left, (int) heightSmall*6/5 + insets.top, size.width, size.height);
		
		size = bigHCell.getPreferredSize();
		bigHCell.setBounds((int) widthSmall*19/9 + insets.left, (int) heightSmall*6/5 + insets.top, size.width, size.height);
		
		size = smallPCell.getPreferredSize();
		smallPCell.setBounds(insets.left, (int) heightSmall*4 + insets.top, size.width, size.height);
		
		size = bigPCell.getPreferredSize();
		bigPCell.setBounds((int) widthSmall*19/9 + insets.left, (int) heightSmall*4 + insets.top, size.width, size.height);
		
		actionPanel.add(smallHCell);
		actionPanel.add(bigHCell);
		actionPanel.add(smallPCell);
		actionPanel.add(bigPCell);
		
		PointCell market1 = new PointCell(21);
		actionCells.add(market1);
		PointCell market2 = new PointCell(22);
		actionCells.add(market2);
		PointCell market3 = new PointCell(23);
		actionCells.add(market3);
		PointCell market4 = new PointCell(24);
		actionCells.add(market4);
		actionCells.add(councilCell);
		
		market1.setPreferredSize(new Dimension((int) widthSmall*3/2, (int) heightSmall*9/5));
		market2.setPreferredSize(market1.getPreferredSize());
		market3.setPreferredSize(market1.getPreferredSize());
		market4.setPreferredSize(market1.getPreferredSize());
		
		size = market1.getPreferredSize();
		market1.setBounds((int) widthSmall*51/5 + insets.left, (int) heightSmall*5/7 + insets.top, size.width, size.height);
		market2.setBounds((int) widthSmall*12 + insets.left, (int) heightSmall*5/7 + insets.top, size.width, size.height);
		market3.setBounds((int) widthSmall*83/6 + insets.left, (int) heightSmall*6/5 + insets.top, size.width, size.height);
		market4.setBounds((int) widthSmall*46/3 + insets.left, (int) heightSmall*28/10 + insets.top, size.width, size.height);
		
		actionPanel.add(market1);
		actionPanel.add(market2);
		actionPanel.add(market3);
		actionPanel.add(market4);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		blackDice.setBorder(border);
		whiteDice.setBorder(border);
		orangeDice.setBorder(border);
		
		blackDice.setForeground(Color.WHITE);
		whiteDice.setForeground(Color.BLACK);
		orangeDice.setForeground(Color.BLACK);
		
		blackDice.setPreferredSize(market1.getPreferredSize());
		whiteDice.setPreferredSize(market1.getPreferredSize());
		orangeDice.setPreferredSize(market1.getPreferredSize());
		
		blackDice.setBounds((int) widthSmall*29/3 + insets.left, (int) heightSmall*13/3 + insets.top, size.width, size.height);
		whiteDice.setBounds((int) widthSmall*35/3 + insets.left, (int) heightSmall*13/3 + insets.top, size.width, size.height);
		orangeDice.setBounds((int) widthSmall*41/3 + insets.left, (int) heightSmall*13/3 + insets.top, size.width, size.height);
		
		actionPanel.add(blackDice);
		actionPanel.add(whiteDice);
		actionPanel.add(orangeDice);
		
	}
	
	public void updateLowerPiece(Game game) {
		
		this.game = game;
		updateFaithPoints();
		updateExCards();
		updateActionCell();
		updateDice();
			
	}
	
	private void updateFaithPoints() {
		
		for (Player player : game.getPlayers()) {
			int vp = player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("FaithPoints").getQuantity();
			for (int i = 0; i < faithCells.size(); i++) {
				PointCell fc = faithCells.get(i); 
				ArrayList<Player> players = fc.getItemList();
				for ( Player cellPlayer : players) {
					if ( player.getIdPlayer() == cellPlayer.getIdPlayer()) {
						fc.remove(cellPlayer);
					}
				}
				
				if (i == vp) fc.add(player);	
			}
		}	
	}

	private void  updateExCards() {
		
		for (int i = 0; i < excommCards.size(); i++) {
			
			//TODO METODO PER RECUPERARE CARTE SCOMUNICA
		}
		
	}
	
	private void updateActionCell() {
		
		ArrayList <String> fmColors = new ArrayList <String>();
		fmColors.add("White");
		fmColors.add("Black");
		fmColors.add("Orange");
		fmColors.add("Neutral");

		for (PointCell cell : actionCells) {
				cell.itemList.clear();
			}
		
		
		for (String fmColor : fmColors) {
			for (Player player : game.getPlayers()) {
				
				FamilyMember fm = player.getFamilyMember(fmColor);
				int fmPosition = fm.getPositionOfFamilyMember();	
				
				if (16 < fmPosition) {
					PointCell actionCell = actionCells.get(fmPosition - 17);
					actionCell.add(player);
				}
			}
		}	
	}
	
	private void updateDice() {
		
		Dice blackD = this.game.getDice("Black");
		Dice whiteD = this.game.getDice("White");
		Dice orangeD = this.game.getDice("Orange");
		
		blackDice.setText(String.valueOf(blackD.getValue()));
		whiteDice.setText(String.valueOf(blackD.getValue()));
		orangeDice.setText(String.valueOf(blackD.getValue()));
	}
}
	
