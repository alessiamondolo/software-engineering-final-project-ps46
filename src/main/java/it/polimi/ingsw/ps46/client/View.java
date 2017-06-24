package it.polimi.ingsw.ps46.client;

import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.ActionSpaceName;
import it.polimi.ingsw.ps46.server.Game;


/**
 * Description of View.
 * 
 * @author Alessia Mondolo
 */
public interface View { 
	
	public void setGame(Game game);

	public void welcomeMessage();
	
	public String getGameMode();

	public String getPlayerUserame();

	public void showInitialOrder();

	public String getPlayerColor(ArrayList<String> colors);
	
	public void printBoard();

	public ActionSpaceName getPlayerAction();

	public void printPlayerStatus();

	public void updateRoundInfo();
	
}
