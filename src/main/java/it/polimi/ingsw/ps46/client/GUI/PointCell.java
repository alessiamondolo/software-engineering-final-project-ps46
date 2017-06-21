package it.polimi.ingsw.ps46.client.GUI;

import java.awt.List;
import java.util.ArrayList;

/**
 * A cell specific for the external grid. All players can place a token inside the cell.
 * This class provides functionalities the add, remove and show tokens on the external grid,
 * the one associated to victory points
 * @author lorenzo
 *
 */

public class PointCell extends Cell {
	
	ArrayList <Token> tokens = new ArrayList <Token> ();
	public PointCell(int number) {
		
		super(number);
		this.availability = true;
	}
	
	/**
	 * Checks if tokens can be placed on the cell, but it is always possible
	 * to place a token on a PointCell.
	 */
	
	@Override
	public void setAvailability() {
		this.availability = true;
	}
	
	@Override
	public void showToken(Token token) {
		
		tokens.add(token);
		this.add(token);	
		
	}
	
	//bisogna assicurarsi che non si cerchi di togliere un token che non c'è altrimenti ciclo infinito
	
	public void removeToken(Token token) { //ogni token è identificato dal suo colore, nel senso che in ogni cella c'è al max un token per colore
		int i=0;
		boolean found = false;
		do { 
			if (tokens.get(i).color == token.color) {
				found = true;
				this.remove(tokens.get(i));
			} else i++;
			
		} while (found != true);
		
	}
	
}

