package it.polimi.ingsw.ps46.server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

import it.polimi.ingsw.ps46.utils.ReadInput;


public class ConsoleView extends View {
	
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
	
	
	public void update(Observable obs, Object obj) {
		((EventAcceptor) obj).accept(this);
	}
	
	
	public void visit(EventMessage eventMessage) {
		//output.println("Game state: " + game.getGameState().toString());
		//output.println("Message: " + eventMessage.getMessage().toString());
		switch(eventMessage.getMessage()) {
		case START_GAME :
			welcomeMessage();
			break;
		case CHANGED_CURRENT_PLAYER :
			GameState gameState = game.getGameState();
			switch(gameState) {
			case SETUP_PLAYERS_USERNAME :
				getPlayerUsername(game.getCurrentPlayer().getIdPlayer());
				break;
			case SETUP_PLAYERS_COLOR :
				getPlayerColor(game.getCurrentPlayer().getUsername());
				break;
			case GET_PLAYER_ACTION : 
				printPlayerStatus();
				String action = getPlayerAction().toString();
				setChanged();
				notifyObservers(new EventStringInput(action, InputType.PLAYER_ACTION));
				getFamilyMember();
				getServants();
				break;
			default:
				break;
			}
			break;
		case SET_INITIAL_ORDER :
			showInitialOrder();
			break;
		case UPDATE_ROUND_INFO : 
			updateRoundInfo();
			break;
		case THROWN_DICE :
			printBoard();
			break;
			/*
		case UPDATE_CURRENT_PLAYER_STATE :
			printPlayerStatus();*/
		default:
			break;
		}
	}


	
	public void visit(EventMV eventMV) {
	}
	
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
	public void welcomeMessage() {
		output.println("Welcome to the game Lorenzo Il Magnifico!");
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.GAME_STARTED));
	}
	
	
	
	public void getPlayerUsername(int id) {
		
		output.println("Player " + id + ": what is your username?");
		String username = input.stringFromConsole();
		
		setChanged();
		notifyObservers(new EventStringInput(username, InputType.PLAYER_USERNAME));
		
	}
	
	
	
	public void showInitialOrder() {
		
		output.println("The initial game order will be:" + i);
		i++;
		int position = 1;
		
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			output.println(position + ". " + player.getUsername());
			position++;
		}
		
		output.println("\n");
		
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.SET_INITIAL_ORDER));
	}
	
	
	
	public String getPlayerColor(String username) {
		output.println(username + ": which color do you want?");
		int index = 1;
		for(ListIterator<String> iterator=colors.listIterator(); iterator.hasNext();){
			String color =iterator.next();
			output.println(index + ". " + color);
			index++;
		}
		int color = input.IntegerFromConsole(1, colors.size()) - 1;
		output.println("Your color will be " + colors.get(color) + ".\n");
		colors.remove(color);
		return colors.get(color);
	}
	
	
	
	public void printBoard() {
		output.println("THIS IS THE BOARD OF LORENZO IL MAGNIFICO:");
		output.println(" ________________________________________________________________________ ");
		output.println("|                                                                        |");
		output.println("|     GREEN TOWER      BLUE TOWER      YELLOW TOWER     PURPLE TOWER     |");
		output.println("|    _____________    _____________    _____________    _____________    |");
		output.println("|   |   FLOOR 4   |  |   FLOOR 4   |  |   FLOOR 4   |  |   FLOOR 4   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Bonus: 2  |  |   Bonus: 2  |  |   Bonus: 2  |  |   Bonus: 2  |   |");
		output.println("|   |     wood    |  |    stone    |  | military pt.|  |    money    |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 7   |  |   Cost: 7   |  |   Cost: 7   |  |   Cost: 7   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.println("|   |   yellow    |  |   green     |  |     red     |  |    blue     |   |");
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 3   |  |   FLOOR 3   |  |   FLOOR 3   |  |   FLOOR 3   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Bonus: 1  |  |   Bonus: 1  |  |   Bonus: 1  |  |   Bonus: 1  |   |");
		output.println("|   |     wood    |  |    stone    |  | military pt.|  |    money    |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 5   |  |   Cost: 5   |  |   Cost: 5   |  |   Cost: 5   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 2   |  |   FLOOR 2   |  |   FLOOR 2   |  |   FLOOR 2   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Bonus:   |  |    Bonus:   |  |    Bonus:   |  |    Bonus:   |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 3   |  |   Cost: 3   |  |   Cost: 3   |  |   Cost: 3   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 1   |  |   FLOOR 1   |  |   FLOOR 1   |  |   FLOOR 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Bonus:   |  |    Bonus:   |  |    Bonus:   |  |    Bonus:   |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.println("|   |      -      |  |      -      |  |      -      |  |      -      |   |");
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
		setChanged();
		notifyObservers(new EventStringInput(color, InputType.FAMILY_MEMBER_CHOICE));
	}
	
	public void getServants() {
		output.println("How many servants do you want to add to your family member?");
		int servants = input.IntegerFromConsole(0, 50);
		Integer aux = new Integer(servants);
		
		setChanged();
		notifyObservers(new EventStringInput(aux.toString(), InputType.SERVANTS_USED));
		
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.ACTION_SENT));
		
	}
	
	public void printPlayerStatus() {
		Player player = game.getCurrentPlayer();
		output.println(player.getUsername() + ": it's now your turn.");
		output.println("This is what you have:");
		output.println(game.getCurrentPlayer().getPlayerResourceSet().toString());
		output.println("\n");
		//TODO print cards
	}
	
	public void updateRoundInfo() {
		output.println("==========================================================================");
		output.println("We are now playing round " + game.getCurrentRound() + " of period " + game.getCurrentPeriod() + ".");
		output.println("\n");
	}
	
}
