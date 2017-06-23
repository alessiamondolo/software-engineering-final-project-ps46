package it.polimi.ingsw.ps46.client;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import it.polimi.ingsw.ps46.server.ActionSpaceName;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.utils.ReadInput;


public class ConsoleView implements View {
	
	private ReadInput input;
	private PrintStream output;
	private int i = 1;
	private List<String> colors = new ArrayList<String>();
	
	private Game game;
	
	public ConsoleView(Game game, OutputStream output) {
		this.game = game;
		this.output = new PrintStream(output);
		input = new ReadInput();
		
		colors.add("Red");
		colors.add("Yellow");
		colors.add("Blue");
		colors.add("Green");
	}
	
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
	public void welcomeMessage() {
		output.println("==========================================================================");
		output.println("==========================================================================");
		output.println("Welcome to the game Lorenzo Il Magnifico!");
	}
	
	
	
	public String getGameMode() {
		
		output.println("==========================================================================");
		output.println("In which game mode do you want to play?");
		output.println("1. Basic");
		output.println("2. Advanced");
		int gameMode = input.IntegerFromConsole(1, 2);
		if (gameMode == 1)
			return "BASIC_GAME_MODE";
		else
			return "ADVANCED_GAME_MODE";
	}
	
	
	
	public String getPlayerUserame(int id) {
		
		output.println("==========================================================================");
		output.println("Player " + id + ": what is your username?");
		String username = input.stringFromConsole();	
		return username;
	}
	
	
	
