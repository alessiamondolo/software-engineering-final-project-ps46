package it.polimi.ingsw.ps46.client;

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
	
}
