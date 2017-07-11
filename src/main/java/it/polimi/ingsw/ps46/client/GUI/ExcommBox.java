package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.ExcommunicationTile;

public class ExcommBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8106470391591568439L;
	private ExcommCardCell excommCardCell;
	// private PointCell pointCell;
	private int era;
	
	public ExcommBox(int era) {
		this.era = era;
		this.setLayout(null);
		
		excommCardCell = new ExcommCardCell();
		excommCardCell.setContentAreaFilled(false);
		// pointCell = new PointCell();
		// pointCell.setContentAreaFilled(false);
		
		// this.add(pointCell);
		this.add(excommCardCell);
		
		this.setBorder(null);
		this.setBackground(null);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		Rectangle bounds = g.getClipBounds();
		excommCardCell.setBounds(bounds);
		// bounds.height /= 2;
		// pointCell.setBounds(bounds);
	}
	
	public void update(Game game) {
		
		// pointCell.removeAll();
		
		excommCardCell.removeAll();
		
		for (ExcommunicationTile tile : game.getExcommunicationTiles()){
			if (tile.getEra() == this.era) {
				excommCardCell.add(tile);
				break;
			}
		}
	}
}

class ExcommCardCell extends Cell<ExcommunicationTile> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2476812680969015433L;
	static ArrayList<BufferedImage>	excommImageList = null;
	
	public ExcommCardCell () {
			
		super();
		if (excommImageList == null) {
			excommImageList = new ArrayList<BufferedImage> (ExcommCardNames.names.length);
			for (int i = 0; i < ExcommCardNames.names.length; i++)
				excommImageList.add(null);
		}
		this.setEnabled(true);
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				if (itemList.isEmpty() == false) {
					int index = itemList.get(0).getId();
					BufferedImage img = ExcommCardCell.excommImageList.get(index);
					ZoomBox.setImage(img);
				}
			}
			
		});
	}

	@Override
	public void update() {
		
		this.removeAllCards();
		for (ExcommunicationTile card : itemList) {
			int index = card.getId();
			System.out.println("Sto cercando la carta " +card.getId() + " con indice " + index);
			BufferedImage img = excommImageList.get(index);
			if (img == null) {
				img = loadCard(index);
				
				excommImageList.set(index, img);
			}
			
			JLabel l = new JLabel();
			l.setIcon(new ImageIcon(img.getScaledInstance(getPreferredSize().width, getPreferredSize().height, Image.SCALE_SMOOTH)));
			this.add(l);
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.setPreferredSize(g.getClipBounds().getSize());
	}
	
	public BufferedImage loadCard(int index) {
		
		String path = "mixed/" + ExcommCardNames.names[index];
		BufferedImage img = null;
		try {
			img = Token.getImagePathMode(path);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	private void removeAllCards() {
		for (Component c : this.getComponents()) {
			if (c instanceof JLabel) {
				this.remove(c);
			}
				
		}
	}
	
}

final class ExcommCardNames {
	static String names[] = {
			"excomm_1_1.png",
			"excomm_1_2.png",
			"excomm_1_3.png",
			"excomm_1_4.png",
			"excomm_1_5.png",
			"excomm_1_6.png",
			"excomm_1_7.png",
			"excomm_2_1.png",
			"excomm_2_2.png",
			"excomm_2_3.png",
			"excomm_2_4.png",
			"excomm_2_5.png",
			"excomm_2_6.png",
			"excomm_2_7.png",
			"excomm_3_1.png",
			"excomm_3_2.png",
			"excomm_3_3.png",
			"excomm_3_4.png",
			"excomm_3_5.png",
			"excomm_3_6.png",
			"excomm_3_7.png",
	};
}