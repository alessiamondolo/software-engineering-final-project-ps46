package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Component;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import it.polimi.ingsw.ps46.server.card.Card;

/**
 * A class to model the behaviour of cells that hold cards. It inherits cells common features
 * from the supertype. 
 * The static attribute imageList holds the list of the images so far loaded from files in order
 * to speed up I/O processing
 * @author lorenzo
 *
 */

public class CardCell extends Cell<Card> {
	
	private static final long serialVersionUID = 5769254098690808463L;
	
	static ArrayList<BufferedImage> imageList = null;
	
	/**
	 * Each CardCell is provided with an action listener to trigger a zoom method
	 */
	
	public CardCell () {
		
		super();
		if (imageList == null) {
			imageList = new ArrayList<BufferedImage> (CardNames.names.length);
			for (int i = 0; i < CardNames.names.length; i++)
				imageList.add(null);
		}
		this.setEnabled(true);
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				if (itemList.isEmpty() == false) {
					int index = CardNames.find(itemList.get(0).getCardName());
					BufferedImage img = CardCell.imageList.get(index);
					ZoomBox.setImage(img);
				}
			}
			
		});
	}

	/**
	 *  Checks which image card needs to be displayed
	 */
	
	@Override
	public void update() {
		
		this.removeAllCards();
		for (Card card : itemList) {
			int index = CardNames.find(card.getCardName());
			
			BufferedImage img = imageList.get(index);
			if (img == null) {
				img = loadCard(index);
				
				imageList.set(index, img);  
			}
			
			// TODO ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(g.getClipBounds().width, g.getClipBounds().height, Image.SCALE_SMOOTH));
			JLabel l = new JLabel();
			l.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
			this.add(l);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	/**
	 * Load card images from I/O 
	 * @param index : used to build the correct file path 
	 * @return
	 */
	
	public BufferedImage loadCard(int index) {
		
		String path = "cards/devcards_f_en_c_" + index + ".png";
		BufferedImage img = null;
		try {
			img = Token.getImagePathMode(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * Remove card images JLabel to make sure no longer needed cards are held by the
	 * CardCell
	 */
	
	private void removeAllCards() {
		for (Component c : this.getComponents()) {
			if (c instanceof JLabel) {
				this.remove(c);
			}
				
		}
	}


}

/**
 * Used to map Card names with the correct index used in the loadCard method.
 * Card names are stored in a String vector ordered in the same way of the imageList 
 * attribute and of the images files, this way loading operations are facilitated
 * @author lorenzo
 *
 */

final class CardNames {
	static String names[] = {
			"",
			"Commercial Hub",
			"Woods",
			"Village",
			"Gravel Pit",
			"Forest",
			"Monastery",
			"Citadel",
			"City",
			"Gold Mine",
			"Mountain Town",  //11
			"Mining Town",
			"Rock Pit",
			"Estate",
			"Hermitage",
			"Manor House",
			"Dukedom",
			"Trading Town",
			"Farm",
			"Colony",
			"Marble Pit",      //21
			"Province",
			"Sanctuary",
			"Castle",
			"Fortified Town",
			"Mint",
			"Tax Office",
			"Triumphal Arch",
			"Theater",
			"Carpenter's Shop",
			"Stonemason's Shop",  //31
			"Chapel",
			"Residence",
			"Marketplace",
			"Treasury",
			"Painters' Guild",
			"Sculptors' Guild",
			"Stonemasons' Guild",
			"Baptistery",
			"Barracks",
			"Stronghold",         //41
			"Bank",
			"Fair",
			"Garden",
			"Fortress",
			"Palace",
			"Church",
			"Military Academy",
			"Cathedral",
			"Warlord",
			"Stonemason",           //51
			"Dame",
			"Knight",
			"Farmer",
			"Artisan",
			"Preacher",
			"Abess",
			"Captain",
			"Architect",
			"Patron",
			"Hero",                  //61
			"Peasant",
			"Scholar",
			"Papal Messenger",
			"Royal Messenger",
			"Noble",
			"Governor",
			"Paramour",
			"Herald",
			"Cardinal",
			"Bishop",                    //71
			"General",
			"Ambassador",
			"Hiring Recruits",
			"Repairing the Church",
			"Building the Walls",
			"Raising a Staute",
			"Military Campaign",
			"Hosting Panhandlers",
			"Fighting Heresies",
			"Support to the Bishop",              //81
			"Hiring Soldiers",
			"Repairing the Abbey",
			"Building the Bastions",
			"Support to the King",
			"Improving the Canals",
			"Hosting Foreigners",
			"Crusade",
			"Support to the Cardinal",
			"Hiring Mercenaries",
			"Repairing the Cathedral",            //91
			"Building the Towers",
			"Promoting Sacred Art",
			"Military Conquest",
			"Improving the Roads",
			"Sacred War",
			"Support to the Pope"	
	};
	
	/**
	 * Used to navigate with the cards' names vector
	 * @param name : the name of the card that needs to be retrieved
	 * @return : the corresponding index
	 */
	
	static int find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].compareToIgnoreCase(name) == 0)
				return i;
		}
		return -1;
	}
}


