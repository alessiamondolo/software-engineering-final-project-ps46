package it.polimi.ingsw.ps46.client.GUI;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * An abstract cell of the board. The board is made of different cells characterized
 * by different functionalities and attributes. Each concrete implementations needs 
 * to define the setAvailability method based on the cell type specific requirements. 
 * @author lorenzo
 *
 */

// si può dire che una classa astratta estende ed implementa qualcosa??

abstract class Cell extends JPanel {
	
	protected final int number;   //bisogna metterlo final?
	protected boolean availability;  //se non è available vorrei renderla non cliccabile
	
	public Cell (int number) {
		this.number = number;
	}
	
	public boolean isAvailable() {
		return (this.availability);
	}
	
	abstract void setAvailability(); //ogni cella implementerà le sue condizioni
	
	abstract void showToken(Token token); //problema, la classe cardcell però non ha token

}
