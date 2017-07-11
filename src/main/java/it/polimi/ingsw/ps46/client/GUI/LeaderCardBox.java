package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.LeaderCard;

public class LeaderCardBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460971081553511650L;
	private ArrayList <LeaderCardCell> leaderCards;
	private ArrayList<JButton> actionButtons;
	private Game game;
	private Player player;
	
	public LeaderCardBox(Player player, double width, double height) {
		
		this.leaderCards = new ArrayList <LeaderCardCell>();
		this.actionButtons = new ArrayList <JButton>();
		this.player = player;
		
		for (int i = 0; i < 4; i++) {
			LeaderCardCell cell = new LeaderCardCell();
			cell.setPreferredSize(new Dimension( (int) width/5, (int) height/8));
			leaderCards.add(cell);
			this.add(cell);
		}
		
		for (int i = 0; i < 4; i++ ) {
			JButton button  = new JButton();
			actionButtons.add(button);
			button.setText("Use");
			
			button.setPreferredSize(new Dimension( (int) width/5, (int) height/20));
			button.setFont(new Font("Arial", Font.PLAIN, 10));
			this.add(button);
			int code = i+40; // TODO
			button.addActionListener(new ActionListener() {
				
				private int action = code;
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object mon;
					synchronized (mon = GUIView.getMonitor()) {
						GUIView.setAction(action);
						mon.notifyAll();
					}
				}
			});
		}
		
		
	}
	
	public void update(Game game) {
		
		this.game = game;
		
		int id =  this.player.getIdPlayer();
		ArrayList<Player> players = (ArrayList<Player>) this.game.getPlayers(); 
		for (Player player : players) {
			if (player.getIdPlayer() == id) {   
				this.player = player;
			}
		}
		
		ArrayList <LeaderCard> cards = new ArrayList <LeaderCard>();
		
		for(String key : this.player.getLeaderCards().keySet()) {
			cards.add(this.player.getLeaderCards().get(key));
		}
		int i = 0;
		for ( LeaderCardCell cell : leaderCards) {
			
			LeaderCard card = cards.get(i);
			cell.removeAll();
			if (card != null) {
				cell.add(card);
			}
			i ++;
		}
		
		
	}	
}

class LeaderCardCell extends Cell<LeaderCard> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2476812680969015433L;
	static ArrayList<BufferedImage>	leaderImageList = null;
	
	public LeaderCardCell () {
			
		super();
		if (leaderImageList == null) {
			leaderImageList = new ArrayList<BufferedImage> (LeaderCardNames.leaderNames.length);
			for (int i = 0; i < LeaderCardNames.leaderNames.length; i++)
				leaderImageList.add(null);
		}
		this.setEnabled(true);
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				if (itemList.isEmpty() == false) {
					int index = LeaderCardNames.find(itemList.get(0).getCardName());
					BufferedImage img = LeaderCardCell.leaderImageList.get(index);
					ZoomBox.setImage(img);
				}
			}
			
		});
	}

	@Override
	public void update() {
		
		this.removeAllCards();
		for (LeaderCard card : itemList) {
			int index = 0;
			if (card.isPermanent() || !card.isActive()) {
				index = CardNames.find(card.getCardName());
			}
			System.out.println("Sto cercando la carta" +card.getCardName()+ " con indice " +index);
			BufferedImage img = leaderImageList.get(index);
			if (img == null) {
				img = loadCard(index);
				
				leaderImageList.set(index, img);
			}
			
			// TODO ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(g.getClipBounds().width, g.getClipBounds().height, Image.SCALE_SMOOTH));
			JLabel l = new JLabel();
			l.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
			this.add(l);
		}
		
	}
	
	public BufferedImage loadCard(int index) {
		
		String path;
		if (index == 0) {
			path = "leaders_card/leaders_b_c_00.jpg";
		} else {
			path = "leaders_card/leaders_f_c_" + String.format("%02d", index) + ".jpg";
		}
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

final class LeaderCardNames {
	
	static String leaderNames[] = {
			"",
			"Francesco Sforza",
			"Ludovico Ariosto",
			"Filippo Brunelleschi",
			"Federico da Montefeltro",
			"Girolamo Savonarola",
			"Giovanni dalle Bande Nere",
			"Sandro Botticelli",
			"Michelangelo Buonarroti",
			"Ludovico III Gonzaga",
			"Leonardo da Vinci",  //10
			"Pico della Mirandola",
			"Sisto IV",
			"Lucrezia Borgia",
			"Sigismondo Malatesta",
			"Lorenzo de' Medici",
			"Ludovico il Moro",
			"Cesare Borgia",
			"Santa Rita",
			"Cosimo de' Medici",
			"Bartolomeo Colleoni",  //29

	};
	
	static int find(String name) {
		for (int i = 0; i < leaderNames.length; i++) {
			if (leaderNames[i].compareToIgnoreCase(name) == 0)
				return i;
		}
		return -1;
	}
	
}
