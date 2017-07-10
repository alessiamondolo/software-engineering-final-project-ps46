package it.polimi.ingsw.ps46.client;

import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.Effect;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


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

	public String getPlayerUserame();

	public void showInitialOrder();

	public String getPlayerColor(ArrayList<String> colors);

	public int getBonusTile();

	public void updateRoundInfo();
	
	public void printBoard();
	
	public void printCurrentPlayer();

	public void printPlayerStatus();

	public int getPlayerAction();
	
	public String getFamilyMember();
	
	public int getServants();
	
	public void printPlayerAction();

	public int getCostCoice(ResourceSet cost1, ResourceSet cost2);

	public int getEffectCoice(Effect effect1, Effect effect2);
	
	public void showNextTurnOrder();

	public ArrayList<Integer> getCouncilPrivilege();
	
}
