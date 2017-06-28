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
	
	//cosa ricevere e come mappare le carte con i file immagine per implementare il metodo
	//paint per fargli stampare di volta in volta l'immagine della carta corretta?
	
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
	
	private Image loadCard(int index) {
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
			"Mint",
			"Woods"
	};
	static int find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].compareToIgnoreCase(name) == 0)
				return i;
		}
		return -1;
	}
}