package it.polimi.ingsw.ps46.server;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Description of View.
 * 
 * @author Alessia Mondolo
 */
public abstract class View extends Observable implements Observer{ 

	public void welcomeMessage() {		
	}

	public String getPlayerUserame(int id) {
		return null;
	}

	public void showInitialOrder(List<String> initialOrder) {		
	}

	public String getPlayerColor(String username, List<String> colors) {
		return null;
	}	
	
	public void rollDice() {
	}
	
	public void printBoard(List<Integer> dice) {
	}

	public ActionSpaceName getPlayerAction() {
		return null;		
	}

	public void printPlayerStatus(String username) {		
	}

	public void updateRoundInfo(int period, int round) {
	}
	
}
