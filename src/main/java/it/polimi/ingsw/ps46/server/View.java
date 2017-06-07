package it.polimi.ingsw.ps46.server;

import java.util.Observable;
import java.util.Observer;


/**
 * Description of View.
 * 
 * @author a.mondolo
 */
public abstract class View extends Observable implements Runnable, Observer{ 
	
	@Override
	public void update(Observable o, Object arg) {
		//WIP
	}

	public void welcomeMessage() {		
	}

	public String getPlayerUserame(int id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
