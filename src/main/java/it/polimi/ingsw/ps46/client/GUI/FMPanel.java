package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

public class FMPanel extends JPanel {
	
	private ArrayList<PointCell> FMCells = new ArrayList<PointCell>();
	private JLabel title;
	private Dimension dimension;
	
	
	public FMPanel(Dimension dimension) {
		
		this.dimension = dimension;
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(new Color(213, 50, 90, 123));
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
			
			PointCell FMCell = new PointCell();
			FMCell.setEnabled(true);
			FMCell.setOpaque(true);
			FMCell.setBackground(Color.lightGray);
			FMCells.add(FMCell);
			FMCell.setPreferredSize(new Dimension(dimension.width/5,dimension.height*2/5)); 
			panel.add(FMCell);
			
			if (i == 0) {
				FMCell.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Object mon;
						synchronized (mon = GUIView.getMonitor()) {
							GUIView.setFamilyMember("Neutral");
							mon.notifyAll();
						}
					}
				});
			} else if (i == 1) {
				FMCell.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Object mon;
						synchronized (mon = GUIView.getMonitor()) {
							GUIView.setFamilyMember("Black");
							mon.notifyAll();
						}
					}
				});
			} else if (i == 2) {
				FMCell.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Object mon;
						synchronized (mon = GUIView.getMonitor()) {
							GUIView.setFamilyMember("White");
							mon.notifyAll();
						}
					}
				});
			} else {
				FMCell.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Object mon;
						synchronized (mon = GUIView.getMonitor()) {
							GUIView.setFamilyMember("Orange");
							mon.notifyAll();
						}
					}
				});
			}
			
			
		}	
		
		this.add(panel, BorderLayout.SOUTH);
	}
	
	public void update(Game game) {
		
		Player player = game.getCurrentPlayer();
		title.setText("Current Player:" +player.getUsername());
		
		for ( PointCell cell : FMCells) {
			cell.itemList.clear();
		}
		
		for ( int i = 0; i < FMCells.size(); i++) {
			
			PointCell cell = FMCells.get(i);
			FamilyMember fm;
			if (i == 0) {
				fm = player.getFamilyMember("Neutral");
				if (!fm.isUsed()) {
					cell.add(player, "Neutral");
				}
			}
			if (i == 1) {
				fm = player.getFamilyMember("Black");
				if (!fm.isUsed()) {
					cell.add(player, "Black");
				}
			}
			if (i == 2) {
				fm = player.getFamilyMember("White");
				if (!fm.isUsed()) {
					cell.add(player, "White");
				}
			}
			if (i == 3) {
				fm = player.getFamilyMember("Orange");
				if (!fm.isUsed()) {
					cell.add(player, "Orange");
				}
			}
			
		}
	}
}
