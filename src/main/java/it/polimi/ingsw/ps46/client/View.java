package it.polimi.ingsw.ps46.client;

import it.polimi.ingsw.ps46.server.ActionSpaceName;


/**
 * Description of View.
 * 
 * @author Alessia Mondolo
 */
public interface View { 

	public void welcomeMessage();
	
	public String getGameMode();

	public String getPlayerUserame(int id);

	public void showInitialOrder();

	public String getPlayerColor(String username);
	
	public void printBoard();

	public ActionSpaceName getPlayerAction();

	public void printPlayerStatus();

	public void updateRoundInfo();
	
}
