package it.polimi.ingsw.ps46.client;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import it.polimi.ingsw.ps46.server.BonusTile;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.card.Effect;
import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.utils.ReadInput;


/**
 * This class implements the Command Line User Interface for the game.
 * 
 * @author Alessia Mondolo
 * @version 1.1
 */
public class ConsoleView implements View {
	
	private ReadInput input;
	private PrintStream output;
	
	private Game game = null;
	
	
	/**
	 * Creates a new ConsoleView object.
	 * 
	 * @param output : the output stream that the view will use to print the outputs.
	 */
	public ConsoleView(OutputStream output) {
		this.output = new PrintStream(output);
		input = new ReadInput();
	}
	
	
	
	/**
	 * Sets a new value for the attribute game.
	 * 
	 * @param game : the new game object that we want to assign to the attribute game.
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
	/**
	 * Prints the message received as parameter
	 * 
	 * @param message : the message that has to be print.
	 */
	public void printMessage(String message) {
		output.println(message);
	}
	
	
	
	/**
	 * Prints on the output stream a welcome message for the client.
	 */
	public void welcomeMessage() {
		output.println("==========================================================================");
		output.println("==========================================================================");
		output.println("Welcome to the game Lorenzo Il Magnifico!");
	}
	
	
	
	/**
	 * Gets the username that the client wants to use for the game.
	 * 
	 * @return username : the username received by input from the client.
	 */
	public String getPlayerUserame() {
		output.println("==========================================================================");
		output.println("What is your username?");
		String username = input.stringFromConsole();	
		return username;
	}
	
	
	
	/**
	 * Prints on the output stream the game oder that will be used during the first round of the game.
	 */
	public void showInitialOrder() {
		output.println("==========================================================================");
		output.println("The initial game order will be:");
		int position = 1;
		
		for(Player player : game.getPlayers()){
			output.println(position + ". " + player.getUsername());
			position++;
		}
	}
	
	
	
	/**
	 * Gets the color that the client wants to use for the game. The color can be chosen from a list of colors
	 * that is received as parameter.
	 * 
	 * @param colors : a list of colors from which the client can choose his color.
	 * @return color : the color received by input from the client.
	 */
	public String getPlayerColor(ArrayList<String> colors) {
		output.println("==========================================================================");
		output.println("Which color do you want?");
		int index = 1;
		for(String color : colors){
			output.println(index + ". " + color);
			index++;
		}
		int color = input.IntegerFromConsole(1, colors.size()) - 1;
		output.println("Your color will be " + colors.get(color));
		return colors.get(color);
	}
	
	
	
	/**
	 * Gets which bonus tile the player wants to use for the game.
	 * 
	 * @return choice : the index of the bonus tile chosen by the player.
	 */
	public int getBonusTile() {
		output.println("==========================================================================");
		output.println("Which bonus tile do you want?");
		int index = 1;
		for(BonusTile bonusTile : game.getBonusTiles()){
			if(bonusTile.isAdvancedPersonalBoard()) {
				output.println(index + ". " + bonusTile);
				index++;
			}
		}
		int choice = input.IntegerFromConsole(1, game.getBonusTiles().size()-1);
		return choice;
	}
	
	
	
	/**
	 * Prints on the output stream the info about the current round.
	 */
	public void updateRoundInfo() {
		output.println("==========================================================================");
		output.println("We are now playing round " + game.getCurrentRound() + " of period " + game.getCurrentPeriod() + ".");
	}
	
	
	
