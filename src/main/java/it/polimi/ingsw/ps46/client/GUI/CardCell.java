package it.polimi.ingsw.ps46.client.GUI;

public class CardCell extends Cell {
	
	//da valutare se renderla zoomabile quindi dotata di action listener
	
	
	String cardName;

	//anche le celle carte possono essere identificate da un numero di convenzione. ogni torre dall'alto verso il basso..
	
	public CardCell(int number, String cardName) {
		
		super(number);
		this.cardName = cardName;
		this.availability = true; //strettamente legato alla casella appena a fianco a dx
	
	}
	
	public void showCard() {
		 
		//getImage(String cardName);
		//this.add(Image);
	}
	
/**
 * A CardCell is created with a card associated, the attribute availability represents
 * the presence of the above mentioned card, whenever a player picks up the card the 
 * setAvailability method gets called and sets availability to false while removing also, 
 *  the card image, meaning that the CardCell no longer has an available card.
 */


	@Override
	void setAvailability() {
		
		this.availability = false;
		//this.remove(image);
		
	}

	@Override
	void showToken(Token token) {

		
	}
}
