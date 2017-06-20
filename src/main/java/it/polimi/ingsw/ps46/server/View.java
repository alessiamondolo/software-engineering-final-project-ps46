package it.polimi.ingsw.ps46.server;

import java.util.Observable;
import java.util.Observer;


/**
 * Description of View.
 * 
 * @author Alessia Mondolo
 */
public abstract class View extends Observable implements Observer, EventVisitor { 

	public void welcomeMessage() {		
	}

	public void getPlayerUserame(int id) {
	}

	public void showInitialOrder() {		
	}

	public String getPlayerColor(String username) {
		return null;
	}
	
	public void printBoard() {
	}

	public ActionSpaceName getPlayerAction() {
		return null;		
	}

	public void printPlayerStatus(String username) {		
	}

	public void updateRoundInfo() {
	}
	
}
