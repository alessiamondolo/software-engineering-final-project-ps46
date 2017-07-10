package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

public class FMPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7824922132057951490L;
	private ArrayList<PointCell> FMCells = new ArrayList<PointCell>();
	private JLabel title;
	
	public static final String[] fmTypes = { "Neutral", "Black", "White", "Orange" };
	
	public FMPanel(Dimension dimension) {
		
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		title = new JLabel("", SwingConstants.CENTER);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		title.setBorder(border);
		this.setOpaque(true);
		this.setBackground(new Color(213, 134, 145, 123));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Family Member"));
		
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		
		for (int i = 0; i < 4; i++) {
			
			PointCell FMCell = new PointCell(fmTypes[i]);
			FMCells.add(FMCell);
			FMCell.setPreferredSize(new Dimension(dimension.width/5,dimension.height*2/5)); 
			panel.add(FMCell);
		}	
		
		this.add(panel, BorderLayout.SOUTH);
	}
	
	public void update(Game game) {
		
		Player player = game.getCurrentPlayer();
		title.setText("Current Player:" +player.getUsername());
		
		for ( PointCell cell : FMCells) {
			cell.removeAll();
		}
		
		for ( int i = 0; i < FMCells.size(); i++) {
			
			int period = game.getCurrentPeriod();
			String username = game.getCurrentPlayer().getUsername();
			
			PointCell cell = FMCells.get(i);
			FamilyMember fm = player.getFamilyMember(fmTypes[i]);
			System.out.println("Siamo nel periodo " +period+ "ed il current player è: " +username);
			System.out.println("Family Member: " + fm.getColor());
			System.out.println("Used: " + fm.isUsed());
			if (!fm.isUsed()) {
				cell.add(player, fmTypes[i]);
			}
		}
		repaint();
	}
	
}
