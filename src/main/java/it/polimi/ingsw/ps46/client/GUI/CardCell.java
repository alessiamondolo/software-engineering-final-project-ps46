package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import it.polimi.ingsw.ps46.server.card.Card;

public class CardCell extends Cell<Card> {
	
	private static final long serialVersionUID = 5769254098690808463L;

	public CardCell() {
		imageList = new ArrayList<Image> ();
	}

	/**
	 *  Paints a card accordingly to the one the model set for that tower floor.
	 */
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		this.removeAll();
		for (Card c : itemList) {
			int index = CardNames.find(c.getCardName());
			Image img = imageList.get(index);
			if (img == null) {
				img = loadCard(index);
				imageList.set(index, img);
			}
			ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(g.getClipBounds().width, g.getClipBounds().height, Image.SCALE_SMOOTH));
			this.setIcon(imageIcon);
		}
	}
	
	private static ArrayList<Image> imageList;
	
	public Image loadCard(int index) {
		
		String path = "img/cards/devcards_f_en_c_" + index + ".png";
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}

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
			"Mountain Town",
			"Mining Town",
			"Rock Pit",
			"Estate",
			"Hermitage",
			"Manor House",
			"Dukedom",
			"Trading Town",
			"Farm",
			"Colony",
			"Marble Pit",
			"Province",
			"Sanctuary",
			"Castle",
			"Fortified Town",
			"Mint",
			"Tax Office",
			"Triumphal Arch",
			"Theater",
			"Carpenter's Shop",
			"Stonemason's Shop",
			"Chapel",
			"Residence",
			"Marketplace",
			"Treasury",
			"Painters' Guild",
			"Sculptors' Guild",
			"Stonemasons' Guild",
			"Baptistery",
			"Barracks",
			"Stronghold",
			"Bank",
			"Fair",
			"Garden",
			"Fortress",
			"Palace",
			"Church",
			"Military Academy",
			"Cathedral",
			"Warlord",
			"Stonemason",
			"Dame",
			"Knight",
			"Farmer",
			"Artisan",
			"Preacher",
			"Abbess",
			"Captain",
			"Architect",
			"Patron",
			"Hero",
			"Peasant",
			"Scholar",
			"Papal Messenger",
			"Royal Messenger",
			"Noble",
			"Governor",
			"Paramour",
			"Herald",
			"Cardinal",
			"Bishop",
			"General",
			"Ambassador",
			"Hiring Recruits",
			"Repairing the Church",
			"Building the Walls",
			"Raising a Statue",
			"Military Campaign",
			"Hosting Panhandlers",
			"Fighting Heresies",
			"Support to the Bishop",
			"Hiring Soldiers",
			"Repairing the Abbey",
			"Building the Bastions",
			"Support to the King",
			"Improving the Canals",
			"Hosting Foreigners",
			"Crusade",
			"Support to the Cardinal",
			"Hiring Mercenaries",
			"Repairing the Cathedral",
			"Building the Towers",
			"Promoting Sacred Art",
			"Military Conquest",
			"Improving the Roads",
			"Sacred War",
			"Support to the Pope"	
	};
	
	static int find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].compareToIgnoreCase(name) == 0)
				return i;
		}
		return -1;
	}
}