	public void showInitialOrder() {
		
		output.println("==========================================================================");
		output.println("The initial game order will be:");
		int position = 1;
		
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			output.println(position + ". " + player.getUsername());
			position++;
		}
	}
	
	
	
	public String getPlayerColor(String username) {
		output.println("==========================================================================");
		output.println(username + ": which color do you want?");
		int index = 1;
		for(ListIterator<String> iterator=colors.listIterator(); iterator.hasNext();){
			String color =iterator.next();
			output.println(index + ". " + color);
			index++;
		}
		int color = input.IntegerFromConsole(1, colors.size()) - 1;
		output.println("Your color will be " + colors.get(color));
		colors.remove(color);
		return colors.get(color);
	}
	
	
	
	public void printBoard() {
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
		
		output.println("THIS IS THE BOARD OF LORENZO IL MAGNIFICO:");
		output.println(" ________________________________________________________________________ ");
		output.println("|                                                                        |");
		output.println("|     GREEN TOWER      BLUE TOWER      YELLOW TOWER     PURPLE TOWER     |");
		output.println("|    _____________    _____________    _____________    _____________    |");
		output.println("|   |   FLOOR 4   |  |   FLOOR 4   |  |   FLOOR 4   |  |   FLOOR 4   |   |");
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
		output.println("|   |   FLOOR 3   |  |   FLOOR 3   |  |   FLOOR 3   |  |   FLOOR 3   |   |");
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
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 2   |  |   FLOOR 2   |  |   FLOOR 2   |  |   FLOOR 2   |   |");
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
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 1   |  |   FLOOR 1   |  |   FLOOR 1   |  |   FLOOR 1   |   |");
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
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|                                                                        |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|           PRODUCTION SPACES                 HARVEST SPACES             |");
		output.println("|    _____________    ____(3+)_____    _____________    ____(3+)_____    |");
		output.println("|   |             |  |             |  |             |  |             |   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   | Penality: - |  | Penality: 3 |  | Penality: - |  | Penality: 3 |   |");
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|          1                2                1                2          |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|                             MARKET SPACES                              |");
		output.println("|    _____________    _____________    _____(4)_____    _____(4)_____    |");
		output.println("|   |             |  |             |  |             |  |             |   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |  Bonus: 5   |  |  Bonus: 5   |  |  Bonus: 2   |  |  Bonus: 2   |   |");
		output.println("|   |    money    |  |  servants   |  | military pt,|  |  different  |   |");
		output.println("|   |             |  |             |  |   2 money   |  |counsil priv.|   |");
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|          1                2                3                4          |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|           COUNSIL SPACE                            DICE                |");
		output.println("|    ______________________________         _____    _____    _____      |");
		output.println("|   |         |                    |       |     |  |     |  |     |     |");
		output.println("|   |  Cost:  | Bonus: 1 counsil   |       |  " + game.getDice().get("Black").getValue() +
				"  |  |  " + game.getDice().get("White").getValue() + "  |  |  " + game.getDice().get("Orange").getValue() + "  |     |");
		output.println("|   |    1    | privilege, 1 money |       |_____|  |_____|  |_____|     |");
		output.println("|   |_________|____________________|        black    white   orange      |");
		output.println("|                                                                        |");
		output.println("|________________________________________________________________________|");
		output.println("\n");
		
		output.println("These are the cards available on the board:");
		int cardNumber = 1;
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for (int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
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
	
	public ActionSpaceName getPlayerAction() {
		output.println("Where do you want to move?");
		output.println("1. Green Tower");
		output.println("2. Blue Tower");
		output.println("3. Yellow Tower");
		output.println("4. Purple Tower");
		output.println("5. Production Spaces");
		output.println("6. Harvest Spaces");
		output.println("7. Council Space");
		int move = input.IntegerFromConsole(1, 7);
		switch(move) {
			case 1 : {
				output.println("In witch floor do you want to move? (1, 2, 3, 4)");
				int space = input.IntegerFromConsole(1, 4);
				switch(space) {
					case 1 :
						return ActionSpaceName.GREEN_TOWER_FLOOR_1;
					case 2 :
						return ActionSpaceName.GREEN_TOWER_FLOOR_2;
					case 3 :
						return ActionSpaceName.GREEN_TOWER_FLOOR_3;
					case 4 :
						return ActionSpaceName.GREEN_TOWER_FLOOR_4;
					default:
						return null;
				}
			}
			case 2 : {
				output.println("In witch floor do you want to move? (1, 2, 3, 4)");
				int space = input.IntegerFromConsole(1, 4);
				switch(space) {
					case 1 :
						return ActionSpaceName.BLUE_TOWER_FLOOR_1;
					case 2 :
						return ActionSpaceName.BLUE_TOWER_FLOOR_2;
					case 3 :
						return ActionSpaceName.BLUE_TOWER_FLOOR_3;
					case 4 :
						return ActionSpaceName.BLUE_TOWER_FLOOR_4;
					default:
						return null;
				}
			}
			case 3 : {
				output.println("In witch floor do you want to move? (1, 2, 3, 4)");
				int space = input.IntegerFromConsole(1, 4);
				switch(space) {
					case 1 :
						return ActionSpaceName.YELLOW_TOWER_FLOOR_1;
					case 2 :
						return ActionSpaceName.YELLOW_TOWER_FLOOR_2;
					case 3 :
						return ActionSpaceName.YELLOW_TOWER_FLOOR_3;
					case 4 :
						return ActionSpaceName.YELLOW_TOWER_FLOOR_4;
					default:
						return null;
				}
			}
			case 4 : {
				output.println("In witch floor do you want to move? (1, 2, 3, 4)");
				int space = input.IntegerFromConsole(1, 4);
				switch(space) {
					case 1 :
						return ActionSpaceName.PURPLE_TOWER_FLOOR_1;
					case 2 :
						return ActionSpaceName.PURPLE_TOWER_FLOOR_2;
					case 3 :
						return ActionSpaceName.PURPLE_TOWER_FLOOR_3;
					case 4 :
						return ActionSpaceName.PURPLE_TOWER_FLOOR_4;
					default:
						return null;
				}
			}
			case 5: {
				output.println("Do you want to move in the first or second space? (1, 2)");
				int space = input.IntegerFromConsole(1, 2);
				switch(space) {
				case 1 :
					return ActionSpaceName.PRODUCTION_SPACE_1;
				case 2 :
					return ActionSpaceName.PRODUCTION_SPACE_2;
				default:
					return null;
				}
			}
			case 6: {
				output.println("Do you want to move in the first or second space? (1, 2)");
				int space = input.IntegerFromConsole(1, 2);
				switch(space) {
				case 1 :
					return ActionSpaceName.HARVEST_SPACE_1;
				case 2 :
					return ActionSpaceName.HARVEST_SPACE_2;
				default:
					return null;
				}
			}
			case 7: {
				return ActionSpaceName.COUNSIL_SPACE;
			}
		}
		return null;
	}
	
	
	public void getFamilyMember() {
		output.println("Which family member do you want to use?");
		int index = 1;
		for(String key : game.getCurrentPlayer().getFamilyMembers().keySet()) {
			if(!game.getCurrentPlayer().getFamilyMembers().get(key).isUsed()) {
				output.println(index + ". " + key);
				index++;
			}
		}
		
		int choice = input.IntegerFromConsole(1, game.getCurrentPlayer().getFamilyMembers().size());
		index = 1;
		String color = null;
		for(String key : game.getCurrentPlayer().getFamilyMembers().keySet()) {
			if(index == choice) {
				color = key;
				break;
			}
			else
				index++;
		}
		
		output.println("You chose the " + color + " family member.");
	}
	
	public void getServants() {
		output.println("How many servants do you want to add to your family member?");
		int servants = input.IntegerFromConsole(0, 50);
		Integer aux = new Integer(servants);		
	}
	
	public void printPlayerStatus() {
		Player player = game.getCurrentPlayer();
		output.println("==========================================================================");
		output.println(player.getUsername() + ": it's now your turn.");
		output.println("This is what you have:");
		output.println("1. Resources:");
		output.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
		output.println("2. Territory Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().isEmpty())
			output.println("You don't have territory cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck());
		output.println("3. Character Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getCharacterDeck().isEmpty())
			output.println("You don't have character cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getCharacterDeck());
		output.println("4. Building Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().isEmpty())
			output.println("You don't have building cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck());
		output.println("5. Venture Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getVentureDeck().isEmpty())
			output.println("You don't have venture cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getVentureDeck());
		output.println("\n");
		//TODO print cards
	}
	
	public void updateRoundInfo() {
		output.println("==========================================================================");
		output.println("We are now playing round " + game.getCurrentRound() + " of period " + game.getCurrentPeriod() + ".");
		output.println("\n");
	}
	
	
	public void showNextTurnOrder() {
		output.println("==========================================================================");
		output.println("The game order for the next round will be:" + i);
		i++;
		int position = 1;
		
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			output.println(position + ". " + player.getUsername());
			position++;
		}		
	}

}
