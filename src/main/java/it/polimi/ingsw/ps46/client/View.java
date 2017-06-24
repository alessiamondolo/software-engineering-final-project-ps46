package it.polimi.ingsw.ps46.client;

import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.ActionSpaceName;
import it.polimi.ingsw.ps46.server.Game;


/**
 * This interface defines the methods that will be defined by the User Interfaces of the game.
 * Having this interface, the implementation of the clients is independent from the actual User Interface
 * that the client wants to use.
 * 
 * @author Alessia Mondolo
 * @version 1.1
 */
public interface View { 
	
	public void setGame(Game game);
	
	public void printMessage(String message);

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