	/**
	 * Prints on the output stream the board of the game.
	 */
	public void printBoard() {
		//BEGIN of the setup of the parameters that will be shown in the board
		int quantity;
		int cost;
		String name;
		String color;

		int[][] towersBonusQuantity = new int[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		String[][] towersBonusName = new String[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		int[][] towersCost = new int[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		String[][] towersPlayers = new String[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for (int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
				
				if(!game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().getResourcesMap().isEmpty()) {
					quantity = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().toArray().get(0).getQuantity();
				}
				else 
					quantity = 0;
				towersBonusQuantity[tower][floor] = quantity;
				
				if(!game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().getResourcesMap().isEmpty()) {
					name = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().toArray().get(0).getId();
					switch(name) {
					case "Wood" :
						towersBonusName[tower][floor] = "|     wood    |";
						break;
					case "Stones" :
						towersBonusName[tower][floor] = "|   stones    |";
						break;
					case "Money" :
						towersBonusName[tower][floor] = "|    money    |";
						break;
					case "Servants" :
						towersBonusName[tower][floor] = "|  servants   |";
						break;
					case "MilitaryPoints" :
						towersBonusName[tower][floor] = "| military pt.|";
						break;
					case "VictoryPoints" :
						towersBonusName[tower][floor] = "| victory pt. |";
						break;
					case "FaithPoints" :
						towersBonusName[tower][floor] = "|  faith pt.  |";
						break;
					};
				}
				else 
					towersBonusName[tower][floor] = "|             |";
				
				cost = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getRequiredFamilyMemberValue().getValue();
				towersCost[tower][floor] = cost;
				
				color = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getPlayerColor();
				switch(color) {
				case "Yellow" :
					towersPlayers[tower][floor] = "|   yellow    |";
					break;
				case "Red" :
					towersPlayers[tower][floor] = "|     red     |";
					break;
				case "Blue" :
					towersPlayers[tower][floor] = "|    blue     |";
					break;
				case "Green" :
					towersPlayers[tower][floor] = "|   green     |";
					break;
				case "" :
					towersPlayers[tower][floor] = "|      -      |";
					break;
				};
			}
		}
		

		String[] boardBoxesPlayer = new String[8];
		
		
		int index = (game.getBoard().getNumberOfTowers() * game.getBoard().getTower(0).getNumberOfFloors()) + 1;
		int box = 0;
		for(int i = 0; i < 8; i++) {
			if(box < game.getBoard().getBoardBoxes().size()) {
				if(game.getBoard().getBoardBox(box).getId() == (i + index)) {
					color = game.getBoard().getBoardBox(box).getPlayerColor();
					switch(color) {
					case "Yellow" :
						boardBoxesPlayer[i] = "|   yellow    |";
						break;
					case "Red" :
						boardBoxesPlayer[i] = "|     red     |";
						break;
					case "Blue" :
						boardBoxesPlayer[i] = "|    blue     |";
						break;
					case "Green" :
						boardBoxesPlayer[i] = "|   green     |";
						break;
					case "" :
						boardBoxesPlayer[i] = "|      -      |";
						break;
					};
				}
				else {
					boardBoxesPlayer[i] = "|      -      |";
					box--;
				}
			}
			else {
				boardBoxesPlayer[i] = "|      -      |";
			}
			box++;
		}
		//END of the setup of the parameters that will be shown in the board
		
		output.println("==========================================================================");
		output.println("THIS IS THE BOARD OF LORENZO IL MAGNIFICO:");
		output.println(" ________________________________________________________________________ ");
		output.println("|                                                                        |");
		output.println("|     GREEN TOWER      BLUE TOWER      YELLOW TOWER     PURPLE TOWER     |");
		output.println("|    _____________    _____________    _____________    _____________    |");
		output.println("|   | SPACE ID: 4 |  | SPACE ID: 8 |  | SPACE ID: 12|  | SPACE ID: 16|   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][3], towersBonusQuantity[1][3], towersBonusQuantity[2][3], towersBonusQuantity[3][3]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][3], towersBonusName[1][3], towersBonusName[2][3], towersBonusName[3][3]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Cost: %d   |  |   Cost: %d   |  |   Cost: %d   |  |   Cost: %d   |   |\n",
				towersCost[0][3], towersCost[1][3], towersCost[2][3], towersCost[3][3]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      4      |  |      8      |  |     12      |  |     16      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   | SPACE ID: 3 |  | SPACE ID: 7 |  | SPACE ID: 11|  | SPACE ID: 15|   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][2], towersBonusQuantity[1][2], towersBonusQuantity[2][2], towersBonusQuantity[3][2]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][2], towersBonusName[1][2], towersBonusName[2][2], towersBonusName[3][2]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 5   |  |   Cost: 5   |  |   Cost: 5   |  |   Cost: 5   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      3      |  |      7      |  |     11      |  |     15      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][2], towersPlayers[1][2], towersPlayers[2][2], towersPlayers[3][2]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   | SPACE ID: 2 |  | SPACE ID: 6 |  | SPACE ID: 10|  | SPACE ID: 14|   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][1], towersBonusQuantity[1][1], towersBonusQuantity[2][1], towersBonusQuantity[3][1]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][1], towersBonusName[1][1], towersBonusName[2][1], towersBonusName[3][1]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 3   |  |   Cost: 3   |  |   Cost: 3   |  |   Cost: 3   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      2      |  |      6      |  |     10      |  |     14      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][1], towersPlayers[1][1], towersPlayers[2][1], towersPlayers[3][1]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   | SPACE ID: 1 |  | SPACE ID: 5 |  | SPACE ID: 9 |  | SPACE ID: 13|   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][0], towersBonusQuantity[1][0], towersBonusQuantity[2][0], towersBonusQuantity[3][0]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][0], towersBonusName[1][0], towersBonusName[2][0], towersBonusName[3][0]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      1      |  |      5      |  |      9      |  |     13      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][0], towersPlayers[1][0], towersPlayers[2][0], towersPlayers[3][0]);
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|                                                                        |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|           PRODUCTION SPACES                 HARVEST SPACES             |");
		output.println("|    _____________    ____(3+)_____    _____________    ____(3+)_____    |");
		output.println("|   | SPACE ID: 17|  | SPACE ID: 18|  | SPACE ID: 19|  | SPACE ID: 20|   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   | Penality: - |  | Penality: 3 |  | Penality: - |  | Penality: 3 |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |  Players:   |  |   Player:   |  |  Players:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", boardBoxesPlayer[0], "| see below   |", boardBoxesPlayer[2], "| see below   |");
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|                                                                        |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|                             MARKET SPACES                              |");
		output.println("|    _____________    _____________    _____(4)_____    _____(4)_____    |");
		output.println("|   | SPACE ID: 21|  | SPACE ID: 22|  | SPACE ID: 23|  | SPACE ID: 24|   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |  Bonus: 5   |  |  Bonus: 5   |  |  Bonus: 2   |  |  Bonus: 2   |   |");
		output.println("|   |    money    |  |  servants   |  | military pt,|  |  different  |   |");
		output.println("|   |             |  |             |  |   2 money   |  |counsil priv.|   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", boardBoxesPlayer[4], boardBoxesPlayer[5], boardBoxesPlayer[6], boardBoxesPlayer[7]);
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|                                                                        |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|        COUNSIL SPACE - ID 25                       DICE                |");
		output.println("|    ______________________________         _____    _____    _____      |");
		output.println("|   |         |                    |       |     |  |     |  |     |     |");
		output.println("|   |  Cost:  | Bonus: 1 counsil   |       |  " + game.getDice().get("Black").getValue() +
				"  |  |  " + game.getDice().get("White").getValue() + "  |  |  " + game.getDice().get("Orange").getValue() + "  |     |");
		output.println("|   |    1    | privilege, 1 money |       |_____|  |_____|  |_____|     |");
		output.println("|   |_________|____________________|        black    white   orange      |");
		output.println("|                                                                        |");
		output.println("|________________________________________________________________________|");
		output.println("\n");
		
		output.println("==========================================================================");
		if(!game.getCouncilPalaceOrder().isEmpty()) {
			output.println("These are the players on the coucil space, ordered by arrival:");
			int indexPlayer = 1;
			for(Player player : game.getCouncilPalaceOrder()) {
				output.println(indexPlayer + ". " + player.getColor());
				indexPlayer++;
			}
		}
		else 
			output.println("There are no players in the council space for now.");
		
		if(game.getNumberPlayers() > 2) {
			output.println("==========================================================================");
			output.println("These are the players on the production space with ID 18:");
			int indexPlayer = 1;
			for (Player player : game.getPlayers()) {
				for(String colorKey : player.getFamilyMembersMap().keySet()) {
					FamilyMember familyMember = player.getFamilyMember(colorKey);
					int position = familyMember.getPositionOfFamilyMember();
					if(position == 18) {
						output.println(indexPlayer + ". " + player.getColor());
						indexPlayer++;
					}
				}
			}
			
			output.println("==========================================================================");
			output.println("These are the players on the harvest space with ID 20:");
			indexPlayer = 1;
			for (Player player : game.getPlayers()) {
				for(String colorKey : player.getFamilyMembersMap().keySet()) {
					FamilyMember familyMember = player.getFamilyMember(colorKey);
					int position = familyMember.getPositionOfFamilyMember();
					if(position == 20)
						output.println(indexPlayer + ". " + player.getColor());
				}
			}
		}
		
		
		
		output.println("==========================================================================");
		output.println("These are the cards available on the board:");
		int cardNumber = 1;
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for (int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
				output.println("__________________________________________________________________________");
				output.println(cardNumber + ". Tower " + (tower+1) + ", floor " + (floor+1));
				Card card = game.getBoard().getTower(tower).getTowerFloor(floor).getCard();
				if(card != null)
					output.println(card);
				else
					output.println("This card has already been taken!");
				cardNumber++;
			}
		}		
	}
	
	
	
	/**
	 * Prints on the output stream the username of the client that is currently playing his turn.
	 */
	public void printCurrentPlayer() {
		Player player = game.getCurrentPlayer();
		output.println("==========================================================================");
		output.println(player.getUsername() + " is now playing.");
	}
	

	
	/**
	 * Prints on the output stream the status of the client that is currently playing his turn.
	 * It will show the resources and the cards of the player.
	 */
	public void printPlayerStatus() {
		Player player = game.getCurrentPlayer();
		output.println("==========================================================================");
		output.println("It's now your turn.\n");
		output.println("This is what you have:");
		output.println("__________________________________________________________________________");
		output.println("1. Resources:");
		output.println(player.getPersonalBoard().getPlayerResourceSet().toString());
		output.println("__________________________________________________________________________");
		output.println("2. Bonus tile:");
		output.println(player.getPersonalBoard().getBonusTile());
		output.println("__________________________________________________________________________");
		output.println("3. Territory Cards:");
		if(player.getPersonalBoard().getTerritoryDeck().isEmpty())
			output.println("You don't have territory cards for now.");
		else {
			for (Card card : player.getPersonalBoard().getTerritoryDeck())
				output.println(card);
		}
		output.println("__________________________________________________________________________");
		output.println("4. Character Cards:");
		if(player.getPersonalBoard().getCharacterDeck().isEmpty())
			output.println("You don't have character cards for now.");
		else {
			for (Card card : player.getPersonalBoard().getCharacterDeck())
				output.println(card);
		}
		output.println("__________________________________________________________________________");
		output.println("5. Building Cards:");
		if(player.getPersonalBoard().getBuildingDeck().isEmpty())
			output.println("You don't have building cards for now.");
		else {
			for (Card card : player.getPersonalBoard().getBuildingDeck())
				output.println(card);
		}
		output.println("__________________________________________________________________________");
		output.println("6. Venture Cards:");
		if(player.getPersonalBoard().getVentureDeck().isEmpty())
			output.println("You don't have venture cards for now.");
		else {
			for (Card card : player.getPersonalBoard().getVentureDeck())
				output.println(card);
		}
		output.println("\n");
	}
	
	
	
	/**
	 * Gets the ID of the action space where the client wants to move during his action.
	 * 
	 * @return move : the ID of the action space.
	 */
	public int getPlayerAction() {
		
		output.println("Where do you want to move? (Insert the ID of the action space)");
		int move = input.IntegerFromConsole(1, 25);
		return move;
		
	}
	
	
	
	/**
	 * Gets the family member that the client wants to move during his action.
	 * 
	 * @return color : the color of the family member.
	 */
	public String getFamilyMember() {
		Map<String,FamilyMember> familyMembersAvailable = new LinkedHashMap<String,FamilyMember>();
		output.println("Which family member do you want to use?");
		int index = 1;
		for(String key : game.getCurrentPlayer().getFamilyMembersMap().keySet()) {
			if(!game.getCurrentPlayer().getFamilyMembersMap().get(key).isUsed()) {
				familyMembersAvailable.put(key, game.getCurrentPlayer().getFamilyMembersMap().get(key));
				output.println(index + ". " + key);
				index++;
			}
		}
		
		int choice = input.IntegerFromConsole(1, (index-1));
		index = 1;
		String color = null;
		for(String key : familyMembersAvailable.keySet()) {
			if(index == choice) {
				color = key;
				break;
			}
			else
				index++;
		}
		
		output.println("You chose the " + color + " family member.");
		return color;
	}
	
	
	
	/**
	 * Gets the number of servants that the client wants to add to his family member for the current action.<br>
	 * The maximum number of servants that the player can use is taken from the number of servants that he has at the moment of the action.
	 * 
	 * @return servants : the number of servants that the client wants to use.
	 */
	public int getServants() {
		output.println("How many servants do you want to add to your family member?");
		int myServants = game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").getQuantity();
		output.println("You still have " + myServants + " servants.");
		int servants = input.IntegerFromConsole(0, myServants);
		return servants;
	}
	
	
	
	/**
	 * Gets which of the two optional costs the player wants to pay.
	 * 
	 * @return choice : the cost chosen by the player.
	 */
	public int getCostCoice(ResourceSet cost1, ResourceSet cost2) {
		output.println("Which of these costs do you want to pay for the card?");
		output.println("1. " + cost1.toString());
		output.println("2. " + cost2.toString());
		int choice = input.IntegerFromConsole(1, 2);
		return choice;
	}

	
	
	/**
	 * Prints on the output stream the action that the current player made during his turn.
	 */
	public void printPlayerAction() {
		Player player = game.getCurrentPlayer();
		output.println("==========================================================================");
		output.println(player.getUsername() + " moved on the action space with ID ");
	}
	
	
	
	/**
	 * Gets which of the two optional effects the player wants to activate.
	 * 
	 * @return choice : the effect chosen by the player.
	 */
	public int getEffectCoice(Effect effect1, Effect effect2) {
		output.println("==========================================================================");
		output.println("Which of these effect do you want to activate?");
		output.println("1. " + effect1.toString());
		output.println("2. " + effect2.toString());
		int choice = input.IntegerFromConsole(1, 2);
		return choice;
	}
	
	
	
	/**
	 * Gets which council privileges the player wants to get.<br>
	 * If the player has more than one council privilege, it will get all of them, excluding each time the privileges
	 * already taken.
	 * 
	 * @return councilPrivileges : the list of the council privileges chosen by the player.
	 */
	public ArrayList<Integer> getCouncilPrivilege() {
		int privileges = game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("CouncilPrivilege").getQuantity();
		ArrayList<Integer> councilPrivileges = new ArrayList<Integer>();
		output.println("You have " + privileges + " council privileges.");
		while(privileges > 0) {
			output.println("Which bonus do you choose?");
			int index = 1;
			for(ResourceSet resourceSet : game.getCouncilPrivileges()) {
				output.println(index + ". " + resourceSet.toString());
				index++;
			}
			int choice = input.IntegerFromConsole(1, (index-1));
			choice--;
			Integer bonus = new Integer(choice);
			if(!councilPrivileges.contains(bonus)) {
				councilPrivileges.add(bonus);
				privileges--;
				if(privileges > 0)
					output.println("You still have " + privileges + " council privileges.");
			}
			else
				output.println("You already have this bonus.");
		}
		return councilPrivileges;
	}
	
	
	
	/**
	 * Prints on the output stream the game oder that will be used during the next round of the game.
	 */
	public void showNextTurnOrder() {
		output.println("==========================================================================");
		output.println("The game order for the next round will be:");
		int position = 1;
		
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			output.println(position + ". " + player.getUsername());
			position++;
		}		
	}


	
	/**
	 * Gets if the player wants or not to support the church.
	 * 
	 * @return choice :<br>
	 * <ul>
	 * <li>1, if the player wants to support the church</li>
	 * <li>2, if the player doesn't want to support the church</li>
	 * </ul>
	 */
	public int getVaticanSupport() {
		output.println("==========================================================================");
		output.println("Do you want to support the Church?");
		output.println("1. Yes");
		output.println("2. No");

		int choice = input.IntegerFromConsole(1, 2);
		return choice;
	}



	/**
	 * Gets the ID of the action space where the client wants to move during his extra move.
	 * 
	 * @return move : the ID of the action space.
	 */
	public int getExtraMove(ExtraMoveEffect effect) {
		int move = 0;
		switch(effect.getType()) {
		case "ActivateHarvestAction":
			output.println("You can perform a Harvest action at value " + effect.getValueOfTheExtraMove() +
					" without placing a Family Member (you can change the action value with servants and card effects).");
			output.println("Where do you want to move? (Insert the ID of the action space)");
			move = input.IntegerFromConsole(17, 18);
			break;
		case "ActivateProdutionAction":
			output.println("You can perform a Production action at value " + effect.getValueOfTheExtraMove() +
					" without placing a Family Member (you can change the action value with servants and card effects).");
			output.println("Where do you want to move? (Insert the ID of the action space)");
			move = input.IntegerFromConsole(19, 20);
			break;
		case "MoveToActionSpaceAction": {
			switch(effect.getWhichActionSpace()) {
			case "AllTowers" :
				output.println("You can perform a Production action at value " + effect.getValueOfTheExtraMove() +
						" to take va card of any type without placing a Family Member (you can change the action value with servants and card effects).");
				output.println("Where do you want to move? (Insert the ID of the action space)");
				move = input.IntegerFromConsole(1, 16);
				break;
			case "GreenTower" :
				output.println("You can perform a Production action at value " + effect.getValueOfTheExtraMove() +
						" to take a territory card without placing a Family Member (you can change the action value with servants and card effects).");
				output.println("Where do you want to move? (Insert the ID of the action space)");
				move = input.IntegerFromConsole(1, 4);
				break;
			case "YellowTower" :
				output.println("You can perform a Production action at value " + effect.getValueOfTheExtraMove() +
						" to take a building card without placing a Family Member (you can change the action value with servants and card effects).");
				output.println("Where do you want to move? (Insert the ID of the action space)");
				move = input.IntegerFromConsole(9, 12);
				break;
			case "BlueTower" :
				output.println("You can perform a Production action at value " + effect.getValueOfTheExtraMove() +
						" to take a character card without placing a Family Member (you can change the action value with servants and card effects).");
				output.println("Where do you want to move? (Insert the ID of the action space)");
				move = input.IntegerFromConsole(5, 8);
				break;
			case "PurpleTower" :
				output.println("You can perform a Production action at value " + effect.getValueOfTheExtraMove() +
						" to take a venture card without placing a Family Member (you can change the action value with servants and card effects).");
				output.println("Where do you want to move? (Insert the ID of the action space)");
				move = input.IntegerFromConsole(13, 16);
				break;
			}
			}
		}
		
		return move;
	}


	
	/**
	 * Gets which leader cards the player wants to activate.
	 * 
	 * @return leaderCardsActivated : list of the indexes of the leader cards to activate
	 */
	public ArrayList<Integer> getActivationLeaderCards() {
		output.println("==========================================================================");
		int numberOfLeaderCardActivable = 0;
		ArrayList<Integer> leaderCardsActivated = new ArrayList<Integer>();
		
		for(String string : game.getCurrentPlayer().getLeaderCards().keySet()) {
			if(game.getCurrentPlayer().getLeaderCards().get(string).isActivable(game)){
				numberOfLeaderCardActivable++;
				}
		}
		output.println("You have " + numberOfLeaderCardActivable + " Leader Cards activable.");
		
		while(numberOfLeaderCardActivable > 0) {
			int index = 0;
			
			output.println("Do you want to activate a Leader Card?");
			output.println(index + ". I don't want to activate any Leader Card");
			index++;
			for(String string : game.getCurrentPlayer().getLeaderCards().keySet()) {
				if(game.getCurrentPlayer().getLeaderCards().get(string).isActivable(game)){
					output.println(index + ". " + string);
					index++;
					}
			}
			int choice = input.IntegerFromConsole(0, (index-1)); // in attesa di un input da 0 a 4
			Integer leaderCard = new Integer(choice);
			if(leaderCard != 0){
				if(!leaderCardsActivated.contains(leaderCard)) {
					leaderCardsActivated.add(leaderCard);
					numberOfLeaderCardActivable--;
					if(numberOfLeaderCardActivable > 0)
						output.println("You still have " + numberOfLeaderCardActivable + " Leader Cards activable.");
				}
				else
					output.println("You already have activated this Leader Card.");
			}
			else 
			{
				leaderCardsActivated.add(leaderCard);
				break;
			}
		}
		return leaderCardsActivated;
	}
	
	

	/**
	 * Gets which leader cards the player wants to discard in exchange of a coucil privilege.
	 * 
	 * @return leaderCardsDiscarded : list of the indexes of the leader cards to discard
	 */
	public ArrayList<Integer> getDiscardLeaderCards(){
		output.println("==========================================================================");
		int numberOfLeaderCardCouldDiscard = 0;
		ArrayList<Integer> leaderCardsDiscarded = new ArrayList<Integer>();
		
		for(String string : game.getCurrentPlayer().getLeaderCards().keySet()) {
			if(!(game.getCurrentPlayer().getLeaderCards().get(string).isActive()) ){
				numberOfLeaderCardCouldDiscard++;
				}
		}
		output.println("You could discard " + numberOfLeaderCardCouldDiscard + " Leader Cards.");
		
		while(numberOfLeaderCardCouldDiscard > 0) {
			int index = 0;
			
			output.println("Do you want to discard a Leader Card to get a council privilege?");
			output.println(index + ". I don't want to discard any Leader Card");
			index++;
			for(String string : game.getCurrentPlayer().getLeaderCards().keySet()) {
				if(!(game.getCurrentPlayer().getLeaderCards().get(string).isActive()) ){
					output.println(index + ". " + string);
					index++;
					}
			}
			int choice = input.IntegerFromConsole(0, (index-1)); // in attesa di un input da 0 a 4
			Integer leaderCardDiscarded = new Integer(choice);
			if(leaderCardDiscarded != 0){
				if(!leaderCardsDiscarded.contains(leaderCardDiscarded)) {
					leaderCardsDiscarded.add(leaderCardDiscarded);
					numberOfLeaderCardCouldDiscard--;
					if(numberOfLeaderCardCouldDiscard > 0)
						output.println("You could discard " + numberOfLeaderCardCouldDiscard + " Leader Cards.");
				}
				else
					output.println("You already have discarded this Leader Card.");
			}
			else 
			{
				leaderCardsDiscarded.add(leaderCardDiscarded);
				break;
			}
		}
		return leaderCardsDiscarded;
	}



	/**
	 * Prints on the output stream the final score the game.
	 */
	public void showFinalScores() {
		output.println("==========================================================================");
		output.println("This is the final score of the game:");
		int index = 1;
		int count = 1;
		
		for(Integer score : game.getFinalScoresOrder().keySet()) {
			ArrayList<Player> players = game.getFinalScoresOrder().get(score);
			while(!players.isEmpty()) {
				Player player = players.get(0);
				output.println(index + ". " + player.getUsername() + ": " + score.intValue());
				players.remove(0);
				count++;
			}
			index = count;
		}
		output.println("==========================================================================");
	}
}
